package com.joyintech.tams.model.impairment.batch.eclcalculation.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.common.dto.modelDto.ModEclResultProcess;

import java.util.List;

public class EclCalculationResultDto extends NeedToWriteEntity {

    /**
     * 减值配置使用情况表
     */
    private List<ModConfigUseage> modConfigUseageList;
    /**
     * 减值结果过程表
     */
    private List<ModEclResultProcess> modEclResultProcessList;
    /**
     * 资产减值结果表
     */
    private List<ModEclResult> modEclResultList;

    /**
     * @return 减值配置使用情况表
     */
    public List<ModConfigUseage> getModConfigUseageList() {
        return modConfigUseageList;
    }

    /**
     * 设置 减值配置使用情况表
     *
     * @param modConfigUseageList 减值配置使用情况表
     */
    public void setModConfigUseageList(List<ModConfigUseage> modConfigUseageList) {
        this.modConfigUseageList = modConfigUseageList;
    }

    /**
     * @return 减值结果过程表
     */
    public List<ModEclResultProcess> getModEclResultProcessList() {
        return modEclResultProcessList;
    }

    /**
     * 设置 减值结果过程表
     *
     * @param modEclResultProcessList 减值结果过程表
     */
    public void setModEclResultProcessList(List<ModEclResultProcess> modEclResultProcessList) {
        this.modEclResultProcessList = modEclResultProcessList;
    }

    /**
     * @return 减值配置使用情况表
     */
    public List<ModEclResult> getModEclResultList() {
        return modEclResultList;
    }

    /**
     * 设置 减值配置使用情况表
     *
     * @param modEclResultList 减值配置使用情况表
     */
    public void setModEclResultList(List<ModEclResult> modEclResultList) {
        this.modEclResultList = modEclResultList;
    }
}
