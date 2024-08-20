package org.example.person_management.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.person_management.user.entity.User;
import org.example.person_management.user.entity.vo.RegisterVo;
import org.example.person_management.user.entity.vo.UserInfoVo;
import org.example.person_management.user.entity.vo.UserVo;

import java.util.List;

public interface UserService extends IService<User> {
    String login(UserVo userVo);

    boolean register(RegisterVo registerVo);

    Integer parseJwt(String token);

    UserInfoVo getUserInfo(String username);

    UserInfoVo updateById(UserInfoVo userInfoVo);

    List<UserInfoVo> queryUserDropDown();
}
