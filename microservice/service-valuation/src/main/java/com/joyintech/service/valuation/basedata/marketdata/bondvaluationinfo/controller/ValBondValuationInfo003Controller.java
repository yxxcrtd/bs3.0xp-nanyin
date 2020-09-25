/*
 * 文件名：ValBondValuationInfo003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ZhangYangFei
 * 修改时间：2018年12月6日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
package com.joyintech.service.valuation.basedata.marketdata.bondvaluationinfo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.basedata.marketdata.bondvaluationinfo.dto.ValBondValuationInfo003Dto;
import com.joyintech.service.valuation.basedata.marketdata.bondvaluationinfo.service.ValBondValuationInfo003Service;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 债券估值行情-新建、删除、修改 Controller
 * 
 * @author ZhangYangFei
 * @version 1.0 2018年12月6日
 * @see ValBondValuationInfo003Controller
 * @since 1.0
 */
//@RestController(value="serviceValBondValuationInfo003Controller")
@RestController("valBondValuationInfo003Ctl_dtx")
@RequestMapping("/ValBondValuationInfo003")
public class ValBondValuationInfo003Controller extends BaseController {

    /**
     * 债券估值行情-新建、删除、修改Service
     */
    @Autowired
    private ValBondValuationInfo003Service valBondValuationInfo003Service;

    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
//    @Override
//    public String init(Model model) {
//        this.setSelectOptionToModel(model, DictCode.估值来源);
////        model.addAttribute("FINPRODID_LIST", JsonUtils.object2Json(getFinprodIdList()));
//        String workflowId = getParam("workflowId");
//        // 来自于代办
//        if (!WorkFlowUtil.isNotFromToDoList()) {
//            // 根据工作流与留痕处理提供的共通方法获取业务实体
//            ValBondValuationInfo003Dto dto = valBondValuationInfo003Service.getBussinessEntityByWorkflowId(workflowId);
//            // 设置到model中
//            model.addAttribute("entity", dto);
//            model.addAttribute("workflowId", workflowId);
//        }
//        else {
//            // 新建
//            if (GlobalConstant.DETAIL_PAGE_MODEL_NEW.equals(this.getPageModel())) {
//
//            }
//            // 修改
//            else if (GlobalConstant.DETAIL_PAGE_MODEL_EDIT.equals(this.getPageModel())) {
//                model.addAttribute("entity", this.getMstBondValuationInfoByKey());
//                model.addAttribute("edit", true);
//            }
//            // 查看
//            else if (GlobalConstant.DETAIL_PAGE_MODEL_REFERENCE.equals(this.getPageModel())) {
//                model.addAttribute("newOrEdit", false);
//                model.addAttribute("entity", this.getMstBondValuationInfoByKey());
//            }
//        }
//        return super.init(model);
//    }

    /**
     * 获取资产代码list
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/getFinprodIdList")
    public List<SelectOptionDto> getFinprodIdList(@RequestBody Map<String, Object> param){
//        List<SelectOptionDto> options = new ArrayList<SelectOptionDto>();
        return valBondValuationInfo003Service.getFinprodIdList(param);
//        return options;
    }
    
    @PostMapping("/checkExistMstBondValuationInfoByKey")
    public ValBondValuationInfo003Dto checkExistMstBondValuationInfoByKey(@RequestBody ValBondValuationInfo003Dto dto) {
    	 return valBondValuationInfo003Service.checkExistMstBondValuationInfoByKey(dto);
    }
    
    /**
     * 根据主键获取对应的债券估值行情
     *
     * @return 债券估值行情
     * @throws Exception 异常
     * @see
     */
    private ValBondValuationInfo003Dto getMstBondValuationInfoByKey() {
        // 债券代码
        String secId = this.getParam("secId");
        String valueDate = this.getParam("valueDate");
        String valueSource = this.getParam("valueSource");
        ValBondValuationInfo003Dto searchDto = new ValBondValuationInfo003Dto();
        searchDto.setSecId(secId);
        searchDto.setValueSource(valueSource);
        searchDto.setValueDate(valueDate);
        return valBondValuationInfo003Service.getMstBondValuationInfoByKey(searchDto);
    }
    
    /**
     * 保存按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @param operateType 操作类型
     * @return check结果
     * @see
     * @since 1.0
     */
//    @ResponseBody
//    @RequestMapping("check")
//    public AjaxResult check(@RequestBody @Valid ValBondValuationInfo003Dto dto,BindingResult result, String operateType) {
//        return commonCheck(result, operateType);
//    }
    
    /**
     * 新建-走流程
     * 
     * @param dto 参数
     * @return
     * @see
     * @since 1.0
     */
//    @Transactional
//    @DTXTransaction
//    @RequestMapping("doCommit")
//    @ResponseBody
//    public AjaxResult doSave(@Valid @RequestBody ValBondValuationInfo003Dto dto, BindingResult br) {
//        if(br.hasErrors()){
//            return new AjaxResult(br);
//        }
//        // 启动流程
//        ProcessParameter<ValBondValuationInfo003Dto> proParam = new ProcessParameter<>();
//        WorkFlowParameter<ValBondValuationInfo003Dto> params = new WorkFlowParameter<>();
//        // 业务类型
//        params.setBusinessType(DictCodeList.估值核算业务类型_债券估值行情.getValue());
//        // 页面id
//        params.setPageId(this.getPageId());
//        // 元素id
//        params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
//        
//        // 修改
//        if (StringUtil.isNotEmpty(dto.getValueDateBfUpd()) && StringUtil.isNotEmpty(dto.getValueSourceBfUpd())) {
//            //用修改前的pk1、pk2、pk3组装pk4
//            dto.setBfUpdPk(dto.getSecId() + dto.getValueSourceBfUpd() + dto.getValueDateBfUpd());
//            //设置流程业务说明, entity即传入的dto
//            params.setComment(entity -> WorkFlowUtil.concatComment("债券估值行情修改：",
//                entity.getSecId(),
//                entity.getValueSource(),
//                entity.getValueDate()
//                ));
//        }
//        // 新建
//        else {
//            // 业务代码
//            params.setBusinessCode(dto.getSecId() + dto.getValueSource() + dto.getValueDate());
//            //设置流程业务说明, entity即传入的dto
//            params.setComment(entity -> WorkFlowUtil.concatComment("债券估值行情新建：",
//                entity.getSecId(),
//                entity.getValueSource(),
//                entity.getValueDate()
//                ));
//        }
//        
//        // 塞入流程参数
//        proParam.setWorkFlowParameter(params);
//        String msg = getMessage("COMMON_I_0001");
//        try {
//            msg = valBondValuationInfo003Service.start(proParam, dto);
//        }
//        catch (Exception e) {
//            getLogger().error("启动流程失败：", e);
//            return new AjaxResult(e.getMessage());
//        }
//        return new AjaxResult(msg);
//    }
    
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody ValBondValuationInfo003Dto entity){
         return valBondValuationInfo003Service.doComplete(entity,OperationType.NEW);
    }
    
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public int update(@RequestBody ValBondValuationInfo003Dto entity){
         return valBondValuationInfo003Service.doComplete(entity,OperationType.MODIFY);
    }
    
    
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody ValBondValuationInfo003Dto entity){
         return valBondValuationInfo003Service.doComplete(entity,OperationType.DELETE);
    }
}
