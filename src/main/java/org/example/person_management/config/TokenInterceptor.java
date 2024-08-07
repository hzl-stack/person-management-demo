package org.example.person_management.config;


import org.apache.commons.lang3.StringUtils;
import org.example.person_management.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        String token = request.getHeader("Wrs-Token");
        try{
            if(StringUtils.isEmpty(token) ||  !Objects.equals(userService.parseJwt(token),1)){
                throw new Exception("Token失效");
            }else{
                return true;
            }
        }
        catch(Exception e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }
}
