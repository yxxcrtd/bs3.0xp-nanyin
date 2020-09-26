package com.joyintech.tams.valuation.accounadjustmanage.valsztinterfsubject.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.valuation.accounadjustmanage.valsztinterfsubject.dto.ValSztInterfSubject001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;

@Service
public class ValSztInterfSubject001Service extends CommonExportService<ValSztInterfSubject001Dto>{

    /**
     * 深证通科目信息页面查询方法 从xybranch20190115版本合并过来
     * <功能详细描述>
     * @param params
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
	public Page<ValSztInterfSubject001Dto> findPage(Map<String, Object> params) throws Exception{
	    return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/ValSztInterfSubject/findPage",
	        params, ValSztInterfSubject001Dto.class);
	}

	/**
	 * 获取接口参数 从xybranch20190115版本合并过来
	 * <功能详细描述>
	 * @param datas
	 * @return
	 * @see
	 * @since 1.0
	 */
	public Map<String, String> getParm(String datas) {
		Map<String, String> urlParams = new HashMap<>();
		String data = "";
        if(datas.length()>0){
        	data=datas.substring(0, datas.length()-1);
        }
        urlParams.put(InterfCommonConstant.接口_任务执行ID, data);
		return urlParams;
	}

}
