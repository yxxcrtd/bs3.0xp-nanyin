package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ValInterfTemplateKey extends BaseEntity {
    /**
     * 模版英文名称
     */
    private String tmpEName;

    /**
     * 接口代码
     */
    private String interfId;

    /**
     * 取得 模版英文名称
     * @return 模版英文名称
     */
    public String getTmpEName() {
        return tmpEName;
    }

    /**
     * 设置 模版英文名称
     * @param tmpEName 模版英文名称
     */
    public void setTmpEName(String tmpEName) {
        this.tmpEName = tmpEName == null ? null : tmpEName.trim();
    }

    /**
     * 取得 接口代码
     * @return 接口代码
     */
    public String getInterfId() {
        return interfId;
    }

    /**
     * 设置 接口代码
     * @param interfId 接口代码
     */
    public void setInterfId(String interfId) {
        this.interfId = interfId == null ? null : interfId.trim();
    }
}