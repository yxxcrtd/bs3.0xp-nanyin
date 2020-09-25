package com.joyintech.service.trade.option.dto;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.service.trade.lend.dto.TrdInvestPersonInfoDto;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TrdOptionTradeDto extends TrdFinProductDealTotal {
    /**
     * 有效状态
     */
    private String validStatus;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 汇总交易编号
     */
    private String totleTradeId;
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
     * 组合代码
     */
    private String portfolioId;
    /**
     * 合约标的
     */
    private String targetId;
    /**
     * 合约类型
     */
    private String contractType;
    /**
     * 交易方向
     */
    private String ps;
    /**
     * 交易日历
     */
    private String calendarId;
    /**
     * 交易日期
     */
    private Date tradeDate;
    /**
     * 支付日期
     */
    private Date payDate;
    /**
     * 起息日
     */
    private Date vdate;
    /**
     * 到期日
     */
    private Date mdate;
    /**
     * 期限
     */
    private BigDecimal term;
    /**
     * 币种
     */
    private String ccy;
    /**
     * 数量
     */
    private BigDecimal amount;
    /**
     * 行权日基准日
     */
    private Date exerciseDate;
    /**
     * 行权方式
     */
    private String exerciseType;
    /**
     * 期权费
     */
    private BigDecimal optFee;

    /**
     * 期权费类型
     */
    private String optFeePayType;

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
    private TrdInvestPersonInfoDto trdInvestPersonInfo;

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
     * 保证金比例
     */
    private BigDecimal bondLevel;

    /**
     * 保证金
     */
    private BigDecimal bond;
    
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
     * 获取 期限
     *
     * @return term 期限
     */
    public BigDecimal getTerm() {
        return this.term;
    }

    /**
     * 设置 期限
     *
     * @param term 期限
     */
    public void setTerm(BigDecimal term) {
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
     * 获取 保证金比例
     *
     * @return bondLevel 保证金比例
     */
    public BigDecimal getBondLevel() {
        return this.bondLevel;
    }

    /**
     * 设置 保证金比例
     *
     * @param bondLevel 保证金比例
     */
    public void setBondLevel(BigDecimal bondLevel) {
        this.bondLevel = bondLevel;
    }

    /**
     * 获取 保证金
     *
     * @return bond 保证金
     */
    public BigDecimal getBond() {
        return this.bond;
    }

    /**
     * 设置 保证金
     *
     * @param bond 保证金
     */
    public void setBond(BigDecimal bond) {
        this.bond = bond;
    }

    /**
     * 获取 有效状态
     *
     * @return validStatus 有效状态
     */
    public String getValidStatus() {
        return this.validStatus;
    }

    /**
     * 设置 有效状态
     *
     * @param validStatus 有效状态
     */
    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }

    /**
     * 获取 组合名称
     *
     * @return portfolioName 组合名称
     */
    public String getPortfolioName() {
        return this.portfolioName;
    }

    /**
     * 设置 组合名称
     *
     * @param portfolioName 组合名称
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    /**
     * 获取 人员信息
     *
     * @return trdInvestPersonInfo 人员信息
     */
    public TrdInvestPersonInfoDto getTrdInvestPersonInfo() {
        return this.trdInvestPersonInfo;
    }

    /**
     * 设置 人员信息
     *
     * @param trdInvestPersonInfo 人员信息
     */
    public void setTrdInvestPersonInfo(TrdInvestPersonInfoDto trdInvestPersonInfo) {
        this.trdInvestPersonInfo = trdInvestPersonInfo;
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
	 * @return the holdPurpose
	 */
	public String getHoldPurpose() {
		return holdPurpose;
	}

	/**
	 * @param holdPurpose the holdPurpose to set
	 */
	public void setHoldPurpose(String holdPurpose) {
		this.holdPurpose = holdPurpose;
	}
}
