/*
 * 文件名：BatchProcessing001Dto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年12月26日
 * 修改内容：新建
 * 系统名称：中债报送系统
 */
 
package com.joyintech.tams.report.system.batchprocessing.dto;

import java.util.Date;

import com.joyintech.tams.common.base.entity.ChbSysBatchInfo;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2017年12月26日
 * @see BatchProcessing001Dto
 * @since 1.0
 */
public class BatchProcessing001Dto extends ChbSysBatchInfo{
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -2916707151688511750L;
    /**
     * 任务名称
     */
    private String processName;
    /**
     * 处理时间
     */
    private Date dealDate;
    
    /**
     * 获取 processName
     * @return processName.
     */
    public String getProcessName() {
        return processName;
    }

    
    /**
     * 设置 processName
     * @param processName processName
     */
    public void setProcessName(String processName) {
        this.processName = processName;
    }


    
    /**
     * 获取 dealDate
     * @return dealDate.
     */
    public Date getDealDate() {
        return dealDate;
    }


    
    /**
     * 设置 dealDate
     * @param dealDate dealDate
     */
    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }
}
