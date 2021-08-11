package com.joyintech.service.valuation.lossmodel.pddefaultmanager.pdfactorconfig.service;

import java.util.List;
import java.util.Map;

import com.joyintech.service.valuation.lossmodel.pddefaultmanager.pdfactorconfig.dao.ModPdFactorConfigInfoMapper;
import com.joyintech.service.valuation.lossmodel.pddefaultmanager.pdfactorconfig.dto.ModPdFactorConfigInfoDto;
import com.joyintech.service.valuation.lossmodel.pddefaultmanager.pdfactorconfig.dto.ModPdFactorWeightConfigDto;
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
 * @see ModPdFactorConfigInfoService
 * @since 1.0
 */
@Service
@Transactional
public class ModPdFactorConfigInfoService extends BaseService<ModPdFactorConfigInfoDto>{
	
	@Autowired
	private ModPdFactorConfigInfoMapper modPdFactorConfigInfoMapper ;
	
	/**
	 * 查询页面
	 * @param page
	 * @param length
	 * @param pageParam
	 * @return
	 */
	public Page<ModPdFactorConfigInfoDto> selectForPage(int page, int length, Map<String, Object> pageParam) 
			throws Exception{
		return super.queryForPage(page, length, () -> modPdFactorConfigInfoMapper.selectForPage(pageParam));
	}
	/**
	 * 查询是否存在年份+季度一致的信息
	 * @param map
	 * @return
	 */
	public List<ModPdFactorConfigInfoDto> selectByYearAndQuarter(Map<String, Object> map) {
		return modPdFactorConfigInfoMapper.selectByYearAndQuarter(map);
	}
	/**
	 * 根据主键查询信息
	 * @param param
	 * @return
	 */
	public ModPdFactorConfigInfoDto selectByPrimaryKey(Map<String, Object> param) {
		return modPdFactorConfigInfoMapper.selectByPrimaryKey(param);
	}
	/**
	 * 插入数据
	 * @param dto
	 * @return
	 */
	public int insert(ModPdFactorConfigInfoDto dto) {
		return modPdFactorConfigInfoMapper.insertSelective(dto);
	}
	/**
	 * 更新数据
	 * @param dto
	 * @return
	 */
	public int update(ModPdFactorConfigInfoDto dto) {
		return modPdFactorConfigInfoMapper.updateByPrimaryKey(dto);
	}
	/**
	 * 删除数据
	 * @param dto
	 * @return
	 */
	public int delete(ModPdFactorConfigInfoDto dto) {
		return modPdFactorConfigInfoMapper.deleteByPrimaryKey(dto);
	}
	/**
	 * 导出页面数据
	 * @param map
	 * @return
	 */
	public List<ModPdFactorConfigInfoDto> selectForExport(Map<String, Object> map) {
		return modPdFactorConfigInfoMapper.selectForPage(map);
	}
	
	/**
	 * 查询最新权重配置信息
	 * @param param
	 * @return
	 */
	public ModPdFactorWeightConfigDto findLastWeightInfo(Map<String, Object> param) {
		return modPdFactorConfigInfoMapper.selectLastWeightInfo(param);
	}
	/**
	 * 权重配置数据插入
	 * @param dto
	 * @return
	 */
	public int insertWeight(ModPdFactorWeightConfigDto dto) {
		modPdFactorConfigInfoMapper.deletePdWeight();
		return modPdFactorConfigInfoMapper.insertWeightSelective(dto);
	}
	
	/**
	 * 
	 */
	public int checkBySeqNo(Map<String, Object> param) {
		return modPdFactorConfigInfoMapper.checkBySeqNo(param);
	}
	
	
	
	
	
	
	
	
}
