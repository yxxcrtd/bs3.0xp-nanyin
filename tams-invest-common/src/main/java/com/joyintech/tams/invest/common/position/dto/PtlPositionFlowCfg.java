package com.joyintech.tams.invest.common.position.dto;

import java.util.Map;

public class PtlPositionFlowCfg extends PtlPositionFlowCfgKey {
    /**
     * 流水类型
     */
    private String flowType;

    /**
     * 处理类型
     */
    private String processType;

    /**
     * 类名和方法
     */
    private String classMethod;

    /**
     * 处理配置
     */
    private String processConfig;

    /**
     * 参数设置
     */
    private String parameterConfig;

    /**
     * 配置说明
     */
    private String configExplain;
    
    /**
     * 配置说明
     */
    private Map<String,Object> dataMap;

    /**
     * 取得 流水类型
     * @return 流水类型
     */
    public String getFlowType() {
        return flowType;
    }

    /**
     * 设置 流水类型
     * @param flowType 流水类型
     */
    public void setFlowType(String flowType) {
        this.flowType = flowType == null ? null : flowType.trim();
    }

    /**
     * 取得 处理类型
     * @return 处理类型
     */
    public String getProcessType() {
        return processType;
    }

    /**
     * 设置 处理类型
     * @param processType 处理类型
     */
    public void setProcessType(String processType) {
        this.processType = processType == null ? null : processType.trim();
    }

    /**
     * 取得 类名和方法
     * @return 类名和方法
     */
    public String getClassMethod() {
        return classMethod;
    }

    /**
     * 设置 类名和方法
     * @param classMethod 类名和方法
     */
    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod == null ? null : classMethod.trim();
    }

    /**
     * 取得 处理配置
     * @return 处理配置
     */
    public String getProcessConfig() {
        return processConfig;
    }

    /**
     * 设置 处理配置
     * @param processConfig 处理配置
     */
    public void setProcessConfig(String processConfig) {
        this.processConfig = processConfig == null ? null : processConfig.trim();
    }

    /**
     * 取得 参数设置
     * @return 参数设置
     */
    public String getParameterConfig() {
        return parameterConfig;
    }

    /**
     * 设置 参数设置
     * @param parameterConfig 参数设置
     */
    public void setParameterConfig(String parameterConfig) {
        this.parameterConfig = parameterConfig == null ? null : parameterConfig.trim();
    }

    /**
     * 取得 配置说明
     * @return 配置说明
     */
    public String getConfigExplain() {
        return configExplain;
    }

    /**
     * 设置 配置说明
     * @param configExplain 配置说明
     */
    public void setConfigExplain(String configExplain) {
        this.configExplain = configExplain == null ? null : configExplain.trim();
    }

	public Map<String,Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String,Object> dataMap) {
		this.dataMap = dataMap;
	}
}