package com.joyintech.tams.model.impairment.batch.eclcalculation.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.common.dto.modelDto.ModEclResultProcess;

/**
 * 二叉树模型-reader
 */
public class EclCalculationReaderDto extends NeedOperateEntity {

    /**
     * 减值结果过程表
     */
    private ModEclResultProcess modEclResultProcess;


    public ModEclResultProcess getModEclResultProcess() {
        return modEclResultProcess;
    }

    public void setModEclResultProcess(ModEclResultProcess modEclResultProcess) {
        this.modEclResultProcess = modEclResultProcess;
    }
}
