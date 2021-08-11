package com.joyintech.tams.model.impairment.batch.eclcalculation.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.common.dto.modelDto.ModEclResultProcess;

public class EclCalculationResultDto extends NeedToWriteEntity {

    /**
     * 减值配置使用情况表
     */
    private ModConfigUseage modConfigUseage;
    /**
     * 减值结果过程表
     */
    private ModEclResultProcess modEclResultProcess;
    /**
     * 资产减值结果表
     */
    private ModEclResult modEclResult;

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

    public ModEclResult getModEclResult() {
        return modEclResult;
    }

    public void setModEclResult(ModEclResult modEclResult) {
        this.modEclResult = modEclResult;
    }
}
