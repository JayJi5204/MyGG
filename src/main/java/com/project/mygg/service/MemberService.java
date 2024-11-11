package com.project.mygg.service;

import com.project.mygg.DTO.MemberRequestDTO;
import com.project.mygg.entity.MemberEntity;
import com.project.mygg.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long signUp(MemberRequestDTO memberRequestDTO) {
        validateMember(memberRequestDTO);
        MemberEntity memberEntity = new MemberEntity(memberRequestDTO);
        memberRepository.save(memberEntity);
        return memberEntity.getId();
    }

    private void validateMember(MemberRequestDTO memberRequestDTO) {
        // 중복 회원 검증: 이름을 기준으로 중복 검사
        List<MemberEntity> findMembers = memberRepository.findByName(memberRequestDTO.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<MemberEntity> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<MemberEntity> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
