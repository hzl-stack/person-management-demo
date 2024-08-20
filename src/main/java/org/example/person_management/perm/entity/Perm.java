package org.example.person_management.perm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.example.person_management.perm.entity.base.BaseEntity;

/**
 * @author: HuZili
 * @date: 2024/8/14
 */
@Data
@Accessors(chain = true)
@TableName("t_perm")
@EqualsAndHashCode(callSuper = false)
public class Perm extends BaseEntity {

    @TableField("perm_name")
    private String permName;
}
