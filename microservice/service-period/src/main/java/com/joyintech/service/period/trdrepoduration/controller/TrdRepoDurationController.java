package com.joyintech.service.period.trdrepoduration.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.period.trdrepoduration.service.TrdRepoDurationService;
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
@RestController("TrdRepoDurationCtr_dtx")
@RequestMapping("/TrdRepoDuration")
public class TrdRepoDurationController {

    @Autowired
    private TrdRepoDurationService trdRepoDurationService;

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
        return trdRepoDurationService.findPage(page, length, param);
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
        return trdRepoDurationService.getBusiCode(page, length, param);
    }

    /**
     * 查询有效、撤销或待处理的存续期交易数据(不分页)
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("selectAllData")
    public List<TrdRepoDurationDto> selectAllData(@RequestBody Map<String, Object> param) {
        return trdRepoDurationService.selectAllData(param);
    }

    /**
     * 查询存续期交易数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findEffectiveData")
    public TrdRepoDurationDto findEffectiveData(@RequestBody Map<String, Object> params) {
        return trdRepoDurationService.findEffectiveData(params);
    }

    /**
     * 查询质押券信息
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findRepoInfo")
    public List<RepoInfo> findRepoInfo(@RequestBody Map<String, Object> params) {
        return trdRepoDurationService.findRepoInfo(params);
    }

    /**
     * 查询现金流信息信息
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findFinCashInfo")
    public List<FinCashPlan> findFinCashInfo(@RequestBody String finprodId) {
        return trdRepoDurationService.findFinCashInfo(finprodId);
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
        return trdRepoDurationService.doComplete(trdRepoDurationDto);
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
        return trdRepoDurationService.doDelete(trdCommonDeleteDto);
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

    /**
     * 新建新生成的标的信息
     *
     * @param  trdRepoDurationDto
     * @return true
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doInsert")
    public String doInsert(@RequestBody TrdRepoDurationDto trdRepoDurationDto) {
        //新建插入标的交易表
        return trdRepoDurationService.doInsert(trdRepoDurationDto);
    }

    /**
     * 查询首期交易的交易场所和到期日
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findTrdMarketByFinprodId")
    public TrdRepoDurationDto findTrdMarketByFinprodId(@RequestBody Map<String, Object> params) {
        return trdRepoDurationService.findTrdMarketByFinprodId(params);
    }

    /**
     * 查询到期续做交易页面数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findTrdDealInfoByBusiCode")
    public TrdRepoDurationDto findTrdDealInfoByBusiCode(@RequestBody String busiCode) {
        return trdRepoDurationService.findTrdDealInfoByBusiCode(busiCode);
    }

    /**
     * 查询到期续做交易页面数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("queryTrdProductDealByFinprodId")
    public TrdProductDeal queryTrdProductDealByFinprodId(@RequestBody String finprodId) {
        return trdRepoDurationService.queryTrdProductDealByFinprodId(finprodId);
    }
    
    /**
     * 撤销时根据汇总表的主键查询授信交易编号
     */
    @PostMapping("findCeTradeIdByTotleTradeId/{totleTradeId}")
    public String findCeTradeIdByTotleTradeId(@PathVariable("totleTradeId") String totleTradeId) {
        return trdRepoDurationService.findCeTradeIdByTotleTradeId(totleTradeId);
    }
}
