package com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto;

import java.util.Date;
import java.util.List;

/**
 * 资产基本信息查询参数封装
 * @author zhangfei
 *
 */
public class ModAssetInfoParamsDto {
	/**
	 * 金融产品代码集合
	 */
	private List<String> finprodIdList;
	
	/**
	 * 资产五级分类集合
	 */
	private List<String> assetFiveList;
	
	/**
	 * 查询日
	 */
	private Date searchDate;

	public List<String> getFinprodIdList() {
		return finprodIdList;
	}

	public List<String> getAssetFiveList() {
		return assetFiveList;
	}

	public Date getSearchDate() {
		return searchDate;
	}

	public void setFinprodIdList(List<String> finprodIdList) {
		this.finprodIdList = finprodIdList;
	}

	public void setAssetFiveList(List<String> assetFiveList) {
		this.assetFiveList = assetFiveList;
	}

	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}
	
	
}
