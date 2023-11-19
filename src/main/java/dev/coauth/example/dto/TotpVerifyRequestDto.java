package dev.coauth.example.dto;

import lombok.Data;

@Data
public class TotpVerifyRequestDto {
    private String code;
    private String codeVerifier;
}
