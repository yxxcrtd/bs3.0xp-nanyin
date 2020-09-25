package com.joyintech.service.period.trdprojbuyoff.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.period.trdprojbuyoff.service.TrdProjectBuyOffInterestService;
import com.joyintech.tams.invest.common.dto.period.trdprojbuyoff.TrdProjectBuyOffInterestDto;

/**
 * @program: component-base
 * @description: 买断利率管理微服务
 * @author: 强成西
 * @create: 2018-10-23 14:52
 **/
@RestController("TrdProjectBuyOffInterestCtr_dtx")
@RequestMapping("/TrdProjectBuyOffInterest")
public class TrdProjectBuyOffInterestController {

    @Autowired
    private TrdProjectBuyOffInterestService trdProjectBuyOffInterestService;
    /**
     * 根据条件查询买断利率
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<TrdProjectBuyOffInterestDto> findPage(@RequestBody Map<String, Object> param,
                                                      @PathVariable("page") int page,
                                                      @PathVariable("length") int length) {
        return trdProjectBuyOffInterestService.findPage(page, length, param);
    }

    /**
     * 查询所有项目类资产
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("getProjAssetId/{page}/{length}")
    public Page<SelectOptionDto> getProjAssetId(@RequestBody Map<String, Object> param,
                                                @PathVariable("page") int page,
                                                @PathVariable("length") int length) {
        return trdProjectBuyOffInterestService.getProjAssetId(page, length, param);
    }

    /**
     * 保存买断利率
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("save")
    public String save(@RequestBody TrdProjectBuyOffInterestDto dto) {
        return trdProjectBuyOffInterestService.save(dto);
    }

    /**
     * 查询所有项目类资产
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("selectCountForMatch")
    public int selectCountForMatch(@RequestBody TrdProjectBuyOffInterestDto dto) {
        return trdProjectBuyOffInterestService.selectCountForMatch(dto);
    }

    /**
     * 判断资产代码是否存在
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("checkIdExists")
    public String checkIdExists(@RequestBody List<TrdProjectBuyOffInterestDto> equityList) {
        return trdProjectBuyOffInterestService.checkIdExists(equityList);
    }

    /**
     * 保存数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("saveList")
    public String saveList(@RequestBody List<TrdProjectBuyOffInterestDto> equityList) {
        return trdProjectBuyOffInterestService.saveList(equityList);
    }

    /**
     * 保存数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("deleteData")
    public String deleteData(@RequestBody List<TrdProjectBuyOffInterestDto> equityList) {
        return trdProjectBuyOffInterestService.deleteData(equityList);
    }

    /**
     * 查询买断利率
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findBuyOffInfo")
    public TrdProjectBuyOffInterestDto findBuyOffInfo(@RequestBody Map<String, String> params) {
        return trdProjectBuyOffInterestService.findBuyOffInfo(params);
    }
}
