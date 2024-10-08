package com.example.sourcebase.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.example.sourcebase.domain.Assess;
import com.example.sourcebase.domain.enumeration.ETypeAssess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAssessRepository extends JpaRepository<Assess, Long> {

    @Query("SELECT a FROM Assess a WHERE a.toUser.id = :userId")
    List<Assess> getListAssessByUserId(Long userId);

    Assess findByToUserIdAndAssessmentType(Long userId, ETypeAssess type);
}