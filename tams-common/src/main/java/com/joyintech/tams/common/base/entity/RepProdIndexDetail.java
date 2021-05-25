package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RepProdIndexDetail {
    /**
     * 父产品代码
     */
    private String fprodId;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 估值日
     */
    private Date valueDate;

    /**
     * 实收资本
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal capital;

    /**
     * 资产类合计(按市值）
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal assetTotalMarket;

    /**
     * 产品资产净值(按成本）_费前
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal assetValueCostFq;

    /**
     * 产品资产净值(按市值）_费前
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal assetValueMarketFq;

    /**
     * 偏离度%
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal deviation;

    /**
     * 产品单位净值
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal unitValue;

    /**
     * 产品累计单位净值
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal unitValueT;

    /**
     * 当日客户收益
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal profitTdy;

    /**
     * 万份收益
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal wYield;

    /**
     * 本日年化收益率%
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal yieldTdy;

    /**
     * 7日年化收益率%
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal yield7;

    /**
     * 当日本期利润已实现
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal profitCurrentYes;

    /**
     * 当日本期利润未实现
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal profitCurrentNo;

    /**
     * 业绩比较基准文本说明
     */
    private String benchmarkInfo;

    /**
     * 业绩基准收益率
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal rateBenchmark;

    /**
     * 现金分红
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal cashFormGrantTdy;

    /**
     * 红利再投金额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal reinvestFormGrantTdy;

    /**
     * 赎回利息金额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal redemIntamtTdy;

    /**
     * 应付利润
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal profitChangeTdy;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**

     */
    private static final long serialVersionUID = 1L;

    public RepProdIndexDetail(String fprodId, String prodId, String prodName, Date valueDate, BigDecimal capital, BigDecimal assetTotalMarket, BigDecimal assetValueCostFq, BigDecimal assetValueMarketFq, BigDecimal deviation, BigDecimal unitValue, BigDecimal unitValueT, BigDecimal profitTdy, BigDecimal wYield, BigDecimal yieldTdy, BigDecimal yield7, BigDecimal profitCurrentYes, BigDecimal profitCurrentNo, String benchmarkInfo, BigDecimal rateBenchmark, BigDecimal cashFormGrantTdy, BigDecimal reinvestFormGrantTdy, BigDecimal redemIntamtTdy, BigDecimal profitChangeTdy, String remark, String createTime, String updateTime) {
        this.fprodId = fprodId;
        this.prodId = prodId;
        this.prodName = prodName;
        this.valueDate = valueDate;
        this.capital = capital;
        this.assetTotalMarket = assetTotalMarket;
        this.assetValueCostFq = assetValueCostFq;
        this.assetValueMarketFq = assetValueMarketFq;
        this.deviation = deviation;
        this.unitValue = unitValue;
        this.unitValueT = unitValueT;
        this.profitTdy = profitTdy;
        this.wYield = wYield;
        this.yieldTdy = yieldTdy;
        this.yield7 = yield7;
        this.profitCurrentYes = profitCurrentYes;
        this.profitCurrentNo = profitCurrentNo;
        this.benchmarkInfo = benchmarkInfo;
        this.rateBenchmark = rateBenchmark;
        this.cashFormGrantTdy = cashFormGrantTdy;
        this.reinvestFormGrantTdy = reinvestFormGrantTdy;
        this.redemIntamtTdy = redemIntamtTdy;
        this.profitChangeTdy = profitChangeTdy;
        this.remark = remark;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public RepProdIndexDetail() {
        super();
    }

    /**
     * 获取 父产品代码
     */
    public String getFprodId() {
        return fprodId;
    }

    /**
     * 设置 父产品代码
     */
    public void setFprodId(String fprodId) {
        this.fprodId = fprodId == null ? null : fprodId.trim();
    }

    /**
     * 获取 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 获取 产品名称
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 产品名称
     */
    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    /**
     * 获取 估值日
     */
    public Date getValueDate() {
        return valueDate;
    }

    /**
     * 设置 估值日
     */
    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    /**
     * 获取 实收资本
     */
    public BigDecimal getCapital() {
        return capital;
    }

    /**
     * 设置 实收资本
     */
    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    /**
     * 获取 资产类合计(按市值）
     */
    public BigDecimal getAssetTotalMarket() {
        return assetTotalMarket;
    }

    /**
     * 设置 资产类合计(按市值）
     */
    public void setAssetTotalMarket(BigDecimal assetTotalMarket) {
        this.assetTotalMarket = assetTotalMarket;
    }

    /**
     * 获取 产品资产净值(按成本）_费前
     */
    public BigDecimal getAssetValueCostFq() {
        return assetValueCostFq;
    }

    /**
     * 设置 产品资产净值(按成本）_费前
     */
    public void setAssetValueCostFq(BigDecimal assetValueCostFq) {
        this.assetValueCostFq = assetValueCostFq;
    }

    /**
     * 获取 产品资产净值(按市值）_费前
     */
    public BigDecimal getAssetValueMarketFq() {
        return assetValueMarketFq;
    }

    /**
     * 设置 产品资产净值(按市值）_费前
     */
    public void setAssetValueMarketFq(BigDecimal assetValueMarketFq) {
        this.assetValueMarketFq = assetValueMarketFq;
    }

    /**
     * 获取 偏离度%
     */
    public BigDecimal getDeviation() {
        return deviation;
    }

    /**
     * 设置 偏离度%
     */
    public void setDeviation(BigDecimal deviation) {
        this.deviation = deviation;
    }

    /**
     * 获取 产品单位净值
     */
    public BigDecimal getUnitValue() {
        return unitValue;
    }

    /**
     * 设置 产品单位净值
     */
    public void setUnitValue(BigDecimal unitValue) {
        this.unitValue = unitValue;
    }

    /**
     * 获取 产品累计单位净值
     */
    public BigDecimal getUnitValueT() {
        return unitValueT;
    }

    /**
     * 设置 产品累计单位净值
     */
    public void setUnitValueT(BigDecimal unitValueT) {
        this.unitValueT = unitValueT;
    }

    /**
     * 获取 当日客户收益
     */
    public BigDecimal getProfitTdy() {
        return profitTdy;
    }

    /**
     * 设置 当日客户收益
     */
    public void setProfitTdy(BigDecimal profitTdy) {
        this.profitTdy = profitTdy;
    }

    /**
     * 获取 万份收益
     */
    public BigDecimal getwYield() {
        return wYield;
    }

    /**
     * 设置 万份收益
     */
    public void setwYield(BigDecimal wYield) {
        this.wYield = wYield;
    }

    /**
     * 获取 本日年化收益率%
     */
    public BigDecimal getYieldTdy() {
        return yieldTdy;
    }

    /**
     * 设置 本日年化收益率%
     */
    public void setYieldTdy(BigDecimal yieldTdy) {
        this.yieldTdy = yieldTdy;
    }

    /**
     * 获取 7日年化收益率%
     */
    public BigDecimal getYield7() {
        return yield7;
    }

    /**
     * 设置 7日年化收益率%
     */
    public void setYield7(BigDecimal yield7) {
        this.yield7 = yield7;
    }

    /**
     * 获取 当日本期利润已实现
     */
    public BigDecimal getProfitCurrentYes() {
        return profitCurrentYes;
    }

    /**
     * 设置 当日本期利润已实现
     */
    public void setProfitCurrentYes(BigDecimal profitCurrentYes) {
        this.profitCurrentYes = profitCurrentYes;
    }

    /**
     * 获取 当日本期利润未实现
     */
    public BigDecimal getProfitCurrentNo() {
        return profitCurrentNo;
    }

    /**
     * 设置 当日本期利润未实现
     */
    public void setProfitCurrentNo(BigDecimal profitCurrentNo) {
        this.profitCurrentNo = profitCurrentNo;
    }

    /**
     * 获取 业绩比较基准文本说明
     */
    public String getBenchmarkInfo() {
        return benchmarkInfo;
    }

    /**
     * 设置 业绩比较基准文本说明
     */
    public void setBenchmarkInfo(String benchmarkInfo) {
        this.benchmarkInfo = benchmarkInfo == null ? null : benchmarkInfo.trim();
    }

    /**
     * 获取 业绩基准收益率
     */
    public BigDecimal getRateBenchmark() {
        return rateBenchmark;
    }

    /**
     * 设置 业绩基准收益率
     */
    public void setRateBenchmark(BigDecimal rateBenchmark) {
        this.rateBenchmark = rateBenchmark;
    }

    /**
     * 获取 现金分红
     */
    public BigDecimal getCashFormGrantTdy() {
        return cashFormGrantTdy;
    }

    /**
     * 设置 现金分红
     */
    public void setCashFormGrantTdy(BigDecimal cashFormGrantTdy) {
        this.cashFormGrantTdy = cashFormGrantTdy;
    }

    /**
     * 获取 红利再投金额
     */
    public BigDecimal getReinvestFormGrantTdy() {
        return reinvestFormGrantTdy;
    }

    /**
     * 设置 红利再投金额
     */
    public void setReinvestFormGrantTdy(BigDecimal reinvestFormGrantTdy) {
        this.reinvestFormGrantTdy = reinvestFormGrantTdy;
    }

    /**
     * 获取 业绩基准说明
     */
    public BigDecimal getRedemIntamtTdy() {
        return redemIntamtTdy;
    }

    /**
     * 设置 业绩基准说明
     */
    public void setRedemIntamtTdy(BigDecimal redemIntamtTdy) {
        this.redemIntamtTdy = redemIntamtTdy;
    }

    /**
     * 获取 应付利润
     */
    public BigDecimal getProfitChangeTdy() {
        return profitChangeTdy;
    }

    /**
     * 设置 应付利润
     */
    public void setProfitChangeTdy(BigDecimal profitChangeTdy) {
        this.profitChangeTdy = profitChangeTdy;
    }

    /**
     * 获取 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取 创建时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * 获取 更新时间
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置 更新时间
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        RepProdIndexDetail other = (RepProdIndexDetail) that;
        return (this.getFprodId() == null ? other.getFprodId() == null : this.getFprodId().equals(other.getFprodId()))
                && (this.getProdId() == null ? other.getProdId() == null : this.getProdId().equals(other.getProdId()))
                && (this.getProdName() == null ? other.getProdName() == null : this.getProdName().equals(other.getProdName()))
                && (this.getValueDate() == null ? other.getValueDate() == null : this.getValueDate().equals(other.getValueDate()))
                && (this.getCapital() == null ? other.getCapital() == null : this.getCapital().equals(other.getCapital()))
                && (this.getAssetTotalMarket() == null ? other.getAssetTotalMarket() == null : this.getAssetTotalMarket().equals(other.getAssetTotalMarket()))
                && (this.getAssetValueCostFq() == null ? other.getAssetValueCostFq() == null : this.getAssetValueCostFq().equals(other.getAssetValueCostFq()))
                && (this.getAssetValueMarketFq() == null ? other.getAssetValueMarketFq() == null : this.getAssetValueMarketFq().equals(other.getAssetValueMarketFq()))
                && (this.getDeviation() == null ? other.getDeviation() == null : this.getDeviation().equals(other.getDeviation()))
                && (this.getUnitValue() == null ? other.getUnitValue() == null : this.getUnitValue().equals(other.getUnitValue()))
                && (this.getUnitValueT() == null ? other.getUnitValueT() == null : this.getUnitValueT().equals(other.getUnitValueT()))
                && (this.getProfitTdy() == null ? other.getProfitTdy() == null : this.getProfitTdy().equals(other.getProfitTdy()))
                && (this.getwYield() == null ? other.getwYield() == null : this.getwYield().equals(other.getwYield()))
                && (this.getYieldTdy() == null ? other.getYieldTdy() == null : this.getYieldTdy().equals(other.getYieldTdy()))
                && (this.getYield7() == null ? other.getYield7() == null : this.getYield7().equals(other.getYield7()))
                && (this.getProfitCurrentYes() == null ? other.getProfitCurrentYes() == null : this.getProfitCurrentYes().equals(other.getProfitCurrentYes()))
                && (this.getProfitCurrentNo() == null ? other.getProfitCurrentNo() == null : this.getProfitCurrentNo().equals(other.getProfitCurrentNo()))
                && (this.getBenchmarkInfo() == null ? other.getBenchmarkInfo() == null : this.getBenchmarkInfo().equals(other.getBenchmarkInfo()))
                && (this.getRateBenchmark() == null ? other.getRateBenchmark() == null : this.getRateBenchmark().equals(other.getRateBenchmark()))
                && (this.getCashFormGrantTdy() == null ? other.getCashFormGrantTdy() == null : this.getCashFormGrantTdy().equals(other.getCashFormGrantTdy()))
                && (this.getReinvestFormGrantTdy() == null ? other.getReinvestFormGrantTdy() == null : this.getReinvestFormGrantTdy().equals(other.getReinvestFormGrantTdy()))
                && (this.getRedemIntamtTdy() == null ? other.getRedemIntamtTdy() == null : this.getRedemIntamtTdy().equals(other.getRedemIntamtTdy()))
                && (this.getProfitChangeTdy() == null ? other.getProfitChangeTdy() == null : this.getProfitChangeTdy().equals(other.getProfitChangeTdy()))
                && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFprodId() == null) ? 0 : getFprodId().hashCode());
        result = prime * result + ((getProdId() == null) ? 0 : getProdId().hashCode());
        result = prime * result + ((getProdName() == null) ? 0 : getProdName().hashCode());
        result = prime * result + ((getValueDate() == null) ? 0 : getValueDate().hashCode());
        result = prime * result + ((getCapital() == null) ? 0 : getCapital().hashCode());
        result = prime * result + ((getAssetTotalMarket() == null) ? 0 : getAssetTotalMarket().hashCode());
        result = prime * result + ((getAssetValueCostFq() == null) ? 0 : getAssetValueCostFq().hashCode());
        result = prime * result + ((getAssetValueMarketFq() == null) ? 0 : getAssetValueMarketFq().hashCode());
        result = prime * result + ((getDeviation() == null) ? 0 : getDeviation().hashCode());
        result = prime * result + ((getUnitValue() == null) ? 0 : getUnitValue().hashCode());
        result = prime * result + ((getUnitValueT() == null) ? 0 : getUnitValueT().hashCode());
        result = prime * result + ((getProfitTdy() == null) ? 0 : getProfitTdy().hashCode());
        result = prime * result + ((getwYield() == null) ? 0 : getwYield().hashCode());
        result = prime * result + ((getYieldTdy() == null) ? 0 : getYieldTdy().hashCode());
        result = prime * result + ((getYield7() == null) ? 0 : getYield7().hashCode());
        result = prime * result + ((getProfitCurrentYes() == null) ? 0 : getProfitCurrentYes().hashCode());
        result = prime * result + ((getProfitCurrentNo() == null) ? 0 : getProfitCurrentNo().hashCode());
        result = prime * result + ((getBenchmarkInfo() == null) ? 0 : getBenchmarkInfo().hashCode());
        result = prime * result + ((getRateBenchmark() == null) ? 0 : getRateBenchmark().hashCode());
        result = prime * result + ((getCashFormGrantTdy() == null) ? 0 : getCashFormGrantTdy().hashCode());
        result = prime * result + ((getReinvestFormGrantTdy() == null) ? 0 : getReinvestFormGrantTdy().hashCode());
        result = prime * result + ((getRedemIntamtTdy() == null) ? 0 : getRedemIntamtTdy().hashCode());
        result = prime * result + ((getProfitChangeTdy() == null) ? 0 : getProfitChangeTdy().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fprodId=").append(fprodId);
        sb.append(", prodId=").append(prodId);
        sb.append(", prodName=").append(prodName);
        sb.append(", valueDate=").append(valueDate);
        sb.append(", capital=").append(capital);
        sb.append(", assetTotalMarket=").append(assetTotalMarket);
        sb.append(", assetValueCostFq=").append(assetValueCostFq);
        sb.append(", assetValueMarketFq=").append(assetValueMarketFq);
        sb.append(", deviation=").append(deviation);
        sb.append(", unitValue=").append(unitValue);
        sb.append(", unitValueT=").append(unitValueT);
        sb.append(", profitTdy=").append(profitTdy);
        sb.append(", wYield=").append(wYield);
        sb.append(", yieldTdy=").append(yieldTdy);
        sb.append(", yield7=").append(yield7);
        sb.append(", profitCurrentYes=").append(profitCurrentYes);
        sb.append(", profitCurrentNo=").append(profitCurrentNo);
        sb.append(", benchmarkInfo=").append(benchmarkInfo);
        sb.append(", rateBenchmark=").append(rateBenchmark);
        sb.append(", cashFormGrantTdy=").append(cashFormGrantTdy);
        sb.append(", reinvestFormGrantTdy=").append(reinvestFormGrantTdy);
        sb.append(", redemIntamtTdy=").append(redemIntamtTdy);
        sb.append(", profitChangeTdy=").append(profitChangeTdy);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}