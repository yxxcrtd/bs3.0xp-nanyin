/*
 * 文件名：InFlowEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.tcms002.entity;

import com.joyintech.framework.base.BaseEntity;

/**
 * 产品在流程中entity
 * 
 * @author changwenbin
 * @version 1.0 2018年11月14日
 * @see InFlowEntity
 * @since 1.0
 */
public class InFlowEntity extends BaseEntity {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 7609398873403129707L;

    /**
     * 产品状态
     */
    private String prostatus;

    /**
     * 产品数据json
     */
    private String datajson;

    /**
     * 获取 产品状态
     * 
     * @return 产品状态
     */
    public String getProstatus() {
        return prostatus;
    }

    /**
     * 设置 产品状态
     * 
     * @param prostatus 产品状态
     */
    public void setProstatus(String prostatus) {
        this.prostatus = prostatus;
    }

    /**
     * 获取 产品数据json
     * 
     * @return 产品数据json
     */
    public String getDatajson() {
        return datajson;
    }

    /**
     * 设置 产品数据json
     * 
     * @param datajson 产品数据json
     */
    public void setDatajson(String datajson) {
        this.datajson = datajson;
    }

}
