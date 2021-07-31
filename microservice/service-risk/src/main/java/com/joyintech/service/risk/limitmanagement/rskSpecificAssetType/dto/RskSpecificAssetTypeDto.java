package com.joyintech.service.risk.limitmanagement.rskSpecificAssetType.dto;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.tams.common.base.entity.RskSpecificAssetType;

/**
 * 特定资产类型设置
 * 
 * @author zhangnian
 *
 */
public class RskSpecificAssetTypeDto extends RskSpecificAssetType implements ITableRow {

	/**
	 * 金融产品全称
	 */
	private String finprodName;

	/**
	 * 市场代码
	 */
	private String finprodMarketId;

	private String isFlowLimited;

	@Override
	public String getIsFlowLimited() {
		return isFlowLimited;
	}

	@Override
	public void setIsFlowLimited(String isFlowLimited) {
		this.isFlowLimited = isFlowLimited;
	}

	public String getFinprodName() {
		return finprodName;
	}

	public void setFinprodName(String finprodName) {
		this.finprodName = finprodName;
	}

	public String getFinprodMarketId() {
		return finprodMarketId;
	}

	public void setFinprodMarketId(String finprodMarketId) {
		this.finprodMarketId = finprodMarketId;
	}
}
