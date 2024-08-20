package org.example.person_management.person.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.person_management.person.entity.enums.BaseEnum;
import org.example.person_management.person.entity.vo.ListPersonSearchVo;
import org.example.person_management.person.entity.vo.ListPersonVo;
import org.example.person_management.person.entity.vo.PersonVo;
import org.example.person_management.pub.result.Result;
import org.example.person_management.pub.result.ResultCodeEnum;
import org.example.person_management.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/hzl-web/person-management/person")
@Slf4j
//@RequiredArgsConstructor
public class PersonController {

    @Autowired
    public PersonService personService;

    @PostMapping(path = "add")
    public Result<PersonVo> addManagePerson(@RequestBody PersonVo personVo) {
        if(personVo.getRowStatus() != BaseEnum.ADD.getStatus()){
            return Result.build(ResultCodeEnum.ADD_ROW_STATUS.getCode(),ResultCodeEnum.ADD_ROW_STATUS.getMessage());
        }
        boolean add = personService.addManagePerson(personVo);
        if(add) {
            return Result.ok();
        }else {
            return Result.build(ResultCodeEnum.ADD_ERROR.getCode(),ResultCodeEnum.ADD_ERROR.getMessage());
        }
    }

    @GetMapping(path = "findById")
    public Result<PersonVo> findById(@RequestParam(name = "id", required = true) String id) {

        return personService.findById(id);
    }

    @PostMapping("queryList")
    public Result<ListPersonVo> queryPersonList(@RequestBody(required = true) ListPersonSearchVo listPersonSearchVo){
        log.info("查询管理员人员列表");
        if(listPersonSearchVo.getPage() == null || listPersonSearchVo.getPageSize() == null){
            return Result.build(
                    ResultCodeEnum.QUERY_LIST_PARAMS_ERROR.getCode(),
                    ResultCodeEnum.QUERY_LIST_PARAMS_ERROR.getMessage()
            );
        }
        ListPersonVo listPersonVo = personService.queryPersonList(
                listPersonSearchVo.getPage(),
                listPersonSearchVo.getPageSize(),
                listPersonSearchVo.getOrder(),
                listPersonSearchVo.getCondition()
        );

        return Result.ok(listPersonVo);
    };

    @GetMapping(path = "deleteById")
    public Result<Object> deleteById(@RequestParam(required = false) String id){
        int delete = personService.deleteById(id);
        if(delete == 0){
            return Result.build(
                    ResultCodeEnum.ID_NOT_NULL.getCode(),
                    ResultCodeEnum.ID_NOT_NULL.getMessage()
            );
        }else if(delete == 1){
            return Result.build(
                    ResultCodeEnum.DELETE_FAILED.getCode(),
                    ResultCodeEnum.DELETE_FAILED.getMessage()
            );
        }else {
            return Result.ok();
        }
    }

    @GetMapping(path = "queryById")
    public Result<PersonVo> selectById(@RequestParam(required = false) String id){
        if(Objects.isNull(id)){
            return Result.build(
                    ResultCodeEnum.ID_NOT_NULL.getCode(),
                    ResultCodeEnum.ID_NOT_NULL.getMessage()
            );
        }
        try {
            PersonVo personVo = personService.selectById(id);
            return Result.ok(personVo);
        }catch (Exception e){
            return Result.build(
                    ResultCodeEnum.QUERY_DETAIL_FAILED.getCode(),
                    ResultCodeEnum.QUERY_DETAIL_FAILED.getMessage()
            );
        }
    }

    @PostMapping(path = "save")
    public Result<PersonVo> save(@RequestBody PersonVo personVo){
        if(Objects.isNull(personVo.getId())){
            return Result.build(
                    ResultCodeEnum.ID_NOT_NULL.getCode(),
                    ResultCodeEnum.ID_NOT_NULL.getMessage()
            );
        }
        if(personVo.getRowStatus() != BaseEnum.EDIT.getStatus()){
            return Result.build(
                    ResultCodeEnum.EDIT_ROW_STATUS.getCode(),
                    ResultCodeEnum.EDIT_ROW_STATUS.getMessage()
            );
        }
        PersonVo personVo1 = personService.save(personVo);
        if(Objects.isNull(personVo1)){
            return Result.build(
                    ResultCodeEnum.EDIT_FAILED.getCode(),
                    ResultCodeEnum.EDIT_FAILED.getMessage()
            );
        }else{
            return Result.ok(personVo1);
        }
    }
}
