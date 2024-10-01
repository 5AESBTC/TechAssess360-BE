package com.example.sourcebase.repository;

import com.example.sourcebase.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemRepository extends JpaRepository<Item, Long> {
}
