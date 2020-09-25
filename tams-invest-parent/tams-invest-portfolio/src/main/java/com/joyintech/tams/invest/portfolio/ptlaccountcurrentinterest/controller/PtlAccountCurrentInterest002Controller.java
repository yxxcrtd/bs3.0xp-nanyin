/*
 * 文件名：PtlAccountCurrentInterest002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlaccountcurrentinterest.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
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
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.portfolio.ptlaccountcurrentinterest.dto.PtlAccountCurrentInterest001Dto;
import com.joyintech.tams.invest.portfolio.ptlaccountcurrentinterest.service.PtlAccountCurrentInterest001Service;
import com.joyintech.tams.invest.portfolio.ptlaccountcurrentinterest.service.PtlAccountCurrentInterest002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 账户活期利率新建
 * @author fanshuanghu
 * @version 1.0 2018年10月11日
 * @see PtlAccountCurrentInterest002Controller
 * @since 1.0
 */
@Controller
@PageId("PtlAccountCurrentInterest002")
@RequestMapping("/invest/portfolio/ptlaccountcurrentinterest/PtlAccountCurrentInterest002")
public class PtlAccountCurrentInterest002Controller extends BaseController{
    @Autowired
    private PtlAccountCurrentInterest002Service ptlAccountCurrentInterest002Service;
    
    @Autowired
    private PtlAccountCurrentInterest001Service ptlAccountCurrentInterest001Service;
//    @Autowired(required=false)
//    private BaseGetBusinessId baseGetBusinessId;
    @Autowired(required=false)
    private IPlatformDate iPlatformDate;

    /**
     * 界面初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        this.setSelectOptionToModel(model,DictCode.币种,DictCode.利率类型,DictCode.计息基础);
        List<SelectOptionDto> curRateSelect = new ArrayList<SelectOptionDto>();
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.利率行情, null);
        for(SelectOptionDto dto : selectDto.getOptions()){
            SelectOptionDto dto1 = new SelectOptionDto();
            dto1.setId(dto.getId());
            dto1.setText(dto.getText());
            dto1.setObject(DictCodeListExt1.基准类型_2_利率.getValue());
            curRateSelect.add(dto1);
        }

//        SelectDto selectDto1 = this.getSelectDataFromDataDic(DictCode.指数行情, null);
//        for(SelectOptionDto dto : selectDto1.getOptions()){
//            SelectOptionDto dto1 = new SelectOptionDto();
//            dto1.setId(dto.getId());
//            dto1.setText(dto.getText());
//            dto1.setObject(DictCodeListExt1.基准类型_2_指数.getValue());
//            curRateSelect.add(dto1);
//        }
//
//        SelectDto selectDto2 = this.getSelectDataFromDataDic(DictCode.收益率曲线, null);
//        for(SelectOptionDto dto : selectDto2.getOptions()){
//            SelectOptionDto dto1 = new SelectOptionDto();
//            dto1.setId(dto.getId());
//            dto1.setText(dto.getText());
//            dto1.setObject(DictCodeListExt1.基准类型_2_收益率曲线.getValue());
//            curRateSelect.add(dto1);
//        }
//
//        List<SelectOptionDto> fuDto= ptlAccountCurrentInterest002Service.getcurrentRate();
//        for(SelectOptionDto dto : fuDto){
//            SelectOptionDto dto1 = new SelectOptionDto();
//            dto1.setId(dto.getId());
//            dto1.setText(dto.getText());
//            dto1.setObject(DictCodeListExt1.基准类型_2_复合.getValue());
//            curRateSelect.add(dto1);
//        }
        model.addAttribute("RATE_SOURCE", JsonUtils.object2Json(curRateSelect.toArray()));
        String cashId = getParam("cashId");
        // 判断是否存在于留痕表
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (StringUtil.isNotEmpty(workflowId) && !WorkFlowUtil.isNotFromToDoList()) {
            PtlAccountCurrentInterest001Dto dto = ptlAccountCurrentInterest002Service.getBussinessEntityByWorkflowId(
                workflowId);
            if(StringUtil.isEmpty(dto.getBasis())){
                Map<String,Object> map = new HashMap<>();
                map.put("cashId", dto.getCashId());
                map.put("effDate", DateUtil.format(dto.getEffDate()));
                map.put("rateType", dto.getRateType());
                if(StringUtil.isNotEmpty(dto.getCashId()) && dto.getEffDate() != null){
                    dto = ptlAccountCurrentInterest002Service.getInitInfo(map);
                }
            }
            model.addAttribute("entity", dto);
            // 判断逐渐是否为空
        }
        else if (StringUtil.isNotEmpty(cashId) && WorkFlowUtil.isNotFromToDoList()) {
            // 将金融代码和固定格式的日期字符串传入参数集合
            Map<String, Object> map = RequestUtil.getParameters(getRequest());
            map.replace("effDate", DateUtil.format(new Date(Long.parseLong(map.get("effDate").toString()))));
            // 根据估值日和金融代码进行查找
            PtlAccountCurrentInterest001Dto dto = ptlAccountCurrentInterest002Service.getInitInfo(map);
            model.addAttribute("entity", dto);
        }
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("getcurrentRate")
    public List<SelectOptionDto> getcurrentRate(){
        return ptlAccountCurrentInterest002Service.getcurrentRate();
    }
    
    @ResponseBody
    @RequestMapping("getBusiAcctNo")
    public Page<SelectOptionDto> getBusiAcctNo(String pageModel){
    	Map<String,Object> map = RequestUtil.getPageParam();
    	String param = getParam("search");
    	if(pageModel != null && !StringUtil.equals(pageModel, GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
    		param = (String) map.get("initValue");
    	}  
        map.put("param", param);
        return ptlAccountCurrentInterest001Service.getBusiAcctNo(map);
    }

    /**
     * 画面点击保存按钮时check
     *
     * @param dto 实体类
     * @param result
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) PtlAccountCurrentInterest001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    @ResponseBody
    @RequestMapping("getSysDate")
    public String getSysDate(){
        return iPlatformDate != null ? iPlatformDate.getSysDateStr() : DateUtil.format(new Date());
    }
    /**
     * 保存
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) PtlAccountCurrentInterest001Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<PtlAccountCurrentInterest001Dto> processParameter = new ProcessParameter<PtlAccountCurrentInterest001Dto>();
        WorkFlowParameter<PtlAccountCurrentInterest001Dto> params = new WorkFlowParameter<PtlAccountCurrentInterest001Dto>();
        params.setPageId("PtlAccountCurrentInterest002");
        if(dto.getCashId() == null || dto.getCashId().isEmpty()){
//            BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
//            bussinessCodeParameter.setCommonBusinessType(DictCodeList.共通业务类型_账户活期利率管理);
    //        String cashId = baseGetBusinessId != null ? baseGetBusinessId.buildBussinessCode(bussinessCodeParameter):IDGenerator.generate();
            String cashId = CommonUtil.generateCashFlowCode(dto.getAcctNo(), DictCodeListExt1.现金流业务类型_资金账户.getValue(), 0L, DictCodeListExt1.现金流二级类型_利息.getValue());
            dto.setCashId(cashId);
            dto.setFinprodType(DictCodeListExt1.现金流业务类型_资金账户.getValue());
        }
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
        String msg = ptlAccountCurrentInterest002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
