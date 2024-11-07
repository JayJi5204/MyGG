package com.project.mygg.entity;

import com.project.mygg.enums.Line;
import com.project.mygg.enums.Role;
import com.project.mygg.enums.Tier;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
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

}
