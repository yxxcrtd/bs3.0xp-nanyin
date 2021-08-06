package com.joyintech.tams.model.impairment.batch.pdcalculate.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto.ModEclResultProcess;

import java.util.List;

/**
 * @Description:资产交易信息Dto
 * @auther: yuzj
 * @date: 2021/7/15
 * @param: 
 * @return: 
 */
public class AssetGroupPdCalculateReaderDto extends NeedOperateEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;


    /**
     * 交易信息List
     */
    private List<ModEclResultProcess> assetImpairmentPdCalculateList;

    public List<ModEclResultProcess> getAssetImpairmentPdCalculateList() {
        return assetImpairmentPdCalculateList;
    }

    public void setAssetImpairmentPdCalculateList(List<ModEclResultProcess> assetImpairmentPdCalculateList) {
        this.assetImpairmentPdCalculateList = assetImpairmentPdCalculateList;
    }
}
