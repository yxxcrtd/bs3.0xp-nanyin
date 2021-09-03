package com.joyintech.tams.model.impairment.batch.impairmentdataload.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.tams.common.base.entity.ModConfigUseage;
import com.joyintech.tams.common.base.entity.ModEclResultProcess;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 二叉树模型-reader
 */
public class ImpairmentDataLoadStep2ReaderDto extends NeedOperateEntity {

    /**
     * 减值结果过程
     */
    private ModEclResultProcess modEclResultProcess;

    /**
     * 减值配置使用情况表
     */
    private ModConfigUseage modConfigUseage;

    /**
     * @return 模型配置使用情况
     */
    public ModConfigUseage getModConfigUseage() {
        return modConfigUseage;
    }

    /**
     * 设置 模型配置使用情况
     *
     * @param modConfigUseage 模型配置使用情况
     */
    public void setModConfigUseage(ModConfigUseage modConfigUseage) {
        this.modConfigUseage = modConfigUseage;
    }

    /**
     * @return 减值结果过程
     */
    public ModEclResultProcess getModEclResultProcess() {
        return modEclResultProcess;
    }

    /**
     * 设置 减值结果过程
     *
     * @param modEclResultProcess 减值结果过程
     */
    public void setModEclResultProcess(ModEclResultProcess modEclResultProcess) {
        this.modEclResultProcess = modEclResultProcess;
    }
}
