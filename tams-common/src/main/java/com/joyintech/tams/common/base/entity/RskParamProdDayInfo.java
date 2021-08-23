package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class RskParamProdDayInfo extends RskParamProdDayInfoKey {
    /**
     * 是否发生巨额赎回（Y、N）
     */
    private String isLargeRed;

    /**
     * 连续3个交易日累计赎回20%以上（Y、N）
     */
    private String isCumRedTridUpTwen;

    /**
     * 连续5个交易日累计赎回30%以上（Y、N）
     */
    private String isCumRedFivedUpThir;

    /**
     * 前10投资者持有份额合计产品总份额占比
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal topteninvSharesumPercent;

    /**
     * 取得 是否发生巨额赎回（Y、N）
     * @return 是否发生巨额赎回（Y、N）
     */
    public String getIsLargeRed() {
        return isLargeRed;
    }

    /**
     * 设置 是否发生巨额赎回（Y、N）
     * @param isLargeRed 是否发生巨额赎回（Y、N）
     */
    public void setIsLargeRed(String isLargeRed) {
        this.isLargeRed = isLargeRed == null ? null : isLargeRed.trim();
    }

    /**
     * 取得 连续3个交易日累计赎回20%以上（Y、N）
     * @return 连续3个交易日累计赎回20%以上（Y、N）
     */
    public String getIsCumRedTridUpTwen() {
        return isCumRedTridUpTwen;
    }

    /**
     * 设置 连续3个交易日累计赎回20%以上（Y、N）
     * @param isCumRedTridUpTwen 连续3个交易日累计赎回20%以上（Y、N）
     */
    public void setIsCumRedTridUpTwen(String isCumRedTridUpTwen) {
        this.isCumRedTridUpTwen = isCumRedTridUpTwen == null ? null : isCumRedTridUpTwen.trim();
    }

    /**
     * 取得 连续5个交易日累计赎回30%以上（Y、N）
     * @return 连续5个交易日累计赎回30%以上（Y、N）
     */
    public String getIsCumRedFivedUpThir() {
        return isCumRedFivedUpThir;
    }

    /**
     * 设置 连续5个交易日累计赎回30%以上（Y、N）
     * @param isCumRedFivedUpThir 连续5个交易日累计赎回30%以上（Y、N）
     */
    public void setIsCumRedFivedUpThir(String isCumRedFivedUpThir) {
        this.isCumRedFivedUpThir = isCumRedFivedUpThir == null ? null : isCumRedFivedUpThir.trim();
    }

    /**
     * 取得 前10投资者持有份额合计产品总份额占比
     * @return 前10投资者持有份额合计产品总份额占比
     */
    public BigDecimal getTopteninvSharesumPercent() {
        return topteninvSharesumPercent;
    }

    /**
     * 设置 前10投资者持有份额合计产品总份额占比
     * @param topteninvSharesumPercent 前10投资者持有份额合计产品总份额占比
     */
    public void setTopteninvSharesumPercent(BigDecimal topteninvSharesumPercent) {
        this.topteninvSharesumPercent = topteninvSharesumPercent;
    }
}