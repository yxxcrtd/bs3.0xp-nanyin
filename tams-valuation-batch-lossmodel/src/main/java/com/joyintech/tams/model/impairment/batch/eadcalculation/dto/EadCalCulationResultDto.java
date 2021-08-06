package com.joyintech.tams.model.impairment.batch.eadcalculation.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.ModEclResultProcess;

import java.util.List;

/**
 * EAD计算结果对象
 */
public class EadCalCulationResultDto extends NeedToWriteEntity {
    /**
     * 资产代码
     */
    private String finprodId;
    /**
     * 返回结果
     */
    private List<EadModEclResultProcess> eadResultList;

    public List<EadModEclResultProcess> getEadResultList() {
        return eadResultList;
    }

    public void setEadResultList(List<EadModEclResultProcess> eadResultList) {
        this.eadResultList = eadResultList;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }
}
