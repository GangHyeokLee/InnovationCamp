package com.example.springvalidation.beanvalidation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class TestDto {

    @NotBlank
    private String stringField;

//    @NotNull(message = "메세지 수정 가능")
    @NotNull
    @Range(min = 1, max = 9999)
    private Integer integerField;

    @NotBlank
    @Pattern(
            regexp = "^[^@.]+@[A-Za-z0-9]+\\.[A-Za-z]$",
            message = "이메일 형식이 올바르지 않습니다."
    )
    private String email;

}
