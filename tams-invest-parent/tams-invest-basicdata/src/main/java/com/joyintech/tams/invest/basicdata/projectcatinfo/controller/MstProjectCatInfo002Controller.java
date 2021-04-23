/*
 * 文件名：MstProjectCatInfo002Controller.java
 * 版权：Copyright 2017-2021 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：mrbbp@qq.com
 * 修改时间：2020-06-30T15:13:50.609
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.projectcatinfo.controller;

import javax.validation.Valid;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.invest.basicdata.projectcatinfo.dto.MstProjectCatInfoDto;
import com.joyintech.framework.annotation.PageId;
import org.springframework.stereotype.Controller;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.ui.Model;
import com.joyintech.tams.invest.basicdata.projectcatinfo.service.MstProjectCatInfo002Service;
import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.validation.annotation.Validated;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目类资产估值行情信息
 * 
 * @author mrbbp@qq.com
 * @version 1.0 2020-06-30T15:13:50.609
 * @see MstProjectCatInfo002Controller
 * @since 1.0
 */
@Controller
@PageId("MstProjectCatInfo002")
@RequestMapping("/invest/basicdata/projectcatinfo/MstProjectCatInfo002")
public class MstProjectCatInfo002Controller extends BaseController {

    /**
     * 项目类资产估值行情信息Service
     */
    @Autowired
    private MstProjectCatInfo002Service mstProjectCatInfo002Service;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {

        setSelectOptionToModel(model, DictCode.收益类型);

        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        MstProjectCatInfoDto dto = new MstProjectCatInfoDto();
        if (StringUtil.isNotEmpty(workflowId) && !WorkFlowUtil.isNotFromToDoList()) {
            dto = mstProjectCatInfo002Service.getBussinessEntityByWorkflowId(workflowId);
        }
        else if (StringUtil.equals(this.getParam("pageModel"),
            GlobalConstant.DETAIL_PAGE_MODEL_EDIT)
                 || StringUtil.equals(this.getParam("pageModel"),
                     GlobalConstant.DETAIL_PAGE_MODEL_REFERENCE)) {
            dto = mstProjectCatInfo002Service.selectInfo(RequestUtil.getPageParam());

            if (dto != null){

            }

        }
        if (!StringUtil.equals(this.getParam("pageModel"), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)
            && !StringUtil.equals(this.getParam("pageModel"),
                GlobalConstant.DETAIL_PAGE_MODEL_REFERENCE)) {
        }
        model.addAttribute("pageModel",this.getParam("pageModel"));
        model.addAttribute("PAGE_MODEL_NEW",GlobalConstant.DETAIL_PAGE_MODEL_NEW);
        model.addAttribute(GlobalConstant.ENTITY, dto);
        return super.init(model);
    }

    /**
     * 画面Check
     * 
     * @param dto 画面内容
     * @param br check结果
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) MstProjectCatInfoDto dto, BindingResult br) {
        return commonCheck(br, dto.getOperateType());
    }

    /**
     * 保存
     * 
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws WorkFlowException 异常
     */
    @ResponseBody
    @RequestMapping(PAGE_SAVE)
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) MstProjectCatInfoDto dto, BindingResult br)
        throws WorkFlowException {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<MstProjectCatInfoDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<MstProjectCatInfoDto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());

        if(StringUtil.isEmpty(dto.getFinprodId())){
            dto.setFinprodId(IDGenerator.generate());
        }

        // 业务编号
        params.setBusinessCode(dto.getFinProdMarketId() == null ? "-空白-" : dto.getFinProdMarketId().toString());
        // 业务类型
        params.setBusinessType(DictCodeListExt3.共通业务类型_权益类资产估值行情信息.getValue());

        // 设置流程业务说明:资产代码 + "(" + 资产代码 + ")" + "-"  + "估值日期" + "-"  + "估值日期"
        params.setComment(
                entity -> this.getMessage("MstProjectCatInfo002.finprodId") + "("+entity.getFinProdMarketId()+ ")"
                        + this.getMessage("MstProjectCatInfo002.finProdName") + "(" + entity.getFinProdName() + ")"
                        + this.getMessage("MstProjectCatInfo002.valDate") + "(" + DateUtil.format(entity.getValDate()) + ")");

        processParameter.setWorkFlowParameter(params);
        String msg = mstProjectCatInfo002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

    @ResponseBody
    @RequestMapping("/getAssetCodeList")
    public List<SelectOptionDto> getAssetCodeList() throws Exception {

        Map<String,Object> param = RequestUtil.getPageParam();
        param.put("FINPROD_TYPE_TG_F37", DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
        List<SelectOptionDto> temp = mstProjectCatInfo002Service.getAssetCodeList(param);

        return temp;

    }

    /**
     * 资产代码画面联动
     * 通过输入的资产代码联动出资产名称和管理人
     * @param finProdMarketId 市场代码
     * @return dto
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getManagerAndName")
    public MstProjectCatInfoDto getManagerAndName(@RequestBody Map<String, Object> params){
        //将需要的数据字典和参数添加到map中
        Map<String, Object> map = new HashMap<>();
        map.put("finProdMarketId",params.get("finProdMarketId"));
        map.put("YESORNO_YES_01", DictCodeList.是否_是.getValue());
//        map.put("profitType", getParam("profitType"));
        MstProjectCatInfoDto dto = mstProjectCatInfo002Service.selectNameAndFundManager(map);
        return dto;
    }
}
