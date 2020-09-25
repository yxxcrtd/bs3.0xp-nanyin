/*
 * 文件名：ReportContentEnum003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.disclosure.information.config.reportcontentenum.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.disclosure.information.config.reportcontentenum.dto.ReportContentEnum002Dto;
import com.joyintech.service.disclosure.information.config.reportcontentenum.dto.ReportContentEnum003Dto;
import com.joyintech.service.disclosure.information.config.reportcontentenum.service.ReportContentEnum002Service;
import com.joyintech.tams.disclosure.common.common.service.BaseCommonService;

/**
 * 右侧栏表格新建 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author lishicheng
 * @version 1.0 2018年11月7日
 * @see ReportContentEnum003Controller
 * @since 1.0
 */
@RestController(value="serviceReportContentEnum003Controller")
@RequestMapping("/ReportContentEnum003")
public class ReportContentEnum003Controller extends BaseController {

	@Autowired
	private ReportContentEnum002Service reportContentEnum002Service;
	@Autowired
	private BaseCommonService baseCommonService;

	
	@GetMapping("findById")
	public ReportContentEnum002Dto findById(String id){
		return reportContentEnum002Service.findById(id);
		
		
	}

	/**
	 * 新建修改时check <一句话功能简述> <功能详细描述>
	 * 
	 * @param dto
	 * @param bindingResult
	 * @return
	 * @throws Exception
	 */
//	@ResponseBody
//	@RequestMapping("check")
//	public AjaxResult check(@RequestBody @Valid ReportContentEnum002Dto dto, BindingResult bindingResult)
//			throws Exception {
//		String msg = StringUtil.EMPTY;
//		String operateType = dto.getOperateType();
//		//新建的时候为提交，修改的时候为保存
//		if(operateType.equals("submit")){
//			//新建时对生效日进行校验
//			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
//			BaseCommonService bs = new BaseCommonService();
//			String sysDate = sdf.format(bs.getSysDate());
//			if(StringUtil.isNotEmpty(dto.getIndexDate())) {
//				String indexDate = sdf.format(dto.getIndexDate());
//				if (indexDate.compareTo(sysDate) < 0 ){ 
//					msg = "生效日必须大于等于平台日";
//					return new AjaxResult(msg, MessageType.错误消息);
//				}
//			}
//		}
//		
//
//		return commonCheck(bindingResult, operateType);
//	}

	/**
	 * 右侧栏新建 <一句话功能简述> <功能详细描述>
	 * 
	 * @param dto
	 * @param bindingResult
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping(value = PAGE_SAVE, method = RequestMethod.POST)
//	public AjaxResult save(@RequestBody @Valid ReportContentEnum002Dto dto, BindingResult bindingResult) {
//		String msg = StringUtil.EMPTY;
//		dto.setIndexType(DisclosureDictCodeList.指标类型_录入型.getValue());
//		if (StringUtils.isEmpty(dto.getId())) {
//			dto.setId(IDGenerator.generate());
//			reportContentEnum002Service.insert(dto);
//			msg = "新建成功！";
//		} else {
//			reportContentEnum002Service.updateByPrimaryKeySelective(dto);
//			msg = "修改成功！";
//		}
//		return new AjaxResult(msg);
//	}

	@PostMapping("insert")
	public void insert(@RequestBody ReportContentEnum002Dto dto){
		reportContentEnum002Service.insert(dto);
	}
	
	
	@PostMapping("updateByPrimaryKeySelective")
	public void updateByPrimaryKeySelective(@RequestBody ReportContentEnum002Dto dto){
		reportContentEnum002Service.updateByPrimaryKeySelective(dto);
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
		AjaxResult res = new AjaxResult();
		try {
			reportContentEnum002Service.readFile(request);
		} catch (Exception e) {
			String msg = "上传失败：" + e.getMessage();
			res = new AjaxResult(msg, MessageType.错误消息);
			return res;
		}

		return res;
	}

	/**
	 * 根据作业编号查询不适用产品
	 * 
	 * @return
	 */
	@RequestMapping("/findRulePage")
	@ResponseBody
	public List<ReportContentEnum003Dto> findRulePage() {
		if (StringUtil.isNotEmpty(this.getParam("id"))) {
			return reportContentEnum002Service.findRulePage(this.getParam("id"));
		}
		return new ArrayList<ReportContentEnum003Dto>();
	}

}
