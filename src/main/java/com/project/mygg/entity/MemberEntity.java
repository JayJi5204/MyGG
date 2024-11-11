package com.project.mygg.entity;

import com.project.mygg.DTO.MemberRequestDTO;
import com.project.mygg.enums.Line;
import com.project.mygg.enums.Role;
import com.project.mygg.enums.Tier;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "member_entity")
@NoArgsConstructor
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;


    private String password;

    private String nickName;

    private int Elo;

    private int penalty;

    @Enumerated(EnumType.STRING)
    private Line line;

    @Enumerated(EnumType.STRING)
    private Tier tier;

    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL)
    private List<ChampionStatsEntity> championStatsEntity = new ArrayList<>();

//    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL)
//    private List<BoardEntity> boardEntity = new ArrayList<>();
//
//    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL)
//    private List<ReplyEntity> replyEntity = new ArrayList<>();


    public MemberEntity(MemberRequestDTO memberRequestDTO) {
        this.name = memberRequestDTO.getName();
        this.password = memberRequestDTO.getPassword();
        this.nickName = memberRequestDTO.getNickName();
    }
}
