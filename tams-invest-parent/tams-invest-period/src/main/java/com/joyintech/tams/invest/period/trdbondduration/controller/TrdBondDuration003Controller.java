package com.joyintech.tams.invest.period.trdbondduration.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdbondduration.dto.SecPositionExerciseInfo003Dto;
import com.joyintech.tams.invest.period.trdbondduration.dto.TrdBondDuration002ProcessDto;
import com.joyintech.tams.invest.period.trdbondduration.dto.TrdBondDuration003ProcessDto;
import com.joyintech.tams.invest.period.trdbondduration.service.TrdBondDuration002Service;
import com.joyintech.tams.invest.period.trdbondduration.service.TrdBondDuration003Service;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.entity.WorkflowEntity;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
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
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

import static com.joyintech.tams.invest.period.common.service.PeriodCommonService.change现金流类型To交易方向;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdbondduration.controller
 * @Description: 债券存续期 多组合行权回售
 * @date Date : 2020年1月17日
 */
@Controller
@PageId("TrdBondDuration003")
@RequestMapping("/invest/period/trdbondduration/TrdBondDuration003")
public class TrdBondDuration003Controller extends BaseController {

    @Autowired
    private BaseGetBusinessId baseGetBusinessIdImpl;

    @Autowired
    private TrdBondDuration003Service trdBondDuration003Service;

    @Autowired
    private PeriodCommonService periodCommonService;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.是否);
        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        TrdBondDuration003ProcessDto entity = null;
        Map<String, Object> params = RequestUtil.getPageParam();
        String statusValid = (String)params.get("statusValid");
        params.put("statusValid", statusValid);
        if ((!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(RequestUtil.getParam("workflowId")))
            || DictCodeListExt1.画面数据状态_流程中.getValue().equals(statusValid)) {
            String workflowId = RequestUtil.getParam("workflowId");
            if (StringUtil.isNotEmpty(workflowId)) {
                entity = trdBondDuration003Service.getBussinessEntityByWorkflowId(workflowId);
                model.addAttribute("workflowId", workflowId);
            }
        }
        else {
            entity = trdBondDuration003Service.getTrdBondDuration003ProcessDto(params);
        }
        if (entity != null) {
            PeriodCommonService.setEntityBussOrderToModel(model, entity.getBussTrdTransferOrder());
        }
        model.addAttribute(GlobalConstant.ENTITY, entity);
        model.addAttribute("GEN_PAY_ORDERS_AUTO", CacheUtil.getSysParam("GEN_PAY_ORDERS_AUTO"));
        return super.init(model);
    }

    /**
     * 画面点击保存按钮时check
     *
     * @param dto 实体类
     * @param result
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) TrdBondDuration003ProcessDto dto,
                            BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    @ResponseBody
    @RequestMapping("findSecPosition")
    public List<SecPositionExerciseInfo003Dto> findSecPosition(@RequestBody Map<String, Object> params) {
        return trdBondDuration003Service.findSecPosition(params);
    }

    /**
     * 保存
     *
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) TrdBondDuration003ProcessDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<TrdBondDuration003ProcessDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdBondDuration003ProcessDto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_资产存续期交易);
        dto.setBizCode(baseGetBusinessIdImpl.buildBussinessCode(parameter));
        params.setBusinessCode(dto.getBizCode());
        params.setBusinessType(DictCodeList.共通业务类型_债券类资产存续期.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(trdBondDuration003Service.生成业务说明());
        processParameter.setWorkFlowParameter(params);
        if (BussinessConstant.SUBMIT.equals(dto.getOperateType())) {
            trdBondDuration003Service.genFinProductTradeDto(dto);
        }
        String msg = trdBondDuration003Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

}
