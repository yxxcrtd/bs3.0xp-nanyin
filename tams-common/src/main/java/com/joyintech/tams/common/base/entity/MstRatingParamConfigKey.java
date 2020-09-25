package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class MstRatingParamConfigKey extends BaseEntity {
    /**
     * 评级对象类型
     */
    private String oType;

    /**
     * 参数代码
     */
    private String paramCode;

    /**
     * 取得 评级对象类型
     * @return 评级对象类型
     */
    public String getoType() {
        return oType;
    }

    /**
     * 设置 评级对象类型
     * @param oType 评级对象类型
     */
    public void setoType(String oType) {
        this.oType = oType == null ? null : oType.trim();
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
}