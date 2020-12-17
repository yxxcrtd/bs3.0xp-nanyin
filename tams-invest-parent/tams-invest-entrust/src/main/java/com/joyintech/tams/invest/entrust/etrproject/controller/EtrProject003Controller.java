package com.joyintech.tams.invest.entrust.etrproject.controller;

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
import com.joyintech.tams.invest.entrust.etrproject.dto.EtrProjectDto;
import com.joyintech.tams.invest.entrust.etrproject.service.EtrProjectService;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;
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
 * 项目类多对多指令新建
 * 〈功能详细描述〉
 * @author zhuyongkang
 * @version 1.0 2019年12月30日
 * @see EtrProject003Controller
 * @since 1.0
 */
@Controller
@PageId("EtrProject003")
@RequestMapping("/invest/entrust/etrproject/EtrProject003")
public class EtrProject003Controller extends BaseController {
    
    /**
     * 业务编号组件
     */
    @Autowired(required=false)
    private BaseGetBusinessId bussGetBussinessIdService;

    /**
     * 计划类净值委托service
     */
    @Autowired
    private EtrProjectService etrProjectService;

    @Autowired
    private CommonBusinessService commonBusinessService;
    
    @Autowired
    private RiskCreditListCommonValidatorService riskCreditListCommonValidatorService;
    /**
     * 页面初始化
     */
    @Override
    public String init(Model model) {
        
        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.项目类一级分类);
        this.setSelectOptionToModel(model, DictCode.交易方向);
        this.setSelectOptionToModel(model, DictCode.行内资产二级分类, DictCode.行内资产三级分类, DictCode.行内资产四级分类);
        Date sysDate = DateUtil.platDateFromCache();
        model.addAttribute("sysDate",DateUtil.format(sysDate));
        model.addAttribute("T01", DictCodeList.交易方向_买入.getValue());
        model.addAttribute("T02", DictCodeList.交易方向_卖出.getValue());
        String operateType = getParam("operateType");
        RequestUtil.getPageParam();
        //导入
        if (StringUtil.equals(operateType,"import")) {
        String tempList = getParam("tempList");
        List<EtrProjectDto> entrustListData= etrProjectService.getEtrDoubleProjectDtoListData(tempList);
        model.addAttribute("etrProjectDtoList", StringUtil.isEmptyList(entrustListData) ? null : JsonUtils.object2Json(entrustListData));
        }
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_委托监控.getValue());
        model.addAttribute("fintype", DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));
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
    public AjaxResult check(@RequestBody  List<EtrProjectDto> dtoList, BindingResult result) {
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
                	TrdProductDeal trdProductDeal = etrProjectService.prepareDeal(dtoList.get(i));
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
            EtrProjectDto dto = dtoList.get(i);
            ValidationUtil.ValidResult itemResult = ValidationUtil.validateBean(dto,
                    ValidateGroupEdit.class);

            // 授信软控 start
            dtoList.get(i).setPrinAmt(recode);
            // 授信软控 end
            // 可用现金软校验
            if (StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_CASH_SWITCH),
                    DictCodeListExt1.是或否_是.getValue())
                    && (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue()))
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
                    && (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue()))
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
     *excel导入数据check
     *
     * @param map dto
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("checkImportData")
    public AjaxResult checkImportData(String fileName) {
        return etrProjectService.checkImportData(fileName);
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
    public AjaxResult save(@RequestBody List<EtrProjectDto> dtoList, BindingResult br)
        throws Exception {
        for(EtrProjectDto dto:dtoList) {
            if (!StringUtil.isNotEmpty(dto.getEntrustId())) {
                BussinessCodeParameter param = new BussinessCodeParameter();
                param.setCommonBusinessType(DictCodeList.共通业务类型_项目类资产投资委托);
                String code = bussGetBussinessIdService != null ? bussGetBussinessIdService.buildBussinessCode(param) : IDGenerator.generate();
                dto.setEntrustId(code);
            }
            dto.setWorkflowParameter("new|||1|EtrProject002|EtrProject002_btn_submit|true");
        }
        ProcessParameter<EtrProjectDto> processParameter = new ProcessParameter<EtrProjectDto>();
        WorkFlowParameter<EtrProjectDto> params = new WorkFlowParameter<EtrProjectDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dtoList.get(0).getElementId());
        params.setBusinessCode(EtrProjectDto::getEntrustId);
        params.setBusinessType(DictCodeList.共通业务类型_项目类资产投资委托.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity -> this.getMessage("EtrProject001") + "(" + entity.getPortfolioId() + ")"
                      + "-" + this.getMessage("EtrProject001.ps") + "-" + "("
                      + this.getMessage("EtrProject001.vdate") + ":" +DateUtil.format(entity.getVdate())
                      + ")-(" + this.getMessage("EtrProject001.mdate") + ":"
                      +DateUtil.format(entity.getMdate())+ ")-"
                      + this.getMessage("EtrProject001.entrustAmt") + ":"
                      + entity.getEntrustAmt());
        processParameter.setWorkFlowParameter(params);
        String msg = etrProjectService.start(processParameter, dtoList.toArray(new EtrProjectDto[dtoList.size()]));
        return new AjaxResult(msg);
    }
}
