package org.example.person_management.role.entity.vo;

import lombok.Data;
import org.example.person_management.org.entity.vo.OrgTreeVo;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: 15713
 * @date: 2024/8/14
 */
@Data
public class RoleInsertVo {
    /**
     * 组织
     */
    @NotNull(message = "组织不能为空")
    private OrgTreeVo org;
    /**
     * 权限列表
     */
    @NotNull(message = "权限列表不能为空")
    private List<SelectVo> perms;
    /**
     * 角色名称
     */
    @NotNull(message = "角色名称不能为空")
    private String role;
    /**
     * 用户列表
     */
    @NotNull(message = "用户列表不能为空")
    private List<SelectVo> user;
}
