package com.example.sourcebase.repository;

import com.example.sourcebase.domain.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CriteriaRepository extends JpaRepository<Criteria, Long> {
}