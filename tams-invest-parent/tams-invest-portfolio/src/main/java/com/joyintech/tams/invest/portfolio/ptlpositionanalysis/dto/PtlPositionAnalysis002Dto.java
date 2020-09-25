/*
 * 文件名：PtlPositionAnalysis002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanghb
 * 修改时间：2019年2月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlpositionanalysis.dto;

import java.math.BigDecimal;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 资产配置汇总列表dto
 * @author wanghb
 * @version 1.0 2019年2月11日
 * @see PtlPositionAnalysis002Dto
 * @since 1.0
 */
public class PtlPositionAnalysis002Dto{
    /**
     * 资产类型
     */
    private String finprodType2;
    /**
     * 市值
     */
    private BigDecimal marketValue;

    /**
     * 成本
     */
    private BigDecimal dscCostAmt;

    /**
     * 应计利息
     */
    private BigDecimal tdyIntincexp;
    /**
     * 规模占比
     */
    private BigDecimal amtRation;
    
    /**
     * 获取 finprodType2
     * @return finprodType2.
     */
    public String getFinprodType2() {
        return finprodType2;
    }

    
    /**
     * 设置 finprodType2
     * @param finprodType2 finprodType2
     */
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }

    
    /**
     * 获取 marketValue
     * @return marketValue.
     */
    public BigDecimal getMarketValue() {
        return marketValue;
    }

    
    /**
     * 设置 marketValue
     * @param marketValue marketValue
     */
    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }

    
    /**
     * 获取 dscCostAmt
     * @return dscCostAmt.
     */
    public BigDecimal getDscCostAmt() {
        return dscCostAmt;
    }

    
    /**
     * 设置 dscCostAmt
     * @param dscCostAmt dscCostAmt
     */
    public void setDscCostAmt(BigDecimal dscCostAmt) {
        this.dscCostAmt = dscCostAmt;
    }

    
    /**
     * 获取 tdyIntincexp
     * @return tdyIntincexp.
     */
    public BigDecimal getTdyIntincexp() {
        return tdyIntincexp;
    }

    
    /**
     * 设置 tdyIntincexp
     * @param tdyIntincexp tdyIntincexp
     */
    public void setTdyIntincexp(BigDecimal tdyIntincexp) {
        this.tdyIntincexp = tdyIntincexp;
    }

    
    /**
     * 获取 amtRation
     * @return amtRation.
     */
    public BigDecimal getAmtRation() {
        return amtRation;
    }

    
    /**
     * 设置 amtRation
     * @param amtRation amtRation
     */
    public void setAmtRation(BigDecimal amtRation) {
        this.amtRation = amtRation;
    }
    /**
     * 获取 finprodType2Name
     * @return finprodType2Name.
     */
    public String getFinprodType2Name() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.平安资产类型, this.getFinprodType2()))?
            SysDataUtil.getNameFromDataDict(DictCode.平安资产类型, this.getFinprodType2()):this.getFinprodType2();
    }
}
