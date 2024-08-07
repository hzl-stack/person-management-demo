package org.example.person_management.person.entity.dto;

import lombok.Data;

@Data
public class PersonStatusDto {
    private String code;

    private String name;

    public PersonStatusDto(String code, String name){
        this.code = code;
        this.name = name;
    }
}
