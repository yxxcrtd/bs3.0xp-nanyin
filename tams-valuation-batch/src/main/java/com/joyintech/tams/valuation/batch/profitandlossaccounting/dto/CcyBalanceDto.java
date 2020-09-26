/*
 * 文件名：CcyBalanceDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2019年1月14日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.profitandlossaccounting.dto;

import java.math.BigDecimal;

/**
 * 币种余额Dto
 * @author weipeng
 * @version 1.0 2019年1月14日
 * @see CcyBalanceDto
 * @since 1.0
 */
public class CcyBalanceDto {
    /**
     * 已实现损益贷方科目余额 = 0
     */
    private BigDecimal realizedBalanceC = BigDecimal.ZERO;
    /**
     * 已实现损益借方科目余额 = 0
     */
    private BigDecimal realizedBalanceD = BigDecimal.ZERO;

    /**
     * 已实现损益贷方科目余额 = 0
     */
    private BigDecimal realizedBalanceC_YB = BigDecimal.ZERO;
    /**
     * 已实现损益借方科目余额 = 0
     */
    private BigDecimal realizedBalanceD_YB = BigDecimal.ZERO;

    /**
     * 未实现损益贷方科目余额  = 0
     */
    private BigDecimal unRealizedBalanceC = BigDecimal.ZERO;

    /**
     * 未实现损益借方科目余额 = 0
     */
    private BigDecimal unRealizedBalanceD = BigDecimal.ZERO;

    /** ADD BY XR  for tishkbs640 货币型产品结转原币余额为0 start
     * 未实现损益原币贷方科目余额  = 0
     */
    private BigDecimal unRealizedBalanceC_YB = BigDecimal.ZERO;

    /**
     * 未实现损益原币借方科目余额 = 0
     */
    private BigDecimal unRealizedBalanceD_YB = BigDecimal.ZERO;
    //ADD BY XR  for tishkbs640 货币型产品结转原币余额为0 END
    /**
     * 获取 已实现损益贷方科目余额
     * @return 已实现损益贷方科目余额
     */
    public BigDecimal getRealizedBalanceC() {
        return realizedBalanceC;
    }
    
    /**
     * 设置 已实现损益贷方科目余额
     * @param realizedBalanceC 已实现损益贷方科目余额
     */
    public void setRealizedBalanceC(BigDecimal realizedBalanceC) {
        this.realizedBalanceC = realizedBalanceC;
    }
    
    /**
     * 获取 已实现损益借方科目余额
     * @return 已实现损益借方科目余额
     */
    public BigDecimal getRealizedBalanceD() {
        return realizedBalanceD;
    }
    
    /**
     * 设置 已实现损益借方科目余额
     * @param realizedBalanceD 已实现损益借方科目余额
     */
    public void setRealizedBalanceD(BigDecimal realizedBalanceD) {
        this.realizedBalanceD = realizedBalanceD;
    }
    
    /**
     * 获取 未实现损益贷方科目余额 
     * @return 未实现损益贷方科目余额 
     */
    public BigDecimal getUnRealizedBalanceC() {
        return unRealizedBalanceC;
    }
    
    /**
     * 设置 未实现损益贷方科目余额 
     * @param unRealizedBalanceC 未实现损益贷方科目余额 
     */
    public void setUnRealizedBalanceC(BigDecimal unRealizedBalanceC) {
        this.unRealizedBalanceC = unRealizedBalanceC;
    }
    
    /**
     * 获取 未实现损益借方科目余额
     * @return 未实现损益借方科目余额
     */
    public BigDecimal getUnRealizedBalanceD() {
        return unRealizedBalanceD;
    }
    
    /**
     * 设置 未实现损益借方科目余额
     * @param unRealizedBalanceD 未实现损益借方科目余额
     */
    public void setUnRealizedBalanceD(BigDecimal unRealizedBalanceD) {
        this.unRealizedBalanceD = unRealizedBalanceD;
    }

    public BigDecimal getUnRealizedBalanceC_YB() {
        return unRealizedBalanceC_YB;
    }

    public void setUnRealizedBalanceC_YB(BigDecimal unRealizedBalanceC_YB) {
        this.unRealizedBalanceC_YB = unRealizedBalanceC_YB;
    }

    public BigDecimal getUnRealizedBalanceD_YB() {
        return unRealizedBalanceD_YB;
    }

    public void setUnRealizedBalanceD_YB(BigDecimal unRealizedBalanceD_YB) {
        this.unRealizedBalanceD_YB = unRealizedBalanceD_YB;
    }

    public BigDecimal getRealizedBalanceC_YB() {
        return realizedBalanceC_YB;
    }

    public void setRealizedBalanceC_YB(BigDecimal realizedBalanceC_YB) {
        this.realizedBalanceC_YB = realizedBalanceC_YB;
    }

    public BigDecimal getRealizedBalanceD_YB() {
        return realizedBalanceD_YB;
    }

    public void setRealizedBalanceD_YB(BigDecimal realizedBalanceD_YB) {
        this.realizedBalanceD_YB = realizedBalanceD_YB;
    }
}
