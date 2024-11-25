package com.project.mygg.service;

import com.project.mygg.DTO.memberDTO.MemberRequestDTO;
import com.project.mygg.DTO.memberDTO.MemberResponseDTO;
import com.project.mygg.entity.MemberEntity;
import com.project.mygg.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원 가입
    @Transactional
    public void signUp(MemberRequestDTO memberRequestDTO) {
        StringBuilder errorMessage = new StringBuilder();

        if (memberRepository.existsByUsername(memberRequestDTO.getUsername())) {
            errorMessage.append(memberRequestDTO.getUsername()).append("는 이미 존재하는 아이디입니다.");
        } else if (memberRepository.existsByPhoneNumber(memberRequestDTO.getPhoneNumber())) {
            errorMessage.append(memberRequestDTO.getPhoneNumber()).append("는 이미 존재하는 휴대폰 번호입니다.");
        }

        if (!memberRequestDTO.getPassword().equals(memberRequestDTO.getCheckPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        if (!errorMessage.isEmpty()) {
            throw new IllegalArgumentException(errorMessage.toString());
        }

        String encodedPassword = bCryptPasswordEncoder.encode(memberRequestDTO.getPassword());
        MemberEntity member = new MemberEntity(memberRequestDTO, encodedPassword);
        memberRepository.save(member);
    }

    // 회원 목록
    public Page<MemberResponseDTO> findMembers(int page) {
        Pageable pageable = PageRequest.of(page, 20, Sort.by(Sort.Direction.DESC, "username"));
        return memberRepository.findAll(pageable).map(MemberResponseDTO::new);
    }

    // 회원 개별 목록
    public Optional<MemberResponseDTO> findOne(Long memberId) {
        return memberRepository.findById(memberId).map(MemberResponseDTO::new);
    }

    // 회원 수정
    @Transactional
    public void updateMember(Long id, MemberRequestDTO memberRequestDTO) {
        MemberEntity memberEntity = memberRepository.findById(id).orElseThrow();
        memberEntity.update(memberRequestDTO);
        memberRepository.save(memberEntity);
    }

    // 회원 삭제
    @Transactional
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    // 회원 개인 수정
    public MemberResponseDTO findMember(String username) {
        MemberEntity member = memberRepository.findByUsername(username);

        if (member == null) {
            throw new EntityNotFoundException("회원이 존재하지 않습니다.");
        }

        return new MemberResponseDTO(member);
    }


}
