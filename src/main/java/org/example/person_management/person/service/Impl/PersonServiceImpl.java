package org.example.person_management.person.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.person_management.person.entity.Person;
import org.example.person_management.person.entity.enums.SortEnum;
import org.example.person_management.person.entity.vo.ConditionVo;
import org.example.person_management.person.entity.vo.ListPersonVo;
import org.example.person_management.person.entity.vo.PersonVo;
import org.example.person_management.person.mapper.PersonMapper;
import org.example.person_management.person.result.Result;
import org.example.person_management.person.result.ResultCodeEnum;
import org.example.person_management.person.service.PersonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Override
    public boolean addManagePerson(PersonVo personVo) {
        Person person = new Person();
        BeanUtils.copyProperties(personVo, person);
        int insert = personMapper.insert(person);
        if(insert > 0) return true;
        return false;
    }

    @Override
    public Result<PersonVo> findById(String id) {
        Person person = personMapper.selectById(id);
        if(!Objects.isNull(person)){
            PersonVo personVo = new PersonVo();
            BeanUtils.copyProperties(person, personVo);
            return Result.ok(personVo);
        }
        return Result.fail(ResultCodeEnum.FIND_BY_ID_NULL);
    }

    @Override
    public ListPersonVo queryPersonList(Integer page, Integer pageSize, String order, List<ConditionVo> condition) {
        if(null == page || null == pageSize){
            return new ListPersonVo();
        }
        LambdaQueryWrapper<Person> queryWrapper = new LambdaQueryWrapper<>();
        Page<Person> pageInfo = new Page<>(page, pageSize);
        if(Objects.equals(order, SortEnum.ASC.getOrder())) {
            queryWrapper.orderByAsc(Person::getInsertDt);
        }else {
            queryWrapper.orderByDesc(Person::getInsertDt);
        }
        if(!condition.isEmpty()){
            for(ConditionVo con : condition){
                if(Objects.equals(con.getCompare(), "cn")){
//                  Method method = Person.class.getMethod("get" + con.getField().substring(0, 1).toUpperCase() + con.getField().substring(1));
                    if(Objects.equals(con.getField(), "username")){
                        queryWrapper.like(Person::getUsername, con.getData());
                    }
                    if(Objects.equals(con.getField(), "userStatus")){
                        queryWrapper.like(Person::getUserStatus, con.getData());
                    }
                    if(Objects.equals(con.getField(), "userRole")){
                        queryWrapper.like(Person::getUserRole, con.getData());
                    }
                }else {
                    if(Objects.equals(con.getField(), "username")){
                        queryWrapper.eq(Person::getUsername, con.getData());
                    }
                    if(Objects.equals(con.getField(), "userStatus")){
                        queryWrapper.eq(Person::getUserStatus, con.getData());
                    }
                    if(Objects.equals(con.getField(), "userRole")){
                        queryWrapper.eq(Person::getUserRole, con.getData());
                    }
                }
            }
        }
        IPage<Person> pageResult = personMapper.selectPage(pageInfo,queryWrapper);

        ListPersonVo listPersonVo = new ListPersonVo();
        listPersonVo.setPage(page);
        listPersonVo.setPageSize(pageSize);
        listPersonVo.setTotalPages(pageResult.getPages());
        listPersonVo.setTotalElements(pageResult.getTotal());
        listPersonVo.setPersonList(pageResult.getRecords().stream().map(person -> {
            PersonVo personVo = new PersonVo();
            BeanUtils.copyProperties(person,personVo);
            return personVo;
        }).collect(Collectors.toList()));

        return listPersonVo;
    }

    @Override
    public Integer deleteById(String id) {
        if(Objects.isNull(id)){
            return 0;
        }
        int delete = personMapper.deleteById(id);
        if(delete >= 1){
            return 2;
        }else {
            return 1;
        }
    }

    @Override
    public PersonVo selectById(String id) {
        Person person = new Person();
        person = personMapper.selectById(id);
        PersonVo personVo = new PersonVo();
        BeanUtils.copyProperties(person, personVo);
        return personVo;
    }

    @Override
    public PersonVo save(PersonVo personVo) {
        Person selectPerson = personMapper.selectById(personVo.getId());
        Person person = new Person();
        BeanUtils.copyProperties(personVo, person);
        person.setDr(selectPerson.getDr());
        person.setInsertDt(selectPerson.getInsertDt());
        person.setUpdateDt(selectPerson.getUpdateDt());
        int update = personMapper.updateById(person);
        if(update > 0){
            return personVo;
        }else {
            return null;
        }
    }
}
