package com.joyintech.tams.model.impairment.batch.datapusheclresult.dto;


import java.util.Date;
import java.util.List;

/**
 * 减值批处理同步参数
 */
public class ImpairmentBatchSynParam {
    /**
     *跑批日期
     */
    private Date synDate;
    /**
     * 资产减值结果
     */
    private List<AssetLossResult> synList;

    public Date getSynDate() {
        return synDate;
    }

    public void setSynDate(Date synDate) {
        this.synDate = synDate;
    }

    public List<AssetLossResult> getSynList() {
        return synList;
    }

    public void setSynList(List<AssetLossResult> synList) {
        this.synList = synList;
    }
}
