package com.joyintech.tams.invest.common.risktrial.reflect;

import com.joyintech.tams.invest.common.risktrial.dto.InvestTradeTrialInput;
import com.joyintech.tams.invest.common.risktrial.dto.SystemParam;

/**
 * @author 裴家俊
 * @remark  所有PLUGIN类型的指标参数调用接口
 * @date 2017-12-20
 */

public interface PluginClass {

		void setSysParameter(SystemParam sysPass, PluginClassDao assetDao, InvestTradeTrialInput investTradeTrialInput);
		
		Object getParamterValue() throws Exception;

}
