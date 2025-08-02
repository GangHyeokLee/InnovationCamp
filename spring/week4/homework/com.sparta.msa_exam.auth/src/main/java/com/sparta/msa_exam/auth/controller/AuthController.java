package com.sparta.msa_exam.auth.controller;

import com.sparta.msa_exam.auth.dto.response.AuthResponseDto;
import com.sparta.msa_exam.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/auth/sign-in")
    public ResponseEntity<AuthResponseDto> signIn(@RequestParam("username") String username) {
        return ResponseEntity.ok(authService.createAccessToken(username));
    }
}
