/*
 * 文件名：RiskTrialDayTimeReaderService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年9月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.risktrialrds.service;

import com.joyintech.batch.core.dto.SysBatchExecInfoDto;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.PtlPortfolioValuation;
import com.joyintech.tams.common.base.entity.RskIndividAssetDel;
import com.joyintech.tams.common.base.entity.RskIndividSpecialStage;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.batch.common.util.BatchConstant;
import com.joyintech.tams.invest.batch.risktrialrds.dto.RiskTrialDayTimeReaderResult;
import com.joyintech.tams.invest.common.risktrialrds.dao.RiskTrialCommMapper;
import com.joyintech.tams.invest.common.risktrialrds.dto.*;
import com.joyintech.tams.invest.common.risktrialrds.service.RiskTrialCommService;
import com.joyintech.tams.invest.common.risktrialrds.util.Const;
import com.joyintech.tams.invest.common.risktrialrds.util.RiskUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 日间批处理生成reader相关业务处理
 * @author 裴家俊
 * @version 1.0 2019年9月2日
 * @see RiskTrialDayTimeReaderService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
@Scope("prototype")
public class RiskTrialDayTimeReaderService extends BaseService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired(required = false)
    private RiskTrialCommMapper riskTrialCommMapper;

    @Autowired(required = false)
    private RiskTrialCommService riskTrialCommService;
    
    private Date supDate;


    /**
     * 获取待处理数据
     * @param param 传递参数
     * @return List<RiskTrialDayTimeReaderResult>
     * @since 1.0
     */
    public List<RiskTrialDayTimeReaderResult> execute(Map<String, Object> param) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        SysBatchExecInfoDto dto = (SysBatchExecInfoDto)param.get(BatchConstant.BATCH_INFO);
        supDate=(Date) param.get(BatchConstant.PLATE_DATE);
        List<RiskTrialDayTimeReaderResult> resultList = new ArrayList<RiskTrialDayTimeReaderResult>();
        List<String> batchPortList=(List<String>) param.get(Const.PROD_LIST);

        logger.info("---------->指标计算批处理数据生产任务开始");
        // 获取指标任务集合
        InvestTradeTrialInput investTradeTrialInput = new InvestTradeTrialInput();
        investTradeTrialInput.setIsEnabled(BussinessDictCodeListRds.使用状态_启用.getValue());
        investTradeTrialInput.setTimePoint(BussinessDictCodeListRds.监督时分_事后监控.getValue());
        investTradeTrialInput.setSupFrequency(param.get(Const.SUP_FREQUENCY).toString());
        investTradeTrialInput.setValDate(DateUtil.format(supDate,DateUtil.DEFAULT_DATE_FORMAT_EN));
        if(batchPortList.size()>0&&batchPortList.size()!=riskTrialCommMapper.selectAllPortfolioWithRisk()){
            Map<String,Object> paramInitMap = new HashMap<String,Object>();
            paramInitMap.put("productIdList", batchPortList);
            List<String> newPortList=riskTrialCommMapper.getPortfolioIdInFinProduct(paramInitMap);
            for(String portCodeSg:newPortList) {
                investTradeTrialInput.setPortfolioCode(portCodeSg);
                dealBasicProducer(investTradeTrialInput,resultList);
            }
        }
        else{
            dealBasicProducer(investTradeTrialInput,resultList);
        }  
        List<SupervisionParameterDto> supervisionParameterDtoList=riskTrialCommMapper.getIndicatorParamAll(investTradeTrialInput.getValDate());
        //查询个性化设置相关信息start--
        List<RskIndividSpecialStage> rskIndividSpecialStageList=riskTrialCommMapper.getRiskIndividSpecialStage(investTradeTrialInput.getValDate(), BussinessDictCodeListRds.阶段类型_特殊阶段.getValue());
        List<RskIndividSpecialStage> rskIndividSpecialStageAstList=riskTrialCommMapper.getRiskIndividSpecialStage(investTradeTrialInput.getValDate(), BussinessDictCodeListRds.阶段类型_特殊资产阶段.getValue());
        List<RskIndividAssetDel> rskIndividAssetDelList=riskTrialCommMapper.getRiskIndividAstDel(investTradeTrialInput.getValDate());
        for(RiskTrialDayTimeReaderResult riskTrialDto:resultList){
            riskTrialDto.setMapOther(Const.SUP_PARAM,supervisionParameterDtoList);
            riskTrialDto.setMapOther(Const.IND_SPE_STAGE,rskIndividSpecialStageList);
            riskTrialDto.setMapOther(Const.IND_SPE_STAGE_AST,rskIndividSpecialStageAstList);
            riskTrialDto.setMapOther(Const.IND_AST_DEL,rskIndividAssetDelList);
        }
        //查询个性化设置相关信息end--
        logger.info("---------->指标计算批处理数据生产任务结束");
        return resultList;
    }

    /**
     * 获取组合关联指标加入任务队列
     * @param investTradeTrialInput
     */
    private void dealBasicProducer(InvestTradeTrialInput investTradeTrialInput,List<RiskTrialDayTimeReaderResult> resultList){
        List<PtlPortfolioValuation>  riskPortfValList=new ArrayList<>();
        investTradeTrialInput.setNextDate(DateUtil.addDay(DateUtil.parse(investTradeTrialInput.getValDate()),1));
        List<SupervisionTargetDto> taskList = riskTrialCommMapper.getPortfIndTask(investTradeTrialInput);
        //查询所有的个性化条件设置信息
        List<RiskIndividConditionDto>  riskIndividConditionDtoList =  riskTrialCommMapper.getRiskIndividCondition();
        if(!StringUtil.isEmptyList(riskIndividConditionDtoList)){
            riskPortfValList =  riskTrialCommMapper.getPortfValuation(investTradeTrialInput.getValDate());
        }
        for(SupervisionTargetDto portfolioPoint : taskList) {
            RiskTrialDayTimeReaderResult riskTrialDayTimeReaderResult=new RiskTrialDayTimeReaderResult();
           /* String[] portCount=portfolioPoint.getPortfolioLevel().equals(BussinessDictCodeList.组合层次_组合包.getValue())?portfolioPoint.getPortfConstId().split(","):portfolioPoint.getPortfolioId().split(",");
            Map<String, Object> param = new HashMap<String, Object>();
            List<List<String>> portList= RiskUtil.splitList(portCount, 1000);
            param.put("portfolioId", portList);
            int reCount=riskTrialCommMapper.getCountWithBasicPortf(param);
            if(portCount.length!=reCount){
                logger.warn("组合包中的组合在组合表出现不存在的情况，可能是该组合已被删除或到期了");
                continue;
            }*/
            Task task = new Task();
            task.setDate(supDate);
            task.setPortfolioId(portfolioPoint.getPortfolioId());
            task.setPoint(portfolioPoint); 
            task.setInstitutionCode(riskTrialCommMapper.getPortfolioInstCode(portfolioPoint.getPortfolioId()));
            //判断是否设置条件设置,排除组合包
           if(portfolioPoint.getPortfolioLevel().equals(BussinessDictCodeListRds.组合层次_单组合.getValue())) {
               List<RiskIndividConditionDto>  riskIndividCondSubList =riskIndividConditionDtoList.stream().filter(item->item.getPrtBagId().equals(portfolioPoint.getPortfolioId())&&item.getIndId().equals(portfolioPoint.getIndId())).collect(Collectors.toList()) ;
               List<PtlPortfolioValuation> ptlPortValList=riskPortfValList.stream().filter(item->item.getPortfolioId().equals(portfolioPoint.getPortfolioId())).collect(Collectors.toList());
               boolean divZcFlag=true; boolean divDwFlag=true;boolean divJcFlag=false;
               for(RiskIndividConditionDto dto:riskIndividCondSubList){
                  /* if(dto.getConditionItem().equals(BussinessDictCodeListRds.条件项_资产净值控制.getValue())&&!StringUtil.isEmptyList(ptlPortValList)){
                       divZcFlag =  RiskUtil.compareNumberVal("NUM",dto.getControlType(), ArithmeticUtil.multiply(dto.getValueStr(),new BigDecimal(10000)),ArithmeticUtil.multiply(dto.getValueEnd(),new BigDecimal(10000)),ptlPortValList.get(0).getTdyAssetNetvalue());
                   }
                   if(dto.getConditionItem().equals(BussinessDictCodeListRds.条件项_单位净值控制.getValue())&&!StringUtil.isEmptyList(ptlPortValList)){
                       divDwFlag = RiskUtil.compareNumberVal("NUM",dto.getControlType(),dto.getValueStr(),dto.getValueEnd(),ptlPortValList.get(0).getTdyUnitValue());
                   }*/
                   //建仓
                   if(dto.getConditionItem().equals(BussinessDictCodeListRds.条件项_建仓期时间.getValue())){
                       //在建仓期期间不计算
                       divJcFlag = RiskUtil.compareNumberVal("DATE",BussinessDictCodeListRds.控制类型_范围控制.getValue(),dto.getValueStr(),dto.getValueEnd(),supDate);
                   }
               }
               if(!divZcFlag||!divDwFlag||divJcFlag){
                   continue;//跳过当前，执行下一个
               }
            }
           riskTrialDayTimeReaderResult.setTaskQueue(task);
           resultList.add(riskTrialDayTimeReaderResult);
        }
    }

}
