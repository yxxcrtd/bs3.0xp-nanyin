package com.joyintech.service.product.nolimit.nonstProUplimitMain.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.nolimit.nonstProUplimitMain.dto.NonstProUplimitMainDto;
@Repository
public interface NonstProUplimitMainMapper {
	/**
	 * 产品系列下拉框查询
	 * @param page
	 * @param length
	 * @param pageParam
	 * @return
	 */
	List<SelectOptionDto> selectPrdFamily(Map<String, Object> pageParam);
	/**
	 * 画面查询方法
	 * @param page
	 * @param length
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<NonstProUplimitMainDto> selectForPage(Map<String, Object> param);
    /**
	 * 	根据系列id 查name
	 * @param page
	 * @param length
	 * @param param
	 * @return
	 */	
	List<NonstProUplimitMainDto> queryPrdPlanSerName(Map<String, Object> param);
	/**
	 * 	插入方法
	 * @param entity
	 * @return
	 */
	void insert(NonstProUplimitMainDto dto);
	/**
	 * 	根据时间和编号判断是否有重复数据
	 * @param entity
	 * @return
	 */
	Integer selectPageData(NonstProUplimitMainDto entity);
	/**
	 * @param entity
	 * @return
	 */
	Integer delete(NonstProUplimitMainDto entity);
	/**
	 * @param entity
	 * @return
	 */
	Integer update(NonstProUplimitMainDto entity);

}
