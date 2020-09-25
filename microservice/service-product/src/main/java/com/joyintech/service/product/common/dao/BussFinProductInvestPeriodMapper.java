
package com.joyintech.service.product.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.product.common.dto.BussFinProductInvestPeriodDto;

/**
 * 投资周期共通Mapper
 * @author 汪敏
 * @version 1.0 2019年10月15日
 * @see BussFinProductInvestPeriodService
 * @since 1.0
 */
@Repository
public interface BussFinProductInvestPeriodMapper {
	/**
	 * 查询投资投资列表信息
	 * @param param 参数
	 * @return
	 */
	List<BussFinProductInvestPeriodDto> queryPeriodList(Map<String,Object> param);
}
