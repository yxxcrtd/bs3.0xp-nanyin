package com.joyintech.tams.invest.entrust.etrstock.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.entrust.etrstock.dto.EtrStockDto;
import com.joyintech.tams.invest.entrust.etrstock.service.EtrStockService;
import com.joyintech.tools.util.ValidationUtil;
import com.joyintech.tools.util.ValidationUtil.ErrorMessage;
import com.joyintech.tools.util.ValidationUtil.ValidResult;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 股票多对多指令 〈功能详细描述〉
 * 
 * @author 赵东东
 * @version 1.0 2020年1月9日
 * @see EtrStock003Controller
 * @since 1.0
 */
@Controller
@PageId("EtrStock003")
@RequestMapping("/invest/entrust/etrstock/EtrStock003")
public class EtrStock003Controller extends BaseController {

    /**
     * 股票委托service
     */
    @Autowired
    private EtrStockService etrStockService;

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
        this.setSelectOptionToModel(model, DictCode.资产_负债二级类别, DictCode.清算速度, DictCode.币种,
            DictCode.本金收益特征, DictCode.投资性质);
        String operateType = getParam("operateType");
        if (StringUtil.equals(operateType, "import")) {
            String tempList = getParam("tempList");
            List<EtrStockDto> etrStockDtoList = etrStockService.getEntrustListData(tempList);
            model.addAttribute("etrStockDtoList", StringUtil.isEmptyList(
                etrStockDtoList) ? null : JsonUtils.object2Json(etrStockDtoList));
        }
        model.addAttribute("MY_SUP_TIME_TYPE_02", BussinessDictCodeList.监督时分_委托监控.getValue());
        model.addAttribute("fintype", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        Date sysDate = CacheUtil.getAppCache(GlobalConstant.NOW_SYS_DATE, Date.class);
        model.addAttribute("sysDate", DateUtil.format(sysDate));
        model.addAttribute("USE_STATUS_01", DictCodeListExt1.使用状态_启用.getValue());
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
        return etrStockService.checkImportData(fileName);
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
    public AjaxResult check(@RequestBody List<EtrStockDto> dtoList, BindingResult result) {
        List<ValidMessage> messages = new ArrayList<>();
        StringBuffer confirmMessage = new StringBuffer();
        for (int i = 0; i < dtoList.size(); i++ ) {
            EtrStockDto dto = dtoList.get(i);
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
            
          // 可用现金软check
            if (StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_CASH_SWITCH),
                DictCodeListExt1.是或否_是.getValue())
                && !(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue()))
                && dto.getProAvailableCash() != null
                && StringUtil.isNotEmpty(dto.getEntrustAmt())
                && dto.getEntrustAmt().compareTo(dto.getProAvailableCash()) == 1) {
                if (StringUtil.isNotBlank(confirmMessage)) {
                    confirmMessage.append("<br />");
                }
                confirmMessage.append(getMessage("ETR_E_0025", String.valueOf(i + 1)));
                confirmMessage.append("<br />");
                confirmMessage.append(getMessage("TRD_C_0002"));
            }
            else if(StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_CASH_SWITCH),
                DictCodeListExt1.是或否_是.getValue())
                && !(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue()))
                && dto.getProAvailableCash() != null
                && StringUtil.isNotEmpty(dto.getEntrustNum())
                && StringUtil.isNotEmpty(dto.getPrice())) {
                BigDecimal amt = dto.getEntrustNum().multiply(dto.getPrice());
                if(amt.compareTo(dto.getProAvailableCash())>0) {
                    if (StringUtil.isNotBlank(confirmMessage)) {
                        confirmMessage.append("<br />");
                    }
                    confirmMessage.append(getMessage("ETR_E_0025", String.valueOf(i + 1)));
                    confirmMessage.append("<br />");
                    confirmMessage.append(getMessage("TRD_C_0002"));
                }
            }
            // 可用持仓软校验
            else if (StringUtil.equals(
                CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_POS_SWITCH),
                DictCodeListExt1.是或否_是.getValue())
                     && StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())
                     && dto.getAssetAvailablePosition() != null
                     && StringUtil.isNotEmpty(dto.getEntrustNum())
                     && dto.getEntrustNum().compareTo(dto.getAssetAvailablePosition()) > 0) {
                if (StringUtil.isNotBlank(confirmMessage)) {
                    confirmMessage.append("<br />");
                }
                confirmMessage.append(getMessage("ETR_E_0025", String.valueOf(i + 1)));
                confirmMessage.append("<br />");
                confirmMessage.append(getMessage("ETR_BOND_002"));
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
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody List<EtrStockDto> dtoList, BindingResult br)
        throws Exception {
        AjaxResult ajaxResult = this.check(dtoList, br);
        if (!StringUtil.isEmptyList(ajaxResult.getErrorList())) {
            return ajaxResult;
        }
        for (EtrStockDto dto : dtoList) {
            if (!StringUtil.isNotEmpty(dto.getEntrustId())) {
                BussinessCodeParameter param = new BussinessCodeParameter();
                param.setCommonBusinessExtType(
                    DictCodeListExt2.fromDictCodeList(DictCodeListExt2.共通业务类型_股票投资委托));
                String code = bussGetBussinessIdService != null ? bussGetBussinessIdService.buildBussinessCode(
                    param) : IDGenerator.generate();
                dto.setEntrustId(code);
            }
        }
        ProcessParameter<EtrStockDto> processParameter = new ProcessParameter<EtrStockDto>();
        WorkFlowParameter<EtrStockDto> params = new WorkFlowParameter<EtrStockDto>();
        params.setPageId("EtrStock02");
        params.setElementId(dtoList.get(0).getElementId());
        params.setBusinessCode(EtrStockDto::getEntrustId);
        params.setBusinessType(DictCodeListExt2.共通业务类型_股票投资委托.getValue());
        // 设置流程业务说明, entity即传入的dto
        for (EtrStockDto dto : dtoList) {
            if (dto.getEntrustNum() != null) {
                params.setComment(
                    entity -> this.getMessage("EtrStock001") + "(" + entity.getPortfolioId() + ")"
                              + "-" + this.getMessage("EtrStock001.ps") + "("
                              + SysDataUtil.getNameFromDataDict(DictCode.交易方向, entity.getPs())
                              + ")" + "-" + "(" + this.getMessage("EtrStock001.vdate") + ":"
                              + DateUtil.format(entity.getVdate()) + ")-("
                              + this.getMessage("EtrStock001.mdate") + ":"
                              + DateUtil.format(entity.getMdate()) + ")-("
                              + this.getMessage("EtrStock001.entrustNum") + ":"
                              + entity.getEntrustNum() + ")-("
                              + this.getMessage("EtrStock001.price") + ":" + entity.getPrice()
                              + ")");
            }
            else {
                params.setComment(
                    entity -> this.getMessage("EtrStock001") + "(" + entity.getPortfolioId() + ")"
                              + "-" + this.getMessage("EtrStock001.ps") + "("
                              + SysDataUtil.getNameFromDataDict(DictCode.交易方向, entity.getPs())
                              + ")" + "-" + "(" + this.getMessage("EtrStock001.vdate") + ":"
                              + DateUtil.format(entity.getVdate()) + ")-("
                              + this.getMessage("EtrStock001.mdate") + ":"
                              + DateUtil.format(entity.getMdate()) + ")-("
                              + this.getMessage("EtrStock001.entrustAmt") + ":"
                              + entity.getEntrustAmt() + ")");
            }
        }
        processParameter.setWorkFlowParameter(params);
        String msg = etrStockService.start(processParameter,
            dtoList.toArray(new EtrStockDto[dtoList.size()]));
        return new AjaxResult(msg);
    }

}
