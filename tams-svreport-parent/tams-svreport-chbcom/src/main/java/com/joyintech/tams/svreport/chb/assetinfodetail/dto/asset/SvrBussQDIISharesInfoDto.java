package com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset;

import com.joyintech.tams.common.base.entity.RepAssetBond;

import java.math.BigDecimal;

/**资产基本信息_其它资产类信息 dto
 * @author wang
 * @create 2021-01-18 16:42
 */


public class SvrBussQDIISharesInfoDto extends RepAssetBond {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 所属国家或地区 参考数据字典：COUNTRY_AREA
     */
    private String countryArea;
    /**
     * 资产名称
     */
    private String assetName;
    /**
     * 资产代码
     */
    private String assetId;
    /**
     * 发行机构
     */
    private String issuePartyName;
    /**
     * 行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     */
    private String industryShares;

    /**
     * 行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     */
    private String industryShares2;
    /**
     * 备注
     */
    private String remark;


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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCountryArea() {
        return countryArea;
    }

    public void setCountryArea(String countryArea) {
        this.countryArea = countryArea;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getIssuePartyName() {
        return issuePartyName;
    }

    public void setIssuePartyName(String issuePartyName) {
        this.issuePartyName = issuePartyName;
    }

    public String getIndustryShares() {
        return industryShares;
    }

    public void setIndustryShares(String industryShares) {
        this.industryShares = industryShares;
    }

    public String getIndustryShares2() {
        return industryShares2;
    }

    public void setIndustryShares2(String industryShares2) {
        this.industryShares2 = industryShares2;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
