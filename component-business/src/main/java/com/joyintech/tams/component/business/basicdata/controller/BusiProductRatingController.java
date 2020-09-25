/*
 * 文件名：BussCalendarController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2019年7月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.basicdata.controller;


import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.common.base.dao.FinProductAddMapper;
import com.joyintech.tams.component.business.basicdata.dto.BusiFinProductRatingDto;
import com.joyintech.tams.component.business.basicdata.service.BusiProductRatingService;
import com.joyintech.tams.component.business.bond.dto.BusiFinBondRatingInfoDto;
import com.joyintech.tams.component.business.trade.BussCalendarService;

/**
 *  产品评级
 * 〈功能详细描述〉
 * @author 
 * @version 1.0 2019年11月5日
 * @see BusiProductRatingController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/basicdata/BusiProductRating")
public class BusiProductRatingController extends BaseController {
	
	@Autowired
    private BusiProductRatingService busiProductRatingService;
	
	/**
	 * 债项评级全量查询
	 * <功能详细描述>
	 * @param map： 对象代码、查询日期、日期区间开始、日期区间结束、内外部类型、是否只看最新、评级显示类型			
	 * @return 
	 * list ：
	 * 对象代码、评级对象类型、评级对象显示类型、评级机构、评级日期、公告日期、
	 * 长短期、评级结果、录入方式、评级展望、变动方向、评级发起类型
	 * @since 1.0
	 */
	@ResponseBody
    @RequestMapping("getProductBondRating")
    public List<BusiFinProductRatingDto> getProductBondRating(@RequestBody Map<String, Object> map) {
		return busiProductRatingService.getProductMarketRating(map);
    }
	
	/**
	 * 主体评级全量查询
	 * <功能详细描述>
	 * @since 1.0
	 */
	@ResponseBody
    @RequestMapping("getProductInnerRating")
    public List<BusiFinProductRatingDto> getProductInnerRating(@RequestBody Map<String, Object> map) {
		return busiProductRatingService.getProductOrgRating(map);
    }
	
	/**
	 * 债项+主体评级全量查询
	 * <功能详细描述>
	 * @since 1.0
	 */
	@ResponseBody
    @RequestMapping("getFinProductRating")
    public Page<BusiFinProductRatingDto> getFinProductRating(@RequestBody Map<String, Object> map) {
		return busiProductRatingService.getFinProductRating(map);
    }
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
	@ResponseBody
    @RequestMapping("getZtBestRatingByOrgId")
    public List<BusiFinProductRatingDto> getZtBestRatingByOrgId(@RequestBody Map<String, Object> map) {
		return busiProductRatingService.getZtBestRatingByOrgId(map);
    }
	/**
	 * 以市场代码查询债项最优先评级
	 * <功能详细描述>
	 * @param map： 对象代码（必输）、查询日期、日期区间开始、日期区间结束、评级显示类型、指定评级机构			
	 * @return 
	 * list ：
	 * 对象代码、评级对象类型、评级对象显示类型、评级机构、内外部类型、评级日期
	 * 、公告日期、长短期、评级结果、录入方式、评级展望、变动方向、评级发起类型
	 * @since 1.0
	 */
	@ResponseBody
    @RequestMapping("getZxBestRatingByMarketId")
    public List<BusiFinProductRatingDto> getZxBestRatingByMarketId(@RequestBody Map<String, Object> map) {
		return busiProductRatingService.getZxBestRatingByMarketId(map);
    }
}
