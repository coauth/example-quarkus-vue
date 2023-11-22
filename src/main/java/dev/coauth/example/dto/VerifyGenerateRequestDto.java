package dev.coauth.example.dto;

import lombok.Data;

@Data
public class VerifyGenerateRequestDto {
    private String userId;
    private String modules;
    private String reConfirmText;
    private String codeChallenge;
}
