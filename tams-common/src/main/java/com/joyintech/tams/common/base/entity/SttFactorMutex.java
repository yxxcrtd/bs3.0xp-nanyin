package com.joyintech.tams.common.base.entity;

public class SttFactorMutex extends SttFactorMutexKey {
    /**
     * 值
     */
    private String mutexValue;

    /**
     * 取得 值
     * @return 值
     */
    public String getMutexValue() {
        return mutexValue;
    }

    /**
     * 设置 值
     * @param mutexValue 值
     */
    public void setMutexValue(String mutexValue) {
        this.mutexValue = mutexValue == null ? null : mutexValue.trim();
    }
}