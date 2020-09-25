/*
 * 文件名：ValVoucherQuery002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.accounadjustmanage.voucherquery.dto;

import com.google.common.collect.Lists;
import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * 记账凭证详情打印dto
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValVoucherQueryPrintDto
 * @since 2.0
 */
public class ValVoucherQueryPrintDto extends BaseEntity {

    /**
     * 凭证明细列表
     */
    private List<ValVoucherQuery002Dto> list = Lists.newArrayList();

    /**
     * 记账日期
     */
    private String bookDate;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 账套名称
     */
    private String booksetName;

    /**
     * 凭证编号
     */
    private String busVouchnum;

    /**
     * 业务类别
     */
    private String tableId;

    /**
     * 汇总：借方金额（本币）
     */
    private BigDecimal dBAmtSum;

    /**
     * 汇总：贷方金额（本币）
     */
    private BigDecimal cBAmtSum;

    /**
     * 汇总：借方金额（原币）
     */
    private BigDecimal dAmtSum;

    /**
     * 汇总：贷方金额（原币）
     */
    private BigDecimal cAmtSum;

    /**
     * 汇总：数量
     */
    private BigDecimal numSum;

    /**
     * 币种
     */
    private String ccy;

    private String tradeId;

    private String createUser;

    private String updateUser;


    public List<ValVoucherQuery002Dto> getList() {
        return list;
    }

    public void setList(List<ValVoucherQuery002Dto> list) {
        this.list = list;
    }

    public String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public String getBusVouchnum() {
        return busVouchnum;
    }

    public void setBusVouchnum(String busVouchnum) {
        this.busVouchnum = busVouchnum;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public BigDecimal getdBAmtSum() {
        return dBAmtSum;
    }

    public void setdBAmtSum(BigDecimal dBAmtSum) {
        this.dBAmtSum = dBAmtSum;
    }

    public BigDecimal getcBAmtSum() {
        return cBAmtSum;
    }

    public void setcBAmtSum(BigDecimal cBAmtSum) {
        this.cBAmtSum = cBAmtSum;
    }

    public BigDecimal getdAmtSum() {
        return dAmtSum;
    }

    public void setdAmtSum(BigDecimal dAmtSum) {
        this.dAmtSum = dAmtSum;
    }

    public BigDecimal getcAmtSum() {
        return cAmtSum;
    }

    public void setcAmtSum(BigDecimal cAmtSum) {
        this.cAmtSum = cAmtSum;
    }

    public BigDecimal getNumSum() {
        return numSum;
    }

    public void setNumSum(BigDecimal numSum) {
        this.numSum = numSum;
    }

    public String getBooksetName() {
        return booksetName;
    }

    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    @Override
    public String getCreateUser() {
        return createUser;
    }

    @Override
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public String getUpdateUser() {
        return updateUser;
    }

    @Override
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
