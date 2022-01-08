package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RepProdBonusRegister extends BaseEntity {
    /**
     * ID
     */
    private String id;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 分红登记日
     */
    private Date bonusRegisterDate;

    /**
     * 分红下发日
     */
    private Date bonusDealtDate;

    /**
     * 分红登记日单位净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bonusRegisterUnitValue;

    /**
     * 单位分红金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitBonusAmt;

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
     * 取得 分红登记日
     * @return 分红登记日
     */
    public Date getBonusRegisterDate() {
        return bonusRegisterDate;
    }

    /**
     * 设置 分红登记日
     * @param bonusRegisterDate 分红登记日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBonusRegisterDate(Date bonusRegisterDate) {
        this.bonusRegisterDate = bonusRegisterDate;
    }

    /**
     * 取得 分红下发日
     * @return 分红下发日
     */
    public Date getBonusDealtDate() {
        return bonusDealtDate;
    }

    /**
     * 设置 分红下发日
     * @param bonusDealtDate 分红下发日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBonusDealtDate(Date bonusDealtDate) {
        this.bonusDealtDate = bonusDealtDate;
    }

    /**
     * 取得 分红登记日单位净值
     * @return 分红登记日单位净值
     */
    public BigDecimal getBonusRegisterUnitValue() {
        return bonusRegisterUnitValue;
    }

    /**
     * 设置 分红登记日单位净值
     * @param bonusRegisterUnitValue 分红登记日单位净值
     */
    public void setBonusRegisterUnitValue(BigDecimal bonusRegisterUnitValue) {
        this.bonusRegisterUnitValue = bonusRegisterUnitValue;
    }

    /**
     * 取得 单位分红金额
     * @return 单位分红金额
     */
    public BigDecimal getUnitBonusAmt() {
        return unitBonusAmt;
    }

    /**
     * 设置 单位分红金额
     * @param unitBonusAmt 单位分红金额
     */
    public void setUnitBonusAmt(BigDecimal unitBonusAmt) {
        this.unitBonusAmt = unitBonusAmt;
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