package com.joyintech.service.portfolio.portfolioanalysis.ptlrepopledgesearch.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.math.BigDecimal;

public class PtlPldgeSummaryDto extends BaseEntity {
    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 查询时间
     */
    private String searchDate;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 债券代码
     */
    private String secid;

    /**
     * 债券名称
     */
    private String secName;

    /**
     * 债券持仓
     */
    private BigDecimal prinAmt;

    /**
     * 已质押券面
     */
    private BigDecimal pledgeAmt;

    /**
     * 可质押券面
     */
    private BigDecimal canpledgeAmt;

    /**
     * 剩余券面
     */
    private BigDecimal remainAmt;

    //主体评级
    private String gradeTypezt;
    //主体评级名称
    private String gradeTypeNamezt;
    //债项评级
    private String gradeTypezx;
    //债项评级名称
    private String gradeTypeNamezx;
    //登记托管机构
    private String registOrg;
    //登记托管机构名称
    private String registOrgName;

    /**
     * 获取 gradeTypezt
     * @return gradeTypezt.
     */
    public String getGradeTypezt() {
        return gradeTypezt;
    }


    /**
     * 设置 gradeTypezt
     * @param gradeTypezt gradeTypezt
     */
    public void setGradeTypezt(String gradeTypezt) {
        this.gradeTypezt = gradeTypezt;
    }


    /**
     * 获取 gradeTypeNamezt
     * @return gradeTypeNamezt.
     */
    public String getGradeTypeNamezt() {
        return SysDataUtil.getNameFromDataDict(DictCode.资产评级, this.getGradeTypezt()== null?"":this.getGradeTypezt().trim());
    }


    /**
     * 设置 gradeTypeNamezt
     * @param gradeTypeNamezt gradeTypeNamezt
     */
    public void setGradeTypeNamezt(String gradeTypeNamezt) {
        this.gradeTypeNamezt = gradeTypeNamezt;
    }


    /**
     * 获取 gradeTypezx
     * @return gradeTypezx.
     */
    public String getGradeTypezx() {
        return gradeTypezx;
    }


    /**
     * 设置 gradeTypezx
     * @param gradeTypezx gradeTypezx
     */
    public void setGradeTypezx(String gradeTypezx) {
        this.gradeTypezx = gradeTypezx;
    }


    /**
     * 获取 gradeTypeNamezx
     * @return gradeTypeNamezx.
     */
    public String getGradeTypeNamezx() {
        return SysDataUtil.getNameFromDataDict(DictCode.资产评级, this.getGradeTypezx()== null?"":this.getGradeTypezx().trim());
    }


    /**
     * 设置 gradeTypeNamezx
     * @param gradeTypeNamezx gradeTypeNamezx
     */
    public void setGradeTypeNamezx(String gradeTypeNamezx) {
        this.gradeTypeNamezx = gradeTypeNamezx;
    }


    /**
     * 获取 registOrg
     * @return registOrg.
     */
    public String getRegistOrg() {
        return registOrg;
    }


    /**
     * 设置 registOrg
     * @param registOrg registOrg
     */
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg;
    }


    /**
     * 获取 registOrgName
     * @return registOrgName.
     */
    public String getRegistOrgName() {
        return SysDataUtil.getNameFromDataDict(DictCode.登记托管机构, this.getRegistOrg()== null?"":this.getRegistOrg().trim());
    }


    /**
     * 设置 registOrgName
     * @param registOrgName registOrgName
     */
    public void setRegistOrgName(String registOrgName) {
        this.registOrgName = registOrgName;
    }

    public BigDecimal getCanpledgeAmt() {
        return canpledgeAmt;
    }

    public void setCanpledgeAmt(BigDecimal canpledgeAmt) {
        this.canpledgeAmt = canpledgeAmt;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(String searchDate) {
        this.searchDate = searchDate;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getSecid() {
        return secid;
    }

    public void setSecid(String secid) {
        this.secid = secid;
    }

    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName;
    }

    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    public BigDecimal getPledgeAmt() {
        return pledgeAmt;
    }

    public void setPledgeAmt(BigDecimal pledgeAmt) {
        this.pledgeAmt = pledgeAmt;
    }

    public BigDecimal getRemainAmt() {
        return remainAmt;
    }

    public void setRemainAmt(BigDecimal remainAmt) {
        this.remainAmt = remainAmt;
    }
}
