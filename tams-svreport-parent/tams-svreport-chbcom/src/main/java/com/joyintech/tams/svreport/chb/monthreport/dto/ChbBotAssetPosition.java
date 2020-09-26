package com.joyintech.tams.svreport.chb.monthreport.dto;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.ChbBotAstPosition;


/**
 * 描述:CHB_ASSET_POSITION表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-01-26
 */
public class ChbBotAssetPosition extends ChbBotAstPosition{
    /**
     * 剩余金额
     */
    private BigDecimal accuamt;
    
    public BigDecimal getAccuamt() {
        return accuamt;
    }
    
    public void setAccuamt(BigDecimal accuamt) {
        this.accuamt = accuamt;
    }
    
}