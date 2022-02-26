package com.joyintech.tams.cfets.etrdispatcher.dto;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.cfets.etrib.repo.dto.CfetsPledgeBondInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * 场外指令分发
 * 指令成交返回数据
 * @author sjj
 * @version 1.0 2021年11月4日
 * @see CfetsEtrDispatcherDealListInfoResponse
 * @since 1.0
 */
@ApiModel(value="指令成交返回数据",description="指令成交返回数据")
public class CfetsEtrDispatcherDealListInfoResponse extends CfetsEtrDispatcherBaseInfo{

    @ApiModelProperty(name="effectFlag",value="有效状态code",example="01")
    private String effectFlag;

    @ApiModelProperty(name="totleTradeId",value="汇总交易编号",example="A1")
    private String totleTradeId;

    @ApiModelProperty(name="tradeId",value="业务编号",example="A1")
    private String tradeId;

    @ApiModelProperty(name="secAcctId",value="证券账户code",example="XXX证券账户")
    private String secAcctId;

    @ApiModelProperty(name="secAcctIdStr",value="证券账户",example="XXX证券账户")
    private String secAcctIdStr;

    @ApiModelProperty(name="tradeDate",value="成交日期",example="2021-01-01")
    private String tradeDate;

    @ApiModelProperty(name="purCfmDate",value="确认日期",example="2021-01-01")
    private String purCfmDate;

    @ApiModelProperty(name="settleSpeed",value="清算速度code",example="T+0")
    private String settleSpeed;

    @ApiModelProperty(name="settleSpeedStr",value="清算速度",example="T+0")
    private String settleSpeedStr;

    @ApiModelProperty(name="settleDate",value="结算日期",example="2021-01-01")
    private String settleDate;

    @ApiModelProperty(name="unitPrice",value="净价",example="100")
    private BigDecimal unitPrice;

    @ApiModelProperty(name="matureYield",value="到期收益率",example="1.11")
    private BigDecimal matureYield;

    @ApiModelProperty(name="exerYield",value="行权收益率",example="1.11")
    private BigDecimal exerYield;

    @ApiModelProperty(name="shareNum",value="券面总额",example="1000000")
    private BigDecimal shareNum;

    @ApiModelProperty(name="unitInt",value="应计利息（元）",example="100")
    private BigDecimal unitInt;

    @ApiModelProperty(name="allPrice",value="全价",example="100")
    private BigDecimal allPrice;

    @ApiModelProperty(name="cpriceAmt",value="净价总额",example="100")
    private BigDecimal cpriceAmt;

    @ApiModelProperty(name="intAmt",value="应计利息总额（元）",example="100")
    private BigDecimal intAmt;

    @ApiModelProperty(name="fpriceAmt",value="全价总额",example="1000000")
    private BigDecimal fpriceAmt;

    @ApiModelProperty(name="jyFeeAmt",value="交易手续费",example="1000000")
    private BigDecimal jyFeeAmt;

    @ApiModelProperty(name="jsFeeAmt",value="结算手续费",example="1000000")
    private BigDecimal jsFeeAmt;

    @ApiModelProperty(name="counterName",value="交易对手",example="A1")
    private String counterName;

    @ApiModelProperty(name="remark",value="备注",example="A1")
    private String remark;

    @ApiModelProperty(name="termType",value="期限",example="1Y/1M")
    private String termType;

    @ApiModelProperty(name="repoTerm",value="回购期限(天)",example="5")
    private String repoTerm;

    @ApiModelProperty(name="vpayDate",value="首次结算日",example="2021-01-01")
    private String vpayDate;

    @ApiModelProperty(name="mpayDate",value="到期结算日",example="2021-01-01")
    private String mpayDate;

    @ApiModelProperty(name="actCapDays",value="实际占款天数（天）",example="5")
    private Long actCapDays;

    @ApiModelProperty(name="rebackPrice",value="回购利率%",example="3")
    private BigDecimal rebackPrice;

    @ApiModelProperty(name="tradeAmt",value="交易金额（元）",example="10000")
    private BigDecimal tradeAmt;

    @ApiModelProperty(name="shareAmt",value="交易份额",example="10000")
    private BigDecimal shareAmt;

    @ApiModelProperty(name="mIntAmt",value="回购利息",example="1.1")
    private BigDecimal mIntAmt;

    @ApiModelProperty(name="vTradeAmt",value="首次结算金额",example="10001")
    private BigDecimal vTradeAmt;

    @ApiModelProperty(name="mTradeAmt",value="到期结算金额",example="10001")
    private BigDecimal mTradeAmt;

    @ApiModelProperty(name="fCprice",value="首次净价",example="99")
    private BigDecimal fCprice;

    @ApiModelProperty(name="fYield",value="首次收益率%",example="1.1")
    private BigDecimal fYield;

    @ApiModelProperty(name="mCprice",value="到期净价",example="99")
    private BigDecimal mCprice;

    @ApiModelProperty(name="mYield",value="到期收益率%",example="1.1")
    private BigDecimal mYield;

    @ApiModelProperty(name="secTotalPrin",value="券面总额（万元）",example="100")
    private BigDecimal secTotalPrin;

    @ApiModelProperty(name="pledgeStockName",value="质押券信息",example="100")
    private String pledgeStockName;

    @ApiModelProperty(name="detailList",value="质押券明细",example="100")
    private List<CfetsPledgeBondInfoDto> detailList;

    @ApiModelProperty(name="chlId",value="通道代码",example="字符串1")
    private String chlId;

    @ApiModelProperty(name="chlIdName",value="通道名称",example="字符串1")
    private String chlIdName;

    @ApiModelProperty(name="orgName",value="机构名称",example="字符串1")
    private String orgName;

    @ApiModelProperty(name="pledgeFinprodId",value="标的资产代码",example="字符串1")
    private String pledgeFinprodId;

    @ApiModelProperty(name="pledgeFinprodName",value="标的资产名称",example="字符串1")
    private String pledgeFinprodName;

    @ApiModelProperty(name="tradeMarket",value="交易场所",example="字符串1")
    private String tradeMarket;

    @ApiModelProperty(name="paBussType",value="拆借类型",example="字符串1")
    private String paBussType;

    @ApiModelProperty(name = "assetType1", value = "资产二级分类", example = "字符串1")
    private String assetType1;

    @ApiModelProperty(name = "assetType10", value = "资产三级分类", example = "字符串1")
    private String assetType10;

    @ApiModelProperty(name = "assetType11", value = "资产四级分类", example = "字符串1")
    private String assetType11;

    @ApiModelProperty(name = "finprodType2", value = "资产类型", example = "字符串1")
    private String finprodType2;

    @ApiModelProperty(name="prinAmt",value="本金金额",example="10000")
    private BigDecimal prinAmt;

    @ApiModelProperty(name="actPayAmt",value="实付金额",example="10000")
    private BigDecimal actPayAmt;

    @ApiModelProperty(name = "recommender", value = "资产推荐方", example = "字符串1")
    private String recommender;

    @ApiModelProperty(name = "recommenderName", value = "资产推荐方名称", example = "字符串1")
    private String recommenderName;

    public String getEffectFlag() {
        return effectFlag;
    }

    public void setEffectFlag(String effectFlag) {
        this.effectFlag = effectFlag;
    }

    public String getTotleTradeId() {
        return totleTradeId;
    }

    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getSecAcctId() {
        return secAcctId;
    }

    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    public String getSecAcctIdStr() {
        return secAcctIdStr;
    }

    public void setSecAcctIdStr(String secAcctIdStr) {
        this.secAcctIdStr = secAcctIdStr;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getSettleSpeed() {
        return settleSpeed;
    }

    public void setSettleSpeed(String settleSpeed) {
        this.settleSpeed = settleSpeed;
    }

    public String getSettleSpeedStr() {
        return settleSpeedStr;
    }

    public void setSettleSpeedStr(String settleSpeedStr) {
        this.settleSpeedStr = settleSpeedStr;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
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

    public BigDecimal getShareNum() {
        return shareNum;
    }

    public void setShareNum(BigDecimal shareNum) {
        this.shareNum = shareNum;
    }

    public BigDecimal getUnitInt() {
        return unitInt;
    }

    public void setUnitInt(BigDecimal unitInt) {
        this.unitInt = unitInt;
    }

    public BigDecimal getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(BigDecimal allPrice) {
        this.allPrice = allPrice;
    }

    public BigDecimal getCpriceAmt() {
        return cpriceAmt;
    }

    public void setCpriceAmt(BigDecimal cpriceAmt) {
        this.cpriceAmt = cpriceAmt;
    }

    public BigDecimal getIntAmt() {
        return intAmt;
    }

    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    public BigDecimal getFpriceAmt() {
        return fpriceAmt;
    }

    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }

    public BigDecimal getJyFeeAmt() {
        return jyFeeAmt;
    }

    public void setJyFeeAmt(BigDecimal jyFeeAmt) {
        this.jyFeeAmt = jyFeeAmt;
    }

    public BigDecimal getJsFeeAmt() {
        return jsFeeAmt;
    }

    public void setJsFeeAmt(BigDecimal jsFeeAmt) {
        this.jsFeeAmt = jsFeeAmt;
    }

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getVpayDate() {
        return vpayDate;
    }

    public void setVpayDate(String vpayDate) {
        this.vpayDate = vpayDate;
    }

    public String getMpayDate() {
        return mpayDate;
    }

    public void setMpayDate(String mpayDate) {
        this.mpayDate = mpayDate;
    }

    public Long getActCapDays() {
        return actCapDays;
    }

    public void setActCapDays(Long actCapDays) {
        this.actCapDays = actCapDays;
    }

    public BigDecimal getRebackPrice() {
        return rebackPrice;
    }

    public void setRebackPrice(BigDecimal rebackPrice) {
        this.rebackPrice = rebackPrice;
    }

    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public BigDecimal getmIntAmt() {
        return mIntAmt;
    }

    public void setmIntAmt(BigDecimal mIntAmt) {
        this.mIntAmt = mIntAmt;
    }

    public BigDecimal getmTradeAmt() {
        return mTradeAmt;
    }

    public void setmTradeAmt(BigDecimal mTradeAmt) {
        this.mTradeAmt = mTradeAmt;
    }

    public BigDecimal getmCprice() {
        return mCprice;
    }

    public void setmCprice(BigDecimal mCprice) {
        this.mCprice = mCprice;
    }

    public BigDecimal getmYield() {
        return mYield;
    }

    public void setmYield(BigDecimal mYield) {
        this.mYield = mYield;
    }

    public BigDecimal getSecTotalPrin() {
        return secTotalPrin;
    }

    public void setSecTotalPrin(BigDecimal secTotalPrin) {
        this.secTotalPrin = secTotalPrin;
    }

    public String getOtherEffectFlag() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易流程状态, this.getEffectFlag());
    }

    public String getOtherCcy() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
    }

    public String getOtherPs() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
    }

    public BigDecimal getShareNumWanYuan() {
        return ArithmeticUtil.divide(this.shareNum, new BigDecimal(10000), 6, RoundingMode.HALF_UP);
    }

    public String getOtherSettleSpeed() {
        return SysDataUtil.getNameFromDataDict(DictCode.清算速度, this.getSettleSpeed());
    }

    public List<CfetsPledgeBondInfoDto> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<CfetsPledgeBondInfoDto> detailList) {
        this.detailList = detailList;
    }

    public String getPledgeStockName() {
        return pledgeStockName;
    }

    public void setPledgeStockName(String pledgeStockName) {
        this.pledgeStockName = pledgeStockName;
    }

    public BigDecimal getvTradeAmt() {
        return vTradeAmt;
    }

    public void setvTradeAmt(BigDecimal vTradeAmt) {
        this.vTradeAmt = vTradeAmt;
    }

    public BigDecimal getfCprice() {
        return fCprice;
    }

    public void setfCprice(BigDecimal fCprice) {
        this.fCprice = fCprice;
    }

    public BigDecimal getfYield() {
        return fYield;
    }

    public void setfYield(BigDecimal fYield) {
        this.fYield = fYield;
    }

    public String getChlId() {
        return chlId;
    }

    public void setChlId(String chlId) {
        this.chlId = chlId;
    }

    public String getChlIdName() {
        return chlIdName;
    }

    public void setChlIdName(String chlIdName) {
        this.chlIdName = chlIdName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPledgeFinprodId() {
        return pledgeFinprodId;
    }

    public void setPledgeFinprodId(String pledgeFinprodId) {
        this.pledgeFinprodId = pledgeFinprodId;
    }

    public String getPledgeFinprodName() {
        return pledgeFinprodName;
    }

    public void setPledgeFinprodName(String pledgeFinprodName) {
        this.pledgeFinprodName = pledgeFinprodName;
    }

    public String getPaBussType() {
        return SysDataUtil.getNameFromDataDict(DictCode.拆借类型, paBussType);
    }

    public void setPaBussType(String paBussType) {
        this.paBussType = paBussType;
    }

    public String getAssetType1() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产二级分类, assetType1);
    }

    public void setAssetType1(String assetType1) {
        this.assetType1 = assetType1;
    }

    public String getAssetType10() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产三级分类, assetType10);
    }

    public void setAssetType10(String assetType10) {
        this.assetType10 = assetType10;
    }

    public String getAssetType11() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产四级分类, assetType11);
    }

    public void setAssetType11(String assetType11) {
        this.assetType11 = assetType11;
    }

    @Override
    public String getTradeMarket() {
        return tradeMarket;
    }

    @Override
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    /**
     * 获取 otherTradeMarket
     * @return otherTradeMarket.
     */
    public String getOtherTradeMarket() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易场所, tradeMarket);
    }

    public String getPurCfmDate() {
        return purCfmDate;
    }

    public void setPurCfmDate(String purCfmDate) {
        this.purCfmDate = purCfmDate;
    }

    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    public String getFinprodType2() {
        return finprodType2;
    }

    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }

    /**
     * 获取资产类型 otherFinprodType2
     *
     * @return otherFinprodType2 资产类型对应name
     */
    public String getOtherFinprodType2() {
        if (StringUtil.equals(finprodType2, BussinessDictCodeList.金融产品类型_投管用_一级投标债券.getValue())) {
            return SysDataUtil.getNameFromDataDict(DictCode.平安资产类型, BussinessDictCodeList.平安资产类型_一级投标债券.getValue());
        }
        return SysDataUtil.getNameFromDataDict(DictCode.平安资产类型, finprodType2);
    }

    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    public BigDecimal getActPayAmt() {
        return actPayAmt;
    }

    public void setActPayAmt(BigDecimal actPayAmt) {
        this.actPayAmt = actPayAmt;
    }

    public String getRecommender() {
        return recommender;
    }

    public void setRecommender(String recommender) {
        this.recommender = recommender;
    }

    public String getRecommenderName() {
        return recommenderName;
    }

    public void setRecommenderName(String recommenderName) {
        this.recommenderName = recommenderName;
    }

}
