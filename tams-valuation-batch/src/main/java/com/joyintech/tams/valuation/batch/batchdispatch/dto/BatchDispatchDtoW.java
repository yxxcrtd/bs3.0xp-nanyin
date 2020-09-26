/*
 * 文件名：BatchDispatchDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年4月27日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.batchdispatch.dto;

import java.io.Serializable;
import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzhiang
 * @version 1.0 2018年4月27日
 * @see BatchDispatchDtoW
 * @since 1.0
 */
public class BatchDispatchDtoW extends NeedToWriteEntity implements Serializable{
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -2340235849771541691L;
    
    /**
     * details
     */
    private List<SysBatchgrpExecInfo> details;

    /**
     * 获取 details
     * @return details.
     */
    public List<SysBatchgrpExecInfo> getDetails() {
        return details;
    }

    /**
     * 设置 details
     * @param details details
     */
    public void setDetails(List<SysBatchgrpExecInfo> details) {
        this.details = details;
    }
    
}
