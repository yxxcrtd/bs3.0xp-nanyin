package com.joyintech.tams.invest.common.dto.period.trdseclendduration;

import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : 裴家俊
 * @version V1.0
 * @Project: tams-invest-parent
 * @Description: 页面查询参数
 * @date 2019年07月01日
 */
public class TrdSecLendDurationDto extends TrdFinProductDealTotal {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1977109359825502966L;

    /**
     * mode
     */
    private String mode;

    /**
     * 平安业务类型
     */
    private String paBussType;

    /**
     * 撤销原因
     */
    private String cancelRemark;

    /**
     * 附件
     */
    private MstFileInputParameter mstFileInputParameter;

    /**
     * 画面数据状态
     */
    private String statusValid;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 画面Id
     */
    private String pageId;

    /**
     * 控件Id
     */
    private String elementId;

    /**
     * 画面类型
     */
    private String pageModel;

    /**
     * 业务代码
     */
    private String busiCode;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 实体组合代码
     */
    private String actPortfolioId;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 标的债券名称
     */
    private String finprodName;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 借贷费率（交易类金融产品中的到期收益率）
     */
    private BigDecimal mYield;

    /**
     * 借贷费用（交易类金融产品中的到期金额）
     */
    private BigDecimal mTradeAmt;

    /**
     * 融入券可用持仓
     */
    private BigDecimal assetIntegrateAvailablePosition;

    /**
     * 是否现金交割
     */
    private String isCashPay;

    /**
     * 实际占券天数
     */
    private String actAmtDays;

    /**
     * 利率%
     */
    private BigDecimal intRate;

    /**
     * 期限品种
     */
    private String termType;
    /**
     * 交易对手名称
     */
    private String counterName;
    /**
     * 交易员名称
     */
    private String traderName;
    /**
     * 首次结算方式(金融产品交易中结算方式)
     */
    private String deliveryType;
    /**
     * 到期结算方式(交易类金融产品中到期结算方式)
     */
    private String mDeliveryType;
    /**
     * 争议解决方案
     */
    private String conflictSolveWay;

    /**
     * 期限
     */
    private String term;

    /**
     * 交易日历
     */
    private String calendarId;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 标的金融产品代码
     */
    private String  pledgeFinprodId;

    private WflTrdFinProductDealTotal trdFinProductDealTotal;

    // 金融产品交易信息DTO.划款指令表Entity -> 划款指令业务流程表
    private List<WflStlPayOrders> stlPayOrdersDtos;

    // 金融产品交易汇总扩展Entity -> 金融产品交易汇总扩展业务流程表
    private WflPaTrdFinDealTotal wflPaTrdFinDealTotal;

    private WflTrdDealTotalAdd wflTrdDealTotalAdd;

    // 金融产品交易Entity -> 金融产品交易业务流程表
    private List<WflTrdProductDeal> wflTrdProductDeals;

    /**
     * 获取 trdFinProductDealTotal
     *
     * @return trdFinProductDealTotal
     */
    public WflTrdFinProductDealTotal getTrdFinProductDealTotal() {
        return trdFinProductDealTotal;
    }

    /**
     * 设置 trdFinProductDealTotal
     *
     * @param trdFinProductDealTotal trdFinProductDealTotal
     */
    public void setTrdFinProductDealTotal(WflTrdFinProductDealTotal trdFinProductDealTotal) {
        this.trdFinProductDealTotal = trdFinProductDealTotal;
    }

    /**
     * 获取 stlPayOrdersDtos
     *
     * @return stlPayOrdersDtos
     */
    public List<WflStlPayOrders> getStlPayOrdersDtos() {
        return stlPayOrdersDtos;
    }

    /**
     * 设置 stlPayOrdersDtos
     *
     * @param stlPayOrdersDtos stlPayOrdersDtos
     */
    public void setStlPayOrdersDtos(List<WflStlPayOrders> stlPayOrdersDtos) {
        this.stlPayOrdersDtos = stlPayOrdersDtos;
    }

    /**
     * 获取 wflPaTrdFinDealTotal
     *
     * @return wflPaTrdFinDealTotal
     */
    public WflPaTrdFinDealTotal getWflPaTrdFinDealTotal() {
        return wflPaTrdFinDealTotal;
    }

    /**
     * 设置 wflPaTrdFinDealTotal
     *
     * @param wflPaTrdFinDealTotal wflPaTrdFinDealTotal
     */
    public void setWflPaTrdFinDealTotal(WflPaTrdFinDealTotal wflPaTrdFinDealTotal) {
        this.wflPaTrdFinDealTotal = wflPaTrdFinDealTotal;
    }

    /**
     * 获取 wflTrdDealTotalAdd
     *
     * @return wflTrdDealTotalAdd
     */
    public WflTrdDealTotalAdd getWflTrdDealTotalAdd() {
        return wflTrdDealTotalAdd;
    }

    /**
     * 设置 wflTrdDealTotalAdd
     *
     * @param wflTrdDealTotalAdd wflTrdDealTotalAdd
     */
    public void setWflTrdDealTotalAdd(WflTrdDealTotalAdd wflTrdDealTotalAdd) {
        this.wflTrdDealTotalAdd = wflTrdDealTotalAdd;
    }

    /**
     * 获取 wflTrdProductDeals
     *
     * @return wflTrdProductDeals
     */
    public List<WflTrdProductDeal> getWflTrdProductDeals() {
        return wflTrdProductDeals;
    }

    /**
     * 设置 wflTrdProductDeals
     *
     * @param wflTrdProductDeals wflTrdProductDeals
     */
    public void setWflTrdProductDeals(List<WflTrdProductDeal> wflTrdProductDeals) {
        this.wflTrdProductDeals = wflTrdProductDeals;
    }

    /**
     * 获取 operateType
     *
     * @return operateType
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

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getPaBussType() {
        return paBussType;
    }

    public void setPaBussType(String paBussType) {
        this.paBussType = paBussType;
    }

    public String getCancelRemark() {
        return cancelRemark;
    }

    public void setCancelRemark(String cancelRemark) {
        this.cancelRemark = cancelRemark;
    }

    public MstFileInputParameter getMstFileInputParameter() {
        return mstFileInputParameter;
    }

    public void setMstFileInputParameter(MstFileInputParameter mstFileInputParameter) {
        this.mstFileInputParameter = mstFileInputParameter;
    }

    public String getStatusValid() {
        return statusValid;
    }

    public void setStatusValid(String statusValid) {
        this.statusValid = statusValid;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getBusiCode() {
        return busiCode;
    }

    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    @Override
    public String getPortfolioId() {
        return portfolioId;
    }

    @Override
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public BigDecimal getmYield() {
        return mYield;
    }

    public void setmYield(BigDecimal mYield) {
        this.mYield = mYield;
    }

    public BigDecimal getmTradeAmt() {
        return mTradeAmt;
    }

    public void setmTradeAmt(BigDecimal mTradeAmt) {
        this.mTradeAmt = mTradeAmt;
    }

    public BigDecimal getIntRate() {
        return intRate;
    }

    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getmDeliveryType() {
        return mDeliveryType;
    }

    public void setmDeliveryType(String mDeliveryType) {
        this.mDeliveryType = mDeliveryType;
    }

    public String getConflictSolveWay() {
        return conflictSolveWay;
    }

    public void setConflictSolveWay(String conflictSolveWay) {
        this.conflictSolveWay = conflictSolveWay;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }

    public String getActAmtDays() {
        return actAmtDays;
    }

    public void setActAmtDays(String actAmtDays) {
        this.actAmtDays = actAmtDays;
    }

    public String getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    public String getIsCashPay() {
        return isCashPay;
    }

    public void setIsCashPay(String isCashPay) {
        this.isCashPay = isCashPay;
    }

    public String getIssuePartyId() {
        return issuePartyId;
    }

    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId;
    }

    public String getPledgeFinprodId() {
        return pledgeFinprodId;
    }

    public void setPledgeFinprodId(String pledgeFinprodId) {
        this.pledgeFinprodId = pledgeFinprodId;
    }

    /**
     * 获取 actPortfolioId
     *
     * @return actPortfolioId
     */
    public String getActPortfolioId() {
        return actPortfolioId;
    }

    /**
     * 设置 actPortfolioId
     *
     * @param actPortfolioId actPortfolioId
     */
    public void setActPortfolioId(String actPortfolioId) {
        this.actPortfolioId = actPortfolioId;
    }

    /**
     * 获取 assetIntegrateAvailablePosition
     *
     * @return assetIntegrateAvailablePosition
     */
    public BigDecimal getAssetIntegrateAvailablePosition() {
        return assetIntegrateAvailablePosition;
    }

    /**
     * 设置 assetIntegrateAvailablePosition
     *
     * @param assetIntegrateAvailablePosition assetIntegrateAvailablePosition
     */
    public void setAssetIntegrateAvailablePosition(BigDecimal assetIntegrateAvailablePosition) {
        this.assetIntegrateAvailablePosition = assetIntegrateAvailablePosition;
    }
}
