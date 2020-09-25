package com.joyintech.service.valuation.accounadjustmanage.valsztinterfvaldata.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.accounadjustmanage.valsztinterfvaldata.dao.ValSztInterfValDataMapper;
import com.joyintech.service.valuation.accounadjustmanage.valsztinterfvaldata.dto.ValSztInterfValDataDto;

@Service
public class ValSztInterfValDataService extends CommonExportService<ValSztInterfValDataDto>{
	
	@Autowired
	private ValSztInterfValDataMapper mapper;


	/**
	 * 深证通估值对账页面table查询 从xybranch20190115版本合并过来
	 * <功能详细描述>
	 * @param params
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	public Page<ValSztInterfValDataDto> findPage(int page, int length, Map<String, Object> params) throws Exception{
	    return super.queryForPage(page, length, () -> mapper.queryPageList(params));
    }

}
