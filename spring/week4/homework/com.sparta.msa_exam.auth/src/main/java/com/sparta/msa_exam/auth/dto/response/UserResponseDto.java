package com.sparta.msa_exam.auth.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {
    private String username;

    public UserResponseDto(String username) {
        this.username = username;
    }
}
