package com.joyintech.tams.valuation.common.interf.base.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.MstIntRateInfo;
import com.joyintech.tams.common.base.entity.ValInterflog;
/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dongxiangyang
 * @version 1.0 2018年7月05日
 * @see InterestInfoRecCommonResult
 * @since 1.0
 */


public class InterestInfoRecCommonResult extends BaseEntity {
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

    
}
