package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class InterfFMarketInfoKey extends BaseEntity {
    /**
     * 基金代码（市场代码）
     */
    private String fundCode;

    /**
     * 交易日
     */
    private Date valueDate;

    /**
     * 数据来源
     */
    private String source;

    /**
     * 取得 基金代码（市场代码）
     * @return 基金代码（市场代码）
     */
    public String getFundCode() {
        return fundCode;
    }

    /**
     * 设置 基金代码（市场代码）
     * @param fundCode 基金代码（市场代码）
     */
    public void setFundCode(String fundCode) {
        this.fundCode = fundCode == null ? null : fundCode.trim();
    }

    /**
     * 取得 交易日
     * @return 交易日
     */
    public Date getValueDate() {
        return valueDate;
    }

    /**
     * 设置 交易日
     * @param valueDate 交易日
     */
    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    /**
     * 取得 数据来源
     * @return 数据来源
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置 数据来源
     * @param source 数据来源
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }
}