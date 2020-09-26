/*
 * 文件名：ValRecvFileEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.transdata.entity;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.ValInterflog;
/**
 * 接收文件entity
 * 
 * @author 王波
 * @version 1.0 2018年9月14日
 * @see ValRecvFileEntity
 * @since 1.0
 */
public class ValRecvFileEntity extends BaseEntity {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 接收明细日志
     */
    private List<ValInterflog> interfLogs = new ArrayList<>();

    
    /**
     * 获取 interfLogs
     * @return interfLogs.
     */
    public List<ValInterflog> getInterfLogs() {
        return interfLogs;
    }

    
    /**
     * 设置 interfLogs
     * @param interfLogs interfLogs
     */
    public void setInterfLogs(List<ValInterflog> interfLogs) {
        this.interfLogs = interfLogs;
    }
    
    

}
