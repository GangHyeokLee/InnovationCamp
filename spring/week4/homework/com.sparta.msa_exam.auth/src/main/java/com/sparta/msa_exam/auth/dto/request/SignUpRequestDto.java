package com.sparta.msa_exam.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignUpRequestDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String role;
}
