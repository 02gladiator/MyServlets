package ru.itis.models;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private int userId;
    private String nickname;
    private String email;
    private String role;
}
