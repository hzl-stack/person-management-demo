package org.example.person_management.org.controller;

import org.apache.commons.lang3.StringUtils;
import org.example.person_management.org.entity.vo.OrgInsertVo;
import org.example.person_management.org.entity.vo.OrgTreeVo;
import org.example.person_management.org.entity.vo.OrgUpdateVo;
import org.example.person_management.org.service.OrgService;
import org.example.person_management.pub.result.Result;
import org.example.person_management.pub.result.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/hzl-web/person-management/org")
public class OrgController {

    @Autowired
    private OrgService orgService;

    @PostMapping("insert")
    public Result<String> insert(@RequestBody OrgInsertVo orgInsertVo){
        if(StringUtils.isEmpty(orgInsertVo.getTitle())){
            return Result.fail("组织名称不能为空！");
        }

        int insert = orgService.insert(orgInsertVo);
        if(insert > 0){
            return Result.ok("新增组织成功");
        }else {
            if(insert == -1){
                return Result.fail("根组织节点不能为空");
            }
            return Result.fail("新增组织失败");
        }
    }

    @GetMapping("queryTree")
    public Result<List<OrgTreeVo>> queryTree(@RequestParam String org){
        if(StringUtils.isEmpty(org)){
            return Result.build(
                    ResultCodeEnum.QUERY_LIST_PARAMS_ERROR.getCode(),
                    ResultCodeEnum.QUERY_LIST_PARAMS_ERROR.getMessage()
            );
        }
        List<OrgTreeVo> list = orgService.queryTree(org);
        return Result.ok(list);
    }

    @GetMapping("queryOrg")
    public Result<List<OrgTreeVo>> queryOrg(){
        return Result.ok(orgService.queryOrg());
    }

    @GetMapping("delete")
    public Result<String> delete(@RequestParam String org){
        if(StringUtils.isEmpty(org)) {
            return Result.build(
                    ResultCodeEnum.QUERY_LIST_PARAMS_ERROR.getCode(),
                    ResultCodeEnum.QUERY_LIST_PARAMS_ERROR.getMessage()
            );
        }
        if(StringUtils.equals("0",org)){
            return Result.fail("不能删除根组织");
        }
        int delete = orgService.delete(org);
        if(delete > 0){
            return Result.ok("删除成功");
        }else{
            return Result.fail("删除失败");
        }
    }

    @PostMapping("update")
    public Result<String> update(@RequestBody OrgUpdateVo orgUpdateVo){
        if(StringUtils.isEmpty(orgUpdateVo.getCode())){
            return Result.fail("组织编码不能为空");
        }
        int update = orgService.update(orgUpdateVo);
        if(update > 0){
            return Result.ok("更新成功");
        }else{
            return Result.fail("删除失败");
        }
    }
}
