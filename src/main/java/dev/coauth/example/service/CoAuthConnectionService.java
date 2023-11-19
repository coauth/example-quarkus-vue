package dev.coauth.example.service;

import dev.coauth.example.dto.TotpRegisterRequestDto;
import dev.coauth.example.dto.TotpRegisterResponseDto;
import dev.coauth.example.dto.TotpVerifyGenerateRequestDto;
import dev.coauth.example.dto.TotpVerifyRequestDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "coauth-api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/api/coauth")
public interface CoAuthConnectionService {

    @POST
    @Path("/register/generate")
    TotpRegisterResponseDto registerTotpGenerate(@HeaderParam("COAUTH-API-KEY") String coAuthApiKey, TotpRegisterRequestDto totpRegisterRequestDto);

    @POST
    @Path("/register/status")
    TotpRegisterResponseDto registerTotpStatus(@HeaderParam("COAUTH-API-KEY") String coAuthApiKey, TotpVerifyRequestDto totpVerifyRequestDto);

    @POST
    @Path("/verify/generate")
    TotpRegisterResponseDto verifyTotpGenerate(@HeaderParam("COAUTH-API-KEY") String coAuthApiKey, TotpVerifyGenerateRequestDto totpVerifyGenerateRequestDto);

    @POST
    @Path("/verify/status")
    TotpRegisterResponseDto verifyTotpStatus(@HeaderParam("COAUTH-API-KEY") String coAuthApiKey, TotpVerifyRequestDto totpVerifyRequestDto);

}
