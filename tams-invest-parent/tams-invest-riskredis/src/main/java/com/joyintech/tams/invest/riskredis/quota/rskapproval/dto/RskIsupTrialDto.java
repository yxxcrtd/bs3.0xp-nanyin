package com.joyintech.tams.invest.riskredis.quota.rskapproval.dto;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.RskIsupTrial;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;

public class RskIsupTrialDto extends RskIsupTrial {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 2810818144845870551L;

	private String indName;
	
	private String indType;

	public String getIndName() {
		return indName;
	}

	public void setIndName(String indName) {
		this.indName = indName;
	}

	public String getIndType() {
		return indType;
	}

	public void setIndType(String indType) {
		this.indType = indType;
	}
	
	public String getIndTypeStr(){
		return SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.指标类型, getIndType());
	}
	
	public String getSupStatStr(){
		return SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.指标状态, getSupStat());
	}
	
	public String getUniteSupStatStr(){
		return SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.指标状态, getUniteSupStat());
	}
}
