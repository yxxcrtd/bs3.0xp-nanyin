package com.joyintech.tams.invest.entrust.etrstock.controller;

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
import com.joyintech.tams.constant.*;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.risk.dto.RiskCreditListCommonDto;
import com.joyintech.tams.invest.common.trade.CeLinesUpdate;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.CreditLinesDealCommon;
import com.joyintech.tams.invest.entrust.etrstock.dto.EtrStockDto;
import com.joyintech.tams.invest.entrust.etrstock.service.EtrStockService;
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
    
    @Autowired
    private CommonBusinessService commonBusinessService;

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
                	TrdProductDeal trdProductDeal = etrStockService.prepareDeal(dtoList.get(i));
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
            EtrStockDto dto = dtoList.get(i);
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
     // 授信软控 start
        if (messages.size() > 0 || joyinMessages.size() > 0) {
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
        params.setPageId("EtrStock002");
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
