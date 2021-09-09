package com.joyintech.tams.model.impairment.batch.impairmentdataload.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.ModConfigUseage;
import com.joyintech.tams.common.base.entity.ModEclResultProcess;
import com.joyintech.tams.common.base.entity.ModValBatchParams;

public class ImpairmentDataLoadStep1ResultDto extends NeedToWriteEntity {

    /**
     * 减值配置使用情况表
     */
    private ModConfigUseage modConfigUseage;
    /**
     * 减值结果过程表
     */
    private ModEclResultProcess modEclResultProcess;

    /**
     * 待处理产品参数表
     */
    private ModValBatchParams modValBatchParams;

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

    /**
     * @return 待处理产品参数表
     */
    public ModValBatchParams getModValBatchParams() {
        return modValBatchParams;
    }

    /**
     * 设置 待处理产品参数表
     *
     * @param modValBatchParams 待处理产品参数表
     */
    public void setModValBatchParams(ModValBatchParams modValBatchParams) {
        this.modValBatchParams = modValBatchParams;
    }
}
