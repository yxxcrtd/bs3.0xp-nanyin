/*
 * 文件名：InterfBaseService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月12日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.core;

import java.util.Date;
import java.util.Map;

import com.joyintech.tams.valuation.interf.core.entity.InterfExecuteInfo;

public abstract class InterfBaseService {
    
    private InterfExecuteInfo interfExecuteInfo;
    
    private Map<String, Object> params;
    
    /**
     * 接口需要处理的日期
     */
    private Date interfTime; 
    
   
    public abstract void execute() throws Exception;
    
    /**
     * 获取 params
     * @return params.
     */
    public Map<String, Object> getParams() {
        return params;
    }

    


    
    /**
     * 设置 params
     * @param params params
     */
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }




    /**
     * 获取 interfTime
     * @return interfTime.
     */
    public Date getInterfTime() {
        return interfTime;
    }

    
    /**
     * 设置 interfTime
     * @param interfTime interfTime
     */
    public void setInterfTime(Date interfTime) {
        this.interfTime = interfTime;
    }


    
    /**
     * 获取 interfExecuteInfo
     * @return interfExecuteInfo.
     */
    public InterfExecuteInfo getInterfExecuteInfo() {
        return interfExecuteInfo;
    }


    
    /**
     * 设置 interfExecuteInfo
     * @param interfExecuteInfo interfExecuteInfo
     */
    public void setInterfExecuteInfo(InterfExecuteInfo interfExecuteInfo) {
        this.interfExecuteInfo = interfExecuteInfo;
    }
}