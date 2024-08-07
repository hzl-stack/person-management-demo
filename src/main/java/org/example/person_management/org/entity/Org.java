package org.example.person_management.org.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.example.person_management.org.entity.base.BaseEntity;

@Data
@TableName("t_org")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Org extends BaseEntity {
    /**
     * 叶子节点key
     */
    @TableField("code")
    private String code;

    /**
     * 组织名称
     */
    @TableField("title")
    private String title;

    /**
     * 父节点组织id
     */
    @TableField("pid")
    private String pid;
}
