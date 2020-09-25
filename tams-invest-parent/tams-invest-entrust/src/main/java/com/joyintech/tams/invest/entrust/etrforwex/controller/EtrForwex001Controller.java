/*
 * 文件名：EtrForwex001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiaominghui
 * 修改时间：2019年6月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrforwex.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.entrust.etrforwex.dto.EtrForwexDto;
import com.joyintech.tams.invest.entrust.etrforwex.service.EtrForwexService;

/**
 * 外汇远期委托查询
 * 〈功能详细描述〉
 * @author qiaominghui
 * @version 1.0 2019年6月18日
 * @see EtrForwex001Controller
 * @since 1.0
 */
@Controller
@PageId("EtrForwex001")
@RequestMapping("/invest/entrust/etrforwex/EtrForwex001")
public class EtrForwex001Controller  extends BaseController{
	
	/**
	 * 自定义service
	 */
	@Autowired
	private EtrForwexService etrForwexService;
	
	/**
	 * 页面初始化
	 */
	@Override
    public String init(Model model) {
        setSelectOptionToModel(model,
            new DictCode[] {DictCode.货币对, DictCode.交易方向,
                DictCode.委托状态, DictCode.外汇交易类型});  
        return super.init(model);
    }
	
	/**
     * 
     * 外汇远期委托分页查询
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<EtrForwexDto> selectForPage(){
        Page<EtrForwexDto> page = etrForwexService.selectForPage(
            RequestUtil.getPageParam());
        return page;
    }
    
    
	 /**
     * 
     * 删除、撤销、关闭委托check
     * <功能详细描述>
     * @param dto EtrForwexDto
     * @param result BindingResult
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) EtrForwexDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 撤回委托 
     * <功能详细描述>
     * @param dto EtrForwexDto
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("recall")
    public AjaxResult recallDelegate(@RequestBody EtrForwexDto dto) {
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        List<EtrForwexDto> list = dto.getSelectedItem();
        for(EtrForwexDto etrForwexDto:list){
            if(etrForwexService.recallDelegate(etrForwexDto.getEntrustId())){
                ajaxResult.setMessage(this.getMessage("ETR_X_001"));
            }else{
                errorList.add(new ValidMessage(getMessage("委托代码:"+etrForwexDto.getPortfolioId())+"委托撤销失败"));
                ajaxResult.setErrorList(errorList);
            }
        }
        return ajaxResult;
    }
    /**
     * 关闭委托 
     * 
     * @param dto EtrForwexDto
     * @return AjaxResult
     * @see 
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("close")
    public AjaxResult closeDelegate(@RequestBody EtrForwexDto dto) {
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        List<EtrForwexDto> list = dto.getSelectedItem();
        for(EtrForwexDto etrForwexDto:list){
            if(etrForwexService.closeDelegate(etrForwexDto.getEntrustId(), etrForwexDto.getCloseWay())){
                ajaxResult.setMessage(this.getMessage("ETR_X_003"));
            }else{
                errorList.add(new ValidMessage(getMessage("组合代码:"+etrForwexDto.getPortfolioId())+"委托关闭失败"));
                ajaxResult.setErrorList(errorList);
            }
        }
        return ajaxResult;
    }
    
    /**
     * 
     * 删除投资委托
     * <功能详细描述>
     * @param dto EtrForwexDto
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult deleteDelegate(@RequestBody EtrForwexDto dto) {
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        List<EtrForwexDto> list = dto.getSelectedItem();
        for(EtrForwexDto etrForwexDto:list){
            if(etrForwexService.deleteDelegate(etrForwexDto.getEntrustId())){
                
            }else{
                errorList.add(new ValidMessage(getMessage("组合代码:"+etrForwexDto.getPortfolioId())+"委托删除失败"));
            }
        }
        ajaxResult.setErrorList(errorList);
        return ajaxResult;
    }
    
    /**
     * 文件导出 <功能详细描述>
     * 
     * @return 导出返回参数
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return etrForwexService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
}
