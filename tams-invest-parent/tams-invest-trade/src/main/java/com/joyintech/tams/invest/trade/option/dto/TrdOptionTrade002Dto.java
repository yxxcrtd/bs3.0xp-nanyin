package com.joyintech.tams.invest.trade.option.dto;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.trade.option.validator.TrdOptionTrade002DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 期权交易新增dto
 */
@ValidLogic(targetClass = TrdOptionTrade002DtoValidator.class)
public class TrdOptionTrade002Dto extends TrdFinProductDealTotal implements ProcessEntity {
    /**
     * 汇总交易编号
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    private String totleTradeId;
    /**
     * 交易编号
     */
    private String tradeId;
    /**
     * 委托编号
     */
    private String entrustId;
    /**
     * 实体组合代码
     */
    private String actPortfolioId;
    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 画面模式
     */
    private String pageModel;

    /**
     * 工作流状态
     */
    private String workflowStatus;

    /**
     * 是否保本
     */
    private String isGuarantee;

    /**
     * 组合代码
     */
    @NotEmpty(fieldName = "TrdOptionTrade001.portfolioId")
    private String portfolioId;
    /**
     * 合约标的
     */
    @NotEmpty(fieldName = "TrdOptionTrade001.targetId")
    private String targetId;
    /**
     * 合约类型
     */
    @NotEmpty(fieldName = "TrdOptionTrade001.contractType")
    private String contractType;
    /**
     * 交易方向
     */
    @NotEmpty(fieldName = "TrdOptionTrade001.ps")
    private String ps;
    /**
     * 交易日历
     */
    @NotEmpty(fieldName = "TrdOptionTrade002.calendarId")
    private String calendarId;
    /**
     * 交易日期
     */
    @NotEmpty(fieldName = "TrdOptionTrade001.tradeDate")
    private Date tradeDate;
    /**
     * 支付日期
     */
    private Date payDate;
    /**
     * 起息日
     */
    @NotEmpty(fieldName = "TrdOptionTrade001.vdate")
    private Date vdate;
    /**
     * 到期日
     */
    @NotEmpty(fieldName = "TrdOptionTrade001.mdate")
    private Date mdate;
    /**
     * 期限
     */
    @NotEmpty(fieldName = "TrdOptionTrade001.term")
    private Long term;
    /**
     * 币种
     */
    @NotEmpty(fieldName = "TrdOptionTrade001.ccy")
    private String ccy;
    /**
     * 数量
     */
    @NotEmpty(fieldName = "TrdOptionTrade001.amount")
    private BigDecimal amount;
    /**
     * 行权日基准日
     */
    @NotEmpty(fieldName = "TrdOptionTrade002.exerciseDate")
    private Date exerciseDate;
    /**
     * 行权方式
     */
    @NotEmpty(fieldName = "TrdOptionTrade001.exerciseType")
    private String exerciseType;
    /**
     * 期权费类型
     */
    @NotEmpty(fieldName = "TrdOptionTrade002.optFeePayType")
    private String optFeePayType;
    /**
     * 期权费
     */
    private BigDecimal optFee;

    /**
     * 组合T日可用现金
     */
    private BigDecimal avalibleCash;

    /**
     * 交易类金融产品
     */
    @Valid
    private FinTradeProduct finTradeProduct;

    /**
     * 交易类金融产品附属
     */
    @Valid
    private FinTradeProductAdd finTradeProductAdd;

    /**
     * 利率结构明细
     */
    private FinRateInfoStructure finRateInfoStructure;
    /**
     * 金融产品分类
     */
    private FinProductType finProductType;
    /**
     * 现金流信息
     */
    private List<FinCashInfo> finCashInfo;
    /**
     * 现金流利率信息
     */
    private FinRateInfo finRateInfo;
    /**
     * 行权规则
     */
    private FinExerciseRule finExerciseRule;
    /**
     * 行权计划
     */
    private FinExercisePlan finExercisePlan;
    /**
     * 金融产品交易
     */
    private TrdProductDeal trdProductDeal;
    /**
     * 金融产品交易附属
     */
    private TrdProductDealAdd trdProductDealAdd;
    /**
     * 金融产品交易汇总附属
     */
    private TrdDealTotalAdd trdDealTotalAdd;
    /**
     * 组合交易表
     */
    private PtlTrade ptlTrade;

    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

    /**
     * 人员信息
     */
    @Valid
    private TrdInvestPersonInfo002Dto trdInvestPersonInfo;

    /**
     * 附件上传
     */
    private List<UploadFile> fileArray;

    /**
     * 撤销说明
     */
    private String revokeReason;

    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;

    /**
     * 金融产品交易汇总扩展表
     */
    private PaTrdFinProductDealTotal paTrdFinProductDealTotal;

    /**
     * 持有目的 HOLD_PURPOSE 中债新增 20200409
     */
    private String holdPurpose;
    
    /**
     * 获取 汇总交易编号
     *
     * @return totleTradeId 汇总交易编号
     */
    public String getTotleTradeId() {
        return this.totleTradeId;
    }

    /**
     * 设置 汇总交易编号
     *
     * @param totleTradeId 汇总交易编号
     */
    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId;
    }

    /**
     * 获取 委托编号
     *
     * @return entrustId 委托编号
     */
    public String getEntrustId() {
        return this.entrustId;
    }

    /**
     * 设置 委托编号
     *
     * @param entrustId 委托编号
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    /**
     * 获取 实体组合代码
     *
     * @return actPortfolioId 实体组合代码
     */
    public String getActPortfolioId() {
        return this.actPortfolioId;
    }

    /**
     * 设置 实体组合代码
     *
     * @param actPortfolioId 实体组合代码
     */
    public void setActPortfolioId(String actPortfolioId) {
        this.actPortfolioId = actPortfolioId;
    }

    /**
     * 获取 操作类型
     *
     * @return operateType 操作类型
     */
    public String getOperateType() {
        return this.operateType;
    }

    /**
     * 设置 操作类型
     *
     * @param operateType 操作类型
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 画面模式
     *
     * @return pageModel 画面模式
     */
    public String getPageModel() {
        return this.pageModel;
    }

    /**
     * 设置 画面模式
     *
     * @param pageModel 画面模式
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    /**
     * 获取 工作流状态
     *
     * @return workflowStatus 工作流状态
     */
    public String getWorkflowStatus() {
        return this.workflowStatus;
    }

    /**
     * 设置 工作流状态
     *
     * @param workflowStatus 工作流状态
     */
    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

    /**
     * 获取 组合代码
     *
     * @return portfolioId 组合代码
     */
    public String getPortfolioId() {
        return this.portfolioId;
    }

    /**
     * 设置 组合代码
     *
     * @param portfolioId 组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 合约标的
     *
     * @return targetId 合约标的
     */
    public String getTargetId() {
        return this.targetId;
    }

    /**
     * 设置 合约标的
     *
     * @param targetId 合约标的
     */
    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    /**
     * 获取 合约类型
     *
     * @return contractType 合约类型
     */
    public String getContractType() {
        return this.contractType;
    }

    /**
     * 设置 合约类型
     *
     * @param contractType 合约类型
     */
    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    /**
     * 获取 交易方向
     *
     * @return ps 交易方向
     */
    public String getPs() {
        return this.ps;
    }

    /**
     * 设置 交易方向
     *
     * @param ps 交易方向
     */
    public void setPs(String ps) {
        this.ps = ps;
    }

    /**
     * 获取 交易日历
     *
     * @return calendarId 交易日历
     */
    public String getCalendarId() {
        return this.calendarId;
    }

    /**
     * 设置 交易日历
     *
     * @param calendarId 交易日历
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    /**
     * 获取 交易日期
     *
     * @return tradeDate 交易日期
     */
    public Date getTradeDate() {
        return this.tradeDate;
    }

    /**
     * 设置 交易日期
     *
     * @param tradeDate 交易日期
     */
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * 获取 支付日期
     *
     * @return payDate 支付日期
     */
    public Date getPayDate() {
        return this.payDate;
    }

    /**
     * 设置 支付日期
     *
     * @param payDate 支付日期
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * 获取 期限
     *
     * @return term 期限
     */
    public Long getTerm() {
        return this.term;
    }

    /**
     * 设置 期限
     *
     * @param term 期限
     */
    public void setTerm(Long term) {
        this.term = term;
    }

    /**
     * 获取 币种
     *
     * @return ccy 币种
     */
    public String getCcy() {
        return this.ccy;
    }

    /**
     * 设置 币种
     *
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 数量
     *
     * @return amount 数量
     */
    public BigDecimal getAmount() {
        return this.amount;
    }

    /**
     * 设置 数量
     *
     * @param amount 数量
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取 行权日基准日
     *
     * @return exerciseDate 行权日基准日
     */
    public Date getExerciseDate() {
        return this.exerciseDate;
    }

    /**
     * 设置 行权日基准日
     *
     * @param exerciseDate 行权日基准日
     */
    public void setExerciseDate(Date exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    /**
     * 获取 行权方式
     *
     * @return exerciseType 行权方式
     */
    public String getExerciseType() {
        return this.exerciseType;
    }

    /**
     * 设置 行权方式
     *
     * @param exerciseType 行权方式
     */
    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    /**
     * 获取 期权费
     *
     * @return optFee 期权费
     */
    public BigDecimal getOptFee() {
        return this.optFee;
    }

    /**
     * 设置 期权费
     *
     * @param optFee 期权费
     */
    public void setOptFee(BigDecimal optFee) {
        this.optFee = optFee;
    }

    /**
     * 获取 交易类金融产品
     *
     * @return finTradeProduct 交易类金融产品
     */
    public FinTradeProduct getFinTradeProduct() {
        return this.finTradeProduct;
    }

    /**
     * 设置 交易类金融产品
     *
     * @param finTradeProduct 交易类金融产品
     */
    public void setFinTradeProduct(FinTradeProduct finTradeProduct) {
        this.finTradeProduct = finTradeProduct;
    }

    /**
     * 获取 交易类金融产品附属
     *
     * @return finTradeProductAdd 交易类金融产品附属
     */
    public FinTradeProductAdd getFinTradeProductAdd() {
        return this.finTradeProductAdd;
    }

    /**
     * 设置 交易类金融产品附属
     *
     * @param finTradeProductAdd 交易类金融产品附属
     */
    public void setFinTradeProductAdd(FinTradeProductAdd finTradeProductAdd) {
        this.finTradeProductAdd = finTradeProductAdd;
    }

    /**
     * 获取 利率结构明细
     *
     * @return finRateInfoStructure 利率结构明细
     */
    public FinRateInfoStructure getFinRateInfoStructure() {
        return this.finRateInfoStructure;
    }

    /**
     * 设置 利率结构明细
     *
     * @param finRateInfoStructure 利率结构明细
     */
    public void setFinRateInfoStructure(FinRateInfoStructure finRateInfoStructure) {
        this.finRateInfoStructure = finRateInfoStructure;
    }

    /**
     * 获取 组合交易表
     *
     * @return ptlTrade 组合交易表
     */
    public PtlTrade getPtlTrade() {
        return this.ptlTrade;
    }

    /**
     * 设置 组合交易表
     *
     * @param ptlTrade 组合交易表
     */
    public void setPtlTrade(PtlTrade ptlTrade) {
        this.ptlTrade = ptlTrade;
    }

    /**
     * 获取 划款指令
     *
     * @return bussTrdTransferOrder 划款指令
     */
    public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
        return this.bussTrdTransferOrder;
    }

    /**
     * 设置 划款指令
     *
     * @param bussTrdTransferOrder 划款指令
     */
    public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
        this.bussTrdTransferOrder = bussTrdTransferOrder;
    }

    /**
     * 获取 人员信息
     *
     * @return trdInvestPersonInfo 人员信息
     */
    public TrdInvestPersonInfo002Dto getTrdInvestPersonInfo() {
        return this.trdInvestPersonInfo;
    }

    /**
     * 设置 人员信息
     *
     * @param trdInvestPersonInfo 人员信息
     */
    public void setTrdInvestPersonInfo(TrdInvestPersonInfo002Dto trdInvestPersonInfo) {
        this.trdInvestPersonInfo = trdInvestPersonInfo;
    }

    /**
     * 获取 附件上传
     *
     * @return fileArray 附件上传
     */
    public List<UploadFile> getFileArray() {
        return this.fileArray;
    }

    /**
     * 设置 附件上传
     *
     * @param fileArray 附件上传
     */
    public void setFileArray(List<UploadFile> fileArray) {
        this.fileArray = fileArray;
    }

    /**
     * 获取 撤销说明
     *
     * @return revokeReason 撤销说明
     */
    public String getRevokeReason() {
        return this.revokeReason;
    }

    /**
     * 设置 撤销说明
     *
     * @param revokeReason 撤销说明
     */
    public void setRevokeReason(String revokeReason) {
        this.revokeReason = revokeReason;
    }

    /**
     * 获取 风险试算实体类
     *
     * @return riskTrialRetDto 风险试算实体类
     */
    public List<RiskTrialRetDto> getRiskTrialRetDto() {
        return this.riskTrialRetDto;
    }

    /**
     * 设置 风险试算实体类
     *
     * @param riskTrialRetDto 风险试算实体类
     */
    public void setRiskTrialRetDto(List<RiskTrialRetDto> riskTrialRetDto) {
        this.riskTrialRetDto = riskTrialRetDto;
    }

    /**
     * 获取 金融产品分类
     *
     * @return finProductType 金融产品分类
     */
    public FinProductType getFinProductType() {
        return this.finProductType;
    }

    /**
     * 设置 金融产品分类
     *
     * @param finProductType 金融产品分类
     */
    public void setFinProductType(FinProductType finProductType) {
        this.finProductType = finProductType;
    }

    /**
     * 获取 现金流利率信息
     *
     * @return finRateInfo 现金流利率信息
     */
    public FinRateInfo getFinRateInfo() {
        return this.finRateInfo;
    }

    /**
     * 设置 现金流利率信息
     *
     * @param finRateInfo 现金流利率信息
     */
    public void setFinRateInfo(FinRateInfo finRateInfo) {
        this.finRateInfo = finRateInfo;
    }

    /**
     * 获取 行权规则
     *
     * @return finExerciseRule 行权规则
     */
    public FinExerciseRule getFinExerciseRule() {
        return this.finExerciseRule;
    }

    /**
     * 设置 行权规则
     *
     * @param finExerciseRule 行权规则
     */
    public void setFinExerciseRule(FinExerciseRule finExerciseRule) {
        this.finExerciseRule = finExerciseRule;
    }

    /**
     * 获取 行权计划
     *
     * @return finExercisePlan 行权计划
     */
    public FinExercisePlan getFinExercisePlan() {
        return this.finExercisePlan;
    }

    /**
     * 设置 行权计划
     *
     * @param finExercisePlan 行权计划
     */
    public void setFinExercisePlan(FinExercisePlan finExercisePlan) {
        this.finExercisePlan = finExercisePlan;
    }

    /**
     * 获取 金融产品交易
     *
     * @return trdProductDeal 金融产品交易
     */
    public TrdProductDeal getTrdProductDeal() {
        return this.trdProductDeal;
    }

    /**
     * 设置 金融产品交易
     *
     * @param trdProductDeal 金融产品交易
     */
    public void setTrdProductDeal(TrdProductDeal trdProductDeal) {
        this.trdProductDeal = trdProductDeal;
    }

    /**
     * 获取 金融产品交易附属
     *
     * @return trdProductDealAdd 金融产品交易附属
     */
    public TrdProductDealAdd getTrdProductDealAdd() {
        return this.trdProductDealAdd;
    }

    /**
     * 设置 金融产品交易附属
     *
     * @param trdProductDealAdd 金融产品交易附属
     */
    public void setTrdProductDealAdd(TrdProductDealAdd trdProductDealAdd) {
        this.trdProductDealAdd = trdProductDealAdd;
    }

    /**
     * 获取 金融产品交易汇总附属
     *
     * @return trdDealTotalAdd 金融产品交易汇总附属
     */
    public TrdDealTotalAdd getTrdDealTotalAdd() {
        return this.trdDealTotalAdd;
    }

    /**
     * 设置 金融产品交易汇总附属
     *
     * @param trdDealTotalAdd 金融产品交易汇总附属
     */
    public void setTrdDealTotalAdd(TrdDealTotalAdd trdDealTotalAdd) {
        this.trdDealTotalAdd = trdDealTotalAdd;
    }

    /**
     * 获取 现金流信息
     *
     * @return finCashInfo 现金流信息
     */
    public List<FinCashInfo> getFinCashInfo() {
        return this.finCashInfo;
    }

    /**
     * 设置 现金流信息
     *
     * @param finCashInfo 现金流信息
     */
    public void setFinCashInfo(List<FinCashInfo> finCashInfo) {
        this.finCashInfo = finCashInfo;
    }

    /**
     * 获取 起息日
     *
     * @return vdate 起息日
     */
    public Date getVdate() {
        return this.vdate;
    }

    /**
     * 设置 起息日
     *
     * @param vdate 起息日
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 获取 到期日
     *
     * @return mdate 到期日
     */
    public Date getMdate() {
        return this.mdate;
    }

    /**
     * 设置 到期日
     *
     * @param mdate 到期日
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 获取 是否保本
     *
     * @return isGuarantee 是否保本
     */
    public String getIsGuarantee() {
        return this.isGuarantee;
    }

    /**
     * 设置 是否保本
     *
     * @param isGuarantee 是否保本
     */
    public void setIsGuarantee(String isGuarantee) {
        this.isGuarantee = isGuarantee;
    }

    /**
     * 获取 组合T日可用现金
     *
     * @return avalibleCash 组合T日可用现金
     */
    public BigDecimal getAvalibleCash() {
        return this.avalibleCash;
    }

    /**
     * 设置 组合T日可用现金
     *
     * @param avalibleCash 组合T日可用现金
     */
    public void setAvalibleCash(BigDecimal avalibleCash) {
        this.avalibleCash = avalibleCash;
    }

    /**
     * 获取 期权费类型
     *
     * @return optFeePayType 期权费类型
     */
    public String getOptFeePayType() {
        return this.optFeePayType;
    }

    /**
     * 设置 期权费类型
     *
     * @param optFeePayType 期权费类型
     */
    public void setOptFeePayType(String optFeePayType) {
        this.optFeePayType = optFeePayType;
    }

    /**
     * 获取 金融产品交易汇总扩展表
     *
     * @return paTrdFinProductDealTotal 金融产品交易汇总扩展表
     */
    public PaTrdFinProductDealTotal getPaTrdFinProductDealTotal() {
        return this.paTrdFinProductDealTotal;
    }

    /**
     * 设置 金融产品交易汇总扩展表
     *
     * @param paTrdFinProductDealTotal 金融产品交易汇总扩展表
     */
    public void setPaTrdFinProductDealTotal(PaTrdFinProductDealTotal paTrdFinProductDealTotal) {
        this.paTrdFinProductDealTotal = paTrdFinProductDealTotal;
    }

    /**
     * 获取 交易编号
     *
     * @return tradeId 交易编号
     */
    public String getTradeId() {
        return this.tradeId;
    }

    /**
     * 设置 交易编号
     *
     * @param tradeId 交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

	/**
	 * @return the holdPurpose
	 */
	public String getHoldPurpose() {
		return this.holdPurpose;
	}

	/**
	 * @param holdPurpose the holdPurpose to set
	 */
	public void setHoldPurpose(String holdPurpose) {
		this.holdPurpose = holdPurpose;
	}
}
