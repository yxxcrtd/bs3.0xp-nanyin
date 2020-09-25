/*
 * 文件名：BookLatestInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月14日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.component.business.evaluation.dto;

import java.util.Date;

import com.joyintech.tams.common.base.entity.BokBookdetailCurrent;


/**
 * 凭证最新账务查询
 * @author Administrator
 * @version 1.0 2018年7月14日
 * @see BookLatestInfoDto
 * @since 1.0
 */
public class BookLatestInfoDto extends BokBookdetailCurrent{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
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
