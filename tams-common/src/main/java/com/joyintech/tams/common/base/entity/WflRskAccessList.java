package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class WflRskAccessList extends WflRskAccessListKey {
    /**
     * 名单名称
     */
    private String listName;

    /**
     * 名单类型，白名单、黑名单
     */
    private String listType;

    /**
     * 名单主体类型，机构名单、资产名单
     */
    private String listDetailType;

    /**
     * 生效日期
     */
    private Date effdate;

    /**
     * 失效日期
     */
    private Date deldate;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 名单名称
     * @return 名单名称
     */
    public String getListName() {
        return listName;
    }

    /**
     * 设置 名单名称
     * @param listName 名单名称
     */
    public void setListName(String listName) {
        this.listName = listName == null ? null : listName.trim();
    }

    /**
     * 取得 名单类型，白名单、黑名单
     * @return 名单类型，白名单、黑名单
     */
    public String getListType() {
        return listType;
    }

    /**
     * 设置 名单类型，白名单、黑名单
     * @param listType 名单类型，白名单、黑名单
     */
    public void setListType(String listType) {
        this.listType = listType == null ? null : listType.trim();
    }

    /**
     * 取得 名单主体类型，机构名单、资产名单
     * @return 名单主体类型，机构名单、资产名单
     */
    public String getListDetailType() {
        return listDetailType;
    }

    /**
     * 设置 名单主体类型，机构名单、资产名单
     * @param listDetailType 名单主体类型，机构名单、资产名单
     */
    public void setListDetailType(String listDetailType) {
        this.listDetailType = listDetailType == null ? null : listDetailType.trim();
    }

    /**
     * 取得 生效日期
     * @return 生效日期
     */
    public Date getEffdate() {
        return effdate;
    }

    /**
     * 设置 生效日期
     * @param effdate 生效日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEffdate(Date effdate) {
        this.effdate = effdate;
    }

    /**
     * 取得 失效日期
     * @return 失效日期
     */
    public Date getDeldate() {
        return deldate;
    }

    /**
     * 设置 失效日期
     * @param deldate 失效日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setDeldate(Date deldate) {
        this.deldate = deldate;
    }

    /**
     * 取得 工作流代码
     * @return 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}