
package com.joyintech.service.common.finproductrating.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.common.finproductrating.dto.FinProductRatingDto;
import com.joyintech.tams.busicommon.productRating.dao.CommonProductRatingMapper;
import com.joyintech.tams.busicommon.productRating.dto.CommonProductRatingDto;
import com.joyintech.tams.common.base.dao.MstBondCreditRatingMapper;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.common.base.entity.MstInnerCreditRating;
import com.joyintech.tams.common.base.entity.MstRatingParamConfig;

/**
 * 产品评级service 〈一句话功能简述〉 〈功能详细描述〉
 * @version 1.0 2019年11月6日
 * @see FinProductRatingService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FinProductRatingService extends BaseService<FinProductRatingDto>{
	
	@Autowired
    private CommonProductRatingMapper commonProductRatingMapper;
	
	@Autowired
	MstBondCreditRatingMapper mstBondCreditRatingMapper;
	/**
     * 债项、主体评级查询(retrun page)
     * @throws Exception 异常
     */
    public Page<FinProductRatingDto> getProductBondInnerRating(int page, int length,Map<String, Object> map) {
    	return super.queryForPage(page,length,() -> commonProductRatingMapper.getProductBondInnerRating(map));
    }
    /**
     * 债项、主体评级查询(retrun list)
     * @throws Exception 异常
     */
    public List<CommonProductRatingDto> getProductBondInnerRatingList(Map<String, Object> map) {
    	return commonProductRatingMapper.getProductBondInnerRating(map);
    }
    /**
     * 评级排序规则查询
     * @throws Exception 异常
     */
    public Map<String, Object> getBestProductRatingRule(Map<String, Object> map) {
    	Map<String, Object> result = new HashMap<>();
    	//对象类型   主体、债券
    	String oType = map.get("oType").toString();		
    	List<MstRatingParamConfig> ratingParamConfig = commonProductRatingMapper.getMstRatingParamConfig(map);
    	if(ratingParamConfig == null || ratingParamConfig.size() == 0) {
    		map.put("oType", "99");		//查不出数据查询共同
    		ratingParamConfig = commonProductRatingMapper.getMstRatingParamConfig(map);
    	}
    	result.put("MST_RATING_PARAM_CONFIG", ratingParamConfig);
    	//查询排序规则表是否有对象类型对应规则记录数量  MST_RATING_ATTR_ORDER
    	map.put("oType", oType);		//查不出数据查询共同
    	int attrOrderCount = commonProductRatingMapper.getMstRatingAttrOrderCount(map);
    	if(attrOrderCount > 0) {
    		result.put("MST_RATING_ATTR_ORDER_COUNT", attrOrderCount);		//对象类型对应的排序规则数据记录
    		result.put("MST_RATING_ATTR_ORDER_COMMON", attrOrderCount);		//共通 99对应的排序规则记录
    	}
    	else {
    		map.put("oType", "99");		//查不出数据查询共通
    		int attrOrderCommonCount = commonProductRatingMapper.getMstRatingAttrOrderCount(map);
    		result.put("MST_RATING_ATTR_ORDER_COUNT", attrOrderCount);		//对象类型对应的排序规则数据记录
    		result.put("MST_RATING_ATTR_ORDER_COMMON", attrOrderCommonCount);		//共通 99对应的排序规则记录
    	}
        return result;
    }
    /**
     * 获取产品评级显示类型   01 原始   02  标准
     * @throws Exception 异常
     */
    public String getMstDisType(Map<String, Object> map) {
    	if(!StringUtil.isNotEmpty(map.get("oType"))) {
    		map.put("oType", "03");		//为空为债项主体综合场景
    	}
    	map.put("paramCode", "MST_DIS_TYPE");
    	List<MstRatingParamConfig> ratingParamConfig = commonProductRatingMapper.getMstRatingParamConfig(map);
    	if(ratingParamConfig == null || ratingParamConfig.size() == 0) {
    		map.put("oType", "99");		//查不出数据查询共同
    		ratingParamConfig = commonProductRatingMapper.getMstRatingParamConfig(map);
    	}
    	if(ratingParamConfig == null || ratingParamConfig.size() == 0) {
    		return null;
    	}
    	else {
    		return ratingParamConfig.get(0).getParamValue();
    	}
    }
    /**
     * 获取最优先产品评级结果
     * @throws Exception 异常
     */
    public List<CommonProductRatingDto> getBestProductRating(Map<String, Object> map) {
    	List<CommonProductRatingDto> list = commonProductRatingMapper.getBestProductRating(map);
    	return list;
    }
	/**
	 * 对行外表的更新，删除操作
	 * 根据主键
	 */
    
	public int deleteByPrimaryKey(MstBondCreditRating dto) {
		return commonProductRatingMapper.updateByPrimaryKey(dto);
	}
	
	public int updateByPrimaryKey(MstBondCreditRating dto) {
		return	commonProductRatingMapper.updateByPrimaryKey(dto);
	}
	/**
	 * 对行内表的更新，删除操作
	 * 根据主键
	 */
	public int deleteInnerByPrimaryKey(MstInnerCreditRating dto) {
		return commonProductRatingMapper.deleteInnerByPrimaryKey(dto);
	}
	public int updateInnerByPrimaryKey(MstInnerCreditRating dto) {
		return	commonProductRatingMapper.updateInnerByPrimaryKey(dto);
	}
}
