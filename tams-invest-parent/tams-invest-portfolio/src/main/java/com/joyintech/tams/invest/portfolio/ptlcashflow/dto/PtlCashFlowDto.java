/*
 * 文件名：PtlCashFlowDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlcashflow.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.portfolio.ptlcashflow.validate.PtlCashFlowValidator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 自定义现金流
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年10月26日
 * @see PtlCashFlowDto
 * @since 1.0
 */
@PageId("PtlCashFlow002")
@ValidLogic(targetClass = PtlCashFlowValidator.class, groups = {ValidateGroupEdit.class,
  ValidateGroupDelete.class})
public class PtlCashFlowDto extends TrdProductDeal implements ProcessEntity{
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 业务编号
     */
    private String busiNo;
    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 实体组合代码
     */
    private String actPortfolioId;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     *资产类型
     */
    private String finprodType2;
    /**
     *资产代码
     */
    private String finprodId;
    /**
     *市场代码
     */
    private String finprodMarketId;
    /**
     * 资产名称
     */
    private String finprodAbbr;
    /**
     * 类型
     */
    private String customCashType;
    /**
     * 界面ID
     */
    private String elementId;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * pageModel
     */
    private String pageModel;
    /**
     * type
     */
    private String type;
    /**
     * 选择项
     */
    private List<PtlCashFlowDto> selectedItem;
    /**
     * 划款指令
     */
    @Valid
    private List<BussTrdTransferOrderBase> bussTrdTransferOrder;
    @PrimaryKey(Field.PK_1)
    public String getBusiNo() {
        return busiNo;
    }
    public void setBusiNo(String busiNo) {
        this.busiNo = busiNo;
    }
    @NotEmpty(fieldName = "PtlCashFlow001.portfolioId", groups = {ValidateGroupEdit.class})
    public String getPortfolioId() {
        return portfolioId;
    }
    
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }
    public String getPortfolioName() {
        return portfolioName;
    }
    @NotEmpty(fieldName = "PtlCashFlow001.ps", groups = {ValidateGroupEdit.class})
    public String getPsStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
    }
    @NotEmpty(fieldName = "PtlCashFlow001.ccy", groups = {ValidateGroupEdit.class})
    public String getCcyStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
    }
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }
    @NotEmpty(fieldName = "PtlCashFlow001.settleDate", groups = {ValidateGroupEdit.class})
    public String getSettleDateStr() {
        return DateUtil.format(super.getSettleDate());
    }
    @NotEmpty(fieldName = "PtlCashFlow002.tradeAmt", groups = {ValidateGroupEdit.class})
    public BigDecimal getTradeAmt() {
        return super.getTradeAmt();
    }
    public String getFinprodType2() {
        return finprodType2;
    }
    public String getFinprodType2Str(){
        return SysDataUtil.getNameFromDataDict(DictCode.平安资产类型, finprodType2);
    }
    
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }
    
    public String getFinprodId() {
        return finprodId;
    }
    
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }
    
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }
    
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }
    @NotEmpty(fieldName = "PtlCashFlow001.customCashType", groups = {ValidateGroupEdit.class})
    public String getCustomCashType() {
        return customCashType;
    }
    public String getCustomCashTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.自定义现金流类型, customCashType );
    }
    public void setCustomCashType(String customCashType) {
        this.customCashType = customCashType;
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
    
    public String getPageModel() {
        return pageModel;
    }
    
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }
    
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public List<PtlCashFlowDto> getSelectedItem() {
        return selectedItem;
    }
    
    public void setSelectedItem(List<PtlCashFlowDto> selectedItem) {
        this.selectedItem = selectedItem;
    }
    /**
     * 获取 bussTrdTransferOrder
     * @return bussTrdTransferOrder.
     */
    public List<BussTrdTransferOrderBase> getBussTrdTransferOrder() {
        return bussTrdTransferOrder;
    }
    /**
     * 设置 bussTrdTransferOrder
     * @param bussTrdTransferOrder bussTrdTransferOrder
     */
    public void setBussTrdTransferOrder(List<BussTrdTransferOrderBase> bussTrdTransferOrder) {
        this.bussTrdTransferOrder = bussTrdTransferOrder;
    }

    /**
     * 获取 actPortfolioId
     *
     * @return actPortfolioId
     */
    public String getActPortfolioId() {
        return actPortfolioId;
    }

    /**
     * 设置 actPortfolioId
     *
     * @param actPortfolioId actPortfolioId
     */
    public void setActPortfolioId(String actPortfolioId) {
        this.actPortfolioId = actPortfolioId;
    }

    public String getTradeStatusStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.交易流程状态, super.getTradeStatus());
    }
}
