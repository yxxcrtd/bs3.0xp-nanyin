package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokSchemeConfig extends BaseEntity {
    /**
     * 配置项代码
     */
    private String configId;

    /**
     * 方案类型
     */
    private String schemeType;

    /**
     * 方案二级类型
     */
    private String schemeType2;

    /**
     * 配置项名称
     */
    private String configName;

    /**
     * 配置项说明
     */
    private String remark;

    /**
     * 取值数据字典
     */
    private String dictCode;

    /**
     * 配置项类型
     */
    private String configType;

    /**
     * 优先级
     */
    private String sortNo;

    /**
     * 取得 配置项代码
     * @return 配置项代码
     */
    public String getConfigId() {
        return configId;
    }

    /**
     * 设置 配置项代码
     * @param configId 配置项代码
     */
    public void setConfigId(String configId) {
        this.configId = configId == null ? null : configId.trim();
    }

    /**
     * 取得 方案类型
     * @return 方案类型
     */
    public String getSchemeType() {
        return schemeType;
    }

    /**
     * 设置 方案类型
     * @param schemeType 方案类型
     */
    public void setSchemeType(String schemeType) {
        this.schemeType = schemeType == null ? null : schemeType.trim();
    }

    /**
     * 取得 方案二级类型
     * @return 方案二级类型
     */
    public String getSchemeType2() {
        return schemeType2;
    }

    /**
     * 设置 方案二级类型
     * @param schemeType2 方案二级类型
     */
    public void setSchemeType2(String schemeType2) {
        this.schemeType2 = schemeType2 == null ? null : schemeType2.trim();
    }

    /**
     * 取得 配置项名称
     * @return 配置项名称
     */
    public String getConfigName() {
        return configName;
    }

    /**
     * 设置 配置项名称
     * @param configName 配置项名称
     */
    public void setConfigName(String configName) {
        this.configName = configName == null ? null : configName.trim();
    }

    /**
     * 取得 配置项说明
     * @return 配置项说明
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 配置项说明
     * @param remark 配置项说明
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 取值数据字典
     * @return 取值数据字典
     */
    public String getDictCode() {
        return dictCode;
    }

    /**
     * 设置 取值数据字典
     * @param dictCode 取值数据字典
     */
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode == null ? null : dictCode.trim();
    }

    /**
     * 取得 配置项类型
     * @return 配置项类型
     */
    public String getConfigType() {
        return configType;
    }

    /**
     * 设置 配置项类型
     * @param configType 配置项类型
     */
    public void setConfigType(String configType) {
        this.configType = configType == null ? null : configType.trim();
    }

    /**
     * 取得 优先级
     * @return 优先级
     */
    public String getSortNo() {
        return sortNo;
    }

    /**
     * 设置 优先级
     * @param sortNo 优先级
     */
    public void setSortNo(String sortNo) {
        this.sortNo = sortNo == null ? null : sortNo.trim();
    }
}