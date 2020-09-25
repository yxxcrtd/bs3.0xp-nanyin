/*
 * 文件名：TrdCommandsCollection002Controller.java
 * 版权：Copyright 2018-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.1.2
 * 修改人：王贵奇
 * 修改时间：2019年12月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.1.2
 */

package com.joyintech.tams.invest.trade.investtrade.commandscollection.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.trade.investtrade.commandscollection.dto.TrdCommandsCollection001Dto;
import com.joyintech.tams.invest.trade.investtrade.commandscollection.dto.TrdCommandsCollection002Dto;
import com.joyintech.tams.invest.trade.investtrade.commandscollection.dto.TrdCommandsCollection003Dto;
import com.joyintech.tams.invest.trade.investtrade.commandscollection.service.TrdCommandsCollection002Service;

/**
 *  指令汇总Controller
 * @author 王贵奇
 * @version 1.0 2019年12月31日
 * @see TrdCommandsCollection002Controller
 * @since 1.0
 */
@Controller
@PageId("TrdCommandsCollection002")
@RequestMapping("/invest/trade/investtrade/commandscollection/TrdCommandsCollection002")
public class TrdCommandsCollection002Controller extends BaseController{

    /**
     * 指令汇总001service注入
     */
    @Autowired
    private TrdCommandsCollection002Service trdCommandsCollection002Service;

    /**
     * 初始化方法
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        String assetType = this.getParam("assetType");
        String entityStr = this.getParam("entity");
        TrdCommandsCollection001Dto dto = StringUtil.isNotEmpty(entityStr
                ) ? JsonUtils.json2Object(entityStr, TrdCommandsCollection001Dto.class) : null;
        if(StringUtil.isNotEmpty(dto)) {
        	if(dto!=null) dto.setEntrustList(trdCommandsCollection002Service.selectEntrustList(assetType, dto));
        }
        model.addAttribute("assetType", assetType);
        model.addAttribute("entity", dto);
        model.addAttribute("PINAN_ASSET_TYPE_F01", DictCodeList.平安资产类型_债券.getValue());
        model.addAttribute("PINAN_ASSET_TYPE_F02", DictCodeList.平安资产类型_基金.getValue());
        model.addAttribute("PINAN_ASSET_TYPE_F07", DictCodeList.平安资产类型_质押式回购.getValue());
        model.addAttribute("PINAN_ASSET_TYPE_F10", DictCodeList.平安资产类型_买断式回购.getValue());
        model.addAttribute("PINAN_ASSET_TYPE_F11", DictCodeListExt2.平安资产类型_股票.getValue());
        model.addAttribute("PINAN_ASSET_TYPE_F17", DictCodeListExt2.平安资产类型_一级市场债.getValue());
        setSelectOptionToModel(model,DictCode.资产二级分类,DictCode.资产三级分类,DictCode.交易平台,DictCode.支付方式,
                DictCode.交易方向,DictCode.交易场所,DictCode.清算速度,DictCode.币种,DictCode.上市板,DictCode.WIND一级分类_含债券一级分类等);
        return super.init(model);
    }

    /**
     * 获取委托详情列表
     */
    @ResponseBody
    @RequestMapping("selectEntrustList")
    public List<TrdCommandsCollection002Dto> selectEntrustList() throws Exception {
        String assetType = super.getRequest().getParameter("assetType");
        String entityStr = super.getRequest().getParameter("entity");
        TrdCommandsCollection001Dto dto = StringUtil.isNotEmpty(entityStr
                ) ? JsonUtils.json2Object(entityStr, TrdCommandsCollection001Dto.class) : null;
        if(StringUtil.isNotEmpty(dto)) {
            return trdCommandsCollection002Service.selectEntrustList(assetType, dto);
        }
        else {
            return null;
        }
    }
    
    /**
     * 查询委托质押券详情列表
     */
    @ResponseBody
    @RequestMapping("selectPledgeList")
    public List<TrdCommandsCollection003Dto> selectPledgeList() throws Exception {
        String entityStr = super.getRequest().getParameter("entity");
        TrdCommandsCollection001Dto dto = StringUtil.isNotEmpty(entityStr
            ) ? JsonUtils.json2Object(entityStr, TrdCommandsCollection001Dto.class) : null;
        if(StringUtil.isNotEmpty(dto)) {
            return trdCommandsCollection002Service.selectPledgeList(dto);
        }
        else {
            return null;
        }
    }
    /**
     * 进入多组合页面需要的值
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/initData")
    public TrdCommandsCollection001Dto initData() throws Exception {
        String assetType = this.getParam("assetType");
        String entityStr = this.getParam("entity");
        TrdCommandsCollection001Dto dto = StringUtil.isNotEmpty(entityStr
                ) ? JsonUtils.json2Object(entityStr, TrdCommandsCollection001Dto.class) : null;
        if(StringUtil.isNotEmpty(dto)) {
        	if(dto!=null)dto.setEntrustList(trdCommandsCollection002Service.selectEntrustList(assetType, dto));
        }
        trdCommandsCollection002Service.checkEtrInFlow(dto);
        return dto;
    } 
}
