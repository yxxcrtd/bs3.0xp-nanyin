/*
 * 文件名：InterfTcms002OutEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.tcms002.entity;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Required;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.core.annotation.EsbNumber;

/**
 * 理财产品信息查询Tcms002输出
 * 
 * @author changwenbin
 * @version 1.0 2018年11月12日
 * @see InterfTcms002OutEntity
 * @since 1.0
 */
public class InterfTcms002OutEntity extends BaseEntity {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 4217899307876898602L;

    /**
     * 产品代码
     */
    private String prodid;

    /**
     * 产品简称
     */
    private String shortname;

    /**
     * 产品名称
     */
    private String prodname;

    /**
     * 销售部门
     */
    private String mktdeptno;

    /**
     * 产品一级分类
     */
    private String prodtype1;

    /**
     * 销售平台
     */
    private String mktplat;

    /**
     * 产品处理模式
     */
    private String prodmode;

    /**
     * 滚动模式
     */
    private String rollmode;

    /**
     * 产品期次
     */
    private String productperiod;

    /**
     * 发行方式
     */
    private String issuetype;

    /**
     * 分行
     */
    private String branch;

    /**
     * 产品成立日
     */
    private String vdate;

    /**
     * 产品到期日
     */
    private String mdate;

    /**
     * 产品期限
     */
    private String term;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 预期收益率（年化）
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * 认购币种
     */
    private String subccy;

    /**
     * 收益币种
     */
    private String settccy;

    /**
     * 付息周期
     */
    private String intpaycycle;

    /**
     * 销售费率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal syield;

    /**
     * 托管费率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal hyield;

    /**
     * 销售开始日
     */
    private String ssdate;

    /**
     * 销售截止日
     */
    private String sedate;

    /**
     * 产品状态
     */
    private String prdstatus;

    /**
     * 文件地址
     */
    private String fileurl;

    /**
     * 金融产品代码
     */
    private String finprodid;

    /**
     * 获取 产品代码
     * 
     * @return 产品代码
     */
    @EsbName(value = "PRODUCT_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    public String getProdid() {
        return prodid;
    }

    /**
     * 设置 产品代码
     * 
     * @param prodid 产品代码
     */
    public void setProdid(String prodid) {
        this.prodid = prodid;
    }

    /**
     * 获取 产品简称
     * 
     * @return 产品简称
     */
    @EsbName(value = "PRODUCT_SHORTNAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 100)
    public String getShortname() {
        return shortname;
    }

    /**
     * 设置 产品简称
     * 
     * @param shortname 产品简称
     */
    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    /**
     * 获取 产品名称
     * 
     * @return 产品名称
     */
    @EsbName(value = "PRODUCT_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 100)
    public String getProdname() {
        return prodname;
    }

    /**
     * 设置 产品名称
     * 
     * @param prodname 产品名称
     */
    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    /**
     * 获取 销售部门
     * 
     * @return 销售部门
     */
    @EsbName(value = "DEPT_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getMktdeptno() {
        return mktdeptno;
    }

    /**
     * 设置 销售部门
     * 
     * @param mktdeptno 销售部门
     */
    public void setMktdeptno(String mktdeptno) {
        this.mktdeptno = mktdeptno;
    }

    /**
     * 获取 产品一级分类
     * 
     * @return 产品一级分类
     */
    @EsbName(value = "PRODUCT_TYPE_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getProdtype1() {
        return prodtype1;
    }

    /**
     * 设置 产品一级分类
     * 
     * @param prodtype1 产品一级分类
     */
    public void setProdtype1(String prodtype1) {
        this.prodtype1 = prodtype1;
    }

    /**
     * 获取 销售平台
     * 
     * @return 销售平台
     */
    @EsbName(value = "SALE_PLAT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getMktplat() {
        return mktplat;
    }

    /**
     * 设置 销售平台
     * 
     * @param mktplat 销售平台
     */
    public void setMktplat(String mktplat) {
        this.mktplat = mktplat;
    }

    /**
     * 获取 产品处理模式
     * 
     * @return 产品处理模式
     */
    @EsbName(value = "PRODUCT_BUSS_MODEL")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getProdmode() {
        return prodmode;
    }

    /**
     * 设置 产品处理模式
     * 
     * @param prodmode 产品处理模式
     */
    public void setProdmode(String prodmode) {
        this.prodmode = prodmode;
    }

    /**
     * 获取 滚动模式
     * 
     * @return 滚动模式
     */
    @EsbName(value = "ROLL_MODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getRollmode() {
        return rollmode;
    }

    /**
     * 设置 滚动模式
     * 
     * @param rollmode 滚动模式
     */
    public void setRollmode(String rollmode) {
        this.rollmode = rollmode;
    }
	/**
     * 获取 产品期次
     * 
     * @return 产品期次
     */
    @EsbName(value = "PRODUCT_PERIOD")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 3)
    public String getProductperiod() {
        return productperiod;
    }

    /**
     * 设置 产品期次
     * 
     * @param productperiod 产品期次
     */
    public void setProductperiod(String productperiod) {
        this.productperiod = productperiod;
    }

    /**
     * 获取 发行方式
     * 
     * @return 发行方式
     */
    @EsbName(value = "ISSUE_MODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 1)
    public String getIssuetype() {
        return issuetype;
    }

    /**
     * 设置 发行方式
     * 
     * @param issuetype 发行方式
     */
    public void setIssuetype(String issuetype) {
        this.issuetype = issuetype;
    }

    /**
     * 获取 分行
     * 
     * @return 分行
     */
    @EsbName(value = "ACCT_BRANCH_ID")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getBranch() {
        return branch;
    }

    /**
     * 设置 分行
     * 
     * @param branch 分行
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * 获取 产品成立日
     * 
     * @return 产品成立日
     */
    @EsbName(value = "ESTABLISH_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getVdate() {
        return vdate;
    }

    /**
     * 设置 产品成立日
     * 
     * @param vdate 产品成立日
     */
    public void setVdate(String vdate) {
        this.vdate = vdate;
    }

    /**
     * 获取 产品到期日
     * 
     * @return 产品到期日
     */
    @EsbName(value = "EXPIRY_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getMdate() {
        return mdate;
    }

    /**
     * 设置 产品到期日
     * 
     * @param mdate 产品到期日
     */
    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    /**
     * 获取 产品期限
     * 
     * @return 产品期限
     */
    @EsbName(value = "TERM")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    public String getTerm() {
        return term;
    }

    /**
     * 设置 产品期限
     * 
     * @param term 产品期限
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * 获取 计息基础
     * 
     * @return 计息基础
     */
    @EsbName(value = "INTEREST_MODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 6)
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 计息基础
     * 
     * @param basis 计息基础
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    /**
     * 获取 预期收益率（年化）
     * 
     * @return 预期收益率（年化）
     */
    @EsbName(value = "YIELD")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 7)
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 预期收益率（年化）
     * 
     * @param yield 预期收益率（年化）
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 获取 认购币种
     * 
     * @return 认购币种
     */
    @EsbName(value = "SUBSCRIPTION_CCY")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 3)
    public String getSubccy() {
        return subccy;
    }

    /**
     * 设置 认购币种
     * 
     * @param subccy 认购币种
     */
    public void setSubccy(String subccy) {
        this.subccy = subccy;
    }

    /**
     * 获取 收益币种
     * 
     * @return 收益币种
     */
    @EsbName(value = "INCOME_CCY")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 3)
    public String getSettccy() {
        return settccy;
    }

    /**
     * 设置 收益币种
     * 
     * @param settccy 收益币种
     */
    public void setSettccy(String settccy) {
        this.settccy = settccy;
    }

    /**
     * 获取 付息周期
     * 
     * @return 付息周期
     */
    @EsbName(value = "INTEREST_PERIOD")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 3)
    public String getIntpaycycle() {
        return intpaycycle;
    }

    /**
     * 设置 付息周期
     * 
     * @param intpaycycle 付息周期
     */
    public void setIntpaycycle(String intpaycycle) {
        this.intpaycycle = intpaycycle;
    }

    /**
     * 获取 销售费率
     * 
     * @return 销售费率
     */
    @EsbName(value = "SALE_FEE_RATE")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 9)
    public BigDecimal getSyield() {
        return syield;
    }

    /**
     * 设置 销售费率
     * 
     * @param syield 销售费率
     */
    public void setSyield(BigDecimal syield) {
        this.syield = syield;
    }

    /**
     * 获取 托管费率
     * 
     * @return 托管费率
     */
    @EsbName(value = "CONSIGN_FEE_RATE")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 9)
    public BigDecimal getHyield() {
        return hyield;
    }

    /**
     * 设置 托管费率
     * 
     * @param hyield 托管费率
     */
    public void setHyield(BigDecimal hyield) {
        this.hyield = hyield;
    }

    /**
     * 获取 销售开始日
     * 
     * @return 销售开始日
     */
    @EsbName(value = "BEGIN_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getSsdate() {
        return ssdate;
    }

    /**
     * 设置 销售开始日
     * 
     * @param ssdate 销售开始日
     */
    public void setSsdate(String ssdate) {
        this.ssdate = ssdate;
    }

    /**
     * 获取 销售截止日
     * 
     * @return 销售截止日
     */
    @EsbName(value = "END_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getSedate() {
        return sedate;
    }

    /**
     * 设置 销售截止日
     * 
     * @param sedate 销售截止日
     */
    public void setSedate(String sedate) {
        this.sedate = sedate;
    }

    /**
     * 获取 产品状态
     * 
     * @return 产品状态
     */
    @EsbName(value = "PRODUCT_STATUS")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 1)
    public String getPrdstatus() {
        return prdstatus;
    }

    /**
     * 设置 产品状态
     * 
     * @param prdstatus 产品状态
     */
    public void setPrdstatus(String prdstatus) {
        this.prdstatus = prdstatus;
    }

    /**
     * 获取 文件地址
     * 
     * @return 文件地址
     */
    @EsbName(value = "FILE_ADDRESS")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 100)
    public String getFileurl() {
        return fileurl;
    }

    /**
     * 设置 文件地址
     * 
     * @param fileurl 文件地址
     */
    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    /**
     * 获取 金融产品代码
     * 
     * @return 金融产品代码
     */
    public String getFinprodid() {
        return finprodid;
    }

    /**
     * 设置 金融产品代码
     * 
     * @param finprodid 金融产品代码
     */
    public void setFinprodid(String finprodid) {
        this.finprodid = finprodid;
    }

}
