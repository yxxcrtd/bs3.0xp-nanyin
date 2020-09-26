/*
 * 文件名：ValBussFinRepaymentController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.component.element.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.SubPageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.tams.valuation.component.common.dto.FinCashPlanDto;
import com.joyintech.tams.valuation.component.common.service.ValCommonBusinessService;
import com.joyintech.tams.valuation.component.element.dto.ValBussFinRepaymentDto;
import com.joyintech.tams.valuation.component.element.dto.ValFinPayInterestStdInfoDto;
import com.joyintech.tams.valuation.component.element.interf.ValBussFinRepaymentService;
import com.joyintech.tams.valuation.component.element.interf.ValFinInterestFactorService;
import com.joyintech.tams.valuation.component.element.interf.ValFinPayInterestStdInfoService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 还本信息--业务组建
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月14日
 * @see ValBussFinRepaymentController
 * @since 1.0
 */
@Controller
@RequestMapping("/valuation/component/element/ValBussFinRepayment")
//一体化对应etliu
@SubPageId("ValBussFinRepayment")
public class ValBussFinRepaymentController extends BaseController{

    @Autowired
    private ValBussFinRepaymentService bussFinRepaymentService;
    
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    
    @Autowired
    private ValCommonBusinessService commonBusinessService;
    
    /**
     * 付息信息
     */
    @Autowired(required = false)
    private ValFinPayInterestStdInfoService finPayInterestStdInfoService;
    @Override
    public String init(Model model) {
        model.addAttribute("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        model.addAttribute("FINPROD_TYPE_TG_F07",DictCodeListExt1.金融产品类型_投管用_存款.getValue());
        model.addAttribute("FINPROD_TYPE_TG_F17",DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        model.addAttribute("FINPROD_TYPE_TG_F18",DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        model.addAttribute("COUPON_SPECIES_01", DictCodeList.息票品种_附息.getValue());
        //还本周期
        model.addAttribute("REPAYCYCLE", createIntpayCycle());
        // 设置画面初始值
        String entityName = StringUtil.defaultString(this.getParam("entityName"),
            ValBussFinRepaymentService.DEFAULT_ENTITY_NAME);
        setEntity(model);
        //计息要素entityName
        String entityNameFinInterestFactor = StringUtil.defaultString(this.getParam("entityNameFinInterestFactor"), ValFinInterestFactorService.DEFAULT_ENTITY_NAME);
        //付息信息entityName
        String entityNameFinPayInterestStdInfo = StringUtil.defaultString(this.getParam("entityNameFinPayInterestStdInfo"), ValFinPayInterestStdInfoService.DEFAULT_ENTITY_NAME);
        model.addAttribute("INT_ASSET_TYPE", this.getParam("int_asset_type"));
        //int_asset_type_payinterest:区分付息计划页面的入口参数(无:表示新建  payinterest:表示付息计划)
        model.addAttribute("INT_ASSET_TYPE_REPAY", this.getParam("int_asset_type_repay"));
      //共通业务类型--付息计划调整
        model.addAttribute("COMMON_BUSINESS_TYPE_FINPROD10", DictCodeList.共通业务类型_付息计划调整.getValue());
        model.addAttribute("entityName", entityName);
        model.addAttribute("entityNameFinInterestFactor", entityNameFinInterestFactor);
        model.addAttribute("entityNameFinPayInterestStdInfo", entityNameFinPayInterestStdInfo);
        model.addAttribute("YES", DictCodeList.是否_是.getValue());
        model.addAttribute("COMMON_BUSINESS_TYPE_F03", DictCodeList.平安资产类型_计划类资产_利率型.getValue());
        model.addAttribute("COMMON_BUSINESS_TYPE", this.getParam("common_business_type"));
        return "/valuation/component/element/ValBussFinRepayment";
    }
    
    
    private void setEntity(Model model) {
        String finprodId = this.getParam("finprodId");
        String workflowId = this.getParam("workflowId");
        String vdate = this.getParam("vdate");
        String entityName = StringUtil.defaultString(this.getParam("entityName"), ValBussFinRepaymentService.DEFAULT_ENTITY_NAME);
        // 不来自于代办
        if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(finprodId)) {
            // 根据主键获取业务实体--付息信息
            ValBussFinRepaymentDto bussFinRepaymentDto = new ValBussFinRepaymentDto();
            if(null!=finPayInterestStdInfoService){
                ValFinPayInterestStdInfoDto finPayInterestStdInfoDto = finPayInterestStdInfoService.getFinPayInterestStdInfoByProductId(
                    finprodId, vdate, DictCodeListExt1.现金流二级类型_本金.getValue(), DictCodeListExt1.现金流业务类型_金融产品.getValue());
                if(null!=finPayInterestStdInfoDto){
                    bussFinRepaymentDto.setPayCycle(finPayInterestStdInfoDto.getPayCycle());
                    bussFinRepaymentDto.setRemark(finPayInterestStdInfoDto.getRemark());
                    bussFinRepaymentDto.setIsPayInt(finPayInterestStdInfoDto.getIsPayInt());//界面是否还本不带息 add by zhouxiuping
                    if(finPayInterestStdInfoDto.getIsPayInt().equals(DictCodeList.是否_否.getValue())){//还本不带息场景
                        bussFinRepaymentDto.setIsPayIntFlag(DictCodeList.是否_是.getValue());
                    }
                }
            }
            model.addAttribute(GlobalConstant.ENTITY, bussFinRepaymentDto);
        } // 来自于代办
        else if (!WorkFlowUtil.isNotFromToDoList()) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
            ValBussFinRepaymentDto bussFinRepaymentDto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(workflowId,
                entityName, ValBussFinRepaymentDto.class);
            model.addAttribute(GlobalConstant.ENTITY, bussFinRepaymentDto);
        }
    }
    
    /**
     * 构建还本周期下拉框
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    private String createIntpayCycle(){
        // 一体化对应etliu start
//        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.支付频率_付息频率, null);
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.频率, null);
        // 一体化对应etliu end
        if (selectDto != null && !StringUtil.isEmptyList(selectDto.getOptions())) {
            List<SelectOptionDto> options = selectDto.getOptions();
            for (int index = options.size() - 1; index >= 0; index-- ) {
                SelectOptionDto option = options.get(index);
             // 一体化对应etliu start
//                if(StringUtil.equals(option.getId(), DictCodeList.支付频率_付息频率_到期支付.getValue())
//                    ||StringUtil.equals(option.getId(), DictCodeList.支付频率_付息频率_年付.getValue())
//                    ||StringUtil.equals(option.getId(), DictCodeList.支付频率_付息频率_半年付.getValue())
//                    ||StringUtil.equals(option.getId(), DictCodeList.支付频率_付息频率_季付.getValue())
//                    ||StringUtil.equals(option.getId(), DictCodeList.支付频率_付息频率_月付.getValue())
//                    ||StringUtil.equals(option.getId(), DictCodeList.支付频率_付息频率_周付.getValue())
//                    ||StringUtil.equals(option.getId(), DictCodeList.支付频率_付息频率_日付.getValue())
//                    ||StringUtil.equals(option.getId(), DictCodeList.支付频率_付息频率_申购同频.getValue())
//                    ||StringUtil.equals(option.getId(), DictCodeList.支付频率_付息频率_不规则.getValue())){
                if(StringUtil.equals(option.getId(), DictCodeList.频率_年.getValue())
                    ||StringUtil.equals(option.getId(), DictCodeList.频率_半年.getValue())
                    ||StringUtil.equals(option.getId(), DictCodeList.频率_季.getValue())
                    ||StringUtil.equals(option.getId(), DictCodeList.频率_月.getValue())
                    ||StringUtil.equals(option.getId(), DictCodeList.频率_周.getValue())
                    ||StringUtil.equals(option.getId(), DictCodeList.频率_日.getValue())
                    ||StringUtil.equals(option.getId(), DictCodeList.频率_不规则.getValue())
                    ||StringUtil.equals(option.getId(), DictCodeList.频率_按批复要求的项目进度还本.getValue())
                    ||StringUtil.equals(option.getId(), DictCodeListExt2.频率_双周.getValue())){
              // 一体化对应etliu end
                    options.remove(option);
                }
            }
            return JsonUtils.objectArray2Json(options.toArray());
        }
        return null;
    }
    
    /**
     * 生成还本信息check
     * <功能详细描述>
     * @param previewParam
     * @param result
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class})ValBussFinRepaymentDto previewParam,
                            BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        return new AjaxResult();
    }
    
    /**
     * 初始化还本信息
     * <功能详细描述>
     * @return
     * @throws ParseException 
     * @see
     * @since 1.0
     */
    @RequestMapping(value = "/initInfo")
    @ResponseBody
    public List<FinCashPlanDto> initInfo(@RequestBody ValBussFinRepaymentDto dto) throws ParseException {
        List<FinCashPlanDto> finCashPlanList = new ArrayList<FinCashPlanDto>();
        List<? extends FinCashPlan> findinvList = new ArrayList<FinCashPlan>();
        //初始化--修改、查看、代办
        Boolean flag = Boolean.parseBoolean(dto.getInitFlag());
        String finprodId = dto.getFinprodId();
        String workFlowId = dto.getWorkflowId();
        Date maxTradeDate = commonBusinessService.getMaxTradeDate(finprodId);
        if(StringUtil.equals(dto.getAdjPlanFlag(), DictCodeList.共通业务类型_付息计划调整.getValue())){
            dto.setFinprodType2(dto.getAdjPlanFlag());
        }
        if(flag){
            if(StringUtil.isNotEmpty(workFlowId)){
                String entityName = StringUtil.defaultString(dto.getEntityName(),
                    ValBussFinRepaymentService.DEFAULT_ENTITY_NAME);
                //根据工作流与留痕处理提供的共通方法获取业务实体
                ValBussFinRepaymentDto bussFinRepaymentDto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(workFlowId, entityName, ValBussFinRepaymentDto.class);
                if(null!=bussFinRepaymentDto){
                    findinvList = bussFinRepaymentDto.getFinCashPlanList();
                }
            }else{
                if(StringUtil.isNotEmpty(finprodId)){
                    findinvList = bussFinRepaymentService.getFinCashPlanByProductId(finprodId, DictCodeListExt1.现金流二级类型_本金.getValue(),DictCodeListExt1.现金流业务类型_金融产品.getValue(),dto.getFinprodType2());
                }
            }
            if(null!=findinvList){
                for(FinCashPlan entity:findinvList){
                    FinCashPlanDto finCashPlanDto = new FinCashPlanDto();
                    BeanUtil.copyPropertiesIgnoreNull(finCashPlanDto, entity);
                    finCashPlanList.add(finCashPlanDto);
                }
            }
        }else{
          //生成还本计划
          finCashPlanList = bussFinRepaymentService.createAssetRePaymentPlan(dto);
        }
        //update by  zhangnian  20191024 start
        //快捷录入
        if(!StringUtil.equals(dto.getPageModel(),GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {            //交易中的计划不可以维护
            if (null != maxTradeDate) {
                if (null != finCashPlanList && !finCashPlanList.isEmpty()) {
                    for (FinCashPlanDto entity : finCashPlanList) {
                        if (DateUtil.compareDate(entity.getMdate(), maxTradeDate) < 1) {
                            entity.setUpdateFlg("02");
                        } else {
                            entity.setUpdateFlg("01");
                        }
                        entity.setMaxTradeDate(maxTradeDate);
                        entity.setOldMdate(entity.getMdate());
                    }
                }
            }
        }
        //排序
        Collections.sort(finCashPlanList, new Comparator<FinCashPlanDto>() {
            @Override
            public int compare(FinCashPlanDto o1, FinCashPlanDto o2) {
                return o1.getMdate().compareTo(o2.getMdate());
            }
        });
        return finCashPlanList;
    }
    
    /**
     * 根据新的计息截止日获取还款日（支付日）
     *
     * @return 还款日（支付日）
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("genPayDate")
    public String genPayDate() {
        return bussFinRepaymentService.genPayDate(RequestUtil.getPageParam());
    }
}
