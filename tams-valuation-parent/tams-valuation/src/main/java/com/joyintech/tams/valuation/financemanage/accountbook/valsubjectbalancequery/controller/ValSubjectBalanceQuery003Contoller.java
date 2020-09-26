/*
 * 文件名：ValVoucherQuery003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.financemanage.accountbook.valsubjectbalancequery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.financemanage.accountbook.valsubjectbalancequery.service.ValSubjectBalanceQuery003Service;
import com.joyintech.tams.valuation.financemanage.accountbook.valsubjectwaterquery.dto.ValSubjectWaterQuery001Dto;
import com.joyintech.tams.valuation.financemanage.accountbook.valsubjectwaterquery.dto.ValSubjectWaterQuery001SearchDto;

/**
 * 模板示例<br>
 * 凭证详情查看
 * 
 * @author 
 * @version 2.0 2018年5月3日
 * @see ValSubjectBalanceQuery003Contoller
 * @since 2.0
 */
@Controller
@PageId("ValSubjectBalanceQuery003")
@RequestMapping("/valuation/financemanage/accountbook/valsubjectbalancequery/ValSubjectBalanceQuery003")
public class ValSubjectBalanceQuery003Contoller extends BaseController {

    /**
     * ValSubjectBalanceQuery002Service
     */
    @Autowired
    private ValSubjectBalanceQuery003Service valSubjectBalanceQuery003Service;

    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        
        //科目号
        String subjectNo = this.getParam("subjectNo1");
        model.addAttribute("subjectNo1", subjectNo);
        
        //记账日期
        String balanceDateFmt = this.getParam("balanceDateFmt");
        model.addAttribute("balanceDateFmt", balanceDateFmt);
        
        //币种
        String oCcy = this.getParam("oCcy");
        model.addAttribute("oCcy", oCcy);
        
        return super.init(model);
    }

    /**
     * 根据凭证编号subjectNo获取对应相同编号的所有数据
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
       
        //科目号
        String subjectNo1 = this.getParam("subjectNo1");
        //币种
        String oCcy = this.getParam("oCcy");
        //记账日期
        String balanceDateFmt = this.getParam("balanceDateFmt");
        ValSubjectWaterQuery001SearchDto dto = new ValSubjectWaterQuery001SearchDto();
//        dto.setBookDateBegin(DateUtil.parseDate(balanceDateFmt, "yyyy-MM-dd"));
        dto.setBookDateEnd(DateUtil.parseDate(balanceDateFmt, "yyyy-MM-dd"));
        dto.setSubjectNo1(subjectNo1);
        dto.setCcy(oCcy);
        return valSubjectBalanceQuery003Service.getSubjectWater(dto);
    }

}
