package com.joyintech.tams.invest.product.messageadjust.supplement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.messageadjust.supplement.dto.FinSupplementPlanChg001Dto;
import com.joyintech.tams.invest.product.messageadjust.supplement.service.FinSupplementPlanChg001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

@Controller
@PageId("FinSupplementPlanChg001")
@RequestMapping("/invest/product/messageadjust/supplement/FinSupplementPlanChg001")
public class FinSupplementPlanChg001Controller extends BaseController {
    @Autowired
    private FinSupplementPlanChg001Service finSupplementPlanChg001Service;

    /**
     * 业务标号生成共通
     */
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;

    @Override
    public String init(Model model) {
        String workflowId =  this.getParam(GlobalConstant.WORKFLOW_ID);

        // 工作流待办跳转
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            FinSupplementPlanChg001Dto dto = finSupplementPlanChg001Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", dto);

        }
        model.addAttribute("YES_OR_NO_Y", DictCodeListExt1.是否_是.getValue());
        model.addAttribute("SUPPLEMENT_STATUS_02", DictCodeListExt3.贴补状态_未贴补.getValue());
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("/getFinprodId")
    public Page<SelectOptionDto> getProdId() {
        return finSupplementPlanChg001Service.getFinprodIds(RequestUtil.getPageParam());
    }

    @ResponseBody
    @RequestMapping("/findPage")
    public Page<FinSupplementPlanChg001Dto> findPage() {
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        // 工作流待办跳转
        Page<FinSupplementPlanChg001Dto> page = new Page<FinSupplementPlanChg001Dto>();
        if (StringUtil.isNotEmpty(workflowId)) {
            FinSupplementPlanChg001Dto dto = finSupplementPlanChg001Service.getBussinessEntityByWorkflowId(workflowId);
            page.setRoot(dto.getDataList());

        } else {
            page = finSupplementPlanChg001Service.findPage(RequestUtil.getPageParam());
            
            //对获取的回补计划进行调整，如果后面区间已经回补过，前面的区间需要改变成无需处理的贴补状态 add by chendijun
            if(StringUtil.isNotEmpty(page) && !StringUtil.isEmptyList(page.getRoot())){
                for(FinSupplementPlanChg001Dto dto1:page.getRoot()) {
                    if(StringUtil.equals(DictCodeListExt3.贴补状态_已贴补.getValue(), dto1.getSupplementStatus())){
                        for(FinSupplementPlanChg001Dto dto2:page.getRoot()) {
                            if(StringUtil.equals(dto2.getLayFinprodId(),dto2.getLayFinprodId())
                                && StringUtil.equals(dto2.getSupplementStatus(),DictCodeListExt3.贴补状态_未贴补.getValue())
                                && DateUtil.compareDate(dto2.getAccrDate(), dto1.getAccrDate())<0){
                                dto2.setSupplementStatus(DictCodeListExt3.贴补状态_无需处理.getValue());
                            }
                        }
                    }
                }
            }
        }
        
        return page;
            
    }

    
    /**
     * 产品款型下拉框
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("queryProdStyleInfo")
    @ResponseBody
    public Page<SelectOptionDto> queryProdStyleInfo(@RequestParam Map<String, Object> param) {
        return finSupplementPlanChg001Service.queryProdStyleInfo(param);
        
    }
    
    /**
     * 保存按钮点击时的check
     *
     * @param dto
     *            业务实体
     * @param result
     *            BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated FinSupplementPlanChg001Dto dto,
            BindingResult result) {
        return commonCheck(result, "0");
    }

    /**
     * 
     * <一句话功能简述> <功能详细描述>
     * 
     * @param dto
     *            业务实体
     * @param br
     *            AjaxResult
     * @return AjaxResul
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated FinSupplementPlanChg001Dto dto,
            BindingResult br) throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<FinSupplementPlanChg001Dto> processParameter = new ProcessParameter<FinSupplementPlanChg001Dto>();
        WorkFlowParameter<FinSupplementPlanChg001Dto> params = new WorkFlowParameter<FinSupplementPlanChg001Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        String branch = CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE);
        dto.setBranch(Long.parseLong(branch));
        // 业务编号
        params.setBusinessCode(entity -> {
            BussinessCodeParameter param = new BussinessCodeParameter();
            param.setCommonBusinessExtType(DictCodeListExt1.fromDictCodeList(DictCodeListExt1.共通业务类型_回补计划调整));
            String code = baseGetBusinessId != null ? baseGetBusinessId.buildBussinessCode(param)
                    : IDGenerator.generate();

            return code;
        });
        dto.setTradeId(params.getBusinessCode(dto));

        params.setBusinessType(DictCodeListExt1.共通业务类型_回补计划调整.getValue());
        // 业务说明："产品代码" + "(" + 画面.产品代码 + ")" + "-" + "开放计划维护"
        params.setComment(entity -> this.getMessage("FinSupplementPlanChg001.finprodAbbr") + "("
                + entity.getFinprodAbbr() + ")" + this.getMessage("FinSupplementPlanChg001"));
        processParameter.setWorkFlowParameter(params);
        String msg = finSupplementPlanChg001Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    /**
     * 拆分计划
     * <一句话功能简述>
     * <功能详细描述>
     * @param finSupplementPlanChg001Dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/splitPlan")
    public List<FinSupplementPlanChg001Dto> splitPlan(@RequestBody FinSupplementPlanChg001Dto finSupplementPlanChg001Dto) {
        
        return finSupplementPlanChg001Service.splitPlan(finSupplementPlanChg001Dto);
    }
    
    /**
     * 合并计划
     * <一句话功能简述>
     * <功能详细描述>
     * @param finSupplementPlanChg001Dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/joinPlan")
    public List<FinSupplementPlanChg001Dto> joinPlan(@RequestBody FinSupplementPlanChg001Dto finSupplementPlanChg001Dto) {
        return finSupplementPlanChg001Service.joinPlan(finSupplementPlanChg001Dto);
    }

}
