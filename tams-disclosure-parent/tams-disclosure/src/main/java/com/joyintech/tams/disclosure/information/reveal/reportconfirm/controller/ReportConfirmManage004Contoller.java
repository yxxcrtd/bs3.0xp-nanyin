package com.joyintech.tams.disclosure.information.reveal.reportconfirm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.*;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.ReportConfirmManage004Dto;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.ReportConfirmManageCommentDto;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.UploadFileDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.ftppath.service.FtpPathService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.service.GetPrdInfoSelectService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.reportDeal.service.ReportDealService;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCode;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.service.ReportConfirmManage004Service;

@Controller
@PageId("ReportConfirmManage004")
@RequestMapping("/disclosure/information/reveal/reportconfirm/ReportConfirmManage004")
public class ReportConfirmManage004Contoller extends BaseController {

	@Autowired
	ReportConfirmManage004Service reportConfirmManage004Service;
	
	@Autowired
    ReportDealService reportDealService;
	
	@Autowired
    private FtpPathService ftpPathService;
	
	@Autowired
	private GetPrdInfoSelectService getPrdInfoSelectService;
	
	@Override
	public String init(Model model) {
		String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
		String seqno = getParam("seqno");
		// 获取披露渠道
		model.addAttribute("channelList", JsonUtils.objectArray2Json(reportConfirmManage004Service.getRevealChannel().toArray()));
		// 获取报表类别
		model.addAttribute("REPORT_STYLE",JsonUtils.objectArray2Json(ftpPathService.getReportCategory().toArray()));
		ReportConfirmManage004Dto dto = null;
		Map<String,String> paramMap = new HashMap<String,String>();
		if(StringUtil.isNotEmpty(seqno)){
			paramMap.put("seqno",seqno);
			dto = reportConfirmManage004Service.queryInfo(paramMap);
			model.addAttribute("startDate",dto.getStartDateStr());
			model.addAttribute("endDate", dto.getEndDateStr());
			model.addAttribute("confirmType", dto.getIsConfirm());
			model.addAttribute("revealType", dto.getIsReveal());
			model.addAttribute("revealChannel", dto.getRevealChannel());
		} else if(StringUtil.isNotEmpty(workflowId)){
			paramMap.put("workflowId",workflowId);
			dto = reportConfirmManage004Service.queryInfo(paramMap);
			model.addAttribute("startDate",dto.getStartDateStr());
			model.addAttribute("endDate", dto.getEndDateStr());
			model.addAttribute("confirmType", dto.getIsConfirm());
			model.addAttribute("revealType", dto.getIsReveal());
			model.addAttribute("revealChannel", dto.getRevealChannel());
		}else{
			// 确认方式、披露方式默认类型为自动
			String type= "02";
			model.addAttribute("type", type);
			model.addAttribute("confirmType", DictCodeListExt1.确认类型_手动.getValue());
			model.addAttribute("revealType", DictCodeListExt2.披露方式_自动.getValue());
			model.addAttribute("revealChannel", DictCodeListExt2.披露渠道_官网.getValue());
		}
		if(dto != null){
			model.addAttribute("seqno", dto.getId());
			if(StringUtil.isNotEmpty(dto.getStartDateStr()) && StringUtil.isNotEmpty(dto.getEndDateStr())){
				String reportDate = dto.getStartDateStr() + " - " +dto.getEndDateStr();
				model.addAttribute("reportDate",reportDate);
			}else if(StringUtil.isNotEmpty(dto.getStartDateStr()) && StringUtil.isEmpty(dto.getEndDateStr())){
				String reportDate = dto.getStartDateStr() + " - " +dto.getStartDateStr();
				model.addAttribute("reportDate",reportDate);
			}else if(StringUtil.isEmpty(dto.getStartDateStr()) && StringUtil.isNotEmpty(dto.getEndDateStr())){
				String reportDate = dto.getEndDateStr() + " - " +dto.getEndDateStr();
				model.addAttribute("reportDate",reportDate);
			}
		}
		model.addAttribute("entity", dto);
		String viewFlag = getParam("viewFlag");
		model.addAttribute("viewFlag",viewFlag);
		this.setSelectOptionToModel(model, DictCode.确认类型, DictCode.披露方式);
		return super.init(model);
	}
	
	/**
	 * 分页查询产品下拉框
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getPrdInfoSelect")
	@ResponseBody
    public Page<SelectOptionDto> getPrdInfoSelect(){
		Page<SelectOptionDto> prdInfoSelect = getPrdInfoSelectService.getPrdInfoSelect(RequestUtil.getPageParam());
        return prdInfoSelect;
    }
	
	/**
     * 
     * 手动上传报表
     * @return AjaxResult value
     * @see
     * @since 1.0
     */
	@RequestMapping(value = "/uploadAndSubmit",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult uploadAndSubmit(){
        AjaxResult res = new AjaxResult();
        Map<String,Object> param = RequestUtil.getPageParam();
        RepReveal repReveal = new RepReveal();
        repReveal.setReportName(param.get("reportName").toString());
    	repReveal.setProdid(param.get("prodId").toString());
    	if(param.get("reportDateBegin") != null) {
    		repReveal.setStartDate(DateUtil.parse(param.get("reportDateBegin").toString()));
    	}
    	if(param.get("reportDateEnd") != null) {
    		repReveal.setEndDate(DateUtil.parse(param.get("reportDateEnd").toString()));
    	}
        String operateType = param.get("operateType").toString();
        List<ValidMessage> checkList = new ArrayList<ValidMessage>();
        //报表人工上传时，有些报表并没有产品名称，要求放开产品名称验证
//        if(!StringUtil.isNotEmpty(repReveal.getProdid())) {
//        	checkList.add(new ValidMessage("产品名称不能为空"));
//        }
        if(!StringUtil.isNotEmpty(repReveal.getReportName())) {
        	checkList.add(new ValidMessage("报表名称不能为空"));
        }
        if(StringUtil.isNotEmpty(checkList) && checkList.size() > 0) {
        	res.setErrorList(checkList);
        	return res;
        }
        boolean confirmFlag = true;
        // 上传报表后提示反馈信息
        checkList.add(new ValidMessage("上传报表失败"));
        if("01".equals(operateType)) {
        	checkList.clear();
        	confirmFlag = reportDealService.selectConfirmStatus(repReveal);
        	if(confirmFlag == true) {
        		reportDealService.updateDataStatus(repReveal);
        		checkList = reportConfirmManage004Service.insertRepReveal(param);
        	}else {
        		checkList.add(new ValidMessage("存在相同且已确认的数据"));
        	}
        }
        if(StringUtil.isNotEmpty(checkList)) {
        	res.setErrorList(checkList);
        }
        else {
        	res = new AjaxResult("上传报表成功", MessageType.提示消息);
        }
        return res;
	}
	/**
	 * 文件上传
	 *
	 * @param request请求
	 * @param response响应
	 * @param model ModelAndView
	 * @return 保存文件信息
	 * @throws Exception
	 *             异常
	 * @see
	 * @since 1.0
	 */
	@RequestMapping(value = "/uploadFile")
	@ResponseBody
	public AjaxResult uploadFile(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		 AjaxResult res = new AjaxResult();
//		 if(request.getParameter("size")>1048576){}
		 String size = this.getParam("size");
		 //文件大小判断 10M
		 if(Integer.parseInt(size)>10485760){
			 //进度条消除
			 reportConfirmManage004Service.removePercent(request);
			 String msg = "文件大小不能超过10M";
				res =  new AjaxResult(msg, MessageType.错误消息);
				return res;
		 }else{
		 try {
			 reportConfirmManage004Service.readFile(request);
			} catch (Exception e) {
				String msg = "上传失败：" + e.getMessage();
				res =  new AjaxResult(msg, MessageType.错误消息);
				return res;
			}}
			return res;
	}
	
	/**
     * 
     * 获取查询条数
     * <功能详细描述>
     * @param repId
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/querySameCount")
    public AjaxResult querySameCount() {
    	@SuppressWarnings("unused")
		String msg = StringUtil.EMPTY;
    	Map<String, Object>params = RequestUtil.getPageParam();
    	int count = reportConfirmManage004Service.querySameCount(params);
    	if(count > 0){
    		return new AjaxResult("存在相同的报表，是否继续生成？",MessageType.确认消息);
    	}
    	else {
    		return null;
    	}
    }



	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	AjaxResult save(@RequestBody ReportConfirmManage004Dto dto){
		ProcessParameter<ReportConfirmManage004Dto> processParameter = new ProcessParameter<>();
		WorkFlowParameter<ReportConfirmManage004Dto> params = new WorkFlowParameter<>();
		processParameter.setWorkFlowParameter(params);
		processParameter.setPageId(this.getPageId());
		if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
			processParameter.setOperationType(OperationType.NEW);
		} else {
			processParameter.setOperationType(OperationType.MODIFY);
		}
		//如果是从新建页面保存,则设置seqno
		if(StringUtils.isEmpty(dto.getId())){
			dto.setId(IDGenerator.generate());
		}
		params.setPageId(this.getPageId());
		params.setElementId(dto.getElementId());
		params.setBusinessCode(dto.getId());
		params.setComment((entity) -> dto.getProdid()+ "(" + "报表名称" + dto.getReportName() + ")");
		processParameter.setWorkFlowParameter(params);
		String msg = reportConfirmManage004Service.start(processParameter,dto);
		return new AjaxResult(msg);
	}


	@ResponseBody
	@RequestMapping(value = "/getCommentList", method = RequestMethod.POST)
	List<Map<String,Object>> getCommentList(){
		String seqno = this.getParam("seqno");
		List<Map<String,Object>> commentList = new ArrayList<Map<String, Object>>();
		//获取审批意见记录
		List<ReportConfirmManageCommentDto> comments = reportConfirmManage004Service.getInfo(seqno);
		for(ReportConfirmManageCommentDto commentDto:comments){
			Map<String,String> param = new HashMap<String,String>();
			param.put("businessId",seqno);
			param.put("seqnoComment",commentDto.getSeqno());
			param.put("fileType","03");
			List<UploadFileDto> fileList = reportConfirmManage004Service.getFileByBusinessCode(param);
			Map<String,Object> commentMap = new HashMap<String,Object>();
			commentMap.put("comment",commentDto.getCommentRemark());
			commentMap.put("fileList",fileList);
			commentList.add(commentMap);
		}
		return commentList;
	}
}
