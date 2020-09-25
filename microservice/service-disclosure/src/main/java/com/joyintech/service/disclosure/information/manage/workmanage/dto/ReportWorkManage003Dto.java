/*
 * 文件名：ReportWorkManage003Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.disclosure.information.manage.workmanage.dto;

import com.joyintech.tams.common.base.entity.RepJobExcludePro;

public class ReportWorkManage003Dto extends RepJobExcludePro {
	/**
	 * 序列
	 */
	private static final long serialVersionUID = -520748456106856489L;
	/**
	 * 产品代码
	 */
	private String prodId;
	/**
	 * 产品名称
	 */
	private String prodName;

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
}
