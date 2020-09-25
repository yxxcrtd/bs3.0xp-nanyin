/*
 * 文件名：ResetBaseRuleInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.mproddivreindealgen.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;


/**
 *  业绩基准重置
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年2月16日
 * @see MProdDivReinDealGenDto
 * @since 1.0
 */
public class MProdDivReinDealGenDto extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7469839386442605795L;
	/**
	 * 产品代码
	 */
	private String finprodId;
	/**
	 * 当日未分配利润
	 */
	private BigDecimal tdyUnpaidIntincexp;
	public String getFinprodId() {
		return finprodId;
	}
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}
	public BigDecimal getTdyUnpaidIntincexp() {
		return tdyUnpaidIntincexp;
	}
	public void setTdyUnpaidIntincexp(BigDecimal tdyUnpaidIntincexp) {
		this.tdyUnpaidIntincexp = tdyUnpaidIntincexp;
	}
    

}
