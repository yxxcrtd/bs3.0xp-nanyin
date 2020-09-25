/*
 * 文件名：FinOpenPlanMaintenanceDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.messageadjust.maintenance.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinOpenPlan;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年10月22日
 * @see FinOpenPlanMaintenanceDto
 * @since 1.0
 */
public class FinOpenPlanMaintenanceDto extends FinOpenPlan {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 是否周期型
     */
    private String isCycle;
    /**
     * 选择数据
     */
    private List<FinOpenPlanMaintenanceDto> selectedItem;
    /**
     * 选择数据
     */
    private List<FinOpenPlanMaintenanceDto> selectedDto;
    public String getIsCycle() {
        return isCycle;
    }
    
    public void setIsCycle(String isCycle) {
        this.isCycle = isCycle;
    }

    
    public List<FinOpenPlanMaintenanceDto> getSelectedItem() {
        return selectedItem;
    }

    
    public void setSelectedItem(List<FinOpenPlanMaintenanceDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    
    public List<FinOpenPlanMaintenanceDto> getSelectedDto() {
        return selectedDto;
    }

    
    public void setSelectedDto(List<FinOpenPlanMaintenanceDto> selectedDto) {
        this.selectedDto = selectedDto;
    }
    

}
