/*
 * 文件名：EsbReqStrEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.interf.dto;

import java.io.Serializable;

/**
 * 存放返回给esb的应答数据字符串形式的实例
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月5日
 * @see EsbReqRspDto
 * @since 1.0
 */
public class EsbReqRspDto implements Serializable {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 5281996038240309758L;

    /**
     * 从Esb传过来的请求字符串
     */
    private String reqStr;

    /**
     * 返回给esb应答数据的字符串
     */
    private String rspStr;

    /**
     * 获取 reqStr
     * 
     * @return reqStr.
     */
    public String getReqStr() {
        return reqStr;
    }

    /**
     * 设置 reqStr
     * 
     * @param reqStr reqStr
     */
    public void setReqStr(String reqStr) {
        this.reqStr = reqStr;
    }

    /**
     * 获取 rspStr
     * 
     * @return rspStr.
     */
    public String getRspStr() {
        return rspStr;
    }

    /**
     * 设置 rspStr
     * 
     * @param rspStr rspStr
     */
    public void setRspStr(String rspStr) {
        this.rspStr = rspStr;
    }
}
