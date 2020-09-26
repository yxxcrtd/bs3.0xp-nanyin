/*
 * 文件名：ValFinInterestFactorController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.component.element.controller;


import com.joyintech.calculation.bonds.constant.Variety;
import com.joyintech.calculation.shell.YieldByPriceCalculator;
import com.joyintech.calculation.shell.param.SecInfoModel;
import com.joyintech.calculation.shell.param.YieldPriceInParameter;
import com.joyintech.calculation.shell.param.YieldPriceOutParameter;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.tams.valuation.component.common.calculator.AdjustedTradeDayCal;
import com.joyintech.tams.valuation.component.common.dto.AssetCommonInfoDto;
import com.joyintech.tams.valuation.component.common.service.ValDateCalService;
import com.joyintech.tams.valuation.component.element.dto.ValFinInterestFactorDto;
import com.joyintech.tams.valuation.component.element.interf.ValBussFinRecommentService;
import com.joyintech.tams.valuation.component.element.interf.ValBussFinRepaymentService;
import com.joyintech.tams.valuation.component.element.interf.ValBussRepAssetMngProdInfoService;
import com.joyintech.tams.valuation.component.element.interf.ValFinInterestFactorService;
import com.joyintech.tams.valuation.component.element.interf.ValFinPayInterestStdInfoService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

/**
 * 计息要素---业务组件
 * @author zhangnian
 * @version 1.0 2018年9月18日
 * @see ValFinInterestFactorController
 * @since 1.0
 */
@Controller
@RequestMapping("/valuation/component/element/ValFinInterestFactor")
public class ValFinInterestFactorController extends BaseController{

    /**
     * FinInterestFactorService
     */
    @Autowired(required = false)
    private ValFinInterestFactorService finInterestFactorService;
    
    @Autowired
    private MstWorkflowCommonService  mstWorkflowCommonService;
    
    @Autowired
    private ValDateCalService dateCalService;
    
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, finInterestFactorService.getInitDictCode());
        // 设置画面初始值
        setEntity(model);
        // 画面参数设置
        model.addAttribute("BASIS_A_A",DictCodeList.计息基础_A_A.getValue());
        model.addAttribute("BASIS_A_A_D",DictCodeListExt1.计息基础_A_A_D.getValue());
        model.addAttribute("INT_TYPE_01",DictCodeList.利率类型_固定利率.getValue());
        model.addAttribute("INT_TYPE_02",DictCodeList.利率类型_浮动利率.getValue());
        model.addAttribute("FINPROD_TYPE_TG_F01",DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        model.addAttribute("FINPROD_TYPE_TG_F07", DictCodeListExt1.金融产品类型_投管用_存款.getValue());
        model.addAttribute("FINPROD_TYPE_TG_F17",DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        model.addAttribute("FINPROD_TYPE_TG_F18",DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        model.addAttribute("COUPON_SPECIES_01",DictCodeList.息票品种_附息.getValue());
        model.addAttribute("COUPON_SPECIES_02",DictCodeList.息票品种_贴现.getValue());
        model.addAttribute("COUPON_SPECIES_03",DictCodeList.息票品种_零息.getValue());
        model.addAttribute("COUPON_SPECIES_04",DictCodeList.息票品种_利随本清.getValue());
        model.addAttribute("RESET_TYPE_01",DictCodeList.重置类型_基准利率调整规则_基准利率变动后固定间隔生效.getValue());
        model.addAttribute("RESET_TYPE_02",DictCodeList.重置类型_基准利率调整规则_付息后下一计息期生效.getValue());
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"), ValFinInterestFactorService.DEFAULT_ENTITY_NAME));
        model.addAttribute("FinInterestFactor_PageId",this.getParam("pageId"));
        model.addAttribute("INT_ASSET_TYPE", this.getParam("int_asset_type"));
        model.addAttribute("INTPAY_RULE_01",DictCodeListExt1.利息分配方式_按照实际天数分配.getValue());
        //add by wangpeng 20190828 修复债券类资产新建时选择利随本清时候 推算方式为空 strat 
        model.addAttribute("CALC_RULE_01", DictCodeList.推算方式_起息日向后.getValue());
        //add by wangpeng 20190828 end
        if(StringUtil.equals(this.getParam("int_asset_type"), DictCodeListExt1.金融产品类型_投管用_存款.getValue())
            ||StringUtil.equals(this.getParam("int_asset_type"), DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())){
            model.addAttribute("BASIS_INIT", DictCodeList.计息基础_A_365.getValue());
            model.addAttribute("ASSET_TYPE_INIT", DictCodeList.利率类型_固定利率.getValue());
        }
        if(StringUtil.equals(this.getParam("int_asset_type"), DictCodeListExt1.金融产品类型_投管用_债券.getValue())){
            model.addAttribute("COUPON_SPECIES_INIT", DictCodeList.息票品种_附息.getValue());
            //model.addAttribute("INTPAY_RULE_INIT", DictCodeListExt1.利息分配方式_平均分配.getValue());
        }
        model.addAttribute("CAL_RATE_TYPE_02", DictCodeListExt3.计息利率方式_净投资收益率计提.getValue());
        return "/valuation/component/element/ValFinInterestFactor";
    }
    
    private void setEntity(Model model) {
        String finprodId = this.getParam("finprodId");
        String workFlowId = this.getParam("workflowId");
        String vdate = this.getParam("vdate");
         
        //息票品种
        String couponSpecies = this.getParam("couponSpecies");
        String entityName = StringUtil.defaultString(this.getParam("entityName"), ValFinInterestFactorService.DEFAULT_ENTITY_NAME);
        //付息信息entityName
        String entityNamePayInterest = StringUtil.defaultString(this.getParam("entityNamePayInterest"), ValFinPayInterestStdInfoService.DEFAULT_ENTITY_NAME);
        //还本信息entityName
        String entityNamebussFinRepayment = StringUtil.defaultString(this.getParam("entityNamebussFinRepayment"), ValBussFinRepaymentService.DEFAULT_ENTITY_NAME);
        //行权计划entityName
        String entityNamebusiFinExercisePlan = StringUtil.defaultString(this.getParam("entityNamebusiFinExercisePlan"), ValBussRepAssetMngProdInfoService.DEFAULT_ENTITY_NAME);
        //不来自于代办
        if(WorkFlowUtil.isNotFromToDoList()&& StringUtil.isNotEmpty(finprodId)) {
            //根据主键获取业务实体--计息要素基本信息
            ValFinInterestFactorDto finInterestFactorDto = finInterestFactorService.getFinInterestFactorByProductId(finprodId,vdate);
            //债券类资产计息基础特殊化处理 
            if(null!=finInterestFactorDto){
                if(StringUtil.equals(finInterestFactorDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())){
                    if(StringUtil.isNotEmpty(finInterestFactorDto.getBasis())){
                        String basis = finInterestFactorDto.getBasis();
                        if(StringUtil.equals(basis, DictCodeList.计息基础_A_A_平均分配.getValue())){
                            finInterestFactorDto.setBasis(DictCodeList.计息基础_A_A.getValue());
                            finInterestFactorDto.setBasisStyle(DictCodeListExt1.利息分配方式_平均分配.getValue());
                        }else if(StringUtil.equals(basis, DictCodeList.计息基础_A_A_按实际天数分配.getValue())){
                            finInterestFactorDto.setBasis(DictCodeList.计息基础_A_A.getValue());
                            finInterestFactorDto.setBasisStyle(DictCodeListExt1.利息分配方式_按照实际天数分配.getValue());
                        }
                        /*String[] arr = finInterestFactorDto.getBasis().split(",");
                        if(null!=arr&&arr.length>1){
                            finInterestFactorDto.setBasis(arr[0]);
                            finInterestFactorDto.setBasisStyle(arr[1]); 
                        }*/
                    }
                } 
            }
            model.addAttribute(GlobalConstant.ENTITY, finInterestFactorDto);
        }//来自于代办
        else if(!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workFlowId)){
            //根据工作流与留痕处理提供的共通方法获取业务实体
            ValFinInterestFactorDto finInterestFactorDto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(workFlowId, entityName, ValFinInterestFactorDto.class);
            model.addAttribute(GlobalConstant.ENTITY, finInterestFactorDto);
        }
        model.addAttribute("entityName", entityName);
        model.addAttribute("entityNamePayInterest",entityNamePayInterest);
        model.addAttribute("entityNamebussFinRepayment", entityNamebussFinRepayment);
        // 增加推荐方与推荐费率 add by renping 20191014 start
        // 推荐方与推荐费率信息entityName
        String entityNameBussFinRecomment = StringUtil.defaultString(
            this.getParam("entityNameBussFinRecomment"),
            ValBussFinRecommentService.DEFAULT_ENTITY_NAME);
        model.addAttribute("entityNameBussFinRecomment", entityNameBussFinRecomment);
        // 增加推荐方与推荐费率 add by renping 20191014 end
        model.addAttribute("", entityNamebusiFinExercisePlan);
        if(StringUtil.isNotEmpty(couponSpecies)){
            model.addAttribute("couponSpecies",couponSpecies);
        }
    }
    
    /**
     * 重写pageId
     * {@inheritDoc}
     */
    @Override
    public String getPageId(){
        return this.getParam(GlobalConstant.PAGE_ID);
    }
    
    /**
     * 计算初始利率
     * <功能详细描述>
     * @param scai
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getyieldByPriceCalculator")
    public BigDecimal getyieldByPriceCalculator(@RequestBody AssetCommonInfoDto scai){
        BigDecimal yieldResult = BigDecimal.ZERO;
        YieldByPriceCalculator yieldByPriceCalculator = new YieldByPriceCalculator();
        YieldPriceInParameter input = new YieldPriceInParameter();
        input.setFullPrice(scai.getIssuePrice());
        input.setCalDate(DateUtil.dateToLocal(scai.getVdate()));
        //到期按节假日顺延
        List<String> holidayList = dateCalService.getDealCalendarHoliday(scai.getVdate(),scai.getMdate(),scai.getCalendarId());
        String mdate =  AdjustedTradeDayCal.getAdjustedTradeDay(DateUtil.format(scai.getMdate()), DictCodeList.营业日准则_向后调整.getValue(), holidayList);
        SecInfoModel secInfoModel = new SecInfoModel();
        secInfoModel.setStartDate(DateUtil.dateToLocal(scai.getVdate()));
        secInfoModel.setEndDate(DateUtil.dateToLocal(DateUtil.parse(mdate)));
        if(StringUtil.equals(scai.getCouponSpecies(), DictCodeList.息票品种_零息.getValue())){
            secInfoModel.setBondsType(Variety.零息);
        }else if(StringUtil.equals(scai.getCouponSpecies(), DictCodeList.息票品种_贴现.getValue())){
            secInfoModel.setBondsType(Variety.贴现);
        }
        input.setSecInfoModel(secInfoModel);
        YieldPriceOutParameter parameter = yieldByPriceCalculator.calculate(input);
        if(null != parameter){
            yieldResult = parameter.getMatYield();
            yieldResult = ArithmeticUtil.multiply(yieldResult, new BigDecimal(100));
            yieldResult =  yieldResult.setScale(4, BigDecimal.ROUND_HALF_UP);
        }
        return yieldResult;
    }
}
