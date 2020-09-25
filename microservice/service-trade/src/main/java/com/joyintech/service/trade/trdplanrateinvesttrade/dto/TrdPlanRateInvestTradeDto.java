/*
 * 文件名：TrdPlanRateInvestTradeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年11月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.trdplanrateinvesttrade.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.WflTrdProductDeal;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author fanshuanghu
 * @version 1.0 2018年11月22日
 * @see TrdPlanRateInvestTradeDto
 * @since 1.0
 */
public class TrdPlanRateInvestTradeDto extends WflTrdProductDeal{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -2412110903235770405L;
    
    /**
     * 委托代码
     */
    private String entrustId;
    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 金融产品类型
     */
    private String type1;
   /* 原因、zhuyongkang 2019/7/30  start */
    /**
     * 资产二级类型
     */
    private String type9;
    /**
     * 资产三级类型
     */
    private String type10;
    /**
     * 资产四级类型
     */
    private String type11;
    /**
     * 获取 type9
     * @return type9.
     */
    public String getType9() {
        return type9;
    }
    /**
     * 设置 type9
     * @param type9 type9
     */
    public void setType9(String type9) {
        this.type9 = type9;
    }
    /**
     * 获取 type10
     * @return type10.
     */
    public String getType10() {
        return type10;
    }
    /**
     * 设置 type10
     * @param type10 type10
     */
    public void setType10(String type10) {
        this.type10 = type10;
    }
    /**
     * 获取 type11
     * @return type11.
     */
    public String getType11() {
        return type11;
    }
    /**
     * 设置 type11
     * @param type11 type11
     */
    public void setType11(String type11) {
        this.type11 = type11;
    }
   /* 原因、zhuyongkang 2019/7/30   end*/
    /**
     * 金融产品名称
     */
    private String finprodName;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * elementId
     */
    private String elementId;
    /**
     * 市场机构代码
     */
    private String finprodMarketId;
    /**
     * 划款指令表
     */
    private List<StlPayOrdersDto> stlPayOrdersList;
    /**
     * 组合表
     */
    private List<PtlTrade> ptlTrade;
    /**
     * 金融产品交易扩展
     */
    private PaTrdProductDeal paTrdProductDeal;
    /**
     * 金融产品交易实体类
     */
    private BaseTradeProductDealDto baseTradeProductDealDto;
    
    /**
     * 获取 entrustId
     * @return entrustId.
     */
    public String getEntrustId() {
        return entrustId;
    }
    
    /**
     * 设置 entrustId
     * @param entrustId entrustId
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }
    
    /**
     * 获取 portfolioId
     * @return portfolioId.
     */
    public String getPortfolioId() {
        return portfolioId;
    }
    
    /**
     * 设置 portfolioId
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }
    
    /**
     * 获取 portfolioName
     * @return portfolioName.
     */
    public String getPortfolioName() {
        return portfolioName;
    }
    
    /**
     * 设置 portfolioName
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }
    
    /**
     * 获取 type1
     * @return type1.
     */
    public String getType1() {
        return type1;
    }
    
    /**
     * 设置 type1
     * @param type1 type1
     */
    public void setType1(String type1) {
        this.type1 = type1;
    }
    
    /**
     * 获取 finprodName
     * @return finprodName.
     */
    public String getFinprodName() {
        return finprodName;
    }
    
    /**
     * 设置 finprodName
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }
    
    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }
    
    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
    
    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }
    
    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
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
     * 获取 stlPayOrdersList
     * @return stlPayOrdersList.
     */
    public List<StlPayOrdersDto> getStlPayOrdersList() {
        return stlPayOrdersList;
    }
    
    /**
     * 设置 stlPayOrdersList
     * @param stlPayOrdersList stlPayOrdersList
     */
    public void setStlPayOrdersList(List<StlPayOrdersDto> stlPayOrdersList) {
        this.stlPayOrdersList = stlPayOrdersList;
    }
    
    /**
     * 获取 ptlTrade
     * @return ptlTrade.
     */
    public List<PtlTrade> getPtlTrade() {
        return ptlTrade;
    }
    
    /**
     * 设置 ptlTrade
     * @param ptlTrade ptlTrade
     */
    public void setPtlTrade(List<PtlTrade> ptlTrade) {
        this.ptlTrade = ptlTrade;
    }
    
    /**
     * 获取 paTrdProductDeal
     * @return paTrdProductDeal.
     */
    public PaTrdProductDeal getPaTrdProductDeal() {
        return paTrdProductDeal;
    }
    
    /**
     * 设置 paTrdProductDeal
     * @param paTrdProductDeal paTrdProductDeal
     */
    public void setPaTrdProductDeal(PaTrdProductDeal paTrdProductDeal) {
        this.paTrdProductDeal = paTrdProductDeal;
    }
    
    /**
     * 获取 baseTradeProductDealDto
     * @return baseTradeProductDealDto.
     */
    public BaseTradeProductDealDto getBaseTradeProductDealDto() {
        return baseTradeProductDealDto;
    }
    
    /**
     * 设置 baseTradeProductDealDto
     * @param baseTradeProductDealDto baseTradeProductDealDto
     */
    public void setBaseTradeProductDealDto(BaseTradeProductDealDto baseTradeProductDealDto) {
        this.baseTradeProductDealDto = baseTradeProductDealDto;
    }

}
