/*
 * 文件名：FinAssetClassDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhouyongjun
 * 修改时间：2019年12月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.product.productplanning.asset.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 资产配置
 * 
 * @author zhouyongjun
 * @version 1.0 2019年12月26日
 * @see FinAssetClassDto
 * @since 1.0
 */
public class FinAssetClassDto extends BaseEntity implements ProcessEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4723725569410781871L;

	private String assetType;
	private String type2;
	private String type3;
	private String type4;
	private String type5;
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	/**
	 * 资产二级分类
	 * @return
	 */
	public String getType2() {
		return type2;
	}
	public void setType2(String type2) {
		this.type2 = type2;
	}
	/**
	 * 资产三级分类
	 * @return
	 */
	public String getType3() {
		return type3;
	}
	public void setType3(String type3) {
		this.type3 = type3;
	}
	/**
	 * 资产四级分类
	 * @return
	 */
	public String getType4() {
		return type4;
	}
	public void setType4(String type4) {
		this.type4 = type4;
	}
	/**
	 * 资产五级分类
	 * @return
	 */
	public String getType5() {
		return type5;
	}
	public void setType5(String type5) {
		this.type5 = type5;
	}
	
}
