package com.joyintech.tams.model.impairment.batch.impairmentdataload.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto.ModEclResultProcess;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.ModConfigUseage;

public class ImpairmentDataLoadStep1ResultDto extends NeedToWriteEntity {

    /**
     * 减值配置使用情况表
     */
    private ModConfigUseage modConfigUseage;
    /**
     * 减值结果过程表
     */
    private ModEclResultProcess modEclResultProcess;

    public ModConfigUseage getModConfigUseage() {
        return modConfigUseage;
    }

    public void setModConfigUseage(ModConfigUseage modConfigUseage) {
        this.modConfigUseage = modConfigUseage;
    }

    public ModEclResultProcess getModEclResultProcess() {
        return modEclResultProcess;
    }

    public void setModEclResultProcess(ModEclResultProcess modEclResultProcess) {
        this.modEclResultProcess = modEclResultProcess;
    }
}
