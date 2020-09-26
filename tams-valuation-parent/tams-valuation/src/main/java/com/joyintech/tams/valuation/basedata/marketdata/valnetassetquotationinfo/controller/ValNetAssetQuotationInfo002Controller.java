/*
 * 文件名：ValNetAssetQuotationInfo002.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年7月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basedata.marketdata.valnetassetquotationinfo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.valuation.basedata.marketdata.valnetassetquotationinfo.dto.ValNetAssetQuotationInfo001Dto;
import com.joyintech.tams.valuation.basedata.marketdata.valnetassetquotationinfo.service.ValNetAssetQuotationInfo001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 新建净值型资产信息
 *可以进行净值型资产行情信息的新建和编辑修改
 * @author liuwei
 * @version 1.0 2018年8月13日
 * @see ValNetAssetQuotationInfo002Controller
 * @since 1.0
 */
@Controller
@PageId("ValNetAssetQuotationInfo002")
@RequestMapping("/valuation/basedata/marketdata/valnetassetquotationinfo/ValNetAssetQuotationInfo002")
public class ValNetAssetQuotationInfo002Controller extends BaseController{
    
    /**
     * 查询和删除Controller
     */
    @Autowired
    private ValNetAssetQuotationInfo001Service valnetassetquotationinfo001service;
    
    /**
     * 新建、编辑、查看界面的初始化
     * 
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){        
        String finprodId = getParam("finprodId");
        String workflowId = getParam("workflowId");
        setSelectOptionToModel(model, DictCode.收益类型);
        if(StringUtil.isNotEmpty(finprodId)) {
            //将输入的估值日期格式化
            String valDate = DateUtil.format(new Date(Long.valueOf(getParam("valDate"))));
            Map<String,Object> param = new HashMap<>();
            //将需要的数据字典和参数添加到map中
			/* param.put("PROFITTYPE_VALUE_01", DictCodeList.收益类型_净值型.getValue()); */
            param.put("YESORNO_YES_01",DictCodeList.是否_是.getValue());
            param.put("finprodId", finprodId);
            param.put("valDate", valDate);
            //根据主键获取业务实体
            ValNetAssetQuotationInfo001Dto mstNetAssetQuotationInfo = valnetassetquotationinfo001service.selectMstNetAsset(param);           
            //设置到model中
            model.addAttribute("entity", mstNetAssetQuotationInfo);
            model.addAttribute("finprodId", finprodId);
        }
            //来自于代办
        else if(!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)){
            //根据工作流与留痕处理提供的共通方法获取业务实体
            ValNetAssetQuotationInfo001Dto mstNetAssetQuotationInfo;
            mstNetAssetQuotationInfo = valnetassetquotationinfo001service.
                    getBussinessEntityByWorkflowId(workflowId);
            //设置到model中
            model.addAttribute("pageModel",mstNetAssetQuotationInfo.getPageModel());
            model.addAttribute("entity", mstNetAssetQuotationInfo);
        } 
        return super.init(model);
    } 
    
    /**
     * 资产代码的联想文本框事件
     * 
     * @return 查询的资产代码和资产名称列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getAssetCodeList")
    public List<SelectOptionDto> getAssetCodeList()
        throws Exception { 
        Map<String,Object> param = RequestUtil.getPageParam();
        //map.put("finProdMarketId", param);
		/* param.put("PROFITTYPE_VALUE_01", DictCodeList.收益类型_净值型.getValue()); */
        param.put("TYPE_TOUGUAN_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        List<SelectOptionDto> temp = valnetassetquotationinfo001service.getAssetCodeList(param);
        return temp;
    }
    
    /**
     * 资产代码画面联动
     * 通过输入的资产代码联动出资产名称和管理人
     * @param finProdMarketId 市场代码
     * @return dto
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getManagerAndName")
    public ValNetAssetQuotationInfo001Dto getManagerAndName(String finProdMarketId, String profitType){
        //将需要的数据字典和参数添加到map中
        Map<String, Object> map = new HashMap<>();
        map.put("finProdMarketId",getParam("finProdMarketId"));
        map.put("PROFITTYPE_VALUE_01", DictCodeList.收益类型_净值型.getValue());
        map.put("YESORNO_YES_01",DictCodeList.是否_是.getValue());
        map.put("profitType", getParam("profitType"));
        return valnetassetquotationinfo001service.selectNameAndFundManager(map);
    }
    
    /**
     * 保存按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) ValNetAssetQuotationInfo001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 保存操作
     *
     * @param dto dto
     * @param br BindingResult
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) ValNetAssetQuotationInfo001Dto dto, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<ValNetAssetQuotationInfo001Dto> processParameter = new ProcessParameter<ValNetAssetQuotationInfo001Dto>();
        WorkFlowParameter<ValNetAssetQuotationInfo001Dto> params = new WorkFlowParameter<ValNetAssetQuotationInfo001Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        if(StringUtil.isEmpty(dto.getFinprodId())){
            dto.setFinprodId(IDGenerator.generate());
        }
        params.setBusinessCode(dto.getFinprodId());
        if(StringUtil.equals(dto.getProfitType(), DictCodeList.收益类型_净值型.getValue())){
        	params.setBusinessType(DictCodeList.共通业务类型_计划类资产_净值型_信息.getValue());
        }else{
        	params.setBusinessType(DictCodeList.共通业务类型_计划类资产_货币型_信息.getValue());
        }
        // 设置流程业务说明:资产代码 + "(" + 资产代码 + ")" + "-"  + "估值日期" + "-"  + "估值日期"
        params.setComment(
            entity -> this.getMessage("ValNetAssetQuotationInfo001.assetCode") + "("+entity.getFinProdMarketId()+ ")"
            + this.getMessage("ValNetAssetQuotationInfo001.valuationDate") + "(" + DateUtil.format(entity.getValDate()) + ")");
        processParameter.setWorkFlowParameter(params);
        
        //add wangwei1 需求优化 默认导入日= 录入日 =平台日
        Date sysDate = DateUtil.platDateFromCache();// 获取平台日期
        dto.setCreateTime(sysDate);
        
        // 流程开始
        String msg = valnetassetquotationinfo001service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
