/*
 * 文件名：Interf3005InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年2月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i3005.entity;

import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 接口3005-输入
 * 
 * @author zhangrui1
 * @version 1.0 2019年2月13日
 * @see Interf3005InEntity
 * @since 1.0
 */
public class Interf3005InEntity {

    /**
     * 托管网银流水号
     */
    private String cusdepOrderNo;

    /**
     * 系统批次号
     */
    private String batchNo;

    /**
     * 系统流水号
     */
    private String sysSeqNo;

    /**
     * 查询方式</br>
     * <p>
     * 输入1：通过对方系统批次号查询；</br>
     * 输入2：通过对方系统流水号查询；</br>
     * 为空：通过托管网银流水号
     * </p>
     */
    private String queryType;

    /**
     * 转账系统标志
     */
    private String tranChannel;

    /**
     * 取得 托管网银流水号
     * 
     * @return 托管网银流水号
     */
    @EsbName(value = "SEQ_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 52)
    public String getCusdepOrderNo() {
        return cusdepOrderNo;
    }

    /**
     * 取得 系统批次号
     * 
     * @return 系统批次号
     */
    @EsbName(value = "SYSTEM_BATCH_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 32)
    public String getBatchNo() {
        return batchNo;
    }

    /**
     * 取得 系统流水号
     * 
     * @return 系统流水号
     */
    @EsbName(value = "SYS_SEQ_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 52)
    public String getSysSeqNo() {
        return sysSeqNo;
    }

    /**
     * 取得 查询方式
     * 
     * @return 查询方式
     */
    @EsbName(value = "QUERY_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getQueryType() {
        return queryType;
    }

    /**
     * 取得 转账系统标志
     * 
     * @return 转账系统标志
     */
    @EsbName(value = "TRAN_CHANNEL")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getTranChannel() {
        return tranChannel;
    }

    /**
     * 设置 托管网银流水号
     * 
     * @param cusdepOrderNo 托管网银流水号
     */
    public void setCusdepOrderNo(String cusdepOrderNo) {
        this.cusdepOrderNo = cusdepOrderNo;
    }

    /**
     * 设置 系统批次号
     * 
     * @param batchNo 系统批次号
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    /**
     * 设置 系统流水号
     * 
     * @param sysSeqNo 系统流水号
     */
    public void setSysSeqNo(String sysSeqNo) {
        this.sysSeqNo = sysSeqNo;
    }

    /**
     * 设置 查询方式
     * 
     * @param queryType 查询方式</br>
     *            <p>
     *            输入1：通过对方系统批次号查询；</br>
     *            输入2：通过对方系统流水号查询；</br>
     *            为空：通过托管网银流水号
     *            </p>
     */
    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    /**
     * 设置 转账系统标志
     * 
     * @param tranChannel 转账系统标志
     */
    public void setTranChannel(String tranChannel) {
        this.tranChannel = tranChannel;
    }
}
