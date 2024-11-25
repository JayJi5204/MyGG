package com.project.mygg.entity;

import com.project.mygg.DTO.memberDTO.MemberRequestDTO;
import com.project.mygg.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "member_entity")
@NoArgsConstructor
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String username; // 멤버의 아이디를 의미함

    private String password;

    private String name; // 실제 이름을 의미함

    @Column(unique = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL)
    private List<BoardEntity> boardEntity = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL)
    private List<ReplyEntity> replyEntity = new ArrayList<>();


    public MemberEntity(MemberRequestDTO memberRequestDTO, String encodedPassword) {
        this.username = memberRequestDTO.getUsername();
        this.password = encodedPassword;
        this.name = memberRequestDTO.getName();
        this.phoneNumber = memberRequestDTO.getPhoneNumber();
        this.role = memberRequestDTO.getRole();
    }

    public void updatePhoneNumber(MemberRequestDTO memberRequestDTO) {
        this.phoneNumber = memberRequestDTO.getPhoneNumber();
    }

    public void update(MemberRequestDTO memberRequestDTO) {
        this.role = memberRequestDTO.getRole();
    }
}
