/*
 * 文件名：TrdFxSwapRateInvestTradeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年11月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.trademanage.trdfxswapvaluationtrade.dto;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.WflTrdProductDeal;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description:外汇掉期交易
 * @auther: yuzj
 * @date: 2022/1/24
 * @param:
 * @return:
 */
public class TrdFxSwapValuationTradeDto extends WflTrdProductDeal implements ProcessEntity {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 5309538894816479429L;

    /**
     * 产品代码
     */
    private String portfolioId;

    /**
     * 产品名称
     */
    private String portfolioName;

    /**
     * 结算金额
     */
    private BigDecimal mdateSettleAmt;


    /**
     * 资金账户
     */
    private String trdAcctId;

    /**
     * 外部交易编号
     */
    private String outTradeId;



    /**
     * 金融产品名称
     */
    private String finprodName;

    /**
     * 交易手续费
     */
    private BigDecimal procedureAmt;
    /**
     * 结算服务费
     */
    private BigDecimal settleServiceAmt;

    /**
     * 货币对
     */
    private String curPair;

    /**
     * 交易类型
     */
    private String ps;

    /**
     * 期限类型
     */
    private String termType;

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
     * 交易货币
     */
    private String dealCur;


    /**
     * 对应货币
     */
    private String corCcy;

    /**
     * 即期汇率
     */
    private BigDecimal unitCprice;

    /**
     * 近端交割日
     */
    private Date vdate;

    /**
     * 近端掉期点
     */
    private BigDecimal nPoint;

    /**
     * 近端掉期全价
     */
    private BigDecimal nFprice;

    /**
     * 对应货币金额
     */
    private BigDecimal nCorCcyAmt;

    /**
     * 对应货币金额
     */
    private BigDecimal nUsdAmt;

    /**
     * 远端交割日
     */
    private Date mdate;

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
    private BigDecimal fCorCcyAmt;

    /**
     * 折美元金额
     */
    private BigDecimal fUsdAmt;

    /**
     * 其他费用
     */
    private BigDecimal feeAmtQT;

    /**
     * 交易货币金额
     */
    private BigDecimal nDealCurAmt;




    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 市场机构代码
     */
    private String finprodMarketId;
    /**
     * elementId
     */
    private String elementId;
    /**
     * 计划类实体类
     */
    private List<TrdFxSwapValuationTradeDto> selectedItem;

    /**
     * 撤销原因
     */
    private String cancelRemark;
    /**
     * pageModel
     */
    private String pageModel;


    /**
     * 获取 pageModel
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }

    /**
     * 获取 finprodMarketId
     * @return finprodMarketId.
     */
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
     * 设置 pageModel
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }
    /**
     * 金融产品类型
     * {@inheritDoc}
     */
    @Override
    public String getFinprodType(){
        return super.getFinprodType();
    }

    @Override
    public Date getTradeDate(){
        return super.getTradeDate();
    }

    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<TrdFxSwapValuationTradeDto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<TrdFxSwapValuationTradeDto> selectedItem) {
        this.selectedItem = selectedItem;
    }
    /**
     * 设置主键
     * {@inheritDoc}
     */
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getTradeId(){
        return super.getTradeId();
    }
    /**
     * 业务类型
     * {@inheritDoc}
     */
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getBusiType(){
        return super.getBusiType();
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

    public String getTrdAcctId() {
        return trdAcctId;
    }

    public void setTrdAcctId(String trdAcctId) {
        this.trdAcctId = trdAcctId;
    }

    @Override
    public String getCancelRemark() {
        return cancelRemark;
    }

    @Override
    public void setCancelRemark(String cancelRemark) {
        this.cancelRemark = cancelRemark;
    }

    /**
     * 获取 operatType
     * @return operatType.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operatType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 币种字符串
     * @return 币种
     * @see
     * @since 1.0
     */
    public String getCcyStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
    }
    /**
     * 币种
     * {@inheritDoc}
     */
    @Override
    public String getCcy(){
        return super.getCcy();
    }
    /**
     * 交易方向
     * {@inheritDoc}
     */
    public String getPs(){
        return super.getPs();
    }
    /**
     * 交易方向字符串
     * @return 交易方向
     * @see
     * @since 1.0
     */
    public String getPsStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
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
     * 设置 portfolioNamefinprodMarketId
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
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


    public String getOutTradeId() {
        return outTradeId;
    }

    public void setOutTradeId(String outTradeId) {
        this.outTradeId = outTradeId;
    }


    public BigDecimal getMdateSettleAmt() {
        return mdateSettleAmt;
    }

    public void setMdateSettleAmt(BigDecimal mdateSettleAmt) {
        this.mdateSettleAmt = mdateSettleAmt;
    }


    //交易金额
    @Override
    public BigDecimal getTradeAmt() {
        return super.getTradeAmt();
    }
    //交易费用
    @Override
    public BigDecimal getFeeAmt() {
        return super.getFeeAmt();
    }

    //资产代码
    @Override
    public String getFinprodId() {
        return super.getFinprodId();
    }


    public String getInvAimStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.投资目的, super.getInvAim());
    }

    public String getTradeDateStr() {
        String tradeDateStr="";
        if(super.getTradeDate() !=null){
            tradeDateStr= DateUtil.format(super.getTradeDate());
        }
        return tradeDateStr;
    }

    public String getSettleDateStr() {
        String settleDateStr="";
        if(super.getSettleDate() !=null){
            settleDateStr= DateUtil.format(super.getSettleDate());
        }
        return settleDateStr;
    }


    public void setProcedureAmt(BigDecimal procedureAmt) {
        this.procedureAmt = procedureAmt;
    }
    public BigDecimal getSettleServiceAmt() {
        return settleServiceAmt;
    }

    public BigDecimal getProcedureAmt() {
        return procedureAmt;
    }

    public void setSettleServiceAmt(BigDecimal settleServiceAmt) {
        this.settleServiceAmt = settleServiceAmt;
    }

    public String getCurPair() {
        return curPair;
    }

    public void setCurPair(String curPair) {
        this.curPair = curPair;
    }

    @Override
    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }
    public String getOurRole() {
        return ourRole;
    }

    public void setOurRole(String ourRole) {
        this.ourRole = ourRole;
    }
    public String getDealMode() {
        return dealMode;
    }

    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }
    public String getSettMode() {
        return settMode;
    }

    public void setSettMode(String settMode) {
        this.settMode = settMode;
    }
    public String getDealCur() {
        return dealCur;
    }

    public void setDealCur(String dealCur) {
        this.dealCur = dealCur;
    }
    public String getCorCcy() {
        return corCcy;
    }

    public void setCorCcy(String corCcy) {
        this.corCcy = corCcy;
    }

    @Override
    public BigDecimal getUnitCprice() {
        return unitCprice;
    }

    @Override
    public void setUnitCprice(BigDecimal unitCprice) {
        this.unitCprice = unitCprice;
    }

    @Override
    public Date getVdate() {
        return vdate;
    }

    @Override
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public BigDecimal getnPoint() {
        return nPoint;
    }

    public void setnPoint(BigDecimal nPoint) {
        this.nPoint = nPoint;
    }
    public BigDecimal getnFprice() {
        return nFprice;
    }

    public void setnFprice(BigDecimal nFprice) {
        this.nFprice = nFprice;
    }
    public BigDecimal getnCorCcyAmt() {
        return nCorCcyAmt;
    }

    public void setnCorCcyAmt(BigDecimal nCorCcyAmt) {
        this.nCorCcyAmt = nCorCcyAmt;
    }
    public BigDecimal getnUsdAmt() {
        return nUsdAmt;
    }

    public void setnUsdAmt(BigDecimal nUsdAmt) {
        this.nUsdAmt = nUsdAmt;
    }

    @Override
    public Date getMdate() {
        return mdate;
    }

    @Override
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }
    public BigDecimal getfPoint() {
        return fPoint;
    }

    public void setfPoint(BigDecimal fPoint) {
        this.fPoint = fPoint;
    }
    public BigDecimal getfFprice() {
        return fFprice;
    }

    public void setfFprice(BigDecimal fFprice) {
        this.fFprice = fFprice;
    }
    public BigDecimal getfDealCurAmt() {
        return fDealCurAmt;
    }

    public void setfDealCurAmt(BigDecimal fDealCurAmt) {
        this.fDealCurAmt = fDealCurAmt;
    }

    public BigDecimal getfCorCcyAmt() {
        return fCorCcyAmt;
    }

    public void setfCorCcyAmt(BigDecimal fCorCcyAmt) {
        this.fCorCcyAmt = fCorCcyAmt;
    }
    public BigDecimal getfUsdAmt() {
        return fUsdAmt;
    }

    public void setfUsdAmt(BigDecimal fUsdAmt) {
        this.fUsdAmt = fUsdAmt;
    }
    public BigDecimal getFeeAmtQT() {
        return feeAmtQT;
    }

    public void setFeeAmtQT(BigDecimal feeAmtQT) {
        this.feeAmtQT = feeAmtQT;
    }

    public BigDecimal getnDealCurAmt() {
        return nDealCurAmt;
    }

    public void setnDealCurAmt(BigDecimal nDealCurAmt) {
        this.nDealCurAmt = nDealCurAmt;
    }
}


