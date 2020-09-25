package com.joyintech.tams.invest.product.productplanning.assetplannewsdefend.dto;

import com.joyintech.tams.common.base.entity.AssetPlanDetail;
import com.joyintech.tams.common.base.entity.AssetPlanInfo;

import java.util.List;

public class AssetPlanNewsForm {

    private String operateType;

    private AssetPlanInfo assetPlanInfo;

    private List<AssetPlanDetail> assetPlanDetailList;

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public AssetPlanInfo getAssetPlanInfo() {
        return assetPlanInfo;
    }

    public void setAssetPlanInfo(AssetPlanInfo assetPlanInfo) {
        this.assetPlanInfo = assetPlanInfo;
    }

    public List<AssetPlanDetail> getAssetPlanDetailList() {
        return assetPlanDetailList;
    }

    public void setAssetPlanDetailList(List<AssetPlanDetail> assetPlanDetailList) {
        this.assetPlanDetailList = assetPlanDetailList;
    }
}
