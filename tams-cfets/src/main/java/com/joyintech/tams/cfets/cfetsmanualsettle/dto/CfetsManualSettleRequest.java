package com.joyintech.tams.cfets.cfetsmanualsettle.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.workflow.engine.dto.ProcessEntity;
import io.swagger.annotations.ApiModel;
import java.util.List;

/**
 * 银行间手工交收
 * 银行间手工交收DTO
 * @author sjj
 * @version 1.0 2021年11月12日
 * @see CfetsManualSettleRequest
 * @since 1.0
 */

@ApiModel(value="银行间手工交收参数",description="银行间手工交收参数")
public class CfetsManualSettleRequest extends BaseEntity implements ITableRow, ProcessEntity {
    // 要素ID
    private String elementId;

    // 操作类型 保存 提交
    private String operateType;

    // 按钮类型 交收01、交收失败02、回溯04、修改05
    private String buttonType;

    List<CfetsManualSettleDto> selectedItem;

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getButtonType() {
        return buttonType;
    }

    public void setButtonType(String buttonType) {
        this.buttonType = buttonType;
    }

    public List<CfetsManualSettleDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<CfetsManualSettleDto> selectedItem) {
        this.selectedItem = selectedItem;
    }
}
