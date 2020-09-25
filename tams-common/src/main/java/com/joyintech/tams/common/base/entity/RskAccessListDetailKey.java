package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class RskAccessListDetailKey extends BaseEntity {
    /**
     * 名单代码
     */
    private String listId;

    /**
     * 名单日期
     */
    private Date listDate;

    /**
     * 名单主体名称
     */
    private String listDetailName;

    /**
     * 取得 名单代码
     * @return 名单代码
     */
    public String getListId() {
        return listId;
    }

    /**
     * 设置 名单代码
     * @param listId 名单代码
     */
    public void setListId(String listId) {
        this.listId = listId == null ? null : listId.trim();
    }

    /**
     * 取得 名单日期
     * @return 名单日期
     */
    public Date getListDate() {
        return listDate;
    }

    /**
     * 设置 名单日期
     * @param listDate 名单日期
     */
    public void setListDate(Date listDate) {
        this.listDate = listDate;
    }

    /**
     * 取得 名单主体名称
     * @return 名单主体名称
     */
    public String getListDetailName() {
        return listDetailName;
    }

    /**
     * 设置 名单主体名称
     * @param listDetailName 名单主体名称
     */
    public void setListDetailName(String listDetailName) {
        this.listDetailName = listDetailName == null ? null : listDetailName.trim();
    }
}