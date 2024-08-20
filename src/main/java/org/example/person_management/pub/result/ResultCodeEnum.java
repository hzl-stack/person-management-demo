package org.example.person_management.pub.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCodeEnum {

    SUCCESS(200, "成功"),
    FAIL(201, "失败"),
    PARAM_ERROR(202, "参数不正确"),
    SERVICE_ERROR(203, "服务异常"),
    DATA_ERROR(204, "数据异常"),
    DATA_UPDATE_ERROR(205, "数据版本异常"),
    DEPARTMENT_NAME_NULL(206, "部门名称不能为空"),
    EMPLOYEE_INFO_NULL(207, "员工信息存在必填字段为空"),
    TEL_ERROR(208, "手机号格式错误"),
    EMPLOYEE_NUMBER_EXIST(209, "人员信息已存在"),
    APPLY_CODE_NULL(210, "申请单号不能为空"),
    DEPARTMENT_EXIST(211, "部门编码已存在"),
    PERSON_ID_NULL(212,"员工编号不能为空"),
    APPLY_CODE_REPEAT(213, "申请单已存在"),
    APPLY_REQUIREDINFO_UNCOMPELETE(214,"申请单必填信息未完成"),

    ADD_ROW_STATUS(215,"新增操作员状态码错误"),
    ADD_ERROR(216,"新增操作员失败"),
    FIND_BY_ID_NULL(217,"未查询到数据"),
    QUERY_LIST_PARAMS_ERROR(218,"缺少查询参数"),
    ID_NOT_NULL(219, "主键不能为空"),
    DELETE_FAILED(220, "删除失败"),
    QUERY_DETAIL_FAILED(221, "查询详情失败"),
    EDIT_ROW_STATUS(222, "编辑操作员状态码错误，编辑失败"),
    EDIT_FAILED(223, "编辑失败"),
    USERNAME_NOT_NULL(224, "用户名不能为空"),
    USER_UPDATE_FAILED(225, "更新用户信息失败"),
    VERIFY_CODE_HAS_NOT_EXPIRED(226, "验证码仍在有效期"),

    TOKEN_EXPIRED(402, "token已失效");

    private final Integer code;
    private final String message;
}
