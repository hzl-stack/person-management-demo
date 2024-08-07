package org.example.person_management.person.entity.vo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.example.person_management.person.entity.dto.DefDocDto;
import org.example.person_management.person.entity.enums.PersonStatus;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PersonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 姓名
     */
    private String username;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 手机号码
     */
    private String mobilePhoneNumber;

    /**
     * 地址
     */
    private String addr;

    /**
     * 用户状态
     */
    private String userStatus;

    /**
     * 角色
     */
    private String userRole;

    /**
     * 备注
     */
    private String note;

    /**
     * 状态码
     */
    private Integer rowStatus = 0;

    /**
     * 头像
     */
    private String avatar;
}
