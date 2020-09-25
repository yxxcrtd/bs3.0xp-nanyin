/*
 * 文件名：FinProductRateQuotation001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.messageadjust.finproductratequotation.controller;

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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.messageadjust.finproductratequotation.dto.FinProductRateQuotationDto;
import com.joyintech.tams.invest.product.messageadjust.finproductratequotation.service.FinProductRateQuotationService;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 信息调整管理_产品费率报价管理Controller
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月11日
 * @see FinProductRateQuotation001Controller
 * @since 1.0
 */
@Controller
@PageId("FinProductRateQuotation001")
@RequestMapping("/invest/product/messageadjust/finproductratequotation/FinProductRateQuotation001")
public class FinProductRateQuotation001Controller extends BaseController{
    
    /**
     * 产品费率报价管理service
     */
    @Autowired
    private FinProductRateQuotationService finProductRateQuotationService;
    
    /**
     * 业务编号组件
     */
    @Autowired(required=false)
    private BaseGetBusinessId bussGetBussinessIdService;
    
    
    @Override
    public String init(Model model) {
        return super.init(model);
    }
    
    /**
     * 产品报价管理查询
     * @return Page<FinProductRateQuotationDto>
     * @throws Exception Exception
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<FinProductRateQuotationDto> selectForPage() throws Exception{
        Map<String,Object> param = RequestUtil.getParameters(getRequest());
        return finProductRateQuotationService.selectForPage(param);
    }
    /**
     * 产品代码联想下拉框
     * @return List<SelectOptionDto>
     */
    @ResponseBody
    @RequestMapping("getFinprodId")
    public List<SelectOptionDto> getProdId() {
        return finProductRateQuotationService.getFinprodIds(RequestUtil.getPageParam());
    }
    
    /**
     * 产品代码联想下拉框
     * @return List<SelectOptionDto>
     */
    @ResponseBody
    @RequestMapping("getSelectProdStyleByFinprodId")
    public List<SelectOptionDto> getSelectProdStyleByFinprodId() {
        Map<String ,Object> param = RequestUtil.getPageParam();
        if(StringUtil.isNotEmpty(param.get("finprodId"))){
            param.put("finprodMarketId", param.get("finprodId")); 
        }
        return finProductRateQuotationService.getSelectProdStyleByFinprodId(param);
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
        return finProductRateQuotationService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
    
    /**
     * 按钮点击时的check
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
        ValidateGroupDelete.class}) FinProductRateQuotationDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 
     * 启动工作流执行删除
     * <功能详细描述>
     * @param dto FinProductRateQuotationDto
     * @return AjaxResult
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult delete(@RequestBody FinProductRateQuotationDto dto) throws Exception{
        String code = null;
//        if(bussGetBussinessIdService != null){
        BussinessCodeParameter param = new BussinessCodeParameter();
        param.setCommonBusinessType(DictCodeList.共通业务类型_产品费率报价调整);
        code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(param):IDGenerator.generate();
//        }
        ProcessParameter<FinProductRateQuotationDto> processParameter=new ProcessParameter<FinProductRateQuotationDto>();
        WorkFlowParameter<FinProductRateQuotationDto> params=new WorkFlowParameter<FinProductRateQuotationDto>();
        params.setPageId("FinProductRateQuotation002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(code);
        params.setBusinessType(DictCodeList.共通业务类型_产品费率报价调整.getValue());
        processParameter.setOperationType(OperationType.DELETE);
        //业务说明："产品代码" + "(" + 产品代码 + ")" + "-"  + "费用名称" + "(" + 画面.费用名称 + ")"
        //+ "-"  + "费用品种" + "(" + 画面.费用品种 + ")" + "-"  + "报价生效日期" + "(" + 画面.生效日 + ")"  
        params.setComment(entity -> this.getMessage("FinProductRateQuotation001.finprodId") + "("
                + entity.getFinprodMarketId() +")" +this.getMessage("FinProductRateQuotation002.feeName") + "("
                + entity.getFeeName() +")" + this.getMessage("FinProductRateQuotation002.feeType") + "("
                + entity.getFeeType() +")" +this.getMessage("FinProductRateQuotation001.effDate") + "("
                + DateUtil.format(entity.getEffDate()) + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = finProductRateQuotationService.start(processParameter, 
            dto.getSelectedItem().toArray(new FinProductRateQuotationDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }
}
