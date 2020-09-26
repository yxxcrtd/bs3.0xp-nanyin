package com.joyintech.tams.svreport.chb.proddurationreg.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.chb.proddurationreg.dto.ProdDurationReg001Dto;
import com.joyintech.tams.svreport.chb.proddurationreg.service.ProdDurationReg001Service;
import com.joyintech.tams.svreport.chb.proddurationreg.service.ProdDurationRegMService;
import com.joyintech.tams.svreport.chb.prodpersonissue.dto.ProdPersonIssue001Dto;

/**
 * 〈功能详细描述〉
 * @author lingxq
 * @version 1.0 2019年1月22日
 * @see ProdDurationReg001Controller
 * @since 1.0
 */
@Controller
@PageId("SvrProdDurationReg001")
@RequestMapping("/chb/proddurationreg/ProdDurationReg001")
public class ProdDurationReg001Controller extends BaseController {
   
	@Autowired
	private ProdDurationReg001Service prodDurationReg001Service;
	@Autowired
	private ProdDurationRegMService prodDurationRegMService;
	/**
     * 页面初始化
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
    	Map<String, Object> map = RequestUtil.getPageParam();
		if (StringUtil.isNotEmpty(map.get("submitStatus"))) {
			model.addAttribute("submitStatus", map.get("submitStatus").toString().trim());
			if (StringUtil.isNotEmpty(map.get("status"))) {
				model.addAttribute("status", map.get("status").toString().trim());
			}
			if(StringUtil.isNotEmpty(map.get("submitFeedbackStatus"))){
            	model.addAttribute("submitFeedbackStatus", map.get("submitFeedbackStatus").toString().trim());
            }
			model.addAttribute("searchDataStart", map.get("searchDataStart"));
			model.addAttribute("searchDataEnd", map.get("searchDataEnd"));
			model.addAttribute("isMain", "01");
		}
        super.setSelectOptionToModel(model, 
        		DictCode.报送状态,
        		DictCode.报送反馈状态,
        		DictCode.报送数据状态,
        		DictCode.产品运作模式);
        return super.init(model);
    }
    
    /**
    *
    * 业务画面查询事件
    * <功能详细描述>
    * @return
    * @see
    * @since 1.0
    */
   @ResponseBody
   @RequestMapping("findPage")
   public Page<ProdDurationReg001Dto> selectAllData() {
       Page<ProdDurationReg001Dto> page = prodDurationRegMService.selectForPage(RequestUtil.getPageParam());
       return page;
   }

   /**
	 *
	 * 业务画面导出事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("export")
	public ResponseEntity<byte[]> export() {
		Map<String, Object> pageParam = RequestUtil.getPageParam();
		if(pageParam.containsKey("keyList")){
	 		List<ProdDurationReg001Dto>  dataList=prodDurationRegMService.searchForExport(pageParam);
	 		  File outFile = prodDurationReg001Service.makeFile(
	 	             "/com/joyintech/tams/svreport/chb/proddurationreg/conf/export_conf.xml", dataList);
	 	         return ExportUtil.export(
	 	             this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
	 	                                  + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS
	 	                                  + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
	 	             outFile, true);
		}else{
			return prodDurationReg001Service.export(this.getPageId(), pageParam);
	 	}
	}
    
	/**
	 * 手工处理功能
	 *
	 * @return 提示信息
	 * @param map
	 *            条件map
	 * @seea
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("doManual")
	public AjaxResult doUpdate(@RequestBody Map<String, Object> map) {
		prodDurationRegMService.updateSumStaus(map);
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	}
	
	/**
	 * 取消手工处理功能
	 *
	 * @return 提示信息
	 * @param map
	 *            条件map
	 * @seea
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping(value = "/cancelMannul", method = RequestMethod.POST)
	public AjaxResult cancelMannul(@RequestBody Map<String, Object> map) {
		prodDurationRegMService.cancelMannul(map);
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	}
	
//	/**
//	 * 报送检查
//	 *
//	 * @return 检查结果
//	 * @see
//	 * @since 1.0
//	 */
//	@ResponseBody
//	@RequestMapping("sendBeforeCheck")
//	public List<ExcelValidDto> sendBeforeCheck() {
//		Map<String, Object> param = RequestUtil.getPageParam();
//		return null;
//	}
	 /**
     * 数据检查
     * @param map 查询条件
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("reportCheck")
    public List<ExcelValidDto> reportCheck() {
        return prodDurationRegMService.reportCheck(RequestUtil.getPageParam());
    }
}
