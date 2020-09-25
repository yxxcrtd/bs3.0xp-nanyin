/*
 * 文件名：EtrInvestPersonInfo002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.dto.common;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * 添加质押券Dto
 * 〈功能详细描述〉
 *
 * @author chenzhili
 * @version 1.0 2019年01月12日
 * @see BussRepoSecDto
 * @since 1.0
 */
public class BussRepoSecDto extends BaseEntity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3849369231082846855L;

    /**
     * 引用画面：EtrRepo002（回购委托新建）、TrdRepoTrade002（回购交易新建）
     */
    private String pageFrom;

    /**
     * 汇总券面总额
     */
    private BigDecimal totalPrinAmt;
    
    /**
     * 分配方式
     */
    private String distribution;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 回购拆借方向：正回购、逆回购
     */
    private String ps;

    /**
     * 债券类型
     */
    private String bondType;

    /**
     * 债券金融产品代码
     */
    private String finprodId;
    /**
     * 债券代码
     */
    private String bondCode;
    /**
     * 登记托管机构
     */
    private String repRegistOrg;

    /**
     * 质押劵置换安排
     */
    private String exchangePledge;

    /**
     * 质押券信息列表
     */
    private List<PledgeBondInfoDto> pledgeBondList;
    
    /**
     * 质押券信息列表明细
     */
    private List<PledgeBondInfoDto> pledgeBondListDetailed;

    /**
     * 可质押券列表
     */
    private List<PledgeBondInfoDto> avaPledgeBondDetailList;

    /**
     * 获取 pageFrom
     *
     * @rturn pageFrom
     */
    public String getPageFrom() {
        return this.pageFrom;
    }

    /**
     * 设置 pageFrom
     *
     * @param pageFrom pageFrom
     */
    public void setPageFrom(String pageFrom) {
        this.pageFrom = pageFrom;
    }

    /**
     * 获取 totalPrinAmt
     *
     * @rturn totalPrinAmt
     */
    public BigDecimal getTotalPrinAmt() {
        return this.totalPrinAmt;
    }

    /**
     * 设置 totalPrinAmt
     *
     * @param totalPrinAmt totalPrinAmt
     */
    public void setTotalPrinAmt(BigDecimal totalPrinAmt) {
        this.totalPrinAmt = totalPrinAmt;
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
     * 获取 ps
     *
     * @rturn ps
     */
    public String getPs() {
        return this.ps;
    }

    /**
     * 设置 ps
     *
     * @param ps ps
     */
    public void setPs(String ps) {
        this.ps = ps;
    }

    /**
     * 获取 bondType
     *
     * @rturn bondType
     */
    public String getBondType() {
        return this.bondType;
    }

    /**
     * 设置 bondType
     *
     * @param bondType bondType
     */
    public void setBondType(String bondType) {
        this.bondType = bondType;
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
     * 获取 repRegistOrg
     *
     * @rturn repRegistOrg
     */
    public String getRepRegistOrg() {
        return this.repRegistOrg;
    }

    /**
     * 设置 repRegistOrg
     *
     * @param repRegistOrg repRegistOrg
     */
    public void setRepRegistOrg(String repRegistOrg) {
        this.repRegistOrg = repRegistOrg;
    }

    /**
     * 获取 pledgeBondList
     *
     * @rturn pledgeBondList
     */
    public List<PledgeBondInfoDto> getPledgeBondList() {
        return this.pledgeBondList;
    }

    /**
     * 设置 pledgeBondList
     *
     * @param pledgeBondList pledgeBondList
     */
    public void setPledgeBondList(List<PledgeBondInfoDto> pledgeBondList) {
        this.pledgeBondList = pledgeBondList;
    }

    /**
     * 获取 avaPledgeBondDetailList
     *
     * @rturn avaPledgeBondDetailList
     */
    public List<PledgeBondInfoDto> getAvaPledgeBondDetailList() {
        return this.avaPledgeBondDetailList;
    }

    /**
     * 设置 avaPledgeBondDetailList
     *
     * @param avaPledgeBondDetailList avaPledgeBondDetailList
     */
    public void setAvaPledgeBondDetailList(List<PledgeBondInfoDto> avaPledgeBondDetailList) {
        this.avaPledgeBondDetailList = avaPledgeBondDetailList;
    }

    
    /**
     * 获取 pledgeBondListDetailed
     * @return pledgeBondListDetailed.
     */
    public List<PledgeBondInfoDto> getPledgeBondListDetailed() {
        return pledgeBondListDetailed;
    }

    
    /**
     * 设置 pledgeBondListDetailed
     * @param pledgeBondListDetailed pledgeBondListDetailed
     */
    public void setPledgeBondListDetailed(List<PledgeBondInfoDto> pledgeBondListDetailed) {
        this.pledgeBondListDetailed = pledgeBondListDetailed;
    }

    
    /**
     * 获取 distribution
     * @return distribution.
     */
    public String getDistribution() {
        return distribution;
    }

    
    /**
     * 设置 distribution
     * @param distribution distribution
     */
    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getExchangePledge() {
        return exchangePledge;
    }

    public void setExchangePledge(String exchangePledge) {
        this.exchangePledge = exchangePledge;
    }

    /**
     * 获取 finprodId
     *
     * @return finprodId
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     *
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }
}
