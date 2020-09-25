/*
 * 文件名：TradeClearDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.interf.dto;

import java.io.Serializable;

/**
 * 接口参数-交易清算列表
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月22日
 * @see TradeClearDto
 * @since 1.0
 */
public class TradeClearDto implements Serializable {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 6066539768504017283L;

    /**
     * 指令/交易编号
     */
    private String orderOrTradeNo;

    /**
     * 外部交易号
     */
    private String outTradeNo;

    /**
     * 业务类型
     */
    private String busiType;

    /**
     * 类型
     */
    private String type;

    /**
     * 请求流水号
     */
    private String bizSeqNo;

    /**
     * 备注
     */
    private String remark;

    /**
     * 日期
     */
    private String cdate;

    /**
     * 处理结果，true：成功、false：失败
     */
    private boolean result;

    /**
     * 错误信息
     */
    private String errMsg;

    /**
     * 获取 指令/交易编号
     * 
     * @return 指令/交易编号.
     */
    public String getOrderOrTradeNo() {
        return orderOrTradeNo;
    }

    /**
     * 设置 指令/交易编号
     * 
     * @param orderOrTradeNo 指令/交易编号
     */
    public void setOrderOrTradeNo(String orderOrTradeNo) {
        this.orderOrTradeNo = orderOrTradeNo;
    }

    /**
     * 获取 日期
     * 
     * @return 日期.
     */
    public String getCdate() {
        return cdate;
    }

    /**
     * 设置 日期
     * 
     * @param cdate 日期
     */
    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    /**
     * 获取 结果
     * 
     * @return 结果.
     */
    public boolean isResult() {
        return result;
    }

    /**
     * 设置 结果
     * 
     * @param result 结果
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
     * 获取 外部交易号
     * 
     * @return 外部交易号.
     */
    public String getOutTradeNo() {
        return outTradeNo;
    }

    /**
     * 设置 外部交易号
     * 
     * @param outTradeNo 外部交易号
     */
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    /**
     * 获取 业务类型
     * 
     * @return 业务类型.
     */
    public String getBusiType() {
        return busiType;
    }

    /**
     * 设置 业务类型
     * 
     * @param busiType 业务类型
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    /**
     * 获取 类型
     * 
     * @return 类型.
     */
    public String getType() {
        return type;
    }

    /**
     * 设置 类型
     * 
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取 请求流水号
     * 
     * @return 请求流水号.
     */
    public String getBizSeqNo() {
        return bizSeqNo;
    }

    /**
     * 设置 请求流水号
     * 
     * @param bizSeqNo 请求流水号
     */
    public void setBizSeqNo(String bizSeqNo) {
        this.bizSeqNo = bizSeqNo;
    }

    /**
     * 获取 备注
     * 
     * @return 备注.
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * 
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
