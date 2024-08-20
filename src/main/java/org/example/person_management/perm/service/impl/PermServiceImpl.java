package org.example.person_management.perm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.person_management.perm.entity.Perm;
import org.example.person_management.perm.entity.vo.PermVo;
import org.example.person_management.perm.mapper.PermMapper;
import org.example.person_management.perm.service.PermService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: 15713
 * @date: 2024/8/14
 */
@Service
public class PermServiceImpl extends ServiceImpl<PermMapper, Perm> implements PermService {

    @Autowired
    private PermMapper permMapper;

    @Override
    public List<PermVo> queryPermList() {
        return permMapper.queryPermList().stream().map(perm -> {
            PermVo permVo = new PermVo();
            BeanUtils.copyProperties(perm,permVo);
            return permVo;
        }).collect(Collectors.toList());
    }

    @Override
    public int insertPerm(PermVo permVo) {
        Perm perm = new Perm();
        BeanUtils.copyProperties(permVo,perm);
        return permMapper.insert(perm);
    }
}
