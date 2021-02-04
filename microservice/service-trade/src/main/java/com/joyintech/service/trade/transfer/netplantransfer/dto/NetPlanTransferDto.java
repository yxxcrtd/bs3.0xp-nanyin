package com.joyintech.service.trade.transfer.netplantransfer.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/***
 * 净值型计划资产转仓Dto
 *
 * @author liuwei
 * @version 1.0 2019年12月23日
 * @see NetPlanTransferDto
 * @since 1.0
 */
public class NetPlanTransferDto extends BaseEntity implements ProcessEntity {
    /**
     * 交易日期
     */
    private Date tradeDate;
    /**
     * 资产代码
     */
    private String finprodMarketId;
    /**
     * 资产名称
     */
    private String finprodAbbr;
    /**
     * 币种
     */
    private String ccy;
    /**
     * 通道名称
     */
    private String chlName;
    /**
     * 通道代码
     */
    private String chlId;
    /**
     * 持仓份额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal posAmt;
    /**
     * 剩余可转仓份额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal undistributedAmount;
    /**
     * 单位净值
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal unitValue;
    /**
     * 转仓列表dtoList
     */
    private List<NetPlanTransferDto002> netPlanTransfer002DtoList;

    /**
     * 获取 交易日期
     *
     * @return tradeDate 交易日期
     */
    public Date getTradeDate() {
        return this.tradeDate;
    }

    /**
     * 设置 交易日期
     *
     * @param tradeDate 交易日期
     */
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * 获取 资产代码
     *
     * @return finprodMarketId 资产代码
     */
    public String getFinprodMarketId() {
        return this.finprodMarketId;
    }

    /**
     * 设置 资产代码
     *
     * @param finprodMarketId 资产代码
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    /**
     * 获取 资产名称
     *
     * @return finprodAbbr 资产名称
     */
    public String getFinprodAbbr() {
        return this.finprodAbbr;
    }

    /**
     * 设置 资产名称
     *
     * @param finprodAbbr 资产名称
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    /**
     * 获取 币种
     *
     * @return ccy 币种
     */
    public String getCcy() {
        return this.ccy;
    }

    /**
     * 设置 币种
     *
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 通道名称
     *
     * @return chlName 通道名称
     */
    public String getChlName() {
        return this.chlName;
    }

    /**
     * 设置 通道名称
     *
     * @param chlName 通道名称
     */
    public void setChlName(String chlName) {
        this.chlName = chlName;
    }

    /**
     * 获取 通道代码
     *
     * @return chlId 通道代码
     */
    public String getChlId() {
        return this.chlId;
    }

    /**
     * 设置 通道代码
     *
     * @param chlId 通道代码
     */
    public void setChlId(String chlId) {
        this.chlId = chlId;
    }

    /**
     * 获取 持仓份额
     *
     * @return posAmt 持仓份额
     */
    public BigDecimal getPosAmt() {
        return this.posAmt;
    }

    /**
     * 设置 持仓份额
     *
     * @param posAmt 持仓份额
     */
    public void setPosAmt(BigDecimal posAmt) {
        this.posAmt = posAmt;
    }

    /**
     * 获取 剩余可转仓份额
     *
     * @return undistributedAmount 剩余可转仓份额
     */
    public BigDecimal getUndistributedAmount() {
        return this.undistributedAmount;
    }

    /**
     * 设置 剩余可转仓份额
     *
     * @param undistributedAmount 剩余可转仓份额
     */
    public void setUndistributedAmount(BigDecimal undistributedAmount) {
        this.undistributedAmount = undistributedAmount;
    }

    /**
     * 获取 单位净值
     *
     * @return unitValue 单位净值
     */
    public BigDecimal getUnitValue() {
        return this.unitValue;
    }

    /**
     * 设置 单位净值
     *
     * @param unitValue 单位净值
     */
    public void setUnitValue(BigDecimal unitValue) {
        this.unitValue = unitValue;
    }

    /**
     * 获取 转仓列表dtoList
     *
     * @return netPlanTransfer002DtoList 转仓列表dtoList
     */
    public List<NetPlanTransferDto002> getNetPlanTransfer002DtoList() {
        return this.netPlanTransfer002DtoList;
    }

    /**
     * 设置 转仓列表dtoList
     *
     * @param netPlanTransfer002DtoList 转仓列表dtoList
     */
    public void setNetPlanTransfer002DtoList(List<NetPlanTransferDto002> netPlanTransfer002DtoList) {
        this.netPlanTransfer002DtoList = netPlanTransfer002DtoList;
    }
}
