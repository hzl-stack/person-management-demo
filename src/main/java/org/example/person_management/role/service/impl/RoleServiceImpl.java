package org.example.person_management.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.person_management.perm.entity.Perm;
import org.example.person_management.perm.entity.vo.PermVo;
import org.example.person_management.perm.mapper.PermMapper;
import org.example.person_management.role.entity.Role;
import org.example.person_management.role.entity.vo.*;
import org.example.person_management.role.mapper.RoleMapper;
import org.example.person_management.role.service.RoleService;
import org.example.person_management.user.entity.User;
import org.example.person_management.user.entity.vo.UserInfoVo;
import org.example.person_management.user.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: 15713
 * @date: 2024/8/15
 */
@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService  {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermMapper permMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public int insertRole(RoleInsertVo roleInsertVo) {
        Role role = new Role();
        role.setOrg(roleInsertVo.getOrg().getCode());
        role.setRole(roleInsertVo.getRole());
        role.setUserCodes(roleInsertVo.getUser().stream().map(
                SelectVo::getValue
        ).collect(Collectors.joining(",")));
        role.setPermPids(roleInsertVo.getPerms().stream().map(
                SelectVo::getValue
        ).collect(Collectors.joining(",")));

        return roleMapper.insert(role);
    }

    @Override
    public RoleBackExtendVo selectRoleListPage(SearchVo searchVo) {
        Page<RoleBackVo> page =  roleMapper.selectRoleListPage(new Page<>(searchVo.getCurrent(),searchVo.getPageSize()), searchVo);
        RoleBackExtendVo roleBackExtendVo = new RoleBackExtendVo();
        roleBackExtendVo.setPageNum(page.getPages());
        roleBackExtendVo.setPageSize(page.getSize());
        roleBackExtendVo.setCurrent(page.getCurrent());
        roleBackExtendVo.setTotal(page.getTotal());
        List<Perm> permList = permMapper.selectList(null);
        List<User> userList = userMapper.selectList(null);
        List<RoleBackRecordVo> roleBackRecordVoList = new ArrayList<>();
        page.getRecords().forEach(
                record -> {
                    RoleBackRecordVo roleBackRecordVo = new RoleBackRecordVo();
                    roleBackRecordVo.setRole(record.getRole());
                    roleBackRecordVo.setId(record.getId());
                    roleBackRecordVo.setOrgCode(record.getOrgCode());
                    roleBackRecordVo.setOrgTitle(record.getOrgTitle());
                    roleBackRecordVo.setSuperOrgCode(record.getSuperOrgCode());
                    List<PermVo> permVoList = new ArrayList<>();
                    List<UserInfoVo> userInfoVoList = new ArrayList<>();
                    Stream.of(record.getPermPids().split(",")).forEach(
                            permPid -> {
                                permList.forEach(
                                        perm -> {
                                            if(StringUtils.equals(perm.getId(),permPid)){
                                                PermVo permVo = new PermVo();
                                                permVo.setPermName(perm.getPermName());
                                                permVo.setId(perm.getId());
                                                permVoList.add(permVo);
                                            }
                                        }
                                );
                            }
                    );
                    Stream.of(record.getUserCodes().split(",")).forEach(
                            userCode -> {
                                userList.forEach(
                                        user -> {
                                            if(StringUtils.equals(user.getUsername(),userCode)){
                                                UserInfoVo userInfoVo = new UserInfoVo();
                                                BeanUtils.copyProperties(user,userInfoVo);
                                                userInfoVoList.add(userInfoVo);
                                            }
                                        }
                                );
                            }
                    );
                    roleBackRecordVo.setPermVoList(permVoList);
                    roleBackRecordVo.setUserInfoVoList(userInfoVoList);
                    roleBackRecordVoList.add(roleBackRecordVo);
                }
        );
        roleBackExtendVo.setContent(roleBackRecordVoList);

        return roleBackExtendVo;
    }
}
