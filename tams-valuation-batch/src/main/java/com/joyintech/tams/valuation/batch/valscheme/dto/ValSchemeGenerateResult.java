/*
 * 文件名：ValBokBalanceGenerateResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V1.0
 */

package com.joyintech.tams.valuation.batch.valscheme.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.common.base.entity.BokValbalTableLog;
import com.joyintech.tams.common.base.entity.BokValbalTableStatus;
import com.joyintech.tams.valuation.batch.valbokbalance.dto.ValBokBalanceGenerateResult;

/**
 * 估值表生成结果
 * 
 * @author ruanhongming
 * @version 1.0 2018年5月29日
 * @see ValSchemeGenerateResult
 * @since 1.0
 */
public class ValSchemeGenerateResult extends ValBokBalanceGenerateResult {

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 账套名称
     */
    private String booksetName;

    /**
     * 账套全称
     */
    private String booksetFullName;

    /**
     * 科目明细
     */
    private List<ProdValuationDto> prodValuationDtoList;

    /**
     * 实收资本
     */
    private ProdValuationDto actRecAmount;

    /**
     * 损益平准金未实现
     */
    private ProdValuationDto profLossUnrealAmount;

    /**
     * 损益平准金已实现
     */
    private ProdValuationDto profLossRealAmount;

    /**
     * 资产类合计
     */
    private ProdValuationDto assetTotal;

    /**
     * 负债类合计
     */
    private ProdValuationDto debtTotal;

    /**
     * 产品资产净值
     */
    private ProdValuationDto prodAssetVal;

    /**
     * 产品单位净值
     */
    private ProdValuationDto prodUnitVal;

    /**
     * 产品名称
     */
    private String finprodName;

    /**
     * 证券投资合计
     */
    private ProdValuationDto securityInvTotal;

    /**
     * 其中股票投资
     */
    private ProdValuationDto shareInvInside;

    /**
     * 其中债券投资
     */
    private ProdValuationDto bondInvInside;

    /**
     * 其中基金投资
     */
    private ProdValuationDto fundInvInside;

    /**
     * 其中权证投资
     */
    private ProdValuationDto warrantInvInside;

    /**
     * 融资业务合计
     */
    private ProdValuationDto financBusiTotal;

    /**
     * 融券业务合计
     */
    private ProdValuationDto marginBusiTotal;

    /**
     * 今日可用头寸
     */
    private ProdValuationDto tdyAvailPosition;

    /**
     * 产品累计单位净值
     */
    private ProdValuationDto prodTotalUnitPrice;

    /**
     * 昨日单位净值
     */
    private ProdValuationDto ystUnitPrice;

    /**
     * 成立以来净值增长率(%)
     */
    private ProdValuationDto priceGrowRateSince;

    /**
     * 净值年增长率(%)
     */
    private ProdValuationDto priceGrowRateYear;

    /**
     * 净值季度增长率(%)
     */
    private ProdValuationDto priceGrowRateQuarter;

    /**
     * 净值月增长率(%)
     */
    private ProdValuationDto priceGrowRateMonth;

    /**
     * 净值周增长率(%)
     */
    private ProdValuationDto priceGrowRateWeek;

    /**
     * 净值日增长率(%)
     */
    private ProdValuationDto priceGrowRateDay;

    /**
     * 影子价格单位净值
     */
    private ProdValuationDto shandowUnitPrice;
    
    /**
     * 待抵扣投资收益税
     */
    private ProdValuationDto deductInvestIncomeTax;
    
    /**
     * 待抵扣暂估投资收益税
     */
    private ProdValuationDto extDeductInvestIncomeTax;

    /**
     * 偏离金额
     */
    private ProdValuationDto deviationAmount;

    /**
     * 偏离度
     */
    private ProdValuationDto deviationDegree;

    /**
     * 产品本日收益
     */
    private ProdValuationDto tdyProdProfit;

    /**
     * 万份收益
     */
    private ProdValuationDto pYield;

    /**
     * 本年累积万份收益
     */
    private ProdValuationDto pYieldYear;

    /**
     * 从成立以来累计万份收益
     */
    private ProdValuationDto pYieldSince;

    /**
     * 本日年化收益率
     */
    private ProdValuationDto tdyYield;

    /**
     * 7日年化收益率
     */
    private ProdValuationDto yield7;

    /**
     * 投资组合平均到期日
     */
    private ProdValuationDto invGroupAvrMday;

    /**
     * 剩余存续期
     */
    private ProdValuationDto remainDuration;

    /**
     * 当日客户收益
     */
    private ProdValuationDto tdyCustProfit;

    /**
     * 累计客户收益
     */
    private ProdValuationDto totalCustProfit;

    /**
     * 当日客户收益率
     */
    private ProdValuationDto tdyCustProfitRate;

    /**
     * 估值表余额表状态表
     */
    private BokValbalTableStatus bokValbalTableStatus;

    /**
     * 估值表余额表生成日志表
     */
    private BokValbalTableLog bokValbalTableLog;

    /**
     * 分层数据明细
     */
    private List<ProdValuationDto> layeredProdValDtoList;

    /**
     * 产品资产净值-费后
     */
    private ProdValuationDto netAssetValue;

    /**
     * 产品单位净值-费后
     */
    private ProdValuationDto netUnitValue;

    /**
     * 产品资产净值-费前
     */
    private ProdValuationDto netAssetValueBef;

    /**
     * 产品单位净值-费前
     */
    private ProdValuationDto netUnitValueBef;

    /**
     * 周期年化收益率
     */
    private ProdValuationDto yieldTerm;

    /**
     * 产品本日收益-费前
     */
    private ProdValuationDto tdyIncomeBef;

    /**
     * 万份收益-费前
     */
    private ProdValuationDto pYieldBef;

    /**
     * 成立以来年化收益率
     */
    private ProdValuationDto toTdyyield;

    /**
     * 获取 finprodName
     * 
     * @return finprodName.
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 finprodName
     * 
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public List<ProdValuationDto> getProdValuationDtoList() {
        return prodValuationDtoList;
    }

    public void setProdValuationDtoList(List<ProdValuationDto> prodValuationDtoList) {
        this.prodValuationDtoList = prodValuationDtoList;
    }

    public ProdValuationDto getActRecAmount() {
        return actRecAmount;
    }

    public void setActRecAmount(ProdValuationDto actRecAmount) {
        this.actRecAmount = actRecAmount;
    }

    public ProdValuationDto getProfLossUnrealAmount() {
        return profLossUnrealAmount;
    }

    public void setProfLossUnrealAmount(ProdValuationDto profLossUnrealAmount) {
        this.profLossUnrealAmount = profLossUnrealAmount;
    }

    public ProdValuationDto getProfLossRealAmount() {
        return profLossRealAmount;
    }

    public void setProfLossRealAmount(ProdValuationDto profLossRealAmount) {
        this.profLossRealAmount = profLossRealAmount;
    }

    public ProdValuationDto getAssetTotal() {
        return assetTotal;
    }

    public void setAssetTotal(ProdValuationDto assetTotal) {
        this.assetTotal = assetTotal;
    }

    public ProdValuationDto getDebtTotal() {
        return debtTotal;
    }

    public void setDebtTotal(ProdValuationDto debtTotal) {
        this.debtTotal = debtTotal;
    }

    public ProdValuationDto getProdAssetVal() {
        return prodAssetVal;
    }

    public void setProdAssetVal(ProdValuationDto prodAssetVal) {
        this.prodAssetVal = prodAssetVal;
    }

    public ProdValuationDto getProdUnitVal() {
        return prodUnitVal;
    }

    public void setProdUnitVal(ProdValuationDto prodUnitVal) {
        this.prodUnitVal = prodUnitVal;
    }

    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    /**
     * 获取 booksetName
     * 
     * @return booksetName.
     */
    public String getBooksetName() {
        return booksetName;
    }

    /**
     * 设置 booksetName
     * 
     * @param booksetName booksetName
     */
    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName;
    }

    /**
     * 取得 证券投资合计
     * 
     * @return 证券投资合计
     */
    public ProdValuationDto getSecurityInvTotal() {
        return securityInvTotal;
    }

    /**
     * 设置 证券投资合计
     * 
     * @param securityInvTotal 证券投资合计
     */
    public void setSecurityInvTotal(ProdValuationDto securityInvTotal) {
        this.securityInvTotal = securityInvTotal;
    }

    /**
     * 取得 其中股票投资
     * 
     * @return 其中股票投资
     */
    public ProdValuationDto getShareInvInside() {
        return shareInvInside;
    }

    /**
     * 设置 其中股票投资
     * 
     * @param shareInvInside 其中股票投资
     */
    public void setShareInvInside(ProdValuationDto shareInvInside) {
        this.shareInvInside = shareInvInside;
    }

    /**
     * 取得 其中债券投资
     * 
     * @return 其中债券投资
     */
    public ProdValuationDto getBondInvInside() {
        return bondInvInside;
    }

    /**
     * 设置 其中债券投资
     * 
     * @param bondInvInside 其中债券投资
     */
    public void setBondInvInside(ProdValuationDto bondInvInside) {
        this.bondInvInside = bondInvInside;
    }

    /**
     * 取得 其中基金投资
     * 
     * @return 其中基金投资
     */
    public ProdValuationDto getFundInvInside() {
        return fundInvInside;
    }

    /**
     * 设置 其中基金投资
     * 
     * @param fundInvInside 其中基金投资
     */
    public void setFundInvInside(ProdValuationDto fundInvInside) {
        this.fundInvInside = fundInvInside;
    }

    /**
     * 取得 其中权证投资
     * 
     * @return 其中权证投资
     */
    public ProdValuationDto getWarrantInvInside() {
        return warrantInvInside;
    }

    /**
     * 设置 其中权证投资
     * 
     * @param warrantInvInside 其中权证投资
     */
    public void setWarrantInvInside(ProdValuationDto warrantInvInside) {
        this.warrantInvInside = warrantInvInside;
    }

    /**
     * 取得 融资业务合计
     * 
     * @return 融资业务合计
     */
    public ProdValuationDto getFinancBusiTotal() {
        return financBusiTotal;
    }

    /**
     * 设置 融资业务合计
     * 
     * @param financBusiTotal 融资业务合计
     */
    public void setFinancBusiTotal(ProdValuationDto financBusiTotal) {
        this.financBusiTotal = financBusiTotal;
    }

    /**
     * 取得 融券业务合计
     * 
     * @return 融券业务合计
     */
    public ProdValuationDto getMarginBusiTotal() {
        return marginBusiTotal;
    }

    /**
     * 设置 融券业务合计
     * 
     * @param marginBusiTotal 融券业务合计
     */
    public void setMarginBusiTotal(ProdValuationDto marginBusiTotal) {
        this.marginBusiTotal = marginBusiTotal;
    }

    /**
     * 取得 今日可用头寸
     * 
     * @return 今日可用头寸
     */
    public ProdValuationDto getTdyAvailPosition() {
        return tdyAvailPosition;
    }

    /**
     * 设置 今日可用头寸
     * 
     * @param tdyAvailPosition 今日可用头寸
     */
    public void setTdyAvailPosition(ProdValuationDto tdyAvailPosition) {
        this.tdyAvailPosition = tdyAvailPosition;
    }

    /**
     * 取得 产品累计单位净值
     * 
     * @return 产品累计单位净值
     */
    public ProdValuationDto getProdTotalUnitPrice() {
        return prodTotalUnitPrice;
    }

    /**
     * 设置 产品累计单位净值
     * 
     * @param prodTotalUnitPrice 产品累计单位净值
     */
    public void setProdTotalUnitPrice(ProdValuationDto prodTotalUnitPrice) {
        this.prodTotalUnitPrice = prodTotalUnitPrice;
    }

    /**
     * 取得 昨日单位净值
     * 
     * @return 昨日单位净值
     */
    public ProdValuationDto getYstUnitPrice() {
        return ystUnitPrice;
    }

    /**
     * 设置 昨日单位净值
     * 
     * @param ystUnitPrice 昨日单位净值
     */
    public void setYstUnitPrice(ProdValuationDto ystUnitPrice) {
        this.ystUnitPrice = ystUnitPrice;
    }

    /**
     * 取得 成立以来净值增长率(%)
     * 
     * @return 成立以来净值增长率(%)
     */
    public ProdValuationDto getPriceGrowRateSince() {
        return priceGrowRateSince;
    }

    /**
     * 设置 成立以来净值增长率(%)
     * 
     * @param priceGrowRateSince 成立以来净值增长率(%)
     */
    public void setPriceGrowRateSince(ProdValuationDto priceGrowRateSince) {
        this.priceGrowRateSince = priceGrowRateSince;
    }

    /**
     * 取得 净值年增长率(%)
     * 
     * @return 净值年增长率(%)
     */
    public ProdValuationDto getPriceGrowRateYear() {
        return priceGrowRateYear;
    }

    /**
     * 设置 净值年增长率(%)
     * 
     * @param priceGrowRateYear 净值年增长率(%)
     */
    public void setPriceGrowRateYear(ProdValuationDto priceGrowRateYear) {
        this.priceGrowRateYear = priceGrowRateYear;
    }

    /**
     * 取得 净值季度增长率(%)
     * 
     * @return 净值季度增长率(%)
     */
    public ProdValuationDto getPriceGrowRateQuarter() {
        return priceGrowRateQuarter;
    }

    /**
     * 设置 净值季度增长率(%)
     * 
     * @param priceGrowRateQuarter 净值季度增长率(%)
     */
    public void setPriceGrowRateQuarter(ProdValuationDto priceGrowRateQuarter) {
        this.priceGrowRateQuarter = priceGrowRateQuarter;
    }

    /**
     * 取得 净值月增长率(%)
     * 
     * @return 净值月增长率(%)
     */
    public ProdValuationDto getPriceGrowRateMonth() {
        return priceGrowRateMonth;
    }

    /**
     * 设置 净值月增长率(%)
     * 
     * @param priceGrowRateMonth 净值月增长率(%)
     */
    public void setPriceGrowRateMonth(ProdValuationDto priceGrowRateMonth) {
        this.priceGrowRateMonth = priceGrowRateMonth;
    }

    /**
     * 取得 净值周增长率(%)
     * 
     * @return 净值周增长率(%)
     */
    public ProdValuationDto getPriceGrowRateWeek() {
        return priceGrowRateWeek;
    }

    /**
     * 设置 净值周增长率(%)
     * 
     * @param priceGrowRateWeek 净值周增长率(%)
     */
    public void setPriceGrowRateWeek(ProdValuationDto priceGrowRateWeek) {
        this.priceGrowRateWeek = priceGrowRateWeek;
    }

    /**
     * 取得 净值日增长率(%)
     * 
     * @return 净值日增长率(%)
     */
    public ProdValuationDto getPriceGrowRateDay() {
        return priceGrowRateDay;
    }

    /**
     * 设置 净值日增长率(%)
     * 
     * @param priceGrowRateDay 净值日增长率(%)
     */
    public void setPriceGrowRateDay(ProdValuationDto priceGrowRateDay) {
        this.priceGrowRateDay = priceGrowRateDay;
    }

    /**
     * 取得 影子价格单位净值
     * 
     * @return 影子价格单位净值
     */
    public ProdValuationDto getShandowUnitPrice() {
        return shandowUnitPrice;
    }

    /**
     * 设置 影子价格单位净值
     * 
     * @param shandowUnitPrice 影子价格单位净值
     */
    public void setShandowUnitPrice(ProdValuationDto shandowUnitPrice) {
        this.shandowUnitPrice = shandowUnitPrice;
    }
    
    /**
     * 取得 待抵扣投资收益税
     * 
     * @return 待抵扣投资收益税
     */
    public ProdValuationDto getDeductInvestIncomeTax() {
        return deductInvestIncomeTax;
    }

    /**
     * 设置 待抵扣投资收益税
     * 
     * @param deductInvestIncomeTax 待抵扣投资收益税
     */
    public void setDeductInvestIncomeTax(ProdValuationDto deductInvestIncomeTax) {
        this.deductInvestIncomeTax = deductInvestIncomeTax;
    }
    
    /**
     * 取得 待抵扣暂估投资收益税
     * 
     * @return 待抵扣暂估投资收益税
     */
    public ProdValuationDto getExtDeductInvestIncomeTax() {
        return extDeductInvestIncomeTax;
    }

    /**
     * 设置 待抵扣暂估投资收益税
     * 
     * @param extDeductInvestIncomeTax 待抵扣暂估投资收益税
     */
    public void setExtDeductInvestIncomeTax(ProdValuationDto extDeductInvestIncomeTax) {
        this.extDeductInvestIncomeTax = extDeductInvestIncomeTax;
    }

    /**
     * 取得 偏离金额
     * 
     * @return 偏离金额
     */
    public ProdValuationDto getDeviationAmount() {
        return deviationAmount;
    }

    /**
     * 设置 偏离金额
     * 
     * @param deviationAmount 偏离金额
     */
    public void setDeviationAmount(ProdValuationDto deviationAmount) {
        this.deviationAmount = deviationAmount;
    }

    /**
     * 取得 偏离度
     * 
     * @return 偏离度
     */
    public ProdValuationDto getDeviationDegree() {
        return deviationDegree;
    }

    /**
     * 设置 偏离度
     * 
     * @param deviationDegree 偏离度
     */
    public void setDeviationDegree(ProdValuationDto deviationDegree) {
        this.deviationDegree = deviationDegree;
    }

    /**
     * 取得 产品本日收益
     * 
     * @return 产品本日收益
     */
    public ProdValuationDto getTdyProdProfit() {
        return tdyProdProfit;
    }

    /**
     * 设置 产品本日收益
     * 
     * @param tdyProdProfit 产品本日收益
     */
    public void setTdyProdProfit(ProdValuationDto tdyProdProfit) {
        this.tdyProdProfit = tdyProdProfit;
    }

    /**
     * 取得 万份收益
     * 
     * @return 万份收益
     */
    public ProdValuationDto getPYield() {
        return pYield;
    }

    /**
     * 设置 万份收益
     * 
     * @param pYield 万份收益
     */
    public void setPYield(ProdValuationDto pYield) {
        this.pYield = pYield;
    }

    /**
     * 取得 本年累积万份收益
     * 
     * @return 本年累积万份收益
     */
    public ProdValuationDto getPYieldYear() {
        return pYieldYear;
    }

    /**
     * 设置 本年累积万份收益
     * 
     * @param pYieldYear 本年累积万份收益
     */
    public void setPYieldYear(ProdValuationDto pYieldYear) {
        this.pYieldYear = pYieldYear;
    }

    /**
     * 取得 从成立以来累计万份收益
     * 
     * @return 从成立以来累计万份收益
     */
    public ProdValuationDto getPYieldSince() {
        return pYieldSince;
    }

    /**
     * 设置 从成立以来累计万份收益
     * 
     * @param pYieldSince 从成立以来累计万份收益
     */
    public void setPYieldSince(ProdValuationDto pYieldSince) {
        this.pYieldSince = pYieldSince;
    }

    /**
     * 取得 本日年化收益率
     * 
     * @return 本日年化收益率
     */
    public ProdValuationDto getTdyYield() {
        return tdyYield;
    }

    /**
     * 设置 本日年化收益率
     * 
     * @param tdyYield 本日年化收益率
     */
    public void setTdyYield(ProdValuationDto tdyYield) {
        this.tdyYield = tdyYield;
    }

    /**
     * 取得 7日年化收益率
     * 
     * @return 7日年化收益率
     */
    public ProdValuationDto getYield7() {
        return yield7;
    }

    /**
     * 设置 7日年化收益率
     * 
     * @param yield7 7日年化收益率
     */
    public void setYield7(ProdValuationDto yield7) {
        this.yield7 = yield7;
    }

    /**
     * 获值 估值表余额表状态表
     * 
     * @return 估值表余额表状态表
     */
    public BokValbalTableStatus getBokValbalTableStatus() {
        return bokValbalTableStatus;
    }

    /**
     * 设值 估值表余额表状态表
     * 
     * @param bokValbalTableStatus 估值表余额表状态表
     */
    public void setBokValbalTableStatus(BokValbalTableStatus bokValbalTableStatus) {
        this.bokValbalTableStatus = bokValbalTableStatus;
    }

    /**
     * 估值表余额表生成日志表
     * 
     * @return 估值表余额表生成日志表
     */
    public BokValbalTableLog getBokValbalTableLog() {
        return bokValbalTableLog;
    }

    /**
     * 设值 估值表余额表状态表
     * 
     * @param bokValbalTableLog 估值表余额表状态表
     */
    public void setBokValbalTableLog(BokValbalTableLog bokValbalTableLog) {
        this.bokValbalTableLog = bokValbalTableLog;
    }

    /**
     * 获取 本年累积万份收益
     * 
     * @return 本年累积万份收益.
     */
    public ProdValuationDto getpYieldYear() {
        return pYieldYear;
    }

    /**
     * 设置 本年累积万份收益
     * 
     * @param pYieldYear 本年累积万份收益
     */
    public void setpYieldYear(ProdValuationDto pYieldYear) {
        this.pYieldYear = pYieldYear;
    }

    /**
     * 获取 从成立以来累计万份收益
     * 
     * @return 从成立以来累计万份收益.
     */
    public ProdValuationDto getpYieldSince() {
        return pYieldSince;
    }

    /**
     * 设置 从成立以来累计万份收益
     * 
     * @param pYieldSince 从成立以来累计万份收益
     */
    public void setpYieldSince(ProdValuationDto pYieldSince) {
        this.pYieldSince = pYieldSince;
    }

    /**
     * 获取 剩余存续期
     * 
     * @return 剩余存续期.
     */
    public ProdValuationDto getRemainDuration() {
        return remainDuration;
    }

    /**
     * 设置 剩余存续期
     * 
     * @param remainDuration 剩余存续期
     */
    public void setRemainDuration(ProdValuationDto remainDuration) {
        this.remainDuration = remainDuration;
    }

    /**
     * 获取 投资组合平均到期日
     * 
     * @return 投资组合平均到期日.
     */
    public ProdValuationDto getInvGroupAvrMday() {
        return invGroupAvrMday;
    }

    /**
     * 设置 投资组合平均到期日
     * 
     * @param invGroupAvrMdate 投资组合平均到期日
     */
    public void setInvGroupAvrMday(ProdValuationDto invGroupAvrMday) {
        this.invGroupAvrMday = invGroupAvrMday;
    }

    /**
     * 获取 当日客户收益
     * 
     * @return 当日客户收益
     */
    public ProdValuationDto getTdyCustProfit() {
        return tdyCustProfit;
    }

    /**
     * 设置 当日客户收益
     * 
     * @param tdyCustProfit 当日客户收益
     */
    public void setTdyCustProfit(ProdValuationDto tdyCustProfit) {
        this.tdyCustProfit = tdyCustProfit;
    }

    /**
     * 获取 累计客户收益
     * 
     * @return 累计客户收益
     */
    public ProdValuationDto getTotalCustProfit() {
        return totalCustProfit;
    }

    /**
     * 设置 累计客户收益
     * 
     * @param totalCustProfit 累计客户收益
     */
    public void setTotalCustProfit(ProdValuationDto totalCustProfit) {
        this.totalCustProfit = totalCustProfit;
    }

    /**
     * 获取 当日客户收益率
     * 
     * @return 当日客户收益率
     */
    public ProdValuationDto getTdyCustProfitRate() {
        return tdyCustProfitRate;
    }

    /**
     * 设置 当日客户收益率
     * 
     * @param tdyCustProfitRate 当日客户收益率
     */
    public void setTdyCustProfitRate(ProdValuationDto tdyCustProfitRate) {
        this.tdyCustProfitRate = tdyCustProfitRate;
    }

    /**
     * 获取 账套全称
     * 
     * @return 账套全称
     */
    public String getBooksetFullName() {
        return booksetFullName;
    }

    /**
     * 设置 账套全称
     * 
     * @param booksetFullName 账套全称
     */
    public void setBooksetFullName(String booksetFullName) {
        this.booksetFullName = booksetFullName;
    }

    /**
     * 获取 分层数据明细
     * 
     * @return 分层数据明细
     */
    public List<ProdValuationDto> getLayeredProdValDtoList() {
        return layeredProdValDtoList;
    }

    /**
     * 设置 分层数据明细
     * 
     * @param layeredProdValDtoList 分层数据明细
     */
    public void setLayeredProdValDtoList(List<ProdValuationDto> layeredProdValDtoList) {
        this.layeredProdValDtoList = layeredProdValDtoList;
    }

    public ProdValuationDto getpYield() {
        return pYield;
    }

    public void setpYield(ProdValuationDto pYield) {
        this.pYield = pYield;
    }

    public ProdValuationDto getNetAssetValue() {
        return netAssetValue;
    }

    public void setNetAssetValue(ProdValuationDto netAssetValue) {
        this.netAssetValue = netAssetValue;
    }

    public ProdValuationDto getNetUnitValue() {
        return netUnitValue;
    }

    public void setNetUnitValue(ProdValuationDto netUnitValue) {
        this.netUnitValue = netUnitValue;
    }

    public ProdValuationDto getNetAssetValueBef() {
        return netAssetValueBef;
    }

    public void setNetAssetValueBef(ProdValuationDto netAssetValueBef) {
        this.netAssetValueBef = netAssetValueBef;
    }

    public ProdValuationDto getNetUnitValueBef() {
        return netUnitValueBef;
    }

    public void setNetUnitValueBef(ProdValuationDto netUnitValueBef) {
        this.netUnitValueBef = netUnitValueBef;
    }

    public ProdValuationDto getYieldTerm() {
        return yieldTerm;
    }

    public void setYieldTerm(ProdValuationDto yieldTerm) {
        this.yieldTerm = yieldTerm;
    }

    public ProdValuationDto getTdyIncomeBef() {
        return tdyIncomeBef;
    }

    public void setTdyIncomeBef(ProdValuationDto tdyIncomeBef) {
        this.tdyIncomeBef = tdyIncomeBef;
    }

    public ProdValuationDto getpYieldBef() {
        return pYieldBef;
    }

    public void setpYieldBef(ProdValuationDto pYieldBef) {
        this.pYieldBef = pYieldBef;
    }

    public ProdValuationDto getToTdyyield() {
        return toTdyyield;
    }

    public void setToTdyyield(ProdValuationDto toTdyyield) {
        this.toTdyyield = toTdyyield;
    }
}
