package com.joyintech.tams.invest.period.trdbuyoutduration.controller;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.trdrepoduration.RepoInfo;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdbuyoutduration.dto.TrdRepoBuyoutDuration002Dto;
import com.joyintech.tams.invest.period.trdbuyoutduration.service.TrdRepoBuyoutDuration002Service;
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

/**
 * @author : 强成西
 * @version V1.0 回购存续期 确认
 * @date Date : 2018年09月21日 13:48
 */
@Controller
@PageId("TrdRepoBuyoutDuration002")
@RequestMapping("/invest/period/trdbuyoutduration/TrdRepoBuyoutDuration002")
public class TrdRepoBuyoutDuration002Controller extends BaseController {

    /**
     * TrdRepoBuyoutDuration002Service
     */
    @Autowired
    private TrdRepoBuyoutDuration002Service trdRepoBuyoutDuration002Service;

    /**
     * 编号生成
     */
    @Autowired
    private BaseGetBusinessId baseGetBusinessIdImpl;

    @Autowired
    private PeriodCommonService periodCommonService;

    /**
     * 平台日期
     */
    @Autowired
    private IPlatformDate platformDateImpl;

    /**
     * 需要初始化的下拉菜单
     */
    private static final IDictCode[] DICT_CODES = new DictCode[]{DictCode.币种};

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DICT_CODES);
        String isTodoList = RequestUtil.getParam("isTodoList");
        TrdRepoBuyoutDuration002Dto entity;
        String workflowId = RequestUtil.getParam("workflowId");
        if (StringUtil.isNotEmpty(isTodoList) && StringUtil.equals(isTodoList, "true") || StringUtil.isNotEmpty(workflowId)) {
            entity = trdRepoBuyoutDuration002Service.getBussinessEntityByWorkflowId(workflowId);
        } else {
            entity = trdRepoBuyoutDuration002Service.findEffectiveData(RequestUtil.getPageParam());
            String statusValid = RequestUtil.getParam("statusValid");
            if (entity != null) {
                entity.setStatusValid(statusValid);
                if (DictCodeListExt1.画面数据状态_待处理.getValue().equals(statusValid) && entity != null) {
                    entity.setSettleDate(platformDateImpl.getSysDateStr());
                    entity.setTradeAmt(entity.getTheoryAmt());
                    entity.setFpriceAmt(entity.getTheoryAmt());
                } else {
                    entity.setBussTrdTransferOrder(periodCommonService.selectPayOrders(entity.getBusiCode()));
                }
            }
        }
        TrdProductDeal deal = TrdRepoBuyoutDuration002Service.getTrdProductDeal(entity);
        deal.setTradeId(StringUtil.EMPTY);
        model.addAttribute(GlobalConstant.ENTITY, entity);
        model.addAttribute("deal", JsonUtils.object2Json(deal));
        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        model.addAttribute("Collect_Type_01",DictCodeListExt2.收取方式_回款前扣除.getValue());
        model.addAttribute("GEN_PAY_ORDERS_AUTO", CacheUtil.getSysParam("GEN_PAY_ORDERS_AUTO"));
        if (entity != null && entity.getBussTrdTransferOrder() != null && !entity.getBussTrdTransferOrder().isEmpty()) {
            model.addAttribute("entity_bussTrdTransferOrderDto", JsonUtils.object2Json(entity.getBussTrdTransferOrder().get(0)));
        }
        return super.init(model);
    }

    /**
     * 检索债券质押信息
     *
     * @return 债券质押信息
     */
    @ResponseBody
    @RequestMapping("findRepoInfo")
    public Page<RepoInfo> findRepoInfo() {
        return trdRepoBuyoutDuration002Service.findRepoInfo();
    }

    /**
     * 画面点击保存按钮时check
     *
     * @param dto    实体类
     * @param result
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated TrdRepoBuyoutDuration002Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存
     *
     * @param dto dto
     * @param br  br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) TrdRepoBuyoutDuration002Dto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<TrdRepoBuyoutDuration002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdRepoBuyoutDuration002Dto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        BussinessCodeParameter parameter = new BussinessCodeParameter();
		
        // 【UAT0324】买断式回购到期，待办事项审批界面，业务编号和业务说明显示错误
//        parameter.setCommonBusinessType(DictCodeList.共通业务类型_买断式回购存续期);
        if (StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue())) {
            parameter.setCommonBusinessType(DictCodeList.共通业务类型_正回购到期);
        } else {
            parameter.setCommonBusinessType(DictCodeList.共通业务类型_逆回购到期);
        }

        dto.setBusiCode(baseGetBusinessIdImpl.buildBussinessCode(parameter));
        params.setBusinessCode(dto.getBusiCode());
        params.setBusinessType(DictCodeList.共通业务类型_买断式回购存续期.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(
                entity -> WorkFlowUtil.concatComment(entity.getBusiCode(), DictCodeList.共通业务类型_买断式回购存续期.getCodeName()));
        processParameter.setWorkFlowParameter(params);
        String msg = trdRepoBuyoutDuration002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

}
