package com.joyintech.tams.invest.common.risktrialrds.reflect.impl;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.risktrialrds.dto.SystemParam;
import com.joyintech.tams.invest.common.risktrialrds.reflect.PluginClass;
import com.joyintech.tams.invest.common.risktrialrds.reflect.PluginClassDao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
* @author 裴家俊
 * @date 2020年2月15日
 * @remark 最大回撤
 */

public class MaxRetReat implements PluginClass {

	  private PluginClassDao pluginClassDao;
	  
	  private SystemParam sysRefParam;


	@Override
    public void setSysParameter(SystemParam sysPass, PluginClassDao assetDao){
		sysRefParam=sysPass;
		pluginClassDao=assetDao;
    }
	
	@Override
    public Object getParamterValue() throws Exception {
		List<Map<String,Object>> unitPriceList=pluginClassDao.getMaxRetReat(sysRefParam.getPortfolioCode(),sysRefParam.getValDate());//取前一日净价
		if (StringUtil.isEmptyList(unitPriceList)) {
			return null;
		}
		// 存放该组合下的回撤集合
		List<BigDecimal> maxRetReatList = new ArrayList<BigDecimal>();
		for (int i = 0; i < unitPriceList.size(); i++) {
			if(!StringUtil.isNotEmpty(unitPriceList.get(i).get("TDY_UNIT_VALUE"))){
				continue;
			}
			BigDecimal netValue = new BigDecimal(unitPriceList.get(i).get("TDY_UNIT_VALUE").toString());
			// 存放该组合下的单位净值集合
			List<BigDecimal> netList = new ArrayList<BigDecimal>();
			for (int j = i; j < unitPriceList.size(); j++) {
				netList.add(new BigDecimal(unitPriceList.get(i).get("TDY_UNIT_VALUE").toString()));
			}
			BigDecimal minNetValue = Collections.min(netList);
			// 计算该组合下时间段内一天的回撤
			BigDecimal retReat = ArithmeticUtil.subtract(netValue,minNetValue);
			maxRetReatList.add(retReat);
		}
		// 获取该组合下的最大回撤
		if(maxRetReatList.size()>0){
			return Collections.max(maxRetReatList);
		}
        return null;
    }
    
    
    
    
}
