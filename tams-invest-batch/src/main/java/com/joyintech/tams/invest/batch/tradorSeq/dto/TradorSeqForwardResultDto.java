package com.joyintech.tams.invest.batch.tradorSeq.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.EtrSecPositionFlow;
import com.joyintech.tams.common.base.entity.PtlRecePositionFlow;
import com.joyintech.tams.common.base.entity.PtlSecPositionFlow;

import java.math.BigDecimal;
import java.util.List;

public class TradorSeqForwardResultDto extends NeedToWriteEntity {
    private String userId;
    private BigDecimal seq;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getSeq() {
        return seq;
    }

    public void setSeq(BigDecimal seq) {
        this.seq = seq;
    }
}
