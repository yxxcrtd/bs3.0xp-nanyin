/*
 * 文件名：InterSMS003Entity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.isms003.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.isms004.entity.InterfSubSysNoArray;

/**
 * 接口SMS003输入
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月22日
 * @see InterfSMS003Entity
 * @since 1.0
 */
public class InterfSMS003Entity {

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 请求系统编号
     */
    private String consumerId;

    /**
     * 处理结果
     */
    private String result;

    /**
     * 批次编号
     */
    private String seqId;

    /**
     * 原批次号
     */
    private String oldSeqNo;

    /**
     * 子系统列表
     */
    private List<InterfSubSysNoArray> arr;

    /**
     * 取得 文件路径
     * 
     * @return 文件路径
     */
    @EsbName(value = "FILE_PATH")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    @Required
    public String getFilePath() {
        return filePath;
    }

    /**
     * 取得 请求系统编号
     * 
     * @return 请求系统编号
     */
    @EsbName(value = "CONSUMER_ID")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 6)
    @Required
    public String getConsumerId() {
        return consumerId;
    }

    /**
     * 取得 处理结果
     * 
     * @return 处理结果
     */
    @EsbName(value = "RESULT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 1)
    @Required
    public String getResult() {
        return result;
    }

    /**
     * 取得 批次编号
     * 
     * @return 批次编号
     */
    @EsbName(value = "SEQ_ID")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 30)
    @Required
    public String getSeqId() {
        return seqId;
    }

    /**
     * 取得 原批次号
     * 
     * @return 原批次号
     */
    @EsbName(value = "OLD_SEQ_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 30)
    public String getOldSeqNo() {
        return oldSeqNo;
    }

    /**
     * 设置 文件路径
     * 
     * @param filePath 文件路径
     */
    @EsbName(value = "FILE_PATH")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 设置 请求系统编号
     * 
     * @param consumerId 请求系统编号
     */
    @EsbName(value = "CONSUMER_ID")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }

    /**
     * 设置 处理结果
     * 
     * @param result 处理结果
     */
    @EsbName(value = "RESULT")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setResult(String result) {
        this.result = result;
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
     * 获取 子系统列表
     * 
     * @return 子系统列表.
     */
    @EsbName(value = "SYSTEM_ARRAY")
    @EsbFieldType(value = EsbFieldType.ARRAY)
    public List<InterfSubSysNoArray> getArr() {
        return arr;
    }

    /**
     * 设置 子系统列表
     * 
     * @param arr 子系统列表
     */
    @EsbName(value = "SYSTEM_ARRAY")
    @EsbFieldType(value = EsbFieldType.ARRAY)
    public void setArr(List<InterfSubSysNoArray> arr) {
        this.arr = arr;
    }
}
