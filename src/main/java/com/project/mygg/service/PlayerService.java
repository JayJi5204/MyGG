package com.project.mygg.service;

import com.project.mygg.DTO.PlayerRequestDTO;
import com.project.mygg.DTO.PlayerResponseDTO;
import com.project.mygg.entity.PlayerEntity;
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

        if (playerRepository.existsByNickName(playerRequestDTO.getNickName())) {
            errorMessage.append(playerRequestDTO.getNickName()).append("는 이미 존재하는 닉네임입니다.\n");
        }
        if (!errorMessage.isEmpty()) {
            throw new IllegalArgumentException(errorMessage.toString());
        }

        PlayerEntity player = new PlayerEntity(playerRequestDTO);
        playerRepository.save(player);
    }


    public Page<PlayerResponseDTO> findPlayers(int page) {
        Pageable pageable = PageRequest.of(page, 20, Sort.by(Sort.Direction.DESC, "nickName"));
        return playerRepository.findAll(pageable).map(PlayerResponseDTO::new);
    }

    public Optional<PlayerResponseDTO> findOne(Long playerInd) {
        return playerRepository.findById(playerInd).map(PlayerResponseDTO::new);
    }

    @Transactional
    public void deletePlayer(Long id){
        playerRepository.deleteById(id);
    }

    public PlayerResponseDTO findPlayer(String nickName) {
        PlayerEntity player = playerRepository.findByNickName(nickName);

        if (player == null) {
            throw new EntityNotFoundException("닉네임이 존재하지 않습니다.");
        }

        return new PlayerResponseDTO(player);
    }

}
