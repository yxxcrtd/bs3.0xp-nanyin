package com.joyintech.tams.invest.common.risktrialrds.reflect;

import com.joyintech.tams.invest.common.risktrialrds.dto.InvestTradeTrialInput;
import com.joyintech.tams.invest.common.risktrialrds.dto.SystemParam;
import com.joyintech.tams.invest.common.risktrialrds.reflect.duration.service.RiskManageCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 裴家俊
 * @remark 所有PLUGIN参数基类
 * @date 2020-07-15
 */
public abstract class PluginClass<V> {

	//@Autowired
	//protected PluginClassRdsDao pluginClassRdsDao;

	@Autowired
	protected RiskManageCommonService riskManageCommonService;

	protected SystemParam systemParam;

	protected InvestTradeTrialInput inputParam;

	/**
	 * 反射类ID
	 */
	private String refId;

	/**
	 * 取得INF注解
	 *
	 * @return INF注解实例
	 * @see
	 * @since 1.0
	 */
	private REF getREF() {
		return getClass().getAnnotation(REF.class);
	}

	@Autowired
	private void init() {
		REF ref = getREF();
		if (ref != null) {
			refId = ref.value();
		}
	}

	public  void setSysParameter(InvestTradeTrialInput investTradeTrialInput, SystemParam sysPass){
		this.inputParam=investTradeTrialInput;
		this.systemParam = sysPass;
	};
		
	public  V getParameterValue() throws Exception{
		V obj=handleData();
		return obj;
	};

	public String getRefId() {
		return refId;
	}

	protected  abstract V handleData() throws Exception;
}
