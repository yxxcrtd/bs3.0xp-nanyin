/*
 * 文件名：SendReaderEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.common.entity;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;

public class SendReaderEntity extends InterfNeedOperateEntity {
    
    /**
     * 账套代码集合
     */
    private List<String> booksetList;
    
    /**
     * 执行时间
     */
    private Date exeDate;
    
    
    
    /**
     * 获取 booksetList
     * @return booksetList.
     */
    public List<String> getBooksetList() {
        return booksetList;
    }



    /**
     * 设置 booksetList
     * @param booksetList booksetList
     */
    public void setBooksetList(List<String> booksetList) {
        this.booksetList = booksetList;
    }



    /**
     * 获取 exeDate
     * @return exeDate.
     */
    public Date getExeDate() {
        return exeDate;
    }



    /**
     * 设置 exeDate
     * @param exeDate exeDate
     */
    public void setExeDate(Date exeDate) {
        this.exeDate = exeDate;
    }



    public SendReaderEntity() {
        super();
        this.setRsStatus(DictCodeInterfList.接口发送接收状态_发送.getValue());
    }
}
