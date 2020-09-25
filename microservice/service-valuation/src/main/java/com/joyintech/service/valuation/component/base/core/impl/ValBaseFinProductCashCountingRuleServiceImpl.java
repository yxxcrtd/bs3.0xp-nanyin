/*
 * 文件名：CashFlowCalculateImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年8月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.component.base.core.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.service.valuation.component.base.core.ValBaseFinProductCashCountingRuleService;
import com.joyintech.service.valuation.component.base.core.dto.ValBaseFinProductCashCountingRuleDto;
import com.joyintech.service.valuation.component.base.core.impl.dao.ValBaseFinProductCashCountingRuleMapper;
import com.joyintech.tams.common.base.entity.FinFloatCountingRule;

/**
 * 现金流计算规则
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年8月29日
 * @see ValBaseFinProductCashCountingRuleServiceImpl
 * @since 1.0
 */
@Service
public class ValBaseFinProductCashCountingRuleServiceImpl extends BaseService<FinFloatCountingRule> implements ValBaseFinProductCashCountingRuleService{
    /**
     * 
     */
    @Autowired
    private ValBaseFinProductCashCountingRuleMapper baseFinProductCashCountingRuleMapper;
    
    /**
     * 现金流计划新建
     */
    @Override
    public boolean insert(List<FinFloatCountingRule> finCashCountingRuleList) {
        int i =0;
        if(finCashCountingRuleList!=null){
            for(FinFloatCountingRule finFloatCountingRule:finCashCountingRuleList){
                baseFinProductCashCountingRuleMapper.insert(finFloatCountingRule);
                i++;
            }
        }
        return i>0 && i==finCashCountingRuleList.size()?true:false;
    }
    /**
     * 现金流计算查询
     */
    @Override
    public List<FinFloatCountingRule> selectByBaseFinProductCashCountingRuleDto(ValBaseFinProductCashCountingRuleDto baseFinProductCashCountingRuleDto) {
        
        return baseFinProductCashCountingRuleMapper.selectByBaseFinProductCashCountingRuleDto(baseFinProductCashCountingRuleDto);
    }
    /**
     * 现金流计算计划删除
     */
    @Override
    public boolean deleteByBaseFinProductCashCountingRuleDto(ValBaseFinProductCashCountingRuleDto baseFinProductCashCountingRuleDto) {
        return baseFinProductCashCountingRuleMapper.deleteByBaseFinProductCashCountingRuleDto(baseFinProductCashCountingRuleDto)>0?true:false;
    }
    /**
     * 现金流计算计划修改
     */
    @Override
    public boolean updateBaseFinProductCashCountingRuleDto(ValBaseFinProductCashCountingRuleDto baseFinProductCashCountingRuleDto) {
        int i =0;
        if(baseFinProductCashCountingRuleDto.getFinCashCountingRuleList().size()>0){
            baseFinProductCashCountingRuleMapper.deleteByBaseFinProductCashCountingRuleDto(baseFinProductCashCountingRuleDto);
            for(FinFloatCountingRule baseFinProductCashCountingRule:baseFinProductCashCountingRuleDto.getFinCashCountingRuleList()){
                baseFinProductCashCountingRuleMapper.insert(baseFinProductCashCountingRule);
                i++;
            }
        }
        return i>0 && i==baseFinProductCashCountingRuleDto.getFinCashCountingRuleList().size()?true:false; 
    }
    
    @Override
	public boolean insertFinFloatCountingRule(FinFloatCountingRule finCashCountingRule) {
	      if(finCashCountingRule!=null){
	           baseFinProductCashCountingRuleMapper.insert(finCashCountingRule);
	           return true;
	        }
	        return false;
	}

}
