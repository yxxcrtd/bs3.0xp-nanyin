/*
 * 文件名：BatchChildDispatchDtoW.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：maodongjin
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.batchchilddispatch.dto;

import java.io.Serializable;
import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.BokBatchTaskTodo;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author maodongjin
 * @version 1.0 2018年5月5日
 * @see BatchChildDispatchDtoW
 * @since 1.0
 */
public class BatchChildDispatchDtoW extends NeedToWriteEntity implements Serializable {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -1184642988734083414L;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     * 明细结果数据
     */
    private List<BokBatchTaskTodo> details;

    /**
     * 获取 details
     * @return details.
     */
    public List<BokBatchTaskTodo> getDetails() {
        return details;
    }

    /**
     * 设置 details
     * @param details details
     */
    public void setDetails(List<BokBatchTaskTodo> details) {
        this.details = details;
    }

}
