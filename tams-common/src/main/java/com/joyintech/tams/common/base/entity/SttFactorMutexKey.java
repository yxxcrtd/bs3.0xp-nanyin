package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttFactorMutexKey extends BaseEntity {
    /**
     * 因子代码
     */
    private String ftrId;

    /**
     * 互斥代码
     */
    private String mutexCode;

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
     * 取得 互斥代码
     * @return 互斥代码
     */
    public String getMutexCode() {
        return mutexCode;
    }

    /**
     * 设置 互斥代码
     * @param mutexCode 互斥代码
     */
    public void setMutexCode(String mutexCode) {
        this.mutexCode = mutexCode == null ? null : mutexCode.trim();
    }
}