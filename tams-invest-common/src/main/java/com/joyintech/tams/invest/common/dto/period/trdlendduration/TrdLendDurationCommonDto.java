/*
 * 文件名：TrdLendDurationCommonDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.dto.period.trdlendduration;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;

/**
 * 拆借存续期共通dto
 *
 * @author etliu
 * @version 1.0 2018年12月14日
 * @see TrdLendDurationCommonDto
 * @since 1.0
 */
public class TrdLendDurationCommonDto extends TrdFinProductDealTotal {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1977109359825502966L;

    /**
     * mode
     */
    private String mode;

    /**
     * 拆借类型
     */
    private String bankLendingType;

    /**
     * 发行机构代码
     */
    private String repIssuePartyId;

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
     * 本金收益特征
     */
    private String capiIncomeFeature;

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
     * 组合代码
     */
    private String actPortfolioId;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 本金金额
     */
    private BigDecimal theoryPrin;

    /**
     * 资金到账日
     */
    private Date settleDate;

    /**
     * 应计利息
     */
    private BigDecimal theoryInt;

    /**
     * 到期本息和
     */
    private BigDecimal theoryAmt;

    /**
     * 实付本息和
     */
    private BigDecimal tradeAmt;

    /**
     * 实收本息和
     */
    private BigDecimal actRecPrincipa;

    /**
     * 利率%
     */
    private BigDecimal intRate;
    /**
     * 交易对手名称
     */
    private BigDecimal counterName;
    /**
     * 交易员名称
     */
    private BigDecimal traderName;
    
    
    /**
     * 获取 counterName
     * @return counterName.
     */
    public BigDecimal getCounterName() {
        return counterName;
    }

    
    /**
     * 设置 counterName
     * @param counterName counterName
     */
    public void setCounterName(BigDecimal counterName) {
        this.counterName = counterName;
    }

    
    /**
     * 获取 traderName
     * @return traderName.
     */
    public BigDecimal getTraderName() {
        return traderName;
    }

    
    /**
     * 设置 traderName
     * @param traderName traderName
     */
    public void setTraderName(BigDecimal traderName) {
        this.traderName = traderName;
    }

    @Override
    public String getPs() {
        return DictCodeList.交易方向_到期.getValue();
    }

    /**
     * 获取 mstFileInputParameter
     *
     * @return mstFileInputParameter.
     */
    public MstFileInputParameter getMstFileInputParameter() {
        return mstFileInputParameter;
    }

    /**
     * 设置 mstFileInputParameter
     *
     * @param mstFileInputParameter mstFileInputParameter
     */
    public void setMstFileInputParameter(MstFileInputParameter mstFileInputParameter) {
        this.mstFileInputParameter = mstFileInputParameter;
    }

    /**
     * 获取 statusValid
     *
     * @return statusValid.
     */
    public String getStatusValid() {
        return statusValid;
    }

    /**
     * 设置 statusValid
     *
     * @param statusValid statusValid
     */
    public void setStatusValid(String statusValid) {
        this.statusValid = statusValid;
    }

    /**
     * 获取 operateType
     *
     * @return operateType.
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

    /**
     * 获取 pageId
     *
     * @return pageId.
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * 设置 pageId
     *
     * @param pageId pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
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
     * 获取 capiIncomeFeature
     *
     * @return capiIncomeFeature.
     */
    public String getCapiIncomeFeature() {
        return capiIncomeFeature;
    }

    /**
     * 设置 capiIncomeFeature
     *
     * @param capiIncomeFeature capiIncomeFeature
     */
    public void setCapiIncomeFeature(String capiIncomeFeature) {
        this.capiIncomeFeature = capiIncomeFeature;
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
     * 获取 busiCode
     *
     * @return busiCode.
     */
    public String getBusiCode() {
        return busiCode;
    }

    /**
     * 设置 busiCode
     *
     * @param busiCode busiCode
     */
    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    /**
     * 获取 portfolioId
     *
     * @return portfolioId.
     */
    @Override
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 portfolioId
     *
     * @param portfolioId portfolioId
     */
    @Override
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
     * 获取 basis
     *
     * @return basis.
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 basis
     *
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    /**
     * 获取 theoryPrin
     *
     * @return theoryPrin.
     */
    public BigDecimal getTheoryPrin() {
        return theoryPrin;
    }

    /**
     * 设置 theoryPrin
     *
     * @param theoryPrin theoryPrin
     */
    public void setTheoryPrin(BigDecimal theoryPrin) {
        this.theoryPrin = theoryPrin;
    }

    /**
     * 获取 settleDate
     *
     * @return settleDate.
     */
    @Override
    public Date getSettleDate() {
        return settleDate;
    }

    /**
     * 设置 settleDate
     *
     * @param settleDate settleDate
     */
    @Override
    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    /**
     * 获取 theoryInt
     *
     * @return theoryInt.
     */
    public BigDecimal getTheoryInt() {
        return theoryInt;
    }

    /**
     * 设置 theoryInt
     *
     * @param theoryInt theoryInt
     */
    public void setTheoryInt(BigDecimal theoryInt) {
        this.theoryInt = theoryInt;
    }

    /**
     * 获取 theoryAmt
     *
     * @return theoryAmt.
     */
    public BigDecimal getTheoryAmt() {
        return theoryAmt;
    }

    /**
     * 设置 theoryAmt
     *
     * @param theoryAmt theoryAmt
     */
    public void setTheoryAmt(BigDecimal theoryAmt) {
        this.theoryAmt = theoryAmt;
    }

    /**
     * 获取 tradeAmt
     *
     * @return tradeAmt.
     */
    @Override
    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    /**
     * 设置 tradeAmt
     *
     * @param tradeAmt tradeAmt
     */
    @Override
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    /**
     * 获取 cancelRemark
     *
     * @return cancelRemark.
     */
    public String getCancelRemark() {
        return cancelRemark;
    }

    /**
     * 设置 cancelRemark
     *
     * @param cancelRemark cancelRemark
     */
    public void setCancelRemark(String cancelRemark) {
        this.cancelRemark = cancelRemark;
    }

    /**
     * 设置 paBussType
     *
     * @param paBussType paBussType
     */
    public void setPaBussType(String paBussType) {
        this.paBussType = paBussType;
    }

    /**
     * 获取 actRecPrincipa
     *
     * @return actRecPrincipa.
     */
    public BigDecimal getActRecPrincipa() {
        return actRecPrincipa;
    }

    /**
     * 设置 actRecPrincipa
     *
     * @param actRecPrincipa actRecPrincipa
     */
    public void setActRecPrincipa(BigDecimal actRecPrincipa) {
        this.actRecPrincipa = actRecPrincipa;
    }

    /**
     * 获取 intRate
     *
     * @return intRate.
     */
    public BigDecimal getIntRate() {
        return intRate;
    }

    /**
     * 设置 intRate
     *
     * @param intRate intRate
     */
    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }

    /**
     * 获取 repIssuePartyId
     *
     * @return repIssuePartyId.
     */
    public String getRepIssuePartyId() {
        return repIssuePartyId;
    }

    /**
     * 设置 repIssuePartyId
     *
     * @param repIssuePartyId repIssuePartyId
     */
    public void setRepIssuePartyId(String repIssuePartyId) {
        this.repIssuePartyId = repIssuePartyId;
    }

    /**
     * 获取 paBussType
     *
     * @return paBussType.
     */
    public String getPaBussType() {
        if (StringUtil.equals(paBussType, DictCodeListExt1.平安业务类型_普通拆入.getValue())) {
            return DictCodeListExt1.平安业务类型_普通拆入到期.getValue();
        }
        else if (StringUtil.equals(paBussType, DictCodeListExt1.平安业务类型_普通拆出.getValue())) {
            return DictCodeListExt1.平安业务类型_普通拆出到期.getValue();
        }
        else {
            return DictCodeListExt1.平安业务类型_特拆拆入到期.getValue();
        }
    }

    /**
     * 获取 bankLendingType
     *
     * @return bankLendingType.
     */
    public String getBankLendingType() {
        return bankLendingType;
    }

    /**
     * 获取 mode
     *
     * @return mode.
     */
    public String getMode() {
        return mode;
    }

    /**
     * 设置 mode
     *
     * @param mode mode
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * 设置 bankLendingType
     *
     * @param bankLendingType bankLendingType
     */
    public void setBankLendingType(String bankLendingType) {
        this.bankLendingType = bankLendingType;
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
}
