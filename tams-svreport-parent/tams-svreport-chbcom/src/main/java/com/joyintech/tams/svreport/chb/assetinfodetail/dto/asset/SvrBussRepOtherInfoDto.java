package com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset;

import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepAssetBond;
import com.joyintech.tams.svreport.chb.assetinfodetail.validator.asset.SvrBussFinBondRepInfoValidator;

import java.math.BigDecimal;

/**
 * @author wang
 * @create 2021-01-13 16:42
 */


public class SvrBussRepOtherInfoDto extends RepAssetBond {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 期限（天）
     */
    private BigDecimal termDate;

    /**
     * 负债规模
     */
    private BigDecimal debtScale;

    /**
     * 所属国家或地区 参考数据字典：COUNTRY_AREA
     */
    private String countryArea;

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


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getTermDate() {
        return termDate;
    }

    public void setTermDate(BigDecimal termDate) {
        this.termDate = termDate;
    }

    public BigDecimal getDebtScale() {
        return debtScale;
    }

    public void setDebtScale(BigDecimal debtScale) {
        this.debtScale = debtScale;
    }

    public String getCountryArea() {
        return countryArea;
    }

    public void setCountryArea(String countryArea) {
        this.countryArea = countryArea;
    }

    public BigDecimal getAssetYield() {
        return assetYield;
    }

    public void setAssetYield(BigDecimal assetYield) {
        this.assetYield = assetYield;
    }
}
