package com.joyintech.tams.invest.common.dto.common;

import java.util.List;

import com.joyintech.framework.base.BaseEntity;

/**
 * 债券交易预录入组合信息实体类
 * @author Administrator
 *
 */
public class BussTransDto extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 组合信息列表
     */
    private List<TrdTransfenpeiDto> trdTransfenpeiDtoList;

	/**
	 * 分配方式
	 */
	private String distribution;

	public String getDistribution() {
		return distribution;
	}

	public void setDistribution(String distribution) {
		this.distribution = distribution;
	}

	public List<TrdTransfenpeiDto> getTrdTransfenpeiDtoList() {
		return trdTransfenpeiDtoList;
	}

	public void setTrdTransfenpeiDtoList(List<TrdTransfenpeiDto> trdTransfenpeiDtoList) {
		this.trdTransfenpeiDtoList = trdTransfenpeiDtoList;
	}


}
