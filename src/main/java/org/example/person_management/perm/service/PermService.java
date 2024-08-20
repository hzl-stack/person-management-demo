package org.example.person_management.perm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.person_management.perm.entity.Perm;
import org.example.person_management.perm.entity.vo.PermVo;
import org.example.person_management.perm.mapper.PermMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 15713
 * @date: 2024/8/14
 */
public interface PermService extends IService<Perm> {
    List<PermVo> queryPermList();

    int insertPerm(PermVo permVo);
}
