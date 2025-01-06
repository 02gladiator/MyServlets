package ru.itis.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpForm {
    private String nickname;
    private String email;
    private String password;
}
