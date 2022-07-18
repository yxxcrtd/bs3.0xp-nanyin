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

	public Page<SelectOptionDto> getPrdInfoSelect(Map<String, Object> paraMap){
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


	/**
	 * 获取所有的正常状态的产品信息，包含母产品和子产品，支持根据产品代码和产品名称 进行模糊查询
	 * 支持筛选只查询 母产品 sonFlag 标识不为空
	 * @param paraMap
	 * @return
	 */
	public Page<SelectOptionDto> getAllPrdInfoSelect(Map<String, Object> paraMap){
		Page<SelectOptionDto> page = this.queryForPage(GetPrdInfoSelectMapper.class, "getAllPrdInfoSelect", paraMap);
		return page;
	}
}
