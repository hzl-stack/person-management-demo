package org.example.person_management.person.entity.vo;

import lombok.Data;

@Data
public class ConditionVo {
    /**
     * 字段名
     */
    private String field;

    /**
     * 字段值
     */
    private String data;

    /**
     * 查询方式
     */
    private String compare = "eq";
}
