/*
 * 文件名：TrdRepoTrade001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.trade.trdstandardbond.dto;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@PageId("TrdStandardBond003")
public class TrdStandardBond003Dto extends BaseEntity implements ProcessEntity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7277250042001522297L;

    /**
     * pageModel
     */
    private String pageModel;

    /**
     * 组合代码
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    private String portfolioId;

    /**
     * 债券代码
     */
    private String bondCode;

    /**
     * 金融产品代码
     */
    private String finProdId;

    /**
     * 债券名称
     */
    private String bondName;

    /**
     * 交易场所
     */
    private String tradePlace;

    /**
     * 交易场所(Text)
     */
    private String tradePlaceText;

    /**
     * 证券账户
     */
    @PrimaryKey(PrimaryKey.Field.PK_2)
    private String secAcctId;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 质押券出库画面：可出库券面总额
     */
    private BigDecimal avaOutPrinAmt;

    /**
     * 质押券出库画面：可出库券面总额（初始）
     */
    private BigDecimal avaOutPrinAmtInit;

    /**
     * 出库券面总额
     * 质押券出库画面：出库券面总额
     */
    private BigDecimal outPrinAmt;

    /**
     * 折算率%
     */
    private BigDecimal discountRate;

    /**
     * 出库标准券总额
     */
    private BigDecimal outStrdPrinAmt;

    /**
     * 标准券额度
     */
    private BigDecimal inOutStdrdPrinAmt;

    /**
     * 已质押标准券总额度
     */
    private BigDecimal pledgeStdrdPrinAmt;

    /**
     * 可使用标准券总额度
     */
    private BigDecimal avaStrdPrinAmt;

    /**
     * 质押券出库画面：当前组合代码、证券账户下竞价回购入出库合计券面总额
     */
    private BigDecimal bidInOutPrinAmt;

    /**
     * 质押券出库画面：当前组合代码、证券账户下竞价回购已使用券面总额（交易上券面总额合计）
     */
    private BigDecimal pledgePrinAmt;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 按投资目的排序：摊余成本类->公允价值变动计入其他综合收益->公允价值变动计入当期损益
     */
    private Long invAimSort;

    /**
     * 标准券额度合计
     */
    private BigDecimal inOutStdrdPrinAmtSum;

    /**
     * 已质押标准券总额度合计
     */
    private BigDecimal pledgeStdrdPrinAmtSum;

    /**
     * 可使用标准券总额度合计
     */
    private BigDecimal avaStrdPrinAmtSum;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 质押券出库画面，待出库列表
     */
    List<TrdStandardBond003Dto> outBondList = new ArrayList<>();

    /**
     * 获取 pageModel
     *
     * @rturn pageModel
     */
    public String getPageModel() {
        return this.pageModel;
    }

    /**
     * 设置 pageModel
     *
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    /**
     * 获取 portfolioId
     *
     * @rturn portfolioId
     */
    public String getPortfolioId() {
        return this.portfolioId;
    }

    /**
     * 设置 portfolioId
     *
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 bondCode
     *
     * @rturn bondCode
     */
    public String getBondCode() {
        return this.bondCode;
    }

    /**
     * 设置 bondCode
     *
     * @param bondCode bondCode
     */
    public void setBondCode(String bondCode) {
        this.bondCode = bondCode;
    }

    /**
     * 获取 finProdId
     *
     * @rturn finProdId
     */
    public String getFinProdId() {
        return this.finProdId;
    }

    /**
     * 设置 finProdId
     *
     * @param finProdId finProdId
     */
    public void setFinProdId(String finProdId) {
        this.finProdId = finProdId;
    }

    /**
     * 获取 bondName
     *
     * @rturn bondName
     */
    public String getBondName() {
        return this.bondName;
    }

    /**
     * 设置 bondName
     *
     * @param bondName bondName
     */
    public void setBondName(String bondName) {
        this.bondName = bondName;
    }

    /**
     * 获取 tradePlace
     *
     * @rturn tradePlace
     */
    public String getTradePlace() {
        return this.tradePlace;
    }

    /**
     * 设置 tradePlace
     *
     * @param tradePlace tradePlace
     */
    public void setTradePlace(String tradePlace) {
        this.tradePlace = tradePlace;
    }

    /**
     * 获取 tradePlaceText
     *
     * @rturn tradePlaceText
     */
    public String getTradePlaceText() {
        if (StringUtil.isNotEmpty(this.getTradePlace())) {
            tradePlaceText = SysDataUtil.getNameFromDataDict(DictCode.交易场所, this.getTradePlace());
        }
        return tradePlaceText;
    }

    /**
     * 设置 tradePlaceText
     *
     * @param tradePlaceText tradePlaceText
     */
    public void setTradePlaceText(String tradePlaceText) {
        this.tradePlaceText = tradePlaceText;
    }

    /**
     * 获取 secAcctId
     *
     * @rturn secAcctId
     */
    public String getSecAcctId() {
        return this.secAcctId;
    }

    /**
     * 设置 secAcctId
     *
     * @param secAcctId secAcctId
     */
    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    /**
     * 获取 ccy
     *
     * @rturn ccy
     */
    public String getCcy() {
        return this.ccy;
    }

    /**
     * 设置 ccy
     *
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 avaOutPrinAmt
     *
     * @rturn avaOutPrinAmt
     */
    public BigDecimal getAvaOutPrinAmt() {
        return this.avaOutPrinAmt;
    }

    /**
     * 设置 avaOutPrinAmt
     *
     * @param avaOutPrinAmt avaOutPrinAmt
     */
    public void setAvaOutPrinAmt(BigDecimal avaOutPrinAmt) {
        this.avaOutPrinAmt = avaOutPrinAmt;
    }

    /**
     * 获取 avaOutPrinAmtInit
     *
     * @rturn avaOutPrinAmtInit
     */
    public BigDecimal getAvaOutPrinAmtInit() {
        return this.avaOutPrinAmtInit;
    }

    /**
     * 设置 avaOutPrinAmtInit
     *
     * @param avaOutPrinAmtInit avaOutPrinAmtInit
     */
    public void setAvaOutPrinAmtInit(BigDecimal avaOutPrinAmtInit) {
        this.avaOutPrinAmtInit = avaOutPrinAmtInit;
    }

    /**
     * 获取 outPrinAmt
     *
     * @rturn outPrinAmt
     */
    public BigDecimal getOutPrinAmt() {
        return this.outPrinAmt;
    }

    /**
     * 设置 outPrinAmt
     *
     * @param outPrinAmt outPrinAmt
     */
    public void setOutPrinAmt(BigDecimal outPrinAmt) {
        this.outPrinAmt = outPrinAmt;
    }

    /**
     * 获取 discountRate
     *
     * @rturn discountRate
     */
    public BigDecimal getDiscountRate() {
        return this.discountRate;
    }

    /**
     * 设置 discountRate
     *
     * @param discountRate discountRate
     */
    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    /**
     * 获取 outStrdPrinAmt
     *
     * @rturn outStrdPrinAmt
     */
    public BigDecimal getOutStrdPrinAmt() {
        return this.outStrdPrinAmt;
    }

    /**
     * 设置 outStrdPrinAmt
     *
     * @param outStrdPrinAmt outStrdPrinAmt
     */
    public void setOutStrdPrinAmt(BigDecimal outStrdPrinAmt) {
        this.outStrdPrinAmt = outStrdPrinAmt;
    }

    /**
     * 获取 pledgePrinAmt
     *
     * @rturn pledgePrinAmt
     */
    public BigDecimal getPledgePrinAmt() {
        return this.pledgePrinAmt;
    }

    /**
     * 设置 pledgePrinAmt
     *
     * @param pledgePrinAmt pledgePrinAmt
     */
    public void setPledgePrinAmt(BigDecimal pledgePrinAmt) {
        this.pledgePrinAmt = pledgePrinAmt;
    }

    /**
     * 获取 bidInOutPrinAmt
     *
     * @rturn bidInOutPrinAmt
     */
    public BigDecimal getBidInOutPrinAmt() {
        return this.bidInOutPrinAmt;
    }

    /**
     * 设置 bidInOutPrinAmt
     *
     * @param bidInOutPrinAmt bidInOutPrinAmt
     */
    public void setBidInOutPrinAmt(BigDecimal bidInOutPrinAmt) {
        this.bidInOutPrinAmt = bidInOutPrinAmt;
    }

    /**
     * 获取 inOutStdrdPrinAmt
     *
     * @rturn inOutStdrdPrinAmt
     */
    public BigDecimal getInOutStdrdPrinAmt() {
        return this.inOutStdrdPrinAmt;
    }

    /**
     * 设置 inOutStdrdPrinAmt
     *
     * @param inOutStdrdPrinAmt inOutStdrdPrinAmt
     */
    public void setInOutStdrdPrinAmt(BigDecimal inOutStdrdPrinAmt) {
        this.inOutStdrdPrinAmt = inOutStdrdPrinAmt;
    }

    /**
     * 获取 pledgeStdrdPrinAmt
     *
     * @rturn pledgeStdrdPrinAmt
     */
    public BigDecimal getPledgeStdrdPrinAmt() {
        return this.pledgeStdrdPrinAmt;
    }

    /**
     * 设置 pledgeStdrdPrinAmt
     *
     * @param pledgeStdrdPrinAmt pledgeStdrdPrinAmt
     */
    public void setPledgeStdrdPrinAmt(BigDecimal pledgeStdrdPrinAmt) {
        this.pledgeStdrdPrinAmt = pledgeStdrdPrinAmt;
    }

    /**
     * 获取 avaStrdPrinAmt
     *
     * @rturn avaStrdPrinAmt
     */
    public BigDecimal getAvaStrdPrinAmt() {
        return this.avaStrdPrinAmt;
    }

    /**
     * 设置 avaStrdPrinAmt
     *
     * @param avaStrdPrinAmt avaStrdPrinAmt
     */
    public void setAvaStrdPrinAmt(BigDecimal avaStrdPrinAmt) {
        this.avaStrdPrinAmt = avaStrdPrinAmt;
    }

    /**
     * 获取 invAim
     *
     * @rturn invAim
     */
    public String getInvAim() {
        return this.invAim;
    }

    /**
     * 设置 invAim
     *
     * @param invAim invAim
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    /**
     * 获取 invAimSort
     *
     * @rturn invAimSort
     */
    public Long getInvAimSort() {
        return this.invAimSort;
    }

    /**
     * 设置 invAimSort
     *
     * @param invAimSort invAimSort
     */
    public void setInvAimSort(Long invAimSort) {
        this.invAimSort = invAimSort;
    }

    /**
     * 获取 inOutStdrdPrinAmtSum
     *
     * @rturn inOutStdrdPrinAmtSum
     */
    public BigDecimal getInOutStdrdPrinAmtSum() {
        return this.inOutStdrdPrinAmtSum;
    }

    /**
     * 设置 inOutStdrdPrinAmtSum
     *
     * @param inOutStdrdPrinAmtSum inOutStdrdPrinAmtSum
     */
    public void setInOutStdrdPrinAmtSum(BigDecimal inOutStdrdPrinAmtSum) {
        this.inOutStdrdPrinAmtSum = inOutStdrdPrinAmtSum;
    }

    /**
     * 获取 pledgeStdrdPrinAmtSum
     *
     * @rturn pledgeStdrdPrinAmtSum
     */
    public BigDecimal getPledgeStdrdPrinAmtSum() {
        return this.pledgeStdrdPrinAmtSum;
    }

    /**
     * 设置 pledgeStdrdPrinAmtSum
     *
     * @param pledgeStdrdPrinAmtSum pledgeStdrdPrinAmtSum
     */
    public void setPledgeStdrdPrinAmtSum(BigDecimal pledgeStdrdPrinAmtSum) {
        this.pledgeStdrdPrinAmtSum = pledgeStdrdPrinAmtSum;
    }

    /**
     * 获取 avaStrdPrinAmtSum
     *
     * @rturn avaStrdPrinAmtSum
     */
    public BigDecimal getAvaStrdPrinAmtSum() {
        return this.avaStrdPrinAmtSum;
    }

    /**
     * 设置 avaStrdPrinAmtSum
     *
     * @param avaStrdPrinAmtSum avaStrdPrinAmtSum
     */
    public void setAvaStrdPrinAmtSum(BigDecimal avaStrdPrinAmtSum) {
        this.avaStrdPrinAmtSum = avaStrdPrinAmtSum;
    }

    /**
     * 获取 branch
     *
     * @rturn branch
     */
    public Long getBranch() {
        return this.branch;
    }

    /**
     * 设置 branch
     *
     * @param branch branch
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 获取 outBondList
     *
     * @rturn outBondList
     */
    public List<TrdStandardBond003Dto> getOutBondList() {
        return this.outBondList;
    }

    /**
     * 设置 outBondList
     *
     * @param outBondList outBondList
     */
    public void setOutBondList(List<TrdStandardBond003Dto> outBondList) {
        this.outBondList = outBondList;
    }
}
