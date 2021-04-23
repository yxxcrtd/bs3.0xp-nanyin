/*
 * 文件名：ValBondMarketInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.batch.finproduct.entity;

import com.joyintech.tams.common.base.entity.TrdProductDeal;

/**
 * 交易数据
 * @author chengzhong
 *
 */
public class TrdProductDealExtDto extends TrdProductDeal{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 3438054836901777342L;
	/**
     * 账套代码
     */
    private String booksetId;
    
    /**
     * 业务表，指令表、交易表等
     */
    private String busiTable;

	public String getBooksetId() {
		return booksetId;
	}

	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}

	public String getBusiTable() {
		return busiTable;
	}

	public void setBusiTable(String busiTable) {
		this.busiTable = busiTable;
	}
    
    
}
