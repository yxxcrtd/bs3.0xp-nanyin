package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ValInterfDefine extends BaseEntity {
    /**
     * 接口代码
     */
    private String interfId;

    /**
     * 接口中文名称
     */
    private String interfCName;

    /**
     * 接口英文名称
     */
    private String interfEName;

    /**
     * 接口执行批次
     */
    private Long orderno;

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

    /**
     * 取得 接口中文名称
     * @return 接口中文名称
     */
    public String getInterfCName() {
        return interfCName;
    }

    /**
     * 设置 接口中文名称
     * @param interfCName 接口中文名称
     */
    public void setInterfCName(String interfCName) {
        this.interfCName = interfCName == null ? null : interfCName.trim();
    }

    /**
     * 取得 接口英文名称
     * @return 接口英文名称
     */
    public String getInterfEName() {
        return interfEName;
    }

    /**
     * 设置 接口英文名称
     * @param interfEName 接口英文名称
     */
    public void setInterfEName(String interfEName) {
        this.interfEName = interfEName == null ? null : interfEName.trim();
    }

    /**
     * 取得 接口执行批次
     * @return 接口执行批次
     */
    public Long getOrderno() {
        return orderno;
    }

    /**
     * 设置 接口执行批次
     * @param orderno 接口执行批次
     */
    public void setOrderno(Long orderno) {
        this.orderno = orderno;
    }
}