package org.example.person_management.person.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ListPersonVo {
    /**
     * 页数
     */
    private Integer page;

    /**
     * 分页条数
     */
    private Integer pageSize;

    /**
     * 总条数
     */
    private Long totalElements;

    /**
     * 总页数
     */
    private Long totalPages;

    /**
     * 人员列表
     */
    private List<PersonVo> personList;
}
