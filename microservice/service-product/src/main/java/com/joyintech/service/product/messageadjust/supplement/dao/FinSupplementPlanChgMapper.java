package com.joyintech.service.product.messageadjust.supplement.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.messageadjust.supplement.dto.FinSupplementPlanChgDto;

@Repository
public interface FinSupplementPlanChgMapper {

    /**
     * 获取产品下拉框
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
	List<SelectOptionDto> getFinprodIds(Map<String, Object> param);

	/**
	 * 查询回补计划
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<FinSupplementPlanChgDto> findPage(Map<String, Object> param);

	/**
	 * 规则
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	String getcycleTdayRule(Map<String, Object> param);
	
	/**
	 * 删除旧的回补计划
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	void deleteAccrualSche(Map<String, Object> param);

}
