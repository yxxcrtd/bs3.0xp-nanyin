/*
 * 文件名：AssetLiabilityStatementQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.threetableslanding.dto;

import java.util.Date;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;

/**
 * 资产负债表
 * 
 * @author zhangwei
 * @version 1.0 2018年9月18日
 * @see ThreeTableLandingDelResult
 * @since 1.0
 */
public class ThreeTableLandingDelResult extends NeedToWriteEntity {

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 利润表数据
     */
    private Date happenDate;
    
    /**
     * 消息类型
     */
    private String messageType;

    
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
     * 获取 messageType
     * @return messageType.
     */
    public String getMessageType() {
        return messageType;
    }

    
    /**
     * 设置 messageType
     * @param messageType messageType
     */
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    
}
