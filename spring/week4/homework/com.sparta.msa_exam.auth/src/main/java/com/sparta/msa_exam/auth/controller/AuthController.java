package com.sparta.msa_exam.auth.controller;

import com.sparta.msa_exam.auth.dto.request.SignUpRequestDto;
import com.sparta.msa_exam.auth.dto.response.AuthResponseDto;
import com.sparta.msa_exam.auth.dto.response.UserResponseDto;
import com.sparta.msa_exam.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/sign-in")
    public ResponseEntity<AuthResponseDto> signIn(@RequestParam("username") String username) {
        return ResponseEntity.ok(authService.createAccessToken(username));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        return ResponseEntity.ok(authService.createUser(signUpRequestDto));
    }

    @GetMapping("/validate/{username}")
    public ResponseEntity<Boolean> validateUsername(@PathVariable String username) {
        return ResponseEntity.ok(authService.validateUser(username));
    }
}
