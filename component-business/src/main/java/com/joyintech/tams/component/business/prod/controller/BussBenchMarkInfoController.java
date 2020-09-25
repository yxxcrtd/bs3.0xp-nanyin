/*
 * 文件名：BussBenchMarkInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.controller;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.prod.BussBenchMarketInfoService;
import com.joyintech.tams.component.business.prod.dto.BussBenchMarkInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业绩基准共通
 * 
 * @author 陈卓
 * @version 1.0 2018年9月17日
 * @see BussBenchMarkInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/prod/BussBenchMarkInfo")
public class BussBenchMarkInfoController extends BaseController {

    /**
     * 开放计划组件
     */
    @Autowired
    private BussBenchMarketInfoService bussBenchMarketInfoService;;

    /**
     * bussRepFundInfoService
     */
    @Autowired(required = false)
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        // 初始化开放计划组件的画面元素
        setSelectOptionToModel(model,
            new DictCode[] {DictCode.重置规则,DictCode.利率类型, DictCode.披露类型, DictCode.是否_中债});
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
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"),
            BussBenchMarketInfoService.DEFAULT_ENTITY_NAME));
        model.addAttribute(GlobalConstant.PAGE_ID, this.getParam(GlobalConstant.PAGE_ID));
        model.addAttribute(GlobalConstant.WORKFLOW_ID, this.getParam(GlobalConstant.WORKFLOW_ID));
        // 父画面处理模式Id
        model.addAttribute("profitType", StringUtil.defaultString(this.getParam("profitType")));
        model.addAttribute("PROFIT_TYPE_01", DictCodeList.收益类型_净值型.getValue());
        model.addAttribute("PROFIT_TYPE_02", DictCodeList.收益类型_收益型.getValue());
        model.addAttribute("dealModeElement", StringUtil.defaultString(this.getParam("dealModeElement")));
        
        model.addAttribute("periodElement", StringUtil.defaultString(this.getParam("periodElement")));
        model.addAttribute("TERM_TYPE_02", DictCodeListExt2.期限类型_多固定期限.getValue());
        //add by wm 2019-10-09 start 增加产品处理模式-周期型
        model.addAttribute("DISPOSE_PATTERN_10", DictCodeList.处理模式_周期型.getValue());
        //add by wm 2019-10-09 end 增加产品处理模式-周期型
        
        model.addAttribute("DISPOSE_PATTERN_11", DictCodeList.处理模式_分档.getValue());
        model.addAttribute("PUBLISH_TYPE_01", DictCodeListExt1.披露类型_区间披露.getValue());
        model.addAttribute("PUBLISH_TYPE_02", DictCodeListExt1.披露类型_单值披露.getValue());

        
        
        //add by wm 2019-10-09 start 增加周期型产品枚举值
        model.addAttribute("DISPOSE_PATTERN_10", DictCodeList.处理模式_周期型.getValue());

        //add by wm 2019-10-09 end 增加周期型产品枚举值
        // 设置页面参数
        setEntity(model);
        return "component/prod/BussBenchMarkInfo";
    }

    /**
     * 设置页面参数
     *
     * @param model model
     * @see
     * @since 1.0
     */
    private void setEntity(Model model) {
        // 在工作流中打开
        String workFlowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        String finprodId = this.getParam("finprodId");
        if (StringUtil.isNotEmpty(workFlowId)) {
            BussBenchMarkInfoDto param = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(
                workFlowId, "bussBenchMarkInfoDto", BussBenchMarkInfoDto.class);
            model.addAttribute(GlobalConstant.ENTITY, param);
        }
        else if (StringUtil.isNotEmpty(finprodId)){
            // 编辑和双击查看时
            String effDate = this.getParam("effDate");
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("finprodId", finprodId);
            param.put("effDate", effDate);  
            param.put("baseRuleType", DictCodeListExt1.业绩基准类型_产品披露.getValue());
            param.put("dealMode", this.getParam("dealMode"));

            if (StringUtil.isNotEmpty(finprodId) && null != bussBenchMarketInfoService) {
                BussBenchMarkInfoDto dto = bussBenchMarketInfoService.getByFinprodIdAndEffDate(
                    param);
                model.addAttribute(GlobalConstant.ENTITY, dto);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPageId() {
        return this.getParam(GlobalConstant.PAGE_ID);
    }
    
    /**
     * 根据金融产品代码查询业绩基准信息
     * 
     * @param finprodId 金融产品代码
     * @return 业绩基准信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryBenchMarkInfoByFinprodId")
    public BussBenchMarkInfoDto queryBenchMarkInfoByFinprodId(String finprodId){
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("finprodId", finprodId);
        return bussBenchMarketInfoService.getByFinprodIdAndEffDate(param);
    }
    
    @ResponseBody
    @RequestMapping("queryBenchMarkInfoByFinprodIdAndBaseRuleType")
    public BussBenchMarkInfoDto queryBenchMarkInfoByFinprodIdAndBaseRuleType(String finprodId, String baseRuleType, String dealMode){
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("finprodId", finprodId);
        param.put("baseRuleType", baseRuleType);
        if(StringUtil.isNotEmpty(dealMode)){
        	param.put("dealMode", dealMode);
        }
        return bussBenchMarketInfoService.getByFinprodIdAndEffDate(param);
    }
}
