package com.joyintech.service.trade.primarybiddingmanage.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.service.trade.primarybiddingmanage.dto.PrimaryBiddingManageDto;
import com.joyintech.service.trade.primarybiddingmanage.dto.PrimaryBiddingPortfolioDto;


/**
 * 指令接收mapper
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author sjj
 * @version 1.0 2019年05月08日
 * @see PrimaryBiddingManage002Mapper
 * @since 1.0
 */

public interface PrimaryBiddingManage002Mapper {

	/**
	 * 获取entity通过参数
	 * <功能详细描述>
	 * @param map  参数
	 * @return 筛选结果
	 * @see
	 * @since 1.0
	 */
	PrimaryBiddingManageDto getEntityByMap(Map<String, Object> map);

	/**
	 * 获取投标组合列表
	 * <功能详细描述>
	 * @param map  参数
	 * @return 筛选结果
	 * @see
	 * @since 1.0
	 */
	List<PrimaryBiddingPortfolioDto> getPrimaryBiddingPortfoliolist(Map<String, Object> map);

	/**
	 * 获取汇总投标列表
	 * <功能详细描述>
	 * @param map  参数
	 * @return 筛选结果
	 * @see
	 * @since 1.0
	 */
	List<PrimaryBiddingPortfolioDto> getPrimaryBiddingAlllist(Map<String, Object> map);
	
	/**
	 * 获取指令状态
	 * <功能详细描述>
	 * @param map  参数
	 * @return 筛选结果
	 * @see
	 * @since 1.0
	 */
	PrimaryBiddingManageDto getPrimaryBiddingManageOriginal(Map<String, Object> map);

}
