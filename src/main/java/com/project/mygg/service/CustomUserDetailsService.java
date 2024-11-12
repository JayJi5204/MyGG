package com.project.mygg.service;

import com.project.mygg.DTO.CustomUserDetails;
import com.project.mygg.entity.MemberEntity;
import com.project.mygg.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberEntity member = memberRepository.findByUsername(username);

        if (member != null) {
            return new CustomUserDetails(member);
        }

        throw new UsernameNotFoundException("User not found with username: " + username); // 메시지 수정
    }

}
