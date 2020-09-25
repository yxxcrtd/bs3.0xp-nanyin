/*
 * 文件名：EtrWtSpotexDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangguoxian
 * 修改时间：2019年6月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.entrust.etrwtspotex.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;

/**
 * 外汇即期委托微服务用Dto
 * 
 * @author wangguoxian
 * @version 1.0 2019年6月28日
 * @see EtrWtSpotexDto
 * @since 1.0
 */
public class EtrWtSpotexDto extends EtrEntrust {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -3314619126979170188L;

    /**
     * 文件上传dto
     */
    private List<SysUploadFileInfo> fileArray;

    /**
     * 投资委托人员信息dto
     */
    @Valid
    private BussEtrInvestPersonInfoDto etrInvestPersonInfo;

    /**
     * 按钮id
     */
    private String elementId;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 完成额度
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal finishAmt;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 表格所选数据
     */
    private transient List<EtrWtSpotexDto> selectedItem;

    /**
     * 委托关闭方式
     */
    private String CloseWay;

    /**
     * 起息日
     */
    private Date vdateAdd;

    /**
     * 到期日
     */
    private Date mdateAdd;

    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;

    /**
     * 外汇交易类型
     */
    private String excDealType;

    /**
     * 货币对
     */
    private String curPair;

    /**
     * 对应货币
     */
    private String corCcy;

    /**
     * 对应货币可用持仓
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal corCurPos;

    /**
     * 预计对应货币金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal expCorAmt;

    /**
     * 预计对应货币金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal tradeFinishAmt;

    /**
     * 交易货币账户
     */
    private String dealCurAcct;

    /**
     * 对应货币账户
     */
    private String corCurAcct;

    /**
     * 清算速度
     */
    private Long settleSpreed;

    /**
     * 获得交易货币账户
     * 
     * @return 交易货币账户
     * @see
     * @since 1.0
     */
    public String getDealCurAcct() {
        return dealCurAcct;
    }

    /**
     * 设置交易货币账户
     * 
     * @param dealCurAcct 交易货币账户
     * @see
     * @since 1.0
     */
    public void setDealCurAcct(String dealCurAcct) {
        this.dealCurAcct = dealCurAcct;
    }

    /**
     * 获得对应货币账户
     * 
     * @return 对应货币账户
     * @see
     * @since 1.0
     */
    public String getCorCurAcct() {
        return corCurAcct;
    }

    /**
     * 设置对应货币账户
     * 
     * @param corCurAcct 对应货币账户
     * @see
     * @since 1.0
     */
    public void setCorCurAcct(String corCurAcct) {
        this.corCurAcct = corCurAcct;
    }

    /**
     * 获得对应货币可用持仓
     * 
     * @return 对应货币可用持仓
     * @see
     * @since 1.0
     */
    public BigDecimal getCorCurPos() {
        return corCurPos;
    }

    /**
     * 设置对应货币可用持仓
     * 
     * @param corCurPos 对应货币可用持仓
     * @see
     * @since 1.0
     */
    public void setCorCurPos(BigDecimal corCurPos) {
        this.corCurPos = corCurPos;
    }

    /**
     * 获取交易完成度
     * 
     * @return 交易完成度
     * @see
     * @since 1.0
     */
    public BigDecimal getTradeFinishAmt() {
        return tradeFinishAmt;
    }

    /**
     * 设置交易完成度
     * 
     * @param tradeFinishAmt 交易完成度
     * @see
     * @since 1.0
     */
    public void setTradeFinishAmt(BigDecimal tradeFinishAmt) {
        this.tradeFinishAmt = tradeFinishAmt;
    }

    /**
     * 获取预计对应货币金额
     * 
     * @return 预计对应货币金额
     * @see
     * @since 1.0
     */
    public BigDecimal getExpCorAmt() {
        return expCorAmt;
    }

    /**
     * 设置预计对应货币金额
     * 
     * @param expCorAmt 预计对应货币金额
     * @see
     * @since 1.0
     */
    public void setExpCorAmt(BigDecimal expCorAmt) {
        this.expCorAmt = expCorAmt;
    }

    /**
     * 获得对应货币
     * 
     * @return 对应货币
     * @see
     * @since 1.0
     */
    public String getCorCcy() {
        return corCcy;
    }

    /**
     * 设置对应货币
     * 
     * @param corCcy 对应货币
     * @see
     * @since 1.0
     */
    public void setCorCcy(String corCcy) {
        this.corCcy = corCcy;
    }

    /**
     * 获取清算速度
     * 
     * @return 清算速度
     * @see
     * @since 1.0
     */
    public Long getSettleSpreed() {
        return this.settleSpreed;
    }

    /**
     * 设置清算速度
     * 
     * @param settleSpeed 清算速度
     * @see
     * @since 1.0
     */
    public void setSettleSpreed(Long settleSpreed) {
        this.settleSpreed = settleSpreed;
    }

    /**
     * 获取外汇交易类型
     * 
     * @return 外汇交易类型
     */
    public String getExcDealType() {
        return excDealType;
    }

    /**
     * 设置外汇交易类型
     * 
     * @param excDealType 外汇交易类型
     */
    public void setExcDealType(String excDealType) {
        this.excDealType = excDealType;
    }

    /**
     * 获取货币对
     * 
     * @return 货币对
     */
    public String getCurPair() {
        return curPair;
    }

    /**
     * 设置货币对
     * 
     * @param curPair 货币对
     */
    public void setCurPair(String curPair) {
        this.curPair = curPair;
    }

    /**
     * 获取 riskTrialRetDto
     * 
     * @return riskTrialRetDto.
     */
    public List<RiskTrialRetDto> getRiskTrialRetDto() {
        return riskTrialRetDto;
    }

    /**
     * 设置 riskTrialRetDto
     * 
     * @param riskTrialRetDto riskTrialRetDto
     */
    public void setRiskTrialRetDto(List<RiskTrialRetDto> riskTrialRetDto) {
        this.riskTrialRetDto = riskTrialRetDto;
    }

    /**
     * 获取 fileArray
     * 
     * @return fileArray.
     */
    public List<SysUploadFileInfo> getFileArray() {
        return fileArray;
    }

    /**
     * 设置 fileArray
     * 
     * @param fileArray fileArray
     */
    public void setFileArray(List<SysUploadFileInfo> fileArray) {
        this.fileArray = fileArray;
    }

    /**
     * 获取 vdateAdd
     * 
     * @return vdateAdd.
     */
    public Date getVdateAdd() {
        return vdateAdd;
    }

    /**
     * 设置 vdateAdd
     * 
     * @param vdateAdd vdateAdd
     */
    public void setVdateAdd(Date vdateAdd) {
        this.vdateAdd = vdateAdd;
    }

    /**
     * 获取 mdateAdd
     * 
     * @return mdateAdd.
     */
    public Date getMdateAdd() {
        return mdateAdd;
    }

    /**
     * 设置 mdateAdd
     * 
     * @param mdateAdd mdateAdd
     */
    public void setMdateAdd(Date mdateAdd) {
        this.mdateAdd = mdateAdd;
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
     * 获取 etrInvestPersonInfo
     * 
     * @return etrInvestPersonInfo.
     */
    public BussEtrInvestPersonInfoDto getEtrInvestPersonInfo() {
        return etrInvestPersonInfo;
    }

    /**
     * 设置 etrInvestPersonInfo
     * 
     * @param etrInvestPersonInfo etrInvestPersonInfo
     */
    public void setEtrInvestPersonInfo(BussEtrInvestPersonInfoDto etrInvestPersonInfo) {
        this.etrInvestPersonInfo = etrInvestPersonInfo;
    }

    /**
     * 获取 closeWay
     * 
     * @return closeWay.
     */
    public String getCloseWay() {
        return CloseWay;
    }

    /**
     * 设置 closeWay
     * 
     * @param closeWay closeWay
     */
    public void setCloseWay(String closeWay) {
        CloseWay = closeWay;
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

    /**
     * 获取 selectedItem
     * 
     * @return selectedItem.
     */
    public List<EtrWtSpotexDto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 设置 selectedItem
     * 
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<EtrWtSpotexDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * 获取 portfolioName
     * 
     * @return portfolioName.
     */
    public String getPortfolioName() {
        return portfolioName;
    }

    /**
     * 设置 portfolioName
     * 
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    /**
     * 获取 finishAmt
     * 
     * @return finishAmt.
     */
    public BigDecimal getFinishAmt() {
        return finishAmt;
    }

    /**
     * 设置 finishAmt
     * 
     * @param finishAmt finishAmt
     */
    public void setFinishAmt(BigDecimal finishAmt) {
        this.finishAmt = finishAmt;
    }


}
