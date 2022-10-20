package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class DisBonusList extends BaseEntity {
    /**
     * 分红计算清单编号
     */
    private String bonusListId;

    /**
     * 产品ID
     */
    private String prodId;

    /**
     * 产品登记编码
     */
    private String prodRegistNo;

    /**
     * 产品资产净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prodAssets;

    /**
     * 产品期末余额(份额)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prodEndBalance;

    /**
     * 单位净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitNum;

    /**
     * 上一收益分配基准日
     */
    private Date lastProfitBaseDate;

    /**
     * 本期收益分配基准日
     */
    private Date profitBaseDate;

    /**
     * 运行天数
     */
    private Short runDays;

    /**
     * 可供分配利润
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal avaiProfit;

    /**
     * 业绩比较基准
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal perBase;

    /**
     * 业绩比较基准收益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal perBaseProfit;

    /**
     * 超出部分
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal exceed;

    /**
     * 业绩报酬
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal perFee;

    /**
     * 应分配分红金额
     */
    private BigDecimal disBonusMoney;

    /**
     * 单位分红
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitBonus;

    /**
     * 收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 数据状态：数据字典，系统模块-DIS，字典code：DIS_DATA_STATUS:01-有效，02-删除
     */
    private String dataStatus;

    /**
     * 数据来源:数据字典syscode：DIS,code:DATA_SOURCE,
     */
    private String dataSource;

    /**
     * 取得 分红计算清单编号
     * @return 分红计算清单编号
     */
    public String getBonusListId() {
        return bonusListId;
    }

    /**
     * 设置 分红计算清单编号
     * @param bonusListId 分红计算清单编号
     */
    public void setBonusListId(String bonusListId) {
        this.bonusListId = bonusListId == null ? null : bonusListId.trim();
    }

    /**
     * 取得 产品ID
     * @return 产品ID
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品ID
     * @param prodId 产品ID
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 产品登记编码
     * @return 产品登记编码
     */
    public String getProdRegistNo() {
        return prodRegistNo;
    }

    /**
     * 设置 产品登记编码
     * @param prodRegistNo 产品登记编码
     */
    public void setProdRegistNo(String prodRegistNo) {
        this.prodRegistNo = prodRegistNo == null ? null : prodRegistNo.trim();
    }

    /**
     * 取得 产品资产净值
     * @return 产品资产净值
     */
    public BigDecimal getProdAssets() {
        return prodAssets;
    }

    /**
     * 设置 产品资产净值
     * @param prodAssets 产品资产净值
     */
    public void setProdAssets(BigDecimal prodAssets) {
        this.prodAssets = prodAssets;
    }

    /**
     * 取得 产品期末余额(份额)
     * @return 产品期末余额(份额)
     */
    public BigDecimal getProdEndBalance() {
        return prodEndBalance;
    }

    /**
     * 设置 产品期末余额(份额)
     * @param prodEndBalance 产品期末余额(份额)
     */
    public void setProdEndBalance(BigDecimal prodEndBalance) {
        this.prodEndBalance = prodEndBalance;
    }

    /**
     * 取得 单位净值
     * @return 单位净值
     */
    public BigDecimal getUnitNum() {
        return unitNum;
    }

    /**
     * 设置 单位净值
     * @param unitNum 单位净值
     */
    public void setUnitNum(BigDecimal unitNum) {
        this.unitNum = unitNum;
    }

    /**
     * 取得 上一收益分配基准日
     * @return 上一收益分配基准日
     */
    public Date getLastProfitBaseDate() {
        return lastProfitBaseDate;
    }

    /**
     * 设置 上一收益分配基准日
     * @param lastProfitBaseDate 上一收益分配基准日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setLastProfitBaseDate(Date lastProfitBaseDate) {
        this.lastProfitBaseDate = lastProfitBaseDate;
    }

    /**
     * 取得 本期收益分配基准日
     * @return 本期收益分配基准日
     */
    public Date getProfitBaseDate() {
        return profitBaseDate;
    }

    /**
     * 设置 本期收益分配基准日
     * @param profitBaseDate 本期收益分配基准日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setProfitBaseDate(Date profitBaseDate) {
        this.profitBaseDate = profitBaseDate;
    }

    /**
     * 取得 运行天数
     * @return 运行天数
     */
    public Short getRunDays() {
        return runDays;
    }

    /**
     * 设置 运行天数
     * @param runDays 运行天数
     */
    public void setRunDays(Short runDays) {
        this.runDays = runDays;
    }

    /**
     * 取得 可供分配利润
     * @return 可供分配利润
     */
    public BigDecimal getAvaiProfit() {
        return avaiProfit;
    }

    /**
     * 设置 可供分配利润
     * @param avaiProfit 可供分配利润
     */
    public void setAvaiProfit(BigDecimal avaiProfit) {
        this.avaiProfit = avaiProfit;
    }

    /**
     * 取得 业绩比较基准
     * @return 业绩比较基准
     */
    public BigDecimal getPerBase() {
        return perBase;
    }

    /**
     * 设置 业绩比较基准
     * @param perBase 业绩比较基准
     */
    public void setPerBase(BigDecimal perBase) {
        this.perBase = perBase;
    }

    /**
     * 取得 业绩比较基准收益
     * @return 业绩比较基准收益
     */
    public BigDecimal getPerBaseProfit() {
        return perBaseProfit;
    }

    /**
     * 设置 业绩比较基准收益
     * @param perBaseProfit 业绩比较基准收益
     */
    public void setPerBaseProfit(BigDecimal perBaseProfit) {
        this.perBaseProfit = perBaseProfit;
    }

    /**
     * 取得 超出部分
     * @return 超出部分
     */
    public BigDecimal getExceed() {
        return exceed;
    }

    /**
     * 设置 超出部分
     * @param exceed 超出部分
     */
    public void setExceed(BigDecimal exceed) {
        this.exceed = exceed;
    }

    /**
     * 取得 业绩报酬
     * @return 业绩报酬
     */
    public BigDecimal getPerFee() {
        return perFee;
    }

    /**
     * 设置 业绩报酬
     * @param perFee 业绩报酬
     */
    public void setPerFee(BigDecimal perFee) {
        this.perFee = perFee;
    }

    /**
     * 取得 应分配分红金额
     * @return 应分配分红金额
     */
    public BigDecimal getDisBonusMoney() {
        return disBonusMoney;
    }

    /**
     * 设置 应分配分红金额
     * @param disBonusMoney 应分配分红金额
     */
    public void setDisBonusMoney(BigDecimal disBonusMoney) {
        this.disBonusMoney = disBonusMoney;
    }

    /**
     * 取得 单位分红
     * @return 单位分红
     */
    public BigDecimal getUnitBonus() {
        return unitBonus;
    }

    /**
     * 设置 单位分红
     * @param unitBonus 单位分红
     */
    public void setUnitBonus(BigDecimal unitBonus) {
        this.unitBonus = unitBonus;
    }

    /**
     * 取得 收益率
     * @return 收益率
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 收益率
     * @param yield 收益率
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 取得 错误信息
     * @return 错误信息
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置 错误信息
     * @param msg 错误信息
     */
    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    /**
     * 取得 数据状态：数据字典，系统模块-DIS，字典code：DIS_DATA_STATUS:01-有效，02-删除
     * @return 数据状态：数据字典，系统模块-DIS，字典code：DIS_DATA_STATUS:01-有效，02-删除
     */
    public String getDataStatus() {
        return dataStatus;
    }

    /**
     * 设置 数据状态：数据字典，系统模块-DIS，字典code：DIS_DATA_STATUS:01-有效，02-删除
     * @param dataStatus 数据状态：数据字典，系统模块-DIS，字典code：DIS_DATA_STATUS:01-有效，02-删除
     */
    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus == null ? null : dataStatus.trim();
    }

    /**
     * 取得 数据来源:数据字典syscode：DIS,code:DATA_SOURCE,
     * @return 数据来源:数据字典syscode：DIS,code:DATA_SOURCE,
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 设置 数据来源:数据字典syscode：DIS,code:DATA_SOURCE,
     * @param dataSource 数据来源:数据字典syscode：DIS,code:DATA_SOURCE,
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }
}