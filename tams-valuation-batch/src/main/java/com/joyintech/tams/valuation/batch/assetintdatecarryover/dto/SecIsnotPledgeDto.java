/*
 * 文件名：BookGenerateResult.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2017年1月3日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.batch.assetintdatecarryover.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 记账凭证生成结果
 * 
 * @author chengzhong
 * @version 1.0 2017年1月3日
 * @see SecIsnotPledgeDto
 * @since 1.0
 */
public class SecIsnotPledgeDto {
    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 质押金融产品代码
     */
    private String pledgeFinprodId;

    /**
     * 质押金融产品代码
     */
    private String pledgeFinprodType;
    
    /**
     * 质押数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pledgeNumber;
    

    /**
     * 交易场所
     */
    private String tradeMarket;


    
    /**
     * 获取 finprodId
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }


    
    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }


    
    /**
     * 获取 finprodType
     * @return finprodType.
     */
    public String getFinprodType() {
        return finprodType;
    }


    
    /**
     * 设置 finprodType
     * @param finprodType finprodType
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }


    
    /**
     * 获取 branch
     * @return branch.
     */
    public Long getBranch() {
        return branch;
    }


    
    /**
     * 设置 branch
     * @param branch branch
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }


    
    /**
     * 获取 pledgeFinprodId
     * @return pledgeFinprodId.
     */
    public String getPledgeFinprodId() {
        return pledgeFinprodId;
    }


    
    /**
     * 设置 pledgeFinprodId
     * @param pledgeFinprodId pledgeFinprodId
     */
    public void setPledgeFinprodId(String pledgeFinprodId) {
        this.pledgeFinprodId = pledgeFinprodId;
    }


    
    /**
     * 获取 pledgeNumber
     * @return pledgeNumber.
     */
    public BigDecimal getPledgeNumber() {
        return pledgeNumber;
    }


    
    /**
     * 设置 pledgeNumber
     * @param pledgeNumber pledgeNumber
     */
    public void setPledgeNumber(BigDecimal pledgeNumber) {
        this.pledgeNumber = pledgeNumber;
    }


    
    /**
     * 获取 tradeMarket
     * @return tradeMarket.
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    
    /**
     * 设置 tradeMarket
     * @param tradeMarket tradeMarket
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }


    /**
     * 获取 质押金融产品代码
     *
     * @return 质押金融产品代码
     */
    public String getPledgeFinprodType() {
        return pledgeFinprodType;
    }

    /**
     * 设置 质押金融产品代码
     *
     * @param pledgeFinprodType 质押金融产品代码
     */
    public void setPledgeFinprodType(String pledgeFinprodType) {
        this.pledgeFinprodType = pledgeFinprodType;
    }
    
}
