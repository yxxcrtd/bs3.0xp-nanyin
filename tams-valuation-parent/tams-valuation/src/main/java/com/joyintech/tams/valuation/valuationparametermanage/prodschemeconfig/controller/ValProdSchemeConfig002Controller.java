/*
 * 文件名：ValProdSchemeConfig001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.valuationparametermanage.prodschemeconfig.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.valuationparametermanage.prodschemeconfig.dto.ValProdSchemeConfig002Dto;
import com.joyintech.tams.valuation.valuationparametermanage.prodschemeconfig.service.ValProdSchemeConfig001Service;
import com.joyintech.tams.valuation.valuationparametermanage.prodschemeconfig.service.ValProdSchemeConfig002Service;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 产品对账方案配置
 * @author weipeng
 * @version 1.0 2018年11月20日
 * @see ValProdSchemeConfig002Controller
 * @since 1.0
 */
@Controller
@PageId("ValProdSchemeConfig002")
@RequestMapping("/valuation/valuationparametermanage/prodschemeconfig/ValProdSchemeConfig002")
public class ValProdSchemeConfig002Controller extends BaseController{
    
    @Autowired
    private ValProdSchemeConfig001Service valProdSchemeConfig001Service;
    
    @Autowired
    private ValProdSchemeConfig002Service valProdSchemeConfig002Service;
    
    /**
     * 对账方案管理 - 页面初始化 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String schemeType = this.getParam("schemeType");
        String booksetIds = this.getParam("booksetIds");
        // 获取模板账套Id 
        String booksetIdTmpl = this.getParam("booksetIdTmpl");
        // 获取方案名称
        List<String> schemeTypeList = new ArrayList<>();
        // 查询所需配置的账套是否有方案类型
        if(StringUtil.isNotEmpty(schemeType)){
            schemeTypeList.add(schemeType);
        }
        // 若没有，则查询全部的估值表和余额表的对账方案
        else{
            schemeTypeList.add(DictCodeListExt1.配置方案类型_估值表对账方案.getValue());
            schemeTypeList.add(DictCodeListExt1.配置方案类型_余额表对账方案.getValue());
        }
        Map<String, Object> params = new HashMap<>();
        params.put("schemeTypeList", schemeTypeList);
        // 只查询对应模板账套下的方案
        params.put("booksetId", booksetIdTmpl);
        List<SelectOptionDto> options = valProdSchemeConfig001Service.getSchemeInfoList(params);
        model.addAttribute("SCHEME_NAME", JsonUtils.objectArray2Json(options.toArray()));
        model.addAttribute("schemeType", schemeType);
        model.addAttribute("booksetIds", booksetIds);
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        
        if(StringUtil.isNotEmpty(workflowId)){
            model.addAttribute(GlobalConstant.WORKFLOW_ID,workflowId);
        }
        return super.init(model);
    }
    
    /**
     * 记账凭证数据取得
     *
     * @return 记账凭证数据
     * @throws Exception
     *             异常
     * @see
     */
    @RequestMapping("findPage")
    @ResponseBody
    public List<ValProdSchemeConfig002Dto> findPage() throws Exception {
        return valProdSchemeConfig002Service.findPage(RequestUtil.getPageParam());
    }
    
    /**
     * 保存处理
     * 
     * @param record 对账规则方案配置新建信息
     * @param result 保存结果
     * @return 保存结果
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = PAGE_SAVE, method = RequestMethod.POST)
    public AjaxResult save() {
        Map<String, Object> params= RequestUtil.getPageParam();
        String datas = (String) params.get("datas");
        String schId = (String) params.get("schId");
        String schemeType = (String) params.get("schemeType");
        try {
            List<ValProdSchemeConfig002Dto> listdto = JsonUtils.json2List(datas, ValProdSchemeConfig002Dto.class);
//            valProdSchemeConfig002Service.doInsertOrupdate(schId, schemeType, listdto);
            valProdSchemeConfig002Service.doInsertOrupdate(schId, schemeType, datas);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(this.getMessage("VAL_COMMON_I_0008") + e.getMessage(),
                MessageType.错误消息);
        }
        return new AjaxResult(getMessage("VAL_COMMON_I_0006"));
    }
    
}
