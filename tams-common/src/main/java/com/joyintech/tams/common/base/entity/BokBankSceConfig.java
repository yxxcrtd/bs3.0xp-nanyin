package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokBankSceConfig extends BaseEntity {
    /**
     * 流水号
     */
    private String sqeNo;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 场景代码
     */
    private String tableId;

    /**
     * 计提区分
     */
    private String accureType;

    /**
     * 是否日终出账
     */
    private String dayendType;

    /**
     * 余额方向
     */
    private String ps;

    /**
     * 基础科目性质
     */
    private String baseNature;

    /**
     * 费用类型
     */
    private String feeType;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSqeNo() {
        return sqeNo;
    }

    /**
     * 设置 流水号
     * @param sqeNo 流水号
     */
    public void setSqeNo(String sqeNo) {
        this.sqeNo = sqeNo == null ? null : sqeNo.trim();
    }

    /**
     * 取得 账套代码
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
    }

    /**
     * 取得 场景代码
     * @return 场景代码
     */
    public String getTableId() {
        return tableId;
    }

    /**
     * 设置 场景代码
     * @param tableId 场景代码
     */
    public void setTableId(String tableId) {
        this.tableId = tableId == null ? null : tableId.trim();
    }

    /**
     * 取得 计提区分
     * @return 计提区分
     */
    public String getAccureType() {
        return accureType;
    }

    /**
     * 设置 计提区分
     * @param accureType 计提区分
     */
    public void setAccureType(String accureType) {
        this.accureType = accureType == null ? null : accureType.trim();
    }

    /**
     * 取得 是否日终出账
     * @return 是否日终出账
     */
    public String getDayendType() {
        return dayendType;
    }

    /**
     * 设置 是否日终出账
     * @param dayendType 是否日终出账
     */
    public void setDayendType(String dayendType) {
        this.dayendType = dayendType == null ? null : dayendType.trim();
    }

    /**
     * 取得 余额方向
     * @return 余额方向
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 余额方向
     * @param ps 余额方向
     */
    public void setPs(String ps) {
        this.ps = ps == null ? null : ps.trim();
    }

    /**
     * 取得 基础科目性质
     * @return 基础科目性质
     */
    public String getBaseNature() {
        return baseNature;
    }

    /**
     * 设置 基础科目性质
     * @param baseNature 基础科目性质
     */
    public void setBaseNature(String baseNature) {
        this.baseNature = baseNature == null ? null : baseNature.trim();
    }

    /**
     * 取得 费用类型
     * @return 费用类型
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * 设置 费用类型
     * @param feeType 费用类型
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType == null ? null : feeType.trim();
    }
}