package com.joyintech.tams.invest.common.risktrialrds.dto;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.RskIsupPointVal;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;

import java.util.List;

/**
 * 风险试算结果DTO
 * @author 裴家俊
 * @version 1.0 2018年11月30日
 * @see RiskTrialRetDto
 * @since 1.0
 */
public class RiskTrialRetDto extends RskIsupPointVal {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 指标名称
	 */
	private String indName;

	/**
	 * 指标类型
	 */
	private String indType;

	/*
	 * 结论
	 */
	private String conclusion;
	/*
	 * 总结论
	 */
	private String totalConclu;
	
	/*
	 * 组合层次
	 */
	private String portfolioLevel;

	/**
	 * 明细结果
	 */
	private List<RiskTrialRetDetailDto> riskTrialRetDetailDtoList;
    /**
     * 获取 totalConclu
     * @return totalConclu.
     */
    public String getTotalConclu() {
        return totalConclu;
    }
    
    /**
     * 设置 totalConclu
     * @param totalConclu totalConclu
     */
    public void setTotalConclu(String totalConclu) {
        this.totalConclu = totalConclu;
    }

	public String getConclusion() {
		return conclusion;
	}
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
	
	public String getPortfolioLevel() {
		return portfolioLevel;
	}

	public void setPortfolioLevel(String portfolioLevel) {
		this.portfolioLevel = portfolioLevel;
	}

	public String getSupStatStr() {
		return SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.监督状态, this.getSupStat());
	}

	public String getIndName() {
		return indName;
	}

	public void setIndName(String indName) {
		this.indName = indName;
	}

	public String getIndType() {
		return indType;
	}

	public void setIndType(String indType) {
		this.indType = indType;
	}

	public List<RiskTrialRetDetailDto> getRiskTrialRetDetailDtoList() {
		return riskTrialRetDetailDtoList;
	}

	public void setRiskTrialRetDetailDtoList(List<RiskTrialRetDetailDto> riskTrialRetDetailDtoList) {
		this.riskTrialRetDetailDtoList = riskTrialRetDetailDtoList;
	}
}
