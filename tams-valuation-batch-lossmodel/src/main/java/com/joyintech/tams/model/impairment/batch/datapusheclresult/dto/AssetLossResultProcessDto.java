package com.joyintech.tams.model.impairment.batch.datapusheclresult.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;

import java.io.Serializable;

/**
 * 资产减值结果信息DTO
 * @author zhangfei
 *
 */
public class AssetLossResultProcessDto extends NeedToWriteEntity implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private AssetLossResult assetLossResult;


	public AssetLossResult getAssetLossResult() {
		return assetLossResult;
	}

	public void setAssetLossResult(AssetLossResult assetLossResult) {
		this.assetLossResult = assetLossResult;
	}
}
