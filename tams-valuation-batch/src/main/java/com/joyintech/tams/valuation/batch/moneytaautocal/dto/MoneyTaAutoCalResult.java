/*
 * 文件名：MoneyTaAutoCalResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：May 25, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.moneytaautocal.dto;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * 货币型产品自动结转处理
 * 
 * @author zhangwei
 * @version 1.0 May 25, 2019
 * @see MoneyTaAutoCalResult
 * @since 1.0
 */
public class MoneyTaAutoCalResult extends NeedOperateEntity {

    /**
     * 账套明细
     */
    List<BokBookDetilDto> listBokBookDetilDto = Lists.newArrayList();

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 账套日期
     */
    private Date booksetDate;

    /**
     * 发生日期
     */
    private Date happenDate;

    /**
     * 入账日期
     */
    private Date bookDate;

    /**
     * 本位币
     */
    private String standardCcy;

    public List<BokBookDetilDto> getListBokBookDetilDto() {
        return listBokBookDetilDto;
    }

    public void setListBokBookDetilDto(List<BokBookDetilDto> listBokBookDetilDto) {
        this.listBokBookDetilDto = listBokBookDetilDto;
    }

    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public Date getBooksetDate() {
        return booksetDate;
    }

    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }

    public Date getHappenDate() {
        return happenDate;
    }

    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public String getStandardCcy() {
        return standardCcy;
    }

    public void setStandardCcy(String standardCcy) {
        this.standardCcy = standardCcy;
    }

}
