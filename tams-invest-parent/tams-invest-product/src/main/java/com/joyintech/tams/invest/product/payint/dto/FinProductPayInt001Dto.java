/*
 * 文件名：FinProductPayInt001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.payint.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;

/**
 * 产品中间付息查询结果dto
 *
 * @author etliu
 * @version 1.0 2018年12月29日
 * @see FinProductPayInt001Dto
 * @since 1.0
 */
public class FinProductPayInt001Dto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -6490393343772328265L;

    /**
     * 有效状态
     */
    private String validStatus;

    /**
     * 有效状态
     */
    private String validStatusText;

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
     * 销售部门
     */
    private String saleDepartmentText;

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
     * 支付日
     */
    private Date payDate;

    /**
     * 操作类型
     */
    private String operateType;
    
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
    @PrimaryKey(PrimaryKey.Field.PK_2)
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
    public String getPayintStartDateStr(){
        return DateUtil.format(payintStartDate);
    }
    
    public String getPayintEndDateStr(){
        return DateUtil.format(payintEndDate);
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
     * 转换币种
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getCcyStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, this.getCcy());
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
    @PrimaryKey(PrimaryKey.Field.PK_1)
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

    /**
     * 获取 validStatusText
     *
     * @return validStatusText.
     */
    public String getValidStatusText() {
        validStatusText = SysDataUtil.getNameFromDataDict(DictCode.画面数据状态, validStatus);
        return validStatusText;
    }

    /**
     * 设置 validStatusText
     *
     * @param validStatusText validStatusText
     */
    public void setValidStatusText(String validStatusText) {
        this.validStatusText = validStatusText;
    }

    /**
     * 获取 saleDepartmentText
     *
     * @return saleDepartmentText.
     */
    public String getSaleDepartmentText() {
        saleDepartmentText = SysDataUtil.getNameFromDataDict(DictCode.销售部门, saleDepartment);
        return saleDepartmentText;
    }

    /**
     * 设置 saleDepartmentText
     *
     * @param saleDepartmentText saleDepartmentText
     */
    public void setSaleDepartmentText(String saleDepartmentText) {
        this.saleDepartmentText = saleDepartmentText;
    }

    
    /**
     * 获取 branch
     * @return branch.
     */
    public String getBranch() {
        return branch;
    }

    
    /**
     * 设置 branch
     * @param branch branch
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * 获取 payDate
     * @return payDate.
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * 设置 payDate
     * @param payDate payDate
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }
    
    public String getPayDateStr(){
        return DateUtil.format(payDate);
    }
    /**
     * 获取 operateType
     * 
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     * 
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
}
