package com.joyintech.tams.report.queryanalysis.equitysharecheck.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.queryanalysis.equitysharecheck.dao.EquityShareCheck001Mapper;
import com.joyintech.tams.report.queryanalysis.equitysharecheck.dto.EquityShareCheck001Dto;

/**
 * 投资者份额检查service
 * 
 * @author zhengxiaolian
 * @version 1.0 2019年11月12日
 * @see EquityShareCheck001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EquityShareCheck001Service extends CommonExportService<EquityShareCheck001Dto> {

	@Autowired
	private EquityShareCheck001Mapper equityShareCheck001Mapper;

	private static Logger logger = LoggerFactory.getLogger(EquityShareCheck001Service.class);

	/**
	 * 页面查询
	 * 
	 * @param pageParam
	 *            查询参数
	 * @return 结果列表
	 * @see
	 * @since 1.0
	 */
	public Page<EquityShareCheck001Dto> searchPage(Map<String, Object> pageParam) {
		formatSearchCon(pageParam);
        int pagenum = StringUtil.integerValueOf(""+pageParam.get(GlobalConstant.PARAM_PAGE), 1);
        int length = StringUtil.integerValueOf(""+pageParam.get(GlobalConstant.PARAM_LENGTH), GlobalConstant.DEFAULT_PAGE_SIZE);
        Page<EquityShareCheck001Dto>page=queryForPage(pagenum,length,()->equityShareCheck001Mapper.searchPage(pageParam));
		//Page<EquityShareCheck001Dto> page = queryForPage(EquityShareCheck001Mapper.class, "searchPage", pageParam);
		this.formatSearchResult(page.getRoot());
		return page;
	}

	/**
	 * 查询产品名称
	 * 
	 * @param param
	 *            参数
	 * @return 结果list
	 * @see
	 * @since 1.0
	 */
	public List<SelectOptionDto> queryPrdNameList(Map<String, Object> param) {
		return equityShareCheck001Mapper.queryPrdNameList(param);
	}

	/**
	 * 查询识别标识
	 * 
	 * @param param
	 *            参数
	 * @return 结果list
	 * @see
	 * @since 1.0
	 */
	public List<SelectOptionDto> queryIndentifyMarkList(Map<String, Object> param) {
		return equityShareCheck001Mapper.queryIndentifyMarkList(param);
	}

	/**
	 * 格式化查询条件
	 *
	 * @param map
	 *            查询条件
	 * @see
	 * @since 1.0
	 */
	private void formatSearchCon(Map<String, Object> map) {
		// 产品登记编码
		String prdRegistCode = (String) map.get("prdRegistCode");
		if (StringUtil.isNotBlank(prdRegistCode)) {
			map.put("prdRegistCodeList", StringUtil.splitToList(GlobalConstant.COMMA, prdRegistCode));
		}
		// 产品
		String prdCode = (String) map.get("prdCode");
		if (StringUtil.isNotBlank(prdCode)) {
			map.put("prdCodeList", StringUtil.splitToList(GlobalConstant.COMMA, prdCode));
		}
		// 识别标识
		String indentifyMark = (String) map.get("indentifyMark");
		map.put("indentifyMark",indentifyMark);
//		if (StringUtil.isNotBlank(indentifyMark)) {
//			map.put("indentifyMarkList", StringUtil.splitToList(GlobalConstant.COMMA, indentifyMark));
//		}
	}

	/**
	 * 导出数据来源
	 * 
	 * @param map
	 *            参数
	 * @return 结果
	 * @see
	 * @since 1.0
	 */
	public List<EquityShareCheck001Dto> setDataList(Map<String, Object> map) {
		List<EquityShareCheck001Dto> resultList = new ArrayList<>();
		String keyList = (String) map.get("keyList");
		if (StringUtil.isNotEmpty(keyList)) {
			try {
				resultList = JsonUtils.json2List(keyList, EquityShareCheck001Dto.class);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				return null;
			}
		} else {
			formatSearchCon(map);
			resultList = equityShareCheck001Mapper.searchPage(map);
			this.formatSearchResult(resultList);
		}
		return resultList;
	}

	/**
	 * 格式化查询结果
	 *
	 * @param resultList
	 *            查询结果
	 * @see
	 * @since 1.0
	 */
	private void formatSearchResult(List<EquityShareCheck001Dto> resultList) {
		if (StringUtil.isEmptyList(resultList)) {
			return;
		}

		for (EquityShareCheck001Dto equityShareCheck001Dto : resultList) {
			equityShareCheck001Dto.setCcy(SysDataUtil
					.getDataDictItem(DictCode.币种_中债.getValue(), equityShareCheck001Dto.getCcy()).getItemValue());
		}
	}
}
