/*
 * 文件名：FinProductDurDeal002.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.durdeal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.product.durdeal.dto.FinProductDurDeal002Dto;
import com.joyintech.tams.invest.product.durdeal.service.FinProductDurDeal002Service;

/**
 * 产品存续期交易查询 明细画面
 *
 * @author etliu
 * @version 1.0 2018年12月18日
 * @see FinProductDurDeal002Controller
 * @since 1.0
 */
@Controller
@PageId("FinProductDurDeal002")
@RequestMapping("/invest/product/durdeal/FinProductDurDeal002")
public class FinProductDurDeal002Controller extends BaseController {

    /**
     * FinProductDurDeal002Service
     */
    @Autowired
    private FinProductDurDeal002Service finProductDurDeal002Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String finprodId = this.getParam("finprodId");
        String prodId = this.getParam("prodId");
        String cashDate = this.getParam("cashDate");
        String profitType = this.getParam("profitType");
        String isLay = this.getParam("isLay");
        String yield = this.getParam("yield");

        model.addAttribute("finprodId", finprodId);
        model.addAttribute("prodId", prodId);
        model.addAttribute("cashDate", cashDate);
        model.addAttribute("profitType", profitType);
        model.addAttribute("isLay", isLay);
        model.addAttribute("yield", yield);

        return super.init(model);
    }

    /**
     * 分页查询
     *
     * @return Page<FinProductDurDeal002Dto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<FinProductDurDeal002Dto> findPage() throws Exception {
        return finProductDurDeal002Service.findPage(RequestUtil.getPageParam());
    }

}
