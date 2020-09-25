/*
 * 文件名：InterfCusdep1020OutEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.icusdep1020.entity;

import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 接口CUSDEP1020-输出
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月23日
 * @see InterfCusdep1020OutEntity
 * @since 1.0
 */
public class InterfCusdep1020OutEntity {

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 对手流水号
     */
    private String opponentSeqNo;

    /**
     * 托管网银流水号
     */
    private String trustEbankSeqNo;

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
     * 取得 对手流水号
     * 
     * @return 对手流水号
     */
    public String getOpponentSeqNo() {
        return opponentSeqNo;
    }

    /**
     * 取得 托管网银流水号
     * 
     * @return 托管网银流水号
     */
    public String getTrustEbankSeqNo() {
        return trustEbankSeqNo;
    }

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
     * 设置 对手流水号
     * 
     * @param opponentSeqNo 对手流水号
     */
    @EsbName(value = "OPPONENT_SEQ_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setOpponentSeqNo(String opponentSeqNo) {
        this.opponentSeqNo = opponentSeqNo;
    }

    /**
     * 设置 托管网银流水号
     * 
     * @param trustEbankSeqNo 托管网银流水号
     */
    @EsbName(value = "TRUST_EBANK_SEQ_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setTrustEbankSeqNo(String trustEbankSeqNo) {
        this.trustEbankSeqNo = trustEbankSeqNo;
    }
}
