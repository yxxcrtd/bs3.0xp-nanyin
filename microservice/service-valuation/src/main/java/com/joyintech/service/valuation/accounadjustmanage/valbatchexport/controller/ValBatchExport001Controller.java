/*
 * 文件名：AssetLiabilityStatementQuery001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.accounadjustmanage.valbatchexport.controller;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.accounadjustmanage.valbatchexport.dto.ValBatchExport001Dto;
import com.joyintech.service.valuation.accounadjustmanage.valbatchexport.dto.ValBatchExport001SearchDto;
import com.joyintech.service.valuation.accounadjustmanage.valbatchexport.service.ValBatchExport001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.Map;


/**
 * @author huanghao
 * 三表批量导出
 */
@RestController(value = "serviceValBatchExport001Controller")
@RequestMapping("ValBatchExport001")
public class ValBatchExport001Controller extends BaseController {

    @Autowired
    private ValBatchExport001Service valBatchExport001Service;

    /**
     * @param param
     * @return
     */
    @PostMapping("/getLinkSelectOption")
    public List<SelectOptionDto> getLinkSelectOption(@RequestBody Map<String, Object> param) {
        return valBatchExport001Service.getLinkSelectOption(param);
    }

    /**
     * 查询科目余额表
     * @param dto
     * @return
     */
    @PostMapping("/selectBokBalance")
    public List<ValBatchExport001Dto> selectBokBalance(@RequestBody ValBatchExport001SearchDto dto) {
        return valBatchExport001Service.selectBokBalance(dto);
    }

    /**
     * 查询科目余额表
     * @param dto
     * @return
     */
    @PostMapping("/selectBokBalanceAll")
    public List<ValBatchExport001Dto> selectBokBalanceAll(@RequestBody ValBatchExport001SearchDto dto) {
        return valBatchExport001Service.selectBokBalanceAll(dto);
    }

}
