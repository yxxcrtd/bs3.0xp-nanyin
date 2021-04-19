package com.joyintech.tams.invest.common.autocoupon.dto;

import com.joyintech.tams.common.base.entity.InterfCFuturesDescription;

import com.joyintech.tams.common.base.entity.MstFuturesPrice;
import com.joyintech.tams.invest.common.autocoupon.dto.BaseFinProductBaseInfoDto;
import java.util.List;

public class InterfCFuturesDescriptionDto extends InterfCFuturesDescription {

    private List<MstFuturesPrice> mstFuturesPriceList;

    public List<MstFuturesPrice> getMstFuturesPriceList() {
        return mstFuturesPriceList;
    }

    public void setMstFuturesPriceList(List<MstFuturesPrice> mstFuturesPriceList) {
        this.mstFuturesPriceList = mstFuturesPriceList;
    }

    private String finprodId;

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    private List<String> infoSecidList;

    public List<String> getInfoSecidList() {
        return infoSecidList;
    }

    public void setInfoSecidList(List<String> infoSecidList) {
        this.infoSecidList = infoSecidList;
    }

    private String ccy;

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 金融产品信息List
     */
    private List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList;
    /**
     * 获取 baseFinProductBaseInfoList
     * @return baseFinProductBaseInfoList.
     */
    public List<BaseFinProductBaseInfoDto> getBaseFinProductBaseInfoList() {
        return baseFinProductBaseInfoList;
    }



    /**
     * 设置 baseFinProductBaseInfoList
     * @param baseFinProductBaseInfoList baseFinProductBaseInfoList
     */
    public void setBaseFinProductBaseInfoList(List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList) {
        this.baseFinProductBaseInfoList = baseFinProductBaseInfoList;
    }


    private String couponFlag;

    public String getCouponFlag() {
        return couponFlag;
    }

    public void setCouponFlag(String couponFlag) {
        this.couponFlag = couponFlag;
    }
}
