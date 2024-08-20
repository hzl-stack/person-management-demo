package org.example.person_management.person.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.person_management.person.entity.Person;
import org.example.person_management.person.entity.vo.ConditionVo;
import org.example.person_management.person.entity.vo.ListPersonVo;
import org.example.person_management.person.entity.vo.PersonVo;
import org.example.person_management.pub.result.Result;

import java.util.List;

public interface PersonService extends IService<Person> {
    /**
     * 新增操作
     * @param personVo
     * @return
     */
    boolean addManagePerson (PersonVo personVo);

    /**
     * 根据id查询人员信息
     * @param id
     * @return
     */
    Result<PersonVo> findById (String id);

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param order
     * @return
     */
    ListPersonVo queryPersonList (Integer page, Integer pageSize, String order, List<ConditionVo> condition);

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    Integer deleteById(String id);

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    PersonVo selectById(String id);

    /**
     * 保存接口
     * @param personVo
     * @return
     */
    PersonVo save(PersonVo personVo);
}
