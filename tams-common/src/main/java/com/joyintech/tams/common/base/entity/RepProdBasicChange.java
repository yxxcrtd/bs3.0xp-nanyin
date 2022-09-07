package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.workflow.engine.annotation.PrimaryKey;

import java.util.Date;

public class RepProdBasicChange extends BaseEntity {
    /**
     * 主键
     */
    private String uuid;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 生效日
     */
    private Date effDate;
    private Date expDate;

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    /**
     * 指数code
     */
    private String dataType;

    /**
     * 指数权重：存储结构为a%
     */
    private String proportion;

    /**
     * 组ID
     */
    private String groupId;

    private String changeType;

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    /**
     * 取得 主键
     * @return 主键
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置 主键
     * @param uuid 主键
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    @NotEmpty(fieldName = "子产品代码",groups={ValidateGroupEdit.class})
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 生效日
     * @return 生效日
     */
    @NotEmpty(fieldName = "业绩比较基准生效日",groups={ValidateGroupEdit.class})
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 生效日
     * @param effDate 生效日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    /**
     * 取得 指数code
     * @return 指数code
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * 设置 指数code
     * @param dataType 指数code
     */
    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    /**
     * 取得 指数权重：存储结构为a%
     * @return 指数权重：存储结构为a%
     */
    public String getProportion() {
        return proportion;
    }

    /**
     * 设置 指数权重：存储结构为a%
     * @param proportion 指数权重：存储结构为a%
     */
    public void setProportion(String proportion) {
        this.proportion = proportion == null ? null : proportion.trim();
    }

    /**
     * 取得 组ID
     * @return 组ID
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置 组ID
     * @param groupId 组ID
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }
}