package com.joyintech.tams.common.base.entity;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

public class RepAssetEquity extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String repFinprodId;

    /**
     * 股票代码
     */
    private String repSharesId;

    /**
     * 股票类型
     */
    private String repSharesType;

    /**
     * 行业
     */
    private String repIndustry;

    /**
     * 行业二级分类
     */
    private String repIndustryLevel2;

    /**
     * 投资阶段
     */
    private String repInvestStage;

    /**
     * 企业类型（按规模划分）
     */
    private String repPartyType1;

    /**
     * 企业类型（按经济类型划分）
     */
    private String repPartyType2;

    /**
     * 企业类型（按技术领域划分）
     */
    private String repPartyType3;

    /**
     * 是否为质押融资
     */
    private String repIsPledge;

    /**
     * 备注
     */
    private String repRemark;
    
    /**
     * 是否为债转股
     */
    private String repDeptEquity;
    
    /**
     * 商业银行优先股
     */
    private String repBankSecPrefer;
    
    /**
     * 股权退出安排 中债新增字段 20200408 start
     */
    private Date repEquityQuit;
    
    public String getRepDeptEquity() {
		return repDeptEquity;
	}

	public void setRepDeptEquity(String repDeptEquity) {
		this.repDeptEquity = repDeptEquity;
	}

	public String getRepBankSecPrefer() {
		return repBankSecPrefer;
	}

	public void setRepBankSecPrefer(String repBankSecPrefer) {
		this.repBankSecPrefer = repBankSecPrefer;
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
     * 取得 股票代码
     * @return 股票代码
     */
    public String getRepSharesId() {
        return repSharesId;
    }

    /**
     * 设置 股票代码
     * @param repSharesId 股票代码
     */
    public void setRepSharesId(String repSharesId) {
        this.repSharesId = repSharesId == null ? null : repSharesId.trim();
    }

    /**
     * 取得 股票类型
     * @return 股票类型
     */
    public String getRepSharesType() {
        return repSharesType;
    }

    /**
     * 设置 股票类型
     * @param repSharesType 股票类型
     */
    public void setRepSharesType(String repSharesType) {
        this.repSharesType = repSharesType == null ? null : repSharesType.trim();
    }

    /**
     * 取得 行业
     * @return 行业
     */
    public String getRepIndustry() {
        return repIndustry;
    }

    /**
     * 设置 行业
     * @param repIndustry 行业
     */
    public void setRepIndustry(String repIndustry) {
        this.repIndustry = repIndustry == null ? null : repIndustry.trim();
    }

    /**
     * 取得 行业二级分类
     * @return 行业二级分类
     */
    public String getRepIndustryLevel2() {
        return repIndustryLevel2;
    }

    /**
     * 设置 行业二级分类
     * @param repIndustryLevel2 行业二级分类
     */
    public void setRepIndustryLevel2(String repIndustryLevel2) {
        this.repIndustryLevel2 = repIndustryLevel2 == null ? null : repIndustryLevel2.trim();
    }

    /**
     * 取得 投资阶段
     * @return 投资阶段
     */
    public String getRepInvestStage() {
        return repInvestStage;
    }

    /**
     * 设置 投资阶段
     * @param repInvestStage 投资阶段
     */
    public void setRepInvestStage(String repInvestStage) {
        this.repInvestStage = repInvestStage == null ? null : repInvestStage.trim();
    }

    /**
     * 取得 企业类型（按规模划分）
     * @return 企业类型（按规模划分）
     */
    public String getRepPartyType1() {
        return repPartyType1;
    }

    /**
     * 设置 企业类型（按规模划分）
     * @param repPartyType1 企业类型（按规模划分）
     */
    public void setRepPartyType1(String repPartyType1) {
        this.repPartyType1 = repPartyType1 == null ? null : repPartyType1.trim();
    }

    /**
     * 取得 企业类型（按经济类型划分）
     * @return 企业类型（按经济类型划分）
     */
    public String getRepPartyType2() {
        return repPartyType2;
    }

    /**
     * 设置 企业类型（按经济类型划分）
     * @param repPartyType2 企业类型（按经济类型划分）
     */
    public void setRepPartyType2(String repPartyType2) {
        this.repPartyType2 = repPartyType2 == null ? null : repPartyType2.trim();
    }

    /**
     * 取得 企业类型（按技术领域划分）
     * @return 企业类型（按技术领域划分）
     */
    public String getRepPartyType3() {
        return repPartyType3;
    }

    /**
     * 设置 企业类型（按技术领域划分）
     * @param repPartyType3 企业类型（按技术领域划分）
     */
    public void setRepPartyType3(String repPartyType3) {
        this.repPartyType3 = repPartyType3 == null ? null : repPartyType3.trim();
    }

    /**
     * 取得 是否为质押融资
     * @return 是否为质押融资
     */
    public String getRepIsPledge() {
        return repIsPledge;
    }

    /**
     * 设置 是否为质押融资
     * @param repIsPledge 是否为质押融资
     */
    public void setRepIsPledge(String repIsPledge) {
        this.repIsPledge = repIsPledge == null ? null : repIsPledge.trim();
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

	/**
	 * @return the repEquityQuit
	 */
	public Date getRepEquityQuit() {
		return repEquityQuit;
	}

	/**
	 * @param repEquityQuit the repEquityQuit to set
	 */
	public void setRepEquityQuit(Date repEquityQuit) {
		this.repEquityQuit = repEquityQuit;
	}
}