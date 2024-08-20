package org.example.person_management.org.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.example.person_management.org.entity.Org;
import org.example.person_management.org.entity.vo.OrgInsertVo;
import org.example.person_management.org.entity.vo.OrgTreeVo;
import org.example.person_management.org.entity.vo.OrgUpdateVo;
import org.example.person_management.org.mapper.OrgMapper;
import org.example.person_management.org.service.OrgService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrgServiceImpl extends ServiceImpl<OrgMapper, Org> implements OrgService{

    @Autowired
    private OrgMapper orgMapper;

    @Override
    public int insert(OrgInsertVo orgInsertVo) {
        Org org = new Org();
        Snowflake snowflake = IdUtil.createSnowflake(1,1);
        BeanUtils.copyProperties(orgInsertVo, org);
        org.setCode(String.valueOf(snowflake.nextId()));
        if(StringUtils.isEmpty(orgInsertVo.getPid())){
            org.setPid("0");
        }
        LambdaQueryWrapper<Org> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Org::getPid, org.getPid());
        try {
            if(Objects.equals(org.getPid(), "0")){
                List<Org> list = orgMapper.selectList(queryWrapper);
                if(!list.isEmpty()){
                    return -1;
                }
            }
            return orgMapper.insert(org);
        }catch (Exception e){
            return -2;
        }
    }

    @Override
    public List<OrgTreeVo> queryOrg() {
        List<Org> list = orgMapper.selectList(null);
        return list.stream().map(record -> {
            OrgTreeVo orgTreeVo = new OrgTreeVo();
            BeanUtils.copyProperties(record, orgTreeVo);
            return orgTreeVo;
        }).collect(Collectors.toList());
    }

    @Override
    public List<OrgTreeVo> queryTree(String org) {
        List<Org> list = orgMapper.selectList(null);

        return getAllOrgAsTree(org,list);
    }

    @Override
    public int delete(String orgCode) {
        LambdaQueryWrapper<Org> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Org::getCode, orgCode);
        List<Org> list = orgMapper.selectList(queryWrapper);
        List<String> ids = list.stream().map(Org::getId).collect(Collectors.toList());
        if(ids.isEmpty()){
            return 0;
        }
        int delete = orgMapper.deleteBatchIds(ids);
        if(list.isEmpty()){
            return 0;
        }else{
            list.forEach(record -> {
                delete(record.getCode());
            });
        }
        return delete;
    }

    @Override
    public int update(OrgUpdateVo orgUpdateVo) {
        LambdaQueryWrapper<Org> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Org::getCode,orgUpdateVo.getCode());
        Org org = new Org();
        BeanUtils.copyProperties(orgUpdateVo, org);
        return orgMapper.update(org, queryWrapper);
    }

    @Override
    public OrgTreeVo queryByCode(String code) {
        if(code == null){
            return null;
        }
        LambdaQueryWrapper<Org> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Org::getCode,code);
        Org org = orgMapper.selectOne(queryWrapper);
        OrgTreeVo orgTreeVo = new OrgTreeVo();
        BeanUtils.copyProperties(org, orgTreeVo);
        return orgTreeVo;
    }

    private List<OrgTreeVo> getAllOrgAsTree(String rootId,List<Org> list){
        List<Org> orgList = list.stream().filter(org -> StringUtils.equals(org.getPid(),rootId))
                .collect(Collectors.toList());
        Org root = orgList.stream().findFirst().orElse(null);
        OrgTreeVo rootVo = new OrgTreeVo();
        if(Objects.isNull(root)){
            return null;
        }else{
            BeanUtils.copyProperties(root, rootVo);
            rootVo.setKey(rootVo.getCode());
            return buildTree(rootVo,list);
        }
    }

    private List<OrgTreeVo> getAnswerByCode(OrgTreeVo orgTreeVo,List<Org> orgList) {
        List<Org> list = orgList.stream().filter(org -> StringUtils.equals(org.getPid(), orgTreeVo.getCode()))
                .collect(Collectors.toList());
        return list.stream().map(entity -> {
            OrgTreeVo orgTreeVo1 = new OrgTreeVo();
            BeanUtils.copyProperties(entity,orgTreeVo1);
            return orgTreeVo1;
        }).collect(Collectors.toList());
    }

    private List<OrgTreeVo> buildTree(OrgTreeVo orgTreeVo,List<Org> orgList){
        if(Objects.isNull(orgTreeVo)){
            return null;
        }
        List<OrgTreeVo> children = getAnswerByCode(orgTreeVo, orgList);
        orgTreeVo.setLeaf(children.isEmpty());
        orgTreeVo.setChildren(buildTreeForList(
                children.stream().peek(
                        record -> record.setKey(record.getCode())
                ).collect(Collectors.toList())
                ,orgList));
        List<OrgTreeVo> ans = new ArrayList<>();
        ans.add(orgTreeVo);
        return ans;
    }

    private List<OrgTreeVo> buildTreeForList(List<OrgTreeVo> childrens, List<Org> orgList) {
        return childrens.stream().peek(
                    children -> children.setKey(children.getCode())
                ).peek(children -> {
                    List<OrgTreeVo> ans = getAnswerByCode(children, orgList);
                    children.setLeaf(ans.isEmpty());
                    children.setChildren(buildTreeForList(ans.stream().peek(
                            record -> record.setKey(record.getCode())
                    ).collect(Collectors.toList()),orgList));
                }).collect(Collectors.toList());
    }
}
