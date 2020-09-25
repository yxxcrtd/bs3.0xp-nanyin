/*
 * 文件名：PaActiveRemind001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.remind.dto;

import com.joyintech.framework.base.BaseEntity;

public class PaActiveRemind002Dto extends BaseEntity{
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    //金融产品代码
    private String finprodid;
    //产品代码
    private String prodid;
    //融资人
    private String financier;
    //发行人
    private String issuer;
    //产品名称
    private String finprodname;
    //管理人
    private String manager;
    //担保人
    private String sponsor;
    //投资顾问
    private String investadviser;
    /**记录是否存在机构负面信息*/
    //标号
    private String objType;
    //人
    private String orgId;
    
    
    
    /**
     * 获取 objType
     * @return objType.
     */
    public String getObjType() {
        return objType;
    }

    
    /**
     * 设置 objType
     * @param objType objType
     */
    public void setObjType(String objType) {
        this.objType = objType;
    }

    
    /**
     * 获取 orgId
     * @return orgId.
     */
    public String getOrgId() {
        return orgId;
    }

    
    /**
     * 设置 orgId
     * @param orgId orgId
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * 获取 finprodid
     * @return finprodid.
     */
    public String getFinprodid() {
        return finprodid;
    }
    
    /**
     * 设置 finprodid
     * @param finprodid finprodid
     */
    public void setFinprodid(String finprodid) {
        this.finprodid = finprodid;
    }
    
    /**
     * 获取 prodid
     * @return prodid.
     */
    public String getProdid() {
        return prodid;
    }
    
    /**
     * 设置 prodid
     * @param prodid prodid
     */
    public void setProdid(String prodid) {
        this.prodid = prodid;
    }
    
    /**
     * 获取 financier
     * @return financier.
     */
    public String getFinancier() {
        return financier;
    }
    
    /**
     * 设置 financier
     * @param financier financier
     */
    public void setFinancier(String financier) {
        this.financier = financier;
    }
    
    /**
     * 获取 issuer
     * @return issuer.
     */
    public String getIssuer() {
        return issuer;
    }
    
    /**
     * 设置 issuer
     * @param issuer issuer
     */
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }
    
    /**
     * 获取 finprodname
     * @return finprodname.
     */
    public String getFinprodname() {
        return finprodname;
    }
    
    /**
     * 设置 finprodname
     * @param finprodname finprodname
     */
    public void setFinprodname(String finprodname) {
        this.finprodname = finprodname;
    }
    
    /**
     * 获取 manager
     * @return manager.
     */
    public String getManager() {
        return manager;
    }
    
    /**
     * 设置 manager
     * @param manager manager
     */
    public void setManager(String manager) {
        this.manager = manager;
    }
    
    /**
     * 获取 sponsor
     * @return sponsor.
     */
    public String getSponsor() {
        return sponsor;
    }
    
    /**
     * 设置 sponsor
     * @param sponsor sponsor
     */
    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }
    
    /**
     * 获取 investadviser
     * @return investadviser.
     */
    public String getInvestadviser() {
        return investadviser;
    }
    
    /**
     * 设置 investadviser
     * @param investadviser investadviser
     */
    public void setInvestadviser(String investadviser) {
        this.investadviser = investadviser;
    }
    
}
