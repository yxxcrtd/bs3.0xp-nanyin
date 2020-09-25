/*
 * 文件名：ReportContentEnum001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.disclosure.information.config.thresholdCheck.controller;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.service.GetPrdInfoSelectService;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCode;
import com.joyintech.tams.disclosure.information.config.reportcontentenum.dto.ReportContentEnum001Dto;
import com.joyintech.tams.disclosure.information.config.reportcontentenum.dto.ReportContentEnum002Dto;
import com.joyintech.tams.disclosure.information.config.reportcontentenum.service.ReportContentEnum002Service;
import com.joyintech.tams.disclosure.information.config.thresholdCheck.dto.ThresholdCheck001Dto;
import com.joyintech.tams.disclosure.information.config.thresholdCheck.service.ThresholdCheck001Service;
/**
 * 阈值校验界面
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dongxiangyang
 * @version 1.0 2018年11月6日
 * @see ThresholdCheck001Controller
 * @since 1.0
 */
@Controller
@PageId("ThresholdCheck001")
@RequestMapping("/disclosure/information/config/thresholdCheck/ThresholdCheck001")
public class ThresholdCheck001Controller  extends BaseController{
	
	@Autowired
	private  ThresholdCheck001Service thresholdCheck001Service;
	
	@Autowired
	private  ReportContentEnum002Service reportContentEnum002Service;
	
	 @Autowired
	private GetPrdInfoSelectService getPrdInfoSelectService;
	
	/**
	 * 内容初始化
	 * {@inheritDoc}
	 */		
    @Override
    public String init(Model model) {
    	List<SelectOptionDto>  listDto=new ArrayList<SelectOptionDto>();
    	SelectOptionDto  dto1=new SelectOptionDto();
    	SelectOptionDto  dto2=new SelectOptionDto();
    	dto1.setId("01");
    	dto1.setText("报表名称");
    	dto2.setId("02");
    	dto2.setText("阈值指标项");
    	listDto.add(dto1);
    	listDto.add(dto2);
    	 super.setSelectOptionToModel(model,DisclosureDictCode.统计维度);
         model.addAttribute("STATISTICAL_DIMENSION",JsonUtils.objectArray2Json(listDto.toArray()));
    	 return super.init(model);
    }
    
    /**
   	 * 分页查询产品下拉框
   	 * 
   	 * @return
   	 * @throws Exception
   	 */
   	@RequestMapping("getPrdInfoSelect")
   	@ResponseBody
       public Page<SelectOptionDto> getPrdInfoSelect()
           throws Exception {
   		Page<SelectOptionDto> prdInfoSelect = getPrdInfoSelectService.getPrdInfoSelect(RequestUtil.getPageParam());
           return prdInfoSelect;
       }
    
    /**
     * 左侧栏查询
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @throws Exception
     */
    @ResponseBody
	@RequestMapping("findIndexId")
	public Page<ThresholdCheck001Dto> findIndexId() throws Exception {
		Page<ThresholdCheck001Dto> page = thresholdCheck001Service
				.findIndexByPage(RequestUtil.getPageParam());
		return page;
	}
    
    /**
     * 右侧表格查询
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @throws Exception
     */
    @ResponseBody
	@RequestMapping("findIndexIdDetail")
	public Page<ThresholdCheck001Dto> findIndexIdDetail() throws Exception {
		Page<ThresholdCheck001Dto> page = thresholdCheck001Service
				.findIndexDetailByPage(RequestUtil.getPageParam());
		return page;
	}
    
    
    
   /**
    * 删除指标名称
    * <一句话功能简述>
    * <功能详细描述>
    * @param dto
    * @return
    */
	@ResponseBody
	@RequestMapping(value = "deleteIndex", method = RequestMethod.POST)
	public AjaxResult deleteScheme(@RequestBody ReportContentEnum001Dto dto) {
		String msg = StringUtils.EMPTY;

		int row = thresholdCheck001Service.deleteByPrimaryKey(dto.getId());
		if (row > 0) {
			msg = "删除成功";
			return new AjaxResult(msg);
		}
		msg = "删除失败";
		return new AjaxResult(msg, MessageType.错误消息);
	}
	
    /**
     * 右侧栏删除
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     */
	@ResponseBody
	@RequestMapping(value = "deleteindexDetial", method = RequestMethod.POST)
	public AjaxResult deleteindexDetial(@RequestBody ReportContentEnum002Dto dto) {
		String msg = StringUtils.EMPTY;
		String[] arr = dto.getId().split(",");
		int row = 0;
		for (int i = 0; i < arr.length; i++) {
			row = reportContentEnum002Service.deleteByPrimaryKey(arr[i]);
		}
		if (row > 0) {
			msg = "删除成功";
			return new AjaxResult(msg);
		}
		msg = "删除失败";
		return new AjaxResult(msg, MessageType.错误消息);
	}
	/**
	 * 导出
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(ACTION_EXPORT)
	public ResponseEntity<byte[]> export() throws Exception {
		Map<String, Object> paraMap = RequestUtil.getPageParam();
		String datas = this.getParam("datas");		
		List<ThresholdCheck001Dto> listdto = null;
				if (datas == null) {
			listdto = thresholdCheck001Service.findIndexDetail(paraMap);
			
		} else {
			listdto = JsonUtils.json2List(datas, ThresholdCheck001Dto.class);
		}
		File outFile = thresholdCheck001Service.makeFile(
				"/com/joyintech/tams/disclosure/information/config/thresholdCheck/config/export_thresholdCheck001.xml",
				listdto);
		return ExportUtil.export(this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
				+ DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
				+ FileNameUtil.getFileExtName(outFile), outFile, true);
	}
	
	/**
	 * 删除检验
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody ReportContentEnum001Dto dto, BindingResult result) throws Exception {
	    //检查是否被引用
		List<ReportContentEnum002Dto> checkResult = reportContentEnum002Service.checkIsQuote(dto);
		if(StringUtil.isEmptyList(checkResult)){
		    return new AjaxResult();
		}
//		String indexnames = "";
//		for(ReportContentEnum002Dto check : checkResult){
//		    indexnames = indexnames + "[" + check.getIndexName()+ "]";
//		}
		return new AjaxResult("指标代码已被引用，不能删除！", MessageType.错误消息);
	}
}
