package org.example.person_management.person.entity.enums;

import lombok.Getter;

@Getter
public enum SortEnum {
    DESC("desc","降序"),
    ASC("asc", "升序");

    private final String order;

    private final String description;

    SortEnum(String order, String description){
        this.order = order;
        this.description = description;
    }
}
