package dev.coauth.example.endpoint;

import dev.coauth.example.dto.*;
import dev.coauth.example.service.CoAuthConnectionService;
import dev.coauth.example.util.RandomCodeVerifierGenerator;
import dev.coauth.example.util.SHAUtil;
import dev.coauth.example.util.UserDatabase;
import dev.coauth.example.util.UserDbModel;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransferVerificationEndpoint {

    @ConfigProperty(name = "coauth.api.key")
    String coAuthApiKey;

    @RestClient
    CoAuthConnectionService coAuthConnectionService;


    @POST
    @Path("/transfer-start")
    public GenericResponseDto verifyGenerate(ExampleRegisterRequestDto exampleRegisterRequestDto) {

        VerifyGenerateRequestDto totpVerifyGenerateRequestDto = new VerifyGenerateRequestDto();
        totpVerifyGenerateRequestDto.setUserId(exampleRegisterRequestDto.getUserId());
        totpVerifyGenerateRequestDto.setModules("TOTP");

        String codeVerifier = RandomCodeVerifierGenerator.generateRandomAlphanumeric();
        String codeChallenge = SHAUtil.generateSHA256(codeVerifier);
        totpVerifyGenerateRequestDto.setCodeChallenge(codeChallenge);
        TotpRegisterResponseDto totpRegisterResponseDto = coAuthConnectionService.verifyTotpGenerate(coAuthApiKey, totpVerifyGenerateRequestDto);

        UserDbModel userDbModel = UserDatabase.dbBeanHashMap.get(exampleRegisterRequestDto.getUserId());
        userDbModel.setCurrentCode(totpRegisterResponseDto.getData());
        userDbModel.setCodeVerifier(codeVerifier);
        UserDatabase.dbBeanHashMap.put(exampleRegisterRequestDto.getUserId(), userDbModel);

        ExampleRegisterResponseDto exampleRegisterResponseDto = new ExampleRegisterResponseDto();
        exampleRegisterResponseDto.setUserId(exampleRegisterRequestDto.getUserId());
        exampleRegisterResponseDto.setCode(totpRegisterResponseDto.getData());

        return GenericResponseDto.builder().data(exampleRegisterResponseDto).build();
    }

    @POST
    @Path("/transfer-verify")
    public GenericResponseDto verifyStatus(ExampleRegisterRequestDto exampleRegisterRequestDto) {
        UserDbModel userDbModel = UserDatabase.dbBeanHashMap.get(exampleRegisterRequestDto.getUserId());
        VerifyRequestDto totpVerifyRequestDto = new VerifyRequestDto();
        totpVerifyRequestDto.setCodeVerifier(userDbModel.getCodeVerifier());
        totpVerifyRequestDto.setCode(userDbModel.getCurrentCode());
        TotpRegisterResponseDto totpRegisterResponseDto = coAuthConnectionService.verifyTotpStatus(coAuthApiKey, totpVerifyRequestDto);
        userDbModel.setCurrentCode("");
        userDbModel.setCodeVerifier("");
        UserDatabase.dbBeanHashMap.put(exampleRegisterRequestDto.getUserId(), userDbModel);
        return GenericResponseDto.builder().data(totpRegisterResponseDto.getData()).build();
    }
}
