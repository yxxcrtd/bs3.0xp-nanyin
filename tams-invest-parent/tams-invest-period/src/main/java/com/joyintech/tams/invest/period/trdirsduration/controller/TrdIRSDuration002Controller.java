package com.joyintech.tams.invest.period.trdirsduration.controller;

import java.util.Map;

import com.joyintech.framework.util.CacheUtil;
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
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdirsduration.dto.TrdIRSDuration002ProcessDto;
import com.joyintech.tams.invest.period.trdirsduration.service.TrdIRSDuration002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 利率互换存续期
 * 
 * @author : renping
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdirsduration.controller
 * @Description: 利率互换存续期 确认
 * @date Date : 2019年08月10日
 */
@Controller
@PageId("TrdIRSDuration002")
@RequestMapping("/invest/period/trdirsduration/TrdIRSDuration002")
public class TrdIRSDuration002Controller extends BaseController {

    @Autowired
    private TrdIRSDuration002Service trdIRSDuration002Service;

    @Autowired
    private BaseGetBusinessId baseGetBusinessIdImpl;

    @Autowired
    private PeriodCommonService periodCommonService;

    @Autowired
    private CommonBusinessService commonBusinessService;

    @Override
    public String init(Model model) {
        TrdIRSDuration002ProcessDto entity = null;
        Map<String, Object> params = RequestUtil.getPageParam();
        String statusValid = (String) params.get("statusValid");
        if (Boolean.valueOf(RequestUtil.getParam("isFromCheck"))) {
            if (DictCodeListExt1.交易流程状态_有效.getValue().equals(statusValid)) {
                statusValid = DictCodeListExt1.画面数据状态_有效.getValue();
            } else {
                statusValid = DictCodeListExt1.画面数据状态_流程中.getValue();
            }
        }
        params.put("statusValid", statusValid);
        if (!WorkFlowUtil.isNotFromToDoList()
                || DictCodeListExt1.画面数据状态_流程中.getValue().equals(statusValid)) {
            String workflowId = RequestUtil.getParam("workflowId");
            if (StringUtil.isNotEmpty(workflowId)) {
                entity = trdIRSDuration002Service.getBussinessEntityByWorkflowId(workflowId);
                model.addAttribute("entityList",
                    JsonUtils.object2Json(entity.getBussTrdTransferOrder()));
                if (entity != null) {
                    model.addAttribute("isTodo",
                            commonBusinessService.isBackCheckNode(entity.getBusiCode()));
                }
            }
        } else {
            entity = trdIRSDuration002Service.findEffectiveData(params);
            if (entity != null) {
                if (DictCodeListExt1.画面数据状态_待处理.getValue().equals(statusValid)) {
                    model.addAttribute("needInit", true);
                } else {
                    entity.setBussTrdTransferOrder(
                            periodCommonService.selectPayOrders(entity.getBusiCode()));
                }
            }
        }
        if (entity != null) {
            PeriodCommonService.setEntityBussOrderToModel(model, entity.getBussTrdTransferOrder());
        }
        model.addAttribute(GlobalConstant.ENTITY, entity);
        this.setSelectOptionToModel(model, DictCode.是否, DictCode.平安划款类型, DictCode.币种, DictCode.互换方式,
                DictCode.利率互换收付息类型);
        model.addAttribute("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        model.addAttribute("finprodType2", DictCodeListExt1.金融产品类型_投管用_利率互换.getValue());
        model.addAttribute("irsType_payFloat", DictCodeListExt2.互换方式_收取固定.getValue());
        model.addAttribute("ps_pay", DictCodeList.交易方向_支付.getValue());
        model.addAttribute("ps_rec", DictCodeList.交易方向_收取.getValue());
        model.addAttribute("formTodoList", !WorkFlowUtil.isNotFromToDoList());
        model.addAttribute("GEN_PAY_ORDERS_AUTO", CacheUtil.getSysParam("GEN_PAY_ORDERS_AUTO"));
        return super.init(model);
    }

    /**
     * 画面点击保存按钮时check
     *
     * @param dto
     *            实体类
     * @param result
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(
            @RequestBody @Validated({ ValidateGroupEdit.class }) TrdIRSDuration002ProcessDto dto,
            BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存
     *
     * @param dto
     *            dto
     * @param br
     *            br
     * @return 结果
     * @throws Exception
     *             异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(
            @RequestBody @Validated({ ValidateGroupEdit.class }) TrdIRSDuration002ProcessDto dto,
            BindingResult br) throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<TrdIRSDuration002ProcessDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdIRSDuration002ProcessDto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_资产存续期交易);
        dto.setBusiCode(baseGetBusinessIdImpl.buildBussinessCode(parameter));
        params.setBusinessCode(dto.getBusiCode());
        params.setBusinessType(DictCodeListExt2.共通业务类型_利率互换存续期交易.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(trdIRSDuration002Service.生成业务说明());
        processParameter.setWorkFlowParameter(params);
        if (BussinessConstant.SUBMIT.equals(dto.getOperateType())) {
            // 2.0
            dto.setBaseTotalTradeInfoDto(trdIRSDuration002Service.genFinProductTradeDto2(dto));
        }
        String msg = trdIRSDuration002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

}
