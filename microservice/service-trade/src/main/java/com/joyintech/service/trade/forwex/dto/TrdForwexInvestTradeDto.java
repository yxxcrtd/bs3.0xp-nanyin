/*
 * 文件名：TrdForwexInvestTradeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2019年7月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.forwex.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.service.trade.bond.dto.TrdInvestPersonInfoDto;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.StlFinProductAcct;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.common.base.entity.WflTrdProductDealOth;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.workflow.engine.annotation.PrimaryKey;

public class TrdForwexInvestTradeDto extends TrdProductDeal {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = -5874242382259939082L;


    /**
     * 委托代码
     */
    private String entrustId;
    
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 人员信息
     */
    private TrdInvestPersonInfoDto trdInvestPersonInfo;

    /**
     * 画面模式
     */
    private String pageModel;
    /**
     * 金融产品交易Entity
     */
    private TrdProductDeal trdProductDeal;
    /**
     * 金融产品交易扩展
     */
    private PaTrdProductDeal paTrdProductDeal;
    /**
     * 附件上传
     */
    private List<UploadFile> uploadFileList;

    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;
    
    /**
     * 金融产品交易附属
     */
    private TrdProductDealAdd trdProductDealAdd;
    /**
     * 交易费用明细表Entity
     */
    private TrdTradeFeeDetail trdTradeFeeDetail;
    /**
     * 组合交易表
     */
    private PtlTrade ptlTrade;

    /**
     * 金融产品交易业务流程其他信息
     */ 
    private WflTrdProductDealOth wflTrdProductDealOth;
    /**
     * 对手方 金融产品账户表
     */
    private StlFinProductAcct stlFinProductAcctCor;
    /**
     * 货币对
     */
    private String curPair;
    
    /**
     * 组合代码
     */
    private String portfolioId;
    
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 生成方式
     */
    private String genType;
    
    /**
     * 交易类型
     */
    private String excDealType;
    
    /**
     * 折美元金额
     */
    private BigDecimal usdAmt;
    
    /**
     * 我方角色
     */
    private String ourRole;
    
    /**
     * 交易模式
     */
    private String dealMode;
    
    /**
     * 报价精度
     */
    private BigDecimal quotPre;
    
    /**
     * 交易费用
     */
    private BigDecimal feeAmt;
    
    /**
     * 是否差额清算
     */
    private String isDifSqu;
    
    /**
     * 交易日历
     */
    private String calendarId;
    
    /**
     * 期限 
     */
    private String term;
    
    /**
     * 清算模式
     */
    private String settMode;
    
    /**
     * 定价日
     */
    private Date pricedDate;
    
    /**
     * 差额交割参考汇率
     */
    private String refRate;
    
    /**
     * 定价日即期汇率
     */
    private BigDecimal pricedDateRate;
    
    /**
     * 差额收付方向
     */
    private String difPs;
    
    /**
     * 差额收付金额
     */
    private BigDecimal difPayAmt;
    
    /**
     * 交易币种可用持仓
     */
    private BigDecimal proAvailableCash;
    
    /**
     * 对应货币
     */
    private String corCcy;
    
    /**
     * 对应货币可用持仓
     */
    private BigDecimal corCurPos;
    
    /**
     * 本方账户
     */
    private String dealAcct;
    
    /**
     * 对手方账户
     */
    private String corAcct;
    
    /**
     * 撤销说明
     */
    private String revokeReason;

    /**
     * 有效状态
     */
    private String validStatus;
    
	/**
	 * tradeId1
	 */
    private String tradeId1;
    
    /**
     * workflowId
     */
    private String workflowId;
    
    /**
     * workflowStatus
     */
    private String workflowStatus;
    /**
     * 期限品种
     */
    private String termType;
    
    /**
     * 是否差额清算，到期
     */
    private String isDifSquEx;
    
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
	 * 获取 curPair
	 * @return curPair.
	 */
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
	 * 获取 trdTradeFeeDetail
	 * @return trdTradeFeeDetail.
	 */
	public TrdTradeFeeDetail getTrdTradeFeeDetail() {
		return trdTradeFeeDetail;
	}
	/**
	 * 设置 trdTradeFeeDetail
	 * @param trdTradeFeeDetail trdTradeFeeDetail
	 */
	public void setTrdTradeFeeDetail(TrdTradeFeeDetail trdTradeFeeDetail) {
		this.trdTradeFeeDetail = trdTradeFeeDetail;
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
	 * 获取 genType
	 * @return genType.
	 */
	public String getGenType() {
		return genType;
	}
	/**
	 * 设置 genType
	 * @param genType genType
	 */
	public void setGenType(String genType) {
		this.genType = genType;
	}
	/**
	 * 获取 excDealType
	 * @return excDealType.
	 */
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
	 * 获取 usdAmt
	 * @return usdAmt.
	 */
	public BigDecimal getUsdAmt() {
		return usdAmt;
	}
	/**
	 * 设置 usdAmt
	 * @param usdAmt usdAmt
	 */
	public void setUsdAmt(BigDecimal usdAmt) {
		this.usdAmt = usdAmt;
	}
	/**
	 * 获取 ourRole
	 * @return ourRole.
	 */
	public String getOurRole() {
		return ourRole;
	}
	/**
	 * 设置 ourRole
	 * @param ourRole ourRole
	 */
	public void setOurRole(String ourRole) {
		this.ourRole = ourRole;
	}
	/**
	 * 获取 dealMode
	 * @return dealMode.
	 */
	public String getDealMode() {
		return dealMode;
	}
	/**
	 * 设置 dealMode
	 * @param dealMode dealMode
	 */
	public void setDealMode(String dealMode) {
		this.dealMode = dealMode;
	}
	/**
	 * 获取 quotPre
	 * @return quotPre.
	 */
	public BigDecimal getQuotPre() {
		return quotPre;
	}
	/**
	 * 设置 quotPre
	 * @param quotPre quotPre
	 */
	public void setQuotPre(BigDecimal quotPre) {
		this.quotPre = quotPre;
	}
	/**
	 * 获取 feeAmt
	 * @return feeAmt.
	 */
	@Override
	public BigDecimal getFeeAmt() {
		return feeAmt;
	}
	/**
	 * 设置 feeAmt
	 * @param feeAmt feeAmt
	 */
	@Override
	public void setFeeAmt(BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}
	/**
	 * 获取 isDifSqu
	 * @return isDifSqu.
	 */
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
	 * 获取 term
	 * @return term.
	 */
	public String getTerm() {
		return term;
	}
	/**
	 * 设置 term
	 * @param term term
	 */
	public void setTerm(String term) {
		this.term = term;
	}
	/**
	 * 获取 settMode
	 * @return settMode.
	 */
	public String getSettMode() {
		return settMode;
	}
	/**
	 * 设置 settMode
	 * @param settMode settMode
	 */
	public void setSettMode(String settMode) {
		this.settMode = settMode;
	}
	/**
	 * 获取 pricedDate
	 * @return pricedDate.
	 */
	public Date getPricedDate() {
		return pricedDate;
	}
	/**
	 * 设置 pricedDate
	 * @param pricedDate pricedDate
	 */
	public void setPricedDate(Date pricedDate) {
		this.pricedDate = pricedDate;
	}
	/**
	 * 获取 refRate
	 * @return refRate.
	 */
	public String getRefRate() {
		return refRate;
	}
	/**
	 * 设置 refRate
	 * @param refRate refRate
	 */
	public void setRefRate(String refRate) {
		this.refRate = refRate;
	}
	/**
	 * 获取 pricedDateRate
	 * @return pricedDateRate.
	 */
	public BigDecimal getPricedDateRate() {
		return pricedDateRate;
	}
	/**
	 * 设置 pricedDateRate
	 * @param pricedDateRate pricedDateRate
	 */
	public void setPricedDateRate(BigDecimal pricedDateRate) {
		this.pricedDateRate = pricedDateRate;
	}
	/**
	 * 获取 difPs
	 * @return difPs.
	 */
	public String getDifPs() {
		return difPs;
	}
	/**
	 * 设置 difPs
	 * @param difPs difPs
	 */
	public void setDifPs(String difPs) {
		this.difPs = difPs;
	}
	/**
	 * 获取 difPayAmt
	 * @return difPayAmt.
	 */
	public BigDecimal getDifPayAmt() {
		return difPayAmt;
	}
	/**
	 * 设置 difPayAmt
	 * @param difPayAmt difPayAmt
	 */
	public void setDifPayAmt(BigDecimal difPayAmt) {
		this.difPayAmt = difPayAmt;
	}
	/**
	 * 获取 proAvailableCash
	 * @return proAvailableCash.
	 */
	public BigDecimal getProAvailableCash() {
		return proAvailableCash;
	}
	/**
	 * 设置 proAvailableCash
	 * @param proAvailableCash proAvailableCash
	 */
	public void setProAvailableCash(BigDecimal proAvailableCash) {
		this.proAvailableCash = proAvailableCash;
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
	 * 获取 tradeId1
	 * @return tradeId1.
	 */
	public String getTradeId1() {
		return tradeId1;
	}
	/**
	 * 设置 tradeId1
	 * @param tradeId1 tradeId1
	 */
	public void setTradeId1(String tradeId1) {
		this.tradeId1 = tradeId1;
	}
	/**
	 * 获取 workflowId
	 * @return workflowId.
	 */
	@Override
	public String getWorkflowId() {
		return workflowId;
	}
	/**
	 * 设置 workflowId
	 * @param workflowId workflowId
	 */
	@Override
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
	 * 获取 trdInvestPersonInfo
	 * @return trdInvestPersonInfo.
	 */
	public TrdInvestPersonInfoDto getTrdInvestPersonInfo() {
		return trdInvestPersonInfo;
	}
	/**
	 * 设置 trdInvestPersonInfo
	 * @param trdInvestPersonInfo trdInvestPersonInfo
	 */
	public void setTrdInvestPersonInfo(TrdInvestPersonInfoDto trdInvestPersonInfo) {
		this.trdInvestPersonInfo = trdInvestPersonInfo;
	}
	/**
	 * 获取 isDifSquEx
	 * @return isDifSquEx.
	 */
	public String getIsDifSquEx() {
		return isDifSquEx;
	}
	/**
	 * 设置 isDifSquEx
	 * @param isDifSquEx isDifSquEx
	 */
	public void setIsDifSquEx(String isDifSquEx) {
		this.isDifSquEx = isDifSquEx;
	}
    
	
}
