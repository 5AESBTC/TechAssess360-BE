package com.example.sourcebase.repository;

import com.example.sourcebase.domain.User;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepository extends JpaRepository<User, Long> {
}
