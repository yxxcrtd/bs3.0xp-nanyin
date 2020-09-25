/*
 * 文件名：ValProductInfoQuery001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.manage.workrule.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCode;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.common.common.service.BaseCommonService;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.information.config.reportpage.controller.ReportPageManage002Controller;
import com.joyintech.tams.disclosure.information.manage.workmanage.dto.ReportWorkManage001Dto;
import com.joyintech.tams.disclosure.information.manage.workrule.dto.ReportRule001Dto;
import com.joyintech.tams.disclosure.information.manage.workrule.dto.ReportRule002Dto;
import com.joyintech.tams.disclosure.information.manage.workrule.service.ReportRule001Service;
import com.joyintech.tams.disclosure.information.manage.workrule.service.ReportRule002Service;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
/**
 * 
 * 作业规则查询
 * @author 
 * @version 1.0 2018年5月16日
 * @see ValNetValueConfirm001Contoller
 * @since 1.0
 */
@Controller
@PageId("ReportRule002")
@RequestMapping("/disclosure/information/manage/workrule/ReportRule002")
public class ReportRule002Contoller extends BaseController {
    @Autowired
    private ReportRule002Service reportRule002Service; 
    @Autowired
    private BaseCommonService baseCommonService;
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ReportRule002Contoller.class);
    /**
     * 重写init方法，加载页面
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        
        this.setSelectOptionToModel(model,DictCode.披露文件类型);
        this.setSelectOptionToModel(model,DictCode.披露频率);
        this.setSelectOptionToModel(model, DictCode.基准日);
        this.setSelectOptionToModel(model, DictCode.日期类型_信批);
        this.setSelectOptionToModel(model, DictCode.报告数据类型);
        this.setSelectOptionToModel(model, DictCode.取数范围);
        this.setSelectOptionToModel(model, DictCode.星期号);
        this.setSelectOptionToModel(model, DictCode.时点类型);
        this.setSelectOptionToModel(model, DictCode.基准区分);
        this.setSelectOptionToModel(model, DictCode.确认类型);
        
        // 获取披露渠道
		model.addAttribute("channelList", JsonUtils.objectArray2Json(reportRule002Service.getRevealChannel().toArray()));
        model.addAttribute("REPORT_NAME",JsonUtils.objectArray2Json(this.getReportNameList().toArray()));
        model.addAttribute(ReportGlobalConstant.REPORT_DEFAULT_FILENAME, CacheUtil.getSysParam(ReportGlobalConstant.REPORT_DEFAULT_FILENAME));
        model.addAttribute(ReportGlobalConstant.REPORT_DOWNLOAD_PATH, CacheUtil.getSysParam(ReportGlobalConstant.REPORT_DOWNLOAD_PATH));
        
        // 确认方式、披露方式默认类型为自动
		String type= "01";
		model.addAttribute("type", type);
        // 入账日期默认为系统平台日
        model.addAttribute("sysDate",DateUtil.format(baseCommonService.getSysDate(), "yyyy-MM-dd"));
        model.addAttribute("time", "23:59");
        model.addAttribute("outType", "pdf");
        String ruleId = this.getParam("id");
        if (StringUtil.isNotEmpty(ruleId)) {
			model.addAttribute("entity", reportRule002Service.ReportRule002Dto(ruleId));
		}
        return super.init(model);
    }
    
    /**
     * 获取报表名称
     * @return
     */
    @RequestMapping("/getReportNameList")
    @ResponseBody
    private List<SelectOptionDto> getReportNameList() {
    	return reportRule002Service.getReportInfo();
		
	}
    /**
     * 新建
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @throws IOException
     */
    @RequestMapping("insert")
    @ResponseBody
    public AjaxResult insert(ReportRule002Dto dto) {
    	AjaxResult res = new AjaxResult();
    	List<ValidMessage>checkList = new ArrayList<ValidMessage>();
//		StringBuilder revealChannel = new StringBuilder();
//    	if(StringUtil.equals(dto.getRevealChannelW(), "on")){
//			revealChannel.append(","+DisclosureDictCodeList.披露渠道_官网.getValue());
//		}
//		if(StringUtil.equals(dto.getRevealChannelO(), "on")){
//			revealChannel.append(","+DisclosureDictCodeList.披露渠道_点对点.getValue());
//		}
//		if(StringUtil.equals(dto.getRevealChannelC(), "on")){
//			revealChannel.append(","+DisclosureDictCodeList.披露渠道_其他.getValue());
//		}
//			dto.setRcfValue("");
//			dto.setMakeTime("01");
//			dto.setCreateDept("");
		if(StringUtil.isEmpty(dto.getDetail())){
			dto.setDetail("");
		}
//		if(StringUtil.isEmpty(revealChannel)){
//			dto.setRevealChannel("");
//		}
//		else{
//			dto.setRevealChannel(revealChannel.substring(1, revealChannel.length()).toString());
//		}
    	if (this.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
			dto.setPageModel(this.getPageModel());
			checkList = reportRule002Service.insert(dto);
		
		} else if (this.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
			dto.setPageModel(this.getPageModel());
			checkList = reportRule002Service.update(dto);
		}
    	if(StringUtil.isNotEmpty(checkList)) {
        	res.setErrorList(checkList);
        }
    	else {
        	res = new AjaxResult("保存成功", MessageType.提示消息);
        }
    	return res;
    	
    }
    /**
	 * 删除选中数据
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete")
	public AjaxResult delete(@RequestBody ReportRule001Dto dto) {
		AjaxResult result = new AjaxResult("删除成功", MessageType.提示消息);
		try {
			List<ReportRule001Dto> listDto = dto.getSelectedItem();
			String erroMes = null;
			for(ReportRule001Dto temp:listDto){
				int i = reportRule002Service.selectById(temp.getId());
				if (i == 0) {
					erroMes = erroMes + "报表规则不存在:"+temp.getId()+"。";
				}
				else{
					reportRule002Service.delete(temp.getId());
				}
			}
			if(StringUtil.isNotEmpty(erroMes)){
				result = new AjaxResult(erroMes, MessageType.错误消息);
			}
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error("error:",e);
			result = new AjaxResult("删除失败", MessageType.错误消息);
		}
		return result;
	}
	
}
