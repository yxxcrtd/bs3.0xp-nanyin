package com.joyintech.tams.invest.batch.autogeneration.secpos.dto;

import com.joyintech.tams.common.base.entity.AssetTargetDb;

public class AssetTargetDbDto extends AssetTargetDb {
    
    /**
     * 评级机构
     */
    private String gradeOrgId;
    /**
     * 评级对象类型
     */
    private String gradeType;
    
    /**
     * 内外部类型
     */
    private String inOrOut;
    
    /**
     * 评级结果
     */
    private String gradeResult;
    
    /**
     * 风险等级资产一级分类
     */
    private String riskAssetType1;
    /**
     * 风险等级资产二级分类
     */
    private String riskAssetType2;
    /**
     * 是否银行
     */
    private String isBank;
    /**
     * 国标一级分类
     */
    private String industry1;
    /**
     * 融资人性质
     */
    
    private String natureOrg;
    
    /**
     * 集中度的字典值
     */
    private String crnDicCode;

    
    /**
     * 获取 gradeOrgId
     * @return gradeOrgId.
     */
    public String getGradeOrgId() {
        return gradeOrgId;
    }

    
    /**
     * 设置 gradeOrgId
     * @param gradeOrgId gradeOrgId
     */
    public void setGradeOrgId(String gradeOrgId) {
        this.gradeOrgId = gradeOrgId;
    }

    /**
     * 获取 gradeType
     * @return gradeType.
     */
    public String getGradeType() {
        return gradeType;
    }
    
    /**
     * 设置 gradeType
     * @param gradeType gradeType
     */
    public void setGradeType(String gradeType) {
        this.gradeType = gradeType;
    }
    
    /**
     * 获取 inOrOut
     * @return inOrOut.
     */
    public String getInOrOut() {
        return inOrOut;
    }
    
    /**
     * 设置 inOrOut
     * @param inOrOut inOrOut
     */
    public void setInOrOut(String inOrOut) {
        this.inOrOut = inOrOut;
    }
    
    /**
     * 获取 gradeResult
     * @return gradeResult.
     */
    public String getGradeResult() {
        return gradeResult;
    }
    
    /**
     * 设置 gradeResult
     * @param gradeResult gradeResult
     */
    public void setGradeResult(String gradeResult) {
        this.gradeResult = gradeResult;
    }
    
    /**
     * 获取 riskAssetType1
     * @return riskAssetType1.
     */
    public String getRiskAssetType1() {
        return riskAssetType1;
    }
    
    /**
     * 设置 riskAssetType1
     * @param riskAssetType1 riskAssetType1
     */
    public void setRiskAssetType1(String riskAssetType1) {
        this.riskAssetType1 = riskAssetType1;
    }
    
    /**
     * 获取 riskAssetType2
     * @return riskAssetType2.
     */
    public String getRiskAssetType2() {
        return riskAssetType2;
    }
    
    /**
     * 设置 riskAssetType2
     * @param riskAssetType2 riskAssetType2
     */
    public void setRiskAssetType2(String riskAssetType2) {
        this.riskAssetType2 = riskAssetType2;
    }
    
    /**
     * 获取 isBank
     * @return isBank.
     */
    public String getIsBank() {
        return isBank;
    }
    
    /**
     * 设置 isBank
     * @param isBank isBank
     */
    public void setIsBank(String isBank) {
        this.isBank = isBank;
    }

    



    /**
     * 获取 natureOrg
     * @return natureOrg.
     */
    public String getNatureOrg() {
        return natureOrg;
    }
    
    /**
     * 设置 natureOrg
     * @param natureOrg natureOrg
     */
    public void setNatureOrg(String natureOrg) {
        this.natureOrg = natureOrg;
    }


    /**
     * 获取 crnDicCode
     * @return crnDicCode.
     */
    public String getCrnDicCode() {
        return crnDicCode;
    }


    /**
     * 设置 crnDicCode
     * @param crnDicCode crnDicCode
     */
    public void setCrnDicCode(String crnDicCode) {
        this.crnDicCode = crnDicCode;
    }


    /**
     * 获取 industry1
     * @return industry1.
     */
    public String getIndustry1() {
        return industry1;
    }


    /**
     * 设置 industry1
     * @param industry1 industry1
     */
    public void setIndustry1(String industry1) {
        this.industry1 = industry1;
    }


    
}
