/*
 * 文件名：BaseTrdDurDbInertDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.dto.period.common;

import java.util.List;

import com.joyintech.tams.common.base.entity.PaTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.RepTrade;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.WflPaTrdFinDealTotal;
import com.joyintech.tams.common.base.entity.WflStlPayOrders;
import com.joyintech.tams.common.base.entity.WflTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.WflTrdProductDeal;
import com.joyintech.tams.common.base.entity.WflTrdProductDealAdd;

/**
 * 资产存续期要登录的流程数据
 *
 * @author etliu
 * @version 1.0 2018年12月14日
 * @see BaseTrdDurDbInertDto
 * @since 1.0
 */
public class BaseTrdDurDbInertDto {

    /**
     * 金融产品交易汇总业务流程表
     */
    private WflTrdFinProductDealTotal wflTrdFinProductDealTotal;

    /**
     * 划款指令业务流程表
     */
    private List<WflStlPayOrders> wflStlPayOrdersList;

    /**
     * 金融产品交易汇总扩展业务流程表
     */
    private WflPaTrdFinDealTotal wflPaTrdFinDealTotal;

    /**
     * 金融产品交易业务流程表
     */
    private List<WflTrdProductDeal> wflTrdProductDealList;

    /**
     * 交易附属
     */
    private List<WflTrdProductDealAdd> wflTrdProductDealAdds;

    /**
     * 组合交易
     */
    private List<PtlTrade> ptlTrades;

    /**
     * 交易报送
     */
    private List<RepTrade> repTrades;

    /**
     * 金融产品交易汇总表
     */
    private TrdFinProductDealTotal trdFinProductDealTotal;

    /**
     * 划款指令表
     */
    private List<StlPayOrders> stlPayOrdersList;

    /**
     * 金融产品交易汇总扩展表
     */
    private PaTrdFinProductDealTotal paTrdFinProductDealTotal;

    /**
     * 金融产品交易业务流程表
     */
    private List<TrdProductDeal> trdProductDealList;

    /**
     * 交易附属
     */
    private List<TrdProductDealAdd> trdProductDealAdds;

    /**
     * 实体组合代码
     */
    private String actPortfolioId;

    /**
     * 获取 trdFinProductDealTotal
     * 
     * @return trdFinProductDealTotal.
     */
    public TrdFinProductDealTotal getTrdFinProductDealTotal() {
        return trdFinProductDealTotal;
    }

    /**
     * 设置 trdFinProductDealTotal
     * 
     * @param trdFinProductDealTotal trdFinProductDealTotal
     */
    public void setTrdFinProductDealTotal(TrdFinProductDealTotal trdFinProductDealTotal) {
        this.trdFinProductDealTotal = trdFinProductDealTotal;
    }

    /**
     * 获取 stlPayOrdersList
     * 
     * @return stlPayOrdersList.
     */
    public List<StlPayOrders> getStlPayOrdersList() {
        return stlPayOrdersList;
    }

    /**
     * 设置 stlPayOrdersList
     * 
     * @param stlPayOrdersList stlPayOrdersList
     */
    public void setStlPayOrdersList(List<StlPayOrders> stlPayOrdersList) {
        this.stlPayOrdersList = stlPayOrdersList;
    }

    /**
     * 获取 paTrdFinProductDealTotal
     * 
     * @return paTrdFinProductDealTotal.
     */
    public PaTrdFinProductDealTotal getPaTrdFinProductDealTotal() {
        return paTrdFinProductDealTotal;
    }

    /**
     * 设置 paTrdFinProductDealTotal
     * 
     * @param paTrdFinProductDealTotal paTrdFinProductDealTotal
     */
    public void setPaTrdFinProductDealTotal(PaTrdFinProductDealTotal paTrdFinProductDealTotal) {
        this.paTrdFinProductDealTotal = paTrdFinProductDealTotal;
    }

    /**
     * 获取 trdProductDealList
     * 
     * @return trdProductDealList.
     */
    public List<TrdProductDeal> getTrdProductDealList() {
        return trdProductDealList;
    }

    /**
     * 设置 trdProductDealList
     * 
     * @param trdProductDealList trdProductDealList
     */
    public void setTrdProductDealList(List<TrdProductDeal> trdProductDealList) {
        this.trdProductDealList = trdProductDealList;
    }

    /**
     * 获取 trdProductDealAdds
     * 
     * @return trdProductDealAdds.
     */
    public List<TrdProductDealAdd> getTrdProductDealAdds() {
        return trdProductDealAdds;
    }

    /**
     * 设置 trdProductDealAdds
     * 
     * @param trdProductDealAdds trdProductDealAdds
     */
    public void setTrdProductDealAdds(List<TrdProductDealAdd> trdProductDealAdds) {
        this.trdProductDealAdds = trdProductDealAdds;
    }

    /**
     * 获取 wflTrdFinProductDealTotal
     *
     * @return wflTrdFinProductDealTotal
     */
    public WflTrdFinProductDealTotal getWflTrdFinProductDealTotal() {
        return wflTrdFinProductDealTotal;
    }

    /**
     * 设置 wflTrdFinProductDealTotal
     *
     * @param wflTrdFinProductDealTotal wflTrdFinProductDealTotal
     */
    public void setWflTrdFinProductDealTotal(WflTrdFinProductDealTotal wflTrdFinProductDealTotal) {
        this.wflTrdFinProductDealTotal = wflTrdFinProductDealTotal;
    }

    /**
     * 获取 wflStlPayOrdersList
     *
     * @return wflStlPayOrdersList
     */
    public List<WflStlPayOrders> getWflStlPayOrdersList() {
        return wflStlPayOrdersList;
    }

    /**
     * 设置 wflStlPayOrdersList
     *
     * @param wflStlPayOrdersList wflStlPayOrdersList
     */
    public void setWflStlPayOrdersList(List<WflStlPayOrders> wflStlPayOrdersList) {
        this.wflStlPayOrdersList = wflStlPayOrdersList;
    }

    /**
     * 获取 wflPaTrdFinDealTotal
     *
     * @return wflPaTrdFinDealTotal
     */
    public WflPaTrdFinDealTotal getWflPaTrdFinDealTotal() {
        return wflPaTrdFinDealTotal;
    }

    /**
     * 设置 wflPaTrdFinDealTotal
     *
     * @param wflPaTrdFinDealTotal wflPaTrdFinDealTotal
     */
    public void setWflPaTrdFinDealTotal(WflPaTrdFinDealTotal wflPaTrdFinDealTotal) {
        this.wflPaTrdFinDealTotal = wflPaTrdFinDealTotal;
    }

    /**
     * 获取 wflTrdProductDealList
     *
     * @return wflTrdProductDealList
     */
    public List<WflTrdProductDeal> getWflTrdProductDealList() {
        return wflTrdProductDealList;
    }

    /**
     * 设置 wflTrdProductDealList
     *
     * @param wflTrdProductDealList wflTrdProductDealList
     */
    public void setWflTrdProductDealList(List<WflTrdProductDeal> wflTrdProductDealList) {
        this.wflTrdProductDealList = wflTrdProductDealList;
    }

    /**
     * 获取 ptlTrades
     *
     * @return ptlTrades
     */
    public List<PtlTrade> getPtlTrades() {
        return ptlTrades;
    }

    /**
     * 设置 ptlTrades
     *
     * @param ptlTrades ptlTrades
     */
    public void setPtlTrades(List<PtlTrade> ptlTrades) {
        this.ptlTrades = ptlTrades;
    }

    /**
     * 获取 repTrades
     *
     * @return repTrades
     */
    public List<RepTrade> getRepTrades() {
        return repTrades;
    }

    /**
     * 设置 repTrades
     *
     * @param repTrades repTrades
     */
    public void setRepTrades(List<RepTrade> repTrades) {
        this.repTrades = repTrades;
    }

    /**
     * 获取 wflTrdProductDealAdds
     *
     * @return wflTrdProductDealAdds
     */
    public List<WflTrdProductDealAdd> getWflTrdProductDealAdds() {
        return wflTrdProductDealAdds;
    }

    /**
     * 设置 wflTrdProductDealAdds
     *
     * @param wflTrdProductDealAdds wflTrdProductDealAdds
     */
    public void setWflTrdProductDealAdds(List<WflTrdProductDealAdd> wflTrdProductDealAdds) {
        this.wflTrdProductDealAdds = wflTrdProductDealAdds;
    }

    /**
     * 获取 actPortfolioId
     * 
     * @return actPortfolioId.
     */
    public String getActPortfolioId() {
        return actPortfolioId;
    }

    /**
     * 设置 actPortfolioId
     * 
     * @param actPortfolioId actPortfolioId
     */
    public void setActPortfolioId(String actPortfolioId) {
        this.actPortfolioId = actPortfolioId;
    }
}
