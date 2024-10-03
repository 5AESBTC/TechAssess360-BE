package com.example.sourcebase.repository;

import com.example.sourcebase.domain.Assess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAssessRepository extends JpaRepository<Assess, Long> {
}