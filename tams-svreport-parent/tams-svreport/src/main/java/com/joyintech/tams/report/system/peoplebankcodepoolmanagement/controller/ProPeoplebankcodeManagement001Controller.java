package com.joyintech.tams.report.system.peoplebankcodepoolmanagement.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.system.peoplebankcodepoolmanagement.dto.ProPeoplebankcodeManagement001Dto;
import com.joyintech.tams.report.system.peoplebankcodepoolmanagement.service.ProPeoplebankcodeManagement001Service;
import com.joyintech.tams.report.system.peoplebankcodepoolmanagement.service.ProPeoplebankcodeManagementMService;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.systemtools.util.PageElementUtil;

/**
 * 人行产品资产池代码管理Controller
 * 
 * @author kx
 *
 */
@Controller
@PageId("ProPeoplebankcodeManagement001")
@RequestMapping("/svreport/system/peoplebankcodepoolmanagement/ProPeoplebankcodeManagement001")
public class ProPeoplebankcodeManagement001Controller extends BaseController {

	@Autowired
	private ProPeoplebankcodeManagement001Service peoplebankcodeManagement001Service;

	@Autowired
	private ProPeoplebankcodeManagementMService peoplebankcodeManagementMService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {
		// 判断数据是否从主页代维护信息加载
		if (RequestUtil.getPageParam().containsKey("status")) {
			model.addAttribute("isFromMain", "01");
		} else {
			model.addAttribute("isFromMain", "02");
		}
		String myOrgCode = CommonUtil.getOrgCode();
		if (StringUtil.isNotEmpty(myOrgCode)) {
			model.addAttribute("MY_AGENCY_CODE", myOrgCode);
		}
		// dingzhipeng add end
		super.setSelectOptionToModel(model, DictCode.目标客户类型, DictCode.发送状态);

		model.addAttribute("YES", DictCodeList.是否_是.getValue());
		// 画面元素是否显示
		PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
		pageElementConfigDto.setPageId(this.getPageId());
		Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto);
		model.addAttribute("isEleShow", isEleShow);
		return super.init(model);
	}

	/**
	 * 内部产品代码下拉框
	 * 
	 * @return 内部产品代码
	 */
	@ResponseBody
	@RequestMapping("getInsideProdId")
	public List<SelectOptionDto> queryInsideProdIdList() {
		return peoplebankcodeManagementMService.queryInsideProdIdList(RequestUtil.getPageParam());
	}

	/**
	 * 查询列表
	 *
	 * @return DtPage
	 */
	@ResponseBody
	@RequestMapping("searchPage")
	public Page<ProPeoplebankcodeManagement001Dto> search() {
		Map<String, Object> param = RequestUtil.getPageParam();
		return peoplebankcodeManagementMService.searchForPage(param);
	}

	/**
	 * 导出excel
	 *
	 * @return 导出
	 */
	@RequestMapping(value = ACTION_EXPORT)
	public ResponseEntity<byte[]> export() {
		List<ProPeoplebankcodeManagement001Dto> dtoList = this.peoplebankcodeManagementMService.queryReportList(RequestUtil.getPageParam());
		File outFile = peoplebankcodeManagement001Service.makeFile(
				"/com/joyintech/tams/report/system/peoplebankcodepoolmanagement/conf/export_conf.xml", dtoList);
		return ExportUtil.export(this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
				+ DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS + GlobalConstant.POINT
				+ "xlsx", outFile, true);
	}
	
	 /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = "checkConfirmMsgForImportData", method = RequestMethod.POST)
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
        return peoplebankcodeManagementMService.checkConfirmMsgForImportData(fileName);
    }
    
    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @ResponseBody
    @RequestMapping("/importFile")
    public List<ExcelValidDto> importData(String fileName) {
        return this.peoplebankcodeManagementMService.importData(fileName);
    }

}
