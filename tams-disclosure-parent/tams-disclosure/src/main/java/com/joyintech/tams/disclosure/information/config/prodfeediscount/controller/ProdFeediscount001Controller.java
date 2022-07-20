/*
 * 文件名：ReportTemplate001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.prodfeediscount.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt4;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.service.GetPrdInfoSelectService;
import com.joyintech.tams.disclosure.information.config.prodfeediscount.dto.ProdFeediscountDto;
import com.joyintech.tams.disclosure.information.config.prodfeediscount.service.ProdFeediscountService;
import com.joyintech.tams.disclosure.information.config.proelementadj.dto.ProElementAdj001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 产品费率优惠 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author lishicheng
 * @version 1.0 2018年11月9日
 * @see ProdFeediscount001Controller
 * @since 1.0
 */
@Controller
@PageId("ProdFeediscount001")
@RequestMapping("/disclosure/information/config/prodfeediscount/ProdFeediscount001")
public class ProdFeediscount001Controller extends BaseController {

	@SuppressWarnings("rawtypes")
	@Autowired
	private CommonExportService commonExportService;

	@Autowired
	private ProdFeediscountService prodFeediscountService;


	@Autowired
	private GetPrdInfoSelectService getPrdInfoSelectService;

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(ProdFeediscount001Controller.class);

	@Override
	public String init(Model model) {

		this.setSelectOptionToModel(model, DictCode.信披_数据状态);
		this.setSelectOptionToModel(model, DictCode.信披_分红方式);
		return super.init(model);
	}

	/**
	 * 导出 <一句话功能简述> <功能详细描述>
	 * 
	 * @return
	 * @throws Exception*/

	@SuppressWarnings("unchecked")
	@RequestMapping(ACTION_EXPORT)
	public ResponseEntity<byte[]> export() throws IOException {
		// Map<String, Object> paraMap = RequestUtil.getPageParam();
		String datas = this.getParam("datas");
		List<ProdFeediscountDto> listdto = null;
		if (datas == null) {
			listdto = prodFeediscountService.findAll(RequestUtil.getPageParam());
		} else {
			listdto = JsonUtils.json2List(datas, ProdFeediscountDto.class);
		}
		File outFile = commonExportService.makeFile(
				"/com/joyintech/tams/disclosure/information/config/prodfeediscount/export_prodfeediscount001.xml",
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





	@SuppressWarnings("finally")
	@RequestMapping("/searchReports")
	@ResponseBody
	public AjaxResult searchReports() {
		AjaxResult value = new AjaxResult();
		try {
			List<ProdFeediscountDto> result = prodFeediscountService.findAll(RequestUtil.getPageParam());
			value.setData(result);
		} catch (Exception e) {
			value.setMessage("查询报表模板:" + e.getMessage(), MessageType.错误消息);
			logger.error("error:", e);
		} finally {
			//return value;
		}
		return value;
	}


/*	@ResponseBody
	@RequestMapping("findPage")
	public Page<ProdBonusplanDto> findIndexId() {
		Page<ProdFeediscountDto> page = prodFeediscountService.findByPage(RequestUtil.getPageParam());
		return page;
	}*/
	/**
	 * 左侧栏查询 <一句话功能简述> <功能详细描述>
	 *
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("findPage")
	public Page<ProdFeediscountDto> findPage(){
		Page<ProdFeediscountDto> page = prodFeediscountService.findByPage(RequestUtil.getPageParam());
		return page;
	}


	@ResponseBody
	@RequestMapping("delete")
	public AjaxResult delete(@RequestBody @Validated({
			ValidateGroupEdit.class}) ProdFeediscountDto dto) {
		dto.setUpdateUser(LoginUserUtil.getUserName());
		ProcessParameter<ProdFeediscountDto> processParameter = new ProcessParameter<ProdFeediscountDto>();
		WorkFlowParameter<ProdFeediscountDto> params = new WorkFlowParameter<ProdFeediscountDto>();
		params.setPageId("ProdFeediscount002");
		params.setElementId(dto.getElementId());
		params.setBusinessCode(dto.getProdDiscountId());
		params.setBusinessType(DictCodeListExt4.共通业务类型_产品费率优惠.getValue());
		params.setComment(entity -> "产品费率优惠-产品代码"+ "(" + entity.getProdId() + ")");
		processParameter.setOperationType(OperationType.DELETE);
		processParameter.setWorkFlowParameter(params);
		String msg =prodFeediscountService.start(processParameter,
				dto.getSelectedItem().toArray(new ProdFeediscountDto[dto.getSelectedItem().size()]));
		return new AjaxResult(msg);
	}


	/**
	 * 分页查询产品下拉框
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getProdFeenameSelect")
	@ResponseBody
	public Page<SelectOptionDto> getProdFeenameSelect() {
		Map<String, Object> param= RequestUtil.getPageParam();
		Page<SelectOptionDto> prdInfoSelect = prodFeediscountService.getProdFeenameSelect(param);
		return prdInfoSelect;
	}


	/**
	 * 分页查询费率名称下拉
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getPrdInfoSelect")
	@ResponseBody
	public Page<SelectOptionDto> getPrdInfoSelect() {
		Map<String, Object> param= RequestUtil.getPageParam();
		Page<SelectOptionDto> prdInfoSelect = getPrdInfoSelectService.getPrdInfoSelect(param);
		return prdInfoSelect;
	}

	/**
	 * 导入
	 *
	 * @param fileName 导入的文件名
	 * @return 处理结果
	 * @throws Exception 异常
	 */
	@ResponseBody
	@RequestMapping("checkImportData")
	public AjaxResult checkImportData(String fileName) {
		return prodFeediscountService.checkImportData(fileName);
	}


	@ResponseBody
	@RequestMapping("importFile")
	public AjaxResult importFile(@RequestBody Map<String, Object> param)  {
		return prodFeediscountService.importFile(param);
	}
}
