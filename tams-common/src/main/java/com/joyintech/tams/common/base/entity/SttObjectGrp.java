package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttObjectGrp extends BaseEntity {
    /**
     * 对象组代码
     */
    private String objectGrpId;

    /**
     * 对象组名称
     */
    private String objectGrpName;

    /**
     * 组成方式，数据字典【对象组成方式】
     */
    private String constituteType;

    /**
     * 取得 对象组代码
     * @return 对象组代码
     */
    public String getObjectGrpId() {
        return objectGrpId;
    }

    /**
     * 设置 对象组代码
     * @param objectGrpId 对象组代码
     */
    public void setObjectGrpId(String objectGrpId) {
        this.objectGrpId = objectGrpId == null ? null : objectGrpId.trim();
    }

    /**
     * 取得 对象组名称
     * @return 对象组名称
     */
    public String getObjectGrpName() {
        return objectGrpName;
    }

    /**
     * 设置 对象组名称
     * @param objectGrpName 对象组名称
     */
    public void setObjectGrpName(String objectGrpName) {
        this.objectGrpName = objectGrpName == null ? null : objectGrpName.trim();
    }

    /**
     * 取得 组成方式，数据字典【对象组成方式】
     * @return 组成方式，数据字典【对象组成方式】
     */
    public String getConstituteType() {
        return constituteType;
    }

    /**
     * 设置 组成方式，数据字典【对象组成方式】
     * @param constituteType 组成方式，数据字典【对象组成方式】
     */
    public void setConstituteType(String constituteType) {
        this.constituteType = constituteType == null ? null : constituteType.trim();
    }
}