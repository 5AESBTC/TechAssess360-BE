package com.example.sourcebase.repository;

import com.example.sourcebase.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
    Position findByName(String positionInput);
}