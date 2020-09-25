/*
 * 文件名：ReportTemplate001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.disclosure.information.config.poireportmodelmanage.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.disclosure.information.config.poireportmodelmanage.service.PoiReportModelManage001Service;
import com.joyintech.service.disclosure.information.config.reportmodelmanage.ReportModelManage001Dto;
import com.joyintech.service.disclosure.information.config.reportmodelmanage.service.ReportModelManage001Service;
import com.joyintech.service.disclosure.information.config.reportmodelmanage.service.ReportModelManage002Service;
import com.joyintech.tams.common.base.entity.DisPoiTab;

/**
 * 报表模板管理 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author lishicheng
 * @version 1.0 2018年11月9日
 * @see PoiReportModelManage001Controller
 * @since 1.0
 */
@RestController(value="servicePoiReportModelManage001Controller")
@RequestMapping("/PoiReportModelManage001")
public class PoiReportModelManage001Controller extends BaseController {

	@SuppressWarnings("rawtypes")
	@Autowired
	private CommonExportService commonExportService;

	@Autowired
	private ReportModelManage001Service reportModelManage001Service;
	
	@Autowired
	private PoiReportModelManage001Service poireportModelManage001Service;
	
	@Autowired
	private ReportModelManage002Service reportModelManage002Service;
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(PoiReportModelManage001Controller.class);


	
	  @PostMapping("/findAll")
	    public List<ReportModelManage001Dto> findAll(@RequestBody Map<String, Object> param){
//	        return valAssetsFundQuery001Service.findPage(RequestUtil.getPageParam());
	    	 return reportModelManage001Service.searchReportsNew(param);
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
	@RequestMapping("exportZip")
	public ResponseEntity<byte[]> exportZip() throws Exception {
		String datas = this.getParam("datas");
		List<ReportModelManage001Dto> val001dto = JsonUtils.json2List(datas, ReportModelManage001Dto.class);
		Map<String, Object> param = RequestUtil.getPageParam();
		if (!StringUtil.isEmptyList(val001dto)) {
			for (ReportModelManage001Dto dto : val001dto) {
				dto.setParam(param);
			}
		}
		return reportModelManage001Service.exportExcel(val001dto);
	}

	/**
	 * 导出合并文档
	 * 
	 * @return excel
	 * @throws Exception
	 *             异常
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("mergeExport")
	public ResponseEntity<byte[]> mergeExport() throws Exception {
		Map<String, Object> param = RequestUtil.getPageParam();
		ReportModelManage001Dto reportModelManage001Dto = (ReportModelManage001Dto) JsonUtils.map2entity(param,
				ReportModelManage001Dto.class);
		reportModelManage001Dto.setParam(param);
		return reportModelManage001Service.mergeExportSingle(reportModelManage001Dto);
	}

	@SuppressWarnings("finally")
	@RequestMapping("/searchReports")
	@ResponseBody
	public AjaxResult searchReports() throws Exception {
		AjaxResult value = new AjaxResult();
		try {
			// List<Map<String,String>> result =
			// reportModelManage001Service.searchReports(RequestUtil.getPageParam());
			List<ReportModelManage001Dto> result = reportModelManage001Service
					.searchReportsNew(RequestUtil.getPageParam());
			value.setData(result);
		} catch (Exception e) {
			value.setMessage("查询报表模板:" + e.getMessage(), MessageType.错误消息);
			// e.printStackTrace();
			logger.error("error:", e);
		} finally {
			//return value;
		}
		return value;
	}

	/**
	 * 左侧栏查询 <一句话功能简述> <功能详细描述>
	 * 
	 * @return
	 * @throws Exception
	 */
	 @PostMapping("findPage/{page}/{length}")
	public Page<DisPoiTab> findPage(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		Page<DisPoiTab> page1 = poireportModelManage001Service.findByPage(page, length, param);
		return page1;
	}

	@PostMapping("/deleteModle")
	public void deleteModle(@RequestBody Map<String, Object> param) {
		reportModelManage002Service.deleteModle(param);
//		return new AjaxResult("删除成功");
	}
	
	@GetMapping("delete")
    public int selectById(String id){
    	return poireportModelManage001Service.delete(id);
    }
}
