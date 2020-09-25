package com.joyintech.service.valuation.tradecostmanagement.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.service.valuation.tradecostmanagement.dto.TradeCostManagement001Dto;
import com.joyintech.service.valuation.tradecostmanagement.service.TradeCostManagement001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 交易费用管理 查询
 *
 * @Author wangXiaoYong
 * @create 2019/11/19
 */
@RestController("tradeCostManagement001Ctl_dtx")
@RequestMapping("/TradeCostManagement001")
public class TradeCostManagement001Controller extends BaseController {

    @Autowired
    private TradeCostManagement001Service tradeCostManagement001Service;



    /**
     * 画面 分页查询
     *
     * @return 分页结果集
     */
//    @RequestMapping("findPage")
//    @ResponseBody
//    public Page<TradeCostManagement001Dto> findPage()
//            throws Exception {
//        Map<String, Object> param = RequestUtil.getParameters(getRequest());
//        int page = Integer.parseInt((String) param.get("page"));
//        int length = Integer.parseInt((String) param.get("length"));
//        String feeTypes = (String) param.get("feeTypes");
//        feeTypes = feeTypes.replaceAll(" ", "");
//        if (StringUtil.isNotEmpty(feeTypes)) {
//            String[] split = feeTypes.split(",");
//            param.put("feeTypes", split);
//        } else {
//            param.put("feeTypes", null);
//        }
//
//        Page<TradeCostManagement001Dto> pageDto = tradeCostManagement001Service.queryPageList(page, length, param);
//        return pageDto;
//
//    }

    @PostMapping("/findPage/{page}/{length}")
    public Page<TradeCostManagement001Dto> findPage(@RequestBody Map<String, Object> param,
                                                   @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception {
        return tradeCostManagement001Service.queryPageList(page, length, param);
    }


    /**
     * 批量删除
     *
     * @param rows 选中数据行
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("deleteList")
    public Integer delete(@RequestBody List<TradeCostManagement001Dto> rows) {
        tradeCostManagement001Service.delete(rows);
        return 1;
    }

    /**
     * 删除
     *
     * @param dto 选中数据行
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("delete")
    public Integer delete(@RequestBody TradeCostManagement001Dto dto) {
        tradeCostManagement001Service.delete(dto);
        return 1;
    }


}
