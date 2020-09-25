package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RepAssetBond extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String repFinprodId;

    /**
     * 债券代码
     */
    private String secId;

    /**
     * 具体类别
     */
    private String repSectype;

    /**
     * 发行方式
     */
    private String repIssueMode;

    /**
     * 主体评级
     */
    private String repMainGrade;

    /**
     * 发行机构类型（按规模划分） 
     */
    private String repPartyType1;

    /**
     * 发行机构类型（按经济类型划分） 
     */
    private String repPartyType2;

    /**
     * 发行机构类型（按技术领域划分）
     */
    private String repPartyType3;

    /**
     * 发行机构所属行业
     */
    private String repIndustry;

    /**
     * 发行机构所属行业二级分类
     */
    private String repIndustryLevel2;

    /**
     * 登记托管机构
     */
    private String repRegistOrg;

    /**
     * 登记托管机构说明
     */
    private String repRegistorgComment;

    /**
     * 备注
     */
    private String repRemark;
    
    /**
     * 是否由金融资产投资公司发行
     */
    private String repFinancialComissue;
    
    /**
     * 商业银行二级资本债
     */
    private String repBankSecCapital;
    
    /**
     * 商业银行永续债
     */
    private String repBankSecPerpetual;
    
    /**
     * 债券性质
     * @return
     */
    private String repSecNature;
    public String getRepFinancialComissue() {
		return repFinancialComissue;
	}

    /**
     * 是否由金融资产投资公司发行
     */
	public void setRepFinancialComissue(String repFinancialComissue) {
		this.repFinancialComissue = repFinancialComissue == null?null:repFinancialComissue.trim();
	}

	public String getRepBankSecCapital() {
		return repBankSecCapital;
	}

	/**
     * 商业银行二级资本债
     */
	public void setRepBankSecCapital(String repBankSecCapital) {
		this.repBankSecCapital = repBankSecCapital == null?null:repBankSecCapital.trim();
	}

	public String getRepBankSecPerpetual() {
		return repBankSecPerpetual;
	}

	 /**
     * 商业银行永续债
     */
	public void setRepBankSecPerpetual(String repBankSecPerpetual) {
		this.repBankSecPerpetual = repBankSecPerpetual == null?null:repBankSecPerpetual.trim();
	}

	/**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getRepFinprodId() {
        return repFinprodId;
    }

    /**
     * 设置 金融产品代码
     * @param repFinprodId 金融产品代码
     */
    public void setRepFinprodId(String repFinprodId) {
        this.repFinprodId = repFinprodId == null ? null : repFinprodId.trim();
    }

    /**
     * 取得 债券代码
     * @return 债券代码
     */
    public String getSecId() {
        return secId;
    }

    /**
     * 设置 债券代码
     * @param secId 债券代码
     */
    public void setSecId(String secId) {
        this.secId = secId == null ? null : secId.trim();
    }

    /**
     * 取得 具体类别
     * @return 具体类别
     */
    public String getRepSectype() {
        return repSectype;
    }

    /**
     * 设置 具体类别
     * @param repSectype 具体类别
     */
    public void setRepSectype(String repSectype) {
        this.repSectype = repSectype == null ? null : repSectype.trim();
    }

    /**
     * 取得 发行方式
     * @return 发行方式
     */
    public String getRepIssueMode() {
        return repIssueMode;
    }

    /**
     * 设置 发行方式
     * @param repIssueMode 发行方式
     */
    public void setRepIssueMode(String repIssueMode) {
        this.repIssueMode = repIssueMode == null ? null : repIssueMode.trim();
    }

    /**
     * 取得 主体评级
     * @return 主体评级
     */
    public String getRepMainGrade() {
        return repMainGrade;
    }

    /**
     * 设置 主体评级
     * @param repMainGrade 主体评级
     */
    public void setRepMainGrade(String repMainGrade) {
        this.repMainGrade = repMainGrade == null ? null : repMainGrade.trim();
    }

    /**
     * 取得 发行机构类型（按规模划分） 
     * @return 发行机构类型（按规模划分） 
     */
    public String getRepPartyType1() {
        return repPartyType1;
    }

    /**
     * 设置 发行机构类型（按规模划分） 
     * @param repPartyType1 发行机构类型（按规模划分） 
     */
    public void setRepPartyType1(String repPartyType1) {
        this.repPartyType1 = repPartyType1 == null ? null : repPartyType1.trim();
    }

    /**
     * 取得 发行机构类型（按经济类型划分） 
     * @return 发行机构类型（按经济类型划分） 
     */
    public String getRepPartyType2() {
        return repPartyType2;
    }

    /**
     * 设置 发行机构类型（按经济类型划分） 
     * @param repPartyType2 发行机构类型（按经济类型划分） 
     */
    public void setRepPartyType2(String repPartyType2) {
        this.repPartyType2 = repPartyType2 == null ? null : repPartyType2.trim();
    }

    /**
     * 取得 发行机构类型（按技术领域划分）
     * @return 发行机构类型（按技术领域划分）
     */
    public String getRepPartyType3() {
        return repPartyType3;
    }

    /**
     * 设置 发行机构类型（按技术领域划分）
     * @param repPartyType3 发行机构类型（按技术领域划分）
     */
    public void setRepPartyType3(String repPartyType3) {
        this.repPartyType3 = repPartyType3 == null ? null : repPartyType3.trim();
    }

    /**
     * 取得 发行机构所属行业
     * @return 发行机构所属行业
     */
    public String getRepIndustry() {
        return repIndustry;
    }

    /**
     * 设置 发行机构所属行业
     * @param repIndustry 发行机构所属行业
     */
    public void setRepIndustry(String repIndustry) {
        this.repIndustry = repIndustry == null ? null : repIndustry.trim();
    }

    /**
     * 取得 发行机构所属行业二级分类
     * @return 发行机构所属行业二级分类
     */
    public String getRepIndustryLevel2() {
        return repIndustryLevel2;
    }

    /**
     * 设置 发行机构所属行业二级分类
     * @param repIndustryLevel2 发行机构所属行业二级分类
     */
    public void setRepIndustryLevel2(String repIndustryLevel2) {
        this.repIndustryLevel2 = repIndustryLevel2 == null ? null : repIndustryLevel2.trim();
    }

    /**
     * 取得 登记托管机构
     * @return 登记托管机构
     */
    public String getRepRegistOrg() {
        return repRegistOrg;
    }

    /**
     * 设置 登记托管机构
     * @param repRegistOrg 登记托管机构
     */
    public void setRepRegistOrg(String repRegistOrg) {
        this.repRegistOrg = repRegistOrg == null ? null : repRegistOrg.trim();
    }

    /**
     * 取得 登记托管机构说明
     * @return 登记托管机构说明
     */
    public String getRepRegistorgComment() {
        return repRegistorgComment;
    }

    /**
     * 设置 登记托管机构说明
     * @param repRegistorgComment 登记托管机构说明
     */
    public void setRepRegistorgComment(String repRegistorgComment) {
        this.repRegistorgComment = repRegistorgComment == null ? null : repRegistorgComment.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRepRemark() {
        return repRemark;
    }

    /**
     * 设置 备注
     * @param repRemark 备注
     */
    public void setRepRemark(String repRemark) {
        this.repRemark = repRemark == null ? null : repRemark.trim();
    }

	public String getRepSecNature() {
		return repSecNature;
	}

	public void setRepSecNature(String repSecNature) {
		this.repSecNature = repSecNature;
	}
    
}