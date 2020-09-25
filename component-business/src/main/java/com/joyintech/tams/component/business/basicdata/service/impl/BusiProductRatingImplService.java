/*
 * 文件名：BusiFinBondRatingInfoImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.basicdata.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.MstBondCreditRatingMapper;
import com.joyintech.tams.common.base.dao.MstInnerCreditRatingMapper;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.common.base.entity.MstInnerCreditRating;
import com.joyintech.tams.component.business.basicdata.dto.BusiFinProductRatingDto;
import com.joyintech.tams.component.business.basicdata.service.BusiProductRatingService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 产品评级
 * @author 
 * @version 1.0 2019年11月5日
 * @see BusiProductRatingImplService
 * @since 1.0
 */
@Service
public class BusiProductRatingImplService extends BaseService<BusiFinProductRatingDto> implements BusiProductRatingService{
	
	@Autowired
    private JoyinRestTemplate restTemplate;
	//根据type选择表进行插入
	@Autowired
	private MstBondCreditRatingMapper mstBondCreditRatingMapper;
	
	@Autowired
	private MstInnerCreditRatingMapper mstInnerCreditRatingMapper;
	
	@Override
	public Page<BusiFinProductRatingDto> getFinProductRating(Map<String, Object> map) {
		//评级显示类型  原始、标准    传值为空取数据库配置数据
		if(!StringUtil.isNotEmpty(map.get("mstDisType"))) {
			map.put("mstDisType",restTemplate.postForObject(ServiceApplication.COMMON_SERVICE_NAME 
					+ "/FinProductRating/getMstDisType" , map , String.class));
		}
		return restTemplate.postForPage(ServiceApplication.COMMON_SERVICE_NAME 
				+ "/FinProductRating/getProductBondRating" , map , BusiFinProductRatingDto.class);
	}

	@Override
	public List<BusiFinProductRatingDto> getProductOrgRating(Map<String, Object> map) {
		map.put("oType", "01");		//对象类型 01 主体
		return getFinProductRatingList(map);
	}

	@Override
	public List<BusiFinProductRatingDto> getProductMarketRating(Map<String, Object> map) {
		map.put("oType", "02");		//对象类型 02 债项
		return getFinProductRatingList(map);
	}
	
	private List<BusiFinProductRatingDto> getFinProductRatingList(Map<String, Object> map) {
		//评级显示类型  原始、标准    传值为空取数据库配置数据
		if(!StringUtil.isNotEmpty(map.get("mstDisType"))) {
			map.put("mstDisType",restTemplate.postForObject(ServiceApplication.COMMON_SERVICE_NAME 
					+ "/FinProductRating/getMstDisType" , map , String.class));
		}
		return restTemplate.postForList(ServiceApplication.COMMON_SERVICE_NAME 
				+ "/FinProductRating/getProductBondRatingList" , map , BusiFinProductRatingDto.class);
	}
	
	@Override
	public List<BusiFinProductRatingDto> getZtBestRatingByOrgId(Map<String, Object> map) {
		map.put("oType", "01");		//对象类型 01 主体
		map.put("sqlSearchString", "(MARKETID IS NULL OR ORGID IS NULL)");		
		return getBestProductRating(map);
	}

	@Override
	public List<BusiFinProductRatingDto> getZxBestRatingByMarketId(Map<String, Object> map) {
		map.put("oType", "02");		//对象类型 02 债项
		map.put("sqlSearchString", "(MARKETID IS NULL OR ORGID IS NULL)");		
		return getBestProductRating(map);
	}

	@Override
	public List<BusiFinProductRatingDto> getBestRatingByMarketId(Map<String, Object> map) {
		map.put("sqlSearchString", "(MARKETID IS NULL OR ORGID IS NULL)");		
		return getBestProductRating(map);
	}

	@Override
	public List<BusiFinProductRatingDto> getZtBestRatingByMarketId(Map<String, Object> map) {
		map.put("sqlSearchString", "(MARKETID IS NOT NULL AND ORGID IS NOT NULL)");		
		return getBestProductRating(map);
	}

	@Override
	public List<BusiFinProductRatingDto> getZxztBestRatingByMarketId(Map<String, Object> map) {
		map.put("oType", "03");		//对象类型 03 债项主体综合
		return getBestProductRating(map);
	}

	private List<BusiFinProductRatingDto> getBestProductRating(Map<String, Object> map) {
		//1、获取产品最优化规则
		Map<String, Object> ruleMap = getBeatRatingRule(map);
		//最优参数数据
		Map<String, Object> bestRuleMap = new HashMap<>();
		//获取评级优先参数配置
		List<Map<String, Object>> mstRatingParamConfig = (List<Map<String, Object>>) ruleMap.get("MST_RATING_PARAM_CONFIG");
		for(Map<String, Object> config : mstRatingParamConfig) {
			//获取排序顺序   key ： PARAM_RAT_SORT
			if(StringUtil.equals("PARAM_RAT_SORT", config.get("paramCode"))) {
				bestRuleMap.put("PARAM_RAT_SORT", config.get("paramValue"));
			}
			//基准数据类型   key ： BASE_DATE_TYPE	01：各评级机构最新99：全量数据	
			if(StringUtil.equals("BASE_DATE_TYPE", config.get("paramCode"))) {
				bestRuleMap.put("BASE_DATE_TYPE", config.get("paramValue"));
			}
			//评级显示类型   key ： MST_DIS_TYPE	01：原始，02：标准
			if(StringUtil.equals("MST_DIS_TYPE", config.get("paramCode"))) {
				bestRuleMap.put("MST_DIS_TYPE", config.get("paramValue"));
			}
		}
		//拼接sql
		String[] sortRules = bestRuleMap.get("PARAM_RAT_SORT").toString().split(",");
		String sqlOtypeParam = "";	//sql字段中的排序规则表评级对象类型
		boolean sortFlag = true;	//是否排序标识	判断排序规则表是否有数据
		//未配置基础排序规则
		if(sortRules.length == 0) {
			sortFlag = false;
		}
		//判断是否配置排序规则数据
		if(StringUtil.isNotEmpty(ruleMap.get("MST_RATING_ATTR_ORDER_COUNT")) && 
				Integer.valueOf(ruleMap.get("MST_RATING_ATTR_ORDER_COUNT").toString()) > 0) {
			//存在评级对象对应的评级规则
			sqlOtypeParam = map.get("oType").toString();
		}
		else if(StringUtil.isNotEmpty(ruleMap.get("MST_RATING_ATTR_ORDER_COMMON")) && 
					Integer.valueOf(ruleMap.get("MST_RATING_ATTR_ORDER_COMMON").toString()) > 0){
			sqlOtypeParam = "99";
		}
		else {
			//未配置排序规则
			sortFlag = false;
		}
		StringBuilder sqlBuild = new StringBuilder();
		sqlBuild.append(" temp");
		map.put("sqlSortString", sqlBuild.toString());
		if(sortFlag) {
			//连接sql
			for(int i=0;i<sortRules.length;i++) {
				sqlBuild.append(" LEFT JOIN MST_RATING_ATTR_ORDER mro").append(i).append(" ON mro").append(i)
					.append(".O_TYPE='").append(sqlOtypeParam).append("' AND mro").append(i).append(".ATTR_CODE = '")
						.append(sortRules[i]).append("' and temp.").append(sortRules[i].replaceAll("_", ""))
							.append(" = mro").append(i).append(".ATTR_VALUE");
			}
			//排序规则
			sqlBuild.append(" order by ");
			for(int i=0;i<sortRules.length;i++) {
				sqlBuild.append(" CAST(").append(" mro").append(i).append(".ATTR_ORDER AS INTEGER) DESC,");
			}
			map.put("sqlSortString", sqlBuild.toString().substring(0, sqlBuild.toString().length()-1));
		}
		if(!StringUtil.isNotEmpty(map.get("mstDisType"))) {
			//评级显示类型    01 原始  02 标准
			map.put("mstDisType", bestRuleMap.get("MST_DIS_TYPE"));
		}
		if(!StringUtil.isNotEmpty(map.get("baseDataType"))) {
			//基准数据类型    01：各评级机构最新99：全量数据
			map.put("baseDataType", bestRuleMap.get("BASE_DATE_TYPE"));
		}
		return restTemplate.postForList(ServiceApplication.COMMON_SERVICE_NAME 
				+ "/FinProductRating/getBestProductRating" , map , BusiFinProductRatingDto.class);
	}
	
	@Override
	public String getProductRatingOrderSql(Map<String, Object> map) {
		//1、获取产品最优化规则
		Map<String, Object> ruleMap = getBeatRatingRule(map);
		//最优参数数据
		Map<String, Object> bestRuleMap = new HashMap<>();
		//获取评级优先参数配置
		List<Map<String, Object>> mstRatingParamConfig = (List<Map<String, Object>>) ruleMap.get("MST_RATING_PARAM_CONFIG");
		for(Map<String, Object> config : mstRatingParamConfig) {
			//获取排序顺序   key ： PARAM_RAT_SORT
			if(StringUtil.equals("PARAM_RAT_SORT", config.get("paramCode"))) {
				bestRuleMap.put("PARAM_RAT_SORT", config.get("paramValue"));
			}
			//基准数据类型   key ： BASE_DATE_TYPE	01：各评级机构最新99：全量数据	
			if(StringUtil.equals("BASE_DATE_TYPE", config.get("paramCode"))) {
				bestRuleMap.put("BASE_DATE_TYPE", config.get("paramValue"));
			}
			//评级显示类型   key ： MST_DIS_TYPE	01：原始，02：标准
			if(StringUtil.equals("MST_DIS_TYPE", config.get("paramCode"))) {
				bestRuleMap.put("MST_DIS_TYPE", config.get("paramValue"));
			}
		}
		//拼接sql
		String sqlSortString = "";		//sql片段结果
		String[] sortRules = bestRuleMap.get("PARAM_RAT_SORT").toString().split(",");
		String sqlOtypeParam = "";	//sql字段中的排序规则表评级对象类型
		boolean sortFlag = true;	//是否排序标识	判断排序规则表是否有数据
		//未配置基础排序规则
		if(sortRules.length == 0) {
			sortFlag = false;
		}
		//判断是否配置排序规则数据
		if(StringUtil.isNotEmpty(ruleMap.get("MST_RATING_ATTR_ORDER_COUNT")) && 
				Integer.valueOf(ruleMap.get("MST_RATING_ATTR_ORDER_COUNT").toString()) > 0) {
			//存在评级对象对应的评级规则
			sqlOtypeParam = map.get("oType").toString();
		}
		else if(StringUtil.isNotEmpty(ruleMap.get("MST_RATING_ATTR_ORDER_COMMON")) && 
					Integer.valueOf(ruleMap.get("MST_RATING_ATTR_ORDER_COMMON").toString()) > 0){
			sqlOtypeParam = "99";
		}
		else {
			//未配置排序规则
			sortFlag = false;
		}
		StringBuilder sqlBuild = new StringBuilder();
		sqlBuild.append(" temp");
		sqlSortString = sqlBuild.toString();
		if(sortFlag) {
			//连接sql
			for(int i=0;i<sortRules.length;i++) {
				sqlBuild.append(" LEFT JOIN MST_RATING_ATTR_ORDER mro").append(i).append(" ON mro").append(i)
					.append(".O_TYPE='").append(sqlOtypeParam).append("' AND mro").append(i).append(".ATTR_CODE = '")
						.append(sortRules[i]).append("' and temp.").append(sortRules[i].replaceAll("_", ""))
							.append(" = mro").append(i).append(".ATTR_VALUE");
			}
			//排序规则
			sqlBuild.append(" order by ");
			for(int i=0;i<sortRules.length;i++) {
				sqlBuild.append(" CAST(").append(" mro").append(i).append(".ATTR_ORDER AS INTEGER) DESC,");
			}
			sqlSortString = sqlBuild.toString().substring(0, sqlBuild.toString().length()-1);
		}
		return sqlSortString;
	}

	/**
	 * 获取最优化规则
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> getBeatRatingRule(Map<String, Object> map) {
		//评级对象类型为空传03   即债项、主体 总体
		if(!StringUtil.isNotEmpty(map.get("oType"))) {
			map.put("oType", "03");
		}
		Map<String,Object> ruleMap = restTemplate.postForObject(ServiceApplication.COMMON_SERVICE_NAME 
				+ "/FinProductRating/getBestProductRatingRule" , map , Map.class);
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> list = mapper.convertValue(ruleMap.get("MST_RATING_PARAM_CONFIG"),List.class);
		ruleMap.put("MST_RATING_PARAM_CONFIG",list);
		return ruleMap;
	}
	//根据type选择表进行删除

	@Override
	public int delete(Object dto,int type) {
		if (type==1) {
			return restTemplate.postForObject(ServiceApplication.COMMON_SERVICE_NAME 
					+ "/FinProductRating/deleteByOcode" , (MstBondCreditRating) dto , Integer.class);
		}else if (type==2) {
			return restTemplate.postForObject(ServiceApplication.COMMON_SERVICE_NAME 
					+ "/FinProductRating/deleteInnerByOcode" ,(MstInnerCreditRating) dto , Integer.class);
		}
		return type;
	}
	//根据type选择表进行更新
	@Override
	public int update(Object dto,int type) {
		if(type==1) {
			return restTemplate.postForObject(ServiceApplication.COMMON_SERVICE_NAME 
				+ "/FinProductRating/update" , (MstBondCreditRating) dto , Integer.class);
		}else if (type==2) {
			return restTemplate.postForObject(ServiceApplication.COMMON_SERVICE_NAME 
					+ "/FinProductRating/updateInner" , (MstInnerCreditRating) dto , Integer.class);
		}
		return type;
	}
	
	@Override
	public void insert(Object entity,int type) {
		if(type==1 && MstBondCreditRating.class.isAssignableFrom(entity.getClass())) {
			mstBondCreditRatingMapper.insert((MstBondCreditRating) entity);
		}else if (type==2) {
			mstInnerCreditRatingMapper.insert((MstInnerCreditRating) entity);
		}
	} 
}
