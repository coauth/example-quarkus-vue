package dev.coauth.example.util;

import lombok.Data;

@Data
public class UserDbModel {
    private String userId;
    private String password;
    private String status;
    private String codeVerifier;
    private String currentCode;
}
