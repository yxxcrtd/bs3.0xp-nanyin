package com.joyintech.service.trade.transfer.fund.dto;

import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.List;

public class FundAssetTransfer001Dto extends TrdProductDeal implements ProcessEntity {
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 元素Id
     */
    private String elementId;
    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     *  投资过度组合
     */
    private String invTransPortf;

    /**
     *  交易日历
     */
    private String calendarId;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 数据状态
     */
    private String status;

    /**
     * 画面模式
     */
    private String pageModel;

    /**
     * 交易份额
     */
    private BigDecimal tradePrin;

    /**
     * 单位净值
     */
    private BigDecimal unitCprice;

    /**
     * 待结转份额
     */
    private BigDecimal carryOverQty;

    /**
     * 交易金额
     */
    private BigDecimal tradeAmt;

    /**
     * 持仓份额
     */
    private BigDecimal tradePrinOrg;

    /**
     * 剩余可转仓份额
     */
    private BigDecimal rollablePrin;
    /**
     * 组合代码名称
     */
    private String portfolioName;

    /**
     * 实际组合
     */
    private String actPortfolioId;

    /**
     * 资产代码
     */
    private String finprodMarketId;

    /**
     * 资产简称
     */
    private String finprodName;

    /**
     * 金融产品代码
     */
    private String finprodIdOrg;

    /**
     * 资产类型
     */
    private String assetType;

    /**
     * 转仓列表
     */
    private List<FundAssetTransfer001Dto> funAssetTransferDtoList;

    /**
     * Gets the value of operateType.
     *
     * @return the value of operateType
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * Sets the operateType.
     *
     * <p>You can use getOperateType() to get the value of operateType</p>
     *
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * Gets the value of elementId.
     *
     * @return the value of elementId
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * Sets the elementId.
     *
     * <p>You can use getElementId() to get the value of elementId</p>
     *
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * Gets the value of invTransPortf.
     *
     * @return the value of invTransPortf
     */
    public String getInvTransPortf() {
        return invTransPortf;
    }

    /**
     * Sets the invTransPortf.
     *
     * <p>You can use getInvTransPortf() to get the value of invTransPortf</p>
     *
     * @param invTransPortf invTransPortf
     */
    public void setInvTransPortf(String invTransPortf) {
        this.invTransPortf = invTransPortf;
    }

    /**
     * Gets the value of calendarId.
     *
     * @return the value of calendarId
     */
    public String getCalendarId() {
        return calendarId;
    }

    /**
     * Sets the calendarId.
     *
     * <p>You can use getCalendarId() to get the value of calendarId</p>
     *
     * @param calendarId calendarId
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    /**
     * Gets the value of workflowId.
     *
     * @return the value of workflowId
     */
    @Override
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * Sets the workflowId.
     *
     * <p>You can use getWorkflowId() to get the value of workflowId</p>
     *
     * @param workflowId workflowId
     */
    @Override
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    /**
     * Gets the value of status.
     *
     * @return the value of status
     */
    @Override
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * <p>You can use getStatus() to get the value of status</p>
     *
     * @param status status
     */
    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the value of pageModel.
     *
     * @return the value of pageModel
     */
    public String getPageModel() {
        return pageModel;
    }

    /**
     * Sets the pageModel.
     *
     * <p>You can use getPageModel() to get the value of pageModel</p>
     *
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    /**
     * Gets the value of tradePrin.
     *
     * @return the value of tradePrin
     */
    public BigDecimal getTradePrin() {
        return tradePrin;
    }

    /**
     * Sets the tradePrin.
     *
     * <p>You can use getTradePrin() to get the value of tradePrin</p>
     *
     * @param tradePrin tradePrin
     */
    public void setTradePrin(BigDecimal tradePrin) {
        this.tradePrin = tradePrin;
    }

    /**
     * Gets the value of unitCprice.
     *
     * @return the value of unitCprice
     */
    @Override
    public BigDecimal getUnitCprice() {
        return unitCprice;
    }

    /**
     * Sets the unitCprice.
     *
     * <p>You can use getUnitCprice() to get the value of unitCprice</p>
     *
     * @param unitCprice unitCprice
     */
    @Override
    public void setUnitCprice(BigDecimal unitCprice) {
        this.unitCprice = unitCprice;
    }

    /**
     * Gets the value of carryOverQty.
     *
     * @return the value of carryOverQty
     */
    public BigDecimal getCarryOverQty() {
        return carryOverQty;
    }

    /**
     * Sets the carryOverQty.
     *
     * <p>You can use getCarryOverQty() to get the value of carryOverQty</p>
     *
     * @param carryOverQty carryOverQty
     */
    public void setCarryOverQty(BigDecimal carryOverQty) {
        this.carryOverQty = carryOverQty;
    }

    /**
     * Gets the value of tradeAmt.
     *
     * @return the value of tradeAmt
     */
    @Override
    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    /**
     * Sets the tradeAmt.
     *
     * <p>You can use getTradeAmt() to get the value of tradeAmt</p>
     *
     * @param tradeAmt tradeAmt
     */
    @Override
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    /**
     * Gets the value of tradePrinOrg.
     *
     * @return the value of tradePrinOrg
     */
    public BigDecimal getTradePrinOrg() {
        return tradePrinOrg;
    }

    /**
     * Sets the tradePrinOrg.
     *
     * <p>You can use getTradePrinOrg() to get the value of tradePrinOrg</p>
     *
     * @param tradePrinOrg tradePrinOrg
     */
    public void setTradePrinOrg(BigDecimal tradePrinOrg) {
        this.tradePrinOrg = tradePrinOrg;
    }

    /**
     * Gets the value of rollablePrin.
     *
     * @return the value of rollablePrin
     */
    public BigDecimal getRollablePrin() {
        return rollablePrin;
    }

    /**
     * Sets the rollablePrin.
     *
     * <p>You can use getRollablePrin() to get the value of rollablePrin</p>
     *
     * @param rollablePrin rollablePrin
     */
    public void setRollablePrin(BigDecimal rollablePrin) {
        this.rollablePrin = rollablePrin;
    }

    /**
     * Gets the value of portfolioName.
     *
     * @return the value of portfolioName
     */
    public String getPortfolioName() {
        return portfolioName;
    }

    /**
     * Sets the portfolioName.
     *
     * <p>You can use getPortfolioName() to get the value of portfolioName</p>
     *
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    /**
     * Gets the value of actPortfolioId.
     *
     * @return the value of actPortfolioId
     */
    public String getActPortfolioId() {
        return actPortfolioId;
    }

    /**
     * Sets the actPortfolioId.
     *
     * <p>You can use getActPortfolioId() to get the value of actPortfolioId</p>
     *
     * @param actPortfolioId actPortfolioId
     */
    public void setActPortfolioId(String actPortfolioId) {
        this.actPortfolioId = actPortfolioId;
    }

    /**
     * Gets the value of finprodMarketId.
     *
     * @return the value of finprodMarketId
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    /**
     * Sets the finprodMarketId.
     *
     * <p>You can use getFinprodMarketId() to get the value of finprodMarketId</p>
     *
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    /**
     * Gets the value of funAssetTransferDtoList.
     *
     * @return the value of funAssetTransferDtoList
     */
    public List<FundAssetTransfer001Dto> getFunAssetTransferDtoList() {
        return funAssetTransferDtoList;
    }

    /**
     * Sets the funAssetTransferDtoList.
     *
     * <p>You can use getFunAssetTransferDtoList() to get the value of funAssetTransferDtoList</p>
     *
     * @param funAssetTransferDtoList funAssetTransferDtoList
     */
    public void setFunAssetTransferDtoList(List<FundAssetTransfer001Dto> funAssetTransferDtoList) {
        this.funAssetTransferDtoList = funAssetTransferDtoList;
    }

    /**
     * Gets the value of finprodName.
     *
     * @return the value of finprodName
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * Sets the finprodName.
     *
     * <p>You can use getFinprodName() to get the value of finprodName</p>
     *
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    /**
     * Gets the value of finprodIdOrg.
     *
     * @return the value of finprodIdOrg
     */
    public String getFinprodIdOrg() {
        return finprodIdOrg;
    }

    /**
     * Sets the finprodIdOrg.
     *
     * <p>You can use getFinprodIdOrg() to get the value of finprodIdOrg</p>
     *
     * @param finprodIdOrg finprodIdOrg
     */
    public void setFinprodIdOrg(String finprodIdOrg) {
        this.finprodIdOrg = finprodIdOrg;
    }

    /**
     * Gets the value of assetType.
     *
     * @return the value of assetType
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * Sets the assetType.
     *
     * <p>You can use getAssetType() to get the value of assetType</p>
     *
     * @param assetType assetType
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    /**
     * Gets the value of portfolioId.
     *
     * @return the value of portfolioId
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * Sets the portfolioId.
     *
     * <p>You can use getPortfolioId() to get the value of portfolioId</p>
     *
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }
}
