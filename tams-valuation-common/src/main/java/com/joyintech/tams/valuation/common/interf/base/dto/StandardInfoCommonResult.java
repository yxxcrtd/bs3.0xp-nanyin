package com.joyintech.tams.valuation.common.interf.base.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.BokBooksetCapacctInfo;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.MstCalendarInfo;
import com.joyintech.tams.common.base.entity.StlFinProductAcct;
import com.joyintech.tams.common.base.entity.ValInterflog;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSubjectInfo;

public class StandardInfoCommonResult extends BaseEntity {
	  /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
	
	/**
     * 金融产品账户表
     */
    private List<StlFinProductAcct> valFinProductAcctList = new ArrayList<StlFinProductAcct>();
    
    /**
     * 日历信息
     */
    private List<MstCalendarInfo> valCalendarInfoList = new ArrayList<MstCalendarInfo>();
    /**
     * 日志
     */
    private List<ValInterflog> valInterflog = new ArrayList<ValInterflog>();


    /**
     * 几许传数
     */
    private List<ValSubjectInfo> listValSubjectInfo = new ArrayList<ValSubjectInfo>();//add by chufengjie 20190523


    /**
     * 账套清算账户
     */
    private List<BokBooksetCapacctInfo> valBooksetCapacctInfoList = new ArrayList<BokBooksetCapacctInfo>();

    
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
    
    /**add by wujie on 20180814 start remark:活期利息改造**/
    /**
     * 账务明细代码表
     */
    private List<BokDetailId> bokDetailIdList = new ArrayList<BokDetailId>();
    
    private Map<String,Object> paramMap = new HashMap<String,Object>();

	public List<BokDetailId> getBokDetailIdList() {
		return bokDetailIdList;
	}


	public void setBokDetailIdList(List<BokDetailId> bokDetailIdList) {
		this.bokDetailIdList = bokDetailIdList;
	}


	/**
	 * @return the paramMap
	 */
	public Map<String, Object> getParamMap() {
		return paramMap;
	}


	/**
	 * @param paramMap the paramMap to set
	 */
	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
	
	/**add by wujie on 20180814 end**/

    /**
     * @return the listValSubjectInfo
     */
    public List<ValSubjectInfo> getListValSubjectInfo() {
        return listValSubjectInfo;
    }


    /**
     * @param listValSubjectInfo the listValSubjectInfo to set
     */
    public void setListValSubjectInfo(List<ValSubjectInfo> listValSubjectInfo) {
        this.listValSubjectInfo = listValSubjectInfo;
    }
    
}
