package org.example.person_management.org.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class OrgTreeVo {
    /**
     * 组织名称
     */
    private String title;
    /**
     * 组织key
     */
    private String key;
    /**
     * 组织编码
     */
    private String code;
    /**
     * 是否叶子节点
     */
    private Boolean leaf;
    /**
     * 父组织编码
     */
    private String pid;
    /**
     * 子节点
     */
    private List<OrgTreeVo> children;
}
