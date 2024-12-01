package com.project.mygg.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.project.mygg.entity.BoardEntity;
import com.project.mygg.entity.ResultEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<ResultEntity,Long> {

    Page<ResultEntity> findByResultDateContaining(String keyword, Pageable pageable);

    Page<ResultEntity> findByPogContaining(String keyword, Pageable pageable);
}
