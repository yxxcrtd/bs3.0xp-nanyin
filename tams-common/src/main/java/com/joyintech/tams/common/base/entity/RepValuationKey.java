package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class RepValuationKey extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String repFinprodId;

    /**
     * 行内资产/负债编码
     */
    private String repAssetCode;

    /**
     * 估值日期
     */
    private Date repValuationDate;

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getRepFinprodId() {
        return repFinprodId;
    }

    /**
     * 设置 金融产品代码
     * @param repFinprodId 金融产品代码
     */
    public void setRepFinprodId(String repFinprodId) {
        this.repFinprodId = repFinprodId == null ? null : repFinprodId.trim();
    }

    /**
     * 取得 行内资产/负债编码
     * @return 行内资产/负债编码
     */
    public String getRepAssetCode() {
        return repAssetCode;
    }

    /**
     * 设置 行内资产/负债编码
     * @param repAssetCode 行内资产/负债编码
     */
    public void setRepAssetCode(String repAssetCode) {
        this.repAssetCode = repAssetCode == null ? null : repAssetCode.trim();
    }

    /**
     * 取得 估值日期
     * @return 估值日期
     */
    public Date getRepValuationDate() {
        return repValuationDate;
    }

    /**
     * 设置 估值日期
     * @param repValuationDate 估值日期
     */
    public void setRepValuationDate(Date repValuationDate) {
        this.repValuationDate = repValuationDate;
    }
}