package com.sparta.msa_exam.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignInRequestDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
