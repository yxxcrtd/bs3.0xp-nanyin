/*
 * 文件名：ReportContentEnum002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.disclosure.information.config.reportcontentenum.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.disclosure.information.config.reportcontentenum.dto.ReportContentEnum001Dto;
import com.joyintech.service.disclosure.information.config.reportcontentenum.dto.ReportContentEnum003Dto;
import com.joyintech.service.disclosure.information.config.reportcontentenum.service.ReportContentEnum001Service;
import com.joyintech.service.disclosure.information.config.reportcontentenum.service.ReportContentEnum002Service;
import com.joyintech.service.disclosure.information.reveal.detailshow.service.RepDetailsShow001Service;
/**
 * 左侧栏新建
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年11月7日
 * @see ReportContentEnum002Controller
 * @since 1.0
 */
@RestController(value="serviceReportContentEnum002Controller")
@RequestMapping("/ReportContentEnum002")
public class ReportContentEnum002Controller  extends BaseController{
	
	@Autowired
	private ReportContentEnum001Service reportContentEnum001Service;
	
	@Autowired
    private RepDetailsShow001Service repDetailsShow001Service;
	@Autowired
    private ReportContentEnum002Service reportContentEnum002Service;
	
	
	
	
	
	@GetMapping("findById")
	public ReportContentEnum001Dto findById(String id){
		return reportContentEnum001Service.findById(id);
		
		
	}
	
	@PostMapping("getProdInfo")
	public List<SelectOptionDto> getProdInfo(){
		return repDetailsShow001Service.getProdInfo();
		
		
	}
	
	
	@GetMapping("findbyIndexId")
	public List<ReportContentEnum001Dto> findbyIndexId(String id){
		return reportContentEnum001Service.findbyIndexId(id);
		
		
	}
	
	@GetMapping("findbyIndexName")
	public List<ReportContentEnum001Dto> findbyIndexName(String name){
		return reportContentEnum001Service.findbyIndexName(name);
		
		
	}
	
//    /**
//     * 新建修改时check
//     * <一句话功能简述>
//     * <功能详细描述>
//     * @param dto
//     * @param bindingResult
//     * @return
//     * @throws Exception
//     */
//	@ResponseBody
//	@RequestMapping("check")
//	public AjaxResult check(@RequestBody @Valid ReportContentEnum001Dto  dto, BindingResult bindingResult)
//			throws Exception {
//		String msg = StringUtil.EMPTY;
//		String operateType = dto.getOperateType();
//
////		 唯一性验证,指标名称
//		if (OPERATE_TYPE_SUBMIT.equals(dto.getOperateType()) && !bindingResult.hasErrors()) {// submit事件 && 基础验证通过
//			//指标名称
//			String id = dto.getId();
//			List<ReportContentEnum001Dto> list = reportContentEnum001Service.findbyIndexId(id);
//			if (CollectionUtils.isNotEmpty(list)) {
//				msg = "指标代码已存在！请重新输入。";
//				return new AjaxResult(msg, MessageType.错误消息);
//			}
//			String name = dto.getIndexName();
//			List<ReportContentEnum001Dto> list1 = reportContentEnum001Service.findbyIndexName(name);
//			if (CollectionUtils.isNotEmpty(list1)) {
//				msg = "指标名称已存在！请重新输入。";
//				return new AjaxResult(msg, MessageType.错误消息);
//			}
//		}
//		if (OPERATE_TYPE_SAVE.equals(dto.getOperateType()) && !bindingResult.hasErrors()) {// submit事件 && 基础验证通过
//			String name = dto.getIndexName();
//			List<ReportContentEnum001Dto> list1 = reportContentEnum001Service.findbyIndexName(name);
//			if (CollectionUtils.isNotEmpty(list1)) {
//				for(ReportContentEnum001Dto searchDto:list1){
//					if(!StringUtil.equals(dto.getId(), searchDto.getId())){
//						msg = "指标名称已存在！请重新输入。";
//						return new AjaxResult(msg, MessageType.错误消息);
//					}
//				}
//			}
//		}
//		return commonCheck(bindingResult, operateType);
//	}
//	
	/**
	 * 左侧栏新建
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 * @param bindingResult
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping(value = PAGE_SAVE, method = RequestMethod.POST)
//	public AjaxResult save(@RequestBody @Valid ReportContentEnum001Dto dto, BindingResult bindingResult) {
//		String msg = StringUtil.EMPTY;
//		String operateType = dto.getOperateType();
//		if ("submit".equals(operateType)) {			
//			reportContentEnum001Service.insert(dto);
//			msg = "新建成功！";
//		} else if ("save".equals(operateType)) {	
//			reportContentEnum001Service.updateByPrimaryKeySelective(dto);
//			msg = "修改成功！";
//		}
//
//		return new AjaxResult(msg);
//	}
	@PostMapping("insert")
	public void insert(@RequestBody ReportContentEnum001Dto dto){
		reportContentEnum001Service.insert(dto);
	}
	
	
	@PostMapping("updateByPrimaryKeySelective")
	public void updateByPrimaryKeySelective(@RequestBody ReportContentEnum001Dto dto){
		reportContentEnum001Service.updateByPrimaryKeySelective(dto);
	}
	
	/**
     * 产品下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getFinprodId")
    @ResponseBody
    public List<SelectOptionDto> getFinprodId() {

        List<SelectOptionDto> listSelectDto = new ArrayList<SelectOptionDto>();
        SelectOptionDto dto;
        dto = new SelectOptionDto();dto.setId("001");dto.setText("产品001");listSelectDto.add(dto);
        dto = new SelectOptionDto();dto.setId("002");dto.setText("产品002");listSelectDto.add(dto);
        dto = new SelectOptionDto();dto.setId("003");dto.setText("产品003");listSelectDto.add(dto);
        dto = new SelectOptionDto();dto.setId("004");dto.setText("产品004");listSelectDto.add(dto);
        dto = new SelectOptionDto();dto.setId("005");dto.setText("产品005");listSelectDto.add(dto);
        dto = new SelectOptionDto();dto.setId("006");dto.setText("产品006");listSelectDto.add(dto);
        dto = new SelectOptionDto();dto.setId("007");dto.setText("产品007");listSelectDto.add(dto);
        dto = new SelectOptionDto();dto.setId("008");dto.setText("产品008");listSelectDto.add(dto);
        dto = new SelectOptionDto();dto.setId("009");dto.setText("产品009");listSelectDto.add(dto);
        dto = new SelectOptionDto();dto.setId("0010");dto.setText("产品010");listSelectDto.add(dto);
        dto = new SelectOptionDto();dto.setId("0011");dto.setText("产品011");listSelectDto.add(dto);
        dto = new SelectOptionDto();dto.setId("0012");dto.setText("产品012");listSelectDto.add(dto);
        return listSelectDto;

        // Map<String,Object> map=new HashMap<String,Object>();
        // map.put("finprodType",DictCodeListExt1.金融产品类型_理财产品.getValue());
        // return reportConfirmManage001Service.getFinprodId(map);
    }
    /**
	 * 根据作业编号查询不适用产品
	 * @return
	 */
	@GetMapping("findRulePage")
	public List<ReportContentEnum003Dto> findRulePage(String id) {
		if (StringUtil.isNotEmpty(this.getParam("id"))) {
			return reportContentEnum002Service.findRulePage(id);
		}
		return new ArrayList<ReportContentEnum003Dto>();
	}
}
