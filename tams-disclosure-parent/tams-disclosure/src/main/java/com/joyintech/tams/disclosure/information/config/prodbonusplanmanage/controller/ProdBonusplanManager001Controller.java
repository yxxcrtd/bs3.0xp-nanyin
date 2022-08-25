/*
 * 文件名：ReportTemplate001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt4;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.service.GetPrdInfoSelectService;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;
import com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.dto.ProdBonusplanDto;
import com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.service.ProdBonusplanManager001Service;
import com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.service.ProdBonusplanManager002Service;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;

/**
 * 产品分红到期计划 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author lishicheng
 * @version 1.0 2018年11月9日
 * @see ProdBonusplanManager001Controller
 * @since 1.0
 */
@Controller
@PageId("ProdBonusplanManager001")
@RequestMapping("/disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager001")
public class ProdBonusplanManager001Controller extends BaseController {

	@Autowired
	private ProdBonusplanManager001Service prodBonusplanManager001Service;
	@Autowired
	private ProdBonusplanManager002Service prodBonusplanManager002Service;


	@Autowired
	private GetPrdInfoSelectService getPrdInfoSelectService;

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(ProdBonusplanManager001Controller.class);

	@Override
	public String init(Model model) {

		this.setSelectOptionToModel(model, DictCode.信披_数据状态ED);
		this.setSelectOptionToModel(model, DictCode.信披_分红方式);
		return super.init(model);
	}

	/**
	 * 导出 <一句话功能简述> <功能详细描述>
	 * 
	 * @return
	 * @throws Exception*/
	@RequestMapping("export")
	public ResponseEntity<byte[]> export(){
		return prodBonusplanManager001Service.export(this.getPageId(),
				RequestUtil.getParameters(getRequest()));
	}

/*	@SuppressWarnings("unchecked")
	@RequestMapping(ACTION_EXPORT)
	public ResponseEntity<byte[]> export() throws IOException {
		// Map<String, Object> paraMap = RequestUtil.getPageParam();
		String datas = this.getParam("datas");
		List<ProdBonusplanDto> listdto = null;
		if (datas == null) {
			listdto = prodBonusplanManager001Service.searchReportsNew(RequestUtil.getPageParam());
		} else {
			listdto = JsonUtils.json2List(datas, ProdBonusplanDto.class);
		}
		File outFile = commonExportService.makeFile(
				"/com/joyintech/tams/disclosure/information/config/prodbonusplanmanage/export_prodbonusplanmanager001.xml",
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
	}*/





	@SuppressWarnings("finally")
	@RequestMapping("/searchReports")
	@ResponseBody
	public AjaxResult searchReports() {
		AjaxResult value = new AjaxResult();
		try {
			List<ProdBonusplanDto> result = prodBonusplanManager001Service
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
	 * @throws Exception*/

	@ResponseBody
	@RequestMapping("findPage")
	public Page<ProdBonusplanDto> findIndexId() {
		Page<ProdBonusplanDto> page = prodBonusplanManager001Service.findByPage(RequestUtil.getPageParam());
		return page;
	}

	@ResponseBody
	@RequestMapping("delete")
	public AjaxResult delete(@RequestBody @Validated({
			ValidateGroupEdit.class}) ProdBonusplanDto dto, BindingResult result) {
		/*prodBonusplanManager002Service.delete(param);
		return new AjaxResult("删除成功");*/

		dto.setUpdateUser(LoginUserUtil.getUserName());
		ProcessParameter<ProdBonusplanDto> processParameter = new ProcessParameter<ProdBonusplanDto>();
		WorkFlowParameter<ProdBonusplanDto> params = new WorkFlowParameter<ProdBonusplanDto>();
		params.setPageId("ProdBonusplanManager002");
		params.setElementId(dto.getElementId());
		params.setBusinessCode(item -> item.getProdId());
		params.setBusinessType(DictCodeListExt4.共通业务类型_产品分红到期计划.getValue());
		params.setComment(entity -> "产品分红到期计划-产品代码"+ "(" + entity.getProdId() + ")");
		processParameter.setOperationType(OperationType.DELETE);
		processParameter.setWorkFlowParameter(params);
		//String msg =prodBonusplanManager002Service.start(processParameter, dto);
		String msg =prodBonusplanManager002Service.start(processParameter, dto.getSelectedItem().toArray(new ProdBonusplanDto[dto.getSelectedItem().size()]));

		return new AjaxResult(msg);
	}


	/**
	 * 分页查询产品下拉框
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
		return prodBonusplanManager001Service.checkImportData(fileName);
	}


	@ResponseBody
	@RequestMapping("importFile")
	public List<ExcelValidDto> importFile(String fileName)  {
		return prodBonusplanManager001Service.importData(fileName);
	}
}
