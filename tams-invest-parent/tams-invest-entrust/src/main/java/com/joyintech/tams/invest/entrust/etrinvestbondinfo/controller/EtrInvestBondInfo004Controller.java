package com.joyintech.tams.invest.entrust.etrinvestbondinfo.controller;

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
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.riskrds.dto.InterfRiskRdsDto;
import com.joyintech.tams.component.business.riskrds.service.InterfRiskRdsService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.risk.dto.RiskCreditListCommonDto;
import com.joyintech.tams.invest.common.risk.service.RiskCreditListCommonValidatorService;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.common.trade.CeLinesUpdate;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.CreditLinesDealCommon;
import com.joyintech.tams.invest.entrust.etrinvestbondinfo.dto.EtrInvestBondInfoDto;
import com.joyintech.tams.invest.entrust.etrinvestbondinfo.service.EtrInvestBondInfoService;
import com.joyintech.tams.invest.entrust.etrinvestbondinfo.validator.EtrInvestBondInfoValidator;
import com.joyintech.tools.util.ValidationUtil;
import com.joyintech.tools.util.ValidationUtil.ErrorMessage;
import com.joyintech.tools.util.ValidationUtil.ValidResult;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.collections.CollectionUtils;
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
 * 债券多指令
 * 
 * @author renping
 * @version 1.0 2019年12月30日
 * @see EtrInvestBondInfo004Controller
 * @since 1.0
 */
@Controller
@PageId("EtrInvestBondInfo004")
@RequestMapping("/invest/entrust/etrinvestbondinfo/EtrInvestBondInfo004")
public class EtrInvestBondInfo004Controller extends BaseController {

    /**
     * 业务编号组件
     */
    @Autowired(required = false)
    private BaseGetBusinessId bussGetBussinessIdService;

    /**
     * 债券委托service
     */
    @Autowired
    private EtrInvestBondInfoService etrInvestBondInfoService;

    /**
     * EtrInvestBondInfoValidator
     */
    @Autowired(required = false)
    private EtrInvestBondInfoValidator etrInvestBondInfoValidator;
    
    @Autowired
    private CommonBusinessService commonBusinessService;
    
    @Autowired
    private RiskCreditListCommonValidatorService riskCreditListCommonValidatorService;

    @Autowired
    private InterfRiskRdsService<InterfRiskRdsDto> interfRiskRdsService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        Date sysDate = DateUtil.platDateFromCache();
        model.addAttribute("sysDate", DateUtil.format(sysDate));
        // 导入
        String tempList = getParam("tempList");
        EtrInvestBondInfoDto dto = etrInvestBondInfoService.getEntrustListData(tempList);
        model.addAttribute(GlobalConstant.ENTITY, dto);
        model.addAttribute("etrInvestBondInfoList", StringUtil.isEmptyList(
            dto.getSelectedItem()) ? null : JsonUtils.object2Json(dto.getSelectedItem()));
        model.addAttribute("MY_SUP_TIME_TYPE_02", BussinessDictCodeList.监督时分_委托监控.getValue());
        model.addAttribute("fintype", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        model.addAttribute("shortSellingSupported", CacheUtil.getSysParam("SHORT_SELLING_SUPPORTED"));

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
        return etrInvestBondInfoService.checkImportData(fileName);
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
    public AjaxResult check(@RequestBody List<EtrInvestBondInfoDto> dtoList, BindingResult result) {
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
                	TrdProductDeal trdProductDeal = etrInvestBondInfoService.prepareDeal(dtoList.get(i));
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
            EtrInvestBondInfoDto dto = dtoList.get(i);
            ValidResult itemResult = ValidationUtil.validateBean(dto, ValidateGroupEdit.class);
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
            // 可用现金软校验
            if (StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_CASH_SWITCH),
                DictCodeListExt1.是或否_是.getValue())
                && StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())
                && dto.getProAvailableCash() != null
                && dto.getEntrustNum().compareTo(dto.getProAvailableCash()) == 1) {
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
                         || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_行权_赎回.getValue())
                         || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_行权_回售.getValue()))
                     && dto.getAssetAvailablePosition() != null
                     && dto.getEntrustNum().compareTo(dto.getAssetAvailablePosition()) > 0) {
                if (StringUtil.isNotBlank(confirmMessage)) {
                    confirmMessage.append("<br />");
                }
                confirmMessage.append(getMessage("ETR_E_0025", String.valueOf(i + 1)));
                confirmMessage.append("<br />");
                confirmMessage.append(getMessage("ETR_BOND_002"));
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
     * @param dtoList dto
     * @param br BindingResult
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody List<EtrInvestBondInfoDto> dtoList, BindingResult br)
        throws Exception {
        AjaxResult ajaxResult = this.check(dtoList, br);
        if (!StringUtil.isEmptyList(ajaxResult.getErrorList())) {
            return ajaxResult;
        }
        for (EtrInvestBondInfoDto dto : dtoList) {
            if (StringUtil.isEmpty(dto.getEntrustId())) {
                BussinessCodeParameter param = new BussinessCodeParameter();
                param.setCommonBusinessType(DictCodeList.共通业务类型_债券投资委托);
                String code = bussGetBussinessIdService != null ? bussGetBussinessIdService.buildBussinessCode(
                    param) : IDGenerator.generate();
                dto.setEntrustId(code);
            }
            dto.setWorkflowParameter("new|||1|EtrRepo003|EtrRepo003_btn_submit|true");
        }
        //多组合风险试算结果处理
        for(EtrInvestBondInfoDto dto: dtoList) { 
            //多组合交易对风险试算处理逻辑
            if(CollectionUtils.isNotEmpty(dto.getRiskTrialRetDto())){  
                 List<RiskTrialRetDto> riskTrialRetlist = new ArrayList<>(); 
                 for (RiskTrialRetDto riskTrialRetDto:dto.getRiskTrialRetDto()) {
                    if(StringUtils.equals(riskTrialRetDto.getPortfolioCode(),dto.getPortfolioId())){
                        riskTrialRetlist.add(riskTrialRetDto); 
                    }
                    List<String> list= interfRiskRdsService.queryPortfolioPackageDtlByBagId(riskTrialRetDto.getPortfolioCode());
                    if (!CollectionUtils.isEmpty(list) && list.contains(dto.getPortfolioId())) {
                        riskTrialRetlist.add(riskTrialRetDto); 
                    } 
                 }    
                 dto.setRiskTrialRetDto(riskTrialRetlist);
            }  
        } 
        ProcessParameter<EtrInvestBondInfoDto> processParameter = new ProcessParameter<EtrInvestBondInfoDto>();
        WorkFlowParameter<EtrInvestBondInfoDto> params = new WorkFlowParameter<EtrInvestBondInfoDto>();
        params.setPageId("EtrInvestBondInfo002");
        params.setElementId(dtoList.get(0).getElementId());
        params.setBusinessCode(EtrInvestBondInfoDto::getEntrustId);
        params.setBusinessType(DictCodeList.共通业务类型_债券投资委托.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity -> this.getMessage("EtrInvestBondInfo001") + "(" + entity.getPortfolioId() + ")"
                      + "-" + this.getMessage("EtrInvestBondInfo001.ps") + "("
                      + SysDataUtil.getNameFromDataDict(DictCode.交易方向, entity.getPs()) + ")" + "-"
                      + "(" + this.getMessage("EtrInvestBondInfo001.vdate") + ":"
                      + DateUtil.format(entity.getVdate()) + ")-("
                      + this.getMessage("EtrInvestBondInfo001.mdate") + ":"
                      + DateUtil.format(entity.getMdate()) + ")-"
                      + this.getMessage("EtrInvestBondInfo001.entrustAmt") + ":"
                      + entity.getEntrustNum());
        processParameter.setWorkFlowParameter(params);
        String msg = etrInvestBondInfoService.start(processParameter,
            dtoList.toArray(new EtrInvestBondInfoDto[dtoList.size()]));
        return new AjaxResult(msg);
    }
}
