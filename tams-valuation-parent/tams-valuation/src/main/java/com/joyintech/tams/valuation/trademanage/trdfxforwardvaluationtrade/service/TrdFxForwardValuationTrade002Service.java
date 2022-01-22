/*
 * 文件名：TrdPlanRateInvestTrade001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年11月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.trademanage.trdfxforwardvaluationtrade.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngBuyTrade;
import com.joyintech.tams.valuation.trademanage.trdfxforwardvaluationtrade.dto.TrdFxForwardTradeResult;
import com.joyintech.tams.valuation.trademanage.trdfxforwardvaluationtrade.dto.TrdFxForwardValuationTradeDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 外汇即期交易新建
 *
 * @author yuzj
 * @version 1.0 2022-1-11
 * @see TrdFxForwardValuationTrade002Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TrdFxForwardValuationTrade002Service extends CommonExportService<TrdFxForwardValuationTradeDto>
        implements BaseProcessService<TrdFxForwardValuationTradeDto> {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired(required = false)
    private IPlatformDate iPlatformDate;



    @Autowired
    private FinProductMapper finProductMapper;
    @Autowired
    private WflStlPayOrdersMapper wflStlPayOrdersMapper;

    @Autowired
    private WflPaTrdProductDealMapper wflPaTrdProductDealMapper;
    @Autowired
    private WflTrdProductDealMapper wflTrdProductDealMapper;
    @Autowired
    private WflTrdProductDealAddMapper wflTrdProductDealAddMapper;
    @Autowired
    private RepTradeMapper repTradeMapper;
    @Autowired
    private RepAssetDeptMapper repAssetDeptMapper;

    @Autowired
    private TrdAsynHandleStatusMapper trdAsynHandleStatusMapper;

    @Autowired
    private FinProductTypeMapper finProductTypeMapper;


    @Autowired
    private PtlPortfolioMapper ptlPortfolioMapper;

    @Autowired
    private FinRateInfoMapper finRateInfoMapper;

    @Autowired
    private TrdFxForwardTradeCommonServer trdFxForwardTradeCommonServer;


    /**
     * 合成初始化实体类
     *
     * @param tradeId 交易编号
     * @return 计划类资产实体类
     * @see
     * @since 1.0
     */
    public TrdFxForwardValuationTradeDto getInitEntity(String tradeId) {
        TrdFxForwardValuationTradeDto entity = new TrdFxForwardValuationTradeDto();
        TrdProductDeal trdProductDeal = null;
        TrdProductDealAdd trdProductDealAdd = null;
        String finprodId = null;
        FinProduct finProduct = null;
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(TrdFxForwardValuationTradeDto entity, OperationType operationType) {
        String action = StringUtil.EMPTY;
        TrdFxForwardTradeResult trdFxForwardTradeResult = trdFxForwardTradeResult = generateEntity(entity);
        if (OperationType.NEW.equals(operationType)) {
            action = "insert";
        }
        else {
            action = "update";
        }
        restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/TrdFxForwardValuationTrade/" + action, trdFxForwardTradeResult, Integer.class);
    }

    /**
     * 产品代码下拉框
     * @auther: Administrator
     * @date: 2021/12/29 17:10
     * @param:
     * @return:
     */
    public Page<SelectOptionDto> findPortfolioId(Map<String, Object> params) {
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdFxForwardValuationTrade/findPortfolioId",
                params, SelectOptionDto.class);
    }

    /**
     * 资产代码下拉框
     * @auther: Administrator
     * @date: 2021/12/29 17:10
     * @param:
     * @return:
     */
    public Page<SelectOptionDto> findFinprodId(Map<String, Object> params) {
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdFxForwardValuationTrade/findFinprodId",
                params, SelectOptionDto.class);
    }

    public List<SelectOptionDto> getTrdAcctId(Map<String, Object> param) {
        return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdFxForwardValuationTrade/getTrdAcctId", param,
                SelectOptionDto.class);
    }

    /**
     * 策略代码生成
     *
     * @see
     * @since 1.0
     */
    public String createPlanTradeId() {
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdFxForwardValuationTrade/createPlanTradeId",
                null, String.class);

    }

    /**
     * @Description:根据交易编号查询详情
     * @auther: yuzj
     * @date: 2022/1/7
     * @param:
     * @return:
     */
    public TrdFxForwardValuationTradeDto queryTradeInfoByTradeId(String tradeId) {
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdFxForwardValuationTrade/queryTradeInfoByTradeId",
                tradeId, TrdFxForwardValuationTradeDto.class);
    }

    //封装实体类
    public TrdFxForwardTradeResult generateEntity(TrdFxForwardValuationTradeDto trdFxForwardValuationTradeDto){
        List<ValMngBuyTrade> listValMngBuyTrade = new ArrayList<ValMngBuyTrade>();
        ValMngBuyTrade valMngBuyTrade = new ValMngBuyTrade();
        BeanUtil.copyPropertiesIgnoreNull(valMngBuyTrade,trdFxForwardValuationTradeDto);
        valMngBuyTrade.setTradeDate(trdFxForwardValuationTradeDto.getTradeDate());
        //比较界面和接口字段差异，并手动给接口字段赋值
        valMngBuyTrade.setAssetId("F17_"+trdFxForwardValuationTradeDto.getFinprodId());//资产代码
        valMngBuyTrade.setDataActionSign("01");
        valMngBuyTrade.setCounterId("FYDLCA365001");
        valMngBuyTrade.setCounterType("01");
        valMngBuyTrade.setIsChlFeeAmt("Y");
        valMngBuyTrade.setIsClean("N");

        listValMngBuyTrade.add(valMngBuyTrade);
        return trdFxForwardTradeCommonServer.generateEntity(trdFxForwardValuationTradeDto,listValMngBuyTrade);
    }

    public TrdFxForwardTradeCommonServer getTrdFxForwardTradeCommonServer() {
        return trdFxForwardTradeCommonServer;
    }

    public void setTrdFxForwardTradeCommonServer(TrdFxForwardTradeCommonServer trdFxForwardTradeCommonServer) {
        this.trdFxForwardTradeCommonServer = trdFxForwardTradeCommonServer;
    }
}
