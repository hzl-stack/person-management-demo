package org.example.person_management.person.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.person_management.person.entity.Person;

@Mapper
public interface PersonMapper extends BaseMapper<Person> {
}
