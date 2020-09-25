package com.joyintech.tams.disclosure.information.reveal.reportconfirm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
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
		// 获取披露渠道
		model.addAttribute("channelList", JsonUtils.objectArray2Json(reportConfirmManage004Service.getRevealChannel().toArray()));
		// 获取报表类别
		model.addAttribute("REPORT_STYLE",JsonUtils.objectArray2Json(ftpPathService.getReportCategory().toArray()));
		// 确认方式、披露方式默认类型为自动
		String type= "02";
		model.addAttribute("type", type);
		model.addAttribute("confirmType", DictCodeListExt1.确认类型_手动.getValue());
		model.addAttribute("revealType", DictCodeListExt2.披露方式_自动.getValue());
		model.addAttribute("revealChannel", DictCodeListExt2.披露渠道_官网.getValue());
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
    public Page<SelectOptionDto> getPrdInfoSelect()
        throws Exception {
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
	public AjaxResult uploadFile(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
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
    public AjaxResult querySameCount() throws Exception{
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
}
