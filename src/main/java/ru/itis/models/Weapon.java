package ru.itis.models;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Weapon {
    private int weaponId;
    private String name;
    private int qualityId;
    private int typeId;
    private int baseATK;
    private int upgradeMaterialId;
    private String secondStat;
    private String passiveAbility;
}
