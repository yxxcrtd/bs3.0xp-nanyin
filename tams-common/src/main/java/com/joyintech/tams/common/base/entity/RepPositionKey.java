package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class RepPositionKey extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String repFinprodId;

    /**
     * 行内资产/负债编码

     */
    private String repBankAssetCode;

    /**
     * 日期
     */
    private Date repDate;

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
    public String getRepBankAssetCode() {
        return repBankAssetCode;
    }

    /**
     * 设置 行内资产/负债编码

     * @param repBankAssetCode 行内资产/负债编码

     */
    public void setRepBankAssetCode(String repBankAssetCode) {
        this.repBankAssetCode = repBankAssetCode == null ? null : repBankAssetCode.trim();
    }

    /**
     * 取得 日期
     * @return 日期
     */
    public Date getRepDate() {
        return repDate;
    }

    /**
     * 设置 日期
     * @param repDate 日期
     */
    public void setRepDate(Date repDate) {
        this.repDate = repDate;
    }
}