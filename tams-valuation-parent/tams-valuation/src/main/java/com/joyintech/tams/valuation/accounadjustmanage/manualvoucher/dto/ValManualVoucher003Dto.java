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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.valuation.accounadjustmanage.manualvoucher.validator.ValManualVoucher003Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 手工凭证新建dto
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValManualVoucher003Dto
 * @since 2.0
 */
@PageId("ValManualVoucher003")
@ValidLogic(targetClass = ValManualVoucher003Validator.class)
public class ValManualVoucher003Dto extends BaseEntity implements ProcessEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 凭证流水号(即TRD_VOUCH_DEAL（手工凭证交易表）中的TRADE_ID（交易编号）)
     */
    @PrimaryKey(value = Field.PK_1)
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
     * 账套名称
     */
    private String booksetName;

    /**
     * 凭证编号
     */
    private String bookId;

    /**
     * 分录类型
     */
    @NotEmpty(fieldName = "ValManualVoucher.recordType")
    private String recordType;
    
    /**
     * 记账顺序
     */
    @NotEmpty(fieldName = "ValManualVoucher.accountOrder")
    private String accountOrder;
    /**
     * 资产代码
     */
    @NotEmpty(fieldName = "ValManualVoucher.busiId")
    private String busiId;

    /**
     * 资产名称
     */
    private String busiName;
    
    /**
     * 业务类型：金融产品类型
     */
    @NotEmpty(fieldName = "ValManualVoucher.finprodType")
    private String finprodType;

    /**
     * 自定义现金流编号
     */
    private String cashId;

    /**
     * 冲销标识
     */
    @NotEmpty(fieldName = "ValManualVoucher.offsetFlag")
    private String offsetFlag;

    /**
     * 摘要（凭证说明）
     */
    private String bookRemark;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 凭证新建时的分录信息
     */
    private List<ValManualVoucher003AddDto> listAddDto = new ArrayList<ValManualVoucher003AddDto>();

    /**
     * 业务明细类型
     */
    private String busiDetailType;

    /**
     * 业务明细代码
     */
    private String busiDetailId;

    /**
     * 分支序号
     */
    private Long branch;

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
     * 取得 账套名称
     * 
     * @return 账套名称
     */
    public String getBooksetName() {
        return booksetName;
    }

    /**
     * 设置 账套名称
     * 
     * @param booksetName 账套名称
     */
    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName;
    }

    /**
     * 取得 凭证编号
     * 
     * @return 凭证编号
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * 设置 凭证编号
     * 
     * @param busVouchnum 凭证编号
     */
    public void setBookId(String bookId) {
        this.bookId = bookId;
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
     * 获取 凭证新建时的列表信息
     * 
     * @return 凭证新建时的列表信息.
     */
    public List<ValManualVoucher003AddDto> getListAddDto() {
        return listAddDto;
    }

    /**
     * 设置 凭证新建时的列表信息
     * 
     * @param listAddDto 凭证新建时的列表信息
     */
    public void setListAddDto(List<ValManualVoucher003AddDto> listAddDto) {
        this.listAddDto = listAddDto;
    }

    /**
     * 获取 recordType
     * 
     * @return recordType.
     */
    public String getRecordType() {
        return recordType;
    }

    /**
     * 设置 recordType
     * 
     * @param recordType recordType
     */
    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    /**
     * 获取 busiId
     * 
     * @return busiId.
     */
    public String getBusiId() {
        return busiId;
    }

    /**
     * 设置 busiId
     * 
     * @param busiId busiId
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    /**
     * 获取 finprodType
     * 
     * @return finprodType.
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 finprodType
     * 
     * @param finprodType finprodType
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    /**
     * 获取 cashId
     * 
     * @return cashId.
     */
    public String getCashId() {
        return cashId;
    }

    /**
     * 设置 cashId
     * 
     * @param cashId cashId
     */
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }

    /**
     * 获取 invAim
     * 
     * @return invAim.
     */
    public String getInvAim() {
        return invAim;
    }

    /**
     * 设置 invAim
     * 
     * @param invAim invAim
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    /**
     * 获取 业务明细类型
     * 
     * @return 业务明细类型.
     */
    public String getBusiDetailType() {
        return busiDetailType;
    }

    /**
     * 设置 业务明细类型
     * 
     * @param busiDetailType 业务明细类型
     */
    public void setBusiDetailType(String busiDetailType) {
        this.busiDetailType = busiDetailType;
    }

    /**
     * 获取 业务明细代码
     * 
     * @return 业务明细代码
     */
    public String getBusiDetailId() {
        return busiDetailId;
    }

    /**
     * 设置 业务明细代码
     * 
     * @param busiDetailId 业务明细代码
     */
    public void setBusiDetailId(String busiDetailId) {
        this.busiDetailId = busiDetailId;
    }

    /**
     * 获取 分支序号
     * 
     * @return 分支序号.
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * 
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }
    
    /**
     * 获取 busiName
     * 
     * @return busiName.
     */
    public String getBusiName() {
        return busiName;
    }

    /**
     * 设置 busiName
     * 
     * @param busiName
     */
    public void setBusiName(String busiName) {
        this.busiName = busiName;
    }
    /**
     * 获取 accountOrder
     * 
     * @return accountOrder.
     */
    public String getAccountOrder() {
        return accountOrder;
    }

    /**
     * 设置 accountOrder
     * 
     * @param accountOrder accountOrder
     */
    public void setAccountOrder(String accountOrder) {
        this.accountOrder = accountOrder;
    }

}
