package com.project.mygg.service;

import com.project.mygg.DTO.PlayerDTO.PlayerRequestDTO;
import com.project.mygg.DTO.PlayerResponseDTO;
import com.project.mygg.entity.PlayerEntity;
import com.project.mygg.enums.Tier;
import com.project.mygg.repository.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Transactional
    public void addPlayer(PlayerRequestDTO playerRequestDTO) {
        StringBuilder errorMessage = new StringBuilder();

        if (playerRepository.existsByNickname(playerRequestDTO.getNickname())) {
            errorMessage.append(playerRequestDTO.getNickname()).append("는 이미 존재하는 닉네임입니다.");
        }
        else if (!playerRequestDTO.getTier().equals(Tier.UNRANKED)) {
            if (playerRepository.existsByTier(playerRequestDTO.getTier())) {
                errorMessage.append(playerRequestDTO.getTier()).append(" 티어는 이미 존재하는 티어입니다.");
            }
        }
        if (!errorMessage.isEmpty()) {
            throw new IllegalArgumentException(errorMessage.toString());
        }

        PlayerEntity player = new PlayerEntity(playerRequestDTO);
        playerRepository.save(player);
    }


    public Page<PlayerResponseDTO> findPlayers(int page) {
        Pageable pageable = PageRequest.of(page, 20, Sort.by(Sort.Direction.DESC, "nickname"));
        return playerRepository.findAll(pageable).map(PlayerResponseDTO::new);
    }

    public Optional<PlayerResponseDTO> findOne(Long playerInd) {
        return playerRepository.findById(playerInd).map(PlayerResponseDTO::new);
    }

    @Transactional
    public void deletePlayer(Long id){
        playerRepository.deleteById(id);
    }

    public PlayerResponseDTO findPlayer(String nickname) {
        PlayerEntity player = playerRepository.findByNickname(nickname);

        if (player == null) {
            throw new EntityNotFoundException("닉네임이 존재하지 않습니다.");
        }

        return new PlayerResponseDTO(player);
    }

    @Transactional
    public void updatePlayer(Long id, PlayerRequestDTO playerRequestDTO) {
        StringBuilder errorMessage = new StringBuilder();

        if (playerRepository.existsByNickname(playerRequestDTO.getNickname())) {
            errorMessage.append(playerRequestDTO.getNickname()).append("는 이미 존재하는 닉네임입니다.");
        }

        if (!errorMessage.isEmpty()) {
            throw new IllegalArgumentException(errorMessage.toString());
        }
        PlayerEntity playerEntity=playerRepository.findById(id).orElseThrow();
        playerEntity.update(playerRequestDTO);
        playerRepository.save(playerEntity);
    }
}
