package com.project.mygg.service;

import com.project.mygg.entity.ChampionStatsEntity;
import com.project.mygg.entity.MemberEntity;
import com.project.mygg.entity.PlayerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChampionStatsService {
    PlayerEntity playerEntity;
    ChampionStatsEntity championStatsEntity;

    // KDA 계산 메서드
    public int setKDA() {
        int kill = championStatsEntity.getKill();
        int death = championStatsEntity.getDeath();
        int assist = championStatsEntity.getAssist();
        return (kill + assist) / death;
    }

    // 승률 계산 메서드
    public int setWinRate() {
        int win = championStatsEntity.getWin();
        int lose = championStatsEntity.getLose();
        return win / (win + lose);
    }

    // 총 킬수 계산 메서드
    public int setTotalKill() {
        return playerEntity.getChampionStatsEntity().stream().
                mapToInt(championStatsEntity::getKill).
                sum();
    }

    // 총 데스수 계산 메서드
    public int setTotalDeath() {
        return playerEntity.getChampionStatsEntity().stream().
                mapToInt(championStatsEntity::getDeath).
                sum();
    }

    // 총 어시스트수 계산 메서드
    public int setTotalAssist() {
        return playerEntity.getChampionStatsEntity().stream().
                mapToInt(championStatsEntity::getAssist).
                sum();
    }
}
