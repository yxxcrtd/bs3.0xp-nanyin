package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttCalcModel extends BaseEntity {
    /**
     * 模型代码
     */
    private String modelId;

    /**
     * 模型名称
     */
    private String modelName;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 是否启用
     */
    private String isEnalbe;

    /**
     * 取得 模型代码
     * @return 模型代码
     */
    public String getModelId() {
        return modelId;
    }

    /**
     * 设置 模型代码
     * @param modelId 模型代码
     */
    public void setModelId(String modelId) {
        this.modelId = modelId == null ? null : modelId.trim();
    }

    /**
     * 取得 模型名称
     * @return 模型名称
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * 设置 模型名称
     * @param modelName 模型名称
     */
    public void setModelName(String modelName) {
        this.modelName = modelName == null ? null : modelName.trim();
    }

    /**
     * 取得 文件名
     * @return 文件名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置 文件名
     * @param fileName 文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * 取得 方法名
     * @return 方法名
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * 设置 方法名
     * @param methodName 方法名
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    /**
     * 取得 是否启用
     * @return 是否启用
     */
    public String getIsEnalbe() {
        return isEnalbe;
    }

    /**
     * 设置 是否启用
     * @param isEnalbe 是否启用
     */
    public void setIsEnalbe(String isEnalbe) {
        this.isEnalbe = isEnalbe == null ? null : isEnalbe.trim();
    }
}