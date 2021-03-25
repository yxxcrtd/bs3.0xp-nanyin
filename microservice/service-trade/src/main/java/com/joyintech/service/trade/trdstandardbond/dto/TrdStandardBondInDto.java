/*
 * 文件名：TrdStandardBondInDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：陈志力
 * 修改时间：2019年1月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.trade.trdstandardbond.dto;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 标准券入库画面 微服务用Dto
 */
public class TrdStandardBondInDto extends BaseEntity {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1341302828425837984L;

    /**
     * pageModel
     */
    private String pageModel;

    /**
     * 组合代码
     */
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
     * 证券账户
     */
    private String secAcctId;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 券面总额
     */
    private BigDecimal positionTotal;

    /**
     * 可质押债券列表中，不可入库券面总额（指出入库合计+协议式质押合计）
     * 质押债券列表中，本次入库券面总额
     */
    private BigDecimal pledgePrinAmt;

    /**
     * 可质押债券列表中，不可入库券面总额（初始）
     */
    private BigDecimal pledgePrinAmtInit;

    /**
     * 可入库券面总额
     */
    private BigDecimal avaPledgePrinAmt;

    /**
     * 折算率%
     */
    private BigDecimal discountRate;

    /**
     * 入库标准券总额
     */
    private BigDecimal inStdrdPrinAmt;

    /**
     * 竞价回购入出库合计后券面总额
     */
    private BigDecimal bidInPrinAmt;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 按投资目的排序：摊余成本类->公允价值变动计入其他综合收益->公允价值变动计入当期损益
     */
    private Long invAimSort;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 是否回退
     */
    private Boolean isBack;

    

    /**
     * 质押券列表信息
     */
    List<TrdStandardBondInDto> pledgeBondList = new ArrayList<>();

    /**
     * 获取 isBack
     *
     * @rturn isBack
     */
    public Boolean getIsBack() {
        return isBack;
    }
    
    /**
     * 设置 isBack
     *
     * @param isBack isBack
     */
    public void setIsBack(Boolean isBack) {
        this.isBack = isBack;
    }

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
     * 获取 positionTotal
     *
     * @rturn positionTotal
     */
    public BigDecimal getPositionTotal() {
        return this.positionTotal;
    }

    /**
     * 设置 positionTotal
     *
     * @param positionTotal positionTotal
     */
    public void setPositionTotal(BigDecimal positionTotal) {
        this.positionTotal = positionTotal;
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
     * 获取 pledgePrinAmtInit
     *
     * @rturn pledgePrinAmtInit
     */
    public BigDecimal getPledgePrinAmtInit() {
        return this.pledgePrinAmtInit;
    }

    /**
     * 设置 pledgePrinAmtInit
     *
     * @param pledgePrinAmtInit pledgePrinAmtInit
     */
    public void setPledgePrinAmtInit(BigDecimal pledgePrinAmtInit) {
        this.pledgePrinAmtInit = pledgePrinAmtInit;
    }

    /**
     * 获取 avaPledgePrinAmt
     *
     * @rturn avaPledgePrinAmt
     */
    public BigDecimal getAvaPledgePrinAmt() {
        return this.avaPledgePrinAmt;
    }

    /**
     * 设置 avaPledgePrinAmt
     *
     * @param avaPledgePrinAmt avaPledgePrinAmt
     */
    public void setAvaPledgePrinAmt(BigDecimal avaPledgePrinAmt) {
        this.avaPledgePrinAmt = avaPledgePrinAmt;
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
     * 获取 inStdrdPrinAmt
     *
     * @rturn inStdrdPrinAmt
     */
    public BigDecimal getInStdrdPrinAmt() {
        return this.inStdrdPrinAmt;
    }

    /**
     * 设置 inStdrdPrinAmt
     *
     * @param inStdrdPrinAmt inStdrdPrinAmt
     */
    public void setInStdrdPrinAmt(BigDecimal inStdrdPrinAmt) {
        this.inStdrdPrinAmt = inStdrdPrinAmt;
    }

    /**
     * 获取 bidInPrinAmt
     *
     * @rturn bidInPrinAmt
     */
    public BigDecimal getBidInPrinAmt() {
        return this.bidInPrinAmt;
    }

    /**
     * 设置 bidInPrinAmt
     *
     * @param bidInPrinAmt bidInPrinAmt
     */
    public void setBidInPrinAmt(BigDecimal bidInPrinAmt) {
        this.bidInPrinAmt = bidInPrinAmt;
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
     * 获取 pledgeBondList
     *
     * @rturn pledgeBondList
     */
    public List<TrdStandardBondInDto> getPledgeBondList() {
        return this.pledgeBondList;
    }

    /**
     * 设置 pledgeBondList
     *
     * @param pledgeBondList pledgeBondList
     */
    public void setPledgeBondList(List<TrdStandardBondInDto> pledgeBondList) {
        this.pledgeBondList = pledgeBondList;
    }
}
