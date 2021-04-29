package com.joyintech.service.trade.bondmarkettrade.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.trade.bondmarkettrade.dao.TrdBondMarketTradeMapper;
import com.joyintech.service.trade.bondmarkettrade.dto.TrdBondMarketTradeDto;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.base.core.*;
import com.joyintech.tams.component.base.core.dto.BaseFinProductCashCalcRuleDto;
import com.joyintech.tams.component.base.core.dto.BaseFinProductCashRateRuleDto;
import com.joyintech.tams.component.base.core.dto.BaseFinProductExerciseRuleDto;
import com.joyintech.tams.component.base.core.dto.BaseFinProductBaseInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.component.base.group.BasePtlSecPositionInfoService;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.etr.EtrPositionHoldDto;
import com.joyintech.tams.invest.common.dto.etr.EtrSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.period.common.BaseTradeInfoDto;
import com.joyintech.tams.invest.common.redisinit.dto.ImplCond;
import com.joyintech.tams.invest.common.redisinit.service.RiskRedisInitService;
import com.joyintech.tams.invest.common.trade.service.impl.etrcash.EtrCommonCashPositionDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TrdBondMarketTradeService extends BaseService {
    @Autowired
    TrdBondMarketTradeMapper trdBondMarkettTradeMapper;

    /**
     * 汇总实物交易表
     */
    @Autowired
    private TrdFinProductBarterTotalMapper trdFinProductBarterTotalMapper;

    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;

    @Autowired
    private TrdProductDealMapper trdProductDealMapper;

    @Autowired
    private TrdProductDealAddMapper trdProductDealAddMapper;
    
    @Autowired
    private IPlatformDate iPlatformDate;
    
    @Autowired
    private RiskRedisInitService<ImplCond> redisRriter;

    /**
     * 实物交易表
     */
    @Autowired
    private TrdFinProductBarterMapper trdFinProductBarterMapper;

    /**
     * 自动生成标的类金融产品表mapper对象
     */
    @Autowired
    private FinProductMapper finProductMapper;

    /**
     * 自动生成标的类金融产品附属表mapper对象
     */
    @Autowired
    private FinProductAddMapper finProductAddMapper;

    /**
     * 自动生成金融产品分类表mapper对象
     */
    @Autowired
    private FinProductTypeMapper finProductTypeMapper;

    /**
     * 金融产品现金流信息组件
     */
    @Autowired(required = false)
    private BaseFinProductCashInfoService baseFinProductCashInfoService;

    /**
     * 金融产品现金流支付规则组件
     */
    @Autowired(required = false)
    private BaseFinProductPayRuleService baseFinProductPayRuleService;

    /**
     * 现金流计算规则组件
     */
    @Autowired(required = false)
    private BaseFinProductCashCalcRuleService baseFinProductCashCalcRuleService;

    /**
     * 现金流利率规则信息组件
     */
    @Autowired(required = false)
    private BaseFinProductCashRateRuleService baseFinProductCashRateRuleService;

    /**
     * 金融产品现金流支付计划组件
     */
    @Autowired(required = false)
    private BaseFinProductCashPayPlanService baseFinProductCashPayPlanService;

    /**
     * 金融产品现金流行权规则组件
     */
    @Autowired(required = false)
    private BaseFinProductExerciseRuleService baseFinProductExerciseRuleService;

    /**
     * 金融产品现金流行权计划组件
     */
    @Autowired(required = false)
    private BaseFinProductExercisePlanService baseFinProductExercisePlanService;

    @Autowired
    private BaseFinProductBaseInfoService baseFinProductBaseInfoService;

    /**
     * 中债报送
     */
    @Autowired
    private RepAssetBondMapper repAssetBondMapper;
    @Autowired
    private RepAssetDeptMapper repAssetDeptMapper;
    @Autowired
    private RepAssetOtherMapper repAssetOtherMapper;
    @Autowired
    private RepAssetEquityMapper repAssetEquityMapper;

    /**
     * 共通Service
     */
    @Autowired(required = false)
    private EtrCommonCashPositionDealService etrCommonCashPositionDealService;

    @Autowired(required = false)
    private BasePtlSecPositionInfoService basePtlSecPositionInfoService;

    @Autowired(required = false)
    private PtlTradeMapper ptlTradeMapper;
    
    /**
     * 分页查询
     *
     * @param page 页编号
     * @param length 页条数
     * @param pageParam 画面参数
     * @return 查询结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<TrdBondMarketTradeDto> findPage(int page, int length,Map<String, Object> pageParam)
            throws Exception {
        pageParam.put("VALID_STATUS_01", DictCodeListExt1.有效状态_有效.getValue());
        pageParam.put("VALID_STATUS_02", DictCodeListExt1.有效状态_待处理.getValue());
        pageParam.put("VALID_STATUS_03", DictCodeListExt1.有效状态_撤销.getValue());
        pageParam.put("YES_NO_YES", DictCodeList.是否_是.getValue());
        pageParam.put("YES_NO_NO", DictCodeList.是否_否.getValue());
        pageParam.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        pageParam.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());

        // 一体化对应etliu start
        List<String> psList = new ArrayList<String>();
        psList.add(DictCodeList.交易方向_买入.getValue());
        psList.add(DictCodeList.交易方向_卖出.getValue());
        psList.add(DictCodeList.交易方向_行权_回售.getValue());
        psList.add(DictCodeList.交易方向_行权_赎回.getValue());
        pageParam.put("psList", psList);
        // 一体化对应etliu end

        return this.queryForPage(page, length,
                () -> trdBondMarkettTradeMapper.selectDeal(pageParam));
    }

    /**
     * 发行代码：下拉框
     *
     * @param
     * @return 发行代码：下拉框
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getFinprodId(int page, int length, Map<String, Object> param) throws Exception {
        param.put("FINPROD_TYPE_TG", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        return this.queryForPage(page, length,
                () -> trdBondMarkettTradeMapper.getFinprodId(param));
    }

    /**
     * 上市代码：下拉框
     *
     * @param
     * @return 资产代码：下拉框
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getAppearMarketId(int page, int length, Map<String, Object> param) throws Exception {
        param.put("FINPROD_TYPE_TG", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        return this.queryForPage(page, length,
                () -> trdBondMarkettTradeMapper.getAppearMarketId(param));
    }

    /**
     * 获取持仓数据
     *
     * @param map
     * @return 持仓数据
     * @see
     * @since 1.0
     */
    public Map<String, Object> getPosition(Map<String, Object> map) {
        Map<String, Object> resultMap = new HashMap<>();
        List<PtlSecPosition> ptlSecPositions = trdBondMarkettTradeMapper.getPtlPosition(map);

        List<EtrSecPosition> etrSecPositions = trdBondMarkettTradeMapper.getEtrPosition(map);
        resultMap.put("ptlSecPositions",ptlSecPositions);
        resultMap.put("etrSecPositions",etrSecPositions);
        return resultMap;
    }

    /**
     * 流程结束登录业务表
     *
     * @param entity
     * @see
     * @since 1.0
     */
    public void doInsertTotal(TrdBondMarketTradeDto entity) {
        if (entity!= null){
            //7.1登录各业务表，包括金融产品汇总交易、金融产品实物汇总交易表
            //金融产品实物汇总交易表
            trdFinProductBarterTotalMapper.insertSelective(entity.getTrdFinProductBarterTotal());

            //金融产品汇总交易
            TrdFinProductDealTotal trdFinProductDealTotal = entity.getFinProductTradeDto().getBaseTradeProductDealDtos().get(0).getTrdFinProductDealTotal();
            trdFinProductDealTotalMapper.insertSelective(trdFinProductDealTotal);

            //更新发行代码金融产品附属表的上市代码
            trdBondMarkettTradeMapper.updateByPrimaryKey(entity);
        }
    }

    /**
     * 流程结束登录业务表
     *
     * @param tradeInfoDtoList
     * @see
     * @since 1.0
     */
    public void doInsert(List<BaseTradeInfoDto> tradeInfoDtoList) {
        if (tradeInfoDtoList != null && tradeInfoDtoList.size() > 0){
            //登录金融产品交易，金融产品附属交易，实物交易表
            for (BaseTradeInfoDto baseTradeInfoDto : tradeInfoDtoList) {
                trdProductDealMapper.insertSelective(baseTradeInfoDto.getTrdProductDeal());
                trdProductDealAddMapper.insertSelective(baseTradeInfoDto.getTrdProductDealAdd());
                trdFinProductBarterMapper.insertSelective(baseTradeInfoDto.getTrdFinProductBarter());
                ptlTradeMapper.insert(baseTradeInfoDto.getPtlTrade().get(0));
            }    
        }
    }

    /**
     * 债券上市流通自动加券
     * @param dto
     * @return
     */
    public void autoAddBond(TrdBondMarketTradeDto dto) {
        BaseFinProductBaseInfoDto baseFinProductBaseInfoDto = new BaseFinProductBaseInfoDto();

        //加券债券的资产代码
        String newFinprodId = DictCodeListExt1.金融产品类型_投管用_债券.getValue() + "_" + dto.getAppearMarketId();

        FinProduct finProduct1 = finProductMapper.selectByPrimaryKey(newFinprodId);
        //根据资产代码查询不到数据则进行加券操作
        if (finProduct1 == null){
            //1. 查询数据
            //标的类金融产品Entity
            FinProduct finProduct = finProductMapper.selectByPrimaryKey(dto.getFinprodId());

            //标的类金融产品附属表Entity
            FinProductAdd finProductAdd = finProductAddMapper.selectByPrimaryKey(dto.getFinprodId());

            //金融产品分类表Entity
            FinProductTypeKey finProductTypeKey = new FinProductTypeKey();
            finProductTypeKey.setFinprodId(dto.getFinprodId());
            finProductTypeKey.setBranch(0L);
            FinProductType finProductType = finProductTypeMapper.selectByPrimaryKey(finProductTypeKey);


            //List<现金流信息Entity>    
            FinCashInfo finCashInfo = new FinCashInfo();
            finCashInfo.setFinprodId(dto.getFinprodId());
            List<FinCashInfo> finCashInfos = baseFinProductCashInfoService.select(finCashInfo);
            Map<String,String> cashIdMap = new HashMap<>();
            for (FinCashInfo cashInfo : finCashInfos) {
                //将原现金流代码作为key，新生成的现金流代码作为value
                String cashId = IDGenerator.generate();
                cashIdMap.put(cashInfo.getCashId(),cashId);
                cashInfo.setFinprodId(newFinprodId);
                cashInfo.setCashId(cashId);
            }

            //List<现金流计算规则表Entity> 
            BaseFinProductCashCalcRuleDto baseFinProductCashCalcRuleDto = new BaseFinProductCashCalcRuleDto();
            baseFinProductCashCalcRuleDto.setFinprodId(dto.getFinprodId());
            List<FinCashCalcRule> finCashCalcRules = baseFinProductCashCalcRuleService.selectByBaseFinProductCashCalcRuleDto(baseFinProductCashCalcRuleDto);

            //List<现金流利率信息表Entity>  
            BaseFinProductCashRateRuleDto baseFinProductCashRateRuleDto = new BaseFinProductCashRateRuleDto();
            baseFinProductCashRateRuleDto.setFinprodId(dto.getFinprodId());
            List<FinRateInfo> finRateInfos = baseFinProductCashRateRuleService.selectByBaseFinProductCashRateRuleDto(baseFinProductCashRateRuleDto);

            //List<现金流支付规则表Entity>  
            FinCashPayRule finCashPayRule = new FinCashPayRule();
            finCashPayRule.setFinprodId(dto.getFinprodId());
            List<FinCashPayRule> finCashPayRules = baseFinProductPayRuleService.select(finCashPayRule);

            //List<现金流计划表Entity>  
            FinCashPlan finCashPlan = new FinCashPlan();
            finCashPlan.setFinprodId(dto.getFinprodId());
            List<FinCashPlan> finCashPlans = baseFinProductCashPayPlanService.select(finCashPlan);

            //List<行权规则表Entity>  
            BaseFinProductExerciseRuleDto baseFinProductExerciseRuleDto = new BaseFinProductExerciseRuleDto();
            baseFinProductExerciseRuleDto.setFinprodId(dto.getFinprodId());
            List<FinExerciseRule> finExerciseRules = baseFinProductExerciseRuleService.select(baseFinProductExerciseRuleDto);

            //List<行权计划表Entity>  
            FinExercisePlan finExercisePlan = new FinExercisePlan();
            finExercisePlan.setFinprodId(dto.getFinprodId());
            List<FinExercisePlan> finExercisePlans = baseFinProductExercisePlanService.select(finExercisePlan);

            //债券资产报送信息Entity
            RepAssetBond repAssetBond = repAssetBondMapper.selectByPrimaryKey(dto.getFinprodId());
            //资产负债报送信息Entity
            RepAssetDept repAssetDept = repAssetDeptMapper.selectByPrimaryKey(dto.getFinprodId());
            //中债资产其他要素Entity
            RepAssetOther repAssetOther = repAssetOtherMapper.selectByPrimaryKey(dto.getFinprodId());
            //权益类资产报送信息Entity
            RepAssetEquity repAssetEquity = repAssetEquityMapper.selectByPrimaryKey(dto.getFinprodId());

            //债券信用评级Entity
            List<MstBondCreditRating> mstBondCreditRatingList = trdBondMarkettTradeMapper.selectByPrimaryKey(finProduct.getFinprodMarketId());

            //处理债券资产报送信息Entity
            if(repAssetBond != null){
                repAssetBond.setRepFinprodId(newFinprodId);
                repAssetBond.setSecId(dto.getAppearMarketId());
            }
            
            //处理资产负债报送信息Entity
            if (repAssetDept != null){
                repAssetDept.setRepFinprodId(newFinprodId);
                repAssetDept.setFinprodMarketId(dto.getAppearMarketId());
            }
            
            //处理中债资产其他要素Entity
            if (repAssetOther != null){
                repAssetOther.setRepFinprodId(newFinprodId);
                repAssetOtherMapper.insertSelective(repAssetOther);
            }
            
            //处理权益类资产报送信息Entity
            if (repAssetEquity != null){
                repAssetEquity.setRepFinprodId(newFinprodId);
                repAssetEquity.setRepSharesId(dto.getAppearMarketId());
                repAssetEquityMapper.insertSelective(repAssetEquity);
            }
            
            //处理债券信用评级Entity
            for (MstBondCreditRating mstBondCreditRating : mstBondCreditRatingList) {
                mstBondCreditRating.setoCode(dto.getAppearMarketId());
            }

            //处理查询到的数据
            if (finProduct != null){
                finProduct.setFinprodId(newFinprodId);
                finProduct.setFinprodMarketId(dto.getAppearMarketId());
            }
            
            if (finProductAdd != null){
                finProductAdd.setFinprodId(newFinprodId);
            }

            if (finProductType != null){
                finProductType.setFinprodId(newFinprodId);
            }

            //处理现金流信息
            for (String key : cashIdMap.keySet()) {
                //现金流计算规则表Entity
                for (FinCashCalcRule finCashCalcRule : finCashCalcRules) {
                    if (StringUtil.equals(key,finCashCalcRule.getCashId())){
                        finCashCalcRule.setCashId(cashIdMap.get(key));
                        finCashCalcRule.setFinprodId(newFinprodId);
                    }
                }

                //现金流支付规则表Entity
                for (FinRateInfo finRateInfo : finRateInfos) {
                    if (StringUtil.equals(key,finRateInfo.getCashId())){
                        finRateInfo.setCashId(cashIdMap.get(key));
                        finRateInfo.setFinprodId(newFinprodId);
                    }
                }

                //现金流支付规则表Entity
                for (FinCashPayRule cashPayRule : finCashPayRules) {
                    if (StringUtil.equals(key,cashPayRule.getCashId())){
                        cashPayRule.setCashId(cashIdMap.get(key));
                        cashPayRule.setFinprodId(newFinprodId);
                    }
                }

                //现金流计划表Entity
                for (FinCashPlan cashPlan : finCashPlans) {
                    if (StringUtil.equals(key,cashPlan.getCashId())){
                        cashPlan.setCashId(cashIdMap.get(key));
                        cashPlan.setFinprodId(newFinprodId);
                    }
                }

                //行权规则表Entity
                for (FinExerciseRule finExerciseRule : finExerciseRules) {
                    if (StringUtil.equals(key,finExerciseRule.getCashId())){
                        finExerciseRule.setCashId(cashIdMap.get(key));
                        finExerciseRule.setFinprodId(newFinprodId);
                    }
                }

                //行权计划表Entity
                for (FinExercisePlan exercisePlan : finExercisePlans) {
                    if (StringUtil.equals(key,exercisePlan.getCashId())){
                        exercisePlan.setCashId(cashIdMap.get(key));
                        exercisePlan.setFinprodId(newFinprodId);
                    }
                }
            }

            //组装标的类金融产品基本信息DTO
            baseFinProductBaseInfoDto.setFinProduct(finProduct);
            baseFinProductBaseInfoDto.setFinProductAdd(finProductAdd);
            baseFinProductBaseInfoDto.setFinProductType(finProductType);
            baseFinProductBaseInfoDto.setFinCashInfoList(finCashInfos);
            baseFinProductBaseInfoDto.setFinCashCalcRuleList(finCashCalcRules);
            baseFinProductBaseInfoDto.setFinRateInfoList(finRateInfos);
            baseFinProductBaseInfoDto.setFinCashPayRuleList(finCashPayRules);
            baseFinProductBaseInfoDto.setFinCashPlanList(finCashPlans);
            baseFinProductBaseInfoDto.setFinExercisePlanList(finExercisePlans);
            baseFinProductBaseInfoDto.setFinExerciseRuleList(finExerciseRules);
            baseFinProductBaseInfoDto.setRepAssetBond(repAssetBond);
            baseFinProductBaseInfoDto.setRepAssetDept(repAssetDept);
            baseFinProductBaseInfoDto.setMstBondCreditRatingList(mstBondCreditRatingList);
            List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoDtos = new ArrayList<>();
            baseFinProductBaseInfoDtos.add(baseFinProductBaseInfoDto);
            //调用接口入库
            baseFinProductBaseInfoService.insert(baseFinProductBaseInfoDtos);
            
            //接风险redis-资产基本信息
            if (CommonUtil.isType(CacheUtil.getSysParam("pageRedisFlag"), BussinessDictCodeListRds.是否_是)) {
            	if(redisRriter!=null&&iPlatformDate!=null&&StringUtil.isNotEmpty(newFinprodId)) {
                    ImplCond cond = new ImplCond();
                    cond.setDel(false);
                    cond.setAssetIdArray(newFinprodId);
                    cond.setPlateDate(iPlatformDate.getSysDate());
                    redisRriter.dealAssetData(cond);	
            	}
            }
        }
    }

    /**
     * 查询是否有工作流中数据
     * @param dto
     * @return
     */
    public int selectIsInWorkflow(TrdBondMarketTradeDto dto) {
        return trdBondMarkettTradeMapper.selectIsInWorkflow(dto);
    }

    /**
     * 查询是否有当日交易数据
     * @param dto
     * @return
     */
    public int selectDealNum(TrdBondMarketTradeDto dto) {
        return  trdBondMarkettTradeMapper.selectDealNum(dto);
    }

    /**
     * 查询交易是否存在流程中的撤销数据
     *
     * @param dto 画面参数dto
     * @see
     * @since 2.1.7
     */
    public int selectRevokeDeal(TrdBondMarketTradeDto dto) {
        dto.setTradeStatus(DictCodeListExt1.交易状态_撤销.getValue());
        return  trdBondMarkettTradeMapper.selectRevokeDeal(dto);
    }

    /**
     * 查询是否有当日以及后续交易数据
     * @param dto
     * @return
     */
    public int selectDealNums(TrdBondMarketTradeDto dto) {
        return  trdBondMarkettTradeMapper.selectDealNums(dto);
    }

    /**
     * 根据汇总交易编号查询交易数据
     * @param totalTradeId
     * @return
     */
    public TrdBondMarketTradeDto selectByTotalTradeId(String totalTradeId) {
        return  trdBondMarkettTradeMapper.selectByTotalTradeId(totalTradeId);   
    }

    /**
     * 撤销数据处理
     * @param entity
     * @return
     */
    public void doCompleteRevoke(TrdBondMarketTradeDto entity) {
        String bizCode = entity.getTotleTradeId();
        if (bizCode.lastIndexOf('R') == bizCode.length() - 1) {
            bizCode = bizCode.substring(0, bizCode.length() - 1);
        }
        List<String> tradeIds =  trdBondMarkettTradeMapper.selectTrdProductDeals(bizCode);
        if(tradeIds != null && tradeIds.size()>0){
            List<PtlSecPositionFlowDto> ptlSecPositionFlows = trdBondMarkettTradeMapper.selectRevokePtlPositinFlows(tradeIds);
            List<PtlSecPositionFlowDto> pltSecListAll = new ArrayList<>();
            
            for (PtlSecPositionFlow ptlSecPositionFlow : ptlSecPositionFlows) {
                PtlSecPositionFlowDto ptlSecPositionFlowNe = new PtlSecPositionFlowDto();
                BeanUtil.copyPropertiesIgnoreNull(ptlSecPositionFlowNe,ptlSecPositionFlow);
                ptlSecPositionFlowNe.setTradeId(ptlSecPositionFlowNe.getTradeId()+"R");
                ptlSecPositionFlowNe.setAmount(ptlSecPositionFlowNe.getAmount().negate());
                ptlSecPositionFlowNe.setSeqno(IDGenerator.generate());
                pltSecListAll.add(ptlSecPositionFlowNe);
            }
            for (PtlSecPositionFlowDto ptlSecPositionFlowDto: pltSecListAll){
                basePtlSecPositionInfoService.inertSecPositionFlow(ptlSecPositionFlowDto);
            }
        }
        
        List<EtrSecPositionFlowDto> etrSecPositionFlow = trdBondMarkettTradeMapper.selectRevokeEtrPositinFlows(bizCode);
        List<EtrSecPositionFlowDto> etrSecListAll = new ArrayList<>();
        for (EtrSecPositionFlowDto secPositionFlow : etrSecPositionFlow) {
            EtrSecPositionFlowDto etrSecPositionFlowDto = new EtrSecPositionFlowDto();
            BeanUtil.copyPropertiesIgnoreNull(etrSecPositionFlowDto,secPositionFlow);
            etrSecPositionFlowDto.setAmount(etrSecPositionFlowDto.getAmount().negate());
            etrSecPositionFlowDto.setTradeId(etrSecPositionFlowDto.getTradeId()+"R");
            etrSecPositionFlowDto.setSeqno(IDGenerator.generate());
            etrSecListAll.add(etrSecPositionFlowDto);
        }
        if (!etrSecListAll.isEmpty()){
            EtrPositionHoldDto etrPositionHoldDto = new EtrPositionHoldDto();
            etrPositionHoldDto.setPtlSecPositionFlows(etrSecListAll);
            EtrCommonCashPositionDealService.持久化流水Dto(etrPositionHoldDto);
        }
        
        if (entity!= null){
            //更新发行代码金融产品附属表的上市代码
            entity.setAppearMarketId(null);
            trdBondMarkettTradeMapper.updateByPrimaryKey(entity);
        }    
    }

    /**
     * 撤销时根据交易编号查询交易
     * @param entity
     * @return
     */
    public TrdBondMarketTradeDto getRevokeEntity(TrdBondMarketTradeDto entity) {
        String bizCode = entity.getTotleTradeId();
        if (bizCode.lastIndexOf('R') == bizCode.length() - 1) {
            bizCode = bizCode.substring(0, bizCode.length() - 1);
        }
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("bizCode",bizCode);
        param.put("finprodId",DictCodeListExt1.金融产品类型_投管用_债券.getValue() + "_" + entity.getAppearMarketId());
        List<BaseTradeInfoDto> baseTradeInfoDtos =  trdBondMarkettTradeMapper.selectbaseTradeInfoDtos(param);
        entity.setBaseTradeInfoDtos(baseTradeInfoDtos);
        return entity;
    }
}
