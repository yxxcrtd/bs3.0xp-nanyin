/*
 * 文件名：TrdFxswapInvestTrade001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年6月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.trdfxswapinvesttrade.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.WflTrdProductDeal;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;

/**
 * 外汇掉期投资交易实体类
 * 
 * @author renping
 * @version 1.0 2019年6月25日
 * @see TrdFxswapInvestTrade001Dto
 * @since 1.0
 */
@PageId("TrdFxswapInvestTrade002")
public class TrdFxswapInvestTrade001Dto extends WflTrdProductDeal {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1214752541124234680L;

    /**
     * 交易日
     */
    private Date tradeDateBegin;

    /**
     * 交易日
     */
    private Date tradeDateEnd;

    /**
     * 近端交割日
     */
    private Date vDateBegin;

    /**
     * 近端交割日
     */
    private Date vDateEnd;

    /**
     * 远端交割日
     */
    private Date mDateBegin;

    /**
     * 远端交割日
     */
    private Date mDateEnd;

    /**
     * 委托代码
     */
    private String entrustId;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 交易类型
     */
    private String excDealType;

    /**
     * 货币对
     */
    private String curPair;

    /**
     * 交易日历
     */
    private String calendarId;

    /**
     * 交易币种
     */
    private String dealCur;

    /**
     * 对应币种
     */
    private String corCcy;

    /**
     * 即期汇率
     */
    private BigDecimal spotRate;

    /**
     * 期限类型
     */
    private String excFxsTermType;

    /**
     * 我方角色
     */
    private String ourRole;

    /**
     * 交易模式
     */
    private String dealMode;

    /**
     * 清算模式
     */
    private String settMode;

    /**
     * 近端期限
     */
    private String nTermTypeExc;

    /**
     * 近端掉期点
     */
    private BigDecimal nPoint;

    /**
     * 近端掉期全价
     */
    private BigDecimal nFprice;

    /**
     * 交易货币金额
     */
    private BigDecimal nDealCurAmt;

    /**
     * 交易货币可用持仓
     */
    private BigDecimal dealCurPos;

    /**
     * 对应货币金额
     */
    private BigDecimal nCorCurAmt;

    /**
     * 对应货币可用持仓
     */
    private BigDecimal corCurPos;

    /**
     * 折美元金额 近端
     */
    private BigDecimal nUsdAmt;

    /**
     * 交易费用
     */
    private BigDecimal feeAmt;

    /**
     * 远端期限
     */
    private String fTermTypeExc;

    /**
     * 远端掉期点
     */
    private BigDecimal fPoint;

    /**
     * 远端掉期全价
     */
    private BigDecimal fFprice;

    /**
     * 交易货币金额
     */
    private BigDecimal fDealCurAmt;

    /**
     * 对应货币金额
     */
    private BigDecimal fCorCurAmt;

    /**
     * 折美元金额
     */
    private BigDecimal fUsdAmt;

    /**
     * 资产提供行
     */
    private String assetProvider;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * elementId
     */
    private String elementId;

    /**
     * 划款指令表
     */
    private List<StlPayOrdersDto> stlPayOrdersList;

    /**
     * 组合表
     */
    private PtlTrade ptlTrade;

    /**
     * 金融产品交易扩展
     */
    private PaTrdProductDeal paTrdProductDeal;

    /**
     * 撤销原因
     */
    private String cancelRemark;

    /**
     * pageModel
     */
    private String pageModel;

    /**
     * 风险试算实体类集合
     */
    private List<RiskTrialRetDto> riskTrialRetDto;

    /**
     * 到期交易交易编号
     */
    private String tradeId1;
    
    /**
     * 持有目的 HOLD_PURPOSE 中债新增 20200409
     */
    private String holdPurpose;

    /**
     * 获取 tradeId1
     * 
     * @return tradeId1.
     */
    public String getTradeId1() {
        return tradeId1;
    }

    /**
     * 设置 tradeId1
     * 
     * @param tradeId1 tradeId1
     */
    public void setTradeId1(String tradeId1) {
        this.tradeId1 = tradeId1;
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
     * 获取 pageModel
     * 
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }

    /**
     * 设置 pageModel
     * 
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    /**
     * 金融产品类型 {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "TrdFxswapInvestTrade001.finprodType", groups = {
        ValidateGroupEdit.class})
    public String getFinprodType() {
        return super.getFinprodType();
    }

    /**
     * 备注 {@inheritDoc}
     */
    @Override
    @Length(fieldName = "TrdFxswapInvestTrade002.remark", max = 1000, groups = {
        ValidateGroupEdit.class, ValidateGroupDelete.class})
    public String getRemark() {
        return super.getRemark();
    }

    /**
     * 获取 cancelRemark
     * 
     * @return cancelRemark.
     */
    @Override
    public String getCancelRemark() {
        return cancelRemark;
    }

    /**
     * 设置 cancelRemark
     * 
     * @param cancelRemark cancelRemark
     */
    @Override
    public void setCancelRemark(String cancelRemark) {
        this.cancelRemark = cancelRemark;
    }

    /**
     * 获取 stlPayOrdersList
     * 
     * @return stlPayOrdersList.
     */
    public List<StlPayOrdersDto> getStlPayOrdersList() {
        return stlPayOrdersList;
    }

    /**
     * 设置 stlPayOrdersList
     * 
     * @param stlPayOrdersList stlPayOrdersList
     */
    public void setStlPayOrdersList(List<StlPayOrdersDto> stlPayOrdersList) {
        this.stlPayOrdersList = stlPayOrdersList;
    }

    /**
     * 获取 ptlTrade
     * 
     * @return ptlTrade.
     */
    public PtlTrade getPtlTrade() {
        return ptlTrade;
    }

    /**
     * 设置 ptlTrade
     * 
     * @param ptlTrade ptlTrade
     */
    public void setPtlTrade(PtlTrade ptlTrade) {
        this.ptlTrade = ptlTrade;
    }

    /**
     * 获取 paTrdProductDeal
     * 
     * @return paTrdProductDeal.
     */
    public PaTrdProductDeal getPaTrdProductDeal() {
        return paTrdProductDeal;
    }

    /**
     * 设置 paTrdProductDeal
     * 
     * @param paTrdProductDeal paTrdProductDeal
     */
    public void setPaTrdProductDeal(PaTrdProductDeal paTrdProductDeal) {
        this.paTrdProductDeal = paTrdProductDeal;
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
     * 获取 operatType
     * 
     * @return operatType.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operatType
     * 
     * @param operateType operatType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 assetProvider
     * 
     * @return assetProvider.
     */
    public String getAssetProvider() {
        return assetProvider;
    }

    /**
     * 设置 assetProvider
     * 
     * @param assetProvider assetProvider
     */
    public void setAssetProvider(String assetProvider) {
        this.assetProvider = assetProvider;
    }

    /**
     * 获取 entrustId
     * 
     * @return entrustId.
     */
    public String getEntrustId() {
        return entrustId;
    }

    /**
     * 设置 entrustId
     * 
     * @param entrustId entrustId
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    /**
     * 获取 portfolioId
     * 
     * @return portfolioId.
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 portfolioId
     * 
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
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
     * 获取 excDealType
     * 
     * @return excDealType.
     */
    public String getExcDealType() {
        return excDealType;
    }

    /**
     * 设置 excDealType
     * 
     * @param excDealType excDealType
     */
    public void setExcDealType(String excDealType) {
        this.excDealType = excDealType;
    }

    /**
     * 获取 curPair
     * 
     * @return curPair.
     */
    public String getCurPair() {
        return curPair;
    }

    /**
     * 设置 curPair
     * 
     * @param curPair curPair
     */
    public void setCurPair(String curPair) {
        this.curPair = curPair;
    }

    /**
     * 获取 calendarId
     * 
     * @return calendarId.
     */
    public String getCalendarId() {
        return calendarId;
    }

    /**
     * 设置 calendarId
     * 
     * @param calendarId calendarId
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    /**
     * 获取 dealCur
     * 
     * @return dealCur.
     */
    public String getDealCur() {
        return dealCur;
    }

    /**
     * 设置 dealCur
     * 
     * @param dealCur dealCur
     */
    public void setDealCur(String dealCur) {
        this.dealCur = dealCur;
    }

    /**
     * 获取 corCcy
     * 
     * @return corCcy.
     */
    public String getCorCcy() {
        return corCcy;
    }

    /**
     * 设置 corCcy
     * 
     * @param corCcy corCcy
     */
    public void setCorCcy(String corCcy) {
        this.corCcy = corCcy;
    }

    /**
     * 获取 spotRate
     * 
     * @return spotRate.
     */
    public BigDecimal getSpotRate() {
        return spotRate;
    }

    /**
     * 设置 spotRate
     * 
     * @param spotRate spotRate
     */
    public void setSpotRate(BigDecimal spotRate) {
        this.spotRate = spotRate;
    }

    /**
     * 获取 excFxsTermType
     * 
     * @return excFxsTermType.
     */
    public String getExcFxsTermType() {
        return excFxsTermType;
    }

    /**
     * 设置 excFxsTermType
     * 
     * @param excFxsTermType excFxsTermType
     */
    public void setExcFxsTermType(String excFxsTermType) {
        this.excFxsTermType = excFxsTermType;
    }

    /**
     * 获取 ourRole
     * 
     * @return ourRole.
     */
    public String getOurRole() {
        return ourRole;
    }

    /**
     * 设置 ourRole
     * 
     * @param ourRole ourRole
     */
    public void setOurRole(String ourRole) {
        this.ourRole = ourRole;
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
     * 获取 settMode
     * 
     * @return settMode.
     */
    public String getSettMode() {
        return settMode;
    }

    /**
     * 设置 settMode
     * 
     * @param settMode settMode
     */
    public void setSettMode(String settMode) {
        this.settMode = settMode;
    }

    /**
     * 获取 nTermTypeExc
     * 
     * @return nTermTypeExc.
     */
    public String getnTermTypeExc() {
        return nTermTypeExc;
    }

    /**
     * 设置 nTermTypeExc
     * 
     * @param nTermTypeExc nTermTypeExc
     */
    public void setnTermTypeExc(String nTermTypeExc) {
        this.nTermTypeExc = nTermTypeExc;
    }

    /**
     * 获取 nPoint
     * 
     * @return nPoint.
     */
    public BigDecimal getnPoint() {
        return nPoint;
    }

    /**
     * 设置 nPoint
     * 
     * @param nPoint nPoint
     */
    public void setnPoint(BigDecimal nPoint) {
        this.nPoint = nPoint;
    }

    /**
     * 获取 nFprice
     * 
     * @return nFprice.
     */
    public BigDecimal getnFprice() {
        return nFprice;
    }

    /**
     * 设置 nFprice
     * 
     * @param nFprice nFprice
     */
    public void setnFprice(BigDecimal nFprice) {
        this.nFprice = nFprice;
    }

    /**
     * 获取 nDealCurAmt
     * 
     * @return nDealCurAmt.
     */
    public BigDecimal getnDealCurAmt() {
        return nDealCurAmt;
    }

    /**
     * 设置 nDealCurAmt
     * 
     * @param nDealCurAmt nDealCurAmt
     */
    public void setnDealCurAmt(BigDecimal nDealCurAmt) {
        this.nDealCurAmt = nDealCurAmt;
    }

    /**
     * 获取 dealCurPos
     * 
     * @return dealCurPos.
     */
    public BigDecimal getDealCurPos() {
        return dealCurPos;
    }

    /**
     * 设置 dealCurPos
     * 
     * @param dealCurPos dealCurPos
     */
    public void setDealCurPos(BigDecimal dealCurPos) {
        this.dealCurPos = dealCurPos;
    }

    /**
     * 获取 nCorCurAmt
     * 
     * @return nCorCurAmt.
     */
    public BigDecimal getnCorCurAmt() {
        return nCorCurAmt;
    }

    /**
     * 设置 nCorCurAmt
     * 
     * @param nCorCurAmt nCorCurAmt
     */
    public void setnCorCurAmt(BigDecimal nCorCurAmt) {
        this.nCorCurAmt = nCorCurAmt;
    }

    /**
     * 获取 corCurPos
     * 
     * @return corCurPos.
     */
    public BigDecimal getCorCurPos() {
        return corCurPos;
    }

    /**
     * 设置 corCurPos
     * 
     * @param corCurPos corCurPos
     */
    public void setCorCurPos(BigDecimal corCurPos) {
        this.corCurPos = corCurPos;
    }

    /**
     * 获取 nUsdAmt
     * 
     * @return nUsdAmt.
     */
    public BigDecimal getnUsdAmt() {
        return nUsdAmt;
    }

    /**
     * 设置 nUsdAmt
     * 
     * @param nUsdAmt nUsdAmt
     */
    public void setnUsdAmt(BigDecimal nUsdAmt) {
        this.nUsdAmt = nUsdAmt;
    }

    /**
     * 获取 feeAmt
     * 
     * @return feeAmt.
     */
    @Override
    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    /**
     * 设置 feeAmt
     * 
     * @param feeAmt feeAmt
     */
    @Override
    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    /**
     * 获取 fTermTypeExc
     * 
     * @return fTermTypeExc.
     */
    public String getfTermTypeExc() {
        return fTermTypeExc;
    }

    /**
     * 设置 fTermTypeExc
     * 
     * @param fTermTypeExc fTermTypeExc
     */
    public void setfTermTypeExc(String fTermTypeExc) {
        this.fTermTypeExc = fTermTypeExc;
    }

    /**
     * 获取 fPoint
     * 
     * @return fPoint.
     */
    public BigDecimal getfPoint() {
        return fPoint;
    }

    /**
     * 设置 fPoint
     * 
     * @param fPoint fPoint
     */
    public void setfPoint(BigDecimal fPoint) {
        this.fPoint = fPoint;
    }

    /**
     * 获取 fFprice
     * 
     * @return fFprice.
     */
    public BigDecimal getfFprice() {
        return fFprice;
    }

    /**
     * 设置 fFprice
     * 
     * @param fFprice fFprice
     */
    public void setfFprice(BigDecimal fFprice) {
        this.fFprice = fFprice;
    }

    /**
     * 获取 fDealCurAmt
     * 
     * @return fDealCurAmt.
     */
    public BigDecimal getfDealCurAmt() {
        return fDealCurAmt;
    }

    /**
     * 设置 fDealCurAmt
     * 
     * @param fDealCurAmt fDealCurAmt
     */
    public void setfDealCurAmt(BigDecimal fDealCurAmt) {
        this.fDealCurAmt = fDealCurAmt;
    }

    /**
     * 获取 fCorCurAmt
     * 
     * @return fCorCurAmt.
     */
    public BigDecimal getfCorCurAmt() {
        return fCorCurAmt;
    }

    /**
     * 设置 fCorCurAmt
     * 
     * @param fCorCurAmt fCorCurAmt
     */
    public void setfCorCurAmt(BigDecimal fCorCurAmt) {
        this.fCorCurAmt = fCorCurAmt;
    }

    /**
     * 获取 fUsdAmt
     * 
     * @return fUsdAmt.
     */
    public BigDecimal getfUsdAmt() {
        return fUsdAmt;
    }

    /**
     * 设置 fUsdAmt
     * 
     * @param fUsdAmt fUsdAmt
     */
    public void setfUsdAmt(BigDecimal fUsdAmt) {
        this.fUsdAmt = fUsdAmt;
    }

    /**
     * 获取 tradeDateBegin
     * 
     * @return tradeDateBegin.
     */
    public Date getTradeDateBegin() {
        return tradeDateBegin;
    }

    /**
     * 设置 tradeDateBegin
     * 
     * @param tradeDateBegin tradeDateBegin
     */
    public void setTradeDateBegin(Date tradeDateBegin) {
        this.tradeDateBegin = tradeDateBegin;
    }

    /**
     * 获取 tradeDateEnd
     * 
     * @return tradeDateEnd.
     */
    public Date getTradeDateEnd() {
        return tradeDateEnd;
    }

    /**
     * 设置 tradeDateEnd
     * 
     * @param tradeDateEnd tradeDateEnd
     */
    public void setTradeDateEnd(Date tradeDateEnd) {
        this.tradeDateEnd = tradeDateEnd;
    }

    /**
     * 获取 vDateBegin
     * 
     * @return vDateBegin.
     */
    public Date getvDateBegin() {
        return vDateBegin;
    }

    /**
     * 设置 vDateBegin
     * 
     * @param vDateBegin vDateBegin
     */
    public void setvDateBegin(Date vDateBegin) {
        this.vDateBegin = vDateBegin;
    }

    /**
     * 获取 vDateEnd
     * 
     * @return vDateEnd.
     */
    public Date getvDateEnd() {
        return vDateEnd;
    }

    /**
     * 设置 vDateEnd
     * 
     * @param vDateEnd vDateEnd
     */
    public void setvDateEnd(Date vDateEnd) {
        this.vDateEnd = vDateEnd;
    }

    /**
     * 获取 mDateBegin
     * 
     * @return mDateBegin.
     */
    public Date getmDateBegin() {
        return mDateBegin;
    }

    /**
     * 设置 mDateBegin
     * 
     * @param mDateBegin mDateBegin
     */
    public void setmDateBegin(Date mDateBegin) {
        this.mDateBegin = mDateBegin;
    }

    /**
     * 获取 mDateEnd
     * 
     * @return mDateEnd.
     */
    public Date getmDateEnd() {
        return mDateEnd;
    }

    /**
     * 设置 mDateEnd
     * 
     * @param mDateEnd mDateEnd
     */
    public void setmDateEnd(Date mDateEnd) {
        this.mDateEnd = mDateEnd;
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
