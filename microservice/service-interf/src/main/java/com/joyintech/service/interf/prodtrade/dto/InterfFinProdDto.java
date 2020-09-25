/*
 * 文件名：FinProdFoundDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.prodtrade.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * 产品参数-产品列表
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月28日
 * @see InterfFinProdDto
 * @since 1.0
 */
public class InterfFinProdDto implements Serializable {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 5878046899153001554L;

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
     * 查询日期/生效日：yyyyMMdd
     */
    private String cdate;

    /**
     * 接口ID
     */
    private String interfId;

    /**
     * 销售开始日
     */
    private String startdate;

    /**
     * 销售截止日
     */
    private String enddate;

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
     */
    public InterfFinProdDto(@NotNull String finProdId) {
        super();
        this.finProdId = finProdId;
    }

    public InterfFinProdDto() {
    }

    /**
     * 获取 finProdId
     * 
     * @return finProdId.
     */
    public String getFinProdId() {
        return finProdId;
    }

    /**
     * 设置 finProdId
     * 
     * @param finProdId finProdId
     */
    public void setFinProdId(String finProdId) {
        this.finProdId = finProdId;
    }

    /**
     * 获取 prodId
     * 
     * @return prodId.
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 prodId
     * 
     * @param prodId prodId
     */
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    /**
     * 获取 saleDept
     * 
     * @return saleDept.
     */
    public String getSaleDept() {
        return saleDept;
    }

    /**
     * 设置 saleDept
     * 
     * @param saleDept saleDept
     */
    public void setSaleDept(String saleDept) {
        this.saleDept = saleDept;
    }

    /**
     * 获取 type
     * 
     * @return type.
     */
    public String getType() {
        return type;
    }

    /**
     * 设置 type
     * 
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取 isHandleOK
     * 
     * @return isHandleOK.
     */
    public boolean isHandleOK() {
        return isHandleOK;
    }

    /**
     * 设置 isHandleOK
     * 
     * @param isHandleOK isHandleOK
     */
    public void setHandleOK(boolean isHandleOK) {
        this.isHandleOK = isHandleOK;
    }

    /**
     * 获取 errMsg
     * 
     * @return errMsg.
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * 设置 errMsg
     * 
     * @param errMsg errMsg
     */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    /**
     * 获取 生效日
     * 
     * @return 生效日.
     */
    public String getCdate() {
        return cdate;
    }

    /**
     * 设置 生效日
     * 
     * @param cdate 生效日
     */
    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    /**
     * 获取 开始销售日
     * 
     * @return 开始销售日.
     */
    public String getStartdate() {
        return startdate;
    }

    /**
     * 设置 开始销售日
     * 
     * @param startdate 开始销售日
     */
    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    /**
     * 获取 截止销售日
     * 
     * @return 截止销售日.
     */
    public String getEnddate() {
        return enddate;
    }

    /**
     * 设置 截止销售日
     * 
     * @param enddate 截止销售日
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
