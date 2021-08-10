package com.joyintech.tams.invest.common.risktrialrds.reflect.impl;


import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.risktrialrds.reflect.PluginClass;
import com.joyintech.tams.invest.common.risktrialrds.reflect.REF;
import org.springframework.stereotype.Service;

@Service
@REF("AssetDeviation")
public class AssetDeviation extends PluginClass {

	@Override
	public Object handleData() throws Exception {
		if(StringUtil.equals(systemParam.getSupFrequency(),BussinessDictCodeListRds.参数粒度_实时.getValue())){
			return riskManageCommonService.calcPrdDeviation(this.systemParam);
		}else{
			return riskManageCommonService.calcPrdDeviation(this.systemParam).get(0);
		}
	}

}
