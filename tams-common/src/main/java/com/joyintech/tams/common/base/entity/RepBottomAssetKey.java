package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class RepBottomAssetKey extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String repFinprodId;

    /**
     * 对应资管及委外资产行内资产/负债编码
     */
    private String repAssetCode;

    /**
     * 持仓日期
     */
    private Date repPositionDate;

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
     * 取得 对应资管及委外资产行内资产/负债编码
     * @return 对应资管及委外资产行内资产/负债编码
     */
    public String getRepAssetCode() {
        return repAssetCode;
    }

    /**
     * 设置 对应资管及委外资产行内资产/负债编码
     * @param repAssetCode 对应资管及委外资产行内资产/负债编码
     */
    public void setRepAssetCode(String repAssetCode) {
        this.repAssetCode = repAssetCode == null ? null : repAssetCode.trim();
    }

    /**
     * 取得 持仓日期
     * @return 持仓日期
     */
    public Date getRepPositionDate() {
        return repPositionDate;
    }

    /**
     * 设置 持仓日期
     * @param repPositionDate 持仓日期
     */
    public void setRepPositionDate(Date repPositionDate) {
        this.repPositionDate = repPositionDate;
    }
}