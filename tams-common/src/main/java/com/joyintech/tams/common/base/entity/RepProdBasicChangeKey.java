package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class RepProdBasicChangeKey extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 指数code
     */
    private String dataType;

    /**
     * 取得 产品代码
     * @return 产品代码
     */
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
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 生效日
     * @param effDate 生效日
     */
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
}