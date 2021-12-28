package com.joyintech.tams.report.submitmaintenance.agentprdinfo.dto;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.ChbAgentPrdInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.submitmaintenance.agentprdinfo.validate.AgentPrdInfoValid;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

@ValidLogic(targetClass = AgentPrdInfoValid.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class AgentPrdInfoDto extends ChbAgentPrdInfo implements ProcessEntity {
    private String operateType;//操作类型
    private String elementId;//页面元素
    private String pageModel;//页面模型

    /**
     * 选中的数据
     */
    private transient List<AgentPrdInfoDto> selectedItem;

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public List<AgentPrdInfoDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<AgentPrdInfoDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.维护数据状态, this.getStatus());
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }
}