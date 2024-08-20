package org.example.person_management.pub.enums;

import lombok.Getter;

/**
 * @author: 15713
 * @date: 2024/8/16
 */
@Getter
public enum SearchEnum {
    /**
     * 降序
     */
    DESC(0),
    /**
     * 升序
     */
    ASC(1);

    private final int order;

    SearchEnum(int order) {
        this.order = order;
    }
}
