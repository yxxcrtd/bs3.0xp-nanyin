package com.joyintech.tams.settle.batch.datagather.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.EtrEntrustAddMapper;
import com.joyintech.tams.common.base.dao.EtrFinishStatusMapper;
import com.joyintech.tams.common.base.dao.FinDealPledgeMapper;
import com.joyintech.tams.common.base.dao.FinProductTypeMapper;
import com.joyintech.tams.common.base.dao.FinTradeProductAddMapper;
import com.joyintech.tams.common.base.dao.FinTradeProductMapper;
import com.joyintech.tams.common.base.dao.PaTrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.PaTrdProductDealMapper;
import com.joyintech.tams.common.base.dao.PtlTradeMapper;
import com.joyintech.tams.common.base.dao.SettleTransTgMappingMapper;
import com.joyintech.tams.common.base.dao.StlPayOrdersMapper;
import com.joyintech.tams.common.base.dao.TrdDealTotalAddMapper;
import com.joyintech.tams.common.base.dao.TrdFinProductBarterMapper;
import com.joyintech.tams.common.base.dao.TrdFinProductBarterTotalMapper;
import com.joyintech.tams.common.base.dao.TrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealAddMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.dao.TrdTradeFeeDetailMapper;
import com.joyintech.tams.common.base.dao.TrdTradeFeeTotalMapper;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.tams.common.base.entity.EtrFinishStatus;
import com.joyintech.tams.common.base.entity.FinDealPledge;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.FinTradeProductAdd;
import com.joyintech.tams.common.base.entity.PaTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.SettleTransTgMapping;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdDealTotalAdd;
import com.joyintech.tams.common.base.entity.TrdFinProductBarter;
import com.joyintech.tams.common.base.entity.TrdFinProductBarterTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.common.base.entity.TrdTradeFeeTotal;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.DictCodeListStl;
import com.joyintech.tams.settle.batch.datagather.dao.StlDataGatherMapper;
import com.joyintech.tams.settle.batch.datagather.dto.BondTradeDealParam;
import com.joyintech.tams.settle.batch.datagather.dto.FinTradeProductDto;
import com.joyintech.tams.settle.batch.datagather.dto.InvestTradeWriteDto;
import com.joyintech.tams.settle.batch.datagather.dto.SettleDelDealDetailDto;
import com.joyintech.tams.settle.batch.datagather.dto.TotalTrdProductDealDto;
import com.joyintech.tams.settle.batch.datagather.dto.TrdProductDealDto;
import com.joyintech.tams.settle.common.dto.FundInfoDto;
import com.joyintech.tams.settle.common.service.SettleCommonService;

/**
 * @author 何阳阳
 * @date 2020/7/16
 * @see StlDataGatherService
 * @since 1.0
 */
@Service
public class StlDataGatherService {

    @Autowired(required = false)
    private JoyinRestTemplate restTemplate;

    @Value("${application.micorservice.contextPath.tamsServer:http://tams-server}")
    private String tamsSerever;
	
	@Autowired
    private StlDataGatherMapper stlDataGatherMapper;

    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;

    @Autowired
    private PaTrdFinProductDealTotalMapper paTrdFinProductDealTotalMapper;

    @Autowired
    private TrdDealTotalAddMapper trdDealTotalAddMapper;
    
    @Autowired
    private TrdFinProductBarterTotalMapper trdBarterTotalMapper;
    
    @Autowired
    private TrdFinProductBarterMapper trdBarterMapper;

    @Autowired
    private TrdProductDealAddMapper trdProductDealAddMapper;

    @Autowired
    private StlPayOrdersMapper stlPayOrdersMapper;

    @Autowired
    private TrdProductDealMapper trdProductDealMapper;

    @Autowired
    private PtlTradeMapper ptlTradeMapper;

    @Autowired
    private TrdTradeFeeDetailMapper trdTradeFeeDetailMapper;

    @Autowired
    private FinTradeProductMapper finTradeProductMapper;

    @Autowired
    private FinTradeProductAddMapper finTradeProductAddMapper;

    @Autowired
    private FinDealPledgeMapper finDealPledgeMapper;
    
    @Autowired
    private TrdTradeFeeTotalMapper trdTradeFeeTotalMapper;
    
    @Autowired
    private EtrEntrustAddMapper etrEntrustAddMapper;
    
    @Autowired
    private EtrFinishStatusMapper etrFinishStatusMapper;

    @Autowired
    private SettleCommonService settleCommonService;
    
    @Autowired
    private PaTrdProductDealMapper paTrdProductDealMapper;
    
    @Autowired
    private FinProductTypeMapper finProductTypeMapper;
    
    @Autowired
    private SettleTransTgMappingMapper settleTransTgMappingMapper;
	
	/**
     * 获取除权交易明细
     * @param paraMap 查询参数
     * @return 除权交易明细
     */
    public List<SettleDelDealDetailDto> selectSettleDelDealDetail(Map<String, Object> paraMap) {
    	return stlDataGatherMapper.selectSettleDelDealDetail(paraMap);
    }

    public int deleteParam(Date dealDate) {
        return stlDataGatherMapper.deleteParam(dealDate);
    }

    /**
     * 根据申请编号查询投管交易编号
     * @param applyTradeId 申请编号
     * @return 投管交易编号
     */
    public String selectBTradeId(String applyTradeId, String ps) {
        Map<String, String> paraMap = new HashMap<>(2);
        paraMap.put("ps", ps);
        paraMap.put("tradeId", applyTradeId);
        return stlDataGatherMapper.selectBTradeId(paraMap);
    }

    /**
     * 计算债券到期收益率和行权收益率
     * @param productDeal
     */
    public void calc(TrdProductDeal productDeal) {
        // 设置计算参数
        BondTradeDealParam dealParam = new BondTradeDealParam();
        dealParam.setFinprodId(productDeal.getFinprodId());
        dealParam.setTradeAmt(productDeal.getShareAmt().multiply(BigDecimal.valueOf(100)));
        dealParam.setSettleDate(productDeal.getSettleDate());
        dealParam.setVdate(productDeal.getVdate());
        dealParam.setTradeMarket(productDeal.getTradeMarket());
        dealParam.setTradeMarketMode(productDeal.getTradeMarketMode());
        dealParam.setPs(productDeal.getPs());
        dealParam.setUnitInt(productDeal.getUnitInt());
        dealParam.setUnitFprice(productDeal.getUnitFprice());

        // 设置计算类型为全价总额（根据全价总额计算到期收益率和行权收益率）
        dealParam.setCalculateField("fpriceAmt");
        dealParam.setFpriceAmt(productDeal.getFpriceAmt());
        dealParam.setIntAmt(productDeal.getIntAmt());
        dealParam.setInvAim(productDeal.getInvAim());

        // 调用债券交易计算逻辑
        Map ajaxResult = restTemplate.postForObject(tamsSerever + "/invest/trade/bond/TrdBondInvestTrade002/calculateData", dealParam, Map.class);
        if (ajaxResult != null && ajaxResult.containsKey("data")) {
	        Object result = ajaxResult.get("data");
	        if(result instanceof Map) {
	            Map<String, Object> calcResult = (Map)result;
	            Object yield = calcResult.get("yield");
	            Object exerYield = calcResult.get("exerYield");
	            if(yield instanceof BigDecimal) {
	                productDeal.setYield((BigDecimal)yield);
	            }
	            else if (yield != null) {
	                productDeal.setYield(new BigDecimal(yield.toString()));
	            }
	            if(exerYield instanceof BigDecimal) {
	                productDeal.setExerYield((BigDecimal)exerYield);
	            }
	            else if (exerYield != null) {
	                productDeal.setExerYield(new BigDecimal(exerYield.toString()));
	            }
	            if (productDeal.getYield() != null){
	                productDeal.setYield(productDeal.getYield().divide(BigDecimal.valueOf(100)));
	            }
                if (productDeal.getExerYield() != null){
                    productDeal.setExerYield(productDeal.getExerYield().divide(BigDecimal.valueOf(100)));
                }
	        }
        }
    }

    public void excutePosition(Map<String, Object> param) {
        // 调用债券交易计算逻辑
        restTemplate.postForObject(tamsSerever + "/invest/trade/api/execute", param, Map.class);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void writeData(InvestTradeWriteDto tradeWriteDto) throws InvocationTargetException, IllegalAccessException {
        /**
         * 金融产品汇总交易
         */
        List<TotalTrdProductDealDto> finProductDealTotalList = tradeWriteDto.getFinProductDealTotalList();

        /**
         * 金融产品交易明细
         */
        List<TrdProductDealDto> productDealList = tradeWriteDto.getProductDealList();

        /**
         * 组合交易DTO
         */
        List<PtlTrade> ptlTradeList = tradeWriteDto.getPtlTradeList();

        /**
         * 金融产品交易费用明细
         */
        List<TrdTradeFeeDetail> tradeFeeDetailList = tradeWriteDto.getTradeFeeDetailList();

        /**
         * 划款指令明细
         */
        List<StlPayOrders> stlPayOrdersList = tradeWriteDto.getStlPayOrdersList();

        /**
         * 交易类金融信息
         */
        List<FinTradeProductDto> tradeProductList = tradeWriteDto.getTradeProductList();

        /**
         * 回购质押品
         */
        List<FinDealPledge> dealPledgeList = tradeWriteDto.getDealPledgeList();
        
        /**
         *  汇总交易附属信息
         */
        List<TrdDealTotalAdd> trdDealTotalAddList = tradeWriteDto.getTrdDealTotalAddList();
        
        /**
         *  实物交易汇总表
         */
        List<TrdFinProductBarterTotal> trdBarterTotalList = tradeWriteDto.getTrdBarterTotalList();
        
        /**
         *  实物交易表
         */
        List<TrdFinProductBarter> trdBarterList = tradeWriteDto.getTrdBarterList();
        
        /**
         *  成交编号业务编号关联表（只针对除权登记数据）
         */
        List<SettleTransTgMapping> settleTransTgMappingList = tradeWriteDto.getSettleTransTgMappingList();
        
        /**
         * 日间委托完成度表有一笔交易编号等于自身委托编号的记录，这条记录每次正向跑批时需要实时更新
         */
        List<EtrFinishStatus> etrFinishStatusList = tradeWriteDto.getEtrFinishStatusList();

        // 全量基金信息获取
        Map<String, FundInfoDto> fundInfoDtoMap = settleCommonService.getAllFundInfoDto();

        if(CollectionUtils.isNotEmpty(finProductDealTotalList)) {
        	Map<String, List<TrdDealTotalAdd>> totleAddMap = new HashMap<String, List<TrdDealTotalAdd>>();
            if (CollectionUtils.isNotEmpty(trdDealTotalAddList)){
                totleAddMap = trdDealTotalAddList.stream().collect(Collectors.groupingBy(TrdDealTotalAdd::getTotleTradeId));
            }
            for (TotalTrdProductDealDto totalTrdProductDealDto : finProductDealTotalList) {
                // 汇总交易表
                trdFinProductDealTotalMapper.insertSelective(totalTrdProductDealDto);
                
                if (!CollectionUtils.isNotEmpty(totleAddMap.get(totalTrdProductDealDto.getTotleTradeId()))){
                    TrdDealTotalAdd trdDealTotalAdd = new TrdDealTotalAdd();
                    BeanUtil.copyProperties(trdDealTotalAdd, totalTrdProductDealDto);
                    // 汇总交易扩展表
                    trdDealTotalAddMapper.insertSelective(trdDealTotalAdd);
                }

                PaTrdFinProductDealTotal paProductDealTotal = new PaTrdFinProductDealTotal();
                paProductDealTotal.setTotleTradeId(totalTrdProductDealDto.getTotleTradeId());
                paProductDealTotal.setPaBussType(getPaBussType(totalTrdProductDealDto.getPs(), tradeWriteDto.getSecType(), totalTrdProductDealDto.getFinprodId(), fundInfoDtoMap));
                paProductDealTotal.setRegDate(totalTrdProductDealDto.getRegDate());
                if (StringUtil.isNotEmpty(totalTrdProductDealDto.getPayType())) {
                	paProductDealTotal.setPayType(totalTrdProductDealDto.getPayType());
                }
                if (StringUtil.isNotEmpty(totalTrdProductDealDto.getBonusCfmDate())) {
                	paProductDealTotal.setBonusCfmDate(totalTrdProductDealDto.getBonusCfmDate());
                }
                paTrdFinProductDealTotalMapper.insertSelective(paProductDealTotal);
            }
        }

        Map<String, List<TrdProductDealDto>> dealMap = new HashMap<String, List<TrdProductDealDto>>();
        if(CollectionUtils.isNotEmpty(productDealList)) {
            dealMap = productDealList.stream().collect(Collectors.groupingBy(TrdProductDealDto::getTradeId));
            for (TrdProductDealDto productDealDto : productDealList) {
                // 金融产品交易表
                trdProductDealMapper.insertSelective(productDealDto);
                // 金融产品交易扩展表
                TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
                BeanUtil.copyProperties(trdProductDealAdd, productDealDto);
                // 申购模式
                if ((DictCodeListExt1.金融产品类型_投管用_债券.getValue().equals(productDealDto.getFinprodType2()))
                        && DictCodeList.交易方向_申购确认.getValue().equals(productDealDto.getPs())) {
                	trdProductDealAdd.setPurchModle(DictCodeListExt2.申购方式_网上申购.getValue());
                }
                // 股票的现金分红交易，金融产品交易附属表的  REG_DATE  也要登值，估值那边说需要
                if ((DictCodeListExt1.金融产品类型_投管用_股票.getValue().equals(productDealDto.getFinprodType2()))
                        && DictCodeList.交易方向_现金分红.getValue().equals(productDealDto.getPs())) {
                	trdProductDealAdd.setRegDate(productDealDto.getRegDate());
                }
                trdProductDealAddMapper.insertSelective(trdProductDealAdd);
                // 回购时登录PA_TRD_PRODUCT_DEAL表
                if (StringUtil.equals(productDealDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue()) ||
                	StringUtil.equals(productDealDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())) {
                	PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
                	paTrdProductDeal.setTradeId(productDealDto.getTradeId());
                	if (StringUtil.equals(productDealDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())) {
                		paTrdProductDeal.setPaBussType(DictCodeListExt1.平安业务类型_正回购.getValue());
                		paTrdProductDeal.setBackLendDirection(DictCodeList.回购拆借方向_正回购.getValue());
                	}
                	else {
                		paTrdProductDeal.setPaBussType(DictCodeListExt1.平安业务类型_逆回购.getValue());
                		paTrdProductDeal.setBackLendDirection(DictCodeList.回购拆借方向_逆回购.getValue());
                	}
                	paTrdProductDealMapper.insert(paTrdProductDeal);
                }
                // 成交数据与投管交易关系
                if (StringUtil.isNotEmpty(productDealDto.getDealId())) {
                    List<String> tradeIdList = StringUtil.splitToList(",", productDealDto.getDealId());
                    Map<String, Object> paraMap = new HashMap<String, Object>();
                    paraMap.put("tradeIdList", tradeIdList);
                    paraMap.put("tradeIdTg", productDealDto.getTradeId());
                    paraMap.put("tgDataStatus", DictCodeListStl.投管交易数据状态_处理结束.getValue());
                    stlDataGatherMapper.updateSettleTransTgMapping(paraMap);
                }
            }
        }

        if (CollectionUtils.isNotEmpty(ptlTradeList)) {
            for (PtlTrade ptlTrade : ptlTradeList) {
                // 组合交易表
                ptlTradeMapper.insertSelective(ptlTrade);
            }
        }

        if(CollectionUtils.isNotEmpty(tradeFeeDetailList)) {
            for (TrdTradeFeeDetail feeDetail : tradeFeeDetailList) {
                // 金融产品交易费用明细
                trdTradeFeeDetailMapper.insertSelective(feeDetail);
                
                // 费用交易汇总
                if (CollectionUtils.isNotEmpty(dealMap.get(feeDetail.getTradeId())) && CollectionUtils.isNotEmpty(finProductDealTotalList)){
                    TrdTradeFeeTotal trdTradeFeeTotal = new TrdTradeFeeTotal();
                    BeanUtil.copyProperties(trdTradeFeeTotal, feeDetail);
                    trdTradeFeeTotal.setTotleTradeId(feeDetail.getTradeId());
                    // 基金股票没有汇总交易
                    String totleTradeId = dealMap.get(feeDetail.getTradeId()).get(0).getTotleTradeId();
                    if (totleTradeId != null){
                        trdTradeFeeTotal.setTotleTradeId(dealMap.get(feeDetail.getTradeId()).get(0).getTotleTradeId());
                    }
                    trdTradeFeeTotalMapper.insertSelective(trdTradeFeeTotal);
                }
            }
        }

        if(CollectionUtils.isNotEmpty(stlPayOrdersList)) {
            for (StlPayOrders stlPayOrders : stlPayOrdersList) {
                // 划款指令
                stlPayOrdersMapper.insertSelective(stlPayOrders);
            }
        }

        if(CollectionUtils.isNotEmpty(tradeProductList)) {
            for (FinTradeProductDto tradeProductDto : tradeProductList) {
                // 交易类金融产品表
                finTradeProductMapper.insertSelective(tradeProductDto);
                // 交易类金融产品附属表
                FinTradeProductAdd finTradeProductAdd = new FinTradeProductAdd();
                BeanUtil.copyProperties(finTradeProductAdd, tradeProductDto);
                finTradeProductAdd.setRegistOrg(DictCodeList.登记托管机构_中债_中国证券登记结算有限责任公司.getValue());
                finTradeProductAddMapper.insert(finTradeProductAdd);
                // FIN_PRODUCT_TYPE(固定设置质押式竞价回购)
                FinProductType finProductType = new FinProductType();
                finProductType.setFinprodId(tradeProductDto.getFinprodId());
                finProductType.setBranch(0L);
                finProductType.setType1("02");
                finProductType.setType2("02");
                finProductType.setType3("G03");
                finProductType.setType6("H02");
                finProductType.setType7("04");
                finProductType.setType8("F07");
                finProductType.setType9("06");
                finProductType.setType10("44");
                finProductType.setType11("092");
                finProductType.setType12("094");
                finProductTypeMapper.insert(finProductType);
            }
        }

        if(CollectionUtils.isNotEmpty(dealPledgeList)) {
            // 回购质押品
            for (FinDealPledge finDealPledge : dealPledgeList) {
                finDealPledgeMapper.insertSelective(finDealPledge);
            }
        }

        if(CollectionUtils.isNotEmpty(trdDealTotalAddList)) {
            // 汇总附属
            for (TrdDealTotalAdd trdDealTotalAdd : trdDealTotalAddList) {
                trdDealTotalAddMapper.insertSelective(trdDealTotalAdd);
            }
        }
        
        if(CollectionUtils.isNotEmpty(trdBarterTotalList)) {
            // 实物汇总
            for (TrdFinProductBarterTotal trdBarterTotal : trdBarterTotalList) {
            	trdBarterTotalMapper.insertSelective(trdBarterTotal);
            }
        }
        
        if(CollectionUtils.isNotEmpty(trdBarterList)) {
            // 实物
            for (TrdFinProductBarter trdFinProductBarter : trdBarterList) {
            	trdBarterMapper.insertSelective(trdFinProductBarter);
            }
        }
        
        // 日间委托完成度表有一笔交易编号等于自身委托编号的记录，这条记录每次正向跑批时需要实时更新
        if (CollectionUtils.isNotEmpty(etrFinishStatusList)){
        	for (EtrFinishStatus etrFinishStatus : etrFinishStatusList){
        		etrFinishStatus.setCreateTime(null);
        		etrFinishStatus.setCreateTime(null);
        		etrFinishStatus.setCreateDept(null);
        		etrFinishStatusMapper.updateByPrimaryKeySelective(etrFinishStatus);
        	}
        }
        
        // 成交编号业务编号关联表（只针对除权登记数据）
        if (CollectionUtils.isNotEmpty(settleTransTgMappingList)){
        	for (SettleTransTgMapping settleTransTgMapping : settleTransTgMappingList){
        		settleTransTgMappingMapper.insert(settleTransTgMapping);
        	}
        }
    }

    private String getPaBussType(String ps, String secType, String finprodId, Map<String, FundInfoDto> fundInfoDtoMap) {
        String paBussType = null;
        if(StringUtil.equals(DictCodeListExt1.证券品种_债券.getValue(), secType)) {
            if (StringUtil.equals(DictCodeList.交易方向_买入.getValue(), ps)) {
                paBussType = DictCodeListExt1.平安业务类型_债券类资产买入.getValue();
            }
            else if (StringUtil.equals(DictCodeList.交易方向_卖出.getValue(), ps)) {
                paBussType = DictCodeListExt1.平安业务类型_债券类资产卖出.getValue();
            }
            else if (StringUtil.equals(DictCodeList.交易方向_行权_回售.getValue(), ps)
                    || StringUtil.equals(DictCodeList.交易方向_行权_赎回.getValue(), ps)) {
                paBussType = DictCodeListExt1.平安业务类型_债券类资产行权.getValue();
            }
        }
        else if(StringUtil.equals(DictCodeListExt1.证券品种_回购.getValue(), secType)) {
            if (DictCodeList.回购拆借方向_正回购.getValue().equals(ps)) {
                paBussType = DictCodeListExt1.平安业务类型_正回购.getValue();
            } else {
                paBussType = DictCodeListExt1.平安业务类型_逆回购.getValue();
            }
        }
        else if(StringUtil.equals(DictCodeListExt1.证券品种_基金.getValue(), secType)) {
            FundInfoDto fundInfoDto = fundInfoDtoMap.get(finprodId);
            if(null != fundInfoDto) {
                String fundType = fundInfoDto.getType1();
                if (StringUtil.equals(fundType, DictCodeList.基金类型_货币基金.getValue())) {
                    if (StringUtil.equals(ps, DictCodeList.交易方向_申购.getValue())) {
                        paBussType = DictCodeListExt1.平安业务类型_货币基金申购申请.getValue();
                    }
                    else if (StringUtil.equals(ps, DictCodeList.交易方向_买入.getValue())) {
                        paBussType = DictCodeListExt3.平安业务类型_货币基金买入.getValue();
                    }
                    else if (StringUtil.equals(ps, DictCodeList.交易方向_赎回.getValue())) {
                        paBussType = DictCodeListExt1.平安业务类型_货币基金赎回申请.getValue();
                    }
                    else if (StringUtil.equals(ps, DictCodeList.交易方向_卖出.getValue())) {
                        paBussType = DictCodeListExt3.平安业务类型_货币基金卖出.getValue();
                    }
                    else if (StringUtil.equals(ps, DictCodeListExt2.交易方向_认购.getValue())) {
                        paBussType = DictCodeListExt3.平安业务类型_货币基金认购申请.getValue();
                    }
                    else if (StringUtil.equals(ps, DictCodeListExt2.交易方向_转换申请.getValue())) {
                        paBussType = DictCodeListExt1.平安业务类型_货币基金赎回申请.getValue();
                    }
                    else if (StringUtil.equals(ps, DictCodeList.交易方向_红利再投.getValue())) {
                        paBussType = DictCodeListExt1.平安业务类型_货币基金红利再投.getValue();
                    }
                }
                else {
                    if (StringUtil.equals(ps, DictCodeList.交易方向_申购.getValue())) {
                        paBussType = DictCodeListExt1.平安业务类型_非货币基金申购申请.getValue();
                    }
                    else if (StringUtil.equals(ps, DictCodeList.交易方向_买入.getValue())) {
                        paBussType = DictCodeListExt3.平安业务类型_非货币基金买入.getValue();
                    }
                    else if (StringUtil.equals(ps, DictCodeList.交易方向_赎回.getValue())) {
                        paBussType = DictCodeListExt1.平安业务类型_非货币基金赎回申请.getValue();
                    }
                    else if (StringUtil.equals(ps, DictCodeList.交易方向_卖出.getValue())) {
                        paBussType = DictCodeListExt3.平安业务类型_非货币基金卖出.getValue();
                    }
                    else if (StringUtil.equals(ps, DictCodeListExt2.交易方向_认购.getValue())) {
                        paBussType = DictCodeListExt3.平安业务类型_非货币基金认购申请.getValue();
                    }
                    else if (StringUtil.equals(ps, DictCodeListExt2.交易方向_转换申请.getValue())) {
                        paBussType = DictCodeListExt1.平安业务类型_非货币基金赎回申请.getValue();
                    }
                    else if (StringUtil.equals(ps, DictCodeList.交易方向_现金分红.getValue())) {
                        paBussType = DictCodeListExt1.平安业务类型_非货币基金现金分红.getValue();
                    }
                }
            }
        }
        else if(StringUtil.equals(DictCodeListExt1.证券品种_股票.getValue(), secType)) {
            paBussType = DictCodeListExt2.平安业务类型_股票交易.getValue();
        }
        return paBussType;
    }
    
    /**
     * 检索非货基现金分红对应的除权交易
     * @param paraMap Map<String, Object>
     * @return 非货基现金分红对应的除权交易
     */
    public List<TotalTrdProductDealDto> selectNhfTotalTrdProductDealDto(Map<String, Object> paraMap) {
    	return stlDataGatherMapper.selectNhfTotalTrdProductDealDto(paraMap);
    }
    
    /**
     * 根据交易编号查询汇总附属信息
     * 
     * @param tradeId 交易编号
     * @return 汇总附属信息
     * @see
     * @since 1.0
     */
    public TrdDealTotalAdd selectTotleDealByTotleId(String tradeId){
        return stlDataGatherMapper.queryTotalAddByTradeId(tradeId);
    }
    
    /**
     * 根据委托代码查询委托基本信息
     * 
     * @param entrustId 委托代码
     * @return 委托基本信息
     * @see
     * @since 1.0
     */
    public EtrEntrustAdd selectEtrEntrustByEntrustId(String entrustId){
    	return etrEntrustAddMapper.selectByPrimaryKey(entrustId);
    }
}
