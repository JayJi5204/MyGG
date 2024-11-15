package com.project.mygg.entity;


import com.project.mygg.DTO.ChampionStatsRequestDTO;
import com.project.mygg.enums.ChampionName;
import com.project.mygg.enums.Line;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "championStats_entity")
public class ChampionStatsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "championStats_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ChampionName championName;

    @Enumerated(EnumType.STRING)
    private Line line;

    private int win;

    private int lose;

    private int kill;

    private int death;

    private int assist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private PlayerEntity playerEntity;

    public ChampionStatsEntity(ChampionStatsRequestDTO championStatsRequestDTO) {
        this.championName = championStatsRequestDTO.getChampionName();
        this.win = championStatsRequestDTO.getWin();
        this.lose = championStatsRequestDTO.getLose();
        this.kill = championStatsRequestDTO.getKill();
        this.death = championStatsRequestDTO.getDeath();
        this.assist = championStatsRequestDTO.getAssist();
        this.line = championStatsRequestDTO.getLine();
    }

    public int getKill(ChampionStatsEntity championStatsEntity) {
        return championStatsEntity.getKill();
    }

    public int getDeath(ChampionStatsEntity championStatsEntity) {
        return championStatsEntity.getDeath();
    }

    public int getAssist(ChampionStatsEntity championStatsEntity) {
        return championStatsEntity.getAssist();
    }
}
