/*
 * 文件名：ValUnfairlendingQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2019年01月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
*/
package com.joyintech.tams.valuation.financemanage.balancecheck.unfairlendingquery.controller;

import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.tams.valuation.financemanage.balancecheck.unfairlendingquery.dto.ValUnfairlendingQuery001Dto;
import com.joyintech.tams.valuation.financemanage.balancecheck.unfairlendingquery.service.ValUnfairlendingQuery001Service;

/**
 * 科目流水查询
 * 
 * @author wanwgei1
 * @version 1.0 2019年01月28日
 * @see ValUnfairlendingQuery001Controller
 * @since 1.0
 */

@Controller
@PageId("ValUnfairlendingQuery001")
@RequestMapping("/valuation/financemanage/balancecheck/unfairlending/ValUnfairlendingQuery001")
public class ValUnfairlendingQuery001Controller extends BaseController {

	@Autowired
	private ValUnfairlendingQuery001Service valUnfairlendingQuery001Service;

	@Autowired
	private ValAssetBook001Service valAssetBook001Service;

	/**
	 * 重写init方法，加载页面 {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
		List<SelectOptionDto> booksetIds = valUnfairlendingQuery001Service.findBooksets();
		// 所属账套控件初期化
		model.addAttribute("booksetIds", JsonUtils.object2Json(booksetIds));

		return super.init(model);
	}

	/**
	 * 所属账套下拉框获取
	 *
	 * @return 返回的数据
	 * @exception Exception
	 *                异常
	 * @since 1.0
	 */
	@RequestMapping("/findBooksetId")
	@ResponseBody
	public Page<SelectOptionDto> findBooksetId() {
		return valAssetBook001Service.findBooksetIdByPage(RequestUtil.getPageParam());
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
	@RequestMapping("findPage")
	@ResponseBody
	public Page<ValUnfairlendingQuery001Dto> findPage() throws Exception {
		return valUnfairlendingQuery001Service.findPage(RequestUtil.getPageParam());
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
	@RequestMapping(ACTION_EXPORT)
	public ResponseEntity<byte[]> export() throws Exception {
		String datas = this.getParam("datas");
		Map<String, Object> params = this.getRequestMap();
		ValUnfairlendingQuery001Dto dto = (ValUnfairlendingQuery001Dto) JsonUtils.map2entity(params,
				ValUnfairlendingQuery001Dto.class);
		List<ValUnfairlendingQuery001Dto> listdto;
		if (datas == null) {
			listdto = valUnfairlendingQuery001Service.queryUnfairlendingList(dto);
		} else {
			listdto = JsonUtils.json2List(datas, ValUnfairlendingQuery001Dto.class);
		}
		File outFile = valUnfairlendingQuery001Service.makeFile(
				"/com/joyintech/tams/valuation/financemanage/balancecheck/unfairlendingquery/config/export_unfairlendingquery.xml",
				listdto);
		return ExportUtil.export(this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
				+ DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
				+ FileNameUtil.getFileExtName(outFile), outFile, true);
	}

}