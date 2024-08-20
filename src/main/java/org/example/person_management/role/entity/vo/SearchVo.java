package org.example.person_management.role.entity.vo;

import lombok.Data;
import org.example.person_management.pub.enums.SearchEnum;

/**
 * @author: 15713
 * @date: 2024/8/16
 */
@Data
public class SearchVo {
    private Integer current = 1;

    private Integer pageNum;

    private Integer pageSize = 15;

    private Integer order = SearchEnum.DESC.getOrder();

    private String org;
}
