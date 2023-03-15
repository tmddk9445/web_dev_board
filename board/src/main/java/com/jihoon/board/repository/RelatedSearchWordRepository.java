package com.jihoon.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jihoon.board.entity.RelatedSearchWordEntity;

@Repository
public interface RelatedSearchWordRepository extends JpaRepository<RelatedSearchWordEntity, Integer> {
    
}
