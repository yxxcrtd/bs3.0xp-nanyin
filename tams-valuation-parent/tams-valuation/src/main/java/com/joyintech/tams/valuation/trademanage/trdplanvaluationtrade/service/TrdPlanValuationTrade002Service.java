/*
 * 文件名：TrdPlanRateInvestTrade001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年11月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.trademanage.trdplanvaluationtrade.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngMnfTade;
import com.joyintech.tams.valuation.trademanage.bond.dto.TrdBondTrade002Dto;
import com.joyintech.tams.valuation.trademanage.bond.dto.TrdBondTradeResult;
import com.joyintech.tams.valuation.trademanage.trdplanvaluationtrade.dto.TrdPlanTradeResult;
import com.joyintech.tams.valuation.trademanage.trdplanvaluationtrade.dto.TrdPlanValuationTradeDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 计划类资产投资交易新建
 *
 * @author fanshuanghu
 * @version 1.0 2018年11月22日
 * @see TrdPlanValuationTrade002Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TrdPlanValuationTrade002Service extends CommonExportService<TrdPlanValuationTradeDto>
        implements BaseProcessService<TrdPlanValuationTradeDto> {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired(required = false)
    private IPlatformDate iPlatformDate;

    @Autowired
    private TrdPlanTradeCommonServer  trdPlanTradeCommonServer;
    @Autowired
    private TrdPlanNetTradeCommonServer trdPlanNetTradeCommonServer;



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


    /**
     * 合成初始化实体类
     *
     * @param tradeId 交易编号
     * @return 计划类资产实体类
     * @see
     * @since 1.0
     */
    public TrdPlanValuationTradeDto getInitEntity(String tradeId) {
        TrdPlanValuationTradeDto entity = new TrdPlanValuationTradeDto();
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
    public void doComplete(TrdPlanValuationTradeDto entity, OperationType operationType) {
        String action = StringUtil.EMPTY;
        TrdPlanTradeResult trdPlanTradeResult = new TrdPlanTradeResult();
        //计划类资产分为净值型和货币型  profitType:01净值型，02预期收益型,03货币型
        if(entity.getFinprodType().equals("F17") && entity.getProfitType().equals("01")){
            trdPlanTradeResult = generateNetEntity(entity);
            if (OperationType.NEW.equals(operationType)) {
                action = "insertNet";
            }
            else {
                action = "updateNet";
            }
        }else if(entity.getFinprodType().equals("F17") && entity.getProfitType().equals("03")){
            trdPlanTradeResult = generateEntity(entity);
            if (OperationType.NEW.equals(operationType)) {
                action = "insert";
            }
            else {
                action = "update";
            }
        }

        restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/TrdPlanValuationTrade/" + action, trdPlanTradeResult, Integer.class);

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
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdPlanValuationTrade/findPortfolioId",
                params, SelectOptionDto.class);
    }

    /**
     * @Description:根据交易编号查询详情
     * @auther: yuzj
     * @date: 2022/1/7
     * @param:
     * @return:
     */
    public TrdPlanValuationTradeDto queryTradeInfoByTradeId(String tradeId) {
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdPlanValuationTrade/queryTradeInfoByTradeId",
                tradeId, TrdPlanValuationTradeDto.class);
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
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdPlanValuationTrade/findFinprodId",
                params, SelectOptionDto.class);
    }

    public List<SelectOptionDto> getTrdAcctId(Map<String, Object> param) {
        return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdPlanValuationTrade/getTrdAcctId", param,
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
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdPlanValuationTrade/createPlanTradeId",
                null, String.class);

    }

    //封装实体类
    public TrdPlanTradeResult generateEntity(TrdPlanValuationTradeDto trdPlanValuationTradeDto){
        List<ValMngMnfTade> listValValMngMnfTade = new ArrayList<ValMngMnfTade>();
        ValMngMnfTade valMngMnfTade = new ValMngMnfTade();
        BeanUtil.copyPropertiesIgnoreNull(valMngMnfTade,trdPlanValuationTradeDto);
        valMngMnfTade.setTradeDate(trdPlanValuationTradeDto.getTradeDate());
        //比较界面和接口字段差异，并手动给接口字段赋值
        valMngMnfTade.setAssetId("F17_"+trdPlanValuationTradeDto.getFinprodId());//资产代码
        valMngMnfTade.setDataActionSign("01");
        valMngMnfTade.setCounterId("FYDLCA365001");
        valMngMnfTade.setCounterType("01");
        valMngMnfTade.setIsChlFeeAmt("Y");
        valMngMnfTade.setIsClean("N");

        listValValMngMnfTade.add(valMngMnfTade);
        return trdPlanTradeCommonServer.generateEntity(trdPlanValuationTradeDto,listValValMngMnfTade);

    }

    //封装实体类
    public TrdPlanTradeResult generateNetEntity(TrdPlanValuationTradeDto trdPlanValuationTradeDto){
        List<ValMngMnfTade> listValValMngMnfTade = new ArrayList<ValMngMnfTade>();
        ValMngMnfTade valMngMnfTade = new ValMngMnfTade();
        BeanUtil.copyPropertiesIgnoreNull(valMngMnfTade,trdPlanValuationTradeDto);
        valMngMnfTade.setTradeDate(trdPlanValuationTradeDto.getTradeDate());
        //比较界面和接口字段差异，并手动给接口字段赋值
        valMngMnfTade.setAssetId("F17_"+trdPlanValuationTradeDto.getFinprodId());//资产代码
        valMngMnfTade.setDataActionSign("01");
        valMngMnfTade.setCounterId("FYDLCA365001");
        valMngMnfTade.setCounterType("01");
        valMngMnfTade.setIsChlFeeAmt("Y");
        valMngMnfTade.setIsClean("N");

        listValValMngMnfTade.add(valMngMnfTade);
        return trdPlanNetTradeCommonServer.generateNetEntity(trdPlanValuationTradeDto,listValValMngMnfTade);
    }


}
