/*
 * 文件名：TrdCommandsCollection002Dto.java
 * 版权：Copyright 2018-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.1.2
 * 修改人：王贵奇
 * 修改时间：2019年12月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.1.2
 */

package com.joyintech.tams.invest.trade.investtrade.commandscollection.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.constant.DictCode;

/**
 *指令汇总委托详情dto
 * @author 王贵奇
 * @version 1.0 2019年12月31日
 * @see TrdCommandsCollection002Dto
 * @since 1.0
 */
public class TrdCommandsCollection002Dto extends EtrEntrust{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 到期收益率
     */
    private BigDecimal matureYield;

    /**
     * 清算速度
     */
    private String settleSpeed;

    /**
     * 已完成额度、数量、股数
     */
    private BigDecimal finishedAmt;

    /**
     * 未完成额度、数量、股数
     */
    private BigDecimal unfinishedAmt;

    /**
     * 投标标位
     */
    private BigDecimal bidPrice;

    /**
     * 证券账户id
     */
    private String secAcctId;

    /**
     * 证券账户名称
     */
    private String SecAcctName;

    /**
     * 申购模式
     */
    private String purchModle;

    /**
     * 申购类型
     */
    private String purchType;

    /**
     * 持有券面总额
     */
    private BigDecimal holdetrPriceTotal;

    /**
     * 可转换券面总额
     */
    private BigDecimal conetrPriceTotal;

    /**
     * 转股券面总额
     */
    private BigDecimal stocketrPriceTotal;

    /**
     * 首次净价
     */
    private BigDecimal firstPrice;

    /**
     * 首次净价2
     */
    private BigDecimal firstPrice2;

    /**
     * 附件信息
     */
    private List<UploadFile> files;

    /**
     * 行权收益率
     */
    private BigDecimal exerYield;

    /**
     * 交割日期
     */
    private Date settleDate;

    /**
     * 清算速度
     */
    private String settleSpreed;

    /**
     * 资产起息日
     */
    private Date finvdate;

    /**
     * 利息总额
     */
    private BigDecimal intAmt;

    /**
     * 单位利息
     */
    private BigDecimal unitInt;

    /**
     * 计算标志
     */
    private String calflag;

    /**
     * 单位全价
     */
    private BigDecimal unitFprice;

    /**
     * 全价总额
     */
    private BigDecimal fpriceAmt;

    /**
     * 最低成交金额
     */
    private BigDecimal lowestDealNum;

    public BigDecimal getLowestDealNum() {
        return lowestDealNum;
    }

    public void setLowestDealNum(BigDecimal lowestDealNum) {
        this.lowestDealNum = lowestDealNum;
    }

    /**
     * 获取 exerYield
     * @return exerYield
     */
    public BigDecimal getExerYield() {
        return exerYield;
    }

    /**
     * 设置 exerYield
     * @param exerYield
     */
    public void setExerYield(BigDecimal exerYield) {
        this.exerYield = exerYield;
    }

    /**
     * 获取 settleDate
     * @return settleDate
     */
    public Date getSettleDate() {
        return settleDate;
    }

    /**
     * 设置 settleDate
     * @param settleDate
     */
    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    /**
     * 获取 settleSpreed
     * @return settleSpreed
     */
    public String getSettleSpreed() {
        return settleSpreed;
    }

    /**
     * 设置 settleSpreed
     * @param settleSpreed
     */
    public void setSettleSpreed(String settleSpreed) {
        this.settleSpreed = settleSpreed;
    }

    /**
     * 获取 finvdate
     * @return finvdate
     */
    public Date getFinvdate() {
        return finvdate;
    }

    /**
     * 设置 finvdate
     * @param finvdate
     */
    public void setFinvdate(Date finvdate) {
        this.finvdate = finvdate;
    }

    /**
     * 获取 intAmt
     * @return intAmt
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }

    /**
     * 设置 intAmt
     * @param intAmt
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    /**
     * 获取 unitInt
     * @return unitInt
     */
    public BigDecimal getUnitInt() {
        return unitInt;
    }

    /**
     * 设置 unitInt
     * @param unitInt
     */
    public void setUnitInt(BigDecimal unitInt) {
        this.unitInt = unitInt;
    }

    /**
     * 获取 calflag
     * @return calflag
     */
    public String getCalflag() {
        return calflag;
    }

    /**
     * 设置 calflag
     * @param calflag
     */
    public void setCalflag(String calflag) {
        this.calflag = calflag;
    }

    /**
     * 获取 unitFprice
     * @return unitFprice
     */
    public BigDecimal getUnitFprice() {
        return unitFprice;
    }

    /**
     * 设置 unitFprice
     * @param unitFprice
     */
    public void setUnitFprice(BigDecimal unitFprice) {
        this.unitFprice = unitFprice;
    }

    /**
     * 获取 fpriceAmt
     * @return fpriceAmt
     */
    public BigDecimal getFpriceAmt() {
        return fpriceAmt;
    }

    /**
     * 设置 fpriceAmt
     * @param fpriceAmt
     */
    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }

    /**
     * 获取 matureYield
     * @return matureYield
     */
    public BigDecimal getMatureYield() {
        return matureYield;
    }

    /**
     * 设置 matureYield
     * @param matureYield matureYield
     */
    public void setMatureYield(BigDecimal matureYield) {
        this.matureYield = matureYield;
    }

    /**
     * 获取 settleSpeed
     * @return settleSpeed
     */
    public String getSettleSpeed() {
        return settleSpeed;
    }

    /**
     * 设置 settleSpeed
     * @param settleSpeed settleSpeed
     */
    public void setSettleSpeed(String settleSpeed) {
        this.settleSpeed = settleSpeed;
    }

    /**
     * 获取 finishedAmt
     * @return finishedAmt
     */
    public BigDecimal getFinishedAmt() {
        return finishedAmt;
    }

    /**
     * 设置 finishedAmt
     * @param finishedAmt finishedAmt
     */
    public void setFinishedAmt(BigDecimal finishedAmt) {
        this.finishedAmt = finishedAmt;
    }

    /**
     * 获取 unfinishedAmt
     * @return unfinishedAmt
     */
    public BigDecimal getUnfinishedAmt() {
        return unfinishedAmt;
    }

    /**
     * 设置 unfinishedAmt
     * @param unfinishedAmt unfinishedAmt
     */
    public void setUnfinishedAmt(BigDecimal unfinishedAmt) {
        this.unfinishedAmt = unfinishedAmt;
    }

    /**
     * 获取 bidPrice
     * @return bidPrice
     */
    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    /**
     * 设置 bidPrice
     * @param bidPrice bidPrice
     */
    public void setBidPrice(BigDecimal bidPrice) {
        this.bidPrice = bidPrice;
    }

    /**
     * 获取 secAcctId
     * @return secAcctId
     */
    public String getSecAcctId() {
        return secAcctId;
    }

    /**
     * 设置 secAcctId
     * @param secAcctId secAcctId
     */
    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    /**
     * 获取 secAcctName
     * @return secAcctName
     */
    public String getSecAcctName() {
        return SecAcctName;
    }

    /**
     * 设置 secAcctName
     * @param secAcctName secAcctName
     */
    public void setSecAcctName(String secAcctName) {
        SecAcctName = secAcctName;
    }

    /**
     * 获取 purchModle
     * @return purchModle
     */
    public String getPurchModle() {
        return purchModle;
    }

    /**
     * 设置 purchModle
     * @param purchModle purchModle
     */
    public void setPurchModle(String purchModle) {
        this.purchModle = purchModle;
    }

    /**
     * 获取 purchType
     * @return purchType
     */
    public String getPurchType() {
        return purchType;
    }

    /**
     * 设置 purchType
     * @param purchType purchType
     */
    public void setPurchType(String purchType) {
        this.purchType = purchType;
    }

    /**
     * 获取 holdetrPriceTotal
     * @return holdetrPriceTotal
     */
    public BigDecimal getHoldetrPriceTotal() {
        return holdetrPriceTotal;
    }

    /**
     * 设置 holdetrPriceTotal
     * @param holdetrPriceTotal holdetrPriceTotal
     */
    public void setHoldetrPriceTotal(BigDecimal holdetrPriceTotal) {
        this.holdetrPriceTotal = holdetrPriceTotal;
    }

    /**
     * 获取 conetrPriceTotal
     * @return conetrPriceTotal
     */
    public BigDecimal getConetrPriceTotal() {
        return conetrPriceTotal;
    }

    /**
     * 设置 conetrPriceTotal
     * @param conetrPriceTotal conetrPriceTotal
     */
    public void setConetrPriceTotal(BigDecimal conetrPriceTotal) {
        this.conetrPriceTotal = conetrPriceTotal;
    }

    /**
     * 获取 stocketrPriceTotal
     * @return stocketrPriceTotal
     */
    public BigDecimal getStocketrPriceTotal() {
        return stocketrPriceTotal;
    }

    /**
     * 设置 stocketrPriceTotal
     * @param stocketrPriceTotal stocketrPriceTotal
     */
    public void setStocketrPriceTotal(BigDecimal stocketrPriceTotal) {
        this.stocketrPriceTotal = stocketrPriceTotal;
    }

    /**
     * 获取 firstPrice
     * @return firstPrice
     */
    public BigDecimal getFirstPrice() {
        return firstPrice;
    }

    /**
     * 设置 firstPrice
     * @param firstPrice firstPrice
     */
    public void setFirstPrice(BigDecimal firstPrice) {
        this.firstPrice = firstPrice;
    }

    /**
     * 获取 firstPrice2
     * @return firstPrice2
     */
    public BigDecimal getFirstPrice2() {
        return firstPrice2;
    }

    /**
     * 设置 firstPrice2
     * @param firstPrice2 firstPrice2
     */
    public void setFirstPrice2(BigDecimal firstPrice2) {
        this.firstPrice2 = firstPrice2;
    }

    /**
     * 获取 files
     * @return files
     */
    public List<UploadFile> getFiles() {
        return files;
    }

    /**
     * 设置 files
     * @param files files
     */
    public void setFiles(List<UploadFile> files) {
        this.files = files;
    }

    /**
     * 获取 清算速度名称
     * @return settleSpeedStr 
     */
    public String getSettleSpeedStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.清算速度, getSettleSpeed());
    }

    /**
     * 获取 投资目的名称
     * @return invAimStr 
     */
    public String getInvAimStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.投资目的, super.getInvAim());
    }

    /**
     * 获取 价格模式名称
     * @return priceModelStr 
     */
    public String getPriceModelStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.价格模式, super.getPriceModel());
    }

    /**
     * 获取 委托开始日字符串
     * @return vdateStr 
     */
    public String getVdateStr() {
        return DateUtil.format(this.getVdate());
    }

    /**
     * 获取 委托到期日字符串
     * @return mdateStr 
     */
    public String getMdateStr() {
        return DateUtil.format(this.getMdate());
    }

    /**
     * 获取 申购方式名称
     * @return purchModleStr 
     */
    public String getPurchModleStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.申购方式, getPurchModle());
    }

    /**
     * 获取 申购类型名称
     * @return purchTypeStr 
     */
    public String getPurchTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.申购类型, getPurchType());
    }
    
}
