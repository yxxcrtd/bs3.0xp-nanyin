package com.joyintech.tams.valuation.accounadjustmanage.valsztinterfvaldata.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.valuation.accounadjustmanage.valsztinterfvaldata.dto.ValSztInterfValData001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;

@Service
public class ValSztInterfValData001Service extends CommonExportService<ValSztInterfValData001Dto>{

    /**
     * 深证通估值对账方法 从xybranch20190115版本合并过来
     * <功能详细描述>
     * @param params
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
	public Page<ValSztInterfValData001Dto> findPage(Map<String, Object> params) throws Exception{
	    return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/ValSztInterfValData/findPage",
            params, ValSztInterfValData001Dto.class);
	}

	/**
     * 获取接口参数 从xybranch20190115版本合并过来
     * <功能详细描述>
     * @param datas
     * @return
     * @see
     * @since 1.0
     */
	public Map<String, String> getParm(String prodIds) {
		Map<String, String> urlParams = new HashMap<>();
		String prodId = "";
        if(prodIds.length()>0){
        	prodId = prodIds.substring(0, prodIds.length()-1);
        }
        urlParams.put(InterfCommonConstant.接口_任务执行ID, prodId);
		return urlParams;
	}

}
