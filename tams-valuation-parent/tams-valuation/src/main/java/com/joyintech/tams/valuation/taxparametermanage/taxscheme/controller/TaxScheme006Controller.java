/*
 * 文件名：TaxScheme006.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.taxparametermanage.taxscheme.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.dto.TaxScheme006Dto;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.service.TaxScheme001Service;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.service.TaxScheme006Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 税务方案 - 参数配置
 * 
 * @author zhangwei
 * @version 1.0 2018年10月10日
 * @see TaxScheme006Controller
 * @since 1.0
 */
@Controller
@PageId("TaxScheme006")
@RequestMapping("/valuation/taxparametermanage/taxscheme/TaxScheme006")
public class TaxScheme006Controller extends BaseController {

	@Autowired
	private TaxScheme001Service taxScheme001Service;

	@Autowired
	private TaxScheme006Service taxScheme006Service;

	/**
	 * 税务方案 - 页面初始化 {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
		String schemeId = getParam("schemeId");
		String seqId = getParam("seqId");
		String operateType = getParam("operateType");
		TaxScheme006Dto dto = new TaxScheme006Dto();
		String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);// 工作流ID
		
		if (StringUtils.isNotEmpty(seqId)) { // 查看 、 修改
			dto = taxScheme006Service.findByPrimaryKey(seqId);
		} else if(StringUtil.isNotEmpty(workflowId)) { // 流程查看页面
            dto = taxScheme006Service.getBussinessEntityByWorkflowId(workflowId);
            
            seqId = dto.getSeqId(); // 避免流程，再生成主键
            model.addAttribute(GlobalConstant.WORKFLOW_ID, workflowId);
        }
		
		
		if (StringUtils.isEmpty(seqId) || "copy".equals(operateType)) { // 新建 、复制
		    dto.setSeqId(IDGenerator.generate()); // 主键是 Sequence，这里仅供流程参考
			dto.setSchemeId(schemeId);
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
	public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) TaxScheme006Dto dto, BindingResult bindingResult) throws Exception {
		String msg = StringUtils.EMPTY;
		String operateType = dto.getOperateType();

		if (!bindingResult.hasErrors()) { // 基础验证通过
			//日期检查：生效日必须大于等于平台日。
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date effDate = sdf.parse(dto.getEffDate());
			Date sysDate = sdf.parse(DateUtil.getSysDate());
			if (effDate.before(sysDate)) {
				msg = "生效日必须大于等于平台日。";
				return new AjaxResult(msg, MessageType.错误消息);
			}

//			2.	项目+生效日组成唯一性数据。(同方案下)
			Map<String, Object> params = Maps.newHashMap();
			params.put("schemeId", dto.getSchemeId());
			params.put("configId", dto.getConfigId());
			params.put("effDate", dto.getEffDate());
			if (GlobalConstant.DETAIL_PAGE_MODEL_EDIT.equals(dto.getPageModel())) {// 修改时忽略本身
				params.put("owner", dto.getSeqId());
			}
			long count = taxScheme001Service.findConfigByPage(params).getRecords();
			if (count > 0) {
				msg = "参数配置表中已存在项目_生效日相同的数据，请确保数据唯一性。";
				return new AjaxResult(msg, MessageType.错误消息);
			}

		}

		return commonCheck(bindingResult, operateType);
	}

	/**
     * 下拉框 ： 项目
     * 
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findProject")
    public List<SelectOptionDto> findProject() throws Exception {
        return taxScheme006Service.findProject(RequestUtil.getPageParam());
    }
    
    /**
     * 下拉框 ： 项目
     * 
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findValue")
    public List<SelectOptionDto> findValue() throws Exception {
        List<SelectOptionDto> list = Lists.newArrayList();
        
        String dictCode = taxScheme006Service.findValue(RequestUtil.getPageParam());
        if(StringUtils.isNotBlank(dictCode)) {
            try {
                SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.get(dictCode), null);
                if (dictCode.equals(DictCode.频率.getValue())) {
                    for (SelectOptionDto dto : selectDto.getOptions()) {
                    	if (dto.getId().equals(DictCodeList.频率_月.getValue())
                    			|| dto.getId().equals(DictCodeList.频率_季.getValue())
                    			|| dto.getId().equals(DictCodeList.频率_年.getValue())) {
                    		list.add(dto);
                    	}
                    }
                } else {
                	list = selectDto.getOptions();
                }
            }catch (Exception e) {
                getLogger().error(e.getMessage(), e);
            }
        }
        return list;
    }

    /**
     * 新建或修改 并启动流程
     * @param dto
     * @param bindingResult
     * @return
     */
    @ResponseBody
    @RequestMapping(value = PAGE_SAVE, method = RequestMethod.POST)
    public AjaxResult save(@RequestBody TaxScheme006Dto dto) {
      String msg = StringUtil.EMPTY;
        
      ProcessParameter<TaxScheme006Dto> processParameter = new ProcessParameter<>();
      WorkFlowParameter<TaxScheme006Dto> workFlowParameter = new WorkFlowParameter<>();
        
      try {
            workFlowParameter.setPageId(this.getPageId()); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(dto.getSeqId());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeList.共通业务类型_税务方案.getValue());// 业务类型
            workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment(
                "参数设置" , entity.getSeqId()));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数
            
            Map<String, Object> map = Maps.newHashMap();
            map.put("configId", dto.getConfigId());
            dto.setDictCode(taxScheme006Service.findValue(map)); // 用于新建流程回显
            
            taxScheme006Service.start(processParameter, dto);
            msg = getMessage("COMMON_I_0001"); // 处理正常结束
      } catch (Exception e) {
            this.getLogger().error("启动估值方案流程失败：", e);
            msg = e.getMessage();
      }
        
        return new AjaxResult(msg);
    }
}
