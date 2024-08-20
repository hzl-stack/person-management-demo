package org.example.person_management.perm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.person_management.perm.entity.Perm;

import java.util.List;

/**
 * @author: 15713
 * @date: 2024/8/14
 */
@Mapper
public interface PermMapper extends BaseMapper<Perm> {

    List<Perm> queryPermList();
}
