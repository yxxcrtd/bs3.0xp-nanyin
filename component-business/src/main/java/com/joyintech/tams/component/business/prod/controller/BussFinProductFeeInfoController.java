/*
 * 文件名：BussFinProductFeeInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年9月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.SubPageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.prod.BussFinProductFeeInfoService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFeeInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 产品费用业务组件
 * 
 * @author 何阳阳
 * @version 1.0 2018年9月30日
 * @see BussFinProductFeeInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/prod/BussFinProductFeeInfo")
// 一体化对应etliu
@SubPageId("BussFinProductFeeInfo")
public class BussFinProductFeeInfoController extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private BussFinProductFeeInfoService bussFinProductFeeInfoService;

    /**
     * {@linkplain}
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * {@linkplain}
     */
    @Autowired
    private DateCalService dateCalService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
    	// 一体化对应etliu start
//        setSelectOptionToModel(model,
//            new DictCode[] {DictCode.资产净值日类型, DictCode.付费规则,
//                DictCode.营业日准则, DictCode.费用类型, DictCode.计费基数_费用品种,
//                DictCode.计息基础});
    	setSelectOptionToModel(model,
                new DictCode[] {DictCode.基数日期类型, DictCode.频率,
                    DictCode.营业日准则, DictCode.费用类型, DictCode.计费基数_费用品种,
                    DictCode.计息基础, DictCode.费用品种类型/*,DictCode.收取方式*/});
     // 一体化对应etliu end

        // 设置画面常亮值，用于JS中的判断
        model.addAttribute("PROFIT_TYPE_01", DictCodeList.收益类型_净值型.getValue());
        model.addAttribute("PROFIT_TYPE_02", DictCodeList.收益类型_收益型.getValue());
        model.addAttribute("PROFIT_TYPE_03", DictCodeList.收益类型_货币型.getValue());
        
     // 一体化对应etliu start
//        model.addAttribute("PAY_RULE_09", DictCodeList.付费规则_不规则.getValue());
//        model.addAttribute("PAY_RULE_01", DictCodeList.付费规则_到期.getValue());
        model.addAttribute("PAY_RULE_09", DictCodeList.频率_不规则.getValue());
        model.addAttribute("PAY_RULE_01", DictCodeList.频率_到期一次.getValue());
     // 一体化对应etliu end
        
     // 一体化对应etliu start
//        model.addAttribute("FEE_TYPE_05", DictCodeListExt2.费用类型_浮动差额中收.getValue());
//        model.addAttribute("FEE_TYPE_05NAME", DictCodeListExt2.费用类型_浮动差额中收.getCodeName());
        model.addAttribute("FEE_TYPE_05", DictCodeList.费用品种类型_浮动差额中收.getValue());
        model.addAttribute("FEE_TYPE_05NAME", DictCodeList.费用品种类型_浮动差额中收.getCodeName());
     // 一体化对应etliu end

        model.addAttribute("profitTypeElement", this.getParam("profitTypeElement"));
        model.addAttribute("dealCalIdElement", this.getParam("dealCalIdElement"));
        model.addAttribute("vdateElement", this.getParam("vdateElement"));
        model.addAttribute("mdateElement", this.getParam("mdateElement"));
        model.addAttribute("finprodMarketIdElement", this.getParam("finprodMarketIdElement"));
        model.addAttribute("ptlIdElement", this.getParam("ptlIdElement"));
        model.addAttribute("entityName", StringUtil.defaultString(getParam("entityName"),
            BussFinProductFeeInfoService.DEFAULT_ENTITY_NAME));
        //通道管理  zhangnian start 
        model.addAttribute("finprodType", StringUtil.defaultString(this.getParam("FINPROD_TYPE_F28"),StringUtil.EMPTY));
        model.addAttribute("FINPROD_TYPE_F28", DictCodeListExt1.金融产品类型_投管用_通道.getValue());
      //通道管理 zhangnian end
        model.addAttribute("finprodId", this.getParam("finprodId"));
        model.addAttribute(GlobalConstant.PAGE_ID, this.getPageId());
        model.addAttribute(GlobalConstant.WORKFLOW_ID, this.getParam(GlobalConstant.WORKFLOW_ID));
        model.addAttribute("BASE_DATE_TYPE_01", DictCodeList.基数日期类型_当日.getValue());
        model.addAttribute("BASE_DATE_TYPE_02", DictCodeList.基数日期类型_前一自然日.getValue());
        model.addAttribute("BILL_BASE_FEE_KIND_03", DictCodeList.计费基数_费用品种_资产规模.getValue());
        model.addAttribute("BASIC_BILL_04", DictCodeList.计费基数_费用品种_存续本金.getValue());
        return "component/prod/BussFinProductFeeInfo";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPageId() {
        return this.getParam(GlobalConstant.PAGE_ID);
    }

    /**
     * 查询费用品种信息
     * 
     * @param finprodMarketId 金融产品代码
     * @return 费用品种信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryFeeInfoList")
    public List<SelectOptionDto> queryFeeInfoList(String finprodMarketId) {
        Map<String, Object> map = RequestUtil.getPageParam();
        return bussFinProductFeeInfoService.queryFeeInfoList(map);
    }
    
    /**
     * 根据费用代码查询费用基本信息
     * 
     * @param feeId 费用代码
     * @param finprodMarketId 市场代码
     * @return 费用基本信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryMstFeeinfoByFeeId")
    public BussFinProductFeeInfoDto queryMstFeeinfoByFeeId(String feeId, String finprodMarketId) {
        return bussFinProductFeeInfoService.queryMstFeeinfoByFeeId(feeId, finprodMarketId);
    }

    /**
     * 生成付费计划的check
     * 
     * @param finProductFeeInfoDto 产品费用信息
     * @return 验证结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("checkPreview")
    public AjaxResult checkPreview(@RequestBody BussFinProductFeeInfoDto finProductFeeInfoDto) {
        AjaxResult ar = new AjaxResult();

     // 一体化对应etliu start
//        if (StringUtil.isNotEmpty(finProductFeeInfoDto.getPayFeeRule())
//            && (StringUtil.equals(finProductFeeInfoDto.getPayFeeRule(),
//                DictCodeList.付费规则_不规则.getValue()))) {
        if (StringUtil.isNotEmpty(finProductFeeInfoDto.getPayFeeRule())
            && (StringUtil.equals(finProductFeeInfoDto.getPayFeeRule(),
                DictCodeList.频率_不规则.getValue()))) {
         // 一体化对应etliu end
            ar.appendMessage(new ValidMessage(getMessage("FIN_PROD_E_0070")));
        }
        else {
            if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getFeeId())) {
                ar.appendMessage(new ValidMessage(
                    getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFeeInfo.feeName"))));
            }
            if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getFeeType())) {
                ar.appendMessage(new ValidMessage(
                    getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFeeInfo.feeType"))));
            }
            if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getFeeRate())) {
                ar.appendMessage(new ValidMessage(
                    getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFeeInfo.feeRate"))));
            }
            if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getBasicBill())) {
                ar.appendMessage(new ValidMessage(
                    getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFeeInfo.basicBill"))));
            }
            if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getBasis())) {
                ar.appendMessage(new ValidMessage(
                    getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFeeInfo.basis"))));
            }
            if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getProvisionFirstDay())) {
                ar.appendMessage(new ValidMessage(getMessage("BUSS_COM_E_0038",
                    getMessage("BussFinProductFeeInfo.provisionFirstDay"))));
            }
            if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getPayFeeRule())) {
                ar.appendMessage(new ValidMessage(getMessage("BUSS_COM_E_0038",
                    getMessage("BussFinProductFeeInfo.payFeeRule"))));
            }
            if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getCalcFeeTradeRule())) {
                ar.appendMessage(new ValidMessage(getMessage("BUSS_COM_E_0038",
                    getMessage("BussFinProductFeeInfo.calcFeeTradeRule"))));
            }
            if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getPayFeeTradeRule())) {
                ar.appendMessage(new ValidMessage(getMessage("BUSS_COM_E_0038",
                    getMessage("BussFinProductFeeInfo.payFeeTradeRule"))));
            }
            if (StringUtil.isEmpty(finProductFeeInfoDto.getNetAssetValueDay())) {
                ar.appendMessage(new ValidMessage(this.getMessage("BUSS_COM_E_0038",
                    this.getMessage("BussFinProductFeeInfo.netAssetValueDay"))));
            }
            if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getDealCalId())) {
                ar.appendMessage(new ValidMessage(
                    getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFeeInfo.dealCalId"))));
            }
            if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getVdate())) {
                ar.appendMessage(new ValidMessage(
                    getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFeeInfo.vdate"))));
            }
            if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getMdate())) {
                ar.appendMessage(new ValidMessage(
                    getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFeeInfo.mdate"))));
            }
            if (StringUtil.isNotEmpty(finProductFeeInfoDto.getVdate())
                && StringUtil.isNotEmpty(finProductFeeInfoDto.getMdate())) {
                if (DateUtil.compareDate(finProductFeeInfoDto.getVdate(),
                    finProductFeeInfoDto.getMdate()) >= 0) {
                    ar.appendMessage(new ValidMessage(
                        getMessage("BUSS_COM_E_0045", getMessage("BussFinProductFeeInfo.vdate"),
                            getMessage("BussFinProductFeeInfo.mdate"))));
                }
            }
            if (StringUtil.isNotEmpty(finProductFeeInfoDto.getProvisionFirstDay())) {
                if (StringUtil.isNotEmpty(finProductFeeInfoDto.getVdate())
                    && DateUtil.compareDate(finProductFeeInfoDto.getProvisionFirstDay(),
                        finProductFeeInfoDto.getVdate()) < 0) {
                    ar.appendMessage(new ValidMessage(this.getMessage("BUSS_COM_E_0004",
                        this.getMessage("BussFinProductFeeInfo.provisionFirstDay"),
                        this.getMessage("FinProductBuild002.vdate"))));
                }
                if (StringUtil.isNotEmpty(finProductFeeInfoDto.getMdate())
                    && DateUtil.compareDate(finProductFeeInfoDto.getProvisionFirstDay(),
                        finProductFeeInfoDto.getMdate()) > 0) {
                    ar.appendMessage(new ValidMessage(this.getMessage("FIN_PROD_E_0017",
                        this.getMessage("BussFinProductFeeInfo.provisionFirstDay"),
                        this.getMessage("FinProductBuild002.mdate"))));
                }
            }
            if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getProfitType())) {
                ar.appendMessage(new ValidMessage(getMessage("BUSS_COM_E_0038",
                    getMessage("BussFinProductFeeInfo.profitType"))));
            }
        }

        if (StringUtil.isEmptyList(ar.getErrorList())
            && StringUtil.isNotEmpty(finProductFeeInfoDto.getProvisionFirstDay())) {
            if (dateCalService.isDealCalendarHoliday(finProductFeeInfoDto.getProvisionFirstDay(),
                finProductFeeInfoDto.getDealCalId())) {
                ar.setData(this.getMessage("BUSS_COM_E_0039",
                    this.getMessage("BussFinProductFeeInfo.provisionFirstDay")));
            }
        }

        return ar;
    }

    /**
     * 查询费用信息列表
     * 
     * @return 费用信息列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getFeeInfo")
    public List<BussFinProductFeeInfoDto> getFeeInfo() {
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        String finprodId = this.getParam("finprodId");
        String entityName = StringUtil.defaultString(this.getParam("entityName"),
            BussFinProductFeeInfoService.DEFAULT_ENTITY_NAME);
        String ptlId = this.getParam("ptlId");
        // 快速录入标志
        String quickFlag = this.getParam("quickFlag");
        //通道 nzhang
        String finprodType2 = this.getParam("finprodType");
        if (StringUtil.isNotEmpty(ptlId)){
            return bussFinProductFeeInfoService.queryFeeInfoByPtlId(ptlId);
        }
        else if (StringUtil.isNotEmpty(workflowId) && !StringUtil.equals(quickFlag, "true")) {
            BussFinProductFeeInfoDto dto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(
                workflowId, entityName, BussFinProductFeeInfoDto.class);
            if (dto != null){
                if (dto.getBussFinProductFeeInfoDtoList() != null && !dto.getBussFinProductFeeInfoDtoList().isEmpty()) {
                    dto.getBussFinProductFeeInfoDtoList().forEach(info -> {
//                        info.setBasicBill(info.getBasicBillStr());
                        info.setBasis(info.getBasisStr());
//                        info.setFeeType(info.getFeeTypeStr());
                    });
                }
                return dto.getBussFinProductFeeInfoDtoList();
            }
        }
        else if (StringUtil.isNotEmpty(finprodId)) {
            return bussFinProductFeeInfoService.queryFeeInfoByFinprodId(finprodId,finprodType2);
        }
        return Collections.emptyList();
    }

    /**
     * 生成费用计划
     * 
     * @return 费用计划
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("generatePayFeePlan")
    public List<FinCashPlan> generatePayFeePlan() {
        BussFinProductFeeInfoDto dto = BeanUtil.mapToObject(RequestUtil.getPageParam(),
            BussFinProductFeeInfoDto.class);
      //付费规则调整页面生成付费计划 20191130 yzg start
        if(StringUtil.equals(this.getParam("parentPageId"), "FinPayPlanAdjustment002")) {
        	String planStartDay = this.getParam("planStartDay");
        	String planEndDay = this.getParam("planEndDay");
        	if(StringUtil.isEmpty(planStartDay) || StringUtil.isEmpty(planEndDay)) {
        		dto.setVdate(dto.getProvisionFirstDay());
        		return bussFinProductFeeInfoService.generatePayFeePlan(dto);
        	}
        	return bussFinProductFeeInfoService.finCashPlanAdjustment(dto, DateUtil.parse(planStartDay), DateUtil.parse(planEndDay));
        }
        //付费规则调整页面生成付费计划 20191130 yzg end
        return bussFinProductFeeInfoService.generatePayFeePlan(dto);
    }
    
    /**
     *  利率调整 选完费用后请求
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryMstFeeinfoForRateAdjByFeeId")
    public BussFinProductFeeInfoDto queryMstFeeinfoForRateAdjByFeeId() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return bussFinProductFeeInfoService.queryMstFeeinfoForRateAdjByFeeId(param);
    }
}
