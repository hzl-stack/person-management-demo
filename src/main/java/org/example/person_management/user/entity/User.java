package org.example.person_management.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.example.person_management.person.entity.base.BaseEntity;

import java.io.Serializable;

@Data
@TableName("t_user")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "name")
    private String name;

    @TableField(value = "sex")
    private String sex;

    @TableField(value = "avatar")
    private String avatar;

    @TableField(value = "phone_number")
    private String phoneNumber;
}
