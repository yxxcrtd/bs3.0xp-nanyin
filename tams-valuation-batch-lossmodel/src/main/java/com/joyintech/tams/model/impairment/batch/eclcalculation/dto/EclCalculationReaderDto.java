package com.joyintech.tams.model.impairment.batch.eclcalculation.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.common.dto.modelDto.ModEclResultProcess;

import java.util.List;
import java.util.Map;

/**
 * 二叉树模型-reader
 */
public class EclCalculationReaderDto extends NeedOperateEntity {

    /**
     * 减值结果过程集合<账套代码,减值结果过程明细>
     */
    private List<ModEclResultProcess> modEclResultProcessList;

    /**
     * @return modEclResultProcessList
     */
    public List<ModEclResultProcess> getModEclResultProcessList() {
        return modEclResultProcessList;
    }

    /**
     * 设置 modEclResultProcessList
     *
     * @param modEclResultProcessList modEclResultProcessList
     */
    public void setModEclResultProcessList(List<ModEclResultProcess> modEclResultProcessList) {
        this.modEclResultProcessList = modEclResultProcessList;
    }
}
