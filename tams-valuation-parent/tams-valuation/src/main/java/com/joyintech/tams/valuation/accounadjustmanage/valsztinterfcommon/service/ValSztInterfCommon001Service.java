package com.joyintech.tams.valuation.accounadjustmanage.valsztinterfcommon.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.accounadjustmanage.valsztinterfcommon.dto.ValSztInterfCommon001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;

@Service
public class ValSztInterfCommon001Service extends CommonExportService<ValSztInterfCommon001Dto>{
	

	/**
	 * 页面产品下拉框公共查询方法
	 * <功能详细描述>
	 * @param map
	 * @return
	 * @see
	 * @since 1.0
	 */
	public Page<SelectOptionDto> getFinprodId(Map<String, Object> map) {
        map.put("finprodType",DictCodeListExt1.金融产品类型_理财产品.getValue());
	    return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/ValSztInterfCommon/getFinprodId",
	        map, SelectOptionDto.class);
	}

}
