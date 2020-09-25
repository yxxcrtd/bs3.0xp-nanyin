/*
 * 文件名：TrdIRSTradeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiaominghui
 * 修改时间：2019年7月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.irs.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.StlFinProductAcct;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.common.base.entity.WflTrdProductDealOth;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.risk.dto.RiskCreditListCommonDto;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.trade.irs.validator.TrdIRSTradeValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 利率互换dto
 * 〈功能详细描述〉
 * @author qiaominghui
 * @version 1.0 2019年7月10日
 * @see TrdIRSTradeDto
 * @since 1.0
 */
@PageId("TrdIRSTrade002")
@ValidLogic(targetClass = TrdIRSTradeValidator.class , groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class TrdIRSTradeDto extends TrdProductDeal implements ProcessEntity{

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1917150730932662993L;
	
	/**
     * 委托代码
     */
    private String entrustId;
    
    /**
     * 组合代码
     */
    @NotEmpty(fieldName = "TrdIRSTrade.portfolioId",groups = ValidateGroupEdit.class)
    private String portfolioId;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 画面模式
     */
    private String pageModel;
    
    /**
     * workflowId
     */
    private String workflowId;
    
    /**
     * workflowStatus
     */
    private String workflowStatus;
    //真实组合代码
    private String actPortfolioId;
    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;
    /**
     * 附件上传
     */
    private List<UploadFile> uploadFileList;

    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;
    
    /**
     * 人员信息
     */
    @Valid
    private TrdInvestPersonInfo002Dto trdInvestPersonInfo;
	

    /**
     * 互换方式
     */
    @NotEmpty(fieldName = "TrdIRSTrade.irsType",groups = ValidateGroupEdit.class)
	private String irsType;
	
	/**
	 * 期限品种
	 */
    //@NotEmpty(fieldName = "TrdIRSTrade.termType",groups = ValidateGroupEdit.class)
	private String termType;
	
    /**
     * 期限(天)
     */
    @NotEmpty(fieldName = "TrdIRSTrade.term",groups = ValidateGroupEdit.class)
    private Long term;
    
    /**
     * 名义本金（万元）
     */
    @NotEmpty(fieldName = "TrdIRSTrade.prin",groups = ValidateGroupEdit.class)
	private BigDecimal prin;
	
	/**
	 *固定利率付息频率
	 */
    @NotEmpty(fieldName = "TrdIRSTrade.payCycle",groups = ValidateGroupEdit.class)
	private String fixPayCycle;
	
	/**
	 * 固定利率首期起息日
	 */
    @NotEmpty(fieldName = "TrdIRSTrade.firstPayDate",groups = ValidateGroupEdit.class)
	private Date firstPayDateR;
	
    /**
     * 挂钩利率
     */
    @NotEmpty(fieldName = "TrdIRSTrade.intType",groups = ValidateGroupEdit.class)
	private String intType;
	
	/**
	 * 利差(BP)
	 */
    @NotEmpty(fieldName = "TrdIRSTrade.spreadRate",groups = ValidateGroupEdit.class)
	private BigDecimal spreadRate;
	
	/**
	 *浮动利率付息频率
	 */
    @NotEmpty(fieldName = "TrdIRSTrade.payCycle",groups = ValidateGroupEdit.class)
	private String floatPayCycle;
	
	/**
	 * 浮动利率首期起息日
	 */
    @NotEmpty(fieldName = "TrdIRSTrade.firstPayDate",groups = ValidateGroupEdit.class)
	private Date firstPayDateF;
	
	/**
	 * 交易日历
	 */
    @NotEmpty(fieldName = "TrdIRSTrade.calendarId",groups = ValidateGroupEdit.class)
	private String calendarId;
	
	/**
	 * 计息营业日准则
	 */
    @NotEmpty(fieldName = "TrdIRSTrade.endTdayRule",groups = ValidateGroupEdit.class)
	private String endTdayRule;
	
	/**
	 * 付息营业日准则
	 */
    @NotEmpty(fieldName = "TrdIRSTrade.payTdayRule",groups = ValidateGroupEdit.class)
	private String payTdayRule;
	
	/**
	 * 交易手续费
	 */
    @NotEmpty(fieldName = "TrdIRSTrade.feeB06",groups = ValidateGroupEdit.class)
	private BigDecimal feeB06;

	/**
	 * 结算费
	 */
    @NotEmpty(fieldName = "TrdIRSTrade.feeB07",groups = ValidateGroupEdit.class)
	private BigDecimal feeB07;
	
	/**
	 * 固定计息基础
	 */
    @NotEmpty(fieldName = "TrdIRSTrade.Basis",groups = ValidateGroupEdit.class)
	private String fixBasis;
	
	/**
	 * 浮动计息基础
	 */
    @NotEmpty(fieldName = "TrdIRSTrade.Basis",groups = ValidateGroupEdit.class)
	private String floatBasis;
	
	/**
	 * 首期利率确定日
	 */
    @NotEmpty(fieldName = "TrdIRSTrade.firstResetDate",groups = ValidateGroupEdit.class)
	private Date firstResetDate;
	
	/**
	 * 利率重置频率
	 */
    @NotEmpty(fieldName = "TrdIRSTrade.resetFreq",groups = ValidateGroupEdit.class)
	private String resetFreq;
	
	/**
	 * 本方账户
	 */
    @NotEmpty(fieldName = "TrdIRSTrade.dealAcct",groups = ValidateGroupEdit.class)
	private String dealAcct;
	 
	/**
	 * 对手方账户
	 */
    @NotEmpty(fieldName = "TrdIRSTrade.corAcct",groups = ValidateGroupEdit.class)
	private String corAcct;
	
	/**
     * 撤销说明
     */
    private String revokeReason;
    
    /**
     * 金融产品交易
     */
    @Valid
    private TrdProductDeal trdProductDeal;
    
    /**
     * 金融产品交易附属
     */
    private TrdProductDealAdd trdProductDealAdd;
    
    /**
     * 组合交易表
     */
    private PtlTrade ptlTrade;
    
    /**
     * 金融产品账户
     */
    private StlFinProductAcct stlFinProductAcct;
    
    /**
     * 交易对手金融产品账户
     */
    private StlFinProductAcct stlFinProductAcctCor;
    
    /**
     *交易费用明细表
     */
    private List<TrdTradeFeeDetail> trdTradeFeeDetailList;
    
    /**
     * 金融交易产品
     */
    private FinTradeProduct finTradeProduct;
    
    /**
     * 金融产品分类
     */
    private FinProductType finProductType;
    
    /**
     * 金融产品交易业务流程其他信息Entity
     */
    private WflTrdProductDealOth wflTrdProductDealOth;
    
    /**
     * 金融产品交易扩展Entity
     */
    private PaTrdProductDeal paTrdProductDeal;
    
    /**
     * 有效状态
     */
    private String validStatus;
    
    private RiskCreditListCommonDto riskCreditListCommonDto;
    
    
    /**
     * 交易日期
     * {@inheritDoc}
     */
    @NotEmpty(fieldName = "TrdIRSTrade.tradeDate",groups = ValidateGroupEdit.class)
    public Date getTradeDate() {
    	return super.getTradeDate();
    }
    
    /**
     * 起息日
     * {@inheritDoc}
     */
    @NotEmpty(fieldName = "TrdIRSTrade.vdate",groups = ValidateGroupEdit.class)
    public Date getVdate() {
    	return super.getVdate();
    }
    
    /**
     * 到期日
     * {@inheritDoc}
     */
    @NotEmpty(fieldName = "TrdIRSTrade.mdate",groups = ValidateGroupEdit.class)
    public Date getMdate() {
    	return super.getMdate();
    }
    
    /**
     * 名义本金币种
     * {@inheritDoc}
     */
    @NotEmpty(fieldName = "TrdIRSTrade.ccy",groups = ValidateGroupEdit.class)
    public String getCcy() {
    	return super.getCcy();
    }
    
    /**
     *国定利率
     * {@inheritDoc}
     */
    @NotEmpty(fieldName = "TrdIRSTrade.yield",groups = ValidateGroupEdit.class)
    public BigDecimal getYield() {
    	return super.getYield();
    }
    
    
    /**
	 * 有效状态str
	 */
    public String getValidStatusStr(){
    	return SysDataUtil.getNameFromDataDict(DictCode.交易流程状态, this.getValidStatus());
    }
    
    /**
     * 交易日期Str
     */
    public String getTradeDateStr() {
    	return DateUtil.format(super.getTradeDate());
    }
    
    /**
     * irsTypeStr 互换方式Str
     */
    public String getIrsTypeStr() {
    	return SysDataUtil.getNameFromDataDict(DictCode.互换方式, this.getIrsType());
    }
    
    /**
     * vdateStr 起息日Str
     */
    public String getVdateStr() {
    	return DateUtil.format(super.getVdate());
    }
    
    /**
     * mdateStr 到息日Str
     */
    public String getMdateStr() {
    	return DateUtil.format(super.getMdate());
    }
    
    /**
     * ccyStr  币种Str
     */
    public String getCcyStr() {
    	return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
    }
    
    /**
     * fixPayCycleStr  固定付息频率
     */
    public String getFixPayCycleStr() {
    	return SysDataUtil.getNameFromDataDict(DictCode.频率, this.getFixPayCycle());
    }
    
    /**
     * firstPayDateRStr 固定端首次付息日
     */
    public String getFirstPayDateRStr() {
    	return DateUtil.format(this.getFirstPayDateR());
    }
    /**
     * intTypeStr
     */
    public String getIntTypeStr() {
    	return SysDataUtil.getNameFromDataDict(DictCode.利率行情, this.getIntType());
    }
    
    /**
     * floatPayCycleStr  浮动付息频率
     */
    public String getFloatPayCycleStr() {
    	return SysDataUtil.getNameFromDataDict(DictCode.频率, this.getFloatPayCycle());
    }
    
    /**
     * firstPayDateFStr 浮动端首次付息日
     */
    public String getFirstPayDateFStr() {
    	return DateUtil.format(this.getFirstPayDateF());
    }
    
    
    
    
    /**
     * 取得 交易编号
     *
     * @return 交易编号
     */
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getTradeId() {
        return super.getTradeId();
    }
    /**
     * 
     * 业务类型
     */
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getBusiType(){
        return super.getBusiType();
    }
	/**
	 * 获取 entrustId
	 * @return entrustId.
	 */
    @PrimaryKey(PrimaryKey.Field.PK_3)
	public String getEntrustId() {
		return entrustId;
	}
	/**
	 * 设置 entrustId
	 * @param entrustId entrustId
	 */
	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}
	/**
	 * 获取 portfolioId
	 * @return portfolioId.
	 */
	public String getPortfolioId() {
		return portfolioId;
	}
	/**
	 * 设置 portfolioId
	 * @param portfolioId portfolioId
	 */
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
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
	 * 获取 workflowId
	 * @return workflowId.
	 */
	public String getWorkflowId() {
		return workflowId;
	}
	/**
	 * 设置 workflowId
	 * @param workflowId workflowId
	 */
	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}
	/**
	 * 获取 workflowStatus
	 * @return workflowStatus.
	 */
	public String getWorkflowStatus() {
		return workflowStatus;
	}
	/**
	 * 设置 workflowStatus
	 * @param workflowStatus workflowStatus
	 */
	public void setWorkflowStatus(String workflowStatus) {
		this.workflowStatus = workflowStatus;
	}
	/**
	 * 获取 bussTrdTransferOrder
	 * @return bussTrdTransferOrder.
	 */
	public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
		return bussTrdTransferOrder;
	}
	/**
	 * 设置 bussTrdTransferOrder
	 * @param bussTrdTransferOrder bussTrdTransferOrder
	 */
	public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
		this.bussTrdTransferOrder = bussTrdTransferOrder;
	}
	/**
	 * 获取 uploadFileList
	 * @return uploadFileList.
	 */
	public List<UploadFile> getUploadFileList() {
		return uploadFileList;
	}
	/**
	 * 设置 uploadFileList
	 * @param uploadFileList uploadFileList
	 */
	public void setUploadFileList(List<UploadFile> uploadFileList) {
		this.uploadFileList = uploadFileList;
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
	 * 获取 trdInvestPersonInfo
	 * @return trdInvestPersonInfo.
	 */
	public TrdInvestPersonInfo002Dto getTrdInvestPersonInfo() {
		return trdInvestPersonInfo;
	}
	/**
	 * 设置 trdInvestPersonInfo
	 * @param trdInvestPersonInfo trdInvestPersonInfo
	 */
	public void setTrdInvestPersonInfo(TrdInvestPersonInfo002Dto trdInvestPersonInfo) {
		this.trdInvestPersonInfo = trdInvestPersonInfo;
	}
	/**
	 * 获取 irsType
	 * @return irsType.
	 */
	public String getIrsType() {
		return irsType;
	}
	/**
	 * 设置 irsType
	 * @param irsType irsType
	 */
	public void setIrsType(String irsType) {
		this.irsType = irsType;
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
	 * 获取 term
	 * @return term.
	 */
	public Long getTerm() {
		return term;
	}
	/**
	 * 设置 term
	 * @param term term
	 */
	public void setTerm(Long term) {
		this.term = term;
	}
	/**
	 * 获取 prin
	 * @return prin.
	 */
	public BigDecimal getPrin() {
		return prin;
	}
	/**
	 * 设置 prin
	 * @param prin prin
	 */
	public void setPrin(BigDecimal prin) {
		this.prin = prin;
	}
	/**
	 * 获取 fixPayCycle
	 * @return fixPayCycle.
	 */
	public String getFixPayCycle() {
		return fixPayCycle;
	}
	/**
	 * 设置 fixPayCycle
	 * @param fixPayCycle fixPayCycle
	 */
	public void setFixPayCycle(String fixPayCycle) {
		this.fixPayCycle = fixPayCycle;
	}
	/**
	 * 获取 intType
	 * @return intType.
	 */
	public String getIntType() {
		return intType;
	}
	/**
	 * 设置 intType
	 * @param intType intType
	 */
	public void setIntType(String intType) {
		this.intType = intType;
	}
	/**
	 * 获取 spreadRate
	 * @return spreadRate.
	 */
	public BigDecimal getSpreadRate() {
		return spreadRate;
	}
	/**
	 * 设置 spreadRate
	 * @param spreadRate spreadRate
	 */
	public void setSpreadRate(BigDecimal spreadRate) {
		this.spreadRate = spreadRate;
	}
	/**
	 * 获取 floatPayCycle
	 * @return floatPayCycle.
	 */
	public String getFloatPayCycle() {
		return floatPayCycle;
	}
	/**
	 * 设置 floatPayCycle
	 * @param floatPayCycle floatPayCycle
	 */
	public void setFloatPayCycle(String floatPayCycle) {
		this.floatPayCycle = floatPayCycle;
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
	 * 获取 endTdayRule
	 * @return endTdayRule.
	 */
	public String getEndTdayRule() {
		return endTdayRule;
	}
	/**
	 * 设置 endTdayRule
	 * @param endTdayRule endTdayRule
	 */
	public void setEndTdayRule(String endTdayRule) {
		this.endTdayRule = endTdayRule;
	}
	/**
	 * 获取 payTdayRule
	 * @return payTdayRule.
	 */
	public String getPayTdayRule() {
		return payTdayRule;
	}
	/**
	 * 设置 payTdayRule
	 * @param payTdayRule payTdayRule
	 */
	public void setPayTdayRule(String payTdayRule) {
		this.payTdayRule = payTdayRule;
	}
	/**
	 * 获取 feeB06
	 * @return feeB06.
	 */
	public BigDecimal getFeeB06() {
		return feeB06;
	}
	/**
	 * 设置 feeB06
	 * @param feeB06 feeB06
	 */
	public void setFeeB06(BigDecimal feeB06) {
		this.feeB06 = feeB06;
	}
	/**
	 * 获取 feeB07
	 * @return feeB07.
	 */
	public BigDecimal getFeeB07() {
		return feeB07;
	}
	/**
	 * 设置 feeB07
	 * @param feeB07 feeB07
	 */
	public void setFeeB07(BigDecimal feeB07) {
		this.feeB07 = feeB07;
	}
	/**
	 * 获取 fixBasis
	 * @return fixBasis.
	 */
	public String getFixBasis() {
		return fixBasis;
	}
	/**
	 * 设置 fixBasis
	 * @param fixBasis fixBasis
	 */
	public void setFixBasis(String fixBasis) {
		this.fixBasis = fixBasis;
	}
	/**
	 * 获取 firstPayDateR
	 * @return firstPayDateR.
	 */
	public Date getFirstPayDateR() {
		return firstPayDateR;
	}
	/**
	 * 设置 firstPayDateR
	 * @param firstPayDateR firstPayDateR
	 */
	public void setFirstPayDateR(Date firstPayDateR) {
		this.firstPayDateR = firstPayDateR;
	}
	/**
	 * 获取 floatBasis
	 * @return floatBasis.
	 */
	public String getFloatBasis() {
		return floatBasis;
	}
	/**
	 * 设置 floatBasis
	 * @param floatBasis floatBasis
	 */
	public void setFloatBasis(String floatBasis) {
		this.floatBasis = floatBasis;
	}
	/**
	 * 获取 firstPayDateF
	 * @return firstPayDateF.
	 */
	public Date getFirstPayDateF() {
		return firstPayDateF;
	}
	/**
	 * 设置 firstPayDateF
	 * @param firstPayDateF firstPayDateF
	 */
	public void setFirstPayDateF(Date firstPayDateF) {
		this.firstPayDateF = firstPayDateF;
	}
	/**
	 * 获取 firstResetDate
	 * @return firstResetDate.
	 */
	public Date getFirstResetDate() {
		return firstResetDate;
	}
	/**
	 * 设置 firstResetDate
	 * @param firstResetDate firstResetDate
	 */
	public void setFirstResetDate(Date firstResetDate) {
		this.firstResetDate = firstResetDate;
	}
	/**
	 * 获取 resetFreq
	 * @return resetFreq.
	 */
	public String getResetFreq() {
		return resetFreq;
	}
	/**
	 * 设置 resetFreq
	 * @param resetFreq resetFreq
	 */
	public void setResetFreq(String resetFreq) {
		this.resetFreq = resetFreq;
	}
	/**
	 * 获取 dealAcct
	 * @return dealAcct.
	 */
	public String getDealAcct() {
		return dealAcct;
	}
	/**
	 * 设置 dealAcct
	 * @param dealAcct dealAcct
	 */
	public void setDealAcct(String dealAcct) {
		this.dealAcct = dealAcct;
	}
	/**
	 * 获取 corAcct
	 * @return corAcct.
	 */
	public String getCorAcct() {
		return corAcct;
	}
	/**
	 * 设置 corAcct
	 * @param corAcct corAcct
	 */
	public void setCorAcct(String corAcct) {
		this.corAcct = corAcct;
	}
	/**
	 * 获取 revokeReason
	 * @return revokeReason.
	 */
	public String getRevokeReason() {
		return revokeReason;
	}
	/**
	 * 设置 revokeReason
	 * @param revokeReason revokeReason
	 */
	public void setRevokeReason(String revokeReason) {
		this.revokeReason = revokeReason;
	}
	/**
	 * 获取 trdProductDeal
	 * @return trdProductDeal.
	 */
	public TrdProductDeal getTrdProductDeal() {
		return trdProductDeal;
	}
	/**
	 * 设置 trdProductDeal
	 * @param trdProductDeal trdProductDeal
	 */
	public void setTrdProductDeal(TrdProductDeal trdProductDeal) {
		this.trdProductDeal = trdProductDeal;
	}
	/**
	 * 获取 trdProductDealAdd
	 * @return trdProductDealAdd.
	 */
	public TrdProductDealAdd getTrdProductDealAdd() {
		return trdProductDealAdd;
	}
	/**
	 * 设置 trdProductDealAdd
	 * @param trdProductDealAdd trdProductDealAdd
	 */
	public void setTrdProductDealAdd(TrdProductDealAdd trdProductDealAdd) {
		this.trdProductDealAdd = trdProductDealAdd;
	}
	/**
	 * 获取 ptlTrade
	 * @return ptlTrade.
	 */
	public PtlTrade getPtlTrade() {
		return ptlTrade;
	}
	/**
	 * 设置 ptlTrade
	 * @param ptlTrade ptlTrade
	 */
	public void setPtlTrade(PtlTrade ptlTrade) {
		this.ptlTrade = ptlTrade;
	}
	/**
	 * 获取 stlFinProductAcct
	 * @return stlFinProductAcct.
	 */
	public StlFinProductAcct getStlFinProductAcct() {
		return stlFinProductAcct;
	}
	/**
	 * 设置 stlFinProductAcct
	 * @param stlFinProductAcct stlFinProductAcct
	 */
	public void setStlFinProductAcct(StlFinProductAcct stlFinProductAcct) {
		this.stlFinProductAcct = stlFinProductAcct;
	}
	/**
	 * 获取 stlFinProductAcctCor
	 * @return stlFinProductAcctCor.
	 */
	public StlFinProductAcct getStlFinProductAcctCor() {
		return stlFinProductAcctCor;
	}
	/**
	 * 设置 stlFinProductAcctCor
	 * @param stlFinProductAcctCor stlFinProductAcctCor
	 */
	public void setStlFinProductAcctCor(StlFinProductAcct stlFinProductAcctCor) {
		this.stlFinProductAcctCor = stlFinProductAcctCor;
	}
	/**
	 * 获取 trdTradeFeeDetailList
	 * @return trdTradeFeeDetailList.
	 */
	public List<TrdTradeFeeDetail> getTrdTradeFeeDetailList() {
		return trdTradeFeeDetailList;
	}

	/**
	 * 设置 trdTradeFeeDetailList
	 * @param trdTradeFeeDetailList trdTradeFeeDetailList
	 */
	public void setTrdTradeFeeDetailList(List<TrdTradeFeeDetail> trdTradeFeeDetailList) {
		this.trdTradeFeeDetailList = trdTradeFeeDetailList;
	}
	/**
	 * 获取 finTradeProduct
	 * @return finTradeProduct.
	 */
	public FinTradeProduct getFinTradeProduct() {
		return finTradeProduct;
	}
	/**
	 * 设置 finTradeProduct
	 * @param finTradeProduct finTradeProduct
	 */
	public void setFinTradeProduct(FinTradeProduct finTradeProduct) {
		this.finTradeProduct = finTradeProduct;
	}
	/**
	 * 获取 finProductType
	 * @return finProductType.
	 */
	public FinProductType getFinProductType() {
		return finProductType;
	}
	/**
	 * 设置 finProductType
	 * @param finProductType finProductType
	 */
	public void setFinProductType(FinProductType finProductType) {
		this.finProductType = finProductType;
	}
	/**
	 * 获取 wflTrdProductDealOth
	 * @return wflTrdProductDealOth.
	 */
	public WflTrdProductDealOth getWflTrdProductDealOth() {
		return wflTrdProductDealOth;
	}
	/**
	 * 设置 wflTrdProductDealOth
	 * @param wflTrdProductDealOth wflTrdProductDealOth
	 */
	public void setWflTrdProductDealOth(WflTrdProductDealOth wflTrdProductDealOth) {
		this.wflTrdProductDealOth = wflTrdProductDealOth;
	}

	/**
	 * 获取 paTrdProductDeal
	 * @return paTrdProductDeal.
	 */
	public PaTrdProductDeal getPaTrdProductDeal() {
		return paTrdProductDeal;
	}

	/**
	 * 设置 paTrdProductDeal
	 * @param paTrdProductDeal paTrdProductDeal
	 */
	public void setPaTrdProductDeal(PaTrdProductDeal paTrdProductDeal) {
		this.paTrdProductDeal = paTrdProductDeal;
	}

	/**
	 * 获取 validStatus
	 * @return validStatus.
	 */
	public String getValidStatus() {
		return validStatus;
	}

	/**
	 * 设置 validStatus
	 * @param validStatus validStatus
	 */
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}

    
    /**
     * 获取 actPortfolioId
     * @return actPortfolioId.
     */
    public String getActPortfolioId() {
        return actPortfolioId;
    }

    
    /**
     * 设置 actPortfolioId
     * @param actPortfolioId actPortfolioId
     */
    public void setActPortfolioId(String actPortfolioId) {
        this.actPortfolioId = actPortfolioId;
    }

    public RiskCreditListCommonDto getRiskCreditListCommonDto() {
        return riskCreditListCommonDto;
    }

    public void setRiskCreditListCommonDto(RiskCreditListCommonDto riskCreditListCommonDto) {
        this.riskCreditListCommonDto = riskCreditListCommonDto;
    }
    
}
