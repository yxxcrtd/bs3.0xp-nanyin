package com.joyintech.tams.invest.risk.fundgoal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.risk.fundgoal.valid.FundGoalValidation;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Classname FundGoalDto
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/11/3 11:16 
 * @version 1.0 
 */
@PageId("FundGoal002")
@ValidLogic(targetClass = FundGoalValidation.class, groups = {ValidateGroupEdit.class, ValidateGroupDelete.class})
public class FundGoalDto extends BaseEntity implements ProcessEntity {



    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String elementId;


    /**
     * 选中的数据
     */
    private transient List<FundGoalDto> selectedItem;


    /**
     * 资产简称
     */
    @NotEmpty(fieldName = "FundGoal002.finprodAbbr",groups= {ValidateGroupEdit.class})
    private String finprodAbbr;


    /**
     * 资产代码
     */
    private String finprodMarket;



    /**
     * 操作类型
     */
    @PrimaryKey(value = PrimaryKey.Field.PK_3)
    private String operateType;


    @PrimaryKey(value = PrimaryKey.Field.PK_2)
    public String goalDateStr;

    private String pageId;

    private String pageModel;

    /**
     * 金融产品代码
     */
    @PrimaryKey(value = PrimaryKey.Field.PK_1)
    private String finprodId;



    /**
     * 投向维护日
     */
    private Date goalDate;


    /**
     * 下一投向公布日
     */
    @NotEmpty(fieldName = "FundGoal002.nextPubDate",groups= {ValidateGroupEdit.class})
    private Date nextPubDate;

    /**
     *权益类占比
     */
    private BigDecimal equityPer;

    /**
     *债权类占比
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
    @NotEmpty(fieldName = "FundGoal002.equityPer",groups= {ValidateGroupEdit.class})
    private BigDecimal bEquityPer;

    /**
     * 债权类占比转换前数值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    @NotEmpty(fieldName = "FundGoal002.creditorRightsPer",groups= {ValidateGroupEdit.class})
    private BigDecimal bCreditorRightsPer;

    /**
     * 商品及衍生品占比转换前数值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    @NotEmpty(fieldName = "FundGoal002.commodityDervPer",groups= {ValidateGroupEdit.class})
    private BigDecimal bCommodityDervPer;

    /**
     * 负债占比转换前数值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    @NotEmpty(fieldName = "FundGoal002.debtPer",groups= {ValidateGroupEdit.class})
    private BigDecimal bDebtPer;

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

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getFinprodMarket() {
        return finprodMarket;
    }

    public void setFinprodMarket(String finprodMarket) {
        this.finprodMarket = finprodMarket;
    }

    public List<FundGoalDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<FundGoalDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
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

    public String getGoalDateStr() {
        return DateUtil.format(this.getGoalDate(), "yyyy-MM-dd");
    }

    public void setGoalDateStr(String goalDateStr) {
        this.goalDateStr = goalDateStr;
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
}
