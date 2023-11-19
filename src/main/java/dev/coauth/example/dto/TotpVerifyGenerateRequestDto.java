package dev.coauth.example.dto;

import lombok.Data;

@Data
public class TotpVerifyGenerateRequestDto {
    private String userId;
    private String modules;
    private String codeChallenge;
}
