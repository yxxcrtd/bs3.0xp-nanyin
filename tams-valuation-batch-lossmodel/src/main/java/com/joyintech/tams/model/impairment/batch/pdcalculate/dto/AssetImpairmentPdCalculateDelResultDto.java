package com.joyintech.tams.model.impairment.batch.pdcalculate.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.common.dto.modelDto.ModEclResultProcess;

public class AssetImpairmentPdCalculateDelResultDto extends NeedToWriteEntity {


    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

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
