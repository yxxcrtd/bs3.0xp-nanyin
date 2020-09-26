/*
 * 文件名：ValVoucherQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.manualvoucher.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.NotEmpty;

/**
 * 手工凭证新建dto
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValManualVoucher003AddValidatorDto
 * @since 2.0
 */
public class ValManualVoucher003AddValidatorDto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 凭证流水号
     */
    private String seqno;
    
    /**
     * 入账日期
     */
    @NotEmpty(fieldName = "ValManualVoucher.bookDate")
    private Date bookDate;

    /**
     * 账套代码
     */
    @NotEmpty(fieldName = "ValManualVoucher.booksetId")
    private String booksetId;

    /**
     * 分录类型
     */
    @NotEmpty(fieldName = "ValManualVoucher.recordType")
    private String recordType;
    
    /**
     * 业务类型
     */
    @NotEmpty(fieldName = "ValManualVoucher.finprodType")
    private String finprodType;

    /**
     * 资产代码
     */
    @NotEmpty(fieldName = "ValManualVoucher.busiId")
    private String busiId;

    /**
     * 冲销标识
     */
    @NotEmpty(fieldName = "ValManualVoucher.offsetFlag")
    private String offsetFlag;

    /**
     * 自定义现金流编号
     */
    private String cashId;
    
    /**
     * 摘要（凭证说明）
     */
    private String bookRemark;

    /**
     * 获取 凭证流水号
     * 
     * @return 凭证流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 凭证流水号
     * 
     * @param seqno 凭证流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }
    
    /**
     * 取得 入账日期
     * 
     * @return 入账日期
     */
    public Date getBookDate() {
        return bookDate;
    }

    /**
     * 设置 入账日期
     * 
     * @param bookDate 入账日期
     */
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
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
     * 获取 凭证说明
     * 
     * @return 凭证说明.
     */
    public String getBookRemark() {
        return bookRemark;
    }

    /**
     * 设置 凭证说明
     * 
     * @param vouchRemark 凭证说明
     */
    public void setBookRemark(String bookRemark) {
        this.bookRemark = bookRemark;
    }

    /**
     * 获取 冲正标识
     * 
     * @return 冲正标识.
     */
    public String getOffsetFlag() {
        return offsetFlag;
    }

    /**
     * 设置 冲正标识
     * 
     * @param offsetFlag 冲正标识
     */
    public void setOffsetFlag(String offsetFlag) {
        this.offsetFlag = offsetFlag;
    }

    
    /**
     * 获取 recordType
     * @return recordType.
     */
    public String getRecordType() {
        return recordType;
    }

    
    /**
     * 设置 recordType
     * @param recordType recordType
     */
    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    
    /**
     * 获取 finprodType
     * @return finprodType.
     */
    public String getFinprodType() {
        return finprodType;
    }

    
    /**
     * 设置 finprodType
     * @param finprodType finprodType
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
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
     * 获取 cashId
     * @return cashId.
     */
    public String getCashId() {
        return cashId;
    }

    
    /**
     * 设置 cashId
     * @param cashId cashId
     */
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }
    
}
