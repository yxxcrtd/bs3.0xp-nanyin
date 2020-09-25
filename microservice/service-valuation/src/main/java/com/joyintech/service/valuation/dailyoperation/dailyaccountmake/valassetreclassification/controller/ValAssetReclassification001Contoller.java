/*
 * 文件名：ValAssetReclassification001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
*/
package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valassetreclassification.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valassetreclassification.dto.ValAssetReclassification001Dto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valassetreclassification.dto.ValAssetReclassification001ValidDto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valassetreclassification.service.ValAssetReclassification001Service;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valassetreclassification.service.ValAssetReclassification001ValidService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.interf.base.util.MD5Util;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 模板示例<br>
 * 资产重分类
 * @author 何阳阳
 * @version 1.0 2018年10月24日
 * @see ValAssetReclassification001Contoller
 * @since 1.0
 */

//@RestController(value="serviceValAssetReclassification001Contoller")
@RestController("valAssetReclassification001Ctl_dtx")
@RequestMapping("/ValAssetReclassification001")
public class ValAssetReclassification001Contoller extends BaseController {
	
	@Autowired
	private ValAssetReclassification001Service valAssetReclassification001Service;
	
	@Autowired
	private ValAssetReclassification001ValidService valAssetReclassification001ValidService;
	
    
	
	/**
     * 页面初始化，显示画面<br>
     * 
     * @param model Model
     * @return 初始化路径
     * @see
     * @since 1.0
     */
//    public String init(Model model) {
//        this.setSelectOptionToModel(model, DictCode.金融产品类型);
//        this.setSelectOptionToModel(model, DictCode.债券类型);
//        this.setSelectOptionToModel(model, DictCode.投资目的);
//        model.addAttribute("finproductTypeCode", DictCodeList.金融产品类型_债券.getValue());
//        
//        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
//        
//        if(StringUtil.isNotEmpty(workflowId)){
//            model.addAttribute(GlobalConstant.WORKFLOW_ID,workflowId);
//        }
//        
//        return super.init(model);
//    }
//    
    /**
     * 产品下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    @PostMapping("/getFinProduct/{page}/{length}")
    public Page<SelectOptionDto> getFinprodId(@RequestBody Map<String, Object> paMap,
    		@PathVariable("page") int page, @PathVariable("length") int length) {
        return valAssetReclassification001Service.getFinProduct(page,length,paMap);
    }
    
    /**
     * 检索账套代码
     * 
     * @return 账套代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/getBookSetId/{page}/{length}")
    public Page<SelectOptionDto> getBooksetId(@RequestBody Map<String, Object> paMap,
    		@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return valAssetReclassification001Service.getBookSetId(page,length,paMap);
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/selectReclassList")
    public List<ValAssetReclassification001Dto> findPage(@RequestBody Map<String, Object> params){
            return valAssetReclassification001Service.selectReclassList(params);
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
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid ValAssetReclassification001ValidDto dto, 
                                BindingResult result, String operateType) {
        return commonCheck(result, operateType);
    }
    
    
    /**
     * 保存编辑后的参数
     *
     * @param dto 内部机构dto
     * @return WebResult 验证结果
     * @throws Exception 
     */
//    @Transactional
//    @DTXTransaction
//    @ResponseBody
//    @RequestMapping(value = "doSave", method=RequestMethod.POST)
//    public AjaxResult doAdd(@RequestBody @Valid ValAssetReclassification001ValidDto dto, BindingResult br) throws Exception {
//
//        if(br.hasErrors()){
//            return new AjaxResult(br);
//        }
//        
//        ProcessParameter<ValAssetReclassification001ValidDto> proParam = new ProcessParameter<ValAssetReclassification001ValidDto>();
//        WorkFlowParameter<ValAssetReclassification001ValidDto> params = new WorkFlowParameter<ValAssetReclassification001ValidDto>();
//        MD5Util.string2MD5(dto.getBooksetId()+dto.getBooksetId()+dto.getBooksetId());
//        params.setBusinessCode(dto.getBooksetId());
//        params.setBusinessType(DictCodeList.估值核算业务类型_债券重分类.getValue());
//        params.setPageId(this.getPageId());
//        params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
//        //设置流程业务说明, entity即传入的dto
//        params.setComment(entity -> WorkFlowUtil.concatComment(dto.getBooksetId(),dto.getAssetType(), dto.getCreateDept()!=null?dto.getCreateDept():""));
//        proParam.setWorkFlowParameter(params);
//        
//        String msg = getMessage("COMMON_I_0001");
//        try {
//            valAssetReclassification001ValidService.save(dto);
//        }
//        catch (Exception e) {
//            getLogger().error("启动流程失败：", e);
//            return new AjaxResult(e.getMessage());
//        }
//        
//        return new AjaxResult(msg);
//    }
//    
    @Transactional
    @DTXTransaction
    @PostMapping("save")
    public int save(@RequestBody ValAssetReclassification001ValidDto entity){
         valAssetReclassification001ValidService.save(entity);
         return 1;
    }
    
    

}