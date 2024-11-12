package com.project.mygg.service;

import com.project.mygg.DTO.MemberRequestDTO;
import com.project.mygg.DTO.MemberResponseDTO;
import com.project.mygg.entity.MemberEntity;
import com.project.mygg.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Transactional
//    public void signUp2(MemberRequestDTO memberRequestDTO) {
//        validateMember(memberRequestDTO);
//        MemberEntity memberEntity = new MemberEntity(memberRequestDTO);
//        memberRepository.save(memberEntity);
//    }

    @Transactional
    public void singUp(MemberRequestDTO memberRequestDTO) {
        boolean haveMember=memberRepository.existsByNickName(memberRequestDTO.getNickName());

        if(haveMember){
            throw new IllegalArgumentException("error =" + memberRequestDTO.getNickName() + "is Member") ;
        }

        String encodedPassword = bCryptPasswordEncoder.encode(memberRequestDTO.getPassword());
        MemberEntity member = new MemberEntity(memberRequestDTO,encodedPassword);
        memberRepository.save(member);
    }

//    private void validateMember(MemberRequestDTO memberRequestDTO) {
//        // 중복 회원 검증: 이름을 기준으로 중복 검사
//        List<MemberEntity> findMembers = memberRepository.findByName(memberRequestDTO.getName());
//        if (!findMembers.isEmpty()) {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }
//    }

    //회원 전체 조회
//    public List<MemberEntity> findMembers() {
//        return memberRepository.findAll();
//    }

    public List<MemberResponseDTO> findMembers() {
        List<MemberEntity> members = memberRepository.findAll();
        return members.stream()
                .map(MemberResponseDTO::new)
                .collect(Collectors.toList());
    }


//    public Optional<MemberEntity> findOne(Long memberId) {
//        return memberRepository.findById(memberId);
//    }

    public Optional<MemberResponseDTO> findOne(Long memberId) {
        return memberRepository.findById(memberId).map(MemberResponseDTO::new);
    }
}
