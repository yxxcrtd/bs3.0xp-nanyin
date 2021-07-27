package com.joyintech.service.risk.fundgoal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Classname FundGoalDto
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/11/3 13:34 
 * @version 1.0 
 */
public class FundGoalDto extends BaseEntity implements ProcessEntity {



    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String elementId;

    /**
     * 选中的数据
     */
    private transient List<FundGoalDto> selectedItem;


    private String finprodAbbr;


    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 金融产品代码
     */
    private String finprodId;


    /**
     * 资产代码
     */
    private String finprodMarket;

    /**
     * 投向维护日
     */
    private Date goalDate;


    /**
     * 下一投向公布日
     */
    private Date nextPubDate;

    /**
     *权益类占比
     */
    private BigDecimal equityPer;

    /**
     *债券类占比
     */
    private BigDecimal creditorRightsPer;

    /**
     *金融及衍生品类占比
     */
    private BigDecimal commodityDervPer;

    /**
     *负债占比
     */
    private BigDecimal debtPer;

    /**
     * 权益类占比转换前数值
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal bEquityPer;

    /**
     * 债权类占比转换前数值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bCreditorRightsPer;

    /**
     * 商品及衍生品占比转换前数值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bCommodityDervPer;

    /**
     * 负债占比转换前数值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bDebtPer;


    private String goalDateStr;

    public String getGoalDateStr() {
        return goalDateStr;
    }

    public void setGoalDateStr(String goalDateStr) {
        this.goalDateStr = goalDateStr;
    }

    public String getFinprodMarket() {
        return finprodMarket;
    }

    public void setFinprodMarket(String finprodMarket) {
        this.finprodMarket = finprodMarket;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public List<FundGoalDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<FundGoalDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public Date getGoalDate() {
        return goalDate;
    }


    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public void setGoalDate(Date goalDate) {
        this.goalDate = goalDate;
    }

    public Date getNextPubDate() {
        return nextPubDate;
    }

    public void setNextPubDate(Date nextPubDate) {
        this.nextPubDate = nextPubDate;
    }

    public BigDecimal getEquityPer() {
        return equityPer;
    }

    public void setEquityPer(BigDecimal equityPer) {
        this.equityPer = equityPer;
    }

    public BigDecimal getCreditorRightsPer() {
        return creditorRightsPer;
    }

    public void setCreditorRightsPer(BigDecimal creditorRightsPer) {
        this.creditorRightsPer = creditorRightsPer;
    }

    public BigDecimal getCommodityDervPer() {
        return commodityDervPer;
    }

    public void setCommodityDervPer(BigDecimal commodityDervPer) {
        this.commodityDervPer = commodityDervPer;
    }

    public BigDecimal getDebtPer() {
        return debtPer;
    }

    public void setDebtPer(BigDecimal debtPer) {
        this.debtPer = debtPer;
    }

    public BigDecimal getbEquityPer() {
        return bEquityPer;
    }

    public void setbEquityPer(BigDecimal bEquityPer) {
        this.bEquityPer = bEquityPer;
    }

    public BigDecimal getbCreditorRightsPer() {
        return bCreditorRightsPer;
    }

    public void setbCreditorRightsPer(BigDecimal bCreditorRightsPer) {
        this.bCreditorRightsPer = bCreditorRightsPer;
    }

    public BigDecimal getbCommodityDervPer() {
        return bCommodityDervPer;
    }

    public void setbCommodityDervPer(BigDecimal bCommodityDervPer) {
        this.bCommodityDervPer = bCommodityDervPer;
    }

    public BigDecimal getbDebtPer() {
        return bDebtPer;
    }

    public void setbDebtPer(BigDecimal bDebtPer) {
        this.bDebtPer = bDebtPer;
    }
}

