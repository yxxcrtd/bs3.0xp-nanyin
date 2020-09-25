package com.joyintech.tams.common.base.entity;

public class BokDealAccountingDetail extends BokDealAccountingDetailKey {
    /**
     * 业务类型，债券交易、债券存续交易、基金交易、收划款等
     */
    private String busiType;

    /**
     * 账务明细代码
     */
    private String bokDetailId;

    /**
     * 取得 业务类型，债券交易、债券存续交易、基金交易、收划款等
     * @return 业务类型，债券交易、债券存续交易、基金交易、收划款等
     */
    public String getBusiType() {
        return busiType;
    }

    /**
     * 设置 业务类型，债券交易、债券存续交易、基金交易、收划款等
     * @param busiType 业务类型，债券交易、债券存续交易、基金交易、收划款等
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType == null ? null : busiType.trim();
    }

    /**
     * 取得 账务明细代码
     * @return 账务明细代码
     */
    public String getBokDetailId() {
        return bokDetailId;
    }

    /**
     * 设置 账务明细代码
     * @param bokDetailId 账务明细代码
     */
    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId == null ? null : bokDetailId.trim();
    }
}