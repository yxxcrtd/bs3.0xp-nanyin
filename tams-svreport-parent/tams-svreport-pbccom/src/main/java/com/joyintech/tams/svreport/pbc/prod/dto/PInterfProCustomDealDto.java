/*
 * 文件名：PInterfProCustomDealDto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：qiangchengxi
 * 修改时间：2019年8月26日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.svreport.pbc.prod.dto;

import java.util.Date;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2019年8月26日
 * @see PInterfProCustomDealDto
 * @since 1.0
 */
public class PInterfProCustomDealDto {
     
    /*产品代码*/        
    private String finprodId    ;
    /*省份代码*/        
    private String buyPlace     ;
    /*省份名称*/        
    private String buyPlaceName ;
    /*客户类型(一级)*/  
    private String customerType1;
    /*客户类型(二级)*/  
    private String customerType2;
    /*币种*/            
    private String ccy          ;
    /*交易类型*/        
    private String dealtype     ;
    /*交易日期*/        
    private String dealdate     ;
    /*总交易金额*/      
    private String dealamt      ;
    /*总交易份额*/      
    private String prinqty      ;
    /*机构交易金额*/    
    private String orgamt       ;
    /*机构交易份额*/    
    private String orgqty       ;
    /*个人交易金额*/    
    private String peramy       ;
    /*个人交易份额*/    
    private String perqty       ;
    /*备注*/            
    private String remark       ;
    /*创建时间*/        
    private Date createTime   ;
    /*更新时间*/        
    private Date updateTime   ;
    /*产品发行方式*/
    private String issueStatus ; 
    /* 提前终止日 */
    private Date terminatedate;
    
    /* 产品到期日 */
    private Date mDate;
  
    /**
     * 获取 mDate
     * @return mDate.
     */
    public Date getmDate() {
        return mDate;
    }



    
    /**
     * 设置 mDate
     * @param mDate mDate
     */
    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }



    /**
     * 获取 terminatedate
     * @return terminatedate.
     */
    public Date getTerminatedate() {
        return terminatedate;
    }


    
    /**
     * 设置 terminatedate
     * @param terminatedate terminatedate
     */
    public void setTerminatedate(Date terminatedate) {
        this.terminatedate = terminatedate;
    }


    /**
     * 获取 issueStatus
     * @return issueStatus.
     */
    public String getIssueStatus() {
        return issueStatus;
    }

    
    /**
     * 设置 issueStatus
     * @param issueStatus issueStatus
     */
    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }

    /**
     * 获取 finprodId
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }
    
    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }
    
    /**
     * 获取 buyPlace
     * @return buyPlace.
     */
    public String getBuyPlace() {
        return buyPlace;
    }
    
    /**
     * 设置 buyPlace
     * @param buyPlace buyPlace
     */
    public void setBuyPlace(String buyPlace) {
        this.buyPlace = buyPlace;
    }
    
    /**
     * 获取 buyPlaceName
     * @return buyPlaceName.
     */
    public String getBuyPlaceName() {
        return buyPlaceName;
    }
    
    /**
     * 设置 buyPlaceName
     * @param buyPlaceName buyPlaceName
     */
    public void setBuyPlaceName(String buyPlaceName) {
        this.buyPlaceName = buyPlaceName;
    }
    
    /**
     * 获取 customerType1
     * @return customerType1.
     */
    public String getCustomerType1() {
        return customerType1;
    }
    
    /**
     * 设置 customerType1
     * @param customerType1 customerType1
     */
    public void setCustomerType1(String customerType1) {
        this.customerType1 = customerType1;
    }
    
    /**
     * 获取 customerType2
     * @return customerType2.
     */
    public String getCustomerType2() {
        return customerType2;
    }
    
    /**
     * 设置 customerType2
     * @param customerType2 customerType2
     */
    public void setCustomerType2(String customerType2) {
        this.customerType2 = customerType2;
    }
    
    /**
     * 获取 ccy
     * @return ccy.
     */
    public String getCcy() {
        return ccy;
    }
    
    /**
     * 设置 ccy
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }
    
    /**
     * 获取 dealtype
     * @return dealtype.
     */
    public String getDealtype() {
        return dealtype;
    }
    
    /**
     * 设置 dealtype
     * @param dealtype dealtype
     */
    public void setDealtype(String dealtype) {
        this.dealtype = dealtype;
    }
    
    /**
     * 获取 dealdate
     * @return dealdate.
     */
    public String getDealdate() {
        return dealdate;
    }
    
    /**
     * 设置 dealdate
     * @param dealdate dealdate
     */
    public void setDealdate(String dealdate) {
        this.dealdate = dealdate;
    }
    
    /**
     * 获取 dealamt
     * @return dealamt.
     */
    public String getDealamt() {
        return dealamt;
    }
    
    /**
     * 设置 dealamt
     * @param dealamt dealamt
     */
    public void setDealamt(String dealamt) {
        this.dealamt = dealamt;
    }
    
    /**
     * 获取 prinqty
     * @return prinqty.
     */
    public String getPrinqty() {
        return prinqty;
    }
    
    /**
     * 设置 prinqty
     * @param prinqty prinqty
     */
    public void setPrinqty(String prinqty) {
        this.prinqty = prinqty;
    }
    
    /**
     * 获取 orgamt
     * @return orgamt.
     */
    public String getOrgamt() {
        return orgamt;
    }
    
    /**
     * 设置 orgamt
     * @param orgamt orgamt
     */
    public void setOrgamt(String orgamt) {
        this.orgamt = orgamt;
    }
    
    /**
     * 获取 orgqty
     * @return orgqty.
     */
    public String getOrgqty() {
        return orgqty;
    }
    
    /**
     * 设置 orgqty
     * @param orgqty orgqty
     */
    public void setOrgqty(String orgqty) {
        this.orgqty = orgqty;
    }
    
    /**
     * 获取 peramy
     * @return peramy.
     */
    public String getPeramy() {
        return peramy;
    }
    
    /**
     * 设置 peramy
     * @param peramy peramy
     */
    public void setPeramy(String peramy) {
        this.peramy = peramy;
    }
    
    /**
     * 获取 perqty
     * @return perqty.
     */
    public String getPerqty() {
        return perqty;
    }
    
    /**
     * 设置 perqty
     * @param perqty perqty
     */
    public void setPerqty(String perqty) {
        this.perqty = perqty;
    }
    
    /**
     * 获取 remark
     * @return remark.
     */
    public String getRemark() {
        return remark;
    }
    
    /**
     * 设置 remark
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }



    
    /**
     * 获取 createTime
     * @return createTime.
     */
    public Date getCreateTime() {
        return createTime;
    }



    
    /**
     * 设置 createTime
     * @param createTime createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



    
    /**
     * 获取 updateTime
     * @return updateTime.
     */
    public Date getUpdateTime() {
        return updateTime;
    }



    
    /**
     * 设置 updateTime
     * @param updateTime updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
   
    
    
    
}
