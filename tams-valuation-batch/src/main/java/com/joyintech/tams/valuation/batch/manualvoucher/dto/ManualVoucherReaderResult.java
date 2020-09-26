/*
 * 文件名：ManualVoucherReaderResult.java 
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：chenggaoling 
 * 修改时间：2018年8月14日 
 * 修改内容：新建 
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.batch.manualvoucher.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * 
 * 手工凭证批处理Reader集合
 * 
 * @author chenggaoling
 * @version 1.0 2018年8月14日
 * @see ManualVoucherReaderResult
 * @since 1.0
 */
public class ManualVoucherReaderResult extends NeedOperateEntity {
    
    /**
     * 账套代码
     */
    private String booksetId;
    
    /**
     * 业务类型
     */
    private String busiType;

    /**
     * 会计准则
     */
    private String acctStandard;

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

    /**
     * 手工凭证交易
     */
    private List<TradVouchDealDto> tradVouchDealList = new ArrayList<TradVouchDealDto>();

    
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
     * 获取 busiType
     * @return busiType.
     */
    public String getBusiType() {
        return busiType;
    }

    
    /**
     * 设置 busiType
     * @param busiType busiType
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    
    /**
     * 获取 acctStandard
     * @return acctStandard.
     */
    public String getAcctStandard() {
        return acctStandard;
    }

    
    /**
     * 设置 acctStandard
     * @param acctStandard acctStandard
     */
    public void setAcctStandard(String acctStandard) {
        this.acctStandard = acctStandard;
    }

    
    /**
     * 获取 booksetDate
     * @return booksetDate.
     */
    public Date getBooksetDate() {
        return booksetDate;
    }

    
    /**
     * 设置 booksetDate
     * @param booksetDate booksetDate
     */
    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }

    
    /**
     * 获取 happenDate
     * @return happenDate.
     */
    public Date getHappenDate() {
        return happenDate;
    }

    
    /**
     * 设置 happenDate
     * @param happenDate happenDate
     */
    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    
    /**
     * 获取 bookDate
     * @return bookDate.
     */
    public Date getBookDate() {
        return bookDate;
    }

    
    /**
     * 设置 bookDate
     * @param bookDate bookDate
     */
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    
    /**
     * 获取 standardCcy
     * @return standardCcy.
     */
    public String getStandardCcy() {
        return standardCcy;
    }

    
    /**
     * 设置 standardCcy
     * @param standardCcy standardCcy
     */
    public void setStandardCcy(String standardCcy) {
        this.standardCcy = standardCcy;
    }

    
    /**
     * 获取 tradVouchDealList
     * @return tradVouchDealList.
     */
    public List<TradVouchDealDto> getTradVouchDealList() {
        return tradVouchDealList;
    }

    
    /**
     * 设置 tradVouchDealList
     * @param tradVouchDealList tradVouchDealList
     */
    public void setTradVouchDealList(List<TradVouchDealDto> tradVouchDealList) {
        this.tradVouchDealList = tradVouchDealList;
    }

}
