package ru.itis.models;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpgradeMaterial {
    private int upgradeMaterialId;
    private int enemyId;
    private String upgradeMaterialName;
    private String upgradeMaterialDescription;
}
