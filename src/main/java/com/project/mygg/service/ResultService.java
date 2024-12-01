package com.project.mygg.service;

import com.project.mygg.DTO.resultDTO.ResultRequestDTO;
import com.project.mygg.DTO.resultDTO.ResultResponseDTO;
import com.project.mygg.entity.MemberEntity;
import com.project.mygg.entity.ResultEntity;
import com.project.mygg.repository.MemberRepository;
import com.project.mygg.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ResultService {

    private final ResultRepository resultRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void createResult(ResultRequestDTO resultRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        MemberEntity memberEntity = memberRepository.findByUsername(username);
        ResultEntity result = new ResultEntity(
                memberEntity,
                resultRequestDTO.getResultDate(),
                resultRequestDTO.getContent(),
                resultRequestDTO.getPog(),
                memberEntity.getUsername()
        );

        resultRepository.save(result);
    }

    public Page<ResultResponseDTO> findResult(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "id"));
        return resultRepository.findAll(pageable).map(ResultResponseDTO::new);
    }

    // 개별 게시글 조회 기능
    public Optional<ResultResponseDTO> getResult(Long id) {
        return resultRepository.findById(id).map(ResultResponseDTO::new);
    }


    public Page<ResultResponseDTO> searchResult(String option, String keyword, int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "id"));
        if ("resultDate".equals(option)) {
            return resultRepository.findByResultDateContaining(keyword, pageable).map(ResultResponseDTO::new);
        } else {

            return resultRepository.findByPogContaining(keyword, pageable).map(ResultResponseDTO::new);
        }
    }

    @Transactional
    public void deleteResult(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        MemberEntity memberEntity = memberRepository.findByUsername(username);
        ResultEntity resultEntity = resultRepository.findById(id)
                .orElseThrow();
        if (!resultEntity.getWriter().equals(memberEntity.getUsername())) {
            throw new SecurityException("작성자만 게시글을 삭제할 수 있습니다.");
        }
        resultRepository.deleteById(id);
    }

    @Transactional
    public void updateResult(Long id, ResultRequestDTO resultRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        MemberEntity memberEntity = memberRepository.findByUsername(username);
        ResultEntity resultEntity = resultRepository.findById(id).orElseThrow();
        if (!resultEntity.getWriter().equals(memberEntity.getUsername())) {
            throw new SecurityException("작성자만 게시글을 수정할 수 있습니다.");
        }
        resultEntity.updateResult(
                resultRequestDTO.getResultDate(),
                resultRequestDTO.getContent(),
                resultRequestDTO.getPog(),
                memberEntity.getUsername()
        );
        resultRepository.save(resultEntity);
    }
}
