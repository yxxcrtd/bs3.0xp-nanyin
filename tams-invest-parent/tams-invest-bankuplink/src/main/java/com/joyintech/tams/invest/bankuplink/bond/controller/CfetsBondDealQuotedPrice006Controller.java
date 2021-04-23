/*
 * 文件名：CfetsBondDealQuotedPrice006Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2020年5月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.bankuplink.bond.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.greenpineyu.fel.common.StringUtils;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.bankuplink.bond.dto.CfetsBondDealEntrustOrderDto;
import com.joyintech.tams.invest.bankuplink.bond.service.CfetsBondDealQuotedPrice001Service;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;


/**
 * 现券买卖报价委托选择页面
 * 
 * @author zhangliang
 * @version 1.0 2020年5月19日
 * @see CfetsBondDealQuotedPrice006Controller
 * @since 1.0
 */
@Controller
@PageId("CfetsBondDealQuotedPrice006")
@RequestMapping("/invest/bankuplink/bond/CfetsBondDealQuotedPrice006")
public class CfetsBondDealQuotedPrice006Controller extends BaseController {
    
    /**
     * {@linkplain}
     */
    @Autowired
    private CfetsBondDealQuotedPrice001Service cfetsBondDealQuotedPrice001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, BussinessDictCode.交易方向);
        if (StringUtil.isNotEmpty(this.getParam("finprodId"))){
            model.addAttribute("finprodId", this.getParam("finprodId"));
        }
        if (StringUtil.isNotEmpty(this.getParam("ps"))){
            model.addAttribute("ps", this.getParam("ps"));
        }
        if (StringUtil.isNotEmpty(this.getParam("entrustId"))){
            model.addAttribute("entrustId", this.getParam("entrustId"));
        }
        if (StringUtil.isNotEmpty(this.getParam("mySide"))){
            model.addAttribute("mySide", this.getParam("mySide"));
        }
        if (StringUtil.isNotEmpty(this.getParam("quotaPageModel"))){
            model.addAttribute("quotaPageModel", this.getParam("quotaPageModel"));
        }
        if (StringUtil.isNotEmpty(this.getParam("settleSpreed"))){
            model.addAttribute("settleSpreed", this.getParam("settleSpreed"));
        }
        if (StringUtil.isNotEmpty(this.getParam("editFlag"))){
            model.addAttribute("editFlag", this.getParam("editFlag"));
        }
        if (StringUtil.isNotEmpty(this.getParam("isDzh"))){
        	model.addAttribute("isDzh",this.getParam("isDzh"));
        }
        if (StringUtil.isNotEmpty(this.getParam("entrustIds"))){
        	model.addAttribute("entrustIds",this.getParam("entrustIds"));
        }
        model.addAttribute("secAcctSourceJ", BussinessDictCodeList.账户来源_自营.getValue());
        return super.init(model);
    }
    
    /**
     * 根据查询符合条件的委托列表
     * 
     * @param param 入参
     * @return 委托列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/queryEntrustOrderList")
    public List<CfetsBondDealEntrustOrderDto> queryEntrustOrderList() {
    	Map<String, Object> param = RequestUtil.getPageParam();
        String isDzh = param.containsKey("isDzh")?(String) param.get("isDzh") : StringUtil.EMPTY;
        if(StringUtil.equals(isDzh,"Y")&&(param.containsKey("entrustIds"))){
            String entrustIds = (String) param.get("entrustIds");
            String finprodId = (String) param.get("finprodId");
            if(StringUtils.isNotEmpty(entrustIds)) {
            	param.put("entrustIdList", Arrays.asList(entrustIds.split(",")));
            }
            if(StringUtils.isNotEmpty(finprodId)) {
            	param.put("finprodIdList", finprodId);
            }
        }
        return cfetsBondDealQuotedPrice001Service.queryEntrustOrderList(param);
    }
    
}
