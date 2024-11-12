package com.project.mygg.repository;

import com.project.mygg.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
   //List<MemberEntity> findByUsername(String username);

    boolean existsByUsername(String username);

    MemberEntity findByUsername(String username);

}
