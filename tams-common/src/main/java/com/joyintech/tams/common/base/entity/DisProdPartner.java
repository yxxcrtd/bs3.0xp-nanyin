package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.workflow.engine.annotation.PrimaryKey;

import java.util.Date;

public class DisProdPartner extends BaseEntity {
    /**
     * 合作机构主键
     */
    @PrimaryKey(value = PrimaryKey.Field.PK_1)
    private String prodPartenerId;

    /**
     * 主产品代码
     */
    private String prodId;

    /**
     * 合作机构名称
     */
    private String partnerName;

    /**
     * 生效日期
     */
    private Date effDate;

    /**
     * 生效截至日
     */
    private Date effEndDate;

    /**
     * 数据状态
     */
    private String dataStatus;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 取得 合作机构主键
     * @return 合作机构主键
     */
    public String getProdPartenerId() {
        return prodPartenerId;
    }

    /**
     * 设置 合作机构主键
     * @param prodPartenerId 合作机构主键
     */
    public void setProdPartenerId(String prodPartenerId) {
        this.prodPartenerId = prodPartenerId == null ? null : prodPartenerId.trim();
    }

    /**
     * 取得 主产品代码
     * @return 主产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 主产品代码
     * @param prodId 主产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 合作机构名称
     * @return 合作机构名称
     */
    public String getPartnerName() {
        return partnerName;
    }

    /**
     * 设置 合作机构名称
     * @param partnerName 合作机构名称
     */
    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName == null ? null : partnerName.trim();
    }

    /**
     * 取得 生效日期
     * @return 生效日期
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 生效日期
     * @param effDate 生效日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    /**
     * 取得 生效截至日
     * @return 生效截至日
     */
    public Date getEffEndDate() {
        return effEndDate;
    }

    /**
     * 设置 生效截至日
     * @param effEndDate 生效截至日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEffEndDate(Date effEndDate) {
        this.effEndDate = effEndDate;
    }

    /**
     * 取得 数据状态
     * @return 数据状态
     */
    public String getDataStatus() {
        return dataStatus;
    }

    /**
     * 设置 数据状态
     * @param dataStatus 数据状态
     */
    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus == null ? null : dataStatus.trim();
    }

    /**
     * 取得 数据来源
     * @return 数据来源
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 设置 数据来源
     * @param dataSource 数据来源
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }
}