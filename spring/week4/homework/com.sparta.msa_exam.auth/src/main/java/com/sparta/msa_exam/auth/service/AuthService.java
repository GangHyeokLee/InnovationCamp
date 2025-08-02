package com.sparta.msa_exam.auth.service;

import com.sparta.msa_exam.auth.dto.request.SignUpRequestDto;
import com.sparta.msa_exam.auth.dto.response.AuthResponseDto;
import com.sparta.msa_exam.auth.dto.response.UserResponseDto;
import com.sparta.msa_exam.auth.entity.User;
import com.sparta.msa_exam.auth.entity.UserRole;
import com.sparta.msa_exam.auth.exception.UserExistsException;
import com.sparta.msa_exam.auth.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class AuthService {

    private final UserRepository userRepository;
    @Value("${spring.application.name}")
    private String issuer;

    @Value("${service.jwt.access-expiration}")
    private Long accessExpiration;

    private final SecretKey secretKey;

    public AuthService(@Value("${service.jwt.secret-key}") String secretKey, UserRepository userRepository) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
        this.userRepository = userRepository;
    }

    public AuthResponseDto createAccessToken(String username) {

        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        return new AuthResponseDto(Jwts.builder()
                .claim("username", user.getUsername())
                .claim("role", user.getRole())
                .issuer(issuer)
                .issuedAt(new Date((System.currentTimeMillis())))
                .expiration(new Date(System.currentTimeMillis() + accessExpiration))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact());
    }

    public UserResponseDto createUser(SignUpRequestDto signUpRequestDto) {

        // username이 이미 존재하면 예외 발생
        if (validateUser(signUpRequestDto.getUsername())) {
            throw new UserExistsException();
        }

        return new UserResponseDto(
                userRepository.save(
                        new User(
                                signUpRequestDto.getUsername(),
                                signUpRequestDto.getPassword(),
                                UserRole.fromString(signUpRequestDto.getRole()
                                )
                        )
                )
        );
    }

    public Boolean validateUser(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
