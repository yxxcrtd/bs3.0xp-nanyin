package com.joyintech.tams.invest.asset.common.dto;

import com.joyintech.tams.common.base.entity.RepAssetOther;

import java.util.List;

public class BaseRepOtherDto {

    /**
     * List<金融产品代码>
     */
    private List<String> repFinprodIdList;
    /**
     * List<资产管理计划报送信息Entity>
     */
    private List<RepAssetOther> repAssetOtherList;

    public List<String> getRepFinprodIdList() {
        return repFinprodIdList;
    }

    public void setRepFinprodIdList(List<String> repFinprodIdList) {
        this.repFinprodIdList = repFinprodIdList;
    }

    public List<RepAssetOther> getRepAssetOtherList() {
        return repAssetOtherList;
    }

    public void setRepAssetOtherList(List<RepAssetOther> repAssetOtherList) {
        this.repAssetOtherList = repAssetOtherList;
    }
}
