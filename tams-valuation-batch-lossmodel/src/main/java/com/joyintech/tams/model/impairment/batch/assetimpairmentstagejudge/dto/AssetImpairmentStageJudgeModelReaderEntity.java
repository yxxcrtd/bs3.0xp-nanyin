package com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.common.base.entity.ModAssetGradeConfig;
import com.joyintech.tams.common.base.entity.ModAssetGradeSpecialConfig;
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
     * 资产评级配置
     */
    private ModAssetGradeConfig modAssetGradeConfig;

    /**
     * 特殊资产评级配置
     */
    private ModAssetGradeSpecialConfig modAssetGradeSpecialConfig;

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

    /**
     * @return 资产评级配置
     */
    public ModAssetGradeConfig getModAssetGradeConfig() {
        return modAssetGradeConfig;
    }

    /**
     * 设置 资产评级配置
     *
     * @param modAssetGradeConfig 资产评级配置
     */
    public void setModAssetGradeConfig(ModAssetGradeConfig modAssetGradeConfig) {
        this.modAssetGradeConfig = modAssetGradeConfig;
    }

    /**
     * @return 特殊资产评级配置
     */
    public ModAssetGradeSpecialConfig getModAssetGradeSpecialConfig() {
        return modAssetGradeSpecialConfig;
    }

    /**
     * 设置 特殊资产评级配置
     *
     * @param modAssetGradeSpecialConfig 特殊资产评级配置
     */
    public void setModAssetGradeSpecialConfig(ModAssetGradeSpecialConfig modAssetGradeSpecialConfig) {
        this.modAssetGradeSpecialConfig = modAssetGradeSpecialConfig;
    }
}
