package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ValtblImportDateTrace extends BaseEntity {
    /**
     * 主键
     */
    private String uuid;

    /**
     * 委外组合代码
     */
    private String portfolioId;

    /**
     * 估值日期
     */
    private Date valdate;

    /**
     * 平台日
     */
    private Date impdate;

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
     * 取得 委外组合代码
     * @return 委外组合代码
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 委外组合代码
     * @param portfolioId 委外组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }

    /**
     * 取得 估值日期
     * @return 估值日期
     */
    public Date getValdate() {
        return valdate;
    }

    /**
     * 设置 估值日期
     * @param valdate 估值日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setValdate(Date valdate) {
        this.valdate = valdate;
    }

    /**
     * 取得 平台日
     * @return 平台日
     */
    public Date getImpdate() {
        return impdate;
    }

    /**
     * 设置 平台日
     * @param impdate 平台日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setImpdate(Date impdate) {
        this.impdate = impdate;
    }
}