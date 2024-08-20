package org.example.person_management.role.entity.vo;

import lombok.Data;
import org.example.person_management.perm.entity.vo.PermVo;

import java.util.List;

/**
 * @author: 15713
 * @date: 2024/8/18
 */
@Data
public class RoleBackExtendVo {
    /**
     * 列表内容
     */
    private List<RoleBackRecordVo> content;
    /**
     * 总数
     */
    private Long total;
    /**
     * 每页数量
     */
    private Long pageSize;
    /**
     * 当前页数
     */
    private Long current;
    /**
     * 总页数
     */
    private Long pageNum;
}
