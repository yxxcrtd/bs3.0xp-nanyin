package com.joyintech.service.valuation.assets.posit.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.service.valuation.assets.fund.dto.ValAssetsFundQuery002Dto;
import com.joyintech.service.valuation.assets.plan.dto.ValPlannedassetquery001Dto;
import com.joyintech.service.valuation.assets.posit.dto.ValDespositAssetQuery001Dto;
import com.joyintech.service.valuation.assets.posit.dto.ValDespositAssetQuery002Dto;
import com.joyintech.service.valuation.assets.posit.service.ValDespositAssetQuery001Service;
import com.joyintech.service.valuation.assets.posit.service.ValDespositAssetQuery002Service;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 存款类资产
 * 
 * @author HuangJian
 * @version 1.0 2018年10月30日
 * @see ValDespositAssetQuery001Controller
 * @since 1.0
 */
//@RestController(value="serviceValDespositAssetQuery001Controller")
@RestController("valDespositAssetQuery001Ctl_dtx")
@RequestMapping("/ValDespositAssetQuery001")
public class ValDespositAssetQuery001Controller extends BaseController {

    /**
     * DespositAssetQuery003Service
     */
    @Autowired
    private ValDespositAssetQuery001Service valDespositAssetQuery001Service;

    /**
     * DespositAssetQuery003Service
     */
    @Autowired
    private ValDespositAssetQuery002Service valDespositAssetQuery002Service;

   

    /**
     * 根据条件分页数据查询
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    @ResponseBody
    public Page<ValDespositAssetQuery001Dto> findPage(@RequestBody Map<String,Object> map,
			@PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return valDespositAssetQuery001Service.findPage(page,length,map);
    }

    
    @PostMapping("/findAll")
    public List<ValDespositAssetQuery001Dto> findAll(@RequestBody Map<String, Object> param){
    	 return valDespositAssetQuery001Service.findAll(param);
    }
    /**
     * 资产下拉框获取
     * 
     * @param request HttpServletRequest
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
     @PostMapping("/getFinprodId")
      public List<SelectOptionDto> getFinprodId(@RequestBody Map<String,Object> param)
        throws Exception {
    	 return valDespositAssetQuery001Service.queryValFinproId(param);
    }
   

    @GetMapping("checkFinprodId")
    public boolean checkFinprodId(String finprodId) {
    	return valDespositAssetQuery001Service.checkFinprodId(finprodId);
    }
    
    @Transactional
	@DTXTransaction
    @GetMapping("delete")
    public int delete(String finprodId) {
    	return valDespositAssetQuery001Service.delete(finprodId);
    }
    
    
}
