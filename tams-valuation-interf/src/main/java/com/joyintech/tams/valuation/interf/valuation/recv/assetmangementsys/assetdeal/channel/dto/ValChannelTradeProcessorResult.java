/*
 * 文件名：ValChannelTradeProcessorResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2019年3月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.channel.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.BokDealAccounting;
import com.joyintech.tams.common.base.entity.BokDealAccountingDetail;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.JJBkCashAcct;
import com.joyintech.tams.common.base.entity.JJPortfolio;
import com.joyintech.tams.common.base.entity.StlOrders;
import com.joyintech.tams.common.base.entity.StlOrdersExt;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.ValInterflog;
import com.joyintech.tams.valuation.common.interf.base.dto.ValChannelTradeValCommonResult;

/**
 * 通道产品-交易数据接口 Processor Result
 * 
 * @author zhangwei
 * @version 1.0 2019年3月12日
 * @see ValChannelTradeProcessorResult
 * @since 1.0
 */
public class ValChannelTradeProcessorResult extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    private int datanum;// 读取数据数

    private Map<String, Object> paramMap;

    /**
     * 日志
     */
    private List<ValInterflog> valInterflog = new ArrayList<ValInterflog>();

    /**
     * 金融产品交易
     */
    private List<TrdProductDeal> listValTrdProductDeal = new ArrayList<TrdProductDeal>();

    /**
     * 估值核算交易扩展表
     */
    private List<TrdProductDealAdd> listValTrdProductDealExt = new ArrayList<TrdProductDealAdd>();

    /**
     * 入账信息表
     */
    private List<BokDealAccounting> listValDealAccounting = new ArrayList<BokDealAccounting>();

    /**
     * 账务明细表
     */
    private List<BokDealAccountingDetail> listValDealAccountingDetail = new ArrayList<BokDealAccountingDetail>();

    /**
     * 账务明细代码表
     */
    private List<BokDetailId> listValBokDetailId = new ArrayList<BokDetailId>();

    /**
     * 结算指令
     */
    private List<StlOrders> listValOrders = new ArrayList<StlOrders>();

    /**
     * 结算指令扩展表
     */
    private List<StlOrdersExt> listValOrdersExt = new ArrayList<StlOrdersExt>();

    /**
     * 产品通道交易
     */
//    private List<ValChannelProdDealDto> listValChannelProdDealDto = new ArrayList<>();//modify by wujie on 20190321

    /**
     * 交易日
     */
    private Date resovleDate;
    
    /**
     * 几许产品信息List
     * @param zhangquan 20200107
     */
    private List<JJPortfolio> jJProtfioList;
    
    /**
     *几许产品资金账户信息List
     * @param zhangquan 20200107
     */
    private List<JJBkCashAcct> jJBkCashAcctList;

	public ValChannelTradeProcessorResult(ValChannelTradeValCommonResult result) {
        this.setListValTrdProductDeal(result.getListValTrdProductDeal());
        this.setListValTrdProductDealExt(result.getListValTrdProductDealExt());
        this.setListValDealAccounting(result.getListValDealAccounting());
        this.setListValDealAccountingDetail(result.getListValDealAccountingDetail());
        this.setListValBokDetailId(result.getListValBokDetailId());
        /**modify by wujie on 20190321 start
        this.setListValChannelProdDealDto(result.getListValChannelProdDealDto());**/
        this.setListValOrders(result.getListValOrders());
        this.setListValOrdersExt(result.getListValOrdersExt());
        /**modify by wujie on 20190321 end**/
        this.setResovleDate(result.getResovleDate());
        this.setValInterflog(result.getValInterflog());
        this.paramMap = result.getParamMap();
        this.setDatanum(result.getDatanum());
        /**add by zhangquan 20200107 end**/
        this.setjJProtfioList(result.getjJPortfolioList());
        this.setjJBkCashAcctList(result.getjJBkCashAcctList());
    }

    public List<TrdProductDeal> getListValTrdProductDeal() {
        return listValTrdProductDeal;
    }

    public void setListValTrdProductDeal(List<TrdProductDeal> listValTrdProductDeal) {
        this.listValTrdProductDeal = listValTrdProductDeal;
    }

    public List<TrdProductDealAdd> getListValTrdProductDealExt() {
        return listValTrdProductDealExt;
    }

    public void setListValTrdProductDealExt(List<TrdProductDealAdd> listValTrdProductDealExt) {
        this.listValTrdProductDealExt = listValTrdProductDealExt;
    }

    public int getDatanum() {
        return datanum;
    }

    public void setDatanum(int datanum) {
        this.datanum = datanum;
    }

    public List<ValInterflog> getValInterflog() {
        return valInterflog;
    }

    public void setValInterflog(List<ValInterflog> valInterflog) {
        this.valInterflog = valInterflog;
    }

    public List<BokDealAccounting> getListValDealAccounting() {
        return listValDealAccounting;
    }

    public void setListValDealAccounting(List<BokDealAccounting> listValDealAccounting) {
        this.listValDealAccounting = listValDealAccounting;
    }

    public List<BokDealAccountingDetail> getListValDealAccountingDetail() {
        return listValDealAccountingDetail;
    }

    public void setListValDealAccountingDetail(List<BokDealAccountingDetail> listValDealAccountingDetail) {
        this.listValDealAccountingDetail = listValDealAccountingDetail;
    }

    public List<BokDetailId> getListValBokDetailId() {
        return listValBokDetailId;
    }

    public void setListValBokDetailId(List<BokDetailId> listValBokDetailId) {
        this.listValBokDetailId = listValBokDetailId;
    }

    public List<StlOrders> getListValOrders() {
        return listValOrders;
    }

    public void setListValOrders(List<StlOrders> listValOrders) {
        this.listValOrders = listValOrders;
    }

    public List<StlOrdersExt> getListValOrdersExt() {
        return listValOrdersExt;
    }

    public void setListValOrdersExt(List<StlOrdersExt> listValOrdersExt) {
        this.listValOrdersExt = listValOrdersExt;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    /**modify by wujie on 20190321 
    public List<ValChannelProdDealDto> getListValChannelProdDealDto() {
        return listValChannelProdDealDto;
    }

    public void setListValChannelProdDealDto(List<ValChannelProdDealDto> listValChannelProdDealDto) {
        this.listValChannelProdDealDto = listValChannelProdDealDto;
    }**/

    public Date getResovleDate() {
        return resovleDate;
    }

    public void setResovleDate(Date resovleDate) {
        this.resovleDate = resovleDate;
    }
    
    public List<JJPortfolio> getjJProtfioList() {
		return jJProtfioList;
	}

	public void setjJProtfioList(List<JJPortfolio> jJProtfioList) {
		this.jJProtfioList = jJProtfioList;
	}

	public List<JJBkCashAcct> getjJBkCashAcctList() {
		return jJBkCashAcctList;
	}

	public void setjJBkCashAcctList(List<JJBkCashAcct> jJBkCashAcctList) {
		this.jJBkCashAcctList = jJBkCashAcctList;
	}

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
