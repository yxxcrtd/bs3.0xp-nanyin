package com.joyintech.tams.svreport.pbc.classic.prdinfoendsubdat.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.svreport.pbc.common.annotation.ExcelCellNumber;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:产品终止信息excel计解析实体
 * @author:  Jason.Jin
 * @创建时间: 2021年7月29日14:30:17
 */
public class PbcPrdBaseinfoEndSubDto  {
    //序号
    @ExcelCellNumber(cellNumber = 0)
    private String number;
    //理财产品代码
    @ExcelCellNumber(cellNumber = 1)
    private String prodId;
    //产品实际终止日期
    @ExcelCellNumber(cellNumber = 2)
    private Date mdate;
    //兑付客户收益率
    @ExcelCellNumber(cellNumber = 3)
    private BigDecimal cusYearYield;
    //币种代码
    @ExcelCellNumber(cellNumber = 4)
    private String ccy;
    //兑付客户收益
    @ExcelCellNumber(cellNumber = 5)
    private BigDecimal customProfit;
    //兑付客户收益折人民币
    @ExcelCellNumber(cellNumber = 6)
    private BigDecimal customProfitCny;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public BigDecimal getCusYearYield() {
        return cusYearYield;
    }

    public void setCusYearYield(BigDecimal cusYearYield) {
        this.cusYearYield = cusYearYield;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public BigDecimal getCustomProfit() {
        return customProfit;
    }

    public void setCustomProfit(BigDecimal customProfit) {
        this.customProfit = customProfit;
    }

    public BigDecimal getCustomProfitCny() {
        return customProfitCny;
    }

    public void setCustomProfitCny(BigDecimal customProfitCny) {
        this.customProfitCny = customProfitCny;
    }
}