package ru.itis.models;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Team {
    private int teamId;
    private int userId;
    private int firstPositionId;
    private int secondPositionId;
    private int thirdPositionId;
    private int fourthPositionId;
}
