/*
 * 文件名：BankCalBookResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：Apr 10, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.bankcalbook.dto;

import java.util.Date;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * 银行账计提账务处理 取得结果DTO
 * 
 * @author zhangwei
 * @version 1.0 Apr 10, 2019
 * @see BankCalBookComResult
 * @since 1.0
 */
public class BankCalBookComResult extends NeedOperateEntity {

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 入账日期
     */
    private Date bookDate;

    /**
     * 发生日期
     */
    private Date happenDate;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 理财账套代码
     */
    private String bokBooksetId;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 收益类型
     */
    private String profitType;

    /**
     * 业务明细代码
     */
    private String busiId;

    /**
     * 币种
     */
    private String ccy;

    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public Date getHappenDate() {
        return happenDate;
    }

    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getBokBooksetId() {
        return bokBooksetId;
    }

    public void setBokBooksetId(String bokBooksetId) {
        this.bokBooksetId = bokBooksetId;
    }

    public Long getBranch() {
        return branch;
    }

    public void setBranch(Long branch) {
        this.branch = branch;
    }

    public String getProfitType() {
        return profitType;
    }

    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    public String getBusiId() {
        return busiId;
    }

    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

}
