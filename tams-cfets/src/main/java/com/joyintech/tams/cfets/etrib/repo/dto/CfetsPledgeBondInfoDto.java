/*
 * 文件名：EtrInvestPersonInfo002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.cfets.etrib.repo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 添加质押券Dto
 * 〈功能详细描述〉
 *
 * @author chenzhili
 * @version 1.0 2019年01月12日
 * @see CfetsPledgeBondInfoDto
 * @since 1.0
 */
public class CfetsPledgeBondInfoDto extends BaseEntity {

    /**
     * 债券代码
     */
    @ApiModelProperty(name = "bondCode",value="债券代码")
    private String bondCode;

    /**
     * 债券金融产品代码
     */
    @ApiModelProperty(name = "finprodId",value="债券金融产品代码")
    private String finprodId;
    
    /**
     * 委托代码
     */
    private String entrustId;


    /**
     * 债券简称
     */
    private String bondAbbrName;

    /**
     * 组合代码
     */
    private String portfolioId;
    
    /**
     * 组合名称
     */
    private String portfolioName;
    
    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 可质押券面总额
     */
    private BigDecimal avaPledgePrin;
    /**
     * 可质押本方券券面总额
     */
    private BigDecimal avaPledgeBfPrin;
    /**
     * 可质押融入券券面总额
     */
    private BigDecimal avaPledgeRrPrin;
    /**
     * 质押券面额
     */
    private BigDecimal pledgePrin;

    /**
     * 质押券折算总额
     */
    private BigDecimal pledgeAmt;
    /**
     * 质押比例%
     */
    private BigDecimal pledgeRatio;
    /**
     * 登记托管机构
     */
    private String repRegistOrg;
    /**
     * 按投资目的排序：摊余成本类->公允价值变动计入其他综合收益->公允价值变动计入当期损益
     */
    private Long invAimSort;


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
     * 获取 bondAbbrName
     *
     * @rturn bondAbbrName
     */
    public String getBondAbbrName() {
        return this.bondAbbrName;
    }

    /**
     * 设置 bondAbbrName
     *
     * @param bondAbbrName bondAbbrName
     */
    public void setBondAbbrName(String bondAbbrName) {
        this.bondAbbrName = bondAbbrName;
    }

    /**
     * 获取 mdate
     *
     * @rturn mdate
     */
    public Date getMdate() {
        return this.mdate;
    }

    /**
     * 设置 mdate
     *
     * @param mdate mdate
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 获取 avaPledgePrin
     *
     * @rturn avaPledgePrin
     */
    public BigDecimal getAvaPledgePrin() {
        return this.avaPledgePrin;
    }

    /**
     * 设置 avaPledgePrin
     *
     * @param avaPledgePrin avaPledgePrin
     */
    public void setAvaPledgePrin(BigDecimal avaPledgePrin) {
        this.avaPledgePrin = avaPledgePrin;
    }

    /**
     * 获取 pledgePrin
     *
     * @rturn pledgePrin
     */
    public BigDecimal getPledgePrin() {
        return this.pledgePrin;
    }

    /**
     * 设置 pledgePrin
     *
     * @param pledgePrin pledgePrin
     */
    public void setPledgePrin(BigDecimal pledgePrin) {
        this.pledgePrin = pledgePrin;
    }

    public BigDecimal getPledgeAmt() {
        return pledgeAmt;
    }

    public void setPledgeAmt(BigDecimal pledgeAmt) {
        this.pledgeAmt = pledgeAmt;
    }

    /**
     * 获取 pledgeRatio
     *
     * @rturn pledgeRatio
     */
    public BigDecimal getPledgeRatio() {
        return this.pledgeRatio;
    }

    /**
     * 设置 pledgeRatio
     *
     * @param pledgeRatio pledgeRatio
     */
    public void setPledgeRatio(BigDecimal pledgeRatio) {
        this.pledgeRatio = pledgeRatio;
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
     * 获取 登记托管机构
     * @rturn ccyText
     */
    public String getRepRegistOrgStr() {
        if(StringUtil.isNoneBlank( this.getRepRegistOrg())){
            return SysDataUtil.getNameFromDataDict(DictCode.登记托管机构_中债, this.getRepRegistOrg());
        }
        return null;
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
     * 获取 portfolioId
     * @return portfolioId.
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 portfolioId
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
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
     * 获取 entrustId
     * @return entrustId.
     */
    public String getEntrustId() {
        return entrustId;
    }

    /**
     * 设置 entrustId
     * @param entrustId entrustId
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    public BigDecimal getAvaPledgeBfPrin() {
        return avaPledgeBfPrin;
    }

    public void setAvaPledgeBfPrin(BigDecimal avaPledgeBfPrin) {
        this.avaPledgeBfPrin = avaPledgeBfPrin;
    }

    public BigDecimal getAvaPledgeRrPrin() {
        return avaPledgeRrPrin;
    }

    public void setAvaPledgeRrPrin(BigDecimal avaPledgeRrPrin) {
        this.avaPledgeRrPrin = avaPledgeRrPrin;
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
