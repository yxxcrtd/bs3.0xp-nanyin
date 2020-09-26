/*
 * 文件名：LayInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：May 25, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.moneytaautocal.dto;

/**
 * 核算分层信息
 * 
 * @author zhangwei
 * @version 1.0 May 25, 2019
 * @see LayInfoDto
 * @since 1.0
 */
public class BokAcctLayInfoDto {

    /**
     * 分层代码
     */
    private String layeringId;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 账务明细代码
     */
    private String bokDetailId;

    /**
     * 业务明细代码
     */
    private String busiId;

    /**
     * 业务明细类型
     */
    private String busiDetailType;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 本位币
     */
    private String standardCcy;

    public String getLayeringId() {
        return layeringId;
    }

    public void setLayeringId(String layeringId) {
        this.layeringId = layeringId;
    }

    public String getBokDetailId() {
        return bokDetailId;
    }

    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId;
    }

    public String getBusiId() {
        return busiId;
    }

    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    public String getBusiDetailType() {
        return busiDetailType;
    }

    public void setBusiDetailType(String busiDetailType) {
        this.busiDetailType = busiDetailType;
    }

    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public String getStandardCcy() {
        return standardCcy;
    }

    public void setStandardCcy(String standardCcy) {
        this.standardCcy = standardCcy;
    }

    public Long getBranch() {
        return branch;
    }

    public void setBranch(Long branch) {
        this.branch = branch;
    }

}
