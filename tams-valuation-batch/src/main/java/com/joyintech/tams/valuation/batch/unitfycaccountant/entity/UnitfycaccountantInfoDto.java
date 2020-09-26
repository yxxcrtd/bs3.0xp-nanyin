package com.joyintech.tams.valuation.batch.unitfycaccountant.entity;

import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.SysUnaccountantInfo;
import com.joyintech.tams.common.base.entity.SysUnaccountantInfoDetail;

public class UnitfycaccountantInfoDto extends NeedToWriteEntity {
    /**
     * 待写入的List
     */
    private List<SysUnaccountantInfoDetail> list;

    // 一键做账任务
    private SysUnaccountantInfo SysUcaccountantInfo;

    public SysUnaccountantInfo getSysUcaccountantInfo() {
        return SysUcaccountantInfo;
    }

    public void setSysUcaccountantInfo(SysUnaccountantInfo sysUcaccountantInfo) {
        SysUcaccountantInfo = sysUcaccountantInfo;
    }

    /**
     * @return 待写入的List
     */
    public List<SysUnaccountantInfoDetail> getList() {
        return list;
    }


    /**
     *
     * @param list 待写入的List
     */
    public void setList(List<SysUnaccountantInfoDetail> list) {
        this.list = list;
    }
}
