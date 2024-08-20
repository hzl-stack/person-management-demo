package org.example.person_management.role.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.person_management.role.entity.Role;
import org.example.person_management.role.entity.vo.RoleBackVo;
import org.example.person_management.role.entity.vo.SearchVo;

/**
 * @author: 15713
 * @date: 2024/8/15
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    Page<RoleBackVo> selectRoleListPage(Page<RoleBackVo> page,
                                        @Param("vo")SearchVo vo);
}
