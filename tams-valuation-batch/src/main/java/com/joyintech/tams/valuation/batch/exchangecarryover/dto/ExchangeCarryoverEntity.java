package com.joyintech.tams.valuation.batch.exchangecarryover.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

import java.util.Date;

/**
 * reader Dto
 *
 * @author zhangwei
 * @createTime 2019-07-08 15:21
 */
public class ExchangeCarryoverEntity extends NeedOperateEntity {

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 发生日期
     */
    private Date happenDate;

    /**
     * 入账日期
     */
    private Date bookDate;

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
     * 参数值 : 汇兑损益结转频率
     */
    private String carryoverCycle;

    /**
     * 账套结束日
     */
    private Date endDate;

    /**
     * 产品到期日
     */
    private Date mdate;

    /**
     * 核算主体代码，产品代码、组合代码、部门号、机构号等
     */
    private String acctSubjectId;

    /**
     * 核算主体类别，理财产品，投资组合、部门等
     */
    private String acctSubjectType;

    /**
     * 账务明细代码
     */
    private String bokDetailId;

    /**
     * 任务优先级
     */
    private Long taskPri;

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public Long getTaskPri() {
        return taskPri;
    }

    public void setTaskPri(Long taskPri) {
        this.taskPri = taskPri;
    }

    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
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

    public Date getBooksetDate() {
        return booksetDate;
    }

    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }

    public String getStandardCcy() {
        return standardCcy;
    }

    public void setStandardCcy(String standardCcy) {
        this.standardCcy = standardCcy;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getCarryoverCycle() {
        return carryoverCycle;
    }

    public void setCarryoverCycle(String carryoverCycle) {
        this.carryoverCycle = carryoverCycle;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getAcctSubjectId() {
        return acctSubjectId;
    }

    public void setAcctSubjectId(String acctSubjectId) {
        this.acctSubjectId = acctSubjectId;
    }

    public String getAcctSubjectType() {
        return acctSubjectType;
    }

    public void setAcctSubjectType(String acctSubjectType) {
        this.acctSubjectType = acctSubjectType;
    }

    public String getBokDetailId() {
        return bokDetailId;
    }

    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId;
    }
}
