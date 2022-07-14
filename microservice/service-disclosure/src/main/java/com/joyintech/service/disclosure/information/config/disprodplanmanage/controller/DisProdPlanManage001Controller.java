/*
 * 文件名：ReportTemplate001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.disclosure.information.config.disprodplanmanage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.joyintech.service.disclosure.information.config.disprodplanmanage.dto.DisProdPlanManage001Dto;
import com.joyintech.service.disclosure.information.config.disprodplanmanage.dto.DisProdPlanManage002Dto;
import com.joyintech.service.disclosure.information.config.disprodplanmanage.service.DisProdPlanManage001Service;
import com.joyintech.service.disclosure.information.config.disprodplanmanage.service.DisProdPlanManage002Service;
import com.joyintech.tams.constant.DictCodeListExt3;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

/**
 * 报表模板管理 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author lishicheng
 * @version 1.0 2018年11月9日
 * @see DisProdPlanManage001Controller
 * @since 1.0
 */
@RestController(value="serviceDisProdPlanManage001Controller")
@RequestMapping("/DisProdPlanManage001")
public class DisProdPlanManage001Controller extends BaseController {

	@SuppressWarnings("rawtypes")
	@Autowired
	private CommonExportService commonExportService;

	@Autowired
	private DisProdPlanManage001Service disProdPlanManage001Service;
	@Autowired
	private DisProdPlanManage002Service disProdPlanManage002Service;
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(DisProdPlanManage001Controller.class);


	/**
	 * 导出 <一句话功能简述> <功能详细描述>
	 * 
	 * @return
	 * @throws Exception
	 */
//	@SuppressWarnings("unchecked")
//	@RequestMapping(ACTION_EXPORT)
//	public ResponseEntity<byte[]> export() throws Exception {
//		// Map<String, Object> paraMap = RequestUtil.getPageParam();
//		String datas = this.getParam("datas");
//		List<DisProdPlanManage001Dto> listdto = null;
//		if (datas == null) {
//			listdto = DisProdPlanManage001Service.searchReportsNew(RequestUtil.getPageParam());
//		} else {
//			listdto = JsonUtils.json2List(datas, DisProdPlanManage001Dto.class);
//		}
//		File outFile = commonExportService.makeFile(
//				"/com/joyintech/tams/disclosure/information/config/DisProdPlanManage/export_DisProdPlanManage001.xml",
//				listdto);
//		return ExportUtil.export(this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
//				+ DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
//				+ FileNameUtil.getFileExtName(outFile), outFile, true);
//	}
	
	  @PostMapping("/findAll")
	    public List<DisProdPlanManage001Dto> findAll(@RequestBody Map<String, Object> param){
//	        return valAssetsFundQuery001Service.findPage(RequestUtil.getPageParam());
	    	 return disProdPlanManage001Service.searchReportsNew(param);
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
	public ResponseEntity<byte[]> exportZip() throws IOException {
		String datas = this.getParam("datas");
		List<DisProdPlanManage001Dto> val001dto = JsonUtils.json2List(datas, DisProdPlanManage001Dto.class);
		Map<String, Object> param = RequestUtil.getPageParam();
		if (!StringUtil.isEmptyList(val001dto)) {
			for (DisProdPlanManage001Dto dto : val001dto) {
				dto.setParam(param);
			}
		}
		return disProdPlanManage001Service.exportExcel(val001dto);
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
	public ResponseEntity<byte[]> mergeExport() throws IOException, InstantiationException {
		Map<String, Object> param = RequestUtil.getPageParam();
		DisProdPlanManage001Dto disProdPlanManage001Dto = null;
		try {
			disProdPlanManage001Dto = (DisProdPlanManage001Dto) JsonUtils.map2entity(param,
					DisProdPlanManage001Dto.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new InstantiationException();
		}
		disProdPlanManage001Dto.setParam(param);
		return disProdPlanManage001Service.mergeExportSingle(disProdPlanManage001Dto);
	}

	@SuppressWarnings("finally")
	@RequestMapping("/searchReports")
	@ResponseBody
	public AjaxResult searchReports() {
		AjaxResult value = new AjaxResult();
		try {
			// List<Map<String,String>> result =
			// disProdPlanManage001Service.searchReports(RequestUtil.getPageParam());
			List<DisProdPlanManage001Dto> result = disProdPlanManage001Service
					.searchReportsNew(RequestUtil.getPageParam());
			value.setData(result);
		} catch (Exception e) {
			value.setMessage("查询报表模板:" + e.getMessage(), MessageType.错误消息);
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
	public Page<DisProdPlanManage001Dto> findPage(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length)
	 {
		Page<DisProdPlanManage001Dto> page1 = disProdPlanManage001Service.findByPage(page, length, param);
		return page1;
	}

	@PostMapping("/delete")
	public void delete(@RequestBody Map<String, Object> param) {
		String ids =param.get("prodPlanId").toString() ;
		List<String> idList = new ArrayList();
		if(StringUtils.isNotEmpty(ids)){
			if(StringUtils.contains(ids,",")){
				idList = Arrays.asList(ids.split(",")).stream().collect(Collectors.toList());
			}else{
				idList.add(ids);
			}
		}
		idList.forEach(i->{
			DisProdPlanManage002Dto dto = new DisProdPlanManage002Dto();
			dto.setProdPlanId(i);
			dto.setDataStatus(DictCodeListExt3.信披_数据状态_删除.getValue());
			disProdPlanManage002Service.update(dto);
		});
		// disProdPlanManage002Service.delete(param);
//		return new AjaxResult("删除成功");
	}
}
