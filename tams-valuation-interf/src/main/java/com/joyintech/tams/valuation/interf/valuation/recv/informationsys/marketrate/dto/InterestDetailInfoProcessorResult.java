package com.joyintech.tams.valuation.interf.valuation.recv.informationsys.marketrate.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.InterestInfoRecCommonResult;
import com.joyintech.tams.common.base.entity.MstIntRateInfo;
import com.joyintech.tams.common.base.entity.ValInterflog;

/**
 * 市场利率行情信息接口Processor返回共通结果
 * @author dongxiangyang
 * @version 1.0 2018年7月05日
 * @see 
 * @since 1.0
 */
public class InterestDetailInfoProcessorResult extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 利率行情
     */
    private   List<MstIntRateInfo> listvalIntRateQuote=new ArrayList<MstIntRateInfo>();
    /**
     * 日志
     */
    private List<ValInterflog> listValInterflog = new ArrayList<ValInterflog>();
	
    
    /**
     * 获取 listvalIntRateQuote
     * @return listvalIntRateQuote.
     */
	  public List<MstIntRateInfo> getListvalIntRateQuote() {
		return listvalIntRateQuote;
	}
    
	  /**
	     * 设置 listvalIntRateQuote
	     * @param listvalIntRateQuote listvalIntRateQuote
	     */
	public void setListvalIntRateQuote(List<MstIntRateInfo> listvalIntRateQuote) {
		this.listvalIntRateQuote = listvalIntRateQuote;
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
     * 获取 serialversionuid
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    /**
     * 重载构造方法 从valuation-common中返回的result重新赋值
     * 
     * @param rtnResult 共通结果
     */

	public InterestDetailInfoProcessorResult(InterestInfoRecCommonResult result) {
		this.setListvalIntRateQuote(result.getListvalIntRateQuote());
		this.setListValInterflog(result.getListValInterflog());
	}
	 
	
}
