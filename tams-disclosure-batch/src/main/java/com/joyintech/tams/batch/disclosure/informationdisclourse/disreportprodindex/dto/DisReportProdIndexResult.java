package com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

public class DisReportProdIndexResult extends NeedOperateEntity {
	/**
     * 流水号
     */
    private String seqNo;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 估值日
     */
    private Date valueDate;

    /**
     * 实收资本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal capital;

    /**
     * 产品资产净值(按成本）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal assetValueCost;

    /**
     * 产品资产净值(按市值）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal assetValueMarket;

    /**
     * 产品单位净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitValue;

    /**
     * 产品累计单位净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitValueT;

    /**
     * 偏离金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal deviationAmt;

    /**
     * 偏离度%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal deviation;

    /**
     * 当日客户收益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal profitTdy;

    /**
     * 累计客户收益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal profitTotal;

    /**
     * 万份收益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal wYield;

    /**
     * 本日年化收益率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yieldTdy;

    /**
     * 7日年化收益率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield7;

    /**
     * 累计年化收益率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yieldTotal;

    /**
     * 预期收益率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yieldE;

    /**
     * 最高收益率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yieldEMax;

    /**
     * 浮动管理费率%
     */
    private Integer surplusTerm;

    /**
     * 投资组合平均到期日
     */
    private Integer averageMdate;

    /**
     * 导入方法
     */
    private String inputType;

    /**
     * 导入用户
     */
    private String inputUser;

    /**
     * 适配器ID
     */
    private String adapterId;

    /**
     * 当日本期利润已实现
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal profitCurrentYes;

    /**
     * 当日本期利润未实现
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal profitCurrentNo;

    /**
     * 本期利润
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal profitCurrent;

    /**
     * 当日可供分配利润
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal profitTdyAble;

    /**
     * 当日可供分配产品份额利润
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prodProfitTdyAble;

    /**
     * 当日净值收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netvalueTdyYield;

    /**
     * 累计净值收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netvalueTotalYield;

    /**
     * 当日净值增长率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netvalueTdyIncre;

    /**
     * 累计净值增长率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netvalueTotalIncre;

    /**
     * 当日业绩比较基准利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal floatBaseRate;

    /**
     * 产品分红净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bounusValue;

    /**
     * 业绩基准收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rateBenchmark;

    /**
     * 现金形式发放总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cashFormGrantTotal;

    /**
     * 再投资形式发放总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal reinvestFormGrantTotal;

    /**
     * 直接通过应付赎回款转出金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal redemptionTurnoutAmt;

    /**
     * 应付利润本年变动
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal profitThisyearChange;

    /**
     * 业绩基准说明
     */
    private String benchmarkInfo;

    /**
     * 现金分红
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cashFormGrantTdy;

    /**
     * 红利再投金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal reinvestFormGrantTdy;

    /**
     * 赎回利息金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal redemIntamtTdy;

    /**
     * 应付利润
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal profitChangeTdy;

    /**
     * 业绩比较基准累计增长率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal floatTotalRate;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流水号
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 估值日
     * @return 估值日
     */
    public Date getValueDate() {
        return valueDate;
    }

    /**
     * 设置 估值日
     * @param valueDate 估值日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    /**
     * 取得 实收资本
     * @return 实收资本
     */
    public BigDecimal getCapital() {
        return capital;
    }

    /**
     * 设置 实收资本
     * @param capital 实收资本
     */
    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    /**
     * 取得 产品资产净值(按成本）
     * @return 产品资产净值(按成本）
     */
    public BigDecimal getAssetValueCost() {
        return assetValueCost;
    }

    /**
     * 设置 产品资产净值(按成本）
     * @param assetValueCost 产品资产净值(按成本）
     */
    public void setAssetValueCost(BigDecimal assetValueCost) {
        this.assetValueCost = assetValueCost;
    }

    /**
     * 取得 产品资产净值(按市值）
     * @return 产品资产净值(按市值）
     */
    public BigDecimal getAssetValueMarket() {
        return assetValueMarket;
    }

    /**
     * 设置 产品资产净值(按市值）
     * @param assetValueMarket 产品资产净值(按市值）
     */
    public void setAssetValueMarket(BigDecimal assetValueMarket) {
        this.assetValueMarket = assetValueMarket;
    }

    /**
     * 取得 产品单位净值
     * @return 产品单位净值
     */
    public BigDecimal getUnitValue() {
        return unitValue;
    }

    /**
     * 设置 产品单位净值
     * @param unitValue 产品单位净值
     */
    public void setUnitValue(BigDecimal unitValue) {
        this.unitValue = unitValue;
    }

    /**
     * 取得 产品累计单位净值
     * @return 产品累计单位净值
     */
    public BigDecimal getUnitValueT() {
        return unitValueT;
    }

    /**
     * 设置 产品累计单位净值
     * @param unitValueT 产品累计单位净值
     */
    public void setUnitValueT(BigDecimal unitValueT) {
        this.unitValueT = unitValueT;
    }

    /**
     * 取得 偏离金额
     * @return 偏离金额
     */
    public BigDecimal getDeviationAmt() {
        return deviationAmt;
    }

    /**
     * 设置 偏离金额
     * @param deviationAmt 偏离金额
     */
    public void setDeviationAmt(BigDecimal deviationAmt) {
        this.deviationAmt = deviationAmt;
    }

    /**
     * 取得 偏离度%
     * @return 偏离度%
     */
    public BigDecimal getDeviation() {
        return deviation;
    }

    /**
     * 设置 偏离度%
     * @param deviation 偏离度%
     */
    public void setDeviation(BigDecimal deviation) {
        this.deviation = deviation;
    }

    /**
     * 取得 当日客户收益
     * @return 当日客户收益
     */
    public BigDecimal getProfitTdy() {
        return profitTdy;
    }

    /**
     * 设置 当日客户收益
     * @param profitTdy 当日客户收益
     */
    public void setProfitTdy(BigDecimal profitTdy) {
        this.profitTdy = profitTdy;
    }

    /**
     * 取得 累计客户收益
     * @return 累计客户收益
     */
    public BigDecimal getProfitTotal() {
        return profitTotal;
    }

    /**
     * 设置 累计客户收益
     * @param profitTotal 累计客户收益
     */
    public void setProfitTotal(BigDecimal profitTotal) {
        this.profitTotal = profitTotal;
    }

    /**
     * 取得 万份收益
     * @return 万份收益
     */
    public BigDecimal getwYield() {
        return wYield;
    }

    /**
     * 设置 万份收益
     * @param wYield 万份收益
     */
    public void setwYield(BigDecimal wYield) {
        this.wYield = wYield;
    }

    /**
     * 取得 本日年化收益率%
     * @return 本日年化收益率%
     */
    public BigDecimal getYieldTdy() {
        return yieldTdy;
    }

    /**
     * 设置 本日年化收益率%
     * @param yieldTdy 本日年化收益率%
     */
    public void setYieldTdy(BigDecimal yieldTdy) {
        this.yieldTdy = yieldTdy;
    }

    /**
     * 取得 7日年化收益率%
     * @return 7日年化收益率%
     */
    public BigDecimal getYield7() {
        return yield7;
    }

    /**
     * 设置 7日年化收益率%
     * @param yield7 7日年化收益率%
     */
    public void setYield7(BigDecimal yield7) {
        this.yield7 = yield7;
    }

    /**
     * 取得 累计年化收益率%
     * @return 累计年化收益率%
     */
    public BigDecimal getYieldTotal() {
        return yieldTotal;
    }

    /**
     * 设置 累计年化收益率%
     * @param yieldTotal 累计年化收益率%
     */
    public void setYieldTotal(BigDecimal yieldTotal) {
        this.yieldTotal = yieldTotal;
    }

    /**
     * 取得 预期收益率%
     * @return 预期收益率%
     */
    public BigDecimal getYieldE() {
        return yieldE;
    }

    /**
     * 设置 预期收益率%
     * @param yieldE 预期收益率%
     */
    public void setYieldE(BigDecimal yieldE) {
        this.yieldE = yieldE;
    }

    /**
     * 取得 最高收益率%
     * @return 最高收益率%
     */
    public BigDecimal getYieldEMax() {
        return yieldEMax;
    }

    /**
     * 设置 最高收益率%
     * @param yieldEMax 最高收益率%
     */
    public void setYieldEMax(BigDecimal yieldEMax) {
        this.yieldEMax = yieldEMax;
    }

    /**
     * 取得 浮动管理费率%
     * @return 浮动管理费率%
     */
    public Integer getSurplusTerm() {
        return surplusTerm;
    }

    /**
     * 设置 浮动管理费率%
     * @param surplusTerm 浮动管理费率%
     */
    public void setSurplusTerm(Integer surplusTerm) {
        this.surplusTerm = surplusTerm;
    }

    /**
     * 取得 投资组合平均到期日
     * @return 投资组合平均到期日
     */
    public Integer getAverageMdate() {
        return averageMdate;
    }

    /**
     * 设置 投资组合平均到期日
     * @param averageMdate 投资组合平均到期日
     */
    public void setAverageMdate(Integer averageMdate) {
        this.averageMdate = averageMdate;
    }

    /**
     * 取得 导入方法
     * @return 导入方法
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置 导入方法
     * @param inputType 导入方法
     */
    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }

    /**
     * 取得 导入用户
     * @return 导入用户
     */
    public String getInputUser() {
        return inputUser;
    }

    /**
     * 设置 导入用户
     * @param inputUser 导入用户
     */
    public void setInputUser(String inputUser) {
        this.inputUser = inputUser == null ? null : inputUser.trim();
    }

    /**
     * 取得 适配器ID
     * @return 适配器ID
     */
    public String getAdapterId() {
        return adapterId;
    }

    /**
     * 设置 适配器ID
     * @param adapterId 适配器ID
     */
    public void setAdapterId(String adapterId) {
        this.adapterId = adapterId == null ? null : adapterId.trim();
    }

    /**
     * 取得 当日本期利润已实现
     * @return 当日本期利润已实现
     */
    public BigDecimal getProfitCurrentYes() {
        return profitCurrentYes;
    }

    /**
     * 设置 当日本期利润已实现
     * @param profitCurrentYes 当日本期利润已实现
     */
    public void setProfitCurrentYes(BigDecimal profitCurrentYes) {
        this.profitCurrentYes = profitCurrentYes;
    }

    /**
     * 取得 当日本期利润未实现
     * @return 当日本期利润未实现
     */
    public BigDecimal getProfitCurrentNo() {
        return profitCurrentNo;
    }

    /**
     * 设置 当日本期利润未实现
     * @param profitCurrentNo 当日本期利润未实现
     */
    public void setProfitCurrentNo(BigDecimal profitCurrentNo) {
        this.profitCurrentNo = profitCurrentNo;
    }

    /**
     * 取得 本期利润
     * @return 本期利润
     */
    public BigDecimal getProfitCurrent() {
        return profitCurrent;
    }

    /**
     * 设置 本期利润
     * @param profitCurrent 本期利润
     */
    public void setProfitCurrent(BigDecimal profitCurrent) {
        this.profitCurrent = profitCurrent;
    }

    /**
     * 取得 当日可供分配利润
     * @return 当日可供分配利润
     */
    public BigDecimal getProfitTdyAble() {
        return profitTdyAble;
    }

    /**
     * 设置 当日可供分配利润
     * @param profitTdyAble 当日可供分配利润
     */
    public void setProfitTdyAble(BigDecimal profitTdyAble) {
        this.profitTdyAble = profitTdyAble;
    }

    /**
     * 取得 当日可供分配产品份额利润
     * @return 当日可供分配产品份额利润
     */
    public BigDecimal getProdProfitTdyAble() {
        return prodProfitTdyAble;
    }

    /**
     * 设置 当日可供分配产品份额利润
     * @param prodProfitTdyAble 当日可供分配产品份额利润
     */
    public void setProdProfitTdyAble(BigDecimal prodProfitTdyAble) {
        this.prodProfitTdyAble = prodProfitTdyAble;
    }

    /**
     * 取得 当日净值收益率
     * @return 当日净值收益率
     */
    public BigDecimal getNetvalueTdyYield() {
        return netvalueTdyYield;
    }

    /**
     * 设置 当日净值收益率
     * @param netvalueTdyYield 当日净值收益率
     */
    public void setNetvalueTdyYield(BigDecimal netvalueTdyYield) {
        this.netvalueTdyYield = netvalueTdyYield;
    }

    /**
     * 取得 累计净值收益率
     * @return 累计净值收益率
     */
    public BigDecimal getNetvalueTotalYield() {
        return netvalueTotalYield;
    }

    /**
     * 设置 累计净值收益率
     * @param netvalueTotalYield 累计净值收益率
     */
    public void setNetvalueTotalYield(BigDecimal netvalueTotalYield) {
        this.netvalueTotalYield = netvalueTotalYield;
    }

    /**
     * 取得 当日净值增长率
     * @return 当日净值增长率
     */
    public BigDecimal getNetvalueTdyIncre() {
        return netvalueTdyIncre;
    }

    /**
     * 设置 当日净值增长率
     * @param netvalueTdyIncre 当日净值增长率
     */
    public void setNetvalueTdyIncre(BigDecimal netvalueTdyIncre) {
        this.netvalueTdyIncre = netvalueTdyIncre;
    }

    /**
     * 取得 累计净值增长率
     * @return 累计净值增长率
     */
    public BigDecimal getNetvalueTotalIncre() {
        return netvalueTotalIncre;
    }

    /**
     * 设置 累计净值增长率
     * @param netvalueTotalIncre 累计净值增长率
     */
    public void setNetvalueTotalIncre(BigDecimal netvalueTotalIncre) {
        this.netvalueTotalIncre = netvalueTotalIncre;
    }

    /**
     * 取得 当日业绩比较基准利率
     * @return 当日业绩比较基准利率
     */
    public BigDecimal getFloatBaseRate() {
        return floatBaseRate;
    }

    /**
     * 设置 当日业绩比较基准利率
     * @param floatBaseRate 当日业绩比较基准利率
     */
    public void setFloatBaseRate(BigDecimal floatBaseRate) {
        this.floatBaseRate = floatBaseRate;
    }

    /**
     * 取得 产品分红净值
     * @return 产品分红净值
     */
    public BigDecimal getBounusValue() {
        return bounusValue;
    }

    /**
     * 设置 产品分红净值
     * @param bounusValue 产品分红净值
     */
    public void setBounusValue(BigDecimal bounusValue) {
        this.bounusValue = bounusValue;
    }

    /**
     * 取得 业绩基准收益率
     * @return 业绩基准收益率
     */
    public BigDecimal getRateBenchmark() {
        return rateBenchmark;
    }

    /**
     * 设置 业绩基准收益率
     * @param rateBenchmark 业绩基准收益率
     */
    public void setRateBenchmark(BigDecimal rateBenchmark) {
        this.rateBenchmark = rateBenchmark;
    }

    /**
     * 取得 现金形式发放总额
     * @return 现金形式发放总额
     */
    public BigDecimal getCashFormGrantTotal() {
        return cashFormGrantTotal;
    }

    /**
     * 设置 现金形式发放总额
     * @param cashFormGrantTotal 现金形式发放总额
     */
    public void setCashFormGrantTotal(BigDecimal cashFormGrantTotal) {
        this.cashFormGrantTotal = cashFormGrantTotal;
    }

    /**
     * 取得 再投资形式发放总额
     * @return 再投资形式发放总额
     */
    public BigDecimal getReinvestFormGrantTotal() {
        return reinvestFormGrantTotal;
    }

    /**
     * 设置 再投资形式发放总额
     * @param reinvestFormGrantTotal 再投资形式发放总额
     */
    public void setReinvestFormGrantTotal(BigDecimal reinvestFormGrantTotal) {
        this.reinvestFormGrantTotal = reinvestFormGrantTotal;
    }

    /**
     * 取得 直接通过应付赎回款转出金额
     * @return 直接通过应付赎回款转出金额
     */
    public BigDecimal getRedemptionTurnoutAmt() {
        return redemptionTurnoutAmt;
    }

    /**
     * 设置 直接通过应付赎回款转出金额
     * @param redemptionTurnoutAmt 直接通过应付赎回款转出金额
     */
    public void setRedemptionTurnoutAmt(BigDecimal redemptionTurnoutAmt) {
        this.redemptionTurnoutAmt = redemptionTurnoutAmt;
    }

    /**
     * 取得 应付利润本年变动
     * @return 应付利润本年变动
     */
    public BigDecimal getProfitThisyearChange() {
        return profitThisyearChange;
    }

    /**
     * 设置 应付利润本年变动
     * @param profitThisyearChange 应付利润本年变动
     */
    public void setProfitThisyearChange(BigDecimal profitThisyearChange) {
        this.profitThisyearChange = profitThisyearChange;
    }

    /**
     * 取得 业绩基准说明
     * @return 业绩基准说明
     */
    public String getBenchmarkInfo() {
        return benchmarkInfo;
    }

    /**
     * 设置 业绩基准说明
     * @param benchmarkInfo 业绩基准说明
     */
    public void setBenchmarkInfo(String benchmarkInfo) {
        this.benchmarkInfo = benchmarkInfo == null ? null : benchmarkInfo.trim();
    }

    /**
     * 取得 现金分红
     * @return 现金分红
     */
    public BigDecimal getCashFormGrantTdy() {
        return cashFormGrantTdy;
    }

    /**
     * 设置 现金分红
     * @param cashFormGrantTdy 现金分红
     */
    public void setCashFormGrantTdy(BigDecimal cashFormGrantTdy) {
        this.cashFormGrantTdy = cashFormGrantTdy;
    }

    /**
     * 取得 红利再投金额
     * @return 红利再投金额
     */
    public BigDecimal getReinvestFormGrantTdy() {
        return reinvestFormGrantTdy;
    }

    /**
     * 设置 红利再投金额
     * @param reinvestFormGrantTdy 红利再投金额
     */
    public void setReinvestFormGrantTdy(BigDecimal reinvestFormGrantTdy) {
        this.reinvestFormGrantTdy = reinvestFormGrantTdy;
    }

    /**
     * 取得 赎回利息金额
     * @return 赎回利息金额
     */
    public BigDecimal getRedemIntamtTdy() {
        return redemIntamtTdy;
    }

    /**
     * 设置 赎回利息金额
     * @param redemIntamtTdy 赎回利息金额
     */
    public void setRedemIntamtTdy(BigDecimal redemIntamtTdy) {
        this.redemIntamtTdy = redemIntamtTdy;
    }

    /**
     * 取得 应付利润
     * @return 应付利润
     */
    public BigDecimal getProfitChangeTdy() {
        return profitChangeTdy;
    }

    /**
     * 设置 应付利润
     * @param profitChangeTdy 应付利润
     */
    public void setProfitChangeTdy(BigDecimal profitChangeTdy) {
        this.profitChangeTdy = profitChangeTdy;
    }

    /**
     * 取得 业绩比较基准累计增长率
     * @return 业绩比较基准累计增长率
     */
    public BigDecimal getFloatTotalRate() {
        return floatTotalRate;
    }

    /**
     * 设置 业绩比较基准累计增长率
     * @param floatTotalRate 业绩比较基准累计增长率
     */
    public void setFloatTotalRate(BigDecimal floatTotalRate) {
        this.floatTotalRate = floatTotalRate;
    }
    
}
