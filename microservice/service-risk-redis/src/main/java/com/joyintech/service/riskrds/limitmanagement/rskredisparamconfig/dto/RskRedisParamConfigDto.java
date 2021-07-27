package com.joyintech.service.riskrds.limitmanagement.rskredisparamconfig.dto;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.tams.common.base.entity.RskBusinessRedisConfig;

/**
 * redis参数配置
 * @author : 裴家俊
 * @version V1.0
 */
public class RskRedisParamConfigDto extends RskBusinessRedisConfig implements ITableRow {

	/**
	 * 参数名称
	 */
	private String paraName;

	public String getParaName() {
		return paraName;
	}

	public void setParaName(String paraName) {
		this.paraName = paraName;
	}
}
