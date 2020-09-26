/*
 * 文件名：BookDetailCompareIntoReadResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.accounting.entity;

import java.util.Date;

import com.joyintech.tams.common.base.entity.BokBookdetailCurrent;

/**
 * 交易最新账务查询
 * 
 * @author chengzhong
 * @version 1.0 2018年6月7日
 * @see BookNewestInfoDto
 * @since 1.0
 */
public class BookNewestInfoDto extends BokBookdetailCurrent {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 5677201097268785067L;



    /**
     * 账套日期表.账套日期
     */
    private Date booksetDateNew;



    
    /**
     * 获取 账套日期表.账套日期
     * @return booksetDateNew.
     */
    public Date getBooksetDateNew() {
        return booksetDateNew;
    }



    
    /**
     * 设置 账套日期表.账套日期
     * @param booksetDateNew 账套日期表.账套日期
     */
    public void setBooksetDateNew(Date booksetDateNew) {
        this.booksetDateNew = booksetDateNew;
    }
    
    
}
