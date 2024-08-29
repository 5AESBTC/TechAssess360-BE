package com.example.sourcebase.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor

@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public enum ETypeUser {
    FRESHER("Fresher"),
    JUNIOR("Junior"),
    MIDDLE("Middle"),
    SENIOR("Senior"),
    MANAGER("Manager"),
    ADMIN("Admin"),;
    String type;
}
