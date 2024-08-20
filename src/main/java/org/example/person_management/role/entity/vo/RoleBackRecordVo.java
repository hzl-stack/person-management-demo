package org.example.person_management.role.entity.vo;

import lombok.Data;
import org.example.person_management.perm.entity.vo.PermVo;
import org.example.person_management.user.entity.vo.UserInfoVo;

import java.util.List;

/**
 * @author: 15713
 * @date: 2024/8/18
 */
@Data
public class RoleBackRecordVo {
    /**
     * 主键
     */
    private String id;
    /**
     * 用户列表
     */
    private List<UserInfoVo> userInfoVoList;
    /**
     * 角色名称
     */
    private String role;
    /**
     * 权限列表
     */
    private List<PermVo> permVoList;
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
