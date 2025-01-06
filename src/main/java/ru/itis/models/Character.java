package ru.itis.models;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Character {
    private int characterId;
    private String weapon;
    private String element;
    private String region;
    private String characterName;
    private String characterDescription;
}
