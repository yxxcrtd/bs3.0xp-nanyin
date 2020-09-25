/**
 *
 */
package com.joyintech.tams.invest.common.dto.common;

import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.workflow.engine.constant.OperationType;

import java.util.List;

/**
 * @author ex_kjkfzx_heyy
 *
 */
public class TrdCommonWflParam {

    /**
     * 金融产品交易汇总业务流程表
     */
    private WflTrdFinProductDealTotal wflTrdFinProductDealTotal;

    /**
     * 划款指令业务流程表
     */
    private List<WflStlPayOrders> wflStlPayOrdersDtos;

    /**
     * 金融产品交易汇总扩展业务流程表
     */
    private WflPaTrdFinDealTotal wflPaTrdFinDealTotal;

    private List<WflTrdProductDealAdd> wflDealAdds;

    private List<WflPaTrdProductDeal> wflPaDeals;

    private List<WflTrdProductDealOth> wflTrdProductDealOth;
    /**
     * 金融产品交易业务流程表
     */
    private List<WflTrdProductDeal> wflTrdProductDeals;

    private FinProductTradeDto tradeDto;

    private OperationType type;

    private String workflowId;

    private Boolean isUpdate;
    public FinProductTradeDto getTradeDto() {
        return tradeDto;
    }

    public void setTradeDto(FinProductTradeDto tradeDto) {
        this.tradeDto = tradeDto;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public WflPaTrdFinDealTotal getWflPaTrdFinDealTotal() {
        return wflPaTrdFinDealTotal;
    }

    public void setWflPaTrdFinDealTotal(WflPaTrdFinDealTotal wflPaTrdFinDealTotal) {
        this.wflPaTrdFinDealTotal = wflPaTrdFinDealTotal;
    }

    public List<WflTrdProductDeal> getWflTrdProductDeals() {
        return wflTrdProductDeals;
    }

    public void setWflTrdProductDeals(List<WflTrdProductDeal> wflTrdProductDeals) {
        this.wflTrdProductDeals = wflTrdProductDeals;
    }

    public WflTrdFinProductDealTotal getWflTrdFinProductDealTotal() {
        return wflTrdFinProductDealTotal;
    }

    public void setWflTrdFinProductDealTotal(WflTrdFinProductDealTotal wflTrdFinProductDealTotal) {
        this.wflTrdFinProductDealTotal = wflTrdFinProductDealTotal;
    }

    public List<WflStlPayOrders> getWflStlPayOrdersDtos() {
        return wflStlPayOrdersDtos;
    }

    public void setWflStlPayOrdersDtos(List<WflStlPayOrders> wflStlPayOrdersDtos) {
        this.wflStlPayOrdersDtos = wflStlPayOrdersDtos;
    }

    public List<WflTrdProductDealAdd> getWflDealAdds() {
        return wflDealAdds;
    }

    public void setWflDealAdds(List<WflTrdProductDealAdd> wflDealAdds) {
        this.wflDealAdds = wflDealAdds;
    }

    public List<WflPaTrdProductDeal> getWflPaDeals() {
        return wflPaDeals;
    }

    public void setWflPaDeals(List<WflPaTrdProductDeal> wflPaDeals) {
        this.wflPaDeals = wflPaDeals;
    }

    /**
     * 获取 wflTrdProductDealOth
     *
     * @return wflTrdProductDealOth
     */
    public List<WflTrdProductDealOth> getWflTrdProductDealOth() {
        return wflTrdProductDealOth;
    }

    /**
     * 设置 wflTrdProductDealOth
     *
     * @param wflTrdProductDealOth wflTrdProductDealOth
     */
    public void setWflTrdProductDealOth(List<WflTrdProductDealOth> wflTrdProductDealOth) {
        this.wflTrdProductDealOth = wflTrdProductDealOth;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public Boolean getUpdate() {
        return isUpdate;
    }

    public void setUpdate(Boolean update) {
        isUpdate = update;
    }
}
