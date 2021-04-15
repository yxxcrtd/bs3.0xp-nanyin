package com.joyintech.tams.invest.trade.manualsettle.dto;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.TrdManualSettleFlow;
import com.joyintech.tams.constant.DictCode;

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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

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

    public String getOperTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.交收操作类型, this.getOperType());
    }

    public String getSettleTimeStr(){
        if(null!=super.getSettleTime()){
            return DateUtil.format(super.getSettleTime(),"HH:mm:ss");
        }
        return StringUtil.EMPTY;
    }

    public String getSettleDateStr(){
        if(null!=super.getSettleTime()){
            return DateUtil.format(super.getSettleTime(),"yyyy-MM-dd");
        }
        return StringUtil.EMPTY;
    }
}

