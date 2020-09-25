/*
 * 文件名：CusdepOrderDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年2月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.interf.dto;

import com.joyintech.tams.common.base.entity.StlDvpOrders;

/**
 * 托管网银指令接口参数类型
 * 
 * @author zhangrui1
 * @version 1.0 2019年2月12日
 * @see CusdepOrderDto
 * @since 1.0
 */
public class CusdepOrderDto {

    /**
     * 接口类型</br>
     * 1：发送指令</br>
     * 2：查询状态</br>
     * 其他：交易单确认</br>
     */
    private String interfType;

    /**
     * 编号/流水号
     */
    private String orderNo;

    /**
     * 是否DVP划款
     */
    private boolean isDvp;

    /**
     * 确认标志
     */
    private String confirmFlag;

    /**
     * 确认方向
     */
    private String confirmDirection;

    /**
     * 指令确认日期
     */
    private String confirmDate;

    /**
     * 账号
     */
    private String acctNo;

    /**
     * 附件名称
     */
    private String affixName;

    /**
     * 确认拒绝原因
     */
    private String reason;

    /**
     * 处理结果
     */
    private boolean result;

    /**
     * 错误信息
     */
    private String errMsg;

    private boolean core;

    private String TransferType;

    private StlDvpOrders StlDvpOrders;

    public boolean getCore() {
        return core;
    }

    public void setCore(boolean core) {
        this.core = core;
    }

    public String getTransferType() {
        return TransferType;
    }

    public void setTransferType(String transferType) {
        TransferType = transferType;
    }

    public com.joyintech.tams.common.base.entity.StlDvpOrders getStlDvpOrders() {
        return StlDvpOrders;
    }

    public void setStlDvpOrders(com.joyintech.tams.common.base.entity.StlDvpOrders stlDvpOrders) {
        StlDvpOrders = stlDvpOrders;
    }

    /**
     * 获取 接口类型
     * 
     * @return 接口类型.
     */
    public String getInterfType() {
        return interfType;
    }

    /**
     * 设置 接口类型
     * 
     * @param interfType 接口类型
     *            <p>
     *            1：发送指令</br>
     *            2：查询状态</br>
     *            其他：交易单确认</br>
     *            </p>
     */
    public void setInterfType(String interfType) {
        this.interfType = interfType;
    }

    /**
     * 获取 编号/流水号
     * 
     * @return 编号/流水号.
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置 编号/流水号
     * 
     * @param orderNo 编号/流水号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取 确认标志
     * 
     * @return 确认标志.
     */
    public String getConfirmFlag() {
        return confirmFlag;
    }

    /**
     * 设置 确认标志
     * 
     * @param confirmFlag 确认标志
     */
    public void setConfirmFlag(String confirmFlag) {
        this.confirmFlag = confirmFlag;
    }

    /**
     * 获取 确认方向
     * 
     * @return 确认方向.
     */
    public String getConfirmDirection() {
        return confirmDirection;
    }

    /**
     * 设置 确认方向
     * 
     * @param confirmDirection 确认方向
     */
    public void setConfirmDirection(String confirmDirection) {
        this.confirmDirection = confirmDirection;
    }

    /**
     * 获取 指令确认日期
     * 
     * @return 指令确认日期.
     */
    public String getConfirmDate() {
        return confirmDate;
    }

    /**
     * 设置 指令确认日期
     * 
     * @param confirmDate 指令确认日期
     */
    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
    }

    /**
     * 获取 账号
     * 
     * @return 账号.
     */
    public String getAcctNo() {
        return acctNo;
    }

    /**
     * 设置 账号
     * 
     * @param acctNo 账号
     */
    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    /**
     * 获取 附件名称
     * 
     * @return 附件名称.
     */
    public String getAffixName() {
        return affixName;
    }

    /**
     * 设置 附件名称
     * 
     * @param affixName 附件名称
     */
    public void setAffixName(String affixName) {
        this.affixName = affixName;
    }

    /**
     * 获取 确认拒绝原因
     * 
     * @return 确认拒绝原因.
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置 确认拒绝原因
     * 
     * @param reason 确认拒绝原因
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * 获取 处理结果
     * 
     * @return 处理结果.
     */
    public boolean isResult() {
        return result;
    }

    /**
     * 设置 处理结果
     * 
     * @param result 处理结果
     */
    public void setResult(boolean result) {
        this.result = result;
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
     * 获取 是否DVP划款
     * 
     * @return 是否DVP划款.
     */
    public boolean isDvp() {
        return isDvp;
    }

    /**
     * 设置 是否DVP划款
     * 
     * @param isDvp 是否DVP划款
     */
    public void setDvp(boolean isDvp) {
        this.isDvp = isDvp;
    }
}
