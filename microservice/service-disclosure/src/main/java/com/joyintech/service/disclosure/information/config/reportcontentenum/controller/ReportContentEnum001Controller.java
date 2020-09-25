/*
 * 文件名：ReportContentEnum001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.disclosure.information.config.reportcontentenum.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.disclosure.information.config.reportcontentenum.dto.ReportContentEnum001Dto;
import com.joyintech.service.disclosure.information.config.reportcontentenum.dto.ReportContentEnum002Dto;
import com.joyintech.service.disclosure.information.config.reportcontentenum.service.ReportContentEnum001Service;
import com.joyintech.service.disclosure.information.config.reportcontentenum.service.ReportContentEnum002Service;
/**
 * 报表指标管理
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年11月6日
 * @see ReportContentEnum001Controller
 * @since 1.0
 */
@RestController(value="serviceReportContentEnum001Controller")
@RequestMapping("/ReportContentEnum001")
public class ReportContentEnum001Controller  extends BaseController{
	
	@Autowired
	private  ReportContentEnum001Service reportContentEnum001Service;
	
	@Autowired
	private  ReportContentEnum002Service reportContentEnum002Service;
	
    /**
     * 左侧栏查询
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @throws Exception
     */
    @PostMapping("findIndexId/{page}/{length}")
	public Page<ReportContentEnum001Dto> findIndexId(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		Page<ReportContentEnum001Dto> page1 = reportContentEnum001Service
				.findIndexByPage(page, length, param);
		return page1;
	}
    
    /**
     * 右侧表格查询
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @throws Exception
     */
    @PostMapping("findIndexIdDetail/{page}/{length}")
	public Page<ReportContentEnum002Dto> findIndexIdDetail(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		Page<ReportContentEnum002Dto> page1 = reportContentEnum002Service
				.findIndexDetailByPage(page, length, param);
		return page1;
	}
    
    
    
   /**
    * 删除指标名称
    * <一句话功能简述>
    * <功能详细描述>
    * @param dto
    * @return
    */
    @GetMapping("deleteIndex")
	public int deleteScheme(String id) {
		int row = reportContentEnum001Service.deleteByPrimaryKey(id);
		return row;
	}
	
    /**
     * 右侧栏删除
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     */
    @GetMapping("deleteindexDetial")
	public int deleteindexDetial(String id) {
//		String msg = StringUtils.EMPTY;
//		String[] arr = dto.getId().split(",");
//		int row = 0;
//		for (int i = 0; i < arr.length; i++) {
			int row = reportContentEnum002Service.deleteByPrimaryKey(id);
			return row;
//		}
//		if (row > 0) {
//			msg = "删除成功";
//			return new AjaxResult(msg);
//		}
//		msg = "删除失败";
//		return new AjaxResult(msg, MessageType.错误消息);
	}
	/**
	 * 导出
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @return
	 * @throws Exception
	 */
    @PostMapping("findAll")
	public List<ReportContentEnum002Dto> findAll(@RequestBody Map<String, Object> paraMap) {
//		Map<String, Object> paraMap = RequestUtil.getPageParam();
//		String datas = this.getParam("datas");		
//		List<ReportContentEnum002Dto> listdto = null;
//		if (datas == null) {
    	return reportContentEnum002Service.findIndexDetail(paraMap);
//			
//		} else {
//			listdto = JsonUtils.json2List(datas, ReportContentEnum002Dto.class);
//		}
//		File outFile = reportContentEnum002Service.makeFile(
//				"/com/joyintech/tams/disclosure/information/config/reportcontentenum/config/export_reportcontentenum001.xml",
//				listdto);
//		return ExportUtil.export(this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
//				+ DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
//				+ FileNameUtil.getFileExtName(outFile), outFile, true);
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
	@PostMapping("checkIsQuote")
	public List<ReportContentEnum002Dto> check(@RequestBody Map<String, Object> paraMap){
	    //检查是否被引用
		return  reportContentEnum002Service.checkIsQuote(paraMap);
//		if(StringUtil.isEmptyList(checkResult)){
//		    return new AjaxResult();
//		}
//		String indexnames = "";
//		for(ReportContentEnum002Dto check : checkResult){
//		    indexnames = indexnames + "[" + check.getIndexName()+ "]";
//		}
//		return new AjaxResult("指标代码已被引用，不能删除！", MessageType.错误消息);
	}
}
