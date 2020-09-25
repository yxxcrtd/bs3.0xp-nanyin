/*
 * 文件名：EtrLendSpecInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.entrust.etrwtspotex.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.system.base.entity.SysUploadFileInfo;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.component.business.trade.dto.BussEtrInvestPersonInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.entrust.etrwtspotex.validator.EtrWtSpotexValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 外汇即期委托dto 〈功能详细描述〉
 * 
 * @author wangguoxian
 * @version 1.0 2018年9月13日
 * @see EtrWtSpotexDto
 * @since 1.0
 */
@PageId("EtrWtSpotex002")
@ValidLogic(targetClass = EtrWtSpotexValidator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class EtrWtSpotexDto extends EtrEntrust implements ProcessEntity {

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
    private EtrInvestPersonInfoDto etrInvestPersonInfo;

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
     * 交易完成度
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal transactioncom;

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
     * 外汇交易类型名称
     */
    private String excDealTypeStr;

    /**
     * 货币对
     */
    private String curPair;

    /**
     * 货币对名称
     */
    private String curPairName;

    /**
     * 清算速度-委托指令附属 表
     */
    private Long settleSpreed;

    /**
     * 清算速度-数据字典表
     */
    private String accountSpeed;

    /**
     * 清算速度名称
     */
    private String settleSpreedStr;

    /**
     * 交易货币
     */
    private String ccyName;

    /**
     * 对应货币名称
     */
    private String corCcyName;

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
     * 交易完成度
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
     * 获取清算速度-数据字典 表
     * 
     * @return 清算速度-数据字典表
     * @see
     * @since 1.0
     */
    @NotEmpty(fieldName = "EtrWtSpotex.settleSpreed", groups = {ValidateGroupEdit.class})
    public String getAccountSpeed() {
        if (this.settleSpreed == null) {
            return null;
        }
        long settleSpreedTmp = this.getSettleSpreed() + 1;
        String settleSpreedStrTmp = null;
        if (settleSpreedTmp < 10) {
            settleSpreedStrTmp = "0" + settleSpreedTmp;
        }
        else {
            settleSpreedStrTmp = Long.toString(settleSpreedTmp);
        }
        return settleSpreedStrTmp;
    }

    /**
     * 设置清算速度-数据字典表
     * 
     * @param accountSpeed 清算速度-数据字典表
     * @see
     * @since 1.0
     */
    public void setAccountSpeed(String accountSpeed) {
        if (StringUtil.isNotEmpty(accountSpeed)) {
            this.settleSpreed = Long.parseLong(accountSpeed) - 1;
        }

    }

    /**
     * 获得交易货币账户
     * 
     * @return 交易货币账户
     * @see
     * @since 1.0
     */
    @NotEmpty(fieldName = "EtrWtSpotex.dealCurAcct", groups = {ValidateGroupEdit.class})
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
    @NotEmpty(fieldName = "EtrWtSpotex.corCurAcct", groups = {ValidateGroupEdit.class})
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
     * 获得对应货币名称
     * 
     * @return 对应货币名称
     * @see
     * @since 1.0
     */
    public String getCorCcyName() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, this.getCorCcy());
    }

    /**
     * 设置对应货币名称
     * 
     * @param corCcyName 对应货币名称
     * @see
     * @since 1.0
     */
    public void setCorCcyName(String corCcyName) {
        this.corCcyName = corCcyName;
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
     * 获取币种名称
     * 
     * @return 币种名称
     * @see
     * @since 1.0
     */
    public String getCcyName() {
        String ccyNameTmp = null;
        if (StringUtil.isNotEmpty(super.getCcy())) {
            ccyNameTmp = SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());

        }
        return ccyNameTmp;
    }

    /**
     * 设置币种名称
     * 
     * @param ccyName 币种名称
     * @see
     * @since 1.0
     */
    public void setCcyName(String ccyName) {
        this.ccyName = ccyName;
    }

    /**
     * 获取清算速度名称
     * 
     * @return 清算速度名称
     * @see
     * @since 1.0
     */
    public String getSettleSpreedStr() {
        String settleSpreedStr = null;
        if (StringUtil.isNotEmpty(this.getAccountSpeed())) {
            settleSpreedStr = SysDataUtil.getNameFromDataDict(DictCode.清算速度,
                this.getAccountSpeed());
        }
        return settleSpreedStr;
    }

    /**
     * 设置清算速度名称
     * 
     * @param settleSpreedStr 清算速度名称
     * @see
     * @since 1.0
     */
    public void setSettleSpreedStr(String settleSpreedStr) {
        this.settleSpreedStr = settleSpreedStr;
    }

    /**
     * 获取清算速度
     * 
     * @return 清算速度
     * @see
     * @since 1.0
     */
    public Long getSettleSpreed() {
        return settleSpreed;
    }

    /**
     * 设置清算速度
     * 
     * @param settleSpreed 清算速度
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
    @NotEmpty(fieldName = "EtrWtSpotex.excDealType", groups = {ValidateGroupEdit.class})
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
     * 获得外汇交易类型名称
     * 
     * @return
     * @see
     * @since 1.0
     */
    public String getExcDealTypeStr() {
        String excDealTypeStrTmp = null;
        if (StringUtil.isNotEmpty(this.getExcDealType())) {
            excDealTypeStrTmp = SysDataUtil.getNameFromDataDict(DictCode.外汇交易类型,
                this.getExcDealType());
        }
        return excDealTypeStrTmp;
    }

    /**
     * 设置外汇交易类型名称
     * 
     * @param excDealTypeStr 外汇交易类型名称
     * @see
     * @since 1.0
     */
    public void setExcDealTypeStr(String excDealTypeStr) {
        this.excDealTypeStr = excDealTypeStr;
    }

    /**
     * 获取货币对
     * 
     * @return 货币对
     */
    @NotEmpty(fieldName = "EtrWtSpotex.curPair", groups = {ValidateGroupEdit.class})
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
     * 获取货币对名称
     * 
     * @return 货币对名称
     * @see
     * @since 1.0
     */
    public String getCurPairName() {
        String curPairNameTmp = null;
        if (StringUtil.isNotEmpty(this.getCurPair())) {
            curPairNameTmp = SysDataUtil.getNameFromDataDict(DictCode.货币对, this.getCurPair());
        }
        return curPairNameTmp;
    }

    /**
     * 设置货币对名称
     * 
     * @param curPairName 货币对名称
     * @see
     * @since 1.0
     */
    public void setCurPairName(String curPairName) {
        this.curPairName = curPairName;
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
    public void setEtrInvestPersonInfo(EtrInvestPersonInfoDto etrInvestPersonInfo) {
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

    /**
     * 获取 transactioncom
     * 
     * @return transactioncom.
     */
    public BigDecimal getTransactioncom() {
        return transactioncom;
    }

    /**
     * 设置 transactioncom
     * 
     * @param transactioncom transactioncom
     */
    public void setTransactioncom(BigDecimal transactioncom) {
        this.transactioncom = transactioncom;
    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @return
     * @see
     * @since 1.0
     */
    public String getVdateStr() {
        return DateUtil.format(super.getVdate());
    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @return
     * @see
     * @since 1.0
     */
    public String getMdateStr() {
        return DateUtil.format(super.getMdate());
    }

    /**
     * 交易方向数据字典转换 <功能详细描述>
     * 
     * @return
     * @see
     * @since 1.0
     */
    public String getPsStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
    }

    /**
     * 币种数据字典装换 <功能详细描述>
     * 
     * @return
     * @see
     * @since 1.0
     */
    public String getCcyStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
    }

    /**
     * 委托状态数据字典转换 <功能详细描述>
     * 
     * @return
     * @see
     * @since 1.0
     */
    public String getEntrustStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.委托状态, super.getEntrustStatus());
    }

    /**
     * 取得 委托代码
     * 
     * @return 委托代码
     */
    @Override
    @PrimaryKey(value = Field.PK_1)
    public String getEntrustId() {
        return super.getEntrustId();
    }

    /**
     * 组合代码判空
     */
    @Override
    @NotEmpty(fieldName = "EtrWtSpotex.portfolioId", groups = {ValidateGroupEdit.class})
    public String getPortfolioId() {
        return super.getPortfolioId();
    }

    /**
     * 委托开始日期判空
     */
    @Override
    @NotEmpty(fieldName = "EtrWtSpotex.vdate", groups = {ValidateGroupEdit.class})
    public Date getVdate() {
        return super.getVdate();
    }

    /**
     * 委托终止日期判空
     */
    @Override
    @NotEmpty(fieldName = "EtrWtSpotex.mdate", groups = {ValidateGroupEdit.class})
    public Date getMdate() {
        return super.getMdate();
    }

    /**
     * 交易货币判空
     */
    @Override
    @NotEmpty(fieldName = "EtrWtSpotex.ccy", groups = {ValidateGroupEdit.class})
    public String getCcy() {
        return super.getCcy();
    }

    /**
     * 交易方向判空
     */
    @Override
    @NotEmpty(fieldName = "EtrWtSpotex.ps", groups = {ValidateGroupEdit.class})
    public String getPs() {
        return super.getPs();
    }

    /**
     * 交易金额判空
     */
    @Override
    @NotEmpty(fieldName = "EtrWtSpotex.entrustAmt", groups = {ValidateGroupEdit.class})
    @Amount(fieldName = "EtrWtSpotex.entrustAmt", groups = {ValidateGroupEdit.class})
    public BigDecimal getEntrustAmt() {
        return super.getEntrustAmt();
    }

    /**
     * 即期汇率判空
     */
    @Override
    @NotEmpty(fieldName = "EtrWtSpotex.price", groups = {ValidateGroupEdit.class})
    public BigDecimal getPrice() {
        return super.getPrice();
    }
}
