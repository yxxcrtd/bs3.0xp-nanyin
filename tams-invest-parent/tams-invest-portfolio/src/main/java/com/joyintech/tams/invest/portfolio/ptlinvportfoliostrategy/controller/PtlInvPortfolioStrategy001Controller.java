/*
 * 文件名：PtlInvPortfolioStrategy001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年7月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlinvportfoliostrategy.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.portfolio.ptlinvportfoliostrategy.dto.PtlInvPortfolioStrategy001Dto;
import com.joyintech.tams.invest.portfolio.ptlinvportfoliostrategy.service.PtlInvPortfolioStrategy001Service;

/**
 * 
 * 投资组合策略管理
 * 
 * @author niuqianyu
 * @version 1.0 2019年7月23日
 * @see PtlInvPortfolioStrategy001Controller
 * @since 1.0
 */
@Controller
@PageId("PtlInvPortfolioStrategy001")
@RequestMapping("/invest/portfolio/ptlinvportfoliostrategy/PtlInvPortfolioStrategy001")
public class PtlInvPortfolioStrategy001Controller extends BaseController {

	@Autowired
	private PtlInvPortfolioStrategy001Service ptlInvPortfolioStrategy001Service;

	/**
	 * 界面初始化 {@inheritDoc}
	 */
	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {
		this.setSelectOptionToModel(model, DictCode.投资策略类型);
		return super.init(model);
	}

	/**
	 * 
	 * 组合代码下拉框
	 * 
	 * @return List<SelectOptionDto>
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("/getPortfolioId")
	@ResponseBody
	public Page<SelectOptionDto> getPortfolioId() {
		return ptlInvPortfolioStrategy001Service.getPortfolioId(RequestUtil.getPageParam());
	}

	/**
	 * 
	 * 策略代码下拉框
	 * 
	 * @return List<SelectOptionDto>
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("/getStrategyId")
	@ResponseBody
	public Page<SelectOptionDto> getStrategyId() {
		return ptlInvPortfolioStrategy001Service.getStrategyId(RequestUtil.getPageParam());
	}

	/**
	 * 分页查询
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("findPage")
	public Page<PtlInvPortfolioStrategy001Dto> findPage() throws Exception {
		return ptlInvPortfolioStrategy001Service.selectForPage(RequestUtil.getPageParam());
	}

	/**
	 * 删除按钮点击时的check
	 *
	 * @param dto
	 *            dto
	 * @param result
	 *            BindingResult
	 * @return check结果
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Validated({ ValidateGroupDelete.class }) PtlInvPortfolioStrategy001Dto dto,
			BindingResult result) {
		return commonCheck(result, dto.getOperateType());
	}

	/**
	 * 
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @param dto
	 *            MstMarketInstitutionsDto
	 * @param br
	 *            BindingResult
	 * @return AjaxResult
	 * @throws Exception
	 *             Exception
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping(ACTION_DELETE)
	public AjaxResult delete(@RequestBody PtlInvPortfolioStrategy001Dto dto) throws Exception {
		String msg = "删除失败";
		int num = ptlInvPortfolioStrategy001Service.delete(dto);
		if (num > 0) {
			msg = "删除成功";
		}
		return new AjaxResult(msg, MessageType.提示消息);
	}

	/**
	 * 导出信息excel
	 * 
	 * @return excel
	 * @throws Exception
	 *             异常
	 * @see
	 * @since 1.0
	 */
	@RequestMapping(ACTION_EXPORT)
	public ResponseEntity<byte[]> export() throws Exception {
		String datas = this.getParam("datas");
		Map<String, Object> paraMap = RequestUtil.getPageParam();
		List<PtlInvPortfolioStrategy001Dto> listdto;
		if (datas == null) {
			listdto = ptlInvPortfolioStrategy001Service.selectForList(paraMap);
		} else {
			listdto = JsonUtils.json2List(datas, PtlInvPortfolioStrategy001Dto.class);
		}
		File outFile = ptlInvPortfolioStrategy001Service
				.makeFile("/com/joyintech/tams/invest/portfolio/ptlinvportfoliostrategy/conf/export_conf.xml", listdto);
		return ExportUtil.export(this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
				+ DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
				+ FileNameUtil.getFileExtName(outFile), outFile, true);
	}
}
