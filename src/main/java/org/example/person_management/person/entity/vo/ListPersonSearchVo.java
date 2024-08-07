package org.example.person_management.person.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.example.person_management.person.entity.enums.SortEnum;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ListPersonSearchVo {
    /**
     * 当前分页数
     */
    private Integer page;

    /**
     * 分页大小
     */
    private Integer pageSize;

    /**
     * 排序方式
     */
    private String order = SortEnum.DESC.getOrder();

    /**
     * 查询条件
     */
    private List<ConditionVo> condition;
}
