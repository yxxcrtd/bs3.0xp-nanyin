/*
 * 文件名：NeedOperateInterfEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月8日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.dto;

import java.util.Date;

/**
 * 需要处理的接口Entity
 * 
 * @author 王波
 * @version 1.0 2018年6月8日
 * @see InterfNeedOperateEntity
 * @since 1.0
 */
public class InterfNeedOperateEntity {

    /**
     * 接口接收还是发送 R-接收 S-send
     */
    private String rsStatus;

    /**
     * 接口触发时间
     */
    private Date occurTime = new Date();
    
    /**
     * 数据处理日期
     */
    private Date resovleDate = new Date();

    public String getRsStatus() {
        return rsStatus;
    }

    public void setRsStatus(String rsStatus) {
        this.rsStatus = rsStatus;
    }

    public Date getOccurTime() {
        return occurTime;
    }

    public void setOccurTime(Date occurTime) {
        this.occurTime = occurTime;
    }

	public Date getResovleDate() {
		return resovleDate;
	}

	public void setResovleDate(Date resovleDate) {
		this.resovleDate = resovleDate;
	}
    
}
