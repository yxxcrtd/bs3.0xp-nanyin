
package com.joyintech.service.product.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.common.dao.BussFinProductInvestPeriodMapper;
import com.joyintech.service.product.common.dto.BussBenchMarkInfoDto;
import com.joyintech.service.product.common.dto.BussFinProductInvestPeriodDto;
import com.joyintech.tams.common.base.dao.FinBaseRuleInfoMapper;
import com.joyintech.tams.common.base.entity.FinBaseRuleInfoKey;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;

/**
 * 投资周期共通
 * @author 汪敏
 * @version 1.0 2019年10月15日
 * @see BussFinProductInvestPeriodService
 * @since 1.0
 */
@Service("backBussFinProductInvestPeriodService")
public class BussFinProductInvestPeriodService {
	
	
	/**
	 * {@linkplain}
	 */
	@Autowired(required=false)
	private BussFinProductInvestPeriodMapper bussFinProductInvestPeriodMapper;
	
	
	/**
	 * 基准信息取得
	 * @return 基准信息
	 */
	public List<BussFinProductInvestPeriodDto> queryPeriodList(Map<String,Object> map) {
		return bussFinProductInvestPeriodMapper.queryPeriodList(map);
	}
}
