package com.joyintech.tams.svreport.loaninfomanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.report.common.dto.ExcelValidDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.common.base.entity.BaseInformationDto;
import com.joyintech.tams.svreport.infomanagement.service.OperationalInfoMService;
import com.joyintech.tams.svreport.loaninfomanagement.dto.OperationalLoanInfo001Dto;
import com.joyintech.tams.svreport.loaninfomanagement.service.OperationalLoanInfoMService;

/**
 * 贷款明细信息维护 Controller
 * @author hpp
 *
 */
@Controller
@PageId("loanplan")
@RequestMapping("/common/loaninfomanagement/OperationalLoanInfo001")
public class OperationalLoanInfo001Controller extends BaseController{

	@Autowired
	private OperationalLoanInfoMService operationalLoanInfoMService;
	@Autowired
	private OperationalInfoMService operationalInfoMService;
	
	@RequestMapping("{pid}/" + PAGE_INIT)
	public String init(Model model, @PathVariable("pid") String pid) {
		
		//根据计划名称查询计划id以及待维护表ID
		OperationalLoanInfo001Dto dto = operationalLoanInfoMService.queryPlanInfo(pid);
		model.addAttribute("filterId", dto.getFilterid());
		// 设置默认查询条件 "CHB_LOAN_DETAIL_INFO"
        model.addAttribute("tableId", dto.getBasetableid());
        
		List<SelectOptionDto> listTableName = new ArrayList<SelectOptionDto>();
		List<BaseInformationDto> baseInfomationList = operationalInfoMService.queryAllTableInfo();
		baseInfomationList.forEach(baseInformationDto -> {
			SelectOptionDto selectOptionDto = new SelectOptionDto();
            selectOptionDto.setId(baseInformationDto.getBaseTableId());
            selectOptionDto.setText(baseInformationDto.getBaseTableName());
            selectOptionDto.setCol("");
            listTableName.add(selectOptionDto);
		});
        model.addAttribute("tableNames", JsonUtils.objectArray2Json(listTableName.toArray())); 
        
        return super.init(model);
	}
	
	/**
	 * 分页查询
	 * @return 业务数据维护查询结果
	 */
	@ResponseBody
    @RequestMapping("findPage")
    public String queryData() {
		Map<String, Object> param = RequestUtil.getPageParam();
         String map =operationalLoanInfoMService.queryData(param);
         return map;
    }
	
	/**
	 * 根据主键删除数据
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping("delete")
	public AjaxResult doDelete(@RequestParam Map<String, Object> param){
		AjaxResult ar = new AjaxResult(this.getMessage("REPORT_COMMON_I_0007"));
		operationalLoanInfoMService.doDelete(param);
		return ar;
	}
	
	/**
     * 导出excel
     *
     * @return 导出
     */
	@ResponseBody
	@RequestMapping("export")
    public ResponseEntity<byte[]> export() {
    	Map<String, Object> param = RequestUtil.getPageParam();
    	return operationalInfoMService.export(param);
    }
    
    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = "checkConfirmMsgForImportData", method = RequestMethod.POST)
    public List<ExcelValidDto> checkConfirmMsgForImportData(String fileName)  {
        return operationalInfoMService.checkConfirmMsgForImportData(fileName);
    }
    
    
    /**
     * 导入数据文件
     * 
     * @param fileName 导入的文件名
     * @return 修改条数
     */
    @ResponseBody
    @RequestMapping("/importFile")
    public AjaxResult importData(String fileName) {
    	return operationalInfoMService.importData(fileName);
    }
	
}
