/*
 * 文件名：ValBondRealRate001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2019年1月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.assets.bondrate.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author HuangJian
 * @version 1.0 2019年1月19日
 * @see ValBondRealRate001Dto
 * @since 1.0
 */
public class ValBondRealRate001Dto extends BaseEntity{
    
    
    
    /**
     * 序列号
     */
    private static final long serialVersionUID = 1L;
    

    /**
     * 账套代码
     */
    private String booksetId;
    /**
     * 账套名称
     */
    private String booksetName;
    /**
     * 债券代码
     */
    private String bondId;
    
    /**
     * 债券名称
     */
    private String bondName;
    /**
     * 会计日期
     */
    private String vdate;
    /**
     * 交易场所
     */
    private String market;
    
    /**
     *  折溢价余额
     */
    private BigDecimal changeAmt;
    
    /**
     * 实际日利率
     */
    private BigDecimal realRate;

    
    
    
    /**
     * 摊余净价 add by yanya 20190917
     */
    private BigDecimal tdyNPAmt;
    
    /**
     * 摊余全价 add by yanya 20190917
     */
    private BigDecimal tdyFPAmt;
    
    /**
     * 摊余成本 add by yanya 20190917
     */
    private BigDecimal tdyCostAmt;
    
    /**
     * 获取 tdyCostAmt
     * @return tdyCostAmt.
     */
    public BigDecimal getTdyCostAmt() {
        return tdyCostAmt;
    }


    /**
     * 设置 tdyCostAmt
     */
    public void setTdyCostAmt(BigDecimal tdyCostAmt) {
        this.tdyCostAmt = tdyCostAmt;
    }


    /**
     * 获取 tdyNPAmt
     * @return tdyNPAmt.
     */
    public BigDecimal getTdyNPAmt() {
        return tdyNPAmt;
    }


    /**
     * 设置 tdyNPAmt
     */
    public void setTdyNPAmt(BigDecimal tdyNPAmt) {
        this.tdyNPAmt = tdyNPAmt;
    }


    /**
     * 获取 tdyFPAmt
     * @return tdyFPAmt.
     */
    public BigDecimal getTdyFPAmt() {
        return tdyFPAmt;
    }


    /**
     * 设置 tdyFPAmt
     */
    public void setTdyFPAmt(BigDecimal tdyFPAmt) {
        this.tdyFPAmt = tdyFPAmt;
    }

    
    /**
     * 获取 booksetId
     * @return booksetId.
     */
    public String getBooksetId() {
        return booksetId;
    }

    
    /**
     * 设置 booksetId
     * @param booksetId booksetId
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    
    /**
     * 获取 booksetName
     * @return booksetName.
     */
    public String getBooksetName() {
        return booksetName;
    }

    
    /**
     * 设置 booksetName
     * @param booksetName booksetName
     */
    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName;
    }

    
    /**
     * 获取 bondId
     * @return bondId.
     */
    public String getBondId() {
        return bondId;
    }

    
    /**
     * 设置 bondId
     * @param bondId bondId
     */
    public void setBondId(String bondId) {
        this.bondId = bondId;
    }

    
    
    
    /**
     * 获取 bondName
     * @return bondName.
     */
    public String getBondName() {
        return bondName;
    }


    
    /**
     * 设置 bondName
     * @param bondName bondName
     */
    public void setBondName(String bondName) {
        this.bondName = bondName;
    }


    /**
     * 获取 vdate
     * @return vdate.
     */
    public String getVdate() {
        return vdate;
    }

    
    /**
     * 设置 vdate
     * @param vdate vdate
     */
    public void setVdate(String vdate) {
        this.vdate = vdate;
    }

    
    /**
     * 获取 market
     * @return market.
     */
    public String getMarket() {
        return market;
    }
    
    
    /**
     * 获取 market
     * @return market.
     */
    public String getMarketFt() {
        return SysDataUtil.getNameFromDataDict(DictCode.估值交易市场, market);
    }

    
    /**
     * 设置 market
     * @param market market
     */
    public void setMarket(String market) {
        this.market = market;
    }

    
    /**
     * 获取 changeAmt
     * @return changeAmt.
     */
    public BigDecimal getChangeAmt() {
        return changeAmt;
    }

    
    /**
     * 设置 changeAmt
     * @param changeAmt changeAmt
     */
    public void setChangeAmt(BigDecimal changeAmt) {
        this.changeAmt = changeAmt;
    }

    
    /**
     * 获取 realRate
     * @return realRate.
     */
    public BigDecimal getRealRate() {
        return realRate;
    }

    
    /**
     * 设置 realRate
     * @param realRate realRate
     */
    public void setRealRate(BigDecimal realRate) {
        this.realRate = realRate;
    }
    
    
    
    
    
    
    
    
    
}
