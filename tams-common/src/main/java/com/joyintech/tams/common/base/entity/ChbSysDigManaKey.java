package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSysDigManaKey extends BaseEntity {
    /**
     * 类型 参照数据字典
     */
    private String type;

    /**
     * 币种 参照数据字典
     */
    private String ccy;

    /**
     * 取得 类型 参照数据字典
     * @return 类型 参照数据字典
     */
    public String getType() {
        return type;
    }

    /**
     * 设置 类型 参照数据字典
     * @param type 类型 参照数据字典
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 取得 币种 参照数据字典
     * @return 币种 参照数据字典
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种 参照数据字典
     * @param ccy 币种 参照数据字典
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }
}