package com.joyintech.tams.svreport.interf.dis.prodinvestadjust.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class DisRepProdInvestAdjust extends BaseEntity {
    /**
     * ID
     */
    private String id;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 投资范围
     */
    private String investRange;

    /**
     * 投资种类
     */
    private String investType;

    /**
     * 调整日期
     */
    private Date adjustDate;

    /**
     * 募集金额上限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal raiseMax;

    /**
     * 导入方法
     */
    private String inputType;

    /**
     * 导入用户
     */
    private String inputUser;

    /**
     * 适配器ID
     */
    private String adapterId;

    /**
     * 取得 ID
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 ID
     * @param id ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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
     * 取得 投资范围
     * @return 投资范围
     */
    public String getInvestRange() {
        return investRange;
    }

    /**
     * 设置 投资范围
     * @param investRange 投资范围
     */
    public void setInvestRange(String investRange) {
        this.investRange = investRange == null ? null : investRange.trim();
    }

    /**
     * 取得 投资种类
     * @return 投资种类
     */
    public String getInvestType() {
        return investType;
    }

    /**
     * 设置 投资种类
     * @param investType 投资种类
     */
    public void setInvestType(String investType) {
        this.investType = investType == null ? null : investType.trim();
    }

    /**
     * 取得 调整日期
     * @return 调整日期
     */
    public Date getAdjustDate() {
        return adjustDate;
    }

    /**
     * 设置 调整日期
     * @param adjustDate 调整日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setAdjustDate(Date adjustDate) {
        this.adjustDate = adjustDate;
    }

    /**
     * 取得 募集金额上限
     * @return 募集金额上限
     */
    public BigDecimal getRaiseMax() {
        return raiseMax;
    }

    /**
     * 设置 募集金额上限
     * @param raiseMax 募集金额上限
     */
    public void setRaiseMax(BigDecimal raiseMax) {
        this.raiseMax = raiseMax;
    }

    /**
     * 取得 导入方法
     * @return 导入方法
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置 导入方法
     * @param inputType 导入方法
     */
    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }

    /**
     * 取得 导入用户
     * @return 导入用户
     */
    public String getInputUser() {
        return inputUser;
    }

    /**
     * 设置 导入用户
     * @param inputUser 导入用户
     */
    public void setInputUser(String inputUser) {
        this.inputUser = inputUser == null ? null : inputUser.trim();
    }

    /**
     * 取得 适配器ID
     * @return 适配器ID
     */
    public String getAdapterId() {
        return adapterId;
    }

    /**
     * 设置 适配器ID
     * @param adapterId 适配器ID
     */
    public void setAdapterId(String adapterId) {
        this.adapterId = adapterId == null ? null : adapterId.trim();
    }
}