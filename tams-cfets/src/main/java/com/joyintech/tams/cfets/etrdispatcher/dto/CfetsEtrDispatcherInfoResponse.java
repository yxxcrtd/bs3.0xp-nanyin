package com.joyintech.tams.cfets.etrdispatcher.dto;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.EtrEntrustBidPrice;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 场外指令分发
 * 指令明细返回数据
 * @author sjj
 * @version 1.0 2021年10月26日
 * @see CfetsEtrDispatcherTradeListResponse
 * @since 1.0
 */
@ApiModel(value="指令明细返回数据",description="指令明细数据")
public class CfetsEtrDispatcherInfoResponse extends CfetsEtrDispatcherBaseInfo{

    //债券一级投标 start
    @ApiModelProperty(name="mainConsignee",value="主承销商",example="字符串1")
    private String mainConsignee;

    @ApiModelProperty(name="paymentDate",value="缴款日",example="date'2021-01-01'")
    private Date paymentDate;

    @ApiModelProperty(name="listingDate",value="上市日",example="date'2021-01-01'")
    private Date listingDate;

    @ApiModelProperty(name="entrustNum",value="投标券面总额汇总",example="字符串1")
    private BigDecimal entrustNum;

    @ApiModelProperty(name="etrBidDate",value="投标日期",example="2021-01-01")
    private String etrBidDate;

    @ApiModelProperty(name="bidPriceList",value="投标标位",example="List<EtrEntrustBidPrice>")
    private List<EtrEntrustBidPrice> bidPriceList;
    //债券一级投标 end

    //债券交易 start
    @ApiModelProperty(name="settleSpeed",value="清算速度CODE",example="T+0")
    private String settleSpeed;

    @ApiModelProperty(name="otherSettleSpeed",value="清算速度",example="字符串1")
    private String otherSettleSpeed;

    @ApiModelProperty(name="priceModel",value="价格模式Code",example="01")
    private String priceModel;

    @ApiModelProperty(name="otherPriceModel",value="价格模式",example="A1")
    private String otherPriceModel;

    @ApiModelProperty(name="secAcctId",value="证券账户",example="XXX证券账户")
    private String secAcctId;

    @ApiModelProperty(name="shareNum",value="券面总额",example="1000000")
    private BigDecimal shareNum;

    @ApiModelProperty(name="entrustOwnNum",value="本方券券面总额",example="1000000")
    private BigDecimal entrustOwnNum;

    @ApiModelProperty(name="integrateSecAmount",value="融入券券面总额",example="1000000")
    private BigDecimal integrateSecAmount;

    @ApiModelProperty(name="unitPrice",value="净价",example="100")
    private BigDecimal unitPrice;

    @ApiModelProperty(name="allPrice",value="全价",example="100")
    private BigDecimal allPrice;

    @ApiModelProperty(name="matureYield",value="到期收益率",example="1.11")
    private BigDecimal matureYield;

    @ApiModelProperty(name="exerYield",value="行权收益率",example="1.11")
    private BigDecimal exerYield;

    @ApiModelProperty(name="fpriceAmt",value="全价总额",example="1000000")
    private BigDecimal fpriceAmt;

    @ApiModelProperty(name="remark",value="备注",example="A1")
    private String remark;
    //债券交易 end

    @ApiModelProperty(name="pledgeBondList",value="质押券信息",example="pledgeBondList")
    private List<PledgeBondInfoDto> pledgeBondList;

    //回购 start

    @ApiModelProperty(name="bondCode",value="债券代码")
    private String bondCode;

    @ApiModelProperty(name="bondAbbrName",value="债券简称")
    private String bondAbbrName;

    @ApiModelProperty(name="avaPledgePrin",value="可质押券面总额")
    private BigDecimal avaPledgePrin;

    @ApiModelProperty(name="underlyingQty",value="质押券券面总额(万元)")
    private BigDecimal underlyingQty;

    @ApiModelProperty(name="repRegistOrg",value="登记托管机构")
    private String repRegistOrg;

    @ApiModelProperty(name="pledgeRatio",value="折算比例%")
    private BigDecimal pledgeRatio;


    @ApiModelProperty(name="repoTerm",value="回购期限",example="A1")
    private String repoTerm;

    @ApiModelProperty(name="rate",value="回购利率",example="20.1%")
    private String rate;

    @ApiModelProperty(name="pledgeStockName",value="质押券库",example="A1")
    private String pledgeStockName;

    @ApiModelProperty(name="firstprice",value="首次净价",example="A1")
    private String firstprice;

    @ApiModelProperty(name="totalPrinAmt",value="汇总券面总额",example="10000")
    private BigDecimal totalPrinAmt;

    @ApiModelProperty(name="repoPledgeModel",value="银行间质押式回购质押券模式",example="01无质押券库，02单个质押库，03多个质押券库")
    private String repoPledgeModel;
    //回购 end

    //利率互换 start
    @ApiModelProperty(name="busiPs",value="互换方式",example="收取固定/收取浮动")
    private String busiPs;

    @ApiModelProperty(name="entrustAmt",value="名义本金",example="1000")
    private BigDecimal entrustAmt;

    @ApiModelProperty(name="dealMode",value="挂钩利率",example="个人定期(整存整取)一年")
    private String dealMode;

    @ApiModelProperty(name="termType",value="期限",example="1Y/1M")
    private String termType;

    @ApiModelProperty(name="dueDate",value="到期日",example="[2017-01-01 - 2017-02-01]")
    private String dueDate;
    //利率互换 end

    @ApiModelProperty(name="isRight",value="是否含权",example="A1")
    private String isRight;


    public String getBusiPs() {
        return busiPs;
    }

    public void setBusiPs(String busiPs) {
        this.busiPs = busiPs;
    }

    public BigDecimal getEntrustAmt() {
        return entrustAmt;
    }

    public void setEntrustAmt(BigDecimal entrustAmt) {
        this.entrustAmt = entrustAmt;
    }

    public String getDealMode() {
        return dealMode;
    }

    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getMainConsignee() {
        return mainConsignee;
    }

    public void setMainConsignee(String mainConsignee) {
        this.mainConsignee = mainConsignee;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getListingDate() {
        return listingDate;
    }

    public void setListingDate(Date listingDate) {
        this.listingDate = listingDate;
    }

    public BigDecimal getEntrustNum() {
        return entrustNum;
    }

    public void setEntrustNum(BigDecimal entrustNum) {
        this.entrustNum = entrustNum;
    }

    public List<EtrEntrustBidPrice> getBidPriceList() {
        return bidPriceList;
    }

    public void setBidPriceList(List<EtrEntrustBidPrice> bidPriceList) {
        this.bidPriceList = bidPriceList;
    }

    public String getSettleSpeed() {
        return settleSpeed;
    }

    public void setSettleSpeed(String settleSpeed) {
        this.settleSpeed = settleSpeed;
    }

    public String getPriceModel() {
        return priceModel;
    }

    public void setPriceModel(String priceModel) {
        this.priceModel = priceModel;
    }

    public String getSecAcctId() {
        return secAcctId;
    }

    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    public BigDecimal getShareNum() {
        return shareNum;
    }

    public void setShareNum(BigDecimal shareNum) {
        this.shareNum = shareNum;
    }

    public BigDecimal getEntrustOwnNum() {
        return entrustOwnNum;
    }

    public void setEntrustOwnNum(BigDecimal entrustOwnNum) {
        this.entrustOwnNum = entrustOwnNum;
    }

    public BigDecimal getIntegrateSecAmount() {
        return integrateSecAmount;
    }

    public void setIntegrateSecAmount(BigDecimal integrateSecAmount) {
        this.integrateSecAmount = integrateSecAmount;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(BigDecimal allPrice) {
        this.allPrice = allPrice;
    }

    public BigDecimal getMatureYield() {
        return matureYield;
    }

    public void setMatureYield(BigDecimal matureYield) {
        this.matureYield = matureYield;
    }

    public BigDecimal getExerYield() {
        return exerYield;
    }

    public void setExerYield(BigDecimal exerYield) {
        this.exerYield = exerYield;
    }

    public BigDecimal getFpriceAmt() {
        return fpriceAmt;
    }

    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRepoTerm() {
        return repoTerm;
    }

    public void setRepoTerm(String repoTerm) {
        this.repoTerm = repoTerm;
    }

    public String getFirstprice() {
        return firstprice;
    }

    public void setFirstprice(String firstprice) {
        this.firstprice = firstprice;
    }

    public BigDecimal getTotalPrinAmt() {
        return totalPrinAmt;
    }

    public void setTotalPrinAmt(BigDecimal totalPrinAmt) {
        this.totalPrinAmt = totalPrinAmt;
    }

    public List<PledgeBondInfoDto> getPledgeBondList() {
        return pledgeBondList;
    }

    public void setPledgeBondList(List<PledgeBondInfoDto> pledgeBondList) {
        this.pledgeBondList = pledgeBondList;
    }

    public String getPledgeStockName() {
        return pledgeStockName;
    }

    public void setPledgeStockName(String pledgeStockName) {
        this.pledgeStockName = pledgeStockName;
    }

    public String getRepoPledgeModel() {
        return repoPledgeModel;
    }

    public void setRepoPledgeModel(String repoPledgeModel) {
        this.repoPledgeModel = repoPledgeModel;
    }

    public String getOtherBusiModel() {
        return SysDataUtil.getNameFromDataDict(DictCode.业务模式, super.getBusiModel());
    }

    /**
     * 获取 otherTradeMarket
     * @return otherTradeMarket.
     */
    public String getOtherTradeMarket() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易场所, super.getTradeMarket());
    }

    public String getOtherCcy() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
    }

    public String getOtherPs() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
    }

    public String getEtrBidDate() {
        return etrBidDate;
    }

    public void setEtrBidDate(String etrBidDate) {
        this.etrBidDate = etrBidDate;
    }

    public String getOtherPriceModel() {
        return SysDataUtil.getNameFromDataDict(DictCode.价格模式, this.getPriceModel());
    }

    public String getOtherSettleSpeed() {
        return SysDataUtil.getNameFromDataDict(DictCode.清算速度, this.getSettleSpeed());
    }

    public String getIsRight() {
        return isRight;
    }

    public void setIsRight(String isRight) {
        this.isRight = isRight;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getBondCode() {
        return bondCode;
    }

    public void setBondCode(String bondCode) {
        this.bondCode = bondCode;
    }

    public String getBondAbbrName() {
        return bondAbbrName;
    }

    public void setBondAbbrName(String bondAbbrName) {
        this.bondAbbrName = bondAbbrName;
    }

    public BigDecimal getAvaPledgePrin() {
        return avaPledgePrin;
    }

    public void setAvaPledgePrin(BigDecimal avaPledgePrin) {
        this.avaPledgePrin = avaPledgePrin;
    }

    public String getRepRegistOrg() {
        return repRegistOrg;
    }

    public void setRepRegistOrg(String repRegistOrg) {
        this.repRegistOrg = repRegistOrg;
    }

    public BigDecimal getUnderlyingQty() {
        return underlyingQty;
    }

    public void setUnderlyingQty(BigDecimal underlyingQty) {
        this.underlyingQty = underlyingQty;
    }

    public BigDecimal getPledgeRatio() {
        return pledgeRatio;
    }

    public void setPledgeRatio(BigDecimal pledgeRatio) {
        this.pledgeRatio = pledgeRatio;
    }
}
