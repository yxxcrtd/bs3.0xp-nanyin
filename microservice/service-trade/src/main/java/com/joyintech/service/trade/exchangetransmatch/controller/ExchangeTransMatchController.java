package com.joyintech.service.trade.exchangetransmatch.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.exchangetransmatch.dto.*;
import com.joyintech.service.trade.exchangetransmatch.service.ExchangeTransMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ExchangeTransMatchController")
public class ExchangeTransMatchController {

    @Autowired
    private ExchangeTransMatchService exchangeTransMatchService;

    /**
     * 分页查询成交数据
     * @param map 查询
     * @return 分页查询结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @PostMapping("findPage/{page}/{length}")
    public Page<ExchangeTransMatch002Dto> findPage(@RequestBody Map<String,Object> map, @PathVariable("page") int page, @PathVariable("length") int length) {
        return exchangeTransMatchService.findPage(map,page, length);
    }

    @ResponseBody
    @PostMapping("findTransMatchInfoPage/{page}/{length}")
    public Page<ExchangeTransMatch004Dto> findTransMatchInfoPage(@RequestBody Map<String,Object> map, @PathVariable("page") int page, @PathVariable("length") int length) {
        return exchangeTransMatchService.findTransMatchInfoPage(map,page, length);
    }

    /**
     * 分页查询委托交易数据
     * @param map
     * @param page
     * @param length
     * @return
     */
    @ResponseBody
    @PostMapping("findEntrustPage/{page}/{length}")
    public Page<ExchangeTransMatch003Dto> findEntrustPage(@RequestBody Map<String,Object> map, @PathVariable("page") int page, @PathVariable("length") int length) {
        return exchangeTransMatchService.findEntrustPage(map,page, length);
    }

    /**
     * 获取资产代码下拉框
     * @param map
     * @param page
     * @param length
     * @return
     */
    @ResponseBody
    @PostMapping("getFinprodId/{page}/{length}")
    public Page<SelectOptionDto> getFinprodId(@RequestBody Map<String,Object> map, @PathVariable("page") int page, @PathVariable("length") int length) {
        return exchangeTransMatchService.getFinprodId(map,page, length);
    }

    /**
     * 获取交易编号下拉框
     * @param map
     * @param page
     * @param length
     * @return
     */
    @ResponseBody
    @PostMapping("getBusiCode/{page}/{length}")
    public Page<SelectOptionDto> getBusiCode(@RequestBody Map<String,Object> map, @PathVariable("page") int page, @PathVariable("length") int length) {
        return exchangeTransMatchService.getBusiCode(map,page, length);
    }


    /**
     * 获取申请编号下拉框
     * @param map
     * @param page
     * @param length
     * @return
     */
    @ResponseBody
    @PostMapping("getApplyNo/{page}/{length}")
    public Page<SelectOptionDto> getApplyNo(@RequestBody Map<String,Object> map, @PathVariable("page") int page, @PathVariable("length") int length) {
        return exchangeTransMatchService.getApplyNo(map,page, length);
    }

    /**
     * 获取证券账户下拉框
     * @param map
     * @param page
     * @param length
     * @return
     */
    @ResponseBody
    @PostMapping("getSecAcctNo/{page}/{length}")
    public Page<SelectOptionDto> getSecAcctNo(@RequestBody Map<String,Object> map, @PathVariable("page") int page, @PathVariable("length") int length) {
        return exchangeTransMatchService.getSecAcctNo(map,page, length);
    }


    /**
     * 获取成交编号下拉框
     * @param map
     * @param page
     * @param length
     * @return
     */
    @ResponseBody
    @PostMapping("getDealId/{page}/{length}")
    public Page<SelectOptionDto> getDealId(@RequestBody Map<String,Object> map, @PathVariable("page") int page, @PathVariable("length") int length) {
        return exchangeTransMatchService.getDealId(map,page, length);
    }


    /**
     * 获取委托信息
     * @param seqNo
     * @return
     */
    @ResponseBody
    @PostMapping("findEntrustList")
    public List<ExchangeTransMatch003Dto> findEntrustList(@RequestBody String seqNo) {
        return exchangeTransMatchService.findEntrustList(seqNo);
    }

    /**
     * 获取成交信息
     * @param seqNo
     * @return
     */
    @ResponseBody
    @PostMapping("findDealList")
    public List<ExchangeTransMatch002Dto> findDealList(@RequestBody String seqNo) {
        return exchangeTransMatchService.findDealList(seqNo);
    }

}
