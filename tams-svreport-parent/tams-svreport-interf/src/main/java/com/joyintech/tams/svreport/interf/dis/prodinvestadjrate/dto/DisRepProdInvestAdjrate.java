package com.joyintech.tams.svreport.interf.dis.prodinvestadjrate.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class DisRepProdInvestAdjrate extends BaseEntity {
    /**
     * ID
     */
    private String id;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 投资种类
     */
    private String investType;

    /**
     * 投资比例
     */
    private String rate;

    /**
     * 投资调整日期
     */
    private Date investAdjustDate;

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
     * 取得 投资比例
     * @return 投资比例
     */
    public String getRate() {
        return rate;
    }

    /**
     * 设置 投资比例
     * @param rate 投资比例
     */
    public void setRate(String rate) {
        this.rate = rate == null ? null : rate.trim();
    }

    /**
     * 取得 投资调整日期
     * @return 投资调整日期
     */
    public Date getInvestAdjustDate() {
        return investAdjustDate;
    }

    /**
     * 设置 投资调整日期
     * @param investAdjustDate 投资调整日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setInvestAdjustDate(Date investAdjustDate) {
        this.investAdjustDate = investAdjustDate;
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