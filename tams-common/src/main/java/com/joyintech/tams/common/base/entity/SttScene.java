package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class SttScene extends BaseEntity {
    /**
     * 情景代码
     */
    private String sceneId;

    /**
     * 情景名称
     */
    private String sceneName;

    /**
     * 有效区间开始
     */
    private Date startDate;

    /**
     * 有效区间结束
     */
    private Date endDate;

    /**
     * 是否事件
     */
    private String isEvent;

    /**
     * 事件代码
     */
    private String eventId;

    /**
     * 情景模板代码
     */
    private String sceneTpmlId;

    /**
     * 是否模板
     */
    private String isTmpl;

    /**
     * 启用状态
     */
    private String effStatus;

    /**
     * 是否全可用
     */
    private String isAllUseable;
    
    /**
     * 备注 
     */
    private String remark;

    /**
     * 机构号
     */
    private String institutionCode; 
    /**
     * 取得 情景代码
     * @return 情景代码
     */
    public String getSceneId() {
        return sceneId;
    }

    /**
     * 设置 情景代码
     * @param sceneId 情景代码
     */
    public void setSceneId(String sceneId) {
        this.sceneId = sceneId == null ? null : sceneId.trim();
    }

    /**
     * 取得 情景名称
     * @return 情景名称
     */
    public String getSceneName() {
        return sceneName;
    }

    /**
     * 设置 情景名称
     * @param sceneName 情景名称
     */
    public void setSceneName(String sceneName) {
        this.sceneName = sceneName == null ? null : sceneName.trim();
    }

    /**
     * 取得 有效区间开始
     * @return 有效区间开始
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置 有效区间开始
     * @param startDate 有效区间开始
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 取得 有效区间结束
     * @return 有效区间结束
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置 有效区间结束
     * @param endDate 有效区间结束
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 取得 是否事件
     * @return 是否事件
     */
    public String getIsEvent() {
        return isEvent;
    }

    /**
     * 设置 是否事件
     * @param isEvent 是否事件
     */
    public void setIsEvent(String isEvent) {
        this.isEvent = isEvent == null ? null : isEvent.trim();
    }

    /**
     * 取得 事件代码
     * @return 事件代码
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * 设置 事件代码
     * @param eventId 事件代码
     */
    public void setEventId(String eventId) {
        this.eventId = eventId == null ? null : eventId.trim();
    }

    /**
     * 取得 情景模板代码
     * @return 情景模板代码
     */
    public String getSceneTpmlId() {
        return sceneTpmlId;
    }

    /**
     * 设置 情景模板代码
     * @param sceneTpmlId 情景模板代码
     */
    public void setSceneTpmlId(String sceneTpmlId) {
        this.sceneTpmlId = sceneTpmlId == null ? null : sceneTpmlId.trim();
    }

    /**
     * 取得 是否模板
     * @return 是否模板
     */
    public String getIsTmpl() {
        return isTmpl;
    }

    /**
     * 设置 是否模板
     * @param isTmpl 是否模板
     */
    public void setIsTmpl(String isTmpl) {
        this.isTmpl = isTmpl == null ? null : isTmpl.trim();
    }

    /**
     * 取得 启用状态
     * @return 启用状态
     */
    public String getEffStatus() {
        return effStatus;
    }

    /**
     * 设置 启用状态
     * @param effStatus 启用状态
     */
    public void setEffStatus(String effStatus) {
        this.effStatus = effStatus == null ? null : effStatus.trim();
    }

    /**
     * 取得 是否全可用
     * @return 是否全可用
     */
    public String getIsAllUseable() {
        return isAllUseable;
    }

    /**
     * 设置 是否全可用
     * @param isAllUseable 是否全可用
     */
    public void setIsAllUseable(String isAllUseable) {
        this.isAllUseable = isAllUseable == null ? null : isAllUseable.trim();
    }
    
    /**
     * 取得 备注 2019/9/2 wangkang
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    
    /**
     * 获取 institutionCode
     * @return institutionCode.
     */
    public String getInstitutionCode() {
        return institutionCode;
    }

    
    /**
     * 设置 institutionCode
     * @param institutionCode institutionCode
     */
    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }
    
    
}