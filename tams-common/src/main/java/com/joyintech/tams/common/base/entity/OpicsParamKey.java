package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class OpicsParamKey extends BaseEntity {
    /**
     * 项目代码
     */
    private String itemId;

    /**
     * 参数类型，包括计息规则、BRANCK、BROK、COST、DEALTYPE、PORT、业务类型、VERIND
     */
    private String paraType;

    /**
     * 取得 项目代码
     * @return 项目代码
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * 设置 项目代码
     * @param itemId 项目代码
     */
    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    /**
     * 取得 参数类型，包括计息规则、BRANCK、BROK、COST、DEALTYPE、PORT、业务类型、VERIND
     * @return 参数类型，包括计息规则、BRANCK、BROK、COST、DEALTYPE、PORT、业务类型、VERIND
     */
    public String getParaType() {
        return paraType;
    }

    /**
     * 设置 参数类型，包括计息规则、BRANCK、BROK、COST、DEALTYPE、PORT、业务类型、VERIND
     * @param paraType 参数类型，包括计息规则、BRANCK、BROK、COST、DEALTYPE、PORT、业务类型、VERIND
     */
    public void setParaType(String paraType) {
        this.paraType = paraType == null ? null : paraType.trim();
    }
}