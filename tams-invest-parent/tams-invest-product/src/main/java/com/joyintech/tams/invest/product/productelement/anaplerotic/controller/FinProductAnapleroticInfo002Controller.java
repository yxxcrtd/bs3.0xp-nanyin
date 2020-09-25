/*
 * 文件名：BussBenchMarkInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：李俊
 * 修改时间：2019年4月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.anaplerotic.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
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
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.prod.dto.BussBenchMarkInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.product.messageadjust.finfloatmgtexpquotation.service.FinFloatMgtExpQuotation001Service;
import com.joyintech.tams.invest.product.productelement.anaplerotic.dto.FinProductAnapleroticInfoDto;
import com.joyintech.tams.invest.product.productelement.anaplerotic.dto.FinProductAnapleroticPlanDto;
import com.joyintech.tams.invest.product.productelement.anaplerotic.service.FinProductAnapleroticInfo002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 回补信息共通
 * 
 * @author 李俊
 * @version 1.0 2019年4月21日
 * @see FinProductAnapleroticInfo002Controller
 * @since 1.0
 */
@PageId("FinProductAnapleroticInfo002")
@Controller
@RequestMapping("/invest/product/productelement/anaplerotic/FinProductAnapleroticInfo002")
public class FinProductAnapleroticInfo002Controller extends BaseController {

    
    @Autowired
    private FinProductAnapleroticInfo002Service finProductAnapleroticInfo002Service;
    /**
     * 浮动管理报价service
     */
    @Autowired
    private FinFloatMgtExpQuotation001Service finFloatMgtExpQuotation001Service;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.回补方式, DictCode.回补规则, BussinessDictCode.计息基础, DictCode.基数日期类型,
            DictCode.频率,DictCode.营业日准则,BussinessDictCode.是否,DictCode.重置规则, DictCode.利率类型);
        model.addAttribute("INT_TYPE_AP_01", DictCodeList.利率类型_固定利率.getValue());
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
        List<SelectOptionDto> finBaseRuleInfoList = finFloatMgtExpQuotation001Service.getFinBaseRuleInfo();
        for (SelectOptionDto dto : finBaseRuleInfoList) {
            SelectOptionDto dto1 = new SelectOptionDto();
            dto1.setId(dto.getId());
            dto1.setText(dto.getText());
            dto1.setObject(DictCodeListExt1.基准类型_2_复合);
            listFloatRate.add(dto1);
        }
        model.addAttribute("INDEX_ID_SOURCE", JsonUtils.objectArray2Json(listFloatRate.toArray()));
           //来自产品端请求
       if (StringUtil.equals(getParam("applyType"), "prod")) {
           //查看模式
           if(StringUtil.equals(GlobalConstant.DETAIL_PAGE_MODEL_REFERENCE, getParam("pageModel")) || StringUtil.equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT, getParam("pageModel")) ){
               //回补id
               FinProductAnapleroticInfoDto  d = JsonUtils.json2Object(getParam("finProductAnapleroticInfoDto"),FinProductAnapleroticInfoDto.class);
               model.addAttribute("entity", d);
               BussBenchMarkInfoDto bussBenchMarkInfoDto = new BussBenchMarkInfoDto();
               BeanUtil.copyPropertiesIgnoreNull(bussBenchMarkInfoDto, d);
               model.addAttribute("entity_benchMark", bussBenchMarkInfoDto);
           }
           model.addAttribute("vdate", getParam("vdate"));
           model.addAttribute("mdate", getParam("mdate"));
           model.addAttribute("calendarId", getParam("calendarId"));
           model.addAttribute("defalutApplyType", DictCodeList.关联范围_产品.getValue());
           model.addAttribute("applyType",getParam("applyType"));
           model.addAttribute("operationType",getParam("operationType"));
           model.addAttribute("dealMode", getParam("dealMode"));
           model.addAttribute("PROD_STYLE", getParam("PROD_STYLE"));
           model.addAttribute("anapleroticList", getParam("anapleroticList"));  
           model.addAttribute("quickInputAnapleroticFlag", getParam("quickInputAnapleroticFlag"));  
           model.addAttribute("parentId",getParam("parentId"));
       }
       if (StringUtil.equals(this.getParam("isTodoList"), "true")) {
           ProcessMark pro = finProductAnapleroticInfo002Service.getBusinessInfo(
               this.getParam(GlobalConstant.WORKFLOW_ID));
           if (pro != null) {
               FinProductAnapleroticInfoDto entity = (FinProductAnapleroticInfoDto)JsonUtils.json2Object(
                   pro.getBusinessData(), FinProductAnapleroticInfoDto.class);
               model.addAttribute("entity", entity);
               model.addAttribute("entity_benchMark", entity.getBussBenchMarkInfoDto());
           }
       } else if(!StringUtil.equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW, getParam("pageModel"))){
    	   if(StringUtil.isEmpty(getParam("finProductAnapleroticInfoDto"))){
	           FinProductAnapleroticInfoDto entity = finProductAnapleroticInfo002Service.queryAnapleroticInfoById(getParam("anapleroticId"),getParam("effDateStr"));
	           if(StringUtil.isEmptyList(entity.getFinProductAnapleroticPlanList())){
	        	   entity.setIsAnapleroticList("N");
	        	   entity.setIsList("01");
	           }
	           model.addAttribute("entity", entity);	           
	           model.addAttribute("mdate", DateUtil.format(entity.getmDate()));
	           model.addAttribute("calendarId", entity.getCalendarId());
	           if(entity!=null){
	        	   BussBenchMarkInfoDto bussBenchMarkInfoDto = new BussBenchMarkInfoDto();
	        	   BeanUtil.copyPropertiesIgnoreNull(bussBenchMarkInfoDto, entity);
	               model.addAttribute("entity_benchMark", bussBenchMarkInfoDto);
	           }
    	   }
       }
       model.addAttribute("anapleroticBasic", "A/365");
       model.addAttribute("DISPOSE_PATTERN_11", DictCodeList.处理模式_分档.getValue());
       return super.init(model);
    }
    
    /**
     * 回补计划生产
     * <功能详细描述>
     * @param Dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("calcProductAnapleroticPlan")
    public List<FinProductAnapleroticPlanDto> calcProductAnapleroticPlan(FinProductAnapleroticInfoDto Dto){
           return finProductAnapleroticInfo002Service.getProductAnapleroticPlanData(Dto);
    }
    
    /**
     * 数据验证
     * <功能详细描述>
     * @param Dto
     * @param result
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@Valid @RequestBody FinProductAnapleroticInfoDto Dto , BindingResult result) {
    	// update by wangcheng 20191028 start 点击计划生成 报流程中数据已存在
        if (Dto.getOperateType()!=null && result != null && result.hasErrors()) {
//            if(StringUtil.isNotEmpty(Dto.getOperateType())){
//               return commonCheck(result, Dto.getOperateType());
//            }
            return new AjaxResult(result);
        }
        return new AjaxResult();
    }
    
    /**
     * 数据验证
     * <功能详细描述>
     * @param Dto
     * @param result
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("saveCheck")
    public AjaxResult saveCheck(@Valid @RequestBody FinProductAnapleroticInfoDto Dto , BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        AjaxResult at = new AjaxResult();
        List<ValidMessage> validList = new ArrayList<>();
        if(!StringUtil.isNotEmpty(Dto.getFinprodId())){
            validList.add(new ValidMessage("anapleroticRatio", this.getMessage("BUSS_COM_E_0038",
                this.getMessage("FinProductAnapleroticInfo.finprodId"))));
        }
        if(Dto.getEffDate()==null){
            validList.add(new ValidMessage("anapleroticRatio", this.getMessage("BUSS_COM_E_0038",
                this.getMessage("FinProductAnapleroticInfo.effDate"))));
        }
//        if(StringUtil.isEmptyList(Dto.getFinProductAnapleroticPlanList())){
//        	if(!"01".equals(Dto.getIsList())){
//        		validList.add(new ValidMessage("anapleroticRatio", this.getMessage("BUSS_COM_E_0038",
//                        "回补计划")));
//        	}
//            
//        }
        if(StringUtil.equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW, Dto.getPageModel())){
            String finprodidTmp = "";
            if(StringUtil.isNotEmpty(Dto.getProdStyle())){
            	finprodidTmp = Dto.getProdStyle();
            }
            else{
            	finprodidTmp = Dto.getFinprodId();
            }
            List<FinProductAnapleroticInfoDto> list =finProductAnapleroticInfo002Service.queryAnapleroticInfoByFinprodId(finprodidTmp);
            if(!StringUtil.isEmptyList(list)){
                validList.add(new ValidMessage("finprodId", this.getMessage("FIN_PROD_E_0224")));
            }
        }

        if (!StringUtil.isEmptyList(validList)){
            at.setErrorList(validList);
        } else {
            String btnName = "";
            if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SAVE, Dto.getOperateType())) {
                btnName = "SysCommon.save";
            }
            else if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SUBMIT, Dto.getOperateType())) {
                btnName = "SysCommon.submit";
            }
            at.setData(this.getMessage("COMMON_C_0001", this.getMessage(btnName)));
        }
        return at;
    }
    /**
     * 保存操作
     *
     * @param dto dto
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody FinProductAnapleroticInfoDto dto)
        throws Exception {
            ProcessParameter<FinProductAnapleroticInfoDto> processParameter = new ProcessParameter<FinProductAnapleroticInfoDto>();
            WorkFlowParameter<FinProductAnapleroticInfoDto> params = new WorkFlowParameter<FinProductAnapleroticInfoDto>();
            params.setElementId(dto.getElementId());
            params.setPageId(this.getPageId());
            params.setBusinessCode(dto.getFinprodId());
            params.setBusinessType(DictCodeListExt1.共通业务类型_回补信息调整.getValue());
            // 设置工作流注解
            params.setComment(
                entity -> this.getMessage("FinProductAnapleroticInfo.finprodId") + "("
                          + entity.getFinprodId() + ")"
                          + this.getMessage("FinProductAnapleroticInfo.finprodAbbr") + "("
                          + entity.getFinprodAbbr() + ")" + this.getMessage("FinProductAnapleroticInfo.effDate")
                          + "(" + DateUtil.format(entity.getEffDate()) + ")"
                          + this.getMessage("FinProductBuild002.mdate") + "("
                          + DateUtil.format(entity.getmDate()) + ")");
            processParameter.setWorkFlowParameter(params);
            
            // 调用工作流事件
            String msg = finProductAnapleroticInfo002Service.start(processParameter, dto);
            // 返回工作流错误信息
            return new AjaxResult(msg);
    }
    
    /**
     * 回补信息新建临时信息
     * 
     * @param dto 回补信息
     * @return 
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("finProductAnapleroticSave")
    public AjaxResult finProductAnapleroticSave(@RequestBody FinProductAnapleroticInfoDto dto) {
        AjaxResult result = new AjaxResult();
        result.setData(dto);
       return result;
    }
    @ResponseBody
    @RequestMapping("queryAnapAndProdInfo")
    public FinProductAnapleroticInfoDto queryAnapAndProdInfo(@RequestBody FinProductAnapleroticInfoDto dto){
        return finProductAnapleroticInfo002Service.queryAnapAndProdInfo(dto.getFinprodId(), dto.getDealMode());
    }
}
