package com.project.mygg.DTO;

import com.project.mygg.entity.MemberEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final MemberEntity memberEntity;

    public CustomUserDetails(MemberEntity memberEntity) {
        this.memberEntity = memberEntity;
    }

    // 사용자의 특정한 권한(Role 값)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {

                return "ROLE_"+ memberEntity.getRole().toString();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return memberEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return memberEntity.getUsername();
    }

    // 사용자의 계정이 만료되었는지
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 사용자의 계정이 잠겨있는지
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    // 사용자의 자격이 만료되었는지
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 사용자의 계정이 사용가능한지
    @Override
    public boolean isEnabled() {
        return true;
    }
}
