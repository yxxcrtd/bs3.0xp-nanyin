package com.joyintech.tams.invest.riskredis.quota.entrustedapproval.controller;

import java.util.Map;

import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.riskredis.quota.entrustedapproval.dto.RepEntrustedApprovalDto;
import com.joyintech.tams.invest.riskredis.quota.entrustedapproval.service.RepEntrustedApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;



/**
 * 委托审批
 * @author zhengjunping
 * @version 1.0 2019年9月9日
 * @see RepEntrustedApproval002Controller
 * @since 1.0  
 */
@Controller
@PageId("RepEntrustedApproval002")
@RequestMapping("/invest/riskredis/quota/entrustedapproval/RepEntrustedApproval002")
public class RepEntrustedApproval002Controller extends BaseController {

    /**
     * 接口日志查询service
     */
    @Autowired
    private RepEntrustedApprovalService repEntrustedApprovalService;

    /**
     * 接口日志查询的初始化
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
    	this.setSelectOptionToModel(model, BussinessDictCodeRds.单位,BussinessDictCodeRds.交易方向,BussinessDictCodeRds.交易流通场所);
         String eventId = this.getParam("eventId");
 		 String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
 		 if(workflowId != null && !StringUtil.equals(workflowId, "")){
 			RepEntrustedApprovalDto dto = repEntrustedApprovalService.getBussinessEntityByWorkflowId(workflowId);
 			model.addAttribute("entity", dto);
 		 }
 		 if(StringUtil.isNotEmpty(eventId)){
 			RepEntrustedApprovalDto repEntrustedApprovalDto = repEntrustedApprovalService.selectInfo(eventId);
 	         model.addAttribute("entity", repEntrustedApprovalDto);
 	         model.addAttribute("eventId", this.getParam("eventId"));
 		 }
         
         return super.init(model);
    }

    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RepEntrustedApprovalDto> selectAll()
            throws Exception {
        Map<String,Object> map = RequestUtil.getParameters(getRequest());
        Page<RepEntrustedApprovalDto> page = repEntrustedApprovalService.selectForPageDel(map);
        return page;
    }
    
   /**
    * 保存
    * @param dto dto
    * @param br br
    * @return 结果
    * @throws Exception 异常
    */
   @DTXTransaction
   @ResponseBody
   @RequestMapping("submit")
   public AjaxResult save(@RequestBody @Validated({
       ValidateGroupEdit.class}) RepEntrustedApprovalDto dto, BindingResult br)
       throws Exception {
       if (br.hasErrors()) {
           return new AjaxResult(br);
       }
       ProcessParameter<RepEntrustedApprovalDto> processParameter = new ProcessParameter<RepEntrustedApprovalDto>();
       WorkFlowParameter<RepEntrustedApprovalDto> params = new WorkFlowParameter<RepEntrustedApprovalDto>();
       params.setPageId(this.getPageId());
       params.setElementId(dto.getElementId());
     //修改时为画面所选记录的委托编号。
       params.setBusinessCode(dto.getEventId());
       params.setBusinessType(BussinessDictCodeListRds.共同业务类型_风险审批.getValue());
    // 设置流程业务说明, entity即传入的dto
       // 业务说明：“委托编号：”+画面.委托编号t
       params.setComment(entity -> "委托代码:" + "(" + entity.getEventId() + ")-交易方向:("+entity.getPsAbbr()+")-委托开始日:("+DateUtil.format(entity.getStartDate())+")-委托结束日:("+DateUtil.format(entity.getEndDate())+")");
       processParameter.setWorkFlowParameter(params);
       String msg = repEntrustedApprovalService.start(processParameter, dto);
       return new AjaxResult(msg);
   }
}
