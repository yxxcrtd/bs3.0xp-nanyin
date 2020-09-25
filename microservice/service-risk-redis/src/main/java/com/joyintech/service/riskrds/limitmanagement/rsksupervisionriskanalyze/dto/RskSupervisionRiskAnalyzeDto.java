package com.joyintech.service.riskrds.limitmanagement.rsksupervisionriskanalyze.dto;

import com.joyintech.tams.common.base.entity.PtlPortfolioAuthority;
import com.joyintech.tams.common.base.entity.PtlPortfolioValuationKey;
import com.joyintech.tams.common.base.entity.RskIsupPointVal;

import java.util.List;

/**
 * 监督值查询
 * @description: RskSupervisionRiskAnalyzeDto
 * @author: 裴家俊
 * @create: 2019-10-01
 **/
public class RskSupervisionRiskAnalyzeDto extends RskIsupPointVal {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 组合/组合包名称
     */
    private String groupName;

    /**
     * 监督指标名称
     */
    private String indName;

    /**
     * 监督类型
     */
    private String supType;
    
	/**
     * 备注
     */
    private String memo;

    private String indType;
    
    private String assetCode;
    
    private String assetName;
    
    /**
     * 组合名称
     */
    private String portfolioName;
    
    /**
     * 持仓规模
     */
    private String shareAmt;
    
    /**
     * 投资经理
     */
    private String invManagerId;

	/**
	 *
	 */
	private int mxCount;


	/**
	 * 突破次数
     * @return
     */
    public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	private int frequency;
    
    public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public String getShareAmt() {
		return shareAmt;
	}

	public void setShareAmt(String shareAmt) {
		this.shareAmt = shareAmt;
	}

	public String getInvManagerId() {
		return invManagerId;
	}

	public void setInvManagerId(String invManagerId) {
		this.invManagerId = invManagerId;
	}

	/**
     * 获取 groupName
     *
     * @return groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置 groupName
     *
     * @param groupName groupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getIndType() {
		return indType;
	}

	public void setIndType(String indType) {
		this.indType = indType;
	}

	/**
     * 获取 indName
     *
     * @return indName
     */
    public String getIndName() {
        return indName;
    }

    /**
     * 设置 indName
     *
     * @param indName indName
     */
    public void setIndName(String indName) {
        this.indName = indName;
    }

    /**
     * 获取 supType
     *
     * @return supType
     */
    public String getSupType() {
        return supType;
    }

    /**
     * 设置 supType
     *
     * @param supType supType
     */
    public void setSupType(String supType) {
        this.supType = supType;
    }

    /**
     * 获取 memo
     *
     * @return memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 设置 memo
     *
     * @param memo memo
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	private String supFrequency;
    /**
     * 事后监控频率
     * @return
     */
    public String getSupFrequency() {
		return supFrequency;
	}

	public void setSupFrequency(String supFrequency) {
		this.supFrequency = supFrequency;
	}

	public int getMxCount() {
		return mxCount;
	}

	public void setMxCount(int mxCount) {
		this.mxCount = mxCount;
	}

}
