/*
 * 文件名：EtrLendSpecInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrlendspecinfo.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.Rate;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.system.base.entity.SysUploadFileInfo;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.component.business.trade.dto.BussEtrInvestPersonInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.entrust.etrlendspecinfo.validator.EtrLendSpecInfoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 拆借委托dto
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年9月13日
 * @see EtrLendSpecInfoDto
 * @since 1.0
 */
@PageId("EtrLendSpecInfo002")
@ValidLogic(targetClass = EtrLendSpecInfoValidator.class , groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class EtrLendSpecInfoDto extends EtrEntrust implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -3314619126979170188L;
    
    /**
     * 序号
     */
    private String indexId;
    
    /**
     * 最低成交金额
     */
    private BigDecimal minAmt;
    

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
     * 交易日历
     */
    private String calendarId;
    
    /**
     * 交易日历转文本
     */
    private String calendarIdName;
    
    /**
     * 接收状态
     */
    private String recStatus;
    /**
     * 按钮id
     */
    private String elementId;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 资产一级分类，委托页面的债券一级分类
     */
    private String assetType1;
    /**
     * 期限(天)
     */
    private Long termDays;
    /**
     * 完成额度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal finishAmt;
    
    /**
     * 交易完成度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal transactioncom;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 表格所选数据
     */
    private List<EtrLendSpecInfoDto> selectedItem;

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
     * 资产本金（授信用）
     */
    private BigDecimal prinAmt;

    /**
     * 后台取为平台日（授信用）
     */
    private Date tradeDate;
    
    private String inputValue1;
    private String inputValue2;
    private String inputValue3;

    /**
     * 利率审批编号
     */
    private String rateApprovalCode;

    public String getRateApprovalCode() {
        return rateApprovalCode;
    }

    public void setRateApprovalCode(String rateApprovalCode) {
        this.rateApprovalCode = rateApprovalCode;
    }

    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;
    
    
    
    /**
     * 获取 riskTrialRetDto
     * @return riskTrialRetDto.
     */
    public List<RiskTrialRetDto> getRiskTrialRetDto() {
        return riskTrialRetDto;
    }

    
    /**
     * 设置 riskTrialRetDto
     * @param riskTrialRetDto riskTrialRetDto
     */
    public void setRiskTrialRetDto(List<RiskTrialRetDto> riskTrialRetDto) {
        this.riskTrialRetDto = riskTrialRetDto;
    }

    /**
     * 获取 fileArray
     * @return fileArray.
     */
    public List<SysUploadFileInfo> getFileArray() {
        return fileArray;
    }
    
    /**
     * 设置 fileArray
     * @param fileArray fileArray
     */
    public void setFileArray(List<SysUploadFileInfo> fileArray) {
        this.fileArray = fileArray;
    }
    /**
     * 获取 calendarId
     * @return calendarId.
     */
    public String getCalendarId() {
        return calendarId;
    }
    /**
     * 设置 calendarId
     * @param calendarId calendarId
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    /**
     * 获取 termDays
     * @return termDays.
     */
    public Long getTermDays() {
        return termDays;
    }
    /**
     * 设置 termDays
     * @param termDays termDays
     */
    public void setTermDays(Long termDays) {
        this.termDays = termDays;
    }

    public String getTermDaysStr(){
        return StringUtil.numberToString(termDays);
    }
    
    /**
     * 获取 vdateAdd
     * @return vdateAdd.
     */
    public Date getVdateAdd() {
        return vdateAdd;
    }


    /**
     * 设置 vdateAdd
     * @param vdateAdd vdateAdd
     */
    public void setVdateAdd(Date vdateAdd) {
        this.vdateAdd = vdateAdd;
    }

    
    /**
     * 获取 mdateAdd
     * @return mdateAdd.
     */
    public Date getMdateAdd() {
        return mdateAdd;
    }


    
    /**
     * 设置 mdateAdd
     * @param mdateAdd mdateAdd
     */
    public void setMdateAdd(Date mdateAdd) {
        this.mdateAdd = mdateAdd;
    }



    /**
     * 获取 assetType1
     * @return assetType1.
     */
    @NotEmpty(fieldName = "EtrLendSpecInfo002.type6", groups = {ValidateGroupEdit.class})
    public String getAssetType1() {
        return assetType1;
    }
    /**
     * 设置 assetType1
     * @param assetType1 assetType1
     */
    public void setAssetType1(String assetType1) {
        this.assetType1 = assetType1;
    }


    /**
     * 获取 recStatus
     * @return recStatus.
     */
    public String getRecStatus() {
        return recStatus;
    }


    
    /**
     * 设置 recStatus
     * @param recStatus recStatus
     */
    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }


    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }


    /**
     * 获取 etrInvestPersonInfo
     * @return etrInvestPersonInfo.
     */
    public BussEtrInvestPersonInfoDto getEtrInvestPersonInfo() {
        return etrInvestPersonInfo;
    }
    /**
     * 设置 etrInvestPersonInfo
     * @param etrInvestPersonInfo etrInvestPersonInfo
     */
    public void setEtrInvestPersonInfo(BussEtrInvestPersonInfoDto etrInvestPersonInfo) {
        this.etrInvestPersonInfo = etrInvestPersonInfo;
    }




    /**
     * 获取 closeWay
     * @return closeWay.
     */
    public String getCloseWay() {
        return CloseWay;
    }



    
    /**
     * 设置 closeWay
     * @param closeWay closeWay
     */
    public void setCloseWay(String closeWay) {
        CloseWay = closeWay;
    }



    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }


    
    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }


    
    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<EtrLendSpecInfoDto> getSelectedItem() {
        return selectedItem;
    }


    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<EtrLendSpecInfoDto> selectedItem) {
        this.selectedItem = selectedItem;
    }


    /**
     * 获取 portfolioName
     * @return portfolioName.
     */
    public String getPortfolioName() {
        return portfolioName;
    }

    
    /**
     * 设置 portfolioName
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    
    /**
     * 获取 finishAmt
     * @return finishAmt.
     */
    public BigDecimal getFinishAmt() {
        return finishAmt;
    }

    
    /**
     * 设置 finishAmt
     * @param finishAmt finishAmt
     */
    public void setFinishAmt(BigDecimal finishAmt) {
        this.finishAmt = finishAmt;
    }

    
    /**
     * 获取 transactioncom
     * @return transactioncom.
     */
    public BigDecimal getTransactioncom() {
        return transactioncom;
    }

    
    /**
     * 设置 transactioncom
     * @param transactioncom transactioncom
     */
    public void setTransactioncom(BigDecimal transactioncom) {
        this.transactioncom = transactioncom;
    }
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getVdateStr(){
        return DateUtil.format(super.getVdate());
    }
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getVdateAddStr(){
        return DateUtil.format(this.getVdateAdd());
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getMdateAddStr(){
        return DateUtil.format(this.getMdateAdd());
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getMdateStr(){
        return DateUtil.format(super.getMdate());
    }
    
    /**
     * 
     * 交易方向数据字典转换
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getPsStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向, super.getPs());
    }
    /**
     * 
     * 币种数据字典装换
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getCcyStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
    }
    /**
     * 
     * 委托状态数据字典转换
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getEntrustStatusStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.委托状态, super.getEntrustStatus());
    }
    /**
     * 
     * 拆借类型数据字典转换
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getType6Str(){
        return SysDataUtil.getNameFromDataDict(DictCode.拆借类型, getAssetType1());
    }
    /**
     * 取得 委托代码
     * @return 委托代码
     */
    @Override
    @PrimaryKey(value = Field.PK_1)
    public String getEntrustId() {
        return super.getEntrustId();
    }
    /**
     * 组合代码判空
     * 
     */
    @Override
    @NotEmpty(fieldName = "EtrLendSpecInfo002.portfolioId", groups = {ValidateGroupEdit.class})
    public String getPortfolioId(){
        return super.getPortfolioId();
    }
    /**
     * 
     * 委托开始日期判空
     */
    @Override
    @NotEmpty(fieldName = "EtrLendSpecInfo002.vdate", groups = {ValidateGroupEdit.class})
    public Date getVdate(){
        return super.getVdate();
    }
    /**
     * 
     * 委托终止日期判空
     */
    @Override
    @NotEmpty(fieldName = "EtrLendSpecInfo002.mdate", groups = {ValidateGroupEdit.class})
    public Date getMdate(){
        return super.getMdate();
    }
    /**
     * 
     * 币种判空
     */
    @Override
    @NotEmpty(fieldName = "EtrLendSpecInfo002.ccy", groups = {ValidateGroupEdit.class})
    public String getCcy(){
        return super.getCcy();
    }
    /**
     * 
     * 交易方向判空
     */
    @Override
    @NotEmpty(fieldName = "EtrLendSpecInfo002.ps", groups = {ValidateGroupEdit.class})
    public String getPs(){
        return super.getPs();
    }
    
    /**
     * 
     * 交易金额判空
     */
    @Override
    @NotEmpty(fieldName = "EtrLendSpecInfo002.entrustAmt", groups = {ValidateGroupEdit.class})
    @Amount(fieldName = "EtrLendSpecInfo002.entrustAmt", groups = {ValidateGroupEdit.class})
    public BigDecimal getEntrustAmt(){
        return super.getEntrustAmt();
    }
    
    @Override
    @Rate(fieldName = "EtrLendSpecInfo002.price", groups = {ValidateGroupEdit.class})
    public BigDecimal getPrice(){
        return super.getPrice();
    }


    /**
     * 获取 indexId
     * @return indexId.
     */
    public String getIndexId() {
        return indexId;
    }


    /**
     * 设置 indexId
     * @param indexId indexId
     */
    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }


    /**
     * 获取 calendarIdName
     * @return calendarIdName.
     */
    public String getCalendarIdName() {
        return calendarIdName;
    }


    /**
     * 设置 calendarIdName
     * @param calendarIdName calendarIdName
     */
    public void setCalendarIdName(String calendarIdName) {
        this.calendarIdName = calendarIdName;
    }


    /**
     * 获取 minAmt
     * @return minAmt.
     */
    public BigDecimal getMinAmt() {
        return minAmt;
    }


    /**
     * 设置 minAmt
     * @param minAmt minAmt
     */
    public void setMinAmt(BigDecimal minAmt) {
        this.minAmt = minAmt;
    }

	public BigDecimal getPrinAmt() {
		return prinAmt;
	}

	public void setPrinAmt(BigDecimal prinAmt) {
		this.prinAmt = prinAmt;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getInputValue1() {
		return inputValue1;
	}

	public void setInputValue1(String inputValue1) {
		this.inputValue1 = inputValue1;
	}

	public String getInputValue2() {
		return inputValue2;
	}

	public void setInputValue2(String inputValue2) {
		this.inputValue2 = inputValue2;
	}

	public String getInputValue3() {
		return inputValue3;
	}

	public void setInputValue3(String inputValue3) {
		this.inputValue3 = inputValue3;
	}


}
