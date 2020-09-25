/*
 * 文件名：FinInterestFactorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.PaFinCashCalcRuleMapper;
import com.joyintech.tams.common.base.entity.PaFinCashCalcRule;
import com.joyintech.service.asset.common.dao.FinInterestFactorMapper;
import com.joyintech.service.asset.common.dto.FinInterestFactorDto;

/**
 * 计息要素
 * @author zhangnian
 * @version 1.0 2018年9月26日
 * @see FinInterestFactorService
 * @since 1.0
 */
@Service
public class FinInterestFactorService {

    @Autowired
    private FinInterestFactorMapper finInterestFactorMapper;
    
    @Autowired
    private PaFinCashCalcRuleMapper paFinCashCalcRuleMapper;
    
    public FinInterestFactorDto getFinInterestFactorByProductId(String finprodId,String vdate,String cashType){
        return finInterestFactorMapper.getFinInterestFactorByProductId(finprodId,vdate,cashType);
    }
    
    /**
     * 插入计算规则扩展表--单独针对平安
     * <功能详细描述>
     * @param faFinCashCalcRule
     * @see
     * @since 1.0
     */
    public void insertPaFinCashCalcRule(PaFinCashCalcRule faFinCashCalcRule){
         paFinCashCalcRuleMapper.insert(faFinCashCalcRule);
    }
    
    /**
     *
     * 删除计算规则扩展表--单独针对平安
     * <功能详细描述>
     * @param faFinCashCalcRule
     * @see
     * @since 1.0
     */
    public void deletePaFinCashCalcRule(PaFinCashCalcRule faFinCashCalcRule){
        paFinCashCalcRuleMapper.deleteByPrimaryKey(faFinCashCalcRule);
   }
}
