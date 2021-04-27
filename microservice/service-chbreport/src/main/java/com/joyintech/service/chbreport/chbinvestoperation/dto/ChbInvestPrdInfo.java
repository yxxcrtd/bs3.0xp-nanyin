package com.joyintech.service.chbreport.chbinvestoperation.dto;

import com.joyintech.framework.base.BaseEntity;

import java.util.Date;

/**
 * @ClassName ChbInvestPrdInfo
 * @Description: 非标投资运行报告查询及产品基本信息
 * @Author yanya
 * @Date 2021-04-25
 * @Version V1.0
 **/
public class ChbInvestPrdInfo extends BaseEntity {

    private static final long serialVersionUID = 4782725432727267282L;
    /**
     * 产品名称
     */
    private String prodName;
    /**
     * 产品代码
     */
    private String prodId;
    /**
     * 理财信息系统登记编码
     */
    private String regCode;
    /**
     * 投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     */
    private String ivstorientation;

    /**
     * 兑付本金币种
     */
    private String cashPrinCcy;

    /**
     * 产品风险等级  01 一级（低）/02 二级（中低）/03 三级（中）/04 四级（中高）/05 五级（高）
     */
    private String riskLevel;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 境内托管机构名称
     */
    private String inDepName;

    /**
     * 境外托管机构名称
     */
    private String outDepName;

    /**
     *  交易日期
     */
    private Date dealdate;

    /**
     * 境内外显示名称
     */
    private String depName;

    /**
     *  产品管理人-默认（华夏理财有限责任公司）
     */
    private String managerName;

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    public String getIvstorientation() {
        return ivstorientation;
    }

    public void setIvstorientation(String ivstorientation) {
        this.ivstorientation = ivstorientation;
    }

    public String getCashPrinCcy() {
        return cashPrinCcy;
    }

    public void setCashPrinCcy(String cashPrinCcy) {
        this.cashPrinCcy = cashPrinCcy;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public String getInDepName() {
        return inDepName;
    }

    public void setInDepName(String inDepName) {
        this.inDepName = inDepName;
    }

    public String getOutDepName() {
        return outDepName;
    }

    public void setOutDepName(String outDepName) {
        this.outDepName = outDepName;
    }

    public Date getDealdate() {
        return dealdate;
    }

    public void setDealdate(Date dealdate) {
        this.dealdate = dealdate;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
}
