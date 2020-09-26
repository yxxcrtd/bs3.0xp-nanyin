/*
 * 文件名：ValuationMethodScheme001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年8月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.valuationparametermanage.valuationmethodscheme.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.valuationparametermanage.valuationmethodscheme.dto.ValuationMethodScheme001Dto;
import com.joyintech.tams.valuation.valuationparametermanage.valuationmethodscheme.dto.ValuationMethodScheme002Dto;
import com.joyintech.tams.valuation.valuationparametermanage.valuationmethodscheme.dto.ValuationMethodScheme003Dto;
import com.joyintech.tams.valuation.valuationparametermanage.valuationmethodscheme.dto.ValuationMethodScheme004Dto;
import com.joyintech.tams.valuation.valuationparametermanage.valuationmethodscheme.service.ValuationMethodScheme001Service;
import com.joyintech.tams.valuation.valuationparametermanage.valuationmethodscheme.service.ValuationMethodScheme002Service;
import com.joyintech.tams.valuation.valuationparametermanage.valuationmethodscheme.service.ValuationMethodScheme003Service;
import com.joyintech.tams.valuation.valuationparametermanage.valuationmethodscheme.service.ValuationMethodScheme004Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 估值方法方案-主界面
 * 
 * @author zhangwei
 * @version 1.0 2018年8月27日
 * @see ValuationMethodScheme001Controller
 * @since 1.0
 */
@Controller
@PageId("ValuationMethodScheme001")
@RequestMapping("/valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme001")
public class ValuationMethodScheme001Controller extends BaseController {

	@Autowired
	private ValuationMethodScheme001Service valuationMethodScheme001Service;

	@Autowired
	private ValuationMethodScheme002Service valuationMethodScheme002Service;

	@Autowired
	private ValuationMethodScheme003Service valuationMethodScheme003Service;

	@Autowired
	private ValuationMethodScheme004Service valuationMethodScheme004Service;

	/**
	 * 估值方法方案 - 页面初始化 {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
		super.setSelectOptionToModel(model, DictCode.估值交易市场);// 交易市场
		super.setSelectOptionToModel(model, DictCode.投资目的);
		super.setSelectOptionToModel(model, DictCode.证券品种);
		super.setSelectOptionToModel(model, DictCode.债券类型);// 债券细分类（需求要求）
		return super.init(model);
	}

	/**
	 * 获取估值方案 - 分页
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("findScheme")
	public Page<ValuationMethodScheme001Dto> findScheme() throws Exception {
		Page<ValuationMethodScheme001Dto> page = valuationMethodScheme001Service
				.findSchemeByPage(RequestUtil.getPageParam());
		return page;
	}

	/**
	 * 获取估值方案详情 - 分页
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("findSchemeDetail")
	public Page<ValuationMethodScheme001Dto> findSchemeDetail() throws Exception {
		Page<ValuationMethodScheme001Dto> page = valuationMethodScheme001Service
				.findSchemeDetailByPage(RequestUtil.getPageParam());
		return page;
	}

	/**
	 * 获取估值方案详情 - 分页：特殊资产估值
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("findSpecialScheme")
	public Page<ValuationMethodScheme001Dto> findSpecialScheme() throws Exception {
		Page<ValuationMethodScheme001Dto> page = valuationMethodScheme001Service
				.findSpecialAssetsByPage(RequestUtil.getPageParam());
		return page;
	}
	
	/**
	 * 
	 * 通用删除验证：根据主键判断
	 * 
	 * @param dto
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody ValuationMethodScheme001Dto dto, BindingResult result) throws Exception {
		String msg = StringUtils.EMPTY;
		Map<String, Object> params = Maps.newHashMap();
		params.put("schemeId", dto.getSchemeId());
		if ("deleteScheme".equals(dto.getOperateType())) {// 删除估值方案验证
			ValuationMethodScheme002Dto entity = valuationMethodScheme002Service.findBySchemeId(dto.getSchemeId());
			if (DictCodeList.是否_是.getValue().equals(entity.getIsdefault())) {
				msg = "默认方案无法删除！";
				return new AjaxResult(msg, MessageType.错误消息);
			}

			int count = valuationMethodScheme002Service.countBokBookSetPlan(dto.getSchemeId());
			if (count > 0 || DictCodeList.是否_是.getValue().equals(dto.getIsdefault())) {
				msg = "当前方案已被使用，不能删除！";
				return new AjaxResult(msg, MessageType.错误消息);
			}
		}else {// 删除估值方案的相关配置验证（一般资产、特殊资产）
	        List<ValuationMethodScheme001Dto> list = dto.getListValuationMethodSchemeDto();
	        for(ValuationMethodScheme001Dto item:list){
            ValuationMethodScheme002Dto  valuationMethodScheme002Dto = valuationMethodScheme002Service.findBySchemeId(item.getSchemeId());
            if(DictCodeList.是否_是.getValue().equals(valuationMethodScheme002Dto.getIsdefault())) {
                return new AjaxResult("默认估值方案里面的内容，均不可删除", MessageType.错误消息);
            }
            }
		}
		return new AjaxResult();
	}

	/**
     * 删除方案 并启动流程
     * 
     * @param dto 业务实体
     * @return 画面地址
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "deleteScheme", method = RequestMethod.POST)
    public AjaxResult deleteScheme(@RequestBody @Validated({ValidateGroupDelete.class}) ValuationMethodScheme002Dto dto, BindingResult br) {
      String msg = StringUtils.EMPTY;
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        
        ProcessParameter<ValuationMethodScheme002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<ValuationMethodScheme002Dto> workFlowParameter = new WorkFlowParameter<>();
        
        try {
            workFlowParameter.setPageId("ValuationMethodScheme002"); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(dto.getSchemeId());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeList.共通业务类型_估值方法方案.getValue());// 业务类型
            workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment(
                    entity.getSchemeId(), "估值方法方案配置"));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数
            
            dto = valuationMethodScheme002Service.findBySchemeId(dto.getSchemeId());// dto 为回显值
            valuationMethodScheme002Service.start(processParameter, dto);
            msg = getMessage("COMMON_I_0001"); // 处理正常结束
        } catch (Exception e) {
            this.getLogger().error("启动估值方案流程失败：", e);
            msg = e.getMessage();
        }
        return new AjaxResult(msg);
    }

    /**
     * 删除一般资产 并启动流程
     * 
     * @param dto 业务实体
     * @return 画面地址
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "deleteTypeAssets", method = RequestMethod.POST)
    public AjaxResult deleteTypeAssets(@RequestBody @Validated({ValidateGroupDelete.class}) ValuationMethodScheme003Dto dto, BindingResult br) {
      String msg = StringUtils.EMPTY;
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        
        ProcessParameter<ValuationMethodScheme003Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<ValuationMethodScheme003Dto> workFlowParameter = new WorkFlowParameter<>();
        
        try {
            workFlowParameter.setPageId("ValuationMethodScheme003"); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(item -> item.getSchemeDetailId());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeList.共通业务类型_估值方法方案.getValue());// 业务类型
            workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment(
                    entity.getSchemeId(), "一般资产配置"));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数
            
            // GZ-061添加流程： 批量删除 主键长度限制，且批量流程无法全部回显，故取消批量删除功能
//            dto = valuationMethodScheme003Service.findBySchemeDetailId(dto.getSchemeDetailId());// dto 为回显值,批量删除回显第一个
            
	        List<ValuationMethodScheme003Dto> list = dto.getListValuationMethodSchemeDto();
	        List<ValuationMethodScheme003Dto> showList = new ArrayList<>();
	        for(ValuationMethodScheme003Dto item:list){
	        	item = valuationMethodScheme003Service.findBySchemeDetailId(item.getSchemeDetailId());
	        	showList.add(item);
            }
            valuationMethodScheme003Service.start(processParameter, showList.toArray(new ValuationMethodScheme003Dto[showList.size()]));
            msg = getMessage("COMMON_I_0001"); // 处理正常结束
        } catch (Exception e) {
            this.getLogger().error("启动估值方案流程失败：", e);
            msg = e.getMessage();
        }
        return new AjaxResult(msg);
    }

    /**
     * 删除特殊资产 并启动流程
     * 
     * @param dto 业务实体
     * @return 画面地址
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "deleteSpecialAssets", method = RequestMethod.POST)
    public AjaxResult deleteSpecialAssets(@RequestBody @Validated({ValidateGroupDelete.class}) ValuationMethodScheme004Dto dto, BindingResult br) {
      String msg = StringUtils.EMPTY;
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        
        ProcessParameter<ValuationMethodScheme004Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<ValuationMethodScheme004Dto> workFlowParameter = new WorkFlowParameter<>();
        
        try {
            workFlowParameter.setPageId("ValuationMethodScheme004"); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(item -> item.getSchemeDetailId());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeList.共通业务类型_估值方法方案.getValue());// 业务类型
            workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment(
                    entity.getSchemeId(), "特殊资产配置"));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数
            
            // GZ-061添加流程： 批量删除 主键长度限制，且批量流程无法全部回显，故取消批量删除功能
//            dto = valuationMethodScheme004Service.findBySchemeDetailId(dto.getSchemeDetailId());// dto 为回显值,批量删除回显第一个
            
	        List<ValuationMethodScheme004Dto> list = dto.getListValuationMethodSchemeDto();
	        List<ValuationMethodScheme004Dto> showList = new ArrayList<>();
	        for(ValuationMethodScheme004Dto item:list){
	        	item = valuationMethodScheme004Service.findBySchemeDetailId(item.getSchemeDetailId());
	        	showList.add(item);
            }
            valuationMethodScheme004Service.start(processParameter, showList.toArray(new ValuationMethodScheme004Dto[showList.size()]));
            msg = getMessage("COMMON_I_0001"); // 处理正常结束
        } catch (Exception e) {
            this.getLogger().error("启动估值方案流程失败：", e);
            msg = e.getMessage();
        }
        return new AjaxResult(msg);
    }

	/**
	 * 
	 * 导出：资产分类估值
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("exportTypeAssets")
	public ResponseEntity<byte[]> exportTypeAssets() throws Exception {

		List<ValuationMethodScheme001Dto> dtoList = valuationMethodScheme001Service
				.findSchemeDetail(RequestUtil.getPageParam());
		File outFile = valuationMethodScheme001Service.makeFile(
				"/com/joyintech/tams/valuation/valuationparametermanage/valuationmethodscheme/conf/export_exporttypeassets.xml",
				dtoList);
		/*return ExportUtil.export(this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
				+ DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
				+ FileNameUtil.getFileExtName(outFile), outFile, true);*/
		return ExportUtil.export("一般资产估值配置" + SystemGlobalConstant.OPEN_PARENTHESIS
				+ DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
				+ FileNameUtil.getFileExtName(outFile), outFile, true);
	}

	/**
	 * 
	 * 导出：特殊资产估值
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("exportSpecialAssets")
	public ResponseEntity<byte[]> exportSpecialAssets() throws Exception {

		List<ValuationMethodScheme001Dto> dtoList = valuationMethodScheme001Service
				.findSpecialAssets(RequestUtil.getPageParam());
		File outFile = valuationMethodScheme001Service.makeFile(
				"/com/joyintech/tams/valuation/valuationparametermanage/valuationmethodscheme/conf/export_exportspecialassets.xml",
				dtoList);
	/*	return ExportUtil.export(this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
				+ DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
				+ FileNameUtil.getFileExtName(outFile), outFile, true);*/
		return ExportUtil.export("特殊资产估值" + SystemGlobalConstant.OPEN_PARENTHESIS
				+ DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
				+ FileNameUtil.getFileExtName(outFile), outFile, true);
	}

	/**
	 * 下拉框 ： 产品代码
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("findFinprod")
	public List<SelectOptionDto> findFinprod() throws Exception {
		return valuationMethodScheme001Service.findFinprod(RequestUtil.getPageParam());
	}

	/**
	 * 设置默认方案
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "setDefaultScheme", method = RequestMethod.POST)
	public AjaxResult setDefaultScheme(@RequestBody @Validated({ValidateGroupDelete.class}) ValuationMethodScheme002Dto dto , BindingResult br) {
		String msg = StringUtils.EMPTY;
		if(br.hasErrors()){
			return new AjaxResult(br);
		}

		ProcessParameter<ValuationMethodScheme002Dto> processParameter = new ProcessParameter<>();
		WorkFlowParameter<ValuationMethodScheme002Dto> workFlowParameter = new WorkFlowParameter<>();

		try {
			workFlowParameter.setPageId("ValuationMethodScheme002"); // 页面id
			workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
			workFlowParameter.setBusinessCode(dto.getSchemeId());// 业务代码编号
			workFlowParameter.setBusinessType(DictCodeList.共通业务类型_估值方法方案.getValue());// 业务类型
			workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment(
					entity.getSchemeId(), "估值方法方案配置"));// 当前工作流备注
			processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数

			dto = valuationMethodScheme002Service.findBySchemeId(dto.getSchemeId());// dto 为回显值
			dto.setIsdefault(DictCodeList.是否_是.getValue()); // 设置为默认
			valuationMethodScheme002Service.start(processParameter, dto);
			msg = getMessage("COMMON_I_0001"); // 处理正常结束
		} catch (Exception e) {
			this.getLogger().error("启动估值方案流程失败：", e);
			msg = e.getMessage();
		}
		return new AjaxResult(msg);
	}

}
