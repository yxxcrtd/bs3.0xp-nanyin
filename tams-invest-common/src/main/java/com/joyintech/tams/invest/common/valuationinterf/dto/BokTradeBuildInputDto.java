/*
 * 文件名：BokTradeBuildInputDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年1月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.valuationinterf.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.*;
import com.joyintech.framework.base.BaseEntity;

/**
 * 交易入账 输入参数
 *
 * @author etliu
 * @version 1.0 2019年1月13日
 * @see BokTradeBuildInputDto
 * @since 1.0
 */
public class BokTradeBuildInputDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 9081658842864015949L;

    /**
     * 产品代码
     */
    private String prodid;

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
     * 汇总交易编号
     */
    private String totleTradeId;

    /**
     * 划款指令
     */
    private StlPayOrders stlPayOrders;
    

    /**
     * 费用明细表
     */
    private TrdTradeFeeDetail trdTradeFeeDetail;
    
    /**
     * 组合交易表List
     * ADD BY WUJIE ON 20191119
     */
    private List<PtlTrade> ptlTradeList;
    
    
    /**ADD BY WUJIE ON 20210129 START REMARK:回购到期区分 到期、到期续作、逾期贩售**/
    private String psType;//回购交易方向：到期续作、逾期贩售
    
    public String getPsType() {
		return psType;
	}

	public void setPsType(String psType) {
		this.psType = psType;
	}

	/**ADD BY WUJIE ON 20210129 END**/
	

    public TrdFinProductBarter getTrdFinProductBarter() {
        return trdFinProductBarter;
    }

    public void setTrdFinProductBarter(TrdFinProductBarter trdFinProductBarter) {
        this.trdFinProductBarter = trdFinProductBarter;
    }

    /**
     * 金融产品实物交易表
     */
    private TrdFinProductBarter trdFinProductBarter;

    /**
     * 金融产品交易附属信息
     */
    private TrdProductDealAdd trdProductDealAdd;

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
     * 
     * @return stlPayOrders.
     */
    public StlPayOrders getStlPayOrders() {
        return stlPayOrders;
    }

    /**
     * 设置 stlPayOrders
     * 
     * @param stlPayOrders stlPayOrders
     */
    public void setStlPayOrders(StlPayOrders stlPayOrders) {
        this.stlPayOrders = stlPayOrders;
    }

	public TrdTradeFeeDetail getTrdTradeFeeDetail() {
		return trdTradeFeeDetail;
	}

	public void setTrdTradeFeeDetail(TrdTradeFeeDetail trdTradeFeeDetail) {
		this.trdTradeFeeDetail = trdTradeFeeDetail;
	}

	public List<PtlTrade> getPtlTradeList() {
		return ptlTradeList;
	}

	public void setPtlTradeList(List<PtlTrade> ptlTradeList) {
		this.ptlTradeList = ptlTradeList;
	}

    public TrdProductDealAdd getTrdProductDealAdd() {
        return trdProductDealAdd;
    }

    public void setTrdProductDealAdd(TrdProductDealAdd trdProductDealAdd) {
        this.trdProductDealAdd = trdProductDealAdd;
    }
}
