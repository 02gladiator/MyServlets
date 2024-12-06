package ru.itis.models;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Artifacts {
    private int artifactId;
    private String artifactName;
    private String firstSetBonus;
    private String secondSetBonus;
}
