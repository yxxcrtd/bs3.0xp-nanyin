package com.joyintech.service.disclosure.information.reveal.detailshow.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * @author chendong
 * @version 1.0 2018年11月27日
 * @see ProdInfoDto
 * @since 1.0
 */
public class ProdInfoDto extends BaseEntity{
	/**
     * 产品ID
     */
    private String prodId;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 获取产品代码
     */
	public String getProdId() {
		return prodId;
	}

	/**
     * 设置产品代码
     */
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	/**
     * 获取产品名称
     */
	public String getProdName() {
		return prodName;
	}

	/**
     * 设置产品名称
     */
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
}
