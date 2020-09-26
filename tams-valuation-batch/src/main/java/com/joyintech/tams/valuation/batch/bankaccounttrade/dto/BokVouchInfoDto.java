/*
 * 文件名：BokVouchInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2019年4月10日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bankaccounttrade.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.common.base.entity.BokVouchMain;

/**
 * 
 * 凭证主表Dto
 * 
 * @author niuqianyu
 * @version 1.0 2019年4月11日
 * @see BokVouchInfoDto
 * @since 1.0
 */
public class BokVouchInfoDto extends BokVouchMain {

	private static final long serialVersionUID = 1L;

	/**
	 * 凭证子表
	 */
	private List<BokVouchDetailDto> listBokVouchDetail = new ArrayList<BokVouchDetailDto>();

	public List<BokVouchDetailDto> getListBokVouchDetail() {
		return listBokVouchDetail;
	}

	public void setListBokVouchDetail(List<BokVouchDetailDto> listBokVouchDetail) {
		this.listBokVouchDetail = listBokVouchDetail;
	}

}
