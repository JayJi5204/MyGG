package com.project.mygg.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "result_entity")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Long id;

    private String resultDate; // 경기일자는 수동으로 기입할 것이라서 String으로 함

    private String content;

    private String pog;

    private String writer;

    @LastModifiedDate
    private LocalDateTime modDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;


    public ResultEntity(MemberEntity memberEntity, String resultDate, String content, String pog, String writer) {
        this.memberEntity = memberEntity;
        this.resultDate = resultDate;
        this.content = content;
        this.pog = pog;
        this.writer = writer;
    }

    public void updateResult(String resultDate, String content, String pog, String writer) {
        this.resultDate = resultDate;
        this.content = content;
        this.pog = pog;
        this.writer = writer;
    }
}
