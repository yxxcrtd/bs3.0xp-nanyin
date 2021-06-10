package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.io.Serializable;
import java.math.BigDecimal;

public class HxProfit extends BaseEntity implements Serializable {
    /**
     * 序号
     */
    private Integer id;

    /**
     * 月份
     */
    private String crtMon;

    /**
     * 投资组合
     */
    private String pbcAssetscode;

    /**
     * 金额类别
     */
    private String accCode;

    /**
     * 利息收入
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal lixiInc;

    /**
     * 利息支出
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal lixiPay;

    /**
     * 投资类利息收入
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal investLixiInc;

    /**
     * 投资类利息支出
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal investLixiPay;

    /**
     * 金融机构往来利息收入
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal finaLixiInc;

    /**
     * 金融机构往来利息支出
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal finaLixiPay;

    /**
     * 手续费收入
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeLixiInc;

    /**
     * 投资收益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal investInc;

    /**
     * 公允价值变动收益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal changeInc;

    /**
     * 以前年度损益调整
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yearProfit;

    /**
     * 外汇收入
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal exchInc;

    /**
     * 理财产品收益支出
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prodPay;

    /**
     * 营业费用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal busiFee;

    /**
     * 资产减值损失
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal assetImp;

    /**
     * 营业利润
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal operProfit;

    /**
     * 备注
     */
    private String remark;

    /**
     * 业务日期
     */
    private String busiDate;

    private static final long serialVersionUID = 1L;

    public HxProfit(Integer id, String crtMon, String pbcAssetscode, String accCode, BigDecimal lixiInc, BigDecimal lixiPay, BigDecimal investLixiInc, BigDecimal investLixiPay, BigDecimal finaLixiInc, BigDecimal finaLixiPay, BigDecimal feeLixiInc, BigDecimal investInc, BigDecimal changeInc, BigDecimal yearProfit, BigDecimal exchInc, BigDecimal prodPay, BigDecimal busiFee, BigDecimal assetImp, BigDecimal operProfit, String remark, String busiDate) {
        this.id = id;
        this.crtMon = crtMon;
        this.pbcAssetscode = pbcAssetscode;
        this.accCode = accCode;
        this.lixiInc = lixiInc;
        this.lixiPay = lixiPay;
        this.investLixiInc = investLixiInc;
        this.investLixiPay = investLixiPay;
        this.finaLixiInc = finaLixiInc;
        this.finaLixiPay = finaLixiPay;
        this.feeLixiInc = feeLixiInc;
        this.investInc = investInc;
        this.changeInc = changeInc;
        this.yearProfit = yearProfit;
        this.exchInc = exchInc;
        this.prodPay = prodPay;
        this.busiFee = busiFee;
        this.assetImp = assetImp;
        this.operProfit = operProfit;
        this.remark = remark;
        this.busiDate = busiDate;
    }

    public HxProfit() {
        super();
    }

    /**
     * 获取 序号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置 序号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取 月份
     */
    public String getCrtMon() {
        return crtMon;
    }

    /**
     * 设置 月份
     */
    public void setCrtMon(String crtMon) {
        this.crtMon = crtMon == null ? null : crtMon.trim();
    }

    /**
     * 获取 投资组合
     */
    public String getPbcAssetscode() {
        return pbcAssetscode;
    }

    /**
     * 设置 投资组合
     */
    public void setPbcAssetscode(String pbcAssetscode) {
        this.pbcAssetscode = pbcAssetscode == null ? null : pbcAssetscode.trim();
    }

    /**
     * 获取 金额类别
     */
    public String getAccCode() {
        return accCode;
    }

    /**
     * 设置 金额类别
     */
    public void setAccCode(String accCode) {
        this.accCode = accCode == null ? null : accCode.trim();
    }

    /**
     * 获取 利息收入
     */
    public BigDecimal getLixiInc() {
        return lixiInc;
    }

    /**
     * 设置 利息收入
     */
    public void setLixiInc(BigDecimal lixiInc) {
        this.lixiInc = lixiInc;
    }

    /**
     * 获取 利息支出
     */
    public BigDecimal getLixiPay() {
        return lixiPay;
    }

    /**
     * 设置 利息支出
     */
    public void setLixiPay(BigDecimal lixiPay) {
        this.lixiPay = lixiPay;
    }

    /**
     * 获取 投资类利息收入
     */
    public BigDecimal getInvestLixiInc() {
        return investLixiInc;
    }

    /**
     * 设置 投资类利息收入
     */
    public void setInvestLixiInc(BigDecimal investLixiInc) {
        this.investLixiInc = investLixiInc;
    }

    /**
     * 获取 投资类利息支出
     */
    public BigDecimal getInvestLixiPay() {
        return investLixiPay;
    }

    /**
     * 设置 投资类利息支出
     */
    public void setInvestLixiPay(BigDecimal investLixiPay) {
        this.investLixiPay = investLixiPay;
    }

    /**
     * 获取 金融机构往来利息收入
     */
    public BigDecimal getFinaLixiInc() {
        return finaLixiInc;
    }

    /**
     * 设置 金融机构往来利息收入
     */
    public void setFinaLixiInc(BigDecimal finaLixiInc) {
        this.finaLixiInc = finaLixiInc;
    }

    /**
     * 获取 金融机构往来利息支出
     */
    public BigDecimal getFinaLixiPay() {
        return finaLixiPay;
    }

    /**
     * 设置 金融机构往来利息支出
     */
    public void setFinaLixiPay(BigDecimal finaLixiPay) {
        this.finaLixiPay = finaLixiPay;
    }

    /**
     * 获取 手续费收入
     */
    public BigDecimal getFeeLixiInc() {
        return feeLixiInc;
    }

    /**
     * 设置 手续费收入
     */
    public void setFeeLixiInc(BigDecimal feeLixiInc) {
        this.feeLixiInc = feeLixiInc;
    }

    /**
     * 获取 投资收益
     */
    public BigDecimal getInvestInc() {
        return investInc;
    }

    /**
     * 设置 投资收益
     */
    public void setInvestInc(BigDecimal investInc) {
        this.investInc = investInc;
    }

    /**
     * 获取 公允价值变动收益
     */
    public BigDecimal getChangeInc() {
        return changeInc;
    }

    /**
     * 设置 公允价值变动收益
     */
    public void setChangeInc(BigDecimal changeInc) {
        this.changeInc = changeInc;
    }

    /**
     * 获取 以前年度损益调整
     */
    public BigDecimal getYearProfit() {
        return yearProfit;
    }

    /**
     * 设置 以前年度损益调整
     */
    public void setYearProfit(BigDecimal yearProfit) {
        this.yearProfit = yearProfit;
    }

    /**
     * 获取 外汇收入
     */
    public BigDecimal getExchInc() {
        return exchInc;
    }

    /**
     * 设置 外汇收入
     */
    public void setExchInc(BigDecimal exchInc) {
        this.exchInc = exchInc;
    }

    /**
     * 获取 理财产品收益支出
     */
    public BigDecimal getProdPay() {
        return prodPay;
    }

    /**
     * 设置 理财产品收益支出
     */
    public void setProdPay(BigDecimal prodPay) {
        this.prodPay = prodPay;
    }

    /**
     * 获取 营业费用
     */
    public BigDecimal getBusiFee() {
        return busiFee;
    }

    /**
     * 设置 营业费用
     */
    public void setBusiFee(BigDecimal busiFee) {
        this.busiFee = busiFee;
    }

    /**
     * 获取 资产减值损失
     */
    public BigDecimal getAssetImp() {
        return assetImp;
    }

    /**
     * 设置 资产减值损失
     */
    public void setAssetImp(BigDecimal assetImp) {
        this.assetImp = assetImp;
    }

    /**
     * 获取 营业利润
     */
    public BigDecimal getOperProfit() {
        return operProfit;
    }

    /**
     * 设置 营业利润
     */
    public void setOperProfit(BigDecimal operProfit) {
        this.operProfit = operProfit;
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
     * 获取 业务日期
     */
    public String getBusiDate() {
        return busiDate;
    }

    /**
     * 设置 业务日期
     */
    public void setBusiDate(String busiDate) {
        this.busiDate = busiDate == null ? null : busiDate.trim();
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
        HxProfit other = (HxProfit) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCrtMon() == null ? other.getCrtMon() == null : this.getCrtMon().equals(other.getCrtMon()))
            && (this.getPbcAssetscode() == null ? other.getPbcAssetscode() == null : this.getPbcAssetscode().equals(other.getPbcAssetscode()))
            && (this.getAccCode() == null ? other.getAccCode() == null : this.getAccCode().equals(other.getAccCode()))
            && (this.getLixiInc() == null ? other.getLixiInc() == null : this.getLixiInc().equals(other.getLixiInc()))
            && (this.getLixiPay() == null ? other.getLixiPay() == null : this.getLixiPay().equals(other.getLixiPay()))
            && (this.getInvestLixiInc() == null ? other.getInvestLixiInc() == null : this.getInvestLixiInc().equals(other.getInvestLixiInc()))
            && (this.getInvestLixiPay() == null ? other.getInvestLixiPay() == null : this.getInvestLixiPay().equals(other.getInvestLixiPay()))
            && (this.getFinaLixiInc() == null ? other.getFinaLixiInc() == null : this.getFinaLixiInc().equals(other.getFinaLixiInc()))
            && (this.getFinaLixiPay() == null ? other.getFinaLixiPay() == null : this.getFinaLixiPay().equals(other.getFinaLixiPay()))
            && (this.getFeeLixiInc() == null ? other.getFeeLixiInc() == null : this.getFeeLixiInc().equals(other.getFeeLixiInc()))
            && (this.getInvestInc() == null ? other.getInvestInc() == null : this.getInvestInc().equals(other.getInvestInc()))
            && (this.getChangeInc() == null ? other.getChangeInc() == null : this.getChangeInc().equals(other.getChangeInc()))
            && (this.getYearProfit() == null ? other.getYearProfit() == null : this.getYearProfit().equals(other.getYearProfit()))
            && (this.getExchInc() == null ? other.getExchInc() == null : this.getExchInc().equals(other.getExchInc()))
            && (this.getProdPay() == null ? other.getProdPay() == null : this.getProdPay().equals(other.getProdPay()))
            && (this.getBusiFee() == null ? other.getBusiFee() == null : this.getBusiFee().equals(other.getBusiFee()))
            && (this.getAssetImp() == null ? other.getAssetImp() == null : this.getAssetImp().equals(other.getAssetImp()))
            && (this.getOperProfit() == null ? other.getOperProfit() == null : this.getOperProfit().equals(other.getOperProfit()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getBusiDate() == null ? other.getBusiDate() == null : this.getBusiDate().equals(other.getBusiDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCrtMon() == null) ? 0 : getCrtMon().hashCode());
        result = prime * result + ((getPbcAssetscode() == null) ? 0 : getPbcAssetscode().hashCode());
        result = prime * result + ((getAccCode() == null) ? 0 : getAccCode().hashCode());
        result = prime * result + ((getLixiInc() == null) ? 0 : getLixiInc().hashCode());
        result = prime * result + ((getLixiPay() == null) ? 0 : getLixiPay().hashCode());
        result = prime * result + ((getInvestLixiInc() == null) ? 0 : getInvestLixiInc().hashCode());
        result = prime * result + ((getInvestLixiPay() == null) ? 0 : getInvestLixiPay().hashCode());
        result = prime * result + ((getFinaLixiInc() == null) ? 0 : getFinaLixiInc().hashCode());
        result = prime * result + ((getFinaLixiPay() == null) ? 0 : getFinaLixiPay().hashCode());
        result = prime * result + ((getFeeLixiInc() == null) ? 0 : getFeeLixiInc().hashCode());
        result = prime * result + ((getInvestInc() == null) ? 0 : getInvestInc().hashCode());
        result = prime * result + ((getChangeInc() == null) ? 0 : getChangeInc().hashCode());
        result = prime * result + ((getYearProfit() == null) ? 0 : getYearProfit().hashCode());
        result = prime * result + ((getExchInc() == null) ? 0 : getExchInc().hashCode());
        result = prime * result + ((getProdPay() == null) ? 0 : getProdPay().hashCode());
        result = prime * result + ((getBusiFee() == null) ? 0 : getBusiFee().hashCode());
        result = prime * result + ((getAssetImp() == null) ? 0 : getAssetImp().hashCode());
        result = prime * result + ((getOperProfit() == null) ? 0 : getOperProfit().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getBusiDate() == null) ? 0 : getBusiDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", crtMon=").append(crtMon);
        sb.append(", pbcAssetscode=").append(pbcAssetscode);
        sb.append(", accCode=").append(accCode);
        sb.append(", lixiInc=").append(lixiInc);
        sb.append(", lixiPay=").append(lixiPay);
        sb.append(", investLixiInc=").append(investLixiInc);
        sb.append(", investLixiPay=").append(investLixiPay);
        sb.append(", finaLixiInc=").append(finaLixiInc);
        sb.append(", finaLixiPay=").append(finaLixiPay);
        sb.append(", feeLixiInc=").append(feeLixiInc);
        sb.append(", investInc=").append(investInc);
        sb.append(", changeInc=").append(changeInc);
        sb.append(", yearProfit=").append(yearProfit);
        sb.append(", exchInc=").append(exchInc);
        sb.append(", prodPay=").append(prodPay);
        sb.append(", busiFee=").append(busiFee);
        sb.append(", assetImp=").append(assetImp);
        sb.append(", operProfit=").append(operProfit);
        sb.append(", remark=").append(remark);
        sb.append(", busiDate=").append(busiDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}