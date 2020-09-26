package com.joyintech.tams.svreport.chb.prodterminationregist.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.chb.prodterminationregist.dto.ProdTerminationRegist001Dto;
import com.joyintech.tams.svreport.chb.prodterminationregist.service.ProdTerminationRegist001Service;
import com.joyintech.tams.svreport.chb.prodterminationregist.service.ProdTerminationRegistMService;


/**
 * @Description 产品终止登记Controller
 * @author jiangxiao
 * @version 3.0 2019年1月23日
 * @since 3.0
 */
@Controller
@PageId("SvrProdTerminationRegist001")
@RequestMapping("/chb/prodterminationregist/ProdTerminationRegist001")
public class ProdTerminationRegist001Controller extends BaseController {

	@Autowired
	private ProdTerminationRegist001Service prodTerminationRegist001Service;
	@Autowired
	private ProdTerminationRegistMService prodTerminationRegistMService;
	/**
     * 初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
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
    	super.setSelectOptionToModel(model, DictCode.报送数据状态,
                DictCode.报送状态,DictCode.报送反馈状态);
            model.addAttribute("indentifyMark",RequestUtil.getParam("indentifyMark"));
            return super.init(model);
    }
    
    /**
     * @Description 产品终止登记页面查询
     * @return Page<ProdTerminationRegist001Dto>
     * @version 3.0 2019年1月23日
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ProdTerminationRegist001Dto> selectAllData() {
        Page<ProdTerminationRegist001Dto> page = prodTerminationRegistMService.selectForPage(RequestUtil.getPageParam());
        return page;
    }
    
    /**
     * 业务画面导出事件
     * @return
     * @since 3.0
     */
    @ResponseBody
    @RequestMapping("export")
    public ResponseEntity<byte[]> export() {
    	Map<String, Object> pageParam = RequestUtil.getPageParam();
    	if(pageParam.containsKey("keyList")){
          List<ProdTerminationRegist001Dto> dtoList = prodTerminationRegistMService.selectForExport(RequestUtil.getParameters(getRequest()));
          File outFile = prodTerminationRegist001Service.makeFile(
              "/com/joyintech/tams/svreport/chb/prodterminationregist/conf/export_conf.xml", dtoList);
          return ExportUtil.export(
              this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
                                   + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS
                                   + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
              outFile, true);
    	}
    	else{
          return prodTerminationRegist001Service.export(this.getPageId(), RequestUtil.getPageParam());
    	}
    }
    /**
     *手工处理功能
     * @return 提示信息
     * @param map 条件map
     */
    @ResponseBody
    @RequestMapping(value = SVReportGlobalConstant.PAGE_UPDATE ,method = RequestMethod.POST)
    public AjaxResult doUpdate(@RequestBody Map<String,Object> map) {
    	prodTerminationRegistMService.updateSumStaus(map);
        return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
    }
    
    /**
     * @Description 取消手工处理
     * @param map
     * @return
     * @return AjaxResult
     * @version 3.0 2019年1月24日
     */
    @ResponseBody
	@RequestMapping(value = "/cancelMannul", method = RequestMethod.POST)
	public AjaxResult cancelMannul(@RequestBody Map<String, Object> map) {
    	String result = prodTerminationRegistMService.cancelMannul(map);
    	if("fail".equals(result)){
			AjaxResult ajaxresult =  new AjaxResult("有未报送的数据",MessageType.错误消息);
			ajaxresult.setStatuCode(HttpStatus.OK.value());
			return ajaxresult;
		}
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	}
    
    /**   
    * @Function: ProdTerminationRegist001Controller.java
    * @Description: 产品终止登记数据检查
    *
    * @return：产品终止登记数据检查结果
    *
    * @author: 王冠/wangguan
    * @date: 2019年6月25日 上午10:26:10 
    */
    @ResponseBody
    @RequestMapping("reportCheck")
    public List<ExcelValidDto> reportCheck() {
    	return prodTerminationRegistMService.reportCheck(RequestUtil.getPageParam());
    }
}
