/*
 * 文件名：BalTableGenerate001HandleDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：wangbo
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.balancetablegeneration.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 记账凭证查询dto
 * 
 * @author wangbo
 * @version 2.0 2018年5月3日
 * @see BalTableGenerate001HandleDto
 * @since 2.0
 */
public class BalTableGenerate001HandleDto extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     * 金融产品代码
     */
    private String finProdId;
    
    /**
     * 金融产品名称
     */
    private String finprodName;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 余额日期
     */
    private Date balDate;

    /**
     * 产品简称
     */
    private String oCcy;

    /**
     * 产品类型
     */
    private int errorCount;
    
    
    /**
     * 获取 finprodName
     * @return finprodName.
     */
    public String getFinprodName() {
        return finprodName;
    }



    
    /**
     * 设置 finprodName
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }



    /**
     * 获取 finProdId
     * @return finProdId.
     */
    public String getFinProdId() {
        return finProdId;
    }


    
    /**
     * 设置 finProdId
     * @param finProdId finProdId
     */
    public void setFinProdId(String finProdId) {
        this.finProdId = finProdId;
    }


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
     * 获取 balDate
     * @return balDate.
     */
    public Date getBalDate() {
        return balDate;
    }

    
    /**
     * 设置 balDate
     * @param balDate balDate
     */
    public void setBalDate(Date balDate) {
        this.balDate = balDate;
    }

    
    /**
     * 获取 oCcy
     * @return oCcy.
     */
    public String getoCcy() {
        return oCcy;
    }

    
    /**
     * 设置 oCcy
     * @param oCcy oCcy
     */
    public void setoCcy(String oCcy) {
        this.oCcy = oCcy;
    }

    
    /**
     * 获取 errorCount
     * @return errorCount.
     */
    public int getErrorCount() {
        return errorCount;
    }

    
    /**
     * 设置 errorCount
     * @param errorCount errorCount
     */
    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

}
