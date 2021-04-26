/*
 * 文件名：ValVoucherQuery002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.psbc.bankbalancequery.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.financemanage.accountbook.valsubjectbalancequery.service.ValSubjectBalanceQuery002Service;
import com.joyintech.tams.valuation.financemanage.accountbook.valsubjectwaterquery.dto.ValSubjectWaterQuery001Dto;
import com.joyintech.tams.valuation.financemanage.accountbook.valsubjectwaterquery.dto.ValSubjectWaterQuery001SearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 模板示例<br>
 * 凭证详情查看
 * 
 * @author wangyang1
 * @version 1.0 2019年8月29日
 * @see BankBalanceQuery002Contoller
 * @since 1.0
 */
@Controller
@PageId("BankBalanceQuery002")
@RequestMapping("/psbc/bankbalancequery/BankBalanceQuery002")
public class BankBalanceQuery002Contoller extends BaseController {

    /**
     * valSubjectBalanceQuery002Service
     */
    @Autowired
    private ValSubjectBalanceQuery002Service valSubjectBalanceQuery002Service;

    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        //账套代码
        String booksetId = this.getParam("booksetId");
        model.addAttribute("booksetId", booksetId);
        //记账日期
        String balanceDateFmt = this.getParam("balanceDateFmt");
        model.addAttribute("balanceDateFmt", balanceDateFmt);
        //科目号
        String subjectNo = this.getParam("subjectNo");
        model.addAttribute("subjectNo", subjectNo);
        //币种
        String oCcy = this.getParam("oCcy");
        model.addAttribute("oCcy", oCcy);
        //产品
        String finprodId = this.getParam("finprodId");
        model.addAttribute("finprodId", finprodId);
        return super.init(model);
    }

    /**
     * 根据凭证编号bookId获取对应相同编号的所有数据
     *
     * @return 记账凭证数据
     * @throws throws Exception 
     * @throws Exception
     *             异常
     * @see
     */
    @RequestMapping("searchList")
    @ResponseBody
    public List<ValSubjectWaterQuery001Dto> searchList() throws Exception {
        //账套代码
        String booksetId = this.getParam("booksetId");
        //记账日期
        String balanceDateFmt = this.getParam("balanceDateFmt");
        //科目号
        String subjectNo = this.getParam("subjectNo");
        //币种
        String oCcy = this.getParam("oCcy");
        //产品代码
        String finprodId = this.getParam("finprodId");
        ValSubjectWaterQuery001SearchDto dto = new ValSubjectWaterQuery001SearchDto();
        dto.setBooksetId(booksetId);
        dto.setBookDateEnd(DateUtil.parseDate(balanceDateFmt, "yyyy-MM-dd"));
        dto.setSubjectNo(subjectNo);
        dto.setCcy(oCcy);
        dto.setBusiId(finprodId);
        return valSubjectBalanceQuery002Service.getSubjectWater(dto);
    }

}
