package dev.coauth.example.dto;

import lombok.Data;

@Data
public class VerifyRequestDto {
    private String code;
    private String codeVerifier;
}
