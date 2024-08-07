package org.example.person_management.user.entity.base;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class BaseEntity {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    @TableField(value = "insert_dt", fill = FieldFill.INSERT)
    private Timestamp insertDt;

    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    @TableField(value = "update_dt", fill = FieldFill.INSERT_UPDATE)
    private Timestamp updateDt;

    @TableField(value = "dr", fill = FieldFill.INSERT_UPDATE)
    @TableLogic
    private Integer dr;

    @TableField(exist = false)
    private Integer rowStatus = 0;
}
