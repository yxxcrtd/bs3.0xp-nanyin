package com.joyintech.tams.cfets.cfetsmanualsettle.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.workflow.engine.dto.ProcessEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 银行间手工交收
 * 银行间手工交收修改参数
 * @author sjj
 * @version 1.0 2021年11月22日
 * @see CfetsManualSettleUpdateRequest
 * @since 1.0
 */

@ApiModel(value="银行间手工交收修改参数",description="银行间手工交收修改参数")
public class CfetsManualSettleUpdateRequest extends BaseEntity implements ITableRow, ProcessEntity {
    // 要素ID
    private String elementId;

    // 操作类型 保存 提交
    private String operateType;

    // 按钮类型 交收01、交收失败02、回溯04、修改05
    private String buttonType;

    CfetsManualSettleDto selectedItem;

    @ApiModelProperty(name="settleNumT",value="汇总交收数量",example="A1")
    private BigDecimal settleNumT;

    @ApiModelProperty(name="settleAmtT",value="汇总交收金额",example="A1")
    private BigDecimal settleAmtT;

    //页面组合信息
    List<PortSettleinfoDto> portSettleinfoDtoList;

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

    public CfetsManualSettleDto getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(CfetsManualSettleDto selectedItem) {
        this.selectedItem = selectedItem;
    }

    public List<PortSettleinfoDto> getPortSettleinfoDtoList() {
        return portSettleinfoDtoList;
    }

    public void setPortSettleinfoDtoList(List<PortSettleinfoDto> portSettleinfoDtoList) {
        this.portSettleinfoDtoList = portSettleinfoDtoList;
    }

    public BigDecimal getSettleNumT() {
        return settleNumT;
    }

    public void setSettleNumT(BigDecimal settleNumT) {
        this.settleNumT = settleNumT;
    }

    public BigDecimal getSettleAmtT() {
        return settleAmtT;
    }

    public void setSettleAmtT(BigDecimal settleAmtT) {
        this.settleAmtT = settleAmtT;
    }
}
