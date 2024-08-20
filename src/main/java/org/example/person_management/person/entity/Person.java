package org.example.person_management.person.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.example.person_management.person.entity.base.BaseEntity;
import org.example.person_management.person.entity.dto.DefDocDto;
import org.example.person_management.person.entity.enums.PersonStatus;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName("t_person")
@EqualsAndHashCode(callSuper = false)
public class Person extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 组织
     */
    @TableField(value = "org")
    private String org;

    /**
     * 电话号码
     */
    @TableField(value = "phone_number")
    private String phoneNumber;

    /**
     * 手机号码
     */
    @TableField(value = "mobile_phone_number")
    private String mobilePhoneNumber;

    /**
     * 地址
     */
    @TableField(value = "addr")
    private String addr;

    /**
     * 用户状态
     */
    @TableField(value = "user_status")
    private String userStatus;

    /**
     * 角色
     */
    @TableField(value = "user_role")
    private String userRole;

    /**
     * 备注
     */
    @TableField(value = "note")
    private String note;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;
}
