package com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset;

import com.joyintech.tams.common.base.entity.RepAssetBond;

import java.math.BigDecimal;

/**
 * @author wang
 * @create 2021-01-13 16:42
 */


public class SvrBussRepOthKindInfoDto extends RepAssetBond {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;


    /**
     * 所属国家或地区 参考数据字典：COUNTRY_AREA
     */
    private String countryArea;

    /**
     * 资产价值
     */
    private BigDecimal assetValue;

    /**
     * 收益率/年利率
     */
    private BigDecimal assetYield;

    /**
     * 备注
     */
    private String  remark;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCountryArea() {
        return countryArea;
    }

    public void setCountryArea(String countryArea) {
        this.countryArea = countryArea;
    }

    public BigDecimal getAssetValue() {
        return assetValue;
    }

    public void setAssetValue(BigDecimal assetValue) {
        this.assetValue = assetValue;
    }

    public BigDecimal getAssetYield() {
        return assetYield;
    }

    public void setAssetYield(BigDecimal assetYield) {
        this.assetYield = assetYield;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
