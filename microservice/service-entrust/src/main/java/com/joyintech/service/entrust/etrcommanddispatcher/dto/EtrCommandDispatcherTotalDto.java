package com.joyintech.service.entrust.etrcommanddispatcher.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 指令查询接口返回DTO
 * @author chaowei
 *
 */
public class EtrCommandDispatcherTotalDto implements Serializable{
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 2830064994067751117L;

	/**
	 * 查询定位串
	 */
    private Integer AMOUNT;
    
    /**
	 * 查询结束标志
	 */
    private Integer pageCount;
    
    /**结果集*/
    private List<EtrCommandDispatcherDto> etr_entrust_list;
    
    
    /**回购列表结果集*/
    private List<EtrTradePledgeDto> etr_pledge_list;



	public Integer getAMOUNT() {
		return AMOUNT;
	}


	public void setAMOUNT(Integer aMOUNT) {
		AMOUNT = aMOUNT;
	}


	public Integer getPageCount() {
		return pageCount;
	}


	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}


	public List<EtrCommandDispatcherDto> getEtr_entrust_list() {
		return etr_entrust_list;
	}


	public void setEtr_entrust_list(List<EtrCommandDispatcherDto> etr_entrust_list) {
		this.etr_entrust_list = etr_entrust_list;
	}


	public List<EtrTradePledgeDto> getEtr_pledge_list() {
		return etr_pledge_list;
	}


	public void setEtr_pledge_list(List<EtrTradePledgeDto> etr_pledge_list) {
		this.etr_pledge_list = etr_pledge_list;
	}
    
}