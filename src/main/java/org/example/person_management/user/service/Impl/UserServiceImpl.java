package org.example.person_management.user.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.example.person_management.user.entity.User;
import org.example.person_management.user.entity.vo.RegisterVo;
import org.example.person_management.user.entity.vo.UserInfoVo;
import org.example.person_management.user.entity.vo.UserVo;
import org.example.person_management.user.mapper.UserMapper;
import org.example.person_management.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public String login(UserVo userVo) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userVo.getUsername());
        List<User> users = userMapper.selectList(queryWrapper);
        if(CollectionUtils.isEmpty(users)){
            return null;
        }else{
            String password = users.get(0).getPassword();
            StringBuilder sb1 = new StringBuilder(64);
            StringBuilder sb2 = new StringBuilder(16);

            for (int i = 0; i < 16; i++) {
                sb1.append(password.charAt(i * 5));
                sb1.append(password.charAt(i * 5 + 2));
                sb1.append(password.charAt(i * 5 + 3));
                sb1.append(password.charAt(i * 5 + 4));
                sb2.append(password.charAt(i * 5 + 1));
            }

            String sha256Hex = sb1.toString();
            String salt = sb2.toString();

            if(StringUtils.equals(DigestUtils.sha256Hex(userVo.getPassword() + salt), sha256Hex)){
                Map<String, Object> claims = new HashMap<>();
                claims.put("username", users.get(0).getUsername());
                claims.put("id", users.get(0).getId());

                return Jwts.builder()
                        .setClaims(claims)
                        .signWith(SignatureAlgorithm.HS256, "wrs-hzl")
                        .setExpiration(new Date(System.currentTimeMillis() + 24*3600*1000))
                        .compact();

            }else{
                return null;
            }
        }
    }

    @Override
    public boolean register(RegisterVo registerVo) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, registerVo.getUsername());
        List<User> users = userMapper.selectList(queryWrapper);
        if(!CollectionUtils.isEmpty(users)){
            return false;
        }else {
            // 生成一个16位的随机数，也就是盐
            String salt = RandomStringUtils.randomAlphanumeric(16);
            // 将盐拼接到明文后，并生成新的sha256码
            String sha256Hex = DigestUtils.sha256Hex(registerVo.getPassword() + salt);
            // 将盐混到新生成的SHA-256码中，之所以这样做是为了后期解密，校验密码
            StringBuilder sb = new StringBuilder(80); // SHA-256是64个字符，加16个字符的盐，总共80个字符
            for (int i = 0; i < 16; i++) {
                sb.append(sha256Hex.charAt(i * 4));
                sb.append(salt.charAt(i));
                sb.append(sha256Hex.charAt(i * 4 + 1));
                sb.append(sha256Hex.charAt(i * 4 + 2));
                sb.append(sha256Hex.charAt(i * 4 + 3));
            }

            User user = new User();
            BeanUtils.copyProperties(registerVo,user);
            user.setPassword(sb.toString());

            int add = userMapper.insert(user);
            return add > 0;
        }
    }

    @Override
    public Integer parseJwt(String token) {
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey("wrs-hzl")//指定签名密钥
                    .parseClaimsJws(token)
                    .getBody();
            if(Objects.isNull(claims)){
                return 2;
            }
            return 1;
        }catch(MalformedJwtException e){
            return 0;
        }catch (Exception e){
            return 3;
        }
    }

    @Override
    public UserInfoVo getUserInfo(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        List<User> list = userMapper.selectList(queryWrapper);
        User ans = list.stream().findFirst().orElse(new User());
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(ans, userInfoVo);
        return userInfoVo;
    }

    @Override
    public UserInfoVo updateById(UserInfoVo userInfoVo) {
        User user = new User();
        BeanUtils.copyProperties(userInfoVo, user);
        User temp = userMapper.selectById(userInfoVo.getId());
        if(!StringUtils.equals(userInfoVo.getUsername(), temp.getUsername())){
            return null;
        }
        int update = userMapper.updateById(user);
        if(update > 0) {
            return userInfoVo;
        }
        return null;
    }
}
