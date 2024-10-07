package com.example.sourcebase.repository;

import com.example.sourcebase.domain.Assess;
import com.example.sourcebase.domain.AssessDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAssessDetailRepository extends JpaRepository<AssessDetail, Long> {
    List<AssessDetail> findByAssess(Assess assess);
}