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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.prod.BussFinProductFeeInfoService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFeeInfoDto;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.SubPageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 产品费用业务组件
 * 
 * @author 何阳阳
 * @version 1.0 2018年9月30日
 * @see BussFinProductFeeInfoCommonRateController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/prod/BussFinProductFeeInfoCommonRate")
// 一体化对应etliu
@SubPageId("BussFinProductFeeInfo")
public class BussFinProductFeeInfoCommonRateController extends BaseController {

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
                    DictCode.计息基础, DictCode.费用品种类型,DictCode.基数日期类型});
     // 一体化对应etliu end

        // 设置画面常亮值，用于JS中的判断
        model.addAttribute("PROFIT_TYPE_01", DictCodeList.收益类型_净值型.getValue());
        model.addAttribute("PROFIT_TYPE_02", DictCodeList.收益类型_收益型.getValue());
        model.addAttribute("PROFIT_TYPE_03", DictCodeList.收益类型_货币型.getValue());
        
        model.addAttribute("PAY_RULE_03", DictCodeList.频率_月.getValue());
        model.addAttribute("PAY_RULE_04", DictCodeList.频率_季.getValue());
        model.addAttribute("PAY_RULE_05", DictCodeList.频率_年.getValue());
        model.addAttribute("PAY_RULE_06", DictCodeList.频率_半年.getValue());
        model.addAttribute("PAY_RULE_08", DictCodeList.频率_不规则.getValue());
        model.addAttribute("PAY_RULE_07", DictCodeList.频率_到期一次.getValue());
        model.addAttribute("CHARGE_TYPE_02", DictCodeList.收费类型_不规则费用.getValue());
        model.addAttribute("BILL_BASE_FEE_KIND_03", DictCodeList.计费基数_费用品种_资产规模.getValue());
        
     // 一体化对应etliu start
//        model.addAttribute("FEE_TYPE_05", DictCodeListExt2.费用类型_浮动差额中收.getValue());
//        model.addAttribute("FEE_TYPE_05NAME", DictCodeListExt2.费用类型_浮动差额中收.getCodeName());
        model.addAttribute("FEE_TYPE_05", DictCodeList.费用品种类型_浮动差额中收.getValue());
        model.addAttribute("FEE_TYPE_05NAME", DictCodeList.费用品种类型_浮动差额中收.getCodeName());
     // 一体化对应etliu end
        
        model.addAttribute("BASIC_BILL_04", DictCodeList.计费基数_费用品种_存续本金.getValue());
        model.addAttribute("BASE_DATE_TYPE_01", DictCodeList.基数日期类型_当日.getValue());
        model.addAttribute("BASE_DATE_TYPE_02", DictCodeList.基数日期类型_前一自然日.getValue());
        model.addAttribute("BASE_DATE_TYPE_03", DictCodeList.基数日期类型_前一工作日.getValue());
        model.addAttribute("profitTypeElement", this.getParam("profitTypeElement"));
        model.addAttribute("dealCalIdElement", this.getParam("dealCalIdElement"));
        model.addAttribute("vdateElement", this.getParam("vdateElement"));
        model.addAttribute("mdateElement", this.getParam("mdateElement"));
        model.addAttribute("finprodMarketIdElement", this.getParam("finprodMarketIdElement"));
        model.addAttribute("ptlIdElement", this.getParam("ptlIdElement"));
        model.addAttribute("entityName", StringUtil.defaultString(getParam("entityName"),
            BussFinProductFeeInfoService.DEFAULT_ENTITY_NAME));

        model.addAttribute("finprodId", this.getParam("finprodId"));
        model.addAttribute(GlobalConstant.PAGE_ID, this.getPageId());
        model.addAttribute(GlobalConstant.WORKFLOW_ID, this.getParam(GlobalConstant.WORKFLOW_ID));
        //add by yejiejie 2019-2-23 start
        model.addAttribute("mdate",this.getParam("mdate"));
        model.addAttribute("vdate",this.getParam("vdate"));
        //add by yejiejie 2019-2-23 end
        model.addAttribute("dealModeElement",this.getParam("dealModeElement"));
        //add by wm 2019-10-09 start 增加是否代办
        model.addAttribute("isTodoList",this.getParam("isTodoList"));
        //add by wm 2019-10-09 end 增加是否代办
        model.addAttribute("DISPOSE_PATTERN_11",DictCodeList.处理模式_分档.getValue());
        //计息基础选择框 20191018 yzg start 
        List<SelectOptionDto> listtype = new ArrayList<SelectOptionDto>();
        SelectOptionDto dto1 = new SelectOptionDto();
        dto1.setId(DictCodeList.计息基础_A_365.getValue());
        dto1.setText(DictCodeList.计息基础_A_365.getCodeName());
        SelectOptionDto dto2 = new SelectOptionDto();
        dto2.setId(DictCodeList.计息基础_A_360.getValue());
        dto2.setText(DictCodeList.计息基础_A_360.getCodeName());
        //BSTOT-1083 产品新建费用时，无法选择计息基础:A/A_D guyunfei 20200213 start
        SelectOptionDto dto3 = new SelectOptionDto();
        dto3.setId(DictCodeListExt1.计息基础_A_A_D.getValue());
        dto3.setText(DictCodeListExt1.计息基础_A_A_D.getCodeName());
        listtype.add(dto1);
        listtype.add(dto2);
        listtype.add(dto3);
        //BSTOT-1083 产品新建费用时，无法选择计息基础:A/A_D guyunfei 20200213 end
        model.addAttribute("BASIS_SELECT", JsonUtils.objectArray2Json(listtype.toArray()));
      //计息基础选择框 20191018 yzg end
        return "component/prod/BussFinProductFeeInfoCommonRate";
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
            if(!StringUtils.equals(finProductFeeInfoDto.getChargeType(), "02")){//add by yejiejie 2019-2-14
	            if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getBasicBill())) {
	                ar.appendMessage(new ValidMessage(
	                    getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFeeInfo.basicBill"))));
	            }
	            if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getBasis())) {
	                ar.appendMessage(new ValidMessage(
	                    getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFeeInfo.basis"))));
	            }
            }
//            if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getProvisionFirstDay())) {
//                ar.appendMessage(new ValidMessage(getMessage("BUSS_COM_E_0038",
//                    getMessage("BussFinProductFeeInfo.provisionFirstDay"))));
//            }
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
//            if (StringUtil.isEmpty(finProductFeeInfoDto.getNetAssetValueDay())) {
//                ar.appendMessage(new ValidMessage(this.getMessage("BUSS_COM_E_0038",
//                    this.getMessage("BussFinProductFeeInfo.netAssetValueDay"))));
//            }
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
            if (!StringUtil.isNotEmpty(finProductFeeInfoDto.geteAccrDate())) {
                ar.appendMessage(new ValidMessage(getMessage("BUSS_COM_E_0038",
                    getMessage("BussFinProductFeeInfo.eAccrDate"))));
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
        if(StringUtils.equals(entityName, ""))entityName="bussFinProductFeeInfoDto";
        String ptlId = this.getParam("ptlId");
        // 快速录入标志
        String quickFlag = this.getParam("quickFlag");
//        if (StringUtil.isNotEmpty(ptlId)){
//            return bussFinProductFeeInfoService.queryFeeInfoByPtlId(ptlId);
//        }
//        else
       if (StringUtil.isNotEmpty(workflowId) && !StringUtil.equals(quickFlag, "true")) {    	
            BussFinProductFeeInfoDto dto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(
                workflowId, entityName, BussFinProductFeeInfoDto.class);
            if (dto != null && !StringUtil.isEmptyList(dto.getBussFinProductFeeInfoDtoList())){
                return dto.getBussFinProductFeeInfoDtoList();
            }
        }
        else if (StringUtil.isNotEmpty(finprodId)) {
            return bussFinProductFeeInfoService.queryFeeInfoByFinprodId(finprodId,null);
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
        return bussFinProductFeeInfoService.generatePayFeePlan(dto);
    }
    
    /**
     * 查询资金账户费用品种信息
     * 
     * @param finprodMarketId 金融产品代码
     * @return 费用品种信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryFundFeeInfoList")
    public List<SelectOptionDto> queryFundFeeInfoList(String finprodMarketId,String acctType) {
        Map<String, Object> map = RequestUtil.getPageParam();
        return bussFinProductFeeInfoService.queryFundFeeInfoList(map);
    }
    
    /**
     * 获取账号信息
     * @return 资金账户实体类
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getCctInfo")
    public StlCapacctInfo getCctInfo(){
        if(bussFinProductFeeInfoService != null){
            return bussFinProductFeeInfoService.getCctInfo(getParam("acctNo"));
        }else{
            return null;
        }
    }
}
