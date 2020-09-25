/*
 * 文件名：ProdDevelopManage001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangJin
 * 修改时间：2019年10月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productplanning.informationMaintenance.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.nolimit.nonstProUplimitMain.dto.NonstProUplimitMain001Dto;
import com.joyintech.tams.invest.product.productplanning.common.dto.PlanningCommonDto;
import com.joyintech.tams.invest.product.productplanning.common.service.PlanningCommonService;
import com.joyintech.tams.invest.product.productplanning.informationMaintenance.dto.FinProductPlanning001Dto;
import com.joyintech.tams.invest.product.productplanning.informationMaintenance.dto.FinProductPlanning002DetailDto;
import com.joyintech.tams.invest.product.productplanning.informationMaintenance.dto.FinProductPlanning002Dto;
import com.joyintech.tams.invest.product.productplanning.informationMaintenance.service.FinProductPlanningEstablish002Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.activiti.editor.language.json.converter.util.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 产品规划信息新建
 *
 * @author 代国富
 * @version 1.0 2019年12月17日
 * @see FinProductPlanningEstablish002Controller
 * @since 1.0
 */
@Controller
@PageId("FinProductPlanningEstablish002")
@RequestMapping("/invest/product/productplanning/informationMaintenance/FinProductPlanningEstablish002")
public class FinProductPlanningEstablish002Controller extends BaseController {
    /**
     * 主键生成类
     */
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;
    @Autowired
    private FinProductPlanningEstablish002Service finProductPlanningEstablish002Service;
    @Override
    public String init(Model model) {
        model.addAttribute("sysDate",DateUtil.getSysDate());
        String planningNo = this.getParam("planningNo");
        String workflowId = this.getParam("workflowId");
        //planningNo 为空,设置基准日为平台日减一
        if(WorkFlowUtil.isNotFromToDoList() && !StringUtil.isNotEmpty(planningNo)){
            FinProductPlanning002Dto entity = new FinProductPlanning002Dto();
            entity.setBaseDate(DateUtil.addDay(DateUtil.platDateFromCache(),-1));
            model.addAttribute("entity",entity);
        }else if(WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(planningNo)){
            //planningNo不为空通过planningNo查询数据
            FinProductPlanning002Dto entity = finProductPlanningEstablish002Service.getEntityByNo(planningNo);
            model.addAttribute("entity",entity);
        }else if(!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)){
            //从工作流中获取
            FinProductPlanning002Dto entity = finProductPlanningEstablish002Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity",entity);
        }
        model.addAttribute("workflowId",workflowId);
        return super.init(model);
    }

    /**
     * 画面点击保存按钮时check
     *
     * @param dto 实体类
     *
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) FinProductPlanning002Dto dto, BindingResult br) {
        if (br != null && br.hasErrors()) {
            return new AjaxResult(br);
        }
        return this.commonCheck(br, dto.getOperateType());

    }
    /**
     * 新建页面查询
     *
     * @return Page<FinProductPayInt001Dto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public List<FinProductPlanning002DetailDto> findPage() throws Exception {
        String planningNo = RequestUtil.getParam("planningNo");
        String workflowId = RequestUtil.getParam(GlobalConstant.WORKFLOW_ID);
        /**
         * 如果没有填写开始时间和结束时间就返回一个空的集合,当有开始时间和结束时间时候才查询
         */
        if(StringUtil.isEmpty(RequestUtil.getPageParam().get("planningStartDate").toString())
                || StringUtil.isEmpty(RequestUtil.getPageParam().get("planningEndDate").toString())
                || StringUtil.isEmpty(RequestUtil.getPageParam().get("baseDate").toString())){
            return new ArrayList<>();
        }
        // 工作流获取entity
        if (WorkFlowUtil.isNotFromToDoList() && StringUtils.isNotEmpty(workflowId)) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
            FinProductPlanning002Dto bussinessEntityByWorkflowId = finProductPlanningEstablish002Service.getBussinessEntityByWorkflowId(workflowId);
          return  bussinessEntityByWorkflowId.getDetailDtoList();
        }
        else if (WorkFlowUtil.isNotFromToDoList() && StringUtils.isNotEmpty(planningNo)) {
            /**
             * planningNo不为空,查询数据库嵌入页面
             */
            FinProductPlanning002Dto dto=  finProductPlanningEstablish002Service.getEntityByNo(planningNo);
            return dto.getDetailDtoList();
        }
             /**
                * 新建自动带出的列表
              */
        List<FinProductPlanning002DetailDto> list = finProductPlanningEstablish002Service.getEntity(RequestUtil.getPageParam());
        return  list;

    }

    /**
     * 点击+号操作
     * 根据系列名称查询是否存在该数据
     * @param
     * @return
     */
    @RequestMapping("findCountByName")
    @ResponseBody
    public Integer findCountByName(@RequestBody Map<String, Object> map){
        return finProductPlanningEstablish002Service.findCountByName(map);
    }

    /**
     * 点击+号操作
     * 根据系列编号查询是否存在该数据
     * @param
     * @return
     */
    @RequestMapping("findCountByNo")
    @ResponseBody
    public Integer findCountByNo(@RequestBody Map<String, Object> map){
        return finProductPlanningEstablish002Service.findCountByNo(map);
    }

    /**
     * 修改页面查询到期份额
     * @param map
     * @return
     */
    @RequestMapping("findProductsDue")
    @ResponseBody
    public  BigDecimal findProductsDue (@RequestBody Map<String, Object> map){
        return finProductPlanningEstablish002Service.findProductsDue(map);
    }

    /**
     * 保存
     * 
     * @param dto dto
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody FinProductPlanning002Dto dto)
        throws Exception {
        // 生成计划编号ID
        if (StringUtil.isEmpty(dto.getPlanningNo())) {
            BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
            bussinessCodeParameter.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_产品规划信息维护));
            if (baseGetBusinessId != null) {
                String tacticConfigCode = baseGetBusinessId.buildBussinessCode(bussinessCodeParameter) != null
                        ? baseGetBusinessId.buildBussinessCode(bussinessCodeParameter)
                        : IDGenerator.generate();
                dto.setPlanningNo(tacticConfigCode);
            }
        }
        /**
         * 设置detail的规划编号
         */
        List<FinProductPlanning002DetailDto> detailDtoList = dto.getDetailDtoList();
        if(CollectionUtils.isNotEmpty(detailDtoList)){
            detailDtoList.forEach(e->{
                e.setPlanningNo(dto.getPlanningNo());
            });
        }

            ProcessParameter<FinProductPlanning002Dto> processParameter = new ProcessParameter<FinProductPlanning002Dto>();
            WorkFlowParameter<FinProductPlanning002Dto> params = new WorkFlowParameter<FinProductPlanning002Dto>();
            params.setPageId(this.getPageId());
            params.setElementId(dto.getElementId());
            params.setBusinessCode(dto.getPlanningNo());
            params.setBusinessType(DictCodeListExt3.共通业务类型_产品规划信息维护.getValue());
            params.setComment(entity-> "产品规划编号" + "(" + dto.getPlanningNo() + ")"
                + "规划开始日" + "(" + DateUtil.format(entity.getPlanningStartDate(),DateUtil.DEFAULT_DATE_FORMAT_EN) + ")"
                + "规划结束日" + "(" + DateUtil.format(entity.getPlanningEndDate(),DateUtil.DEFAULT_DATE_FORMAT_EN) + ")"
                + "规划基准日" + "(" + DateUtil.format(entity.getBaseDate(),DateUtil.DEFAULT_DATE_FORMAT_EN) + ")");
            processParameter.setWorkFlowParameter(params);
            // 调用工作流事件
        String  msg = finProductPlanningEstablish002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
