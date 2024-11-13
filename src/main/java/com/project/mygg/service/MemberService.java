package com.project.mygg.service;

import com.project.mygg.DTO.MemberRequestDTO;
import com.project.mygg.DTO.MemberResponseDTO;
import com.project.mygg.entity.MemberEntity;
import com.project.mygg.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional()
    public void singUp(MemberRequestDTO memberRequestDTO) {
        boolean haveMember = memberRepository.existsByUsername(memberRequestDTO.getUsername());

        if (haveMember) {
            throw new IllegalArgumentException(memberRequestDTO.getUsername() + "는 이미 존재하는 아이디입니다.");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(memberRequestDTO.getPassword());
        MemberEntity member = new MemberEntity(memberRequestDTO, encodedPassword);
        memberRepository.save(member);
    }

    public Page<MemberResponseDTO> findMembers(int page) {
        Pageable pageable = PageRequest.of(page, 20, Sort.by(Sort.Direction.DESC, "username"));
        return memberRepository.findAll(pageable).map(MemberResponseDTO::new);
    }

    public Optional<MemberResponseDTO> findOne(Long memberId) {
        return memberRepository.findById(memberId).map(MemberResponseDTO::new);
    }

    public void deleteMember(Long id){
        memberRepository.deleteById(id);
    }

    public MemberResponseDTO findMember(String username) {
        MemberEntity member = memberRepository.findByUsername(username);

        if (member == null) {
            throw new EntityNotFoundException("회원이 존재하지 않습니다.");
        }

        return new MemberResponseDTO(member);
    }

}
