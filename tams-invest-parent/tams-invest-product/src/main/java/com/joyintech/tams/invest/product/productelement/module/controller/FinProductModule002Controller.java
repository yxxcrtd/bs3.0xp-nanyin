/*
 * 文件名：ProductModule002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年8月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.module.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.prod.BussBenchMarketInfoService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.productelement.module.dto.FinProductModuleInfo002Dto;
import com.joyintech.tams.invest.product.productelement.module.dto.FinProductModuleInfoDto;
import com.joyintech.tams.invest.product.productelement.module.service.FinProductModuleService;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * @author 何阳阳
 * @version 1.0 2018年8月28日
 * @see FinProductModule002Controller
 * @since 1.0
 */
/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author chenzhuo
 * @version 1.0 2018年10月31日
 * @see FinProductModule002Controller
 * @since 1.0
 */
@Controller
@PageId("FinProductModule002")
@RequestMapping("/invest/product/productelement/module/FinProductModule002")
public class FinProductModule002Controller extends BaseController {

    /**
     * FinProductModuleService
     */
    @Autowired(required = false)
    private FinProductModuleService finProductModule002Service;
    
    @Autowired
    private BussBenchMarketInfoService bussBenchMarketInfoService;

    /**
     * 业务编号
     */
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;

    /**
     * 需要初始化的下拉菜单
     */
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.开放类型,
        DictCode.利率类型, DictCode.产品收益标识, DictCode.发行方式,
        DictCode.募集方式, DictCode.处理模式, DictCode.收益类型,
        DictCode.开放类型, DictCode.利率类型, DictCode.分层类型,
        DictCode.产品收益标识, DictCode.排期发行方式, DictCode.计息基础,
        DictCode.币种, DictCode.募集方式, DictCode.投资性质,
        DictCode.销售模式,DictCode.开放类型,DictCode.是否,DictCode.分红方式,
        DictCode.产品风险等级,DictCode.销售渠道,DictCode.销售部门,DictCode.目标客户类型,
        DictCode.销售区域_中债,DictCode.是否_中债,DictCode.重置规则,DictCode.期限类型,
        DictCode.尾数处理方式,DictCode.开放模式,DictCode.开放天数类型,DictCode.周期单位,
        DictCode.星期号,DictCode.日期号,DictCode.工作日号,DictCode.月周数,DictCode.推算方式,
        DictCode.营业日准则,DictCode.推算方法,DictCode.净值日规则,DictCode.分红规则,
        DictCode.频率,DictCode.付息周期,DictCode.计费基数_费用品种,DictCode.基数日期类型,
        DictCode.计提类型,DictCode.产品期限,DictCode.资金投向地区,DictCode.所属国家或地区,
        DictCode.理财业务服务模式,DictCode.产品运作模式,DictCode.产品资产配置方式_中债,
        DictCode.管理模式,DictCode.定价方式,DictCode.境内托管机构名称,DictCode.所属国家或地区,
        DictCode.投资者风险偏好,DictCode.合作模式,DictCode.投资本金_收益_到账日,
        DictCode.产品增信机构类型,DictCode.产品增信形式,DictCode.开放期业务,
        DictCode.层级类别,DictCode.开放模式_中债,DictCode.规律开放周期,DictCode.人行客户类型,
        DictCode.人行理财产品发起机构标识,DictCode.产品系列,DictCode.产品特点,DictCode.销售类型,
        DictCode.费用品种类型,DictCode.是否_中债_期限
    };

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DICT_CODES);
        model.addAttribute("YES", DictCodeList.是否_是.getValue());
        model.addAttribute("NO", DictCodeList.是否_否.getValue());
        model.addAttribute("DISPOSE_PATTERN_01", DictCodeList.处理模式_普通.getValue());
        model.addAttribute("DISPOSE_PATTERN_03", BussinessDictCodeList.处理模式_滚动N天.getValue());
        model.addAttribute("DISPOSE_PATTERN_04", BussinessDictCodeList.处理模式_普通N天.getValue());
        model.addAttribute("DISPOSE_PATTERN_05", BussinessDictCodeList.处理模式_开放阶梯.getValue());
        model.addAttribute("DISPOSE_PATTERN_08", BussinessDictCodeList.处理模式_货币型.getValue());
        model.addAttribute("DISPOSE_PATTERN_09", BussinessDictCodeList.处理模式_净值型.getValue());
        model.addAttribute("PROFIT_TYPE_01", DictCodeList.收益类型_净值型.getValue());
        model.addAttribute("PROFIT_TYPE_02", DictCodeList.收益类型_收益型.getValue());
        model.addAttribute("PROFIT_TYPE_03", DictCodeList.收益类型_货币型.getValue());
        model.addAttribute("ISSUE_MODE_01", DictCodeList.开放类型_开放式.getValue());
        model.addAttribute("ISSUE_MODE_02", DictCodeList.开放类型_封闭式.getValue());
        model.addAttribute("PRODPROFITFLAG_03", DictCodeList.产品收益标识_非保本浮动收益.getValue());
        model.addAttribute("SCHEDUAL_ISSUE_02", DictCodeList.排期发行方式_单一.getValue());
        model.addAttribute("BASIS_A_365", DictCodeList.计息基础_A_365.getValue());
        model.addAttribute("CCY_CNY", DictCodeList.币种_人民币_CNY.getValue());
        model.addAttribute("RAISETYPE_01", DictCodeListExt1.募集方式_公募.getValue());
        model.addAttribute("INVEST_TYPE_01", DictCodeList.投资性质_固定收益类.getValue());
        model.addAttribute("finprodTypeTmpl", DictCodeListExt1.金融产品类型_投管用_系列模板.getValue());
        //申赎同频
        model.addAttribute("sameFlag",DictCodeList.是否_是.getValue());
        //处理模式
        model.addAttribute("dealMode_10",DictCodeList.处理模式_周期型.getValue());
        model.addAttribute("dealMode_12",DictCodeList.处理模式_分档周期型.getValue());
        model.addAttribute("dealMode_01",DictCodeList.处理模式_普通.getValue());
        model.addAttribute("dealMode_11",DictCodeList.处理模式_分档.getValue());
        //是否
        model.addAttribute("YES_01",DictCodeList.是否_中债_是.getValue());
        model.addAttribute("NO_01",DictCodeList.是否_中债_否.getValue());
        //利率类型
        model.addAttribute("intType_01",DictCodeList.利率类型_固定利率.getValue());
        model.addAttribute("intType_02",DictCodeList.利率类型_浮动利率.getValue());
        model.addAttribute("openMode_01",DictCodeList.开放模式_定期开放.getValue());
        model.addAttribute("openMode_03",DictCodeList.开放模式_周期开放.getValue());
        model.addAttribute("openDaysType_01",DictCodeList.开放天数类型_固定天数.getValue());
        model.addAttribute("openDaysType_02",DictCodeList.开放天数类型_每天.getValue());
        model.addAttribute("openCycleUnit_day",DictCodeListExt1.周期单位_日.getValue());
        model.addAttribute("openCycleUnit_week",DictCodeListExt1.周期单位_周.getValue());
        model.addAttribute("openCycleUnit_month",DictCodeListExt1.周期单位_月.getValue());
        model.addAttribute("openCycleUnit_year",DictCodeListExt1.周期单位_年.getValue());
        model.addAttribute("investorType_04",DictCodeList.目标客户类型_金融同业专属.getValue());
        model.addAttribute("period_02",DictCodeListExt2.期限类型_多固定期限.getValue());
        model.addAttribute("CHARGE_TYPE_02", DictCodeList.收费类型_不规则费用.getValue());
        model.addAttribute("PAY_RULE_08", DictCodeList.频率_不规则.getValue());
        model.addAttribute("FEE_TYPE_05", DictCodeList.费用品种类型_浮动差额中收.getValue());
        model.addAttribute("BASIC_BILL_04", DictCodeList.计费基数_费用品种_存续本金.getValue());
        model.addAttribute("BASE_DATE_TYPE_01", DictCodeList.基数日期类型_当日.getValue());
        model.addAttribute("BASE_DATE_TYPE_02", DictCodeList.基数日期类型_前一自然日.getValue());
        model.addAttribute("BASE_DATE_TYPE_03", DictCodeList.基数日期类型_前一工作日.getValue());
        model.addAttribute("HAS", DictCodeListExt2.有无_中债_有.getValue());
        model.addAttribute("saleAreaDeclareIn", DictCodeListExt2.资金投向地区_境内.getValue());
		model.addAttribute("saleAreaDeclareOut", DictCodeListExt2.资金投向地区_境外.getValue());
		model.addAttribute("saleAreaDeclareInOut", DictCodeListExt2.资金投向地区_境内和境外.getValue());
		model.addAttribute("prodManageModeActM", DictCodeListExt1.产品管理模式_资产管理公司为实际管理人.getValue());
		model.addAttribute("prodManageModeOthO", DictCodeListExt1.产品管理模式_其他机构为实际管理人.getValue());
		model.addAttribute("prodManageModeBank", DictCodeListExt1.产品管理模式_银行为实际管理人.getValue());
		model.addAttribute("prodManageModeSpv", DictCodeListExt1.产品管理模式_特殊目的载体_SPV.getValue());
		model.addAttribute("copModeSigRun", DictCodeListExt2.合作模式_独立运作.getValue());
		model.addAttribute("prodRunModeCloN", DictCodeListExt2.产品运作模式_封闭式净值型.getValue());
		model.addAttribute("prodRunModeCloNN", DictCodeListExt2.产品运作模式_封闭式非净值型.getValue());
		model.addAttribute("prodRunModeOpeN", DictCodeListExt2.产品运作模式_开放式净值型.getValue());
		model.addAttribute("prodRunModeOpeNN", DictCodeListExt2.产品运作模式_开放式非净值型.getValue());
		//分红规则
		model.addAttribute("payCycle_01", DictCodeList.分红规则_到期支付.getValue());
		model.addAttribute("payCycle_02",DictCodeList.分红规则_周期支付.getValue());
		//画面元素是否显示
        PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
        pageElementConfigDto.setPageId(this.getPageId());
        Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto);
        model.addAttribute("isEleShow", isEleShow);
        
        //业绩基准-利率类型下拉框
        List<SelectOptionDto> list1 = new ArrayList<>();
        SelectOptionDto selectOptionDto = new SelectOptionDto();
        selectOptionDto.setId(DictCodeList.利率类型_固定利率.getValue());
        selectOptionDto.setText(DictCodeList.利率类型_固定利率.getCodeName());
        list1.add(selectOptionDto);
        SelectOptionDto selectOptionDto2 = new SelectOptionDto();
        selectOptionDto2.setId(DictCodeList.利率类型_浮动利率.getValue());
        selectOptionDto2.setText(DictCodeList.利率类型_浮动利率.getCodeName());
        list1.add(selectOptionDto2);
        model.addAttribute("INT_TYPE_2", JsonUtils.objectArray2Json(list1.toArray()));
        // 浮动利率下拉框（含利率行情、复合基准四个部分）
        List<SelectOptionDto> listFloatRate = new ArrayList<SelectOptionDto>();
        // 利率行情
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.利率行情, null);
        for (SelectOptionDto dto : selectDto.getOptions()) {
            SelectOptionDto dto1 = new SelectOptionDto();
            dto1.setId(dto.getId());
            dto1.setText(dto.getText());
            dto1.setObject(DictCodeListExt1.基准类型_2_利率);
            listFloatRate.add(dto1);
        }
        // 复合基准
        List<SelectOptionDto> finBaseRuleInfoList = bussBenchMarketInfoService.getFinBaseRuleInfo();
        for (SelectOptionDto dto : finBaseRuleInfoList) {
            SelectOptionDto dto1 = new SelectOptionDto();
            dto1.setId(dto.getId());
            dto1.setText(dto.getText());
            dto1.setObject(DictCodeListExt1.基准类型_2_复合);
            listFloatRate.add(dto1);
        }
        model.addAttribute("INDEX_ID_SOURCE", JsonUtils.objectArray2Json(listFloatRate.toArray()));
        //计息基础选择框 
        List<SelectOptionDto> listtype = new ArrayList<SelectOptionDto>();
        SelectOptionDto dto1 = new SelectOptionDto();
        dto1.setId(DictCodeList.计息基础_A_365.getValue());
        dto1.setText(DictCodeList.计息基础_A_365.getCodeName());
        SelectOptionDto dto2 = new SelectOptionDto();
        dto2.setId(DictCodeList.计息基础_A_360.getValue());
        dto2.setText(DictCodeList.计息基础_A_360.getCodeName());
        listtype.add(dto1);
        listtype.add(dto2);
        model.addAttribute("BASIS_SELECT", JsonUtils.objectArray2Json(listtype.toArray()));
        //分红周期
        List<SelectOptionDto> payCycle = new ArrayList<>();
        SelectOptionDto week = new SelectOptionDto();
        week.setId(DictCodeList.频率_周.getValue());
        week.setText(DictCodeList.频率_周.getCodeName());
        payCycle.add(week);
        SelectOptionDto season = new SelectOptionDto();
        season.setId(DictCodeList.频率_季.getValue());
        season.setText(DictCodeList.频率_季.getCodeName());
        payCycle.add(season);
        SelectOptionDto year = new SelectOptionDto();
        year.setId(DictCodeList.频率_年.getValue());
        year.setText(DictCodeList.频率_年.getCodeName());
        payCycle.add(year);
        SelectOptionDto day = new SelectOptionDto();
        day.setId(DictCodeList.频率_日.getValue());
        day.setText(DictCodeList.频率_日.getCodeName());
        payCycle.add(day);
        SelectOptionDto month = new SelectOptionDto();
        month.setId(DictCodeList.频率_月.getValue());
        month.setText(DictCodeList.频率_月.getCodeName());
        payCycle.add(month);
        model.addAttribute("FREQUENCY_UNIT_2", JsonUtils.objectArray2Json(payCycle.toArray()));
        //分红计划-推算方法
        List<SelectOptionDto> caclRule = new ArrayList<>();
        SelectOptionDto sod1 = new SelectOptionDto();
        sod1.setId(DictCodeList.推算方式_起息日向后.getValue());
        sod1.setText(DictCodeList.推算方式_起息日向后.getCodeName());
        caclRule.add(sod1);
        SelectOptionDto sod2 = new SelectOptionDto();
        sod2.setId(DictCodeList.推算方式_首次支付日.getValue());
        sod2.setText("首次分红日");
        caclRule.add(sod2);
        model.addAttribute("CALC_RULE_2", JsonUtils.objectArray2Json(caclRule.toArray()));
        //付息计划-推算方法
        List<SelectOptionDto> caclRule2 = new ArrayList<>();
        SelectOptionDto sod3 = new SelectOptionDto();
        sod3.setId(DictCodeList.推算方式_起息日向后.getValue());
        sod3.setText(DictCodeList.推算方式_起息日向后.getCodeName());
        caclRule2.add(sod3);
        SelectOptionDto sod4 = new SelectOptionDto();
        sod4.setId(DictCodeList.推算方式_首次支付日.getValue());
        sod4.setText("首次付息日");
        caclRule2.add(sod4);
        model.addAttribute("CALC_RULE_3", JsonUtils.objectArray2Json(caclRule2.toArray()));
        //浮动管理费-付费规则
        SelectDto selectDto2 = getSelectDataFromDataDic(DictCode.频率,null);
        List<SelectOptionDto> payCycle2 = selectDto2.getOptions();
        SelectOptionDto selectOptionDto3 = new SelectOptionDto();
        selectOptionDto3.setId(DictCodeList.频率_周.getValue());
        selectOptionDto3.setText("周付");
        payCycle2.add(0, selectOptionDto3);
        model.addAttribute("FREQUENCY_UNIT_3", JsonUtils.objectArray2Json(payCycle2.toArray()));
                
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String templateId = this.getParam("templateId");
        FinProductModuleInfo002Dto finProductModuleInfo002Dto = null;
        // 待办跳转
        if (StringUtil.isNotEmpty(workflowId) && !WorkFlowUtil.isNotFromToDoList()) {
            model.addAttribute(GlobalConstant.WORKFLOW_ID, workflowId);
            finProductModuleInfo002Dto = finProductModule002Service.getBussinessEntityByWorkflowId(
                workflowId);
        }
        else if (StringUtil.isNotEmpty(templateId)) {
            finProductModuleInfo002Dto = finProductModule002Service.selectByTemplateId(templateId);
        }
        /*else if (StringUtil.equals(this.getParam("pageModel"), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            // 采番生成费用新建时缓存key值
            BussinessCodeParameter parameter = new BussinessCodeParameter();
            parameter.setCommonBusinessType(DictCodeList.共通业务类型_产品要素管理);
            model.addAttribute("finprodId", baseGetBusinessId != null ? baseGetBusinessId.buildBussinessCode(
                parameter) : IDGenerator.generate());
        }*/
        if(StringUtil.isNotEmpty(finProductModuleInfo002Dto)) {
        	model.addAttribute(GlobalConstant.ENTITY,
        			finProductModuleInfo002Dto==null?null:finProductModuleInfo002Dto.getProductModuleInfo());

        	if(finProductModuleInfo002Dto!=null){
                this.setViewSelectOptionToModel(finProductModuleInfo002Dto.getCreateUser(),model,DictCode.产品系列);
            }

                //基本信息
                model.addAttribute("productModuleInfo",finProductModuleInfo002Dto.getProductModuleInfo());
                //募集要素
                model.addAttribute("bussFinProductSaleParamDto",finProductModuleInfo002Dto.getBussFinProductSaleParamDto());
                //业绩基准
                model.addAttribute("bussBenchMarkInfoDto",finProductModuleInfo002Dto.getBussBenchMarkInfoDto());
                //分档业绩基准
                model.addAttribute("bussBenchMarkLayeredInfoDto",finProductModuleInfo002Dto.getBussBenchMarkLayeredInfoDto());
                model.addAttribute("bussBenchMarkLayeredInfoDtoList",finProductModuleInfo002Dto.getBussBenchMarkLayeredInfoDto().getBussBenchMarkLayeredInfoDtoList());
                //估值信息
                model.addAttribute("bussFinProductValuationDto",finProductModuleInfo002Dto.getBussFinProductValuationDto());
                //开放计划-申购
                model.addAttribute("bussFinProductOpenPlanDto",finProductModuleInfo002Dto.getBussFinProductOpenPlanDto());
                //申赎同频
                String sameFlag=DictCodeList.是否_否.getValue();
                if(StringUtil.isNotEmpty(finProductModuleInfo002Dto.getBussFinProductOpenPlanDto()) && 
                		StringUtils.equals(DictCodeList.申赎开放类型_申赎开放.getValue(), finProductModuleInfo002Dto.getBussFinProductOpenPlanDto().getOpenType()))
        			 sameFlag=DictCodeList.是否_是.getValue();
                model.addAttribute("sameFlag",sameFlag);
                //开放计划-赎回
                model.addAttribute("bussFinProductOpenPlanRedDto",finProductModuleInfo002Dto.getBussFinProductOpenPlanRedDto());
                //分红计划
                model.addAttribute("bussFinProductBonusPlanDto",finProductModuleInfo002Dto.getBussFinProductBonusPlanDto());
                //付息计划
                model.addAttribute("bussFinProductPayPlanDto",finProductModuleInfo002Dto.getBussFinProductPayPlanDto());
                //浮动管理费
                model.addAttribute("bussBenchMarkFloatInfoDto_float",finProductModuleInfo002Dto.getBussBenchMarkFloatInfoDto());
                model.addAttribute("bussFinProductFloatFeeDto",finProductModuleInfo002Dto.getBussFinProductFloatFeeDto());
                model.addAttribute("bussFinProductFloatFeeInfoDtoList",finProductModuleInfo002Dto.getBussFinProductFloatFeeDto().getBussFinProductFloatFeeInfoDtoList());
                //申报信息
                model.addAttribute("bussRepFinProductInfoDto",finProductModuleInfo002Dto.getBussRepFinProductInfoDto());
                model.addAttribute("finprodId", finProductModuleInfo002Dto.getFinprodId());
                //分层信息
                model.addAttribute("finProductPtlLayeredDtoList",finProductModuleInfo002Dto.getBussFinProductLayeredInfoDto());
                //产品费用
                model.addAttribute("bussFinProductFeeInfoDtoList",finProductModuleInfo002Dto.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList());
                //回补信息
                model.addAttribute("finProductAnapleroticTabInfoDtoList",finProductModuleInfo002Dto.getFinProductAnapleroticTabInfoDtoList());
        }
        
        return super.init(model);
    }

    /**
     * 画面点击保存按钮时check
     * 
     * @param dto 实体类
     * @param result BindingResult
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid FinProductModuleInfo002Dto dto,
                            BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存或者提交
     * 
     * @param dto FinProductModuleInfo002Dto
     * @param result BindingResult
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Valid FinProductModuleInfo002Dto dto,
                           BindingResult result)
        throws Exception {
        if (result.hasErrors()) {
            return new AjaxResult(result);
        }

        // 业务代码生成参数
        //dto.setFinprodName(dto.getProductModuleInfo().getTemplateName());
        //dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_系列模板.getValue());
        if(null != dto.getProductModuleInfo()) {
            // 如果是模板代码为空就生成模板代码
        	if(!StringUtil.isNotEmpty(dto.getProductModuleInfo().getTemplateId())) {
        		BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
    			bussinessCodeParameter.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_战术配置优化));
    			if (baseGetBusinessId != null) {
    				String templateId = baseGetBusinessId.buildBussinessCode(bussinessCodeParameter) != null
    						? baseGetBusinessId.buildBussinessCode(bussinessCodeParameter)
    						: IDGenerator.generate();
    				dto.getProductModuleInfo().setTemplateId(templateId);
    				dto.setTemplateId(templateId);
    			}
        	}
            
        }
        ProcessParameter<FinProductModuleInfo002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<FinProductModuleInfo002Dto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        // 金融产品代码作为业务编号
        String businessCode = dto.getProductModuleInfo().getTemplateId();
        params.setBusinessCode(businessCode);
        params.setBusinessType(DictCodeList.共通业务类型_产品模板管理.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("FinProductModule001.templetId"),
                GlobalConstant.OPEN_PARENTHESIS, businessCode, GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("FinProductModule001.templetName"), GlobalConstant.OPEN_PARENTHESIS,
                entity.getProductModuleInfo().getTemplateName(), GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = finProductModule002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    /**
     * 根据发行方式联动处理模式下拉框
     * @return
     */
    @RequestMapping("getDealModeSelect")
    @ResponseBody
    public List<SelectOptionDto> getDealModeSelect(){
    	List<SelectOptionDto> list = new ArrayList<SelectOptionDto>();
    	String profitType= this.getParam("productModuleInfo.profitType");
    	SelectDto selectDto = getSelectDataFromDataDic(DictCode.处理模式,null);
    	if(StringUtil.isEmpty(profitType)) {
    		list = selectDto.getOptions();
    	}else if(StringUtil.equals(profitType, DictCodeList.收益类型_净值型.getValue())) {
    		SelectOptionDto selectOptionDto = new SelectOptionDto();
    		selectOptionDto.setId(DictCodeList.处理模式_普通.getValue());
    		selectOptionDto.setText(DictCodeList.处理模式_普通.getCodeName());
    		list.add(selectOptionDto);
    		SelectOptionDto selectOptionDto1 = new SelectOptionDto();
    		selectOptionDto1.setId(DictCodeList.处理模式_周期型.getValue());
    		selectOptionDto1.setText(DictCodeList.处理模式_周期型.getCodeName());
    		list.add(selectOptionDto1);
    		SelectOptionDto selectOptionDto2 = new SelectOptionDto();
    		selectOptionDto2.setId(DictCodeList.处理模式_分档.getValue());
    		selectOptionDto2.setText(DictCodeList.处理模式_分档.getCodeName());
    		list.add(selectOptionDto2);
    	}else if (StringUtil.equals(profitType, DictCodeList.收益类型_货币型.getValue())) {
    		SelectOptionDto selectOptionDto = new SelectOptionDto();
    		selectOptionDto.setId(DictCodeList.处理模式_普通.getValue());
    		selectOptionDto.setText(DictCodeList.处理模式_普通.getCodeName());
    		list.add(selectOptionDto);
    	}else if (StringUtil.equals(profitType, DictCodeList.收益类型_收益型.getValue())) {
    		SelectOptionDto selectOptionDto = new SelectOptionDto();
    		selectOptionDto.setId(DictCodeList.处理模式_普通.getValue());
    		selectOptionDto.setText(DictCodeList.处理模式_普通.getCodeName());
    		list.add(selectOptionDto);
    		SelectOptionDto selectOptionDto2 = new SelectOptionDto();
    		selectOptionDto2.setId(DictCodeList.处理模式_分档.getValue());
    		selectOptionDto2.setText(DictCodeList.处理模式_分档.getCodeName());
    		list.add(selectOptionDto2);
    	}
    	return list;
    }
    
    /**
     * 根据模板代码查询模板
     * @param templetId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getModeleInfoByTemplateId")
    public AjaxResult getModeleInfoByTemplateId(String templateId) {
    	AjaxResult ar = new AjaxResult();
    	
    	FinProductModuleInfo002Dto finProductModuleInfo002Dto = finProductModule002Service.selectByTemplateId(templateId);
    	if(finProductModuleInfo002Dto != null) {
    		ar.setData(finProductModuleInfo002Dto);
    	}else {
    		List<ValidMessage> errorList = new ArrayList<ValidMessage>();
    		errorList.add(new ValidMessage(this.getMessage("FinProductModule001.templetId.right")));
    		ar.setErrorList(errorList);
		}
    	return ar;
    }

}
