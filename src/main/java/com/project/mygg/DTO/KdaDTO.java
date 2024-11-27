package com.project.mygg.DTO;

import com.project.mygg.enums.ChampionName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KdaDTO {
    private ChampionName championName;
    private Long totalKill;
    private Long totalDeath;
    private Long totalAssist;
}
