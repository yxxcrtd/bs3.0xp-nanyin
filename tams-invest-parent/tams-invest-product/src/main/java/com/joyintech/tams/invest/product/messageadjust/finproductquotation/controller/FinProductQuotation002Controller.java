package com.joyintech.tams.invest.product.messageadjust.finproductquotation.controller;
/**
 * 产品报价管理-新建
 * @author wangkang
 * @date 2018-10-11
 */

import java.util.Collections;
import java.util.List;

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
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.messageadjust.finproductquotation.dto.FinProductQuotation001Dto;
import com.joyintech.tams.invest.product.messageadjust.finproductquotation.dto.FinProductQuotation002Dto;
import com.joyintech.tams.invest.product.messageadjust.finproductquotation.dto.FinProductQuotation003Dto;
import com.joyintech.tams.invest.product.messageadjust.finproductquotation.service.FinProductQuotation002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 产品报价新建
 * 
 * @author zhangliang
 * @version 1.0 2018年11月28日
 * @see FinProductQuotation002Controller
 * @since 1.0
 */
@Controller
@PageId("FinProductQuotation002")
@RequestMapping("/invest/product/messageadjust/finproductquotation/FinProductQuotation002")
public class FinProductQuotation002Controller extends BaseController {

    /**
     * 产品报价管理查询service
     */
    @Autowired
    private FinProductQuotation002Service finProductQuotation002Service;
    
    /**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;

    @Override
    public String init(Model model) {
        //setSelectOptionToModel(model, DictCode.客户等级, DictCode.销售部门);
        model.addAttribute("YES_OR_NOY", DictCodeList.是否_是.getValue());
        model.addAttribute("platDate", iPlatformDate.getSysDateStr());
        
        String workflowId = getParam("workflowId");
        String finprodId=getParam("finprodId");
        String effDate=getParam("effDateStr");
        if(WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(finprodId) && StringUtil.isNotEmpty(effDate)){
        	FinProductQuotation002Dto dto = finProductQuotation002Service.queryQuoteInfoByFinprodIdEffDate(finprodId, effDate);
        	
            FinProductQuotation001Dto tmpModel = finProductQuotation002Service.queryProdInfoByFinprodId(finprodId);
            //如果是分层产品
            if(tmpModel != null && DictCodeList.是否_是.getValue().equals(tmpModel.getIsLay())) {
                 List<FinProductQuotation003Dto> list=finProductQuotation002Service.queryQuoteLayDetailInfoList(finprodId, effDate);
                 
            	 dto.setLayTypeInfoList(finProductQuotation002Service.queryQuoteLayDetailInfoList(finprodId, effDate));
            }
            model.addAttribute("entity", dto);
            if(StringUtil.isNotEmpty(dto.getEffDateOld())){
                model.addAttribute("effDateOld", dto.getEffDateOld());
            }
            else{
                model.addAttribute("effDateOld", dto.getEffDate());
            }
        }
        else if(!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)){
            FinProductQuotation002Dto dto = finProductQuotation002Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", dto);
            model.addAttribute("workflowId", workflowId);
            if(StringUtil.isNotEmpty(dto.getEffDateOld())){
                model.addAttribute("effDateOld", dto.getEffDateOld());
            }
            else{
                model.addAttribute("effDateOld", dto.getEffDate());
            }
        }
        return super.init(model);
    }

    /**
     * 产品代码联想下拉框
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping("getFinprodId2")
    public List<SelectOptionDto> getProdId(String search) {
        return finProductQuotation002Service.getFinprodId2(search);
    }

    /**
     * 根据产品代码查询产品基本信息
     * 
     * @param finprodId 金融产品代码
     * @return 基本信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryProdInfoByFinprodId")
    public FinProductQuotation001Dto queryProdInfoByFinprodId(String finprodId) {
        return finProductQuotation002Service.queryProdInfoByFinprodId(finprodId);
    }
    
    /**
     * 保存时的check
     * 
     * @param dto 验证对象
     * @param result 验证结果
     * @return 验证信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated FinProductQuotation002Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 保存
     * 
     * @param dto 保存对象
     * @param br 验证信息
     * @return 操作结果
     * @throws Exception 异常信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated FinProductQuotation002Dto dto, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<FinProductQuotation002Dto> processParameter = new ProcessParameter<FinProductQuotation002Dto>();
        WorkFlowParameter<FinProductQuotation002Dto> params = new WorkFlowParameter<FinProductQuotation002Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getFinprodMarketId());
        params.setBusinessType(DictCodeList.共通业务类型_产品报价调整.getValue());
        params.setComment(entity -> this.getMessage("FinProductQuotation001.finprodMarketId") + "("
            + entity.getFinprodMarketId() +")" + this.getMessage("FinProductQuotation001.effDate") + "("
            + DateUtil.format(entity.getEffDate()) + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = finProductQuotation002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    /**
     * 查询分层明细列表
     * 
     * @return 分层明细列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryLayDetailInfoList")
    public List<FinProductQuotation003Dto> queryLayDetailInfoList(){
        if (StringUtil.isNotEmpty(this.getParam(GlobalConstant.WORKFLOW_ID))) {
            ProcessMark pro = finProductQuotation002Service.getBusinessInfo(
                this.getParam(GlobalConstant.WORKFLOW_ID));
            if (pro != null) {
                FinProductQuotation002Dto entity = (FinProductQuotation002Dto)JsonUtils.json2Object(
                    pro.getBusinessData(), FinProductQuotation002Dto.class);
                return entity.getLayTypeInfoList() != null ? entity.getLayTypeInfoList() : Collections.emptyList();
            }
        }
        else if (StringUtil.isNotEmpty(this.getParam("finprodId"))){
            return finProductQuotation002Service.queryLayerDetailByFinprodId(this.getParam("finprodId"), this.getParam("effDate"));
        }
        
        return Collections.emptyList();
    }
    /**
     * 查询分层明细列表
     * 
     * @return 分层明细报价列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryQuoteLayDetailInfoList")
    public List<FinProductQuotation003Dto> queryQuoteLayDetailInfoList(){
        return finProductQuotation002Service.queryQuoteLayDetailInfoList(this.getParam("finprodId"), this.getParam("effDate"));
    }
}
