package com.joyintech.tams.invest.entrust.etrlendspecinfo.controller;


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
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.risk.dto.RiskCreditListCommonDto;
import com.joyintech.tams.invest.common.risk.service.RiskCreditListCommonValidatorService;
import com.joyintech.tams.invest.common.trade.CeLinesUpdate;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.CreditLinesDealCommon;
import com.joyintech.tams.invest.entrust.etrlendspecinfo.dto.EtrLendSpecInfoDto;
import com.joyintech.tams.invest.entrust.etrlendspecinfo.service.EtrLendSpecInfoService;
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
 * 拆借类资产多对多指令新建
 *
 * @author gaopeng
 * @version 1.0 2019年12月18日
 * @see EtrLendSpecInfo003Controller
 * @since 1.0
 */

@Controller
@PageId("EtrLendSpecInfo003")
@RequestMapping("/invest/entrust/etrlendspecinfo/EtrLendSpecInfo003")
public class EtrLendSpecInfo003Controller extends BaseController {
	
    /**
     * 业务编号组件
     */
    @Autowired(required=false)
    private BaseGetBusinessId bussGetBussinessIdService;
    /**
     * 存款委托service
     */
    @Autowired
    private EtrLendSpecInfoService etrLendSpecInfoService;

    @Autowired
    private RiskCreditListCommonValidatorService riskCreditListCommonValidatorService;
    
    @Override
    public String init(Model model) {
    Date sysDate = DateUtil.platDateFromCache();
    model.addAttribute("sysDate", DateUtil.format(sysDate));
    //如果是导入
    String tempList = getParam("tempList");
    EtrLendSpecInfoDto dto = etrLendSpecInfoService.getEntrustListData(tempList);
    model.addAttribute(GlobalConstant.ENTITY, dto);
    model.addAttribute("etrLendSpecInfoDtoList", StringUtil.isEmptyList(
        dto.getSelectedItem()) ? null : JsonUtils.object2Json(dto.getSelectedItem()));
    model.addAttribute("sysDate",DateUtil.format(sysDate));
    model.addAttribute("S01", DictCodeList.拆借类型_普通拆借.getValue());
    model.addAttribute("S02", DictCodeList.拆借类型_特殊目的载体.getValue());
    model.addAttribute("T32", DictCodeList.回购拆借方向_拆入.getValue());
    model.addAttribute("T32name", DictCodeList.回购拆借方向_拆入.getCodeName());
    model.addAttribute("T33", DictCodeList.回购拆借方向_拆出.getValue());
    model.addAttribute("T33name", DictCodeList.回购拆借方向_拆出.getCodeName());
    model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_委托监控.getValue());
    model.addAttribute("fintype_cr", DictCodeListExt1.金融产品类型_投管用_拆入.getValue());
    model.addAttribute("fintype_cc", DictCodeListExt1.金融产品类型_投管用_拆出.getValue());
    model.addAttribute("fintype_tccr", DictCodeListExt1.金融产品类型_投管用_特拆拆入.getValue());
    return super.init(model);
    }
    
    /**
     * 导入时的check
     *
     * @param map dto
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("checkImportData")
    public AjaxResult checkImportData(String fileName) {
        return etrLendSpecInfoService.checkImportData(fileName);
    }
    
    
    /**
     * 保存按钮点击时的check
     *
     * @param List<EtrLendSpecInfoDto> dtoList
     * @param BindingResult result
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody List<EtrLendSpecInfoDto> dtoList, BindingResult result) {
        List<ValidMessage> messages = new ArrayList<>();
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
                	TrdProductDeal trdProductDeal = etrLendSpecInfoService.prepareDeal(dtoList.get(i));
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
        return commonCheck(result, dtoList.get(0).getOperateType());
    }
    /**
     * 批量提交
     * 
     * @param List<EtrConBondDto> dtoList
     * @param String msg
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody List<EtrLendSpecInfoDto> dtoList, BindingResult br)
        throws Exception {
        AjaxResult ajaxResult = this.check(dtoList, br);
        if (!StringUtil.isEmptyList(ajaxResult.getErrorList())) {
            return ajaxResult;
        }
        for(EtrLendSpecInfoDto dto: dtoList) {
            if(!StringUtil.isNotEmpty(dto.getEntrustId())){
                BussinessCodeParameter param = new BussinessCodeParameter();
                param.setCommonBusinessType(DictCodeList.共通业务类型_拆借投资委托);
                param.setType1(dto.getAssetType1().equals(DictCodeList.拆借类型_普通拆借.getValue()) ? DictCodeList.业务编号一级分类_普通拆借:DictCodeList.业务编号一级分类_特殊拆借);
                String code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(param):IDGenerator.generate();
                dto.setEntrustId(code);
            }
            dto.getEtrInvestPersonInfo().setInvManagerId(dto.getCreateUser());
            dto.getEtrInvestPersonInfo().setRoom(dto.getCreateDept());
            dto.setWorkflowParameter("new|||1|EtrLendSpecInfo003|EtrLendSpecInfo003_btn_submit|true");
        }
        ProcessParameter<EtrLendSpecInfoDto> processParameter = new ProcessParameter<EtrLendSpecInfoDto>();
        WorkFlowParameter<EtrLendSpecInfoDto> params = new WorkFlowParameter<EtrLendSpecInfoDto>();
        params.setPageId("EtrLendSpecInfo002");
        params.setElementId(dtoList.get(0).getElementId());
        params.setBusinessCode(item->item.getEntrustId());
        params.setBusinessType(DictCodeList.共通业务类型_拆借投资委托.getValue());
     // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity -> this.getMessage("EtrLendSpecInfo001") + "(" + entity.getPortfolioId() + ")"
                      + "-" + this.getMessage("EtrLendSpecInfo001.ps")+"("+SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向, entity.getPs())+")" + "-" + "("
                      + this.getMessage("EtrLendSpecInfo001.vdate") + ":" + DateUtil.format(entity.getVdate())
                      + ")-(" + this.getMessage("EtrLendSpecInfo001.mdate") + ":"
                      + DateUtil.format(entity.getMdate()) + ")-"
                      + this.getMessage("EtrLendSpecInfo001.entrustAmt") + ":"
                      + entity.getEntrustAmt());
        processParameter.setWorkFlowParameter(params);
        String msg = etrLendSpecInfoService.start(processParameter, dtoList.toArray(new EtrLendSpecInfoDto[dtoList.size()]));
        return new AjaxResult(msg);
    }
    
}
