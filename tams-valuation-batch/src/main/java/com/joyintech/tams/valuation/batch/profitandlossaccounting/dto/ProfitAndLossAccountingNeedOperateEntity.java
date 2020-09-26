/*
 * 文件名：ProfitAndLossAccountingNeedOperateEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年5月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.profitandlossaccounting.dto;

import java.io.Serializable;
import java.util.Date;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

public class ProfitAndLossAccountingNeedOperateEntity extends NeedOperateEntity implements Serializable {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -7488770518127971415L;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 入账日
     */
    private Date bookDate;

    /**
     * 发生日期
     */
    private Date happenDate;

    /**
     * 账套日期
     */
    private Date booksetDate;

    /**
     * 本位币
     */
    private String standardCcy;

    /**
     * 任务代码
     */
    private String taskId;

    /**
     * 任务优先级
     */
    private Long taskPri;

    /**
     * 结转频率
     */
    private String carryoverCycle;

    /**
     * 账套结束日
     */
    private String bksEndDate;

    /**
     * 产品到期日
     */
    private String prdMdate;
    /**
     * 业务明细代码
     */
    private String busiId;
    /**
     * 业务明细类型
     */
    private String busiDetailType;
    /**
     * 账务明细代码
     */
    private String bokDetailId;
    
    /**
     * 特殊核算模型
     */
    private String bokSpecMode;
    

    /**
     * 获取 serialversionuid
     * 
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * 获取 booksetId
     * 
     * @return booksetId.
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 获取 bookDate
     * 
     * @return bookDate.
     */
    public Date getBookDate() {
        return bookDate;
    }

    /**
     * 获取 happenDate
     * 
     * @return happenDate.
     */
    public Date getHappenDate() {
        return happenDate;
    }

    /**
     * 获取 booksetDate
     * 
     * @return booksetDate.
     */
    public Date getBooksetDate() {
        return booksetDate;
    }

    /**
     * 获取 standardCcy
     * 
     * @return standardCcy.
     */
    public String getStandardCcy() {
        return standardCcy;
    }

    /**
     * 获取 taskId
     * 
     * @return taskId.
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 获取 carryoverCycle
     * 
     * @return carryoverCycle.
     */
    public String getCarryoverCycle() {
        return carryoverCycle;
    }

    /**
     * 获取 bksEndDate
     * 
     * @return bksEndDate.
     */
    public String getBksEndDate() {
        return bksEndDate;
    }

    /**
     * 获取 prdMdate
     * 
     * @return prdMdate.
     */
    public String getPrdMdate() {
        return prdMdate;
    }

    /**
     * 设置 booksetId
     * 
     * @param booksetId booksetId
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    /**
     * 设置 bookDate
     * 
     * @param bookDate bookDate
     */
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    /**
     * 设置 happenDate
     * 
     * @param happenDate happenDate
     */
    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    /**
     * 设置 booksetDate
     * 
     * @param booksetDate booksetDate
     */
    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }

    /**
     * 设置 standardCcy
     * 
     * @param standardCcy standardCcy
     */
    public void setStandardCcy(String standardCcy) {
        this.standardCcy = standardCcy;
    }

    /**
     * 设置 taskId
     * 
     * @param taskId taskId
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * 设置 carryoverCycle
     * 
     * @param carryoverCycle carryoverCycle
     */
    public void setCarryoverCycle(String carryoverCycle) {
        this.carryoverCycle = carryoverCycle;
    }

    /**
     * 设置 bksEndDate
     * 
     * @param bksEndDate bksEndDate
     */
    public void setBksEndDate(String bksEndDate) {
        this.bksEndDate = bksEndDate;
    }

    /**
     * 设置 prdMdate
     * 
     * @param prdMdate prdMdate
     */
    public void setPrdMdate(String prdMdate) {
        this.prdMdate = prdMdate;
    }

    
    /**
     * 获取 taskPri
     * @return taskPri.
     */
    public Long getTaskPri() {
        return taskPri;
    }

    
    /**
     * 设置 taskPri
     * @param taskPri taskPri
     */
    public void setTaskPri(Long taskPri) {
        this.taskPri = taskPri;
    }

    
    /**
     * 获取 busiId
     * @return busiId.
     */
    public String getBusiId() {
        return busiId;
    }

    
    /**
     * 设置 busiId
     * @param busiId busiId
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    
    /**
     * 获取 busiDetailType
     * @return busiDetailType.
     */
    public String getBusiDetailType() {
        return busiDetailType;
    }

    
    /**
     * 设置 busiDetailType
     * @param busiDetailType busiDetailType
     */
    public void setBusiDetailType(String busiDetailType) {
        this.busiDetailType = busiDetailType;
    }

    
    /**
     * 获取 bokDetailId
     * @return bokDetailId.
     */
    public String getBokDetailId() {
        return bokDetailId;
    }

    
    /**
     * 设置 bokDetailId
     * @param bokDetailId bokDetailId
     */
    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId;
    }

    
    /**
     * 获取 bokSpecMode
     * @return bokSpecMode.
     */
    public String getBokSpecMode() {
        return bokSpecMode;
    }

    
    /**
     * 设置 bokSpecMode
     * @param bokSpecMode bokSpecMode
     */
    public void setBokSpecMode(String bokSpecMode) {
        this.bokSpecMode = bokSpecMode;
    }
    

}
