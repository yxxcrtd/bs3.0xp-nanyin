/*
 * 文件名：InterfOutEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.prodtrade.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.tams.common.base.entity.InterfProdInfo;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 接口输出共通类
 * 
 * @author yjm
 * @version 1.0 2018年11月7日
 * @see InterfOutEntity
 * @since 1.0
 */
public class InterfOutEntity extends InterfProdInfo {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 7640857644242562970L;

    /**
     * 分层信息
     */
    private List<InterfOutLvlEntity> lvlList;

    /**
     * 分行信息
     */
    private List<InterfOutBrhEntity> branchList;

    /**
     * 分层信息循环次数
     */
    private int rtlRefAddRateNum;

    /**
     * 分行信息循环次数
     */
    private int rtlBranchNum;

    @EsbName(value = "ShortName")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setProdid(String prodid) {
        super.setProdid(prodid);
    }

    @EsbName(value = "StartDate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setStartdate(String startdate) {
        super.setStartdate(startdate);

    }

    @EsbName(value = "EndDate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setEnddate(String enddate) {
        super.setEnddate(enddate);
    }

    @EsbName(value = "PreInAmount")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setPreinamount(BigDecimal preinamount) {
        super.setPreinamount(preinamount);
    }

    @EsbName(value = "TotalBuyAmount")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setTotalbuyamount(BigDecimal totalbuyamount) {
        super.setTotalbuyamount(totalbuyamount);
    }

    @EsbName(value = "TotalDrawAmount")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setTotaldrawamount(BigDecimal totaldrawamount) {
        super.setTotaldrawamount(totaldrawamount);
    }

    /**
     * 449108接口.上日赎回市值金额
     * 
     * @param totaldrawamount 上日赎回市值金额
     * @see
     * @since 1.0
     */
    @EsbName(value = "TotalDrawValAmount")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setTotaldrawValAmount(BigDecimal totaldrawamount) {
        super.setTotaldrawamount(totaldrawamount);
    }

    @EsbName(value = "TotalCurrentAmount")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setTotalcurrentamount(BigDecimal totalcurrentamount) {
        super.setTotalcurrentamount(totalcurrentamount);
    }

    /**
     * 449194接口.本期次总金额
     * 
     * @param totalcurrentamount 本期次总金额
     * @see
     * @since 1.0
     */
    @EsbName(value = "TotalAccountAmount")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setTotalAccountAmount(BigDecimal totalcurrentamount) {
        super.setTotalcurrentamount(totalcurrentamount);
    }

    /**
     * 449189接口.总成本本金
     * 
     * @param totalcurrentamount 总成本本金
     * @see
     * @since 1.0
     */
    @EsbName(value = "TotalSourceAmount")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setTotalSourceAmount(BigDecimal totalcurrentamount) {
        super.setTotalcurrentamount(totalcurrentamount);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "ProductName")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setProdname(String prodname) {
        super.setProdname(prodname);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "CurrencyId")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setCcy(String ccy) {
        super.setCcy(ccy);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "ActEndDate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setActenddate(String actenddate) {
        super.setActenddate(actenddate);
    }

    /**
     * 449195接口.实际结束日期
     * 
     * @param actenddate 实际结束日期
     * @see
     * @since 1.0
     */
    @EsbName(value = "RealEndDate")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setRealEndDate(String actenddate) {
        super.setActenddate(actenddate);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "NetValue")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setNetvalue(BigDecimal netvalue) {
        super.setNetvalue(netvalue);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "TotalDrawCustAmount")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setTotaldrawcustamount(BigDecimal totaldrawcustamount) {
        super.setTotaldrawcustamount(totaldrawcustamount);
    }

    /**
     * 448189接口.总金额
     * 
     * @param totaldrawcustamount 总金额
     * @see
     * @since 1.0
     */
    @EsbName(value = "TotalAmount")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setTotalAmount(BigDecimal totaldrawcustamount) {
        super.setTotaldrawamount(totaldrawcustamount);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "TotalDrawFeesAmount")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setTotalfeesamount(BigDecimal totalfeesamount) {
        super.setTotalfeesamount(totalfeesamount);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "TotalCarriedInterest")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setTotalcarriedinterest(BigDecimal totalcarriedinterest) {
        super.setTotalcarriedinterest(totalcarriedinterest);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "totalNotCarriedInterest")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setTotalnotcarriedinterest(BigDecimal totalnotcarriedinterest) {
        super.setTotalnotcarriedinterest(totalnotcarriedinterest);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "ReserveBuyAmt")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setReservebuyamt(BigDecimal reservebuyamt) {
        super.setReservebuyamt(reservebuyamt);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "ReserveDrawVol")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setReservedrawvol(BigDecimal reservedrawvol) {
        super.setReservedrawvol(reservedrawvol);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "TotalCarriedVol")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setTotalcarriedvol(BigDecimal totalcarriedvol) {
        super.setTotalcarriedvol(totalcarriedvol);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "totalPayableInterest")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setTotalpayableinterest(BigDecimal totalpayableinterest) {
        super.setTotalpayableinterest(totalpayableinterest);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "totalNewInterest")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setTotalnewinterest(BigDecimal totalnewinterest) {
        super.setTotalnewinterest(totalnewinterest);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "totalCloseInterest")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setTotalcloseinterest(BigDecimal totalcloseinterest) {
        super.setTotalcloseinterest(totalcloseinterest);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "totalCarryInterest")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setTotalcarryinterest(BigDecimal totalcarryinterest) {
        super.setTotalcarryinterest(totalcarryinterest);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "TotalCurrentQuantity")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setTotalcurrentquantity(BigDecimal totalcurrentquantity) {
        super.setTotalcurrentquantity(totalcurrentquantity);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "TotalBuyQuantity")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setTotalbuyquantity(BigDecimal totalbuyquantity) {
        super.setTotalbuyquantity(totalbuyquantity);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "TotalDrawQuantity")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setTotaldrawquantity(BigDecimal totaldrawquantity) {
        super.setTotaldrawquantity(totaldrawquantity);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "TotalCount")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setTotalcount(Integer totalcount) {
        super.setTotalcount(totalcount);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "TotalProfitAmount")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setTotalprofitamount(BigDecimal totalprofitamount) {
        super.setTotalprofitamount(totalprofitamount);
    }

    /**
     * 设置分层信息
     * 
     * @param lvlList 分层信息
     * @see
     * @since 1.0
     */
    @EsbName(value = "RtlRefAddRate")
    @EsbFieldType(value = EsbFieldType.ARRAY)
    public void setLvlList(List<InterfOutLvlEntity> lvlList) {
        this.lvlList = lvlList;
    }

    /**
     * 设置 branchList
     * 
     * @param branchList branchList
     */
    @EsbName(value = "RtlBranch")
    @EsbFieldType(value = EsbFieldType.ARRAY)
    public void setBranchList(List<InterfOutBrhEntity> branchList) {
        this.branchList = branchList;
    }

    /**
     * 设置 rtlRefAddRateNum
     * 
     * @param rtlRefAddRateNum rtlRefAddRateNum
     */
    @EsbName(value = "RtlRefAddRateNum")
    @EsbFieldType(value = EsbFieldType.INT)
    public void setRtlRefAddRateNum(int rtlRefAddRateNum) {
        this.rtlRefAddRateNum = rtlRefAddRateNum;
    }

    /**
     * 设置 rtlBranchNum
     * 
     * @param rtlBranchNum rtlBranchNum
     */
    @EsbName(value = "RtlBranchNum")
    @EsbFieldType(value = EsbFieldType.INT)
    public void setRtlBranchNum(int rtlBranchNum) {
        this.rtlBranchNum = rtlBranchNum;
    }

    /**
     * 设置 中文简称
     * 
     * @param chnShortName 中文简称
     */
    @EsbName(value = "CHN_SHORT_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setChnShortName(String chnShortName) {
        super.setProdid(chnShortName);
    }

    /**
     * 设置 产品名称
     * 
     * @param productName 产品名称
     */
    @EsbName(value = "PRODUCT_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setProductName(String productName) {
        super.setProdname(productName);
    }

    /**
     * 设置 币种
     * 
     * @param ccy 币种
     */
    @EsbName(value = "CCY")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setCcy2(String ccy) {
        super.setCcy(ccy);
    }

    /**
     * 设置 单位净值
     * 
     * @param unitNetValue 单位净值
     */
    @EsbName(value = "UNIT_NET_VALUE")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setUnitNetValue(BigDecimal unitNetValue) {
        super.setNetvalue(unitNetValue);
    }

    /**
     * 设置 总本金
     * 
     * @param totalCorpus 总本金
     */
    @EsbName(value = "TOTAL_CORPUS")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setTotalCorpus(BigDecimal totalCorpus) {
        super.setTotalcurrentamount(totalCorpus);
    }

    /**
     * 设置 总募集
     * 
     * @param totalCollect 总募集
     */
    @EsbName(value = "TOTAL_COLLECT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setTotalCollect(BigDecimal totalCollect) {
        super.setTotalbuyamount(totalCollect);
    }

    /**
     * 设置 总赎回
     * 
     * @param totalRedeem 总赎回
     */
    @EsbName(value = "TOTAL_REDEEM")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setTotalRedeem(BigDecimal totalRedeem) {
        super.setTotaldrawamount(totalRedeem);
    }

    /**
     * 设置 总已结转收益
     * 
     * @param totalComvertIncome 总已结转收益
     */
    @EsbName(value = "TOTAL_COMVERT_INCOME")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setTotalComvertIncome(BigDecimal totalComvertIncome) {
        super.setTotalcarriedinterest(totalComvertIncome);
    }

    /**
     * 设置 总待结转收益
     * 
     * @param totalWaitComvertIncome 总待结转收益
     */
    @EsbName(value = "TOTAL_WAIT_COMVERT_INCOME")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setTotalWaitComvertIncome(BigDecimal totalWaitComvertIncome) {
        super.setTotalnotcarriedinterest(totalWaitComvertIncome);
    }

    /**
     * 设置 预约买入金额
     * 
     * @param bespeakBuyAmt 预约买入金额
     */
    @EsbName(value = "BESPEAK_BUY_AMT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setBespeakBuyAmt(BigDecimal bespeakBuyAmt) {
        super.setReservebuyamt(bespeakBuyAmt);
    }

    /**
     * 设置 预约赎回份额
     * 
     * @param bespeakRedeemVol 预约赎回份额
     */
    @EsbName(value = "BESPEAK_REDEEM_VOL")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setBespeakRedeemVol(BigDecimal bespeakRedeemVol) {
        super.setReservedrawvol(bespeakRedeemVol);
    }

    /**
     * 设置 月红利再投总份额
     * 
     * @param monDivAgInvTotalLot 月红利再投总份额
     */
    @EsbName(value = "MON_DIVIDEND_AGAIN_INVEST_TOTAL_LOT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setMonDivAgInvTotalLot(BigDecimal monDivAgInvTotalLot) {
        this.setTotalcarriedvol(monDivAgInvTotalLot);
    }

    /**
     * 设置 收益本金数组
     * 
     * @param lvlList 收益本金数组
     */
    @EsbName(value = "INCOME_CORPUS_CYCLE_ARRAY")
    @EsbFieldType(value = EsbFieldType.ARRAY)
    public void setLvlList2(List<InterfOutLvlEntity> lvlList) {
        this.setLvlList(lvlList);
    }

    /**
     * 设置 分行统计数组
     * 
     * @param branchList 分行统计数组
     */
    @EsbName(value = "BRANCH_STAT_ARRAY")
    @EsbFieldType(value = EsbFieldType.ARRAY)
    public void setBranchList2(List<InterfOutBrhEntity> branchList) {
        this.setBranchList(branchList);
    }

    /**
     * 获取 rtlRefAddRateNum
     * 
     * @return rtlRefAddRateNum.
     */
    public int getRtlRefAddRateNum() {
        return rtlRefAddRateNum;
    }

    /**
     * 获取 rtlBranchNum
     * 
     * @return rtlBranchNum.
     */
    public int getRtlBranchNum() {
        return rtlBranchNum;
    }

    /**
     * 取得分层信息
     * 
     * @return 分层信息
     * @see
     * @since 1.0
     */
    public List<InterfOutLvlEntity> getLvlList() {
        return lvlList;
    }

    /**
     * 取得分行信息
     * 
     * @return 分行信息
     * @see
     * @since 1.0
     */
    public List<InterfOutBrhEntity> getBranchList() {
        return branchList;
    }
}
