/*
 * 文件名：PbcClassicsReportType.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2019年6月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.svreport.pbc.classic.data.dto;


/**
 * 人行单产品报表类型
 * @author chenxc
 * @version 1.0 2019年6月10日
 * @see PbcClassicsReportType
 * @since 1.0
 */
public enum PbcClassicsReportType {
    
    人行产品基本信息登记("PBCCLASSICSTYPE_PROD_BASEINFO","人行产品基本信息登记"),
    人行资产池信息登记("PBCCLASSICSTYPE_ACCOUNT_BASEINFO","人行资产池信息登记"),
    人行产品起始募集信息登记("PBCCLASSICSTYPE_PROD_INITRAISE","人行产品起始募集信息登记"),
    人行产品终止信息登记("PBCCLASSICSTYPE_PROD_END","人行产品起始募集信息登记"),
    人行资产池终止信息登记("PBCCLASSICSTYPE_ACCOUNT_END","人行产品起始募集信息登记"),
    人行产品资金募集信息登记("PBCCLASSICSTYPE_PROD_RAISE","人行产品起始募集信息登记"),
    人行产品资产负债信息登记("PBCCLASSICSTYPE_PROD_BALANCE","人行产品起始募集信息登记");
    
    /*两个属性*/
    private String code;
    private String name;
    
   /*构造函数*/
    private PbcClassicsReportType(String code, String name) {
        this.code = code;
        this.name = name;
    }
    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }
}
