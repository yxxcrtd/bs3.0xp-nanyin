/*
 * 文件名：MstSharePrice002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.common.basicdata.marketdata.mstshareprice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.basicdata.marketdata.mstshareprice.dto.ComSharePriceDto;
import com.joyintech.tams.common.basicdata.marketdata.mstshareprice.service.ComSharePriceService;
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
 * @see ComSharePrice002Controller
 * @since 1.0
 */
@Controller("ComSharePrice002")
@PageId("ComSharePrice002")
@RequestMapping("/common/basicdata/marketdata/mstshareprice/ComSharePrice002")
public class ComSharePrice002Controller extends BaseController{
    /**
     * 股票行情Service
     */
    @Autowired
    private ComSharePriceService comSharePriceService;
    
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
        	ComSharePriceDto mstSharePriceDto=null;
            Map<String,Object> pageParam=new HashMap<>();
            pageParam.put("sharesId", sharesId);
            pageParam.put("dealDateString", dealDateString);
            List<ComSharePriceDto> selectAllList = comSharePriceService.selectAllList(pageParam);
            if(selectAllList!=null && selectAllList.size()>0){
            	mstSharePriceDto = selectAllList.get(0);
            }
            model.addAttribute("entity", mstSharePriceDto);
        }
        else if(StringUtil.isNotEmpty(workflowId)){
    		ComSharePriceDto ComSharePriceDto = comSharePriceService.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", ComSharePriceDto);
        }
        return super.init(model);
    }
    /**
     * 
     * 保存时，check操作
     * <功能详细描述>
     * @param dto ComSharePriceDto
     * @param result result
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) ComSharePriceDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 
     * 保存提交
     * @param dto ComSharePriceDto
     * @return AjaxResult
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) ComSharePriceDto dto, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        WorkFlowParameter<ComSharePriceDto> params = new WorkFlowParameter<ComSharePriceDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getSharesId());
        params.setBusinessType(DictCodeListExt2.共通业务类型_股票行情信息.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
                entity -> SpringContextUtil.getTextValue("ComSharePrice001.sharesId")+"("+ entity.getSharesId() +")"+"-"+SpringContextUtil.getTextValue("ComSharePrice001.dealDate") + "(" + DateUtil.format(entity.getDealDate()) + ")"
                );
        //给参数变成ProcessParameter而非WorkFlowParameter
        ProcessParameter<ComSharePriceDto> parameter = new ProcessParameter<ComSharePriceDto>();
        parameter.setWorkFlowParameter(params);
        //系统页面表IS_NEED_WORKFLOW 是否走流程，01-是，02-否
        String msg = comSharePriceService.start(parameter, dto);
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
        Page<SelectOptionDto> temp = comSharePriceService.getFinprodIdForAdd(RequestUtil.getPageParam());
        return temp;
    }
        
    /**
     * 通过id获取最近指定日期的股票行情信息   
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/getLatelyShareById")
    public ComSharePriceDto getLatelyShareById() 
        throws Exception {
    	ComSharePriceDto temp = comSharePriceService.getLatelyShareById(RequestUtil.getPageParam());
        return temp;
    }

}
