package com.project.mygg.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TotalKdaDTO {
    private Long totalWin;
    private Long totalLose;
    private Long totalKill;
    private Long totalDeath;
    private Long totalAssist;
}
