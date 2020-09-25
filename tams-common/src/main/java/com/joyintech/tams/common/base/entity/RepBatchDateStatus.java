package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class RepBatchDateStatus extends BaseEntity {
    /**
     * 流水
     */
    private String id;

    /**
     * 上游主键
     */
    private String sourid;

    /**
     * 产品编号
     */
    private Date prodid;

    /**
     * 取得 流水
     * @return 流水
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 流水
     * @param id 流水
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 取得 上游主键
     * @return 上游主键
     */
    public String getSourid() {
        return sourid;
    }

    /**
     * 设置 上游主键
     * @param sourid 上游主键
     */
    public void setSourid(String sourid) {
        this.sourid = sourid == null ? null : sourid.trim();
    }

    /**
     * 取得 产品编号
     * @return 产品编号
     */
    public Date getProdid() {
        return prodid;
    }

    /**
     * 设置 产品编号
     * @param prodid 产品编号
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setProdid(Date prodid) {
        this.prodid = prodid;
    }
}