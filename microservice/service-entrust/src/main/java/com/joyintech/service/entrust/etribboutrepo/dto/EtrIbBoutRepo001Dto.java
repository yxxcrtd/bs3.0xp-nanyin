package com.joyintech.service.entrust.etribboutrepo.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 银行间债券指令
 * @author chengzhong
 * @version 1.0 2021年1月19日
 * @see EtrIbBoutRepo001Dto
 * @since 1.0
 */
public class EtrIbBoutRepo001Dto extends BaseEntity {

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 市场代码
     */
    private String finprodMarketId;

    /**
     * 资产简称
     */
    private String finprodAbbr;

    /**
     * 指令编号
     */
    private String entrustId;

    /**
     * 有效状态
     */
    private String entrustStatus;

    /**
     * 投资经理
     */
    private String invManagerId;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 券面总额
     */
    private BigDecimal shareNum;

    /**
     * 价格模式
     */
    private String priceModel;

    /**
     * 清算速度
     */
    private String settleSpreed;

    /**
     * 指令开始日
     */
    private Date vdate;

    /**
     * 指令结束日
     */
    private Date mdate;

    /**
     * 回购利率
     */
    private BigDecimal price;

    /**
     * 已完成额度
     */
    private BigDecimal finishAmt;

    /**
     * 交易完成度
     */
    private BigDecimal transactioncom;

    /**
     * 备注
     */
    private String remark;

    /**
     * 通道代码
     */
    private String channelId;

    /**
     * 期限品种
     */
    private String termType;

    /**
     * 天数
     */
    private String repoTerm;


    /**
     * 指令金额
     */
    private BigDecimal entrustAmt;

    /**
     * 证券账户/通道代码
     */
    private String secAcctId;

    /**
     * 证券账户/通道名称
     */
    private String secAcctStr;
    /**
     * 金融产品Id
     */
    private String finProdId;
    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 可质押券面总额
     */
    private BigDecimal pledStrdPrinAmt;
    /**
     * 持仓券面总额
     */
    private BigDecimal posStrdPrinAmt;
    /**
     * 登记托管机构
     */
    private String registOrg ;
    /**
     * 折算比例
     */
    private BigDecimal scale;
    /**
     * 债项评级
     */
    private String gradeResult;

    /**
     * 主体评级
     */
    private String ztgradeResult;


    /**
     * 剩余期限
     */
    private BigDecimal lastPeriod;
    /**
     * 首次净价开始
     */
    private BigDecimal firstprice;

    /**
     * 首次净价结束
     */
    private BigDecimal firstprice2;

    /**
     * 融入券面总额(万元)
     */
    private BigDecimal inStrdPrinAmt;

    /**
     * 融出券面总额(万元)
     */
    private BigDecimal outStrdPrinAmt;

    /**
     * 可用券面总额(万元)
     */
    private BigDecimal avaStrdPrinAmt;

    /**
     * 最新净价
     */
    private BigDecimal newPrice;

    /**
     * 债券一级分类
     */
    private String secType1;

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    public String getEntrustStatus() {
        return entrustStatus;
    }

    public void setEntrustStatus(String entrustStatus) {
        this.entrustStatus = entrustStatus;
    }

    public String getInvManagerId() {
        return invManagerId;
    }

    public void setInvManagerId(String invManagerId) {
        this.invManagerId = invManagerId;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public BigDecimal getShareNum() {
        return shareNum;
    }

    public void setShareNum(BigDecimal shareNum) {
        this.shareNum = shareNum;
    }

    public String getPriceModel() {
        return priceModel;
    }

    public void setPriceModel(String priceModel) {
        this.priceModel = priceModel;
    }

    public String getSettleSpreed() {
        return settleSpreed;
    }

    public void setSettleSpreed(String settleSpreed) {
        this.settleSpreed = settleSpreed;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public BigDecimal getFinishAmt() {
        return finishAmt;
    }

    public void setFinishAmt(BigDecimal finishAmt) {
        this.finishAmt = finishAmt;
    }

    public BigDecimal getTransactioncom() {
        return transactioncom;
    }

    public void setTransactioncom(BigDecimal transactioncom) {
        this.transactioncom = transactioncom;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }

    public String getRepoTerm() {
        return repoTerm;
    }

    public void setRepoTerm(String repoTerm) {
        this.repoTerm = repoTerm;
    }

    public BigDecimal getEntrustAmt() {
        return entrustAmt;
    }

    public void setEntrustAmt(BigDecimal entrustAmt) {
        this.entrustAmt = entrustAmt;
    }

    public String getSecAcctId() {
        return secAcctId;
    }

    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    public String getFinProdId() {
        return finProdId;
    }

    public void setFinProdId(String finProdId) {
        this.finProdId = finProdId;
    }

    public Long getBranch() {
        return branch;
    }

    public void setBranch(Long branch) {
        this.branch = branch;
    }

    public String getSecAcctStr() {
        return secAcctStr;
    }

    public void setSecAcctStr(String secAcctStr) {
        this.secAcctStr = secAcctStr;
    }

    public BigDecimal getPledStrdPrinAmt() {
        return pledStrdPrinAmt;
    }

    public void setPledStrdPrinAmt(BigDecimal pledStrdPrinAmt) {
        this.pledStrdPrinAmt = pledStrdPrinAmt;
    }

    public BigDecimal getPosStrdPrinAmt() {
        return posStrdPrinAmt;
    }

    public void setPosStrdPrinAmt(BigDecimal posStrdPrinAmt) {
        this.posStrdPrinAmt = posStrdPrinAmt;
    }

    public String getRegistOrg() {
        return registOrg;
    }

    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg;
    }

    public BigDecimal getScale() {
        return scale;
    }

    public void setScale(BigDecimal scale) {
        this.scale = scale;
    }

    public String getGradeResult() {
        return gradeResult;
    }

    public void setGradeResult(String gradeResult) {
        this.gradeResult = gradeResult;
    }

    public BigDecimal getLastPeriod() {
        return lastPeriod;
    }

    public void setLastPeriod(BigDecimal lastPeriod) {
        this.lastPeriod = lastPeriod;
    }



    public BigDecimal getFirstprice() {
        return firstprice;
    }

    public void setFirstprice(BigDecimal firstprice) {
        this.firstprice = firstprice;
    }

    public BigDecimal getFirstprice2() {
        return firstprice2;
    }

    public void setFirstprice2(BigDecimal firstprice2) {
        this.firstprice2 = firstprice2;
    }

    public String getZtgradeResult() {
        return ztgradeResult;
    }

    public void setZtgradeResult(String ztgradeResult) {
        this.ztgradeResult = ztgradeResult;
    }

    public BigDecimal getInStrdPrinAmt() {
        return inStrdPrinAmt;
    }

    public void setInStrdPrinAmt(BigDecimal inStrdPrinAmt) {
        this.inStrdPrinAmt = inStrdPrinAmt;
    }

    public BigDecimal getOutStrdPrinAmt() {
        return outStrdPrinAmt;
    }

    public void setOutStrdPrinAmt(BigDecimal outStrdPrinAmt) {
        this.outStrdPrinAmt = outStrdPrinAmt;
    }

    public BigDecimal getAvaStrdPrinAmt() {
        return avaStrdPrinAmt;
    }

    public void setAvaStrdPrinAmt(BigDecimal avaStrdPrinAmt) {
        this.avaStrdPrinAmt = avaStrdPrinAmt;
    }

    public BigDecimal getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(BigDecimal newPrice) {
        this.newPrice = newPrice;
    }

    public String getSecType1() {
        return secType1;
    }

    public void setSecType1(String secType1) {
        this.secType1 = secType1;
    }

    private String parentPortfolioId;


    public String getParentPortfolioId() {
        return parentPortfolioId;
    }

    public void setParentPortfolioId(String parentPortfolioId) {
        this.parentPortfolioId = parentPortfolioId;
    }

}
