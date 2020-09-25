package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttSceneFtrtips extends BaseEntity {
    /**
     * 情景因子代码
     */
    private String sceneFtrtipsId;
    
    /**
     * 情景因子代码
     */
    private String sceneFtrId;

    /**
     * 情景代码
     */
    private String sceneId;

    /**
     * 参数代码
     */
    private String paramCode;

    /**
     * 参数值
     */
    private String paramValue;

    /**
     * 级别
     */
    private Long level;

    /**
     * 字典代码
     */
    private String dictCode;

    /**
     * 取得 情景因子代码
     * @return 情景因子代码
     */
    public String getSceneFtrtipsId() {
        return sceneFtrtipsId;
    }

    /**
     * 设置 情景因子代码
     * @param sceneFtrtipsId 情景因子代码
     */
    public void setSceneFtrtipsId(String sceneFtrtipsId) {
        this.sceneFtrtipsId = sceneFtrtipsId == null ? null : sceneFtrtipsId.trim();
    }

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
     * 取得 参数代码
     * @return 参数代码
     */
    public String getParamCode() {
        return paramCode;
    }

    /**
     * 设置 参数代码
     * @param paramCode 参数代码
     */
    public void setParamCode(String paramCode) {
        this.paramCode = paramCode == null ? null : paramCode.trim();
    }

    /**
     * 取得 参数值
     * @return 参数值
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * 设置 参数值
     * @param paramValue 参数值
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue == null ? null : paramValue.trim();
    }

    /**
     * 取得 级别
     * @return 级别
     */
    public Long getLevel() {
        return level;
    }

    /**
     * 设置 级别
     * @param level 级别
     */
    public void setLevel(Long level) {
        this.level = level;
    }

    /**
     * 取得 字典代码
     * @return 字典代码
     */
    public String getDictCode() {
        return dictCode;
    }

    /**
     * 设置 字典代码
     * @param dictCode 字典代码
     */
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode == null ? null : dictCode.trim();
    }

	public String getSceneFtrId() {
		return sceneFtrId;
	}

	public void setSceneFtrId(String sceneFtrId) {
		this.sceneFtrId = sceneFtrId;
	}
    
    
}