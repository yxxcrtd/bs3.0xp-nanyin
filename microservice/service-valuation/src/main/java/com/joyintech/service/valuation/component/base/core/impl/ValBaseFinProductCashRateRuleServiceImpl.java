/*
 * 文件名：BaseFinProductCashRateRuleServiceImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年9月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.component.base.core.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.service.valuation.component.base.core.ValBaseFinProductCashRateRuleService;
import com.joyintech.service.valuation.component.base.core.dto.ValBaseFinProductCashRateRuleDto;
import com.joyintech.service.valuation.component.base.core.impl.dao.ValBaseFinProductCashRateRuleMapper;
import com.joyintech.tams.common.base.entity.FinRateInfo;


/**
 * 现金流利率规则信息组件
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年9月2日
 * @see ValBaseFinProductCashRateRuleServiceImpl
 * @since 1.0
 */
@Service
public class ValBaseFinProductCashRateRuleServiceImpl implements ValBaseFinProductCashRateRuleService{
    /**
     * 
     */
    @Autowired
    private ValBaseFinProductCashRateRuleMapper baseFinProductCashRateRuleMapper;
    
    /**
     * 现金流利率信息新建
     */
    @Override
    public boolean insert(List<FinRateInfo> finRateInfoList) {
        int i=0;
        if(finRateInfoList!=null && finRateInfoList.size()>0){
            for(FinRateInfo baseFinProductCashRateRuleDto:finRateInfoList){
                baseFinProductCashRateRuleMapper.insert(baseFinProductCashRateRuleDto);
                i++;
            }
        }
        return i>0 && i==finRateInfoList.size()?true:false;
    }

    /**
     * 现金流利率信息查询
     */
    @Override
    public List<FinRateInfo> selectByBaseFinProductCashRateRuleDto(ValBaseFinProductCashRateRuleDto baseFinProductCashRateRuleDto) {
        return baseFinProductCashRateRuleMapper.selectByBaseFinProductCashRateRuleDto(baseFinProductCashRateRuleDto);
    }

    /**
     * 现金流利率信息删除
     */
    @Override
    public boolean deleteByBaseFinProductCashRateRuleDto(ValBaseFinProductCashRateRuleDto baseFinProductCashRateRuleDto) {
        
        return baseFinProductCashRateRuleMapper.deleteByBaseFinProductCashRateRuleDto(baseFinProductCashRateRuleDto)>0?true:false;
    }

    /**
     * 现金流利率信息修改
     */
    @Override
    public boolean updateBaseFinProductCashRateRuleDto(ValBaseFinProductCashRateRuleDto baseFinProductCashRateRuleDto) {
        int i=0;
        baseFinProductCashRateRuleMapper.deleteByBaseFinProductCashRateRuleDto(baseFinProductCashRateRuleDto);
        if(baseFinProductCashRateRuleDto.getFinRateInfoList().size()>0){
            for(FinRateInfo baseFinProductCashRateRule :baseFinProductCashRateRuleDto.getFinRateInfoList()){
                baseFinProductCashRateRuleMapper.insert(baseFinProductCashRateRule);
                i++;
            }
        }
        return i>0 && i==baseFinProductCashRateRuleDto.getFinRateInfoList().size()?true:false;
    }
}
