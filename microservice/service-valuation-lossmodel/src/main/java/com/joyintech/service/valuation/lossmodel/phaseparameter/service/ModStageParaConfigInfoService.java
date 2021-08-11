package com.joyintech.service.valuation.lossmodel.phaseparameter.service;

import java.util.List;
import java.util.Map;

import com.joyintech.service.valuation.lossmodel.phaseparameter.dao.ModStageParaConfigInfoMapper;
import com.joyintech.service.valuation.lossmodel.phaseparameter.dto.ModStageParaConfigInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;

/**
 * 减值阶段参数管理service
 *
 * @author zhangfei
 * @version 1.0 2021年6月24日
 * @see ModStageParaConfigInfoService
 * @since 1.0
 */
@Service
@Transactional
public class ModStageParaConfigInfoService extends BaseService<ModStageParaConfigInfoDto>{
	
	@Autowired
	private ModStageParaConfigInfoMapper modStageParaConfigInfoMapper;
	
	/**
	 * 分页查询
	 * @param page
	 * @param length
	 * @param pageParam
	 * @return
	 */
	public Page<ModStageParaConfigInfoDto> selectForPage(int page, int length, Map<String, Object> pageParam) {
		return super.queryForPage(page, length, () -> modStageParaConfigInfoMapper.selectForPage(pageParam));
	}
	/**
	 * 通过主键查看参数详情
	 * @param param
	 * @return
	 */
	public ModStageParaConfigInfoDto selectByPrimaryKey(Map<String, Object> param) {
		return modStageParaConfigInfoMapper.selectByPrimaryKey(param);
	}
	
	/**
	 * 根据减值阶段和要素查询参数值
	 * 
	 */
	public ModStageParaConfigInfoDto selectByStageAndElements(Map<String, Object> param) {
		return modStageParaConfigInfoMapper.selectByStageAndElements(param);
	}
	
	/**
	 * 更新数据
	 * @param dto
	 * @return
	 */
	public int update(ModStageParaConfigInfoDto dto) {
		return modStageParaConfigInfoMapper.updateByPrimaryKey(dto);
	}
	/**
	 * 导出查询数据
	 * @param map
	 * @return
	 */
	public List<ModStageParaConfigInfoDto> selectForExport(Map<String, Object> map) {
		return modStageParaConfigInfoMapper.selectForPage(map);
	}
	
	
	
	
	
}	
