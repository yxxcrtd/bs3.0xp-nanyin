package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class RepAssetDept extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String repFinprodId;

    /**
     * 市场代码，可以按规则生成，或者手输，不校验唯一
     */
    private String finprodMarketId;

    /**
     * 金融产品名称
     */
    private String repFinprodName;

    /**
     * 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     */
    private String repIssuePartyId;

    /**
     * 资产/负债类别
     */
    private String repAssetType;

    /**
     * 币种
     */
    private String repCcy;

    /**
     * 交易流通场所
     */
    private String repTradeMarket;

    /**
     * 报送状态
     */
    private String repSendStatus;

    /**
     * 数据日期
     */
    private Date repStatusDate;

    /**
     * 是否报送对象
     */
    private String isRepTarget;

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
     * 取得 市场代码，可以按规则生成，或者手输，不校验唯一
     * @return 市场代码，可以按规则生成，或者手输，不校验唯一
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    /**
     * 设置 市场代码，可以按规则生成，或者手输，不校验唯一
     * @param finprodMarketId 市场代码，可以按规则生成，或者手输，不校验唯一
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId == null ? null : finprodMarketId.trim();
    }

    /**
     * 取得 金融产品名称
     * @return 金融产品名称
     */
    public String getRepFinprodName() {
        return repFinprodName;
    }

    /**
     * 设置 金融产品名称
     * @param repFinprodName 金融产品名称
     */
    public void setRepFinprodName(String repFinprodName) {
        this.repFinprodName = repFinprodName == null ? null : repFinprodName.trim();
    }

    /**
     * 取得 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     * @return 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     */
    public String getRepIssuePartyId() {
        return repIssuePartyId;
    }

    /**
     * 设置 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     * @param repIssuePartyId 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     */
    public void setRepIssuePartyId(String repIssuePartyId) {
        this.repIssuePartyId = repIssuePartyId == null ? null : repIssuePartyId.trim();
    }

    /**
     * 取得 资产/负债类别
     * @return 资产/负债类别
     */
    public String getRepAssetType() {
        return repAssetType;
    }

    /**
     * 设置 资产/负债类别
     * @param repAssetType 资产/负债类别
     */
    public void setRepAssetType(String repAssetType) {
        this.repAssetType = repAssetType == null ? null : repAssetType.trim();
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getRepCcy() {
        return repCcy;
    }

    /**
     * 设置 币种
     * @param repCcy 币种
     */
    public void setRepCcy(String repCcy) {
        this.repCcy = repCcy == null ? null : repCcy.trim();
    }

    /**
     * 取得 交易流通场所
     * @return 交易流通场所
     */
    public String getRepTradeMarket() {
        return repTradeMarket;
    }

    /**
     * 设置 交易流通场所
     * @param repTradeMarket 交易流通场所
     */
    public void setRepTradeMarket(String repTradeMarket) {
        this.repTradeMarket = repTradeMarket == null ? null : repTradeMarket.trim();
    }

    /**
     * 取得 报送状态
     * @return 报送状态
     */
    public String getRepSendStatus() {
        return repSendStatus;
    }

    /**
     * 设置 报送状态
     * @param repSendStatus 报送状态
     */
    public void setRepSendStatus(String repSendStatus) {
        this.repSendStatus = repSendStatus == null ? null : repSendStatus.trim();
    }

    /**
     * 取得 数据日期
     * @return 数据日期
     */
    public Date getRepStatusDate() {
        return repStatusDate;
    }

    /**
     * 设置 数据日期
     * @param repStatusDate 数据日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepStatusDate(Date repStatusDate) {
        this.repStatusDate = repStatusDate;
    }

    /**
     * 取得 是否报送对象
     * @return 是否报送对象
     */
    public String getIsRepTarget() {
        return isRepTarget;
    }

    /**
     * 设置 是否报送对象
     * @param isRepTarget 是否报送对象
     */
    public void setIsRepTarget(String isRepTarget) {
        this.isRepTarget = isRepTarget == null ? null : isRepTarget.trim();
    }
}