/*
 * 文件名：EtrFund003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2020年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.entrust.etrfund.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.entrust.etrfund.dto.EtrFundDto;
import com.joyintech.tams.invest.entrust.etrfund.service.EtrFundService;
import com.joyintech.tams.invest.entrust.etrstock.dto.EtrStockDto;
import com.joyintech.tams.invest.entrust.etrstock.service.EtrStockService;
import com.joyintech.tools.util.ValidationUtil;
import com.joyintech.tools.util.ValidationUtil.ErrorMessage;
import com.joyintech.tools.util.ValidationUtil.ValidResult;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 基金多对多指令 〈功能详细描述〉
 * 
 * @author 赵东东
 * @version 1.0 2020年1月3日
 * @see EtrFund003Controller
 * @since 1.0
 */
@Controller
@PageId("EtrFund003")
@RequestMapping("/invest/entrust/etrfund/EtrFund003")
public class EtrFund003Controller extends BaseController {

    /**
     * 股票委托service
     */
    @Autowired
    private EtrFundService etrFundService;

    /**
     * 业务编号组件
     */
    @Autowired(required = false)
    private BaseGetBusinessId bussGetBussinessIdService;

    /**
     * 多对多指令界面入口
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.币种, DictCode.交易所基金类型, DictCode.交易方向,
            DictCode.交易场所, DictCode.基金类资产类型);
        this.setSelectOptionToModel(model, DictCode.行内资产二级分类);
        this.setSelectOptionToModel(model, DictCode.行内资产三级分类);
        this.setSelectOptionToModel(model, DictCode.行内资产四级分类);
        String operateType = getParam("operateType");
        if (StringUtil.equals(operateType, "import")) {
            String tempList = getParam("tempList");
            List<EtrFundDto> etrFundDtoList = etrFundService.getEntrustListData(tempList);
            model.addAttribute("etrFundDtoList", StringUtil.isEmptyList(
                etrFundDtoList) ? null : JsonUtils.object2Json(etrFundDtoList));
        }
        Date sysDate = DateUtil.platDateFromCache();
        model.addAttribute("sysDate", DateUtil.format(sysDate));
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_委托监控.getValue());
        model.addAttribute("isShowType",
            CacheUtil.getSysParam("IS_SHOW_TYPE") == null ? "01" : CacheUtil.getSysParam(
                "IS_SHOW_TYPE"));
        model.addAttribute("T04", DictCodeList.交易方向_申购.getValue());
        model.addAttribute("T06", DictCodeList.交易方向_赎回.getValue());
        model.addAttribute("T01", DictCodeList.交易方向_买入.getValue());
        model.addAttribute("T02", DictCodeList.交易方向_卖出.getValue());
        model.addAttribute("T52", DictCodeListExt2.交易方向_认购.getValue());
        model.addAttribute("fintype", DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        return super.init(model);
    }

    /**
     * 导入excel数据时check
     *
     * @param map dto
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("checkImportData")
    public AjaxResult checkImportData(String fileName) {
        return etrFundService.checkImportData(fileName);
    }

    /**
     * 保存按钮点击时的check
     *
     * @param dtoList
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody EtrFundDto allDto, BindingResult result) {
        List<ValidMessage> messages = new ArrayList<>();
        StringBuffer confirmMessage = new StringBuffer();
        List<EtrFundDto> dtoList = allDto.getSelectedItem();
        for (int i = 0; i < dtoList.size(); i++ ) {
            EtrFundDto dto = dtoList.get(i);
            ValidResult itemResult = ValidationUtil.validateBean(dtoList.get(i),
                ValidateGroupEdit.class);
            if (!StringUtil.isEmptyList(itemResult.getAllErrors())) {
                StringBuffer message = new StringBuffer();
                message.append(getMessage("ETR_E_0025", String.valueOf(i + 1)));
                for (ErrorMessage errorMessage : itemResult.getAllErrors()) {
                    message.append("<br />");
                    message.append(errorMessage.getMessage());
                }
                messages.add(new ValidMessage(message.toString()));
            }
            // 可用持仓软check by 赵东东 20200121 start
            if(StringUtil.isNotEmpty(dto.getEntrustNum())&&StringUtil.isNotEmpty(dto.getAssetAvailablePosition())
                &&(StringUtil.equals(dto.getPs(),DictCodeList.交易方向_卖出.getValue())||StringUtil.equals(dto.getPs(),DictCodeList.交易方向_赎回.getValue())
                    ||StringUtil.equals(dto.getPs(),DictCodeListExt2.交易方向_转换申请.getValue()))&&StringUtil.equals(
                        CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_POS_SWITCH),
                        DictCodeListExt1.是或否_是.getValue())) {
                if(dto.getEntrustNum().compareTo(dto.getAssetAvailablePosition())>0) {
                    if (StringUtil.isNotBlank(confirmMessage)) {
                        confirmMessage.append("<br />");
                    }
                    confirmMessage.append(getMessage("ETR_E_0025", String.valueOf(i + 1)));
                    confirmMessage.append("<br />");
                    confirmMessage.append(getMessage("ETR_BOND_002"));
                }
            }
            // 可用现金软check by 赵东东 start 20200121 
            if(StringUtil.isNotEmpty(dto.getEntrustAmt())&&StringUtil.isNotEmpty(dto.getProAvailableCash())
                &&(StringUtil.equals(dto.getPs(),DictCodeList.交易方向_买入.getValue())||StringUtil.equals(dto.getPs(),DictCodeList.交易方向_申购.getValue())
                    ||StringUtil.equals(dto.getPs(),DictCodeListExt2.交易方向_认购.getValue()))&&StringUtil.equals(
                        CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_CASH_SWITCH),
                        DictCodeListExt1.是或否_是.getValue())) {
                if(dto.getEntrustAmt().compareTo(dto.getProAvailableCash())>0) {
                    if (StringUtil.isNotBlank(confirmMessage)) {
                        confirmMessage.append("<br />");
                    }
                    confirmMessage.append(getMessage("ETR_E_0025", String.valueOf(i + 1)));
                    confirmMessage.append("<br />");
                    confirmMessage.append(getMessage("TRD_C_0002"));
                }
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
     * 保存/提交
     * 
     * @param allDto EtrFundDto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody EtrFundDto allDto, BindingResult br)
        throws Exception {
    	   AjaxResult ajaxResult = this.check(allDto, br);
        if (!StringUtil.isEmptyList(ajaxResult.getErrorList())) {
            return ajaxResult;
        }
        List<EtrFundDto> dtoList = allDto.getSelectedItem();
        for (EtrFundDto dto : dtoList) {
            if (!StringUtil.isNotEmpty(dto.getEntrustId())) {
                BussinessCodeParameter param = new BussinessCodeParameter();
                param.setCommonBusinessType(DictCodeList.共通业务类型_基金类资产投资委托);
                String code = bussGetBussinessIdService != null ? bussGetBussinessIdService.buildBussinessCode(
                    param) : IDGenerator.generate();
                dto.setEntrustId(code);
            }
          
        }
        ProcessParameter<EtrFundDto> processParameter = new ProcessParameter<EtrFundDto>();
        WorkFlowParameter<EtrFundDto> params = new WorkFlowParameter<EtrFundDto>();
        params.setPageId("EtrFund002");
        params.setElementId(dtoList.get(0).getElementId());
        params.setBusinessCode(EtrFundDto::getEntrustId);
        params.setBusinessType(DictCodeList.共通业务类型_基金类资产投资委托.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity -> getMessage("EtrFund001") + "(" + entity.getPortfolioId() + ")-"
                      + getMessage("EtrFund001.ps") + "(" + entity.getPsStr() + ")" + "-("
                      + getMessage("EtrFund001.entrustVDate") + "："
                      + DateUtil.format(entity.getVdate()) + ")-("
                      + getMessage("EtrFund001.entrustMDate") + "："
                      + DateUtil.format(entity.getMdate()) + ")-"
                      + getMessage("EtrFund001.entrustAmt") + "/"
                      + getMessage("EtrFund001.entrustNum") + "："
                      + (entity.getEntrustAmt() == null ? entity.getEntrustNumStr() : entity.getEntrustAmtStr()));
        processParameter.setWorkFlowParameter(params);
        String msg = etrFundService.start(processParameter,
            dtoList.toArray(new EtrFundDto[dtoList.size()]));
        return new AjaxResult(msg);
    }

}
