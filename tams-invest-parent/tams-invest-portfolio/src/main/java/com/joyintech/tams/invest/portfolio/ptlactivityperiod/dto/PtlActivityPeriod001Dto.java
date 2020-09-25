/*
 * 文件名：PtlActivityPeriod001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlactivityperiod.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.portfolio.ptlactivityperiod.validate.PtlActivityPeriod001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 活期结息
 * @author fanshuanghu
 * @version 1.0 2018年9月17日
 * @see PtlActivityPeriod001Dto
 * @since 1.0
 */
@PageId("PtlActivityPeriod002")
@ValidLogic(targetClass = PtlActivityPeriod001Validator.class, groups = {ValidateGroupEdit.class,
  ValidateGroupDelete.class})
public class PtlActivityPeriod001Dto extends PtlTrade implements ITableRow,ProcessEntity{

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
     * 网点账号
     */
    private String busiAcctNo;
    /**
     * 网点账号
     */
    private String acctNo;
    /**
     * 上次收息日
     */
    private Date vdate;
    /**
     * 本次收息日
     */
    @NotEmpty(fieldName="PtlActivityPeriod001.mdate",groups = {ValidateGroupEdit.class})
    private Date mdate;
    /**
     * 交易金额
     */
    @NotEmpty(fieldName="PtlActivityPeriod001.tradeAmts",groups = {ValidateGroupEdit.class})
    private BigDecimal tradeAmt;
    /**
     * 币种
     */
    private String ccy;
    /**
     * 处理的币种
     */
    private String ccy1;
    /**
     * 界面ID
     */
    private String elementId;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 流水号
     */
    private String seqno;
    /**
     * 撤销说明
     */
    private String cancelRemark;
    
    /**
     * 理论收息金额
     */
    private BigDecimal theoryTradeAmt;
    
    /**
     * 实际收息金额
     */
    private BigDecimal actualTradeAmt;
    
    /**
     * 支付日
     */
    @NotEmpty(fieldName="PtlActivityPeriod001.settleDate",groups = {ValidateGroupEdit.class})
    private Date settleDate;
    /**
     * 交易状态
     */
    private String tradeStatus;
    
    /**
     * 开户机构
     */
    private String bankDep;
    
    /**
     * 是否是子母组合
     */
    private String isParentPortfolio;
    
    /**
     * 明细列表
     */
    private List<PtlActivityPeriod001Dto> detailsList;
    
    /**
     * 划款指令业务主键实体类
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrderList;
    
    
    /**
     * 选择项
     */
    private List<PtlActivityPeriod001Dto> selectedItem;
    
    
    /**
     * 子组合明细列表
     */
    private List<PtlActivityPeriod001Dto> childrenDetailsList;
    
    /**
     * 
     */
    private String childrenDetailsJson;
    
    
    private BussTrdTransferOrderDto  bussTrdTransferOrder;
    /**
     * 获取 ccy1
     * @return ccy1.
     */
    public String getCcy1() {
        return ccy1;
    }
    
    /**
     * 设置 ccy1
     * @param ccy1 ccy1
     */
    public void setCcy1(String ccy1) {
        this.ccy1 = ccy1;
    }

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
     * 获取 tradeStatus
     * @return tradeStatus.
     */
    public String getTradeStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易流程状态, this.getTradeStatus());
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
     * 设置业务编号为业务主键一
     * {@inheritDoc}
     */
    @Override
    @PrimaryKey(Field.PK_1)
    public String getBusiNo(){
        return super.getBusiNo();
    }
    /**
     * 设置业务类型为业务主键二
     * {@inheritDoc}
     */
    @Override
    @PrimaryKey(Field.PK_2)
    public String getBusiType(){
        return super.getBusiType();
    }
    /**
     * 币种字符串
     * @return String 币种字符
     * @see
     * @since 1.0
     */
    public String getCcyStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.币种, this.ccy);
    }
    /**
     * 获取账号类型字符串
     * @return 账号类型字符串
     * @see
     * @since 1.0
     */
    public String getAcctTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.资金账户类型, this.acctType);
    }
    
    
    
    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }
    
    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
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
     * 组合代码不能为空
     */
    @Override
    public String getPortfolioId(){
        return super.getPortfolioId();
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
    @PrimaryKey(Field.PK_3)
    public String getBusiAcctNo() {
        return busiAcctNo;
    }
    
    /**
     * 设置 acctNo
     * @param acctNo acctNo
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
    
    @PrimaryKey(Field.PK_4)
    public String getMdateStr() {
        return DateUtil.format(getMdate());
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
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<PtlActivityPeriod001Dto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<PtlActivityPeriod001Dto> selectedItem) {
        this.selectedItem = selectedItem;
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
     * 获取 bussTrdTransferOrderList
     * @return bussTrdTransferOrderList.
     */
    public List<BussTrdTransferOrderDto> getBussTrdTransferOrderList() {
        return bussTrdTransferOrderList;
    }

    /**
     * 设置 bussTrdTransferOrderList
     * @param bussTrdTransferOrderList bussTrdTransferOrderList
     */
    public void setBussTrdTransferOrderList(List<BussTrdTransferOrderDto> bussTrdTransferOrderList) {
        this.bussTrdTransferOrderList = bussTrdTransferOrderList;
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
     * 获取 childrenDetailsJson
     * @return childrenDetailsJson.
     */
    public String getChildrenDetailsJson() {
        return childrenDetailsJson;
    }

    /**
     * 设置 childrenDetailsJson
     * @param childrenDetailsJson childrenDetailsJson
     */
    public void setChildrenDetailsJson(String childrenDetailsJson) {
        this.childrenDetailsJson = childrenDetailsJson;
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
