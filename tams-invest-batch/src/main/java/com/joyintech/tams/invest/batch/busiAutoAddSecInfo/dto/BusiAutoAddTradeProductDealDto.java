/*
 * 文件名：BaseTradeProductDealDto.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：huwenbin 修改时间：2018年9月4日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.busiAutoAddSecInfo.dto;

import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.common.dto.MstFileOutputParameter;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.BussRepoSecDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 金融产品交易信息实体 〈功能详细描述〉
 *
 * @author huwenbin
 * @version 1.0 2018年9月4日
 * @see BusiAutoAddTradeProductDealDto
 * @since 1.0
 */
public class BusiAutoAddTradeProductDealDto {

    /**
     * 金融产品汇总交易
     */
    private TrdFinProductDealTotal trdFinProductDealTotal;

    /**
     * 金融产品实物汇总交易
     */
    private TrdFinProductBarterTotal trdFinProductBarterTotal;

    /**
     * 金融产品汇总交易扩展表
     */
    private PaTrdFinProductDealTotal paTrdFinProductDealTotal;

    /**
     *
     */
    private TrdDealTotalAdd trdDealTotalAdd;

    private List<PtlTrade> ptlTrades;

    /**
     * 实体组合代码
     */
    private String actPortfolioId;

    private List<TrdProductDeal> trdProductDeals;
    /**
     * 金融产品交易附属
     */
    private List<TrdProductDealAdd> trdProductDealAdds;

    /**
     * 金融产品交易扩展
     */
    private List<PaTrdProductDeal> paTrdProductDeals;

    /**
     * 金融产品实物交易表
     */
    private List<TrdFinProductBarter> trdFinProductBarterList;

    public TrdFinProductDealTotal getTrdFinProductDealTotal() {
        return trdFinProductDealTotal;
    }

    public void setTrdFinProductDealTotal(TrdFinProductDealTotal trdFinProductDealTotal) {
        this.trdFinProductDealTotal = trdFinProductDealTotal;
    }

    public PaTrdFinProductDealTotal getPaTrdFinProductDealTotal() {
        return paTrdFinProductDealTotal;
    }

    public void setPaTrdFinProductDealTotal(PaTrdFinProductDealTotal paTrdFinProductDealTotal) {
        this.paTrdFinProductDealTotal = paTrdFinProductDealTotal;
    }

    public TrdDealTotalAdd getTrdDealTotalAdd() {
        return trdDealTotalAdd;
    }

    public void setTrdDealTotalAdd(TrdDealTotalAdd trdDealTotalAdd) {
        this.trdDealTotalAdd = trdDealTotalAdd;
    }

    public String getActPortfolioId() {
        return actPortfolioId;
    }

    public void setActPortfolioId(String actPortfolioId) {
        this.actPortfolioId = actPortfolioId;
    }

    public List<TrdProductDeal> getTrdProductDeals() {
        return trdProductDeals;
    }

    public void setTrdProductDeals(List<TrdProductDeal> trdProductDeals) {
        this.trdProductDeals = trdProductDeals;
    }

    public List<TrdProductDealAdd> getTrdProductDealAdds() {
        return trdProductDealAdds;
    }

    public void setTrdProductDealAdds(List<TrdProductDealAdd> trdProductDealAdds) {
        this.trdProductDealAdds = trdProductDealAdds;
    }

    public List<PaTrdProductDeal> getPaTrdProductDeals() {
        return paTrdProductDeals;
    }

    public void setPaTrdProductDeals(List<PaTrdProductDeal> paTrdProductDeals) {
        this.paTrdProductDeals = paTrdProductDeals;
    }

    public List<TrdFinProductBarter> getTrdFinProductBarterList() {
        return trdFinProductBarterList;
    }

    public void setTrdFinProductBarterList(List<TrdFinProductBarter> trdFinProductBarterList) {
        this.trdFinProductBarterList = trdFinProductBarterList;
    }

    public TrdFinProductBarterTotal getTrdFinProductBarterTotal() {
        return trdFinProductBarterTotal;
    }

    public void setTrdFinProductBarterTotal(TrdFinProductBarterTotal trdFinProductBarterTotal) {
        this.trdFinProductBarterTotal = trdFinProductBarterTotal;
    }

    public List<PtlTrade> getPtlTrades() {
        return ptlTrades;
    }

    public void setPtlTrades(List<PtlTrade> ptlTrades) {
        this.ptlTrades = ptlTrades;
    }
}
