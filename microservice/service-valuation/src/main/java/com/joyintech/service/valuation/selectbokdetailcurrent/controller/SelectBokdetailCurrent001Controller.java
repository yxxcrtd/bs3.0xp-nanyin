package com.joyintech.service.valuation.selectbokdetailcurrent.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.selectbokdetailcurrent.dto.SelectBokdetailCurrent001Dto;
import com.joyintech.service.valuation.selectbokdetailcurrent.service.SelectBokdetailCurrent001Service;

/**
 * 账务明细当前表查询
 *
 * @author tanxuan
 * @createTime 2019-10-16
 */
@RestController(value="serviceSelectBokdetailCurrent001Controller")
@RequestMapping("/SelectBokdetailCurrent001")
public class SelectBokdetailCurrent001Controller extends BaseController {

    @Autowired
    private SelectBokdetailCurrent001Service selectBokdetailCurrent001Service;

    /**
     * 列表查询 - 分页
     * @param page
     * @param length
     * @param params
     * @return
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<SelectBokdetailCurrent001Dto> queryPageList(@RequestBody Map<String, Object> params,
                                                        @PathVariable("page") int page, @PathVariable("length") int length){
        return selectBokdetailCurrent001Service.queryPageList(page,length,params);
    }

    /**
     * 列表查询
     * @param page
     * @param length
     * @param params
     * @return
     */
    @PostMapping("queryAllList")
    public List<SelectBokdetailCurrent001Dto> queryAllList(@RequestBody Map<String, Object> param){
        return selectBokdetailCurrent001Service.queryAllList(param);
    }
}
