package dev.coauth.example.endpoint;

import dev.coauth.example.dto.*;
import dev.coauth.example.service.CoAuthConnectionService;
import dev.coauth.example.util.RandomCodeVerifierGenerator;
import dev.coauth.example.util.SHAUtil;
import dev.coauth.example.util.UserDatabase;
import dev.coauth.example.util.UserDbModel;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegisterEndpoint {

    @ConfigProperty(name = "coauth.api.key")
    String coAuthApiKey;

    @RestClient
    CoAuthConnectionService coAuthConnectionService;


    @POST
    @Path("/register-start")
    public GenericResponseDto register(ExampleRegisterRequestDto exampleRegisterRequestDto) {
        System.out.println("HERE");
        TotpRegisterRequestDto totpRegisterRequestDto=new TotpRegisterRequestDto();
        totpRegisterRequestDto.setUserId(exampleRegisterRequestDto.getUserId());
        totpRegisterRequestDto.setModule("TOTP");
        String codeVerifier= RandomCodeVerifierGenerator.generateRandomAlphanumeric();
        String codeChallenge= SHAUtil.generateSHA256(codeVerifier);
        totpRegisterRequestDto.setCodeChallenge(codeChallenge);
        TotpRegisterResponseDto totpRegisterResponseDto=coAuthConnectionService.registerTotpGenerate(coAuthApiKey,totpRegisterRequestDto);
        ExampleRegisterResponseDto exampleRegisterResponseDto=new ExampleRegisterResponseDto();
        exampleRegisterResponseDto.setUserId(exampleRegisterRequestDto.getUserId());
        exampleRegisterResponseDto.setCode(totpRegisterResponseDto.getData());

        UserDbModel userDbModel=UserDatabase.dbBeanHashMap.get(exampleRegisterRequestDto.getUserId());

        if(userDbModel==null || userDbModel.getStatus().equals("P")){
            userDbModel=new UserDbModel();
            userDbModel.setUserId(exampleRegisterRequestDto.getUserId());
            userDbModel.setStatus("P");
            userDbModel.setCurrentCode(totpRegisterResponseDto.getData());
            userDbModel.setCodeVerifier(codeVerifier);
            userDbModel.setPassword(exampleRegisterRequestDto.getPassword());
            UserDatabase.dbBeanHashMap.put(exampleRegisterRequestDto.getUserId(),userDbModel);
        }
        return GenericResponseDto.builder().data(exampleRegisterResponseDto).build();
    }

    @POST
    @Path("/register-verify")
    public GenericResponseDto registerVerify(ExampleRegisterRequestDto exampleRegisterRequestDto) {

        UserDbModel userDbModel=UserDatabase.dbBeanHashMap.get(exampleRegisterRequestDto.getUserId());

        VerifyRequestDto totpVerifyRequestDto=new VerifyRequestDto();
        totpVerifyRequestDto.setCodeVerifier(userDbModel.getCodeVerifier());
        totpVerifyRequestDto.setCode(userDbModel.getCurrentCode());
        TotpRegisterResponseDto totpRegisterResponseDto=coAuthConnectionService.registerTotpStatus(coAuthApiKey,totpVerifyRequestDto);

        if(userDbModel.getStatus().equals("P")){
            userDbModel.setUserId(exampleRegisterRequestDto.getUserId());
            userDbModel.setStatus(totpRegisterResponseDto.getData());
            userDbModel.setCurrentCode("");
            userDbModel.setCodeVerifier("");
            userDbModel.setPassword(exampleRegisterRequestDto.getPassword());
            UserDatabase.dbBeanHashMap.put(exampleRegisterRequestDto.getUserId(),userDbModel);
        }
        return GenericResponseDto.builder().data(totpRegisterResponseDto.getData()).build();
    }
}
