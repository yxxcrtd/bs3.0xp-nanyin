package com.joyintech.tams.valuation.batch.batchunistart.entity;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.SysBatchUnistart;
import com.joyintech.tams.common.base.entity.SysBatchUnistartDetail;

import java.util.List;

public class BatchUnistartDto extends NeedToWriteEntity {

    private SysBatchUnistart sysBatchUnistart;

    private List<SysBatchUnistartDetail> sysBatchUnistartDetailList;


    public SysBatchUnistart getSysBatchUnistart() {
        return sysBatchUnistart;
    }

    public void setSysBatchUnistart(SysBatchUnistart sysBatchUnistart) {
        this.sysBatchUnistart = sysBatchUnistart;
    }

    public List<SysBatchUnistartDetail> getSysBatchUnistartDetailList() {
        return sysBatchUnistartDetailList;
    }

    public void setSysBatchUnistartDetailList(List<SysBatchUnistartDetail> sysBatchUnistartDetailList) {
        this.sysBatchUnistartDetailList = sysBatchUnistartDetailList;
    }
}
