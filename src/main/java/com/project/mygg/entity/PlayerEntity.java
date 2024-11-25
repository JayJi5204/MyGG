package com.project.mygg.entity;

import com.project.mygg.DTO.playerDTO.PlayerRequestDTO;
import com.project.mygg.enums.Tier;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "player_entity")
@NoArgsConstructor
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long id;

    @Column(unique = true)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Tier tier;

    private Long penalty;

    @OneToMany(mappedBy = "playerEntity", cascade = CascadeType.ALL)
    private List<MatchEntity> matchEntity = new ArrayList<>();

    @Builder
    public PlayerEntity(String nickname, Tier tier, Long penalty) {
        this.nickname = nickname;
        this.tier = tier;
        this.penalty = penalty;
    }


    public void update(PlayerRequestDTO playerRequestDTO) {
        this.nickname = playerRequestDTO.getNickname();
        this.tier = playerRequestDTO.getTier();
    }

    // enum값 정수로 변환
    public int getTierValue() {
        return this.tier.getValue();
    }
}
