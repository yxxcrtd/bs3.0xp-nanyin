package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class RepProdBasicChange extends BaseEntity {
    /**
     * 业绩基准变更表主键id
     */
    private String basicChangeId;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 行情代码
     */
    private String quotaCode;

    /**
     * 系数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ratio;

    /**
     * 数据状态：数据字典，系统模块-DIS，字典code：DIS_DATA_STATUS:01-有效，02-删除
     */
    private String dataStatus;

    /**
     * 数据来源:数据字典syscode：DIS,code:DATA_SOURCE,
     */
    private String dataSource;

    /**
     * 取得 业绩基准变更表主键id
     * @return 业绩基准变更表主键id
     */
    public String getBasicChangeId() {
        return basicChangeId;
    }

    /**
     * 设置 业绩基准变更表主键id
     * @param basicChangeId 业绩基准变更表主键id
     */
    public void setBasicChangeId(String basicChangeId) {
        this.basicChangeId = basicChangeId == null ? null : basicChangeId.trim();
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
     * 取得 行情代码
     * @return 行情代码
     */
    public String getQuotaCode() {
        return quotaCode;
    }

    /**
     * 设置 行情代码
     * @param quotaCode 行情代码
     */
    public void setQuotaCode(String quotaCode) {
        this.quotaCode = quotaCode == null ? null : quotaCode.trim();
    }

    /**
     * 取得 系数
     * @return 系数
     */
    public BigDecimal getRatio() {
        return ratio;
    }

    /**
     * 设置 系数
     * @param ratio 系数
     */
    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
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