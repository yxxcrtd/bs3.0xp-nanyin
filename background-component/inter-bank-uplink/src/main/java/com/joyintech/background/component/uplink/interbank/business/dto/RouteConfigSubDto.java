/*
 * 文件名：RouteConfigSubDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：caoguo
 * 修改时间：2020年5月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.background.component.uplink.interbank.business.dto;

import java.util.List;

import com.joyintech.background.component.uplink.interbank.database.entity.IbuIntfAttrConfig;
import com.joyintech.background.component.uplink.interbank.database.entity.IbuIntfScheme;

/**
 * 方案及属性配置联合查询DTO 〈功能详细描述〉
 * 
 * @author caoguo
 * @version 1.0 2020年5月8日
 * @see RouteConfigSubDto
 * @since 1.0
 */
public class RouteConfigSubDto extends IbuIntfScheme {

    /**
     * 属性组
     */
    private String arrtGrpId;

    /**
     * 属性配置列表
     */
    private List<IbuIntfAttrConfig> atrrList;

    /**
     * 获取 atrrList
     * 
     * @return atrrList.
     */
    public List<IbuIntfAttrConfig> getAtrrList() {
        return atrrList;
    }

    /**
     * 设置 atrrList
     * 
     * @param atrrList atrrList
     */
    public void setAtrrList(List<IbuIntfAttrConfig> atrrList) {
        this.atrrList = atrrList;
    }

    /**
     * 获取 arrtGrpId
     * 
     * @return arrtGrpId.
     */
    public String getArrtGrpId() {
        return arrtGrpId;
    }

    /**
     * 设置 arrtGrpId
     * 
     * @param arrtGrpId arrtGrpId
     */
    public void setArrtGrpId(String arrtGrpId) {
        this.arrtGrpId = arrtGrpId;
    }

}
