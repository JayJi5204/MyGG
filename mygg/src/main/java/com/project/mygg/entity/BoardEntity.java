//package com.project.mygg.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Data
//@Entity
//@EntityListeners(AuditingEntityListener.class)
//public class BoardEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "board_id")
//    private Long id;
//
//    @Column(nullable = false)
//    private String title;
//
//    @Column(nullable = false)
//    private String content;
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
//    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.ALL)
//    private List<ReplyEntity> replyEntity = new ArrayList<>();
//}
