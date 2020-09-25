package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttFactorCalcStr extends BaseEntity {
    /**
     * 结构代码
     */
    private String strId;

    /**
     * 因子代码
     */
    private String ftrId;

    /**
     * 计算方式
     */
    private String calcType;

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
     * 取得 结构代码
     * @return 结构代码
     */
    public String getStrId() {
        return strId;
    }

    /**
     * 设置 结构代码
     * @param strId 结构代码
     */
    public void setStrId(String strId) {
        this.strId = strId == null ? null : strId.trim();
    }

    /**
     * 取得 因子代码
     * @return 因子代码
     */
    public String getFtrId() {
        return ftrId;
    }

    /**
     * 设置 因子代码
     * @param ftrId 因子代码
     */
    public void setFtrId(String ftrId) {
        this.ftrId = ftrId == null ? null : ftrId.trim();
    }

    /**
     * 取得 计算方式
     * @return 计算方式
     */
    public String getCalcType() {
        return calcType;
    }

    /**
     * 设置 计算方式
     * @param calcType 计算方式
     */
    public void setCalcType(String calcType) {
        this.calcType = calcType == null ? null : calcType.trim();
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