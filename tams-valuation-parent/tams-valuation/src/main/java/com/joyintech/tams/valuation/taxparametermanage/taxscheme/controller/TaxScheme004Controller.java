/*
 * 文件名：TaxScheme004.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.taxparametermanage.taxscheme.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.dto.TaxScheme004Dto;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.service.TaxScheme001Service;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.service.TaxScheme004Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 税务方案 - 附加税则表
 * 
 * @author zhangwei
 * @version 1.0 2018年10月10日
 * @see TaxScheme004Controller
 * @since 1.0
 */
@Controller
@PageId("TaxScheme004")
@RequestMapping("/valuation/taxparametermanage/taxscheme/TaxScheme004")
public class TaxScheme004Controller extends BaseController {

	@Autowired
	private TaxScheme001Service taxScheme001Service;

	@Autowired
	private TaxScheme004Service taxScheme004Service;

	/**
	 * 税务方案 - 页面初始化 {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
		String schId = getParam("schId");
		String taxId = getParam("taxId");
		String operateType = getParam("operateType");
		String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);// 工作流ID
		
		TaxScheme004Dto dto = new TaxScheme004Dto();
		if (StringUtils.isNotEmpty(taxId)) { // 查看 、 修改
			dto = taxScheme004Service.findByPrimaryKey(taxId);
		} else if(StringUtil.isNotEmpty(workflowId)) { // 流程查看页面
            dto = taxScheme004Service.getBussinessEntityByWorkflowId(workflowId);
            
            taxId = dto.getTaxId(); // 避免流程，再生成主键
        } 
		
		if (StringUtils.isEmpty(taxId) || "copy".equals(operateType)) { // 新建 、复制
		    dto.setTaxId(taxScheme004Service.getPrimaryKey()); // 主键是 Sequence，这里仅供流程参考
			dto.setSchId(schId);
		}
		dto.setOperateType(operateType);
		model.addAttribute("entity", dto);

		super.setSelectOptionToModel(model, DictCode.附加税类型);
		return super.init(model);
	}

	/**
	 * 提交/保存 check
	 * 
	 * @param dto
	 * @param result
	 * @param operateType
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) TaxScheme004Dto dto, BindingResult bindingResult) throws Exception {
		String msg = StringUtils.EMPTY;
		String operateType = dto.getOperateType();

		if (!bindingResult.hasErrors()) { // 基础验证通过
//			日期检查：生效日必须大于等于平台日。
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			Date effDate = sdf.parse(dto.getEffDate());
//			Date sysDate = sdf.parse(DateUtil.getSysDate());
//			if (effDate.before(sysDate)) {
//				msg = "生效日必须大于等于平台日。";
//				return new AjaxResult(msg, MessageType.错误消息);
//			}

//			2.	唯一性检查：附加税类型+生效日组成唯一性数据。(同方案下)
			Map<String, Object> params = Maps.newHashMap();
			params.put("schId", dto.getSchId());
			params.put("addTaxType", dto.getAddTaxType());
			params.put("effDate", dto.getEffDate());
			if (GlobalConstant.DETAIL_PAGE_MODEL_EDIT.equals(dto.getPageModel())) {// 修改时忽略本身
				params.put("owner", dto.getTaxId());
			}
			long count = taxScheme001Service.findAdtTaxByPage(params).getRecords();
			if (count > 0) {
				msg = "税则表中已存在附加税类型_生效日相同的数据，请确保数据唯一性。";
				return new AjaxResult(msg, MessageType.错误消息);
			}

		}

		return commonCheck(bindingResult, operateType);
	}

  /**
   * 新建或修改 并启动流程
   * @param dto
   * @param bindingResult
   * @return
   */
  @ResponseBody
  @RequestMapping(value = PAGE_SAVE, method = RequestMethod.POST)
  public AjaxResult save(@RequestBody TaxScheme004Dto dto) {
    String msg = StringUtil.EMPTY;
      
    ProcessParameter<TaxScheme004Dto> processParameter = new ProcessParameter<>();
    WorkFlowParameter<TaxScheme004Dto> workFlowParameter = new WorkFlowParameter<>();
      
    try {
          workFlowParameter.setPageId(this.getPageId()); // 页面id
          workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
          workFlowParameter.setBusinessCode(dto.getTaxId());// 业务代码编号
          workFlowParameter.setBusinessType(DictCodeList.共通业务类型_税务方案.getValue());// 业务类型
          workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment(
              "附加税则表" , entity.getTaxId()));// 当前工作流备注
          processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数
          
          taxScheme004Service.start(processParameter, dto);
          msg = getMessage("COMMON_I_0001"); // 处理正常结束
    } catch (Exception e) {
          this.getLogger().error("启动估值方案流程失败：", e);
          msg = e.getMessage();
    }
      
      return new AjaxResult(msg);
  }
	
}
