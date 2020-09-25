/*
 * 文件名：FlowRiskStressTest001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年8月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.stresstestmanage.flowrisksstresstest.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskflowassets.dto.FlowRiskFlowAssets001Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.flowrisksstresstest.dto.FlowRiskStressTest001Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.flowrisksstresstest.service.FlowRiskStressTest001Service;
import com.joyintech.tams.invest.risk.stresstestmanage.riskfactor.dto.RiskFactor001Dto;
import com.joyintech.tools.collectors.JoyinCollectors;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2019年8月13日
 * @see FlowRiskStressTest001Controller
 * @since 1.0
 */
@Controller
@PageId("FlowRiskStressTest001")
@RequestMapping("/invest/risk/stresstestmanage/flowriskstresstest/FlowRiskStressTest001")
public class FlowRiskStressTest001Controller extends BaseController{
    @Autowired(required = false)
    private FlowRiskStressTest001Service flowRiskStressTest001Service;
    
    /**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;
    /**
     * 初始化的查询条件
     */
   private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.指标类型,
            DictCode.监督类型, DictCode.运算符, DictCode.连接符,DictCode.操作符,
            DictCode.单位, DictCode.监督时分, DictCode.监督频率, DictCode.组合层次,
            DictCode.分类维度,DictCode.收益类型,DictCode.开放类型,DictCode.募集方式,
            DictCode.投资性质
           };

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DICT_CODES);
        SelectDto selectZhccDto = this.getSelectDataFromDataDic(DictCode.组合层次, null);
        List<SelectOptionDto> optionZhcc = selectZhccDto.getOptions();
        Map<String,List<SelectOptionDto>> mapZhcc = optionZhcc.stream().collect(JoyinCollectors.groupingBy(x->x.getId().toString()));
        model.addAttribute("mapZhcc",mapZhcc);
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date sysDate = iPlatformDate.getSysDate();
        Calendar date = Calendar.getInstance();
        date.setTime(sysDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
        Date analysisDate = new Date();
        try {
            analysisDate = dft.parse(dft.format(date.getTime()));
        }
        catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        model.addAttribute("analysisDate",analysisDate);
        return super.init(model);
    }
    
    /**
     * 获取组合树结构数据
     * @return 画面元素
     * @throws Exception Exception
     * @since 1.0
     */
    @SuppressWarnings("rawtypes")
    @ResponseBody
    @RequestMapping("/getAllPortfolioBySort")
    public List<TreeNode> getAllPortfolioBySort(@RequestParam(name="type", required=false) String type,@RequestParam(name="indId", required=false) String indId,@RequestParam(name="portfName", required=false) String portfName,@RequestParam(name="profitType", required=false) String profitType
            ,@RequestParam(name="issueMode", required=false) String issueMode,@RequestParam(name="raiseType", required=false) String raiseType,@RequestParam(name="investType", required=false) String investType)
        throws Exception {
        return flowRiskStressTest001Service.getAllPortfolioBySort(type,indId,portfName,profitType,issueMode,raiseType,investType);
        
    }
    //@RequestBody @Validated RiskFactor001Dto dto, BindingResult br
    @ResponseBody
    @RequestMapping("/setex")
    public void FlowRiskStress(@RequestBody FlowRiskStressTest001Dto testDto){
        FlowRiskStressTest001Dto dto = new FlowRiskStressTest001Dto();
//        dto.setTestName(getParam("testname"));
//        dto.setPortfolioSingleName(getParam("portfolioSingleName"));
//        dto.setSceneId(getParam("sceneId"));
//        dto.setAnalysisVdate(getParam("analysisVdate"));
//        dto.setAnalysisMdate(getParam("analysisMdate"));
//        dto.setRemark(getParam("remark"));
        FlowRiskStressTest001Dto returnDto = flowRiskStressTest001Service.flowRiskStressTest(testDto);
        //存基础信息然后调存储过程
        if(StringUtil.isNotEmpty(returnDto.getTestId())){
            flowRiskStressTest001Service.stressTestLoad(returnDto);
        }
    }
    /*
     * 查询因子下拉框
     * */
    @ResponseBody
    @RequestMapping("querySceneOption")
    public List<SelectOptionDto> querySceneOption(@RequestParam Map<String, Object> param)throws Exception {
        return flowRiskStressTest001Service.querySceneOption(getParam("search"));
    }
    
    /**
     * 保存按钮点击时的check
     * @return check结果
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated FlowRiskStressTest001Dto dto, BindingResult result ) {
        return commonCheck(result, dto.getOperateType());
    }
    
    
    /*
     * 查询压测下拉框
     * */
    @ResponseBody
    @RequestMapping("selectProdSelectOpt")
    public List<SelectOptionDto> selectProdSelectOpt(@RequestParam Map<String, Object> param)
        throws Exception {

        return flowRiskStressTest001Service.selectProdSelectOpt(param);
    }
}
