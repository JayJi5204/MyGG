package com.project.mygg.service;

import com.project.mygg.DTO.StatsDTO.StatsRequestDTO;
import com.project.mygg.DTO.matchDTO.MatchRequestDTO;
import com.project.mygg.DTO.playerDTO.PlayerRequestDTO;
import com.project.mygg.entity.MatchEntity;
import com.project.mygg.entity.PlayerEntity;
import com.project.mygg.entity.StatsEntity;
import com.project.mygg.repository.MatchRepository;
import com.project.mygg.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchService {

    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;

    @Transactional
    public void addMatch(PlayerRequestDTO playerRequestDTO, StatsRequestDTO statsRequestDTO, MatchRequestDTO matchRequestDTO) {

        PlayerEntity player = playerRepository.findByNickname(playerRequestDTO.getNickname())
                .orElseThrow(() -> new IllegalArgumentException("플레이어를 찾을 수 없습니다."));

        StatsEntity stats = StatsEntity.builder()
                .championName(statsRequestDTO.getChampionName())
                .win(statsRequestDTO.getWin())
                .lose(statsRequestDTO.getLose())
                .kill(statsRequestDTO.getKill())
                .death(statsRequestDTO.getDeath())
                .assist(statsRequestDTO.getAssist())
                .build();

        MatchEntity match= MatchEntity.builder()
                .matchDate(matchRequestDTO.getMatchDate())
                .playerEntity(player)
                .statsEntity(stats)
                .build();

        matchRepository.save(match);

    }

}
