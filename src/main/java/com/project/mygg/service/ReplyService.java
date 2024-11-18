package com.project.mygg.service;

import com.project.mygg.DTO.ReplyDTO.ReplyRequestDTO;
import com.project.mygg.DTO.ReplyDTO.ReplyResponseDTO;
import com.project.mygg.entity.BoardEntity;
import com.project.mygg.entity.MemberEntity;
import com.project.mygg.entity.ReplyEntity;
import com.project.mygg.repository.BoardRepository;
import com.project.mygg.repository.MemberRepository;
import com.project.mygg.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    // 댓글 생성 기능
    @Transactional
    public void createReply(Long id, ReplyRequestDTO replyRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        MemberEntity memberEntity = memberRepository.findByUsername(username);

        BoardEntity boardEntity = boardRepository.findById(id).orElseThrow();

        ReplyEntity replyEntity = new ReplyEntity(replyRequestDTO, boardEntity, memberEntity);
        replyRepository.save(replyEntity);
    }

    // 모든 댓글 조회 기능
    public Slice<ReplyResponseDTO> findReply(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return replyRepository.findAll(pageable).map(ReplyResponseDTO::new);
    }


    // 댓글 삭제 기능
    @Transactional
    public void deleteReply(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        MemberEntity memberEntity = memberRepository.findByUsername(username);
        ReplyEntity replyEntity = replyRepository.findById(id)
                .orElseThrow();
        if (!replyEntity.getReplyWriter().equals(memberEntity.getUsername())) {
            throw new SecurityException("작성자만 댓글을 삭제할 수 있습니다.");
        }
        replyRepository.deleteById(id);
    }

    // 댓글 수정 기능
    @Transactional
    public void updateReply(Long id, ReplyRequestDTO replyRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        MemberEntity memberEntity = memberRepository.findByUsername(username);
        ReplyEntity replyEntity = replyRepository.findById(id).orElseThrow();
        if (!replyEntity.getReplyWriter().equals(memberEntity.getUsername())) {
            throw new SecurityException("작성자만 댓글을 수정할 수 있습니다.");
        }
        replyEntity.updateReply(replyRequestDTO);
        replyRepository.save(replyEntity);
    }

}
