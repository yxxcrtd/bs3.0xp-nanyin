/*
 * 文件名：FinPayInterestStdInfoController.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：zhangnian
 * 修改时间：2018年9月11日 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.component.asset.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.SubPageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.component.asset.dto.FinPayInterestStdInfoDto;
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

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * 付息信息 --业务组件
 * 
 * @author yanya
 * @version 1.0 2021年3月4日
 * @see FinInterestFactorController
 * @since 1.0
 */
@Controller
@RequestMapping("/valuation/component/asset/FinPayInterestStdInfo")
@SubPageId("FinPayInterestStdInfo")
public class FinPayInterestStdInfoController extends BaseController {

    @Autowired
    private FinPayInterestStdInfoService finPayInterestStdInfoService;

    @Autowired
    private ComponentWorkflowCommonService componentWorkflowCommonService;
    
    @Autowired
    private CommonBusinessService commonBusinessService;

    
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model,
                DictCode.营业日准则,
                DictCode.频率,
                DictCode.推算方式,
                DictCode.日期号,
                DictCode.月);
        model.addAttribute("FINPROD_TYPE_F01", DictCodeListExt1.金融产品类型_债券.getValue());
        model.addAttribute("FINPROD_TYPE_F07", DictCodeListExt1.金融产品类型_存款.getValue());
        /** modify by yanya on 20210319 增加同业借款**/
        model.addAttribute("FINPROD_TYPE_F21", DictCodeListExt1.金融产品类型_同业借款.getValue());
        model.addAttribute("FINPROD_TYPE_F17", DictCodeListExt1.金融产品类型_计划类资产.getValue());
        model.addAttribute("BIZ_TYPE_TRD17", DictCodeList.共通业务类型_项目类资产存续期.getValue());
        
     // 一体化对应etliu start
//        model.addAttribute("CALC_METHOD_04", DictCodeList.推算方法_首次付息日.getValue());
//        model.addAttribute("CALC_METHOD_NAME_04", DictCodeList.推算方法_首次付息日.getCodeName());
        model.addAttribute("CALC_METHOD_04", DictCodeList.推算方式_首次支付日.getValue());
        model.addAttribute("CALC_METHOD_NAME_04", DictCodeList.推算方式_首次支付日.getCodeName());
        // 推算方式二次加工
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.推算方式, null);
        List<SelectOptionDto> options = selectDto.getOptions();
        for (SelectOptionDto dto : options) {
        	if (StringUtil.equals(DictCodeList.推算方式_首次支付日.getValue(), dto.getId())) {
    			dto.setText(DictCodeList.推算方式_首次支付日.getCodeName());
    		}
        }
        model.addAttribute(DictCode.推算方式.getValue(),
                JsonUtils.objectArray2Json(options.toArray()));
        // 一体化对应end
        
     // 一体化对应etliu start
//        model.addAttribute("INTPAY_CYCLE_09", DictCodeList.支付频率_付息频率_不规则.getValue());
//        model.addAttribute("INTPAY_CYCLE_NAME_09", DictCodeList.支付频率_付息频率_不规则.getCodeName());
        model.addAttribute("INTPAY_CYCLE_09", DictCodeList.频率_不规则.getValue());
        model.addAttribute("INTPAY_CYCLE_NAME_09", DictCodeList.频率_不规则.getCodeName());
     // 一体化对应etliu end
        
        model.addAttribute("COUPON_SPECIES_01", DictCodeList.息票品种_附息.getValue());
        model.addAttribute("COUPON_SPECIES_02", DictCodeList.息票品种_贴现.getValue());
        model.addAttribute("COUPON_SPECIES_03", DictCodeList.息票品种_零息.getValue());
        model.addAttribute("COUPON_SPECIES_04", DictCodeList.息票品种_利随本清.getValue());
        // 设置画面初始值
        setEntity(model, this.getParam("int_asset_type"));
        model.addAttribute("FinInterestFactor_PageId", this.getParam("pageId"));
        model.addAttribute("INT_ASSET_TYPE", this.getParam("int_asset_type"));
        //int_asset_type_payinterest:区分付息计划页面的入口参数(无:表示新建  payinterest:表示付息计划)  
        model.addAttribute("INT_ASSET_TYPE_PAYINTEREST", this.getParam("int_asset_type_payinterest"));
        //共通业务类型--付息计划调整
        model.addAttribute("COMMON_BUSINESS_TYPE_FINPROD10", DictCodeList.共通业务类型_付息计划调整.getValue());
//        if (StringUtil.equals(this.getParam("int_asset_type"), DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())) {
//            model.addAttribute("payTdayRuleLableName", this.getMessage("FinPayInterestStdInfo.payTdayRuleProject"));
//        }
//        else {
          model.addAttribute("payTdayRuleLableName", this.getMessage("FinPayInterestStdInfo.payTdayRule"));
//        }
        if (StringUtil.equals(this.getParam("int_asset_type"), DictCodeListExt1.金融产品类型_存款.getValue())
                || StringUtil.equals(this.getParam("int_asset_type"), DictCodeListExt1.金融产品类型_同业借款.getValue())
        ) {
            model.addAttribute("TDAY_RULE_INIT", DictCodeList.营业日准则_向后调整.getValue());
         // 一体化对应etliu
//            model.addAttribute("CALC_METHOD_INIT", DictCodeList.推算方法_起息日向后.getValue());
            model.addAttribute("CALC_METHOD_INIT", DictCodeList.推算方式_起息日向后.getValue());
            
         // 一体化对应etliu start
//            model.addAttribute("INTPAY_CYCLE_INIT", DictCodeList.支付频率_付息频率_到期支付.getValue());
            model.addAttribute("INTPAY_CYCLE_INIT", DictCodeList.频率_到期一次.getValue());
         // 一体化对应etliu end
        }else if (StringUtil.equals(this.getParam("int_asset_type"), DictCodeListExt1.金融产品类型_债券.getValue())) {
            model.addAttribute("TDAY_RULE_INIT", DictCodeList.营业日准则_向后调整.getValue());
        }
        else if (StringUtil.equals(this.getParam("int_asset_type"), DictCodeListExt1.金融产品类型_计划类资产.getValue())) {
            model.addAttribute("TDAY_RULE_INIT", DictCodeList.营业日准则_向后调整.getValue());
            model.addAttribute("INTPAY_CYCLE_10", DictCodeList.频率_到期一次.getValue());
        }
        model.addAttribute("INTPAY_CYCLE_03", DictCodeList.频率_月.getValue());
        model.addAttribute("INTPAY_CYCLE_04", DictCodeList.频率_季.getValue());
        model.addAttribute("INTPAY_CYCLE_05", DictCodeList.频率_年.getValue());
        return "valuation/component/asset/FinPayInterestStdInfo";
    }

    private void setEntity(Model model, String intAssetType) {
        String finprodId = this.getParam("finprodId");
        String workflowId = this.getParam("workflowId");
        String vdate = this.getParam("vdate");
        // 息票品种
        String couponSpecies = this.getParam("couponSpecies");
        String entityName = StringUtil.defaultString(this.getParam("entityName"),
                ValComponentConstant.FINPAYINTERESTSTD_ENTITY_NAME);
        // 不来自于代办
        // 计息要素entityName
        String entityNameFinInterestFactor = StringUtil.defaultString(this.getParam("entityNameFinInterestFactor"),
                ValComponentConstant.FININTERESTFACTOR_ENTITY_NAME);
        if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(finprodId)) {
            String type = DictCodeListExt1.现金流业务类型_金融产品.getValue();
            if(StringUtil.isNotEmpty(intAssetType)) {
                type = intAssetType;
            }
            // 根据主键获取业务实体--付息信息
            FinPayInterestStdInfoDto finPayInterestStdInfoDto = finPayInterestStdInfoService.getFinPayInterestStdInfoByProductId(
                finprodId, vdate, DictCodeListExt1.现金流二级类型_利息.getValue(), type);
            model.addAttribute(GlobalConstant.ENTITY, finPayInterestStdInfoDto);
        } // 来自于代办
        else if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
            FinPayInterestStdInfoDto finPayInterestStdInfoDto = componentWorkflowCommonService.getBusinessEntityByWorkFlowId(workflowId,
                entityName, FinPayInterestStdInfoDto.class);
            model.addAttribute(GlobalConstant.ENTITY, finPayInterestStdInfoDto);
        }
        model.addAttribute("entityName", entityName);
        model.addAttribute("entityNameFinInterestFactor", entityNameFinInterestFactor);

        // 画面参数设置
        //还本信息DTO
        model.addAttribute("entityNameBussFinRepayment",
                StringUtil.defaultString(this.getParam("entityNameBussFinRepayment"),
                        ValComponentConstant.BUSSFINREPAYMENT_ENTITY_NAME));
        model.addAttribute("couponSpecies", couponSpecies);
    }

    /**
     * 生成付息计划check <功能详细描述>
     * 
     * @param previewParam
     * @param result
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) FinPayInterestStdInfoDto previewParam,
                            BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        return new AjaxResult();
    }

    /**
     * 生成付息计划 <功能详细描述> 说明:计划列表有票面利率 所以返回FinCashPlanDto,不用FinCashPlan
     * 
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    @RequestMapping(value = "/generationPlan")
    @ResponseBody
    public List<FinCashPlanDto> generationPlan(@RequestBody FinPayInterestStdInfoDto dto)
        throws ParseException {
        // 修改查看初始化
        Boolean flag = Boolean.parseBoolean(dto.getInitFlag());
        String finprodId = dto.getFinprodId();
        String workFlowId = dto.getWorkflowId();
        // 付息计划
        List<FinCashPlanDto> invList = new ArrayList<>();
        String entityName = StringUtil.defaultString(dto.getEntityName(),
                ValComponentConstant.FINPAYINTERESTSTD_ENTITY_NAME);
        Date maxTradeDate = commonBusinessService.getMaxTradeDate(finprodId);
        if(StringUtil.equals(dto.getAdjPlanFlag(), DictCodeList.共通业务类型_付息计划调整.getValue())){
            dto.setFinprodType2(dto.getAdjPlanFlag());
            dto.setFinprodType(dto.getAdjPlanFlag());
        }
        // 初始化--修改、查看、代办
        if (flag) {
            // 来自代办
            if (StringUtil.isNotEmpty(workFlowId)) {
                // 根据工作流与留痕处理提供的共通方法获取业务实体
                FinPayInterestStdInfoDto finPayInterestStdInfoDto = componentWorkflowCommonService.getBusinessEntityByWorkFlowId(
                    workFlowId, entityName, FinPayInterestStdInfoDto.class);
                if(null!=finPayInterestStdInfoDto){
                    invList = finPayInterestStdInfoDto.getFinCashPlanList();
                }
            }
            else {
                // 查询付息计划
                if (StringUtil.isNotEmpty(finprodId)) {
                    invList = finPayInterestStdInfoService.getFinCashPlanByProductId(finprodId,
                        DictCodeListExt1.现金流二级类型_利息.getValue(), DictCodeListExt1.现金流业务类型_金融产品.getValue(), dto.getFinprodType());
                }
            }
        }
        else {
            // 生付息成计划
            dto.setMaxTradeDate(maxTradeDate);
            invList = finPayInterestStdInfoService.createAssetPayInteresPlan(dto);
        }
        //modify by yanya on 20210312 特殊处理
        if(StringUtil.equals(DictCodeList.息票品种_贴现.getValue(),dto.getCouponSpecies())
                || StringUtil.equals(DictCodeList.息票品种_零息.getValue(),dto.getCouponSpecies())) {
            invList.stream().forEach(item->item.setIntAmt(BigDecimal.ZERO));
        }
        //update by  zhangnian  20191024 start
        //快捷录入
        if(!StringUtil.equals(dto.getPageModel(),GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            //交易中的计划不可以维护
            if(null!=maxTradeDate){
                if(null!=invList&&!invList.isEmpty()){
                    for(FinCashPlanDto entity:invList){
                        if(DateUtil.compareDate(entity.getMdate(), maxTradeDate)<1){
                            entity.setUpdateFlg("02");
                        }else{
                            entity.setUpdateFlg("01");
                        }
                        entity.setMaxTradeDate(maxTradeDate);
                        entity.setOldMdate(entity.getMdate());
                        entity.setOldVdate(entity.getVdate());
                    }
                }
            }
        }
        //update by  zhangnian 20191024  end
        //排序
        Collections.sort(invList, new Comparator<FinCashPlanDto>() {
            @Override
            public int compare(FinCashPlanDto o1, FinCashPlanDto o2) {
                return o1.getVdate().compareTo(o2.getVdate());
            }
        });
        return invList;
    }

    
    /**
     * 支付计划详情 <功能详细描述>
     * 
     * @param model
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping(value = "/cashPlan/detaile")
    public String detaile(Model model) {
        String finPayInterestStdInfo = this.getParam("finPayInterestStdInfo");
        model.addAttribute("finPayInterestStdInfo_JsonStr", finPayInterestStdInfo);
        return "valuation/component/asset/FinPayInterestStdInfoPlan";
    }
    
    /**
     * 查看区间付息计划
     * <功能详细描述>
     * @param dto
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    @RequestMapping(value = "/generationSubPlan")
    @ResponseBody
    public List<FinCashPlanDto> generationSubPlan(@RequestBody FinPayInterestStdInfoDto dto)
        throws ParseException {
            return  finPayInterestStdInfoService.createAssetPayInteresPlan(dto);
    }

    /**
     * 重写pageId {@inheritDoc}
     */
    @Override
    public String getPageId() {
        return this.getParam(GlobalConstant.PAGE_ID);
    }
}
