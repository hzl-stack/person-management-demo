package org.example.person_management.role.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.person_management.role.entity.Role;
import org.example.person_management.role.entity.vo.RoleBackExtendVo;
import org.example.person_management.role.entity.vo.RoleBackVo;
import org.example.person_management.role.entity.vo.RoleInsertVo;
import org.example.person_management.role.entity.vo.SearchVo;
import org.springframework.stereotype.Service;

/**
 * @author: 15713
 * @date: 2024/8/15
 */
public interface RoleService extends IService<Role> {
    int insertRole(RoleInsertVo roleInsertVo);

    RoleBackExtendVo selectRoleListPage(SearchVo searchVo);
}
