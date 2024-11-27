package com.project.mygg.service;

import com.project.mygg.DTO.boardDTO.BoardRequestDTO;
import com.project.mygg.DTO.boardDTO.BoardResponseDTO;
import com.project.mygg.entity.BoardEntity;
import com.project.mygg.entity.MemberEntity;
import com.project.mygg.repository.BoardRepository;
import com.project.mygg.repository.MemberRepository;
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
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    // 게시글 생성 기능
    @Transactional
    public void createBoard(BoardRequestDTO boardRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        MemberEntity memberEntity = memberRepository.findByUsername(username);

        BoardEntity boardEntity = new BoardEntity(boardRequestDTO,memberEntity);
        boardRepository.save(boardEntity);
    }

    // 모든 게시글 조회 기능
    public Page<BoardResponseDTO> findBoards(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC,"id"));
        return boardRepository.findAll(pageable).map(BoardResponseDTO::new);
    }

    // 개별 게시글 조회 기능
    public Optional<BoardResponseDTO> getBoard(Long id) {
        return boardRepository.findById(id).map(BoardResponseDTO::new);
    }

    // 게시글 삭제 기능
    @Transactional
    public void deleteBoard(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        MemberEntity memberEntity = memberRepository.findByUsername(username);
        BoardEntity boardEntity = boardRepository.findById(id)
                .orElseThrow();
        if (!boardEntity.getWriter().equals(memberEntity.getUsername())) {
            throw new SecurityException("작성자만 게시글을 삭제할 수 있습니다.");
        }
        boardRepository.deleteById(id);
    }

    // 게시글 수정 기능
    @Transactional
    public void updateBoard(Long id, BoardRequestDTO boardRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        MemberEntity memberEntity = memberRepository.findByUsername(username);
        BoardEntity boardEntity = boardRepository.findById(id).orElseThrow();
        if (!boardEntity.getWriter().equals(memberEntity.getUsername())) {
            throw new SecurityException("작성자만 게시글을 수정할 수 있습니다.");
        }
        boardEntity.updateBoard(boardRequestDTO);
        boardRepository.save(boardEntity);
    }

    public Page<BoardResponseDTO> searchBoard(String option,String keyword,int page){
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC,"id"));
        if("title".equals(option)){
        return boardRepository.findByTitleContaining(keyword,pageable).map(BoardResponseDTO::new);
        }else {

        return boardRepository.findByContentContaining(keyword,pageable).map(BoardResponseDTO::new);
        }
    }

}
