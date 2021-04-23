package com.joyintech.service.trade.investtrade.commanddispatcher.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 指令查询接口返回DTO
 * @author chaowei
 *
 */
public class EntrCommandDispatcherTotalDto implements Serializable{
   
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
    private List<EntrCommandDispatcherDto> etr_entrust_list;
    
    
    /**回购列表结果集*/
    private List<EntrTradePledgeDto> etr_pledge_list;



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


	public List<EntrCommandDispatcherDto> getEtr_entrust_list() {
		return etr_entrust_list;
	}


	public void setEtr_entrust_list(List<EntrCommandDispatcherDto> etr_entrust_list) {
		this.etr_entrust_list = etr_entrust_list;
	}


	public List<EntrTradePledgeDto> getEtr_pledge_list() {
		return etr_pledge_list;
	}


	public void setEtr_pledge_list(List<EntrTradePledgeDto> etr_pledge_list) {
		this.etr_pledge_list = etr_pledge_list;
	}
    
}