
package com.joyintech.service.common.finproductrating.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.joyintech.service.common.finproductrating.dto.FinProductRatingDto;

@Repository
public interface FinProductRatingMapper {
	/**
	 * 根据条件查找债项、主体评级数据
	 */
	List<FinProductRatingDto> getProductBondInnerRating(Map<String, Object> map);
	/**
	 * 获取评级优先参数配置 MST_RATING_PARAM_CONFIG
	 */
	List getMstRatingParamConfig(Map<String, Object> map);
	/**
	 * 获取评级排序属性优先级数据个数（判断是否配置对象类型对应规则）  MST_RATING_ATTR_ORDER
	 */
	Integer getMstRatingAttrOrderCount(Map<String, Object> map);
	/**
	 * 获取最优评级结果
	 */
	List<FinProductRatingDto> getBestProductRating(Map<String, Object> map);
	/**
	 * 对行外表的更新，删除操作
	 * 根据主键
	 */
	int deleteByPrimaryKey(FinProductRatingDto dto);
	int updateByPrimaryKey(FinProductRatingDto dto);
	/**
	 * 对行内表的更新，删除操作
	 * 根据主键
	 */
	int deleteInnerByPrimaryKey(FinProductRatingDto dto);
	int updateInnerByPrimaryKey(FinProductRatingDto dto);
}
