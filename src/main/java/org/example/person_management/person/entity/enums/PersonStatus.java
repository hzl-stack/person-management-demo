package org.example.person_management.person.entity.enums;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum PersonStatus {
    OUT_DUTY("0", "离职"),
    IN_DUTY("1", "在职"),
    HOLIDAY("2", "请假"),
    TEMPORARILY("3", "借调"),
    PUNISH("4", "处分");

    /**
     * 编码
     */
    private final String code;
    /**
     * 描述
     */
    private final String name;

    PersonStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getPersonStatusName(String code){
        for(PersonStatus personStatus:PersonStatus.values()){
            if(Objects.equals(personStatus.getCode(), code)){
                return personStatus.getName();
            }
        }
        return null;
    }
}
