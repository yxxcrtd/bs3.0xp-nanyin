/*
 * 文件名：ValProductInfoQuery001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.disclosure.information.manage.workmanage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.disclosure.information.manage.workmanage.dto.ReportWorkManage002Dto;
import com.joyintech.service.disclosure.information.manage.workmanage.dto.ReportWorkManage003Dto;
import com.joyintech.service.disclosure.information.manage.workmanage.dto.ReportWorkManage004Dto;
import com.joyintech.service.disclosure.information.manage.workmanage.service.ReportWorkManage002Service;
import com.joyintech.service.disclosure.information.reveal.detailshow.service.RepDetailsShow001Service;
import com.joyintech.tams.disclosure.common.common.service.BaseCommonService;

/**
 * 
 * 金融产品交易查询
 * 
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValNetValueConfirm001Contoller
 * @since 1.0
 */
@RestController(value="serviceReportWorkManage002Contoller")
@RequestMapping("/ReportWorkManage002")
public class ReportWorkManage002Contoller extends BaseController {

	/**
	 * ValProductInfoQuery001Service
	 */
	@Autowired
	private ReportWorkManage002Service reportWorkManage002Service;

	@Autowired
	private RepDetailsShow001Service repDetailsShow001Service;

	@Autowired
	private BaseCommonService baseCommonService;
	
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ReportWorkManage002Contoller.class);
	/**
	 * 重写init方法，加载页面 {@inheritDoc}
	 */

	/**
	 * 获取产品信息
	 * 
	 * @return 返回的数据
	 * @exception Exception
	 *                异常
	 * @since 1.0
	 */
	@GetMapping("getReportWorkManage002Dto")
	public ReportWorkManage002Dto getReportWorkManage002Dto(String JobId) {

		return reportWorkManage002Service.getReportWorkManage002Dto(JobId);
	}
	/**
	    * 
	    * check
	    * @param dto 待校验数据
	    * @return 结果
	    * @see
	    * @since 1.0
	    */
//	  @ResponseBody
//	  @RequestMapping("/checkSave")
//	  public AjaxResult checkSave(@RequestBody ReportWorkManage002Dto dto){
//	      AjaxResult result = reportWorkManage002Service.check(dto);
//	      if(result.getErrorList().isEmpty()){
//	          result.setData(this.getMessage("REPORT_COMMON_C_0001", this.getMessage("SysCommon.save")));
//	      }
//	      return  result;
//	  }
	/**
	 * 新建 <一句话功能简述> <功能详细描述>
	 * 
	 * @return
	 * @throws IOException
	 */
	
	
	
	@PostMapping("selectJobByIdAndName")
	public int selectJobByIdAndName(@RequestBody ReportWorkManage002Dto dto){
		return reportWorkManage002Service.selectJobByIdAndName(dto);
		
	}
	
	@PostMapping("insert")
	public int insert(@RequestBody ReportWorkManage002Dto dto){
		return reportWorkManage002Service.insert(dto);
		
	}
	
	@PostMapping("update")
	public int update(@RequestBody ReportWorkManage002Dto dto){
		return reportWorkManage002Service.update(dto);
		
	}
//	@RequestMapping("insert")
//	@ResponseBody
//	public AjaxResult insert() throws IOException {
//		AjaxResult result = new AjaxResult("保存成功", MessageType.提示消息);
//		String datas = this.getParam("object");
//		String chooseProdTable = this.getParam("chooseProdTable");
//		String settedRule = this.getParam("settedRule");
//
//		ReportWorkManage002Dto dto = JsonUtils.json2Object(datas, ReportWorkManage002Dto.class);
//		List<ReportWorkManage003Dto> prodList = JsonUtils.json2List(chooseProdTable, ReportWorkManage003Dto.class);
//		List<ReportWorkManage004Dto> settedRuleList = JsonUtils.json2List(settedRule, ReportWorkManage004Dto.class);
//		try {
//			int i = 0;
//			dto.setChooseProdTable(prodList);
//			dto.setSettedRule(settedRuleList);
//			dto.setLoopDateType("01");
//			if (this.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
//				//查询是否已经存在该作业编号和名称
//				int j=reportWorkManage002Service.selectJobByIdAndName(dto);
//				if(j>0){
//					result = new AjaxResult(
//							this.getMessage("REPORT_E_0019", this.getMessage("ReportWorkManage002.jobId"),
//									this.getMessage("ReportWorkManage002.jobName")),
//							MessageType.错误消息);
//				}
//				else{
//					i = reportWorkManage002Service.insert(dto);
//					if (i == 0) {
//						result = new AjaxResult("作业编号已存在", MessageType.错误消息);
//					}
//				}
//			} else if (this.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
//				i = reportWorkManage002Service.update(dto);
//			}
//		} catch (Exception e) {
////			e.printStackTrace();
//			logger.error("error:",e);
//			result = new AjaxResult("保存失败", MessageType.错误消息);
//		}
//		return result;
//	}
//
//	@RequestMapping("/update")
//	@ResponseBody
//	public AjaxResult update() {
//		AjaxResult result = new AjaxResult("修改成功", MessageType.提示消息);
//		ReportWorkManage002Dto dto = JsonUtils.json2Object(this.getParam("data"), ReportWorkManage002Dto.class);
//		try {
//			int i = reportWorkManage002Service.update(dto);
//			if (i == 0) {
//				result = new AjaxResult("作业编号不存在", MessageType.错误消息);
//			}
//		} catch (Exception e) {
////			e.printStackTrace();
//			logger.error("error:",e);
//			result = new AjaxResult("修改失败", MessageType.错误消息);
//		}
//		return result;
//	}
	/**
	 * 删除选中数据
	 * @param dto
	 * @return
	 */
	@GetMapping("delete")
	public int delete(String jobId){
		return reportWorkManage002Service.delete(jobId);
	}
//	@ResponseBody
//	@RequestMapping(value = "/delete", method = RequestMethod.POST)
//	public AjaxResult delete(@RequestBody ReportWorkManage001Dto dto) {
//		AjaxResult result = new AjaxResult("删除成功", MessageType.提示消息);
//		try {
//			List<ReportWorkManage001Dto> listDto = dto.getSelectedItem();
//			if(listDto.size() == 1){
//				int i = reportWorkManage002Service.delete(listDto.get(0).getJobId());
//				if (i == 0) {
//					result = new AjaxResult("作业编号不存在:"+dto.getJobId(), MessageType.错误消息);
//				}
//			}
//			else{
//				String erroMes = null;
//				for(ReportWorkManage001Dto temp:listDto){
//					int i = reportWorkManage002Service.delete(temp.getJobId());
//					if (i == 0) {
//						erroMes = erroMes + "作业编号不存在:"+temp.getJobId()+"。";
//					}
//				}
//				if(StringUtil.isNotEmpty(erroMes)){
//					result = new AjaxResult(erroMes, MessageType.错误消息);
//				}
//			}
//			
//		} catch (Exception e) {
////			e.printStackTrace();
//			logger.error("error:",e);
//			result = new AjaxResult("删除失败", MessageType.错误消息);
//		}
//		return result;
//	}

	@PostMapping("/getReportIdList")
	public List<SelectOptionDto> getReportIdList() {
		return repDetailsShow001Service.getProdInfo();
	}

	/**
	 * 获取规则模板下拉菜单数据
	 * 
	 * @return
	 */
	@PostMapping("getRepRule")
	public List<SelectOptionDto> getRepRule() {
		return reportWorkManage002Service.getRepRule();
	}

	/**
	 * 根据作业编号查询不适用产品
	 * 
	 * @return
	 */
	@GetMapping("findProdPage")
	public List<ReportWorkManage003Dto> findProdPage(String jobId) {
		if (StringUtil.isNotEmpty(jobId)) {
			return reportWorkManage002Service.findProdPage(jobId);
		}
		return new ArrayList<ReportWorkManage003Dto>();
	}

	/**
	 * 根据作业编号查询不适用产品
	 * @return
	 */
	@GetMapping("findRulePage")
	public List<ReportWorkManage004Dto> findRulePage(String jobId) {
		if (StringUtil.isNotEmpty(jobId)) {
			return reportWorkManage002Service.findRulePage(jobId);
		}
		return new ArrayList<ReportWorkManage004Dto>();
	}
}
