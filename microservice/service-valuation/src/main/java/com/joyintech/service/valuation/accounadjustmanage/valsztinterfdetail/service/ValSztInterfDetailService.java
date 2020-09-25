package com.joyintech.service.valuation.accounadjustmanage.valsztinterfdetail.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.accounadjustmanage.valsztinterfdetail.dao.ValSztInterfDetailMapper;
import com.joyintech.service.valuation.accounadjustmanage.valsztinterfdetail.dto.ValSztInterfDetailDto;

@Service
public class ValSztInterfDetailService extends CommonExportService<ValSztInterfDetailDto>{
	
	@Autowired
	private ValSztInterfDetailMapper mapper;


	/**
	 * 深证通对账结果明细页面table查询 从xybranch20190115版本合并过来
	 * <功能详细描述>
	 * @param params
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	public Page<ValSztInterfDetailDto> findPage(int page, int length, Map<String, Object> params) throws Exception{
	    return super.queryForPage(page, length, () -> mapper.queryPageList(params));
    }

}
