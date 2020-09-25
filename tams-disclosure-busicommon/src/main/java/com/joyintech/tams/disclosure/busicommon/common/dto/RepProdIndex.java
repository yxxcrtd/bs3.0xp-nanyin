package com.joyintech.tams.disclosure.busicommon.common.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RepProdIndex extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 产品简称
     */
    private String prodAbbr;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 发行方式
     */
    private String issueType;

    /**
     * 发行方式(汉字)
     */
    private String issueTypeZ;

    /**
     * 估值日
     */
    private Date valueDate;
    
    /**
     * 适配器id
     */
    private  String adpaterId;
    /**
     * 实收资本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal capital;

    /**
     * 损益平准金未实现
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal profitNo;

    /**
     * 损益平准金已实现
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal profitYes;

    /**
     * 资产类合计(按成本）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal assetTotalCost;

    /**
     * 资产类合计(按市值）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal assetTotalMarket;

    /**
     * 负债类合计
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal debtTotal;

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
     * 产品昨日单位净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitValueY;

    /**
     * 产品分红净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bounusValue;

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
     * 本年累积万份收益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal wYieldYear;

    /**
     * 从成立以来累计万份收益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal wYieldTotal;

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
     * 投资组合平均到期日
     */
    private Integer averageMdate;

    /**
     * 剩余存续期
     */
    private Integer surplusTerm;

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
     * 平均剩余期限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal avgSurplusTime;

    
    /**
     *业绩比较基准
     */
    private BigDecimal floatBaseRate;
    
    
    
    public String getAdpaterId() {
		return adpaterId;
	}

	public void setAdpaterId(String adpaterId) {
		this.adpaterId = adpaterId;
	}

	public BigDecimal getFloatBaseRate() {
		return floatBaseRate;
	}

	public void setFloatBaseRate(BigDecimal floatBaseRate) {
		this.floatBaseRate = floatBaseRate;
	}

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
     * 取得 账套代码
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
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
     * 取得 产品简称
     * @return 产品简称
     */
    public String getProdAbbr() {
        return prodAbbr;
    }

    /**
     * 设置 产品简称
     * @param prodAbbr 产品简称
     */
    public void setProdAbbr(String prodAbbr) {
        this.prodAbbr = prodAbbr == null ? null : prodAbbr.trim();
    }

    /**
     * 取得 产品名称
     * @return 产品名称
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 产品名称
     * @param prodName 产品名称
     */
    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    /**
     * 取得 发行方式
     * @return 发行方式
     */
    public String getIssueType() {
        return issueType;
    }

    /**
     * 设置 发行方式
     * @param issueType 发行方式
     */
    public void setIssueType(String issueType) {
        this.issueType = issueType == null ? null : issueType.trim();
    }

    /**
     * 取得 发行方式(汉字)
     * @return 发行方式(汉字)
     */
    public String getIssueTypeZ() {
        return issueTypeZ;
    }

    /**
     * 设置 发行方式(汉字)
     * @param issueTypeZ 发行方式(汉字)
     */
    public void setIssueTypeZ(String issueTypeZ) {
        this.issueTypeZ = issueTypeZ == null ? null : issueTypeZ.trim();
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
     * 取得 损益平准金未实现
     * @return 损益平准金未实现
     */
    public BigDecimal getProfitNo() {
        return profitNo;
    }

    /**
     * 设置 损益平准金未实现
     * @param profitNo 损益平准金未实现
     */
    public void setProfitNo(BigDecimal profitNo) {
        this.profitNo = profitNo;
    }

    /**
     * 取得 损益平准金已实现
     * @return 损益平准金已实现
     */
    public BigDecimal getProfitYes() {
        return profitYes;
    }

    /**
     * 设置 损益平准金已实现
     * @param profitYes 损益平准金已实现
     */
    public void setProfitYes(BigDecimal profitYes) {
        this.profitYes = profitYes;
    }

    /**
     * 取得 资产类合计(按成本）
     * @return 资产类合计(按成本）
     */
    public BigDecimal getAssetTotalCost() {
        return assetTotalCost;
    }

    /**
     * 设置 资产类合计(按成本）
     * @param assetTotalCost 资产类合计(按成本）
     */
    public void setAssetTotalCost(BigDecimal assetTotalCost) {
        this.assetTotalCost = assetTotalCost;
    }

    /**
     * 取得 资产类合计(按市值）
     * @return 资产类合计(按市值）
     */
    public BigDecimal getAssetTotalMarket() {
        return assetTotalMarket;
    }

    /**
     * 设置 资产类合计(按市值）
     * @param assetTotalMarket 资产类合计(按市值）
     */
    public void setAssetTotalMarket(BigDecimal assetTotalMarket) {
        this.assetTotalMarket = assetTotalMarket;
    }

    /**
     * 取得 负债类合计
     * @return 负债类合计
     */
    public BigDecimal getDebtTotal() {
        return debtTotal;
    }

    /**
     * 设置 负债类合计
     * @param debtTotal 负债类合计
     */
    public void setDebtTotal(BigDecimal debtTotal) {
        this.debtTotal = debtTotal;
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
     * 取得 产品昨日单位净值
     * @return 产品昨日单位净值
     */
    public BigDecimal getUnitValueY() {
        return unitValueY;
    }

    /**
     * 设置 产品昨日单位净值
     * @param unitValueY 产品昨日单位净值
     */
    public void setUnitValueY(BigDecimal unitValueY) {
        this.unitValueY = unitValueY;
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
     * 取得 本年累积万份收益
     * @return 本年累积万份收益
     */
    public BigDecimal getwYieldYear() {
        return wYieldYear;
    }

    /**
     * 设置 本年累积万份收益
     * @param wYieldYear 本年累积万份收益
     */
    public void setwYieldYear(BigDecimal wYieldYear) {
        this.wYieldYear = wYieldYear;
    }

    /**
     * 取得 从成立以来累计万份收益
     * @return 从成立以来累计万份收益
     */
    public BigDecimal getwYieldTotal() {
        return wYieldTotal;
    }

    /**
     * 设置 从成立以来累计万份收益
     * @param wYieldTotal 从成立以来累计万份收益
     */
    public void setwYieldTotal(BigDecimal wYieldTotal) {
        this.wYieldTotal = wYieldTotal;
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
     * 取得 剩余存续期
     * @return 剩余存续期
     */
    public Integer getSurplusTerm() {
        return surplusTerm;
    }

    /**
     * 设置 剩余存续期
     * @param surplusTerm 剩余存续期
     */
    public void setSurplusTerm(Integer surplusTerm) {
        this.surplusTerm = surplusTerm;
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
     * 取得 平均剩余期限
     * @return 平均剩余期限
     */
    public BigDecimal getAvgSurplusTime() {
        return avgSurplusTime;
    }

    /**
     * 设置 平均剩余期限
     * @param avgSurplusTime 平均剩余期限
     */
    public void setAvgSurplusTime(BigDecimal avgSurplusTime) {
        this.avgSurplusTime = avgSurplusTime;
    }
}