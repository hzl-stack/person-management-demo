package org.example.person_management.user.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.person_management.pub.result.Result;
import org.example.person_management.pub.result.ResultCodeEnum;
import org.example.person_management.user.entity.vo.*;
import org.example.person_management.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@CrossOrigin
@RestController
@RequestMapping("/hzl-web/person-management/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DefaultKaptcha producer;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping("login")
    public Result<String> login(@RequestBody UserVo userVo) {
        try{
            String token = userService.login(userVo);
            if(StringUtils.isEmpty(token)){
                return Result.fail("登录失败");
            }
            return Result.ok(token);
        }catch (Exception e){
            return Result.fail("登录失败");
        }
    }

    @PostMapping("register")
    public Result<String> register(@RequestBody RegisterVo registerVo){
        if(StringUtils.isEmpty(registerVo.getUsername())){
            return Result.fail("用户名不能为空!");
        }
        if(StringUtils.isEmpty(registerVo.getPassword())){
            return Result.fail("密码不能为空!");
        }
        if(StringUtils.isEmpty(registerVo.getConfirmPassword())){
            return Result.fail("确认密码不能为空!");
        }
        if(StringUtils.isEmpty(registerVo.getSex())){
            return Result.fail("性别不能为空");
        }
        if(!StringUtils.equals(registerVo.getConfirmPassword(),registerVo.getPassword())){
            return Result.fail("密码和确认密码不一致");
        }
        boolean status = userService.register(registerVo);
        if(status){
            return Result.ok("注册成功");
        }else {
            return Result.fail("注册失败");
        }
    }

    @PostMapping("checkLogin")
    public Result<String> checkLogin(@RequestBody TokenVo tokenVo ) {
        Integer status = userService.parseJwt(tokenVo.getToken());
        if(Objects.equals(status,1)){
            return Result.ok("token仍在有效期");
        }else if(Objects.equals(status, 2)){
            return Result.fail("token不能为空");
        }else if(Objects.equals(status,0)){
            return Result.fail("token已过期");
        }else {
            return Result.fail("请登录");
        }
    }

    @GetMapping("getUserInfo")
    public Result<UserInfoVo> getUserInfo(@RequestParam String username){
        if(StringUtils.isEmpty(username)){
            return Result.build(ResultCodeEnum.USERNAME_NOT_NULL.getCode(), ResultCodeEnum.USERNAME_NOT_NULL.getMessage());
        }
        return Result.ok(userService.getUserInfo(username));
    }

    @PostMapping("updateById")
    public Result<UserInfoVo> updateById(@RequestBody UserInfoVo userInfoVo){
        if(StringUtils.isEmpty(userInfoVo.getId())){
            return Result.build(ResultCodeEnum.ID_NOT_NULL.getCode(), ResultCodeEnum.ID_NOT_NULL.getMessage());
        }

        UserInfoVo userInfoVo1 = userService.updateById(userInfoVo);
        if(!Objects.isNull(userInfoVo1)){
            return Result.ok(userInfoVo1);
        }else {
            return Result.build(ResultCodeEnum.USER_UPDATE_FAILED.getCode(), ResultCodeEnum.USER_UPDATE_FAILED.getMessage());
        }
    }

    @GetMapping("getVerifyCode")
    public Result<VerifyVo> getVerifyCode(@RequestParam String username) throws IOException {
        Long ttl = redisTemplate.getExpire(username);

//        if(null == ttl || ttl <= 0){
            String text = producer.createText();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BufferedImage image = producer.createImage(text);
            ImageIO.write(image, "jpg", outputStream);

            BASE64Encoder encoder = new BASE64Encoder();
            String str = "data:image/jpeg;base64,";
            String base64Img = str + encoder.encode(outputStream.toByteArray()).replace("\n", "").replace("\r", "");

            VerifyVo verifyVo = new VerifyVo();
            verifyVo.setCode(username);
            verifyVo.setImg(base64Img);

            redisTemplate.opsForValue().set(username, text,5, TimeUnit.MINUTES);

            return Result.ok(verifyVo);
//        }
//        else {
//            return Result.build(
//                    ResultCodeEnum.VERIFY_CODE_HAS_NOT_EXPIRED.getCode(),
//                    ResultCodeEnum.VERIFY_CODE_HAS_NOT_EXPIRED.getMessage()
//            );
//        }
    }

    @GetMapping("verify")
    public Result<String> verify(@RequestParam String code,@RequestParam String username){
        Object verify = redisTemplate.opsForValue().get(username);
        if(Objects.isNull(verify)){
            return Result.fail("用户名变更，请重新输入验证码");
        }
        String verifyCode = Objects.requireNonNull(redisTemplate.opsForValue().get(username)).toString();
        if(StringUtils.equals(code, verifyCode)){
            redisTemplate.delete(username);
            return Result.ok("校验成功");
        }
        return Result.fail("验证码不匹配");
    }

    @GetMapping("queryUserDropDown")
    public Result<List<UserInfoVo>> queryUserDropDown(){
        log.info("查询人员信息开始");
        return Result.ok(userService.queryUserDropDown());
    }
}
