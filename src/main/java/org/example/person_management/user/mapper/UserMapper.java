package org.example.person_management.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.person_management.user.entity.User;

import java.util.List;

/**
 * @author HuZili
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> queryUserDropDown();
}
