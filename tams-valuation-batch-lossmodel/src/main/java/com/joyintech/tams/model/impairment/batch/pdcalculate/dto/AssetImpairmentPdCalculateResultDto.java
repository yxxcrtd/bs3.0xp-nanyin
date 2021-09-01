package com.joyintech.tams.model.impairment.batch.pdcalculate.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.ModEclResultProcess;

import java.util.List;

/**
 * @Description:process返回结果
 * 返回结果1：减值结果过程Dto
 * 返回结果2：减值配置使用情况Dto
 * @auther: yuzj
 * @date: 2021/7/15
 * @param: 
 * @return: 
 */
public class AssetImpairmentPdCalculateResultDto extends NeedToWriteEntity {


    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 减值结果过程list
     */
    private List<ModEclResultProcess> modEclResultProcessResultList;

    /**
     * 减值配置使用情况list
     */
    private List<ModConfigUseageResultDto> modConfigUseageResultList;








    public List<ModEclResultProcess> getModEclResultProcessResultList() {
        return modEclResultProcessResultList;
    }

    public void setModEclResultProcessResultList(List<ModEclResultProcess> modEclResultProcessResultList) {
        this.modEclResultProcessResultList = modEclResultProcessResultList;
    }

    public List<ModConfigUseageResultDto> getModConfigUseageResultList() {
        return modConfigUseageResultList;
    }

    public void setModConfigUseageResultList(List<ModConfigUseageResultDto> modConfigUseageResultList) {
        this.modConfigUseageResultList = modConfigUseageResultList;
    }

}
