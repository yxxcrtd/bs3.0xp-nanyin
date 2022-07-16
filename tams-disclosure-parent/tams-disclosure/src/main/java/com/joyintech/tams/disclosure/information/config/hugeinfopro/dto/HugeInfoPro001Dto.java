package com.joyintech.tams.disclosure.information.config.hugeinfopro.dto;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.tams.common.base.entity.DisLargeRedemption;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

public class HugeInfoPro001Dto extends DisLargeRedemption implements ITableRow, ProcessEntity {

    /**
     * 序列
     */
    private static final long serialVersionUID = -2744914232496735261L;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 选中的数据
     */
    private transient List<HugeInfoPro001Dto> selectedItem;
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

    public List<HugeInfoPro001Dto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<HugeInfoPro001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }
}
