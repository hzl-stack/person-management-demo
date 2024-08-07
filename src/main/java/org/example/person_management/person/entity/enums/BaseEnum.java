package org.example.person_management.person.entity.enums;

import lombok.Getter;

@Getter
public enum BaseEnum {
    /**
     * 未变化
     */
    UNCHANGED(0),
    /**
     * 编辑
     */
    EDIT(1),
    /**
     * 新增
     */
    ADD(2),
    /**
     * 删除
     */
    DELETE(3);

    private int status;

    /**
     * 有参构造器
     * @param status
     */
    BaseEnum(int status){
        this.status = status;
    }
}
