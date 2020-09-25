package com.joyintech.service.asset.option.dto; /*
 * 文件名：
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：
 * 修改时间：
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

public class FinOptionTargetDto extends BaseEntity implements ProcessEntity {
    private static final long serialVersionUID = -5790927186395487117L;
    /**
     * 标的代码
     */
    private String targetId;

    /**
     * 标的名称
     */
    private String targetName;

    /**
     * 标的类型
     */
    private String targetType;

    /**
     * 备注
     */

    private String remark;
    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 目的和意义以及可能会用到的地方
     */
    private String pageModel;

    /**
     * 已选择的数据
     */
    public List<FinOptionTargetDto> SelectedItem;

    /**
     * 要素id
     */
    private String elementId;

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getOperateType() {
        return operateType;
    }


    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public List<FinOptionTargetDto> getSelectedItem() {
        return SelectedItem;
    }

    public void setSelectedItem(List<FinOptionTargetDto> selectedItem) {
        SelectedItem = selectedItem;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
