/*
 * 文件名：FlowRiskSceneManage001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年8月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.dto;

import java.math.BigDecimal;
import java.util.List;


import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.common.ValidateGroupEffective;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.valid.FlowRiskSceneManage002Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 情景管理实体
 * @author wangkang
 * @version 1.0 2019年8月13日
 * @see FlowRiskSceneManageListDto
 * @since 1.0
 */
public class FlowRiskSceneManageListDto {

    /**
     * 适用产品
     */
    private String openmode;
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
     * 旧情景名称
     */
    private String OldSceneName;
    /**
     * 情景编号
     */
    private String sceneId;
    /**
     * 数据状态
     */
    private String dataStatus;
    /**
     * 指标代码
     */
    private String indId;
    /**
     * 指标名称
     */
    private String indName;
    /**
     * 指标类型
     */
    private String indType;
    /**
     * 备注
     */
    private String remark;
    /**
     * 因子代码
     */
    private String ftrId;
    /**
     * 因子名称
     */
    private String ftrName;
    /**
     * 轻度
     */
    private BigDecimal lightValue;
    /**
     * 中度
     */
    private BigDecimal mediumValue;
    /**
     * 重度
     */
    private BigDecimal heavyValue;
    /**
     * 因子说明
     */
    private String ftrRemark;
    /**
     * 申赎净流出radio选择
     */
    private String netOutf;
    
    /**
     * 模板代码
     */
    private String tmplId;
    /**
     * 模板名称
     */
    private String tmplName;
    /**
     * 情景因子代码
     */
    private String sceneFtrId;
    /**
     * 标签值
     */
    private String tipsValue;
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
     * 设置 sceneType
     * @param sceneType sceneType
     */
    public void setSceneType(String sceneType) {
        this.sceneType = sceneType;
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

    

    public String getRiskTypeName() {
        return SysDataUtil.getNameFromDataDict(DictCode.情景风险分类,  this.riskType);
    }

    public String getSceneTypeName() {
        return SysDataUtil.getNameFromDataDict(DictCode.情景类型,  this.sceneType);
    }
    
    public String getSceneType2Name() {
        return SysDataUtil.getNameFromDataDict(DictCode.情景二级类型,  this.sceneType2);
    }
    



    
    /**
     * 获取 indId
     * @return indId.
     */
    public String getIndId() {
        return indId;
    }

    
    /**
     * 设置 indId
     * @param indId indId
     */
    public void setIndId(String indId) {
        this.indId = indId;
    }

    
    /**
     * 获取 indName
     * @return indName.
     */
    public String getIndName() {
        return indName;
    }

    
    /**
     * 设置 indName
     * @param indName indName
     */
    public void setIndName(String indName) {
        this.indName = indName;
    }

    
    /**
     * 获取 indType
     * @return indType.
     */
    public String getIndType() {
        return indType;
    }

    
    /**
     * 设置 indType
     * @param indType indType
     */
    public void setIndType(String indType) {
        this.indType = indType;
    }

    
    /**
     * 设置 remark
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    
    /**
     * 获取 remark
     * @return remark.
     */
    public String getRemark() {
        return remark;
    }

    
    /**
     * 获取 ftrId
     * @return ftrId.
     */
    public String getFtrId() {
        return ftrId;
    }

    
    /**
     * 设置 ftrId
     * @param ftrId ftrId
     */
    public void setFtrId(String ftrId) {
        this.ftrId = ftrId;
    }

    
    /**
     * 获取 lightValue
     * @return lightValue.
     */
    public BigDecimal getLightValue() {
        return lightValue;
    }

    
    /**
     * 设置 lightValue
     * @param lightValue lightValue
     */
    public void setLightValue(BigDecimal lightValue) {
        this.lightValue = lightValue;
    }

    
    /**
     * 获取 mediumValue
     * @return mediumValue.
     */
    public BigDecimal getMediumValue() {
        return mediumValue;
    }

    
    /**
     * 设置 mediumValue
     * @param mediumValue mediumValue
     */
    public void setMediumValue(BigDecimal mediumValue) {
        this.mediumValue = mediumValue;
    }

    
    /**
     * 获取 heavyValue
     * @return heavyValue.
     */
    public BigDecimal getHeavyValue() {
        return heavyValue;
    }

    
    /**
     * 设置 heavyValue
     * @param heavyValue heavyValue
     */
    public void setHeavyValue(BigDecimal heavyValue) {
        this.heavyValue = heavyValue;
    }

    
    /**
     * 获取 ftrRemark
     * @return ftrRemark.
     */
    public String getFtrRemark() {
        return ftrRemark;
    }

    
    /**
     * 设置 ftrRemark
     * @param ftrRemark ftrRemark
     */
    public void setFtrRemark(String ftrRemark) {
        this.ftrRemark = ftrRemark;
    }

    
    /**
     * 获取 ftrName
     * @return ftrName.
     */
    public String getFtrName() {
        return ftrName;
    }

    
    /**
     * 设置 ftrName
     * @param ftrName ftrName
     */
    public void setFtrName(String ftrName) {
        this.ftrName = ftrName;
    }

    
    /**
     * 获取 netOutf
     * @return netOutf.
     */
    public String getNetOutf() {
        return netOutf;
    }

    
    /**
     * 设置 netOutf
     * @param netOutf netOutf
     */
    public void setNetOutf(String netOutf) {
        this.netOutf = netOutf;
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
     * 获取 sceneFtrId
     * @return sceneFtrId.
     */
    public String getSceneFtrId() {
        return sceneFtrId;
    }

    
    /**
     * 设置 sceneFtrId
     * @param sceneFtrId sceneFtrId
     */
    public void setSceneFtrId(String sceneFtrId) {
        this.sceneFtrId = sceneFtrId;
    }

    
    /**
     * 获取 tipsValue
     * @return tipsValue.
     */
    public String getTipsValue() {
        return tipsValue;
    }

    
    /**
     * 设置 tipsValue
     * @param tipsValue tipsValue
     */
    public void setTipsValue(String tipsValue) {
        this.tipsValue = tipsValue;
    }
    /**
     * 设置 openmode
     * @return
     */
	public String getOpenmode() {
		return openmode;
	}
	/**
	 * 获取 openmode
	 * @param openmode
	 */
	public void setOpenmode(String openmode) {
		this.openmode = openmode;
	}
	/**
	 * 获取旧 情景名称
	 * @return
	 */
	public String getOldSceneName() {
		return OldSceneName;
	}
	/**
	 * 设置旧情景名称
	 * @param oldSceneName
	 */
	public void setOldSceneName(String oldSceneName) {
		OldSceneName = oldSceneName;
	}
    
    
}
