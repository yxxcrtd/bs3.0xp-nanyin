package com.joyintech.tams.valuation.trdassetlossmanage.trdassetloss.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.trdassetlossmanage.trdassetloss.dto.TrdAssetLoss001Dto;
import com.joyintech.tams.valuation.trdassetlossmanage.trdassetloss.service.TrdAssetLossService;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 估值核算-财务管理-资产减值管理--撤销
 *
 * @author lby
 * @version 1.0 2020年10月15日
 * @see TrdAssetLoss004Controller
 * @since 1.0
 */
@Controller
@PageId("TrdAssetLoss004")
@RequestMapping("/valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss004")
public class TrdAssetLoss004Controller extends BaseController {

    @Autowired
    private TrdAssetLossService trdAssetLossService;
    /**
     * 业务编号组件
     */
    @Autowired(required = false)
    private InterfCommonService interfCommonService;

    private static final String CHECK_STATUS_STR_TG = "通过";
    private static final String CHECK_STATUS_STR_BTG = "不通过";
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        Date sysDate = DateUtil.platDateFromCache();
        model.addAttribute("sysDate", DateUtil.format(sysDate));
        // 导入
        String tempList = getParam("tempList");
        String tempEclList = getParam("tempEclList");
        TrdAssetLoss001Dto dto = new TrdAssetLoss001Dto();
        if (StringUtil.isNotEmpty(tempList)){
            dto = trdAssetLossService.getTrdAssetLossListData(tempList);
        }
        if (StringUtil.isNotEmpty(tempEclList)) {
            dto = trdAssetLossService.getTrdAssetLossEclListData(tempEclList);
        }
        model.addAttribute(GlobalConstant.ENTITY, dto);
        model.addAttribute("trdAssetLossList", StringUtil.isEmptyList(
                dto.getSelectedItem()) ? null : JsonUtils.object2Json(dto.getSelectedItem()));
        model.addAttribute("shortSellingSupported", CacheUtil.getSysParam("SHORT_SELLING_SUPPORTED"));
        return super.init(model);
    }

    /**
     * 导入excel数据时check
     *
     * @param
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("checkImportData")
    public AjaxResult checkImportData(String fileName) {
        return trdAssetLossService.checkImportData(fileName);
    }

    /**
     * 中债ECL模板导入excel数据时check
     *
     * @param
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("checkImportDataEcl")
    public AjaxResult checkImportDataEcl(String fileName) {
        return trdAssetLossService.checkImportDataEcl(fileName);
    }

    /**
     * 保存按钮点击时的check
     *
     * @param dtoList dto
     * @param result  BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody List<TrdAssetLoss001Dto> dtoList, BindingResult result) {
        List<ValidMessage> messages = new ArrayList<>();
        int rownum = 0;
        WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
        wflValidInputParameter.setPageId("TrdAssetLoss002");
        for (int i = 0; i < dtoList.size(); i++) {
            StringBuffer message = new StringBuffer();
            //真实行数
            rownum = i + 1;
            TrdAssetLoss001Dto dto = dtoList.get(i);
            if (StringUtil.equals(CHECK_STATUS_STR_BTG, dto.getCheckStatusStr())) {
                message.append("检查结果：不通过 " + "行数：" + rownum);
                messages.add(new ValidMessage(message.toString()));
            }
            wflValidInputParameter.setBusinessDataPk1(dto.getFinprodId());
            if (WorkFlowUtil.isNotFromToDoList() && mstWorkflowCommonService.isInWflProcess(wflValidInputParameter)) {
                message.append(this.getMessage("BUSS_COM_E_0041") + "资产：" + dto.getFinprodId());
                messages.add(new ValidMessage(message.toString()));
            }
        }
        if (messages.size() > 0) {
            AjaxResult errorRs = new AjaxResult();
            errorRs.setErrorList(messages);
            return errorRs;
        }
        return commonCheck(result, dtoList.get(0).getOperateType());
    }

    /**
     * 保存/提交
     *
     * @param allDto
     * @param br     BindingResult
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody TrdAssetLoss001Dto allDto, BindingResult br)
            throws Exception {
        List<TrdAssetLoss001Dto> dtoList = allDto.getSelectedItem();
        AjaxResult ajaxResult = this.check(dtoList, br);
        if (!StringUtil.isEmptyList(ajaxResult.getErrorList())) {
            return ajaxResult;
        }
        for (TrdAssetLoss001Dto dto : dtoList) {
            if (StringUtil.isEmpty(dto.getTradeId())) {
                String tradeId = interfCommonService != null ? interfCommonService.getTradeid("TEDLOSS", dto.getValDate(), "A") : IDGenerator.generate();
                dto.setTradeId(tradeId);
            }
        }

        ProcessParameter<TrdAssetLoss001Dto> processParameter = new ProcessParameter<TrdAssetLoss001Dto>();
        WorkFlowParameter<TrdAssetLoss001Dto> params = new WorkFlowParameter<TrdAssetLoss001Dto>();
        params.setPageId("TrdAssetLoss002");
        params.setElementId(dtoList.get(0).getElementId());
        params.setBusinessCode(TrdAssetLoss001Dto::getFinprodId);
        params.setBusinessType(DictCodeListExt3.共通业务类型_资产减值管理.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
                entity -> "交易代码：" + entity.getTradeId() + "业务类型：" + DictCodeListExt3.共通业务类型_资产减值管理.getCodeName() + "交易日：" + DateUtil.format(entity.getValDate()));

        processParameter.setWorkFlowParameter(params);
        String msg = trdAssetLossService.start(processParameter,
                dtoList.toArray(new TrdAssetLoss001Dto[dtoList.size()]));
        return new AjaxResult(msg);
    }
}
