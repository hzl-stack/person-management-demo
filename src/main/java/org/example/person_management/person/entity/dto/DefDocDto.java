package org.example.person_management.person.entity.dto;

import lombok.Data;

@Data
public class DefDocDto {
    /**
     * 编号
     */
    private String code;

    /**
     * 描述
     */
    private String name;

    public DefDocDto(String code, String name){
        this.code = code;
        this.name = name;
    }
}
