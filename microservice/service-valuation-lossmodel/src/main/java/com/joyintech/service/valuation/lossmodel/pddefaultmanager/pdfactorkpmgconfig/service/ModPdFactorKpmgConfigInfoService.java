package com.joyintech.service.valuation.lossmodel.pddefaultmanager.pdfactorkpmgconfig.service;

import java.util.List;
import java.util.Map;

import com.joyintech.service.valuation.lossmodel.pddefaultmanager.pdfactorkpmgconfig.dao.ModPdFactorKpmgConfigInfoMapper;
import com.joyintech.service.valuation.lossmodel.pddefaultmanager.pdfactorkpmgconfig.dto.ModPdFactorKpmgConfigInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
/**
 * PD前瞻因子维护查询service
 *
 * @author zhangfei
 * @version 1.0 2021年6月24日
 * @see ModPdFactorKpmgConfigInfoService
 * @since 1.0
 */
@Service
@Transactional
public class ModPdFactorKpmgConfigInfoService extends BaseService<ModPdFactorKpmgConfigInfoDto>{
	
	@Autowired
	private ModPdFactorKpmgConfigInfoMapper modPdFactorKpmgConfigInfoMapper ;
	
	/**
	 * 查询页面
	 * @param page
	 * @param length
	 * @param pageParam
	 * @return
	 */
	public Page<ModPdFactorKpmgConfigInfoDto> selectForPage(int page, int length, Map<String, Object> pageParam)
			throws Exception{
		return super.queryForPage(page, length, () -> modPdFactorKpmgConfigInfoMapper.selectForPage(pageParam));
	}
	/**
	 * 查询是否存在年份+季度一致的信息
	 * @param map
	 * @return
	 */
	public List<ModPdFactorKpmgConfigInfoDto> selectByYear(Map<String, Object> map) {
		return modPdFactorKpmgConfigInfoMapper.selectByYear(map);
	}
	/**
	 * 根据主键查询信息
	 * @param param
	 * @return
	 */
	public ModPdFactorKpmgConfigInfoDto selectByPrimaryKey(Map<String, Object> param) {
		return modPdFactorKpmgConfigInfoMapper.selectByPrimaryKey(param);
	}
	/**
	 * 插入数据
	 * @param dto
	 * @return
	 */
	public int insert(ModPdFactorKpmgConfigInfoDto dto) {
		return modPdFactorKpmgConfigInfoMapper.insertSelective(dto);
	}
	/**
	 * 更新数据
	 * @param dto
	 * @return
	 */
	public int update(ModPdFactorKpmgConfigInfoDto dto) {
		return modPdFactorKpmgConfigInfoMapper.updateByPrimaryKey(dto);
	}
	/**
	 * 删除数据
	 * @param dto
	 * @return
	 */
	public int delete(ModPdFactorKpmgConfigInfoDto dto) {
		return modPdFactorKpmgConfigInfoMapper.deleteByPrimaryKey(dto);
	}
	/**
	 * 导出页面数据
	 * @param map
	 * @return
	 */
	public List<ModPdFactorKpmgConfigInfoDto> selectForExport(Map<String, Object> map) {
		return modPdFactorKpmgConfigInfoMapper.selectForPage(map);
	}
	

	/**
	 * 
	 */
	public int checkBySeqNo(Map<String, Object> param) {
		return modPdFactorKpmgConfigInfoMapper.checkBySeqNo(param);
	}
	
	
	
	
	
	
	
	
}
