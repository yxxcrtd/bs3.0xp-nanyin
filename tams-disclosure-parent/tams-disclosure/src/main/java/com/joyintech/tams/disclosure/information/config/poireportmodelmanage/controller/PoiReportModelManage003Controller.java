/*
 * 文件名：ReportModelManage002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.poireportmodelmanage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.service.GetPrdInfoSelectService;
import com.joyintech.tams.disclosure.information.config.poireportmodelmanage.dto.PoiReportModelManage003Dto;
import com.joyintech.tams.disclosure.information.config.poireportmodelmanage.service.PoiReportModelManage003Service;

/**
 * 报表模板管理-导出报表
 * 
 * @author zhangyf
 * @version 1.0 2020年2月21日
 * @see PoiReportModelManage003Controller
 * @since 1.0
 */
@Controller
@PageId("PoiReportModelManage003")
@RequestMapping("/disclosure/information/config/poireportmodelmanage/PoiReportModelManage003")
public class PoiReportModelManage003Controller extends BaseController {

	@Autowired
	PoiReportModelManage003Service poireportModelManage003Service;

	@Autowired
	private GetPrdInfoSelectService getPrdInfoSelectService;
	
	@Override
	public String init(Model model) {
		model.addAttribute("cureDate", DateUtil.getCurrentDate());
		// 报表信息主键
		model.addAttribute("tabNum", this.getParam("tabNum"));
		return super.init(model);
	}

	/**
	 * 获取报表生成条件
	 * 
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getReporSearchConditions")
	public List<PoiReportModelManage003Dto> getReporSearchConditions() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tabNum", this.getParam("tabNum"));
		return poireportModelManage003Service.getReportSearchConditions(map);
	}

	/**
	 * 查询下拉框内容列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getSelectOptions")
	@ResponseBody
	public Page<SelectOptionDto> getSelectOptions(){
		Map<String, Object> map = RequestUtil.getPageParam();
		// 报表条件主键
		map.put("disId", this.getParam("disId"));
		return poireportModelManage003Service.getSelectOptionForPage(map);
	}

	/**
	 * 分页查询产品下拉框
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getPrdInfoSelect")
	@ResponseBody
    public Page<SelectOptionDto> getPrdInfoSelect() throws Exception {
	    Map<String, Object> param=RequestUtil.getPageParam();
		Page<SelectOptionDto> prdInfoSelect = getPrdInfoSelectService.getPrdInfoSelect(param);
        return prdInfoSelect;
    }
	
	/**
	 * 导出文件（仅导出查阅，不做其他操作）
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/reportExport")
    public ResponseEntity<byte[]> reportExport() throws IOException{
	    return poireportModelManage003Service.reportExport(RequestUtil.getPageParam());
    }
    
    
    /**
     * 查询是否有相同报表
     * 
     * @return
     * @throws IOException 
     */
    @RequestMapping("/querySameCount")
	@ResponseBody
	public AjaxResult querySameCount() throws IOException{
		AjaxResult ajaxResult = new AjaxResult();
		Map<String, Object> param = RequestUtil.getPageParam();
		// 是否有已确认报表
		Boolean confirmFlag = poireportModelManage003Service.hasConfirmStatus(param);
		if(confirmFlag) {
			ajaxResult.setMessage("存在相同且已确认的报表，不能再次生成！", MessageType.错误消息);
			return ajaxResult;
		}
		// 是否有已确认报表
		Boolean hasSame = poireportModelManage003Service.querySameCount(param);
		if(hasSame) {
			return new AjaxResult("存在相同的报表，是否继续生成？",MessageType.确认消息);
		}
		return ajaxResult;
	}
    
    
    /**
     * 生成报表、生成信息披露确认（未确认）数据
     * 
     * @return
     * @throws IOException 
     */
    @RequestMapping("/reportGenerate")
	@ResponseBody
	public AjaxResult reportGenerate() throws IOException{
		AjaxResult ajaxResult = new AjaxResult();
		Map<String, Object> param = RequestUtil.getPageParam();
		// 生成报表
		Boolean res = poireportModelManage003Service.reportGenerate(param);
		if(res) {
			ajaxResult.setMessage("生成成功！", MessageType.提示消息);
		}else {
			ajaxResult.setMessage("生成失败！", MessageType.错误消息);
		}
		return ajaxResult;
	}
}
