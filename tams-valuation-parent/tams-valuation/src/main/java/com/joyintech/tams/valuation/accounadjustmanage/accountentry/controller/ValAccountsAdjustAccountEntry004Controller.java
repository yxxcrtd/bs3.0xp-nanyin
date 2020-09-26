/*
 * 文件名：AccountsAdjustAccountEntry004Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.accounadjustmanage.accountentry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.valuation.accounadjustmanage.accountentry.dto.ValAccountsAdjustAccountEntry004Dto;
import com.joyintech.tams.valuation.accounadjustmanage.accountentry.service.ValAccountsAdjustAccountEntry004Service;

/**
 * 查看会计分录详情
 * @author weipeng
 * @version 1.0 2018年7月18日
 * @see ValAccountsAdjustAccountEntry004Controller
 * @since 1.0
 */
@Controller
@RequestMapping("/valuation/accounadjustmanage/accountentry/ValAccountsAdjustAccountEntry004")
@PageId("ValAccountsAdjustAccountEntry004")
public class ValAccountsAdjustAccountEntry004Controller extends BaseController{
    
    @Autowired
    private ValAccountsAdjustAccountEntry004Service valAccountsAdjustAccountEntry004Service;
    
    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String booksetId = this.getParam("booksetId");
        String sceneNodeId = this.getParam("sceneNodeId");
        model.addAttribute("booksetId", booksetId);
        model.addAttribute("sceneNodeId", sceneNodeId);
        return super.init(model);
    }

    /**
     * 根据凭证编号bookId获取对应相同编号的所有数据
     *
     * @return 记账凭证数据
     * @throws Exception
     *             异常
     * @see
     */
    @RequestMapping("searchList")
    @ResponseBody
    public List<ValAccountsAdjustAccountEntry004Dto> searchList() {
        // 凭证编号
        String booksetId = this.getParam("booksetId");
        String sceneNodeId = this.getParam("sceneNodeId");
        return valAccountsAdjustAccountEntry004Service.searchList(booksetId, sceneNodeId);
    }
}
