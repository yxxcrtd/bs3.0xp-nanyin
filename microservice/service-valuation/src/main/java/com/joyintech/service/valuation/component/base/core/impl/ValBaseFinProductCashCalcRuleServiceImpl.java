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
import com.joyintech.service.valuation.component.base.core.ValBaseFinProductCashCalcRuleService;
import com.joyintech.service.valuation.component.base.core.dto.ValBaseFinProductCashCalcRuleDto;
import com.joyintech.service.valuation.component.base.core.impl.dao.ValBaseFinProductCashCalcRuleMapper;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;

/**
 * 现金流计算规则
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年8月29日
 * @see ValBaseFinProductCashCalcRuleServiceImpl
 * @since 1.0
 */
@Service
public class ValBaseFinProductCashCalcRuleServiceImpl extends BaseService<FinCashCalcRule> implements ValBaseFinProductCashCalcRuleService{
    /**
     * 
     */
    @Autowired
    private ValBaseFinProductCashCalcRuleMapper baseFinProductCashCalcRuleMapper;
    
    /**
     * 现金流计划新建
     */
    @Override
    public boolean insert(List<FinCashCalcRule> finCashCalcRuleList) {
        int i =0;
        if(finCashCalcRuleList!=null){
            for(FinCashCalcRule finCashCalcRule:finCashCalcRuleList){
                baseFinProductCashCalcRuleMapper.insert(finCashCalcRule);
                i++;
            }
        }
        return i>0 && i==finCashCalcRuleList.size()?true:false;
    }
    /**
     * 现金流计算查询
     */
    @Override
    public List<FinCashCalcRule> selectByBaseFinProductCashCalcRuleDto(ValBaseFinProductCashCalcRuleDto baseFinProductCashCalcRuleDto) {
        
        return baseFinProductCashCalcRuleMapper.selectByBaseFinProductCashCalcRuleDto(baseFinProductCashCalcRuleDto);
    }
    /**
     * 现金流计算计划删除
     */
    @Override
    public boolean deleteByBaseFinProductCashCalcRuleDto(ValBaseFinProductCashCalcRuleDto baseFinProductCashCalcRuleDto) {
        return baseFinProductCashCalcRuleMapper.deleteByBaseFinProductCashCalcRuleDto(baseFinProductCashCalcRuleDto)>0?true:false;
    }
    /**
     * 现金流计算计划修改
     */
    @Override
    public boolean updateBaseFinProductCashCalcRuleDto(ValBaseFinProductCashCalcRuleDto baseFinProductCashCalcRuleDto) {
        int i =0;
        if(baseFinProductCashCalcRuleDto.getFinCashCalcRuleList().size()>0){
            baseFinProductCashCalcRuleMapper.deleteByBaseFinProductCashCalcRuleDto(baseFinProductCashCalcRuleDto);
            for(FinCashCalcRule baseFinProductCashCalcRule:baseFinProductCashCalcRuleDto.getFinCashCalcRuleList()){
                baseFinProductCashCalcRuleMapper.insert(baseFinProductCashCalcRule);
                i++;
            }
        }
        return i>0 && i==baseFinProductCashCalcRuleDto.getFinCashCalcRuleList().size()?true:false; 
    }
    
    

}
