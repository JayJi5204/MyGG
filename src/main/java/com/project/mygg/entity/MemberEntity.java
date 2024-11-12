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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    private String name;

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

//    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL)
//    private List<BoardEntity> boardEntity = new ArrayList<>();
//
//    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL)
//    private List<ReplyEntity> replyEntity = new ArrayList<>();


    public MemberEntity(MemberRequestDTO memberRequestDTO, String encodedPassword) {
        this.name = memberRequestDTO.getName();
        this.password = encodedPassword;
        this.nickName = memberRequestDTO.getNickName();
        this.penalty=0;
        this.tier=Tier.UNRANKED;
        this.role=Role.ADMIN;
    }
}
