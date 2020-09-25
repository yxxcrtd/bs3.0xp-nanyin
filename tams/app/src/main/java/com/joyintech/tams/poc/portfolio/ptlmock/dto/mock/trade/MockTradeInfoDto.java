/*
 * 文件名：MockTradeInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2020年4月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.trade;

import java.util.List;

/**
 * 金融产品交易查询-组合代码主方法返回值 封装资产端交易列表、负债端交易列表、实物类交易列表
 * 
 * @author etliu
 * @version 1.0 2020年4月14日
 * @see MockTradeInfoDto
 * @since 1.0
 */
public class MockTradeInfoDto {

    /**
     * 资产端交易列表
     */
    private List<MockTrdProductDealDto> assetTradeList;

    /**
     * 负债端交易列表
     */
    private List<MockTrdProductDealDto> liabilitiesTradeList;

//    /**
//     * 实物交易列表
//     */
//    private List<MockTrdProductDealDto> barterTradeList;

    /**
     * 资产端T+N交易列表
     */
    private List<MockTrdProductDealDto> assetTdayPlusNdayTradeList;

    /**
     * 负债端T+N交易列表
     */
    private List<MockTrdProductDealDto> liabilitiesTdayPlusNdayTradeList;

    /**
     * 费用交易列表
     */
    private List<MockTrdProductDealDto> feeTradeList;

    /**
     * 排序准则
     */
    private String acctStandard;
    /**
     * 业务类型
     */
    private String busiType;

    /**
     * 获取 assetTradeList
     * 
     * @return assetTradeList.
     */
    public List<MockTrdProductDealDto> getAssetTradeList() {
        return assetTradeList;
    }

    /**
     * 设置 assetTradeList
     * 
     * @param assetTradeList assetTradeList
     */
    public void setAssetTradeList(List<MockTrdProductDealDto> assetTradeList) {
        this.assetTradeList = assetTradeList;
    }

    /**
     * 获取 liabilitiesTradeList
     * 
     * @return liabilitiesTradeList.
     */
    public List<MockTrdProductDealDto> getLiabilitiesTradeList() {
        return liabilitiesTradeList;
    }

    /**
     * 设置 liabilitiesTradeList
     * 
     * @param liabilitiesTradeList liabilitiesTradeList
     */
    public void setLiabilitiesTradeList(List<MockTrdProductDealDto> liabilitiesTradeList) {
        this.liabilitiesTradeList = liabilitiesTradeList;
    }

//    /**
//     * 获取 barterTradeList
//     * 
//     * @return barterTradeList.
//     */
//    public List<MockTrdProductDealDto> getBarterTradeList() {
//        return barterTradeList;
//    }
//
//    /**
//     * 设置 barterTradeList
//     * 
//     * @param barterTradeList barterTradeList
//     */
//    public void setBarterTradeList(List<MockTrdProductDealDto> barterTradeList) {
//        this.barterTradeList = barterTradeList;
//    }

    /**
     * 获取 assetTdayPlusNdayTradeList
     * 
     * @return assetTdayPlusNdayTradeList.
     */
    public List<MockTrdProductDealDto> getAssetTdayPlusNdayTradeList() {
        return assetTdayPlusNdayTradeList;
    }

    /**
     * 设置 assetTdayPlusNdayTradeList
     * 
     * @param assetTdayPlusNdayTradeList assetTdayPlusNdayTradeList
     */
    public void setAssetTdayPlusNdayTradeList(List<MockTrdProductDealDto> assetTdayPlusNdayTradeList) {
        this.assetTdayPlusNdayTradeList = assetTdayPlusNdayTradeList;
    }

    /**
     * 获取 liabilitiesTdayPlusNdayTradeList
     * 
     * @return liabilitiesTdayPlusNdayTradeList.
     */
    public List<MockTrdProductDealDto> getLiabilitiesTdayPlusNdayTradeList() {
        return liabilitiesTdayPlusNdayTradeList;
    }

    /**
     * 设置 liabilitiesTdayPlusNdayTradeList
     * 
     * @param liabilitiesTdayPlusNdayTradeList liabilitiesTdayPlusNdayTradeList
     */
    public void setLiabilitiesTdayPlusNdayTradeList(List<MockTrdProductDealDto> liabilitiesTdayPlusNdayTradeList) {
        this.liabilitiesTdayPlusNdayTradeList = liabilitiesTdayPlusNdayTradeList;
    }

    /**
     * 获取 feeTradeList
     * 
     * @return feeTradeList.
     */
    public List<MockTrdProductDealDto> getFeeTradeList() {
        return feeTradeList;
    }

    /**
     * 设置 feeTradeList
     * 
     * @param feeTradeList feeTradeList
     */
    public void setFeeTradeList(List<MockTrdProductDealDto> feeTradeList) {
        this.feeTradeList = feeTradeList;
    }

    public String getAcctStandard() {
        return acctStandard;
    }

    public void setAcctStandard(String acctStandard) {
        this.acctStandard = acctStandard;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }
}
