package org.example.person_management.perm.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.person_management.perm.entity.vo.PermVo;
import org.example.person_management.perm.service.PermService;
import org.example.person_management.pub.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: 15713
 * @date: 2024/8/14
 */
@RestController
@CrossOrigin
@RequestMapping("/hzl-web/person-management/perm")
@Slf4j
public class PermController {
    @Autowired
    private PermService permService;

    @GetMapping("permList")
    public Result<List<PermVo>> queryPermList() {
        log.info("权限列表查询开始");
        return Result.ok(permService.queryPermList());
    }

    @PostMapping("insertPerm")
    public Result<String> insertPerm(@RequestBody PermVo permVo) {
        log.warn("正在进行权限模块新增,敏感操作请注意");
        if(permVo.getPermName() == null){
            return Result.fail("权限名称不能为空");
        }
        int insert = permService.insertPerm(permVo);
        if(insert > 0){
            return Result.ok("插入权限成功");
        }else {
            return Result.fail("插入权限失败");
        }
    }
}
