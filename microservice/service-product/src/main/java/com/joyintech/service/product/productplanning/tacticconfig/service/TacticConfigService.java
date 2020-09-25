package com.joyintech.service.product.productplanning.tacticconfig.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.productplanning.tacticconfig.dao.TacticConfigMapper;
import com.joyintech.service.product.productplanning.tacticconfig.dto.TacticConfig001Dto;
import com.joyintech.service.product.productplanning.tacticconfig.dto.TacticConfig002Dto;
import com.joyintech.tams.common.base.dao.TacticConfigOptDetailMapper;
import com.joyintech.tams.common.base.dao.TacticConfigOptMapper;
import com.joyintech.tams.common.base.entity.TacticConfigOptDetail;
/**
 * yaozhenguo 
 * @author Administrator
 *	20191228 战术配置优化
 */
@Service
public class TacticConfigService extends BaseService<TacticConfig001Dto> {

	@Autowired
	private TacticConfigMapper tacticConfigMapper;
	@Autowired
	private TacticConfigOptMapper tacticConfigOptMapper;
	@Autowired
	private TacticConfigOptDetailMapper tacticConfigOptDetailMapper;

	/**
	 * 资产大类下拉框
	 * 
	 * @param param
	 * @param page
	 * @param length
	 * @return
	 */
	public Page<SelectOptionDto> selectAllAssetType(Map<String, Object> param, int page, int length) {
		return queryForPageForSelectReturn(page, length, () -> {
			tacticConfigMapper.selectAllAssetType(param);
		});
	}

	/**
	 * 战术配置优化插入
	 * 
	 * @param param
	 */
	public void insertTacticConfig(TacticConfig002Dto tacticConfig002Dto) {
		tacticConfigMapper.insertTacticConfigOpt(tacticConfig002Dto);
		if (!StringUtil.isEmptyList(tacticConfig002Dto.getTacticConfigOptDetails())) {
			// 战术配置优化明细列表插入
			for (TacticConfigOptDetail tacticConfigOptDetail : tacticConfig002Dto.getTacticConfigOptDetails()) {
				tacticConfigOptDetail.setTacticConfigCode(tacticConfig002Dto.getTacticConfigCode());
				tacticConfigOptDetailMapper.insert(tacticConfigOptDetail);
			}
		}
	}

	/**
	 * 战术配置修改
	 * 
	 * @param tacticConfig002Dto
	 */
	public void updateTacTicConfig(TacticConfig002Dto tacticConfig002Dto) {
		// 删除战术配置优化
		tacticConfigOptMapper.deleteByPrimaryKey(tacticConfig002Dto.getTacticConfigCode());
		// 删除战术配置优化明细
		tacticConfigMapper.deleteTacticConfigByCode(tacticConfig002Dto.getTacticConfigCode());
		// 插入
		insertTacticConfig(tacticConfig002Dto);
	}

	/**
	 * 删除资产明细
	 * 
	 * @param tacticConfig002Dto
	 */
	public void deleteTacticConfig(TacticConfig001Dto tacticConfig001Dto) {
		// 删除战术配置优化
		tacticConfigOptMapper.deleteByPrimaryKey(tacticConfig001Dto.getTacticConfigCode());
		// 删除战术配置优化明细
		tacticConfigMapper.deleteTacticConfigByCode(tacticConfig001Dto.getTacticConfigCode());
		
		/*tacticConfigMapper.deleteTacticConfig(tacticConfig001Dto);
		List<TacticConfigOptDetail> list = queryByCode(tacticConfig001Dto.getTacticConfigCode());
		// 如果明细为空，删除战术配置
		if (StringUtil.isEmptyList(list)) {
			tacticConfigOptMapper.deleteByPrimaryKey(tacticConfig001Dto.getTacticConfigCode());
		}*/
	}

	/**
	 * 根据资产配置
	 * 
	 * @param code
	 * @return
	 */
	public List<TacticConfigOptDetail> queryByCode(String tacticConfigCode) {
		List<TacticConfigOptDetail> list = tacticConfigMapper.queryByCode(tacticConfigCode);
		if (!StringUtil.isEmptyList(list)) {
			// 乘100转化为百分数
			for (TacticConfigOptDetail tacticConfigOptDetail : list) {
				tacticConfigOptDetail.setTheoConfigRate(
						ArithmeticUtil.multiply(tacticConfigOptDetail.getTheoConfigRate(), new BigDecimal(100)));
				tacticConfigOptDetail.setSamleAverRate(
						ArithmeticUtil.multiply(tacticConfigOptDetail.getSamleAverRate(), new BigDecimal(100)));
				tacticConfigOptDetail
						.setVix(ArithmeticUtil.multiply(tacticConfigOptDetail.getVix(), new BigDecimal(100)));
				tacticConfigOptDetail
						.setCorRate(ArithmeticUtil.multiply(tacticConfigOptDetail.getCorRate(), new BigDecimal(100)));
				tacticConfigOptDetail
						.setCorRatio(ArithmeticUtil.multiply(tacticConfigOptDetail.getCorRatio(), new BigDecimal(100)));
			}
		}
		return list;
	}

	/**
	 * 列表页查询
	 * 
	 * @param param
	 * @param page
	 * @param length
	 * @return
	 */
	public Page<TacticConfig001Dto> selectForPage(Map<String, Object> param, int page, int length) {
		return queryForPage(page, length, () -> {
			tacticConfigMapper.selectForPage(param);
		});
	}

	/**
	 * 查询最新一条战术配置
	 * 
	 * @return
	 */
	public TacticConfig002Dto selectFirstTacticConfigOpt() {
		return tacticConfigMapper.selectFirstTacticConfigOpt();
	}

	/**
	 * 根据战术配置ID查询战术配置
	 * @param tacticConfigCode
	 * @return
	 */
	public TacticConfig002Dto selectByTacticConfigCode(String tacticConfigCode) {
		return tacticConfigMapper.selectByTacticConfigCode(tacticConfigCode);
	}
}
