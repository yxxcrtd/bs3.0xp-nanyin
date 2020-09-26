package com.joyintech.tams.svreport.chb.prodraisetotalregist.controller;

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
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.chb.prodraisetotalregist.dto.RaiseTotalRegist001Dto;
import com.joyintech.tams.svreport.chb.prodraisetotalregist.service.RaiseTotalRegist001Service;
import com.joyintech.tams.svreport.chb.prodraisetotalregist.service.RaiseTotalRegistMService;

/**
 * 募集总量登记Controller
 * 〈功能详细描述〉
 * @author jiangxiao
 * @version 3.0 2019年01月21日
 * @see RaiseTotalRegist001Controller
 * @since 3.0
 */
@Controller
@PageId("SvrRaiseTotalRegist001")
@RequestMapping("/chb/prodraisetotalregist/RaiseTotalRegist001")
public class RaiseTotalRegist001Controller extends BaseController{
	@Autowired
	private RaiseTotalRegist001Service raiseTotalRegist001Service;
	@Autowired
	private RaiseTotalRegistMService raiseTotalRegistMService;
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
                DictCode.报送状态,DictCode.报送反馈状态,
                DictCode.是否_中债);
            model.addAttribute("indentifyMark",RequestUtil.getParam("indentifyMark"));
            return super.init(model);
    }
    
    /**
     * @Description 产品募集总量登记分页查询
     * @return Page<InvestorsHoldInfo001Dto>
     * @version 3.0 2019年1月22日
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RaiseTotalRegist001Dto> selectAllData() {
        Page<RaiseTotalRegist001Dto> page = raiseTotalRegistMService.selectForPage(RequestUtil.getPageParam());
        return page;
    }
    
    /**
    * 业务画面导出事件
    * @return
    * @since 1.0
    */
   @ResponseBody
   @RequestMapping("export")
   public ResponseEntity<byte[]> export() {
   	Map<String, Object> pageParam = RequestUtil.getPageParam();
   	if(pageParam.containsKey("keyList")){
         List<RaiseTotalRegist001Dto> dtoList = raiseTotalRegistMService.selectForExport(RequestUtil.getParameters(getRequest()));
         File outFile = raiseTotalRegist001Service.makeFile(
             "/com/joyintech/tams/svreport/chb/prodraisetotalregist/conf/export_conf.xml", dtoList);
         return ExportUtil.export(
             this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
                                  + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS
                                  + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
             outFile, true);
   	}
   	else{
        return raiseTotalRegist001Service.export(this.getPageId(), RequestUtil.getPageParam());
   	}

   }
   
   /**
    *手工处理功能
    * @return 提示信息
    * @param map 条件map
    */
   @ResponseBody
   @RequestMapping(value = "/manual" ,method = RequestMethod.POST)
   public AjaxResult doUpdate(@RequestBody Map<String,Object> map) {
	   raiseTotalRegistMService.updateSumStaus(map);
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
        String result = raiseTotalRegistMService.cancelMannul(map);
	   if("fail".equals(result)){
			AjaxResult ajaxresult =  new AjaxResult("有未报送的数据",MessageType.错误消息);
			ajaxresult.setStatuCode(HttpStatus.OK.value());
			return ajaxresult;
		}
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	}
    
    @ResponseBody
    @RequestMapping("reportCheck")
    public List<ExcelValidDto> reportCheck() {
        return raiseTotalRegistMService.reportCheck(RequestUtil.getPageParam());
    }
}
