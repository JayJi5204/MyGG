package com.project.mygg.entity;

import com.project.mygg.DTO.MemberRequestDTO;
import com.project.mygg.enums.Role;
import com.project.mygg.enums.Tier;
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
    private String username;

    private String password;

    @Column(unique = true)
    private String nickName;

    private int penalty;

    @Enumerated(EnumType.STRING)
    private Tier tier;

    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL)
    private List<ChampionStatsEntity> championStatsEntity = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL)
    private List<BoardEntity> boardEntity = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL)
    private List<ReplyEntity> replyEntity = new ArrayList<>();


    public MemberEntity(MemberRequestDTO memberRequestDTO, String encodedPassword) {
        this.username = memberRequestDTO.getUsername();
        this.password = encodedPassword;
        this.nickName = memberRequestDTO.getNickName();
        this.penalty = memberRequestDTO.getPenalty();
        this.tier = memberRequestDTO.getTier();
        this.role = memberRequestDTO.getRole();
    }
}
