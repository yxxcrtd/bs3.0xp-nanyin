package com.joyintech.tams.invest.risk.fundgoal.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.ValidateGroupNew;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.fundgoal.dto.FundGoalDto;
import com.joyintech.tams.invest.risk.fundgoal.service.FundGoalService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname FundGoal002Controller
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/11/3 11:19 
 * @version 1.0 
 */

@Controller
@PageId("FundGoal002")
@RequestMapping("/invest/risk/fundgoal/FundGoal002")
public class FundGoal002Controller extends BaseController {

    @Autowired
    private FundGoalService fundGoalService;

    @Autowired
    private DateCalService dateCalService;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        String finprodId = getParam("finprodId");
        String goalDate = getParam("goalDate");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String operateType = getParam("operateType");
        Date sysDate = DateUtil.platDateFromCache();
        model.addAttribute("sysDate", DateUtil.format(sysDate));
        //流程代办
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            FundGoalDto  fundGoalDto = fundGoalService.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", fundGoalDto);
        }
        //不来自代办,修改页面
        else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(finprodId)){
            FundGoalDto fundGoalDto = new FundGoalDto();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("finprodId",finprodId);
            map.put("goalDate",goalDate);
            fundGoalDto = fundGoalService.selectByPrimaryKey(map);
            model.addAttribute("entity", fundGoalDto);
        }
        //新建页面
        else {
            FundGoalDto fundGoalDto = new FundGoalDto();
            fundGoalDto.setGoalDate(sysDate);
            model.addAttribute("entity", fundGoalDto);
        }
        return super.init(model);
    }


    /**
     *
     * @Description 新建check
     * @Param [dto, result]
     * @return com.joyintech.framework.ajax.AjaxResult
     * @version 1.0
     * @see
     **/
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) FundGoalDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody FundGoalDto dto, BindingResult br) throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        WorkFlowParameter<FundGoalDto> params = new WorkFlowParameter<FundGoalDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getFinprodId());
        params.setBusinessType(DictCodeListExt2.共通业务类型_基金投向维护.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
                entity -> SpringContextUtil.getTextValue("FundGoal001.finprodId")+"("+ entity.getFinprodId() +")"+SpringContextUtil.getTextValue("FundGoal002.goalDate")+"("+entity.getGoalDate().toString()+")"
        );
        //给参数变成ProcessParameter而非WorkFlowParameter
        ProcessParameter<FundGoalDto> parameter = new ProcessParameter<FundGoalDto>();
        parameter.setWorkFlowParameter(params);
        String msg = fundGoalService.start(parameter, dto);
        return new AjaxResult(msg);


    }



    @ResponseBody
    @RequestMapping("getNextPubDate")
    public Date getNextPubDate(@RequestBody Map<String,Object> map){
       return fundGoalService.getNextPubDate(map);
    }
}
