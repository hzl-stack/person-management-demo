package org.example.person_management.role.entity.vo;

import lombok.Data;

/**
 * @author: 15713
 * @date: 2024/8/16
 */
@Data
public class RoleBackVo {
    /**
     * 主键
     */
    private String id;
    /**
     * 用户编码
     */
    private String userCodes;
    /**
     * 角色名称
     */
    private String role;
    /**
     * 权限主表id
     */
    private String permPids;
    /**
     * 组织编码
     */
    private String orgCode;
    /**
     * 组织名称
     */
    private String orgTitle;
    /**
     * 上级组织编码
     */
    private String superOrgCode;
}
