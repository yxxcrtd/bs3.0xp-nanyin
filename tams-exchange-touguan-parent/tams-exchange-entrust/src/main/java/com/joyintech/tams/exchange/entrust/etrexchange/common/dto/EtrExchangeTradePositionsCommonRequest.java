/*
 * 文件名：EtrExchangeTradeQuotation001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2020年7月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.exchange.entrust.etrexchange.common.dto;

import com.joyintech.tams.business.common.dto.PageMode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 *
 * 〈功能详细描述〉
 * @author wangyang1
 * @version 1.0 2021年12月21日
 * @see EtrExchangeTradePositionsCommonRequest
 * @since 1.0
 */
@ApiModel(value="持仓信息查询请求参数",description="持仓信息查询请求参数")
public class EtrExchangeTradePositionsCommonRequest {


//    @ApiModelProperty(name="search",value="关键字",example="A1")
//    private String search;

//    @ApiModelProperty(name="portfolioId",value="组合代码",example="A1")
//    private String portfolioId;
    @ApiModelProperty(name="portfolioName",value="组合名称",example="A1")
    private String portfolioName;

    @ApiModelProperty(name="pageMode",value="分页对象",example="{pageSize: 10,pageNo: 1, sort: 'name', direction: 'asc'}")
    private PageMode pageMode;

    @ApiModelProperty(name="tradeMarket",value="交易场所",example="A1")
    private String tradeMarket;

    @ApiModelProperty(name="secAcctId",value="证券账户",example="A1")
    private String secAcctId;

    @ApiModelProperty(name="finprodName",value="资产名称",example="A1")
    private String finprodName;

    @ApiModelProperty(name="secId",value="证券代码",example="A1")
    private String secId;

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    //    @ApiModelProperty(name="finprodType2",value="金融产品类型",example="A1")
//    private String finprodType2;


//    public List<String> getPortfolioIdList() {
//        return portfolioIdList;
//    }
//
//    public void setPortfolioIdList(List<String> portfolioIdList) {
//        this.portfolioIdList = portfolioIdList;
//    }
//
//    public String getPortfolioId() {
//        return portfolioId;
//    }
//
//    public void setPortfolioId(String portfolioId) {
//        this.portfolioId = portfolioId;
//    }

    public PageMode getPageMode() {
        return pageMode;
    }

    public void setPageMode(PageMode pageMode) {
        this.pageMode = pageMode;
    }

    public String getSecAcctId() {
        return secAcctId;
    }

    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public String getSecId() {
        return secId;
    }

    public void setSecId(String secId) {
        this.secId = secId;
    }

//    public String getSearch() {
//        return search;
//    }
//
//    public void setSearch(String search) {
//        this.search = search;
//    }

//    public String getFinprodType2() {
//        return finprodType2;
//    }
//
//    public void setFinprodType2(String finprodType2) {
//        this.finprodType2 = finprodType2;
//    }
}
