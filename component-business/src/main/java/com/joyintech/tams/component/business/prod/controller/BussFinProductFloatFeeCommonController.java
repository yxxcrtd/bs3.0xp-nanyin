/*
 * 文件名：BussFinProductFloatFeeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.joyintech.framework.annotation.SubPageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.MstFeeinfo;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.prod.BussBenchMarketInfoService;
import com.joyintech.tams.component.business.prod.BussFinProductFloatFeeService;
import com.joyintech.tams.component.business.prod.dto.BussBenchMarkInfoDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFloatFeeDto;
import com.joyintech.tams.component.business.prod.dto.FinProductFloatFeeDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;

/**
 * 浮动管理费
 * 
 * @author 张亮
 * @version 1.0 2018年10月11日
 * @see BussFinProductFloatFeeCommonController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/prod/BussFinProductFloatFeeCommon")
@SubPageId("BussFinProductFloatFee")
public class BussFinProductFloatFeeCommonController extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private BussFinProductFloatFeeService bussFinProductFloatFeeService;
    /**
     * 开放计划组件
     */
    @Autowired
    private BussBenchMarketInfoService bussBenchMarketInfoService;
    @Autowired(required = false)
    private MstWorkflowCommonService mstWorkflowCommonService;
    
    @Override
    public String init(Model model) {
       	// 一体化对应etliu start
//        super.setSelectOptionToModel(model, BussinessDictCode.计费基数_费用品种, BussinessDictCode.计息基础,
//            BussinessDictCode.业绩基准规则, BussinessDictCode.资产净值日类型);
    	setSelectOptionToModel(model,new DictCode[] { DictCode.频率,DictCode.计费基数_费用品种, DictCode.计息基础,
    	    DictCode.业绩报酬计算规则, DictCode.基数日期类型,DictCode.营业日准则,DictCode.计提类型});
        // 一体化对应etliu end
        model.addAttribute("PROFIT_TYPE_02", DictCodeList.收益类型_收益型.getValue());
        model.addAttribute("PROFIT_TYPE_01", DictCodeList.收益类型_净值型.getValue());
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"),
            BussFinProductFloatFeeService.DEFAULT_ENTITY_NAME));
        model.addAttribute(GlobalConstant.PAGE_ID, this.getParam(GlobalConstant.PAGE_ID));
        model.addAttribute("finprodId", this.getParam("finprodId"));
        model.addAttribute(GlobalConstant.WORKFLOW_ID, this.getParam(GlobalConstant.WORKFLOW_ID));
        // 父画面收益类型
        model.addAttribute("profitType", StringUtil.defaultString(this.getParam("profitType")));
        // 父画面生效日期
        model.addAttribute("effDate", StringUtil.defaultString(this.getParam("effDate")));
        model.addAttribute("mdate", this.getParam("mdate"));
        model.addAttribute("vdate", this.getParam("vdate"));
        model.addAttribute("productBuildForm", this.getParam("productBuildForm"));
        model.addAttribute("operationType", this.getParam("operationType"));
        model.addAttribute("dealModeElement", this.getParam("dealModeElement"));
        model.addAttribute("DISPOSE_PATTERN_11", DictCodeList.处理模式_分档.getValue());
        //add by wm 2019-10-09 start 增加产品处理模式-周期型
        model.addAttribute("DISPOSE_PATTERN_10", DictCodeList.处理模式_周期型.getValue());
        //add by wm 2019-10-09 end 增加产品处理模式-周期型
        
        model.addAttribute("periodElement", StringUtil.defaultString(this.getParam("periodElement")));
        model.addAttribute("TERM_TYPE_02", DictCodeListExt2.期限类型_多固定期限.getValue());
        
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

        if (StringUtil.isNotEmpty(this.getParam(GlobalConstant.WORKFLOW_ID))) {
            model.addAttribute(GlobalConstant.WORKFLOW_ID,
                this.getParam(GlobalConstant.WORKFLOW_ID));
            model.addAttribute(GlobalConstant.ENTITY,
                bussFinProductFloatFeeService.productFloatFeeInfoQueryByWflId(
                    this.getParam(GlobalConstant.WORKFLOW_ID),
                    StringUtil.defaultString(this.getParam("entityName"),
                        BussFinProductFloatFeeService.DEFAULT_ENTITY_NAME)));
        }
        else if (StringUtil.isNotEmpty(this.getParam("finprodId"))) {
            model.addAttribute(GlobalConstant.ENTITY,
                bussFinProductFloatFeeService.queryFloatFeeInfoByFinprodId(
                    this.getParam("finprodId"), this.getParam("effDate")));
        }

        setEntity(model);
        return "component/prod/BussFinProductFloatFeeCommon";
    }

    /**
     * 设置页面参数
     *
     * @param model model
     * @see
     * @since 1.0
     */
    private void setEntity(Model model) {
    	// 初始化开放计划组件的画面元素
        setSelectOptionToModel(model,
            new DictCode[] {DictCode.重置规则});
        // 利率类型
        List<SelectOptionDto> intType = new ArrayList<SelectOptionDto>();
        SelectDto selectDto1 = this.getSelectDataFromDataDic(DictCode.利率类型, null);
        for (SelectOptionDto dto : selectDto1.getOptions()) {
            if (!StringUtil.equals(dto.getId(), DictCodeList.利率类型_结构性.getValue())) {
                SelectOptionDto dto1 = new SelectOptionDto();
                dto1.setId(dto.getId());
                dto1.setText(dto.getText()); 
                intType.add(dto1);
            } 
        }
        model.addAttribute("INT_TYPE", JsonUtils.objectArray2Json(intType.toArray()));
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
        model.addAttribute("INT_TYPE_01", DictCodeList.利率类型_固定利率.getValue());
        model.addAttribute("entityNameFloat", "bussBenchMarkFloatInfoDto");
        //model.addAttribute(GlobalConstant.PAGE_ID, this.getParam(GlobalConstant.PAGE_ID));
        //model.addAttribute(GlobalConstant.WORKFLOW_ID, this.getParam(GlobalConstant.WORKFLOW_ID));
        // 父画面处理模式Id
        //model.addAttribute("profitType", StringUtil.defaultString(this.getParam("profitType")));
        //model.addAttribute("PROFIT_TYPE_01", DictCodeList.收益类型_净值型.getValue());
        //model.addAttribute("PROFIT_TYPE_02", DictCodeList.收益类型_收益型.getValue());
        //model.addAttribute("dealModeElement", StringUtil.defaultString(this.getParam("dealModeElement")));
        //model.addAttribute("DISPOSE_PATTERN_11", DictCodeList.处理模式_分档.getValue());
        
        this.setBenchEntity(model);
    }
    
    /**
     * 设置页面参数
     *
     * @param model model
     * @see
     * @since 1.0
     */
    private void setBenchEntity(Model model) {
        // 在工作流中打开
        String workFlowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        String finprodId = this.getParam("finprodId");
        if (StringUtil.isNotEmpty(workFlowId)) {
            BussBenchMarkInfoDto param = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(
                workFlowId, "bussBenchMarkFloatInfoDto", BussBenchMarkInfoDto.class);
            model.addAttribute("entity_float", param);
        }
        else if (StringUtil.isNotEmpty(finprodId)){
            // 编辑和双击查看时
            String effDate = this.getParam("effDate");
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("finprodId", finprodId);
            param.put("effDate", effDate);
            param.put("baseRuleType", DictCodeListExt1.业绩基准类型_浮动管理费.getValue());
            param.put("dealMode", this.getParam("dealMode"));
//            param.put("publishType", DictCodeListExt1.披露类型_单值披露.getValue());
            if (StringUtil.isNotEmpty(finprodId) && null != bussBenchMarketInfoService) {
                BussBenchMarkInfoDto dto = bussBenchMarketInfoService.getByFinprodIdAndEffDate(
                    param);
                model.addAttribute("entity_float", dto);
            }
        }
    }
    
    /**
     * 查询费用品种信息下拉框
     * 
     * @return 费用品种信息下拉框
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryFeeNameInfoList")
    public List<SelectOptionDto> queryFeeNameInfoList() {
        return bussFinProductFloatFeeService.queryFeeInfoList(RequestUtil.getPageParam());
    }

    /**
     * 根据费用代码查询费用基本信息
     * 
     * @param feeId 费用代码
     * @return 费用基本信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryMstFeeinfoByFeeId")
    public MstFeeinfo queryMstFeeinfoByFeeId(String feeId) {
        return bussFinProductFloatFeeService.queryMstFeeinfoByFeeId(feeId);
    }

    /**
     * 查询业绩报酬计算规则明细列表
     * 
     * @return 业绩报酬计算规则明细列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("productFloatFeeQuery")
    public List<FinProductFloatFeeDto> productFloatFeeQuery() {
        // 金融产品代码
        String finprodId = this.getParam("finprodId");
        // 工作流代码
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        // 生效日期
        String effDate = this.getParam("effDate");
        if (StringUtil.isNotEmpty(workflowId)
            && !StringUtil.equals(this.getParam("quickFlag"), "true")) {
            BussFinProductFloatFeeDto bussFinProductFloatFeeDto = bussFinProductFloatFeeService.productFloatFeeInfoQueryByWflId(
                workflowId, StringUtil.defaultString(this.getParam("entityName"),
                    BussFinProductFloatFeeService.DEFAULT_ENTITY_NAME));
            if (bussFinProductFloatFeeDto != null && !StringUtil.isEmptyList(bussFinProductFloatFeeDto.getFinFloatCalcRuleDetailDtoList())) {
                return bussFinProductFloatFeeDto.getFinFloatCalcRuleDetailDtoList();
            }

        }
        else if (StringUtil.isNotEmpty(finprodId)) {
            return bussFinProductFloatFeeService.queryFloatFeeDetailByFinprodId(finprodId,
                effDate);
        }
        return Collections.emptyList();
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getPageId() {
        return this.getParam(GlobalConstant.PAGE_ID);
    }

    /**
     * 根据金融产品代码查询浮动管理费信息
     * 
     * @param finprodId 金融产品代码
     * @return 浮动管理费信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryFloatFeeInfoByFinprodId")
    public BussFinProductFloatFeeDto queryFloatFeeInfoByFinprodId(String finprodId) {
        return bussFinProductFloatFeeService.queryFloatFeeInfoByFinprodId(finprodId, null);
    }
    /**
     * 生成浮动管理费计划
     * 
     * @return 浮动管理费计划
     * @see
     * @since 1.0
     */
   @ResponseBody
    @RequestMapping("generateFloatFeePlan")
    public List<FinCashPlan> generateFloatFeePlan(@RequestBody BussFinProductFloatFeeDto dto){
    	/*BussFinProductFloatFeeDto dto = BeanUtil.mapToObject(RequestUtil.getPageParam(),
    			BussFinProductFloatFeeDto.class);*/
            return bussFinProductFloatFeeService.generateFloatFeePlan(dto); //bussFinProductFeeInfoService.generatePayFeePlan(dto);
    }
    
   
    
}
