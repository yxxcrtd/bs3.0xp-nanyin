package com.joyintech.tams.invest.risk.creditmanagement.creditrulemanage.controller;
import java.util.Map;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.invest.risk.creditmanagement.creditrulemanage.dto.CreditRuleDto;
import com.joyintech.tams.invest.risk.creditmanagement.creditrulemanage.service.CreditRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;



/**
 * 额度校验规则管理
 * @author gejiaao
 * @version 1.0 2020年02月15日
 * @see CreditRule002Controller
 * @since 1.0
 */
@Controller
@PageId("CreditRule002")
@RequestMapping("/invest/risk/creditmanagement/creditrulemanage/CreditRule002")
public class CreditRule002Controller extends BaseController {
	 
	/**
     * ceLineService
     */
    @Autowired(required = false)
    private CreditRuleService creditRuleService;
    
    
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;
    
    @Override
    @RequestMapping(PAGE_INIT)    
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.额度分类,
                DictCode.授信资产类型, DictCode.授信资产二级分类, DictCode.授信资产三级分类,
                DictCode.授信资产四级分类, DictCode.授信资产五级分类,DictCode.扣减对象,DictCode.是否);

        model.addAttribute("YES", DictCodeList.是否_是.getValue());
        model.addAttribute("NO", DictCodeList.是否_否.getValue());
        String workflowId = getParam("workflowId");
         // 待办跳转
         if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
             model.addAttribute("workflowId", workflowId);
             model.addAttribute("isTodoList", getParam("isTodoList"));
             CreditRuleDto creditRuleDto = creditRuleService.getBussinessEntityByWorkflowId(workflowId);
//             String e = "["+'"'+creditRuleDto.getAstType3()+'"'+"]";
//             creditRuleDto.setAstType3(JSONArray.toJSONString(e));
             model.addAttribute("entity", creditRuleDto);
         } else if (WorkFlowUtil.isNotFromToDoList() &&StringUtil.isNotEmpty(this.getParam("creditRuleId"))){
             String creditRuleId = this.getParam("creditRuleId");
             CreditRuleDto creditRuleDto = creditRuleService.getBussinessEntityByIndId(creditRuleId);

             model.addAttribute(GlobalConstant.ENTITY, creditRuleDto);
         }
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return super.init(model);
    }

   
	/**
     * 保存按钮点击时的check
     * @return check结果
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
    	ValidateGroupEdit.class}) CreditRuleDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 保存
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(PAGE_SAVE)
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) CreditRuleDto dto, BindingResult br) throws Exception{
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if(StringUtil.isEmpty(dto.getCreditRuleId())){
    	   dto.setCreditRuleId(IDGenerator.generate());
        }
   
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_额度扣减规则管理);
        ProcessParameter<CreditRuleDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<CreditRuleDto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        // 授权代码作为业务编号
        String businessCode = dto.getCreditRuleId();
        params.setBusinessCode(businessCode);
        params.setBusinessType(DictCodeList.共通业务类型_额度扣减规则管理.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("CreditRule002"),
                GlobalConstant.OPEN_PARENTHESIS, businessCode, GlobalConstant.CLOSE_PARENTHESIS,
                 GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = creditRuleService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    /**
     * 导出信息excel
     * @return excel
     * @throws Exception 异常
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return creditRuleService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
    
  
    /**
     * 获取额度标签
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @RequestMapping("getCreditTagInfoList")
    @ResponseBody
    public Page<SelectOptionDto> getCreditTagInfoList(@RequestParam Map<String, Object> params) {
        return creditRuleService.getCreditTagInfoList(params);
    }

}
