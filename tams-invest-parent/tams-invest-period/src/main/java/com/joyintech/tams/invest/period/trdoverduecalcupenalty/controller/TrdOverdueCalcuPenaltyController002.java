package com.joyintech.tams.invest.period.trdoverduecalcupenalty.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.period.trdoverduecalcupenalty.dto.TrdOverdueCalcuPenaltyDto;
import com.joyintech.tams.invest.period.trdoverduecalcupenalty.service.TrdOverdueCalcuPenaltyService;
import com.joyintech.tams.invest.period.trdsecoverdueduration.dto.TrdSecOverdueDuration001Dto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @ClassName TrdOverdueCalcuPenaltyController002
 * @Description: TODO
 * @Author wangxuelei
 * @Date 2019/12/10 0010
 * @Version V1.0
 **/
@Controller
@PageId("TrdOverdueCalcuPenalty002")
@RequestMapping("/invest/period/trdoverduecalcupenalty/TrdOverdueCalcuPenalty002")
public class TrdOverdueCalcuPenaltyController002 extends BaseController {

    @Autowired
    private TrdOverdueCalcuPenaltyService trdOverdueCalcuPenaltyService;
    /**
     * 业务编号共通
     */
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;
    //画面初始化
    @Override
    public String init(Model model) {
        Long bjBranch = null;
        Long liBranch = null;
        String penaltyBaseType = null;
        String  withOutBranchs =null;
        Map<String,Object> param = RequestUtil.getPageParam();
        if(param.containsKey("penaltyBaseType")){
             penaltyBaseType = param.get("penaltyBaseType").toString();
        }
        super.setSelectOptionToModel(model, DictCode.罚息计提基数, DictCode.是否);
        String statusValid = (String)param.get("statusValid");
        TrdOverdueCalcuPenaltyDto dto = null;
        TrdSecOverdueDuration001Dto withOutBranch = null;
        String sysDate = DateUtil.getSysDate();
        model.addAttribute("sysDate",sysDate);
        if(StringUtil.equals(DictCodeListExt1.画面数据状态_待处理.getValue(),statusValid )
                || (((StringUtil.equals(DictCodeListExt1.画面数据状态_流程中.getValue(),statusValid )
                ||!WorkFlowUtil.isNotFromToDoList())&& StringUtil.isEmpty((String)param.get(GlobalConstant.WORKFLOW_ID))))) {


        }   else if(StringUtil.equals(DictCodeListExt1.画面数据状态_流程中.getValue(),statusValid )||!WorkFlowUtil.isNotFromToDoList()) {
            String workflowId = (String)param.get(GlobalConstant.WORKFLOW_ID);
            if(StringUtil.isNotEmpty(workflowId)){
                model.addAttribute("workflowId",workflowId);
                dto = trdOverdueCalcuPenaltyService.getBussinessEntityByWorkflowId(workflowId);
            }
        }else if(StringUtil.isNotEmpty(statusValid)){//有效

        }  else{//新建页面
            dto = trdOverdueCalcuPenaltyService.getTrdOverdueCalcuPenaltyDto(bjBranch, liBranch, penaltyBaseType, withOutBranchs, param, withOutBranch);
        }
        model.addAttribute(GlobalConstant.ENTITY,dto);
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) TrdOverdueCalcuPenaltyDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) TrdOverdueCalcuPenaltyDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<TrdOverdueCalcuPenaltyDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdOverdueCalcuPenaltyDto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_逾期计息要素维护));
        if (baseGetBusinessId != null) {
            BussinessCodeParameter codeParameter = new BussinessCodeParameter();
            codeParameter.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_逾期计息要素维护));
            String code = baseGetBusinessId.buildBussinessCode(parameter);
            dto.setTotleTradeId(code);
            params.setBusinessCode(code);
        }
        params.setBusinessType(DictCodeListExt3.共通业务类型_逾期计息要素维护.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment(
                "资产类型",//资产名称
                GlobalConstant.OPEN_PARENTHESIS + entity.getFinprodTypeName()
                        + GlobalConstant.CLOSE_PARENTHESIS,
               "资产代码",
                GlobalConstant.OPEN_PARENTHESIS +entity.getFinprodId()+ GlobalConstant.CLOSE_PARENTHESIS,
                "资产简称",
                GlobalConstant.OPEN_PARENTHESIS+ entity.getFinprodName()+ GlobalConstant.CLOSE_PARENTHESIS,
                "计息区间",
                GlobalConstant.OPEN_PARENTHESIS+entity.getVdate()+"-"+entity.getMdate()+ GlobalConstant.CLOSE_PARENTHESIS

        ));
        processParameter.setWorkFlowParameter(params);
        String msg = trdOverdueCalcuPenaltyService.start(processParameter, dto);
        return new AjaxResult(msg);
    }

}
