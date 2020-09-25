package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class BokBatchTaskTodo extends BaseEntity {
	/**
     * 批处理任务组执行ID
     */
    private String seqNo;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 发生日期
     */
    private Date happenDate;

    /**
     * 任务代码
     */
    private String taskId;

    /**
     * 账套日期
     */
    private Date booksetDate;

    /**
     * 账务明细代码
     */
    private String bokDetailId;

    /**
     * 入账日期
     */
    private Date bookDate;

    /**
     * 是否倒起息
     */
    private String isBackValue;

    /**
     * 是否子账套
     */
    private String isSubBookset;
  

	/**
     * 取得 批处理任务组执行ID
     * @return 批处理任务组执行ID
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 批处理任务组执行ID
     * @param seqNo 批处理任务组执行ID
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 账套代码
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
    }

    /**
     * 取得 发生日期
     * @return 发生日期
     */
    public Date getHappenDate() {
        return happenDate;
    }

    /**
     * 设置 发生日期
     * @param happenDate 发生日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    /**
     * 取得 任务代码
     * @return 任务代码
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 设置 任务代码
     * @param taskId 任务代码
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    /**
     * 取得 账套日期
     * @return 账套日期
     */
    public Date getBooksetDate() {
        return booksetDate;
    }

    /**
     * 设置 账套日期
     * @param booksetDate 账套日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }

    /**
     * 取得 账务明细代码
     * @return 账务明细代码
     */
    public String getBokDetailId() {
        return bokDetailId;
    }

    /**
     * 设置 账务明细代码
     * @param bokDetailId 账务明细代码
     */
    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId == null ? null : bokDetailId.trim();
    }

    /**
     * 取得 入账日期
     * @return 入账日期
     */
    public Date getBookDate() {
        return bookDate;
    }

    /**
     * 设置 入账日期
     * @param bookDate 入账日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    /**
     * 取得 是否倒起息
     * @return 是否倒起息
     */
    public String getIsBackValue() {
        return isBackValue;
    }

    /**
     * 设置 是否倒起息
     * @param isBackValue 是否倒起息
     */
    public void setIsBackValue(String isBackValue) {
        this.isBackValue = isBackValue == null ? null : isBackValue.trim();
    }
    
    public String getIsSubBookset() {
  		return isSubBookset;
  	}

  	public void setIsSubBookset(String isSubBookset) {
  		this.isSubBookset = isSubBookset;
  	}
}