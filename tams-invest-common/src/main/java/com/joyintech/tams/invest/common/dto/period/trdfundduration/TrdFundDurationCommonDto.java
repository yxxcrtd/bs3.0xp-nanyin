/*
 * 文件名：TrdFundDurationCommonDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人："??????é1?"
 * 修改时间：2019年1月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.dto.period.trdfundduration;

import java.util.List;

import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;

/**
 * 基金存续期共通dto
 * 〈功能详细描述〉
 * @author "丁志鹏"
 * @version 1.0 2019年1月21日
 * @see TrdFundDurationCommonDto
 * @since 1.0
 */
public class TrdFundDurationCommonDto extends TrdFinProductDealTotal{
    /**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 9148050058721030720L;
	/**
	 * 存在后续交易
	 */
	private  String hasFlow;
	/**
     * 是否需要跳过插入流程表数据
     */
    private Boolean needSkipInsertWfl;
    /**
     * 利息信息明细
     * 
     */
    private List<FinAssetIntInfo> finAssetIntInfoList;


    /**
     * 所有相关表，贯穿整个流程
     */
    private BaseTotalTradeInfoDto baseTotalTradeInfoDto;
    /**
     * 发行机构代码
     */
    private String repIssuePartyId;
    /**
     * 撤销原因
     */
    private String revokeReason;


	// 多级法人改造 add by caiqun 20200615
	/**
	 * 所属机构名称
	 */
	private String institutionName;
	// 多级法人改造 add by caiqun 20200615
    
	/**
	 * 获取 repIssuePartyId
	 * @return repIssuePartyId.
	 */
	public String getRepIssuePartyId() {
		return repIssuePartyId;
	}
	/**
	 * 设置 repIssuePartyId
	 * @param repIssuePartyId repIssuePartyId
	 */
	public void setRepIssuePartyId(String repIssuePartyId) {
		this.repIssuePartyId = repIssuePartyId;
	}
	/**
	 * 获取 needSkipInsertWfl
	 * @return needSkipInsertWfl.
	 */
	public Boolean getNeedSkipInsertWfl() {
		return needSkipInsertWfl;
	}
	/**
	 * 设置 needSkipInsertWfl
	 * @param needSkipInsertWfl needSkipInsertWfl
	 */
	public void setNeedSkipInsertWfl(Boolean needSkipInsertWfl) {
		this.needSkipInsertWfl = needSkipInsertWfl;
	}
	/**
	 * 获取 finAssetIntInfoList
	 * @return finAssetIntInfoList.
	 */
	public List<FinAssetIntInfo> getFinAssetIntInfoList() {
		return finAssetIntInfoList;
	}
	/**
	 * 设置 finAssetIntInfoList
	 * @param finAssetIntInfoList finAssetIntInfoList
	 */
	public void setFinAssetIntInfoList(List<FinAssetIntInfo> finAssetIntInfoList) {
		this.finAssetIntInfoList = finAssetIntInfoList;
	}
	/**
	 * 获取 baseTotalTradeInfoDto
	 * @return baseTotalTradeInfoDto.
	 */
	public BaseTotalTradeInfoDto getBaseTotalTradeInfoDto() {
		return baseTotalTradeInfoDto;
	}
	/**
	 * 设置 baseTotalTradeInfoDto
	 * @param baseTotalTradeInfoDto baseTotalTradeInfoDto
	 */
	public void setBaseTotalTradeInfoDto(BaseTotalTradeInfoDto baseTotalTradeInfoDto) {
		this.baseTotalTradeInfoDto = baseTotalTradeInfoDto;
	}
	/**
	 * 获取 revokeReason
	 * @return revokeReason.
	 */
	public String getRevokeReason() {
		return revokeReason;
	}
	/**
	 * 设置 revokeReason
	 * @param revokeReason revokeReason
	 */
	public void setRevokeReason(String revokeReason) {
		this.revokeReason = revokeReason;
	}

	/**
	 * 获取hasFlow
	 * @return hasFlow
	 */
	public String getHasFlow() {
		return hasFlow;
	}

	/**
	 * 设置hasFlow
	 * @param hasFlow hasFlow
	 */
	public void setHasFlow(String hasFlow) {
		this.hasFlow = hasFlow;
	}

	// 多级法人改造 add by caiqun 20200615
	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	// 多级法人改造 add by caiqun 20200615
}
