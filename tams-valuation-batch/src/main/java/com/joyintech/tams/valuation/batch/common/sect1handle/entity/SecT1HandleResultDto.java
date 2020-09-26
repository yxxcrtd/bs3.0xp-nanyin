/*
 * 文件名：DealTypesFeeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年6月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.common.sect1handle.entity;

import java.math.BigDecimal;


/**
 * 
 * 交易金额计算参数
 * 
 * @author chengzhong
 * @version 1.0 2018年6月7日
 * @see SecT1HandleResultDto
 * @since 1.0
 */
public class SecT1HandleResultDto {
    
    /**
     * 数量余额
     */
    private BigDecimal numBalance;
    
    /**
     * 成本类科目余额
     */
    private BigDecimal cbSubjectBalance;
    
    /**
     * 应计利息科目余额
     */
    private BigDecimal yjlxSubjectBalance;                               
     
    /**
     * 公允价值变动类科目余额
     */
    private BigDecimal gyjzbdSubjectBalance;  
                                    
    /**
     * 溢折价类科目余额 
     */
    private BigDecimal zyjSubjectBalance;

    
    /**
     * 获取 数量余额
     * @return numBalance.
     */
    public BigDecimal getNumBalance() {
        return numBalance;
    }


    
    /**
     * 设置 数量余额
     * @param numBalance 数量余额
     */
    public void setNumBalance(BigDecimal numBalance) {
        this.numBalance = numBalance;
    }


    
    /**
     * 获取 成本类科目余额
     * @return cbSubjectBalance.
     */
    public BigDecimal getCbSubjectBalance() {
        return cbSubjectBalance;
    }


    
    /**
     * 设置 成本类科目余额
     * @param cbSubjectBalance 成本类科目余额
     */
    public void setCbSubjectBalance(BigDecimal cbSubjectBalance) {
        this.cbSubjectBalance = cbSubjectBalance;
    }


    
    /**
     * 获取 应计利息科目余额
     * @return yjlxSubjectBalance.
     */
    public BigDecimal getYjlxSubjectBalance() {
        return yjlxSubjectBalance;
    }


    
    /**
     * 设置 应计利息科目余额
     * @param yjlxSubjectBalance 应计利息科目余额
     */
    public void setYjlxSubjectBalance(BigDecimal yjlxSubjectBalance) {
        this.yjlxSubjectBalance = yjlxSubjectBalance;
    }


    
    /**
     * 获取 公允价值变动类科目余额
     * @return gyjzbdSubjectBalance.
     */
    public BigDecimal getGyjzbdSubjectBalance() {
        return gyjzbdSubjectBalance;
    }


    
    /**
     * 设置 公允价值变动类科目余额
     * @param gyjzbdSubjectBalance 公允价值变动类科目余额
     */
    public void setGyjzbdSubjectBalance(BigDecimal gyjzbdSubjectBalance) {
        this.gyjzbdSubjectBalance = gyjzbdSubjectBalance;
    }


    
    /**
     * 获取 溢折价类科目余额 
     * @return zyjSubjectBalance.
     */
    public BigDecimal getZyjSubjectBalance() {
        return zyjSubjectBalance;
    }


    
    /**
     * 设置 溢折价类科目余额 
     * @param zyjSubjectBalance 溢折价类科目余额 
     */
    public void setZyjSubjectBalance(BigDecimal zyjSubjectBalance) {
        this.zyjSubjectBalance = zyjSubjectBalance;
    }


    

}
