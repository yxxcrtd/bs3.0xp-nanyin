package com.joyintech.tams.model.impairment.batch.datapusheclresult.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;


public class AssetLossResultReaderDto extends NeedOperateEntity {

    /**
     * 资产减值结果表
     */
    private AssetLossResult assetLossResult;


    public AssetLossResult getAssetLossResult() {
        return assetLossResult;
    }

    public void setAssetLossResult(AssetLossResult assetLossResult) {
        this.assetLossResult = assetLossResult;
    }
}