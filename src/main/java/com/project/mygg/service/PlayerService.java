package com.project.mygg.service;


import com.project.mygg.DTO.playerDTO.PlayerRequestDTO;
import com.project.mygg.DTO.playerDTO.PlayerResponseDTO;
import com.project.mygg.entity.PlayerEntity;
import com.project.mygg.enums.Tier;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlayerService {

    private final PlayerRepository playerRepository;

    private final StatsRepository statsRepository;

    // 선수 추가
    @Transactional
    public void addPlayer(PlayerRequestDTO playerRequestDTO) {
        StringBuilder errorMessage = new StringBuilder();

        if (playerRepository.existsByNickname(playerRequestDTO.getNickname())) {
            errorMessage.append(playerRequestDTO.getNickname()).append("는 이미 존재하는 닉네임입니다.");
        } else if (!playerRequestDTO.getTier().equals(Tier.UNRANKED)) {
            if (playerRepository.existsByTier(playerRequestDTO.getTier())) {
                errorMessage.append(playerRequestDTO.getTier()).append(" 티어는 이미 존재하는 티어입니다.");
            }
        }
        if (!errorMessage.isEmpty()) {
            throw new IllegalArgumentException(errorMessage.toString());
        }

        PlayerEntity player = PlayerEntity.builder()
                .nickname(playerRequestDTO.getNickname())
                .tier(playerRequestDTO.getTier())
                .penalty(playerRequestDTO.getPenalty())
                .build();
        playerRepository.save(player);
    }

    // 선수 전체 조회
    public Page<PlayerResponseDTO> findPlayers(int page) {
        Pageable pageable = PageRequest.of(page, 20, Sort.by(Sort.Direction.DESC, "nickname"));
        return playerRepository.findAll(pageable).map(PlayerResponseDTO::new);
    }

    // 선수 개별 조회
    public Optional<PlayerResponseDTO> findPlayer(Long playerInd) {
        return playerRepository.findById(playerInd).map(PlayerResponseDTO::new);
    }

    public List<PlayerResponseDTO> findPlayer() {
        return playerRepository.findAll().stream().map(PlayerResponseDTO::new).collect(Collectors.toList());
    }

    // 선수 삭제
    @Transactional
    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }


    // 선수 수정
    @Transactional
    public void updatePlayer(Long id, PlayerRequestDTO playerRequestDTO) {
        PlayerEntity playerEntity = playerRepository.findById(id).orElseThrow();
        playerEntity.update(playerRequestDTO);
        playerRepository.save(playerEntity);
    }

    // 티어리스트
    public List<PlayerResponseDTO> tierList() {
        List<PlayerEntity> player = playerRepository.findAllByOrderByTier();
        return player.stream()
                .map(PlayerResponseDTO::new)
                .collect(Collectors.toList());
    }


}
