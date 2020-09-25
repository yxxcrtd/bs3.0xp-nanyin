package com.joyintech.tams.invest.entrust.etrrepo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
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
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.entrust.etrrepo.dto.EtrRepoDto;
import com.joyintech.tams.invest.entrust.etrrepo.service.EtrRepo002Service;
import com.joyintech.tools.util.ValidationUtil;
import com.joyintech.tools.util.ValidationUtil.ErrorMessage;
import com.joyintech.tools.util.ValidationUtil.ValidResult;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 质押式回购多指令
 * 
 * @author renping
 * @version 1.0 2019年12月30日
 * @see EtrRepo003Controller
 * @since 1.0
 */
@Controller
@PageId("EtrRepo003")
@RequestMapping("/invest/entrust/etrrepo/EtrRepo003")
public class EtrRepo003Controller extends BaseController {

    /**
     * 业务编号组件
     */
    @Autowired(required = false)
    private BaseGetBusinessId bussGetBussinessIdService;

    /**
     * 质押式回购委托service
     */
    @Autowired
    private EtrRepo002Service etrRepo002Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        Date sysDate = DateUtil.platDateFromCache();
        model.addAttribute("sysDate", DateUtil.format(sysDate));
        // 导入
        String tempList = getParam("tempList");
        EtrRepoDto dto = etrRepo002Service.getEntrustListData(tempList);
        model.addAttribute(GlobalConstant.ENTITY, dto);
        model.addAttribute("etrRepoList", StringUtil.isEmptyList(
            dto.getSelectedItem()) ? null : JsonUtils.object2Json(dto.getSelectedItem()));
        if (dto.getBussRepoSec() != null
            && !StringUtil.isEmptyList(dto.getBussRepoSec().getPledgeBondList())) {
            model.addAttribute("pledgeBondList",
                JsonUtils.object2Json(dto.getBussRepoSec().getPledgeBondList()));
        }
        model.addAttribute("MY_SUP_TIME_TYPE_02", BussinessDictCodeList.监督时分_委托监控.getValue());
        model.addAttribute("fintype_zhg", DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue());
        model.addAttribute("fintype_nhg", DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue());
        model.addAttribute("TARGET_BACK_TYPE_H01", DictCodeList.回购类型_质押式协议回购.getValue());
        model.addAttribute("ps_03", DictCodeList.回购拆借方向_正回购.getValue());
        model.addAttribute("ps_04", DictCodeList.回购拆借方向_逆回购.getValue());
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
        return etrRepo002Service.checkImportData(fileName);
    }

    /**
     * 保存按钮点击时的check
     *
     * @param dtoList dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody List<EtrRepoDto> dtoList, BindingResult result) {
        List<ValidMessage> messages = new ArrayList<>();
        StringBuffer confirmMessage = new StringBuffer();
        // 软校验Flag
        boolean checkFlag = StringUtil.equals(
            CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_CASH_SWITCH),
            DictCodeListExt1.是或否_是.getValue());
        for (int i = 0; i < dtoList.size(); i++ ) {
            EtrRepoDto dto = dtoList.get(i);
            ValidResult itemResult = ValidationUtil.validateBean(dto, ValidateGroupEdit.class);
            if (!StringUtil.isEmptyList(itemResult.getAllErrors())) {
                StringBuffer message = new StringBuffer();
                message.append(getMessage("ETR_E_0025", String.valueOf(i + 1)));
                for (ErrorMessage errorMessage : itemResult.getAllErrors()) {
                    message.append("<br />");
                    message.append(errorMessage.getMessage());
                }
                messages.add(new ValidMessage(message.toString()));
            }

            // 可用现金软校验
            if (checkFlag && StringUtil.equals(dto.getPs(), DictCodeList.回购拆借方向_逆回购.getValue())
                && dto.getProAvailableCash() != null && dto.getEntrustAmt() != null
                && dto.getEntrustAmt().compareTo(dto.getProAvailableCash()) == 1) {
                if (StringUtil.isNotBlank(confirmMessage)) {
                    confirmMessage.append("<br />");
                }
                confirmMessage.append(getMessage("ETR_E_0025", String.valueOf(i + 1)));
                confirmMessage.append("<br />");
                confirmMessage.append(getMessage("TRD_C_0002"));
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
     * @param dtoList dto
     * @param br BindingResult
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody List<EtrRepoDto> dtoList, BindingResult br)
        throws Exception {
        AjaxResult ajaxResult = this.check(dtoList, br);
        if (!StringUtil.isEmptyList(ajaxResult.getErrorList())) {
            return ajaxResult;
        }
        for (EtrRepoDto dto : dtoList) {
            if (StringUtil.isEmpty(dto.getEntrustId())) {
                BussinessCodeParameter param = new BussinessCodeParameter();
                param.setCommonBusinessType(DictCodeList.共通业务类型_回购投资委托);
                String code = bussGetBussinessIdService != null ? bussGetBussinessIdService.buildBussinessCode(
                    param) : IDGenerator.generate();
                dto.setEntrustId(code);
            }
            dto.setWorkflowParameter("new|||1|EtrRepo002|EtrRepo002_btn_submit|true");
        }

        ProcessParameter<EtrRepoDto> processParameter = new ProcessParameter<EtrRepoDto>();
        WorkFlowParameter<EtrRepoDto> params = new WorkFlowParameter<EtrRepoDto>();
        params.setPageId("EtrRepo002");
        params.setElementId(dtoList.get(0).getElementId());
        params.setBusinessCode(EtrRepoDto::getEntrustId);
        params.setBusinessType(DictCodeList.共通业务类型_回购投资委托.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity -> this.getMessage("EtrRepo001") + "(" + entity.getPortfolioId() + ")" + "-"
                      + this.getMessage("EtrRepo002.ps") + "("
                      + SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向, entity.getPs()) + ")"
                      + "-" + "(" + this.getMessage("EtrRepo002.vdate") + ":"
                      + DateUtil.format(entity.getVdate()) + ")-("
                      + this.getMessage("EtrRepo002.mdate") + ":"
                      + DateUtil.format(entity.getMdate()) + ")-"
                      + this.getMessage("EtrRepo002.entrustAmt") + ":" + entity.getEntrustAmt());
        processParameter.setWorkFlowParameter(params);
        String msg = etrRepo002Service.start(processParameter,
            dtoList.toArray(new EtrRepoDto[dtoList.size()]));
        return new AjaxResult(msg);
    }
}
