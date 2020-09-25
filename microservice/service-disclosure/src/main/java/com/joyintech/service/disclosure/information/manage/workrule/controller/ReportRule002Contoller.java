/*
 * 文件名：ValProductInfoQuery001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.disclosure.information.manage.workrule.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.disclosure.information.manage.workrule.dto.ReportRule002Dto;
import com.joyintech.service.disclosure.information.manage.workrule.service.ReportRule002Service;
import com.joyintech.tams.disclosure.common.common.service.BaseCommonService;
/**
 * 
 * 作业规则查询
 * @author 
 * @version 1.0 2018年5月16日
 * @see ValNetValueConfirm001Contoller
 * @since 1.0
 */
@RestController(value="serviceReportRule002Contoller")
@RequestMapping("/ReportRule002")
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
     * 获取报表名称
     * @return
     */
    @RequestMapping("/getReportNameList")
    @ResponseBody
    private List<SelectOptionDto> getReportNameList() {
    	return reportRule002Service.getReportInfo();
		
	}
    
    @GetMapping("ReportRule002Dto")
    public ReportRule002Dto ReportRule002Dto(String ruleId){
    	return reportRule002Service.ReportRule002Dto(ruleId);
    }
    
    
    @PostMapping("/insert")
   public List<ValidMessage> insert(@RequestBody ReportRule002Dto dto){
    	return reportRule002Service.insert(dto);
    }
    
    @PostMapping("/update")
    public List<ValidMessage> update(@RequestBody ReportRule002Dto dto){
    	return reportRule002Service.update(dto);
    }
    
    
    
    
//    /**
//     * 新建
//     * <一句话功能简述>
//     * <功能详细描述>
//     * @return
//     * @throws IOException
//     */
//    @RequestMapping("insert")
//    @ResponseBody
//    public AjaxResult insert(ReportRule002Dto dto) {
//    	AjaxResult res = new AjaxResult();
//    	List<ValidMessage>checkList = new ArrayList<ValidMessage>();
////		StringBuilder revealChannel = new StringBuilder();
////    	if(StringUtil.equals(dto.getRevealChannelW(), "on")){
////			revealChannel.append(","+DisclosureDictCodeList.披露渠道_官网.getValue());
////		}
////		if(StringUtil.equals(dto.getRevealChannelO(), "on")){
////			revealChannel.append(","+DisclosureDictCodeList.披露渠道_点对点.getValue());
////		}
////		if(StringUtil.equals(dto.getRevealChannelC(), "on")){
////			revealChannel.append(","+DisclosureDictCodeList.披露渠道_其他.getValue());
////		}
////			dto.setRcfValue("");
////			dto.setMakeTime("01");
////			dto.setCreateDept("");
//		if(StringUtil.isEmpty(dto.getDetail())){
//			dto.setDetail("");
//		}
////		if(StringUtil.isEmpty(revealChannel)){
////			dto.setRevealChannel("");
////		}
////		else{
////			dto.setRevealChannel(revealChannel.substring(1, revealChannel.length()).toString());
////		}
//    	if (this.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
//			dto.setPageModel(this.getPageModel());
//			checkList = reportRule002Service.insert(dto);
//		
//		} else if (this.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
//			dto.setPageModel(this.getPageModel());
//			checkList = reportRule002Service.update(dto);
//		}
//    	if(StringUtil.isNotEmpty(checkList)) {
//        	res.setErrorList(checkList);
//        }
//    	else {
//        	res = new AjaxResult("保存成功", MessageType.提示消息);
//        }
//    	return res;
//    	
//    }
    /**
	 * 删除选中数据
	 * @param dto
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping(value = "/delete")
//	public AjaxResult delete(@RequestBody ReportRule001Dto dto) {
//		AjaxResult result = new AjaxResult("删除成功", MessageType.提示消息);
//		try {
//			List<ReportRule001Dto> listDto = dto.getSelectedItem();
//			String erroMes = null;
//			for(ReportRule001Dto temp:listDto){
//				int i = reportRule002Service.(temp.getId());
//				if (i == 0) {
//					erroMes = erroMes + "报表规则不存在:"+temp.getId()+"。";
//				}
//				else{
//					reportRule002Service.delete(temp.getId());
//				}
//			}
//			if(StringUtil.isNotEmpty(erroMes)){
//				result = new AjaxResult(erroMes, MessageType.错误消息);
//			}
//		} catch (Exception e) {
////			e.printStackTrace();
//			logger.error("error:",e);
//			result = new AjaxResult("删除失败", MessageType.错误消息);
//		}
//		return result;
//	}
    @GetMapping("selectById")
    public int selectById(String id){
    	return reportRule002Service.selectById(id);
    }
    
    @GetMapping("delete")
    public int delete(String id){
    	return reportRule002Service.delete(id);
    }
}
