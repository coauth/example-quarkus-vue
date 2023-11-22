package dev.coauth.example.dto;

import lombok.Data;

@Data
public class ExampleReConfirmRequestDto {
    private String userId;
    private String reConfirmText;
}
