/*
 * 文件名：ValUnfairlendingQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2019年01月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
*/
package com.joyintech.service.valuation.financemanage.balancecheck.unfairlendingquery.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.service.valuation.financemanage.balancecheck.unfairlendingquery.dto.ValUnfairlendingQuery001Dto;
import com.joyintech.service.valuation.financemanage.balancecheck.unfairlendingquery.service.ValUnfairlendingQuery001Service;

/**
 * 科目流水查询
 * 
 * @author wanwgei1
 * @version 1.0 2019年01月28日
 * @see ValUnfairlendingQuery001Controller
 * @since 1.0
 */

@RestController
@RequestMapping("/ValUnfairlendingQuery001")
public class ValUnfairlendingQuery001Controller extends BaseController {

	@Autowired
	private ValUnfairlendingQuery001Service valUnfairlendingQuery001Service;

	@Autowired
	private ValAssetBook001Service valAssetBook001Service;


	
	 @PostMapping("/findBooksets")
	    public List<SelectOptionDto> findBooksets() {
	        return valUnfairlendingQuery001Service.findBooksets();
	       }


	/**
	 * 
	 * 分页数据查询
	 * 
	 * @return page
	 * @throws Exception
	 *             异常
	 * @see
	 * @since 1.0
	 */
	@PostMapping("findPage/{page}/{length}")
	public Page<ValUnfairlendingQuery001Dto> findPage(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		return valUnfairlendingQuery001Service.findPage(page, length, param);
	}

	/**
	 * 
	 * 获取页面参数 页面中有时间参数，获取参数后将时间转换成Date类型
	 * 
	 * @return paraMap
	 * @throws ParseException
	 * @see
	 * @since 1.0
	 */
	public Map<String, Object> getRequestMap() throws ParseException {
		Map<String, Object> paraMap = RequestUtil.getPageParam();
		// 入账日期区间
		String bookDateBegin = this.getParam("bookDateBegin");
		String bookDateEnd = this.getParam("bookDateEnd");
		if (StringUtil.isNotEmpty(bookDateBegin)) {
			paraMap.put("bookDateBegin", DateUtil.parseDate(bookDateBegin, "yyyy-MM-dd"));
		}
		if (StringUtil.isNotEmpty(bookDateEnd)) {
			paraMap.put("bookDateEnd", DateUtil.parseDate(bookDateEnd, "yyyy-MM-dd"));
		}
		return paraMap;
	}

	/**
	 * 导出数据
	 * 
	 * @return ResponseEntity<byte[]>
	 * @throws Exception
	 *             ExportUtil.export
	 * @see
	 * @since 1.0
	 */
	@PostMapping("/queryUnfairlendingList")
    public List<ValUnfairlendingQuery001Dto> getSubjectWater(ValUnfairlendingQuery001Dto dto) throws Exception {
		return valUnfairlendingQuery001Service.queryUnfairlendingList(dto);
    }

}