package com.joyintech.tams.invest.common.autoperiod.basePtlRecePosition.dto;

import com.joyintech.tams.common.base.entity.PtlRecePositionFlow;
import com.joyintech.tams.common.base.entity.TrdFinProductBarter;
import com.joyintech.tams.common.base.entity.TrdProductDeal;

import java.util.List;

/**
 * 应收款持仓流水输入DTO
 */
public class PtlRecePositionsFlowInputDto extends PtlRecePositionFlow {
    /**
     * 持仓类型
     */
    private String positionType;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 分红方式
     */
    private String bonusMode;

    /**
     * 资产二级类型
     */
    private String assetType2;

    /**
     * 金融产品交易Entity
     */
    private TrdProductDeal trdProductDeal;
    /**
     * 金融产品实物交易表
     */
    private List<TrdFinProductBarter> trdFinProductBarterList;

    /**
     * 应收款类型
     */
   private String recType;
    /**
     * 获取 positionType
     *
     * @return positionType
     */
    public String getPositionType() {
        return positionType;
    }

    /**
     * 设置 positionType
     *
     * @param positionType positionType
     */
    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    /**
     * 获取 trdProductDeal
     *
     * @return trdProductDeal
     */
    public TrdProductDeal getTrdProductDeal() {
        return trdProductDeal;
    }

    /**
     * 设置 trdProductDeal
     *
     * @param trdProductDeal trdProductDeal
     */
    public void setTrdProductDeal(TrdProductDeal trdProductDeal) {
        this.trdProductDeal = trdProductDeal;
    }

    /**
     * 获取 portfolioId
     *
     * @return portfolioId
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 portfolioId
     *
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getBonusMode() {
        return bonusMode;
    }

    public void setBonusMode(String bonusMode) {
        this.bonusMode = bonusMode;
    }

    /**
     * 获取 assetType2
     *
     * @rturn assetType2
     */
    public String getAssetType2() {
        return this.assetType2;
    }

    /**
     * 设置 assetType2
     *
     * @param assetType2 assetType2
     */
    public void setAssetType2(String assetType2) {
        this.assetType2 = assetType2;
    }

    /**
     * 获取 金融产品实物交易表
     *
     * @return trdFinProductBarterList 金融产品实物交易表
     */
    public List<TrdFinProductBarter> getTrdFinProductBarterList() {
        return this.trdFinProductBarterList;
    }

    /**
     * 设置 金融产品实物交易表
     *
     * @param trdFinProductBarterList 金融产品实物交易表
     */
    public void setTrdFinProductBarterList(List<TrdFinProductBarter> trdFinProductBarterList) {
        this.trdFinProductBarterList = trdFinProductBarterList;
    }

    /**
     * 获取 recType
     *
     * @return recType
     */
    public String getRecType() {
        return recType;
    }

    /**
     * 设置 recType
     *
     * @param recType recType
     */
    public void setRecType(String recType) {
        this.recType = recType;
    }
}
