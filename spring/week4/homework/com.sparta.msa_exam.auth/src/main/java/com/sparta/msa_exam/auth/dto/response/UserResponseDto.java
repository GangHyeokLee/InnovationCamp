package com.sparta.msa_exam.auth.dto.response;

import com.sparta.msa_exam.auth.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {
    private String username;

    public UserResponseDto(User user) {
        this.username = user.getUsername();
    }
}
