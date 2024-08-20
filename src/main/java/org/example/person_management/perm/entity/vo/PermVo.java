package org.example.person_management.perm.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author: 15713
 * @date: 2024/8/14
 */
@Data
public class PermVo {
    /**
     * 主键
     */
    private String id;
    /**
     * 权限名称
     */
    private String permName;
}
