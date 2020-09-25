/*
 * 文件名：MarketRiskSceneManage002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：汪海
 * 修改时间：2019年9月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;


/**
 * 市场性风险-情景新建DTO
 * 〈功能详细描述〉
 * @author 汪海
 * @version 1.0 2019年9月4日
 * @see MarketRiskSceneManage002Dto
 * @since 1.0
 */
public class MarketRiskSceneManage002Dto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 4426277687347352046L;
    
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
    private String suitProduct;
    /**
     * 情景名称
     */
    private String sceneName;
    /**
     * 情景编号
     */
    private String sceneId;
    /**
     * 启用状态
     */
    private String effStatus;
    /**
     * 数据状态
     */
    private String dataStatus;
    /**
     * 备注
     */
    private String remark;
    /**
     * 模板代码
     */
    private String tmplId;
    /**
     * 模板名称
     */
    private String tmplName;
    /**
     * 区间开始日期
     */
    private Date startDate;
    /**
     * 区间结束日期
     */
    private Date endDate;
    
    
    /**
     * 关联因子分类:一个情景对应多个因子分类
     */
    private List<MarketRiskSceneFactorTypeDto> ftrTypeList;
    /**
     * 情景因子代码
     */
    private String sceneFtrId;
    /**
     * 因子代码
     */
    private String ftrId;
    /**
     * 因子名称
     */
    private String ftrName;
    /**
     * 因子分类
     */
    private String ftrTypeValue;
    /**
     * 因子说明
     */
    private String ftrRemark;

    private String institutionCode;

    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    /**
     * 因子变化值
     */
    private List<MarketRiskSceneManage003Dto> ftrInfoList;
    
    /**
     * 产品指标集合
     */
    private List<MarketRiskSceneManage002Dto> proIndList;
    /**
     * 承压指标集合
     */
    private List<MarketRiskSceneManage002Dto> strIndList;
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
	 * 获取 suitProduct
	 * @return suitProduct.
	 */
	public String getSuitProduct() {
		return suitProduct;
	}

	/**
	 * 设置 suitProduct
	 * @param suitProduct suitProduct
	 */
	public void setSuitProduct(String suitProduct) {
		this.suitProduct = suitProduct;
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
	 * 获取 ftrTypeList
	 * @return ftrTypeList.
	 */
	public List<MarketRiskSceneFactorTypeDto> getFtrTypeList() {
		return ftrTypeList;
	}

	/**
	 * 设置 ftrTypeList
	 * @param ftrTypeList ftrTypeList
	 */
	public void setFtrTypeList(List<MarketRiskSceneFactorTypeDto> ftrTypeList) {
		this.ftrTypeList = ftrTypeList;
	}

	/**
	 * 获取 ftrInfoList
	 * @return ftrInfoList.
	 */
	public List<MarketRiskSceneManage003Dto> getFtrInfoList() {
		return ftrInfoList;
	}

	/**
	 * 设置 ftrInfoList
	 * @param ftrInfoList ftrInfoList
	 */
	public void setFtrInfoList(List<MarketRiskSceneManage003Dto> ftrInfoList) {
		this.ftrInfoList = ftrInfoList;
	}

	/**
     * 获取 proIndList
     * @return proIndList.
     */
    public List<MarketRiskSceneManage002Dto> getProIndList() {
        return proIndList;
    }
    
    /**
     * 设置 proIndList
     * @param proIndList proIndList
     */
    public void setProIndList(List<MarketRiskSceneManage002Dto> proIndList) {
        this.proIndList = proIndList;
    }
    
    /**
     * 获取 strIndList
     * @return strIndList.
     */
    public List<MarketRiskSceneManage002Dto> getStrIndList() {
        return strIndList;
    }
    
    /**
     * 设置 strIndList
     * @param strIndList strIndList
     */
    public void setStrIndList(List<MarketRiskSceneManage002Dto> strIndList) {
        this.strIndList = strIndList;
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
     * 获取 ftrTypeValue
     * @return ftrTypeValue.
     */
    public String getFtrTypeValue() {
        return ftrTypeValue;
    }

    
    /**
     * 设置 ftrTypeValue
     * @param ftrTypeValue ftrTypeValue
     */
    public void setFtrTypeValue(String ftrTypeValue) {
        this.ftrTypeValue = ftrTypeValue;
    }

    /**
     * 获取 startDate
     * @return startDate.
     */
    public Date getStartDate() {
        return startDate;
    }

    
    /**
     * 设置 startDate
     * @param startDate startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    
    /**
     * 获取 endDate
     * @return endDate.
     */
    public Date getEndDate() {
        return endDate;
    }

    
    /**
     * 设置 endDate
     * @param endDate endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    
}
