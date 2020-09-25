
package com.joyintech.tams.busicommon.productRating.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.joyintech.tams.busicommon.productRating.dto.CommonProductRatingDto;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.common.base.entity.MstInnerCreditRating;

@Repository
public interface CommonProductRatingMapper {
	/**
	 * 根据条件查找债项、主体评级数据
	 */
	List<CommonProductRatingDto> getProductBondInnerRating(Map<String, Object> map);
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
	List<CommonProductRatingDto> getBestProductRating(Map<String, Object> map);
	/**
	 * 对行外表的更新，删除操作
	 * 根据主键
	 */
	int deleteByPrimaryKey(MstBondCreditRating dto);
	int updateByPrimaryKey(MstBondCreditRating dto);
	/**
	 * 对行内表的更新，删除操作
	 * 根据主键
	 */
	int deleteInnerByPrimaryKey(MstInnerCreditRating dto);
	int updateInnerByPrimaryKey(MstInnerCreditRating dto);
}
