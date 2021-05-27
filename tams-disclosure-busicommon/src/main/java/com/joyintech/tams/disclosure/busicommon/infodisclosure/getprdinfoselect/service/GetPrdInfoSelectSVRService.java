package com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.dao.GetPrdInfoSelectSVRMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Service
public class GetPrdInfoSelectSVRService extends BaseService {
	
	/**
	 * 分页获取产品信息
	 * 
	 * @param paraMap
	 * @return
	 * @throws Exception
	 */
	public Page<SelectOptionDto> getPrdInfoSelect(Map<String, Object> paraMap)
	        throws Exception {
        Page<SelectOptionDto> page = this.queryForPage(GetPrdInfoSelectSVRMapper.class, "getPrdInfoSelect", paraMap);
        return page;
    }

}
