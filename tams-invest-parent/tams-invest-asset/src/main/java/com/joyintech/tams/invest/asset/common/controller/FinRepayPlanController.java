/*
 * 文件名：FinRepayPlanController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.component.business.asset.FinPayInterestStdInfoService;
import com.joyintech.tams.invest.common.asset.dto.FinCashPlanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.asset.common.dto.FinPayPlanDto;
import com.joyintech.tams.invest.asset.common.service.FinPayPlanService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 *  还本计划维护
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月26日
 * @see FinRepayPlanController
 * @since 1.0
 */
@Controller
@PageId("FinRepayPlan")
@RequestMapping("/invest/asset/common/FinRepayPlan")
public class FinRepayPlanController extends BaseController{

    @Autowired
    private FinPayPlanService finPayPlanService;

    @Autowired
    private FinPayInterestStdInfoService finPayInterestStdInfoService;
 // 一体化对应etliu start
//    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.项目类一级分类,DictCode.支付频率_付息频率};
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.项目类一级分类,DictCode.频率};
 // 一体化对应etliu end
    
    @Override
    public String init(Model model){
        setSelectOptionToModel(model, DICT_CODES);
        model.addAttribute("INT_ASSET_TYPE", DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        model.addAttribute("REPAY", DictCodeList.共通业务类型_付息计划调整.getValue());
        setEntity(model);
        return super.init(model); 
    }
   
    
    private void setEntity(Model model) {
        String finprodId = this.getParam("finprodId");
        String workflowId = this.getParam("workflowId");
        // 不来自于代办
        if(WorkFlowUtil.isNotFromToDoList()&& StringUtil.isNotEmpty(finprodId)) {
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("finprodId", finprodId);
            paramMap.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
            paramMap.put("cashType1", DictCodeListExt1.现金流一级类型_本金.getValue());
            paramMap.put("cashType2", DictCodeListExt1.现金流二级类型_本金.getValue());
          //根据主键获取业务实体--标的金融产品基本信息
            FinPayPlanDto finPayPlanDto  = finPayPlanService.getBasicInfo(
                paramMap);
            model.addAttribute("entity", finPayPlanDto);
        }//来自于代办
        else if(!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotBlank(workflowId)){
            //根据工作流与留痕处理提供的共通方法获取业务实体
            FinPayPlanDto finPayPlanDto = finPayPlanService.getBussinessEntityByWorkflowId(workflowId);
            //设置到model中
            model.addAttribute("entity", finPayPlanDto);
    }
        model.addAttribute("workflowId", workflowId);
    }
    
    /**
     * check--保存
     * @param dto
     * @param result
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) FinPayPlanDto dto, BindingResult result) {
    	AjaxResult rst = new AjaxResult();
        if (RequestUtil.getParam("isTodoList") == null || !Boolean.valueOf(RequestUtil.getParam("isTodoList"))) {
            Map<String, Object> params = new HashMap<>();
            params.put("pageId", this.getPageId());
            params.put("state", DictCodeList.流程状态_流程中.getValue());
            params.put("stateNew", DictCodeList.流程状态_新建.getValue());
            params.put("tradeId", dto.getFinprodId());
            if (finPayPlanService.isNotIng(params)) {
                ValidMessage addRst = new ValidMessage("已存在流程中数据，不能重复提交。!");
                List<ValidMessage> errorList = new ArrayList<>();
                errorList.add(addRst);
                rst.setErrorList(errorList);
                return rst;
            }
        }
        StringBuffer sb = new StringBuffer();
        //校验还本计划最后一条数据的支付日是否等于付息计划最后一条数据的支付日
        if(StringUtil.isNotEmpty(dto) && StringUtil.isNotEmpty(dto.getBussFinRepaymentDto())){
            List<FinCashPlanDto>  cashPlans=dto.getBussFinRepaymentDto().getFinCashPlanList();
            FinCashPlanDto cashDto=new FinCashPlanDto();
            if(null!=cashPlans && cashPlans.size()>0){
                cashDto=cashPlans.get(cashPlans.size()-1);
                if(!StringUtil.isNotEmpty(cashDto.getPayDate())){
                    sb.append(
                            this.getMessage("还本计划最后一条支付日为空，是否继续？", this.getMessage("FinRepayPlan_bussFinRepaymentDto"))
                                    + GlobalConstant.HTML_NEWLINE);
                }else{
                    // 查询付息计划
                    List<FinCashPlanDto> invList = new ArrayList<>();
                    if (StringUtil.isNotEmpty(dto.getFinprodId())) {
                        invList = finPayInterestStdInfoService.getFinCashPlanByProductId(dto.getFinprodId(),
                                DictCodeListExt1.现金流二级类型_利息.getValue(), DictCodeListExt1.现金流业务类型_金融产品.getValue(), null);
                    }
                    if(null!=invList && invList.size()>0){
                        FinCashPlan repayDto=invList.get(invList.size()-1);
                        if(null!=repayDto.getPayDate() && null!=cashDto.getPayDate() && cashDto.getPayDate().compareTo(repayDto.getPayDate())!=0){
                            sb.append(
                                    this.getMessage("还本计划最后一条支付日与付息计划的支付日不一致，是否继续？", this.getMessage("FinPayInterestPlan_finPayInterestStdInfoDto"))
                                            + GlobalConstant.HTML_NEWLINE);
                        }
                    }
                }
            }

        }
        if (StringUtil.isNotEmpty(sb)) {
            rst.setData(sb.substring(0, sb.length() - GlobalConstant.HTML_NEWLINE.length()));
            return rst;
        }else{
            return commonCheck(result, dto.getOperateType());
        }
    }
    
    /**
     * 保存
     * @param dto
     * @param br
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(PAGE_SAVE)
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) FinPayPlanDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        WorkFlowParameter<FinPayPlanDto> params = new WorkFlowParameter<FinPayPlanDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getFinprodId());
//        params.setBusinessType(DictCodeList.共通业务类型_付息计划调整.getValue());
        params.setBusinessType(DictCodeList.共通业务类型_项目类资产还本计划调整.getValue());
        // 设置流程业务说明
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("FinRepayPlan.finprodId")+
                GlobalConstant.OPEN_PARENTHESIS+entity.getFinprodId()+GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("FinRepayPlan.finprodName")+GlobalConstant.OPEN_PARENTHESIS+
                entity.getFinprodName()+GlobalConstant.CLOSE_PARENTHESIS);
        });
        // 给参数变成ProcessParameter而非WorkFlowParameter
        ProcessParameter<FinPayPlanDto> parameter = new ProcessParameter<FinPayPlanDto>();
        parameter.setWorkFlowParameter(params);
        // 系统页面表IS_NEED_WORKFLOW 是否走流程，01-是，02-否
        String msg = finPayPlanService.start(parameter, dto);
        return new AjaxResult(msg);
    }
}
