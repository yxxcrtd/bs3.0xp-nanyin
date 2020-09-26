package com.joyintech.tams.valuation.common.interf.base.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.BokBookdetailCurrent;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.ValInterflog;
/**
 * 增值税接口--
 * 
 * @author dongxiangyang
 * @version 1.0 2018年6月28日
 * @see ValueAddedTaxRecCommonResult
 * @since 1.0
 */
public class ValueAddedTaxRecCommonResult extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	 /**
     * 账务明细
     */
	private List<BokBookdetailCurrent>  listValBokBookdetailCurrent=new ArrayList<BokBookdetailCurrent>();
	 
	/**
     * 账务明细代码表
     */
	private List<BokDetailId> listValBokDetailId=new ArrayList<BokDetailId>();
	/**
     * 日志
     */
    private List<ValInterflog> listValInterflog = new ArrayList<ValInterflog>();
	
    /**
     * 获取 listValBokBookdetailCurrent
     * @return listValBokBookdetailCurrent.
     */
    public List<BokBookdetailCurrent> getListValBokBookdetailCurrent() {
		return listValBokBookdetailCurrent;
	}
    /**
     * 设置 listValBokBookdetailCurrent
     * @param listValBokBookdetailCurrent listValBokBookdetailCurrent
     */
	public void setListValBokBookdetailCurrent(List<BokBookdetailCurrent> listValBokBookdetailCurrent) {
		this.listValBokBookdetailCurrent = listValBokBookdetailCurrent;
	}
	/**
     * 获取 listValInterflog
     * @return listValInterflog.
     */
	public List<ValInterflog> getListValInterflog() {
		return listValInterflog;
	}
	  /**
     * 设置 listValInterflog
     * @param listValInterflog listValInterflog
     */
	public void setListValInterflog(List<ValInterflog> listValInterflog) {
		this.listValInterflog = listValInterflog;
	}
	/**
     * 获取 listValBokDetailId
     * @return listValBokDetailId.
     */
	public List<BokDetailId> getListValBokDetailId() {
		return listValBokDetailId;
	}
	 /**
     * 设置 listValBokDetailId
     * @param listValBokDetailId listValBokDetailId
     */
	public void setListValBokDetailId(List<BokDetailId> listValBokDetailId) {
		this.listValBokDetailId = listValBokDetailId;
	}
    
    
    
}
