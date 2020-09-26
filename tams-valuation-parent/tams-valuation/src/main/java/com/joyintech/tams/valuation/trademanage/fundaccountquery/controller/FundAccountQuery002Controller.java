/*
 * 文件名：FundAccountQuery002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年10月12日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.trademanage.fundaccountquery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.valuation.trademanage.fundaccountquery.dto.FundAccountQuery001Dto;
import com.joyintech.tams.valuation.trademanage.fundaccountquery.service.FundAccountQuery001Service;
import com.joyintech.tams.constant.DictCode;

/**
 * 资金账户查询-双击查看
 * 
 * @author niuqianyu
 * @version 1.0 2018年10月12日
 * @see FundAccountQuery002Controller
 * @since 1.0
 */
@Controller
@PageId("FundAccountQuery002")
@RequestMapping("/valuation/trademanage/fundaccountquery/FundAccountQuery002")
public class FundAccountQuery002Controller extends BaseController {

    @Autowired
    private FundAccountQuery001Service fundAccountQuery001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.资金账户类型);
        this.setSelectOptionToModel(model, DictCode.行内行外);
        this.setSelectOptionToModel(model, DictCode.境内外);
        String acctNo = this.getParam("acctNo");
        FundAccountQuery001Dto entity = fundAccountQuery001Service.queryFundAccountInfo(acctNo);
        model.addAttribute("entity", entity);
        return super.init(model);
    }

}
