/*
 * 文件名：RskPortfolioPoolConfigDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.PtlParamPoolConfig;
import com.joyintech.framework.base.BaseEntity;

/**
 * 组合参数入池配置
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see RskPortfolioIndicator002Dto.java
 * @since 1.0
 */
public class RskPortfolioPoolConfigDto  extends BaseEntity{

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;
	
	private List<PtlParamPoolConfig> ptlParamPoolConfigList;

	public List<PtlParamPoolConfig> getPtlParamPoolConfigList() {
		return ptlParamPoolConfigList;
	}

	public void setPtlParamPoolConfigList(List<PtlParamPoolConfig> ptlParamPoolConfigList) {
		this.ptlParamPoolConfigList = ptlParamPoolConfigList;
	}

    
}
