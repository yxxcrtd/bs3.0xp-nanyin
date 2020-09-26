package com.joyintech.tams.valuation.common.interf.base.dto;

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
     * 产品总净
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal assetValue;

    /**
     * 单位净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netUnitValue;
    
    /**
     * 累计净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal taotalNetValue;
    
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
     * 取得 产品总净
     * @return 产品总净
     */
    public BigDecimal getAssetValue() {
        return assetValue;
    }

    /**
     * 设置 产品总净
     * @param assetValue 产品总净
     */
    public void setAssetValue(BigDecimal assetValue) {
        this.assetValue = assetValue;
    }

    /**
     * 取得 单位净值
     * @return 单位净值
     */
    public BigDecimal getNetUnitValue() {
        return netUnitValue;
    }

    /**
     * 设置 单位净值
     * @param netUnitValue 单位净值
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

    
    public BigDecimal getTaotalNetValue() {
        return taotalNetValue;
    }

    
    public void setTaotalNetValue(BigDecimal taotalNetValue) {
        this.taotalNetValue = taotalNetValue;
    }

    
    public BigDecimal getSevenDayRate() {
        return sevenDayRate;
    }

    
    public void setSevenDayRate(BigDecimal sevenDayRate) {
        this.sevenDayRate = sevenDayRate;
    }

    
    public BigDecimal getPayProfit() {
        return payProfit;
    }

    
    public void setPayProfit(BigDecimal payProfit) {
        this.payProfit = payProfit;
    }

    
    public String getFileId() {
        return fileId;
    }

    
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    
}