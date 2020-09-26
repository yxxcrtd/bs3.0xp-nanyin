/*
 * 文件名：QueryDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2019年2月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.financemanage.balanceunusualquery.dto;

import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author HuangJian
 * @version 1.0 2019年2月13日
 * @see QueryDto
 * @since 1.0
 */
public class QueryDto {
    
    /**
     * 账套代码
     */
    private String booksetId;
    /**
     * 资产类型组
     */
    private String[] fintypes;
    /**
     * 清零科目性质组
     */
    private String[] subjectno;
    /**
     * 查询日期
     */
    private String qrydate;
    /**
     * 资产代码组
     */
    private List<String> assetidArr;
    
    private List<String> noNullArr;
    
    /**
     * 获取 booksetId
     * @return booksetId.
     */
    public String getBooksetId() {
        return booksetId;
    }
    
    /**
     * 设置 booksetId
     * @param booksetId booksetId
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }
    
    /**
     * 获取 fintypes
     * @return fintypes.
     */
    public String[] getFintypes() {
        return fintypes;
    }
    
    /**
     * 设置 fintypes
     * @param fintypes fintypes
     */
    public void setFintypes(String[] fintypes) {
        this.fintypes = fintypes;
    }
    
    /**
     * 获取 subjectno
     * @return subjectno.
     */
    public String[] getSubjectno() {
        return subjectno;
    }
    
    /**
     * 设置 subjectno
     * @param subjectno subjectno
     */
    public void setSubjectno(String[] subjectno) {
        this.subjectno = subjectno;
    }
    
    /**
     * 获取 qrydate
     * @return qrydate.
     */
    public String getQrydate() {
        return qrydate;
    }
    
    /**
     * 设置 qrydate
     * @param qrydate qrydate
     */
    public void setQrydate(String qrydate) {
        this.qrydate = qrydate;
    }
    
    /**
     * 获取 assetidArr
     * @return assetidArr.
     */
    public  List<String> getAssetidArr() {
        return assetidArr;
    }
    
    /**
     * 设置 assetidArr
     * @param assetidArr assetidArr
     */
    public void setAssetidArr(List<String> assetidArr) {
        this.assetidArr = assetidArr;
    }

    
    /**
     * 获取 noNullArr
     * @return noNullArr.
     */
    public List<String> getNoNullArr() {
        return noNullArr;
    }

    
    /**
     * 设置 noNullArr
     * @param noNullArr noNullArr
     */
    public void setNoNullArr(List<String> noNullArr) {
        this.noNullArr = noNullArr;
    }

    
 
    
    
    
    
    

}
