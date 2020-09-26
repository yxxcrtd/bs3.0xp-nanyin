package com.joyintech.tams.valuation.accounadjustmanage.valsztinterfdetail.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.valuation.accounadjustmanage.valsztinterfdetail.dto.ValSztInterfDetail001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;

@Service
public class ValSztInterfDetail001Service extends CommonExportService<ValSztInterfDetail001Dto>{

    /**
     * 深证通对账结果明细界面查询方法 从xybranch20190115版本合并过来
     * <功能详细描述>
     * @param params
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
	public Page<ValSztInterfDetail001Dto> findPage(Map<String, Object> params) throws Exception{
	    return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/ValSztInterfDetail/findPage",
            params, ValSztInterfDetail001Dto.class);
	}
}
