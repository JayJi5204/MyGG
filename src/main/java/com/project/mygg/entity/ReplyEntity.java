package com.project.mygg.entity;//package com.project.mygg.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import java.time.LocalDateTime;
//
//@Data
//@Entity
//@EntityListeners(AuditingEntityListener.class)
//public class ReplyEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "reply_id")
//    private Integer replyNo;
//    private String reply;
//
//    @CreatedDate
//    @Column(updatable = false)
//    private LocalDateTime regDay;
//
//    @LastModifiedDate
//    private LocalDateTime modDay;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private MemberEntity memberEntity;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "board_id")
//    private BoardEntity boardEntity;
//
//}
