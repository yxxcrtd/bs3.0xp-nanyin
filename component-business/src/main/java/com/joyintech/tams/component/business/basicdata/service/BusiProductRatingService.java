/*
 * 文件名：BusiFinExercisePlanService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.basicdata.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.component.business.basicdata.dto.BusiFinProductRatingDto;

/**
 * 产品评级
 * 〈功能详细描述〉
 * @author 
 * @version 1.0 2019年11月5日
 * @see BusiProductRatingService
 * @since 1.0
 */
@Service
public interface BusiProductRatingService {
	/**
	 * 产品评级全量查询
	 * <功能详细描述>
	 * @param map： 对象代码、查询日期、日期区间开始、日期区间结束、内外部类型、是否只看最新、评级显示类型			
	 * @return list ：
	 * 对象代码、评级对象类型、评级对象显示类型、评级机构、评级日期、公告日期、
	 * 长短期、评级结果、录入方式、评级展望、变动方向、评级发起类型
	 * @since 1.0
	 */
	Page<BusiFinProductRatingDto> getFinProductRating(Map<String, Object> map);
	/**
	 * 主体评级全量查询
	 * @since 1.0
	 */
	List<BusiFinProductRatingDto> getProductOrgRating(Map<String, Object> map);
	/**
	 * 债项评级全量查询
	 * @since 1.0
	 */
	List<BusiFinProductRatingDto> getProductMarketRating(Map<String, Object> map);
	
	/**
	 * 以机构代码查询主体最优先评级
	 * <功能详细描述>
	 * @param map： 对象代码（必输）、查询日期、日期区间开始、日期区间结束、评级显示类型、指定评级机构			
	 * @return 
	 * list ：
	 * 对象代码、评级对象类型、评级对象显示类型、评级机构、内外部类型、评级日期
	 * 、公告日期、长短期、评级结果、录入方式、评级展望、变动方向、评级发起类型
	 * @since 1.0
	 */
	List<BusiFinProductRatingDto> getZtBestRatingByOrgId(Map<String, Object> map);
	/**
	 * 以市场代码查询债项最优先债项评级
	 * <功能详细描述>
	 * @since 1.0
	 */
	List<BusiFinProductRatingDto> getZxBestRatingByMarketId(Map<String, Object> map);
	/**
	 * 以市场代码查询最优先债项及主体评级（参数 oCode  对象代码）
	 * <功能详细描述>
	 * @since 1.0
	 */
	List<BusiFinProductRatingDto> getBestRatingByMarketId(Map<String, Object> map);
	/**
	 * 以市场代码查询债项最优先主体评级
	 * <功能详细描述>
	 * @since 1.0
	 */
	List<BusiFinProductRatingDto> getZtBestRatingByMarketId(Map<String, Object> map);
	/**
	 * 以市场代码查询最优先债项主体综合评级
	 * <功能详细描述>
	 * @since 1.0
	 */
	List<BusiFinProductRatingDto> getZxztBestRatingByMarketId(Map<String, Object> map);
	/**
	 *获取排序规则sql片段
	 * @param oType 评级对象类型（非必输 默认03 债项 主体 综合） 01 主体、02 债项 、03 主体债项综合 
	 * @return（left  join ... on ... left join ... on ... order by ...）
	 */
	String getProductRatingOrderSql(Map<String, Object> map);
	
	/**
	 * 删除
	 * <功能详细描述>
	 * @since 1.0
	 */
	public int delete(Object dto,int type);
	/**
	 * 更新
	 * <功能详细描述>
	 * @since 1.0
	 */
	public int update(Object dto,int type);
	/**
	 * 插入
	 * <功能详细描述>
	 * @since 1.0
	 */
	public void insert(Object dto,int type);
	
}
