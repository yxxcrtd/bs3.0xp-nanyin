package com.joyintech.tams.invest.entrust.etrbrowdepoinfo.controller;


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
import com.joyintech.tams.invest.entrust.etrbrowdepoinfo.dto.EtrBrowDepoInfoDto;
import com.joyintech.tams.invest.entrust.etrbrowdepoinfo.service.EtrBrowDepoInfoService;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;
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
 * 存款类资产多对多指令新建
 *
 * @author gaopeng
 * @version 1.0 2019年12月18日
 * @see EtrBrowDepoInfo003Controller
 * @since 1.0
 */

@Controller
@PageId("EtrBrowDepoInfo003")
@RequestMapping("/invest/entrust/etrbrowdepoinfo/EtrBrowDepoInfo003")
public class EtrBrowDepoInfo003Controller extends BaseController {

    /**
     * 业务编号组件
     */
    @Autowired(required = false)
    private BaseGetBusinessId bussGetBussinessIdService;
    /**
     * 存款委托service
     */
    @Autowired
    private EtrBrowDepoInfoService etrBrowDepoInfoService;

    @Autowired
    private CommonBusinessService commonBusinessService;

    @Autowired
    private RiskCreditListCommonValidatorService riskCreditListCommonValidatorService;

    @Override
    public String init(Model model) {
        Date sysDate = DateUtil.platDateFromCache();
        model.addAttribute("sysDate", DateUtil.format(sysDate));
            //如果是导入
    String tempList = getParam("tempList");
    EtrBrowDepoInfoDto dto = etrBrowDepoInfoService.getEntrustListData(tempList);
    model.addAttribute(GlobalConstant.ENTITY, dto);
    model.addAttribute("etrBrowDepoInfoDtoList", StringUtil.isEmptyList(
          dto.getSelectedItem()) ? null : JsonUtils.object2Json(dto.getSelectedItem()));
    model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_委托监控.getValue());
    model.addAttribute("T01",DictCodeList.交易方向_买入.getValue());
    model.addAttribute("T02",DictCodeList.交易方向_卖出.getValue());
    model.addAttribute("fintype", DictCodeListExt1.金融产品类型_投管用_存款.getValue());
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
        return etrBrowDepoInfoService.checkImportData(fileName);
    }

    /**
     * 保存按钮点击时的check
     *
     * @param List<EtrConBondDto> dtoList
     * @param BindingResult       result
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody List<EtrBrowDepoInfoDto> dtoList, BindingResult result) {
        List<ValidMessage> messages = new ArrayList<>();
        // 授信软控 start
        List<JoyinMessage> joyinMessages = new ArrayList<>();
        Map<String, BigDecimal> cusMap = new HashMap<String, BigDecimal>();
        // 授信软控 end
        for (int i = 0; i < dtoList.size(); i++) {
            // 授信软控 start
            BigDecimal recode = dtoList.get(i).getPrinAmt();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("finprodId", dtoList.get(i).getFinprodId());
            String customerId = commonBusinessService.selectCustomer(map);
            if (customerId != null) {
                if (cusMap.get(customerId) == null) {
                    cusMap.put(customerId, dtoList.get(i).getPrinAmt());
                } else {
                    dtoList.get(i).setPrinAmt(cusMap.get(customerId).add(dtoList.get(i).getPrinAmt()));
                    cusMap.put(customerId, dtoList.get(i).getPrinAmt());
                }
            }

            boolean isCreditSoftControl = Boolean.parseBoolean(CacheUtil.getSysParam("IS_CREDIT_SOFTCONTROL"));
            RiskCreditListCommonDto riskCreditListCommonDto = new RiskCreditListCommonDto();
            if (!StringUtil.equals(BussinessDictCodeList.监控授信开关_关.getValue(), CacheUtil.getSysParam(BussinessConstant.ISCREDIT))
                    && isCreditSoftControl) {
//                if (!DictCodeListExt3.授信来源_外部.getValue().equals(dtoList.get(i).getCreditSource())) {
                    TrdProductDeal trdProductDeal = etrBrowDepoInfoService.prepareDeal(dtoList.get(i));
                    if (trdProductDeal.getCounterId() == null) {
                        trdProductDeal.setCounterId("");
                    }
                    CeLinesUpdate ceLinesUpdate = SpringContextUtil.getBean(CeLinesUpdate.class);
                    AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
                    inputDto.setTrdProductDeal(trdProductDeal);
                    List<CreditLinesDealCommon> list = ceLinesUpdate.isNeedOccupyCre(inputDto, true, false, false);
                    if (list != null && list.size() > 0) {
                        if (list.get(0).getErrMessage() != null && getMessage("BUSS_COM_CRE_001").equals(list.get(0).getErrMessage())) {
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
            } else if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SUBMIT, dtoList.get(0).getOperateType())) {
                btnName = "SysCommon.submit";
            } else if (StringUtil.equalsIgnoreCase("revoke", dtoList.get(0).getOperateType())) {
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
     * @param String              msg
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody List<EtrBrowDepoInfoDto> dtoList, BindingResult br)
            throws Exception {
        AjaxResult ajaxResult = this.check(dtoList, br);
        if (!StringUtil.isEmptyList(ajaxResult.getErrorList())) {
            return ajaxResult;
        }
        for (EtrBrowDepoInfoDto dto : dtoList) {
            if (!StringUtil.isNotEmpty(dto.getEntrustId())) {
                BussinessCodeParameter param = new BussinessCodeParameter();
                param.setCommonBusinessType(DictCodeList.共通业务类型_存款类资产投资委托);
                param.setType1(StringUtil.equals(dto.getFinprodType2(), DictCodeList.存款类资产类型_同业借款.getValue())
                        ? DictCodeList.业务编号一级分类_同业借款 : DictCodeList.业务编号一级分类_存放同业);
                String code = bussGetBussinessIdService != null ? bussGetBussinessIdService.buildBussinessCode(param) : IDGenerator.generate();
                //画面.资产类型==数据字典缓存.存款类资产类型.同业借款？数据字典缓存.业务编号一级分类.同业借款:数据字典缓存.业务编号一级分类.存放同业
                dto.setEntrustId(code);
            }
            dto.getBussEtrInvestPersonInfoDto().setInvManagerId(dto.getCreateUser());
            dto.getBussEtrInvestPersonInfoDto().setRoom(dto.getCreateDept());
            dto.setWorkflowParameter("new|||1|EtrBrowDepoInfo003|EtrBrowDepoInfo003_btn_submit|true");
        }
        ProcessParameter<EtrBrowDepoInfoDto> processParameter = new ProcessParameter<EtrBrowDepoInfoDto>();
        WorkFlowParameter<EtrBrowDepoInfoDto> params = new WorkFlowParameter<EtrBrowDepoInfoDto>();
        params.setPageId("EtrBrowDepoInfo002");
        params.setElementId(dtoList.get(0).getElementId());
        params.setBusinessCode(item -> item.getEntrustId());
        params.setBusinessType(DictCodeList.共通业务类型_存款类资产投资委托.getValue());
        // 设置流程业务说明, entity即传入的dto
        //业务说明：存款委托投资+"("+组合代码+")"+"-"+交易方向+"-"+"（委托开始日："+委托开始日+"）-"+"（委托结束日："+委托结束日+"）-"+"金额："+金额"
        params.setComment(
                entity -> getMessage("EtrBrowDepoInfo001") + "(" + entity.getPortfolioId() + ")"
                        + "-" + getMessage("EtrBrowDepoInfo001.ps") + "(" + entity.getPsStr() + ")" + "-" +
                        "(" + getMessage("EtrBrowDepoInfo001.entrustVDate") + "：" + DateUtil.format(entity.getVdate()) + ")"
                        + "-(" + getMessage("EtrBrowDepoInfo001.entrustMDate") + "：" + DateUtil.format(entity.getMdate()) + ")-" + getMessage("EtrBrowDepoInfo001.entrustAmt") + "：" + entity.getEntrustAmt());
        processParameter.setWorkFlowParameter(params);
        //dto.getBussEtrInvestPersonInfoDto().setInvManagerId(SecurityUtil.getLoginUser().getUserName());
        String msg = etrBrowDepoInfoService.start(processParameter, dtoList.toArray(new EtrBrowDepoInfoDto[dtoList.size()]));
        return new AjaxResult(msg);
    }

}
