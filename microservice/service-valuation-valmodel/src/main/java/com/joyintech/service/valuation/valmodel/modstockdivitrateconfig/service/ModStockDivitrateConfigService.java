package com.joyintech.service.valuation.valmodel.modstockdivitrateconfig.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.dto.StockQuotationDto;
import com.joyintech.service.valuation.valmodel.modstockdivitrateconfig.dao.ModStockDivitrateConfigMapper;
import com.joyintech.service.valuation.valmodel.modstockdivitrateconfig.dto.ModStockDivitrateConfigInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;

/**
 * 预期股息增长率配置查询service
 *
 * @author zhangfei
 * @version 1.0 2021年7月1日18:52:32
 * @see ModStockDivitrateConfigService
 * @since 1.0
 */
@Service
@Transactional
public class ModStockDivitrateConfigService extends BaseService<ModStockDivitrateConfigInfoDto> {
	
	
	@Autowired
	private ModStockDivitrateConfigMapper modStockDivitrateConfigMapper;
	/**
	 * 分页查询数据
	 * @param page
	 * @param length
	 * @param pageParam
	 * @return
	 */
	public Page<ModStockDivitrateConfigInfoDto> selectForPage(int page, int length, Map<String, Object> pageParam) throws Exception{
		return super.queryForPage(page, length, () -> modStockDivitrateConfigMapper.selectForPage(pageParam));
	}
	
	/**
	 * 根据主键查询信息
	 * @param param
	 * @return
	 */
	public ModStockDivitrateConfigInfoDto selectByPrimaryKey(Map<String, Object> param) {
		return modStockDivitrateConfigMapper.selectByPrimaryKey(param);
	}
	/**
	 * 插入数据
	 * @param dto
	 * @return
	 */
	public int insert(ModStockDivitrateConfigInfoDto dto) {
		if(StringUtil.isNotEmpty(dto.getStockIntarte())){
			dto.setStockIntarte(dto.getStockIntarte().divide(new BigDecimal(100+"")));
		}
		return modStockDivitrateConfigMapper.insertSelective(dto);
	}
	/**
	 * 更新数据
	 * @param dto
	 * @return
	 */
	public int update(ModStockDivitrateConfigInfoDto dto) {
		if(StringUtil.isNotEmpty(dto.getStockIntarte())){
			dto.setStockIntarte(dto.getStockIntarte().divide(new BigDecimal(100+"")));
		}
		return modStockDivitrateConfigMapper.updateByPrimaryKey(dto);
	}
	/**
	 * 删除数据
	 * @param dto
	 * @return
	 */
	public int delete(ModStockDivitrateConfigInfoDto dto) {
		return modStockDivitrateConfigMapper.deleteByPrimaryKey(dto);
	}
	/**
	 * 导出页面数据
	 * @param map
	 * @return
	 */
	public List<ModStockDivitrateConfigInfoDto> selectForExport(Map<String, Object> map) {
		return modStockDivitrateConfigMapper.selectForPage(map);
	}
	
	/**
	 * 新建校验唯一资产代码
	 */
	public int checkByFinprodId(Map<String, Object> param) {
		return modStockDivitrateConfigMapper.checkByFinprodId(param);
	}


	/**
	 * 查询标的信息：不设置参数的话查所有的
	 * @author zhangfei
	 * @param
	 * @date 2021年7月1日
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<SelectOptionDto> getAssetCodeList(Map<String, Object> map,int page, int length) {
		return this.queryForPageForSelectReturn(page, length,()->modStockDivitrateConfigMapper.selectAssetCodeList(map));
	}
	
	
}
