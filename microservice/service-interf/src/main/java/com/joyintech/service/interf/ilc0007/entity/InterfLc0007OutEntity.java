/*
 * 文件名：InterfLc0007OutEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ilc0007.entity;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Required;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.core.annotation.EsbNumber;

/**
 * Lc0007接口输出
 * 
 * @author changwenbin
 * @version 1.0 2018年11月10日
 * @see InterfLc0007OutEntity
 * @since 1.0
 */
public class InterfLc0007OutEntity extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 4078496398938534391L;

    public InterfLc0007OutEntity() {
        super();
    }

    /**
     * 借据流水号
     */
    private String duebillseqno;

    /**
     * 借据金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal duebillamt;

    /**
     * 借据余额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal duebillbalance;

    /**
     * 卖出余额
     */
    private String sellbalance;

    /**
     * 资产类型
     */
    private String finprodType;

    /**
     * 获取借据流水号
     * 
     * @return 借据流水号
     */
    @EsbName(value = "DUEBILL_SEQ_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 52)
    @Required
    public String getDuebillseqno() {
        return duebillseqno;
    }

    /**
     * 设置借据流水号
     * 
     * @param duebillseqno 借据流水号
     */
    public void setDuebillseqno(String duebillseqno) {
        this.duebillseqno = duebillseqno;
    }

    /**
     * 获取借据金额
     * 
     * @return 借据金额
     */
    @EsbName(value = "DUEBILL_AMT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 4)
    @Required
    public BigDecimal getDuebillamt() {
        return duebillamt;
    }

    /**
     * 设置借据金额
     * 
     * @param duebillamt 借据金额
     */
    public void setDuebillamt(BigDecimal duebillamt) {
        this.duebillamt = duebillamt;
    }

    /**
     * 获取借据余额
     * 
     * @return 借据余额
     */
    @EsbName(value = "DUEBILL_BALANCE")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 4)
    @Required
    public BigDecimal getDuebillbalance() {
        return duebillbalance;
    }

    /**
     * 设置借据余额
     * 
     * @param duebillbalance 借据余额
     */
    public void setDuebillbalance(BigDecimal duebillbalance) {
        this.duebillbalance = duebillbalance;
    }

    /**
     * 获取卖出余额
     * 
     * @return 卖出余额
     */
    @EsbName(value = "SELL_BALANCE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    public String getSellbalance() {
        return sellbalance;
    }

    /**
     * 设置卖出余额
     * 
     * @param sellbalance 卖出余额
     */
    public void setSellbalance(String sellbalance) {
        this.sellbalance = sellbalance;
    }

    /**
     * 获取 资产类型
     * 
     * @return 资产类型
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 资产类型
     * 
     * @param finprodType 资产类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

}
