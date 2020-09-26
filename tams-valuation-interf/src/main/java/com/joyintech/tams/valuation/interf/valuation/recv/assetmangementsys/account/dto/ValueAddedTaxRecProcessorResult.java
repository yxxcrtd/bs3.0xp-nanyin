package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.account.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.ValueAddedTaxRecCommonResult;
import com.joyintech.tams.common.base.entity.BokBookdetailCurrent;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.ValInterflog;


/**
 * 增值税返回共通结果 ValueAddedTaxRecProcessorResult
 * 
 * @author dongxiangyang
 * @version 1.0 2018年7月10日
 * @see ValueAddedTaxRecProcessorResult
 * @since 1.0
 */
public class ValueAddedTaxRecProcessorResult extends BaseEntity  {
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
     * 重载构造方法 从valuation-common中返回的result重新赋值
     * 
     * @param rtnResult 共通结果
     */
	public ValueAddedTaxRecProcessorResult(ValueAddedTaxRecCommonResult result) {
		this.setListValBokBookdetailCurrent(result.getListValBokBookdetailCurrent());
		this.setListValBokDetailId(result.getListValBokDetailId());
		this.setListValInterflog(result.getListValInterflog());
		
	}
    /**
     * 获取 serialversionuid
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
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
