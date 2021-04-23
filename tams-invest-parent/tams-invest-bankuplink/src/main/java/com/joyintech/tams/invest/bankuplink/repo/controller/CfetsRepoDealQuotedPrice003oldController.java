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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.bankuplink.repo.dto.CfetsRepoDealQuotedPrice003Dto;
import com.joyintech.tams.invest.bankuplink.repo.service.CfetsRepoDealQuotedPrice003Service;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;

/**
 * CFETS现券买卖报价
 * 
 * @author zhangliang
 * @version 1.0 2020年5月7日
 * @see CfetsRepoDealQuotedPrice003oldController
 * @since 1.0
 */
@Controller
@PageId("CfetsRepoDealQuotedPrice003old")
@RequestMapping("/invest/bankuplink/repo/CfetsRepoDealQuotedPrice003old")
public class CfetsRepoDealQuotedPrice003oldController extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private CfetsRepoDealQuotedPrice003Service cfetsRepoDealQuotedPrice003Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, BussinessDictCode.中债_登记托管机构, BussinessDictCode.是否);
        String portfolioId = getParam("portfolioId");
        String secAcctId = getParam("secAcctId");
        model.addAttribute("secAcctId", secAcctId);
        model.addAttribute("portfolioId", portfolioId);
        // add by zhangliang 20200617 正回购添加本方机构过滤条件
        model.addAttribute("mySide", getParam("mySide"));
        model.addAttribute("ibuFlag_repoOverAvaCanContinue", CacheUtil.getSysParam("ibuFlag_repoOverAvaCanContinue"));
        String entityStr = this.getParam("entity");
        CfetsRepoDealQuotedPrice003Dto entity = StringUtil.isNotEmpty(
            entityStr) ? JsonUtils.json2Object(entityStr,
                CfetsRepoDealQuotedPrice003Dto.class) : null;
        if (entity != null) {
            // 已质押券信息一览
            model.addAttribute("entity", entity);
        }
        return super.init(model);
    }

    /**
     * 根据查询符合条件的可质押列表
     * 
     * @param param 入参
     * @return 委托列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/queryPledgeBondList")
    public Page<CfetsRepoDealQuotedPrice003Dto> queryPledgeBondList() {
        return cfetsRepoDealQuotedPrice003Service.queryPledgeBondList(RequestUtil.getPageParam());
    }

}
