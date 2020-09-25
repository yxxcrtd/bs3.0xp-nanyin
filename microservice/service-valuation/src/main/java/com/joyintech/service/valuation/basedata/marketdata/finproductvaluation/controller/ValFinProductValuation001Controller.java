/*
 * 文件名：ValFinProductValuation001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年7月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.basedata.marketdata.finproductvaluation.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.basedata.marketdata.finproductvaluation.dto.ResultDto;
import com.joyintech.service.valuation.basedata.marketdata.finproductvaluation.service.ValFinProductValuation001Service;
import com.joyintech.tams.constant.DictCode;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2018年7月20日
 * @see ValFinProductValuation001Controller
 * @since 1.0
 */
@RestController(value="serviceValFinProductValuation001Controller")
@RequestMapping("/ValFinProductValuation001")
public class ValFinProductValuation001Controller extends BaseController {

    /**
     * ValFinProductValuation001Service
     */
    @Autowired
    private ValFinProductValuation001Service valFinProductValuation001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.基金类型);
        this.setSelectOptionToModel(model, DictCode.估值来源);
        //model.addAttribute("defaultFundType", DictCodeList.基金类型_货币基金.getValue());
        return super.init(model);
    }

    /**
     * 表格加载
     * 
     * @return List<ResultDto>
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @PostMapping("/tableLoadPage/{page}/{length}")
    public Page<ResultDto> tableLoadPage(@RequestBody Map<String, Object> params,
    		@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
//        String finprodId = this.getParam("finprodId");
//        String fundtype = this.getParam("fundtype");
//        String fundAbbr = this.getParam("fundAbbr");
//        String valuationStartDate = this.getParam("valuationStartDate");
//        String valuationEndDate = this.getParam("valuationEndDate");
//        Map<String, Object> params = valFinProductValuation001Service.getParams(finprodId, fundtype,fundAbbr,
//            valuationStartDate, valuationEndDate);
//        ValFinProductValuation001SearchDto dto = (ValFinProductValuation001SearchDto)JsonUtils.map2entity(
//                params, ValFinProductValuation001SearchDto.class);
        
        return valFinProductValuation001Service.tableLoadPage(page,length,params);
    }

    /**
     * 导出数据
     * 
     * @return ResponseEntity<byte[]>
     * @throws Exception ExportUtil.export
     * @see
     * @since 1.0
     */
    @PostMapping("/tableLoad")
    public List<ResultDto> tableLoad(@RequestBody Map<String, Object> params) throws Exception {
            return valFinProductValuation001Service.tableLoad(params);
        
    }
}
