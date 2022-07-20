/*
 * 文件名：DisProdPlanManage002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.disprodplanmanage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.DictCodeListExt4;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.service.GetPrdInfoSelectService;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.dto.DisProdPlanManage002Dto;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.service.DisProdPlanManage002Service;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCode;

/**
 * 报表模板管理新建
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author longhao
 * @version 1.0 2018年11月9日
 * @see DisProdPlanManage002Controller
 * @since 1.0
 */
@Controller
@PageId("DisProdPlanManage002")
@RequestMapping("/disclosure/information/config/disprodplanmanage/DisProdPlanManage002")
public class DisProdPlanManage002Controller extends BaseController {

	@Autowired
	DisProdPlanManage002Service disProdPlanManage002Service;

	@Autowired
	private GetPrdInfoSelectService getPrdInfoSelectService;

	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(DisProdPlanManage002Controller.class);
	@Override
	public String init(Model model) {

		this.setSelectOptionToModel(model,DictCode.信披_数据状态);
		
		String id = this.getParam("prodPlanId");
		/*if (StringUtil.isNotEmpty(id)) {
			model.addAttribute("entity", disProdPlanManage002Service.getDisProdPlanManage002Dto(id));
		}*/
		String workflowId = getParam("workflowId");
		// 不来自于代办
		if (WorkFlowUtil.isNotFromToDoList() &&  StringUtil.isNotEmpty(id)) {
			// 根据主键获取业务实体
			model.addAttribute("entity", disProdPlanManage002Service.getDisProdPlanManage002Dto(id));
		}
		// 来自代办 现在是不走流程
		else if (StringUtil.isNotEmpty(workflowId)) {
			// 根据工作流与留痕处理提供的共通方法获取业务实体
			DisProdPlanManage002Dto entity = disProdPlanManage002Service.getBussinessEntityByWorkflowId(workflowId);
			model.addAttribute("entity", entity);
			model.addAttribute("workflowId", workflowId);
		}

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
	public Page<SelectOptionDto> getPrdInfoSelect() {
		Map<String, Object> param= RequestUtil.getPageParam();
		// param.put("sonFlag","1");
		Page<SelectOptionDto> prdInfoSelect = getPrdInfoSelectService.getAllPrdInfoSelect(param);
		return prdInfoSelect;
	}


	/**
	 *
	 * 分页查询产品下拉框
	 * @param
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("/getPrdInfo")
	public RepProdInfo getPrdInfo(String finprodId){

		return  disProdPlanManage002Service.getPrdInfo(finprodId);
	}


	/**
	 * 根据主键id查询模板参数
	 * @return
	 */
	@RequestMapping("/findParamPage")
	@ResponseBody
	public List<DisProdPlanManage002Dto> findParamPage() {
		if (StringUtil.isNotEmpty(this.getParam("repId"))) {
			return disProdPlanManage002Service.findParamPage(this.getParam("repId"));
		}
		
		return new ArrayList<DisProdPlanManage002Dto>();
	}
	/**
	    * 
	    * check
	    * @param dto 待校验数据
	    * @return 结果
	    * @see
	    * @since 1.0
	    */
	  @ResponseBody
	  @RequestMapping("/checkSave")
	  public AjaxResult checkSave(@RequestBody DisProdPlanManage002Dto dto){
	      AjaxResult result = disProdPlanManage002Service.check(dto);
	      if(result.getErrorList().isEmpty()){
	          result.setData(this.getMessage("DIS_COMMON_C_0001", this.getMessage("SysCommon.save")));
	      }
	      return  result;
	  }

	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Valid DisProdPlanManage002Dto dto, BindingResult bindingResult) {
		String operateType = dto.getOperateType();
		return commonCheck(bindingResult, operateType);
	}


	  
	  /**
		 * 保存 <一句话功能简述> <功能详细描述>
		 * 
		 * @return
		 * @throws IOException
		 */
		@RequestMapping("insert")
		@ResponseBody
		public AjaxResult insert() throws IOException {
			AjaxResult result = new AjaxResult();
			List<ValidMessage> checkList = new ArrayList<ValidMessage>();
			String datas = this.getParam("object");
			String addParam = this.getParam("addParam");
			DisProdPlanManage002Dto dto = JsonUtils.json2Object(datas, DisProdPlanManage002Dto.class);
			try {
				if (this.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
					dto.setDataStatus(DictCodeListExt3.信披_数据状态_有效.getValue());
					dto.setDataSource(DictCodeListExt3.信披_数据来源_系统新建.getValue());
					disProdPlanManage002Service.insert(dto);
				}
				if(this.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
					disProdPlanManage002Service.update(dto);
				}
			}catch (Exception e) {
				logger.error("error:",e);
				result = new AjaxResult("保存失败", MessageType.错误消息);
				return result;
			}
			if(null!=checkList&&checkList.size()!=0) {
				result.setErrorList(checkList);
	        }
			else{
				result= new AjaxResult("保存成功", MessageType.提示消息);
			}
			return result;
		}

	/**
	 * 数据保存操作
	 * @param dto 请求实体类
	 */
	@ResponseBody
	@RequestMapping(OPERATE_TYPE_SAVE)
	public AjaxResult save(@RequestBody @Validated({
			ValidateGroupEdit.class}) DisProdPlanManage002Dto dto, BindingResult br)
			throws Exception {
		if (br.hasErrors()) {
			return new AjaxResult(br);
		}
		// 新建留痕表参数和工作流参数
		ProcessParameter<DisProdPlanManage002Dto> processParameter = new ProcessParameter<DisProdPlanManage002Dto>();
		WorkFlowParameter<DisProdPlanManage002Dto> params = new WorkFlowParameter<DisProdPlanManage002Dto>();
		params.setPageId("DisProdPlanManage002");
		params.setElementId(dto.getElementId());
		params.setBusinessCode(dto.getProdId());
		params.setBusinessType(DictCodeListExt4.共通业务类型_产品排期计划.getValue());
		params.setComment(entity -> "产品排期计划-产品代码"+ "(" + entity.getProdId() + ")");
		// 塞入工作流参数
		processParameter.setWorkFlowParameter(params);

		Date sysDate = com.joyintech.framework.util.DateUtil.platDateFromCache();// 获取平台日期
		dto.setCreateTime(sysDate);
		dto.setUpdateTime(sysDate);
		dto.setCreateUser(CommonUtil.getUserName());
		dto.setUpdateUser(CommonUtil.getUserName());

		// 流程开始
		String msg =disProdPlanManage002Service.start(processParameter, dto);
		return new AjaxResult(msg);
	}
		
}
