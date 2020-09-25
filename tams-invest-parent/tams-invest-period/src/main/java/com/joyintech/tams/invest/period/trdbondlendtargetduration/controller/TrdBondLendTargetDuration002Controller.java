package com.joyintech.tams.invest.period.trdbondlendtargetduration.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdbondlendtargetduration.dto.TrdBondLendTargetDuration002ProcessDto;
import com.joyintech.tams.invest.period.trdbondlendtargetduration.service.TrdBondLendTargetDuration002Service;
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

import java.util.Map;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdbondduration.controller
 * @Description: 债券存续期 确认
 * @date Date : 2018年09月21日 13:48
 */
@Controller
@PageId("TrdBondLendTargetDuration002")
@RequestMapping("/invest/period/trdbondlendtargetduration/TrdBondLendTargetDuration002")
public class TrdBondLendTargetDuration002Controller extends BaseController {

    @Autowired
    private TrdBondLendTargetDuration002Service trdBondLendTargetDuration002Service;

    @Autowired
    private BaseGetBusinessId baseGetBusinessIdImpl;

    @Autowired
    private PeriodCommonService periodCommonService;

    @Autowired
    private CommonBusinessService commonBusinessService;

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DictCode.资产来源);
        TrdBondLendTargetDuration002ProcessDto entity = null;
        Map<String, Object> params = RequestUtil.getPageParam();
        String statusValid = (String) params.get("statusValid");
        params.put("statusValid", statusValid);
        if ((!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(RequestUtil.getParam("workflowId")))
                || DictCodeListExt1.画面数据状态_流程中.getValue().equals(statusValid)) {
            String workflowId = RequestUtil.getParam("workflowId");
            if (StringUtil.isNotEmpty(workflowId)) {
                entity = trdBondLendTargetDuration002Service.getBussinessEntityByWorkflowId(workflowId);
                model.addAttribute("workflowId",workflowId);
                if(entity != null) {
                    model.addAttribute("isTodo", commonBusinessService.isBackCheckNode(entity.getTotleTradeId()));
                }
            }
        }
        else {
            entity = trdBondLendTargetDuration002Service.findEffectiveData(params);
            if (entity != null) {
                if (DictCodeListExt1.画面数据状态_待处理.getValue().equals(statusValid)) {
                    entity.setTradeAmt(entity.getTheoryAmt());
                }
                else {
                    entity.setBussTrdTransferOrder(periodCommonService.selectPayOrders(entity.getTotleTradeId()));
                }
            }
        }
        if (entity != null && entity.getBussTrdTransferOrder() != null && !entity.getBussTrdTransferOrder().isEmpty()) {
            model.addAttribute("entity_bussTrdTransferOrderDto", JsonUtils.object2Json(entity.getBussTrdTransferOrder().get(0)));
        }
        model.addAttribute(GlobalConstant.ENTITY, entity);
        this.setSelectOptionToModel(model, DictCode.是否,DictCode.是否,DictCode.平安划款类型,DictCode.币种,DictCode.投资目的);
        model.addAttribute("CASH_CLASS_TYPE01", DictCodeList.现金流类型_中间收息.getValue());
        model.addAttribute("CASH_CLASS_TYPE02", DictCodeList.现金流类型_中间还本.getValue());
        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        model.addAttribute("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        model.addAttribute("formTodoList", !WorkFlowUtil.isNotFromToDoList());
        model.addAttribute("sysDate", DateUtil.getSysDate());
        model.addAttribute("in_out", '出');
        model.addAttribute("ps", '收');
        model.addAttribute("tradeDateOther", "资金到账日");
        model.addAttribute("GEN_PAY_ORDERS_AUTO", CacheUtil.getSysParam("GEN_PAY_ORDERS_AUTO"));
        if (entity != null) {
            if (DictCodeListExt3.平安业务类型_融入债券付息还本_付.getValue().equals(entity.getTradeType())) {
                model.addAttribute("in_out", '入');
                model.addAttribute("ps", '付');
                model.addAttribute("tradeDateOther", "实际支付日期");
            }
        }
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) TrdBondLendTargetDuration002ProcessDto dto,
                            BindingResult result) {
        return commonCheck(result, dto.getOperateType());
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
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) TrdBondLendTargetDuration002ProcessDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<TrdBondLendTargetDuration002ProcessDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdBondLendTargetDuration002ProcessDto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_资产存续期交易);
        dto.setTotleTradeId(baseGetBusinessIdImpl.buildBussinessCode(parameter));
        params.setBusinessCode(dto.getTotleTradeId());
        params.setBusinessType(DictCodeList.共通业务类型_债券类资产存续期.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(trdBondLendTargetDuration002Service.生成业务说明());
        processParameter.setWorkFlowParameter(params);
        if (BussinessConstant.SUBMIT.equals(dto.getOperateType())) {
            trdBondLendTargetDuration002Service.genFinProductTradeDto(dto);
        }
        String msg = trdBondLendTargetDuration002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

}
