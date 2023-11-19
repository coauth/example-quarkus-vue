package dev.coauth.example.dto;

import lombok.Data;

@Data
public class TotpRegisterRequestDto {
    private String userId;
    private String module;
    private String codeChallenge;
}
