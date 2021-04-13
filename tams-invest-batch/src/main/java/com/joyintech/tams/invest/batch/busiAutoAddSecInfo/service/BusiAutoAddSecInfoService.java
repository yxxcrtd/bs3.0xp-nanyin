/*
 * 文件名：SecPosAutoGenerationService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：刘二通
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.busiAutoAddSecInfo.service;

import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.batch.busiAutoAddSecInfo.dao.BusiAutoAddSecInfoMapper;
import com.joyintech.tams.invest.batch.busiAutoAddSecInfo.dto.BaseFinProductBaseInfoDto;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.autoperiod.common.AutoPeriodCommonService;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.util.ValItemValueGenUtil;
import com.joyintech.tams.valuation.common.batch.finproduct.dao.FinProductCommonMapper;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.*;
import com.joyintech.tams.valuation.common.batch.finproduct.service.FinProductCommonBatchService;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.ValDictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 债券自动上市流通批处理
 *
 * @author sjj
 * @version 1.0 2021年04月07日
 * @see BusiAutoAddSecInfoService
 * @since 1.0
 */
@Service
public class BusiAutoAddSecInfoService {

    @Autowired
    private BusiAutoAddSecInfoMapper busiAutoAddSecInfoMapper;
    @Autowired
    private FinProductMapper finProductMapper;
    @Autowired
    private FinProductAddMapper finProductAddMapper;
    @Autowired
    private FinProductTypeMapper finProductTypeMapper;
    @Autowired
    private FinProductCommonMapper finProductCommonMapper;
    @Autowired
    private FinCashPayRuleMapper finCashPayRuleMapper;
    @Autowired
    private FinCashInfoMapper finCashInfoMapper;
    @Autowired
    private FinCashCalcRuleMapper finCashCalcRuleMapper;
    @Autowired
    private FinRateInfoMapper finRateInfoMapper;
    @Autowired
    private FinCashPlanMapper finCashPlanMapper;
    @Autowired
    private FinExercisePlanMapper finExercisePlanMapper;
    @Autowired
    private EtrSecPositionMapper etrSecPositionMapper;
    @Autowired
    private EtrSecPositionFlowMapper etrSecPositionFlowMapper;
    @Autowired
    private PtlSecPositionMapper ptlSecPositionMapper;
    @Autowired
    private PtlSecPositionFlowMapper ptlSecPositionFlowMapper;
    @Autowired
    private AutoPeriodCommonService autoPeriodCommonService;
    @Autowired
    private MstBondCreditRatingMapper mstBondCreditRatingMapper;
    @Autowired
    private RepAssetBondMapper repAssetBondMapper;
    @Autowired
    private RepAssetDeptMapper repAssetDeptMapper;
    @Autowired
    private RepAssetOtherMapper repAssetOtherMapper;
    @Autowired
    private RepAssetEquityMapper repAssetEquityMapper;

    /**
     *  债券自动上市流通批处理
     *
     * @param batchDate 执行日
     * @see
     * @since 1.0
     */
    public void autoGeneration(Date batchDate) {
        //1、获取未上市债券信息
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("FINPRODTYPE2F01",DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        paramMap.put("YESORNO_Y",DictCodeListExt1.是否_是.getValue());
        List<FinProduct> listFinProduct = busiAutoAddSecInfoMapper.selectUnlistedSecList(paramMap);

        //2、获取接口表债券信息，按照简称完全匹配
        List<BaseFinProductBaseInfoDto> baseFinProductBaseInfolist = new ArrayList<BaseFinProductBaseInfoDto>();
        for(FinProduct finProduct : listFinProduct){
            String secId = busiAutoAddSecInfoMapper.getSecId(finProduct);
            if(StringUtil.isBlank(secId)){
                continue;
            }

            BaseFinProductBaseInfoDto baseFinProductBaseInfoDto = new BaseFinProductBaseInfoDto();
            //2.1、复制未上市债券信息，并修改金融产品代码为上市金融产品代码
            //标的类金融产品表
            FinProduct finProductNew = new FinProduct();
            BeanUtil.copyPropertiesIgnoreNull(finProductNew, finProduct);
            finProductNew.setFinprodId("F01_"+secId);
            finProductNew.setFinprodMarketId(secId);
            baseFinProductBaseInfoDto.setFinProduct(finProductNew);

            //List<现金流信息Entity>
            // 查询条件
            List<TargetSearchDto> queryCriteria = new ArrayList<TargetSearchDto>();

            //金融产品附属表
            TargetSearchDto searchAddinfo = new TargetSearchDto();
            searchAddinfo.setQueryType("FINPROD_ADD");
            queryCriteria.add(searchAddinfo);

            //金融产品分类表
            TargetSearchDto searchTypeinfo = new TargetSearchDto();
            searchTypeinfo.setQueryType("FINPROD_TYPE");
            queryCriteria.add(searchTypeinfo);

            //金融产品分类表
            TargetSearchDto searchCashinfo = new TargetSearchDto();
            searchCashinfo.setQueryType("FINCASH_INFO");
            queryCriteria.add(searchCashinfo);

            // 现金流计算规则表
            TargetSearchDto searchCashCalcRuleinfo = new TargetSearchDto();
            searchCashCalcRuleinfo.setQueryType(ValDictCodeList.标的_现金流计算规则.getValue());
            queryCriteria.add(searchCashCalcRuleinfo);

            // 现金流利率信息表
            TargetSearchDto searchFinRateinfo = new TargetSearchDto();
            searchFinRateinfo.setQueryType("FINRATE_INFO");
            queryCriteria.add(searchFinRateinfo);

            // 现金流支付规则条件
            TargetSearchDto searchPayinfo = new TargetSearchDto();
            searchPayinfo.setQueryType(ValDictCodeList.标的_现金流支付规则.getValue());
            queryCriteria.add(searchPayinfo);

            // 还本计划条件
            TargetSearchDto searchRedinfo = new TargetSearchDto();
            searchRedinfo.setQueryType(ValDictCodeList.标的_现金流计划.getValue());
            queryCriteria.add(searchRedinfo);

            // 行权计划条件
            TargetSearchDto searchfinExercisePlan = new TargetSearchDto();
            searchfinExercisePlan.setQueryType("FINEXERCISE_PLAN");
            queryCriteria.add(searchfinExercisePlan);

            // 评级信息
            TargetSearchDto searchMarketRating = new TargetSearchDto();
            searchMarketRating.setQueryType("Market_Rating");
            queryCriteria.add(searchMarketRating);

            Map<TargetSearchDto, Object> targetSearchMap = this.getTargetInfo(
                    finProduct.getFinprodId(), queryCriteria);

            //标的类金融产品附属表
            FinProductAdd finProductAddNew = new FinProductAdd();
            BeanUtil.copyPropertiesIgnoreNull(finProductAddNew, targetSearchMap.get(searchAddinfo));
            finProductAddNew.setFinprodId("F01_"+secId);
            finProductAddNew.setWindId(secId);
            finProductAddNew.setIsAutoUpdate(DictCodeList.是否_是.getValue());
            baseFinProductBaseInfoDto.setFinProductAdd(finProductAddNew);

            //金融产品分类表
            FinProductType finProductTypeNew = new FinProductType();
            BeanUtil.copyPropertiesIgnoreNull(finProductTypeNew, targetSearchMap.get(searchTypeinfo));
            finProductTypeNew.setFinprodId("F01_"+secId);
            baseFinProductBaseInfoDto.setFinProductType(finProductTypeNew);

            //现金流信息表
            // TODO
            List<FinCashInfo> finCashInfolist = new ArrayList<FinCashInfo>();
            Map<String,String> paraMap =new HashMap<String,String>();
            String cashIdB = CommonUtil.generateCashFlowCode(finProductNew.getFinprodId(), DictCodeListExt1.现金流业务类型_金融产品.getValue(), 0L, DictCodeListExt1.现金流二级类型_本金.getValue());
            String cashIdL = CommonUtil.generateCashFlowCode(finProductNew.getFinprodId(), DictCodeListExt1.现金流业务类型_金融产品.getValue(), 0L, DictCodeListExt1.现金流二级类型_利息.getValue());

            for(FinCashInfo finCashInfo: (List<FinCashInfo>)targetSearchMap.get(searchCashinfo)){
                if(StringUtil.equals(DictCodeListExt1.现金流二级类型_本金.getValue(),finCashInfo.getCashType2())){
                    if(StringUtil.isBlank(paraMap.get(finCashInfo.getCashId()))){
                        paraMap.put(finCashInfo.getCashId(),cashIdB);
                    }
                    finCashInfo.setCashId(cashIdB);
                    finCashInfo.setFinprodId(finProductNew.getFinprodId());
                }else if(StringUtil.equals(DictCodeListExt1.现金流二级类型_利息.getValue(),finCashInfo.getCashType2())){
                    if(StringUtil.isBlank(paraMap.get(finCashInfo.getCashId()))){
                        paraMap.put(finCashInfo.getCashId(),cashIdL);
                    }
                    finCashInfo.setCashId(cashIdL);
                    finCashInfo.setFinprodId(finProductNew.getFinprodId());
                }
                finCashInfolist.add(finCashInfo);
            }
            baseFinProductBaseInfoDto.setFinCashInfoList(finCashInfolist);

            //现金流计算规则表
            List<FinCashCalcRule> finCashCalcRuleList = new ArrayList<FinCashCalcRule>();
            for(FinCashCalcRule finCashCalcRule: (List<FinCashCalcRule>)targetSearchMap.get(searchCashCalcRuleinfo)){
                if(paraMap.containsKey(finCashCalcRule.getCashId())){
                    finCashCalcRule.setCashId(paraMap.get(finCashCalcRule.getCashId()));
                    finCashCalcRule.setFinprodId(finProductNew.getFinprodId());
                    finCashCalcRuleList.add(finCashCalcRule);
                }
            }
            baseFinProductBaseInfoDto.setFinCashCalcRuleList(finCashCalcRuleList);

            //现金流利率信息表
            List<FinRateInfo> finRateInfoList = new ArrayList<FinRateInfo>();
            for(FinRateInfo finRateInfo: (List<FinRateInfo>)targetSearchMap.get(searchFinRateinfo)){
                if(paraMap.containsKey(finRateInfo.getCashId())){
                    finRateInfo.setCashId(paraMap.get(finRateInfo.getCashId()));
                    finRateInfo.setFinprodId(finProductNew.getFinprodId());
                    finRateInfoList.add(finRateInfo);
                }
            }
            baseFinProductBaseInfoDto.setFinRateInfoList(finRateInfoList);

            //现金流支付规则表
            List<FinCashPayRule> finCashPayRuleList = new ArrayList<FinCashPayRule>();
            for(FinCashPayRule finCashPayRule: (List<FinCashPayRule>)targetSearchMap.get(searchPayinfo)){
                if(paraMap.containsKey(finCashPayRule.getCashId())){
                    finCashPayRule.setCashId(paraMap.get(finCashPayRule.getCashId()));
                    finCashPayRule.setFinprodId(finProductNew.getFinprodId());
                    finCashPayRuleList.add(finCashPayRule);
                }
            }
            baseFinProductBaseInfoDto.setFinCashPayRuleList(finCashPayRuleList);

            //现金流计划
            List<FinCashPlan> finCashPlanList = new ArrayList<FinCashPlan>();
            for(FinCashPlan finCashPlan: (List<FinCashPlan>)targetSearchMap.get(searchRedinfo)){
                if(paraMap.containsKey(finCashPlan.getCashId())){
                    finCashPlan.setCashId(paraMap.get(finCashPlan.getCashId()));
                    finCashPlan.setFinprodId(finProductNew.getFinprodId());
                    finCashPlanList.add(finCashPlan);
                }
            }
            baseFinProductBaseInfoDto.setFinCashPlanList(finCashPlanList);

            //行权计划表
            List<FinExercisePlan> finExercisePlanList = new ArrayList<FinExercisePlan>();
            for(FinExercisePlan finExercisePlan: (List<FinExercisePlan>)targetSearchMap.get(searchfinExercisePlan)){
                if(paraMap.containsKey(finExercisePlan.getCashId())){
                    finExercisePlan.setCashId(paraMap.get(finExercisePlan.getCashId()));
                    finExercisePlan.setFinprodId(finProductNew.getFinprodId());
                    finExercisePlanList.add(finExercisePlan);
                }
            }
            baseFinProductBaseInfoDto.setFinExercisePlanList(finExercisePlanList);

            //评级信息表
            List<MstBondCreditRating> mstBondCreditRatingList = new ArrayList<MstBondCreditRating>();
            for(MstBondCreditRating mstBondCreditRating: (List<MstBondCreditRating>)targetSearchMap.get(searchMarketRating)){
                mstBondCreditRating.setoCode(secId);
                mstBondCreditRatingList.add(mstBondCreditRating);
            }
            baseFinProductBaseInfoDto.setMstBondCreditRatingList(mstBondCreditRatingList);

            baseFinProductBaseInfolist.add(baseFinProductBaseInfoDto);

            //2.2 根据组合持仓重组各组合上市流通交易
            //2.2.1 处理各组合指令持仓数据
            Map<String,String> tempMap =new HashMap<String,String>();
            tempMap.put("finprodId",finProduct.getFinprodId());
            tempMap.put("DealDate", DateUtil.addDay(DateUtil.format(batchDate),-1));
            List<EtrSecPosition> etrSecPositionList = busiAutoAddSecInfoMapper.getEtrSecPositionList(tempMap);
            BussinessCodeParameter parameter = new BussinessCodeParameter();
            parameter.setCommonBusinessType(DictCodeList.共通业务类型_债券买入);
            String tradeid =  autoPeriodCommonService.buildBussinessCode(parameter);
            for(EtrSecPosition etrSecPosition : etrSecPositionList){
                EtrSecPositionFlow etrSecPositionFlow =new EtrSecPositionFlow();
                //发行代码对应流水
                BeanUtil.copyPropertiesIgnoreNull(etrSecPositionFlow, etrSecPosition);
                etrSecPositionFlow.setSeqno(IDGenerator.generate());
                etrSecPositionFlow.setAmount(ArithmeticUtil.multiply(etrSecPosition.getAmount(),new BigDecimal("-1")));
                etrSecPositionFlow.setTradeId(tradeid);
                etrSecPositionFlowMapper.insertSelective(etrSecPositionFlow);

                //上市流通代码对应持仓
                etrSecPositionFlow.setAmount(ArithmeticUtil.multiply(etrSecPositionFlow.getAmount(),new BigDecimal("-1")));
                etrSecPositionFlow.setFinprodId("F01_"+secId);
                etrSecPositionFlow.setSeqno(IDGenerator.generate());
                etrSecPositionFlow.setTradeId(tradeid);
                etrSecPositionFlowMapper.insertSelective(etrSecPositionFlow);
            }

            //2.2.2 处理各组合持仓数据
            List<PtlSecPosition> ptlSecPositionList = busiAutoAddSecInfoMapper.getPtlSecPositionList(tempMap);
            for(PtlSecPosition ptlSecPosition : ptlSecPositionList){
                PtlSecPositionFlow ptlSecPositionFlow =new PtlSecPositionFlow();
                BeanUtil.copyPropertiesIgnoreNull(ptlSecPositionFlow, ptlSecPosition);
                //发行代码对应流水
                ptlSecPositionFlow.setSeqno(IDGenerator.generate());
                ptlSecPositionFlow.setAmount(ArithmeticUtil.multiply(ptlSecPosition.getAmount(),new BigDecimal("-1")));
                ptlSecPositionFlow.setTradeId(tradeid);
                ptlSecPositionFlowMapper.insertSelective(ptlSecPositionFlow);

                //上市流通代码对应持仓
                ptlSecPositionFlow.setAmount(ArithmeticUtil.multiply(ptlSecPositionFlow.getAmount(),new BigDecimal("-1")));
                ptlSecPositionFlow.setFinprodId("F01_"+secId);
                ptlSecPositionFlow.setSeqno(IDGenerator.generate());
                ptlSecPositionFlow.setTradeId(tradeid);
                ptlSecPositionFlowMapper.insertSelective(ptlSecPositionFlow);
            }

            //债券资产报送信息
            RepAssetBond repAssetBondNew = new RepAssetBond();
            RepAssetBond repAssetBond = repAssetBondMapper.selectByPrimaryKey(finProduct.getFinprodId());
            if(repAssetBond !=null){
                BeanUtil.copyPropertiesIgnoreNull(repAssetBondNew, repAssetBond);
                repAssetBondNew.setRepFinprodId("F01_"+secId);
                baseFinProductBaseInfoDto.setRepAssetBond(repAssetBondNew);
            }

            //资产负债报送信息
            RepAssetDept repAssetDeptNew = new RepAssetDept();
            RepAssetDept repAssetDept = repAssetDeptMapper.selectByPrimaryKey(finProduct.getFinprodId());
            if(repAssetDept !=null){
                BeanUtil.copyPropertiesIgnoreNull(repAssetDeptNew, repAssetDept);
                repAssetDeptNew.setRepFinprodId("F01_"+secId);
                baseFinProductBaseInfoDto.setRepAssetDept(repAssetDeptNew);
            }

            //中债资产其他要素
            RepAssetOther repAssetOtherNew = new RepAssetOther();
            RepAssetOther repAssetOther = repAssetOtherMapper.selectByPrimaryKey(finProduct.getFinprodId());
            if(repAssetOther !=null){
                BeanUtil.copyPropertiesIgnoreNull(repAssetOtherNew, repAssetOther);
                repAssetOtherNew.setRepFinprodId("F01_"+secId);
                int i = repAssetOtherMapper.insert(repAssetOtherNew);
            }

            //权益类资产报送信息
            RepAssetEquity repAssetEquityNew = new RepAssetEquity();
            RepAssetEquity repAssetEquity = repAssetEquityMapper.selectByPrimaryKey(finProduct.getFinprodId());
            if(repAssetEquity !=null){
                BeanUtil.copyPropertiesIgnoreNull(repAssetEquityNew, repAssetEquity);
                repAssetEquityNew.setRepFinprodId("F01_"+secId);
                int ii = repAssetEquityMapper.insert(repAssetEquityNew);
            }

            //2.3 更新原有标的上市代码
            FinProductAdd finProductAdd = new FinProductAdd();
            finProductAdd.setFinprodId(finProduct.getFinprodId());
            //finProductAdd.setap(secId);
            busiAutoAddSecInfoMapper.updateByPrimaryKey(finProductAdd);
        }
        //入库
        if(baseFinProductBaseInfolist != null && baseFinProductBaseInfolist.size() > 0){
            this.insert(baseFinProductBaseInfolist);
        }
    }

    /**
     * 公用方法：标的类共通方法 - 标的信息查询
     * @param finprodId 标的代码【必输项】
     * @param queryCriteria 查询类型【必输项】
     * @return Map<TargetSearchDto, Object> 返回值获取，直接用查询实体类作为key值获取。
     */
    public Map<TargetSearchDto, Object> getTargetInfo(String finprodId,
                                                      List<TargetSearchDto> queryCriteria) {
        // 定义返回值
        Map<TargetSearchDto, Object> returnMap = new HashMap<TargetSearchDto, Object>();

        // 循环查询类型list,进行获取数据
        for (TargetSearchDto svo : queryCriteria) {
            // 获取当前查询类型
            String queryTypeNow = svo.getQueryType();

            // 设置sql查询参数map
            Map<String, Object> paMap = new HashMap<String, Object>();
            paMap.put("finprodId", finprodId);// 标的代码
            paMap.put("branch", BookConstant.DEFALUT_BRANCH);
            // 根据传递的查询类型，获取不同的标的数据
            if(StringUtil.equals("FINPROD_ADD",queryTypeNow)){
                FinProductAdd finProductAdd = finProductAddMapper.selectByPrimaryKey(finprodId);
                returnMap.put(svo, finProductAdd);
            }
            else if(StringUtil.equals("FINPROD_TYPE",queryTypeNow)){
                FinProductTypeKey key =new FinProductTypeKey();
                key.setFinprodId(finprodId);
                key.setBranch(0L);
                FinProductType finProductType = finProductTypeMapper.selectByPrimaryKey(key);
                returnMap.put(svo, finProductType);
            }
            else if(StringUtil.equals("FINCASH_INFO",queryTypeNow)){
                List<FinCashInfo> finCashInfolist = busiAutoAddSecInfoMapper.selectFinCashInfo(finprodId);
                returnMap.put(svo, finCashInfolist);
            }
            else if(StringUtil.equals(ValDictCodeList.标的_现金流计算规则.getValue(),queryTypeNow)){
                List<FinCashCalcRule> finCashCalcRuleList = busiAutoAddSecInfoMapper.getTargetCashCalcRuleList(paMap);
                returnMap.put(svo, finCashCalcRuleList);
            }
            else if(StringUtil.equals("FINRATE_INFO",queryTypeNow)){
                List<FinRateInfo> finRateInfoList = busiAutoAddSecInfoMapper.getFinRateInfoList(paMap);
                returnMap.put(svo, finRateInfoList);
            }
            else if (ValDictCodeList.标的_现金流支付规则.getValue().equals(queryTypeNow)) {
                // 查询标的现金流支付规则
                List<FinCashPayRule> finCashPayRuleList = busiAutoAddSecInfoMapper.getFinCashPayRuleList(paMap);
                // 查询结果塞入map中
                returnMap.put(svo, finCashPayRuleList);
            }
            else if (ValDictCodeList.标的_现金流计划.getValue().equals(queryTypeNow)) {
                // 查询标的现金流计划list
                List<FinCashPlan> finCashPlanList = busiAutoAddSecInfoMapper.getFinCashPlanList(paMap);
                // 查询结果塞入map中
                returnMap.put(svo, finCashPlanList);

            }
            else if ("FINEXERCISE_PLAN".equals(queryTypeNow)) {
                // 查询标的现金流计划list
                List<FinExercisePlan> finExerciseList = busiAutoAddSecInfoMapper.getFinExercisePlanList(paMap);
                // 查询结果塞入map中
                returnMap.put(svo, finExerciseList);

            }
            else if("Market_Rating".equals(queryTypeNow)){
                // 查询评级信息list
                List<MstBondCreditRating> mstBondCreditRatingList = busiAutoAddSecInfoMapper.selectMstBondCreditRating(finprodId.replace("F01_",""));
                mstBondCreditRatingList.addAll(busiAutoAddSecInfoMapper.selectMstinnercreditrating(finprodId.replace("F01_","")));
                // 查询结果塞入map中
                returnMap.put(svo, mstBondCreditRatingList);
            }
//            else if("REP_ASSET_BOND".equals(queryTypeNow)){
//                // 查询评级信息list
//                List<MstBondCreditRating> mstBondCreditRatingList = busiAutoAddSecInfoMapper.selectMstBondCreditRating(finprodId.replace("F01_",""));
//                mstBondCreditRatingList.addAll(busiAutoAddSecInfoMapper.selectMstinnercreditrating(finprodId.replace("F01_","")));
//                // 查询结果塞入map中
//                returnMap.put(svo, mstBondCreditRatingList);
//            }
//            else if("REP_ASSET_EQUITY".equals(queryTypeNow)){
//                // 查询评级信息list
//                List<BussRepEquityInfoDto> mstBondCreditRatingList = bussRepEquityInfoMapper.getBussRepEquityInfoByProductId(finprodId);
//                mstBondCreditRatingList.addAll(busiAutoAddSecInfoMapper.selectMstinnercreditrating(finprodId.replace("F01_","")));
//                // 查询结果塞入map中
//                returnMap.put(svo, mstBondCreditRatingList);
//            }
//            else if (ValDictCodeList.标的_现金流计算规则.getValue().equals(queryTypeNow)) {
//                // 查询标的现金流计算规则list
//                List<TargetCashCalcRule> targetCashCalcRuleList = finProductCommonMapper.getTargetCashCalcRuleList(paMap);
//                // 查询结果塞入map中
//                returnMap.put(svo, targetCashCalcRuleList);
//
//            }
//            else if (ValDictCodeList.标的_业绩报酬计提计划.getValue().equals(queryTypeNow)) {
//                // 查询标的业绩报酬计提计划list
//                List<TargetPerfRewardAccrsche> targetPerfRewardAccrscheList = finProductCommonMapper.getTargetPerfRewardAccrscheList(paMap);
//                // 查询结果塞入map中
//                returnMap.put(svo, targetPerfRewardAccrscheList);
//
//            }
//            else if (ValDictCodeList.标的_业绩报酬计提规则.getValue().equals(queryTypeNow)) {
//                // 查询标的业绩报酬规则 list
//                List<TargetPerfRewardRule> targetPerfRewardRuleList = finProductCommonMapper.getTargetPerfRewardRuleList(paMap);
//
//                // 查询结果塞入map中
//                returnMap.put(svo, targetPerfRewardRuleList);
//            }
//            else if (ValDictCodeList.标的_业绩报酬计算规则.getValue().equals(queryTypeNow)) {
//                // 查询标的业绩报酬规则 list
//                List<TargetPerfRewardCalRule> targetPerfRewardCalRule = finProductCommonMapper.getTargetPerfRewardCalRuleList(paMap);
//
//                // 循环业绩报酬规则 list，获取每个对应的标的业绩报酬规则明细
//                for (TargetPerfRewardCalRule dto : targetPerfRewardCalRule) {
//                    // 获取对应的明细List
//                    List<TargetPerfRewardCalRuleDetail> details = finProductCommonMapper.getTargetPerfRewardRuleDetailList(
//                            dto.getCashId(), dto.getEffDate());
//                    // 将明显塞入实体类中
//                    dto.setTargetPerfRewardCalRuleDetailList(details);
//                }
//
//                // 查询结果塞入map中
//                returnMap.put(svo, targetPerfRewardCalRule);
//            }
//            /** modify by dongjun on 20190219 remark:标的_业绩回补计算规则 start**/
//            else if (ValDictCodeList.标的_业绩回补计算规则.getValue().equals(queryTypeNow)) {
//                // 查询标的_业绩报酬计算规则 list
//                List<TargetFinClawbackCalcRule> targetFinCawlbackCalcRuleList = finProductCommonMapper.getTargetFinCLawbackList(paMap);
//                // 循环标的_业绩回补计算规则 list，获取每个对应的标的业绩报酬规则明细
//                for (TargetFinClawbackCalcRule dto : targetFinCawlbackCalcRuleList) {
//                    // 获取对应的明细List
//                    List<TargetPerfRewardCalRuleDetail> details = finProductCommonMapper.getTargetPerfRewardRuleDetailList(
//                            dto.getCashId(), dto.getEffDate());
//                    // 将明显塞入实体类中
//                    dto.setTargetPerfRewardCalRuleDetailList(details);
//                }
//
//                // 查询结果塞入map中
//                returnMap.put(svo, targetFinCawlbackCalcRuleList);
//            }
            /** modify by dongjun on 20190219 remark:标的_业绩回补计算规则 end**/

        }

        return returnMap;
    }
    /**
     * 标的类金融产品基本信息
     */
    private void insert(List<BaseFinProductBaseInfoDto> list) {
        if (list != null && list.size() > 0) {
            // 循环遍历list里的dto
            for (BaseFinProductBaseInfoDto dto : list) {
                // 插入标的类金融产品表
                if (dto.getFinProduct() != null) {
                    // 一体化对应etliu start
                    ValItemValueGenUtil.genFinprodTypeForFinProduct(dto.getFinProduct(), dto.getFinProductType());
                    // 一体化对应etliu end
                    finProductMapper.insert(dto.getFinProduct());
                }
                // 插入标的类金融产品附属表
                if (dto.getFinProductAdd() != null) {
                    finProductAddMapper.insert(dto.getFinProductAdd());
                }
                // 插入金融产品分类表
                if (dto.getFinProductType() != null) {
                    ValItemValueGenUtil.genTypeForFinProductType(dto.getFinProduct(), dto.getFinProductType());
                    finProductTypeMapper.insert(dto.getFinProductType());
                }
                //现金流信息
                if (!StringUtil.isEmptyList(dto.getFinCashInfoList())) {
                    for (FinCashInfo finCashInfo : dto.getFinCashInfoList()) {
                        finCashInfoMapper.insert(finCashInfo);
                    }
                }
                //插入金融产品现金流计算规则表
                if (dto.getFinCashCalcRuleList()!=null && !dto.getFinCashCalcRuleList().isEmpty()) {
                    for (FinCashCalcRule finCashCalcRule : dto.getFinCashCalcRuleList()) {
                        finCashCalcRuleMapper.insert(finCashCalcRule);
                    }
                }
                //插入金融产品现金流利率信息表
                if (!StringUtil.isEmptyList(dto.getFinRateInfoList())) {
                    for (FinRateInfo finRateInfo : dto.getFinRateInfoList()) {
                        finRateInfoMapper.insert(finRateInfo);
                    }
                }
                //插入金融产品现金流支付规则表
                if (!StringUtil.isEmptyList(dto.getFinCashPayRuleList())) {
                    for (FinCashPayRule finCashPayRule : dto.getFinCashPayRuleList()) {
                        finCashPayRuleMapper.insert(finCashPayRule);
                    }
                }
                //插入金融产品现金流计划表
                if (!StringUtil.isEmptyList(dto.getFinCashPlanList())) {
                    for (FinCashPlan finCashPlan : dto.getFinCashPlanList()) {
                        finCashPlanMapper.insert(finCashPlan);
                    }
                }

                //插入金融产品行权计划表
                if (!StringUtil.isEmptyList(dto.getFinExercisePlanList())) {
                    for (FinExercisePlan finExercisePlan : dto.getFinExercisePlanList()) {
                        finExercisePlanMapper.insert(finExercisePlan);
                    }
                }

                //插入评级表
                if (!StringUtil.isEmptyList(dto.getMstBondCreditRatingList())) {
                    for (MstBondCreditRating mstBondCreditRating : dto.getMstBondCreditRatingList()) {
                       mstBondCreditRatingMapper.insert(mstBondCreditRating);
                    }
                }
                // 插入标的类金融产品表
                if (dto.getRepAssetBond() != null) {
                    repAssetBondMapper.insert(dto.getRepAssetBond());
                }

                // 插入标的类金融产品表
                if (dto.getRepAssetDept() != null) {
                    repAssetDeptMapper.insert(dto.getRepAssetDept());
                }
            }
        }
    }
}
