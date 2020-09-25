package com.joyintech.tams.invest.risk.creditmanagement.ceasset.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.risk.creditmanagement.ceasset.dto.CreditLinesAstDto;
import com.joyintech.tams.invest.risk.creditmanagement.ceasset.service.CeAsset001Service;
import com.joyintech.tams.invest.risk.creditmanagement.ceasset.service.CeAsset002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;



/**
 * 资产授信
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see CeAsset001Controller
 * @since 1.0
 */
@Controller
@PageId("CeAsset001")
@RequestMapping("/invest/risk/creditmanagement/ceasset/CeAsset001")
public class CeAsset001Controller extends BaseController {
	 
	/**
     * CeAsset001Service
     */
    @Autowired(required = false)
    private CeAsset001Service ceAsset001Service;
    
    /**
     * CeAsset002Service
     */
    @Autowired(required = false)
    private CeAsset002Service ceAsset002Service;
    
    @Override
    public String init(Model model) {
    	model.addAttribute("cemodel", CacheUtil.getSysParam(BussinessConstant.CEMODEL));
        return super.init(model);
    }
  
    @ResponseBody
    @RequestMapping("/getFinprodMarketIds")
    public List<SelectOptionDto> getFinprodMarketIds()
        throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String param = super.getRequest().getParameter("search");
        map.put("finprodMarketId", param);
        List<SelectOptionDto> temp = ceAsset001Service.getFinprodMarketIds(map);
      
        return temp;
    }
    
    /**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<CreditLinesAstDto> selectAll()
        throws Exception {
        return ceAsset001Service.selectForPage(
            RequestUtil.getParameters(getRequest()));
    }
    
   
	
	/**
     * 保存按钮点击时的check
     * @return check结果
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) CreditLinesAstDto dto, BindingResult result ) {
        return commonCheck(result, dto.getOperateType());
    }
    
	 /**
     * 删除
     * @param dto
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) CreditLinesAstDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<CreditLinesAstDto> processParameter = new ProcessParameter<CreditLinesAstDto>();
        WorkFlowParameter<CreditLinesAstDto> params = new WorkFlowParameter<CreditLinesAstDto>();
        params.setPageId("CeAsset002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getSeqno());
        params.setBusinessType(DictCodeList.共通业务类型_资产授信.getValue());
        // 设置流程业务说明, entity即传入的dto
        if("01".equals(CacheUtil.getSysParam(BussinessConstant.CEMODEL))){
        	  params.setComment(entity -> {
             	 return WorkFlowUtil.concatComment(getMessage("CeAsset002.seqno"),
                          GlobalConstant.OPEN_PARENTHESIS, entity.getSeqno(), GlobalConstant.CLOSE_PARENTHESIS,
                          getMessage("CeAsset002.customerId"), GlobalConstant.OPEN_PARENTHESIS,
                          entity.getCustomerId(), GlobalConstant.CLOSE_PARENTHESIS);
             });
        }
        else{
     	   params.setComment(entity -> {
 	           return WorkFlowUtil.concatComment(getMessage("CeAsset002.seqno"),
 	               GlobalConstant.OPEN_PARENTHESIS, entity.getSeqno(), GlobalConstant.CLOSE_PARENTHESIS,
 	               getMessage("CeAsset002.pflshName"), GlobalConstant.OPEN_PARENTHESIS,
 	               entity.getPflshName(), GlobalConstant.CLOSE_PARENTHESIS);
 	       });
        }
        processParameter.setWorkFlowParameter(params);
        String msg = ceAsset002Service.start(processParameter,
            dto.getSelectedItem().toArray(
                new CreditLinesAstDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }
    
    /**
     * 导出信息excel
     * @return excel
     * @throws Exception 异常
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return ceAsset001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
}
