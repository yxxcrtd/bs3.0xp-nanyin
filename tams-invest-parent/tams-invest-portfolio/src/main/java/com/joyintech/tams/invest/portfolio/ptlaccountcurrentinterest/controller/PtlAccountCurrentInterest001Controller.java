/*
 * 文件名：PtlAccountCurrentInterest001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlaccountcurrentinterest.controller;
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
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.portfolio.ptlaccountcurrentinterest.dto.PtlAccountCurrentInterest001Dto;
import com.joyintech.tams.invest.portfolio.ptlaccountcurrentinterest.service.PtlAccountCurrentInterest001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 账户活期利率
 * @author fanshuanghu
 * @version 1.0 2018年10月11日
 * @see PtlAccountCurrentInterest001Controller
 * @since 1.0
 */
@Controller
@PageId("PtlAccountCurrentInterest001")
@RequestMapping("/invest/portfolio/ptlaccountcurrentinterest/PtlAccountCurrentInterest001")
public class PtlAccountCurrentInterest001Controller extends BaseController{
    @Autowired
    private PtlAccountCurrentInterest001Service ptlAccountCurrentInterest001Service;
    /**
     * 界面初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
//        this.setSelectOptionToModel(model, DictCode.资金账户类型);
        return super.init(model);
    }
    
    @ResponseBody
    @RequestMapping("getBusiAcctNo")
    public Page<SelectOptionDto> getBusiAcctNo(){
        String param = getParam("search");
        Map<String,Object> map = RequestUtil.getPageParam();
        map.put("param", param);
        return ptlAccountCurrentInterest001Service.getBusiAcctNo(map);
    }
    
    @ResponseBody
    @RequestMapping("findPage")
    public Page<PtlAccountCurrentInterest001Dto> findPage(){
        return ptlAccountCurrentInterest001Service.findPage(RequestUtil.getPageParam());
    }
    
    /**
     * 删除按钮点击时的check
     * @param dto 验证的实体类
     * @param result 验证的结果
     * @return 错误信息
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) PtlAccountCurrentInterest001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 删除操作
     * @param dto 验证的实体类
     * @param br 错误结果
     * @return 错误信息
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) PtlAccountCurrentInterest001Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<PtlAccountCurrentInterest001Dto> processParameter = new ProcessParameter<PtlAccountCurrentInterest001Dto>();
        WorkFlowParameter<PtlAccountCurrentInterest001Dto> params = new WorkFlowParameter<PtlAccountCurrentInterest001Dto>();
        params.setPageId("PtlAccountCurrentInterest002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getCashId());
        params.setBusinessType(DictCodeList.共通业务类型_账户活期利率管理.getValue());
        // 业务说明：账户名称 + "(" + 账户名称 + ")"+ "-"  + "现金流业务类型" + "("  + "现金流业务类型"+")" + "-"  + "现金流代码" + "("  + "现金流代码"+")" + "-"  + "生效日" + "("  + "生效日"+")"
        params.setComment(
            entity -> this.getMessage("PtlAccountCurrentInterest001.acctName") + "("
                      + entity.getAcctName() + ")" + "-"
                      + this.getMessage("PtlAccountCurrentInterest001.finprodType") + "("
                      + entity.getFinprodType() + ")" 
                      + this.getMessage("PtlAccountCurrentInterest001.cashId") + "("
                      + entity.getCashId() + ")" 
                      + this.getMessage("PtlAccountCurrentInterest001.effDate") + "("
                          + DateUtil.format(entity.getEffDate()) + ")" );
        processParameter.setWorkFlowParameter(params);
        String msg = ptlAccountCurrentInterest001Service.start(processParameter,
            dto.getSelectedItem().toArray(
                new PtlAccountCurrentInterest001Dto[dto.getSelectedItem().size()]));
//        StringBuilder s = new StringBuilder();
//        s.append(msg).append(this.getMessage("PtlAccountCurrentInterest001.cashId")).append("-").append(this.getMessage("PtlAccountCurrentInterest001.effDate")).append("[");
//        for(PtlAccountCurrentInterest001Dto dt : dto.getSelectedItem()){
//            s.append((dt.getCashId()+"-"+dt.getEffDateStr()));
//        }
//        s.append("]");
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
        return ptlAccountCurrentInterest001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
}
