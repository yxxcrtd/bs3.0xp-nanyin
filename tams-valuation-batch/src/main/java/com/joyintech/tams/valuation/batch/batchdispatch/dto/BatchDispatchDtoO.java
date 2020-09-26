/*
 * 文件名：DepoAcctGroupInfoEntity.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统 修改人：caoguo 修改时间：2016年12月5日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.batch.batchdispatch.dto;

import java.io.Serializable;
import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;

/**
 * 根据CDATE查询最小跑批日 得到一个list&lt;A&gt;
 * 
 * @author caoguo
 * @version 1.0 2016年12月5日
 * @see BatchDispatchDtoO
 * @since 1.0
 */
public class BatchDispatchDtoO extends NeedOperateEntity implements Serializable {

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
     * 
     * @return details.
     */
    public List<SysBatchgrpExecInfo> getDetails() {
        return details;
    }

    /**
     * 设置 details
     * 
     * @param details details
     */
    public void setDetails(List<SysBatchgrpExecInfo> details) {
        this.details = details;
    }

}
