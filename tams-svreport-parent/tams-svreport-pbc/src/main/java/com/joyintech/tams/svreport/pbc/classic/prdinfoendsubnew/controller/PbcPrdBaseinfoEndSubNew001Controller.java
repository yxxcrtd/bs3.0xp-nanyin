package com.joyintech.tams.svreport.pbc.classic.prdinfoendsubnew.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.*;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubnew.dto.PbcPrdBaseinfoEndSubNew;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubnew.serivce.PbcPrdBaseinfoEndSubNew001Service;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubnew.service.PbcPrdBaseinfoEndSubNewMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.dto.ExcelValidDto;

import static java.util.stream.Collectors.toList;

/**
 * 
 * <p>人行报送信息登记-产品终止登记Controller</p>
 * @author liuyu
 * @创建时间 2019年6月4日 下午4:57:28
 */
@Controller
@PageId("PbcPrdBaseinfoEndSubNew001")
@RequestMapping("/pbc/prdinfoendsubnew/PbcPrdBaseinfoEndSubNew001")
public class PbcPrdBaseinfoEndSubNew001Controller extends BaseController {
	/**
	 * 产品终止登记service
	 */
	@Autowired
	private PbcPrdBaseinfoEndSubNew001Service prdBaseinfoEndSubNew001Service;
	@Autowired
	private PbcPrdBaseinfoEndSubNewMService pbcPrdBaseinfoEndSubNewMService;

	/**
	 * 页面初始化
	 */
	@Override
	public String init(Model model) {
		Map<String, Object> map = RequestUtil.getPageParam();
		if (StringUtil.isNotEmpty(map.get("submitStatus"))) {
			model.addAttribute("submitStatus", map.get("submitStatus").toString().trim());
			if (StringUtil.isNotEmpty(map.get("status"))) {
				model.addAttribute("status", map.get("status").toString().trim());
			}
			if (StringUtil.isNotEmpty(map.get("submitFeedbackStatus"))) {
				model.addAttribute("submitFeedbackStatus", map.get("submitFeedbackStatus").toString().trim());
			}
			model.addAttribute("searchDataStart", map.get("searchDataStart"));
			model.addAttribute("searchDataEnd", map.get("searchDataEnd"));
			model.addAttribute("isMain", "01");
		}
		super.setSelectOptionToModel(model, DictCode.报送数据状态, DictCode.报送状态, DictCode.人行报送反馈状态, DictCode.人行产品类型, DictCode.人行产品运作模式);
		return super.init(model);
	}

	/**
	 * 产品终止登记查询
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping("searchPage")
	public Page<PbcPrdBaseinfoEndSubNew> searchPage() {
		Map<String, Object> searchParam = RequestUtil.getPageParam();
		Page<PbcPrdBaseinfoEndSubNew> page = pbcPrdBaseinfoEndSubNewMService.searchPage(searchParam);
		return page;

	}

	/**
	 * 业务画面导出事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("export")
	public ResponseEntity<byte[]> export() {
		return prdBaseinfoEndSubNew001Service.export("PIB产品终止信息(资产管理)_" +DateUtil.formatDate(DateUtil.getCurrentDate())+ GlobalConstant.POINT +"xls", RequestUtil.getPageParam());
	}

	/**
	 * 手工处理
	 *
	 * @param updateList
	 * @return
	 */
	@ResponseBody
	@RequestMapping("doManul")
	public AjaxResult manul(@RequestBody Map<String, Object> map) {
		pbcPrdBaseinfoEndSubNewMService.updateSumStaus(map);
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	}

	/**
	 * 取消手工处理
	 *
	 * @param updateList
	 * @return
	 */
	@ResponseBody
	@RequestMapping("cancelManul")
	public AjaxResult cancelManul(@RequestBody Map<String, Object> map) {
		String result = pbcPrdBaseinfoEndSubNewMService.cancelMannul(map);
		if ("fail".equals(result)) {
			AjaxResult ajaxresult = new AjaxResult("有未报送的数据", MessageType.错误消息);
			ajaxresult.setStatuCode(HttpStatus.OK.value());
			return ajaxresult;
		}
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	}

	/**
	 * 数据检查
	 */
	@ResponseBody
	@RequestMapping("reportCheck")
	public List<ExcelValidDto> reportCheck() {
		return pbcPrdBaseinfoEndSubNewMService.reportCheck(RequestUtil.getPageParam());
	}


	/**
	 * 产品终止登记人行导出Excel
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("pedestrianExport")
	public ResponseEntity<byte[]> pedestrianExport() throws IOException {
		String datas = this.getParam("datas");
		Map<String, Object> paraMap = RequestUtil.getPageParam();
		List<PbcPrdBaseinfoEndSubNew> dtoList;

		if (null == datas) {
			dtoList = prdBaseinfoEndSubNew001Service.searchByExcel(paraMap);
		} else {
			dtoList = JsonUtils.json2List(datas, PbcPrdBaseinfoEndSubNew.class);
			String seqno = dtoList.stream().map(PbcPrdBaseinfoEndSubNew::getRegistSeqno).collect(toList()).toString();
			paraMap.put("keyList", seqno.substring(1, seqno.length() - 1));
			dtoList = prdBaseinfoEndSubNew001Service.searchByExcel(paraMap);
		}

		File outFile = prdBaseinfoEndSubNew001Service.makeFile(
				"/com/joyintech/tams/svreport/pbc/classic/prdinfoendsubnew/conf/export_confNew.xml",
				dtoList);
		return ExportUtil.export(" PIB产品终止信息(资产管理)报送内容_"
						+ DateUtil.formatDate(DateUtil.getCurrentDate())
						+ GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
				outFile, true);
	}
}



