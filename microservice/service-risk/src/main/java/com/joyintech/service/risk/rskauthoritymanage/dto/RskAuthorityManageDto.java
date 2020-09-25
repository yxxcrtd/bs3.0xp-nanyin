/*
 * 文件名：RskAuthorityManageDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.rskauthoritymanage.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.RskAuthorizeMng;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年11月30日
 * @see RskAuthorityManageDto
 * @since 1.0
 */
public class RskAuthorityManageDto extends RskAuthorizeMng{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 画面处理模式
     */
    private String pageModel;

    /**
     * 操作类型 - 工作流参数
     */
    private String operateType;

    /**
     * 按钮ID - 工作流参数
     */
    private String elementId;
    /**
     * 画面Id - 工作流参数
     */
    private String pageId;
    /**
     * 流程中做pk1存json串
     */
    private String allData;
    
    /**
     * 选择数据
     */
    private List<RskAuthorityManageDto> selectedItem;

    
    public String getPageModel() {
        return pageModel;
    }

    
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    
    public String getOperateType() {
        return operateType;
    }

    
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    
    public String getElementId() {
        return elementId;
    }

    
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    
    public String getPageId() {
        return pageId;
    }

    
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getAllData() {
        return allData;
    }


    
    public void setAllData(String allData) {
        this.allData = allData;
    }

    


    public List<RskAuthorityManageDto> getSelectedItem() {
        return selectedItem;
    }

    
    public void setSelectedItem(List<RskAuthorityManageDto> selectedItem) {
        this.selectedItem = selectedItem;
    }
    
}
