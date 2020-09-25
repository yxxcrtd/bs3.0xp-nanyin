/*
 * 文件名：PtlActivityPeriod001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.ptlactivityperiod.dto;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;

/**
 * 活期结息
 * @author fanshuanghu
 * @version 1.0 2018年9月17日
 * @see PtlActivityPeriod001Dto
 * @since 1.0
 */
public class PtlActivityPeriod001Dto extends PtlTrade{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -8719933559729962291L;
    /**
     * 组合名称
     */
    private String portfolioName;
   
    /**
     * 账号类型
     */
    private String acctType;
    /**
     * 账号名称
     */
    private String acctName;
    /**
     * 账户账户
     */
    private String busiAcctNo;
    /**
     * 上次收息日
     */
    private Date vdate;
    /**
     * 本次收息日
     */
    private Date mdate;
    /**
     * 交易金额
     */
    private BigDecimal tradeAmt;
    /**
     * 币种
     */
    private String ccy;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 是否撤销
     */
    private String isCancel;
    /**
     * 交易状态
     */
    private String tradeStatus;
    /**
     * 网点账号
     */
    private String acctNo;
    /**
     * 流水号
     */
    private String seqno;
    /**
     * 支付日
     */
    private Date settleDate;
    /**
     * 持仓类型
     */
    private String hodingType;
    
    /**
     * 理论收息金额
     */
    private BigDecimal theoryTradeAmt;
    
    /**
     * 实际收息金额
     */
    private BigDecimal actualTradeAmt;
    
    /**
     * 是否是子母组合
     */
    private String isParentPortfolio;
    
    /**
     * 开户机构
     */
    private String bankDep;
    
    /**
     * 撤销说明
     */
    private String cancelRemark;
    
    
    /**
     * 明细列表
     */
    private List<PtlActivityPeriod001Dto> detailsList;
    
    /**
     * 子组合明细列表
     */
    private List<PtlActivityPeriod001Dto> childrenDetailsList;
    
    /**
     * 划款指令业务主键实体类
     */
    private BussTrdTransferOrderDto bussTrdTransferOrder;
    
    /**
     * 获取 cancelRemark
     * @return cancelRemark.
     */
    public String getCancelRemark() {
        return cancelRemark;
    }
    
    /**
     * 设置 cancelRemark
     * @param cancelRemark cancelRemark
     */
    public void setCancelRemark(String cancelRemark) {
        this.cancelRemark = cancelRemark;
    }
    
    /**
     * 获取 settleDate
     * @return settleDate.
     */
    public Date getSettleDate() {
        return settleDate;
    }
    
    /**
     * 设置 settleDate
     * @param settleDate settleDate
     */
    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    /**
     * 持仓类型
     */
    
    /**
     * 获取 hodingType
     * @return hodingType.
     */
    public String getHodingType() {
        return hodingType;
    }
    
    /**
     * 设置 hodingType
     * @param hodingType hodingType
     */
    public void setHodingType(String hodingType) {
        this.hodingType = hodingType;
    }

    /**
     * 获取 isCancel
     * @return isCancel.
     */
    public String getIsCancel() {
        return isCancel;
    }
    
    /**
     * 设置 isCancel
     * @param isCancel isCancel
     */
    public void setIsCancel(String isCancel) {
        this.isCancel = isCancel;
    }
    
    /**
     * 获取 tradeStatus
     * @return tradeStatus.
     */
    public String getTradeStatus() {
        return tradeStatus;
    }


    
    /**
     * 设置 tradeStatus
     * @param tradeStatus tradeStatus
     */
    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }
    
    /**
     * 获取 acctNo
     * @return acctNo.
     */
    public String getAcctNo() {
        return acctNo;
    }
    
    /**
     * 设置 acctNo
     * @param acctNo acctNo
     */
    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    /**
     * 获取 seqno
     * @return seqno.
     */
    public String getSeqno() {
        return seqno;
    }
    
    /**
     * 设置 seqno
     * @param seqno seqno
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }
    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }
    
    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 portfolioName
     * @return portfolioName.
     */
    public String getPortfolioName() {
        return portfolioName;
    }
    
    /**
     * 设置 portfolioName
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }
    
    /**
     * 获取 acctType
     * @return acctType.
     */
    public String getAcctType() {
        return acctType;
    }
    
    /**
     * 设置 acctType
     * @param acctType acctType
     */
    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }
    
    /**
     * 获取 acctName
     * @return acctName.
     */
    public String getAcctName() {
        return acctName;
    }
    
    /**
     * 设置 acctName
     * @param acctName acctName
     */
    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }
    
    /**
     * 获取 acctNo
     * @return acctNo.
     */
    public String getBusiAcctNo() {
        return busiAcctNo;
    }
    
    /**
     * 设置 busiAcctNo
     * @param busiAcctNo busiAcctNo
     */
    public void setBusiAcctNo(String busiAcctNo) {
        this.busiAcctNo = busiAcctNo;
    }
    
    /**
     * 获取 vdate
     * @return vdate.
     */
    public Date getVdate() {
        return vdate;
    }
    
    /**
     * 设置 vdate
     * @param vdate vdate
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }
    
    /**
     * 获取 mdate
     * @return mdate.
     */
    public Date getMdate() {
        return mdate;
    }
    
    /**
     * 设置 mdate
     * @param mdate mdate
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }
    
    /**
     * 获取 tradeAmt
     * @return tradeAmt.
     */
    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }
    
    /**
     * 设置 tradeAmt
     * @param tradeAmt tradeAmt
     */
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }
    
    /**
     * 获取 ccy
     * @return ccy.
     */
    public String getCcy() {
        return ccy;
    }
    
    /**
     * 设置 ccy
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 theoryTradeAmt
     * @return theoryTradeAmt.
     */
    public BigDecimal getTheoryTradeAmt() {
        return theoryTradeAmt;
    }

    /**
     * 设置 theoryTradeAmt
     * @param theoryTradeAmt theoryTradeAmt
     */
    public void setTheoryTradeAmt(BigDecimal theoryTradeAmt) {
        this.theoryTradeAmt = theoryTradeAmt;
    }

    /**
     * 获取 actualTradeAmt
     * @return actualTradeAmt.
     */
    public BigDecimal getActualTradeAmt() {
        return actualTradeAmt;
    }

    /**
     * 设置 actualTradeAmt
     * @param actualTradeAmt actualTradeAmt
     */
    public void setActualTradeAmt(BigDecimal actualTradeAmt) {
        this.actualTradeAmt = actualTradeAmt;
    }

    /**
     * 获取 isParentPortfolio
     * @return isParentPortfolio.
     */
    public String getIsParentPortfolio() {
        return isParentPortfolio;
    }

    /**
     * 设置 isParentPortfolio
     * @param isParentPortfolio isParentPortfolio
     */
    public void setIsParentPortfolio(String isParentPortfolio) {
        this.isParentPortfolio = isParentPortfolio;
    }

    /**
     * 获取 bankDep
     * @return bankDep.
     */
    public String getBankDep() {
        return bankDep;
    }

    /**
     * 设置 bankDep
     * @param bankDep bankDep
     */
    public void setBankDep(String bankDep) {
        this.bankDep = bankDep;
    }

    /**
     * 获取 detailsList
     * @return detailsList.
     */
    public List<PtlActivityPeriod001Dto> getDetailsList() {
        return detailsList;
    }

    /**
     * 设置 detailsList
     * @param detailsList detailsList
     */
    public void setDetailsList(List<PtlActivityPeriod001Dto> detailsList) {
        this.detailsList = detailsList;
    }

    /**
     * 获取 childrenDetailsList
     * @return childrenDetailsList.
     */
    public List<PtlActivityPeriod001Dto> getChildrenDetailsList() {
        return childrenDetailsList;
    }

    /**
     * 设置 childrenDetailsList
     * @param childrenDetailsList childrenDetailsList
     */
    public void setChildrenDetailsList(List<PtlActivityPeriod001Dto> childrenDetailsList) {
        this.childrenDetailsList = childrenDetailsList;
    }

    /**
     * 获取 bussTrdTransferOrder
     * @return bussTrdTransferOrder.
     */
    public BussTrdTransferOrderDto getBussTrdTransferOrder() {
        return bussTrdTransferOrder;
    }

    /**
     * 设置 bussTrdTransferOrder
     * @param bussTrdTransferOrder bussTrdTransferOrder
     */
    public void setBussTrdTransferOrder(BussTrdTransferOrderDto bussTrdTransferOrder) {
        this.bussTrdTransferOrder = bussTrdTransferOrder;
    }
    
}
