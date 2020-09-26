package com.joyintech.tams.valuation.interf.valuation.recv.informationsys.rate.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinProductValuation;
import com.joyintech.tams.common.base.entity.MstBondMarketInfo;
import com.joyintech.tams.common.base.entity.MstBondValuationInfo;
import com.joyintech.tams.common.base.entity.MstFundMarketInfo;
import com.joyintech.tams.common.base.entity.MstIntRateInfo;
import com.joyintech.tams.common.base.entity.ValInterflog;
import com.joyintech.framework.base.BaseEntity;

/**
 * 行情信息接口Processor返回共通结果
 * 
 * @author dongxiangyang
 * @version 1.0 2018年7月05日
 * @see
 * @since 1.0
 */
public class RateProcessorResult extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 行情信息
	 */
//	private List<MstRateInfo> listMstRateInfo = new ArrayList<MstRateInfo>();

	/**
	 * 利率行情
	 */
	private List<MstIntRateInfo> listvalIntRateQuote = new ArrayList<MstIntRateInfo>();

	/**
	 * 基金市场行情
	 */
	private List<MstFundMarketInfo> listValFundMarketInfo = new ArrayList<MstFundMarketInfo>();
	/**
	 * 债券市场行情
	 */
	private List<MstBondMarketInfo> listMstBondMarketInfo = new ArrayList<MstBondMarketInfo>();
	/**
	 * 债券估值行情
	 */
	private List<MstBondValuationInfo> listMstBondValuationInfo = new ArrayList<MstBondValuationInfo>();
	/**
	 * 场外金融产品行情
	 */
	private List<FinProductValuation> listValFinProductValuation = new ArrayList<FinProductValuation>();

	/**
	 * 日志
	 */
	private List<ValInterflog> listValInterflog = new ArrayList<ValInterflog>();

	/**
	 * 获取 listvalIntRateQuote
	 * 
	 * @return listvalIntRateQuote.
	 */
	public List<MstIntRateInfo> getListvalIntRateQuote() {
		return listvalIntRateQuote;
	}

	/**
	 * 设置 listvalIntRateQuote
	 * 
	 * @param listvalIntRateQuote
	 *            listvalIntRateQuote
	 */
	public void setListvalIntRateQuote(List<MstIntRateInfo> listvalIntRateQuote) {
		this.listvalIntRateQuote = listvalIntRateQuote;
	}

	/**
	 * 获取 listValInterflog
	 * 
	 * @return listValInterflog.
	 */
	public List<ValInterflog> getListValInterflog() {
		return listValInterflog;
	}

	/**
	 * 设置 listValInterflog
	 * 
	 * @param listValInterflog
	 *            listValInterflog
	 */
	public void setListValInterflog(List<ValInterflog> listValInterflog) {
		this.listValInterflog = listValInterflog;
	}

	/**
	 * 获取 serialversionuid
	 * 
	 * @return serialversionuid.
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<FinProductValuation> getListValFinProductValuation() {
		return listValFinProductValuation;
	}

	public void setListValFinProductValuation(List<FinProductValuation> listValFinProductValuation) {
		this.listValFinProductValuation = listValFinProductValuation;
	}

	public List<MstBondValuationInfo> getListMstBondValuationInfo() {
		return listMstBondValuationInfo;
	}

	public void setListMstBondValuationInfo(List<MstBondValuationInfo> listMstBondValuationInfo) {
		this.listMstBondValuationInfo = listMstBondValuationInfo;
	}

	public List<MstBondMarketInfo> getListMstBondMarketInfo() {
		return listMstBondMarketInfo;
	}

	public void setListMstBondMarketInfo(List<MstBondMarketInfo> listMstBondMarketInfo) {
		this.listMstBondMarketInfo = listMstBondMarketInfo;
	}

	public List<MstFundMarketInfo> getListValFundMarketInfo() {
		return listValFundMarketInfo;
	}

	public void setListValFundMarketInfo(List<MstFundMarketInfo> listValFundMarketInfo) {
		this.listValFundMarketInfo = listValFundMarketInfo;
	}

//	public List<MstRateInfo> getListMstRateInfo() {
//		return listMstRateInfo;
//	}
//
//	public void setListMstRateInfo(List<MstRateInfo> listMstRateInfo) {
//		this.listMstRateInfo = listMstRateInfo;
//	}

}
