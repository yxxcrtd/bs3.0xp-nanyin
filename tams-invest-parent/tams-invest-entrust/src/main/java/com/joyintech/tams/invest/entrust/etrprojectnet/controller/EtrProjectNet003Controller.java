package com.joyintech.tams.invest.entrust.etrprojectnet.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.entrust.etrplanassetn.controller.EtrPlanAssetN003Controller;
import com.joyintech.tams.invest.entrust.etrprojectnet.dto.EtrProjectNetDto;
import com.joyintech.tams.invest.entrust.etrprojectnet.service.EtrProjectNetService;
import com.joyintech.tools.util.ValidationUtil;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
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
 * 权益类资产
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年12月30日
 * @see EtrPlanAssetN003Controller
 * @since 1.0
 */
@Controller
@PageId("EtrProjectNet003")
@RequestMapping("/invest/entrust/etrprojectnet/EtrProjectNet003")
public class EtrProjectNet003Controller extends BaseController {
    /**
     * 业务编号组件
     */
    @Autowired(required=false)
    private BaseGetBusinessId bussGetBussinessIdService;
    /**
     * 计划类净值委托service
     */
    @Autowired
    private EtrProjectNetService etrProjectNetService;

    /**
     * 初始化新建界面
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.计划类资产一级分类);
        this.setSelectOptionToModel(model, DictCode.计划类资产二级分类);
        this.setSelectOptionToModel(model, DictCode.行内资产二级分类,
                DictCode.行内资产三级分类,
                DictCode.行内资产四级分类);
        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.交易方向);
        Date sysDate = DateUtil.platDateFromCache();
        model.addAttribute("sysDate",DateUtil.format(sysDate));
        model.addAttribute("T04", DictCodeList.交易方向_申购.getValue());
        model.addAttribute("T06", DictCodeList.交易方向_赎回.getValue());
        model.addAttribute("T02", DictCodeList.交易方向_卖出.getValue());
        model.addAttribute("T01", DictCodeList.交易方向_买入.getValue());
        String operateType = getParam("operateType");
        //导入
        if (StringUtil.equals(operateType,"import")) {
            String tempList = getParam("tempList");
            List<EtrProjectNetDto> entrustListData= etrProjectNetService.getEntrustNListData(tempList);
            model.addAttribute("etrPlanAssetNDtoList", StringUtil.isEmptyList(entrustListData) ? null : JsonUtils.object2Json(entrustListData));
        }
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_委托监控.getValue());
        model.addAttribute("fintype", DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return super.init(model);
    }


    /**
     * 保存按钮点击时的check
     *
     * @param allDto EtrPlanAssetNDto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody EtrProjectNetDto allDto, BindingResult result) {
        List<ValidMessage> messages = new ArrayList<>();
        StringBuffer confirmMessage = new StringBuffer();
        List<EtrProjectNetDto> dtoList = allDto.getSelectedItem();
        for (int i = 0; i < dtoList.size(); i++ ) {
            EtrProjectNetDto dto = dtoList.get(i);
            ValidationUtil.ValidResult itemResult = ValidationUtil.validateBean(dto,
                    ValidateGroupEdit.class);

            // 可用现金软校验
            if (StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_CASH_SWITCH),
                    DictCodeListExt1.是或否_是.getValue())
                    && (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())
                    ||StringUtil.equals(dto.getPs(),DictCodeList.交易方向_申购.getValue()))
                    && dto.getProAvailableCash() != null
                    && dto.getEntrustAmt().compareTo(dto.getProAvailableCash()) == 1) {
                if (StringUtil.isNotBlank(confirmMessage)) {
                    confirmMessage.append("<br />");
                }
                confirmMessage.append(getMessage("ETR_E_0025", String.valueOf(i + 1)));
                confirmMessage.append("<br />");
                confirmMessage.append(getMessage("TRD_C_0002"));
            }
            // 可用持仓软校验
            else if (StringUtil.equals(
                    CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_POS_SWITCH),
                    DictCodeListExt1.是或否_是.getValue())
                    && (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())
                    || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回.getValue()))
                    && dto.getAssetAvailablePosition() != null
                    && dto.getEntrustNum().compareTo(dto.getAssetAvailablePosition()) > 0) {
                if (StringUtil.isNotBlank(confirmMessage)) {
                    confirmMessage.append("<br />");
                }
                confirmMessage.append(getMessage("ETR_E_0025", String.valueOf(i + 1)));
                confirmMessage.append("<br />");
                confirmMessage.append(getMessage("ETR_X_019"));
            }

            if (!StringUtil.isEmptyList(itemResult.getAllErrors())) {
                StringBuffer message = new StringBuffer();
                message.append(getMessage("ETR_E_0025", String.valueOf(i + 1)));
                for (ValidationUtil.ErrorMessage errorMessage : itemResult.getAllErrors()) {
                    message.append("<br />");
                    message.append(errorMessage.getMessage());
                }
                messages.add(new ValidMessage(message.toString()));
            }

        }
        if (messages.size() > 0) {
            AjaxResult errorRs = new AjaxResult();
            errorRs.setErrorList(messages);
            return errorRs;
        }
        if (StringUtil.isNotEmpty(confirmMessage.toString())) {
            AjaxResult wr = new AjaxResult();
            wr.setData(confirmMessage.toString());
            return wr;
        }
        return commonCheck(result, dtoList.get(0).getOperateType());
    }

    /**
     * 导入excel数据时check
     *
     * @param fileName fileName
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("checkImportData")
    public AjaxResult checkImportData(String fileName) {
        return etrProjectNetService.checkImportData(fileName);
    }

    /**
     * 保存/提交
     *
     * @param allDto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody EtrProjectNetDto allDto, BindingResult br)
            throws Exception {
        List<EtrProjectNetDto> dtoList = allDto.getSelectedItem();
        AjaxResult ajaxResult = this.check(allDto, br);
        if (!StringUtil.isEmptyList(ajaxResult.getErrorList())) {
            return ajaxResult;
        }
        for(EtrProjectNetDto dto: dtoList) {
            if (!StringUtil.isNotEmpty(dto.getEntrustId())) {
                BussinessCodeParameter param = new BussinessCodeParameter();
                param.setCommonBusinessType(DictCodeListExt3.共通业务类型_权益类资产委托);
                String code = bussGetBussinessIdService != null ? bussGetBussinessIdService.buildBussinessCode(param) : IDGenerator.generate();
                dto.setEntrustId(code);
            }
            //走002界面流程
        }
//        // ----------add by yushilong 20200721 预占功能 start----------
//        if (StringUtil.equals(BussinessConstant.SUBMIT, allDto.getOperateType())) {
//            AjaxResult ar = EntrustRdsCommonAop.doListPreOccupy(allDto.getSelectedItem(),
//                    DictCodeListExt3.平安资产类型_权益类资产.getValue(),
//                    BussinessDictCodeListRds.接口系统ID_预占批量接口.getValue(),
//                    false, null);
//            if (StringUtil.isNotEmpty(ar)) {
//                return ar;
//            }
//        }
//        // ----------add by yushilong 20200721 预占功能 end----------
//        // add by zhangrui1 批量提交设置风控指标明细 start
//        EntrustRdsCommonAop.riskListToChildren(allDto.getRiskTrialRetDto(), dtoList,
//                (dto, list) -> dto.setRiskTrialRetDto(list));
        // add by zhangrui1 批量提交设置风控指标明细 end
        ProcessParameter<EtrProjectNetDto> processParameter = new ProcessParameter<EtrProjectNetDto>();
        WorkFlowParameter<EtrProjectNetDto> params = new WorkFlowParameter<EtrProjectNetDto>();
        params.setPageId("EtrProjectNet002");
        params.setElementId(dtoList.get(0).getElementId());
        params.setBusinessCode(EtrProjectNetDto::getEntrustId);
        params.setBusinessType(DictCodeListExt3.共通业务类型_权益类资产委托.getValue());
        // 设置流程业务说明, entity即传入的dto
        /*if((StringUtil.equals(dtoList.get(0).getPs(),DictCodeList.交易方向_申购.getValue())||StringUtil.equals(dtoList.get(0).getPs(),DictCodeList.交易方向_买入.getValue()))
        &&dtoList.get(0).getEntrustAmt() != null){
            params.setComment(
                    entity -> this.getMessage("EtrProjectNet001") + "(" + entity.getPortfolioId() + ")"
                            + "-" + this.getMessage("EtrPlanAssetN001.ps")+"("+SysDataUtil.getNameFromDataDict(DictCode.交易方向, entity.getPs())+")" + "-" + "("
                            + this.getMessage("EtrPlanAssetN001.vdate") + ":" + DateUtil.format(entity.getVdate())
                            + ")-(" + this.getMessage("EtrPlanAssetN001.mdate") + ":"
                            + DateUtil.format(entity.getMdate()) + ")-"
                            + this.getMessage("EtrPlanAssetN001.entrustAmt") + ":"
                            + entity.getEntrustAmt());
        }else if((StringUtil.equals(dtoList.get(0).getPs(),DictCodeList.交易方向_赎回.getValue())||StringUtil.equals(dtoList.get(0).getPs(),DictCodeList.交易方向_卖出.getValue()))
        &&dtoList.get(0).getEntrustNum() != null){
            params.setComment(
                    entity -> this.getMessage("EtrProjectNet001") + "(" + entity.getPortfolioId() + ")"
                            + "-" + this.getMessage("EtrPlanAssetN001.ps")+"("+ SysDataUtil.getNameFromDataDict(DictCode.交易方向, entity.getPs())+")" + "-" + "("
                            + this.getMessage("EtrPlanAssetN001.vdate") + ":" + DateUtil.format(entity.getVdate())
                            + ")-(" + this.getMessage("EtrPlanAssetN001.mdate") + ":"
                            + DateUtil.format(entity.getMdate()) + ")-"
                            + this.getMessage("EtrPlanAssetN002.entrustNum") + ":"
                            + entity.getEntrustNum());
        }*/
        params.setComment(entity ->(StringUtil.equals(entity.getPs(),DictCodeList.交易方向_申购.getValue())||StringUtil.equals(entity.getPs(),DictCodeList.交易方向_买入.getValue()))
                &&entity.getEntrustAmt() != null?this.getMessage("EtrProjectNet001") + "(" + entity.getPortfolioId() + ")"
                + "-" + this.getMessage("EtrPlanAssetN001.ps")+"("+SysDataUtil.getNameFromDataDict(DictCode.交易方向, entity.getPs())+")" + "-" + "("
                + this.getMessage("EtrPlanAssetN001.vdate") + ":" + DateUtil.format(entity.getVdate())
                + ")-(" + this.getMessage("EtrPlanAssetN001.mdate") + ":"
                + DateUtil.format(entity.getMdate()) + ")-"
                + this.getMessage("EtrPlanAssetN001.entrustAmt") + ":"
                + entity.getEntrustAmt():this.getMessage("EtrProjectNet001") + "(" + entity.getPortfolioId() + ")"
                + "-" + this.getMessage("EtrPlanAssetN001.ps")+"("+ SysDataUtil.getNameFromDataDict(DictCode.交易方向, entity.getPs())+")" + "-" + "("
                + this.getMessage("EtrPlanAssetN001.vdate") + ":" + DateUtil.format(entity.getVdate())
                + ")-(" + this.getMessage("EtrPlanAssetN001.mdate") + ":"
                + DateUtil.format(entity.getMdate()) + ")-"
                + this.getMessage("EtrPlanAssetN002.entrustNum") + ":"
                + entity.getEntrustNum());
        processParameter.setWorkFlowParameter(params);
        String msg = etrProjectNetService.start(processParameter,
                dtoList.toArray(new EtrProjectNetDto[dtoList.size()]));
        return new AjaxResult(msg);
    }
}
