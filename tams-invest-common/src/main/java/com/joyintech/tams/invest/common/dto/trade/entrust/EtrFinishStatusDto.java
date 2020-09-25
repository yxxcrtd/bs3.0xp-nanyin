package com.joyintech.tams.invest.common.dto.trade.entrust;

import com.joyintech.tams.common.base.entity.EtrFinishStatus;

/**
 * @ClassName EtrFinishStatusDto
 * @Description: TODO
 * @Author wangxuelei
 * @Date 2020/2/18 0018
 * @Version V1.0
 **/
public class EtrFinishStatusDto extends EtrFinishStatus {
    private String cstpTradeId;

    public String getCstpTradeId() {
        return cstpTradeId;
    }

    public void setCstpTradeId(String cstpTradeId) {
        this.cstpTradeId = cstpTradeId;
    }
}
