package dev.coauth.example.dto;

import lombok.Data;

@Data
public class ExampleRegisterRequestDto {
    private String userId;
    private String password;
    private String rePassword;

}
