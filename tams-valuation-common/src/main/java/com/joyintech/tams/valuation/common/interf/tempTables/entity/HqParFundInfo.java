package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

public class HqParFundInfo extends BaseEntity {

    /**
     * 证券代码
     */
    private String zqdm;

    /**
     * 单位资产净值
     */
    private BigDecimal netValue;

    /**
     * 公告日
     */
    private Date dataDate;
    
    /**
     * 估值日期
     */
    private Date valDate;
    
    /**
     * 累计净值
     */
    private BigDecimal totalNet;

    /**
     * 取得 证券代码
     * 
     * @return 证券代码
     */
    public String getZqdm() {
        return zqdm;
    }

    /**
     * 设置 证券代码
     * 
     * @param zqdm 证券代码
     */
    public void setZqdm(String zqdm) {
        this.zqdm = zqdm;
    }

    /**
     * 取得 单位资产净值
     * 
     * @return 单位资产净值
     */
    public BigDecimal getNetValue() {
        return netValue;
    }

    /**
     * 设置 单位资产净值
     * 
     * @param netValue 单位资产净值
     */
    public void setNetValue(BigDecimal netValue) {
        this.netValue = netValue;
    }

    
    /**
     * 获取 dataDate
     * @return dataDate.
     */
    public Date getDataDate() {
        return dataDate;
    }

    
    /**
     * 设置 dataDate
     * @param dataDate dataDate
     */
    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    
    /**
     * 获取 valDate
     * @return valDate.
     */
    public Date getValDate() {
        return valDate;
    }

    
    /**
     * 设置 valDate
     * @param valDate valDate
     */
    public void setValDate(Date valDate) {
        this.valDate = valDate;
    }

	public BigDecimal getTotalNet() {
		return totalNet;
	}

	public void setTotalNet(BigDecimal totalNet) {
		this.totalNet = totalNet;
	}


}