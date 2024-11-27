package com.project.mygg.repository;

import com.project.mygg.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Long> {

    Page<BoardEntity> findByTitleContaining(String keyword, Pageable pageable);
    Page<BoardEntity> findByContentContaining(String keyword, Pageable pageable);


}
