package com.joyintech.tams.cfets.bankuplink.repo.dto;

import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.invest.trade.lend.dto.FinTradeProductDto;

import java.util.List;

/**
 * 入库dto
 * @author zznn
 * @date 2021/11/12
 */
public class CfetsRepoDealDbDto {

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 操作方式
     */
    private String operationType;

    /**
     * 汇总登记编号
     */
    private String totleTradeId;

    private RegFinProductDealTotal regFinProductDealTotal;

    private RegFinProductDealTotalAdd regFinProductDealTotalAdd;

    private List<RegTradeFeeTotal> regTradeFeeTotals;

    private List<RegFinProductDeal> regFinProductDeals;

    private List<RegFinProductDealAdd> regFinProductDealAdds;

    private List<RegTradeFee> regTradeFees;

    private List<RegFinPledge> regFinPledges;
    /**
     * 交易类金融产品Entity
     */
    FinTradeProductDto finTradeProduct;

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getTotleTradeId() {
        return totleTradeId;
    }

    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId;
    }

    public RegFinProductDealTotal getRegFinProductDealTotal() {
        return regFinProductDealTotal;
    }

    public void setRegFinProductDealTotal(RegFinProductDealTotal regFinProductDealTotal) {
        this.regFinProductDealTotal = regFinProductDealTotal;
    }

    public RegFinProductDealTotalAdd getRegFinProductDealTotalAdd() {
        return regFinProductDealTotalAdd;
    }

    public void setRegFinProductDealTotalAdd(RegFinProductDealTotalAdd regFinProductDealTotalAdd) {
        this.regFinProductDealTotalAdd = regFinProductDealTotalAdd;
    }

    public List<RegTradeFeeTotal> getRegTradeFeeTotals() {
        return regTradeFeeTotals;
    }

    public void setRegTradeFeeTotals(List<RegTradeFeeTotal> regTradeFeeTotals) {
        this.regTradeFeeTotals = regTradeFeeTotals;
    }

    public List<RegFinProductDeal> getRegFinProductDeals() {
        return regFinProductDeals;
    }

    public void setRegFinProductDeals(List<RegFinProductDeal> regFinProductDeals) {
        this.regFinProductDeals = regFinProductDeals;
    }

    public List<RegFinProductDealAdd> getRegFinProductDealAdds() {
        return regFinProductDealAdds;
    }

    public void setRegFinProductDealAdds(List<RegFinProductDealAdd> regFinProductDealAdds) {
        this.regFinProductDealAdds = regFinProductDealAdds;
    }

    public List<RegTradeFee> getRegTradeFees() {
        return regTradeFees;
    }

    public void setRegTradeFees(List<RegTradeFee> regTradeFees) {
        this.regTradeFees = regTradeFees;
    }

    public List<RegFinPledge> getRegFinPledges() {
        return regFinPledges;
    }

    public void setRegFinPledges(List<RegFinPledge> regFinPledges) {
        this.regFinPledges = regFinPledges;
    }

    public FinTradeProductDto getFinTradeProduct() {
        return finTradeProduct;
    }

    public void setFinTradeProduct(FinTradeProductDto finTradeProduct) {
        this.finTradeProduct = finTradeProduct;
    }
}

