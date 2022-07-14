package com.joyintech.service.disclosure.information.config.proelementadj.dto;

import com.joyintech.tams.common.base.entity.DisProdAdjust;

import java.util.List;

public class ProElementAdjDto extends DisProdAdjust {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 选中的数据
     */
    private transient List<ProElementAdjDto> selectedItem;
    /**
     * 元素ID
     */
    private String elementId;

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public List<ProElementAdjDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<ProElementAdjDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }
}
