package com.joyintech.service.portfolio.portfolioanalysis.ptlrepopledgesearch.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.FinDealPledge;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: tams-invest-parent
 * @description: PtlRepoPledgeSearchDto
 * @author: liuwei
 * @create: 2018-11-28 14:10
 **/
public class PtlRepoPledgeSearchDto extends FinDealPledge {
    /**
     * 组合代码
     */
    private String counterId;
    /**
     * 分类4（wind一级类型）
     */
    private String type4;

    /**
     * 分类5（wind二级类型）
     */
    private String type5;

    /**
     * 业务类型
     */
    private String finprodType2;

    /**
     * 市场代码，可以按规则生成，或者手输，不校验唯一。对于理财产品分层，同母金融产品代码对应的市场代码。
     */
    private String finprodMarketId;
    /**
     * 金融产品简称
     */
    private String finprodAbbr;
    /**
     * 登记托管机构，中债、上清、中证等
     */
    private String registOrg;
    /**
     * 是否次级债
     */
    private String isSecBond;
    /**
     * 固定利率
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal intRate;
    /**
     * 期限(天)
     */
    private Long term;
    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;
    /**
     * 评级结果
     */
    private String gradeResult;
    /**
     * 企业性质
     */
    private String companyNature;

    /**
     * 持仓券面
     */
    private BigDecimal prinAmt;

    /**
     * 可质押券面
     */
    private BigDecimal canpledgeAmt;

    /**
     * 已质押券面
     */
    private BigDecimal usedPledgeAmt;

    /**
     * 剩余可质押券面
     */
    private BigDecimal remainAmt;

    /**
     * 获取 prinAmt
     *
     * @return prinAmt
     */
    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    /**
     * 设置 prinAmt
     *
     * @param prinAmt prinAmt
     */
    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    /**
     * 获取 canpledgeAmt
     *
     * @return canpledgeAmt
     */
    public BigDecimal getCanpledgeAmt() {
        return canpledgeAmt;
    }

    /**
     * 设置 canpledgeAmt
     *
     * @param canpledgeAmt canpledgeAmt
     */
    public void setCanpledgeAmt(BigDecimal canpledgeAmt) {
        this.canpledgeAmt = canpledgeAmt;
    }

    /**
     * 获取 usedPledgeAmt
     *
     * @return usedPledgeAmt
     */
    public BigDecimal getUsedPledgeAmt() {
        return usedPledgeAmt;
    }

    /**
     * 设置 usedPledgeAmt
     *
     * @param usedPledgeAmt usedPledgeAmt
     */
    public void setUsedPledgeAmt(BigDecimal usedPledgeAmt) {
        this.usedPledgeAmt = usedPledgeAmt;
    }

    /**
     * 获取 remainAmt
     *
     * @return remainAmt
     */
    public BigDecimal getRemainAmt() {
        return remainAmt;
    }

    /**
     * 设置 remainAmt
     *
     * @param remainAmt remainAmt
     */
    public void setRemainAmt(BigDecimal remainAmt) {
        this.remainAmt = remainAmt;
    }

    /**
     * 获取 counterId
     *
     * @return counterId
     */
    public String getCounterId() {
        return counterId;
    }

    /**
     * 设置 counterId
     *
     * @param counterId counterId
     */
    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

    /**
     * 获取 type4
     *
     * @return type4
     */
    public String getType4() {
        return type4;
    }

    /**
     * 设置 type4
     *
     * @param type4 type4
     */
    public void setType4(String type4) {
        this.type4 = type4;
    }

    /**
     * 获取 type5
     *
     * @return type5
     */
    public String getType5() {
        return type5;
    }

    /**
     * 设置 type5
     *
     * @param type5 type5
     */
    public void setType5(String type5) {
        this.type5 = type5;
    }

    /**
     * 获取 finprodMarketId
     *
     * @return finprodMarketId
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    /**
     * 设置 finprodMarketId
     *
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    /**
     * 获取 finprodAbbr
     *
     * @return finprodAbbr
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    /**
     * 设置 finprodAbbr
     *
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    /**
     * 获取 registOrg
     *
     * @return registOrg
     */
    public String getRegistOrg() {
        return registOrg;
    }

    /**
     * 设置 registOrg
     *
     * @param registOrg registOrg
     */
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg;
    }

    /**
     * 获取 isSecBond
     *
     * @return isSecBond
     */
    public String getIsSecBond() {
        return isSecBond;
    }

    /**
     * 设置 isSecBond
     *
     * @param isSecBond isSecBond
     */
    public void setIsSecBond(String isSecBond) {
        this.isSecBond = isSecBond;
    }

    /**
     * 获取 intRate
     *
     * @return intRate
     */
    public BigDecimal getIntRate() {
        return intRate;
    }

    /**
     * 设置 intRate
     *
     * @param intRate intRate
     */
    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }

    /**
     * 获取 term
     *
     * @return term
     */
    public Long getTerm() {
        return term;
    }

    /**
     * 设置 term
     *
     * @param term term
     */
    public void setTerm(Long term) {
        this.term = term;
    }

    /**
     * 获取 vdate
     *
     * @return vdate
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 vdate
     *
     * @param vdate vdate
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 获取 mdate
     *
     * @return mdate
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 mdate
     *
     * @param mdate mdate
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 获取 gradeResult
     *
     * @return gradeResult
     */
    public String getGradeResult() {
        return gradeResult;
    }

    /**
     * 设置 gradeResult
     *
     * @param gradeResult gradeResult
     */
    public void setGradeResult(String gradeResult) {
        this.gradeResult = gradeResult;
    }

    /**
     * 获取 companyNature
     *
     * @return companyNature
     */
    public String getCompanyNature() {
        return companyNature;
    }

    /**
     * 设置 companyNature
     *
     * @param companyNature companyNature
     */
    public void setCompanyNature(String companyNature) {
        this.companyNature = companyNature;
    }

    public String getFinprodType2() {
        return finprodType2;
    }

    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }
}
