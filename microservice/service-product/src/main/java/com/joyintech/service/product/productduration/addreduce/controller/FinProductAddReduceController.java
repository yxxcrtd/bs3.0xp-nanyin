package com.joyintech.service.product.productduration.addreduce.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.productduration.addreduce.service.FinProductAddReduceService;
import com.joyintech.tams.invest.common.dto.product.addreduce.*;

/**
 * 产品存续期-强增强减管理
 * 
 * @author : 强成西
 * @version V1.0
 * @Project: microservice
 * @Package com.joyintech.service.product.productduration.addreduce.controller
 * @Description: TODO
 * @date Date : 2018年09月19日 10:02
 */
@RestController("backFinProductAddReduceController")
@RequestMapping("/FinProductAddReduce")
public class FinProductAddReduceController {

    @Autowired
    private FinProductAddReduceService finProductAddReduceService;

    /**
     * 画面查询方法
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("selectForPage/{page}/{length}")
    public Page<FinProductAddReduceDto> selectForPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                      @PathVariable("length") int length)
        throws Exception {
        return finProductAddReduceService.selectForPage(page, length, param);
    }

    /**
     * 画面查询方法
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("getProdId/{page}/{length}")
    public Page<SelectOptionDto> getProdId(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                           @PathVariable("length") int length)
        throws Exception {
        return finProductAddReduceService.getProdId(page, length, param);
    }

    /**
     * 画面查询方法
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("getSaleDepartment")
    public String getSaleDepartment(@RequestBody Map<String, Object> param) {
        return finProductAddReduceService.getSaleDepartment(param);
    }

    /**
     * 画面查询方法
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("getValueByDate")
    public BigDecimal getValueByDate(@RequestBody Map<String, Object> param) {
        return finProductAddReduceService.getValueByDate(param);
    }

    /**
     * 画面查询方法
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("getProductAmount")
    public BigDecimal getProductAmount(@RequestBody Map<String, Object> param) {
        return finProductAddReduceService.getProductAmount(param);
    }
    /**
     * 画面查询方法
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findReduceInfoData")
    public List<ReduceInfoDto> findReduceInfoData(@RequestBody Map<String, Object> param) {
        return finProductAddReduceService.findReduceInfoData(param);
    }

    /**
     * 画面查询费用明细信息方法
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findProdFeeData")
    public List<ProductFeeInfo> findProdFeeData(@RequestBody Map<String, Object> param) {
        return finProductAddReduceService.findProdFeeData(param);
    }

    /**
     * 画面查询方法
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("selectByTotalTradeId")
    public ProductAddReduceBaseDto selectByTotalTradeId(@RequestBody Map<String, Object> params) {
        return finProductAddReduceService.selectByTotalTradeId(params);
    }

    /**
     * 画面查询方法
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("selectProductTradeInfo")
    public ProductTradeInfoDto selectProductTradeInfo(@RequestBody String totalTradeId) {
        return finProductAddReduceService.selectProductTradeInfo(totalTradeId);
    }
    
    /**
     * 画面查询方法
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @RequestMapping("getProdId01/{page}/{length}")
    public Page<SelectOptionDto> getProdId01(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page,
            @PathVariable("length") int length) {
        return finProductAddReduceService.getProdId01(page, length, param);
    }
}
