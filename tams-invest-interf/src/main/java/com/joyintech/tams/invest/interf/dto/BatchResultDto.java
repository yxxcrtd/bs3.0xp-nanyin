/*
 * 文件名：BatchResultDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.interf.dto;

import java.util.List;

import com.joyintech.framework.base.BaseEntity;

/**
 * batch调用接口执行返回类型
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月7日
 * @see BatchResultDto
 * @since 1.0
 */
public class BatchResultDto extends BaseEntity {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 946480512410359014L;

    /**
     * 名称
     */
    private String batchName;

    /**
     * ID
     */
    private String batchId;

    /**
     * 执行结果
     */
    private boolean result;

    /**
     * 错误信息
     */
    private String errMsg;

    /**
     * 产品列表
     */
    private List<String> prodList;

    /**
     * ods文件全名
     */
    private String odsFile;

    /**
     * 获取 result
     * 
     * @return result.
     */
    public boolean isResult() {
        return result;
    }

    /**
     * 设置 result
     * 
     * @param result result
     */
    public void setResult(boolean result) {
        this.result = result;
    }

    /**
     * 获取 batchName
     * 
     * @return batchName.
     */
    public String getBatchName() {
        return batchName;
    }

    /**
     * 设置 batchName
     * 
     * @param batchName batchName
     */
    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    /**
     * 获取 batchId
     * 
     * @return batchId.
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * 设置 batchId
     * 
     * @param batchId batchId
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    /**
     * 获取 产品列表
     * 
     * @return 产品列表.
     */
    public List<String> getProdList() {
        return prodList;
    }

    /**
     * 设置 产品列表
     * 
     * @param prodList 产品列表
     */
    public void setProdList(List<String> prodList) {
        this.prodList = prodList;
    }

    /**
     * 获取 信息
     * 
     * @return 信息.
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * 设置 信息
     * 
     * @param errMsg 信息
     */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    /**
     * 获取 ods文件全名
     * 
     * @return ods文件全名.
     */
    public String getOdsFile() {
        return odsFile;
    }

    /**
     * 设置 ods文件全名
     * 
     * @param odsPath ods文件全名
     */
    public void setOdsFile(String odsPath) {
        this.odsFile = odsPath;
    }
}
