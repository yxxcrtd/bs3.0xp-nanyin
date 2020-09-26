package com.joyintech.tams.report.queryanalysis.prosharecheck.service;

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
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.queryanalysis.prosharecheck.dao.ProShareCheck001Mapper;
import com.joyintech.tams.report.queryanalysis.prosharecheck.dto.ProShareCheck001Dto;

/**
 * 产品份额检查service
 * 
 * @author zhengxiaolian
 * @version 1.0 2019年11月12日
 * @see EquityShareCheck001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProShareCheck001Service extends CommonExportService<ProShareCheck001Dto> {
	@Autowired
	private ProShareCheck001Mapper proShareCheck001Mapper;

	private static Logger logger = LoggerFactory.getLogger(ProShareCheck001Service.class);

	/**
	 * 页面查询
	 * 
	 * @param pageParam
	 *            查询参数
	 * @return 结果列表
	 * @see
	 * @since 1.0
	 */
	public Page<ProShareCheck001Dto> searchPage(Map<String, Object> pageParam) {
		formatSearchCon(pageParam);
		int pagenum = StringUtil.integerValueOf(""+pageParam.get(GlobalConstant.PARAM_PAGE), 1);
        int length = StringUtil.integerValueOf(""+pageParam.get(GlobalConstant.PARAM_LENGTH), GlobalConstant.DEFAULT_PAGE_SIZE);
        
        Page<ProShareCheck001Dto> page = queryForPage(pagenum,length,()->proShareCheck001Mapper.searchPage(pageParam));
//		Page<ProShareCheck001Dto> page = queryForPage(ProShareCheck001Mapper.class, "searchPage", pageParam);
		formatSearchResult(page.getRoot());
		return page;
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
	public List<ProShareCheck001Dto> setDataList(Map<String, Object> map) {
		List<ProShareCheck001Dto> resultList = new ArrayList<>();
		String keyList = (String) map.get("keyList");
		if (StringUtil.isNotEmpty(keyList)) {
			try {
				resultList = JsonUtils.json2List(keyList, ProShareCheck001Dto.class);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				return null;
			}
		} else {
			formatSearchCon(map);
			resultList = proShareCheck001Mapper.searchPage(map);
			this.formatSearchResult(resultList);
		}
		return resultList;
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
	}

	/**
	 * 格式化查询结果
	 *
	 * @param resultList
	 *            查询结果
	 * @see
	 * @since 1.0
	 */
	private void formatSearchResult(List<ProShareCheck001Dto> resultList) {
		if (StringUtil.isEmptyList(resultList)) {
			return;
		}

		for (ProShareCheck001Dto proShareCheck001Dto : resultList) {
			proShareCheck001Dto.setCcy(SysDataUtil
					.getDataDictItem(DictCode.币种_中债.getValue(), proShareCheck001Dto.getCcy()).getItemValue());
			proShareCheck001Dto.setHoldDateStr(DateUtil.format(proShareCheck001Dto.getholdDate()));
		}
	}

}
