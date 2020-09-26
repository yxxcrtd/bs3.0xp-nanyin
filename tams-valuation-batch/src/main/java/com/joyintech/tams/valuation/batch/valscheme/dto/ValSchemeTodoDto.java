/*
 * 文件名：ValBatchTaskTodoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V1.0
 */

package com.joyintech.tams.valuation.batch.valscheme.dto;

import java.io.Serializable;
import java.util.Date;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * 待处理账套Dto
 * 
 * @author weipeng
 * @version 1.0 2018年5月28日
 * @see ValSchemeTodoDto
 * @since 1.0
 */
public class ValSchemeTodoDto extends NeedOperateEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 账套日期
     */
    private Date booksetDate;

    /**
     * 入账日
     */
    private Date bookDate;

    /**
     * 发生日
     */
    private Date happenDate;

    /**
     * 本位币
     */
    private String booksetName;

    private String acctMode;

    private String standardCcy;

    private String valDecDigits;

    private String valDecType;

    private String remark;

    /**
     * 万份收益小数位
     */
    private String pyeildDecDigits;

    /**
     * 产品名称
     */
    private String finprodName;

    /**
     * 账套全称
     */
    private String booksetFullName;

    /**
     * 特殊核算模型
     */
    private String bokSpecMode;

    /**
     * 核算主体代码
     */
    private String acctSubjectId;
    
    /**
     * 分层代码
     */
    private String layeringId;

    /**
     * 获取 finprodName
     * 
     * @return finprodName.
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 finprodName
     * 
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    /**
     * 取得 账套代码
     * 
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * 
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    /**
     * 取得 账套日期
     * 
     * @return 账套日期
     */
    public Date getBooksetDate() {
        return booksetDate;
    }

    /**
     * 设置 账套日期
     * 
     * @param booksetDate 账套日期
     */
    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }

    /**
     * 取得 入账日
     * 
     * @return 入账日
     */
    public Date getBookDate() {
        return bookDate;
    }

    /**
     * 设置 入账日
     * 
     * @param bookDate 入账日
     */
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    /**
     * 取得 发生日
     * 
     * @return 发生日
     */
    public Date getHappenDate() {
        return happenDate;
    }

    /**
     * 设置 发生日
     * 
     * @param happenDate 发生日
     */
    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    /**
     * 取得 本位币
     * 
     * @return 本位币
     */
    public String getStandardCcy() {
        return standardCcy;
    }

    /**
     * 设置 本位币
     * 
     * @param standardCcy 本位币
     */
    public void setStandardCcy(String standardCcy) {
        this.standardCcy = standardCcy;
    }

    public String getBooksetName() {
        return booksetName;
    }

    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName;
    }

    public String getAcctMode() {
        return acctMode;
    }

    public void setAcctMode(String acctMode) {
        this.acctMode = acctMode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getValDecDigits() {
        return valDecDigits;
    }

    public void setValDecDigits(String valDecDigits) {
        this.valDecDigits = valDecDigits;
    }

    public String getValDecType() {
        return valDecType;
    }

    public void setValDecType(String valDecType) {
        this.valDecType = valDecType;
    }

    /**
     * 获取 pyeildDecDigits
     * 
     * @return pyeildDecDigits.
     */
    public String getPyeildDecDigits() {
        return pyeildDecDigits;
    }

    /**
     * 设置 pyeildDecDigits
     * 
     * @param pyeildDecDigits pyeildDecDigits
     */
    public void setPyeildDecDigits(String pyeildDecDigits) {
        this.pyeildDecDigits = pyeildDecDigits;
    }

    /**
     * 获取 账套全称
     * 
     * @return 账套全称
     */
    public String getBooksetFullName() {
        return booksetFullName;
    }

    /**
     * 设置 账套全称
     * 
     * @param booksetFullName 账套全称
     */
    public void setBooksetFullName(String booksetFullName) {
        this.booksetFullName = booksetFullName;
    }

    /**
     * 获取 特殊核算模型
     * 
     * @return 特殊核算模型
     */
    public String getBokSpecMode() {
        return bokSpecMode;
    }

    /**
     * 设置 特殊核算模型
     * 
     * @param bokSpecMode 特殊核算模型
     */
    public void setBokSpecMode(String bokSpecMode) {
        this.bokSpecMode = bokSpecMode;
    }

    /**
     * 获取 核算主体代码
     * 
     * @return 核算主体代码.
     */
    public String getAcctSubjectId() {
        return acctSubjectId;
    }

    /**
     * 设置 核算主体代码
     * 
     * @param acctSubjectId 核算主体代码
     */
    public void setAcctSubjectId(String acctSubjectId) {
        this.acctSubjectId = acctSubjectId;
    }

    
    /**
     * 获取 分层代码
     * @return 分层代码
     */
    public String getLayeringId() {
        return layeringId;
    }

    
    /**
     * 设置 分层代码
     * @param layeringId 分层代码
     */
    public void setLayeringId(String layeringId) {
        this.layeringId = layeringId;
    }
    
    

}
