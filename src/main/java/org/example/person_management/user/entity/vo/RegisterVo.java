package org.example.person_management.user.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterVo {
    private String username;

    private String password;

    private String confirmPassword;

    private String sex;
}
