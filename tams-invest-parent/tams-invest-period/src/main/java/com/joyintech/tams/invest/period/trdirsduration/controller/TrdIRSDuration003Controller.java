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
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdirsduration.dto.TrdIRSDuration003ProcessDto;
import com.joyintech.tams.invest.period.trdirsduration.service.TrdIRSDuration003Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 利率互换提前终止
 * 
 * @author : renping
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdirsduration.controller
 * @Description: 利率互换提前终止 确认
 * @date Date : 2019年08月10日
 */
@Controller
@PageId("TrdIRSDuration003")
@RequestMapping("/invest/period/trdirsduration/TrdIRSDuration003")
public class TrdIRSDuration003Controller extends BaseController {

    @Autowired
    private TrdIRSDuration003Service trdIRSDuration003Service;

    @Autowired
    private BaseGetBusinessId baseGetBusinessIdImpl;

    @Autowired
    private PeriodCommonService periodCommonService;

    @Autowired
    private IPlatformDate iPlatformDate;

    @Override
    public String init(Model model) {
        TrdIRSDuration003ProcessDto entity = null;
        Map<String, Object> params = RequestUtil.getPageParam();
        if (!WorkFlowUtil.isNotFromToDoList()) {
            String workflowId = RequestUtil.getParam("workflowId");
            if (StringUtil.isNotEmpty(workflowId)) {
                entity = trdIRSDuration003Service.getBussinessEntityByWorkflowId(workflowId);
                model.addAttribute("entityList",
                    JsonUtils.object2Json(entity.getBussTrdTransferOrder()));
            }
        }
        else if (StringUtil.isNotEmpty(params.get("busiCode"))) {
            entity = trdIRSDuration003Service.findEffectiveData(params);
            entity.setBussTrdTransferOrder(
                periodCommonService.selectPayOrders(entity.getBusiCode()));
        }
        else {
            entity = trdIRSDuration003Service.findIrsDealInfo((String)params.get("finprodId"),
                iPlatformDate.getSysDate());
        }
        if (entity != null) {
            PeriodCommonService.setEntityBussOrderToModel(model, entity.getBussTrdTransferOrder());
            if (entity.getBeforeEndDate() == null) {
                entity.setBeforeEndDate(iPlatformDate.getSysDate());
            }
        }
        model.addAttribute(GlobalConstant.ENTITY, entity);
        this.setSelectOptionToModel(model, DictCode.是否, DictCode.平安划款类型, DictCode.币种,
            DictCode.互换方式, DictCode.交易方向);
        model.addAttribute("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        model.addAttribute("finprodType2", DictCodeListExt1.金融产品类型_投管用_利率互换.getValue());
        model.addAttribute("formTodoList", !WorkFlowUtil.isNotFromToDoList());
        model.addAttribute("GEN_PAY_ORDERS_AUTO", CacheUtil.getSysParam("GEN_PAY_ORDERS_AUTO"));
        return super.init(model);
    }

    /**
     * 根据金融产品代码查询利率互换交易信息
     * 
     * @param pageParam 参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findIrsDealInfo")
    public TrdIRSDuration003ProcessDto findIrsDealInfo(@RequestBody TrdIRSDuration003ProcessDto dto) {
        return trdIRSDuration003Service.findIrsDealInfo(dto.getFinprodId(),
                iPlatformDate.getSysDate());
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
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) TrdIRSDuration003ProcessDto dto, BindingResult result) {
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
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) TrdIRSDuration003ProcessDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<TrdIRSDuration003ProcessDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdIRSDuration003ProcessDto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_资产存续期交易);
        dto.setBusiCode(baseGetBusinessIdImpl.buildBussinessCode(parameter));
        params.setBusinessCode(dto.getBusiCode());
        params.setBusinessType(DictCodeListExt2.共通业务类型_利率互换存续期交易.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(trdIRSDuration003Service.生成业务说明());
        processParameter.setWorkFlowParameter(params);
        if (BussinessConstant.SUBMIT.equals(dto.getOperateType())) {
            // 2.0
            dto.setBaseTotalTradeInfoDto(trdIRSDuration003Service.genFinProductTradeDto2(dto));
        }
        String msg = trdIRSDuration003Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

}
