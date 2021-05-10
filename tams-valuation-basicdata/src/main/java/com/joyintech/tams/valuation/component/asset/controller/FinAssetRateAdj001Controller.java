/*
 * 文件名：FinAssetRateAdj001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.component.asset.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.service.BaseCommonService;
import com.joyintech.tams.valuation.component.asset.dto.FinAssetRateAdjDto;
import com.joyintech.tams.valuation.component.asset.service.FinAssetRateAdj001Service;
import com.joyintech.tams.valuation.component.business.service.CommonBusinessService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 利率调整
 * @author zhangnian
 * @version 1.0 2018年10月10日
 * @see FinAssetRateAdj001Controller
 * @since 1.0
 */
@Controller
@PageId("FinAssetRateAdj001")
@RequestMapping("/valuation/component/asset/FinAssetRateAdj001")
public class FinAssetRateAdj001Controller extends BaseController {

    @Autowired
    private FinAssetRateAdj001Service finAssetRateAdj001Service;

    @Autowired
    private BaseCommonService baseCommonService;
    
    @Autowired
    private CommonBusinessService commonBusinessService;
    
    
    @Override
    public String init(Model model){
        model.addAttribute("finprodId", this.getParam("finprodId"));
        model.addAttribute("finprodName", this.getParam("finprodName"));
        model.addAttribute("finprodType2", this.getParam("finprodType2"));
        model.addAttribute("finprodType", this.getParam("finprodType"));
        model.addAttribute("finprodMarketId", this.getParam("finprodMarketId"));
        model.addAttribute("vdateString", this.getParam("vdateString"));
        model.addAttribute("finprodAbbr", this.getParam("finprodAbbr"));
        model.addAttribute("workflowId", this.getParam("workflowId"));
        model.addAttribute("rateType", this.getParam("rateType"));
        model.addAttribute("FINPROD_TYPE_F01", DictCodeListExt1.金融产品类型_债券.getValue());
        model.addAttribute("FINPROD_TYPE_F07", DictCodeListExt1.金融产品类型_存款.getValue());
        model.addAttribute("FINPROD_TYPE_F21", DictCodeListExt1.金融产品类型_同业借款.getValue());
        model.addAttribute("FINPROD_TYPE_F17", DictCodeListExt1.金融产品类型_计划类资产.getValue());
        //model.addAttribute("FINPROD_TYPE_TG_F18", DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        model.addAttribute("FINPROD_TYPE_F28", DictCodeListExt1.金融产品类型_通道.getValue());
        model.addAttribute("RATE_TYPE_01", DictCodeList.利率类型_固定利率.getValue());
        model.addAttribute("isInitial", DictCodeList.是否_否.getValue());
        // 平台日期
        model.addAttribute("iPlatformDate", baseCommonService.getSysDate());
        setEntity(model);
        return super.init(model); 
    }
    
    
    private void setEntity(Model model) {
        String workflowId = this.getParam("workflowId");
        //待办
        if(StringUtil.isNotEmpty(workflowId)){
            FinAssetRateAdjDto finAssetRateAdjDto = finAssetRateAdj001Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("finprodId", finAssetRateAdjDto.getFinprodId());
            model.addAttribute("finprodType2", finAssetRateAdjDto.getFinprodType2());
            model.addAttribute("finprodType", finAssetRateAdjDto.getFinprodType());
            model.addAttribute("vdateString", finAssetRateAdjDto.getVdateString());
        }
    }
    /**
     * 初始化
     * <功能详细描述>
     * @param dto
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    
    @RequestMapping(value = "/initAssetRateAdjInfo")
    @ResponseBody
    public List<FinAssetRateAdjDto> initInfo(@RequestBody FinAssetRateAdjDto dto) throws ParseException {
        String finprodId = dto.getFinprodId();
        String workflowId = dto.getWorkflowId();
        String finprodType2 = dto.getFinprodType2();
        String finprodType = dto.getFinprodType();
        String vdateString = dto.getVdateString();
        List<FinAssetRateAdjDto> finAssetRateAdjList = new ArrayList<FinAssetRateAdjDto>();
            //来自代办
            if(StringUtil.isNotEmpty(workflowId)){
                //根据工作流与留痕处理提供的共通方法获取业务实体
                FinAssetRateAdjDto finAssetRateAdjDto = finAssetRateAdj001Service.getBussinessEntityByWorkflowId(workflowId);
                finAssetRateAdjList = finAssetRateAdjDto.getSelectedItem();
            }else{
                if(StringUtil.isNotEmpty(finprodId)){
                    Map<String,Object> paramMap = new HashMap<String,Object>();
                    paramMap.put("finprodId", finprodId);
                    //paramMap.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
                    paramMap.put("finprodType", dto.getFinprodType());
                    if (DictCodeListExt1.金融产品类型_通道.getValue().equals(finprodType)) {
                    	paramMap.put("finprodType", DictCodeListExt1.金融产品类型_通道.getValue());
                    }
                    paramMap.put("cashType1", DictCodeListExt1.现金流一级类型_利息.getValue());
                    paramMap.put("cashType2", DictCodeListExt1.现金流二级类型_利息.getValue());
                    paramMap.put("finprodType2", finprodType2);
                    paramMap.put("vdateString", vdateString);
                    finAssetRateAdjList = finAssetRateAdj001Service.getfinAssetRateAdjByProductId(paramMap);
                }
            }
        return finAssetRateAdjList;
    }
    

    
    /**
     * 该资产是否有交易数据check
     * <功能详细描述>
     * @param dto
     * @param result
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("isTransInAssetCheck")
    public Boolean isTransInAssetCheck(@RequestBody FinAssetRateAdjDto dto) {
        if(commonBusinessService.isTransInAsset(dto.getFinprodId())!=0){
            return true;
        }
        return false;
    }
    
    /**
     * check--保存
     * @param dto
     * @param result
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) FinAssetRateAdjDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 保存
     * @param dto
     * @param br
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(PAGE_SAVE)
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) FinAssetRateAdjDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        WorkFlowParameter<FinAssetRateAdjDto> params = new WorkFlowParameter<FinAssetRateAdjDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getFinprodMarketId());
        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_存款.getValue())
                || StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_同业借款.getValue())
        ){
            params.setBusinessType(DictCodeList.共通业务类型_存款类资产利率调整.getValue());
        }else if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_债券.getValue())){
            params.setBusinessType(DictCodeList.共通业务类型_债券类资产利率调整.getValue());
        }else if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_计划类资产.getValue())){
            params.setBusinessType(DictCodeList.共通业务类型_计划类资产利率调整.getValue());
        }else if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_通道.getValue())){
            params.setBusinessType(DictCodeList.共通业务类型_通道利率调整.getValue());
        }
        // 设置流程业务说明
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("FinAssetRateAdj001.finprodId")+
                GlobalConstant.OPEN_PARENTHESIS+entity.getFinprodMarketId()+ GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("FinAssetRateAdj001.finprodName")+ GlobalConstant.OPEN_PARENTHESIS+
                entity.getFinprodName()+ GlobalConstant.CLOSE_PARENTHESIS);
        });
        // 给参数变成ProcessParameter而非WorkFlowParameter
        ProcessParameter<FinAssetRateAdjDto> parameter = new ProcessParameter<FinAssetRateAdjDto>();
        parameter.setWorkFlowParameter(params);
        // 系统页面表IS_NEED_WORKFLOW 是否走流程，01-是，02-否
        String msg = finAssetRateAdj001Service.start(parameter, dto);
        return new AjaxResult(msg);
    }
}
