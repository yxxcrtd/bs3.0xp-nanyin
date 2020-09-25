package com.joyintech.tams.invest.batch.tradorSeq.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

import java.math.BigDecimal;

/**
 * @ClassName TradorSeqForwardDto
 * @Description: TODO
 * @Author wangxuelei
 * @Date 2020/1/6 0006
 * @Version V1.0
 **/
public class TradorSeqForwardDto extends NeedOperateEntity {

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
