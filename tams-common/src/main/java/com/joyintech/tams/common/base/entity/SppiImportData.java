package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class SppiImportData extends BaseEntity {
    /**
     * 债券代码
     */
    private String bondId;

    /**
     * 债券简称
     */
    private String bondAbbr;

    /**
     * 是否通过SPPI测试
     */
    private String isSppiPass;

    /**
     * 未通过原因
     */
    private String reason;

    /**
     * 使权不重大的购入价条件
     */
    private String bidPriceCon;

    /**
     * 权的价值
     */
    private String costValue;

    /**
     * 其他条件
     */
    private String otherCondition;

    /**
     * 导入时间
     */
    private Date importDate;

    /**
     * 取得 债券代码
     * @return 债券代码
     */
    public String getBondId() {
        return bondId;
    }

    /**
     * 设置 债券代码
     * @param bondId 债券代码
     */
    public void setBondId(String bondId) {
        this.bondId = bondId == null ? null : bondId.trim();
    }

    /**
     * 取得 债券简称
     * @return 债券简称
     */
    public String getBondAbbr() {
        return bondAbbr;
    }

    /**
     * 设置 债券简称
     * @param bondAbbr 债券简称
     */
    public void setBondAbbr(String bondAbbr) {
        this.bondAbbr = bondAbbr == null ? null : bondAbbr.trim();
    }

    /**
     * 取得 是否通过SPPI测试
     * @return 是否通过SPPI测试
     */
    public String getIsSppiPass() {
        return isSppiPass;
    }

    /**
     * 设置 是否通过SPPI测试
     * @param isSppiPass 是否通过SPPI测试
     */
    public void setIsSppiPass(String isSppiPass) {
        this.isSppiPass = isSppiPass == null ? null : isSppiPass.trim();
    }

    /**
     * 取得 未通过原因
     * @return 未通过原因
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置 未通过原因
     * @param reason 未通过原因
     */
    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    /**
     * 取得 使权不重大的购入价条件
     * @return 使权不重大的购入价条件
     */
    public String getBidPriceCon() {
        return bidPriceCon;
    }

    /**
     * 设置 使权不重大的购入价条件
     * @param bidPriceCon 使权不重大的购入价条件
     */
    public void setBidPriceCon(String bidPriceCon) {
        this.bidPriceCon = bidPriceCon == null ? null : bidPriceCon.trim();
    }

    /**
     * 取得 权的价值
     * @return 权的价值
     */
    public String getCostValue() {
        return costValue;
    }

    /**
     * 设置 权的价值
     * @param costValue 权的价值
     */
    public void setCostValue(String costValue) {
        this.costValue = costValue == null ? null : costValue.trim();
    }

    /**
     * 取得 其他条件
     * @return 其他条件
     */
    public String getOtherCondition() {
        return otherCondition;
    }

    /**
     * 设置 其他条件
     * @param otherCondition 其他条件
     */
    public void setOtherCondition(String otherCondition) {
        this.otherCondition = otherCondition == null ? null : otherCondition.trim();
    }

    /**
     * 取得 导入时间
     * @return 导入时间
     */
    public Date getImportDate() {
        return importDate;
    }

    /**
     * 设置 导入时间
     * @param importDate 导入时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }
}