/*
 * 文件名：EtrBoutRepo003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2020年1月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.entrust.etrboutrepo.controller;

import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.risk.dto.RiskCreditListCommonDto;
import com.joyintech.tams.invest.common.risk.service.RiskCreditListCommonValidatorService;
import com.joyintech.tams.invest.common.trade.CeLinesUpdate;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.CreditLinesDealCommon;
import com.joyintech.tams.invest.entrust.etrboutrepo.dto.EtrBoutRepoDto;
import com.joyintech.tams.invest.entrust.etrboutrepo.service.EtrBoutRepoService;
import com.joyintech.tools.util.ValidationUtil;
import com.joyintech.tools.util.ValidationUtil.ErrorMessage;
import com.joyintech.tools.util.ValidationUtil.ValidResult;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;

/**
 * 买断式回购多对多指令 〈功能详细描述〉
 * 
 * @author 赵东东
 * @version 1.0 2020年1月10日
 * @see EtrBoutRepo003Controller
 * @since 1.0
 */
@Controller
@PageId("EtrBoutRepo003")
@RequestMapping("/invest/entrust/etrboutrepo/EtrBoutRepo003")
public class EtrBoutRepo003Controller extends BaseController {

    /**
     * 业务编号组件
     */
    @Autowired(required = false)
    private BaseGetBusinessId bussGetBussinessIdService;

    @Autowired
    private EtrBoutRepoService etrBoutRepoService;
    
    @Autowired
    private RiskCreditListCommonValidatorService riskCreditListCommonValidatorService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DictCode.回购拆借方向);
        setSelectOptionToModel(model, DictCode.币种);
        setSelectOptionToModel(model, DictCode.清算速度);
        setSelectOptionToModel(model, DictCode.回购期限品种);
        setSelectOptionToModel(model, DictCode.价格模式);
        model.addAttribute("PRICEMODEL_03", DictCodeListExt2.价格模式_市价指令.getValue());
        model.addAttribute("ps_03", DictCodeList.回购拆借方向_正回购.getValue());
        model.addAttribute("ps_04", DictCodeList.回购拆借方向_逆回购.getValue());
        Date sysDate = CacheUtil.getAppCache(GlobalConstant.NOW_SYS_DATE, Date.class);
        model.addAttribute("sysDate", DateUtil.format(sysDate));
        String operateType = getParam("operateType");
        if (StringUtil.equals(operateType, "import")) {
            String tempList = getParam("tempList");
            List<EtrBoutRepoDto> etrBoutRepoDtoList = etrBoutRepoService.getEntrustListData(
                tempList);
            model.addAttribute("etrBoutRepoDtoList", StringUtil.isEmptyList(
                etrBoutRepoDtoList) ? null : JsonUtils.object2Json(etrBoutRepoDtoList));
        }
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_委托监控.getValue());
        model.addAttribute("fintype_zhg", DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue());
        model.addAttribute("fintype_nhg", DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue());
        PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
        pageElementConfigDto.setPageId("BaseRiskMathComm");
        Map<String, String> map = PageElementUtil.isPageElementShow(pageElementConfigDto); // 风险试算页面
        pageElementConfigDto.setPageId(this.getPageId());
        Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto);
        isEleShow.putAll(map);
        model.addAttribute("isEleShow", isEleShow);
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
        return etrBoutRepoService.checkImportData(fileName);
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
    public AjaxResult check(@RequestBody List<EtrBoutRepoDto> dtoList, BindingResult result) {
        List<ValidMessage> messages = new ArrayList<>();
        StringBuffer confirmMessage = new StringBuffer();
    	// 授信软控 start
        List<JoyinMessage> joyinMessages = new ArrayList<>();
        Map<String, BigDecimal> cusMap  = new HashMap<String, BigDecimal>();
        // 授信软控 end
        for (int i = 0; i < dtoList.size(); i++ ) {
        	// 授信软控 start
        	BigDecimal recode = dtoList.get(i).getPrinAmt();
        	Map<String, Object> map  = new HashMap<String, Object>();
        	map.put("finprodId", dtoList.get(i).getFinprodId());
        	String customerId = dtoList.get(i).getCounterId();
        	if(customerId != null) {
            	if(cusMap.get(customerId) == null) {
            		cusMap.put(customerId, dtoList.get(i).getPrinAmt());
            	}else {
            		dtoList.get(i).setPrinAmt(cusMap.get(customerId).add(dtoList.get(i).getPrinAmt()));
            		cusMap.put(customerId, dtoList.get(i).getPrinAmt());
            	}
        	}
        		
            boolean isCreditSoftControl = Boolean.parseBoolean(CacheUtil.getSysParam("IS_CREDIT_SOFTCONTROL"));
            RiskCreditListCommonDto riskCreditListCommonDto = new RiskCreditListCommonDto();
            if (!StringUtil.equals(BussinessDictCodeList.监控授信开关_关.getValue(), CacheUtil.getSysParam(BussinessConstant.ISCREDIT))
                    && isCreditSoftControl) {
//                if(!DictCodeListExt3.授信来源_外部.getValue().equals(dtoList.get(i).getCreditSource())){
                	TrdProductDeal trdProductDeal = etrBoutRepoService.prepareDeal(dtoList.get(i));
            		if(trdProductDeal.getCounterId() == null) {
            			trdProductDeal.setCounterId("");
            		}
                	CeLinesUpdate ceLinesUpdate = SpringContextUtil.getBean(CeLinesUpdate.class);
                	AllTradeCommonInputDto inputDto =new AllTradeCommonInputDto();
                	inputDto.setTrdProductDeal(trdProductDeal);
                	// TODO 回购类型
                	inputDto.setRepoType(dtoList.get(i).getAssetType1());
                	List<CreditLinesDealCommon> list = ceLinesUpdate.isNeedOccupyCre(inputDto, true,false,false);
                	if(list !=null && list.size()>0){
                		if(list.get(0).getErrMessage() !=null && getMessage("BUSS_COM_CRE_001").equals(list.get(0).getErrMessage())){
                			list.get(0).setErrMessage(getMessage("BUSS_COM_CRE_004")); 
                		}
                		JoyinMessage joyinMessage = new JoyinMessage(list.get(0).getErrMessage(), MessageType.确认消息);
                		joyinMessages.add(joyinMessage);
                	}
//                }
            }
            // 授信软控 end
            EtrBoutRepoDto dto = dtoList.get(i);
            ValidResult itemResult = ValidationUtil.validateBean(dtoList.get(i),
                ValidateGroupEdit.class);
            // 授信软控 start
            dtoList.get(i).setPrinAmt(recode);
            // 授信软控 end
            if (!StringUtil.isEmptyList(itemResult.getAllErrors())) {
                StringBuffer message = new StringBuffer();
                message.append(getMessage("ETR_E_0025", String.valueOf(i + 1)));
                for (ErrorMessage errorMessage : itemResult.getAllErrors()) {
                    message.append("<br />");
                    message.append(errorMessage.getMessage());
                }
                messages.add(new ValidMessage(message.toString()));
            }
            // 指令可用持仓软校验
            if(dto.getEntrustAmt() != null && dto.getAssetAvailablePosition() != null && dto.getEntrustAmt().divide(new BigDecimal(100)).compareTo(dto.getAssetAvailablePosition()) ==1&&StringUtil.equals(
                CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_POS_SWITCH),
                DictCodeListExt1.是或否_是.getValue())){
                if (StringUtil.isNotBlank(confirmMessage)) {
                    confirmMessage.append("<br />");
                }
                confirmMessage.append(getMessage("ETR_E_0025", String.valueOf(i + 1)));
                confirmMessage.append("<br />");
                confirmMessage.append(getMessage("ETR_BOND_002"));
            }
            // 指令可用现金软校验
            if (dto.getEntrustAmt() != null && dto.getProAvailableCash() != null
                && StringUtil.equals(dto.getPs(), DictCodeList.回购拆借方向_逆回购.getValue())&&StringUtil.equals(
                    CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_CASH_SWITCH),
                    DictCodeListExt1.是或否_是.getValue())) {
                if (dto.getEntrustAmt().compareTo(dto.getProAvailableCash()) == 1) {
                    if (StringUtil.isNotBlank(confirmMessage)) {
                        confirmMessage.append("<br />");
                    }
                    confirmMessage.append(getMessage("ETR_E_0025", String.valueOf(i + 1)));
                    confirmMessage.append("<br />");
                    confirmMessage.append(getMessage("TRD_C_0002"));  
                }
                }
        }
        // 授信软控 start
        if (joyinMessages.size() > 0 || messages.size() > 0) { 
            AjaxResult errorRs = new AjaxResult();
            errorRs.setErrorList(messages);
            // 授信软控 start
            errorRs.setJoyinMessages(joyinMessages);
            
            String btnName = "操作";
            if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SAVE, dtoList.get(0).getOperateType())) {
                btnName = "SysCommon.save";
            }
            else if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SUBMIT, dtoList.get(0).getOperateType())) {
                btnName = "SysCommon.submit";
            }
            else if (StringUtil.equalsIgnoreCase("revoke", dtoList.get(0).getOperateType())) {
                btnName = "SysCommon.revoke";
            }
            errorRs.setData(
                SpringContextUtil.getTextValue("COMMON_C_0001", SpringContextUtil.getTextValue(btnName)));
            // 授信软控 end
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
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody List<EtrBoutRepoDto> dtoList, BindingResult br)
        throws Exception {
        AjaxResult ajaxResult = this.check(dtoList, br);
        if (!StringUtil.isEmptyList(ajaxResult.getErrorList())) {
            return ajaxResult;
        }
        for (EtrBoutRepoDto dto : dtoList) {
            if (!StringUtil.isNotEmpty(dto.getEntrustId())) {
                BussinessCodeParameter param = new BussinessCodeParameter();
                param.setCommonBusinessType(DictCodeList.共通业务类型_回购投资委托);
                String code = bussGetBussinessIdService != null ? bussGetBussinessIdService.buildBussinessCode(
                    param) : IDGenerator.generate();
                dto.setEntrustId(code);
            }
            dto.setWorkflowParameter("new|||1|EtrBoutRepo002|EtrBoutRepo002_btn_submit|true");
        }
        ProcessParameter<EtrBoutRepoDto> processParameter = new ProcessParameter<EtrBoutRepoDto>();
        WorkFlowParameter<EtrBoutRepoDto> params = new WorkFlowParameter<EtrBoutRepoDto>();
        params.setPageId("EtrBoutRepo002");
        params.setElementId(dtoList.get(0).getElementId());
        params.setBusinessCode(EtrBoutRepoDto::getEntrustId);
        params.setBusinessType(DictCodeList.共通业务类型_回购投资委托.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity -> this.getMessage("EtrBoutRepo001") + "(" + entity.getPortfolioId() + ")" + "-"
                      + this.getMessage("EtrBoutRepo002.ps") + "("
                      + SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向, entity.getPs()) + ")"
                      + "-" + "(" + this.getMessage("EtrBoutRepo002.vdate") + ":"
                      + DateUtil.format(entity.getVdate()) + ")-("
                      + this.getMessage("EtrBoutRepo002.mdate") + ":"
                      + DateUtil.format(entity.getMdate()) + ")-"
                      + this.getMessage("EtrBoutRepo002.entrustAmt") + ":"
                      + entity.getEntrustAmt());
        processParameter.setWorkFlowParameter(params);
        String msg = etrBoutRepoService.start(processParameter,
            dtoList.toArray(new EtrBoutRepoDto[dtoList.size()]));
        return new AjaxResult(msg);
    }

}
