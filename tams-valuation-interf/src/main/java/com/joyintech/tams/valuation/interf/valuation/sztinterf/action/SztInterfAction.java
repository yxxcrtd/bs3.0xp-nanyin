package com.joyintech.tams.valuation.interf.valuation.sztinterf.action;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.common.sztinterf.constant.DictCodeSztList;
import com.joyintech.tams.valuation.common.sztinterf.constant.SztValConstant;
import com.joyintech.tams.valuation.interf.valuation.sztinterf.service.SztInterfDealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SztInterfAction {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	 @Autowired
	 private SztInterfDealService sztInterfDealService;

	/**
     * 参数
     */
    private Map<String, Object> params;
    
    /**
     * 获取 params
     * 
     * @return params.
     */
    public Map<String, Object> getParams() {
        return params;
    }

    /**
     * 设置 params
     * 
     * @param params params
     */
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

	public void execute() {
		try{
			if(params != null && params.get(DictCodeSztList.深圳通页面调用接口参数字段_发送时间.getValue()) != null
				&& params.get(DictCodeSztList.深圳通页面调用接口参数字段_接口类型.getValue()) != null
				&& params.get(DictCodeSztList.深圳通页面调用接口参数字段_产品代码.getValue()) != null
			){
				String cdate = (String) params.get(DictCodeSztList.深圳通页面调用接口参数字段_发送时间.getValue());//时间
				String interType = (String) params.get(DictCodeSztList.深圳通页面调用接口参数字段_接口类型.getValue());//类型
				String prodIds = (String) params.get(DictCodeSztList.深圳通页面调用接口参数字段_产品代码.getValue());//产品代码
				String detailFlag = SztValConstant.深圳通_是否发送四级明细科目号_否;//是否发送四级科目号
				if(params.get(DictCodeSztList.深圳通页面调用接口参数字段_是否发送四级科目号.getValue()) != null){
					detailFlag = (String) params.get(DictCodeSztList.深圳通页面调用接口参数字段_是否发送四级科目号.getValue());
				}
				sztInterfDealService.callSztInterf(prodIds, interType, cdate, detailFlag);
			}
		}catch(Exception e){
			logger.error("" ,e);
		}
		
	}
	
	/**
	 * 自动跑批调用方法
	 * @param cdate
	 * @param interType
	 * @param listProdId
	 * @param detailFlag
	 */
	public void executeTiming(String cdate, String interType, String prodIds, String detailFlag){
		
		try {
			if(StringUtil.isNotEmpty(interType) && StringUtil.isNotEmpty(cdate)
				&& StringUtil.isNotEmpty(prodIds)
			) {
				sztInterfDealService.callSztInterf(prodIds, interType, cdate, detailFlag);
			}
		} catch (Exception e) {
			logger.error("" ,e);
		}
		
	}
	
}
