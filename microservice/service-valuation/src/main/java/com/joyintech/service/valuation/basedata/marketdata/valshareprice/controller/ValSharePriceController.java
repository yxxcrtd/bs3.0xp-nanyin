/*
 * 文件名：ValSharePriceController.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：wangpeng 修改时间：2019年6月28日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.basedata.marketdata.valshareprice.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.basedata.marketdata.valshareprice.dto.ValSharePriceDto;
import com.joyintech.service.valuation.basedata.marketdata.valshareprice.service.ValSharePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 股票行情信息 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangpeng
 * @version 1.0 2019年6月20日
 * @see ValSharePriceController
 * @since 1.0
 */
@RestController("ValSharePriceCtr_dtx")
@RequestMapping("/ValSharePrice")
public class ValSharePriceController {

    /**
     * 股票行情信息service
     */
    @Autowired
    private ValSharePriceService mstSharePriceService;

    /**
     * 查询 支持分页 <功能详细描述>
     * 
     * @param pageParam Map<String, Object> sharesId dealDateString
     * @return Page<ValYieldCurveInfoDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("/selectForPage/{page}/{length}")
    public Page<ValSharePriceDto> selectForPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") int page,
                                                @PathVariable("length") int length)
        throws Exception {
        Page<ValSharePriceDto> page1 = mstSharePriceService.selectForPage(page, length, pageParam);
        return page1;
    }

    /**
     * 获取指定日期最近的股票信息
     * 
     * @param pageParam dealdateString sharesId
     * @return
     * @throws Exception
     */
    @RequestMapping("/getLatelyShareById")
    public ValSharePriceDto getLatelyShareById(@RequestBody Map<String, Object> pageParam)
        throws Exception {
        ValSharePriceDto page = mstSharePriceService.getLatelyShareById(pageParam);
        return page;
    }

    /**
     * 获取所有的数据 <功能详细描述>
     * 
     * @param pageParam Map<String, Object>
     * @return List<ValSharePriceDto>
     * @see
     * @since 1.0
     */
    @PostMapping("/selectAllList")
    public List<ValSharePriceDto> selectAllList(@RequestBody Map<String, Object> pageParam) {
        return mstSharePriceService.selectAllList(pageParam);
    }

    /**
     * 回调函数 操作具体 new delete update <功能详细描述>
     * 
     * @param pageParam Map<String, Object> dto type
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/doComplete/{type}")
    public void doComplete(@RequestBody ValSharePriceDto dto, @PathVariable("type") String type) {
        dto.setOperateType(type);
        mstSharePriceService.doComplete(dto);
    }

    /**
     * 通过资产代码或者资产名称等信息
     * 
     * @param map
     * @param page
     * @param length
     * @return
     * @throws Exception
     */
    @PostMapping("getFinprodIdForAdd/{page}/{length}")
    @ResponseBody
    public Page<SelectOptionDto> getFinprodIdForAdd(@RequestBody Map<String, String> map, @PathVariable("page") int page,
                                                    @PathVariable("length") int length)
        throws Exception {
        Page<SelectOptionDto> temp = mstSharePriceService.getFinprodIdForAdd(page, length, map);
        return temp;
    }
}
