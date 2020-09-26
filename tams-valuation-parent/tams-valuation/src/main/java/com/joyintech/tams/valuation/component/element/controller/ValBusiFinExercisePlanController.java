/*
 * 文件名：ValBusiFinExercisePlanController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.component.element.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinExercisePlan;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.tams.valuation.component.element.dto.ValBusiFinExercisePlanDto;
import com.joyintech.tams.valuation.component.element.interf.ValBusiFinExercisePlanService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 *  行权计划
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月11日
 * @see ValBusiFinExercisePlanController
 * @since 1.0
 */
@Controller
@RequestMapping("/valuation/component/element/ValBusiFinExercisePlan")
public class ValBusiFinExercisePlanController extends BaseController{

    @Autowired
    private MstWorkflowCommonService  mstWorkflowCommonService;
    
    @Autowired
    private ValBusiFinExercisePlanService busiFinExercisePlanService;
    
    @Override
    public String init(Model model) {
        String entityName = StringUtil.defaultString(this.getParam("entityName"),
            ValBusiFinExercisePlanService.DEFAULT_ENTITY_NAME);
        model.addAttribute("entityName", entityName);
        //行权方式
        this.setSelectOptionToModel(model, DictCode.行权状态);
        this.setSelectOptionToModel(model, DictCode.行权方式);
        model.addAttribute("EXEC_TYPE_01", DictCodeList.行权状态_未行权.getValue());
        return "/valuation/component/element/ValBusiFinExercisePlan";
    }
    
    /**
     * 初始化行权计划列表
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping(value = "/initPlan")
    @ResponseBody
    public List<FinExercisePlan> initPlan() {
        List<FinExercisePlan> finExercisePlanList = new ArrayList<FinExercisePlan>();
        String finprodId = this.getParam("finprodId");
        if(StringUtil.isNotEmpty(finprodId)){
        if(!WorkFlowUtil.isNotFromToDoList()){
            String workFlowId = this.getParam("workflowId");
            String entityName = StringUtil.defaultString(this.getParam("entityName"),
                ValBusiFinExercisePlanService.DEFAULT_ENTITY_NAME);
            //根据工作流与留痕处理提供的共通方法获取业务实体
            ValBusiFinExercisePlanDto busiFinExercisePlanDto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(workFlowId, entityName, ValBusiFinExercisePlanDto.class);
            if( null != busiFinExercisePlanDto && busiFinExercisePlanDto.getFinExercisePlanList() != null){
                finExercisePlanList = busiFinExercisePlanDto.getFinExercisePlanList();
            }
        }else{
            //查询行权计划
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("finprodId", finprodId);
            
            // 一体化对应etliu 
//            param.put("psT03", DictCodeList.交易方向_行权.getValue());
            param.put("psT03", DictCodeList.交易方向_行权_回售.getValue());
            
            param.put("cashType2", DictCodeListExt1.现金流二级类型_本金.getValue());
            param.put("execType01", DictCodeList.行权状态_未行权.getValue());
            param.put("execType02", DictCodeList.行权状态_已行权.getValue());
            finExercisePlanList = busiFinExercisePlanService.getfinExercisePlanByProductId(param);
        }
        }
        return finExercisePlanList;
    }

    // 增加实际行权日 add by renping 20190927 start
    /**
     * 根据行权日取得实际行权日
     *
     * @param dto 参数
     * @return 实际行权日
     * @see
     */
    @ResponseBody
    @RequestMapping("/getActExerciseDate")
    public Map<String, String> getActExerciseDate(@RequestBody Map<String,Object> param) {
        // 实际行权日
        return busiFinExercisePlanService.getActExerciseDate(param);
    }
    // 增加实际行权日 add by renping 20190927 end
}
