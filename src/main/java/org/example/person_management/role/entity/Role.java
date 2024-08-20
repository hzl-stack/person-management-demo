package org.example.person_management.role.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.example.person_management.role.entity.base.BaseEntity;

/**
 * @author: 15713
 * @date: 2024/8/14
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("t_role")
public class Role extends BaseEntity {
    /**
     * 用户编码
     */
    private String userCodes;
    /**
     * 角色名称
     */
    private String role;
    /**
     * 权限子表id
     */
    private String permPids;
    /**
     * 所属组织
     */
    private String org;
}
