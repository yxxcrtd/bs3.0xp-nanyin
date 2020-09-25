package com.joyintech.tams.invest.common.dto.period.trdexchangeduration;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.WflPaTrdProductDeal;
import com.joyintech.tams.common.base.entity.WflStlPayOrders;
import com.joyintech.tams.common.base.entity.WflTrdProductDeal;
import com.joyintech.tams.common.base.entity.WflTrdProductDealAdd;
import com.joyintech.tams.invest.common.dto.period.common.BaseTrdDurDbInertDto;

/**
 * 外汇远期掉期到期交易自动生成Dto
 *
 * @author renping
 * @version 1.0 2019年7月19日
 * @description: 数据库批量处理DTO
 **/
public class TrdExchangeDurDbInertDto extends BaseTrdDurDbInertDto {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 92132334154767876L;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 金融产品交易
     */
    private TrdProductDeal trdProductDeal;

    /**
     * 金融产品交易附属
     */
    private TrdProductDealAdd trdProductDealAdd;

    /**
     * 交易类金融产品
     */
    private FinTradeProduct finTradeProduct;

    /**
     * 组合交易表
     */
    private PtlTrade ptlTrade;

    /**
     * 金融产品交易扩展
     */
    private PaTrdProductDeal paTrdProductDeal;

    /**
     * 划款指令List
     */
    List<StlPayOrders> stlPayOrdersList;

    /**
     * 金融产品交易业务流程
     */
    private WflTrdProductDeal wflTrdProductDeal;

    /**
     * 金融产品交易附属业务流程
     */
    private WflTrdProductDealAdd wflTrdProductDealAdd;

    /**
     * 金融产品交易扩展业务流程
     */
    private WflPaTrdProductDeal wflPaTrdProductDeal;

    /**
     * 划款指令业务流程List
     */
    List<WflStlPayOrders> wflStlPayOrdersList;

    /**
     * 产品代码
     */
    private String finprodMarketName;

    /**
     * 交易编号
     */
    private String busiCode;

    /**
     * 分支号
     */
    private String branch;

    /**
     * 货币对
     */
    private String curPair;

    /**
     * 交易日期
     */
    private Date tradeDate;

    /**
     * 平安业务类型
     */
    private String paBussType;

    /**
     * 本方账户
     */
    private String dealAcct;

    /**
     * 对手方账户
     */
    private String corAcct;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 获取 portfolioId
     * 
     * @return portfolioId.
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

    public String getFinprodMarketName() {
        return finprodMarketName;
    }

    public void setFinprodMarketName(String finprodMarketName) {
        this.finprodMarketName = finprodMarketName;
    }

    public String getBusiCode() {
        return busiCode;
    }

    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    public String getPaBussType() {
        return paBussType;
    }

    public void setPaBussType(String paBussType) {
        this.paBussType = paBussType;
    }

    /**
     * 获取 trdProductDeal
     * 
     * @return trdProductDeal.
     */
    public TrdProductDeal getTrdProductDeal() {
        return trdProductDeal;
    }

    /**
     * 设置 trdProductDeal
     * 
     * @param trdProductDeal
     *            trdProductDeal
     */
    public void setTrdProductDeal(TrdProductDeal trdProductDeal) {
        this.trdProductDeal = trdProductDeal;
    }

    /**
     * 获取 trdProductDealAdd
     * 
     * @return trdProductDealAdd.
     */
    public TrdProductDealAdd getTrdProductDealAdd() {
        return trdProductDealAdd;
    }

    /**
     * 设置 trdProductDealAdd
     * 
     * @param trdProductDealAdd
     *            trdProductDealAdd
     */
    public void setTrdProductDealAdd(TrdProductDealAdd trdProductDealAdd) {
        this.trdProductDealAdd = trdProductDealAdd;
    }

    /**
     * 获取 finTradeProduct
     * 
     * @return finTradeProduct.
     */
    public FinTradeProduct getFinTradeProduct() {
        return finTradeProduct;
    }

    /**
     * 设置 finTradeProduct
     * 
     * @param finTradeProduct
     *            finTradeProduct
     */
    public void setFinTradeProduct(FinTradeProduct finTradeProduct) {
        this.finTradeProduct = finTradeProduct;
    }

    /**
     * 获取 branch
     * 
     * @return branch.
     */
    public String getBranch() {
        return branch;
    }

    /**
     * 设置 branch
     * 
     * @param branch
     *            branch
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * 获取 paTrdProductDeal
     * 
     * @return paTrdProductDeal.
     */
    public PaTrdProductDeal getPaTrdProductDeal() {
        return paTrdProductDeal;
    }

    /**
     * 设置 paTrdProductDeal
     * 
     * @param paTrdProductDeal
     *            paTrdProductDeal
     */
    public void setPaTrdProductDeal(PaTrdProductDeal paTrdProductDeal) {
        this.paTrdProductDeal = paTrdProductDeal;
    }

    /**
     * 获取 ptlTrade
     * 
     * @return ptlTrade.
     */
    public PtlTrade getPtlTrade() {
        return ptlTrade;
    }

    /**
     * 设置 ptlTrade
     * 
     * @param ptlTrade
     *            ptlTrade
     */
    public void setPtlTrade(PtlTrade ptlTrade) {
        this.ptlTrade = ptlTrade;
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
     * @param stlPayOrdersList
     *            stlPayOrdersList
     */
    public void setStlPayOrdersList(List<StlPayOrders> stlPayOrdersList) {
        this.stlPayOrdersList = stlPayOrdersList;
    }

    /**
     * 获取 curPair
     * 
     * @return curPair.
     */
    public String getCurPair() {
        return curPair;
    }

    /**
     * 设置 curPair
     * 
     * @param curPair
     *            curPair
     */
    public void setCurPair(String curPair) {
        this.curPair = curPair;
    }

    /**
     * 获取 tradeDate
     * 
     * @return tradeDate.
     */
    public Date getTradeDate() {
        return tradeDate;
    }

    /**
     * 设置 tradeDate
     * 
     * @param tradeDate
     *            tradeDate
     */
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * 获取 wflTrdProductDeal
     * 
     * @return wflTrdProductDeal.
     */
    public WflTrdProductDeal getWflTrdProductDeal() {
        return wflTrdProductDeal;
    }

    /**
     * 设置 wflTrdProductDeal
     * 
     * @param wflTrdProductDeal
     *            wflTrdProductDeal
     */
    public void setWflTrdProductDeal(WflTrdProductDeal wflTrdProductDeal) {
        this.wflTrdProductDeal = wflTrdProductDeal;
    }

    /**
     * 获取 wflTrdProductDealAdd
     * 
     * @return wflTrdProductDealAdd.
     */
    public WflTrdProductDealAdd getWflTrdProductDealAdd() {
        return wflTrdProductDealAdd;
    }

    /**
     * 设置 wflTrdProductDealAdd
     * 
     * @param wflTrdProductDealAdd
     *            wflTrdProductDealAdd
     */
    public void setWflTrdProductDealAdd(WflTrdProductDealAdd wflTrdProductDealAdd) {
        this.wflTrdProductDealAdd = wflTrdProductDealAdd;
    }

    /**
     * 获取 wflPaTrdProductDeal
     * 
     * @return wflPaTrdProductDeal.
     */
    public WflPaTrdProductDeal getWflPaTrdProductDeal() {
        return wflPaTrdProductDeal;
    }

    /**
     * 设置 wflPaTrdProductDeal
     * 
     * @param wflPaTrdProductDeal
     *            wflPaTrdProductDeal
     */
    public void setWflPaTrdProductDeal(WflPaTrdProductDeal wflPaTrdProductDeal) {
        this.wflPaTrdProductDeal = wflPaTrdProductDeal;
    }

    /**
     * 获取 wflStlPayOrdersList
     * 
     * @return wflStlPayOrdersList.
     */
    public List<WflStlPayOrders> getWflStlPayOrdersList() {
        return wflStlPayOrdersList;
    }

    /**
     * 设置 wflStlPayOrdersList
     * 
     * @param wflStlPayOrdersList
     *            wflStlPayOrdersList
     */
    public void setWflStlPayOrdersList(List<WflStlPayOrders> wflStlPayOrdersList) {
        this.wflStlPayOrdersList = wflStlPayOrdersList;
    }

    /**
     * 获取 dealAcct
     * 
     * @return dealAcct.
     */
    public String getDealAcct() {
        return dealAcct;
    }

    /**
     * 设置 dealAcct
     * 
     * @param dealAcct
     *            dealAcct
     */
    public void setDealAcct(String dealAcct) {
        this.dealAcct = dealAcct;
    }

    /**
     * 获取 corAcct
     * 
     * @return corAcct.
     */
    public String getCorAcct() {
        return corAcct;
    }

    /**
     * 设置 corAcct
     * 
     * @param corAcct
     *            corAcct
     */
    public void setCorAcct(String corAcct) {
        this.corAcct = corAcct;
    }

    /**
     * 获取 operateType
     * 
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     * 
     * @param operateType
     *            operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
}
