package com.project.mygg.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "match_entity")
@NoArgsConstructor
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long id;

    private String matchDate; // 몇월 몇일에 경기했는지(그래서 일부러 LocalDateTime으로 설정안함)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stats_id")
    private StatsEntity statsEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private PlayerEntity playerEntity;


    @Builder
    public MatchEntity(String matchDate, StatsEntity statsEntity, PlayerEntity playerEntity) {
        this.matchDate = matchDate;
        this.statsEntity = statsEntity;
        this.playerEntity = playerEntity;
    }

    public MatchEntity(String matchDate) {
        this.matchDate = matchDate;
    }
}
