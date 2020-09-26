/*
 * 文件名：OtherBaseInfoCommonResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年6月26日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.BokBooksetCapacctInfo;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.MstCalendarInfo;
import com.joyintech.tams.common.base.entity.MstCustomerInfo;
import com.joyintech.tams.common.base.entity.StlFinProductAcct;
import com.joyintech.tams.common.base.entity.MstHoliday;
import com.joyintech.tams.common.base.entity.ValInterflog;
import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.tams.common.base.entity.MstTradeCalendar;

public class OtherBaseInfoCommonResult extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 市场机构信息
     */
    private List<MstCustomerInfo> valCustomerInfoList = new ArrayList<MstCustomerInfo>();
    
    /**
     * 资金账户信息
     */
    private List<StlCapacctInfo> valMstCapacctInfoList = new ArrayList<StlCapacctInfo>();
    
    /**
     * 金融产品账户表
     */
    private List<StlFinProductAcct> valFinProductAcctList = new ArrayList<StlFinProductAcct>();
    
    /**
     * 日历信息
     */
    private List<MstCalendarInfo> valCalendarInfoList = new ArrayList<MstCalendarInfo>();
    
    /**
     * 交易场所日历
     */
    private List<MstTradeCalendar> valTradeCalendarList = new ArrayList<MstTradeCalendar>();
    
    /**
     * 账套清算账户
     */
    private List<BokBooksetCapacctInfo> valBooksetCapacctInfoList = new ArrayList<BokBooksetCapacctInfo>();
    
    /**
     * 日历节假日
     */
    private List<MstHoliday> valHolidayList = new ArrayList<MstHoliday>();
    
    /**
     * 日志
     */
    private List<ValInterflog> valInterflog = new ArrayList<ValInterflog>();
    
    /**
     * 获取 valCustomerInfoList
     * @return valCustomerInfoList.
     */
    public List<MstCustomerInfo> getValCustomerInfoList() {
        return valCustomerInfoList;
    }

    
    /**
     * 设置 valCustomerInfoList
     * @param valCustomerInfoList valCustomerInfoList
     */
    public void setValCustomerInfoList(List<MstCustomerInfo> valCustomerInfoList) {
        this.valCustomerInfoList = valCustomerInfoList;
    }

    
    /**
     * 获取 valMstCapacctInfoList
     * @return valMstCapacctInfoList.
     */
    public List<StlCapacctInfo> getValMstCapacctInfoList() {
        return valMstCapacctInfoList;
    }

    
    /**
     * 设置 valMstCapacctInfoList
     * @param valMstCapacctInfoList valMstCapacctInfoList
     */
    public void setValMstCapacctInfoList(List<StlCapacctInfo> valMstCapacctInfoList) {
        this.valMstCapacctInfoList = valMstCapacctInfoList;
    }

    
    /**
     * 获取 valFinProductAcctList
     * @return valFinProductAcctList.
     */
    public List<StlFinProductAcct> getValFinProductAcctList() {
        return valFinProductAcctList;
    }

    
    /**
     * 设置 valFinProductAcctList
     * @param valFinProductAcctList valFinProductAcctList
     */
    public void setValFinProductAcctList(List<StlFinProductAcct> valFinProductAcctList) {
        this.valFinProductAcctList = valFinProductAcctList;
    }

    
    /**
     * 获取 valCalendarInfoList
     * @return valCalendarInfoList.
     */
    public List<MstCalendarInfo> getValCalendarInfoList() {
        return valCalendarInfoList;
    }

    
    /**
     * 设置 valCalendarInfoList
     * @param valCalendarInfoList valCalendarInfoList
     */
    public void setValCalendarInfoList(List<MstCalendarInfo> valCalendarInfoList) {
        this.valCalendarInfoList = valCalendarInfoList;
    }

    
    /**
     * 获取 valTradeCalendarList
     * @return valTradeCalendarList.
     */
    public List<MstTradeCalendar> getValTradeCalendarList() {
        return valTradeCalendarList;
    }

    
    /**
     * 设置 valTradeCalendarList
     * @param valTradeCalendarList valTradeCalendarList
     */
    public void setValTradeCalendarList(List<MstTradeCalendar> valTradeCalendarList) {
        this.valTradeCalendarList = valTradeCalendarList;
    }

    
    /**
     * 获取 valBooksetCapacctInfoList
     * @return valBooksetCapacctInfoList.
     */
    public List<BokBooksetCapacctInfo> getValBooksetCapacctInfoList() {
        return valBooksetCapacctInfoList;
    }

    
    /**
     * 设置 valBooksetCapacctInfoList
     * @param valBooksetCapacctInfoList valBooksetCapacctInfoList
     */
    public void setValBooksetCapacctInfoList(List<BokBooksetCapacctInfo> valBooksetCapacctInfoList) {
        this.valBooksetCapacctInfoList = valBooksetCapacctInfoList;
    }

    
    /**
     * 获取 valHolidayList
     * @return valHolidayList.
     */
    public List<MstHoliday> getValHolidayList() {
        return valHolidayList;
    }

    
    /**
     * 设置 valHolidayList
     * @param valHolidayList valHolidayList
     */
    public void setValHolidayList(List<MstHoliday> valHolidayList) {
        this.valHolidayList = valHolidayList;
    }

    
    /**
     * 获取 valInterflog
     * @return valInterflog.
     */
    public List<ValInterflog> getValInterflog() {
        return valInterflog;
    }

    
    /**
     * 设置 valInterflog
     * @param valInterflog valInterflog
     */
    public void setValInterflog(List<ValInterflog> valInterflog) {
        this.valInterflog = valInterflog;
    }
    
    /**add by wujie on 20180814 start remark:活期利息改造**/
    /**
     * 现金流信息表
     */
    private List<FinCashInfo> finCashInfoList = new ArrayList<FinCashInfo>();
    
    /**
     * 金融产品分类表
     */
    private List<FinProductType> finProductTypeList = new ArrayList<FinProductType>();
    

	public List<FinCashInfo> getFinCashInfoList() {
		return finCashInfoList;
	}


	public void setFinCashInfoList(List<FinCashInfo> finCashInfoList) {
		this.finCashInfoList = finCashInfoList;
	}
	
	/**
	 * @return the finProductTypeList
	 */
	public List<FinProductType> getFinProductTypeList() {
		return finProductTypeList;
	}


	/**
	 * @param finProductTypeList the finProductTypeList to set
	 */
	public void setFinProductTypeList(List<FinProductType> finProductTypeList) {
		this.finProductTypeList = finProductTypeList;
	}

	private Map<String,Object> paramMap;


	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
	
	/**add by wujie on 20180814 end**/
}
