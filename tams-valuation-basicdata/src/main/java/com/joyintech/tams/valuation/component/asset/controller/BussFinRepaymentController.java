/*
 * 文件名：BussFinRepaymentController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.component.asset.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.SubPageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.valuation.component.asset.dto.BussFinRepaymentDto;
import com.joyintech.tams.valuation.component.asset.dto.FinPayInterestStdInfoDto;
import com.joyintech.tams.valuation.component.asset.service.BussFinRepaymentService;
import com.joyintech.tams.valuation.component.asset.service.FinInterestFactorService;
import com.joyintech.tams.valuation.component.asset.service.FinPayInterestStdInfoService;
import com.joyintech.tams.valuation.component.business.service.CommonBusinessService;
import com.joyintech.tams.valuation.component.business.service.ComponentWorkflowCommonService;
import com.joyintech.tams.valuation.component.common.constant.ValComponentConstant;
import com.joyintech.tams.valuation.component.common.dto.FinCashPlanDto;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.*;

/**
 * 还本信息--业务组建
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月14日
 * @see BussFinRepaymentController
 * @since 1.0
 */
@Controller
@RequestMapping("/valuation/component/asset/BussFinRepayment")
//一体化对应etliu
@SubPageId("BussFinRepayment")
public class BussFinRepaymentController extends BaseController{

    @Autowired
    private BussFinRepaymentService bussFinRepaymentService;
    
    @Autowired
    private ComponentWorkflowCommonService componentWorkflowCommonService;
    
    @Autowired
    private CommonBusinessService commonBusinessService;
    
    /**
     * 付息信息
     */
    @Autowired(required = false)
    private FinPayInterestStdInfoService finPayInterestStdInfoService;
    @Override
    public String init(Model model) {
        model.addAttribute("FINPROD_TYPE_F01", DictCodeListExt1.金融产品类型_债券.getValue());
        model.addAttribute("FINPROD_TYPE_F07", DictCodeListExt1.金融产品类型_存款.getValue());
        /** modify by yanya on 20210319 增加同业借款**/
        model.addAttribute("FINPROD_TYPE_F21", DictCodeListExt1.金融产品类型_同业借款.getValue());
        model.addAttribute("FINPROD_TYPE_F17", DictCodeListExt1.金融产品类型_计划类资产.getValue());
        model.addAttribute("COUPON_SPECIES_01", DictCodeList.息票品种_附息.getValue());
        model.addAttribute("COUPON_SPECIES_02", DictCodeList.息票品种_贴现.getValue());
        model.addAttribute("COUPON_SPECIES_03", DictCodeList.息票品种_零息.getValue());
        model.addAttribute("COUPON_SPECIES_04", DictCodeList.息票品种_利随本清.getValue());
        //还本周期
        model.addAttribute("REPAYCYCLE", createIntpayCycle());
        // 设置画面初始值
        String entityName = StringUtil.defaultString(this.getParam("entityName"),
                ValComponentConstant.BUSSFINREPAYMENT_ENTITY_NAME);
        setEntity(model);
        //计息要素entityName
        String entityNameFinInterestFactor = StringUtil.defaultString(this.getParam("entityNameFinInterestFactor"),
                ValComponentConstant.FININTERESTFACTOR_ENTITY_NAME);
        //付息信息entityName
        String entityNameFinPayInterestStdInfo = StringUtil.defaultString(this.getParam("entityNameFinPayInterestStdInfo"),
                ValComponentConstant.FINPAYINTERESTSTD_ENTITY_NAME);
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
        return "valuation/component/asset/BussFinRepayment";
    }
    
    
    private void setEntity(Model model) {
        String finprodId = this.getParam("finprodId");
        String workflowId = this.getParam("workflowId");
        String vdate = this.getParam("vdate");
        String entityName = StringUtil.defaultString(this.getParam("entityName"),
                ValComponentConstant.BUSSFINREPAYMENT_ENTITY_NAME);
        // 不来自于代办
        if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(finprodId)) {
            // 根据主键获取业务实体--付息信息
            BussFinRepaymentDto bussFinRepaymentDto = new BussFinRepaymentDto();
            if(null!=finPayInterestStdInfoService){
                FinPayInterestStdInfoDto finPayInterestStdInfoDto = finPayInterestStdInfoService.getFinPayInterestStdInfoByProductId(
                    finprodId, vdate, DictCodeListExt1.现金流二级类型_本金.getValue(), DictCodeListExt1.现金流业务类型_金融产品.getValue());
                if(null!=finPayInterestStdInfoDto){
                    bussFinRepaymentDto.setPayCycle(finPayInterestStdInfoDto.getPayCycle());
                    bussFinRepaymentDto.setRemark(finPayInterestStdInfoDto.getRemark());
                    bussFinRepaymentDto.setIsPayInt(finPayInterestStdInfoDto.getIsPayInt());//界面是否还本不带息 add by zhouxiuping
                    // 母资产计划finPayInterestStdInfoDto.getIsPayInt()属性为空，页面提示异常修复
                    if(StringUtil.equals(finPayInterestStdInfoDto.getIsPayInt(), DictCodeList.是否_否.getValue())){//还本不带息场景
                        bussFinRepaymentDto.setIsPayIntFlag(DictCodeList.是否_是.getValue());
                    }
                }
            }
            model.addAttribute(GlobalConstant.ENTITY, bussFinRepaymentDto);
        } // 来自于代办
        else if (!WorkFlowUtil.isNotFromToDoList()) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
            BussFinRepaymentDto bussFinRepaymentDto = componentWorkflowCommonService.getBusinessEntityByWorkFlowId(workflowId,
                entityName, BussFinRepaymentDto.class);
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
        ValidateGroupEdit.class})BussFinRepaymentDto previewParam,
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
    public List<FinCashPlanDto> initInfo(@RequestBody BussFinRepaymentDto dto) throws ParseException {
        List<FinCashPlanDto> finCashPlanList = new ArrayList<FinCashPlanDto>();
        List<? extends FinCashPlan> findinvList = new ArrayList<FinCashPlan>();
        //初始化--修改、查看、代办
        Boolean flag = Boolean.parseBoolean(dto.getInitFlag());
        String finprodId = dto.getFinprodId();
        String workFlowId = dto.getWorkflowId();
        Date maxTradeDate = commonBusinessService.getMaxTradeDate(finprodId);
        if(StringUtil.equals(dto.getAdjPlanFlag(), DictCodeList.共通业务类型_付息计划调整.getValue())){
            dto.setFinprodType2(dto.getAdjPlanFlag());
            dto.setFinprodType(dto.getAdjPlanFlag());
        }
        if(flag){
            if(StringUtil.isNotEmpty(workFlowId)){
                String entityName = StringUtil.defaultString(dto.getEntityName(),
                        ValComponentConstant.BUSSFINREPAYMENT_ENTITY_NAME);
                //根据工作流与留痕处理提供的共通方法获取业务实体
                BussFinRepaymentDto bussFinRepaymentDto = componentWorkflowCommonService.getBusinessEntityByWorkFlowId(workFlowId, entityName, BussFinRepaymentDto.class);
                if(null!=bussFinRepaymentDto){
                    findinvList = bussFinRepaymentDto.getFinCashPlanList();
                }
            }else{
                if(StringUtil.isNotEmpty(finprodId)){
                    findinvList = bussFinRepaymentService.getFinCashPlanByProductId(finprodId, DictCodeListExt1.现金流二级类型_本金.getValue(), DictCodeListExt1.现金流业务类型_金融产品.getValue(),dto.getFinprodType());
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
