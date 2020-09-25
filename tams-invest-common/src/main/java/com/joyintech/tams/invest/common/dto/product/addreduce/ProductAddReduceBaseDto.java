package com.joyintech.tams.invest.common.dto.product.addreduce;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;

/**
 * @program: tams-invest-parent
 * @description:
 * @author: 强成西
 * @create: 2019-01-10 19:24
 **/
public class ProductAddReduceBaseDto extends BaseEntity {

    /**
     * 汇总交易编号
     */
    private String totleTradeId;

    /**
     * 产品代码
     */
    private String finprodId;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 是否分层
     */
    private String isLay;

    /**
     * 分层类型
     */
    private String layType;

    /**
     * 起息日
     */
    private String vDate;

    /**
     * 到期日
     */
    private String mDate;

    private String prdVdate;

    private String prdMdate;

    private String remark;

    /**
     * 确认日
     */
    private String tradeDate;

    /**
     * 产品简称
     */
    private String prodAbbr;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 产品登记编码
     */
    private String prodRegistCode;

    /**
     * 销售部门
     */
    private String saleDepartment;

    /**
     * 分行
     */
    private String entrusterId;

    /**
     * 分行名
     */
    private String entrusterName;

    /**
     * 本金收益特征
     */
    private String capiIncomeFeature;

    /**
     * 收益类型
     */
    private String profitType;

    /**
     * 组合代码
     */
    private String ptlId;

    /**
     * 撤销说明
     */
    private String revokeReason;

    /**
     * 金额
     */
    private BigDecimal tradeAmt;

    /**
     * 强减本金
     */
    private BigDecimal prinAmt;

    /**
     * 份额
     */
    private BigDecimal shareAmt;

    /**
     * 应付客户收益
     */
    private BigDecimal interest;

    /**
     * 实付客户收益
     */
    private BigDecimal actCusProfit;

    /**
     * 单位净值
     */
    private BigDecimal unitCprice;

    /**
     * 净值日
     */
    private String cpriceDate;

    /**
     * 组装的交易数据
     */
    private ProductTradeInfoDto productTradeInfoDto;

    /**
     * 强减信息
     */
    private List<ReduceInfoDto> reduceInfoDtoList;

    /**
     * 费用信息
     */
    private List<ProductFeeInfo> productFeeInfoList;

    /**
     * 确认日
     */
    private String settleDate;

    /**
     * 违约金
     */
    private BigDecimal sdefaultAmt;
    /**
     * 市场代码
     */
    private String finprodMarketId;
    /**
     * 获取 productFeeInfoList
     *
     * @return productFeeInfoList
     */
    public List<ProductFeeInfo> getProductFeeInfoList() {
        return productFeeInfoList;
    }

    /**
     * 设置 productFeeInfoList
     *
     * @param productFeeInfoList productFeeInfoList
     */
    public void setProductFeeInfoList(List<ProductFeeInfo> productFeeInfoList) {
        this.productFeeInfoList = productFeeInfoList;
    }

    /**
     * 获取 totleTradeId
     *
     * @return totleTradeId
     */
    public String getTotleTradeId() {
        return totleTradeId;
    }

    /**
     * 设置 totleTradeId
     *
     * @param totleTradeId totleTradeId
     */
    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId;
    }

    /**
     * 获取 prodAbbr
     *
     * @return prodAbbr
     */
    public String getProdAbbr() {
        return prodAbbr;
    }

    /**
     * 设置 prodAbbr
     *
     * @param prodAbbr prodAbbr
     */
    public void setProdAbbr(String prodAbbr) {
        this.prodAbbr = prodAbbr;
    }

    /**
     * 获取 prodName
     *
     * @return prodName
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 prodName
     *
     * @param prodName prodName
     */
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    /**
     * 获取 prodRegistCode
     *
     * @return prodRegistCode
     */
    public String getProdRegistCode() {
        return prodRegistCode;
    }

    /**
     * 设置 prodRegistCode
     *
     * @param prodRegistCode prodRegistCode
     */
    public void setProdRegistCode(String prodRegistCode) {
        this.prodRegistCode = prodRegistCode;
    }

    /**
     * 获取 saleDepartment
     *
     * @return saleDepartment
     */
    public String getSaleDepartment() {
        return saleDepartment;
    }

    /**
     * 设置 saleDepartment
     *
     * @param saleDepartment saleDepartment
     */
    public void setSaleDepartment(String saleDepartment) {
        this.saleDepartment = saleDepartment;
    }

    /**
     * 获取 capiIncomeFeature
     *
     * @return capiIncomeFeature
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
     * 获取 profitType
     *
     * @return profitType
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 profitType
     *
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    /**
     * 获取 ptlId
     *
     * @return ptlId
     */
    public String getPtlId() {
        return ptlId;
    }

    /**
     * 设置 ptlId
     *
     * @param ptlId ptlId
     */
    public void setPtlId(String ptlId) {
        this.ptlId = ptlId;
    }

    /**
     * 获取 revokeReason
     *
     * @return revokeReason
     */
    public String getRevokeReason() {
        return revokeReason;
    }

    /**
     * 设置 revokeReason
     *
     * @param revokeReason revokeReason
     */
    public void setRevokeReason(String revokeReason) {
        this.revokeReason = revokeReason;
    }

    /**
     * 获取 productTradeInfoDto
     *
     * @return productTradeInfoDto
     */
    public ProductTradeInfoDto getProductTradeInfoDto() {
        return productTradeInfoDto;
    }

    /**
     * 设置 productTradeInfoDto
     *
     * @param productTradeInfoDto productTradeInfoDto
     */
    public void setProductTradeInfoDto(ProductTradeInfoDto productTradeInfoDto) {
        this.productTradeInfoDto = productTradeInfoDto;
    }

    /**
     * 获取 reduceInfoDtoList
     *
     * @return reduceInfoDtoList
     */
    public List<ReduceInfoDto> getReduceInfoDtoList() {
        return reduceInfoDtoList;
    }

    /**
     * 设置 reduceInfoDtoList
     *
     * @param reduceInfoDtoList reduceInfoDtoList
     */
    public void setReduceInfoDtoList(List<ReduceInfoDto> reduceInfoDtoList) {
        this.reduceInfoDtoList = reduceInfoDtoList;
    }

    /**
     * 获取 finprodId
     *
     * @return finprodId
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     *
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 ps
     *
     * @return ps
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 ps
     *
     * @param ps ps
     */
    public void setPs(String ps) {
        this.ps = ps;
    }

    /**
     * 获取 ccy
     *
     * @return ccy
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 ccy
     *
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 tradeDate
     *
     * @return tradeDate
     */
    public String getTradeDate() {
        return tradeDate;
    }

    /**
     * 设置 tradeDate
     *
     * @param tradeDate tradeDate
     */
    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * 获取 isLay
     *
     * @return isLay
     */
    public String getIsLay() {
        return isLay;
    }

    /**
     * 设置 isLay
     *
     * @param isLay isLay
     */
    public void setIsLay(String isLay) {
        this.isLay = isLay;
    }

    /**
     * 获取 layType
     *
     * @return layType
     */
    public String getLayType() {
        return layType;
    }

    /**
     * 设置 layType
     *
     * @param layType layType
     */
    public void setLayType(String layType) {
        this.layType = layType;
    }

    /**
     * 获取 vDate
     *
     * @return vDate
     */
    public String getvDate() {
        return vDate;
    }

    /**
     * 设置 vDate
     *
     * @param vDate vDate
     */
    public void setvDate(String vDate) {
        this.vDate = vDate;
    }

    /**
     * 获取 mDate
     *
     * @return mDate
     */
    public String getmDate() {
        return mDate;
    }

    /**
     * 设置 mDate
     *
     * @param mDate mDate
     */
    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    /**
     * 获取 tradeAmt
     *
     * @return tradeAmt
     */
    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    /**
     * 设置 tradeAmt
     *
     * @param tradeAmt tradeAmt
     */
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    /**
     * 获取 prinAmt
     *
     * @return prinAmt
     */
    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    /**
     * 设置 prinAmt
     *
     * @param prinAmt prinAmt
     */
    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    /**
     * 获取 shareAmt
     *
     * @return shareAmt
     */
    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    /**
     * 设置 shareAmt
     *
     * @param shareAmt shareAmt
     */
    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    /**
     * 获取 interest
     *
     * @return interest
     */
    public BigDecimal getInterest() {
        return interest;
    }

    /**
     * 设置 interest
     *
     * @param interest interest
     */
    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    /**
     * 获取 actCusProfit
     *
     * @return actCusProfit
     */
    public BigDecimal getActCusProfit() {
        return actCusProfit;
    }

    /**
     * 设置 actCusProfit
     *
     * @param actCusProfit actCusProfit
     */
    public void setActCusProfit(BigDecimal actCusProfit) {
        this.actCusProfit = actCusProfit;
    }

    /**
     * 获取 unitCprice
     *
     * @return unitCprice
     */
    public BigDecimal getUnitCprice() {
        return unitCprice;
    }

    /**
     * 设置 unitCprice
     *
     * @param unitCprice unitCprice
     */
    public void setUnitCprice(BigDecimal unitCprice) {
        this.unitCprice = unitCprice;
    }

    /**
     * 获取 cpriceDate
     *
     * @return cpriceDate
     */
    public String getCpriceDate() {
        return cpriceDate;
    }

    /**
     * 设置 cpriceDate
     *
     * @param cpriceDate cpriceDate
     */
    public void setCpriceDate(String cpriceDate) {
        this.cpriceDate = cpriceDate;
    }

    /**
     * 获取 entrusterId
     *
     * @return entrusterId
     */
    public String getEntrusterId() {
        return entrusterId;
    }

    /**
     * 设置 entrusterId
     *
     * @param entrusterId entrusterId
     */
    public void setEntrusterId(String entrusterId) {
        this.entrusterId = entrusterId;
    }

    /**
     * 获取 prdVdate
     *
     * @return prdVdate
     */
    public String getPrdVdate() {
        return prdVdate;
    }

    /**
     * 设置 prdVdate
     *
     * @param prdVdate prdVdate
     */
    public void setPrdVdate(String prdVdate) {
        this.prdVdate = prdVdate;
    }

    /**
     * 获取 prdMdate
     *
     * @return prdMdate
     */
    public String getPrdMdate() {
        return prdMdate;
    }

    /**
     * 设置 prdMdate
     *
     * @param prdMdate prdMdate
     */
    public void setPrdMdate(String prdMdate) {
        this.prdMdate = prdMdate;
    }

    /**
     * 获取 remark
     *
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 remark
     *
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 entrusterName
     *
     * @return entrusterName
     */
    public String getEntrusterName() {
        return entrusterName;
    }

    /**
     * 设置 entrusterName
     *
     * @param entrusterName entrusterName
     */
    public void setEntrusterName(String entrusterName) {
        this.entrusterName = entrusterName;
    }

    /**
     * 获取 settleDate
     * @return settleDate.
     */
    public String getSettleDate() {
        return settleDate;
    }

    /**
     * 设置 settleDate
     * @param settleDate settleDate
     */
    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    /**
     * 获取 sdefaultAmt
     * @return sdefaultAmt.
     */
    public BigDecimal getSdefaultAmt() {
        return sdefaultAmt;
    }

    /**
     * 设置 sdefaultAmt
     * @param sdefaultAmt sdefaultAmt
     */
    public void setSdefaultAmt(BigDecimal sdefaultAmt) {
        this.sdefaultAmt = sdefaultAmt;
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

}