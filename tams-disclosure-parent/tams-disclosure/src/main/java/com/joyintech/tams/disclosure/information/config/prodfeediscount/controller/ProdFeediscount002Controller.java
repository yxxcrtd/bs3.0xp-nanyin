/*
 * 文件名：DisProdPlanManage002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.prodfeediscount.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.DictCodeListExt4;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.service.GetPrdInfoSelectService;
import com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.dto.ProdBonusplanDto;
import com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.service.ProdBonusplanManager002Service;
import com.joyintech.tams.disclosure.information.config.prodfeediscount.dto.ProdFeediscountDto;
import com.joyintech.tams.disclosure.information.config.prodfeediscount.service.ProdFeediscountService;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.collections4.CollectionUtils;
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

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * 报表模板管理新建
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author longhao
 * @version 1.0 2018年11月9日
 * @see ProdFeediscount002Controller
 * @since 1.0
 */
@Controller
@PageId("ProdFeediscount002")
@RequestMapping("/disclosure/information/config/prodfeediscount/ProdFeediscount002")
public class ProdFeediscount002Controller extends BaseController {

	@Autowired
	ProdFeediscountService prodFeediscountService;

	@Autowired
	private GetPrdInfoSelectService getPrdInfoSelectService;

	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ProdFeediscount002Controller.class);
	@Override
	public String init(Model model) {
		this.setSelectOptionToModel(model,DictCode.信披_数据状态ED);
		this.setSelectOptionToModel(model, DictCode.信披_分红方式);
		String workflowId = getParam("workflowId");
		String id = this.getParam("prodDiscountId");

		// 不来自于代办
		if (WorkFlowUtil.isNotFromToDoList() &&  StringUtil.isNotEmpty(id)) {
			// 根据主键获取业务实体
			model.addAttribute("entity",  prodFeediscountService.selectByKeyId(id));
		}
		// 来自代办 现在是不走流程
		else if (StringUtil.isNotEmpty(workflowId)) {
			// 根据工作流与留痕处理提供的共通方法获取业务实体
			ProdFeediscountDto entity = prodFeediscountService.getBussinessEntityByWorkflowId(workflowId);
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

		return  prodFeediscountService.getPrdInfo(prodId);
	}


/*	*//**
	 * 根据主键id查询模板参数
	 * @return
	 *//*
	@RequestMapping("/findParamPage")
	@ResponseBody
	public List<ProdFeediscountDto> findParamPage() {
		if (StringUtil.isNotEmpty(this.getParam("repId"))) {
			return prodFeediscountService.findParamPage(this.getParam("repId"));
		}
		
		return new ArrayList<ProdFeediscountDto>();
	}*/
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
	  public AjaxResult checkSave(@RequestBody ProdFeediscountDto dto){
	      AjaxResult result = prodFeediscountService.check(dto);
		  String operateType = dto.getOperateType();
		  if (StringUtil.equals(operateType,"new")) {
			  if(CollectionUtils.isNotEmpty(prodFeediscountService.getProdFeeDiscount(dto))) {
				  result.setStatuCode(500);
				  result.setMessage("不可创建重复数据！",MessageType.错误消息);
				  return result;
			  }
		  }
	      if(result.getErrorList().isEmpty()){
	          result.setData(this.getMessage("DIS_COMMON_C_0001", this.getMessage("SysCommon.save")));
	      }
	      return  result;
	  }

	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Valid ProdFeediscountDto dto, BindingResult bindingResult) {

		  String operateType = dto.getOperateType();
		  dto.setRptIssueDateStr(dto.getRptIssueDateStr());
		if (StringUtil.equals(operateType,"new")) {
			AjaxResult result = new AjaxResult();
			if(CollectionUtils.isNotEmpty(prodFeediscountService.getProdFeeDiscount(dto))) {
				result.setStatuCode(500);
				result.setMessage("不可创建重复数据！",MessageType.错误消息);
				return result;
			}
		}
		AjaxResult result = prodFeediscountService.check(dto);
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
			ProdFeediscountDto dto = JsonUtils.json2Object(datas, ProdFeediscountDto.class);
			try {
				if (this.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
					dto.setDataStatus(DictCodeListExt3.信披_数据状态_有效E.getValue());
					dto.setDataSource(DictCodeListExt3.信披_数据来源_系统新建.getValue());
					prodFeediscountService.insert(dto);
				}
				if(this.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
					prodFeediscountService.update(dto);
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
			ValidateGroupEdit.class}) ProdFeediscountDto dto, BindingResult br)
			throws Exception {
		if (br.hasErrors()) {
			return new AjaxResult(br);
		}
		// 新建留痕表参数和工作流参数
		ProcessParameter<ProdFeediscountDto> processParameter = new ProcessParameter<ProdFeediscountDto>();
		WorkFlowParameter<ProdFeediscountDto> params = new WorkFlowParameter<ProdFeediscountDto>();
		params.setPageId("ProdFeediscount002");
		params.setElementId(dto.getElementId());
		params.setBusinessCode(dto.getProdId());
		params.setBusinessType(DictCodeListExt4.共通业务类型_产品费率优惠.getValue());
		params.setComment(entity -> "产品费率优惠-产品代码"+ "(" + entity.getProdId() + ")");
		// 塞入工作流参数
		processParameter.setWorkFlowParameter(params);

		Date sysDate = com.joyintech.framework.util.DateUtil.platDateFromCache();// 获取平台日期
		dto.setCreateTime(sysDate);
		dto.setUpdateTime(sysDate);
		dto.setCreateUser(CommonUtil.getUserName());
		dto.setUpdateUser(CommonUtil.getUserName());

		// 流程开始
		String msg =prodFeediscountService.start(processParameter, dto);
		return new AjaxResult(msg);
	}

		
		

}
