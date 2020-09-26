package com.joyintech.tams.valuation.system.fileupload.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.system.excelimp.dto.ExcelImpFileInfoDto;
import com.joyintech.system.excelimp.dto.ExcelImpResultDto;
import com.joyintech.tams.valuation.system.fileupload.service.SystemFileUpload001Service;
import com.joyintech.tams.valuation.common.service.BaseCommonService;

/**
 * 系统文件上传
 * 
 * @author Administrator
 *
 */
@Controller
@PageId("FileUpload001")
@RequestMapping("/valuation/system/fileupload/FileUpload001")
public class SystemFileUpload001Controller extends BaseController {

	/**
	 * 
	 */
	@Autowired
	private SystemFileUpload001Service systemFileUpload001Service;

	@Autowired
	private BaseCommonService baseCommonService;

	/**
	 * 重写init方法，加载页面 {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
		String sysDate = baseCommonService.getSysDateString();
		if (sysDate == null || "".equals(sysDate)) {
			sysDate = DateUtil.format(new Date());
		}
		model.addAttribute("sysDate", sysDate);
		return super.init(model);
	}

	/**
	 * 文件上传
	 *
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @param model
	 *            ModelAndView
	 * @return 保存文件信息
	 * @throws Exception
	 *             异常
	 * @see
	 * @since 1.0
	 */
	@RequestMapping(value = "/uploadFile")
	@ResponseBody
	public AjaxResult uploadFile(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {

		try {
			systemFileUpload001Service.readFile(request);
		} catch (Exception e) {
			String msg = "上传失败：" + e.getMessage();
			new AjaxResult(msg, MessageType.错误消息);
		}
		return new AjaxResult(getMessage("VAL_EXCELIMP_E_0020"), MessageType.提示消息);
	}

	/**
	 * 保存
	 * 
	 * @param request
	 *            request
	 * @param impFileInfo
	 *            导入文件信息
	 * @return 画面结果
	 * @throws Exception
	 *             异常
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping(value = PAGE_SAVE, method = RequestMethod.POST)
	public ExcelImpResultDto save(HttpServletRequest request, @RequestBody ExcelImpFileInfoDto impFileInfo)
			throws Exception {
		return null;
	}
}
