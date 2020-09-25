/*
 * 文件名：BaseFinAnapleroticCalcRuleServiceImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：LiJun
 * 修改时间：2018年4月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.core.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.FinClawbackCalcRuleMapper;
import com.joyintech.tams.common.base.entity.FinClawbackCalcRule;
import com.joyintech.tams.common.base.entity.FinClawbackCalcRuleKey;
import com.joyintech.tams.component.base.core.BaseFinAnapleroticCalcRuleService;

/**
 * 业绩回补计算规则
 * 〈功能详细描述〉
 * @author LiJun
 * @version 1.0 2019年4月24日
 * @see BaseFinAnapleroticCalcRuleServiceImpl
 * @since 1.0
 */
@Service
public class BaseFinAnapleroticCalcRuleServiceImpl implements BaseFinAnapleroticCalcRuleService{
    /**
     * 业绩回补计算规则mapper
     */
    @Autowired
    private FinClawbackCalcRuleMapper finClawbackCalcRuleMapper;
  
    /**
     * 业绩回补计算规则删除
     */
    @Override
    public boolean delete(FinClawbackCalcRule dto) {
        if(StringUtil.isNotEmpty(dto.getCashId())){
            finClawbackCalcRuleMapper.deleteByPrimaryKey(dto);
            return true;
        }
        return false;
    }

    /**
     * 业绩回补计算规则新增
     */
    @Override
    public boolean insert(FinClawbackCalcRule entity) {
        if(entity!=null){
            finClawbackCalcRuleMapper.insert(entity);
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FinClawbackCalcRule> selectListByPrimaryKey(FinClawbackCalcRuleKey key) {
        // TODO Auto-generated method stub
        if(StringUtil.isNotEmpty(key.getCashId())){
            return finClawbackCalcRuleMapper.selectListByPrimaryKey(key);
        }
        return null;
    }

    
}
