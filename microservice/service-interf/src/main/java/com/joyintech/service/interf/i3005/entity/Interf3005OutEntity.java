/*
 * 文件名：Interf3005OutEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年2月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i3005.entity;

import java.util.List;

import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 接口3005-输出
 * 
 * @author zhangrui1
 * @version 1.0 2019年2月13日
 * @see Interf3005OutEntity
 * @since 1.0
 */
public class Interf3005OutEntity {

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 原批次号
     */
    private String oldSeqNo;

    /**
     * 批次编号
     */
    private String seqId;

    /**
     * 交易信息数组
     */
    private List<Interf3005OutArrEntity> tranItemList;

    /**
     * 设置 错误码
     * 
     * @param errorCode 错误码
     */
    @EsbName(value = "ERROR_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 设置 错误信息
     * 
     * @param errorMsg 错误信息
     */
    @EsbName(value = "ERROR_MSG")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * 设置 原批次号
     * 
     * @param oldSeqNo 原批次号
     */
    @EsbName(value = "OLD_SEQ_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setOldSeqNo(String oldSeqNo) {
        this.oldSeqNo = oldSeqNo;
    }

    /**
     * 设置 批次编号
     * 
     * @param seqId 批次编号
     */
    @EsbName(value = "SEQ_ID")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    /**
     * 设置 交易信息数组
     * 
     * @param tranItemList 交易信息数组
     */
    @EsbName(value = "TRAN_ITEM_ARRAY")
    @EsbFieldType(value = EsbFieldType.ARRAY)
    public void setTranItemList(List<Interf3005OutArrEntity> tranItemList) {
        this.tranItemList = tranItemList;
    }

    /**
     * 取得 错误码
     * 
     * @return 错误码
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * 取得 错误信息
     * 
     * @return 错误信息
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * 取得 原批次号
     * 
     * @return 原批次号
     */
    public String getOldSeqNo() {
        return oldSeqNo;
    }

    /**
     * 取得 批次编号
     * 
     * @return 批次编号
     */
    public String getSeqId() {
        return seqId;
    }

    /**
     * 取得 交易信息数组
     * 
     * @return 交易信息数组
     */
    public List<Interf3005OutArrEntity> getTranItemList() {
        return tranItemList;
    }
}
