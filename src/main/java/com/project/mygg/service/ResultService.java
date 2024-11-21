package com.project.mygg.service;

import com.project.mygg.DTO.ResultDTO.ResultRequestDTO;
import com.project.mygg.DTO.ResultDTO.ResultResponseDTO;
import com.project.mygg.entity.ResultEntity;
import com.project.mygg.entity.PlayerEntity;
import com.project.mygg.repository.ResultRepository;
import com.project.mygg.repository.PlayerRepository;
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
public class ResultService {
    private final ResultRepository resultRepository;
    PlayerEntity playerEntity;
    ResultEntity resultEntity;

    @Transactional
    public void createResult(ResultRequestDTO resultRequestDTO, PlayerEntity player) {
        ResultEntity championStats = new ResultEntity(resultRequestDTO, player);
        resultRepository.save(championStats);
    }

    public Page<ResultResponseDTO> findResult(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC,"id"));
        return resultRepository.findAll(pageable).map(ResultResponseDTO::new);
    }


    public Optional<ResultResponseDTO> getResult(Long id) {
        return resultRepository.findById(id).map(ResultResponseDTO::new);
    }
}
