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

    @Transactional
    public void singUp(MemberRequestDTO memberRequestDTO) {
        boolean haveMember = memberRepository.existsByUsername(memberRequestDTO.getUsername());

        if (haveMember) {
            throw new IllegalArgumentException("error =" + memberRequestDTO.getUsername() + "is Member");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(memberRequestDTO.getPassword());
        MemberEntity member = new MemberEntity(memberRequestDTO, encodedPassword);
        memberRepository.save(member);
    }

    public List<MemberResponseDTO> findMembers() {
        List<MemberEntity> members = memberRepository.findAll();
        return members.stream()
                .map(MemberResponseDTO::new)
                .collect(Collectors.toList());
    }


    public Optional<MemberResponseDTO> findOne(Long memberId) {
        return memberRepository.findById(memberId).map(MemberResponseDTO::new);
    }
}
