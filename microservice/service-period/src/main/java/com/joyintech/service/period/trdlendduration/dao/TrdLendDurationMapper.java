package com.joyintech.service.period.trdlendduration.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.period.trdlendduration.dto.TrdLendDurationDto;

/**
 * 
 * 拆借存续期查询Mapper
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2018年9月15日
 * @see TrdLendDurationMapper
 * @since 1.0
 */
@Repository
public interface TrdLendDurationMapper {

    /**
     * 查询组合代码
     * <功能详细描述>
     * @param map 参数
     * @return 结果值
     * @see
     * @since 1.0
     */
	List<SelectOptionDto> getPortfolioId(Map<String, Object> map);
    /**
     * 交易对手下拉菜单
     * <功能详细描述>
     * @param map 参数
     * @return 结果值
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getCounterId(Map<String, Object> map);
	/**
	 * 查询
	 * <功能详细描述>
	 * @param map 参数
	 * @return 查询结果
	 * @see
	 * @since 1.0
	 */
	List<TrdLendDurationDto> findPage(Map<String, Object> map);

	/**
	 * 获取entity通过参数
	 * <功能详细描述>
	 * @param map  参数
	 * @return 筛选结果
	 * @see
	 * @since 1.0
	 */
	TrdLendDurationDto getEntityByMap(Map<String, Object> map);

	/**
	 * 获取原始拆借交易的回购拆借方向
	 * <功能详细描述>
	 * @param map  参数
	 * @return 筛选结果
	 * @see
	 * @since 1.0
	 */
	String getBackLendDirection(Map<String, Object> map);
	
	String findCeTradeIdByWorkflowId(String workflowId);
	//add by liupengfei for 多级法人改造 S
    List<SelectOptionDto> getBusiCode(Map<String, Object> param);
	//add by liupengfei for 多级法人改造 E
}
