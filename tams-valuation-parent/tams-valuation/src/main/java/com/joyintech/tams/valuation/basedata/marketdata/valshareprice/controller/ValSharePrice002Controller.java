/*
 * 文件名：ValSharePrice002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basedata.marketdata.valshareprice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.basedata.marketdata.valshareprice.dto.ValSharePriceDto;
import com.joyintech.tams.valuation.basedata.marketdata.valshareprice.service.ValSharePriceService;
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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;


/**
 * 股票行情新建
 * 〈功能详细描述〉
 * @author wangpeng
 * @version 1.0 2019年6月28日
 * @see ValSharePrice001Controller
 * @since 1.0
 */
@Controller
@PageId("ValSharePrice002")
@RequestMapping("/valuation/basedata/marketdata/valshareprice/ValSharePrice002")
public class ValSharePrice002Controller extends BaseController{
    /**
     * 股票行情Service
     */
    @Autowired
    private ValSharePriceService ValSharePriceService;
    
    /**
     * 页面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
    	
        //单选框枚举值设置
        this.setSelectOptionToModel(model, DictCode.股票交易状态);
        String workflowId = getParam("workflowId");
        String sharesId = getParam("sharesId");
        String dealDateString = getParam("dealDate");
        //修改时
        if (StringUtil.isNotEmpty(sharesId)) {
        	ValSharePriceDto ValSharePriceDto=null;
            Map<String,Object> pageParam=new HashMap<>();
            pageParam.put("sharesId", sharesId);
            pageParam.put("dealDateString", dealDateString);
            List<ValSharePriceDto> selectAllList = ValSharePriceService.selectAllList(pageParam);
            if(selectAllList!=null && selectAllList.size()>0){
            	ValSharePriceDto = selectAllList.get(0);
            }
            model.addAttribute("entity", ValSharePriceDto);
        }
        else if(StringUtil.isNotEmpty(workflowId)){
    		ValSharePriceDto ValSharePriceDto = ValSharePriceService.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", ValSharePriceDto);
        }
        return super.init(model);
    }
    /**
     * 
     * 保存时，check操作
     * <功能详细描述>
     * @param dto ValSharePriceDto
     * @param result result
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) ValSharePriceDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 
     * 保存提交
     * @param dto ValSharePriceDto
     * @return AjaxResult
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) ValSharePriceDto dto, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        WorkFlowParameter<ValSharePriceDto> params = new WorkFlowParameter<ValSharePriceDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getSharesId());
        params.setBusinessType(DictCodeListExt2.共通业务类型_股票行情信息.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
                entity -> SpringContextUtil.getTextValue("ValSharePrice001.sharesId")+"("+ entity.getSharesId() +")"+"-"+SpringContextUtil.getTextValue("ValSharePrice001.dealDate") + "(" + DateUtil.format(entity.getDealDate()) + ")"
                );
        //给参数变成ProcessParameter而非WorkFlowParameter
        ProcessParameter<ValSharePriceDto> parameter = new ProcessParameter<ValSharePriceDto>();
        parameter.setWorkFlowParameter(params);
        //系统页面表IS_NEED_WORKFLOW 是否走流程，01-是，02-否
        String msg = ValSharePriceService.start(parameter, dto);
//        String msg ="";
        return new AjaxResult(msg);
    }

    
    /**
     *@title: getFinprodIdForAdd
     *@description: 获取新建界面的资产代码联想下拉框
     *@param: * @param  1
     *@return: com.joyintech.framework.grid.Page<com.joyintech.framework.select.SelectOptionDto>
     *@throws:
     */
    @ResponseBody
    @RequestMapping("/getFinprodIdForAdd")
    public Page<SelectOptionDto> getFinprodIdForAdd() 
        throws Exception {
        Page<SelectOptionDto> temp = ValSharePriceService.getFinprodIdForAdd(RequestUtil.getPageParam());
        return temp;
    }
        
    /**
     * 通过id获取最近指定日期的股票行情信息   
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/getLatelyShareById")
    public ValSharePriceDto getLatelyShareById() 
        throws Exception {
    	ValSharePriceDto temp = ValSharePriceService.getLatelyShareById(RequestUtil.getPageParam());
        return temp;
    }

}
