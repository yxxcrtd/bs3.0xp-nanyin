package com.joyintech.tams.invest.common.dto.period.trdprojbuyoff;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;

/**
 * @program: component-base
 * @description:
 * @author: 强成西
 * @create: 2018-10-23 14:54
 **/
public class TrdProjectBuyOffInterestDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -6559803536877924827L;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 资产名称
     */
    private String assetName;

    /**
     * 买断利率
     */
    private BigDecimal rate;

    /**
     * 生效日
     */
    private String effDate;

    /**
     * 现金流代码
     */
    private String cashId;

    /**
     * 资产类型
     */
    private String assetType;

    public Date getEffDateDate() {
        try {
            return DateUtil.parse(getEffDate());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取 assetId
     *
     * @return assetId
     */
    public String getAssetId() {
        return assetId;
    }

    /**
     * 设置 assetId
     *
     * @param assetId assetId
     */
    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    /**
     * 获取 assetName
     *
     * @return assetName
     */
    public String getAssetName() {
        return assetName;
    }

    /**
     * 设置 assetName
     *
     * @param assetName assetName
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    /**
     * 获取 operateType
     *
     * @return operateType
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     *
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 finprodId
     *
     * @return finprodId
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     *
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 rate
     *
     * @return rate
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * 设置 rate
     *
     * @param rate rate
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * 获取 effDate
     *
     * @return effDate
     */
    public String getEffDate() {
        return effDate;
    }

    /**
     * 设置 effDate
     *
     * @param effDate effDate
     */
    public void setEffDate(String effDate) {
        this.effDate = effDate;
    }

    /**
     * 获取 cashId
     *
     * @return cashId
     */
    public String getCashId() {
        return cashId;
    }

    /**
     * 设置 cashId
     *
     * @param cashId cashId
     */
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }

    /**
     * 获取 assetType
     *
     * @return assetType
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * 设置 assetType
     *
     * @param assetType assetType
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }
}
