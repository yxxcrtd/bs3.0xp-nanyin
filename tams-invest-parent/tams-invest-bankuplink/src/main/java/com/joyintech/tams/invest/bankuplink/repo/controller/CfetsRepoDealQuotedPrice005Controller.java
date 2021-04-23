/*
 * 文件名：CfetsRepoDealQuotedPrice003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2020年5月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.bankuplink.repo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.bankuplink.repo.dto.CfetsRepoDealQuotedPrice001Dto;
import com.joyintech.tams.invest.bankuplink.repo.service.CfetsRepoDealQuotedPrice001Service;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;

/**
 * CFETS现券买卖报价
 * 
 * @author zhangliang
 * @version 1.0 2020年5月7日
 * @see CfetsRepoDealQuotedPrice005Controller
 * @since 1.0
 */
@Controller
@PageId("CfetsRepoDealQuotedPrice005")
@RequestMapping("/invest/bankuplink/repo/CfetsRepoDealQuotedPrice005")
public class CfetsRepoDealQuotedPrice005Controller extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private CfetsRepoDealQuotedPrice001Service cfetsRepoDealQuotedPrice001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, BussinessDictCode.回购拆借方向);
        model.addAttribute("entrustStatusUnAccept", BussinessDictCodeList.委托状态_待接收.getValue());
        model.addAttribute("entrustStatusAccept", BussinessDictCodeList.委托状态_已接收.getValue());
        model.addAttribute("unSendStatus", BussinessDictCodeList.银行间上行发送状态_待发送.getValue());
        Map<String, Object> param = RequestUtil.getPageParam();
        model.addAttribute("ps", param.get("ps"));
        model.addAttribute("entrustIdOne", param.get("entrustIdOne"));
        model.addAttribute("settleSpreed", param.get("settleSpreed"));
        //多组合
        model.addAttribute("isDzh",param.get("isDzh"));
        //回购期限
        model.addAttribute("term",param.get("term"));
        model.addAttribute("entrustIds",param.get("entrustIds"));
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
    public List<CfetsRepoDealQuotedPrice001Dto> queryEntrustOrderList() {
        Map<String, Object> param = RequestUtil.getPageParam();
        String isDzh = param.containsKey("isDzh")?(String) param.get("isDzh") : StringUtil.EMPTY;
        if(StringUtil.equals(isDzh,"Y")&&(param.containsKey("entrustIds"))){
            String entrustIds = (String) param.get("entrustIds");
            param.put("entrustIdList", StringUtils.isNotEmpty(entrustIds)?Arrays.asList(entrustIds.split(",")):new ArrayList<>());
        }
        List<CfetsRepoDealQuotedPrice001Dto> list = cfetsRepoDealQuotedPrice001Service.queryEntrustOrderList(
            param);
        if (!StringUtil.isEmptyList(list)) {
            String entrustIdOne = (String)param.get("entrustIdOne");
            if (StringUtil.isNotEmpty(entrustIdOne)) {
                List<CfetsRepoDealQuotedPrice001Dto> result = list.stream().filter(
                    dto -> StringUtil.equals(dto.getEntrustId(), entrustIdOne)).collect(
                        Collectors.toList());
                if(!StringUtil.isEmptyList(result)){
                    List<CfetsRepoDealQuotedPrice001Dto> result2 = list.stream().filter(
                        dto -> !StringUtil.equals(dto.getEntrustId(), entrustIdOne)).collect(
                            Collectors.toList());
                    result.addAll(result2);
                    return result;
                }
            }
        }
        return list;
    }

}
