package com.joyintech.tams.invest.entrust.etrplanassetn.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.risk.dto.RiskCreditListCommonDto;
import com.joyintech.tams.invest.common.risk.service.RiskCreditListCommonValidatorService;
import com.joyintech.tams.invest.common.trade.CeLinesUpdate;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.CreditLinesDealCommon;
import com.joyintech.tams.invest.entrust.etrplanassetn.dto.EtrPlanAssetNDto;
import com.joyintech.tams.invest.entrust.etrplanassetn.service.EtrPlanAssetNService;
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

import java.math.BigDecimal;
import java.util.*;

/**
 * 计划类净值指令多对多
 * 〈功能详细描述〉
 * @author zhuyongkang
 * @version 1.0 2019年12月30日
 * @see EtrPlanAssetN003Controller
 * @since 1.0
 */
@Controller
@PageId("EtrPlanAssetN003")
@RequestMapping("/invest/entrust/etrplanassetn/EtrPlanAssetN003")
public class EtrPlanAssetN003Controller extends BaseController {
    /**
     * 业务编号组件
     */
    @Autowired(required=false)
    private BaseGetBusinessId bussGetBussinessIdService;
    /**
     * 计划类净值委托service
     */
    @Autowired
    private EtrPlanAssetNService etrPlanAssetNService;

    @Autowired
    private CommonBusinessService commonBusinessService;
    
    @Autowired
    private RiskCreditListCommonValidatorService riskCreditListCommonValidatorService;

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
            List<EtrPlanAssetNDto> entrustListData= etrPlanAssetNService.getEntrustNListData(tempList);
            model.addAttribute("etrPlanAssetNDtoList", StringUtil.isEmptyList(entrustListData) ? null : JsonUtils.object2Json(entrustListData));
        }
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_委托监控.getValue());
        model.addAttribute("fintype", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return super.init(model);
    }


    /**
     * 保存按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody  List<EtrPlanAssetNDto> dtoList, BindingResult result) {
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
        	String customerId = commonBusinessService.selectCustomer(map);
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
                	TrdProductDeal trdProductDeal = etrPlanAssetNService.prepareDeal(dtoList.get(i));
            		if(trdProductDeal.getCounterId() == null) {
            			trdProductDeal.setCounterId("");
            		}
                	CeLinesUpdate ceLinesUpdate = SpringContextUtil.getBean(CeLinesUpdate.class);
                	AllTradeCommonInputDto inputDto =new AllTradeCommonInputDto();
                	inputDto.setTrdProductDeal(trdProductDeal);
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
            EtrPlanAssetNDto dto = dtoList.get(i);
            ValidationUtil.ValidResult itemResult = ValidationUtil.validateBean(dto,
                    ValidateGroupEdit.class);
            // 授信软控 start
            dtoList.get(i).setPrinAmt(recode);
            // 授信软控 end
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
        return etrPlanAssetNService.checkImportData(fileName);
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
    public AjaxResult save(@RequestBody List<EtrPlanAssetNDto> dtoList, BindingResult br)
        throws Exception {
        AjaxResult ajaxResult = this.check(dtoList, br);
        if (!StringUtil.isEmptyList(ajaxResult.getErrorList())) {
            return ajaxResult;
        }
        for(EtrPlanAssetNDto dto: dtoList) {
            if (!StringUtil.isNotEmpty(dto.getEntrustId())) {
                BussinessCodeParameter param = new BussinessCodeParameter();
                param.setCommonBusinessType(DictCodeList.共通业务类型_计划类资产_净值型_投资委托);
                String code = bussGetBussinessIdService != null ? bussGetBussinessIdService.buildBussinessCode(param) : IDGenerator.generate();
                dto.setEntrustId(code);
            }
            //走002界面流程
            dto.setWorkflowParameter("new|||1|EtrPlanAssetN002|EtrPlanAssetN002_btn_submit|true");
        }
        ProcessParameter<EtrPlanAssetNDto> processParameter = new ProcessParameter<EtrPlanAssetNDto>();
        WorkFlowParameter<EtrPlanAssetNDto> params = new WorkFlowParameter<EtrPlanAssetNDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dtoList.get(0).getElementId());
        params.setBusinessCode(EtrPlanAssetNDto::getEntrustId);
        params.setBusinessType(DictCodeList.共通业务类型_计划类资产_净值型_投资委托.getValue());
        // 设置流程业务说明, entity即传入的dto
        if(dtoList.get(0).getEntrustAmt() != null){
            params.setComment(
                entity -> this.getMessage("EtrPlanAssetN001") + "(" + entity.getPortfolioId() + ")"
                          + "-" + this.getMessage("EtrPlanAssetN001.ps")+"("+SysDataUtil.getNameFromDataDict(DictCode.交易方向, entity.getPs())+")" + "-" + "("
                          + this.getMessage("EtrPlanAssetN001.vdate") + ":" + DateUtil.format(entity.getVdate()) 
                          + ")-(" + this.getMessage("EtrPlanAssetN001.mdate") + ":"
                          + DateUtil.format(entity.getMdate()) + ")-"
                          + this.getMessage("EtrPlanAssetN001.entrustAmt") + ":"
                          + entity.getEntrustAmt());
        }else if(dtoList.get(0).getEntrustNum() != null){
            params.setComment(
                entity -> this.getMessage("EtrPlanAssetN001") + "(" + entity.getPortfolioId() + ")"
                          + "-" + this.getMessage("EtrPlanAssetN001.ps")+"("+ SysDataUtil.getNameFromDataDict(DictCode.交易方向, entity.getPs())+")" + "-" + "("
                          + this.getMessage("EtrPlanAssetN001.vdate") + ":" + DateUtil.format(entity.getVdate()) 
                          + ")-(" + this.getMessage("EtrPlanAssetN001.mdate") + ":"
                          + DateUtil.format(entity.getMdate()) + ")-"
                          + this.getMessage("EtrPlanAssetN002.entrustNum") + ":"
                          + entity.getEntrustNum());
        }
        processParameter.setWorkFlowParameter(params);
        String msg = etrPlanAssetNService.start(processParameter,
                dtoList.toArray(new EtrPlanAssetNDto[dtoList.size()]));
        return new AjaxResult(msg);
    }
    
}
