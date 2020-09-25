package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class BokValbalTableStatus extends BokValbalTableStatusKey {
    /**
     * 产品名称
     */
    private String finprodName;

    /**
     * 理财份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal capital;

    /**
     * 产品总净值/本日收益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal assetValue;

    /**
     * 累计净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totalNetValue;

    /**
     * 七日年化收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sevenDayRate;

    /**
     * 应付利润
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal payProfit;

    /**
     * 附件uuid
     */
    private String fileId;

    /**
     * 单位净值/万份收益/利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netUnitValue;

    /**
     * 对账状态
     */
    private String checkStatus;

    /**
     * 对账结果
     */
    private String checkResult;

    /**
     * 确认状态
     */
    private String confirmStatus;

    /**
     * 下发状态
     */
    private String noticeStatus;

    /**
     * 当日年化收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyYield;

    /**
     * 取得 产品名称
     * @return 产品名称
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 产品名称
     * @param finprodName 产品名称
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName == null ? null : finprodName.trim();
    }

    /**
     * 取得 理财份额
     * @return 理财份额
     */
    public BigDecimal getCapital() {
        return capital;
    }

    /**
     * 设置 理财份额
     * @param capital 理财份额
     */
    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    /**
     * 取得 产品总净值/本日收益
     * @return 产品总净值/本日收益
     */
    public BigDecimal getAssetValue() {
        return assetValue;
    }

    /**
     * 设置 产品总净值/本日收益
     * @param assetValue 产品总净值/本日收益
     */
    public void setAssetValue(BigDecimal assetValue) {
        this.assetValue = assetValue;
    }

    /**
     * 取得 累计净值
     * @return 累计净值
     */
    public BigDecimal getTotalNetValue() {
        return totalNetValue;
    }

    /**
     * 设置 累计净值
     * @param totalNetValue 累计净值
     */
    public void setTotalNetValue(BigDecimal totalNetValue) {
        this.totalNetValue = totalNetValue;
    }

    /**
     * 取得 七日年化收益率
     * @return 七日年化收益率
     */
    public BigDecimal getSevenDayRate() {
        return sevenDayRate;
    }

    /**
     * 设置 七日年化收益率
     * @param sevenDayRate 七日年化收益率
     */
    public void setSevenDayRate(BigDecimal sevenDayRate) {
        this.sevenDayRate = sevenDayRate;
    }

    /**
     * 取得 应付利润
     * @return 应付利润
     */
    public BigDecimal getPayProfit() {
        return payProfit;
    }

    /**
     * 设置 应付利润
     * @param payProfit 应付利润
     */
    public void setPayProfit(BigDecimal payProfit) {
        this.payProfit = payProfit;
    }

    /**
     * 取得 附件uuid
     * @return 附件uuid
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * 设置 附件uuid
     * @param fileId 附件uuid
     */
    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    /**
     * 取得 单位净值/万份收益/利率
     * @return 单位净值/万份收益/利率
     */
    public BigDecimal getNetUnitValue() {
        return netUnitValue;
    }

    /**
     * 设置 单位净值/万份收益/利率
     * @param netUnitValue 单位净值/万份收益/利率
     */
    public void setNetUnitValue(BigDecimal netUnitValue) {
        this.netUnitValue = netUnitValue;
    }

    /**
     * 取得 对账状态
     * @return 对账状态
     */
    public String getCheckStatus() {
        return checkStatus;
    }

    /**
     * 设置 对账状态
     * @param checkStatus 对账状态
     */
    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus == null ? null : checkStatus.trim();
    }

    /**
     * 取得 对账结果
     * @return 对账结果
     */
    public String getCheckResult() {
        return checkResult;
    }

    /**
     * 设置 对账结果
     * @param checkResult 对账结果
     */
    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult == null ? null : checkResult.trim();
    }

    /**
     * 取得 确认状态
     * @return 确认状态
     */
    public String getConfirmStatus() {
        return confirmStatus;
    }

    /**
     * 设置 确认状态
     * @param confirmStatus 确认状态
     */
    public void setConfirmStatus(String confirmStatus) {
        this.confirmStatus = confirmStatus == null ? null : confirmStatus.trim();
    }

    /**
     * 取得 下发状态
     * @return 下发状态
     */
    public String getNoticeStatus() {
        return noticeStatus;
    }

    /**
     * 设置 下发状态
     * @param noticeStatus 下发状态
     */
    public void setNoticeStatus(String noticeStatus) {
        this.noticeStatus = noticeStatus == null ? null : noticeStatus.trim();
    }

    /**
     * 取得 当日年化收益率
     * @return 当日年化收益率
     */
    public BigDecimal getTdyYield() {
        return tdyYield;
    }

    /**
     * 设置 当日年化收益率
     * @param tdyYield 当日年化收益率
     */
    public void setTdyYield(BigDecimal tdyYield) {
        this.tdyYield = tdyYield;
    }
}