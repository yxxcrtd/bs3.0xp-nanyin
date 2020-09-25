package com.joyintech.service.riskrds.limitmanagement.rskevent.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.RskEvent;
import com.joyintech.tams.common.base.entity.RskEventParaRel;

public class RskEvent003Dto extends RskEvent {

    /**
     * 
     */
    private static final long serialVersionUID = -8813939139338674203L;

    /**
     * 事件名称
     */
    private String eventName;

    /**
     * 画面处理模式
     */
    private String pageModel;

    /**
     * 操作类型 - 工作流参数
     */
    private String operateType;

    /**
     * 画面Id - 工作流参数
     */
    private String pageId;

    /**
     * 按钮ID - 工作流参数
     */
    private String elementId;

    /**
     * 配置信息
     */
    private List<RskEventParaRel> paramList;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
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

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public List<RskEventParaRel> getParamList() {
        return paramList;
    }

    public void setParamList(List<RskEventParaRel> paramList) {
        this.paramList = paramList;
    }

}
