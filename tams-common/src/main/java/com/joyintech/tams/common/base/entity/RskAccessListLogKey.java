package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class RskAccessListLogKey extends BaseEntity {
    /**
     * 名单代码
     */
    private String listId;

    /**
     * 操作日期
     */
    private Date listDate;

    /**
     * 名单主体名称，白名单、黑名单
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
     * 取得 操作日期
     * @return 操作日期
     */
    public Date getListDate() {
        return listDate;
    }

    /**
     * 设置 操作日期
     * @param listDate 操作日期
     */
    public void setListDate(Date listDate) {
        this.listDate = listDate;
    }

    /**
     * 取得 名单主体名称，白名单、黑名单
     * @return 名单主体名称，白名单、黑名单
     */
    public String getListDetailName() {
        return listDetailName;
    }

    /**
     * 设置 名单主体名称，白名单、黑名单
     * @param listDetailName 名单主体名称，白名单、黑名单
     */
    public void setListDetailName(String listDetailName) {
        this.listDetailName = listDetailName == null ? null : listDetailName.trim();
    }
}