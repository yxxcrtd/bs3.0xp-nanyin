/*
 * 文件名：MarketInformationController002.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstbondquotationinfo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.basicdata.mstbondquotationinfo.dto.MstBondQuotationInfoDto;
import com.joyintech.tams.invest.basicdata.mstbondquotationinfo.service.MstBondQuotationInfoService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 债券行情信息新建 进行债券估值行情信息的新建
 * 
 * @author wangzehui
 * @version 1.0 2018年7月18日
 * @see MstBondQuotationInfo002Controller
 * @since 1.0
 */
@Controller
@PageId("MstBondQuotationInfo002")
@RequestMapping("/invest/basicdata/mstbondquotationinfo/MstBondQuotationInfo002")
public class MstBondQuotationInfo002Controller extends BaseController {
    /**
     * 定义serviec对象
     */
    @Autowired
    private MstBondQuotationInfoService mstBondQuotationInfoService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String secId = getParam("secId");
        String valueDate = getParam("valueDate");
        String valueSource = getParam("valueSource");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            MstBondQuotationInfoDto dto = mstBondQuotationInfoService.getBussinessEntityByWorkflowId(
                workflowId);
            model.addAttribute("pageModel",dto.getPageModel());
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        else if (WorkFlowUtil.isNotFromToDoList() && 
            StringUtil.isNotEmpty(secId) && StringUtil.isNotEmpty(valueDate)
            && StringUtil.isNotEmpty(valueSource)) {
            //通过主键查询数据
            MstBondQuotationInfoDto dto = mstBondQuotationInfoService.selectByKey(
                secId, valueDate,valueSource);
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        this.setSelectOptionToModel(model, DictCode.估值来源);
        return super.init(model);
    }
    /**
     * 
     * 新建页面查询资产代码
     * <功能详细描述>
     * @return list
     * @throws Exception Exception
     * @see
     * @param param param
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getSecIdToNew")
    public List<SuggestDto> getSecIdToNew(String param)
        throws Exception{
        List<SuggestDto> list = new ArrayList<SuggestDto>();
        for(SuggestDto dto:mstBondQuotationInfoService.getSecIdToNew(param)){
            if(dto!=null){
                list.add(dto);
            }
        }
        return list;
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
        ValidateGroupEdit.class}) MstBondQuotationInfoDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
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
        ValidateGroupEdit.class}) MstBondQuotationInfoDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<MstBondQuotationInfoDto> processParameter = new ProcessParameter<MstBondQuotationInfoDto>();
        WorkFlowParameter<MstBondQuotationInfoDto> params = new WorkFlowParameter<MstBondQuotationInfoDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        if (StringUtil.isEmpty(dto.getSecId())) {
            dto.setSecId(IDGenerator.generate());
        }
        params.setBusinessCode(dto.getSecId());
        params.setBusinessType(DictCodeList.共通业务类型_债券估值行情信息.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> this.getMessage("MstBondQuotationInfo001.secId") + "(" + entity.getSecId() + ")" + "-" 
            + this.getMessage("MstBondQuotationInfo001.valueDate") + "("
            + DateUtil.format(entity.getValueDate()) + ")"+ this.getMessage("MstBondQuotationInfo001.valueSource") + "("
            + entity.getValueSourceStr() + ")");
        processParameter.setWorkFlowParameter(params);
        
        //add wangwei1 需求优化 默认导入日= 录入日 =平台日
        Date sysDate = DateUtil.platDateFromCache();// 获取平台日期
        dto.setCreateTime(sysDate);
        
      // 流程开始
        String msg = mstBondQuotationInfoService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    /**
     * 资产代码的联想文本框事件
     * 
     * @return 查询的资产代码和资产名称列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getAssetCodeList")
    public List<SelectOptionDto> getAssetCodeList()
        throws Exception { 
    	Map<String,Object> param = RequestUtil.getPageParam();
        List<SelectOptionDto> temp = mstBondQuotationInfoService.getFinprodAbbrOption(param);
        return temp;
    }
}
