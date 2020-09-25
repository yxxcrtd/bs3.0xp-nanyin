/*
 * 文件名：FinProductBuild002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.tcms002.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * json转换用
 * 
 * @author changwenbin
 * @version 1.0 2018年11月14日
 * @see FinProductBuild002Dto
 * @since 1.0
 */
public class FinProductBuild002Dto extends FinProduct {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -648566894348445884L;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 元素Id
     */
    private String elementId;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 画面Id
     */
    private String pageId;

    /**
     * 模板代码
     */
    private String tmplId;

    /**
     * 组合代码
     */
    private String ptlId;

    /**
     * 处理模式
     */
    private String dealMode;

    /**
     * 开放类型
     */
    private String openType;

    /**
     * 利率类型
     */
    private String calcType;

    /**
     * 是否周期型
     */
    private String isCycle;

    /**
     * 是否分层
     */
    private String isLay;

    /**
     * 分层类型
     */
    private String layType;

    /**
     * 产品收益标识
     */
    private String profitFlag;

    /**
     * 预期收益率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * 募集开始日
     */
    private Date firstSaleVdate;

    /**
     * 募集结束日
     */
    private Date firstSaleMdate;

    /**
     * 封闭期限
     */
    private Integer closeDays;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 销售部门
     */
    private String saleDepartment;

    /**
     * 申赎确认天数
     */
    private Integer purSpeed;

    /**
     * 投资性质
     */
    private String investNature;

    /**
     * 销售模式
     */
    private String saleMode;

    /**
     * {@linkplain}
     */
    @Valid
    private BussFinProductPayPlanDto bussFinProductPayPlanDto;

    /**
     * {@linkplain}
     */
    @Valid
    private BussFinProductOpenPlanDto bussFinProductOpenPlanDto;

    /**
     * {@linkplain}
     */
    @Valid
    private BussFinProductBonusPlanDto bussFinProductBonusPlanDto;

    /**
     * {@linkplain}
     */
    @Valid
    private BussFinProductFeeInfoDto bussFinProductFeeInfoDto;

    /**
     * {@linkplain}
     */
    @Valid
    private BussBenchMarkInfoDto bussBenchMarkInfoDto;

    /**
     * {@linkplain}
     */
    @Valid
    private transient BussFinProductFloatFeeDto bussFinProductFloatFeeDto;

    /**
     * {@linkplain}
     */
    @Valid
    private BussFinProductSaleParamDto bussFinProductSaleParamDto;

    /**
     * {@linkplain}
     */
    @Valid
    private BussFinProductValuationDto bussFinProductValuationDto;

    /**
     * {@linkplain}
     */
    @Valid
    private BussFinProductGoalInfoDto bussFinProductGoalInfoDto;

    /**
     * {@linkplain}
     */
    @Valid
    private BussRepFinProductInfoDto bussRepFinProductInfoDto;

    /**
     * {@linkplain}
     */
    @Valid
    private BussFinProductRevalDocumentDto bussFinProductRevalDocumentDto;

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

    /**
     * 获取 elementId
     * 
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 elementId
     * 
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 获取 branch
     * 
     * @return branch.
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 branch
     * 
     * @param branch branch
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 获取 pageId
     * 
     * @return pageId.
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * 设置 pageId
     * 
     * @param pageId pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    /**
     * 获取 tmplId
     * 
     * @return tmplId.
     */
    public String getTmplId() {
        return tmplId;
    }

    /**
     * 设置 tmplId
     * 
     * @param tmplId tmplId
     */
    public void setTmplId(String tmplId) {
        this.tmplId = tmplId;
    }

    /**
     * 获取 ptlId
     * 
     * @return ptlId.
     */
    public String getPtlId() {
        return ptlId;
    }

    /**
     * 设置 ptlId
     * 
     * @param ptlId ptlId
     */
    public void setPtlId(String ptlId) {
        this.ptlId = ptlId;
    }

    /**
     * 获取 dealMode
     * 
     * @return dealMode.
     */
    public String getDealMode() {
        return dealMode;
    }

    /**
     * 设置 dealMode
     * 
     * @param dealMode dealMode
     */
    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }

    /**
     * 获取 openType
     * 
     * @return openType.
     */
    public String getOpenType() {
        return openType;
    }

    /**
     * 设置 openType
     * 
     * @param openType openType
     */
    public void setOpenType(String openType) {
        this.openType = openType;
    }

    /**
     * 获取 calcType
     * 
     * @return calcType.
     */
    public String getCalcType() {
        return calcType;
    }

    /**
     * 设置 calcType
     * 
     * @param calcType calcType
     */
    public void setCalcType(String calcType) {
        this.calcType = calcType;
    }

    /**
     * 获取 isCycle
     * 
     * @return isCycle.
     */
    public String getIsCycle() {
        return isCycle;
    }

    /**
     * 设置 isCycle
     * 
     * @param isCycle isCycle
     */
    public void setIsCycle(String isCycle) {
        this.isCycle = isCycle;
    }

    /**
     * 获取 isLay
     * 
     * @return isLay.
     */
    public String getIsLay() {
        return isLay;
    }

    /**
     * 设置 isLay
     * 
     * @param isLay isLay
     */
    public void setIsLay(String isLay) {
        this.isLay = isLay;
    }

    /**
     * 获取 layType
     * 
     * @return layType.
     */
    public String getLayType() {
        return layType;
    }

    /**
     * 设置 layType
     * 
     * @param layType layType
     */
    public void setLayType(String layType) {
        this.layType = layType;
    }

    /**
     * 获取 profitFlag
     * 
     * @return profitFlag.
     */
    public String getProfitFlag() {
        return profitFlag;
    }

    /**
     * 设置 profitFlag
     * 
     * @param profitFlag profitFlag
     */
    public void setProfitFlag(String profitFlag) {
        this.profitFlag = profitFlag;
    }

    /**
     * 获取 yield
     * 
     * @return yield.
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 yield
     * 
     * @param yield yield
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 获取 firstSaleVdate
     * 
     * @return firstSaleVdate.
     */
    public Date getFirstSaleVdate() {
        return firstSaleVdate;
    }

    /**
     * 设置 firstSaleVdate
     * 
     * @param firstSaleVdate firstSaleVdate
     */
    public void setFirstSaleVdate(Date firstSaleVdate) {
        this.firstSaleVdate = firstSaleVdate;
    }

    /**
     * 获取 firstSaleMdate
     * 
     * @return firstSaleMdate.
     */
    public Date getFirstSaleMdate() {
        return firstSaleMdate;
    }

    /**
     * 设置 firstSaleMdate
     * 
     * @param firstSaleMdate firstSaleMdate
     */
    public void setFirstSaleMdate(Date firstSaleMdate) {
        this.firstSaleMdate = firstSaleMdate;
    }

    /**
     * 获取 closeDays
     * 
     * @return closeDays.
     */
    public Integer getCloseDays() {
        return closeDays;
    }

    /**
     * 设置 closeDays
     * 
     * @param closeDays closeDays
     */
    public void setCloseDays(Integer closeDays) {
        this.closeDays = closeDays;
    }

    /**
     * 获取 basis
     * 
     * @return basis.
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 basis
     * 
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
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
     * 获取 purSpeed
     * 
     * @return purSpeed.
     */
    public Integer getPurSpeed() {
        return purSpeed;
    }

    /**
     * 设置 purSpeed
     * 
     * @param purSpeed purSpeed
     */
    public void setPurSpeed(Integer purSpeed) {
        this.purSpeed = purSpeed;
    }

    /**
     * 获取 investNature
     * 
     * @return investNature.
     */
    public String getInvestNature() {
        return investNature;
    }

    /**
     * 设置 investNature
     * 
     * @param investNature investNature
     */
    public void setInvestNature(String investNature) {
        this.investNature = investNature;
    }

    /**
     * 获取 saleMode
     * 
     * @return saleMode.
     */
    public String getSaleMode() {
        return saleMode;
    }

    /**
     * 设置 saleMode
     * 
     * @param saleMode saleMode
     */
    public void setSaleMode(String saleMode) {
        this.saleMode = saleMode;
    }

    /**
     * 获取 bussFinProductPayPlanDto
     * 
     * @return bussFinProductPayPlanDto.
     */
    public BussFinProductPayPlanDto getBussFinProductPayPlanDto() {
        return bussFinProductPayPlanDto;
    }

    /**
     * 设置 bussFinProductPayPlanDto
     * 
     * @param bussFinProductPayPlanDto bussFinProductPayPlanDto
     */
    public void setBussFinProductPayPlanDto(BussFinProductPayPlanDto bussFinProductPayPlanDto) {
        this.bussFinProductPayPlanDto = bussFinProductPayPlanDto;
    }

    /**
     * 获取 bussFinProductOpenPlanDto
     * 
     * @return bussFinProductOpenPlanDto.
     */
    public BussFinProductOpenPlanDto getBussFinProductOpenPlanDto() {
        return bussFinProductOpenPlanDto;
    }

    /**
     * 设置 bussFinProductOpenPlanDto
     * 
     * @param bussFinProductOpenPlanDto bussFinProductOpenPlanDto
     */
    public void setBussFinProductOpenPlanDto(BussFinProductOpenPlanDto bussFinProductOpenPlanDto) {
        this.bussFinProductOpenPlanDto = bussFinProductOpenPlanDto;
    }

    /**
     * 获取 bussFinProductBonusPlanDto
     * 
     * @return bussFinProductBonusPlanDto.
     */
    public BussFinProductBonusPlanDto getBussFinProductBonusPlanDto() {
        return bussFinProductBonusPlanDto;
    }

    /**
     * 设置 bussFinProductBonusPlanDto
     * 
     * @param bussFinProductBonusPlanDto bussFinProductBonusPlanDto
     */
    public void setBussFinProductBonusPlanDto(BussFinProductBonusPlanDto bussFinProductBonusPlanDto) {
        this.bussFinProductBonusPlanDto = bussFinProductBonusPlanDto;
    }

    /**
     * 获取 bussFinProductFeeInfoDto
     * 
     * @return bussFinProductFeeInfoDto.
     */
    public BussFinProductFeeInfoDto getBussFinProductFeeInfoDto() {
        return bussFinProductFeeInfoDto;
    }

    /**
     * 设置 bussFinProductFeeInfoDto
     * 
     * @param bussFinProductFeeInfoDto bussFinProductFeeInfoDto
     */
    public void setBussFinProductFeeInfoDto(BussFinProductFeeInfoDto bussFinProductFeeInfoDto) {
        this.bussFinProductFeeInfoDto = bussFinProductFeeInfoDto;
    }

    /**
     * 获取 bussBenchMarkInfoDto
     * 
     * @return bussBenchMarkInfoDto.
     */
    public BussBenchMarkInfoDto getBussBenchMarkInfoDto() {
        return bussBenchMarkInfoDto;
    }

    /**
     * 设置 bussBenchMarkInfoDto
     * 
     * @param bussBenchMarkInfoDto bussBenchMarkInfoDto
     */
    public void setBussBenchMarkInfoDto(BussBenchMarkInfoDto bussBenchMarkInfoDto) {
        this.bussBenchMarkInfoDto = bussBenchMarkInfoDto;
    }

    /**
     * 获取 bussFinProductFloatFeeDto
     * 
     * @return bussFinProductFloatFeeDto.
     */
    public BussFinProductFloatFeeDto getBussFinProductFloatFeeDto() {
        return bussFinProductFloatFeeDto;
    }

    /**
     * 设置 bussFinProductFloatFeeDto
     * 
     * @param bussFinProductFloatFeeDto bussFinProductFloatFeeDto
     */
    public void setBussFinProductFloatFeeDto(BussFinProductFloatFeeDto bussFinProductFloatFeeDto) {
        this.bussFinProductFloatFeeDto = bussFinProductFloatFeeDto;
    }

    /**
     * 获取 bussFinProductSaleParamDto
     * 
     * @return bussFinProductSaleParamDto.
     */
    public BussFinProductSaleParamDto getBussFinProductSaleParamDto() {
        return bussFinProductSaleParamDto;
    }

    /**
     * 设置 bussFinProductSaleParamDto
     * 
     * @param bussFinProductSaleParamDto bussFinProductSaleParamDto
     */
    public void setBussFinProductSaleParamDto(BussFinProductSaleParamDto bussFinProductSaleParamDto) {
        this.bussFinProductSaleParamDto = bussFinProductSaleParamDto;
    }

    /**
     * 获取 bussFinProductValuationDto
     * 
     * @return bussFinProductValuationDto.
     */
    public BussFinProductValuationDto getBussFinProductValuationDto() {
        return bussFinProductValuationDto;
    }

    /**
     * 设置 bussFinProductValuationDto
     * 
     * @param bussFinProductValuationDto bussFinProductValuationDto
     */
    public void setBussFinProductValuationDto(BussFinProductValuationDto bussFinProductValuationDto) {
        this.bussFinProductValuationDto = bussFinProductValuationDto;
    }

    /**
     * 获取 bussFinProductGoalInfoDto
     * 
     * @return bussFinProductGoalInfoDto.
     */
    public BussFinProductGoalInfoDto getBussFinProductGoalInfoDto() {
        return bussFinProductGoalInfoDto;
    }

    /**
     * 设置 bussFinProductGoalInfoDto
     * 
     * @param bussFinProductGoalInfoDto bussFinProductGoalInfoDto
     */
    public void setBussFinProductGoalInfoDto(BussFinProductGoalInfoDto bussFinProductGoalInfoDto) {
        this.bussFinProductGoalInfoDto = bussFinProductGoalInfoDto;
    }

    /**
     * 获取 bussRepFinProductInfoDto
     * 
     * @return bussRepFinProductInfoDto.
     */
    public BussRepFinProductInfoDto getBussRepFinProductInfoDto() {
        return bussRepFinProductInfoDto;
    }

    /**
     * 设置 bussRepFinProductInfoDto
     * 
     * @param bussRepFinProductInfoDto bussRepFinProductInfoDto
     */
    public void setBussRepFinProductInfoDto(BussRepFinProductInfoDto bussRepFinProductInfoDto) {
        this.bussRepFinProductInfoDto = bussRepFinProductInfoDto;
    }

    /**
     * 获取 bussFinProductRevalDocumentDto
     * 
     * @return bussFinProductRevalDocumentDto.
     */
    public BussFinProductRevalDocumentDto getBussFinProductRevalDocumentDto() {
        return bussFinProductRevalDocumentDto;
    }

    /**
     * 设置 bussFinProductRevalDocumentDto
     * 
     * @param bussFinProductRevalDocumentDto bussFinProductRevalDocumentDto
     */
    public void setBussFinProductRevalDocumentDto(BussFinProductRevalDocumentDto bussFinProductRevalDocumentDto) {
        this.bussFinProductRevalDocumentDto = bussFinProductRevalDocumentDto;
    }
}
