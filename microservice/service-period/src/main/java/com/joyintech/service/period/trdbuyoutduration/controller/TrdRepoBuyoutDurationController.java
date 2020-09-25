package com.joyintech.service.period.trdbuyoutduration.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.period.trdbuyoutduration.service.TrdRepoBuyoutDurationService;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.invest.common.dto.common.TrdCommonDeleteDto;
import com.joyintech.tams.invest.common.dto.common.TrdCommonWflParam;
import com.joyintech.tams.invest.common.dto.period.trdrepoduration.RepoInfo;
import com.joyintech.tams.invest.common.dto.period.trdrepoduration.TrdRepoDurationDto;
import com.joyintech.tams.invest.common.service.MapperDelegateService;

/**
 * 回购存续期
 *
 * @author : 强成西
 * @version V1.0
 * @Project: microservice
 * @Package com.joyintech.service.product.productduration.addreduce.controller
 * @date Date : 2018年09月19日 10:02
 */
@RestController("TrdRepoBuyoutDurationCtr_dtx")
@RequestMapping("/TrdRepoBuyoutDuration")
public class TrdRepoBuyoutDurationController {

    @Autowired
    private TrdRepoBuyoutDurationService trdRepoBuyoutDurationService;

    @Autowired
    private MapperDelegateService mapperDelegateService;

    /**
     * 查询有效、撤销或待处理的存续期交易数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<TrdRepoDurationDto> findPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                             @PathVariable("length") int length) {
        return trdRepoBuyoutDurationService.findPage(page, length, param);
    }

    /**
     * 查询有效、撤销或待处理的存续期交易数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("getBusiCode/{page}/{length}")
    public Page<SelectOptionDto> getBusiCode(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                             @PathVariable("length") int length) {
        return trdRepoBuyoutDurationService.getBusiCode(page, length, param);
    }
    /**
     * 查询有效、撤销或待处理的存续期交易数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("genPrinAmtByVal")
    public BigDecimal genPrinAmtByVal(@RequestBody Map<String, Object> param) {
        return trdRepoBuyoutDurationService.genPrinAmtByVal(param);
    }
    /**
     * 查询有效、撤销或待处理的存续期交易数据(不分页)
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("selectAllData")
    public List<TrdRepoDurationDto> selectAllData(@RequestBody Map<String, Object> param) {
        return trdRepoBuyoutDurationService.selectAllData(param);
    }

    /**
     * 查询存续期交易数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findEffectiveData")
    public TrdRepoDurationDto findEffectiveData(@RequestBody Map<String, Object> params) {
        return trdRepoBuyoutDurationService.findEffectiveData(params);
    }

    /**
     * 查询质押券信息
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findRepoInfo")
    public List<RepoInfo> findRepoInfo(@RequestBody Map<String, Object> params) {
        return trdRepoBuyoutDurationService.findRepoInfo(params);
    }

    /**
     * 查询现金流信息信息
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findFinCashInfo")
    public List<FinCashPlan> findFinCashInfo(@RequestBody String finprodId) {
        return trdRepoBuyoutDurationService.findFinCashInfo(finprodId);
    }

    /**
     * 插入数据，并删除流程表数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public String doComplete(@RequestBody TrdRepoDurationDto trdRepoDurationDto) {
        return trdRepoBuyoutDurationService.doComplete(trdRepoDurationDto);
    }

    /**
     * 作废流程， 并删除流程表数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doDelete")
    public String doDelete(@RequestBody TrdCommonDeleteDto trdCommonDeleteDto) {
        return trdRepoBuyoutDurationService.doDelete(trdCommonDeleteDto);
    }

    @Transactional
    @DTXTransaction
    @PostMapping("insertAutoClassifyBatch")
    public String insertAutoClassifyBatch(@RequestBody TrdCommonWflParam trdCommonWflParam) {
        mapperDelegateService.insertAutoClassifyBatch(trdCommonWflParam.getWflTrdProductDeals(),
                trdCommonWflParam.getWflPaTrdFinDealTotal(),
                trdCommonWflParam.getWflTrdFinProductDealTotal(),
                trdCommonWflParam.getWflStlPayOrdersDtos());
        return "Success";
    }
}
