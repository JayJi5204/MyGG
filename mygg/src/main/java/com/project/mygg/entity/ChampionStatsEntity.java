package com.project.mygg.entity;

import com.project.mygg.enums.Line;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ChampionStatsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "championStats_id")
    private Long id;

    private String championName;

    private int win;

    private int lose;

    private int kill;

    private int death;

    private int assist;

    @Enumerated(EnumType.STRING)
    private Line line;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

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
