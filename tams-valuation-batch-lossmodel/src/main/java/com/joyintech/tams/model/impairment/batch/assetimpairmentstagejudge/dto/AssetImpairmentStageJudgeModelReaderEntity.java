package com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.common.dto.modelDto.ModCashFlowDto;
import com.joyintech.tams.common.base.entity.ModEclResultProcess;

import java.util.List;


/**
 * 资产减值阶段判断-reader
 */
public class AssetImpairmentStageJudgeModelReaderEntity extends NeedOperateEntity {
    /**
     * 减值结果过程表
     */
    private ModEclResultProcess modEclResultProcess;

    /**
     * 获取现金流计划
     */
    private List<ModCashFlowDto> modCashFlowList;

    public ModEclResultProcess getModEclResultProcess() {
        return modEclResultProcess;
    }

    public void setModEclResultProcess(ModEclResultProcess modEclResultProcess) {
        this.modEclResultProcess = modEclResultProcess;
    }

    public List<ModCashFlowDto> getModCashFlowList() {
        return modCashFlowList;
    }

    public void setModCashFlowList(List<ModCashFlowDto> modCashFlowList) {
        this.modCashFlowList = modCashFlowList;
    }
}
