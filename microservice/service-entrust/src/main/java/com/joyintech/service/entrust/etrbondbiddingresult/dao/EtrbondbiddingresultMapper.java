package com.joyintech.service.entrust.etrbondbiddingresult.dao;

import com.joyintech.service.entrust.etrbondbiddingresult.dto.EtrbondbiddingresultDto;
import com.joyintech.service.entrust.etrfund.dao.EtrFundMapper;
import com.joyintech.tams.common.base.entity.FinProduct;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月8日
 * @see EtrFundMapper
 * @since 1.0
 */
@Repository
public interface EtrbondbiddingresultMapper {

	/**
	 *
	 * 页面查询
	 * <功能详细描述>
	 * @param map 查询参数
	 * @return List<EtrBrowDepoInfoDto>
	 * @see
	 * @since 1.0
	 */
	List<EtrbondbiddingresultDto> select(Map<String,Object> map);

	/**
	 * 查询金融产品信息通过原金融产品代码
	 * @param map
	 * @return
	 */
	List<FinProduct> findFinProductByOFinprodId(Map<String,Object> map);

	int updateTradeId(Map<String,Object> map);

	int deleteTradeId(Map<String,Object> map);
}
