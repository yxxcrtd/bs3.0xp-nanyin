/*
 * 文件名：ValSharePrice001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年6月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basedata.marketdata.valshareprice.controller;

import com.joyintech.tams.valuation.basedata.marketdata.valshareprice.dto.ValSharePriceDto;
import com.joyintech.tams.valuation.basedata.marketdata.valshareprice.service.ValSharePriceService;
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
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;


/**
 * 证券账户管理
 * 〈功能详细描述〉
 * @author wangpeng
 * @version 1.0 2019年6月28日
 * @see ValSharePrice001Controller
 * @since 1.0
 */
@Controller
@PageId("ValSharePrice001")
@RequestMapping("/valuation/basedata/marketdata/valshareprice/ValSharePrice001")
public class ValSharePrice001Controller extends BaseController{
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
        return super.init(model);
    }
    /**
     * 页面查询，表格分页
     * 
     * @return Page
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ValSharePriceDto> findPage()
        throws Exception {
        Page<ValSharePriceDto> page = ValSharePriceService.selectForPage(
            RequestUtil.getParameters(getRequest()));
        return page;
    }
    
    /**
     * 获取页面下拉选信息(资产代码信息）
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/getFinprodIdForAdd")
    public Page<SelectOptionDto> getFinprodIdForAdd() 
        throws Exception {
        Page<SelectOptionDto> temp = ValSharePriceService.getFinprodIdForAdd(RequestUtil.getPageParam());
        return temp;
    }


    /**
     * 删除按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) ValSharePriceDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 删除按钮点击
     * @return AjaxResult
     * @param dto ValSharePriceDto
     * @throws Exception Exception
     * @param  br BindingResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) ValSharePriceDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<ValSharePriceDto> processParameter = new ProcessParameter<ValSharePriceDto>();
        WorkFlowParameter<ValSharePriceDto> params = new WorkFlowParameter<ValSharePriceDto>();
        params.setPageId("ValSharePrice002");
//        params.setPageId(RequestUtil.getParam("pageId"));
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getSharesId());
        params.setBusinessType(DictCodeListExt2.共通业务类型_股票行情信息.getValue());
//        params.setBusinessType("Val30");
        // 设置流程业务说明, entity即传入的dtoSpringContextUtil.getTextValue("ValBondQuotationInfo001.secId")
        params.setComment(
                entity -> SpringContextUtil.getTextValue("ValSharePrice001.sharesId")+"("+ entity.getSharesId() +")"+"-"+SpringContextUtil.getTextValue("ValSharePrice001.dealDate") + "(" + DateUtil.format(entity.getDealDate()) + ")"
                );
        processParameter.setWorkFlowParameter(params);
        String msg = ValSharePriceService.start(processParameter,
            dto.getSelectedItem().toArray(
                new ValSharePriceDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }
    
    /**
     * 导出信息excel
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return ValSharePriceService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }

}
