package ru.itis.models;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Enemy {
    private int enemyId;
    private String name;
    private String enemyDescription;
}
