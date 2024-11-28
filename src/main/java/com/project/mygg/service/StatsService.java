package com.project.mygg.service;

import com.project.mygg.DTO.KdaDTO;
import com.project.mygg.DTO.StatDTO;
import com.project.mygg.DTO.statsDTO.StatsRequestDTO;
import com.project.mygg.DTO.statsDTO.StatsResponseDTO;
import com.project.mygg.entity.PlayerEntity;
import com.project.mygg.entity.StatsEntity;
import com.project.mygg.enums.ChampionName;
import com.project.mygg.repository.PlayerRepository;
import com.project.mygg.repository.StatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StatsService {

    private final StatsRepository statsRepository;
    private final PlayerRepository playerRepository;


    @Transactional
    public void createStats(Long playerId,StatsRequestDTO statsRequestDTO) {
        PlayerEntity playerEntity=playerRepository.findById(playerId).orElseThrow();
        // 생성자를 사용하여 GameResultEntity 객체 생성
        StatsEntity statsEntity = new StatsEntity(
                playerEntity,
                statsRequestDTO.getChampionName(),
                statsRequestDTO.getLine(),
                statsRequestDTO.getKill(),
                statsRequestDTO.getDeath(),
                statsRequestDTO.getAssist()

        );

        statsRepository.save(statsEntity);

    }

    @Transactional
    public Page<StatsResponseDTO> searchPlayer(String nickname, int page) {
        Pageable pageable = PageRequest.of(page, 100, Sort.by(Sort.Direction.ASC, "id"));
        return statsRepository.findByNicknameContaining(nickname, pageable).map(StatsResponseDTO::new);
    }


    public List<KdaDTO> getTotalKda(Long playerId) {
        return statsRepository.findTotalKdaByPlayer(playerId);
    }

    public List<StatDTO> TotalKda(Long playerId) {
        return statsRepository.findStatDTOByPlayerId(playerId);
    }


    @Transactional
    public void addMatch(Long playerId, ChampionName championName, Long kill, Long death, Long assist) {
        PlayerEntity playerEntity = playerRepository.findById(playerId).orElseThrow();
        StatsEntity statsEntity = new StatsEntity(playerEntity, championName, kill, death, assist);
        statsRepository.save(statsEntity);
    }
}
