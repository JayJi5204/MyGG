package com.project.mygg.DTO.matchDTO;

import com.project.mygg.entity.MatchEntity;
import com.project.mygg.enums.ChampionName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MatchResponseDTO {

    private Long id;

    private String matchDate;
    private String nickname;
    private ChampionName championName;
    private Long win;
    private Long lose;
    private Long kill;
    private Long death;
    private Long assist;


    public MatchResponseDTO(MatchEntity matchEntity) {
        this.id = matchEntity.getId();
        this.matchDate = matchEntity.getMatchDate();
        this.nickname=matchEntity.getPlayerEntity().getNickname();
        this.championName=matchEntity.getStatsEntity().getChampionName();
        this.win=matchEntity.getStatsEntity().getWin();
        this.lose=matchEntity.getStatsEntity().getLose();
        this.kill=matchEntity.getStatsEntity().getKill();;
        this.death=matchEntity.getStatsEntity().getDeath();
        this.assist=matchEntity.getStatsEntity().getAssist();
    }
}
