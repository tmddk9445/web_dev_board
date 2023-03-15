package com.jihoon.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jihoon.board.entity.SearchWordLogEntity;

@Repository
public interface SearchWordLogRepository extends JpaRepository<SearchWordLogEntity, Integer> {
    
}
