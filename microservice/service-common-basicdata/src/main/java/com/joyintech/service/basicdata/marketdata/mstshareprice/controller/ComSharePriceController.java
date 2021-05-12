/*
 * 文件名：MstSharePriceController.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：wangpeng 修改时间：2019年6月28日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.marketdata.mstshareprice.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.service.basicdata.marketdata.mstshareprice.dto.ComSharePriceDto;
import com.joyintech.service.basicdata.marketdata.mstshareprice.service.ComSharePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;

/**
 * 股票行情信息 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangpeng
 * @version 1.0 2019年6月20日
 * @see ComSharePriceController
 * @since 1.0
 */
@RestController("serviceComSharePriceController")
@RequestMapping("/ComSharePrice")
public class ComSharePriceController {

    /**
     * 股票行情信息service
     */
    @Autowired
    private ComSharePriceService comSharePriceService;

    /**
     * 查询 支持分页 <功能详细描述>
     * 
     * @param pageParam Map<String, Object> sharesId dealDateString
     * @return Page<MstYieldCurveInfoDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("selectForPage/{page}/{length}")
    public Page<ComSharePriceDto> selectForPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") int page,
                                                @PathVariable("length") int length)
        throws Exception {
        Page<ComSharePriceDto> page1 = comSharePriceService.selectForPage(page, length, pageParam);
        return page1;
    }

    /**
     * 获取指定日期最近的股票信息
     * 
     * @param pageParam dealdateString sharesId
     * @return
     * @throws Exception
     */
    @RequestMapping("getLatelyShareById")
    public ComSharePriceDto getLatelyShareById(@RequestBody Map<String, Object> pageParam)
        throws Exception {
        ComSharePriceDto page = comSharePriceService.getLatelyShareById(pageParam);
        return page;
    }

    /**
     * 获取所有的数据 <功能详细描述>
     * 
     * @param pageParam Map<String, Object>
     * @return List<MstSharePriceDto>
     * @see
     * @since 1.0
     */
    @PostMapping("/selectAllList")
    public List<ComSharePriceDto> selectAllList(@RequestBody Map<String, Object> pageParam) {
        return comSharePriceService.selectAllList(pageParam);
    }

    /**
     * 回调函数 操作具体 new delete update <功能详细描述>
     * @param dto
     * @param type
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/doComplete/{type}")
    public void doComplete(@RequestBody ComSharePriceDto dto, @PathVariable("type") String type) {
        dto.setOperateType(type);
        comSharePriceService.doComplete(dto);
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
        Page<SelectOptionDto> temp = comSharePriceService.getFinprodIdForAdd(page, length, map);
        return temp;
    }


    /**
     *
     * 插入数据
     * @param equityList 参数
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/mergeInfoList")
    public void mergeInfoList(@RequestBody List<ComSharePriceDto> equityList){
    	comSharePriceService.mergeInfoList(equityList);
    }
}
