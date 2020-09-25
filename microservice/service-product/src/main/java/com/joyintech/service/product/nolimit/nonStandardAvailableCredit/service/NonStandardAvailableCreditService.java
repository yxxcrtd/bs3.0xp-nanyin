package com.joyintech.service.product.nolimit.nonStandardAvailableCredit.service;

import java.math.BigDecimal;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.service.product.messageadjust.bmquotation.service.FinProductBmQuotationService;
import com.joyintech.service.product.nolimit.common.dao.NolimitCommonMapper;
import com.joyintech.service.product.nolimit.nonStandardAvailableCredit.dao.NonStandardAvailableCreditMapper;
import com.joyintech.service.product.nolimit.nonStandardAvailableCredit.dto.NonStandardAvailableCreditDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 非标可用额度查询 〈功能详细描述〉
 * 
 * @author dongzhikang
 * @version 1.0
 * @see nonStandardAvailableCreditService
 * @since 1.0
 */

@Service("nonStandardAvailableCreditService")
public class NonStandardAvailableCreditService extends BaseService<NonStandardAvailableCreditDto> {
	@Autowired
	NonStandardAvailableCreditMapper nonStandardAvailableCreditMapper;
	@Autowired
	NolimitCommonMapper nolimitCommonMapper;

	/**
	 * <一句话功能简述>
	 * 
	 * <功能详细描述>
	 * 
	 * @param parameters
	 * @return
	 * @see
	 * @since 1.0
	 */
	public Page<NonStandardAvailableCreditDto> selectForPage(int page, int length, Map<String, Object> parameters) {
		Page<NonStandardAvailableCreditDto> pages = super.queryForPage(page, length,
				() -> nonStandardAvailableCreditMapper.selectForPage(parameters));
		BigDecimal danwei = new BigDecimal("1");
		BigDecimal bili = new BigDecimal("100");
		List<NonStandardAvailableCreditDto> pagelist = pages.getRoot();
		if (pagelist.size() > 0) {
			Date cudate = DateUtil.parse((String) parameters.get("curveDate"));
			String cdate = DateUtil.format(pagelist.get(0).getBenchDate());
			String cudate2 = (String) parameters.get("curveDate");
			pagelist.get(0).setCurveDate(cudate);
			/*
			 * 获取当前总资产
			 * 
			 */

			Map<String, Object> param = new HashMap<>();

			param.put("cdate", cudate2);
			BigDecimal pageCutotalAssets = nolimitCommonMapper.getShareAmt(param);
			if (pageCutotalAssets != null) {
				pagelist.get(0).setCutotalAssets(pageCutotalAssets);
			}

			/*
			 * 获取当前非标总资产
			 * 
			 */

			Map<String, Object> param2 = new HashMap<>();
			param2.put("isNonstandard", DictCodeListExt1.是否_是.getValue());
			param2.put("cdate", cudate2);
			BigDecimal pageCuNonStandardTotalAssets = nolimitCommonMapper.getShareAmt(param2);
			if (pageCuNonStandardTotalAssets != null) {
				pagelist.get(0).setCuNonStandardTotalAssets(pageCuNonStandardTotalAssets);

			}

			/*
			 * 获取当前非标总可用限额
			 * 
			 * 
			 */

			Map<String, Object> param3 = new HashMap<>();
			param3.put("effdate", cudate);
			List<Map<String, Object>> map = nolimitCommonMapper.queryLimitAmt(param3);
			Map<String, Object> getmap = map.get(0);
			BigDecimal pagenonStandardLimit = (BigDecimal) getmap.get("NON_STANDARD_LIMIT");
			BigDecimal getpagenonStandardRatio = (BigDecimal) getmap.get("NON_STANDARD_ROTIO");
			BigDecimal pagenonStandardRatio = getpagenonStandardRatio.divide(bili);

			if (pagenonStandardLimit != null && pageCuNonStandardTotalAssets != null) {
				BigDecimal pageCuNonStandardTotalLimit = pagenonStandardLimit.subtract(pageCuNonStandardTotalAssets);

				pagelist.get(0).setCuNonStandardTotalLimit(pageCuNonStandardTotalLimit);
			}

			/*
			 * 获取计划资产总量
			 * 
			 */
			BigDecimal totalPlannedAssets = pagelist.get(0).getTargetScale();
			/* pagelist.get(0).setTotalPlannedAssets(totalPlannedAssets); */
			if (totalPlannedAssets != null && pagenonStandardRatio != null) {
				BigDecimal gettotalPlannedAssets = totalPlannedAssets.multiply(danwei);
				pagelist.get(0).setTotalPlannedAssets(gettotalPlannedAssets);
				BigDecimal NontotalPlannedAssets = gettotalPlannedAssets.multiply(pagenonStandardRatio);

				/*
				 * 
				 * 计划非标总可用限额 = （计划资产总量*变量.非标比例）-当前非标总资产
				 */

				if (pageCuNonStandardTotalAssets != null) {
					BigDecimal pagePlanNonStandTotalLimit = NontotalPlannedAssets
							.subtract(pageCuNonStandardTotalAssets);
					pagelist.get(0).setPlanNonStandTotalLimit(pagePlanNonStandTotalLimit);
				}
			}

			pages.setRoot(pagelist);
		}
		return pages;
	}
}
