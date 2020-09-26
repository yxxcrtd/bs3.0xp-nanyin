/*
 * 文件名：BokVouchDetailDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2019年4月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bankaccounttrade.dto;

import com.joyintech.tams.common.base.entity.BokVouchDetail;

/**
 * 
 * 凭证明细Dto
 * 
 * @author niuqianyu
 * @version 1.0 2019年4月11日
 * @see BokVouchDetailDto
 * @since 1.0
 */
public class BokVouchDetailDto extends BokVouchDetail {

	private static final long serialVersionUID = 1L;

	/**
	 * 金融产品代码
	 */
	private String finprodId;
	/**
	 * 分支序号
	 */
	private Long branch;
	/**
	 * 理财产品代码
	 */
	private String proId;

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public Long getBranch() {
		return branch;
	}

	public void setBranch(Long branch) {
		this.branch = branch;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

}
