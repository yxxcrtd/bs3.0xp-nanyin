package com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.ModEclResultProcess;

public class AssetImpairmentStageJudgeModelResultDto extends NeedToWriteEntity {
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
