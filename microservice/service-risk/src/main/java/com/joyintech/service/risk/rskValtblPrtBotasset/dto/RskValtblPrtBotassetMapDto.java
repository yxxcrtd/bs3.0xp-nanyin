/*
 * 文件名：RskValtblPrtBotassetMapDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.rskValtblPrtBotasset.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.RskValtblPrtBotassetMap;

/**
 *  估值表底层资产映射DTO
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月3日
 * @see RskValtblPrtBotassetMapDto
 * @since 1.0
 */
public class RskValtblPrtBotassetMapDto extends RskValtblPrtBotassetMap{

    /**
     * 操作类型
     */
    private String operateType;
    
    private String objectId;
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    private String finprodType2;
    
    /**
     * 金融产品简称
     */
    private String finprodAbbr;
    
    
    /**
     * 市场代码，可以按规则生成，或者手输，不校验唯一。对于理财产品分层，同母金融产品代码对应的市场代码。
     */
    private String finprodMarketId;
    
    /**
     * 交易场所
     */
    private String tradeMarket;
    
    /**
     * 是否新增
     */
    private String isAdd;
    
    private List<RskValtblPrtBotassetMapDto> selectedItem;

    
    

    
    
    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<RskValtblPrtBotassetMapDto> getSelectedItem() {
        return selectedItem;
    }



    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<RskValtblPrtBotassetMapDto> selectedItem) {
        this.selectedItem = selectedItem;
    }



    /**
     * 获取 finprodAbbr
     * @return finprodAbbr.
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }


    
    /**
     * 设置 finprodAbbr
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }


    
    /**
     * 获取 finprodMarketId
     * @return finprodMarketId.
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }


    
    /**
     * 设置 finprodMarketId
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }


    
    /**
     * 获取 tradeMarket
     * @return tradeMarket.
     */
    public String getTradeMarket() {
        return tradeMarket;
    }


    
    /**
     * 设置 tradeMarket
     * @param tradeMarket tradeMarket
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }




    
    /**
     * 获取 finprodType2
     * @return finprodType2.
     */
    public String getFinprodType2() {
        return finprodType2;
    }

    
    /**
     * 设置 finprodType2
     * @param finprodType2 finprodType2
     */
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }




    
    /**
     * 获取 objectId
     * @return objectId.
     */
    public String getObjectId() {
        return objectId;
    }




    
    /**
     * 设置 objectId
     * @param objectId objectId
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }




    
    /**
     * 获取 isAdd
     * @return isAdd.
     */
    public String getIsAdd() {
        return isAdd;
    }




    
    /**
     * 设置 isAdd
     * @param isAdd isAdd
     */
    public void setIsAdd(String isAdd) {
        this.isAdd = isAdd;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
}
