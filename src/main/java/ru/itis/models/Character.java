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
    private int weaponId;
    private int elementId;
    private int regionId;
    private int upgradeMaterialId;
    private int artefactId;
    private int baseHP;
    private int baseATK;
    private String characterName;
    private String characterDescription;
}
