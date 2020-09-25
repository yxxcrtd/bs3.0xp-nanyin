/*
 * 文件名：SecAssetStatistics001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年10月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.poc.report.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.poc.report.service.RiskMonitorIndicators001Service;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;

/**
 * 风险监测指标Controller
 * 〈功能详细描述〉
 * @author liupeilin
 * @version 1.0 2019年11月11日
 * @see RiskMonitorIndicators001Controller
 * @since 1.0
 */
@Controller
@PageId("RiskMonitorIndicators001")
@RequestMapping("/poc/report/RiskMonitorIndicators001")
public class RiskMonitorIndicators001Controller extends BaseController{
    @Autowired
    private RiskMonitorIndicators001Service riskMonitorIndicators001Service;
    
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model,
            new DictCode[] {DictCode.是否}); 
        //平台日
        model.addAttribute("platformDate",DateUtil.format(DateUtil.platDateFromCache()));
        //平台日期前一日
        model.addAttribute("currentDate",DateUtil.addDay(DateUtil.format(DateUtil.platDateFromCache()),-1));
        // 画面元素是否显示
        PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
        pageElementConfigDto.setPageId(this.getPageId());
        Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto);
        model.addAttribute("yesOrNo1",DictCodeList.是否_否.getValue());
        model.addAttribute("isEleShow", isEleShow); 
        JSONArray array = new JSONArray();
        Map<String, Object> surplusMap = new HashMap<String, Object>();
        surplusMap.put("id", "01");
        surplusMap.put("text", "明细");
        Map<String, Object> surplusMap1 = new HashMap<String, Object>();
        surplusMap1.put("id", "02");
        surplusMap1.put("text", "汇总"); 
        Object json = JSON.toJSON(surplusMap);
        array.add(json);
        Object json1 = JSON.toJSON(surplusMap1);
        array.add(json1);
        model.addAttribute("STATISTICAL_METHOD", array);
        return super.init(model);
    }
    
    
    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception { 
        return riskMonitorIndicators001Service.export(this.getPageId(), RequestUtil.getParameters(getRequest()));
    }
    
    /**
     * 理财经理联想下拉框
     * 
     * @return 理财经理列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryManagerInfo")
    public List<SelectOptionDto> queryManagerInfo() {
        return riskMonitorIndicators001Service.queryManagerInfo(RequestUtil.getPageParam());
    }
    /**
     * 产品代码联想下拉框
     * 
     * @return 产品代码列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryProdIdInfo")
    public List<SelectOptionDto> queryProdIdInfo() {
        return riskMonitorIndicators001Service.queryProdIdInfo(RequestUtil.getPageParam());
    }
}
