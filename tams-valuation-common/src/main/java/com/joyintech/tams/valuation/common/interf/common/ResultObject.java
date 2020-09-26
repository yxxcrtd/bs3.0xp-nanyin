/*
 * 文件名：ResultObject.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2019年1月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.common.interf.common;

import com.joyintech.framework.base.BaseEntity;

import java.util.Map;

/**
 * <p>估值交互接口共通返回类</p>
 * 
 * @author
 * @since v1.0.0.0-20190903
 *
 */
public class ResultObject extends BaseEntity {

    /**
     * 处理结果代码
     */
    private String resultCode;

    /**
     * 处理结果描述
     */
    private String resultMsg;

    /**
     * 处理结果集
     */
    private Map<String,Object> resultMap;


    public ResultObject() {
        super();
    }

    public ResultObject(ResultCode resultCode) {
        super();
        this.resultCode = resultCode.getCode();
        this.resultMsg = resultCode.getName();
    }

    public ResultObject(String resultCode, String resultMsg) {
        super();
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }
}
