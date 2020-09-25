package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class ChbSysPlanAdjWarn extends BaseEntity {
    /**
     * 提醒代码
     */
    private String warnId;

    /**
     * 任务代码
     */
    private String taskId;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 业务类型 参照数据字典
     */
    private String businessType;

    /**
     * 产品代码
     */
    private String prdId;

    /**
     * 画面代码
     */
    private String pageId;

    /**
     * 要素代码
     */
    private String elementId;

    /**
     * 处理时间
     */
    private Date dealTime;

    /**
     * 提醒状态 参照数据字典
     */
    private String warnStatus;

    /**
     * 提示信息
     */
    private String remInfo;

    /**
     * 取得 提醒代码
     * @return 提醒代码
     */
    public String getWarnId() {
        return warnId;
    }

    /**
     * 设置 提醒代码
     * @param warnId 提醒代码
     */
    public void setWarnId(String warnId) {
        this.warnId = warnId == null ? null : warnId.trim();
    }

    /**
     * 取得 任务代码
     * @return 任务代码
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 设置 任务代码
     * @param taskId 任务代码
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    /**
     * 取得 工作流代码
     * @return 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 业务类型 参照数据字典
     * @return 业务类型 参照数据字典
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * 设置 业务类型 参照数据字典
     * @param businessType 业务类型 参照数据字典
     */
    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getPrdId() {
        return prdId;
    }

    /**
     * 设置 产品代码
     * @param prdId 产品代码
     */
    public void setPrdId(String prdId) {
        this.prdId = prdId == null ? null : prdId.trim();
    }

    /**
     * 取得 画面代码
     * @return 画面代码
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * 设置 画面代码
     * @param pageId 画面代码
     */
    public void setPageId(String pageId) {
        this.pageId = pageId == null ? null : pageId.trim();
    }

    /**
     * 取得 要素代码
     * @return 要素代码
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 要素代码
     * @param elementId 要素代码
     */
    public void setElementId(String elementId) {
        this.elementId = elementId == null ? null : elementId.trim();
    }

    /**
     * 取得 处理时间
     * @return 处理时间
     */
    public Date getDealTime() {
        return dealTime;
    }

    /**
     * 设置 处理时间
     * @param dealTime 处理时间
     */
    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    /**
     * 取得 提醒状态 参照数据字典
     * @return 提醒状态 参照数据字典
     */
    public String getWarnStatus() {
        return warnStatus;
    }

    /**
     * 设置 提醒状态 参照数据字典
     * @param warnStatus 提醒状态 参照数据字典
     */
    public void setWarnStatus(String warnStatus) {
        this.warnStatus = warnStatus == null ? null : warnStatus.trim();
    }

    /**
     * 取得 提示信息
     * @return 提示信息
     */
    public String getRemInfo() {
        return remInfo;
    }

    /**
     * 设置 提示信息
     * @param remInfo 提示信息
     */
    public void setRemInfo(String remInfo) {
        this.remInfo = remInfo == null ? null : remInfo.trim();
    }
}