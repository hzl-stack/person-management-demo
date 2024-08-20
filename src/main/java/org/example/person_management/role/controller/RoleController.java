package org.example.person_management.role.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.example.person_management.pub.result.Result;
import org.example.person_management.role.entity.vo.RoleBackExtendVo;
import org.example.person_management.role.entity.vo.RoleBackVo;
import org.example.person_management.role.entity.vo.RoleInsertVo;
import org.example.person_management.role.entity.vo.SearchVo;
import org.example.person_management.role.service.RoleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author: 15713
 * @date: 2024/8/14
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping(path = "/hzl-web/person-management/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @PostMapping("insertRole")
    public Result<RoleInsertVo> insertRole(@RequestBody @Valid RoleInsertVo roleInsertVo){
        log.info("新增角色");
        int insert = roleService.insertRole(roleInsertVo);
        if(insert > 0){
            return Result.ok(roleInsertVo);
        }
        return Result.fail();
    }

    @PostMapping("queryRolePage")
    public Result<RoleBackExtendVo> queryRolePage(@RequestBody SearchVo searchVo){
        return Result.ok(roleService.selectRoleListPage(searchVo));
    }
}
