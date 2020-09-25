package com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.dao.GetPrdInfoSelectMapper;
@SuppressWarnings({ "unchecked", "rawtypes" })
@Service
public class GetPrdInfoSelectService extends BaseService {
	
	/**
	 * 分页获取产品信息
	 * 
	 * @param paraMap
	 * @return
	 * @throws Exception
	 */
	public Page<SelectOptionDto> getPrdInfoSelect(Map<String, Object> paraMap)
	        throws Exception {
        Page<SelectOptionDto> page = this.queryForPage(GetPrdInfoSelectMapper.class, "getPrdInfoSelect", paraMap);
        return page;
    }

	/**
	 * 分页获取客户信息
	 * @param paraMap
	 * @return
	 */
	public Page<SelectOptionDto> getCusInfoSelect(Map<String, Object> paraMap) {
		Page<SelectOptionDto> page = this.queryForPage(GetPrdInfoSelectMapper.class, "getCusInfoSelect", paraMap);
		return page;
	}
}
