/*
 * 文件名：FinProductPayIntDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.interf.bankname.sale.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 产品中间付息查询结果dto
 *
 * @author etliu
 * @version 1.0 2018年12月29日
 * @see FinProductPayIntDto
 * @since 1.0
 */
public class FinProductPayIntDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 5318916927188149151L;

    /**
     * 有效状态
     */
    private String validStatus;

    /**
     * 业务编号
     */
    private String bussId;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 产品简称
     */
    private String prodAbbr;

    /**
     * 销售部门
     */
    private String saleDepartment;

    /**
     * 本次付息起始日
     */
    private Date payintStartDate;

    /**
     * 本次付息结束日
     */
    private Date payintEndDate;

    /**
     * 本金金额
     */
    private BigDecimal prinAmt;

    /**
     * 应付客户收益
     */
    private BigDecimal theoryCusProfit;

    /**
     * 实付客户收益
     */
    private BigDecimal actCusProfit;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 是否分层
     */
    private String isLay;

    /**
     * 金融产品代码
     */
    private String finprodId;
    
    /**
     * 分支序号
     */
    private String branch;

    /**
     * 获取 validStatus
     *
     * @return validStatus.
     */
    public String getValidStatus() {
        return validStatus;
    }

    /**
     * 设置 validStatus
     *
     * @param validStatus validStatus
     */
    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }

    /**
     * 获取 bussId
     *
     * @return bussId.
     */
    public String getBussId() {
        return bussId;
    }

    /**
     * 设置 bussId
     *
     * @param bussId bussId
     */
    public void setBussId(String bussId) {
        this.bussId = bussId;
    }

    /**
     * 获取 portfolioId
     *
     * @return portfolioId.
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 portfolioId
     *
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 prodId
     *
     * @return prodId.
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 prodId
     *
     * @param prodId prodId
     */
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    /**
     * 获取 prodAbbr
     *
     * @return prodAbbr.
     */
    public String getProdAbbr() {
        return prodAbbr;
    }

    /**
     * 设置 prodAbbr
     *
     * @param prodAbbr prodAbbr
     */
    public void setProdAbbr(String prodAbbr) {
        this.prodAbbr = prodAbbr;
    }

    /**
     * 获取 saleDepartment
     *
     * @return saleDepartment.
     */
    public String getSaleDepartment() {
        return saleDepartment;
    }

    /**
     * 设置 saleDepartment
     *
     * @param saleDepartment saleDepartment
     */
    public void setSaleDepartment(String saleDepartment) {
        this.saleDepartment = saleDepartment;
    }

    /**
     * 获取 payintStartDate
     *
     * @return payintStartDate.
     */
    public Date getPayintStartDate() {
        return payintStartDate;
    }

    /**
     * 设置 payintStartDate
     *
     * @param payintStartDate payintStartDate
     */
    public void setPayintStartDate(Date payintStartDate) {
        this.payintStartDate = payintStartDate;
    }

    /**
     * 获取 payintEndDate
     *
     * @return payintEndDate.
     */
    public Date getPayintEndDate() {
        return payintEndDate;
    }

    /**
     * 设置 payintEndDate
     *
     * @param payintEndDate payintEndDate
     */
    public void setPayintEndDate(Date payintEndDate) {
        this.payintEndDate = payintEndDate;
    }

    /**
     * 获取 prinAmt
     *
     * @return prinAmt.
     */
    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    /**
     * 设置 prinAmt
     *
     * @param prinAmt prinAmt
     */
    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    /**
     * 获取 theoryCusProfit
     *
     * @return theoryCusProfit.
     */
    public BigDecimal getTheoryCusProfit() {
        return theoryCusProfit;
    }

    /**
     * 设置 theoryCusProfit
     *
     * @param theoryCusProfit theoryCusProfit
     */
    public void setTheoryCusProfit(BigDecimal theoryCusProfit) {
        this.theoryCusProfit = theoryCusProfit;
    }

    /**
     * 获取 actCusProfit
     *
     * @return actCusProfit.
     */
    public BigDecimal getActCusProfit() {
        return actCusProfit;
    }

    /**
     * 设置 actCusProfit
     *
     * @param actCusProfit actCusProfit
     */
    public void setActCusProfit(BigDecimal actCusProfit) {
        this.actCusProfit = actCusProfit;
    }

    /**
     * 获取 ccy
     *
     * @return ccy.
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 ccy
     *
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 isLay
     * @return isLay.
     */
    public String getIsLay() {
        return isLay;
    }

    /**
     * 设置 isLay
     * @param isLay isLay
     */
    public void setIsLay(String isLay) {
        this.isLay = isLay;
    }

    /**
     * 获取 finprodId
     *
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     *
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    
    public String getBranch() {
        return branch;
    }

    
    public void setBranch(String branch) {
        this.branch = branch;
    }
    
}
