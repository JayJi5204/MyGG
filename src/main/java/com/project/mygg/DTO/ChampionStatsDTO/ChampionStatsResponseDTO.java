package com.project.mygg.DTO.ChampionStatsDTO;

import com.project.mygg.entity.ChampionStatsEntity;
import com.project.mygg.enums.ChampionName;
import com.project.mygg.enums.Line;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChampionStatsResponseDTO {

    private Long id;

    private ChampionName championName;

    private Line line;

    private int win;

    private int lose;

    private int kill;

    private int death;

    private int assist;

    public ChampionStatsResponseDTO(ChampionStatsEntity championStatsEntity) {
        this.id = championStatsEntity.getId();
        this.championName = championStatsEntity.getChampionName();
        this.line = championStatsEntity.getLine();
        this.win = championStatsEntity.getWin();
        this.lose = championStatsEntity.getLose();
        this.kill = championStatsEntity.getKill();
        this.death = championStatsEntity.getDeath();
        this.assist = championStatsEntity.getAssist();
    }
}
