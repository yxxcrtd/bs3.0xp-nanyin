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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.prod.BussBenchMarketInfoService;
import com.joyintech.tams.component.business.prod.BussFinProductFloatFeeService;
import com.joyintech.tams.component.business.prod.dto.BussBenchMarkLayeredInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;

/**
 * 业绩基准共通
 * 
 * @author 陈卓
 * @version 1.0 2018年9月17日
 * @see BussBenchMarkLayeredInfoCommonController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/prod/BussBenchMarkLayeredInfoCommon")
public class BussBenchMarkLayeredInfoCommonController extends BaseController {

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
            new DictCode[] {DictCode.重置规则,DictCode.利率类型, DictCode.披露类型,DictCode.是否_中债});
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
        model.addAttribute("finprodId", this.getParam("finprodId"));
        // 父画面处理模式Id
        model.addAttribute("profitType", StringUtil.defaultString(this.getParam("profitType")));
        model.addAttribute("PROFIT_TYPE_01", DictCodeList.收益类型_净值型.getValue());
        model.addAttribute("PROFIT_TYPE_02", DictCodeList.收益类型_收益型.getValue());
        model.addAttribute("dealModeElement", StringUtil.defaultString(this.getParam("dealModeElement")));
        model.addAttribute("DISPOSE_PATTERN_11", DictCodeList.处理模式_分档.getValue());
        model.addAttribute("PUBLISH_TYPE_01", DictCodeListExt1.业绩基准类型_产品披露.getValue());
        //add by wm 2019-10-09 start 增加周期型产品及是否代办
        model.addAttribute("DISPOSE_PATTERN_10", DictCodeList.处理模式_周期型.getValue());
        model.addAttribute("isTodoList",this.getParam("isTodoList"));
        //add by wm 2019-10-09 end 增加周期型产品及是否代办
        
        model.addAttribute("periodElement", StringUtil.defaultString(this.getParam("periodElement")));
        model.addAttribute("TERM_TYPE_02", DictCodeListExt2.期限类型_多固定期限.getValue());
        
        return "component/prod/BussBenchMarkLayeredInfoCommon";
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
    public List<BussBenchMarkLayeredInfoDto> queryBenchMarkInfoByFinprodId(){
        String finprodId = this.getParam("finprodId");
        List<BussBenchMarkLayeredInfoDto> result = new ArrayList<>();
        if(!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(this.getParam(GlobalConstant.WORKFLOW_ID))){
            BussBenchMarkLayeredInfoDto wflDto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(
                this.getParam(GlobalConstant.WORKFLOW_ID),
                StringUtil.defaultString(this.getParam("entityName"),
                    BussFinProductFloatFeeService.DEFAULT_ENTITY_NAME), BussBenchMarkLayeredInfoDto.class);
            if(wflDto!=null && !StringUtil.isEmptyList(wflDto.getBussBenchMarkLayeredInfoDtoList())){
            	result = wflDto.getBussBenchMarkLayeredInfoDtoList();
            }
        } else if(StringUtil.isNotEmpty(finprodId)){
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("finprodId", finprodId);
            param.put("baseRuleType", DictCodeListExt1.业绩基准类型_产品披露.getValue());
            result = bussBenchMarketInfoService.getByFinprodIdAndEffDateList(param);
       }
        if(!StringUtil.isEmptyList(result)){
            return bussBenchMarketInfoService.getBenchMarkLayeredInfoList(result);
        }
        return Collections.emptyList();
    }
    
    @ResponseBody
    @RequestMapping("getFloatRate")
    public String getFloatRate(@RequestBody BussBenchMarkLayeredInfoDto dto){
        List<BussBenchMarkLayeredInfoDto> result = new ArrayList<>();
        result.add(dto);
        result = bussBenchMarketInfoService.getBenchMarkLayeredInfoList(result);
        String floatRate = "";
        if(result != null && result.size()>0){
        	BussBenchMarkLayeredInfoDto bussBenchMarkLayeredInfoDto = result.get(0);
            floatRate = bussBenchMarkLayeredInfoDto.getFloatRate() == null? "": bussBenchMarkLayeredInfoDto.getFloatRate().toString();
        }
        return floatRate;
    }
    
    
}
