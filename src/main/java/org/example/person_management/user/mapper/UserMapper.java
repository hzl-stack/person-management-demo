package org.example.person_management.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.person_management.user.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
