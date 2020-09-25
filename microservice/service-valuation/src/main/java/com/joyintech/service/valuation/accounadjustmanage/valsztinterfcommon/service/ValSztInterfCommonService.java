package com.joyintech.service.valuation.accounadjustmanage.valsztinterfcommon.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.accounadjustmanage.valsztinterfcommon.dao.ValSztInterfCommonMapper;
import com.joyintech.service.valuation.accounadjustmanage.valsztinterfcommon.dto.ValSztInterfCommonDto;

@Service
public class ValSztInterfCommonService extends CommonExportService<ValSztInterfCommonDto>{
	
	@Autowired
	private ValSztInterfCommonMapper mapper;

	/**
	 * 页面产品下拉框公共查询方法
	 * <功能详细描述>
	 * @param map
	 * @return
	 * @see
	 * @since 1.0
	 */
	public Page<SelectOptionDto> getFinprodId(int page, int length, Map<String, Object> map) {
		return super.queryForPageForSelectReturn(() -> mapper.getFinprodId(map));
	}

}
