/*
 * 文件名：EtrForwexDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiaominghui
 * 修改时间：2019年6月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrforwex.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.system.base.entity.SysUploadFileInfo;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.component.business.trade.dto.BussEtrInvestPersonInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.entrust.etrforwex.validator.EtrForwexValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 *  外汇远期委托
 * 〈功能详细描述〉
 * @author qiaominghui
 * @version 1.0 2019年6月18日
 * @see EtrForwexDto
 * @since 1.0
 */
@PageId("EtrForwex002")
@ValidLogic(targetClass = EtrForwexValidator.class , groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class EtrForwexDto  extends EtrEntrust implements ProcessEntity {

	
	 /**
     * 页面提交模式
     */
    private String pageModel;
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String elementId;
    
    /**
     * 选中的数据
     */
    private transient List<EtrForwexDto> selectedItem;
    
    /**
     * 委托关闭方式
     */
    private String CloseWay;
    
    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 委托操作类型
     */
    private String entrustOperationType;
    
    /**
     * 投资人员Dto
     */
    @Valid
    private BussEtrInvestPersonInfoDto bussEtrInvestPersonInfoDto;
    
    /**
     * 
     */
    private List<SysUploadFileInfo> fileArray;
    

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
     * 预计对应货币金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal expCorAmt;

    /**
     * 对应货币可用持仓
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal corCurPos;
	
    /**
     * 是否差额清算
     */
    private String isDifSqu;

    /**
     * 交易货币账户
     */
    private String dealCurAcct;

    /**
     * 对应货币账户
     */
    private String corCurAcct;

    /**
     * 人民币账户
     */
    private String cnyAcct;

    /**
     * 远端交割日（开始）
     */
    private Date fSettleDateStr;

    /**
     * 远端交割日（结束）
     */
    private Date fSettleDateEnd;

    /**
     * 外汇期限品种
     */
    private String termType;
    
    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;
    
    /**
     * 交割起始日
     */
    private Date settleVdate;
    
    /**
     * 交割截止日
     */
    private Date settleMdate;
    
    /**
     * 组合名称
     */
     private String portfolioName;

     /**
      * 完成额度
      */
     private BigDecimal finishAmt;
     
     /**
      * 交易完成度
      */
     private BigDecimal transactioncom;
     
     /**
      * 委托id 主键
      * {@inheritDoc}
      */
     @PrimaryKey(value=Field.PK_1)
     public String getEntrustId(){
         return super.getEntrustId();
     }
     
     /**
      * 组合id 非空判断
      */
     @NotEmpty(fieldName = "EtrForwex001.portfolioId", groups = {ValidateGroupEdit.class})
     public String getPortfolioId(){
         return super.getPortfolioId();
     }
     
     /**
      * 委托开始日 非空判断
      */
     @NotEmpty(fieldName = "EtrForwex001.vdate", groups = {ValidateGroupEdit.class})
     public Date getVdate(){
         return super.getVdate();
     }
     
     /**
      * 委托结束日 非空判断
      */
     @NotEmpty(fieldName = "EtrForwex001.mdate", groups = {ValidateGroupEdit.class})
     public Date getMdate(){
         return super.getMdate();
     }
     
     /**
      * 交易方向 非空判断
      */
     @NotEmpty(fieldName = "EtrForwex001.ps", groups = {ValidateGroupEdit.class})
     public String getPs(){
         return super.getPs();
     }
     
     /**
      * 委托金额 非空判断
      */
     @NotEmpty(fieldName = "EtrForwex001.entrustAmt", groups = {ValidateGroupEdit.class})
     public BigDecimal getEntrustAmt(){
         return super.getEntrustAmt();
     }
     
     /**
      * 全价 非空判断
      */
     @NotEmpty(fieldName = "EtrForwex001.price", groups = {ValidateGroupEdit.class})
     public BigDecimal getPrice(){
         return super.getPrice();
     } 
     
     /**
      * 投资目的  非空判断
      */
     @NotEmpty(fieldName = "EtrForwex002.invAim", groups = {ValidateGroupEdit.class})
     public String getInvAim(){
         return super.getInvAim();
     } 
     
     /**
      * 交易币种  非空判断
      */
     @NotEmpty(fieldName = "EtrForwex001.ccy", groups = {ValidateGroupEdit.class})
     public String getCcy(){
         return super.getCcy();
     } 
//     /**
//      * 可用持仓  非空判断
//      */
//     @NotEmpty(fieldName = "EtrForwex002.proAvailableCash", groups = {ValidateGroupEdit.class})
//     public BigDecimal getProAvailableCash(){
//         return super.getProAvailableCash();
//     } 
     
     
     /**
      * 委托状态中文参数值
      */
     public String getEntrustStatusStr(){
         return SysDataUtil.getNameFromDataDict(DictCode.委托状态, super.getEntrustStatus());
     }
     
     /**
      * 外汇交易类型中文参数值
      */
     public String getExcDealTypeStr(){
         return SysDataUtil.getNameFromDataDict(DictCode.外汇交易类型, this.getExcDealType());
     }
     
     /**
      * 货币对中文参数值
      */
     public String getCurPairStr(){
         return SysDataUtil.getNameFromDataDict(DictCode.货币对, this.getCurPair());
     }

     /**
      * 交易方向中文参数值
      */
     public String getPsStr(){
         return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
     }

     /**
      * 外汇期限类型中文参数值
      */
     public String getTermTypeStr(){
         return SysDataUtil.getNameFromDataDict(DictCode.外汇期限, this.getTermType());
     }

     /**
      * 交易币种中文参数值
      */
     public String getCcyStr(){
         return SysDataUtil.getNameFromDataDict(DictCode.币种, this.getCcy());
     }

     /**
      * 对应币种中文参数值
      */
     public String getcorCcyStr(){
         return SysDataUtil.getNameFromDataDict(DictCode.币种, this.getCorCcy());
     }

     /**
      * 根据交割起始日和交割截至日拼接成交割日区间
      */
     public String getSettleDate() {
    	 if(this.getSettleVdate()==null||this.getSettleMdate()==null) {
    		 return null;
    	 }
    	 return DateUtil.format(this.getSettleVdate())+" - "+DateUtil.format(this.getSettleMdate());
     }
     
     /**
      * 根据交割起始日和交割截至日拼接成交割日区间
      */
     public String getvdateStr() {
    	 if(this.getSettleVdate()==null||this.getSettleMdate()==null) {
    		 return null;
    	 }
    	 return DateUtil.format(this.getSettleVdate())+" - "+DateUtil.format(this.getSettleMdate());
     }
     
     /**
      * 根据交割起始日和交割截至日拼接成交割日区间
      */
     public String getmdateStr() {
    	 if(this.getSettleVdate()==null||this.getSettleMdate()==null) {
    		 return null;
    	 }
    	 return DateUtil.format(this.getSettleVdate())+" - "+DateUtil.format(this.getSettleMdate());
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
	 * 获取 pageModel
	 * @return pageModel.
	 */
	public String getPageModel() {
		return pageModel;
	}

	/**
	 * 设置 pageModel
	 * @param pageModel pageModel
	 */
	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
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
	public List<EtrForwexDto> getSelectedItem() {
		return selectedItem;
	}

	/**
	 * 设置 selectedItem
	 * @param selectedItem selectedItem
	 */
	public void setSelectedItem(List<EtrForwexDto> selectedItem) {
		this.selectedItem = selectedItem;
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
	 * 获取 entrustOperationType
	 * @return entrustOperationType.
	 */
	public String getEntrustOperationType() {
		return entrustOperationType;
	}

	/**
	 * 设置 entrustOperationType
	 * @param entrustOperationType entrustOperationType
	 */
	public void setEntrustOperationType(String entrustOperationType) {
		this.entrustOperationType = entrustOperationType;
	}

	/**
	 * 获取 bussEtrInvestPersonInfoDto
	 * @return bussEtrInvestPersonInfoDto.
	 */
	public BussEtrInvestPersonInfoDto getBussEtrInvestPersonInfoDto() {
		return bussEtrInvestPersonInfoDto;
	}

	/**
	 * 设置 bussEtrInvestPersonInfoDto
	 * @param bussEtrInvestPersonInfoDto bussEtrInvestPersonInfoDto
	 */
	public void setBussEtrInvestPersonInfoDto(BussEtrInvestPersonInfoDto bussEtrInvestPersonInfoDto) {
		this.bussEtrInvestPersonInfoDto = bussEtrInvestPersonInfoDto;
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
	 * 获取 excDealType
	 * @return excDealType.
	 */
    @NotEmpty(fieldName = "EtrForwex002.excDealType", groups = {ValidateGroupEdit.class})
	public String getExcDealType() {
		return excDealType;
	}

	/**
	 * 设置 excDealType
	 * @param excDealType excDealType
	 */
	public void setExcDealType(String excDealType) {
		this.excDealType = excDealType;
	}

	/**
	 * 获取 curPair
	 * @return curPair.
	 */
	@NotEmpty(fieldName = "EtrForwex001.curPair", groups = {ValidateGroupEdit.class})
	public String getCurPair() {
		return curPair;
	}

	/**
	 * 设置 curPair
	 * @param curPair curPair
	 */
	public void setCurPair(String curPair) {
		this.curPair = curPair;
	}

	/**
	 * 获取 corCcy
	 * @return corCcy.
	 */
	public String getCorCcy() {
		return corCcy;
	}

	/**
	 * 设置 corCcy
	 * @param corCcy corCcy
	 */
	public void setCorCcy(String corCcy) {
		this.corCcy = corCcy;
	}

	/**
	 * 获取 expCorAmt
	 * @return expCorAmt.
	 */
	public BigDecimal getExpCorAmt() {
		return expCorAmt;
	}

	/**
	 * 设置 expCorAmt
	 * @param expCorAmt expCorAmt
	 */
	public void setExpCorAmt(BigDecimal expCorAmt) {
		this.expCorAmt = expCorAmt;
	}

	/**
	 * 获取 corCurPos
	 * @return corCurPos.
	 */
	public BigDecimal getCorCurPos() {
		return corCurPos;
	}

	/**
	 * 设置 corCurPos
	 * @param corCurPos corCurPos
	 */
	public void setCorCurPos(BigDecimal corCurPos) {
		this.corCurPos = corCurPos;
	}

	/**
	 * 获取 isDifSqu
	 * @return isDifSqu.
	 */
	@NotEmpty(fieldName = "EtrForwex002.isDifSqu", groups = {ValidateGroupEdit.class})
	public String getIsDifSqu() {
		return isDifSqu;
	}

	/**
	 * 设置 isDifSqu
	 * @param isDifSqu isDifSqu
	 */
	public void setIsDifSqu(String isDifSqu) {
		this.isDifSqu = isDifSqu;
	}

	/**
	 * 获取 dealCurAcct
	 * @return dealCurAcct.
	 */
	public String getDealCurAcct() {
		return dealCurAcct;
	}

	/**
	 * 设置 dealCurAcct
	 * @param dealCurAcct dealCurAcct
	 */
	public void setDealCurAcct(String dealCurAcct) {
		this.dealCurAcct = dealCurAcct;
	}

	/**
	 * 获取 corCurAcct
	 * @return corCurAcct.
	 */
	public String getCorCurAcct() {
		return corCurAcct;
	}

	/**
	 * 设置 corCurAcct
	 * @param corCurAcct corCurAcct
	 */
	public void setCorCurAcct(String corCurAcct) {
		this.corCurAcct = corCurAcct;
	}

	/**
	 * 获取 cnyAcct
	 * @return cnyAcct.
	 */
	public String getCnyAcct() {
		return cnyAcct;
	}

	/**
	 * 设置 cnyAcct
	 * @param cnyAcct cnyAcct
	 */
	public void setCnyAcct(String cnyAcct) {
		this.cnyAcct = cnyAcct;
	}

	/**
	 * 获取 fSettleDateStr
	 * @return fSettleDateStr.
	 */
	public Date getfSettleDateStr() {
		return fSettleDateStr;
	}

	/**
	 * 设置 fSettleDateStr
	 * @param fSettleDateStr fSettleDateStr
	 */
	public void setfSettleDateStr(Date fSettleDateStr) {
		this.fSettleDateStr = fSettleDateStr;
	}

	/**
	 * 获取 fSettleDateEnd
	 * @return fSettleDateEnd.
	 */
	public Date getfSettleDateEnd() {
		return fSettleDateEnd;
	}

	/**
	 * 设置 fSettleDateEnd
	 * @param fSettleDateEnd fSettleDateEnd
	 */
	public void setfSettleDateEnd(Date fSettleDateEnd) {
		this.fSettleDateEnd = fSettleDateEnd;
	}

	/**
	 * 获取 termType
	 * @return termType.
	 */
	public String getTermType() {
		return termType;
	}

	/**
	 * 设置 termType
	 * @param termType termType
	 */
	public void setTermType(String termType) {
		this.termType = termType;
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
	 * 获取 settleVdate
	 * @return settleVdate.
	 */
	public Date getSettleVdate() {
		return settleVdate;
	}

	/**
	 * 设置 settleVdate
	 * @param settleVdate settleVdate
	 */
	public void setSettleVdate(Date settleVdate) {
		this.settleVdate = settleVdate;
	}

	/**
	 * 获取 settleMdate
	 * @return settleMdate.
	 */
	public Date getSettleMdate() {
		return settleMdate;
	}

	/**
	 * 设置 settleMdate
	 * @param settleMdate settleMdate
	 */
	public void setSettleMdate(Date settleMdate) {
		this.settleMdate = settleMdate;
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
	

}
