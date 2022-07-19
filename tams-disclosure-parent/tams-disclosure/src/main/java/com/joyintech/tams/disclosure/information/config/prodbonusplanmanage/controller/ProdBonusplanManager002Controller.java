/*
 * 文件名：DisProdPlanManage002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.DictCodeListExt4;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.service.GetPrdInfoSelectService;
import com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.dto.ProdBonusplanDto;
import com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.service.ProdBonusplanManager002Service;
import com.joyintech.tams.disclosure.information.config.prodfeedealInterf.dto.SvrProdFeeDealInterfDto;
import com.joyintech.tams.disclosure.information.config.proelementadj.dto.ProElementAdj001Dto;
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
 * @see ProdBonusplanManager002Controller
 * @since 1.0
 */
@Controller
@PageId("ProdBonusplanManager002")
@RequestMapping("/disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager002")
public class ProdBonusplanManager002Controller extends BaseController {

	@Autowired
	ProdBonusplanManager002Service prodBonusplanManager002Service;

	@Autowired
	private GetPrdInfoSelectService getPrdInfoSelectService;

	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ProdBonusplanManager002Controller.class);
	@Override
	public String init(Model model) {

		this.setSelectOptionToModel(model,DictCode.信披_数据状态);
		this.setSelectOptionToModel(model, DictCode.信披_分红方式);
		String workflowId = getParam("workflowId");

		String id = this.getParam("bonusPlanId");

		// 不来自于代办
		if (WorkFlowUtil.isNotFromToDoList() &&  StringUtil.isNotEmpty(id)) {
			// 根据主键获取业务实体
			model.addAttribute("entity",  prodBonusplanManager002Service.getProdBonusplanDto(id));
		}
		// 来自代办 现在是不走流程
		else if (StringUtil.isNotEmpty(workflowId)) {
			// 根据工作流与留痕处理提供的共通方法获取业务实体
			ProdBonusplanDto entity = prodBonusplanManager002Service.getBussinessEntityByWorkflowId(workflowId);
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
		param.put("sonFlag","2");
		Page<SelectOptionDto> prdInfoSelect = getPrdInfoSelectService.getAllPrdInfoSelect(param);
		return prdInfoSelect;
	}

	/**
	 *
	 * check
	 * @param prodId 查询产品明细信息
	 * @return 结果
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("/getPrdInfo")
	public RepProdInfo getPrdInfo(String prodId){

		return  prodBonusplanManager002Service.getPrdInfo(prodId);
	}


	/**
	 * 根据主键id查询模板参数
	 * @return
	 */
	@RequestMapping("/findParamPage")
	@ResponseBody
	public List<ProdBonusplanDto> findParamPage() {
		if (StringUtil.isNotEmpty(this.getParam("repId"))) {
			return prodBonusplanManager002Service.findParamPage(this.getParam("repId"));
		}
		
		return new ArrayList<ProdBonusplanDto>();
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
	  public AjaxResult checkSave(@RequestBody ProdBonusplanDto dto){
	      AjaxResult result = prodBonusplanManager002Service.check(dto);
	      if(result.getErrorList().isEmpty()){
	          result.setData(this.getMessage("DIS_COMMON_C_0001", this.getMessage("SysCommon.save")));
	      }
	      return  result;
	  }

	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Valid ProdBonusplanDto dto, BindingResult bindingResult) {
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
			ProdBonusplanDto dto = JsonUtils.json2Object(datas, ProdBonusplanDto.class);
			try {
				if (this.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
					dto.setDataStatus(DictCodeListExt3.信披_数据状态_有效.getValue());
					dto.setDataSource(DictCodeListExt3.信披_数据来源_系统新建.getValue());
					prodBonusplanManager002Service.insert(dto);
				}
				if(this.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
					prodBonusplanManager002Service.update(dto);
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
			ValidateGroupEdit.class}) ProdBonusplanDto dto, BindingResult br)
			throws Exception {
		if (br.hasErrors()) {
			return new AjaxResult(br);
		}
		// 新建留痕表参数和工作流参数
		ProcessParameter<ProdBonusplanDto> processParameter = new ProcessParameter<ProdBonusplanDto>();
		WorkFlowParameter<ProdBonusplanDto> params = new WorkFlowParameter<ProdBonusplanDto>();
		params.setPageId("ProdBonusplanManager002");
		params.setElementId(dto.getElementId());
		params.setBusinessCode(dto.getProdId());
		params.setBusinessType(DictCodeListExt4.共通业务类型_产品分红到期计划.getValue());
		params.setComment(entity -> "分红计划编号-产品代码"+ "(" + entity.getProdId() + ")");
		// 塞入工作流参数
		processParameter.setWorkFlowParameter(params);

		Date sysDate = com.joyintech.framework.util.DateUtil.platDateFromCache();// 获取平台日期
		dto.setCreateTime(sysDate);
		dto.setUpdateTime(sysDate);
		dto.setCreateUser(CommonUtil.getUserName());
		dto.setUpdateUser(CommonUtil.getUserName());

		// 流程开始
		String msg =prodBonusplanManager002Service.start(processParameter, dto);
		return new AjaxResult(msg);
	}

		
		

}
