package com.project.mygg.entity;

import com.project.mygg.enums.Line;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "championStats_entity")
public class ChampionStatsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "championStats_id")
    private Long id;

    private String championName;

    private final int win;

    private final int lose;

    private final int kill;

    private final int death;

    private final int assist;

    @Enumerated(EnumType.STRING)
    private Line line;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private PlayerEntity playerEntity;

    public ChampionStatsEntity() {
        this.win = 0;
        this.lose = 0;
        this.kill = 0;
        this.death = 0;
        this.assist = 0;
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
