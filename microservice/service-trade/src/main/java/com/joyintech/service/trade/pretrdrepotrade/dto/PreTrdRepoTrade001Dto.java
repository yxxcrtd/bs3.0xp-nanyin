/*
 * 文件名：TrdLendInvestTradeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.trade.pretrdrepotrade.dto;

import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.invest.common.dto.common.BussRepoSecDto;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import com.joyintech.workflow.engine.dto.ProcessEntity;
import java.math.BigDecimal;
import java.util.List;

public class PreTrdRepoTrade001Dto extends TrdFinProductDealTotal implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -441611713511199822L;

    /**
     * 树形菜单父节点Id
     */
    private String parentId;
    /**
     * 树形菜单子节点Id
     */
    private String childId;
    /**
     * 汇总交易编号
     */
    private String tradeId;
    /**
     * 有效状态
     */
    private String validStatus;

    /**
     * 有效状态Text
     */
    private String validStatusText;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 工作流状态
     */
    private String workflowStatus;

    /**
     * 交易方向Text
     */
    private String psText;

    /**
     * 交易日期Text
     */
    private String tradeDateText;

    /**
     * 币种Text
     */
    private String ccyText;

    /**
     * 有效时间Text
     */
    private String effTimeText;

    /**
     * 实际占款天数
     */
    private Long actCapDays;

    /**
     * 应计利息
     */
    private BigDecimal rebackIntAmt;

    /**
     * 到期结算金额
     */
    private BigDecimal mdateSettleAmt;

    /**
     * 证券托管户
     */
    private String secAcctId;

    /**
     * 证券账户名称
     */
    private String secAcctName;

    /**
     * 登记托管机构
     */
    private String registOrg;

    /**
     * 资金账户
     */
    private String acctNo;
    /**
     * 资金账户名
     */
    private String acctName;

    /**
     * 资金开户行
     */
    private String acctBankDep;

    /**
     * 资金开户行支付系统行号
     */
    private String acctBankDepoPayno;

    /**
     * 债券币种
     */
    private String bondCcy;

    /**
     * 结算币种
     */
    private String settleCcy;

    private String businessData;

    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 债券代码
     */
    private String finprodMarketId;
    /**
     * 债券名称
     */
    private String finprodAbbr;
    /**
     * 券面总额
     */
    private String pledgeAmt;
    /**
     * 折算比例
     */
    private String pledgeRatio;

    private String settleSpeedText;
    
    /**
     * 质押券信息
     */
    private BussRepoSecDto bussRepoSecPtl;

    /**
     * 质押券信息列表
     */
    private List<PledgeBondInfoDto> pledgeBondList;

    /**
     * 组合交易
     */
    private List<PtlTrade> ptlTrade;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    /**
     * 获取 有效状态
     * @rturn 有效状态
     */
    public String getValidStatus() {
        return this.validStatus;
    }

    /**
     * 设置 有效状态
     * @param validStatus 有效状态
     */
    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }

    /**
     * 取得 有效状态
     * @return 有效状态
     */
    public String getValidStatusText() {
        return validStatusText;
    }

    /**
     * 设置 有效状态
     * @param validStatusText 有效状态
     */
    public void setValidStatusText(String validStatusText) {
        this.validStatusText = validStatusText;
    }

    /**
     * 获取 操作类型
     * @rturn 操作类型
     */
    public String getOperateType() {
        return this.operateType;
    }

    /**
     * 设置 操作类型
     * @param operateType 操作类型
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }


    /**
     * 获取 工作流状态
     * @rturn 工作流状态
     */
    public String getWorkflowStatus() {
        return this.workflowStatus;
    }

    /**
     * 设置 工作流状态
     * @param workflowStatus 工作流状态
     */
    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

    /**
     * 取得 交易方向
     * @return 交易方向
     */
    public String getPsText() {
        return psText;
    }

    /**
     * 设置 交易方向
     * @param psText 交易方向
     */
    public void setPsText(String psText) {
        this.psText = psText;
    }

    /**
     * 取得 交易日期
     * @return 交易日期
     */
    public String getTradeDateText() {
        return tradeDateText;
    }

    /**
     * 设置 交易日期
     * @param tradeDateText 交易日期
     */
    public void setTradeDateText(String tradeDateText) {
        this.tradeDateText = tradeDateText;
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcyText() {
        return ccyText;
    }

    /**
     * 设置 币种
     * @param ccyText 币种
     */
    public void setCcyText(String ccyText) {
        this.ccyText = ccyText;
    }
    /**
     * 取得 有效时间
     * @return 有效时间
     */
    public String getEffTimeText() {
        return effTimeText;
    }

    /**
     * 设置 有效时间
     * @param effTimeText 有效时间
     */
    public void setEffTimeText(String effTimeText) {
        this.effTimeText = effTimeText;
    }

    /**
     * 获取 实际占款天数
     * @return actCapDays.
     */
    public Long getActCapDays() {
        return actCapDays;
    }

    /**
     * 设置 实际占款天数
     * @param actCapDays actCapDays
     */
    public void setActCapDays(Long actCapDays) {
        this.actCapDays = actCapDays;
    }

    /**
     * 获取 应计利息
     * @rturn rebackIntAmt
     */
    public BigDecimal getRebackIntAmt() {
        return this.rebackIntAmt;
    }

    /**
     * 设置 应计利息
     * @param rebackIntAmt rebackIntAmt
     */
    public void setRebackIntAmt(BigDecimal rebackIntAmt) {
        this.rebackIntAmt = rebackIntAmt;
    }

    /**
     * 获取 到期结算金额
     * @rturn mdateSettleAmt
     */
    public BigDecimal getMdateSettleAmt() {
        return this.mdateSettleAmt;
    }

    /**
     * 设置 到期结算金额
     * @param mdateSettleAmt mdateSettleAmt
     */
    public void setMdateSettleAmt(BigDecimal mdateSettleAmt) {
        this.mdateSettleAmt = mdateSettleAmt;
    }

    /**
     * 获取 证券托管户
     * @rturn secAcctId
     */
    public String getSecAcctId() {
        return this.secAcctId;
    }

    /**
     * 设置 证券托管户
     * @param secAcctId secAcctId
     */
    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    /**
     * 获取 证券账户名称
     * @return
     */
    public String getSecAcctName() {
        return secAcctName;
    }

    /**
     * 设置 证券账户名称
     * @param secAcctName
     */
    public void setSecAcctName(String secAcctName) {
        this.secAcctName = secAcctName;
    }

    /**
     * 获取 登记托管机构
     * @return
     */
    public String getRegistOrg() {
        return registOrg;
    }

    /**
     * 设置 登记托管机构
     * @param registOrg
     */
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg;
    }

    /**
     * 获取 资金账户
     * @return
     */
    public String getAcctNo() {
        return acctNo;
    }

    /**
     * 设置 资金账户
     * @param acctNo
     */
    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    /**
     * 获取 资金账户名
     * @return
     */
    public String getAcctName() {
        return acctName;
    }

    /**
     * 设置 资金账户名
     * @param acctName
     */
    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    /**
     * 获取 资金开户行
     * @return
     */
    public String getAcctBankDep() {
        return acctBankDep;
    }

    /**
     * 设置 资金开户行
     * @param acctBankDep
     */
    public void setAcctBankDep(String acctBankDep) {
        this.acctBankDep = acctBankDep;
    }

    /**
     * 获取 资金开户行支付系统行号
     * @return
     */
    public String getAcctBankDepoPayno() {
        return acctBankDepoPayno;
    }

    /**
     * 设置 资金开户行支付系统行号
     * @param acctBankDepoPayno
     */
    public void setAcctBankDepoPayno(String acctBankDepoPayno) {
        this.acctBankDepoPayno = acctBankDepoPayno;
    }

    public String getBondCcy() {
        return bondCcy;
    }

    public void setBondCcy(String bondCcy) {
        this.bondCcy = bondCcy;
    }

    public String getSettleCcy() {
        return settleCcy;
    }

    public void setSettleCcy(String settleCcy) {
        this.settleCcy = settleCcy;
    }

    public String getBusinessData() {
        return businessData;
    }

    public void setBusinessData(String businessData) {
        this.businessData = businessData;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
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

    public String getPledgeAmt() {
        return pledgeAmt;
    }

    public void setPledgeAmt(String pledgeAmt) {
        this.pledgeAmt = pledgeAmt;
    }

    public String getPledgeRatio() {
        return pledgeRatio;
    }

    public void setPledgeRatio(String pledgeRatio) {
        this.pledgeRatio = pledgeRatio;
    }

    public String getSettleSpeedText() {
        return settleSpeedText;
    }

    public void setSettleSpeedText(String settleSpeedText) {
        this.settleSpeedText = settleSpeedText;
    }

    /**
     * 获取 质押券信息
     * @rturn 质押券信息
     */
    public BussRepoSecDto getBussRepoSecPtl() {
        return this.bussRepoSecPtl;
    }

    /**
     * 设置 质押券信息
     * @param bussRepoSecPtl 质押券信息
     */
    public void setBussRepoSecPtl(BussRepoSecDto bussRepoSecPtl) {
        this.bussRepoSecPtl = bussRepoSecPtl;
    }

    /**
     * 获取 组合交易
     * @rturn 组合交易
     */
    public List<PtlTrade> getPtlTrade() {
        return this.ptlTrade;
    }

    /**
     * 设置 组合交易
     * @param ptlTrade 组合交易
     */
    public void setPtlTrade(List<PtlTrade> ptlTrade) {
        this.ptlTrade = ptlTrade;
    }

    /**
     * 获取 质押券信息列表
     * @return 质押券信息列表.
     */
    public List<PledgeBondInfoDto> getPledgeBondList() {
        return pledgeBondList;
    }

    /**
     * 设置 质押券信息列表
     * @param pledgeBondList 质押券信息列表
     */
    public void setPledgeBondList(List<PledgeBondInfoDto> pledgeBondList) {
        this.pledgeBondList = pledgeBondList;
    }
}
