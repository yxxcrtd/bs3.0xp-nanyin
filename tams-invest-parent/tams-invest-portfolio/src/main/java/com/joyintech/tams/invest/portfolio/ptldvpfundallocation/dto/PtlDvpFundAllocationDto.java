/*
 * 文件名：PtlDvpFundAllocationDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年3月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptldvpfundallocation.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlCashPosition;
import com.joyintech.tams.common.base.entity.PtlCashPositionFlow;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.portfolio.ptldvpfundallocation.validate.PtlDvpFundAllocation001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * DVP资金调拨管理dto
 *
 * @author etliu
 * @version 1.0 2019年3月29日
 * @see PtlDvpFundAllocationDto
 * @since 1.0
 */
@PageId("PtlDvpFundAllocation002")
@ValidLogic(targetClass = PtlDvpFundAllocation001Validator.class)
public class PtlDvpFundAllocationDto extends StlPayOrders implements ITableRow, ProcessEntity {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -8155852096396197686L;

    /**
     * 工作流代码
     */
    private String workflowId;
    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 实体组合代码
     */
    private String actPortfolioId;

    /**
     * 业务编号
     */
    private String busiNo;

    /**
     * 账户余额
     */
    private BigDecimal amount;

    /**
     * 付款账户余额
     */
    private BigDecimal myAcctAmount;

    /**
     * 收款账户余额
     */
    private BigDecimal counterAcctAmount;

    /**
     * 节点ID
     */
    private String elementId;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 组合现金流水
     */
    private List<PtlCashPositionFlow> ptlCashPositionFlow;

    /**
     * 组合交易
     */
    private List<PtlTrade> ptlTrade;

    /**
     * 金融产品交易
     */
    private TrdProductDeal trdProductDeal;

    /**
     * 金融产品交易附属
     */
    private TrdProductDealAdd trdProductDealAdd;
    
    /**
     * 金融产品交易扩展表
     */
    private PaTrdProductDeal paTrdProductDeal;

    /**
     * 组合现金余额
     */
    private List<PtlCashPosition> ptlCashPosition;

    /**
     * 选中的实体类
     */
    private List<PtlDvpFundAllocationDto> selectedItem;

    /**
     * 划款指令表
     */
    private List<StlPayOrdersDto> stlPayOrdersList;

    /**
     * 撤销说明
     */
    private String revokeReason;

    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

    @NotEmpty(fieldName = "PtlDvpFundAllocation001.portfolioId")
    public String getPortfolioId() {
        return super.getPortfolioId();
    }

    @NotEmpty(fieldName = "PtlDvpFundAllocation002.ccy")
    public String getCcy() {
        return super.getCcy();
    }

    @NotEmpty(fieldName = "PtlDvpFundAllocation001.payType")
    public String getPayType() {
        return super.getPayType();
    }

    @NotEmpty(fieldName = "PtlDvpFundAllocation001.counterAcctNo")
    public String getCounterAcctNo() {
        return super.getCounterAcctNo();
    }

    @NotEmpty(fieldName = "PtlDvpFundAllocation001.myAcctNos")
    public String getMyAcctNo() {
        return super.getMyAcctNo();
    }

    @NotEmpty(fieldName = "PtlDvpFundAllocation001.settleAmts")
    public BigDecimal getSettleAmt() {
        return super.getSettleAmt();
    }

    @NotEmpty(fieldName = "PtlDvpFundAllocation001.settleDate")
    public Date getSettleDate() {
        return super.getSettleDate();
    }

    /**
     * 获取 selectedItem
     * 
     * @return selectedItem.
     */
    public List<PtlDvpFundAllocationDto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 设置 selectedItem
     * 
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<PtlDvpFundAllocationDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * 获取 busiNo
     * 
     * @return busiNo.
     */
    public String getBusiNo() {
        return busiNo;
    }

    /**
     * 设置 busiNo
     * 
     * @param busiNo busiNo
     */
    public void setBusiNo(String busiNo) {
        this.busiNo = busiNo;
    }

    /**
     * 获取 amount
     * 
     * @return amount.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置 amount
     * 
     * @param amount amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取 ptlCashPositionFlow
     * 
     * @return ptlCashPositionFlow.
     */
    public List<PtlCashPositionFlow> getPtlCashPositionFlow() {
        return ptlCashPositionFlow;
    }

    /**
     * 设置 ptlCashPositionFlow
     * 
     * @param ptlCashPositionFlow ptlCashPositionFlow
     */
    public void setPtlCashPositionFlow(List<PtlCashPositionFlow> ptlCashPositionFlow) {
        this.ptlCashPositionFlow = ptlCashPositionFlow;
    }

    /**
     * 获取 ptlTrade
     * 
     * @return ptlTrade.
     */
    public List<PtlTrade> getPtlTrade() {
        return ptlTrade;
    }

    /**
     * 设置 ptlTrade
     * 
     * @param ptlTrade ptlTrade
     */
    public void setPtlTrade(List<PtlTrade> ptlTrade) {
        this.ptlTrade = ptlTrade;
    }

    /**
     * 获取 trdProductDeal
     * 
     * @return trdProductDeal.
     */
    public TrdProductDeal getTrdProductDeal() {
        return trdProductDeal;
    }

    /**
     * 设置 trdProductDeal
     * 
     * @param trdProductDeal trdProductDeal
     */
    public void setTrdProductDeal(TrdProductDeal trdProductDeal) {
        this.trdProductDeal = trdProductDeal;
    }

    /**
     * 获取 ptlCashPosition
     * 
     * @return ptlCashPosition.
     */
    public List<PtlCashPosition> getPtlCashPosition() {
        return ptlCashPosition;
    }

    /**
     * 设置 ptlCashPosition
     * 
     * @param ptlCashPosition ptlCashPosition
     */
    public void setPtlCashPosition(List<PtlCashPosition> ptlCashPosition) {
        this.ptlCashPosition = ptlCashPosition;
    }

    /**
     * 货款指令
     * 
     * @return
     * @see
     * @since 1.0
     */
    @Override
    @PrimaryKey(Field.PK_1)
    public String getOrderId() {
        return StringUtil.isNotEmpty(
            super.getOrderId()) ? super.getOrderId() : IDGenerator.generate();
    }

    /**
     * 获取 elementId
     * 
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 elementId
     * 
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 获取 operateType
     * 
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     * 
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 portfolioName
     * 
     * @return portfolioName.
     */
    public String getPortfolioName() {
        return portfolioName;
    }

    /**
     * 设置 portfolioName
     * 
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    /**
     * 获取币种字符串
     * 
     * @return 币种字符串
     * @see
     * @since 1.0
     */
    public String getCcyStr() {
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.币种, super.getCcy());
    }

    /**
     * 获取划款性质字符串
     * 
     * @return 划款性质字符串
     * @see
     * @since 1.0
     */
    public String getOrderStatusStr() {
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.画面数据状态, super.getOrderStatus());
    }

    /**
     * 获取划款类型字符串
     * 
     * @return 划款类型字符串
     * @see
     * @since 1.0
     */
    public String getPayTypeStr() {
        // return SysDataUtil.getNameFromDataDict(BussinessDictCode.指令类型, super.getPayType());
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.交易方向, super.getPayType());
    }

    /**
     * 获取 stlPayOrdersList
     * 
     * @return stlPayOrdersList.
     */
    public List<StlPayOrdersDto> getStlPayOrdersList() {
        return stlPayOrdersList;
    }

    /**
     * 设置 stlPayOrdersList
     * 
     * @param stlPayOrdersList stlPayOrdersList
     */
    public void setStlPayOrdersList(List<StlPayOrdersDto> stlPayOrdersList) {
        this.stlPayOrdersList = stlPayOrdersList;
    }

    /**
     * 获取 bussTrdTransferOrder
     * 
     * @return bussTrdTransferOrder.
     */
    public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
        return bussTrdTransferOrder;
    }

    /**
     * 设置 bussTrdTransferOrder
     * 
     * @param bussTrdTransferOrder bussTrdTransferOrder
     */
    public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
        this.bussTrdTransferOrder = bussTrdTransferOrder;
    }

    /**
     * 获取 trdProductDealAdd
     * 
     * @return trdProductDealAdd.
     */
    public TrdProductDealAdd getTrdProductDealAdd() {
        return trdProductDealAdd;
    }

    /**
     * 设置 trdProductDealAdd
     * 
     * @param trdProductDealAdd trdProductDealAdd
     */
    public void setTrdProductDealAdd(TrdProductDealAdd trdProductDealAdd) {
        this.trdProductDealAdd = trdProductDealAdd;
    }

    /**
     * 获取 myAcctAmount
     * 
     * @return myAcctAmount.
     */
    public BigDecimal getMyAcctAmount() {
        return myAcctAmount;
    }

    /**
     * 设置 myAcctAmount
     * 
     * @param myAcctAmount myAcctAmount
     */
    public void setMyAcctAmount(BigDecimal myAcctAmount) {
        this.myAcctAmount = myAcctAmount;
    }

    /**
     * 获取 counterAcctAmount
     * 
     * @return counterAcctAmount.
     */
    public BigDecimal getCounterAcctAmount() {
        return counterAcctAmount;
    }

    /**
     * 设置 counterAcctAmount
     * 
     * @param counterAcctAmount counterAcctAmount
     */
    public void setCounterAcctAmount(BigDecimal counterAcctAmount) {
        this.counterAcctAmount = counterAcctAmount;
    }

    /**
     * 获取 revokeReason
     * 
     * @return revokeReason.
     */
    public String getRevokeReason() {
        return revokeReason;
    }

    /**
     * 设置 revokeReason
     * 
     * @param revokeReason revokeReason
     */
    public void setRevokeReason(String revokeReason) {
        this.revokeReason = revokeReason;
    }

    /**
     * 获取 paTrdProductDeal
     * @return paTrdProductDeal.
     */
    public PaTrdProductDeal getPaTrdProductDeal() {
        return paTrdProductDeal;
    }

    /**
     * 设置 paTrdProductDeal
     * @param paTrdProductDeal paTrdProductDeal
     */
    public void setPaTrdProductDeal(PaTrdProductDeal paTrdProductDeal) {
        this.paTrdProductDeal = paTrdProductDeal;
    }

    
    public String getWorkflowId() {
        return workflowId;
    }

    
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
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
}
