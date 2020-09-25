package com.joyintech.tams.invest.period.trdprojbuyoff.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.Rate;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.period.trdprojbuyoff.TrdProjectBuyOffInterestDto;

/**
 * @program: tams-invest-parent
 * @description: 买断利率查询dto
 * @author: 强成西
 * @create: 2018-10-23 14:26
 **/
public class TrdProjectBuyOffInterest001Dto extends TrdProjectBuyOffInterestDto {

    private List<TrdProjectBuyOffInterest001Dto> selectedData;

    @NotEmpty(fieldName = "TrdProjectBuyOffInterest002.assetId")
    public String getAssetId() {
        return super.getAssetId();
    }

    @Rate(fieldName = "TrdProjectBuyOffInterest002.rate")
    @NotEmpty(fieldName = "TrdProjectBuyOffInterest002.rate")
    public BigDecimal getRate() {
        return super.getRate();
    }

    @NotEmpty(fieldName = "TrdProjectBuyOffInterest002.effDate")
    public String getEffDate() {
        return super.getEffDate();
    }

    /**
     * 获取 assetTypeString
     *
     * @return assetTypeString
     */
    public String getAssetTypeString() {
        return SysDataUtil.getNameFromDataDict(DictCode.项目类一级分类, this.getAssetType());
    }

    /**
     * 获取 selectedData
     *
     * @return selectedData
     */
    public List<TrdProjectBuyOffInterest001Dto> getSelectedData() {
        return selectedData;
    }

    /**
     * 设置 selectedData
     *
     * @param selectedData selectedData
     */
    public void setSelectedData(List<TrdProjectBuyOffInterest001Dto> selectedData) {
        this.selectedData = selectedData;
    }
}
