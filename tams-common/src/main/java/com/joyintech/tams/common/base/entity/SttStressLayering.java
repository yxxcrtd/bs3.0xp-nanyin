package com.joyintech.tams.common.base.entity;

public class SttStressLayering extends SttStressLayeringKey {
    /**
     * 参数代码
     */
    private String paramCode;

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
}