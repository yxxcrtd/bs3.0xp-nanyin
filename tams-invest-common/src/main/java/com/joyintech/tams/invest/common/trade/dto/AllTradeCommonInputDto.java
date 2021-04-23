/*
 * 文件名：AllTradeCommonInputDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年1月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.trade.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.*;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;

/**
 * 所有交易都要调用的共通Util用参数dto
 *
 * @author etliu
 * @version 1.0 2019年1月16日
 * @see AllTradeCommonInputDto
 * @since 1.0
 */
public class AllTradeCommonInputDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -9071350683004259851L;

    /**
     * 产品代码
     */
    private String prodid;

    /**
     * 子组合代码
     */
    private String actPortfolioId;

    /**
     * 产品类型(F16、F18、F22)：见数据字典：金融产品类型
     */
    private String prodFinprodType;

    /**
     * 子产品起息日(产品类型为F22时为必输项)
     */
    private Date prodVdate;

    /**
     * 交易信息Dto(TrdProductDeal)
     */
    private TrdProductDeal trdProductDeal;
    /**
     * 交易信息List
     */
    private List<TrdProductDeal> trdProductDealList;

    /**
     * 组合交易表List
     */
    private List<PtlTrade> ptlTradeList;

    /**
     * 汇总交易编号
     */
    private String totleTradeId;
    
    /**
     * 回购类型：质押式协议回购，质押式竞价回购
     */
    
    private String repoType;
    
    /**
     * 划款指令
     */
    private StlPayOrders stlPayOrders;

    /**反向交易用 start by qiangchengxi*/
    private List<BaseTradeProductDealDto> baseTradeProductDealDtoList;

    /**应收款转出反向交易用*/
    private List<BaseTradeProductDealDto> baseReceTradeProductDealDtoList;

    /**
     * 交易附属表
     */
    private TrdProductDealAdd trdProductDealAdd;

    /**
     * 交易附属表
     */
    private TrdTradeFeeDetail trdTradeFeeDetail;
    
    /**
     * 费用明细list
     */
    private List<TrdTradeFeeDetail> ListTrdTradeFeeDetail;
    /**
     * 金融产品实物交易表
     */
    private TrdFinProductBarter trdFinProductBarter;

    /**
     * 是否转仓交易
     */
    private String isSwapTransaction;
    /**
     * 不自动生成反向交易
     */
    private String isNotAutoGenerateReverseDeal;

    /**
     * 画面id（反向交易调接口用）
     */
    private String pageId;
    /**ADD BY WUJIE ON 20210129 START REMARK:回购到期区分 到期、到期续作、逾期贩售**/
    private String psType;//回购交易方向：到期续作、逾期贩售
    
    public String getPsType() {
		return psType;
	}

	public void setPsType(String psType) {
		this.psType = psType;
	}

	/**ADD BY WUJIE ON 20210129 END**/
    /**
     * 获取 pageId
     * 
     * @return pageId.
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * 设置 pageId
     * 
     * @param pageId pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    /**
     * 获取 isSwapTransaction
     * 
     * @return isSwapTransaction.
     */
    public String getIsSwapTransaction() {
        return isSwapTransaction;
    }

    /**
     * 设置 isSwapTransaction
     * 
     * @param isSwapTransaction isSwapTransaction
     */
    public void setIsSwapTransaction(String isSwapTransaction) {
        this.isSwapTransaction = isSwapTransaction;
    }

    /**
     * 获取 trdProductDealAdd
     *
     * @return trdProductDealAdd
     */
    public TrdProductDealAdd getTrdProductDealAdd() {
        return trdProductDealAdd;
    }

    /**
     * 设置 trdProductDealAdd
     *
     * @param trdProductDealAdd trdProductDealAdd
     */
    public void setTrdProductDealAdd(TrdProductDealAdd trdProductDealAdd) {
        this.trdProductDealAdd = trdProductDealAdd;
    }

    /**
     * 获取 trdTradeFeeDetail
     *
     * @return trdTradeFeeDetail
     */
    public TrdTradeFeeDetail getTrdTradeFeeDetail() {
        return trdTradeFeeDetail;
    }

    /**
     * 设置 trdTradeFeeDetail
     *
     * @param trdTradeFeeDetail trdTradeFeeDetail
     */
    public void setTrdTradeFeeDetail(TrdTradeFeeDetail trdTradeFeeDetail) {
        this.trdTradeFeeDetail = trdTradeFeeDetail;
    }

    /**
     * 获取 baseTradeProductDealDtoList
     *
     * @return baseTradeProductDealDtoList
     */
    public List<BaseTradeProductDealDto> getBaseTradeProductDealDtoList() {
        return baseTradeProductDealDtoList;
    }

    /**
     * 设置 baseTradeProductDealDtoList
     *
     * @param baseTradeProductDealDtoList baseTradeProductDealDtoList
     */
    public void setBaseTradeProductDealDtoList(List<BaseTradeProductDealDto> baseTradeProductDealDtoList) {
        this.baseTradeProductDealDtoList = baseTradeProductDealDtoList;
    }
    /**反向交易用 end by qiangchengxi*/

    /**
     * 获取 repoType
     * 
     * @return repoType.
     */
    public String getRepoType() {
		return repoType;
	}

    /**
     * 设置 repoType
     * 
     * @param repoType repoType
     */
	public void setRepoType(String repoType) {
		this.repoType = repoType;
	}

	/**
     * 获取 prodid
     * 
     * @return prodid.
     */
    public String getProdid() {
        return prodid;
    }

    /**
     * 设置 prodid
     * 
     * @param prodid prodid
     */
    public void setProdid(String prodid) {
        this.prodid = prodid;
    }

    /**
     * 获取 prodFinprodType
     * 
     * @return prodFinprodType.
     */
    public String getProdFinprodType() {
        return prodFinprodType;
    }

    /**
     * 设置 prodFinprodType
     * 
     * @param prodFinprodType prodFinprodType
     */
    public void setProdFinprodType(String prodFinprodType) {
        this.prodFinprodType = prodFinprodType;
    }

    /**
     * 获取 prodVdate
     * 
     * @return prodVdate.
     */
    public Date getProdVdate() {
        return prodVdate;
    }

    /**
     * 设置 prodVdate
     * 
     * @param prodVdate prodVdate
     */
    public void setProdVdate(Date prodVdate) {
        this.prodVdate = prodVdate;
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
     * @param trdProductDeal trdProductDeal
     */
    public void setTrdProductDeal(TrdProductDeal trdProductDeal) {
        this.trdProductDeal = trdProductDeal;
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
     * 获取 totleTradeId
     * 
     * @return totleTradeId.
     */
    public String getTotleTradeId() {
        return totleTradeId;
    }

    /**
     * 设置 totleTradeId
     * 
     * @param totleTradeId totleTradeId
     */
    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId;
    }

    /**
     * 获取 stlPayOrders
     * @return stlPayOrders.
     */
    public StlPayOrders getStlPayOrders() {
        return stlPayOrders;
    }

    /**
     * 设置 stlPayOrders
     * @param stlPayOrders stlPayOrders
     */
    public void setStlPayOrders(StlPayOrders stlPayOrders) {
        this.stlPayOrders = stlPayOrders;
    }

    
    /**
     * 获取 listTrdTradeFeeDetail
     * @return listTrdTradeFeeDetail.
     */
    public List<TrdTradeFeeDetail> getListTrdTradeFeeDetail() {
        return ListTrdTradeFeeDetail;
    }

    
    /**
     * 设置 listTrdTradeFeeDetail
     * @param listTrdTradeFeeDetail listTrdTradeFeeDetail
     */
    public void setListTrdTradeFeeDetail(List<TrdTradeFeeDetail> listTrdTradeFeeDetail) {
        ListTrdTradeFeeDetail = listTrdTradeFeeDetail;
    }

    /**
     * 获取 actPortfolioId
     * @return actPortfolioId.
     */
    public String getActPortfolioId() {
        return actPortfolioId;
    }

    /**
     * 设置 actPortfolioId
     * @param actPortfolioId actPortfolioId
     */
    public void setActPortfolioId(String actPortfolioId) {
        this.actPortfolioId = actPortfolioId;
    }

    /**
     * 获取 ptlTradeList
     *
     * @return ptlTradeList
     */
    public List<PtlTrade> getPtlTradeList() {
        return ptlTradeList;
    }

    /**
     * 设置 ptlTradeList
     *
     * @param ptlTradeList ptlTradeList
     */
    public void setPtlTradeList(List<PtlTrade> ptlTradeList) {
        this.ptlTradeList = ptlTradeList;
    }

    /**
     * 获取 金融产品实物交易表
     *
     * @return trdFinProductBarter 金融产品实物交易表
     */
    public TrdFinProductBarter getTrdFinProductBarter() {
        return this.trdFinProductBarter;
    }

    /**
     * 设置 金融产品实物交易表
     *
     * @param trdFinProductBarter 金融产品实物交易表
     */
    public void setTrdFinProductBarter(TrdFinProductBarter trdFinProductBarter) {
        this.trdFinProductBarter = trdFinProductBarter;
    }

    /**
     * 获取 baseReceTradeProductDealDtoList
     *
     * @return baseReceTradeProductDealDtoList
     */
    public List<BaseTradeProductDealDto> getBaseReceTradeProductDealDtoList() {
        return baseReceTradeProductDealDtoList;
    }

    /**
     * 设置 baseReceTradeProductDealDtoList
     *
     * @param baseReceTradeProductDealDtoList baseReceTradeProductDealDtoList
     */
    public void setBaseReceTradeProductDealDtoList(List<BaseTradeProductDealDto> baseReceTradeProductDealDtoList) {
        this.baseReceTradeProductDealDtoList = baseReceTradeProductDealDtoList;
    }


    public String turnInvAim;


    /**
     * 获取 turnInvAim
     * @return turnInvAim.
     */
    public String getTurnInvAim() {
        return turnInvAim;
    }


    /**
     * 设置 turnInvAim
     * @param turnInvAim turnInvAim
     */
    public void setTurnInvAim(String turnInvAim) {
        this.turnInvAim = turnInvAim;
    }

    /**
     * 获取 不自动生成反向交易
     *
     * @return isNotAutoGenerateReverseDeal 不自动生成反向交易
     */
    public String getIsNotAutoGenerateReverseDeal() {
        return this.isNotAutoGenerateReverseDeal;
    }

    /**
     * 设置 不自动生成反向交易
     *
     * @param isNotAutoGenerateReverseDeal 不自动生成反向交易
     */
    public void setIsNotAutoGenerateReverseDeal(String isNotAutoGenerateReverseDeal) {
        this.isNotAutoGenerateReverseDeal = isNotAutoGenerateReverseDeal;
    }
}
