package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class TrdPbRelation extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 要素类型
     */
    private String factorType;
    
    /**
     * 要素类型名称
     */
    private String factorTypeStr;

    /**
     * 产品/子组合代码
     */
    private String prdPortfolioId;
    
    /**
     * 产品/子组合名称
     */
    private String prdPortfolioName;

    /**
     * PB编号
     */
    private String pbId;

    /**
     * 产品代码
     */
    private String prdId;
    /**
     * 子组合代码
     */
    private String portfolioId;
    
    /**
     * 更新日期
     */
    private String updateTimeStr;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 要素类型
     * @return 要素类型
     */
    public String getFactorType() {
        return factorType;
    }

    /**
     * 设置 要素类型
     * @param factorType 要素类型
     */
    public void setFactorType(String factorType) {
        this.factorType = factorType == null ? null : factorType.trim();
    }

    /**
     * 取得 产品/子组合代码
     * @return 产品/子组合代码
     */
    public String getPrdPortfolioId() {
        return prdPortfolioId;
    }

    /**
     * 设置 产品/子组合代码
     * @param prdPortfolioId 产品/子组合代码
     */
    public void setPrdPortfolioId(String prdPortfolioId) {
        this.prdPortfolioId = prdPortfolioId == null ? null : prdPortfolioId.trim();
    }
    
    /**
     * 取得 产品/子组合名称
     * @return 产品/子组合名称
     */
    public String getPrdPortfolioName() {
		return prdPortfolioName;
	}

    /**
     * 设置 产品/子组合名称
     * @param prdPortfolioName 产品/子组合名称
     */
	public void setPrdPortfolioName(String prdPortfolioName) {
		 this.prdPortfolioName = prdPortfolioName == null ? null : prdPortfolioName.trim();
	}

	/**
     * 取得 PB编号
     * @return PB编号
     */
    public String getPbId() {
        return pbId;
    }

    /**
     * 设置 PB编号
     * @param pbId PB编号
     */
    public void setPbId(String pbId) {
        this.pbId = pbId == null ? null : pbId.trim();
    }

    /**
     * 产品代码
     * @return
     */
	public String getPrdId() {
		return prdId;
	}
	/**
     * 产品代码
     * @return
     */
	public void setPrdId(String prdId) {
		this.prdId = prdId == null ? null : prdId.trim();
	}
	/**
     * 子组合代码
     * @return
     */
	public String getPortfolioId() {
		return portfolioId;
	}
	/**
     * 子组合代码
     * @return
     */
	public void setPortfolioId(String portfolioId) {
		portfolioId = portfolioId == null ? null : portfolioId.trim();
	}
	/**
     * 要素类型名称
     */
	public String getFactorTypeStr() {
		return factorTypeStr;
	}
	/**
     * 要素类型名称
     */
	public void setFactorTypeStr(String factorTypeStr) {
		this.factorTypeStr = factorTypeStr;
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}
    
}