package com.joyintech.service.trade.manualsettle.dto;

import com.joyintech.tams.common.base.entity.TrdManualSettleFlow;

/**
 * @author zhangnian
 * @ClassName ManualSettleFlowDto
 * @description TODO
 * @date 2020/10/22 0022 14:05
 **/
public class ManualSettleFlowDto extends TrdManualSettleFlow {

    /**
     * 市场代码
     */
    private String finprodMarketId;

    /**
     * 金融产品全称
     */
    private String finprodName;
    private String operator;

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}

