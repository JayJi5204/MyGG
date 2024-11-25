package com.project.mygg.repository;

import com.project.mygg.entity.MatchEntity;
import com.project.mygg.entity.PlayerEntity;
import com.project.mygg.enums.Tier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<MatchEntity,Long> {


}
