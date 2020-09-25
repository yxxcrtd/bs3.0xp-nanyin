package com.joyintech.service.valuation.assets.posit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.assets.posit.dto.DateTableDto;
import com.joyintech.service.valuation.assets.posit.dto.ValDespositAssetQuery002Dto;
import com.joyintech.service.valuation.assets.posit.service.ValDespositAssetQuery002Service;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 存款类资产详情页面
 * 
 * @author wangting
 * @version 1.0 2018年7月24日
 * @see ValDespositAssetQuery002Controller
 * @since 1.0
 */
//@RestController(value="serviceValDespositAssetQuery002Controller")
@RestController("valDespositAssetQuery002Ctl_dtx")
@RequestMapping("/ValDespositAssetQuery002")
public class ValDespositAssetQuery002Controller extends BaseController {

    /**
     * ValDespositAssetQuery002Service
     */
    @Autowired
    private ValDespositAssetQuery002Service valDespositAssetQuery002Service;


   
    @PostMapping("/fingByfinprodId")
    public ValDespositAssetQuery002Dto fingByfinprodId(@RequestBody Map<String, Object> param){
//        return valAssetsFundQuery001Service.findPage(RequestUtil.getPageParam());
    	 return valDespositAssetQuery002Service.fingByfinprodId(param);
    
    }

   
  
    
    @GetMapping("getTable1")
    public List<DateTableDto> getTable1(String finprodId) {
    	return valDespositAssetQuery002Service.getTable1(finprodId);
    }
    
    @PostMapping("/getTable2")
    public List<DateTableDto> getTable2(@RequestBody Map<String, Object> param) {
    	return valDespositAssetQuery002Service.getTable2(param);
    }
    
    
    @PostMapping("/getTable3")
    public List<DateTableDto> getTable3(@RequestBody Map<String, Object> param) {
    	return valDespositAssetQuery002Service.getTable3(param);
    }
    
    @Transactional
	@DTXTransaction
    @PostMapping("doCommit")
    public int doCommit(@RequestBody ValDespositAssetQuery002Dto param) {
    	return valDespositAssetQuery002Service.doCommit(param);
    }
    
    
    
}
