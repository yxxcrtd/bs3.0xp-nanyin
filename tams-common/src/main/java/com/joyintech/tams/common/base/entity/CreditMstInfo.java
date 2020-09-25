package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class CreditMstInfo extends BaseEntity {
    /**
     * 授信配置代码
     */
    private String seqno;

    /**
     * 授信配置类型（机构授信、资产授信、资产系列授信、机构+额度品种授信）
     */
    private String creditInfoType;

    /**
     * 授信额度品种字典代码
     */
    private String creditQuoteTypeCode;

    /**
     * 授信额度品种（金融债、二级资本债、同业存单、优先股、国际机构债、政府支持机构债券、资产支持证券、可转债、可交换债、公开发行、非公开发行、企业债、公司债、中期票据、短期融资券、定向工具）
     */
    private String creditQuoteTypeValue;

    /**
     * 授权额度品种层级
     */
    private String creditQuoteRank;

    /**
     * 取得 授信配置代码
     * @return 授信配置代码
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 授信配置代码
     * @param seqno 授信配置代码
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 授信配置类型（机构授信、资产授信、资产系列授信、机构+额度品种授信）
     * @return 授信配置类型（机构授信、资产授信、资产系列授信、机构+额度品种授信）
     */
    public String getCreditInfoType() {
        return creditInfoType;
    }

    /**
     * 设置 授信配置类型（机构授信、资产授信、资产系列授信、机构+额度品种授信）
     * @param creditInfoType 授信配置类型（机构授信、资产授信、资产系列授信、机构+额度品种授信）
     */
    public void setCreditInfoType(String creditInfoType) {
        this.creditInfoType = creditInfoType == null ? null : creditInfoType.trim();
    }

    /**
     * 取得 授信额度品种字典代码
     * @return 授信额度品种字典代码
     */
    public String getCreditQuoteTypeCode() {
        return creditQuoteTypeCode;
    }

    /**
     * 设置 授信额度品种字典代码
     * @param creditQuoteTypeCode 授信额度品种字典代码
     */
    public void setCreditQuoteTypeCode(String creditQuoteTypeCode) {
        this.creditQuoteTypeCode = creditQuoteTypeCode == null ? null : creditQuoteTypeCode.trim();
    }

    /**
     * 取得 授信额度品种（金融债、二级资本债、同业存单、优先股、国际机构债、政府支持机构债券、资产支持证券、可转债、可交换债、公开发行、非公开发行、企业债、公司债、中期票据、短期融资券、定向工具）
     * @return 授信额度品种（金融债、二级资本债、同业存单、优先股、国际机构债、政府支持机构债券、资产支持证券、可转债、可交换债、公开发行、非公开发行、企业债、公司债、中期票据、短期融资券、定向工具）
     */
    public String getCreditQuoteTypeValue() {
        return creditQuoteTypeValue;
    }

    /**
     * 设置 授信额度品种（金融债、二级资本债、同业存单、优先股、国际机构债、政府支持机构债券、资产支持证券、可转债、可交换债、公开发行、非公开发行、企业债、公司债、中期票据、短期融资券、定向工具）
     * @param creditQuoteTypeValue 授信额度品种（金融债、二级资本债、同业存单、优先股、国际机构债、政府支持机构债券、资产支持证券、可转债、可交换债、公开发行、非公开发行、企业债、公司债、中期票据、短期融资券、定向工具）
     */
    public void setCreditQuoteTypeValue(String creditQuoteTypeValue) {
        this.creditQuoteTypeValue = creditQuoteTypeValue == null ? null : creditQuoteTypeValue.trim();
    }

    /**
     * 取得 授权额度品种层级
     * @return 授权额度品种层级
     */
    public String getCreditQuoteRank() {
        return creditQuoteRank;
    }

    /**
     * 设置 授权额度品种层级
     * @param creditQuoteRank 授权额度品种层级
     */
    public void setCreditQuoteRank(String creditQuoteRank) {
        this.creditQuoteRank = creditQuoteRank == null ? null : creditQuoteRank.trim();
    }
}