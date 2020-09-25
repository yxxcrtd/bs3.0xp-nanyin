package com.joyintech.service.trade.primarybiddingmanage.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.service.trade.primarybiddingmanage.dto.PrimaryBiddingManageDto;


/**
 * 指令接收mapper
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author sjj
 * @version 1.0 2019年05月08日
 * @see PrimaryBiddingManage001Mapper
 * @since 1.0
 */

public interface PrimaryBiddingManage001Mapper {

	/**
	 * 指令查询
	 * @param map
	 * @return
	 */
	List<PrimaryBiddingManageDto> selectForPage(Map<String, Object> param);

	/**
	 * 通过资产代码和交易对手查询记录
	 * @param param
	 * @return
	 */
	List<PrimaryBiddingManageDto> findDataByFinprodIdAndCounterId(Map<String, Object> param);
}
