/*
 * 文件名：FinProdFoundDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.interf.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * 接口参数-产品列表
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月28日
 * @see FinProdDto
 * @since 1.0
 */
public class FinProdDto implements Serializable {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 4256158584744483961L;

    /**
     * 金融产品代码
     */
    private String finProdId;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 销售部门
     */
    private String saleDept;

    /**
     * 处理模式
     */
    private String type;

    /**
     * 交易日/成立日/到期日/生效日：查询日期（yyyyMMdd）
     */
    private String cdate;

    /**
     * 销售开始日
     */
    private String startdate;

    /**
     * 销售截止日
     */
    private String enddate;

    /**
     * 接口ID
     */
    private String interfId;

    /**
     * 处理结果
     */
    private boolean isHandleOK;

    /**
     * 错误信息
     */
    private String errMsg;

    /**
     * @param finProdId 金融产品代码
     * @param prodId 产品代码（市场代码）
     * @param saleDept 销售部门
     * @param type 处理模式
     */
    public FinProdDto(@NotNull String finProdId, String prodId, @NotNull String saleDept,
                      @NotNull String type) {
        super();
        this.finProdId = finProdId;
        this.prodId = prodId;
        this.saleDept = saleDept;
        this.type = type;
    }

    public FinProdDto() {

    }

    /**
     * 获取 金融产品代码
     * 
     * @return 金融产品代码.
     */
    public String getFinProdId() {
        return finProdId;
    }

    /**
     * 设置 金融产品代码
     * 
     * @param finProdId 金融产品代码
     */
    public void setFinProdId(String finProdId) {
        this.finProdId = finProdId;
    }

    /**
     * 获取 产品代码
     * 
     * @return 产品代码.
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * 
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    /**
     * 获取 销售部门
     * 
     * @return 销售部门.
     */
    public String getSaleDept() {
        return saleDept;
    }

    /**
     * 设置 销售部门
     * 
     * @param saleDept 销售部门
     */
    public void setSaleDept(String saleDept) {
        this.saleDept = saleDept;
    }

    /**
     * 获取 处理模式
     * 
     * @return 处理模式.
     */
    public String getType() {
        return type;
    }

    /**
     * 设置 处理模式
     * 
     * @param type 处理模式
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取 处理结果
     * 
     * @return 处理结果.
     */
    public boolean isHandleOK() {
        return isHandleOK;
    }

    /**
     * 设置 处理结果
     * 
     * @param isHandleOK 处理结果
     */
    public void setHandleOK(boolean isHandleOK) {
        this.isHandleOK = isHandleOK;
    }

    /**
     * 获取 错误信息
     * 
     * @return 错误信息.
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * 设置 错误信息
     * 
     * @param errMsg 错误信息
     */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    /**
     * 获取 交易日/成立日/到期日/生效日
     * 
     * @return 交易日/成立日/到期日/生效日.
     */
    public String getCdate() {
        return cdate;
    }

    /**
     * 设置 交易日/成立日/到期日/生效日
     * 
     * @param cdate 交易日/成立日/到期日/生效日
     */
    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    /**
     * 获取 销售开始日
     * 
     * @return 销售开始日.
     */
    public String getStartdate() {
        return startdate;
    }

    /**
     * 设置 销售开始日
     * 
     * @param startdate 销售开始日
     */
    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    /**
     * 获取 销售截止日
     * 
     * @return 销售截止日.
     */
    public String getEnddate() {
        return enddate;
    }

    /**
     * 设置 销售截止日
     * 
     * @param enddate 销售截止日
     */
    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    /**
     * 获取 接口ID
     * 
     * @return 接口ID.
     */
    public String getInterfId() {
        return interfId;
    }

    /**
     * 设置 接口ID
     * 
     * @param interfId 接口ID
     */
    public void setInterfId(String interfId) {
        this.interfId = interfId;
    }
}
