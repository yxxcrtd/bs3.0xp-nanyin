/*
 * 文件名：RiskSpeedImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.riskspeed.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.FinProductAddMapper;
import com.joyintech.tams.common.base.dao.FinProductTypeMapper;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.riskspeed.RiskSpeedService;
import com.joyintech.tams.invest.common.riskspeed.dto.IRiskSpeed;
import com.joyintech.tams.invest.common.riskspeed.dto.RiskSpeedDto;
import com.joyintech.tams.invest.common.riskspeed.service.RiskSpeedCallable;
import com.joyintech.tams.invest.common.risktrial.dao.RiskTrialCommMapper;
import com.joyintech.tams.invest.common.risktrial.dto.InvestTradeTrialInput;
import com.joyintech.tams.invest.common.risktrial.dto.SupervisionTargetDto;
import com.joyintech.tams.invest.common.risktrial.util.RiskUtil;
import com.joyintech.tams.invest.common.risktrial.util.TradeCommUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 组合创建类
 * 〈功能详细描述〉
 * @author 裴家俊
 * @version 1.0 2019年10月11日
 * @see RiskSpeedImplService
 * @since 1.0
 */
@Service
public class RiskSpeedImplService {

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RiskTrialCommMapper riskTrialCommMapper;

    @Autowired
    private FinProductTypeMapper finProductTypeMapper;

    @Autowired
    private FinProductAddMapper finProductAddMapper;

    @Autowired
    private RiskSpeedCallable riskSpeedCallable;


    public List<RiskSpeedDto> getCanCastAmt(RiskSpeedDto riskSpeedDto) {
        List<RiskSpeedDto> retList=new ArrayList<>();

        try {


            //引用现有限额风险试算实体类
            InvestTradeTrialInput inputDto = queryAstTypeAndProperty(riskSpeedDto);
            filterAstTypeCond(inputDto);
            retList = dealRiskSpeedFun(riskSpeedDto, inputDto, "ALL");
        }
        catch (Exception e)
        {

        }
           return retList;
    }


    public List<RiskSpeedDto> getHasCastAmt(RiskSpeedDto riskSpeedDto) {
        List<RiskSpeedDto> retList=new ArrayList<>();

        InvestTradeTrialInput inputDto=queryAstTypeAndProperty(riskSpeedDto);
        filterAstTypeCond(inputDto);
        retList=dealRiskSpeedFun(riskSpeedDto,inputDto,"HAS");
        return retList;
    }

    /**
     * 检查输入条件
     * @param iRisk
     * @return
     */
    private boolean checkRiskBasicCond(IRiskSpeed iRisk){
        if(!StringUtil.isNotEmpty(iRisk.getValDate())||!StringUtil.isNotEmpty(iRisk.getAssetType())
                ||!StringUtil.isNotEmpty(iRisk.getPortfolioId())){
            return false;
        }
        if (!TradeCommUtil.拆借_回购.contains(iRisk.getAssetType()) && !StringUtil.isNotEmpty(iRisk.getAssetCode())) {
            return false;
        }
        return true;
    }

    /**
     * 处理指标计算
     * @param riskSpeedDto
     * @param inputDto
     * @return
     */
    private List<RiskSpeedDto> dealRiskSpeedFun(RiskSpeedDto riskSpeedDto,InvestTradeTrialInput inputDto,String speedType){
        List<RiskSpeedDto> riskTrialRetList=new ArrayList<>();
        // 获取指标任务集合
        List<SupervisionTargetDto> taskList = riskTrialCommMapper.getPortfIndTask(inputDto);
        taskList=taskList.stream().filter(item->{
            JsonNode jsonNode=JsonUtils.toJsonNode(item.getFoulCond());
            JsonNode opJson=jsonNode.findValue("op");
            //取大于或大于等于的限制性指标
            return opJson.asText().contains(">")&&item.getIndType().indexOf(RiskUtil.LIMIT)!=-1;
        }).collect(Collectors.toList());

       /* final ExecutorService executor = Executors.newCachedThreadPool();
        final List<Callable<List<IRiskSpeed>>> taskCallableList = new ArrayList<>();
        final Map<String,List<SupervisionTargetDto>> mapTaskMap=taskList.stream().collect(Collectors.groupingBy(SupervisionTargetDto::getIndId));
        mapTaskMap.keySet().forEach(key ->
                taskCallableList.add(()->{
                        List<IRiskSpeed> listTotalHas=riskSpeedCallable.getRiskSpeedValue(speedType,iRisk,mapTaskMap.get(key), inputDto);
                        riskTrialRetList.addAll(listTotalHas);
                        return riskTrialRetList;
                    }
                )
        );
        try {
            executor.invokeAll(taskCallableList);
            executor.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


        try {
            riskTrialRetList = riskSpeedCallable.getRiskSpeedValue(speedType,riskSpeedDto,taskList, inputDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return riskTrialRetList;
    }

    /**
     * 设置资产类型与投资性质
     * @param iRisk
     * @return
     */
    private InvestTradeTrialInput queryAstTypeAndProperty(RiskSpeedDto iRisk){
        InvestTradeTrialInput inputDto=new InvestTradeTrialInput();
        inputDto.setAssetCode(iRisk.getAssetCode());
        inputDto.setValDate(iRisk.getValDate());
        inputDto.setPortfolioCode(iRisk.getPortfolioId());
        inputDto.setAssetType(iRisk.getAssetType());
        FinProductType finType = new FinProductType();
        finType.setFinprodId(inputDto.getAssetCode());
        finType.setBranch(Long.valueOf(CacheUtil.getSysParam("noBranchNoValue")));
        FinProductType fin = finProductTypeMapper.selectByPrimaryKey(finType);
        if (null != fin) {
            inputDto.setAssetThrType(fin.getType7());
            if (!StringUtil.isNotEmpty(inputDto.getAssetSecType()) && StringUtil.isNotEmpty(fin.getType6())) {
                inputDto.setAssetSecType(fin.getType6());
            }
        }

        if (StringUtil.isNotBlank(inputDto.getAssetCode())) {
            FinProductAdd paInvest=finProductAddMapper.selectByPrimaryKey(inputDto.getAssetCode());
            if(null!=paInvest){
                inputDto.setInvestProporty(paInvest.getInvestNature());
            }
        }
        return inputDto;
    }
    /**
     * 设置指标计算条件，含三级类型
     * @param inputDto
     */
    private void filterAstTypeCond(InvestTradeTrialInput inputDto){
        inputDto.setIsEnabled(DictCodeListExt1.使用状态_启用.getValue());
        inputDto.setTradeDirection(DictCodeList.交易方向_买入.getValue());
        inputDto.setTimePoint(DictCodeListExt1.监督时分_交易监控.getValue());
        dealNeedAstType(inputDto,(item)->{
            if (StringUtil.equals(DictCodeList.WIND一级分类_含债券一级分类等_金融债.getValue(), item.getAssetSecType())
                    || StringUtil.equals(DictCodeList.基金类资产类型_非货币基金.getValue(), item.getAssetSecType())
                    || StringUtil.equals(DictCodeList.项目类一级分类_非标债权.getValue(), item.getAssetSecType())) {
                item.setNeedAssetThrType(true);
            }
            else {
                item.setNeedAssetThrType(false);
            }
        });

    }

    /**
     *设置是否需要三级类型
     * @param dto
     * @param val
     * @return
     */
    private void dealNeedAstType(InvestTradeTrialInput dto, Consumer<InvestTradeTrialInput> val){
         val.accept(dto);
    }
}
