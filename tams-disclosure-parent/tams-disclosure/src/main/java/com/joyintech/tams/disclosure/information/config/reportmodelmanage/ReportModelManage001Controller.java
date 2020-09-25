/*
 * 文件名：ReportTemplate001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.reportmodelmanage;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.information.config.reportcontentenum.dto.ReportContentEnum001Dto;
import com.joyintech.tams.disclosure.information.config.reportmodelmanage.service.ReportModelManage001Service;
import com.joyintech.tams.disclosure.information.config.reportmodelmanage.service.ReportModelManage002Service;
import com.joyintech.system.constant.SystemGlobalConstant;

/**
 * 报表模板管理 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author lishicheng
 * @version 1.0 2018年11月9日
 * @see ReportModelManage001Controller
 * @since 1.0
 */
@Controller
@PageId("ReportModelManage001")
@RequestMapping("/disclosure/information/config/reportmodelmanage/ReportModelManage001")
public class ReportModelManage001Controller extends BaseController {

	@SuppressWarnings("rawtypes")
	@Autowired
	private CommonExportService commonExportService;

	@Autowired
	private ReportModelManage001Service reportModelManage001Service;
	@Autowired
	private ReportModelManage002Service reportModelManage002Service;
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(ReportModelManage001Controller.class);

	@Override
	public String init(Model model) {
		String isModelSelect = this.getParam("isModelSelect");

		model.addAttribute(ReportGlobalConstant.REPORT_SERVER_URL,
				CacheUtil.getSysParam(ReportGlobalConstant.REPORT_SERVER_URL));
		model.addAttribute(ReportGlobalConstant.REPORT_SERVER_PATH,
				CacheUtil.getSysParam(ReportGlobalConstant.REPORT_SERVER_PATH));
		model.addAttribute(ReportGlobalConstant.REPORT_USERNAME,
				CacheUtil.getSysParam(ReportGlobalConstant.REPORT_USERNAME));
		model.addAttribute(ReportGlobalConstant.REPORT_PASSWORD,
				CacheUtil.getSysParam(ReportGlobalConstant.REPORT_PASSWORD));

		model.addAttribute("isModelSelect", isModelSelect);
		return super.init(model);
	}

	/**
	 * 导出 <一句话功能简述> <功能详细描述>
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(ACTION_EXPORT)
	public ResponseEntity<byte[]> export() throws Exception {
		// Map<String, Object> paraMap = RequestUtil.getPageParam();
		String datas = this.getParam("datas");
		List<ReportModelManage001Dto> listdto = null;
		if (datas == null) {
			listdto = reportModelManage001Service.searchReportsNew(RequestUtil.getPageParam());
		} else {
			listdto = JsonUtils.json2List(datas, ReportModelManage001Dto.class);
		}
		File outFile = commonExportService.makeFile(
				"/com/joyintech/tams/disclosure/information/config/reportmodelmanage/export_reportmodelmanage001.xml",
				listdto);
		String exportFileName = this.getPageId();
		com.joyintech.framework.base.intf.Page page = CacheUtil.getPage(this.getPageId());
        if(null != page) {
            exportFileName = page.getName()
                             + SystemGlobalConstant.OPEN_PARENTHESIS + DateUtil.getCurrentDate()
                             + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
                             + FileNameUtil.getFileExtName(outFile);
        }
		return ExportUtil.export(exportFileName, outFile, true);
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
	@ResponseBody
	@RequestMapping("findPage")
	public Page<ReportModelManage001Dto> findIndexId() throws Exception {
		Page<ReportModelManage001Dto> page = reportModelManage001Service.findByPage(RequestUtil.getPageParam());
		return page;
	}

	@ResponseBody
	@RequestMapping("deleteModle")
	public AjaxResult deleteReport(@RequestBody Map<String, Object> param) {
		reportModelManage002Service.deleteModle(param);
		return new AjaxResult("删除成功");
	}
}
