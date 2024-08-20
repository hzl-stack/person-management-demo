package org.example.person_management.org.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.person_management.org.entity.Org;
import org.example.person_management.org.entity.vo.OrgInsertVo;
import org.example.person_management.org.entity.vo.OrgTreeVo;
import org.example.person_management.org.entity.vo.OrgUpdateVo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrgService extends IService<Org> {

    /**
     * 新增组织
     * @param orgInsertVo
     * @return
     */
    int insert(OrgInsertVo orgInsertVo);

    List<OrgTreeVo> queryOrg();

    /**
     * 查询组织树
     * @param org
     * @return
     */
    List<OrgTreeVo> queryTree(String org);

    /**
     * 根据组织编码删除本下组织
     * @param orgCode
     * @return
     */
    int delete(String orgCode);

    /**
     * 更新组织名称
     * @param orgUpdateVo
     * @return
     */
    int update(OrgUpdateVo orgUpdateVo);

    OrgTreeVo queryByCode(String code);
}
