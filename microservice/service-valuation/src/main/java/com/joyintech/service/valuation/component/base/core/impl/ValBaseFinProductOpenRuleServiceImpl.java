/*
 * 文件名：BaseFinProductOpenRuleServiceImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.component.base.core.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.component.base.core.ValBaseFinProductOpenRuleService;
import com.joyintech.service.valuation.component.base.core.dto.ValBaseFinProductOpenRuleDto;
import com.joyintech.service.valuation.component.base.core.impl.dao.ValBaseFinProductOpenRuleMapper;
import com.joyintech.tams.common.base.dao.FinOpenRuleMapper;
import com.joyintech.tams.common.base.entity.FinOpenRule;

/**
 * 开放规则组件实现类
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年9月4日
 * @see ValBaseFinProductOpenRuleServiceImpl
 * @since 1.0
 */
@Service
public class ValBaseFinProductOpenRuleServiceImpl implements ValBaseFinProductOpenRuleService{
    /**
     * 开放规则组件自定义mapper
     */
    @Autowired
    private ValBaseFinProductOpenRuleMapper baseFinProductOpenRuleMapper;
    /**
     * 开放规则组件自动生成mapper
     */
    @Autowired
    private FinOpenRuleMapper finOpenRuleMapper;
    
    @Autowired
    private ValBaseFinProductOpenRuleServiceImpl baseFinProductOpenRuleServiceImpl;

    /**
     * 开放规则组件修改
     */
    @Override
    public boolean update(ValBaseFinProductOpenRuleDto dto) {
        return baseFinProductOpenRuleServiceImpl.delete(dto) && baseFinProductOpenRuleServiceImpl.insert(dto.getFinOpenRuleList());
    }

    /**
     * 开放规则组件删除
     */
    @Override
    public boolean delete(ValBaseFinProductOpenRuleDto dto) {
//        if(dto.getFinOpenRuleList()!=null && dto.getFinOpenRuleList().size()>0){
//            for(FinOpenRule finOpenRule:dto.getFinOpenRuleList()){
//                BaseFinProductOpenRuleDto entity = new BaseFinProductOpenRuleDto();
//                entity.setCashId(finOpenRule.getCashId());
//                entity.setOpenType(finOpenRule.getOpenType());
//                return baseFinProductOpenRuleMapper.delete(entity)>0;
//            }
//        }else{
//            if(dto.getCashId()!=null){
//                return baseFinProductOpenRuleMapper.delete(dto)>0;
//            }
//        }
        if(StringUtil.isNotEmpty(dto.getCashId())){
            baseFinProductOpenRuleMapper.delete(dto);
            return true;
        }
        return false;
    }

    /**
     * 开放规则组件新增
     */
    @Override
    public boolean insert(List<FinOpenRule> entityList) {
        int i = 0;
        if(entityList!=null){
            for(FinOpenRule entity:entityList){
                i+=finOpenRuleMapper.insert(entity);
            }
            return i==entityList.size();
        }
        return false;
    }

    /**
     * 开放规则组件查找
     */
    @Override
    public List<FinOpenRule> search(ValBaseFinProductOpenRuleDto dto) {
        List<FinOpenRule> dtoList = new ArrayList<FinOpenRule>();
        if(dto.getCashId()!=null){
            dtoList.addAll(baseFinProductOpenRuleMapper.search(dto));
        }
        return dtoList;
    }

}
