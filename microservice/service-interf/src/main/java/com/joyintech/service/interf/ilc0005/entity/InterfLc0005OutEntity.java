/*
 * 文件名：InterfLc0005OutEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ilc0005.entity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.core.annotation.EsbNumber;

/**
 * 理财产品报价报价查询接口Lc0005输出
 * 
 * @author changwenbin
 * @version 1.0 2018年11月8日
 * @see InterfLc0005OutEntity
 * @since 1.0
 */
public class InterfLc0005OutEntity extends InterfLc0005InEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 5843548728123327373L;

    /**
     * 是否分层
     */
    private String islay;

    /**
     * 销售费率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal salefeerate;

    /**
     * 分层list
     */
    private List<InterfLc0005OutLvlEntity> lvllist;

    /**
     * 获取是否分层
     * 
     * @return 是否分层
     * @see
     * @since 1.0
     */
    public String getIslay() {
        return islay;
    }

    /**
     * 设置是否分层
     * 
     * @param islay 是否分层
     * @see
     * @since 1.0
     */
    public void setIslay(String islay) {
        this.islay = islay;
    }

    /**
     * 获取销售费率
     * 
     * @return 销售费率
     * @see
     * @since 1.0
     */
    @EsbName(value = "SALE_FEE_RATE")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 9)
    public BigDecimal getSalefeerate() {
        return salefeerate;
    }

    /**
     * 设置销售费率
     * 
     * @param salefeerate 销售费率
     * @see
     * @since 1.0
     */
    public void setSalefeerate(BigDecimal salefeerate) {
        this.salefeerate = salefeerate;
    }

    /**
     * 获取分层list
     * 
     * @return 分层list
     * @see
     * @since 1.0
     */
    @EsbName(value = "PRODUCT_ARRAY")
    @EsbFieldType(value = EsbFieldType.ARRAY)
    public List<InterfLc0005OutLvlEntity> getLvllist() {
        return lvllist;
    }

    /**
     * 设置分层list
     * 
     * @param lvllist 分层list
     * @see
     * @since 1.0
     */
    public void setLvllist(List<InterfLc0005OutLvlEntity> lvllist) {
        this.lvllist = lvllist;
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "PRODUCT_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public String getProductcode() {
        return super.getProductcode();
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "ESTABLISH_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    @Override
    public String getEstablishdate() {
        return super.getEstablishdate();
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "EXPIRY_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    @Override
    public String getExpirydate() {
        return super.getExpirydate();
    }

}