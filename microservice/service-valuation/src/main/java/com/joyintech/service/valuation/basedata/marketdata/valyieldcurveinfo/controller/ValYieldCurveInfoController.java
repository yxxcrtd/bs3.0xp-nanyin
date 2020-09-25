/*
 * 文件名：YieldCurveInfo001Controller.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：wangzhao 修改时间：2018年7月17日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.basedata.marketdata.valyieldcurveinfo.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.basedata.marketdata.valyieldcurveinfo.dto.ValYieldCurveInfoDto;
import com.joyintech.service.valuation.basedata.marketdata.valyieldcurveinfo.service.ValYieldCurveInfoService;

/**
 * 收益率曲线信息 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzhao
 * @version 1.0 2018年8月9日
 * @see ValYieldCurveInfoController
 * @since 1.0
 */
@RestController("ValYieldCurveInfoCtr_dtx")
@RequestMapping("/ValYieldCurveInfo")
public class ValYieldCurveInfoController {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    @Autowired
    private ValYieldCurveInfoService valYieldCurveInfoService;

    /**
     * 查询 <功能详细描述>
     * 
     * @param pageParam Map<String, Object>
     * @return Page<ValYieldCurveInfoDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("/selectForPage/{page}/{length}")
    public Page<ValYieldCurveInfoDto> selectForPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") int page,
                                                    @PathVariable("length") int length)
        throws Exception {
        return valYieldCurveInfoService.selectForPage(page, length, pageParam);
    }

    /**
     * 获取所有的数据 <功能详细描述>
     * 
     * @param pageParam Map<String, Object>
     * @return List<ValYieldCurveInfoDto>
     * @see
     * @since 1.0
     */
    @PostMapping("/selectAllList")
    public List<ValYieldCurveInfoDto> selectAllList(@RequestBody Map<String, Object> pageParam) {
        return valYieldCurveInfoService.selectAllList(pageParam);
    }

    /**
     * 获取所有的数据 <功能详细描述>
     * 
     * @param pageParam Map<String, Object>
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/doComplete")
    public void doComplete(@RequestBody ValYieldCurveInfoDto dto) {
        valYieldCurveInfoService.doComplete(dto);
    }
    //add by lishuping 20191008 start
    /**
     * 曲线名称的联想下拉框事件
     * @param
     * @return
     * @throws Exception
     */
    @PostMapping("/getCurveName/{page}/{length}")
    public Page<SelectOptionDto> getCurveName(@RequestBody Map<String, Object> map,
                                              @PathVariable("page") int page, @PathVariable("length") int length) {
        return valYieldCurveInfoService.getCurveName(page,length,map);
    }

    /**
     * 根据曲线名称查询n、k
     * @param param
     * @return
     */
    @PostMapping("selectNK")
    public ValYieldCurveInfoDto selectNK(@RequestBody ValYieldCurveInfoDto param) {
        return valYieldCurveInfoService.selectNK(param);
    }
    //add by lishuping 20191008 end
}
