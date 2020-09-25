package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSysDbInfo extends BaseEntity {
    /**
     * 是否锁定 参照数据字典（是否）
     */
    private String isLock;

    /**
     * 取得 是否锁定 参照数据字典（是否）
     * @return 是否锁定 参照数据字典（是否）
     */
    public String getIsLock() {
        return isLock;
    }

    /**
     * 设置 是否锁定 参照数据字典（是否）
     * @param isLock 是否锁定 参照数据字典（是否）
     */
    public void setIsLock(String isLock) {
        this.isLock = isLock == null ? null : isLock.trim();
    }
}