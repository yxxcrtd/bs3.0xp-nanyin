package com.joyintech.tams.svreport.chbreport.finassetliabilitystatement.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;

/**
 * 理财产品资产负债表导出Dto
 *
 * @author WanShiwei
 * @version 2.1.6 2021年4月20日
 * @see FinAssetLiabilityStatementQuery003Dto
 * @since 2.1.6
 */
public class FinAssetLiabilityStatementQuery003Dto extends BaseEntity {

    private static final long serialVersionUID = -1493892052117293624L;

    /**
     * 资产.项目名
     */
    private String itemName;

    /**
     * 资产.期末余额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal endingBalance = BigDecimal.ZERO;

    /**
     * 资产.年初余额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal initialBalance = BigDecimal.ZERO;

    /**
     * 负债和所有者权益.项目名
     */
    private String itemName1;

    /**
     * 负债和所有者权益.期末余额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal endingBalance1 = BigDecimal.ZERO;

    /**
     * 负债和所有者权益.年初余额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal initialBalance1 = BigDecimal.ZERO;

    public FinAssetLiabilityStatementQuery003Dto() {
        super();
    }

    public FinAssetLiabilityStatementQuery003Dto(String itemName) {
        super();
        this.itemName = itemName;
    }

    public FinAssetLiabilityStatementQuery003Dto(String itemName, BigDecimal endingBalance, BigDecimal initialBalance) {
        super();
        this.itemName = itemName;
        this.endingBalance = endingBalance;
        this.initialBalance = initialBalance;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getEndingBalance() {
        return endingBalance;
    }

    public void setEndingBalance(BigDecimal endingBalance) {
        this.endingBalance = endingBalance;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    /**
     * 获取 itemName1
     *
     * @return itemName1.
     */
    public String getItemName1() {
        return itemName1;
    }

    /**
     * 设置 itemName1
     *
     * @param itemName1 itemName1
     */
    public void setItemName1(String itemName1) {
        this.itemName1 = itemName1;
    }

    /**
     * 获取 endingBalance1
     *
     * @return endingBalance1.
     */
    public BigDecimal getEndingBalance1() {
        return endingBalance1;
    }

    /**
     * 设置 endingBalance1
     *
     * @param endingBalance1 endingBalance1
     */
    public void setEndingBalance1(BigDecimal endingBalance1) {
        this.endingBalance1 = endingBalance1;
    }

    /**
     * 获取 initialBalance1
     *
     * @return initialBalance1.
     */
    public BigDecimal getInitialBalance1() {
        return initialBalance1;
    }

    /**
     * 设置 initialBalance1
     *
     * @param initialBalance1 initialBalance1
     */
    public void setInitialBalance1(BigDecimal initialBalance1) {
        this.initialBalance1 = initialBalance1;
    }
}

