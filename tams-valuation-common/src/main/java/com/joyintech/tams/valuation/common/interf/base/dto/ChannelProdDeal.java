package com.joyintech.tams.valuation.common.interf.base.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.StlOrders;
import com.joyintech.tams.common.base.entity.TrdFinProductBarter;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;

public class ChannelProdDeal extends ValChannelProdDealDto {
	
	/**
	 * 产品类型
	 */
	private String productType;
	/**
	 * 交易拆分标志
	 */
	private String dealSepFlag;

	/**
	 * 是否转仓交易
	 */
	private String IsSwapTransaction;

	/**
	 * 通道费用信息
	 */
	private ChannelFeeInfo channelFeeInfo;
	
	/**
	 * 组合交易表List
	 */
	private List<PtlTrade> ptlTradeList;
	
	/**
	 * 金融产品扩展表
	 */
	private TrdProductDealAdd trdProductDealAdd;
	
	/**
	 * 金融产品实物交易表
	 */
	private TrdFinProductBarter trdFinProductBarter;
	
	/**
	 * 费用信息
	 */
	private TrdTradeFeeDetail trdTradeFeeDetail;
	
	/**
	 * 非通道费用List
	 */
	private List<TrdTradeFeeDetail> listTrdTradeFeeDetail;
	
	/**
	 * 资金交收指令
	 */
	private StlOrders stlOrders;
	
	

	public ChannelFeeInfo getChannelFeeInfo() {
		return channelFeeInfo;
	}

	public void setChannelFeeInfo(ChannelFeeInfo channelFeeInfo) {
		this.channelFeeInfo = channelFeeInfo;
	}

	public String getDealSepFlag() {
		return dealSepFlag;
	}

	public void setDealSepFlag(String dealSepFlag) {
		this.dealSepFlag = dealSepFlag;
	}
	/**
	 * 取得 是否转仓交易
	 * @return 是否转仓交易
	 */
	public String getIsSwapTransaction() {
		return IsSwapTransaction;
	}

	/**
	 * 设置 是否转仓交易
	 * @param isSwapTransaction 是否转仓交易
	 */
	public void setIsSwapTransaction(String isSwapTransaction) {
		IsSwapTransaction = isSwapTransaction;
	}

	public List<PtlTrade> getPtlTradeList() {
		return ptlTradeList;
	}

	public void setPtlTradeList(List<PtlTrade> ptlTradeList) {
		this.ptlTradeList = ptlTradeList;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public TrdProductDealAdd getTrdProductDealAdd() {
		return trdProductDealAdd;
	}

	public void setTrdProductDealAdd(TrdProductDealAdd trdProductDealAdd) {
		this.trdProductDealAdd = trdProductDealAdd;
	}

	public TrdFinProductBarter getTrdFinProductBarter() {
		return trdFinProductBarter;
	}

	public void setTrdFinProductBarter(TrdFinProductBarter trdFinProductBarter) {
		this.trdFinProductBarter = trdFinProductBarter;
	}

	public StlOrders getStlOrders() {
		return stlOrders;
	}

	public void setStlOrders(StlOrders stlOrders) {
		this.stlOrders = stlOrders;
	}

	public TrdTradeFeeDetail getTrdTradeFeeDetail() {
		return trdTradeFeeDetail;
	}

	public void setTrdTradeFeeDetail(TrdTradeFeeDetail trdTradeFeeDetail) {
		this.trdTradeFeeDetail = trdTradeFeeDetail;
	}

	public List<TrdTradeFeeDetail> getListTrdTradeFeeDetail() {
		return listTrdTradeFeeDetail;
	}

	public void setListTrdTradeFeeDetail(List<TrdTradeFeeDetail> listTrdTradeFeeDetail) {
		this.listTrdTradeFeeDetail = listTrdTradeFeeDetail;
	}
	
}
