package com.example.sourcebase.repository;

import com.example.sourcebase.domain.Assess;
import com.example.sourcebase.domain.Project;
import com.example.sourcebase.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAssessRepository extends JpaRepository<Assess, Long> {
    @Query("SELECT a FROM Assess a INNER JOIN UserProject up ON a.toUser = up.user WHERE a.toUser = :user AND up.project = :project")
    List<Assess> findByToUserAndProject(@Param("user") User user, @Param("project") Project project);

}