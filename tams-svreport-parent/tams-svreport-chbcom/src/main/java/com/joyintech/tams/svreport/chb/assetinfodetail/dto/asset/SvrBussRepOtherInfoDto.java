package com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset;

import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepAssetBond;
import com.joyintech.tams.svreport.chb.assetinfodetail.validator.asset.SvrBussFinBondRepInfoValidator;

import java.math.BigDecimal;

/**资产基本信息_其它资产类信息 dto
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

    /**
     * 新人行资产一级分类
     */
    private String pbcAssetType1;

    /**
     * 新人行资产二级分类
     */
    private String pbcAssetType2;

    /**
     * 新人行资产三级分类
     */
    private String pbcAssetType3;

    /**
     * 资产名称
     */
    private String assetName;

    public String getPbcAssetType1() {
        return pbcAssetType1;
    }

    public void setPbcAssetType1(String pbcAssetType1) {
        this.pbcAssetType1 = pbcAssetType1;
    }

    public String getPbcAssetType2() {
        return pbcAssetType2;
    }

    public void setPbcAssetType2(String pbcAssetType2) {
        this.pbcAssetType2 = pbcAssetType2;
    }

    public String getPbcAssetType3() {
        return pbcAssetType3;
    }

    public void setPbcAssetType3(String pbcAssetType3) {
        this.pbcAssetType3 = pbcAssetType3;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

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
