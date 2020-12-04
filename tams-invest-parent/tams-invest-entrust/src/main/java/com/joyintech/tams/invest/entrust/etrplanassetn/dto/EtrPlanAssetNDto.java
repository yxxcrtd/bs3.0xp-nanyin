/*
 * 文件名：EtrPlanAssetNDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年10月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.entrust.etrplanassetn.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
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
import com.joyintech.tams.invest.entrust.etrplanassetn.validator.EtrPlanAssetNValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 计划类净值委托DTO
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年10月8日
 * @see EtrPlanAssetNDto
 * @since 1.0
 */
@PageId("EtrPlanAssetN002")
@ValidLogic(targetClass = EtrPlanAssetNValidator.class , groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class EtrPlanAssetNDto extends EtrEntrust implements ProcessEntity {
    /**
     * 资产二级分类名称
     */
    private String assetType1Name;

    /**
     * 资产三级分类名称
     */
    private String assetType10Name;

    /**
     * 资产四级分类名称
     */
    private String assetType11Name;
    /**
     * 所属部门
     */
    private String room;
    /**
     * 备注
     */
  private String  remark;
    /**
     * 交易方向名称
     */
    private String psName;
    /**
     * 市场代码
     */
    private String  finprodMarketId;
    /**
     * 交易对手
     */
    private String counterId;
    /**
     * 交易对手名称
     */
    private String counterIdName;
    /**
     * 通道
     */
    private String chlId;
    /**
     * 金融产品全称
     */
    private String finprodName;
    /**
     * 序号
     */
    private String indexId;
    /**
     *委托总额
     */
    private String entrustSumAmt;
    /**
     * 指令可用现金
     */
    private BigDecimal proAvailableCash;
//    /**
//     * 委托份额
//     */
//    private String entrustNum;

    /**
     * 币种名称
     */
    private String ccyName;
    /**
     * 最低成交金额
     */
    private String dealAmount;
	private String inputValue1;
    private String inputValue2;
    private String inputValue3;

    /**
     * 组合T+1可用现金,用于质押式回购指令还是买断式回购指令
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal appProAvailableCash1;

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPsName() {
        return psName;
    }

    public void setPsName(String psName) {
        this.psName = psName;
    }

    @Override
    public String getCounterId() {
        return counterId;
    }

    @Override
    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

    public String getCounterIdName() {
        return counterIdName;
    }

    public void setCounterIdName(String counterIdName) {
        this.counterIdName = counterIdName;
    }

    @Override
    public String getChlId() {
        return chlId;
    }

    @Override
    public void setChlId(String chlId) {
        this.chlId = chlId;
    }

    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    public String getEntrustSumAmt() {
        return entrustSumAmt;
    }

    public void setEntrustSumAmt(String entrustSumAmt) {
        this.entrustSumAmt = entrustSumAmt;
    }

    @Override
    public BigDecimal getProAvailableCash() {
        return proAvailableCash;
    }

    @Override
    public void setProAvailableCash(BigDecimal proAvailableCash) {
        this.proAvailableCash = proAvailableCash;
    }


    public String getCcyName() {
        return ccyName;
    }

    public void setCcyName(String ccyName) {
        this.ccyName = ccyName;
    }

    public String getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(String dealAmount) {
        this.dealAmount = dealAmount;
    }

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -3472609403478897515L;

    /**
     * 收益类型
     */
    private String profitType;
    /**
     * 资产起息日
     */
    private Date finvdate;
    /**
     * 资产到期日
     */
    private Date finmdate;
    /**
     * 指令可用持仓
     */
    private BigDecimal assetAvailablePosition;
    /**
     * 交易日历
     */
    private String calendarId;
    /**
     * 文件上传dto
     */
    private List<SysUploadFileInfo> fileArray;
    /**
     * 计划类资产二级分类
     */
    private String assetType2;
    /**
     * 人员信息业务组件DTO
     */
    @Valid
    private BussEtrInvestPersonInfoDto etrInvestPersonInfo;
    /**
     * 按钮id
     */
    private String elementId;
    /**
     * 选中的数据
     */
    private transient List<EtrPlanAssetNDto> selectedItem;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 委托关闭方式
     */
    private String CloseWay;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 资产一级分类
     */
    private String assetType1;
    /*添加三-四级分类 zhangpuyu 2019/7/29 start*/
    /**
     * 资产三级分类
     */
    private String assetType10;
    /**
     * 资产四级分类
     */
    private String assetType11;
    
    /**
     * 资产本金（授信用）
     */
    private BigDecimal prinAmt;
    
    /**
     * 后台取为平台日（授信用）
     */
    private Date tradeDate;

    /**
     * 获取 assetType10
     * @return assetType10.
     */
    @NotEmpty(fieldName = "EtrPlanAssetN001.type10",groups={ValidateGroupEdit.class})
    public String getAssetType10() {
        return assetType10;
    }
    /**
     * 设置 assetType10
     * @param assetType10 assetType10
     */
    public void setAssetType10(String assetType10) {
        this.assetType10 = assetType10;
    }
    /**
     * 获取 assetType11
     * @return assetType11.
     */
    public String getAssetType11() {
        return assetType11;
    }
    /**
     * assetType11
     * @param assetType11 assetType11
     */
    public void setAssetType11(String assetType11) {
        this.assetType11 = assetType11;
    }
    /**
    *
    * 行内资产三级分类数据字典转换
    * <功能详细描述>
    * @return
    * @see
    * @since 1.0
    */
    public String getBusAssetType10(){
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产三级分类, this.getAssetType10());
    }
    /**
    *
    * 行内资产四级分类数据字典转换
    * <功能详细描述>
    * @return
    * @see
    * @since 1.0
    */
    public String getBusAssetType11(){
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产四级分类, this.getAssetType11());
    }
    /*添加三-四级分类 zhangpuyu 2019/7/29 end*/

    /**
     * 交易对手类型
     */
    private String counterType;
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
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;

    public String getProfitType() {
        return profitType;
    }

    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    /**
     * 获取 finvdate
     * @return finvdate.
     */
    public Date getFinvdate() {
        return finvdate;
    }






    /**
     * 设置 finvdate
     * @param finvdate finvdate
     */
    public void setFinvdate(Date finvdate) {
        this.finvdate = finvdate;
    }






    /**
     * 获取 finmdate
     * @return finmdate.
     */
    public Date getFinmdate() {
        return finmdate;
    }






    /**
     * 设置 finmdate
     * @param finmdate finmdate
     */
    public void setFinmdate(Date finmdate) {
        this.finmdate = finmdate;
    }





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
     * 获取 assetAvailablePosition
     * @return assetAvailablePosition.
     */
    public BigDecimal getAssetAvailablePosition() {
        return assetAvailablePosition;
    }




    /**
     * 设置 assetAvailablePosition
     * @param assetAvailablePosition assetAvailablePosition
     */
    public void setAssetAvailablePosition(BigDecimal assetAvailablePosition) {
        this.assetAvailablePosition = assetAvailablePosition;
    }



    /**
     * 获取 calendarId
     * @return calendarId.
     */
    @Override
    public String getCalendarId() {
        return calendarId;
    }



    /**
     * 设置 calendarId
     * @param calendarId calendarId
     */
    @Override
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
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
     * 获取 finprodMarketId
     * @return finprodMarketId.
     */
    @NotEmpty(fieldName = "EtrPlanAssetR001.finprodId", groups = {ValidateGroupEdit.class})
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    /**
     * 设置 finprodMarketId
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }
    /**
     * 获取 assetType2
     * @return assetType2.
     */
    public String getAssetType2() {
        return assetType2;
    }
    /**
     * 设置 assetType2
     * @param assetType2 assetType2
     */
    public void setAssetType2(String assetType2) {
        this.assetType2 = assetType2;
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
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<EtrPlanAssetNDto> getSelectedItem() {
        return selectedItem;
    }
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<EtrPlanAssetNDto> selectedItem) {
        this.selectedItem = selectedItem;
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
     * 获取 assetType1
     * @return assetType1.
     */
    @NotEmpty(fieldName = "EtrPlanAssetN001.assetType1", groups = {ValidateGroupEdit.class})
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
     * 获取 finprodName
     * @return finprodName.
     */
    public String getFinprodName() {
        return finprodName;
    }


    /**
     * 设置 finprodName
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
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
     * 委托状态数据字典转换
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getBusEntrustStatus(){
        return SysDataUtil.getNameFromDataDict(DictCode.委托状态, super.getEntrustStatus());
    }
    /**
     *
     * 币种数据字典转换
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getBusCcy(){
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
    }
    /**
     *
     * 交易方向数据字典转换
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getBusPs(){
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
    }
    /**
     *
     * 资产类型数据字典转换
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    /* add by zhangpuyu 2019-7-31 start */
    public String getBusAssetType1(){
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产二级分类, this.getAssetType1());
    }
    /* add by zhangpuyu 2019-7-31 end */
    /**
     * 取得 委托代码
     * @return 委托代码
     */
    @Override
    @PrimaryKey(value = Field.PK_1)
    public String getEntrustId() {
        return super.getEntrustId();
    }

    @Override
    @NotEmpty(fieldName = "EtrPlanAssetN002.portfolioId", groups = {ValidateGroupEdit.class})
    public String getPortfolioId(){
        return super.getPortfolioId();
    }

    @Override
    @NotEmpty(fieldName = "EtrPlanAssetN002.vdate", groups = {ValidateGroupEdit.class})
    public Date getVdate(){
        return super.getVdate();
    }

    @Override
    @NotEmpty(fieldName = "EtrPlanAssetN002.mdate", groups = {ValidateGroupEdit.class})
    public Date getMdate(){
        return super.getMdate();
    }

    @Override
    @NotEmpty(fieldName = "EtrPlanAssetN002.finprodId", groups = {ValidateGroupEdit.class})
    public String getFinprodId(){
        return super.getFinprodId();
    }

    @Override
    @NotEmpty(fieldName = "EtrPlanAssetN002.ps", groups = {ValidateGroupEdit.class})
    public String getPs(){
        return super.getPs();
    }

    @Override
    @NotEmpty(fieldName = "EtrPlanAssetN002.ccy", groups = {ValidateGroupEdit.class})
    public String getCcy(){
        return super.getCcy();
    }

    @Override
    @Amount(fieldName = "EtrPlanAssetN002.entrustAmt", groups = {ValidateGroupEdit.class})
    public BigDecimal getEntrustAmt(){
        return super.getEntrustAmt();
    }

    @Override
    @Amount(fieldName = "EtrPlanAssetN002.entrustNum", groups = {ValidateGroupEdit.class})
    public BigDecimal getEntrustNum(){
        return super.getEntrustNum();
    }
    public BigDecimal getBusEntrustAmt(){
        return this.getEntrustAmt()==null?super.getEntrustNum():getEntrustAmt();
    }

    /**
     * 获取 counterType
     *
     * @return counterType
     */
    public String getCounterType() {
        return counterType;
    }

    /**
     * 设置 counterType
     *
     * @param counterType counterType
     */
    public void setCounterType(String counterType) {
        this.counterType = counterType;
    }
    public String getVdateStr(){
		return DateUtil.format(this.getVdate());
	}
    public String getMdateStr(){
		return DateUtil.format(this.getMdate());
	}

    public String getAssetType1Name() {
        return assetType1Name;
    }

    public void setAssetType1Name(String assetType1Name) {
        this.assetType1Name = assetType1Name;
    }

    public String getAssetType10Name() {
        return assetType10Name;
    }

    public void setAssetType10Name(String assetType10Name) {
        this.assetType10Name = assetType10Name;
    }

    public String getAssetType11Name() {
        return assetType11Name;
    }

    public void setAssetType11Name(String assetType11Name) {
        this.assetType11Name = assetType11Name;
    }


    public BigDecimal getAppProAvailableCash1() {
        return appProAvailableCash1;
    }

    public void setAppProAvailableCash1(BigDecimal appProAvailableCash1) {
        this.appProAvailableCash1 = appProAvailableCash1;
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
