package com.joyintech.service.valuation.accounadjustmanage.valsztinterfbalance.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.accounadjustmanage.valsztinterfbalance.dao.ValSztInterfBalanceMapper;
import com.joyintech.service.valuation.accounadjustmanage.valsztinterfbalance.dto.ValSztInterfBalanceDto;

@Service
public class ValSztInterfBalanceService extends CommonExportService<ValSztInterfBalanceDto>{
	
	@Autowired
	private ValSztInterfBalanceMapper mapper;


	/**
	 * 深证通科目余额对账table查询 从xybranch20190115版本合并过来
	 * <功能详细描述>
	 * @param params
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	public Page<ValSztInterfBalanceDto> findPage(int page, int length, Map<String, Object> params) throws Exception{
	    return super.queryForPage(page, length, () -> mapper.queryPageList(params));
    }

}
