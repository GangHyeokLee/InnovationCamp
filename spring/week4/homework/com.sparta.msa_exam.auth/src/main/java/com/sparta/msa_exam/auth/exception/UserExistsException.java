package com.sparta.msa_exam.auth.exception;

public class UserExistsException extends RuntimeException {
    public UserExistsException(String message) {
        super(message);
    }

    public UserExistsException() {
        super("User already exists");
    }

}
