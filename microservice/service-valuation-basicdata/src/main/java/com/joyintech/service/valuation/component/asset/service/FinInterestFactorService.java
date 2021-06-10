package com.joyintech.service.valuation.component.asset.service;

import com.joyintech.service.valuation.component.asset.dao.FinInterestFactorMapper;
import com.joyintech.service.valuation.component.asset.dto.FinInterestFactorDto;
import com.joyintech.tams.common.base.dao.PaFinCashCalcRuleMapper;
import com.joyintech.tams.common.base.entity.PaFinCashCalcRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName FinInterestFactorService
 * @Description: 计息service
 * @Author yanya
 * @Date 2021/3/4 0004
 * @Version V1.0
 **/
@Service(value="serviceFinInterestFactorService")
public class FinInterestFactorService {

    @Autowired
    private FinInterestFactorMapper finInterestFactorMapper;

//    @Autowired
//    private PaFinCashCalcRuleMapper paFinCashCalcRuleMapper;

    /**
     * 根据相关参数查询计息要素信息
     * @param finprodId
     * @param vdate
     * @param cashType
     * @return
     */
    public FinInterestFactorDto getFinInterestFactorByProductId(String finprodId,
                                                                String vdate,
                                                                String cashType){
        return finInterestFactorMapper.getFinInterestFactorByProductId(finprodId,vdate,cashType);
    }

//    /**
//     * 插入计算规则扩展表--单独针对平安
//     * <功能详细描述>
//     * @param faFinCashCalcRule
//     * @see
//     * @since 1.0
//     */
//    public void insertPaFinCashCalcRule(PaFinCashCalcRule faFinCashCalcRule){
//        paFinCashCalcRuleMapper.insert(faFinCashCalcRule);
//    }
//
//    /**
//     *
//     * 删除计算规则扩展表--单独针对平安
//     * <功能详细描述>
//     * @param faFinCashCalcRule
//     * @see
//     * @since 1.0
//     */
//    public void deletePaFinCashCalcRule(PaFinCashCalcRule faFinCashCalcRule){
//        paFinCashCalcRuleMapper.deleteByPrimaryKey(faFinCashCalcRule);
//    }
}
