/*
 * 文件名：PtlSubFundsMange002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：adrian.zhou
 * 修改时间：2019年8月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlsubfundsmange.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.portfolio.dto.FinSubFundsDealDbDataDto;
import com.joyintech.tams.invest.portfolio.ptlsubfundsmange.validator.PtlSubFundsAllocate002Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author adrian.zhou
 * @version 1.0 2019年8月15日
 * @see PtlSubFundsAllocate002Dto
 * @since 1.0
 */
@ValidLogic(targetClass = PtlSubFundsAllocate002Validator.class, groups = {
        ValidateGroupEdit.class, ValidateGroupDelete.class})
public class PtlSubFundsAllocate002Dto extends BaseEntity implements ITableRow, ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -4363771038520424070L;
    /**
     * 交易编号
     */
    private String tradeId;
    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 子组合代码
     */
    private String subPortfolioId;
    /**
     * 子组合名称
     */
    private String subPortfolioName;
    /**
     * 继承比例
     */
    private BigDecimal disPercent;
    /**
     * 备注
     */
    private String remark;
    /**
     * 调配日期
     */
    private String settleDate;
    /**
     * 币种
     */
    private String ccy;
    /**
     * 调配金额
     */
    private BigDecimal tradeAmt;
    /**
     * 调配备注
     */
    /**
     * 实际现金
     */
    private BigDecimal actualAmt;
    /**
     * 可用现金
     */
    private BigDecimal avaAmt;
    /**
     * 调配后实际现金
     */
    private BigDecimal ajustActualAmt;
    /**
     * 调配后可用现金
     */
    private BigDecimal ajustAvaAmt;

    private List<PtlSubFundsAllocate002Dto> selectedItem;

    private String workflowStatus;

    private String parentPortfolioId;

    public String getParentPortfolioId() {
        return parentPortfolioId;
    }

    public void setParentPortfolioId(String parentPortfolioId) {
        this.parentPortfolioId = parentPortfolioId;
    }

    /**
     * 操作类型
     */
    private String operateType;

    private String elementId;

    private String finprodId;

    private List<FinSubFundsDealDbDataDto> subFundsDealDbDataList;

    public List<FinSubFundsDealDbDataDto> getSubFundsDealDbDataList() {
        return subFundsDealDbDataList;
    }

    public void setSubFundsDealDbDataList(List<FinSubFundsDealDbDataDto> subFundsDealDbDataList) {
        this.subFundsDealDbDataList = subFundsDealDbDataList;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public List<PtlSubFundsAllocate002Dto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<PtlSubFundsAllocate002Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    @PrimaryKey(Field.PK_2)
    public String getBusiType() {
        return DictCodeList.共通业务类型_子组合资金调配.getValue();
    }

    public String getWorkflowStatus() {
        return workflowStatus;
    }

    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public BigDecimal getAjustActualAmt() {
        return ajustActualAmt;
    }

    public void setAjustActualAmt(BigDecimal ajustActualAmt) {
        this.ajustActualAmt = ajustActualAmt;
    }

    public BigDecimal getAjustAvaAmt() {
        return ajustAvaAmt;
    }

    public void setAjustAvaAmt(BigDecimal ajustAvaAmt) {
        this.ajustAvaAmt = ajustAvaAmt;
    }

    public BigDecimal getActualAmt() {
        return actualAmt;
    }

    public void setActualAmt(BigDecimal actualAmt) {
        this.actualAmt = actualAmt;
    }

    public BigDecimal getAvaAmt() {
        return avaAmt;
    }

    public void setAvaAmt(BigDecimal avaAmt) {
        this.avaAmt = avaAmt;
    }

    private String deployrRemark;

    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    @PrimaryKey(PrimaryKey.Field.PK_3)
    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getSubPortfolioId() {
        return subPortfolioId;
    }

    public void setSubPortfolioId(String subPortfolioId) {
        this.subPortfolioId = subPortfolioId;
    }

    public String getSubPortfolioName() {
        return subPortfolioName;
    }

    public void setSubPortfolioName(String subPortfolioName) {
        this.subPortfolioName = subPortfolioName;
    }

    public BigDecimal getDisPercent() {
        return disPercent;
    }

    public void setDisPercent(BigDecimal disPercent) {
        this.disPercent = disPercent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public String getDeployrRemark() {
        return deployrRemark;
    }

    public void setDeployrRemark(String deployrRemark) {
        this.deployrRemark = deployrRemark;
    }


}
