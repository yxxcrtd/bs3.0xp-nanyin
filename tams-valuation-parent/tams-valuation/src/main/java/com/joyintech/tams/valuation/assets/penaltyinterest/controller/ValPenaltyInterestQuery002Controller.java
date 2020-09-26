/*
 * 文件名：ValPenaltyInterestQuery002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2018年10月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.assets.penaltyinterest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.valuation.assets.penaltyinterest.dto.ValPenaltyInterestQuery001Dto;
import com.joyintech.tams.valuation.assets.penaltyinterest.service.ValPenaltyInterestQuery002Service;
import com.joyintech.tams.constant.DictCode;

/**
 * 查看逾期罚息类资产详细页面
 * 
 * @author zhangquan
 * @version 1.0 2019年12月25日
 * @see ValPenaltyInterestQuery002Controller
 * @since 1.0
 */
@Controller
@PageId("ValPenaltyInterestQuery002")
@RequestMapping("/valuation/assets/penaltyinterest/ValPenaltyInterestQuery002")
public class ValPenaltyInterestQuery002Controller extends BaseController {

    /**
     * valPenaltyInterestQuery002Service
     */
    @Autowired
    private ValPenaltyInterestQuery002Service valPenaltyInterestQuery002Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String finprodId = this.getParam("finprodId");
        String finprodType = this.getParam("finprodType");
        String branchNo = this.getParam("branchNo");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("finprodType", finprodType);
        map.put("branchNo", branchNo);

        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.计息基础);
        this.setSelectOptionToModel(model, DictCode.本金收益特征);
        this.setSelectOptionToModel(model, DictCode.金融产品类型);
        this.setSelectOptionToModel(model, DictCode.期限品种_回购);
        this.setSelectOptionToModel(model, DictCode.估值交易市场);
        this.setSelectOptionToModel(model, DictCode.交易所回购计提天数方式);

        ValPenaltyInterestQuery001Dto dto = valPenaltyInterestQuery002Service.queryRepassetByfinprodId(
            map);
        model.addAttribute("entity", dto);
        // 交易日历下拉框
        List<SelectOptionDto> options = valPenaltyInterestQuery002Service.getCalendarInfoList();
        model.addAttribute("CALENDAR_ID", JsonUtils.objectArray2Json(options.toArray()));
        return super.init(model);
    }

    /**
     * 
     * 详情页表单数据的获取-罚息利率报价信息
     * @return List<ValPenaltyInterestQuery001Dto>
     * @see
     * @since 1.0
     */
    @RequestMapping("collateralinfo")
    @ResponseBody
    public List<ValPenaltyInterestQuery001Dto> queryCollateralinfo() {
        String finprodId = this.getParam("finprodId");
        String finprodType = this.getParam("finprodType");
        String branchNo = this.getParam("branchNo");
        Map<String, Object> map = new HashMap<>();
        map.put("finprodId", finprodId);
        map.put("finprodType", finprodType);
        map.put("branchNo", branchNo);
        return valPenaltyInterestQuery002Service.queryCollateralinfo(map);
    }
    
   
    /**
     * 
     * 详情页表单数据的获取-罚息计划列表信息
     * @return List<ValPenaltyInterestQuery001Dto>
     * @see
     * @since 1.0
     */
    @RequestMapping("fenaltyinterestinfo")
    @ResponseBody
    public List<ValPenaltyInterestQuery001Dto> queryFenaltyInterestInfo() {
        String finprodId = this.getParam("finprodId");
        String finprodType = this.getParam("finprodType");
        String branchNo = this.getParam("branchNo");
        Map<String, Object> map = new HashMap<>();
        map.put("finprodId", finprodId);
        map.put("finprodType", finprodType);
        map.put("branchNo", branchNo);
        return valPenaltyInterestQuery002Service.queryFenaltyInterestInfo(map);
    }
    
    
    
    

}
