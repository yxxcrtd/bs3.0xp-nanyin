/*
 * 文件名：FlowRiskSceneManage001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.dto;

import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.SttFactor;

/**
 * 
 * 流动性风险-情景管理查询返回结果对象
 * @author wankang
 * @version 1.0 2019年8月21日
 * @see FlowRiskSceneManage001Dto
 * @since 1.0
 */
public class FlowRiskSceneManage001Dto  extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 6855972078440751347L;

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
     * 适用风险分类
     */
    private String riskType;
    /**
     * 适用二级分类
     */
    private String riskType2;
    /**
     * 情景类型
     */
    private String sceneType;
    /**
     * 情景二级类型
     */
    private String sceneType2;
    /**
     * 情景名称
     */
    private String sceneName;
    /**
     * 情景编号
     */
    private String sceneId;
    /**
     * 关联因子:一个情景对应多个因子
     */
    //private String sceneFtrName;
    /**
     * 启用状态
     */
    private String effStatus;
    /**
     * 数据状态
     */
    private String dataStatus;

    /**
     * 情景模板编号
     */
    private String tmplId;
    
    /**
     * 情景模板名称
     */
    private String tmplName;
    
    /**
     * 适用产品（开放类型）
     */
    private String openMode;

    /**
     * 备注
     */
    private String remark;
    
    /**
     * 关联因子:一个情景对应多个因子
     */
    private List<SttFactor> ftrList;
    
    /**
     * 获取 pageModel
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }
    
    /**
     * 设置 pageModel
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }
    
    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }
    
    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
    
    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }
    
    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }
    
    /**
     * 获取 pageId
     * @return pageId.
     */
    public String getPageId() {
        return pageId;
    }
    
    /**
     * 设置 pageId
     * @param pageId pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
    
    /**
     * 获取 riskType
     * @return riskType.
     */
    public String getRiskType() {
        return riskType;
    }
    
    /**
     * 设置 riskType
     * @param riskType riskType
     */
    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }
    
    /**
     * 获取 riskType2
     * @return riskType2.
     */
    public String getRiskType2() {
        return riskType2;
    }
    
    /**
     * 设置 riskType2
     * @param riskType2 riskType2
     */
    public void setRiskType2(String riskType2) {
        this.riskType2 = riskType2;
    }
    
    /**
     * 获取 sceneType
     * @return sceneType.
     */
    public String getSceneType() {
        return sceneType;
    }
    
    /**
     * 设置 sceneType
     * @param sceneType sceneType
     */
    public void setSceneType(String sceneType) {
        this.sceneType = sceneType;
    }

    /**
     * 获取 sceneType2
     * @return sceneType2.
     */
    public String getSceneType2() {
        return sceneType2;
    }

    /**
     * 设置 sceneType2
     * @param sceneType2 sceneType2
     */
    public void setSceneType2(String sceneType2) {
        this.sceneType2 = sceneType2;
    }

    /**
     * 获取 sceneName
     * @return sceneName.
     */
    public String getSceneName() {
        return sceneName;
    }
    
    /**
     * 设置 sceneName
     * @param sceneName sceneName
     */
    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }
    
    /**
     * 获取 sceneId
     * @return sceneId.
     */
    public String getSceneId() {
        return sceneId;
    }
    
    /**
     * 设置 sceneId
     * @param sceneId sceneId
     */
    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    /**
     * 获取 effStatus
     * @return effStatus.
     */
    public String getEffStatus() {
        return effStatus;
    }
    
    /**
     * 设置 effStatus
     * @param effStatus effStatus
     */
    public void setEffStatus(String effStatus) {
        this.effStatus = effStatus;
    }
    
    /**
     * 获取 dataStatus
     * @return dataStatus.
     */
    public String getDataStatus() {
        return dataStatus;
    }
    
    /**
     * 设置 dataStatus
     * @param dataStatus dataStatus
     */
    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }
    
    /**
     * 获取 tmplId
     * @return tmplId.
     */
    public String getTmplId() {
        return tmplId;
    }
    
    /**
     * 设置 tmplId
     * @param tmplId tmplId
     */
    public void setTmplId(String tmplId) {
        this.tmplId = tmplId;
    }
    
    /**
     * 获取 tmplName
     * @return tmplName.
     */
    public String getTmplName() {
        return tmplName;
    }
    
    /**
     * 设置 tmplName
     * @param tmplName tmplName
     */
    public void setTmplName(String tmplName) {
        this.tmplName = tmplName;
    }
    
    /**
     * 获取 openMode
     * @return openMode.
     */
    public String getOpenMode() {
        return openMode;
    }
    
    /**
     * 设置 openMode
     * @param openMode openMode
     */
    public void setOpenMode(String openMode) {
        this.openMode = openMode;
    }

    /**
     * 获取 remark
     * @return remark.
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 remark
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 ftrList
     * @return ftrList.
     */
    public List<SttFactor> getFtrList() {
        return ftrList;
    }

    /**
     * 设置 ftrList
     * @param ftrList ftrList
     */
    public void setFtrList(List<SttFactor> ftrList) {
        this.ftrList = ftrList;
    }

}
