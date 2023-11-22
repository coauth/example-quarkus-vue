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
public class DeletePayeeEndpoint {

    @ConfigProperty(name = "coauth.api.key")
    String coAuthApiKey;

    @RestClient
    CoAuthConnectionService coAuthConnectionService;


    @POST
    @Path("/delete-payee-start")
    public GenericResponseDto verifyGenerate(ExampleReConfirmRequestDto exampleReConfirmRequestDto) {

        VerifyGenerateRequestDto verifyGenerateRequestDto = new VerifyGenerateRequestDto();
        verifyGenerateRequestDto.setUserId(exampleReConfirmRequestDto.getUserId());
        verifyGenerateRequestDto.setModules("RECONFIRM");
        verifyGenerateRequestDto.setReConfirmText(exampleReConfirmRequestDto.getReConfirmText());

        String codeVerifier = RandomCodeVerifierGenerator.generateRandomAlphanumeric();
        String codeChallenge = SHAUtil.generateSHA256(codeVerifier);
        verifyGenerateRequestDto.setCodeChallenge(codeChallenge);
        TotpRegisterResponseDto totpRegisterResponseDto = coAuthConnectionService.verifyTotpGenerate(coAuthApiKey, verifyGenerateRequestDto);

        UserDbModel userDbModel = UserDatabase.dbBeanHashMap.get(verifyGenerateRequestDto.getUserId());
        userDbModel.setCurrentCode(totpRegisterResponseDto.getData());
        userDbModel.setCodeVerifier(codeVerifier);
        UserDatabase.dbBeanHashMap.put(verifyGenerateRequestDto.getUserId(), userDbModel);

        ExampleRegisterResponseDto exampleRegisterResponseDto = new ExampleRegisterResponseDto();
        exampleRegisterResponseDto.setUserId(verifyGenerateRequestDto.getUserId());
        exampleRegisterResponseDto.setCode(totpRegisterResponseDto.getData());

        return GenericResponseDto.builder().data(exampleRegisterResponseDto).build();
    }

    @POST
    @Path("/delete-payee-verify")
    public GenericResponseDto verifyStatus(ExampleReConfirmRequestDto exampleReConfirmRequestDto) {
        UserDbModel userDbModel = UserDatabase.dbBeanHashMap.get(exampleReConfirmRequestDto.getUserId());
        VerifyRequestDto verifyRequestDto = new VerifyRequestDto();
        verifyRequestDto.setCodeVerifier(userDbModel.getCodeVerifier());
        verifyRequestDto.setCode(userDbModel.getCurrentCode());
        TotpRegisterResponseDto totpRegisterResponseDto = coAuthConnectionService.verifyTotpStatus(coAuthApiKey, verifyRequestDto);
        userDbModel.setCurrentCode("");
        userDbModel.setCodeVerifier("");
        UserDatabase.dbBeanHashMap.put(exampleReConfirmRequestDto.getUserId(), userDbModel);
        return GenericResponseDto.builder().data(totpRegisterResponseDto.getData()).build();
    }
}
